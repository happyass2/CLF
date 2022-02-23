<%--
  Created by IntelliJ IDEA.
  User: cyh
  Date: 2021/4/20
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户消息</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/collection.css">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script charset="UTF-8" language="JavaScript" type="text/javascript" src="js/message.js"></script>
</head>
<body>
<!-- 背景 -->
<div class="main_bg">


    <!-- 首部 -->
    <div class="main_header">
  <%@ include file="header.jsp" %>

        <!-- 主区域 -->
        <div class="main_bd_collect">


            <!-- 主内容 -->
            <div class ="main_content_collect">


          <%@ include file="header_2.jsp" %>




            </div>


            <div class="collectioncontent">

                <ul class="messageTag">
                    <li style="color:#ffffff;">消息通知</li>

                </ul>
                <div  id="myMessage">


                </div>




                <div class="collectcon2">

                </div>

                <div class="pagenumber">
                    <ul>

                    </ul>
                </div>

            </div>


        </div>

        <!-- 脚部 -->
        <div class="main_footer">

        </div>
    </div>
</div>

</body>
</html>
