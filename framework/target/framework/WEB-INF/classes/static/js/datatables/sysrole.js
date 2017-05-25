var _table;
$(function (){
	var $wrapper = $('#div-table-container');
	var $table = $('#table-data');

	_table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
		ajax : function(data, callback, settings) {//ajax配置为function,手动调用异步查询
			//手动控制遮罩
			$wrapper.spinModal();
			//封装请求参数
			var param = userManage.getQueryCondition(data);
			$.ajax({
		            type: "post",
		            url: "./getList",
		            cache : false,	//禁用缓存
		            data: param,	//传入已封装的参数
		            dataType: "json",
		            		//json中数据节点的name
		            success: function(result) {
		            		//异常判断与处理
		            		if (result.success) {

							}else{
                                $.dialog.alert("查询失败。错误码："+result.error);
                                return;
							}
		            		//封装返回数据，这里仅演示了修改属性名
		            		var returnData = {};
			            	returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
			            	returnData.recordsTotal = result.total;
			            	returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
			            	returnData.data = result.pageData.content;
			            	//关闭遮罩
			            	$wrapper.spinModal(false);
			            	//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
			            	//此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
			            	callback(returnData);
		            },
		            error: function(XMLHttpRequest, textStatus, errorThrown) {
		                $.dialog.alert("查询失败");
		                $wrapper.spinModal(false);
		            }
		        });
		},
        columns: [
            CONSTANT.DATA_TABLES.COLUMN.CHECKBOX,
            {
            	//className : "ellipsis",	//文字过长时用省略号显示，CSS实现
            	data: "id"
            	//render : CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,//会显示省略号的列，需要用title属性实现划过时显示全部文本的效果
            },
            {
            	//className : "ellipsis",	
            	data: "role"
            	//render : CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
            	//固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
				//切记设置table样式为table-layout:fixed; 否则列宽不会强制为指定宽度，也不会出现省略号。
				//width : "20px"			
            },
            {
            	//className : "ellipsis",	
            	data: "name"
            	//render : CONSTANT.DATA_TABLES.RENDER.ELLIPSIS,
            	//固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
				//切记设置table样式为table-layout:fixed; 否则列宽不会强制为指定宽度，也不会出现省略号。
				//width : "20px"			
            },
			 {
				data: "available"
				//width : "120px"
			},{
				className : "td-operation",
				data: null,
				defaultContent:"",
				orderable : false
				//width : "120px"
			}
        ],
        "createdRow": function ( row, data, index ) {
        	//行渲染回调,在这里可以对该行dom元素进行任何操作
        	//给当前行加样式
        	//if (data.role) {
        	//	$(row).addClass("info");
			//}
        	//给当前行某列加样式
        	//$('td', row).eq(3).addClass(data.status?"text-success":"text-error");
        	//不使用render，改用jquery文档操作呈现单元格
            var $btnEdit = $('<button type="button" class="btn btn-small btn-primary btn-edit">修改</button>');
            var $btnDel = $('<button type="button" class="btn btn-small btn-danger btn-del">删除</button>');
            //var $btnView = $('<button type="button" class="btn btn-small btn-danger btn-del">查看</button>');
            $('td', row).eq(7).append($btnEdit).append($btnDel);
            
        },
        "drawCallback": function( settings ) {
        	//渲染完毕后的回调
        	//清空全选状态
			$(":checkbox[name='cb-check-all']",$wrapper).prop('checked', false);
        	//默认选中第一行
        	//$("tbody tr",$table).eq(0).click();
        }
	})).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
	//添加
	$("#btn-add").click(function(){		
		userManage.addItemInit();
	});
	//删除
	$("#btn-del").click(function(){
		var arrItemId = [];
        $("tbody :checkbox:checked",$table).each(function(i) {
        	var item = _table.row($(this).closest('tr')).data();
        	arrItemId.push(item);
        });
		userManage.deleteItem(arrItemId);
	});
	
	$("#btn-simple-search").click(function(){
		userManage.fuzzySearch = true;		
		//reload效果与draw(true)或者draw()类似,draw(false)则可在获取新数据的同时停留在当前页码,可自行试验
//		_table.ajax.reload();
//		_table.draw(false);
		_table.draw();
	});
	
	$("#btn-advanced-search").click(function(){
		userManage.fuzzySearch = false;
		_table.draw();
	});
	
	$("#btn-save-add").click(function(){
		userManage.addItemSubmit();
	});
	
	$("#btn-save-edit").click(function(){
		userManage.editItemSubmit();
	});
	
	//行点击事件
	$("tbody",$table).on("click","tr",function(event) {
		$(this).addClass("active").siblings().removeClass("active");
		//获取该行对应的数据
		var item = _table.row($(this).closest('tr')).data();
		userManage.currentItem = item;
		userManage.showItemDetail(item);
    });
	
	$table.on("change",":checkbox",function() {
		if ($(this).is("[name='cb-check-all']")) {
			//全选
			$(":checkbox",$table).prop("checked",$(this).prop("checked"));
		}else{
			//一般复选
			var checkbox = $("tbody :checkbox",$table);
			$(":checkbox[name='cb-check-all']",$table).prop('checked', checkbox.length == checkbox.filter(':checked').length);
		}
    }).on("click",".td-checkbox",function(event) {
    	//点击单元格即点击复选框
    	!$(event.target).is(":checkbox") && $(":checkbox",this).trigger("click");
    }).on("click",".btn-edit",function() {
    	//点击编辑按钮
        var item = _table.row($(this).closest('tr')).data();
		$(this).closest('tr').addClass("active").siblings().removeClass("active");
		userManage.currentItem = item;
		userManage.editItemInit(item);
	}).on("click",".btn-del",function() {
		//点击删除按钮
		var item = _table.row($(this).closest('tr')).data();
		$(this).closest('tr').addClass("active").siblings().removeClass("active");
		userManage.deleteItem([item]);
	});
	
	$("#toggle-advanced-search").click(function(){
		$("i",this).toggleClass("fa-angle-double-down fa-angle-double-up");
		$("#div-advanced-search").slideToggle("fast");
	});
	
	//$("#btn-info-content-collapse").click(function(){
	//	$("i",this).toggleClass("fa-minus fa-plus");
	//	$("span",this).toggle();
	//	$("#user-view .info-content").slideToggle("fast");
	//});
	
	//$("#btn-view-edit").click(function(){
	//	userManage.editItemInit(userManage.currentItem);
	//});
	
	//$(".btn-cancel").click(function(){
		//userManage.showItemDetail(userManage.currentItem);
	//});
});

