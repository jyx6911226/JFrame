<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><spring:message code="login.title" /></title>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrapvalidator-0.4.5/src/css/bootstrapValidator.css" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <script type="text/javascript"> 
	if (window != top) {
       top.location.href = location.href;
    }
  </script>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="${pageContext.request.contextPath}/index"><b><spring:message
						code="login.project.firstname" /></b>
			<spring:message code="login.project.lastname" /></a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">
			<c:if test="${empty msg }">
				<div class="alert alert-success"><spring:message code="login.welcome" /></div>
			</c:if>
			<c:if test="${not empty msg }">
				<div class="alert alert-danger">${msg }</div>			
			</c:if>
			</p>
			<form id="defaultForm"
				action="${pageContext.request.contextPath}/login"
				method="post">
				<div class="form-group">					
					<input type="text" class="form-control" placeholder="<spring:message code="login.please_input_account" />" name="username">				
					<span class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="<spring:message code="login.please_input_password" />" name="password">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="text" class="form-control" placeholder="<spring:message code="login.captcha" />" name="captcha">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>					
				</div>
				<div align="right">
					<img alt="<spring:message code="login.click_change"/>"
						style="width: 47%; display: inline;" id="kaptcha"
						src="${pageContext.request.contextPath}/images/kaptcha.jpg"
						title="<spring:message code="login.click_change"/>"
						onclick="javascript:refreshCaptcha();" />
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox" name="rememberMe" checked="checked"> <spring:message
									code="login.remember_me" />
							</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">
							<spring:message code="login.login" />
						</button>
					</div>
					<!-- /.col -->
				</div>			
			</form>
			<a href="#"><spring:message code="login.forget_password" /></a><br>
			<a href="${pageContext.request.contextPath}/register/init" class="text-center"><spring:message
					code="login.register_membership" /></a>
		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->
	<!-- jQuery 2.2.3 -->
	<script
		src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script
		src="${pageContext.request.contextPath}/AdminLTE-2.3.7/bootstrap/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script
		src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/iCheck/icheck.min.js"></script>
	<!-- bootstrapValidator -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrapvalidator-0.4.5/dist/js/bootstrapValidator.js"></script>
	<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
</script>
	<script>
	function refreshCaptcha() {  
	   $("#kaptcha").attr("src","${pageContext.request.contextPath}/images/kaptcha.jpg?t=" + Math.random());    
	} 
</script>
<script type="text/javascript">
$(document).ready(function() {
    /**
     * 下面是进行插件初始化
     * 你只需传入相应的键值对
     * */
    $('#defaultForm').bootstrapValidator({
            message: '<spring:message code="valid.value.not_valid" />',
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon',
                invalid: 'glyphicon',
                validating: 'glyphicon'
            },
            fields: {/*验证*/
                username: {/*键名username和input name值对应*/
                    message: '<spring:message code="valid.account.valid" />',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '<spring:message code="login.please_input_account" />'
                        },
                        stringLength: {/*长度提示*/
                            min: 5,
                            max: 20,
                            message: '<spring:message code="valid.account.valid" />'
                        }/*最后一个没有逗号*/
                    }
                },
                password: {
                    message:'<spring:message code="valid.password.valid" />',
                    validators: {
                        notEmpty: {
                            message: '<spring:message code="login.please_input_password" />'
                        },
                        stringLength: {
                            min: 6,
                            max: 20,
                            message: '<spring:message code="valid.password.valid" />'
                        }
                    }
                },
                captcha:{
                	message:'<spring:message code="valid.captcha.valid" />',
                    validators: {
                        notEmpty: {
                            message: '<spring:message code="login.please_input_captcha" />'
                        },
                        stringLength: {
                            min: 4,
                            max: 4,
                            message: '<spring:message code="valid.captcha.valid" />'
                        }
                    }
                }
            },
            submitHandler: function(validator, form, submitButton){
                //注销原有用户
                $.ajax({
			          type : "post",
			          url : "${pageContext.request.contextPath}/logout",
			          async : false,
			          success : function(data){
			        	  validator.defaultSubmit();
			          }
			     });
            } 
        });
});
</script>
</body>
</html>
