function addpost() {
    var posttitle = $('#posttitle').val();
    var sec = document.getElementById("secsel");
    var postcontent = editor.txt.html();
    var posythemeId = sec.options[sec.selectedIndex].value;
    var postSection = getQueryVariable("sectionId");
    console.log("写贴："+posttitle+"|"+posythemeId+"|"+postcontent+"|"+postSection);

    if (posttitle == null){
        alert("title null");
        return;
    }
    if (posythemeId<=0){
        alert("theme error");
        return;
    }
    var editPost = {};
    editPost.postTitle = posttitle;
    editPost.postContent = postcontent;
    editPost.postThemeId = posythemeId;
    editPost.postSection = postSection;
    var post = JSON.stringify(editPost);
    $.ajax({
        url:"/post/writepost",
        type:"POST",
        dataType:"json",
        data: post,
        contentType:"application/json;charset=UTF-8",
        success:function (data) {
            if (data == 301)
            {
                console.log("add error")
                location.reload();
                return;
            }

            if (data == 300)
            {
                console.log("success");
                location.reload();
                return;

            }

            if (data==302)
            {
                alert("请登录！");
                window.location.href="/index.jsp";
                return;
            }

        }
    })
    //
    // location.reload();

}

