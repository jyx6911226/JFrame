<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>字典维护</title>
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
	href="${pageContext.request.contextPath}/bootstrapvalidator-0.4.5/src/css/bootstrapValidator.css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/css/skins/_all-skins.min.css">
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
			<h1>字典管理</h1>
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}/welcome"><i
						class="fa fa-dashboard"></i> 首页</a></li>
				<li><a href="${pageContext.request.contextPath}/dict/initList">
						字典管理</a></li>
				<li><a href="${pageContext.request.contextPath}/dict/initAdd">
						字典维护</a></li>
			</ol>
		</section>
		<!-- 字典维护from -->
		<div class="col-md-12">
			<div class="box box-info">
				<div class="col-md-6">
					<div class="login-box-msg">
						<div id="successAlert" class="alert alert-success hide">
							<strong>保存成功！</strong>
						</div>
						<div id="warnAlert" class="alert alert-warning hide">
							<strong>操作失败！</strong>
						</div>
					</div>
					<form id="defaultForm" action="#" method="post">
						<input type="hidden" id="sysdict_id" name="id" value="${obj.id}">
						<input type="hidden" name="createdTime" value="${obj.createdTime}">
						<input type="hidden" name="lastUpdateTime" value="${obj.lastUpdateTime}">
						<div class="form-group">
							<label>名称</label> <input type="text" id="sysdict_name"
								name="name" value="${obj.name}" class="form-control"
								placeholder="名称"> <span class="form-control-feedback"></span>
						</div>
						<div class="form-group">
							<label>标识</label> <input type="text" id="sysdict_code"
								name="code" value="${obj.code}" class="form-control"
								placeholder="标识"> <span class="form-control-feedback"></span>
						</div>
						<div class="box-footer">
							<shiro:hasPermission name="SysDict-Add-Btn">
							<button type="submit" class="btn btn-info">保存</button>
							</shiro:hasPermission>
						</div>
					</form>
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
	<!-- SlimScroll -->
	<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/slimScroll/jquery.slimscroll.min.js"></script> -->
	<!-- FastClick -->
	<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/fastclick/fastclick.js"></script>  -->
	<!-- AdminLTE App
<script type="text/javascript" src="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/js/app.min.js"></script>-->
	<!-- AdminLTE for demo purposes -->
	<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/js/demo.js"></script>  -->
	<!-- SpinJS-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/lib/spin-2.1.0/jquery.spin.merge.js"></script>
	<!-- lhgdialog -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/lib/lhgdialog-4.2.0/lhgdialog.js?skin=bootstrap2"></script>
	<!-- page script -->
	<!-- iCheck -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/iCheck/icheck.min.js"></script>
	<!-- bootstrapValidator -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/bootstrapvalidator-0.4.5/dist/js/bootstrapValidator.js"></script>
	<script type="text/javascript">
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
		});
	</script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							/**
							 * 下面是进行插件初始化
							 * 你只需传入相应的键值对
							 * */
							$('#defaultForm')
									.bootstrapValidator(
											{
												message : '<spring:message code="valid.value.not_valid" />',
												feedbackIcons : {
													/*输入框不同状态，显示图片的样式*/
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													/*验证*/
													name : {
														/*键名name和input name值对应*/
														message : '请输入字典名称',
														validators : {
															notEmpty : {
																/*非空提示*/
																message : '字典名称不能为空'
															},
															stringLength : {
																/*长度提示*/
																min : 3,
																max : 20,
																message : '请输入3-20位的字典名称'
															},
															remote : {
																message : '字典名称已经存在',
																url : '${pageContext.request.contextPath}/dict/validUnique',
																data : {
																	id : function() {
																		return $(
																				"#sysdict_id")
																				.val();
																	}
																},
																delay : 2000
															}
														}
													},
													code : {
														message : '请输入字典标识',
														validators : {
															notEmpty : {
																message : '字典标识不能为空'
															},
															remote : {
																message : '字典标识已经存在',
																url : '${pageContext.request.contextPath}/dict/validUnique',
																data : {
																	id : function() {
																		return $(
																				"#sysdict_id")
																				.val();
																	}
																},
																delay : 2000
															}
														}
													}
												},
												submitHandler : function(
													validator, form,
													submitButton) {
													$.ajax({
															type : 'post',
															url : '${pageContext.request.contextPath}/dict/',
															data : form.serialize(),
															success : function(
																	data) {
																if (data.success == true) {
																	$("#successAlert").removeClass("hide");
																	window.location.href = "${pageContext.request.contextPath}/dict/initList";
																} else {
																	$("#warnAlert").removeClass("hide");
																}
															}
														});
												}
											});
						});
	</script>
</body>
</html>
