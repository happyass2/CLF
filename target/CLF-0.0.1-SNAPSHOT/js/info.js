$(function () {
    var userId = getQueryVariable("userId");
    $.ajax({
        url:"user/getUserinfoById",
        data:{
            "userId":userId
        },type:"get",success:function (data) {
            var item = data.obj;

            $("#userinfodiv").empty();
            if (data.code == 200){
                $("#userinfodiv").append(
                    "<div class='user_icon'>"+
                    "<div class='inner_icon'>"+
                    "<img src="+item.userIcon+">"+
                    "</div>"+
                    "<button type='button'>"+"修改头像"+"</button>"+
                    "<button type='button'>"+"提交"+"</button>"+
                    "</div>"+
                    "<div class='info_div'>"+
                    "<ul class='info_right'>"+
                    "<li>"+
                    "<h4>"+item.userName+"（"+item.userId+"）"+
                    "</h4>"+
                    "</li>"+

                    "</ul>"+


                    "<ul class='info_right'>"+
                    "<li>"+"性别:"+item.userGender+"</li>"+
                    "<li>"+"邮箱已认证"+"</li>"+
                    "</ul>"+
                    "<ul class='info_right'>"+
                    "<li>"+"发帖数:"+item.userPostNumber+"</li>"+
                    "<li>"+"回帖数:"+data.commentNum+"</li>"+
                    " <li>"+"活跃度:"+item.userAct+"</li>"+
                    "</ul>"+
                    "<ul class='info_right'>"+
                    " <li>"+"注册时间:"+getMyDate(item.registerTime)+"</li>"+
                    " <li>"+"上次活跃时间:"+getMyDate(item.lastLoginTime)+"</li>"+
                    "</ul>"+
                    "<ul class='info_right'>"+
                    "<li>"+"上次登录IP："+item.lastLoginIp+"</li>"+
                    "</ul>"+
                    "</div>"
                )
            }else
            {
                $("#userinfodiv").append(
                    "<div class='user_icon'>"+
                    "<div class='inner_icon'>"+
                    "<img src="+item.userIcon+">"+
                    "</div>"+
                    "</div>"+
                    "<div class='info_div'>"+
                    "<ul class='info_right'>"+
                    "<li>"+
                    "<h4>"+item.userName+"（"+item.userId+"）"+
                    "</h4>"+
                    "</li>"+

                    "</ul>"+


                    "<ul class='info_right'>"+
                    "<li>"+"性别:"+getSex(item.userGender)+"</li>"+
                    "<li>"+"邮箱已认证"+"</li>"+
                    "</ul>"+
                    "<ul class='info_right'>"+
                    "<li>"+"发帖数:"+item.userPostNumber+"</li>"+
                    "<li>"+"回帖数:"+data.commentNum+"</li>"+
                    " <li>"+"活跃度:"+item.userAct+"</li>"+
                    "</ul>"+
                    "<ul class='info_right'>"+
                    " <li>"+"注册时间:"+getMyDate(item.registerTime)+"</li>"+
                    " <li>"+"上次活跃时间:"+getMyDate(item.lastLoginTime)+"</li>"+
                    "</ul>"+
                    "<ul class='info_right'>"+
                    "</ul>"+
                    "</div>"
                )
            }

        }
    })

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

function getSex(arg) {
    if (arg ==0)
        return "女";
    else
        return "男";

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