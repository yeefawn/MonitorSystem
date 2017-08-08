<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-8-7
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="/lib/html5shiv.js"></script>
    <script type="text/javascript" src="/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>添加资源节点</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-resource-add" method="post">
        <input type="hidden" name="id" value="${resource.id}">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>资源名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${resource.name}" placeholder="资源名称" id="name" name="name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>资源类型：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="select-box" name="type" size="1">
                    <option value="0" ${fn:substring(resource.type, 0, 1) == "0" ? "selected" : ""}>菜单</option>
                    <option value="1" ${fn:substring(resource.type, 0, 1) == "1" ? "selected" : ""}>操作</option>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">&nbsp;</span>上级资源：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="select-box" name="parentId" size="1">
                    <option value=""></option>
                    <c:forEach var="menu" items="${menus}">
                        <option value="${menu.id}" ${menu.id == resource.parentId ? "selected" : ""}>${menu.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">&nbsp;</span>请求地址：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <input type="text" class="input-text" value="${resource.url}" placeholder="请求地址" id="url" name="url">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限标识：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${resource.permission}" placeholder="权限标识(示例:'xxx:xxx')" id="permission" name="permission">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">&nbsp;</span>排序序号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${resource.priority}" placeholder="排序序号" name="priority" id="priority">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
    $(function(){
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        jQuery.validator.addMethod('permStyle', function(value, element, param) {
            var score = /[a-z]+:[a-z]+/i;
            return this.optional(element) || score.test(value);
        }, "请确保输入的格式为: XXX:XXX)");

        $("#form-resource-add").validate({
            rules:{
                name:{
                    required:true,
                    minlength:4,
                    maxlength:16
                },
                permission:{
                    required:true,
                    permStyle:true
                }
            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                $(form).ajaxSubmit({
                    type: 'post',
                    url: "${pageContext.request.contextPath}/resource/edit" ,
                    success: function(data){
                        layer.msg('添加成功!',{icon:1,time:1000},function(){
                            closeWindow();
                        });

                    },
                    error: function(XmlHttpRequest, textStatus, errorThrown){
                        layer.msg('error!',{icon:1,time:1000},function () {
                            closeWindow();
                        });

                    }
                });
            }
        });
    });

    function closeWindow() {
        var index = parent.layer.getFrameIndex(window.name);
        //alert(parent.$('#refresh').html());
        parent.$('#refresh').click();
        //parent.layer.close(index);
    }
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
