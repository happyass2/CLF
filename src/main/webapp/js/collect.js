$(function () {

    var userId = getQueryVariable("userId");

    $.ajax({
        url:"/user/getUserinfoById",
        data:{
            "userId":userId
        },
        type:"get",
        success:function (data) {
            if (data.code==201)
            {
                window.location="localhost:8080/user_post.jsp?userId="+userId;
            }
            else {
                getUserPosts(userId);
            }
        }
    })




})

function getCollect(arg) {
    var tags = document.getElementsByName("infotag");

    for (var i = 0;i<tags.length;i++)
    {
        tags[i].style.background='#eeeeee';
        tags[i].style.color='#4E5465';
    }

    var cd = document.getElementById("mycollect");
    cd.style.background='#3c8dbc';
    cd.style.color='#ffffff'


var page = arg;
    $.ajax({
        url:"/collect/getCollection",
        type:"post",
        data:{
            "pageNum":page
        },
        success:function (data) {

            $("#myPostTag").empty()
            $("#choseAll").empty();
            $("#choseAll").append(
                "<span onclick='getAll()' style='cursor: pointer'>"+"全选"+"</span>"
            )
            $("#myPostTag").append(
                "<td class='tagright'>"+"作者"+"</td>"+
                "<td class='tagright'>"+"发布时间"+"</td>"+
                "<td class='tagright'>"+"最后回复时间"+"</td>"
            )
            $(".collectcon2").empty();

            $.each(data.obj, function (i, item) {

                $(".collectcon2").append(
                    "<table class='listtable' id='"+item.postId+"'>" +
                    "<tbody>" +
                    "<tr>" +
                    "<td class='tagleft'>" +
                    "<input type='checkbox' name='delcheck' value="+item.postId+">" +
                    "</td>" +
                    "<td class='tagleft'>" +
                    "<img src=" + item.img + ">" +
                    "</td>" +

                    "<th class='tagleft'>" +
                    "<a href='post_comment.jsp?postId="+item.postId+"&pageNum=1'>"+item.postTitle +"</a>"+
                    "</th>" +
                    "<td class='tagright'>" + item.username + "</td>" +
                    "<td class='tagright'>" + item.createtime + "</td>" +
                    "<td class='tagright'>" + item.lasttime + "</td>" +
                    "</tr>" +
                    "</tbody>" +
                    "</table>"
                );
            })
            $("#delbtn").empty();
            $("#delbtn").append(
                "<button type='button' value='删除' style='width: 100px;height: 30px;' onclick='delCollect()'>"+
                "删除"+
                "</button>"
            )



            $(".pagenumber ul").empty();

            $(".pagenumber ul").append(
                "<li onclick='getCollect(1)' style='width: 50px' >" + "首页" + "</li>"
            )

            var toa = data.toaPage;
            var cur = data.currPage;


            //标签在中间
            if(toa<5)
            {
                for(var i = 1; i<=toa;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getCollect("+i+")' style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getCollect("+i+")' >" + i + "</li>"
                        )
                }
            }else if (toa >5&&cur>3&&(cur+2)<toa)
            {
                for(var i = cur-2; i<=cur+2;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getCollect("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getCollect("+i+")' >" + i + "</li>"
                        )
                }
            }else if(toa>5&&cur<=3)
            {
                for (var i = 1;i<=5;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getCollect("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getCollect("+i+")' >" + i + "</li>"
                        )
                }
            }else if((cur+2)>=toa)
            {
                for (var i =toa-4;i<=toa;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getCollect("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getCollect("+i+")' >" + i + "</li>"
                        )
                }
            }


            $(".pagenumber ul").append(
                "<li onclick='getCollect("+toa+")'  style='width: 50px' >" + "尾页" + "</li>"
            )

        }


        })

}



