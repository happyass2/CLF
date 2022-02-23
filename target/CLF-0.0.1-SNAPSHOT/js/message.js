$(function () {

    changeMessagePage(1)
})



function delMessage(arg) {

    $.ajax({
        url: "/message/delMessage",
        type: "post",
        data: {
            "messageId":arg
        },success:function (data) {
            var ck = document.getElementById(arg);
            $(ck).empty();
        }
    })
}

function watchMessage(arg) {
    var ck = document.getElementById("r"+arg);
    $.ajax({
        url: "/message/readMessage",
        type: "post",
        data: {
            "messageId":arg
        },success:function (data) {
            $(ck).text("已读").css({'color':'#0f0'});
        }
    })
}




function changeMessagePage(arg) {
    var pageNum = arg;

    $.ajax({
        url:"/message/getUserMessage",
        type:"post",
        data:{
            "pageNum":pageNum
        },
        success:function (data) {
            $("#myMessage").empty();
            $.each(data.obj, function (i, item) {
                $("#myMessage").append(
                    "<table class='titletable' id='"+item.messageId+"'>"+
                    "<tbody>"+
                    "<tr>" +
                    "<td id='msgTag'></td>"+
                    "<td style='color: #4E5465;'>" +
                    item.userName + "   " +
                    item.description + "了你的帖子：  ”" +
                    "<a href='post_comment.jsp?postId="+item.postId+"&pageNum=1'>"+item.postTitle+"</a>" + "”" +
                    "<a style='cursor: pointer;margin-left: 20px; color: #4E5465' onclick='delMessage("+item.messageId+")'>" +
                    "删除该通知" +
                    "</a>" +
                    "</td>" +
                    " <td style='float: right;line-height: 35px; margin-right: 10px;'>" +
                    item.createTime +
                    "</td>" +
                    "</tr>"+
                    "</tbody>"+
                    "</table>"
                )
                if (item.messageStatus == 0)
                {
                    $("#msgTag").empty();
                    $("#msgTag").append(
                        "<span style='color: red; cursor: pointer' id='r"+item.messageId+"' onclick='watchMessage("+item.messageId+")' >"+
                        "未读"+
                        "</span>"
                    )

                }else {
                    $("#msgTag").empty();
                    $("#msgTag").append(
                        "<span style='color: red; cursor: pointer' id='r"+item.messageId+"'>"+
                        "已读"+
                        "</span>"
                    )
                }

            })



            $(".pagenumber ul").empty();

            $(".pagenumber ul").append(
                "<li onclick='changeMessagePage(1)' style='width: 50px' >" + "首页" + "</li>"
            )

            var toa = data.toaPage;
            var cur = pageNum;



            //标签在中间
            if(toa<5)
            {
                for(var i = 1; i<=toa;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='changeMessagePage("+i+")' style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='changeMessagePage("+i+")' >" + i + "</li>"
                        )
                }
            }else if (toa >5&&cur>3&&(cur+2)<toa)
            {
                for(var i = cur-2; i<=cur+2;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='changeMessagePage("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='changeMessagePage("+i+")' >" + i + "</li>"
                        )
                }
            }else if(toa>5&&cur<=3)
            {
                for (var i = 1;i<=5;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='changeMessagePage("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='changeMessagePage("+i+")' >" + i + "</li>"
                        )
                }
            }else if((cur+2)>=toa)
            {
                for (var i =toa-4;i<=toa;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='changeMessagePage("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='changeMessagePage("+i+")' >" + i + "</li>"
                        )
                }
            }


            $(".pagenumber ul").append(
                "<li onclick='changeMessagePage("+toa+")'  style='width: 50px' >" + "尾页" + "</li>"
            )
        }
    })





}