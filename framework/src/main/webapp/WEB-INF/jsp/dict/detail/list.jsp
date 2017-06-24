<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>字典明细</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta
            content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
            name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/AdminLTE-2.3.7/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/iCheck/square/blue.css">
    <!-- bootstrapValidator -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bootstrapvalidator-0.4.5/src/css/bootstrapValidator.css"/>
    <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/css/skins/_all-skins.min.css">
    <!-- ztree-->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Content Wrapper. Contains page content -->
<div>
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            字典明细
            <small>列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/welcome"><i
                    class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${pageContext.request.contextPath}/dict/initList">
                系统字典</a></li>
            <li><a
                    href="${pageContext.request.contextPath}/dictDetail/initList/">
                字典明细</a></li>
            <li class="active">权限列表</li>
        </ol>
    </section>
    <!-- ztree -->
    <div class="col-md-4">
        <div class="box box-primary">
            <div class="zTreeDemoBackground left">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>
    </div>
    <!-- 明细维护from -->
    <div class="col-md-8">
        <div class="box box-info">
            <div class="login-box-msg">
                <div id="successAlert" class="alert alert-success hide">
                    <strong>保存成功！</strong>
                </div>
                <div id="warnAlert" class="alert alert-warning hide">
                    <strong>操作失败！</strong>
                </div>
            </div>
            <form id="defaultForm" action="#" method="post">
                <input type="hidden" id="node_id" name="id" value="">
                <input type="hidden" id="parent_id" name="parentId" value="1">
                <input type="hidden" id="parent_ids" name="parentIds" value="1">
                <input type="hidden" id="createdTime" name="createdTime" value="">
                <input type="hidden" id="lastUpdateTime" name="lastUpdateTime" value="">
                <div class="form-group">
                    <label>上级选项</label> <input type="text" id="parent_name" disabled
                                               class="form-control" placeholder="">
                </div>
                <div class="form-group">
                    <label>标签</label> <input type="text" id="node_name" name="label"
                                             class="form-control" placeholder="标签"> <span
                        class="form-control-feedback"></span>
                </div>
                <div class="form-group">
                    <label>选项值</label> <input type="text" id="value"
                                              name="value" class="form-control" placeholder="选项值">
                    <span class="form-control-feedback"></span>
                </div>
                <div class="box-footer">
                    <shiro:hasPermission name="SysDictDetail-Add-Btn">
                        <button type="button" id="add_child_node"
                                class="btn btn-default btn-left">添加子选项
                        </button>
                        <button type="submit" class="btn btn-info">保存</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="SysDictDetail-Del-Btn">
                        <button type="button" id="del_node" class="btn btn-danger btn-del">删除</button>
                    </shiro:hasPermission>
                </div>
            </form>
        </div>
    </div>
    <!-- /.box-body -->
