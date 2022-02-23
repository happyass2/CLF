<%--
  Created by IntelliJ IDEA.
  User: cyh
  Date: 2021/4/26
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理主页</title>
</head>
<link rel="stylesheet" href="../layui/css/layui.css">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="../js/admin.js"></script>

<body style="height: 100%">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">论坛管理</div>
        <!-- 头部区域（可配合layui 已有的水平导航） -->

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">

                <dl class="layui-nav-child">
                    <dd><a href="">set 1</a></dd>
                    <dd><a href="">set 2</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item"><a href="/admin/admin_user.jsp">用户管理</a></li>

                <li class="layui-nav-item"><a href="/admin/admin_moderator.jsp">版主管理</a></li>

                <li class="layui-nav-item"><a href="/admin/admin_post.jsp">帖子、回帖管理</a></li>

                <li class="layui-nav-item"><a href="/admin/admin_secNthe.jsp">板块、主题管理</a></li>

                <li class="layui-nav-item"><a href="/admin/admin_message.jsp">消息中心</a></li>


            </ul>
        </div>
    </div>

    <div class="layui-body">

        <script src="../layui/layui.js"></script>
        <script>
            $(function () {
                $.ajax({
                    url:"/admin/checkLogin",
                    type:"post",
                    success:function (data) {
                        if (data!=200){
                            alert("请登录！")
                            window.location.href="/admin/admin_login.jsp";
                        }

                    }
                })

            })

        </script>

    </div>
</div>
</div>
</body>

</html>
