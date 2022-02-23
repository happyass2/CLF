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
    <title>用户管理</title>
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
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <script type="text/html" id="MyToolBar">
                <button type="button" class="layui-btn" lay-event="add">
                    <i class="layui-icon">&#xe608;</i>
                    添加
                </button>
                <button type="button" class="layui-btn layui-bg-red" lay-event="deleteM">
                    <i class="layui-icon">&#xe640;</i>
                    删除
                </button>
                <button type="button" class="layui-btn layui-bg-blue" lay-event="search">
                    <i class="layui-icon">&#xe615;</i>
                    查询
                </button>
            </script>
            <script type="text/html" id="MyToolBar2">
                <div class="layui-btn-group">
                    <button type="button" class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit" >
                        <i class="layui-icon">&#xe642;</i>
                    </button>
                    <button type="button" class="layui-btn layui-btn-primary layui-btn-xs" lay-event="delete">
                        <i class="layui-icon">&#xe640;</i>
                    </button>
                </div>
            </script>


            <table class="layui-hide" id="LAY_table_user" lay-filter="test">
            </table>


        </div>

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
            //JavaScript代码区域
            layui.use('element', function(){
                var element = layui.element;
            });

            //添加弹出表单

            layui.use('laydate', function(){
                var laydate = layui.laydate;

                //执行一个laydate实例
                laydate.render({

                    elem: '#loginTime' //指定元素
                });
                laydate.render({

                    elem: '#loginTime2' //指定元素
                });


                laydate.render({

                    elem: '#regTime' //指定元素
                });
                laydate.render({

                    elem: '#regTime2' //指定元素
                });
            });


            layui.use('table', function(){
                var table = layui.table;


                table.render({
                    elem: '#LAY_table_user'
                    ,url:'/user/getAllUser'
                    ,method:'post'
                    ,toolbar: '#MyToolBar'
                    ,title: '用户数据表'
                    ,cols: [[

                        {type:'checkbox',fixed:true}
                        ,{field:'userId', title:'ID', width:100, sort: true,fixed:true}
                        ,{field:'userName', title:'用户名', width:200,  edit: 'text',fixed:true}
                        ,{field:'userMail', title:'邮箱', width:250, edit: 'text',fixed:true}
                        ,{field:'userAct', title:'活跃度', width:150, sort: true,fixed:true}
                        ,{field:'userGender22', title:'性别', width:80,  sort: true,fixed:true}
                        ,{field:'registerTime', title:'注册时间', width:220, sort: true,fixed:true}
                        ,{field:'lastLoginTime', title:'上次登录时间', width:220, sort: true,fixed:true}
                        ,{field:'lastLoginIp', title:'上次登录ip', width:170,fixed:true}
                        ,{field:'userPostNumber', title:'帖子数', width:120, sort: true,fixed:true}
                        ,{field:'domethod', title:'操作', width:120,fixed:true, align:'center', toolbar: '#MyToolBar2'}
                    ]]
                    ,page: true
                    ,height:800
                    ,id:'tableid'
                    ,request:{
                        pageName:'page',//页码的参数名，默认：page
                        limitName:'limit'//每页数据量的参数名，默认：limit
                    }



                });
                //多选框选择




                //监听行工具事件
                table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                    var data = obj.data //获得当前行数据
                        ,layEvent = obj.event; //获得 lay-event 对应的值
                    if(layEvent === 'delete'){
                        layer.confirm('真的删除行么', function(index){
                            obj.del(); //删除对应行（tr）的DOM结构
                            delUser(data.userId);
                            layer.close(index);
                            //向服务端发送删除指令
                        })
                    }

                    if (layEvent === 'edit'){
                        updUser(data.userName,data.userMail,data.userId)
                    }

                });

                table.on('toolbar(test)', function(obj) {
                    var arr = [];
                    var checkStatus = table.checkStatus('tableid')
                        , data = checkStatus.data;
                    if(obj.event === 'deleteM') {
                        layer.confirm('真的删除这些行么', function(index){

                            for ( var i = 0;i<data.length;i++){
                                arr.push(data[i].userId);
                            }
                            delUser(arr);
                            layer.close(index);
                            table.reload('tableid',{});//刷新表格
                        })
                    }

                    if (obj.event === 'add'){

                        layer.open({
                            type : 1,
                            title : "添加页面",
                            area : [ '580px', '350px' ],    //设置弹出页面的宽高
                            content :$("#userform"),    //表单所在的那个ID，简单的来说就是要填写的input框等
                            cancel : function() {
                                // 你点击右上角 X 取消后要做什么
                                setTimeout('window.location.reload()', 1); //点击右上角的X，关闭页面。然后进行刷新页面；

                            }
                        })
                    }

                    if (obj.event ==='search'){
                        layer.open({
                            type : 1,
                            title : "查询页面",
                            area : [ '580px', '500px' ],    //设置弹出页面的宽高
                            content :$("#searchform"),    //表单所在的那个ID，简单的来说就是要填写的input框等
                        })
                    }
                });



            });



            layui.use('form', function(){
                var form = layui.form;


                form.on('submit(adduser)', function(data){
                    var data1 = form.val("adduser"); //获取表单内的值
                    addUser(data1);

                    return false;

                });


                form.on('checkbox(switchin)', function(data){
                    if (data.elem.checked)
                    {
                        var ip = document.getElementsByName(data.value);
                        for (var i =0;i<ip.length;i++ )
                        {
                            ip[i].removeAttribute('disabled');
                        }
                    }
                    else
                    {
                        var ip = document.getElementsByName(data.value);
                        for (var i =0;i<ip.length;i++ ) {
                            ip[i].disabled = "true";
                        }
                    }
                });

                form.on('submit(searchform)',function (data) {
                    var data2 = form.val("searchform");

                    data2.regTime = $("#regTime").val();
                    data2.loginTime = $("#loginTime").val();

                    console.log(data2.userName2)
                    search(JSON.stringify(data2));
                    return false;
                })




            })



            function delUser(arg) {

                console.log(arg);
                $.ajax({
                    url:"/user/delUser",
                    type:"post",
                    traditional: true,
                    data:{
                        "userId":arg
                    },success:function (data) {
                        console.log("success")

                    }
                })
            }

            function addUser(arg) {
                 arg.activecode = "0"
                 arg = JSON.stringify(arg);
                console.log(arg)
                $.ajax({
                    url:"/user/register2",
                    type:"POST",
                    dataType:"json",
                    contentType:"application/json;charset=UTF-8",
                    data: arg,
                    success:function (data) {
                        switch (data) {
                            case 200:alert("成功添加！");break;
                            case 201:alert("姓名重复！");break;
                            case 202:alert("邮箱重复！");break;
                            case 203:alert("注册失败！");break;
                            default:break;
                        }
                    }
                })

            }

            function updUser(userName,userMail,userId) {
                $.ajax({
                    url:"/user/updUser",
                    type:"post",
                    data:{
                        "userId":userId,
                        "userName":userName,
                        "userMail":userMail
                    },success:function (data) {
                        switch (data) {
                            case 200:console.log("success!");break;
                            case 201:alert("信息不能为空！");break;
                            case 202:alert("姓名重复！");break;
                            case 203:alert("邮箱重复！");break;
                            default:break;
                        }

                    }
                })
            }

            function search(arg) {
                if (arg==null||arg=="")
                    return;
                if (arg.regTime>arg.regTime2||arg.loginTime>arg.loginTime2)
                {
                    alert("日期错误！");
                }
                $.ajax({
                    url:"/user/searchUser",
                    type:"post",
                    contentType:"application/json;charset=UTF-8",
                    dataType: "json",
                    data:arg,
                    success:function (data1) {
                        layui.use('table', function(){
                            var table = layui.table;
                            table.render({
                                elem: '#LAY_table_user'
                                ,data:data1.data
                                ,method:'post'
                                ,toolbar: '#MyToolBar'
                                ,title: '用户数据表'
                                ,cols: [[
                                    {type:'checkbox',fixed:true}
                                    ,{field:'userId', title:'ID', width:100, sort: true,fixed:true}
                                    ,{field:'userName', title:'用户名', width:200,  edit: 'text',fixed:true}
                                    ,{field:'userMail', title:'邮箱', width:250, edit: 'text',fixed:true}
                                    ,{field:'userAct', title:'活跃度', width:150, sort: true,fixed:true}
                                    ,{field:'userGender22', title:'性别', width:80,  sort: true,fixed:true}
                                    ,{field:'registerTime', title:'注册时间', width:220, sort: true,fixed:true}
                                    ,{field:'lastLoginTime', title:'上次登录时间', width:220, sort: true,fixed:true}
                                    ,{field:'lastLoginIp', title:'上次登录ip', width:170,fixed:true}
                                    ,{field:'userPostNumber', title:'帖子数', width:120, sort: true,fixed:true}
                                    ,{field:'domethod', title:'操作', width:120,fixed:true, align:'center', toolbar: '#MyToolBar2'}
                                ]]
                                ,height:800
                                ,id:'tableid'
                            });

                        })
                        console.log(data1);
                    }
                })

            }





        </script>
    </div>