</div>
<!-- /.content-wrapper -->
<!-- jQuery 2.2.3 -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/jQuery/jquery-2.2.3.js"></script>
<!-- Bootstrap 3.3.6 -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/AdminLTE-2.3.7/bootstrap/js/bootstrap.min.js"></script>
<!-- SpinJS-->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/lib/spin-2.1.0/jquery.spin.merge.js"></script>
<!-- lhgdialog -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/lib/lhgdialog-4.2.0/lhgdialog.js?skin=bootstrap2"></script>
<!-- page script -->
<!-- ztree -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.core.js"></script>
<!-- iCheck -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/iCheck/icheck.min.js"></script>
<!-- bootstrapValidator -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/bootstrapvalidator-0.4.5/dist/js/bootstrapValidator.js"></script>
<script type="text/javascript">
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
<script type="text/javascript">
    $(document).ready(function () {
        formValidator();
    });
    function formValidator() {
        /**
         * 下面是进行插件初始化
         * 你只需传入相应的键值对
         * */
        $('#defaultForm')
            .bootstrapValidator(
                {
                    message: '<spring:message code="valid.value.not_valid" />',
                    feedbackIcons: {
                        /*输入框不同状态，显示图片的样式*/
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        /*验证*/
                        label: {
                            /*键名name和input name值对应*/
                            message: '请输入选项标注',
                            validators: {
                                notEmpty: {
                                    /*非空提示*/
                                    message: '标注不能为空'
                                },
                                stringLength: {
                                    /*长度提示*/
                                    min: 1,
                                    max: 32,
                                    message: '请输入1-32位的选项标注'
                                },
                                remote: {
                                    message: '选项标注已经存在',
                                    url: '${pageContext.request.contextPath}/dictDetail/validUnique',
                                    data: {
                                        id: function () {
                                            return $("#node_id").val();
                                        },
                                        parentId: function () {
                                            return $("#parent_id")
                                                .val();
                                        },
                                        dictId: "${obj.id}"
                                    },
                                    delay: 2000
                                }
                            }
                        },
                        value: {
                            message: '请输入选项值',
                            validators: {
                                notEmpty: {
                                    message: '选项值不能为空'
                                },
                                remote: {
                                    message: '选项值已经存在',
                                    url: '${pageContext.request.contextPath}/dictDetail/validUnique',
                                    data: {
                                        id: function () {
                                            return $("#node_id")
                                                .val();
                                        },
                                        parentId: function () {
                                            return $("#parent_id")
                                                .val();
                                        },
                                        dictId: "${obj.id}"
                                    },
                                    delay: 2000
                                }
                            }
                        }
                    },
                    submitHandler: function (validator, form,
                                             submitButton) {
                        $.ajax({
                                type: 'post',
                                url: '${pageContext.request.contextPath}/dictDetail/${obj.id}',
                                data: form.serialize(),
                                success: function (data) {
                                    $(form)[0].reset();
                                    $(form).bootstrapValidator(
                                        'resetForm');
                                    if (data.success == true) {
                                        $("#successAlert")
                                            .removeClass(
                                                "hide");
                                        $("#warnAlert")
                                            .addClass(
                                                "hide");
                                    } else {
                                        $("#warnAlert")
                                            .removeClass(
                                                "hide");
                                        $("#successAlert")
                                            .addClass(
                                                "hide");
                                    }
                                    $.fn.zTree.init(
                                        $("#treeDemo"),
                                        setting, null);
                                }
                            });
                    }
                });
    }
</script>
<script type="text/javascript">
    function ajaxDataFilter(treeId, parentNode, responseData) {
        return responseData.list.content;
    }
    var setting = {
        data: {
            key:{
                name: "label"
            },
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId"
            }
        },
        async: {
            enable: true,
            url: "${pageContext.request.contextPath}/dictDetail/getListByDict/${obj.id}",
            dataFilter: ajaxDataFilter
        },
        callback: {
            onClick: zTreeOnClick
        }
    };
    function zTreeOnClick(event, treeId, treeNode) {
        $("#node_id").val(treeNode.id);
        $("#node_name").val(treeNode.label);
        $("#resourceType").val(treeNode.resourceType);
        var parent_ids = ",";
        for (var i = 0; i < treeNode.getPath().length - 1; i++) {
            parent_ids += treeNode.getPath()[i].id + ",";
        }
        $("#parent_ids").val(parent_ids);

        $("#value").val(treeNode.value);
        var parentNode = treeNode.getParentNode();
        if (parentNode != null) {
            $("#parent_id").val(parentNode.id);
            $("#parent_name").val(parentNode.label);
        } else {
            $("#parent_id").val(0);
            $("#parent_name").val("无");
        }
    }
    ;
    $(document).ready(function () {
        $.fn.zTree.init($("#treeDemo"), setting, null);
    });
</script>
<!--添加子节点 -->
<script type="text/javascript">
    $("#add_child_node").click(function () {
        var pid = $("#node_id").val();
        var pname = $("#node_name").val();
        var pids = $("#parent_ids").val() + pid + ",";
        $("#node_id").val("");
        $("#parent_id").val(pid);
        $("#parent_ids").val(pids);
        $("#parent_name").val(pname);
        $("#node_name").val("");
        $("#value").val("");
    });
</script>
<!-- 删除权限 -->
<script type="text/javascript">
    $("#del_node").click(function () {
        var message = "确定要删除该选项及其子选项吗?";
        var id = $("#node_id").val();
        if(id ==""){
            $.dialog.alert("请先选择要删除的选项");
            return;
        }
        $.dialog.confirmDanger(message, function () {
            $.ajax({
                type: "delete",
                url: "../" + id,
                //json中数据节点的name
                success: function (result) {
                    //setTimeout仅为测试遮罩效果
                    setTimeout(function () {
                        //异常判断与处理
                        if (result.success) {
                            $("#defaultForm")[0].reset();
                            $.fn.zTree.init($("#treeDemo"), setting, null);
                            $.dialog.alert("删除成功。");
                            return;
                        } else {
                            $.dialog.alert("删除失败。错误码：" + result.error);
                            return;
                        }

                    }, 200);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $.dialog.alert("删除失败");
                }
            });
        });
    });
</script>
</body>
</html>
