<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色管理</title>
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
    <!-- iCheck -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/iCheck/square/blue.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/css/skins/_all-skins.min.css">
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
            角色管理
        </h1>
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/welcome"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${pageContext.request.contextPath}/sysRole/initList"> 角色管理</a></li>
        </ol>
    </section>
    <!-- 权限维护from -->
    <div class="col-md-12">
        <div class="box box-info">
            <div class="col-md-6">
                <div class="form-group">
                    <label>名称</label>
                    <input type="text" id="role_name" name="name" value="${obj.name}" class="form-control"
                           placeholder="名称" disabled>
                </div>
                <div class="form-group">
                    <label>标识</label>
                    <input type="text" id="role_code" name="role" value="${obj.role}" class="form-control"
                           placeholder="标识" disabled>
                </div>

                <div class="form-group">
                    <label>描述</label>
                    <input type="text" id="role_description" name="description" value="${obj.description}"
                           class="form-control" placeholder="描述" disabled>
                </div>
                <div class="form-group">
                    <label>状态</label>
                    <input type="text" id="role_available" name="available" value="${obj.available?'有效':'无效'}"
                           class="form-control" placeholder="状态" disabled>
                </div>
            </div>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/spin-2.1.0/jquery.spin.merge.js"></script>
<!-- lhgdialog -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/lib/lhgdialog-4.2.0/lhgdialog.js?skin=bootstrap2"></script>
<!-- page script -->
<!-- iCheck -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript">
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>

</body>
</html>
