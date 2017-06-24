<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色列表</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE-2.3.7/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/css/skins/_all-skins.min.css">
    <!-- ztree-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
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
            用户管理
            <small>角色挂接</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/welcome"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${pageContext.request.contextPath}/userInfo/initList"> 资源管理</a></li>
            <li><a href="${pageContext.request.contextPath}/userInfo/initList"> 用户管理</a></li>
            <li class="active">用户角色挂接</li>
        </ol>
    </section>
    <!-- 角色ztree -->
    <div class="col-md-4">
        <div class="box box-primary">
            <div class="zTreeDemoBackground left">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>
        <shiro:hasPermission name="UserInfo-UpdateRole-Btn">
            <button type="button" id = "btn-save" class="btn btn-info btn-right">保存</button>
        </shiro:hasPermission>
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
<!-- SlimScroll -->
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/slimScroll/jquery.slimscroll.min.js"></script> -->
<!-- FastClick -->
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/fastclick/fastclick.js"></script>  -->
<!-- AdminLTE App
<script type="text/javascript" src="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/js/app.min.js"></script>-->
<!-- AdminLTE for demo purposes -->
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/js/demo.js"></script>  -->
<!-- SpinJS-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/spin-2.1.0/jquery.spin.merge.js"></script>
<!-- lhgdialog -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/lib/lhgdialog-4.2.0/lhgdialog.js?skin=bootstrap2"></script>
<!-- page script -->
<!-- ztree -->
<script type="text/javascript" src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript">
    function ajaxDataFilter(treeId, parentNode, responseData) {
        return responseData.list.content;
    }
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id"
            }
        },
        check: {
            enable: true
        },
        async: {
            enable: true,
            url: "${pageContext.request.contextPath}/sysRole/getAllList",
            dataFilter: ajaxDataFilter
        },
        callback: {
        }
    };
    function initZTreeCheck(id) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/sysRole/getListByUserInfo/${obj.id}",
            contentType:"application/json",
            dataType: "json",
            success: function(result) {
                //setTimeout仅为测试遮罩效果
                setTimeout(function(){
                    //异常判断与处理
                    if (result.success == true) {
                        $.each( result.list, function(index, role) {
                            var node = zTree.getNodeByParam("id",role.id);
                            if(node) {
                                node.checked = true;
                            }
                        });
                    }else{
                        $.dialog.alert("获取用户对应角色失败。错误码："+result.error);
                        return;
                    }
                },200);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                $.dialog.alert("获取角色对应权限失败");
            }
        });
    }
    //保存角色
    function saveRole(){
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = zTree.getCheckedNodes(true);
        var rids = new Array();
        $.each( nodes, function(index, node){
            rids.push(node.id);
        });
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/sysRole/saveRoleList/${obj.id}",
            data: JSON.stringify(rids),
            contentType:"application/json",
            dataType: "json",
            success: function(result) {
                //setTimeout仅为测试遮罩效果
                setTimeout(function(){
                    //异常判断与处理
                    if (result.success == true) {
                        $.dialog.alert("保存成功");
                    }else{
                        $.dialog.alert("保存失败");
                        return;
                    }
                },200);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                $.dialog.alert("获取角色对应权限失败");
            }
        });
    }
    $(document).ready(function () {
        //获取全部权限
        $.fn.zTree.init($("#treeDemo"), setting, null);
        //根据选择的角色设置选中状态
        initZTreeCheck(${obj.id});
        $("#btn-save").on("click",function(){saveRole();});
    });
</script>
</body>
</html>
