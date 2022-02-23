<%--
  Created by IntelliJ IDEA.
  User: cyh
  Date: 2021/4/14
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户帖子</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/collection.css">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script charset="UTF-8" language="JavaScript" type="text/javascript" src="js/collect.js"></script>
</head>
<body>
<!-- 背景 -->
<div class="main_bg" style="background: #eeeeee;">


    <!-- 首部 -->

        <%@ include file="header.jsp" %>

    <!-- 主区域 -->
    <div class="main_bd_collect">


        <!-- 主内容 -->
        <div class ="main_content_collect">
            <%@ include file="header_2.jsp" %>






        </div>


        <div class="collectioncontent">

            <ul class="messageTag">
                <li onclick="getCollect(1)" id="mycollect" name="infotag">我的收藏</li>
                <li onclick="getUserPosts(1)" id="mypost" name="infotag">我的帖子</li>
                <li onclick="getUserComment(1)" id="icomment" name="infotag">我回复的</li>
                <li onclick="getMyPostComment(1)" id="commentme" name="infotag">回复我的</li>
            </ul>
            <table class="titletable">
                <tbody>

                <tr>
                    <td id="choseAll"  style="width: 40px"></td>


                    <td id="myPostTag" style="float:right;height: 39px;" >

                    </td>

                </tr>
                </tbody>
            </table>



            <div class="collectcon2">

            </div>
            <div id="delbtn"></div>
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


</body>
</html>