function getUserPosts(arg) {


    var tags = document.getElementsByName("infotag");

    for (var i = 0;i<tags.length;i++)
    {
        tags[i].style.background='#eeeeee';
        tags[i].style.color='#4E5465';
    }


    var cd = document.getElementById("mypost");
    cd.style.background='#3c8dbc';
    cd.style.color='#ffffff';

    var page = arg;
    $.ajax({
        url:"/post/getUserPosts",
        type:"post",
        data:{

            "pageNum":page
        },
        success:function (data) {
            $("#myPostTag").empty()
            $("#choseAll").empty();
            $("#choseAll").append(
            "<span onclick='getAll()' style='cursor: pointer'>"+"全选"+"</span>"
            )

            $("#myPostTag").append(
                "<td class='tagright'>"+"浏览人数"+"</td>"+
                "<td class='tagright'>"+"评论人数"+"</td>"+
                "<td class='tagright'>"+"发布时间"+"</td>"+
                "<td class='tagright'>"+"最后回复时间"+"</td>"
            )

            $(".collectcon2").empty();

            $.each(data.obj, function (i, item) {

                $(".collectcon2").append(
                    "<table class='listtable' id='"+item.postId+"'>" +
                    "<tbody>" +
                    "<tr>" +
                    "<td class='tagleft'>" +
                    "<input type='checkbox' name='delcheck' value=" + item.postId + ">" +
                    "</td>" +
                    "<td class='tagleft'>" +
                    "<img src=" + item.img + ">" +
                    "</td>" +

                    "<th class='tagleft'>" +

                    "<a href='post_comment.jsp?postId="+item.postId+"&pageNum=1'>"+item.postTitle +"</a>"+
                    "</th>" +
                    "<td class='tagright'>" + item.watchnumber + "</td>" +
                    "<td class='tagright'>" + item.commentnumber + "</td>" +
                    "<td class='tagright'>" + item.createtime + "</td>" +
                    "<td class='tagright'>" + item.lasttime + "</td>" +
                    "</tr>" +
                    "</tbody>" +
                    "</table>"
                );
            })


            $("#delbtn").empty();
            $("#delbtn").append(
            "<button type='button' value='删除' style='width: 100px;height: 30px;' onclick='delPosts()'>"+
            "删除"+
            "</button>"
            )

            $(".pagenumber ul").empty();

            $(".pagenumber ul").append(
                "<li onclick='getUserPosts(1)' style='width: 50px' >" + "首页" + "</li>"
            )

            var toa = data.toaPage;
            var cur = data.currPage;


            //标签在中间
            if(toa<5)
            {
                for(var i = 1; i<=toa;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getUserPosts("+i+")' style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getUserPosts("+i+")' >" + i + "</li>"
                        )
                }
            }else if (toa >5&&cur>3&&(cur+2)<toa)
            {
                for(var i = cur-2; i<=cur+2;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getUserPosts("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getUserPosts("+i+")' >" + i + "</li>"
                        )
                }
            }else if(toa>5&&cur<=3)
            {
                for (var i = 1;i<=5;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getUserPosts("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getUserPosts("+i+")' >" + i + "</li>"
                        )
                }
            }else if((cur+2)>=toa)
            {
                for (var i =toa-4;i<=toa;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getUserPosts("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getUserPosts("+i+")' >" + i + "</li>"
                        )
                }
            }


            $(".pagenumber ul").append(
                "<li onclick='getUserPosts("+toa+")'  style='width: 50px' >" + "尾页" + "</li>"
            )

        }


    })

}

function delPosts() {

    var arr = new Array();
    var str = document.getElementsByName("delcheck");

    str.forEach(function (input,index) {
        if (input.checked){
            arr.push(input.value);
        }

    })
    for (var i = 0;i<arr.length;i++){
        var dk = document.getElementById(arr[i]);
        console.log(dk);
        $(dk).empty();
    }
    console.log(arr);

    $.ajax({
        url:"/post/delPost",
        traditional: true,
        data: {
            "postId":arr
        },
        type:"post",
        success:function (data) {
            
        }
    })


}

function getAll() {

    var str = document.getElementsByName("delcheck");

    str.forEach(function (input,index) {
        if (input.checked){
            input.checked = false;
        }else
        {
            input.checked = true;
        }

    })
}

function getUserComment(arg) {
    var tags = document.getElementsByName("infotag");

    for (var i = 0;i<tags.length;i++)
    {
        tags[i].style.background='#eeeeee';
        tags[i].style.color='#4E5465';
    }

    var cd = document.getElementById("icomment");
    cd.style.background='#3c8dbc';
    cd.style.color='#ffffff';


    var page = arg;
    $.ajax({
        url:"/comment/getUserComment",
        type:"post",
        data:{

            "pageNum":page
        },
        success:function (data) {

            $("#choseAll").empty();
            $("#myPostTag").empty()

            // $("#myPostTag").append(
            //     "<td class='tagright'>"+"浏览人数"+"</td>"+
            //     "<td class='tagright'>"+"评论人数"+"</td>"+
            //     "<td class='tagright'>"+"发布时间"+"</td>"+
            //     "<td class='tagright'>"+"最后回复时间"+"</td>"
            // )

            $(".collectcon2").empty();

            $.each(data.obj, function (i, item) {

                $(".collectcon2").append(
                "<table class='listtable'>"+
                    "<tbody>"+
                    "<tr>"+
                    "<td class='tagleft'>"+
                    "<a>"+
                    item.userName+
                    "</a>"+
                    "  在帖子  "+
                    "<a href='post_comment.jsp?postId="+item.postId+"&pageNum=1'>"+"“"+
                item.postTitle+"”"+
                    "</a>"+
                item.commentFloor+"楼  "+
               "回复："+
            "</td>"+
                "<td style='float: right;'>"+
                item.createTime+
                "</td>"+
                "</tr>"+
                "<tr class='tagleft'>"+
                    "<td>"+"“"+
                    item.commentContent.replace(/<[^>]+>/g,"")+"”"+
                    "</td>"+

                    "</tr>"+

                    "</tbody>"+
                    "</table>"
                );
            })

            $(".pagenumber ul").empty();

            $(".pagenumber ul").append(
                "<li onclick='getUserComment(1)' style='width: 50px' >" + "首页" + "</li>"
            )

            var toa = data.toaPage;
            var cur = data.currPage;


            //标签在中间
            if(toa<5)
            {
                for(var i = 1; i<=toa;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getUserComment("+i+")' style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getUserComment("+i+")' >" + i + "</li>"
                        )
                }
            }else if (toa >5&&cur>3&&(cur+2)<toa)
            {
                for(var i = cur-2; i<=cur+2;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getUserComment("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getUserComment("+i+")' >" + i + "</li>"
                        )
                }
            }else if(toa>5&&cur<=3)
            {
                for (var i = 1;i<=5;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getUserComment("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getUserComment("+i+")' >" + i + "</li>"
                        )
                }
            }else if((cur+2)>=toa)
            {
                for (var i =toa-4;i<=toa;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getUserComment("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getUserComment("+i+")' >" + i + "</li>"
                        )
                }
            }


            $(".pagenumber ul").append(
                "<li onclick='getUserComment("+toa+")'  style='width: 50px' >" + "尾页" + "</li>"
            )

        }


    })

}


