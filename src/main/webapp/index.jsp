<%--
  Created by IntelliJ IDEA.
  User: cyh
  Date: 2021/2/18
  Time: 2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>主页</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script charset="UTF-8" language="JavaScript" type="text/javascript" src="js/index.js"></script>
</head>
<body>
<!-- 背景 -->
<div class="main_bg" >


    <!-- 首部 -->


        <%@ include file="header.jsp" %>

    <!-- 主区域 -->
    <div class="main_bd">


        <!-- 主内容 -->
        <div class ="main_content">
            <div id="propaganda">

            </div>

            <%@ include file="header_2.jsp" %>

            <div class="ind_content">
                <div id="hot_blog">
                    <div class="content_title">
                        <span>热门话题</span>
                    </div>
                    <ul class="line_sort" id="hotpost">

                    </ul>
                </div>
                <div id="active_user">
                    <div class="content_title">
                        <span>活跃用户</span>
                    </div>
                    <ul class="line_sort" id="hotuser">
                    </ul>
                </div>
            </div>

            <div class="plate">
                <div class="content_title">
                    <span>主论坛</span>
                    <div class="tilt_right">最新发表</div>
                    <div class="tilt_right">帖子</div>


                </div>
                <div id="plateul">

                </div>
            </div>

        </div>


    </div>

    <!-- 脚部 -->
    <div class="main_footer">

    </div>
</div>
</body>
</html>