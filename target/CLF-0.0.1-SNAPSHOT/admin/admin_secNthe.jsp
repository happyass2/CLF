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
    <title>板块、主题管理</title>
</head>
<link rel="stylesheet" href="../layui/css/layui.css">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/wangeditor@latest/dist/wangEditor.min.js" type="text/javascript" charset="UTF-8"></script>

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
                <li class="layui-this">板块管理</li>
            </ul>

            <div class="layui-tab-content" id="tagBody">
                <!-- 内容主体区域 -->
                <div class="layui-tab-item layui-show">
                    <script type="text/html" id="MyToolBar">
                        <button type="button" class="layui-btn" lay-event="add">
                            <i class="layui-icon">&#xe608;</i>
                            添加
                        </button>
                        <button type="button" class="layui-btn layui-bg-red" lay-event="deleteM">
                            <i class="layui-icon">&#xe640;</i>
                            删除
                        </button>
                    </script>

                    <script type="text/html" id="MyToolBar2">
                        <div class="layui-btn-group">
                            <button type="button" class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" >
                                <i class="layui-icon">&#xe615;</i>
                            </button>
                            <button type="button" class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit" >
                                <i class="layui-icon">&#xe642;</i>
                            </button>
                            <button type="button" class="layui-btn layui-btn-primary layui-btn-xs" lay-event="delete">
                                <i class="layui-icon">&#xe640;</i>
                            </button>

                        </div>
                    </script>


                    <div id="ftablediv">
                        <table class="layui-hide" id="LAY_table_section" lay-filter="test">
                        </table>
                    </div>


                </div>

            </div>
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
            layui.use('element', function(){
                var element = layui.element;
                element.tab({
                    headerElem:'#tagHeader>li'
                    ,bodyElem:'#tagBody>.layui-tab-item'
                })
            });

            layui.use('table', function() {
                var table = layui.table;


                table.render({
                    elem: '#LAY_table_section'
                    , url: '/section/getAllSection'
                    , method: 'post'
                    , toolbar: '#MyToolBar'
                    , title: '帖子数据表'
                    , cols: [[
                        {type: 'checkbox', fixed: true}
                        , {field: 'sectionId', title: '板块ID', width: 300, sort: true, fixed: true}
                        , {field: 'sectionName', title: '板块名', width: 300, fixed: true}
                        , {field: 'sectionDescribe', title: '板块描述', width: 300, fixed: true}
                        , {field: 'sectionIcon', title: '板块图标', width: 300, sort: true, fixed: true}
                        , {field: 'sectionRule', title: '板块规则', width: 300, sort: true, fixed: true}
                        , {
                            field: 'domethod',
                            title: '操作',
                            width: 200,
                            fixed: true,
                            align: 'center',
                            toolbar: '#MyToolBar2'
                        }
                    ]]
                    , height: 900
                    , id: 'tableid'


                });

                table.on('toolbar(test)',function (obj) {
                    var arr = [];
                    var checkStatus = table.checkStatus('tableid')
                        , data = checkStatus.data;
                    if (obj.event === 'deleteM') {
                        layer.confirm('真的删除这些行么', function (index) {

                            for (var i = 0; i < data.length; i++) {
                                arr.push(data[i].sectionId);
                            }
                            if (arr.length==0)
                            {
                                alert("未选中板块！")
                                return;
                            }
                            delSection(arr);
                            layer.close(index);
                            table.reload('tableid', {});//刷新表格
                        })
                    }

                    if (obj.event ==='add'){

                        layer.open({
                            type : 1,
                            title : "添加板块",
                            area : [ '580px', '500px' ],    //设置弹出页面的宽高
                            content :$("#sectionadd"),    //表单所在的那个ID，简单的来说就是要填写的input框等
                        })



                        return false;

                    }



                })
                table.on('tool(test)',function (obj) {
                    var data = obj.data //获得当前行数据
                        ,layEvent = obj.event;

                    if (layEvent === 'delete'){
                        delSection(data.sectionId);
                    }

                    if (layEvent ==='edit'){
                        layer.open({
                            type : 1,
                            title : "添加板块",
                            area : [ '580px', '500px' ],    //设置弹出页面的宽高
                            content :$("#sectionadd2"),    //表单所在的那个ID，简单的来说就是要填写的input框等
                        })

                        document.getElementById("sectionName").value=data.sectionName
                        document.getElementById("sectionDescribe").value=data.sectionDescribe



                    }
                    var form = layui.form;
                    form.on('submit(sectionadd2)', function(data){
                        var data2 = form.val("sectionadd2");
                        data2.sectionRule = editor2.txt.html();
                        data2.sectionId = obj.data.sectionId;
                        if (data2.sectionRule==null)
                        {
                            data2.sectionRule = obj.data.sectionRule;
                        }
                        console.log(JSON.stringify(data2));
                        updSection(JSON.stringify(data2));
                    })

                    if (layEvent === 'detail'){

                        layer.open({
                            type : 1,
                            title : "板块主题",
                            area : [ '580px', '500px' ],    //设置弹出页面的宽高
                            content :$("#themediv"),    //表单所在的那个ID，简单的来说就是要填写的input框等
                        })

                        var table = layui.table;
                        table.render({
                            elem: '#LAY_table_theme'
                            ,url:'/sectiontheme/showthemebysecAdmin'
                            ,method: 'post'
                            ,where:{
                                'secId':data.sectionId
                            }
                            , toolbar: '#MyToolBar'
                            , title: '主题数据表'
                            , cols: [[
                                {type: 'checkbox', fixed: true}
                                , {field: 'themeId', title: '主题ID', width: 150, sort: true, fixed: true}
                                , {field: 'themeName', title: '主题名', width: 200, sort: true, fixed: true}
                                , {field: 'sectionId', title: '所属板块', width: 150, sort: true, fixed: true}
                            ]]
                            , height: 900
                            , id: 'themeTableId'
                        })
                    }

                    var form = layui.form;
                    form.on('submit(themeadd)',function (data) {
                        var data2 = form.val("themeadd");
                        data2.sectionId = obj.data.sectionId;

                        addTheme(JSON.stringify(data2));
                        table.reload('themeTableId',{});
                        return false;

                    })
                })

                table.on('toolbar(themeTable)',function (obj) {
                    var arr = [];
                    var checkStatus = table.checkStatus('themeTableId')
                        , data = checkStatus.data;

                    if (obj.event === 'deleteM') {
                        layer.confirm('真的删除这些行么', function (index) {

                            for (var i = 0; i < data.length; i++) {
                                arr.push(data[i].themeId);
                            }
                            if (arr.length==0)
                            {
                                alert("未选中主题！")
                                return;
                            }
                            console.log(arr);
                            delTheme(arr);
                            layer.close(index);
                            table.reload('themeTableId', {});//刷新表格
                        })
                    }

                    if (obj.event === 'add'){

                        layer.open({
                            type : 1,
                            title : "板块主题",
                            area : [ '580px', '500px' ],    //设置弹出页面的宽高
                            content :$("#themeadd")
                            //表单所在的那个ID，简单的来说就是要填写的input框等
                        })



                    }

                })

            })

            layui.use('form', function(data){
                var form = layui.form;
                form.on('submit(sectionadd)', function(data){
                    var data2 = form.val("sectionadd");
                    data2.sectionRule = editor.txt.html();

                    console.log(JSON.stringify(data2));
                    addSection(JSON.stringify(data2));

                })


            })




            function addSection(arg) {
                $.ajax({
                    url:'/section/addSectionAdmin',
                    data:arg,
                    type:'post',
                    dataType:"json",
                    contentType: "application/json;charset=UTF-8",
                    success:function () {
                        console.log("success!");
                    }
                })
            }
            function delSection(arg) {
                $.ajax({
                    url:"/section/delSection",
                    traditional:true,
                    type:"post",
                    data:{
                      'sectionId':arg
                    },success:function (data) {

                    }

                })
            }


            function updSection(arg) {

                $.ajax({
                    url:'/section/updSection',
                    data:arg,
                    type:'post',
                    dataType:"json",
                    contentType: "application/json;charset=UTF-8",
                    success:function (data) {
                        console.log("修改成功");
                    }
                })

            }
            
            function delTheme(arg) {
                $.ajax({
                    url:'/sectiontheme/delTheme',
                    traditional:true,
                    type:'post',
                    data:{
                        'themeId':arg
                    },success:function (data) {
                        console.log("success");
                    }
                })

            }
            
            function addTheme(arg) {
                console.log(arg)
                $.ajax({
                    url:"/sectiontheme/addTheme",
                    data: arg,
                    type:"post",
                    dataType:"json",
                    contentType: "application/json;charset=UTF-8",
                    success:function (data) {
                        console.log("success!")
                    }
                })
            }



        </script>

    </div>
