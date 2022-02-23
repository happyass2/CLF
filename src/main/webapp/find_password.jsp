<%--
  Created by IntelliJ IDEA.
  User: cyh
  Date: 2021/4/22
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/registerc.css">

    <script charset="UTF-8" language="JavaScript" type="text/javascript" src="js/registerit.js"></script>
</head>
<body>
<!-- 背景 -->
<div class="main_bg">

    <%@ include file="header.jsp" %>

    <!-- 主区域 -->
    <div class="register_content">
        <div class="borderfindep">
            <div class="findepTag">找回密码</div>
            <form class="register_form" method="post" action="" name="formreg">


                <div class="single_reg">
                    <table>
                        <tbody>
                        <tr>
                            <th>用户名：</th>
                            <td><input type="text" name="username" id="username" disabled="true"></td>
                            <td id="username-msg"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="single_reg">
                    <table>
                        <tbody>
                        <tr>
                            <th>新密码：</th>
                            <td><input type="password" name="userpassword" id="userpassword"></td>
                            <td id="password-msg"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="single_reg">
                    <table>
                        <tbody>
                        <tr>
                            <th>确认密码：</th>
                            <td><input type="password" name="cuserpassword" id="cuserpassword"></td>
                            <td id="conpassword-msg"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>


                <div class="sub_reg">
                    <input type="button" value="提交" style="float:left;" id = "submit-btn" >
                </div>
            </form>

        </div>




    </div>

    <!-- 脚部 -->
    <div class="main_footer">

    </div>
</div>
</body>
</html>
