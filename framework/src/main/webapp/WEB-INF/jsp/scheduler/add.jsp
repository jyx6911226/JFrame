<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>任务维护</title>
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
			<h1>任务管理</h1>
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}/welcome"><i
						class="fa fa-dashboard"></i> 首页</a></li>
				<li><a href="${pageContext.request.contextPath}/scheduler/initList">
						任务管理</a></li>
				<li><a href="${pageContext.request.contextPath}/scheduler/initAdd">
						任务维护</a></li>
			</ol>
		</section>
		<!-- 任务维护from -->
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
						<input type="hidden" id="sysscheduler_id" name="id" value="${obj.id}">
						<input type="hidden" name="createdTime" value="${obj.createdTime}">
						<input type="hidden" name="lastUpdateTime" value="${obj.lastUpdateTime}">
						<input type="hidden" name="creator.id" value="${obj.creator.id}">
						<div class="form-group">
							<label>名称</label> <input type="text" id="sysscheduler_name"
								name="name" value="${obj.name}" class="form-control"
								placeholder="名称"> <span class="form-control-feedback"></span>
						</div>
						<div class="form-group">
							<label>任务类</label> <input type="text" id="sysscheduler_jobClass"
								name="jobClass" value="${obj.jobClass}" class="form-control"
								placeholder="任务类"> <span class="form-control-feedback"></span>
						</div>
						<div class="form-group">
							<label>表达式</label> <input type="text" id="sysscheduler_cron"
													  name="cron" value="${obj.cron}" class="form-control"
													  placeholder="表达式"> <span class="form-control-feedback"></span>
						</div>
						<div class="form-group">
							<label>任务状态</label>
							<div class="radio">
								<label>
									<input name="startFlag" value="true" ${empty obj.startFlag?"checked":""} ${obj.startFlag==true?"checked":""}
										   type="radio">
									运行
								</label>
								<label>
									<input name="startFlag" value="false"  ${obj.startFlag==false?"checked":""}
										   type="radio">
									暂停
								</label>
							</div>
						</div>
						<div class="form-group">
							<label>运行参数</label>
							<textarea class="form-control" rows="3" id="sysscheduler_jobParams" name="jobParams" placeholder="运行参数">${obj.jobParams}</textarea>
							<span class="form-control-feedback"></span>
						</div>
						<div class="form-group">
							<label>说明</label>
							<textarea class="form-control" rows="3" id="sysscheduler_notes" name="notes" value="${obj.notes}" placeholder="说明"></textarea>
							<span class="form-control-feedback"></span>
						</div>
						<div class="form-group">
							<label>创建人</label> <input type="text"
													 name="name" value="${obj.creator.name}" class="form-control"
													 placeholder="创建人" disabled></span>
						</div>
						<div class="box-footer">
							<shiro:hasPermission name="SysScheduler-Add-Btn">
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
														message : '请输入任务名称',
														validators : {
															notEmpty : {
																/*非空提示*/
																message : '任务名称不能为空'
															},
															stringLength : {
																/*长度提示*/
																min : 3,
																max : 32,
																message : '请输入3-32位的任务名称'
															},
															remote : {
																message : '任务名称已经存在',
																url : '${pageContext.request.contextPath}/scheduler/validUnique',
																data : {
																	id : function() {
																		return $("#sysscheduler_id")
																				.val();
																	}
																},
																delay : 2000
															}
														}
													},
                                                    jobClass : {
														message : '请输入任务运行类',
														validators : {
															notEmpty : {
																message : '运行类不能为空'
															},
															remote : {
																message : '运行类已经存在',
																url : '${pageContext.request.contextPath}/scheduler/validUnique',
																data : {
																	id : function() {
																		return $("#sysscheduler_id")
																				.val();
																	}
																},
																delay : 2000
															}
														}
													},
                                                    jobParams : {
                                                        message : '请输入任务参数',
                                                        validators : {
                                                            remote : {
                                                                message : '任务参数必须为JSON数组格式',
                                                                url : '${pageContext.request.contextPath}/scheduler/validJobParams',
                                                                data : {
                                                                    jobParams : function() {
                                                                        return $("#sysscheduler_jobParams").val();
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
															url : '${pageContext.request.contextPath}/scheduler/',
															data : form.serialize(),
															success : function(
																	data) {
																if (data.success == true) {
																	$("#successAlert").removeClass("hide");
																	window.location.href = "${pageContext.request.contextPath}/scheduler/initList";
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
