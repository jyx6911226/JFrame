<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><spring:message code="register.title"/></title>
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
    <!-- bootstrapValidator -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bootstrapvalidator-0.4.5/src/css/bootstrapValidator.css"/>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition register-page">
<div class="register-box">
    <div class="register-logo">
        <a href="${pageContext.request.contextPath}/index"><b>
            <spring:message code="login.project.firstname"/></b>
            <spring:message code="login.project.lastname"/></a>
    </div>

    <div class="register-box-body">
        <p class="login-box-msg">
            <c:if test="${empty msg }">
        <div class="alert alert-success"><spring:message code="register.register_a_new_membership"/></div>
        </c:if>
        <c:if test="${not empty msg }">
            <c:if test="${res == 1}">
                <div class="alert alert-success">${msg }</div>
            </c:if>
            <c:if test="${res == 2}">
                <div class="alert alert-danger">${msg }</div>
            </c:if>
        </c:if>
        </p>
        <form id="defaultForm" action="${pageContext.request.contextPath}/register/add" method="post">
            <div class="form-group has-feedback">
                <input type="text" name="username" class="form-control"
                       placeholder="<spring:message code="register.username" />">
                <span class="form-control-feedback"></span>
            </div>
            <%--       <div class="form-group has-feedback">
                    <input type="email" name="email" class="form-control" placeholder="<spring:message code="register.email" />">
                    <span class="form-control-feedback"></span>
                  </div> --%>
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control"
                       placeholder="<spring:message code="register.password" />">
                <span class="form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="repeat_password" class="form-control"
                       placeholder="<spring:message code="register.repeat_password" />">
                <span class="form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label>
                            <input type="checkbox" name="terms"><spring:message code="register.I_agree"/> <a
                                href="#"><spring:message code="register.terms"/></a>
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat"><spring:message
                            code="register.register"/></button>
                </div>
                <!-- /.col -->
            </div>
        </form>

        <!--<div class="social-auth-links text-center">
              <p>- OR -</p>
              <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign up using
                Facebook</a>
              <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign up using
                Google+</a>
            </div> -->

        <a href="${pageContext.request.contextPath}/login" class="text-center"><spring:message
                code="register.have_membership"/></a>
    </div>
    <!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- jQuery 2.2.3 -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/AdminLTE-2.3.7/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/AdminLTE-2.3.7/bootstrap/js/bootstrap.min.js"></script>
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
        /**
         * 下面是进行插件初始化
         * 你只需传入相应的键值对
         * */
        $('#defaultForm').bootstrapValidator({
            message: '<spring:message code="valid.value.not_valid" />',
            feedbackIcons: {
                /*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                /*验证*/
                username: {
                    /*键名username和input name值对应*/
                    message: '<spring:message code="valid.account.valid" />',
                    validators: {
                        notEmpty: {
                            /*非空提示*/
                            message: '<spring:message code="login.please_input_account" />'
                        },
                        stringLength: {
                            /*长度提示*/
                            min: 5,
                            max: 20,
                            message: '<spring:message code="valid.account.valid" />'
                        },
                        remote: {
                            message: '<spring:message code="valid.account_already_exists" />',
                            url: '${pageContext.request.contextPath}/register/validUsername',
                            delay: 2000
                        }
                    }
                },
//             email:{
//             	message: '<spring:message code="valid.email.valid_error" />',
//             	validators: {
//                     notEmpty: {
//                         message: '<spring:message code="valid.email.valid_error" />'
//                     },
//                     emailAddress: {     //　　邮箱格式校验
//                         message: '<spring:message code="valid.email.format_error" />'
//                     }
//                 }
//             },
                password: {
                    message: '<spring:message code="valid.password.valid" />',
                    validators: {
                        notEmpty: {
                            message: '<spring:message code="login.please_input_password" />'
                        },
                        stringLength: {
                            min: 6,
                            max: 20,
                            message: '<spring:message code="valid.password.valid" />'
                        },
                        identical: {
                            field: 'repeat_password',
                            message: '<spring:message code="valid.repeat_password_is_different" />'
                        }
                    }
                },
                repeat_password: {
                    message: '<spring:message code="valid.repeat_password_is_not_empty" />',
                    validators: {
                        notEmpty: {
                            message: '<spring:message code="valid.repeat_password_is_not_empty" />'
                        },
                        identical: {
                            field: 'password',
                            message: '<spring:message code="valid.repeat_password_is_different" />'
                        }
                    }
                },
                terms: {
                    message: '<spring:message code="valid.terms.valid" />',
                    validators: {
                        notEmpty: {
                            message: '<spring:message code="valid.terms.valid" />'
                        }
                    }
                }
            },
            submitHandler: function (validator, form, submitButton) {
                validator.defaultSubmit();
            }
        });
    });
</script>
</body>
</html>