var userManage = {
	currentItem : null,
	fuzzySearch : true,
	getQueryCondition : function(data) {
		var param = {};
		//组装排序参数
		if (data.order&&data.order.length&&data.order[0]) {
			switch (data.order[0].column) {
			case 1:
				param.orderColumn = "id";
				break;
			case 2:
				param.orderColumn = "role";
				break;
			case 3:
				param.orderColumn = "name";
				break;
			default:
				param.orderColumn = "available";
				break;
			}
			param.orderDir = data.order[0].dir;
		}
		//组装查询参数
		param.fuzzySearch = userManage.fuzzySearch;
		if (userManage.fuzzySearch) {
			param.role = $("#fuzzy-search").val();
		}else{
			param.name = $("#name-search").val();
			param.role = $("#role-search").val();
			param.available = $("#available-search").val();
		}
		//组装分页参数
		param.startIndex = data.start;
		param.pageSize = data.length;
		param.draw = data.draw;
		param.pageNo = data.start/data.length;
		return param;
	},
	showItemDetail : function(item) {
		if(item){
			window.location.href="./initView/"+item.id;
		}
	},
	addItemInit : function() {
		//$("#form-add")[0].reset();
		//打开添加页面
		//$("#user-add").show().siblings(".info-block").hide();
        window.location.href="./initAdd";
	},
	editItemInit : function(item) {
		if (!item) {
			return;
		}
//		$("#form-edit")[0].reset();
//		$("#title-edit").text(item.name);
//		$("#name-edit").val(item.name);
//		$("#position-edit").val(item.position);
//		$("#salary-edit").val(item.salary);
//		$("#start-date-edit").val(item.start_date);
//		$("#office-edit").val(item.office);
//		$("#extn-edit").val(item.extn);
//		$("#role-edit").val(item.role);
		
//		$("#user-edit").show().siblings(".info-block").hide();
	},
//	addItemSubmit : function() {
//		$.dialog.tips('保存当前添加用户');
//	},
//	editItemSubmit : function() {
//		$.dialog.tips('保存当前编辑用户');
//	},
	deleteItem : function(selectedItems) {
		var message;
		if (selectedItems&&selectedItems.length) {
			if (selectedItems.length == 1) {
				message = "确定要删除吗?";
			}else{
				message = "确定要删除选中的"+selectedItems.length+"项记录吗?";
			}
			$.dialog.confirmDanger(message, function(){
                $.ajax({
                    type: "delete",
                    url: "./",
                    data: JSON.stringify(selectedItems),	//传入已封装的参数
                    contentType:"application/json",
                    dataType: "json",
                    //json中数据节点的name
                    success: function(result) {
                        //setTimeout仅为测试遮罩效果
                        setTimeout(function(){
                            //异常判断与处理
                            if (result.success) {
                                _table.ajax.reload();
                                $.dialog.alert("删除成功。");
                                return;
                            }else{
                                $.dialog.alert("删除失败。错误码："+result.error);
                                return;
							}
                        },200);
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        $.dialog.alert("删除失败");
                    }
                });
			});
		}else{
			$.dialog.tips('请先选中要操作的行');
		}
	}
};