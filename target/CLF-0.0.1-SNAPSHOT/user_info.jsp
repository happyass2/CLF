<%--
  Created by IntelliJ IDEA.
  User: cyh
  Date: 2021/4/23
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/collection.css">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script charset="UTF-8" language="JavaScript" type="text/javascript" src="js/info.js"></script>

</head>
<body>

<div class="main_bg">

    <%@ include file="header.jsp" %>

    <!-- 首部 -->



        <!-- 主区域 -->
        <div class="main_bd_collect">


            <!-- 主内容 -->
            <div class ="main_content_collect">
                <%@ include file="header_2.jsp" %>
            </div>


            <div class="collectioncontent" style="height: 300px;">

                <ul class="messageTag">
                    <li>我的信息</li>
                </ul>




                <div class="collectcon2" style="height: 300px;">
                    <div style="height: 280px;width: 100%;" id="userinfodiv">
                        <div class="user_icon">
                            <div class="inner_icon"></div>
                            <button type="button">修改头像</button><button type="button">提交</button>
                        </div>
                        <div class="info_div">
                            <ul class="info_right">
                                <li><h4>happyass(UID15243)</h4></li>
                            </ul>


                            <ul class="info_right">
                                <li>性别:男</li>
                                <li>邮箱已认证</li>
                            </ul>
                            <ul class="info_right">
                                <li>发帖数:12324</li>
                                <li>回帖数:1234</li>
                                <li>活跃度:12</li>
                            </ul>
                            <ul class="info_right">
                                <li>注册时间:2014-12-25 15:32:21</li>
                                <li>上次活跃时间:2014-12-25 15:32:21</li>
                            </ul>
                            <ul class="info_right">
                                <li>上次登录IP：192.168.1.24</li>
                                <li>注册IP：192.168.1.24</li>
                            </ul>
                        </div>

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
