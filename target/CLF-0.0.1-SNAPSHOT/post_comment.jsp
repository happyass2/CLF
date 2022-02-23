<%--
  Created by IntelliJ IDEA.
  User: cyh
  Date: 2021/4/6
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>回帖</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/detail.css">
    <link rel="stylesheet" type="text/css" href="css/post.css">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script charset="UTF-8" language="JavaScript" type="text/javascript" src="js/post_comment.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/wangeditor@latest/dist/wangEditor.min.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<!-- 背景 -->
<div class="main_bg">


    <%@ include file="header.jsp" %>

    <!-- 主区域 -->
    <div class="main_bd_detail">


        <!-- 主内容 -->
        <div class ="main_content_detail">

                <%@ include file="header_2.jsp" %>

        </div>
        <div class="pageNpublish">
					<span style="float:left;">
						回帖
					</span>
            <div id="collectionSpan" style="float:left;margin-left: 10px; cursor: pointer">
                    <span onclick="collectPost()" >
						收藏
					</span>
            </div>
            <div id="reportSpan" style="float:left;margin-left: 10px; cursor: pointer">
                    <span onclick="reportPost()" >
						举报
					</span>
            </div>

            <div id="reportdiv">
                <div class="reporttiltle">举报</div>
                <textarea id="reportReason" placeholder="请输入原因"></textarea>
                <button type="button" onclick="subReport()">确定</button>
                <button type="button" id="reportcancel">取消</button>
            </div>

            <div class="pagenumber" >
                <ul id="page_number">

                </ul>
            </div>
        </div>


        <div class="postmaster">
            <table class="posttag">
                <tbody>
                <tr>
                    <td class="tag1">
                        <span>浏览</span>
                        <span id="watch_number"></span>
                        <span>|</span>
                        <span>回复</span>
                        <span id="comment_number"></span>

                    </td>
                    <td class="tag2">
                        <h1 id="comment_title">

                        </h1>
                    </td>
                </tr>
                </tbody>
            </table>

            <div id="comment_detail">

            </div>


        </div>
        <div class="pageNpublish">


            <div class="pagenumber">
                <ul>

                </ul>
            </div>
        </div>
    </div>

    <div class="huitie">
        <table >
            <tbody>
            <div class="uicon">

            </div>
            <div class="editdiv" id="editcontent">

            </div>

            <input class="sub_btn" onclick="writeComment()" type="button" value="提交"/>


            </tbody>
        </table>
    </div>




</div>
</body>
<script type="text/javascript">
    const Ex = window.wangEditor
    const editor = new Ex('#editcontent')
    // 或者 const editor = new E( document.getElementById('div1') )
    //设置高度
    editor.config.height = 160
    editor.create()
</script>
</html>
