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
    <title>帖子管理</title>
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

                <li class="layui-nav-item"><a href="/admin/moderator_index.jsp">帖子、回帖管理</a></li>

            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-allowClose="true" lay-filter="tagLay">
            <ul class="layui-tab-title" id="tagHeader">
                <li class="layui-this">帖子管理</li>
            </ul>

            <div class="layui-tab-content" id="tagBody">
                <!-- 内容主体区域 -->
                <div class="layui-tab-item layui-show">
                    <script type="text/html" id="MyToolBar">
                        <button type="button" class="layui-btn layui-bg-red" lay-event="deleteM">
                            <i class="layui-icon">&#xe640;</i>
                            删除
                        </button>
                        <button type="button" class="layui-btn layui-bg-blue" lay-event="search">
                            <i class="layui-icon">&#xe615;</i>
                            查询
                        </button>
                    </script>
                    <script type="text/html" id="PostToolBar">
                        <button type="button" class="layui-btn layui-bg-red" lay-event="deleteM">
                            <i class="layui-icon">&#xe640;</i>
                            删除
                        </button>
                        <button type="button" class="layui-btn layui-bg-blue" lay-event="search">
                            <i class="layui-icon">&#xe615;</i>
                            查询
                        </button>
                        <button type="button" class="layui-btn layui-bg-green" lay-event="top">
                            <i class="layui-icon">&#xe619;</i>
                            置顶
                        </button>
                        <button type="button" class="layui-btn layui-bg-green" lay-event="elite">
                            <i class="layui-icon">&#xe6c6;</i>
                            加精
                        </button>
                    </script>
                    <script type="text/html" id="MyToolBar2">
                        <div class="layui-btn-group">
                            <button type="button" class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" >
                                <i class="layui-icon">&#xe615;</i>
                            </button>
                            <button type="button" class="layui-btn layui-btn-primary layui-btn-xs" lay-event="delete">
                                <i class="layui-icon">&#xe640;</i>
                            </button>

                        </div>
                    </script>


                    <div id="ftablediv">
                        <table class="layui-hide" id="LAY_table_post" lay-filter="test">
                        </table>
                    </div>


                </div>

            </div>
        </div>



        <script src="../layui/layui.js"></script>
        <script>


            $(function () {
                $.ajax({
                    url:"/admin/checkLoginModer",
                    type:"post",
                    success:function (data) {
                        if (data!=200){
                            alert("请登录！")
                            window.location.href="/admin/admin_login.jsp";
                        }

                    }
                })

            })
            layui.use('element', function(){
                var element = layui.element;

                element.tab({
                    headerElem:'#tagHeader>li'
                    ,bodyElem:'#tagBody>.layui-tab-item'
                })





            });

            layui.use('table',function () {
                var table = layui.table;



                table.on('tool(postComment)',function (obj) {
                    var data = obj.data //获得当前行数据
                        ,layEvent = obj.event; //获得 lay-event 对应的值
                    if (layEvent === 'delete'){
                        layer.confirm('真的删除行么', function(index){
                            obj.del(); //删除对应行（tr）的DOM结构
                            console.log(data.commentId);
                            delComment(data.commentId);
                            layer.close(index);
                            //向服务端发送删除指令
                        })
                    }

                    if (layEvent === 'detail'){

                        layer.open({
                            type : 1,
                            title : "回帖详细内容",
                            area : [ '580px', '700px' ],    //设置弹出页面的宽高
                            content :$("#commentForm"),    //表单所在的那个ID，简单的来说就是要填写的input框等

                        })

                        document.getElementById("commentContentArea").innerHTML = data.commentContent

                        return false;
                    }

                })

                table.on('toolbar(postComment)',function (obj) {
                    var postId = $(".layui-this").attr("lay-id");
                    var arr = [];
                    var tt = 'commentTableId'+postId
                    var checkStatus = table.checkStatus(tt.toString())
                        , data = checkStatus.data;
                    if (obj.event === 'deleteM') {
                        layer.confirm('真的删除这些行么', function (index) {

                            for (var i = 0; i < data.length; i++) {
                                arr.push(data[i].commentId);
                            }
                            delComment(arr);
                            layer.close(index);
                            table.reload(tt, {});//刷新表格
                        })
                    }

                    if (obj.event === 'search'){
                        layer.open({
                            type : 1,
                            title : "回帖详细内容",
                            area : [ '580px', '500px' ],    //设置弹出页面的宽高
                            content :$("#searchcomment"),    //表单所在的那个ID，简单的来说就是要填写的input框等

                        });



                    }
                })

            })

            layui.use('table', function() {
                var table = layui.table;


                table.render({
                    elem: '#LAY_table_post'
                    , url: '/post/getAllPostModerator'
                    , method: 'post'
                    , toolbar: '#PostToolBar'
                    , title: '帖子数据表'
                    , cols: [[
                        {type: 'checkbox', fixed: true}
                        , {field: 'postId', title: '帖子ID', width: 80, sort: true, fixed: true}
                        , {field: 'postTitle', title: '标题', width: 180, fixed: true}
                        , {field: 'postIcon', title: '图标', width: 80, fixed: true}
                        , {field: 'watchNumber', title: '浏览数', width: 80, sort: true, fixed: true}
                        , {field: 'collectNumber', title: '收藏数', width: 80, sort: true, fixed: true}
                        , {field: 'commentNumber', title: '评论数', width: 80, sort: true, fixed: true}
                        , {field: 'createTime', title: '发布时间', width: 200, sort: true, fixed: true}
                        , {field: 'lastTime', title: '更新时间', width: 200, sort: true, fixed: true}
                        , {field: 'topPostDesc', title: '是否置顶', width: 80, sort: true, fixed: true}
                        , {field: 'elitePostDesc', title: '是否精华', width: 80, sort: true, fixed: true}
                        , {field: 'sectionName', title: '所属板块', width: 100, sort: true, fixed: true}
                        , {field: 'themeName', title: '所属主题', width: 100, sort: true, fixed: true}
                        , {field: 'userName', title: '楼主名', width: 100, sort: true, fixed: true}
                        , {field: 'publisherId', title: '楼主ID', width: 80, sort: true, fixed: true}
                        , {
                            field: 'domethod',
                            title: '操作',
                            width: 100,
                            fixed: true,
                            align: 'center',
                            toolbar: '#MyToolBar2'
                        }
                    ]]
                    , page: true
                    , height: 900
                    , id: 'tableid'


                });

                table.on('toolbar(test)', function (obj) {

                    var checkStatus = table.checkStatus('tableid')
                        , data = checkStatus.data;
                    if (obj.event === 'deleteM') {
                        var arr = [];
                        layer.confirm('真的删除这些行么', function (index) {

                            for (var i = 0; i < data.length; i++) {
                                arr.push(data[i].postId);
                            }
                            if (arr.length==0)
                            {
                                alert("未选中贴子！");
                                return;
                            }

                            delPost(arr);
                            layer.close(index);
                            table.reload('tableid', {});//刷新表格
                        })
                    }   //多个删除


                    if (obj.event === 'search'){

                        layer.open({
                            type : 1,
                            title : "回帖详细内容",
                            area : [ '580px', '500px' ],    //设置弹出页面的宽高
                            content :$("#searchpost"),    //表单所在的那个ID，简单的来说就是要填写的input框等

                        })


                    }

                    if (obj.event === 'top'){

                        var arr = [];
                        var arrCancel = [];
                        for (var i = 0; i < data.length; i++) {
                            console.log(data[i].topPost);
                            if (data[i].topPost == 1)
                                arrCancel.push(data[i].postId)
                            else
                                arr.push(data[i].postId);
                        }
                        if (arr.length==0&&arrCancel.length==0){
                            alert("未选中帖子！");
                            return;
                        }
                        if (arrCancel.length!=0)
                            cancelTop(arrCancel);
                        if (arr.length!=0)
                            setTop(arr);
                        table.reload('tableid', {});

                    }

                    if (obj.event === 'elite'){
                        var arr = [];
                        var arrCancel = [];
                        for (var i = 0; i < data.length; i++) {
                            if (data[i].ElitePost == 1)
                                arrCancel.push(data[i].postId)
                            else
                                arr.push(data[i].postId);
                        }
                        if (arr.length==0&&arrCancel.length==0){
                            alert("未选中帖子！");
                            return;
                        }

                        if (arrCancel.length!=0)
                            cancelElite(arrCancel);
                        if (arr.length!=0)
                            setElite(arr);
                        table.reload('tableid', {});

                    }

                })

                table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                    var data = obj.data //获得当前行数据
                        ,layEvent = obj.event; //获得 lay-event 对应的值


                    if(layEvent === 'delete'){
                        layer.confirm('真的删除行么', function(index){
                            obj.del(); //删除对应行（tr）的DOM结构
                            delPost(data.postId);
                            layer.close(index);
                            //向服务端发送删除指令
                        })
                    }  //单个删除


                    if (layEvent === 'detail'){

                        var tt = document.getElementById("layui-tab-commentdiv"+data.postId+"")
                        if (tt!=null)
                        {
                            return false;
                            alert('已打开');
                        }

                        var element = layui.element;
                        element.tabAdd('tagLay', {
                            title: "帖子"+data.postId+"的回帖"
                            ,content:"<div id='layui-tab-commentdiv"+data.postId+"'>"+
                                "<table class='layui-hide' id='LAY_table_comment"+data.postId+"' lay-filter='postComment'>"+
                                "</table>"+
                                "</div>"
                            ,id: data.postId
                        });


                        var table = layui.table;
                        table.render({
                            elem: '#LAY_table_comment'+data.postId+''
                            ,url:'/comment/showAdminComment'
                            ,method: 'post'
                            ,where:{
                                'postId':data.postId
                            }
                            ,request:{
                                pageName:'pageNum'
                                ,limitName:'pageSize'
                            }
                            , toolbar: '#MyToolBar'
                            , title: '回复数据表'
                            , cols: [[
                                {type: 'checkbox', fixed: true}
                                , {field: 'commentId', title: '回帖ID', width: 150, sort: true, fixed: true}
                                , {field: 'commentorId', title: '评论者ID', width: 200, fixed: true}
                                , {field: 'userName', title: '评论者名字', width: 200, fixed: true}
                                , {field: 'commentContent', title: '评论内容', width: 650, fixed: true}
                                , {field: 'createTime', title: '收藏时间', width: 200, sort: true, fixed: true}
                                , {field: 'commentFloor', title: '评论楼层', width: 150, sort: true, fixed: true}
                                , {
                                    field: 'domethod',
                                    title: '操作',
                                    width: 100,
                                    fixed: true,
                                    align: 'center',
                                    toolbar: '#MyToolBar2'
                                }
                            ]]
                            , page: true
                            , height: 900
                            , id: 'commentTableId'+data.postId
                        })
                    };



                });


            })

            layui.use('form', function(){
                var form = layui.form;

                form.on('checkbox(switchin)', function(data){
                    if (data.elem.checked)
                    {
                        var ip = document.getElementsByName(data.value);
                        if (data.value=='postSectionId') getSection();
                        if (data.value=='postThemeId') {

                            var sectionId = $("#postSectionId").val();
                            if (sectionId==0)
                            {
                                alert("请先选择板块！");
                                return;
                            }else {
                                getTheme(sectionId);
                            }
                        }
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

                form.on('submit(searchpost)',function (data) {


                    var data2 = form.val("searchpost");

                    data2.createTime = $("#createTime").val();
                    data2.lastTime = $("#lastTime").val();

                    getSearchPost(JSON.stringify(data2))

                    return false;
                })

                form.on('submit(searchcomment)',function (data) {


                    var postId = $(".layui-this").attr("lay-id");
                    var commentorId = $("#commentorId").val();
                    var commentContent=$("#commentContent").val();
                    console.log(postId+commentorId+commentContent)
                    var table = layui.table;
                    table.reload('commentTableId'+postId,{
                        url:'/comment/getSearchComment'
                        ,method:'post'
                        ,where:{
                            'postId':postId,
                            'commentorId':commentorId,
                            'commentContent':commentContent
                        }
                        ,page:{
                            curr:1
                        }
                    })
                    return false;

                })




            })
            layui.use('laydate', function(){
                var laydate = layui.laydate;

                //执行一个laydate实例
                laydate.render({

                    elem: '#createTime' //指定元素
                });
                laydate.render({

                    elem: '#createTime2' //指定元素
                });


                laydate.render({

                    elem: '#lastTime' //指定元素
                });
                laydate.render({

                    elem: '#lastTime2' //指定元素
                });
            });
            function delComment(arg) {

                console.log(arg)

                $.ajax({
                    url:'/comment/delComment'
                    ,data:{
                        'commentId':arg
                    }
                    ,traditional: true
                    ,type:'post'
                    ,success:function (data) {
                        console.log("sucess")
                    }
                })

            }

            function delPost(arg) {

                $.ajax({
                    url:"/post/delPost",
                    traditional: true,
                    type: "post",
                    data:{
                        postId:arg
                    },success:function () {
                        console.log("successDel")
                    }
                })
            }

            function getSection() {
                $.ajax({
                    url:"/section/showSection",
                    type:"get",
                    success:function (data) {
                        $("#postSectionId").empty();
                        $("#postSectionId").append(
                            "<option value=''>"+"-请选择-"+"</option>"
                        )
                        $.each(data,function (i,item) {
                            $("#postSectionId").append(
                                "<option value='"+item.sectionId+"'>"+item.sectionName+"</option>"
                            )
                        })
                    }
                })

            }

            function getTheme(arg) {
                $.ajax({
                    url:"/sectiontheme/showthemebysec",
                    type:"post",
                    data:{
                        'secId':arg
                    },
                    success:function (data) {
                        $("#postThemeId").empty();
                        $("#postThemeId").append(
                            "<option value=''>"+"-请选择-"+"</option>"
                        )
                        $.each(data.obj,function (i,item) {


                            $("#postThemeId").append(
                                "<option value='"+item.themeId+"'>"+item.themeName+"</option>"
                            )
                        })
                    }
                })
            }


            function getSearchPost(arg) {
                console.log(arg);

                $.ajax({
                    url:"/post/getSearchPostModerator",
                    contentType: "application/json;charset=UTF-8",
                    dataType: "json",
                    type:'post',
                    data:arg,
                    success:function (data1) {

                        layui.use('table', function() {
                            var table = layui.table;
                            table.render({
                                elem: '#LAY_table_post'
                                , data: data1.data
                                , method: 'post'
                                , toolbar: '#MyToolBar'
                                , title: '帖子数据表'
                                , cols: [[
                                    {type: 'checkbox', fixed: true}
                                    , {field: 'postId', title: '帖子ID', width: 80, sort: true, fixed: true}
                                    , {field: 'postTitle', title: '标题', width: 180, fixed: true}
                                    , {field: 'postIcon', title: '图标', width: 80, fixed: true}
                                    , {field: 'watchNumber', title: '浏览数', width: 80, sort: true, fixed: true}
                                    , {field: 'collectNumber', title: '收藏数', width: 80, sort: true, fixed: true}
                                    , {field: 'commentNumber', title: '评论数', width: 80, sort: true, fixed: true}
                                    , {field: 'createTime', title: '发布时间', width: 200, sort: true, fixed: true}
                                    , {field: 'lastTime', title: '更新时间', width: 200, sort: true, fixed: true}
                                    , {field: 'topPostDesc', title: '是否置顶', width: 80, sort: true, fixed: true}
                                    , {field: 'elitePostDesc', title: '是否精华', width: 80, sort: true, fixed: true}
                                    , {field: 'sectionName', title: '所属板块', width: 100, sort: true, fixed: true}
                                    , {field: 'themeName', title: '所属主题', width: 100, sort: true, fixed: true}
                                    , {field: 'userName', title: '楼主名', width: 100, sort: true, fixed: true}
                                    , {field: 'publisherId', title: '楼主ID', width: 80, sort: true, fixed: true}
                                    , {
                                        field: 'domethod',
                                        title: '操作',
                                        width: 100,
                                        fixed: true,
                                        align: 'center',
                                        toolbar: '#MyToolBar2'
                                    }
                                ]]
                                , height: 900
                                , id: 'tableid'
                            })
                            console.log(data1);
                        });

                    }
                })
            }


            function setTop(arg) {
                $.ajax({
                    url:'/post/setTop',
                    data:arg,
                    type:'post',
                    traditional:true,
                    success:function (data) {

                    }
                })

            }

            function setElite(arg) {
                $.ajax({
                    url:'/post/setElite',
                    data:arg,
                    type:'post',
                    traditional:true,
                    success:function (data) {

                    }
                })
            }

            function setTop(arg) {
                $.ajax({
                    url:'/post/setTop',
                    data:{
                        'postId':arg
                    },
                    traditional:true,
                    method:'post',
                    success:function (data) {

                    }

                })
            }
            function setElite(arg) {
                $.ajax({
                    url:'/post/setElite',
                    data:{
                        'postId':arg
                    },
                    traditional:true,
                    method:'post',
                    success:function (data) {

                    }

                })
            }
            function cancelTop(arg) {
                $.ajax({
                    url:'/post/cancelTop',
                    data:{
                        'postId':arg
                    },
                    traditional:true,
                    method:'post',
                    success:function (data) {

                    }

                })
            }
            function cancelElite(arg) {
                $.ajax({
                    url:'/post/cancelElite',
                    data:{
                        'postId':arg
                    },
                    traditional:true,
                    method:'post',
                    success:function (data) {

                    }

                })
            }



        </script>

    </div>
</div>
</body>

<div id="commentForm" style="display: none">
    <div style="padding: 10px;" id="commentContentArea">

    </div>
</div>

<div id="searchpost" style="display:none;"  >
    <form  class="layui-form" lay-filter="searchpost" method="post" style="margin: 10px;">
        <div class="layui-form-item">
            <label class="layui-form-label">帖子标题</label>
            <div class="layui-input-block">
                <input type="text" name="postTitle"  placeholder="请输入用户名" autocomplete="off" class="layui-input" style="width: 400px;float: left;margin-right: 10px;" disabled="true">
                <input type="checkbox"  lay-skin="primary" value="postTitle" lay-filter="switchin" >
            </div>
        </div>



        <div class="layui-form-item">
            <label class="layui-form-label">发帖时间</label>
            <div class="layui-input-block">
                <input type="text" id="createTime" placeholder="开始时间" name="createTime2" autocomplete="off" class="layui-input" style="width: 190px;float: left;margin-right: 10px;" disabled="true">
                <input type="text" id="createTime2"  placeholder="结束时间" name="createTime2" autocomplete="off" class="layui-input" style="width: 190px;float: left;margin-right: 10px;" disabled="true">
                <input type="checkbox"  lay-skin="primary" value="createTime2" lay-filter="switchin">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">上次登录时间</label>
            <div class="layui-input-block">
                <input type="text" id="lastTime" placeholder="开始时间" name="lastTime2"  autocomplete="off" class="layui-input" style="width: 190px;float: left;margin-right: 10px;" disabled="true">
                <input type="text" id="lastTime2" placeholder="结束时间" name="lastTime2"  autocomplete="off" class="layui-input" style="width: 190px;float: left;margin-right: 10px;" disabled="true">
                <input type="checkbox"  lay-skin="primary" value="lastTime2" lay-filter="switchin">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">发帖人ID</label>
            <div class="layui-input-block">
                <input type="text" id="publisherId"  name="publisherId"  autocomplete="off" class="layui-input" style="width: 400px;float: left;margin-right: 10px;" disabled="true">
                <input type="checkbox"  lay-skin="primary" value="publisherId" lay-filter="switchin">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">发帖人ID</label>
            <div class="layui-input-block">
                <select lay-ignore name="postSectionId" id="postSectionId" disabled="true" style="margin-top: 5px">
                    <option value="0">-请选择-</option>
                </select>

                <input type="checkbox"  lay-skin="primary" value="postSectionId" lay-filter="switchin">

                <select lay-ignore name="postThemeId" id="postThemeId" disabled="true" style="margin-top: 5px">
                    <option value="0">-请选择-</option>
                </select>
                <input type="checkbox"  lay-skin="primary" value="postThemeId" lay-filter="switchin">
            </div>
        </div>




        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
            </div>
        </div>



    </form>
</div>


<div id="searchcomment" style="display:none;">
    <form  class="layui-form" lay-filter="searchcomment" method="post" style="margin: 10px;">
        <div class="layui-form-item">
            <label class="layui-form-label">用户ID</label>
            <div class="layui-input-block">
                <input type="text" name="commentorId" id="commentorId"  placeholder="请输入用户名" autocomplete="off" class="layui-input" style="width: 400px;float: left;margin-right: 10px;" disabled="true">
                <input type="checkbox"  lay-skin="primary" value="commentorId" lay-filter="switchin" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">回帖内容</label>
            <div class="layui-input-block">
                <input type="text"  name="commentContent" id="commentContent"  autocomplete="off" class="layui-input" style="width: 400px;float: left;margin-right: 10px;" disabled="true">
                <input type="checkbox"  lay-skin="primary" value="commentContent" lay-filter="switchin">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
            </div>
        </div>



    </form>
</div>


</html>
