<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-8-7
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <link rel="stylesheet" type="text/css" href="/lib/laypage/1.3/skin/laypage.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>资源管理</title>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页
    <span class="c-gray en">&gt;</span> 系统管理
    <span class="c-gray en">&gt;</span> 监控管理
    <a class="btn btn-success radius r" id="refresh" style="line-height:1.6em;margin-top:3px" href="javascript:void(0);" title="刷新" onclick="refreshWindow()" >
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>
<div class="page-container">
    <div class="text-c">
        <form class="Huiform" method="post" action="/monitor/list" target="_self">
            <input type="hidden" name="pageNum" value="" id="pageNum">
            <input type="text" class="input-text" style="width:150px" placeholder="登录名" id="username" name="username">
            <input type="text" class="input-text" style="width:150px" placeholder="管理员名称" id="fullname" name="fullname">
            <input type="text" class="input-text" style="width:150px" placeholder="邮箱地址" id="email" name="email">
            <input type="text" class="input-text" style="width:150px" placeholder="手机号码" id="telephone" name="telephone">
            <button type="submit" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i> 搜监控</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:" onclick="datadel()" class="btn btn-danger radius">
                <i class="Hui-iconfont">&#xe6e2;</i> 批量删除
            </a>
            <shiro:hasPermission name="user:add">
            <a href="javascript:" onclick="admin_user_add('添加监控','/monitor/toAdd','800')" class="btn btn-primary radius">
                <i class="Hui-iconfont">&#xe600;</i> 添加监控
            </a>
            </shiro:hasPermission>
        </span>
        <span class="r">
            共有数据：<strong>${page.total}</strong> 条
        </span> </div>
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="12">监控列表</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" name="" value=""></th>
            <th width="40">ID</th>
            <th width="100">监控系统名称</th>
            <th width="100">检测地址</th>
            <th width="80">联系人列表</th>
            <th width="140">电子邮箱</th>
            <th width="80">角色</th>
            <th width="60">登陆次数</th>
            <th width="120">上一次登录IP</th>
            <th width="120">上一次登录时间</th>
            <th width="60">状态</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="user" varStatus="s">
            <tr class="text-c">
                <td><input type="checkbox" value="${user.id}" name="id"></td>
                <td>${s.index + 1}</td>
                <td>${user.username}</td>
                <td>${user.fullname}</td>
                <td>${user.telephone}</td>
                <td>${user.email}</td>
                <td>${user.role.role}</td>
                <td>${user.loginCount}</td>
                <td>${user.lastLoginIp}</td>
                <td><fmt:formatDate value="${user.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                <td><span class="label ${user.locked == 1 ? "label-success" : ""} radius">${user.locked == 1 ? "可用" : "被锁定"}</span></td>
                <td>
                    <shiro:hasPermission name="user:edit">
                    <a title="编辑" href="javascript:" onclick="admin_user_edit('资源编辑','/monitor/toEdit','${user.id}','800')" class="ml-5" style="text-decoration:none">
                        <i class="Hui-iconfont">&#xe6df;</i>
                    </a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:delete">
                    <a title="删除" href="javascript:" onclick="admin_user_del(this,'${user.id}')" class="ml-5" style="text-decoration:none">
                        <i class="Hui-iconfont">&#xe6e2;</i>
                    </a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="width: 100%; height: 5px;"></div>
    <div id="page" style="text-align: center"></div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/laypage/1.3/laypage.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    $(function(){
        laypage(
            {
                cont : 'page', //容器。值支持id名、原生dom对象，jquery对象,
                curr : '${page.pageNum}',
                pages : '${page.pages}', //总页数
                skin : 'yahei',
                jump : function(obj, first) {
                    if (!first) {
                        $('#pageNum').val(obj.curr);
                        $('.Huiform').submit();
                    }
                }
            }
        );
    });

    /*
        参数解释：
        title	标题
        url		请求的url
        id		需要操作的数据id
        w		弹出层宽度（缺省调默认值）
        h		弹出层高度（缺省调默认值）
    */
    /*管理员-监控-添加*/
    function admin_user_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*管理员-监控-编辑*/
    function admin_user_edit(title,url,id,w,h){
        layer_show(title,url + '/' + id,w,h);
    }

    /*管理员-监控-删除*/
    function admin_user_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'GET',
                url: '/monitor/delete/' + id,
                dataType: 'json',
                success: function(data){
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!',{icon:1,time:1000});
                },
                error:function(data) {
                    console.log(data.msg);
                }
            });
        });
    }

    function refreshWindow(){
        location.replace(location.href);
    }
</script>
</body>
</html>
