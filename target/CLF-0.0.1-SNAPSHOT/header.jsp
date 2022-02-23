<%--
  Created by IntelliJ IDEA.
  User: cyh
  Date: 2021/4/2
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/loginin.css">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script charset="UTF-8" language="JavaScript" type="text/javascript" src="js/login.js"></script>
</head>
<body>
<!-- 首部 -->
<div class="main_header">

    <!-- 首部 -->
    <div class="main_header">
        <!-- 登录、logo、首页栏 -->
        <div class="mini_header_content">

            <div class="header_button">
                <a href="index.jsp" class="header_link">论坛首页</a>
            </div>
            <div class="header_button">
                <a href="www.hhxy.com" class="header_link">学校首页</a>
            </div>
            <div class="header_button">
                <a href="www.hhxy.com" class="header_link">校务处</a>
            </div>


            <div class="header_login" id="logintitle">


            </div>

            <div id="find_pass_dialog">
                <div class="find_title">找回密码</div>
                <span>请输入用户名: <input type="text" id="userName"></span>
                <br/>
                <span>请输入邮箱: <input type="text" id="userEmail"></span>
                <button type="button" onclick="changePass()">确定</button>
                <button type="button" onclick="cancelPass()">取消</button>
            </div>

        </div>
    </div>
</div>
</body>
</html>
