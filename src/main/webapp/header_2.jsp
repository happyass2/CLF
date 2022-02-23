<%--
  Created by IntelliJ IDEA.
  User: cyh
  Date: 2021/4/2
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="js/postsec.js" type="text/javascript" charset="UTF-8"></script>
    <script src="js/post.js" type="text/javascript" charset="UTF-8"></script>

</head>
<body>

    <div class="each_plate">
        <ul class="detai_plate" id="sectionTag">

        </ul>
    </div>


    <div class="blog_search">
        <div id="search" >
            <input type="text" id="searchb" placeholder="请输入搜索内容">
            <button type="submit" onclick="searchPost()">搜索</button>
        </div>
        <ul class ="update_blog" id="infoNum">


        </ul>
    </div>
</body>
</html>
