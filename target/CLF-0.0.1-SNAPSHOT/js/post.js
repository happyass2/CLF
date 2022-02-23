
function pagechange(arg,arg2,arg3) {

    var pageNum = arg;
    var section = arg2;
    var theme = arg3;
    var page = document.getElementsByName("pagecode");

    $.ajax({
        type: "GET",
        url:"/post/showpost?sectionId="+section+"&themeId="+theme+"&pageNum="+pageNum,
        success:function (data) {



            //清空数据
            $("#postshowbysnt").empty();
            //返回的数据用data获取
            $.each(data.obj, function (i, item) {
                $("#postshowbysnt").append(
                    "<table class='postlist2'>" +
                    "<tbody class='detaillist'>" +
                    "<tr>" +
                    "<td class='posticon'>" +
                    "<img width='30px' height='30px' src="+item.img+" alt=''>" +
                    "</td>" +
                    "<td class='poasttag'>" +
                    "[<a  href='post.jsp?sectionId="+section+"&themeId="+item.themeId+"&pageNum=1'>"+item.theme+"</a>]" +
                    "</td>" +
                    "<th class='titleshow'>" +
                    "<a href='post_comment.jsp?postId="+item.postId+"&pageNum=1'>" + item.postTitle + "</a>" +
                    "</th>" +

                    "<td class='titletag2'>" +
                    "<span>" + item.watchnumber + "</span>" +
                    "<span>" + item.commentnumber + "</span>" +
                    "</td>" +
                    "<td class='titletag2'>" +
                    "<span>" + item.username + "</span>" +
                    "<span>" + item.createtime + "</span>" +
                    "</td>" +
                    "<td class='titletag2'>" +
                    "<span>" + item.lasttime + "</span>" +
                    "</td>" +
                    "</tr>" +
                    "</tbody>"
                )
            })

            //页码
            $(page).empty();

            $(page).append(
                "<a  href='post.jsp?sectionId="+section+"&themeId="+theme+"&pageNum=1'><li onclick='pagechange("+i+")' style='width: 50px' >" + "首页" + "</li></a>"
            )

            var cur = data.curpage;
            var toa = data.totalpage;


            //标签在中间
            if(toa<5)
            {
                for(var i = 1; i<=toa;i++)
                {
                    if (i == data.curpage)
                        $(page).append(
                            "<a  href='post.jsp?sectionId="+section+"&themeId="+theme+"&pageNum="+i+"'><li onclick='pagechange("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li></a>"
                        )
                    else
                        $(page).append(
                            "<a  href='post.jsp?sectionId="+section+"&themeId="+theme+"&pageNum="+i+"'><li onclick='pagechange("+i+")'>" + i + "</li></a>"
                        )
                }
            }else if (toa >5&&cur>3&&(cur+2)<toa)
            {
                for(var i = cur-2; i<=cur+2;i++)
                {
                    if (i == data.curpage)
                        $(page).append(
                            "<a  href='post.jsp?sectionId="+section+"&themeId="+theme+"&pageNum="+i+"'><li onclick='pagechange("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li></a>"
                        )
                    else
                        $(page).append(
                            "<a  href='post.jsp?sectionId="+section+"&themeId="+theme+"&pageNum="+i+"'><li onclick='pagechange("+i+")'>" + i + "</li></a>"
                        )
                }
            }else if(toa>5&&cur<=3)
            {
                for (var i = 1;i<=5;i++)
                {
                    if (i == data.curpage)
                        $(page).append(
                            "<a  href='post.jsp?sectionId="+section+"&themeId="+theme+"&pageNum="+i+"'><li onclick='pagechange("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li></a>"
                        )
                    else
                        $(page).append(
                            "<a  href='post.jsp?sectionId="+section+"&themeId="+theme+"&pageNum="+i+"'><li onclick='pagechange("+i+")'>" + i + "</li></a>"
                        )
                }
            }else if((cur+2)>=toa)
            {
                for (var i =toa-4;i<=toa;i++)
                {
                    if (i == data.curpage)
                        $(page).append(
                            "<a  href='post.jsp?sectionId="+section+"&themeId="+theme+"&pageNum="+i+"'><li onclick='pagechange("+i+")'  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li></a>"
                        )
                    else
                        $(page).append(
                            "<a  href='post.jsp?sectionId="+section+"&themeId="+theme+"&pageNum="+i+"'><li onclick='pagechange("+i+")'>" + i + "</li></a>"
                        )
                }
            }


            $(page).append(
                "<a  href='post.jsp?sectionId="+section+"&themeId="+theme+"&pageNum="+data.totalpage+"'><li onclick='pagechange("+data.totalpage+")' style='width: 50px' >" + "尾页" + "</li></a>"
            )
            return;



        },
        error:function () {
            console.log("错误");
        }


    })

}


$(function () {

    var pagenu = getQueryVariable("pageNum");
    var sec = getQueryVariable("sectionId");
    var the = getQueryVariable("themeId");


    if (sec==false||sec == 0)
        return;
    pagechange(pagenu,sec,the);

    writepostoption(sec,the);

    writeSectionDescr(sec);

})


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

function writeSectionDescr(arg) {
    $.ajax({
        url:'/section/writeSectionDescr',
        type:'post',
        data: {
            'sectionId':arg
        },success:function (data) {
            console.log(data);
            $("#sectionRule").empty();
            $("#sectionRule").append("<div>"+data.obj+"</div>");
        }
    })

}


function writepostoption(arg,arg2) {


    var sec = arg;
    var the = arg2;
    $.ajax({
        url:"/sectiontheme/showthemebysec",
        type: "POST",
        dataType: "json",
        data: {
            "secId":sec
        },success:function (data) {

            $("#secsel").empty();
            $("#themetag").empty();
            if (the == 0)
            {
                $("#themetag").append(
                    "<a  href='post.jsp?sectionId="+sec+"&themeId=0&pageNum=1'><li style='background: #3c8dbc;color: #eeeeee'>"+"全部"+"</li></a>"
                )
            }else
            {
                $("#themetag").append(
                    "<a  href='post.jsp?sectionId="+sec+"&themeId=0&pageNum=1'><li>"+"全部"+"</li></a>"
                )
            }
            $("#secsel").append(
                " <option value='0'>"+"-请选择-"+"</option>"
            )

            $.each(data.obj, function (i, item) {

                $("#secsel").append(

                    " <option value="+item.themeId+">"+item.themeName+"</option>"
                )
                if (the == item.themeId)
                {
                    $("#themetag").append(
                        "<a  href='post.jsp?sectionId="+sec+"&themeId="+item.themeId+"&pageNum=1'><li style='background: #3c8dbc;color: #eeeeee'>"+item.themeName+"</li></a>"
                    )
                }else
                {
                    $("#themetag").append(
                        "<a  href='post.jsp?sectionId="+sec+"&themeId="+item.themeId+"&pageNum=1'><li>"+item.themeName+"</li></a>"
                    )
                }

            })
        }
    })
}





