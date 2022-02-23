function login() {
    var userNamed = $('#login_id').val();
    var userPassword = $('#login_pass').val();
    $.ajax({
        url:'/user/login',
        type:'POST',
        dataType:'json',
        data:{
            "userName":userNamed,
            "userPassword":userPassword
        },success:function (data) {

            if (data != null){
                $("#logintitle").empty();
                $("#logintitle").append(
                    <!-- 头像 -->
                    "<div id='user_icon' class='flo_l' value="+data.userId+">" +
                    "<img width='40px' height='40px' src="+data.userIcon+" alt=''>" +
                    "</div>" +
                    <!-- 用户名 -->
                    "<a href='user_info.jsp?userId="+data.userId+"'>"+
                    "<div id='user_id' class='flo_l' value="+data.userId+">" +
                    data.userName + "</div>"+
                    "</a>"+
                    "<a href='user_post.jsp?userId="+data.userId +"'>"+
                    "<div id='user_about' class='flo_l'>"
                    +"我的"+
                    "</div>" +"</a>"+
                    <!-- 用户相关帖子提醒 -->
                    "<div  class='flo_l'>" +
                    "消息" +"<div id='user_message'  style='position: absolute;'>"+"</div>"+
                    "</div>" +
                    "<button id='login_out_btn' onclick='loginout()' type='button' class='flo_l'>" + "注销" +"</button>"
                )
                getUnkonwMessage();
            }
        }

    })






}

$(function () {

    var url = "/user/usersession";

    $.post(url,function (data) {
        $("#logintitle").empty();
        if (data != ""){
            $("#logintitle").append(
                <!-- 头像 -->

                "<div id='user_icon' class='flo_l'>" +

                 "<img width='40px' height='40px' src="+data.userIcon+" alt=''>" +

                "</div>" +
                <!-- 用户名 -->
                "<a href='user_info.jsp?userId="+data.userId+"'>"+
                "<div id='user_id' class='flo_l' value="+data.userId+">" +
                data.userName + "</div>"+
                "</a>"+
                "<a  href='user_post.jsp?userId="+data.userId +"'>"+
                "<div id='user_about' class='flo_l'>"
                +"我的"+
                "</div>" +"</a>"+
                <!-- 用户相关帖子提醒 -->
                "<div  class='flo_l'>" +
                "消息" +"<div id='user_message' style='position: absolute;'>"+"</div>"+
                "</div>" +
                "<button id='login_out_btn' onclick='loginout()' type='button' class='flo_l'>" + "注销" +"</button>"
            )
            getUnkonwMessage();
        }else{
            $("#logintitle").append(
            <!-- 登录表单 -->
            "<div class='login_form'>" +
                "<form action='' method='post' accept-charset='utf-8'>"+
                "<input  id='login_id' type='text' name='user_name' placeholder='账号'>"+
                "<input  id='login_pass' type='password' name='user_password' placeholder="+"密码"+">"+
                "<button id='login_btn' type='button' onclick='login()'>"+"登录"+"</button>" +
                "</form>"+
                "</div>"+

                <!-- 注册，注销 -->
                "<div class='header_reg_lout'>"+
                "<div class='reg'>"+

                "</div>" +
                "<div class='login_out'>"+
                "<button id='reg_btn' onclick='regpage()' type='button'>"+"注册"+"</button>" +
                "<button id='login_out_btn' type='button' onclick='findpass()'>"+"找回密码"+"</button>"+
               " </div>" +

                "</div>"
            )

        }
    })


})

function loginout() {


    var url = "/user/loginout";
    $.get(url,function (data) {
        console.log("exit");
    })


}

function regpage() {
    window.location.href="registerpage.jsp"
}



function getUnkonwMessage() {

    $.ajax({
        url:"/message/getUnKnowMessage",
        type:"post",
        success:function (data) {
            if (data==0)
                return;
            $("#user_message").empty();

            $("#user_message").append(
                "<span style=' position:absolute;display: block;border-radius:50%;color: white; background: red; top: -25px; right: -50px; '>"+
                data+
                "</span>"
            )



        }
    })
}

function findpass() {

    document.getElementById("find_pass_dialog").style.display="block";

}

function cancelPass() {
    document.getElementById("find_pass_dialog").style.display="none";
}

function changePass() {
    var name = $("#userName").val();
    var mail = $("#userEmail").val();
    console.log(name+"|"+mail)
    var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
    if (name==null&&mail==null&&name==""&&mail=="") {
        alert("信息不能为空!");
        return;
    }

    if(!reg.test(mail)){
        alert("邮箱错误!");
        return false;
    }

    $.ajax({
        url:"/user/checkUser",
        type:"post",
        data:{
            "userName":name,
            "userMail":mail
        },success:function (data) {
            if (data == 200)
            {
                alert("请速去邮箱验证！")
                document.getElementById("find_pass_dialog").style.display="none";
            }
        }
    })








}

