<%--
  Created by IntelliJ IDEA.
  User: cyh
  Date: 2021/5/7
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>
        #loginForm{
            margin: 0 auto;
            margin-top:300px ;
        }
    </style>

</head>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<body>
    <div id="loginForm" style="width: 400px;height: 200px;border: 1px solid #000;padding: 20px;">
        <form class="layui-form" lay-filter="loginForm" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="userName" required placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="userPassword" required placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户身份</label>
                <div class="layui-input-block">
                    <input type="radio" name="identity" value="0" title="管理员">
                    <input type="radio" name="identity" value="1" title="版主" checked>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="*">登录</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
    <script src="../layui/layui.js"></script>
    <script>
        layui.use('form',function(){
            var form = layui.form;

            //刷新界面 所有元素

            form.on('submit(loginForm)',function (data) {

                var data2 = form.val("loginForm");
                var idf = data2.identity;
                if (idf ==0 ){
                    adminlogin(data2.userName,data2.userPassword);
                }
                if (idf==1){
                    moderatorlogin(data2.userName,data2.userPassword);
                }

                return false;

                })




        });
        
        function adminlogin(name,pass) {
            $.ajax({
                url:'/admin/loginAdmin',
                type:'post',
                data:{
                    'adminName':name,
                    'adminPassword':pass
                },
                success:function (data) {
                   if(data==200)
                    window.location.href="/admin/admin_index.jsp"
                    if (data==201)
                        alert("登录信息错误！")
                }
            })

        }
        
        function moderatorlogin(name,pass) {
            $.ajax({
                url:'/admin/loginModerator',
                type:'post',
                data:{
                    'userName':name,
                    'userPassword':pass
                },
                success:function (data) {
                    if(data==200)
                        window.location.href="/admin/moderator_index.jsp"

                    if (data==201)
                        alert("登录信息错误！")
                }
            })
        }

    </script>
</body>
</html>
