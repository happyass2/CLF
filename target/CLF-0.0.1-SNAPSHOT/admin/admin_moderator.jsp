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
    <title>版主管理</title>
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
                <button type="button" class="layui-btn layui-bg-blue" lay-event="add" style="float:left;">
                    <i class="layui-icon">&#xe615;</i>
                    添加
                </button>
                <button type="button" class="layui-btn layui-bg-red" lay-event="deleteM" style="float:left;">
                    <i class="layui-icon">&#xe640;</i>
                    删除
                </button>
                <input type="text" id="userId" required  lay-verify="number" placeholder="用户id" autocomplete="off" class="layui-input" style="width: 100px;float:left;margin-left: 10px;">
                <input type="text" id="sectionId" required  lay-verify="number" placeholder="板块id" autocomplete="off" class="layui-input" style="width: 100px;float:left;margin-left: 10px;">
                <button type="button" class="layui-btn layui-bg-blue" lay-event="search">
                    <i class="layui-icon">&#xe615;</i>
                    查询
                </button>
            </script>
            <script type="text/html" id="MyToolBar2">
                <div class="layui-btn-group">
                    <button type="button" class="layui-btn layui-btn-primary layui-btn-xs" lay-event="delete">
                        <i class="layui-icon">&#xe640;</i>
                    </button>
                </div>
            </script>


            <table class="layui-hide" id="LAY_table_moderator" lay-filter="test">
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

            layui.use('table', function(){
                var table = layui.table;

                table.render({
                    elem: '#LAY_table_moderator'
                    ,url:'/moderator/getAllModerator'
                    ,method:'post'
                    ,toolbar: '#MyToolBar'
                    ,title: '版主数据表'
                    ,cols: [[
                        {type:'checkbox',fixed:true}
                        ,{field:'moderatorId', title:'版主Id', width:100, sort: true,fixed:true}
                        ,{field:'userId', title:'用户id', width:130,  edit: 'text',fixed:true}
                        ,{field:'userName', title:'用户名', width:250, edit: 'text',fixed:true}
                        ,{field:'sectionName', title:'负责板块', width:180,fixed:true}
                        ,{field:'userMail', title:'用户邮箱', width:270, sort: true,fixed:true}
                        ,{field:'lastLoginTime', title:'最后登录时间', width:270,  sort: true,fixed:true}
                        ,{field:'lastLoginIp', title:'最后登录IP', width:270, sort: true,fixed:true}
                        ,{field:'domethod', title:'操作', width:150,fixed:true, align:'center', toolbar: '#MyToolBar2'}

                    ]]
                    ,page: true
                    ,height:800
                    ,id:'moderatortableid'
                    ,request:{
                        pageName:'page',//页码的参数名，默认：page
                        limitName:'limit'//每页数据量的参数名，默认：limit
                    }
                })

                table.on('tool(test)',function (obj){
                    var data = obj.data //获得当前行数据
                        ,layEvent = obj.event; //获得 lay-event 对应的值

                    if (layEvent === 'delete'){
                        layer.confirm('真的删除行么', function(index){
                            obj.del(); //删除对应行（tr）的DOM结构
                            console.log(data.moderatorId);
                            delModerator(data.moderatorId);
                            layer.close(index);
                            //向服务端发送删除指令
                        })
                    }

                })

                table.on('toolbar(test)',function (obj) {
                    var arr = [];
                    var checkStatus = table.checkStatus('moderatortableid')
                        , data = checkStatus.data;

                    if (obj.event === 'deleteM'){
                        layer.confirm('真的删除这些行么', function(index){

                            for ( var i = 0;i<data.length;i++){
                                arr.push(data[i].moderatorId);
                            }
                            delModerator(arr);
                            layer.close(index);
                            table.reload('moderatortableid',{});//刷新表格
                        })
                    }

                    if (obj.event === 'search'){

                        var userId = $("#userId").val();
                        var sectionId = $("#sectionId").val();
                        table.reload('moderatortableid',{
                            url:"/moderator/getModeratorSearch",
                            type:"post",
                            where: {
                                'userId':userId,
                                'sectionId':sectionId
                            },request: {
                                pageName: "page",
                                limitName: "limit"
                            },
                            page: {
                                curr:1
                            }
                        })
                    }

                    if (obj.event === 'add'){
                        layer.open({
                            type : 1,
                            title : "添加页面",
                            area : [ '580px', '250px' ],    //设置弹出页面的宽高
                            content :$("#moderatoradd")
                        })

                        getSection();
                    }


                })
            });

            //添加弹出表单

            
            layui.use('form',function () {
                var form = layui.form;
                var table = layui.table;

                form.on('submit(moderatoradd)',function (data) {

                    var data2 = form.val("moderatoradd");

                    if (data2.sectionIdadd==0)
                    {
                        alert("请选择板块！")
                        return;
                    }

                    addModerator(data2.userIdadd,data2.sectionIdadd);
                    table.reload('moderatortableid',{});
                   return false;

                })


            })
            
            
            function addModerator(userId,sectionId) {

                $.ajax({
                    url:'/moderator/addModerator',
                    type: 'post',
                    data:{
                        'userId':userId,
                        'sectionId':sectionId
                    },success:function (data) {
                        console.log("success")
                    }

                })
            }

            function delModerator(arg) {
                $.ajax({
                    url:'/moderator/delModerator',
                    type: 'post',
                    traditional:true,
                    data:{
                        'moderatorId':arg,
                    },success:function (data) {
                        console.log("success")
                    }

                })
            }


            function getSection() {
                $.ajax({
                    url:"/section/showSection",
                    type:"get",
                    success:function (data) {
                        $("#sectionIdadd").empty();
                        $("#sectionIdadd").append(
                            "<option value=''>"+"-请选择-"+"</option>"
                        )
                        $.each(data,function (i,item) {
                            $("#sectionIdadd").append(
                                "<option value='"+item.sectionId+"'>"+item.sectionName+"</option>"
                            )
                        })
                    }
                })

            }
        </script>
    </div>
</div>
</div>
</body>


<div id="moderatoradd" style="display:none;" >
    <form  class="layui-form" lay-filter="moderatoradd" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">用户id</label>
            <div class="layui-input-block">
                <input type="text" id="userIdadd" name="userIdadd"  placeholder="请输入用户名" required lay-verify="number" autocomplete="off" class="layui-input" style="width: 400px;float: left;margin-right: 10px;">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">负责板块</label>
            <div class="layui-input-block">
                <select lay-ignore id="sectionIdadd" name="sectionIdadd" required style="margin-top: 5px">
                    <option value="0">-请选择-</option>
                </select>
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
