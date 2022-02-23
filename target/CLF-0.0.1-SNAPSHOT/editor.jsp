
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.jsdelivr.net/npm/wangeditor@latest/dist/wangEditor.min.js" type="text/javascript" charset="UTF-8"></script>
</head>

<body>
        <div id="div1">
            <p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>
        </div>
</body>
<!-- 引入 wangEditor.min.js -->
<script type="text/javascript">
    const Ex = window.wangEditor
    const editor = new Ex('#div1')
    // 或者 const editor = new E( document.getElementById('div1') )
    editor.create()
</script>
</html>
