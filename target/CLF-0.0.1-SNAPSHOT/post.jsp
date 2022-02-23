<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
	<title>帖子</title>
	<link rel="stylesheet" type="text/css" href="css/index.css"> 
	<link rel="stylesheet" type="text/css" href="css/post.css">
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/wangeditor@latest/dist/wangEditor.min.js" type="text/javascript" charset="UTF-8"></script>

	<script src="js/post.js" type="text/javascript" charset="UTF-8"></script>
	<script src="js/editpost.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
	<!-- 背景 -->
	<div class="main_bg">

		<%@ include file="header.jsp" %>


		<!-- 主区域 -->
		<div class="main_bd_post">

			<%@ include file="header_2.jsp" %>
				<div class="sectionnotice" id="sectionRule">
					

				</div>

				<div class="pageNpublish">
					<span>
						发帖
					</span>

					<div class="pagenumber">
						<ul name = "pagecode">

						</ul>
					</div>
				</div>
				

				<ul class="tagshow" id="themetag">



				</ul>
				<div class="postshow">

						<table  class="posttitle">
							<thead>
								<tr >
									<th colspan="2" class="titleleft">

									</th>
									<td class="titletag">浏览/回复</td>
									<td class="titletag">作者/时间</td>
									<td class="titletag">最后发表</td>
									
								</tr>
								
							</thead>
						</table>
					<div id = "postshowbysnt">


					</div>
					<div class="postlist">
						
					</div>
				</div>
				
				<div class="pageNpublish">


					<div class="pagenumber">

						<ul name = "pagecode">

						</ul>
					</div>
				</div>
				

			</div>


		</div>

		<div class="postedit">
					<div class="pubposttitle">
					快速发帖	
					</div>

					<div class="editpostquck">
						<form action="post_submit" method="get" accept-charset="utf-8">
							<div class="themeNtitle">
								<select id = "secsel">


								</select>
								<input type="text" name="title" id="posttitle"/>
								<span>请输入标题</span>
							</div>

							<div class="postcontent" id="editcontent">
								
							</div>
							
						</form>
					</div>

					<button class="btsubpost" type="button" onclick="addpost()">发表帖子</button>
				</div>

		<!-- 脚部 -->
		<div class="main_footer">
			
		</div>
	</div>
</body>
<script type="text/javascript">
	const Ex = window.wangEditor
	const editor = new Ex('#editcontent')
	// 或者 const editor = new E( document.getElementById('div1') )
	//设置高度
	editor.config.height = 180
	editor.create()
</script>
</html>