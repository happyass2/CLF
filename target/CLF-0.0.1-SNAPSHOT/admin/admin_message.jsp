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
    <title>消息中心</title>
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


        <div class="layui-tab" lay-allowClose="true" lay-filter="tagLay">
            <ul class="layui-tab-title" id="tagHeader">
                <li class="layui-this">举报信息</li>
            </ul>

            <div class="layui-tab-content" id="tagBody">
                <!-- 内容主体区域 -->
                <div class="layui-tab-item layui-show">
                        <table class="layui-hide" id="LAY_table_message" lay-filter="test">
                        </table>
                </div>

            </div>
        </div>
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <script type="text/html" id="MyToolBar">
<%--                <button type="button" class="layui-btn" lay-event="add">--%>
<%--                    <i class="layui-icon">&#xe608;</i>--%>
<%--                    发送全体消息--%>
<%--                </button>--%>
                <button type="button" class="layui-btn layui-bg-red" lay-event="deleteM" style="float:left;">
                    <i class="layui-icon">&#xe640;</i>
                    删除
                </button>
                <button type="button" class="layui-btn layui-bg-blue" lay-event="deal" style="float:left;">
                    <i class="layui-icon">&#xe615;</i>
                    处理
                </button>
                <input type="text" id="sendId" required  lay-verify="number" placeholder="举报人id" autocomplete="off" class="layui-input" style="width: 100px;float:left;margin-left: 10px;">
                <input type="text" id="postId" required  lay-verify="number" placeholder="帖子id" autocomplete="off" class="layui-input" style="width: 100px;float:left;margin-left: 10px;">
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
                element.tab({
                    headerElem:'#tagHeader>li'
                    ,bodyElem:'#tagBody>.layui-tab-item'
                })
            });

            layui.use('table',function () {
                var table = layui.table;
                table.render({


                    elem: '#LAY_table_message'
                    ,url:'/message/getMessageAdmin'
                    ,method:'post'
                    ,toolbar: '#MyToolBar'
                    ,where:{
                        'userId':0
                    }
                    ,title: '信息数据表'
                    ,cols: [[
                        {type:'checkbox',fixed:true}
                        ,{field:'messageId', title:'信息ID', width:100, sort: true,fixed:true}
                        ,{field:'sendUserId', title:'举报者ID', width:100,  edit: 'text',fixed:true}
                        ,{field:'userName', title:'用户名', width:200, edit: 'text',fixed:true}
                        ,{field:'postId', title:'举报帖Id', width:100, sort: true,fixed:true}
                        ,{field:'postTitle', title:'帖子标题', width:200,  sort: true,fixed:true}
                        ,{field:'statusStr', title:'信息状态', width:100, sort: true,fixed:true}
                        ,{field:'description', title:'举报内容', width:530, sort: true,fixed:true}
                        ,{field:'createTime', title:'举报时间', width:180, sort: true,fixed:true}
                        ,{field:'domethod', title:'操作', width:120,fixed:true, align:'center', toolbar: '#MyToolBar2'}
                    ]]
                    ,page: true
                    ,height:800
                    ,id:'messagetableid'
                    ,request:{
                        pageName:'pageNum',//页码的参数名，默认：page
                        limitName:'pageSize'//每页数据量的参数名，默认：limit
                    }

                })



                table.on('toolbar(test)',function (obj) {
                    var arr = [];
                    var checkStatus = table.checkStatus('messagetableid')
                        , data = checkStatus.data;

                    if(obj.event === 'deleteM') {
                        layer.confirm('真的删除这些行么', function(index){

                            for ( var i = 0;i<data.length;i++){
                                arr.push(data[i].messageId);
                            }
                            delMessageAdmin(arr);
                            layer.close(index);
                            table.reload('messagetableid',{});//刷新表格
                        })
                    }

                    if(obj.event === 'deal') {
                        layer.confirm('处理完所选帖子', function(index){

                            for ( var i = 0;i<data.length;i++){
                                arr.push(data[i].messageId);
                            }
                            dealMessage(arr);
                            layer.close(index);
                            table.reload('messagetableid',{});//刷新表格
                        })
                    }

                    if(obj.event === 'search') {
                        var sendId = $("#sendId").val();
                        var postId = $("#postId").val();

                        table.reload('messagetableid',{
                            url:"/message/getMessageSearch",
                            type:"post",
                            where: {
                                'sendId':sendId,
                                'postId':postId
                            },request: {
                                pageName: "pageNum",
                                limitName: "pageSize"
                            },
                            page: {
                                curr:1
                            }

                        })
                    }

                })



            })





            function delMessageAdmin(arg) {
                $.ajax({
                    url:'/message/delMessageAdmin',
                    traditional:true,
                    data:{
                        'messageId':arg
                    },
                    type: 'post',
                    success:function (data) {
                        console.log("success!")
                    }
                })
            }
            
            function addImportMessage() {
                $.ajax({
                    url:'/message/addMessageAdmin',
                    traditional:true,
                    data:{
                        'description':arg
                    },
                    type: 'post',
                    success:function (data) {
                        console.log("success!")
                    }
                })
            }

            function dealMessage(arg) {
                $.ajax({
                    url:'/message/dealMessage',
                    traditional:true,
                    data:{
                        'messageId':arg
                    },
                    type: 'post',
                    success:function (data) {
                        console.log("success!")
                    }
                })
            }
        </script>
    </div>
</div>
</div>
</body>

<%--<div id="messageadd" style="display:none; padding: 10px" >--%>
<%--    <form class="layui-form" lay-filter="messageadd"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->--%>

<%--        <div class="layui-form-item">--%>
<%--            <label class="layui-form-label">消息内容</label>--%>
<%--            <div class="layui-input-block">--%>
<%--                <textarea name="description" placeholder="请输入内容" class="layui-textarea">--%>
<%--                    --%>
<%--                </textarea>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div class="layui-form-item">--%>
<%--            <div class="layui-input-block">--%>
<%--                <button class="layui-btn" lay-submit lay-filter="*">发送</button>--%>
<%--                <button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <!-- 更多表单结构排版请移步文档左侧【页面元素-表单】一项阅览 -->--%>
<%--    </form>--%>
<%--</div>--%>

</html>