function getMyPostComment(arg) {

    var tags = document.getElementsByName("infotag");

    for (var i = 0;i<tags.length;i++)
    {
        tags[i].style.background='#eeeeee';
        tags[i].style.color='#4E5465';
    }


    var cd = document.getElementById("commentme");
    cd.style.background='#3c8dbc';
    cd.style.color='#ffffff';

    var page = arg;
    $.ajax({
        url:"/comment/getMyPostComment",
        type:"post",
        data:{

            "pageNum":page
        },
        success:function (data) {

            $("#choseAll").empty();
            $("#myPostTag").empty()

            $(".collectcon2").empty();

            $.each(data.obj, function (i, item) {

                $(".collectcon2").append(
                    "<table class='listtable'>"+
                    "<tbody>"+
                    "<tr>"+
                    "<td class='tagleft'>"+
                    "<a>"+
                    item.userName+
                    "</a>"+
                    "  在我的帖子：  "+
                    "<a href='post_comment.jsp?postId="+item.postId+"&pageNum=1'>"+"“"+
                    item.postTitle+"”"+
                    "</a>"+
                    item.commentFloor+"楼  "+
                    "回复："+
                    "</td>"+
                    "<td style='float: right;'>"+
                    item.createTime+
                    "</td>"+
                    "</tr>"+
                    "<tr class='tagleft'>"+
                    "<td>"+"“"+
                    item.commentContent.replace(/<[^>]+>/g,"")+"”"+
                    "</td>"+

                    "</tr>"+

                    "</tbody>"+
                    "</table>"
                );
            })

            $(".pagenumber ul").empty();

            $(".pagenumber ul").append(
                "<li onclick='getMyPostComment(1)' style='width: 50px' >" + "首页" + "</li>"
            )

            var toa = data.toaPage;
            var cur = data.currPage;


            //标签在中间
            if(toa<5)
            {
                for(var i = 1; i<=toa;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getMyPostComment("+i+")' style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getMyPostComment("+i+")' >" + i + "</li>"
                        )
                }
            }else if (toa >5&&cur>3&&(cur+2)<toa)
            {
                for(var i = cur-2; i<=cur+2;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getMyPostComment("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getMyPostComment("+i+")' >" + i + "</li>"
                        )
                }
            }else if(toa>5&&cur<=3)
            {
                for (var i = 1;i<=5;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getMyPostComment("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getMyPostComment("+i+")' >" + i + "</li>"
                        )
                }
            }else if((cur+2)>=toa)
            {
                for (var i =toa-4;i<=toa;i++)
                {
                    if (i == cur)
                        $(".pagenumber ul").append(
                            "<li onclick='getMyPostComment("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li>"
                        )
                    else
                        $(".pagenumber ul").append(
                            "<li onclick='getMyPostComment("+i+")' >" + i + "</li>"
                        )
                }
            }


            $(".pagenumber ul").append(
                "<li onclick='getMyPostComment("+toa+")'  style='width: 50px' >" + "尾页" + "</li>"
            )

        }


    })

}

function delCollect() {
    var arr = new Array();
    var str = document.getElementsByName("delcheck");

    str.forEach(function (input,index) {
        if (input.checked){
            arr.push(input.value);
        }

    })
    for (var i = 0;i<arr.length;i++){
        var dk = document.getElementById(arr[i]);
        console.log(dk);
        $(dk).empty();
    }
    console.log(arr);

    $.ajax({
        url:"/collect/delCollection",
        traditional: true,
        data: {
            "postId":arr
        },
        type:"post",
        success:function (data) {

        }
    })
}


function getQueryVariable(variable) {

    var query = window.location.search.substring(1);
    var vars = query.split("&");

    for (var i =0;i<vars.length;i++)
    {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }

    return(false);

}