</div>
</body>

<div id="sectionadd" style="display:none; padding: 10px" >
    <form class="layui-form" lay-filter="sectionadd"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
        <div class="layui-form-item">
            <label class="layui-form-label">板块名</label>
            <div class="layui-input-block">
                <input  type="text" name="sectionName" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">板块描述</label>
            <div class="layui-input-block">
                <input type="text" name="sectionDescribe" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">板块规则</label>
            <div class="layui-input-block">
                <div id="sectionRule">
                </div>
            </div>
        </div>



        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
        <!-- 更多表单结构排版请移步文档左侧【页面元素-表单】一项阅览 -->
    </form>
</div>

<div id="sectionadd2" style="display:none; padding: 10px" >
    <form class="layui-form" lay-filter="sectionadd2"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
        <div class="layui-form-item">
            <label class="layui-form-label">板块名</label>
            <div class="layui-input-block">
                <input  id="sectionName" type="text" name="sectionName" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">板块描述</label>
            <div class="layui-input-block">
                <input id="sectionDescribe" type="text" name="sectionDescribe" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">板块规则</label>
            <div class="layui-input-block">
                <div id="sectionRule2">
                </div>
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
        <!-- 更多表单结构排版请移步文档左侧【页面元素-表单】一项阅览 -->
    </form>
</div>

<div id="themeadd" style="display:none; padding: 10px">
    <form class="layui-form" lay-filter="themeadd"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
        <div class="layui-form-item">
            <label class="layui-form-label">主题名</label>
            <div class="layui-input-block">
                <input  id="themeName" type="text" name="themeName" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<div id="themediv" style="display: none">
    <table class="layui-hide" id="LAY_table_theme" lay-filter="themeTable">
        </table>
</div>


<script type="text/javascript">
    const Ex = window.wangEditor
    const editor = new Ex('#sectionRule')
    editor.config.menus=[
        'bold',
        'head',
        'link',
        'italic',
        'underline',
        'fontSize',
        'foreColor',
        'backColor',
        'link',
    ]
    // 或者 const editor = new E( document.getElementById('div1') )
    //设置高度
    editor.config.height = 160
    editor.create()
</script>

<script type="text/javascript">
    const Ex2 = window.wangEditor
    const editor2 = new Ex2('#sectionRule2')
    editor2.config.menus=[
        'bold',
        'head',
        'link',
        'italic',
        'underline',
        'fontSize',
        'foreColor',
        'backColor',
        'link',
    ]
    // 或者 const editor = new E( document.getElementById('div1') )
    //设置高度
    editor2.config.height = 160
    editor2.create()
</script>

</html>
