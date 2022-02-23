$(function () {
    var str = getUrlParam("searchstr");
    var cur = getQueryVariable("pageNum");

    $.ajax({
        url:"/post/search",
        data: {"searchStr": str,"pageNum":cur},
        type:"post",success:function (data) {

            if (data.obj ==null) {
                $("#searchContent").empty();
                $("#searchContent").text("无查询结果");
                return;
            }
            $("#searchContent").empty();
            $.each(data.obj, function (i, item){
                $("#searchContent").append(
                "<li>"+
                "<h3>"+
                "<a href='post_comment.jsp?postId="+item.postId+"&pageNum=1'>"+changeKeyRed(item.postTitle,str)+"</a>"+
                "</h3>"+
                "<p>"+item.commentNumber+" 个回复 - "+item.watchNumber+" 次查看"+"</p>"+
                "<p >"+changeKeyRed(item.postContent,str)+"</p>"+
                "<p>"+
                "<span>"+getMyDate2(item.createTime)+"</span>-"+
                "<span>"+
                "<a>"+item.userName+"</a>"+
                "</span>-"+
                "<span>"+
                "<a href=''>"+item.sectionName+"</a>"+
                "</span>"+
                "</p>"+
                "</li>"
                )

                $(".pagenumber ul").empty();

                $(".pagenumber ul").append(
                    "<a  href='search_list.jsp?searchstr="+str+"&pageNum=1'><li style='width: 50px' >" + "首页" + "</li></a>"
                )


                var toa = data.toaPage;


                //标签在中间
                if(toa<5)
                {
                    for(var i = 1; i<=toa;i++)
                    {
                        if (i == cur)
                            $(".pagenumber ul").append(
                                "<a  href='search_list.jsp?searchstr="+str+"&pageNum="+i+"'><li style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li></a>"
                            )
                        else
                            $(".pagenumber ul").append(
                                "<a  href='search_list.jsp?searchstr="+str+"&pageNum="+i+"'><li >" + i + "</li></a>"
                            )
                    }
                }else if (toa >5&&cur>3&&(cur+2)<toa)
                {
                    for(var i = cur-2; i<=cur+2;i++)
                    {
                        if (i == cur)
                            $(".pagenumber ul").append(
                                "<a  href='search_list.jsp?searchstr="+str+"&pageNum="+i+"'><li  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li></a>"
                            )
                        else
                            $(".pagenumber ul").append(
                                "<a  href='search_list.jsp?searchstr="+str+"&pageNum="+i+"'><li >" + i + "</li></a>"
                            )
                    }
                }else if(toa>5&&cur<=3)
                {
                    for (var i = 1;i<=5;i++)
                    {
                        if (i == cur)
                            $(".pagenumber ul").append(
                                "<a  href='search_list.jsp?searchstr="+str+"&pageNum="+i+"'><li  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li></a>"
                            )
                        else
                            $(".pagenumber ul").append(
                                "<a  href='search_list.jsp?searchstr="+str+"&pageNum="+i+"'><li >" + i + "</li></a>"
                            )
                    }
                }else if((cur+2)>=toa)
                {
                    for (var i =toa-4;i<=toa;i++)
                    {
                        if (i == cur)
                            $(".pagenumber ul").append(
                                "<a  href='search_list.jsp?searchstr="+str+"&pageNum="+i+"'><li  style='background: #3c8dbc;color: #eeeeee;' >" + i + "</li></a>"
                            )
                        else
                            $(".pagenumber ul").append(
                                "<a  href='search_list.jsp?searchstr="+str+"&pageNum="+i+"'><li >" + i + "</li></a>"
                            )
                    }
                }


                $(".pagenumber ul").append(
                    "<a  href='search_list.jsp?searchstr="+str+"&pageNum="+toa+"'><li  style='width: 50px' >" + "尾页" + "</li></a>"
                )
            })
        }
    })
    changeKeyRed(str);
})

function getMyDate2(str) {
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



function changeKeyRed(str,keyWord) {
    if(str!=null&&keyWord!=null){
        var substr="/"+keyWord+"/g";
        var replaceStr=str.toString().replace(eval(substr),"<span style='color:red;font-weight:bold'>"+keyWord+"</span>")
        return replaceStr;
    }
    else {
        return str;
    }
}



function getUrlParam(name) {
    // 获取参数
    var url = window.location.search;
    // 正则筛选地址栏
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    // 匹配目标参数
    var result = url.substr(1).match(reg);
    //返回参数值
    return result ? decodeURIComponent(result[2]) : null;
}

