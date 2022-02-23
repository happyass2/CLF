$(function () {

    infoNumfun();
    showsectiondetail();
    showhotuserpage();
    showhotpost();
})


function infoNumfun() {

    var url = "/post/showpostandusernum";

    $.get(url,function (data) {

        $("#infoNum").empty()

        $("#infoNum").append(
            "<li>"+"今日："+data.postNumToday+"</li>" +
            "<span class='pipe'>"+"|"+"</span>" +
            "<li>"+"帖子："+data.postNum+"</li>" +
            "<span class='pipe'>"+"|"+"</span>" +
            "<li>"+"会员："+data.userNum+"</li>"
        )
    })


}

function showsectiondetail() {

    var url = "/section/showsectionde";

    $.post(url,function (data) {
        $("#plateul").empty();

        $.each(data, function (i, item) {

            $("#plateul").append(
                "<ul class='detailplate'>"+
                "<li class='plaimg'>"+
                "<img src="+item.sectionIcon+">"+
                "<div>"+
                "<a  href='post.jsp?sectionId="+item.sectionId+"&themeId=0&pageNum=1'><b>"+item.sectionName+"</b></a>"+
                "<span>"+item.sectionDescr+"</span>"+
                "</div>"+
                "</li>"+
                "<li class='plaudp'>"+
                "<a href='post_comment.jsp?postId="+item.postId+"&pageNum=1'>"+item.postTitle+"</a>"+
                "<span>"+item.createTime+"</span>"+
                "</li>"+
                "<li class='plablog'>"+
                "<span>"+item.postNum+"</span>"+
                "</li>"+

                "</ul>"
            )


        })
    })

}

function showhotuserpage() {
    var url = '/user/gethotuser'
    $.post(url,function (data) {
        $("#hotuser").empty();
        $.each(data, function (i, item) {
            $("#hotuser").append(

            "<li value="+item.userId+"><img width='30' height='30' class='title_user_logo' src="+item.userIcon+" />"+item.userName+"</li>"

            )

        })
    })
}

function showhotpost() {
    var url = '/post/gethotpost'
    $.get(url, function (data) {
        $("#hotpost").empty();
        $.each(data, function (i, item) {
            $("#hotpost").append(
                "<li>" +
                "<a  href='post.jsp?sectionId="+item.postSectionId+"&themeId="+item.postThemeId+"&pageNum=1'><span class='brackets'>" + "[" + item.themeName + "]" + "</span></a>" +
                "<a class='blog_title' href='post_comment.jsp?postId="+item.postId+"&pageNum=1'>" + item.postTitle + "</a>" +
                "<img class='title_logo' src=''>" +
                "<span class='title_date'>" + getDate(item.createTime) + "</span>" +
                "</li>"
            )

        })

    })
}

function getDate(str) {
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay);//最后拼接时间  
    return oTime;
}

function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}
