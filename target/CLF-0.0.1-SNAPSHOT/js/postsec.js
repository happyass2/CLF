$(function () {

    var sec = getQueryVariable("sectionId");
    $.ajax({
        url:'/section/showSection',
        type:'GET',
        dataType:'json',
        success:function (data) {
            // href='post.jsp?sectionId="+item.sectionId+"&themeId=0&pageNum=1'
            //清空数据
            $("#sectionTag").empty();
            // onclick='pagechange(1,"+item.sectionId+",0)'
            //返回的数据用data获取
            $.each(data, function (i, item) {

                if (sec == item.sectionId)
                {
                    $("#sectionTag").append(
                        "<a href='post.jsp?sectionId="+item.sectionId+"&themeId=0&pageNum=1'><li style='background: #3c8dbc;color: #eeeeee'>"+item.sectionName+"</li></a>"
                    )
                }else{
                    $("#sectionTag").append(
                        "<a href='post.jsp?sectionId="+item.sectionId+"&themeId=0&pageNum=1'><li>"+item.sectionName+"</li></a>"
                    )
                }

            })

        }


    })


})

function searchPost() {
    var str = $("#searchb").val();
    window.location.href="search_list.jsp?searchstr="+str+"&pageNum=1";
}

