$(function () {
    var postId = getQueryVariable("postId");
    var pageNum = getQueryVariable("pageNum");
    console.log("helllllllo")
    showPostCommentAndTitle(postId,pageNum);
    getCollect(postId);
    $('#reportcancel').click(function () {
        document.getElementById("reportdiv").style.display="none";
    })
})


function showPostCommentAndTitle(arg,arg2) {
    var postId = arg;
    var pageNum = arg2;

    $.ajax({
        url:"/comment/showcomment?postId="+postId+"&pageNum="+pageNum,
        type:"GET",
        success:function (data) {
            if (data!=null){
                console.log(data);
                var title = data.commentTitle;
                var commentpage = data.commentPage;

                $("#watch_number").text(title.watchNumber);
                $("#comment_number").text(title.commentNumber);
                $("#comment_title").empty();
                $("#comment_title").append(
                    "<a href='post.jsp?sectionId="+title.sectionId+"&themeId="+title.themeId+"&pageNum=1'>"+"["+title.themeName+"]"+"</a>" +
                    "<span>"+title.postTitle+"</span>"

                )
                $("#comment_detail").empty();
                $.each(commentpage, function (i, item){
                    console.log(item.commentContent);
                    $("#comment_detail").append(

                    "<div class='commentpost'>" +
                        "<table>" +
                        "<tbody>" +
                        "<tr>" +
                        "<td class='uicon'>"+
                        "<div class='uname'>"+
                        "<a>"+item.userName+"</a>"+
                        "</div>"+
                        "<div class='uicon2'>"+
                        "<img src="+item.userIcon+" >"+
                        "</div>"+
                        "<table class='uicinfo'>"+
                        "<tbody>"+
                        "<tr>"+

                    "<th>"+
                    "<p class='borle' style='font-size:14px; color: #4E5465 '>"+
                        "&nbsp;"+"<a>"+item.userAct+"</a>"+
                    "<br/>"+
                    "活跃"+
                    "</p>"+
                    "</th>"+
                    "<th>"+
                    "<p style='font-size:14px; color: #4E5465 '>"+
                    "&nbsp;"+"<a>"+item.userPostNumber+"</a>"+
                    "<br/>"+
                    "帖子"+
                    "</p>"+
                    "</th>"+
                    "</tr>"+
                    "</tbody>"+
                    "</table>"+
                    "<div style='font-size:12px; color: #4E5465 '>"+
                    "注册时间&nbsp;&nbsp;"+getMyDate(item.registerTime)+
                    "</div>"+
                   " </td>"+


                    "<td class='ucomment'>"+
                        "<div class='commtitle'>"+
                        "<p>"+getMyDate(item.createTime)+"</p>"+
                    "<span>"+item.commentFloor+"楼"+"</span>"+
                    "</div>"+

                    "<table>"+

                    "<tbody>"+
                    "<tr>"+
                    "<td class='commentcontent'>"
                       +item.commentContent+
                "</td>"+
                    "</tr>"+
                    "</tbody>"+
                    "</table>"+
                    "</td>"+
                    "</tr>"+
                    "</tbody>"+
                    "</table>"+
                    "</div>"


                    )
                })
            }

            //页码



            $(".pagenumber ul").empty();

            $(".pagenumber ul").append(
                "<a  href='post_comment.jsp?postId="+postId+"&pageNum=1'><li style='width: 50px' >" + "首页" + "</li></a>"
            )

            var cur = pageNum;
            var toa = data.totalpage;


            //标签在中间
            if(toa<5)
            {
                for(var i = 1; i<=toa;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<a  href='post_comment.jsp?postId="+postId+"&pageNum="+i+"'><li style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li></a>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<a  href='post_comment.jsp?postId="+postId+"&pageNum="+i+"'><li >" + i + "</li></a>"
                        )
                }
            }else if (toa >5&&cur>3&&(cur+2)<toa)
            {
                for(var i = cur-2; i<=cur+2;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<a  href='post_comment.jsp?postId="+postId+"&pageNum="+i+"'><li  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li></a>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<a  href='post_comment.jsp?postId="+postId+"&pageNum="+i+"'><li >" + i + "</li></a>"
                        )
                }
            }else if(toa>5&&cur<=3)
            {
                for (var i = 1;i<=5;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<a  href='post_comment.jsp?postId="+postId+"&pageNum="+i+"'><li  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li></a>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<a  href='post_comment.jsp?postId="+postId+"&pageNum="+i+"'><li >" + i + "</li></a>"
                        )
                }
            }else if((cur+2)>=toa)
            {
                for (var i =toa-4;i<=toa;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<a  href='post_comment.jsp?postId="+postId+"&pageNum="+i+"'><li  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li></a>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<a  href='post_comment.jsp?postId="+postId+"&pageNum="+i+"'><li >" + i + "</li></a>"
                        )
                }
            }


            $(".pagenumber ul").append(
                "<a  href='post_comment.jsp?postId="+postId+"&pageNum="+toa+"'><li  style='width: 50px' >" + "尾页" + "</li></a>"
            )



        }

    })
}


function getMyDate(str) {
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ addZero(oMonth) +'-'+ addZero(oDay) +' '+ addZero(oHour) +':'+
            addZero(oMin) +':'+addZero(oSen);
    return oTime;
}
function addZero(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}

function writeComment() {
    var content  = editor.txt.html();
    var postId = getQueryVariable("postId");

    $.ajax({
        url:"/comment/addPostComment",
        data:{
            "postId":postId,
            "commentContent":content
        },type:"POST",
        success:function (data) {
            if (data == 401) {
                alert("请登录！");
                location.reload();
            }
            if (data == 400) {
                console.log("sucess")
                location.reload();
            }
        }
    })


}


function collectPost() {
    var postId = getQueryVariable("postId");
    $.ajax({
        url:"/collect/addCollection",
        data:{
            "postId":postId,
        },type:"POST",
        success:function (data) {
            if (data == 501)
                console.log("login error");
            if (data == 500)
                console.log("sucess")
        }
    })

}

function getCollect(arg) {

    var postId = arg;
    $.ajax({
        url:"/collect/checkCollection",
        data:{
            "postId":postId,
        },type:"POST",
        success:function (data) {
            if (data == 500)
            {
                $("#collectionSpan").empty();
                $("#collectionSpan").append(
                "<span>"+
                    "已收藏"+
                "</span>"
                )
            }
        }
    })

}


function reportPost() {
   document.getElementById("reportdiv").style.display="block";
}

function subReport(){
    var postId = getQueryVariable("postId");
    var dscr = $("#reportReason").val();
    $.ajax({
        url:"/message/reportPost",
        type:"post",
        data:{
            "postId":postId,
            "descr":dscr
        },success:function (data) {
            alert("举报成功！");
            document.getElementById("reportdiv").style.display="none";
        }
    })
}
