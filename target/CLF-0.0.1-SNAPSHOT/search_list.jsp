<%--
  Created by IntelliJ IDEA.
  User: cyh
  Date: 2021/4/14
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/searchlist.css">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script charset="UTF-8" language="JavaScript" type="text/javascript" src="js/serch.js"></script>
</head>
<body>
<div class="main_bg">


    <!-- 首部 -->
    <%@ include file="header.jsp" %>

    <!-- 主区域 -->
    <div class="main_bd_search">


        <!-- 主内容 -->
        <div class ="main_content_search">


            <%@ include file="header_2.jsp" %>



        </div>
        <div class="searchcontent">
            <ul class="searchshow" id="searchContent">

            </ul>
        </div>
        <div class="pagenumber">
            <ul>

            </ul>
        </div>

    </div>


</div>
</body>
</html>
