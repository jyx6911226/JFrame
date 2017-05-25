<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>权限列表</title>
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
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE-2.3.7/dist/css/skins/_all-skins.min.css">
    <!-- ztree-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <!-- Bootstrap -->
    <!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/js/lib/bootstrap-2.3.2/css/bootstrap.min.css" media="screen">  -->
    <!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/js/lib/bootstrap-2.3.2/css/bootstrap-responsive.min.css" media="screen">  -->
    <!-- FontAwesome -->
    <!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/js/lib/font-awesome-4.2.0/css/font-awesome.min.css">  -->
    <!-- DataTables CSS start-->
    <!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/js/lib/dataTables-1.10.7/plugins/integration/bootstrap/2/dataTables.bootstrap.css">  -->
    <!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/js/lib/dataTables-1.10.7/plugins/integration/font-awesome/dataTables.fontAwesome.css">  -->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
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
            权限管理
            <small>列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/welcome"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${pageContext.request.contextPath}/userInfo/initList"> 资源管理</a></li>
            <li><a href="${pageContext.request.contextPath}/userInfo/initList"> 权限管理</a></li>
            <li class="active">权限列表</li>
        </ol>
    </section>
    <!-- 权限ztree -->
    <div class="col-md-4">
        <div class="box box-primary">
            <div class="zTreeDemoBackground left">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>
    </div>
    <!-- 权限维护from -->
    <div class="col-md-8">
        <div class="box box-info">
            <p class="login-box-msg">
            <div id="successAlert" class="alert alert-success hide">
                <strong>保存成功！</strong>
            </div>
            <div id="warnAlert" class="alert alert-warning hide">
                <strong>操作失败！</strong>
            </div>
            </p>
            <form id="defaultForm" action="#" method="post">
                <input type="hidden" id="node_id" name="id" value="">
                <input type="hidden" id="parent_id" name="parentId" value="1">
                <input type="hidden" id="parent_ids" name="parentIds">
                <div class="form-group">
                    <label>上级权限</label>
                    <input type="text" id="parent_name" disabled="" class="form-control" placeholder="">
                </div>
                <div class="form-group">
                    <label>名称</label>
                    <input type="text" id="node_name" name="name" class="form-control" placeholder="名称">
                    <span class="form-control-feedback"></span>
                </div>
                <div class="form-group">
                    <label>标识</label>
                    <input type="text" id="permission" name="permission" class="form-control" placeholder="标识">
                    <span class="form-control-feedback"></span>
                </div>
                <div class="form-group">
                    <label>资源类型</label>
                    <select class="form-control" name="resourceType" id="resourceType">
                        <option value="page">页面</option>
                        <option value="interface">接口</option>
                        <option value="button">按钮</option>
                        <option value="node">节点</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>地址</label>
                    <input type="text" name="resourceUrl" class="form-control" placeholder="地址" id="input_url">
                    <span class="form-control-feedback"></span>
                </div>
                <div class="form-group">
                    <label>状态</label>
                    <div class="radio">
                        <label>
                            <input name="available" value="true" checked="" type="radio">
                            有效
                        </label>
                        <label>
                            <input name="available" value="false" type="radio">
                            无效
                        </label>
                    </div>
                </div>
                <div class="box-footer">
                    <button type="button" id="add_child_node" class="btn btn-default btn-left">添加子权限</button>
                    <button type="submit" class="btn btn-info">保存</button>
                    <button type="button" id="del_node" class="btn btn-danger btn-del">删除</button>
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
                name: {
                    /*键名name和input name值对应*/
                    message: '请输入权限名称',
                    validators: {
                        notEmpty: {
                            /*非空提示*/
                            message: '权限名称不能为空'
                        },
                        stringLength: {
                            /*长度提示*/
                            min: 3,
                            max: 20,
                            message: '请输入3-20位的权限名称'
                        },
                        remote: {
                            message: '权限名称已经存在',
                            url: '${pageContext.request.contextPath}/sysPermission/validUnique',
                            data: {
                                id: function () {
                                    return $("#node_id").val();
                                }
                            },
                            delay: 2000
                        }
                    }
                },
                permission: {
                    message: '请输入权限标识',
                    validators: {
                        notEmpty: {
                            message: '权限标识不能为空'
                        },
                        remote: {
                            message: '权限标识已经存在',
                            url: '${pageContext.request.contextPath}/sysPermission/validUnique',
                            data: {
                                id: function () {
                                    return $("#node_id").val();
                                }
                            },
                            delay: 2000
                        }
                    }
                },
                resourceUrl: {
                    message: '请输入资源地址',
                    validators: {
                        notEmpty: {
                            message: '资源地址不能为空'
                        },
                        remote: {
                            message: 'URL已经存在',
                            url: '${pageContext.request.contextPath}/sysPermission/validUnique',
                            data: {
                                id: function () {
                                    return $("#node_id").val();
                                }
                            },
                            delay: 2000
                        }
                    }
                }
            },
            submitHandler: function (validator, form, submitButton) {
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/sysPermission/',
                    data: form.serialize(),
                    success: function (data) {
                        $(form)[0].reset();
                        $(form).bootstrapValidator('resetForm');
                        if (data.success == true) {
                            $("#successAlert").removeClass("hide");
                            $("#warnAlert").addClass("hide");
                        } else {
                            $("#warnAlert").removeClass("hide");
                            $("#successAlert").addClass("hide");
                        }
                        $.fn.zTree.init($("#treeDemo"), setting, null);
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
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId"
            }
        },
        async: {
            enable: true,
            url: "${pageContext.request.contextPath}/sysPermission/getAllList",
            dataFilter: ajaxDataFilter
        },
        callback: {
            onClick: zTreeOnClick
        }
    };
    function zTreeOnClick(event, treeId, treeNode) {
        //alert(treeNode.id + ", " + treeNode.name);
        $("#node_id").val(treeNode.id);
        $("#node_name").val(treeNode.name);
        $("#input_url").val(treeNode.resourceUrl);
        $("#resourceType").val(treeNode.resourceType);
        var parent_ids = ",";
        for (var i = 0; i < treeNode.getPath().length - 1; i++) {
            parent_ids += treeNode.getPath()[i].id + ",";
        }
        $("#parent_ids").val(parent_ids);
        $("input:radio[name='available']").val(treeNode.available);
        if (treeNode.resourceType == "button" || treeNode.resourceType == "node") {
            $('#defaultForm').data('bootstrapValidator').enableFieldValidators("resourceUrl", false);
            $('#input_url').val("");
            $('#input_url').attr("disabled", "disabled");
        } else {
            $('#defaultForm').data('bootstrapValidator').enableFieldValidators("resourceUrl", true);
            $('#input_url').removeAttr("disabled");
        }
        $("#permission").val(treeNode.permission);
        var parentNode = treeNode.getParentNode();
        if (parentNode != null) {
            $("#parent_id").val(parentNode.id);
            $("#parent_name").val(parentNode.name);
        } else {
            $("#parent_id").val(0);
            $("#parent_name").val("无");
        }
    };
    $(document).ready(function () {
        $.fn.zTree.init($("#treeDemo"), setting, null);
    });
</script>
<!--动态管理校验-->
<!--当权限类型为button时不需要维护url-->
<script type="text/javascript">
    $("#resourceType").change(function () {
        var val = $(this).val();
        if (val == "button" || val == "node") {
            $('#defaultForm').data('bootstrapValidator').enableFieldValidators("resourceUrl", false);
            $('#input_url').val("");
            $('#input_url').attr("disabled", "disabled");
        } else {
            $('#defaultForm').data('bootstrapValidator').enableFieldValidators("resourceUrl", true);
            $('#input_url').removeAttr("disabled");
        }
    })
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
        $("#permission").val("");
        $("#input_url").val("");
    });
</script>
<!-- 删除权限 -->
<script type="text/javascript">
    $("#del_node").click(function () {
        var message = "确定要删除该权限及其子权限吗?";
        var id = $("#node_id").val();
        $.dialog.confirmDanger(message, function () {
            $.ajax({
                type: "delete",
                url: "./" + id,
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