</div>
</div>
</body>

<div id="userform" style="display:none;">
    <form  class="layui-form" lay-filter="adduser">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="userName" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input name="userPassword" type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">8位以上20位以内数字与字母</div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text"  name="userMail" required  lay-verify="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="userGender" value="0" title="男">
                <input type="radio" name="userGender" value="1" title="女" checked>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo" id="addAdmin">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>



<div id="searchform" style="display:none;" >
    <form  class="layui-form" lay-filter="searchform" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="userName2"  placeholder="请输入用户名" autocomplete="off" class="layui-input" style="width: 400px;float: left;margin-right: 10px;" disabled="true">
                <input type="checkbox"  lay-skin="primary" value="userName2" lay-filter="switchin" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text"  name="userMail2" placeholder="请输入邮箱" autocomplete="off" class="layui-input" style="width: 400px;float: left;margin-right: 10px;" disabled="true">
                <input type="checkbox"  lay-skin="primary" value="userMail2" lay-filter="switchin">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="userGender2" value="" title="全部" checked>
                <input type="radio" name="userGender2" value="0" title="男">
                <input type="radio" name="userGender2" value="1" title="女">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">注册时间</label>
            <div class="layui-input-block">
                <input type="text" id="regTime" placeholder="开始时间" name="regTime2" autocomplete="off" class="layui-input" style="width: 190px;float: left;margin-right: 10px;" disabled="true">
                <input type="text" id="regTime2"  placeholder="结束时间" name="regTime2" autocomplete="off" class="layui-input" style="width: 190px;float: left;margin-right: 10px;" disabled="true">
                <input type="checkbox"  lay-skin="primary" value="regTime2" lay-filter="switchin">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">上次登录时间</label>
            <div class="layui-input-block">
                <input type="text" id="loginTime" placeholder="开始时间" name="loginTime2"  autocomplete="off" class="layui-input" style="width: 190px;float: left;margin-right: 10px;" disabled="true">
                <input type="text" id="loginTime2" placeholder="结束时间" name="loginTime2"  autocomplete="off" class="layui-input" style="width: 190px;float: left;margin-right: 10px;" disabled="true">
                <input type="checkbox"  lay-skin="primary" value="loginTime2" lay-filter="switchin">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">发帖数</label>
            <div class="layui-input-block">
                <input type="text" id="postNumber"  name="postNumber2"  autocomplete="off" class="layui-input" style="width: 400px;float: left;margin-right: 10px;" disabled="true">
                <input type="checkbox"  lay-skin="primary" value="postNumber2" lay-filter="switchin">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">登录ip</label>
            <div class="layui-input-block">
                <input type="text" id="loginIp"  name="loginIp2"  autocomplete="off" class="layui-input" style="
                width: 400px;float: left;margin-right: 10px;" disabled="true">
                <input type="checkbox"  lay-skin="primary" value="loginIp2" lay-filter="switchin">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>



    </form>
</div>
</html>
