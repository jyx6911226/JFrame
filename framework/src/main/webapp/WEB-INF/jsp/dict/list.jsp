<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>系统字典列表</title>
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
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- Content Wrapper. Contains page content -->
	<div>
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				系统字典管理 <small>列表</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}/welcome"><i
						class="fa fa-dashboard"></i> 首页</a></li>
				<li><a
					href="${pageContext.request.contextPath}/dict/initList">
						系统字典</a></li>
				<li class="active">系统字典列表</li>
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
												<input type="text" placeholder="名称" id="fuzzy-search"
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
												<shiro:hasPermission name="SysDict-Add-Btn">
													<button type="button" class="btn btn-primary" id="btn-add">
														<i class="fa fa-plus"></i> 添加
													</button>
												</shiro:hasPermission>
												<shiro:hasPermission name="SysDict-Del-Btn">
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
								<span>名称:</span> <input type="text" class="input-medium"
									placeholder="名称" id="name-search"> <span>代码:</span> <input
									type="text" class="input-medium" placeholder="代码"
									id="code-search">

								<button type="button" class="btn" id="btn-advanced-search">
									<i class="fa fa-search"></i> 查询
								</button>
							</form>
						</div>
						<!-- 模糊查询 end -->
						<!-- /.box-header -->
						<div class="box-body"
							id="div-table-container">
							<table id="table-data" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th><input type="checkbox" name="cb-check-all"></th>
										<th>名称</th>
										<th>代码</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
								<tfoot>
									<tr>
										<th><input type="checkbox" name="cb-check-all"></th>
										<th>名称</th>
										<th>代码</th>
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
	<!-- SlimScroll -->
	<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/slimScroll/jquery.slimscroll.min.js"></script> -->
	<!-- FastClick -->
	<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/fastclick/fastclick.js"></script>  -->
	<!-- AdminLTE App -->
	<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/js/app.min.js"></script>  -->
	<!-- AdminLTE for demo purposes -->
	<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/js/demo.js"></script>  -->
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
		src="${pageContext.request.contextPath}/js/datatables/sysdict.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/lib/json2.js"></script>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<script>
        var shiro_Update_Btn = false;
        var shiro_UpdateDetail_Btn = false;
        var shiro_Del_Btn = false;
	</script>
	<shiro:hasPermission name="SysDict-Update-Btn">
		<script>
            shiro_Update_Btn = true;
		</script>
	</shiro:hasPermission>
	<shiro:hasPermission name="SysDict-UpdateDetail-Btn">
		<script>
            shiro_UpdateDetail_Btn  = true;
		</script>
	</shiro:hasPermission>
	<shiro:hasPermission name="SysDict-Del-Btn">
		<script>
            shiro_Del_Btn  = true;
		</script>
	</shiro:hasPermission>
</body>
</html>
