<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>用户列表</title>
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
<!-- DataTables -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/datatables/dataTables.bootstrap.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/css/skins/_all-skins.min.css">
<!--[if lt IE 9]>


    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- Content Wrapper. Contains page content -->
	<div>
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				用户管理 <small>列表</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}/welcome"><i
						class="fa fa-dashboard"></i> 首页</a></li>
				<li><a
					href="${pageContext.request.contextPath}/userInfo/initList">
						资源管理</a></li>
				<li><a
					href="${pageContext.request.contextPath}/userInfo/initList">
						用户管理</a></li>
				<li class="active">用户列表</li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<!-- 模糊查询 start-->
						<div class="row-fluid margin">
							<div class="span12">
								<div class="btn-toolbar">
									<div class="pull-right">
										<div class="input-append">
											<div class="input-group">
												<!-- /btn-group -->
												<input type="text" placeholder="用户名" id="fuzzy-search"
													class="form-control">
												<div class="input-group-btn">
													<button type="button" class="btn" id="btn-simple-search">
														<i class="fa fa-search"></i>
													</button>
												</div>
												<button type="button" class="btn" title="高级查询"
													id="toggle-advanced-search">
													<i class="fa fa-angle-double-down"></i>
												</button>
                                                <shiro:hasPermission name="UserInfo-Add-Btn">
                                                    <button type="button" class="btn btn-primary" id="btn-add">
                                                        <i class="fa fa-plus"></i> 添加
                                                    </button>
                                                </shiro:hasPermission>
                                                <shiro:hasPermission name="UserInfo-Del-Btn">
                                                    <button type="button" class="btn btn-danger" id="btn-del">
                                                        <i class="fa fa-remove"></i> 删除
                                                    </button>
                                                </shiro:hasPermission>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row-fluid" style="display: none;"
							id="div-advanced-search">
							<form class="form-inline well">
								<span>姓名:</span> <input type="text" class="input-medium"
									placeholder="姓名" id="name-search"> <span>用户名:</span> <input
									type="text" class="input-medium" placeholder="用户名"
									id="username-search"> <span>手机:</span> <input
									type="text" class="input-medium" placeholder="手机"
									id="telephone-search"> <span>电子邮箱:</span> <input
									type="text" class="input-medium" placeholder="电子邮箱"
									id="email-search">

                            <!-- <span>在线状态:</span>
                            <select class="input-small" id="status-search">
                                <option value="">全部</option>
                                <option value="1">在线</option>
                                <option value="0">离线</option>
                            </select>
                            <select class="input-small" id="role-search">
                                <option value="">全部</option>
                                <option value="1">管理员</option>
                                <option value="0">操作员</option>
                            </select> -->
								<button type="button" class="btn" id="btn-advanced-search">
									<i class="fa fa-search"></i> 查询
								</button>
							</form>
						</div>
						<!-- 模糊查询 end -->
						<!-- /.box-header -->
						<div class="box-body"
							id="div-table-container">
							<table id="table-user" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><input type="checkbox" name="cb-check-all"></th>
										<th>id</th>
										<th>用户名</th>
										<th>姓名</th>
										<th>邮箱</th>
										<th>电话</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
								<tfoot>
									<tr>
										<th><input type="checkbox" name="cb-check-all"></th>
										<th>id</th>
										<th>用户名</th>
										<th>姓名</th>
										<th>邮箱</th>
										<th>电话</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</tfoot>
							</table>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<!-- jQuery 2.2.3 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/jQuery/jquery-2.2.3.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/AdminLTE-2.3.7/bootstrap/js/bootstrap.min.js"></script>
	<!-- DataTables -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/datatables/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/datatables/dataTables.bootstrap.js"></script>
	<!-- SpinJS-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/lib/spin-2.1.0/jquery.spin.merge.js"></script>
	<!-- lhgdialog -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/lib/lhgdialog-4.2.0/lhgdialog.js?skin=bootstrap2"></script>
	<!-- page script -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/datatables/constant.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/datatables/userinfo.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/lib/json2.js"></script>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script>
        var shiro_Update_Btn = false;
        var shiro_UpdateRole_Btn = false;
        var shiro_Del_Btn = false;
    </script>
    <shiro:hasPermission name="UserInfo-Update-Btn">
        <script>
            shiro_Update_Btn = true;
        </script>
    </shiro:hasPermission>
    <shiro:hasPermission name="UserInfo-UpdateRole-Btn">
        <script>
            shiro_UpdateRole_Btn  = true;
        </script>
    </shiro:hasPermission>
    <shiro:hasPermission name="UserInfo-Del-Btn">
        <script>
            shiro_Del_Btn  = true;
        </script>
    </shiro:hasPermission>
</body>
</html>
