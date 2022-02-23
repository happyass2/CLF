<%--
  Created by IntelliJ IDEA.
  User: cyh
  Date: 2021/2/18
  Time: 2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <title>注册</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/registerc.css">

    <script charset="UTF-8" language="JavaScript" type="text/javascript" src="js/registerit.js"></script>

    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
</head>
<body>
<!-- 背景 -->
<div class="main_bg">
    
    <%@ include file="header.jsp" %>

    <!-- 主区域 -->
    <div class="register_content">
        <%@ include file="header_2.jsp" %>
        <div class="borderreg" >
            <div class="regTag">
                用户注册
            </div>
            <form class="register_form" method="post" action="" name="formreg">


                <div class="single_reg">
                    <table>
                        <tbody>
                        <tr>
                            <th>用户名：</th>
                            <td><input type="text" name="username" id="username"></td>
                            <td id="username-msg"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="single_reg">
                    <table>
                        <tbody>
                        <tr>
                            <th>密码：</th>
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



                <div class="single_reg2">
                    <table>
                        <tbody>
                        <tr>
                            <th>性别：</th>
                            <td>
                                <span>男</span><input width="80px" type="Radio" name="usergender" value="1">
                                <span>女</span><input width="80px" type="Radio" name="usergender" value="2" >
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="single_reg">
                    <table>
                        <tbody>
                        <tr>
                            <th>邮箱：</th>
                            <td>
                                <input type="text" name="usermail" id="usermail"  />

                            </td>
                            <td id="mail-msg">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="single_reg">
                    <table>
                        <tbody>
                        <tr>
                            <th>激活码：</th>
                            <td>
                                <input type="text" name="activecode" id="activecode">
                            </td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="sub_reg">
                    <input type="button" value="提交" style="float:left;" id = "submit-btn" >
                    <input type="button" value="发送验证码" onclick='sendMail()' style="float:left;" id="send">
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

<script type="text/javascript" src="js/tbdautoemail.js"></script>
<script type="text/javascript">
    //邮箱补全
    new TbdAutoEmail({
        inputid:"usermail",
        selectsuccess:function(){

        },
        regpass:function(){

        },
        regerr:function(){

        }
    }).init();


</script>



