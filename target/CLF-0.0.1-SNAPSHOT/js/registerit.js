
//发送按钮
function sendMail(){

    var usermail = $('#usermail').val();
    var str = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
    if(!str.test(usermail)){
        alert('erroe email');
    }
    $.ajax({
        type: 'POST',
        url: '/user/sendmail',
        dataType:'json',
        data: {
            'usermail': $('#usermail').val()
        },
        success: function (data) {
            if (data.code== 1) {
                $('#mail-msg').text('验证码已经发送至邮箱').css({'color': '#0f0'});
                cutdoun();  //间隔时间
                flagmail = true;

                return;

            } else {
                $('#mail-msg').text('验证码发送失败').css({'color': '#f00'});
                return;

            }

        }
    })


}

//按钮倒计时
function cutdoun(){
    var btn = $('#send');
    var count = 60;
    var loop = function () {
        if(count < 0){
            btn.val('重新获取').attr("disabled",false);
            return;
        }
        btn.val(count+'s后可重新发送');
        count--;
        setTimeout(loop,1000);
    }
    btn.attr("disabled",true);
    loop();
}



$(function () {

    var flagname = false;
    var flagpass = false;
    var flagconp = false;
    var flagmail = false;


    // 用户名验证-看是否被占用
    $('#username').on('input', function () {
        var username = $(this).val();
        if ($(this).val() == "") {
            $('#username-msg').text('用户名为空').css({'color': '#ff0000'});
            flagname = false;
            return;
        }
        if($(this).val().length > 10){
            $('#username-msg').text('用户名过长').css({'color': '#ff0000'});
            flagname = false;
            return;
        }
        $.ajax({
            type: 'POST',
            url: '/user/checkname',
            dataType:'json',
            data: {
                'username': $('#username').val()
            },
            success: function (data) {
                if (data.code== 0) {
                    $('#username-msg').text('√').css({'color': '#0f0'});
                    flagname = true;
                    return;

                } else {
                    $('#username-msg').text('已被使用').css({'color': '#f00'});
                    flagname = false;
                    return;

                }
            }
        })


    });

    //密码是否合规
    $('#userpassword').on('input',function () {
        var str = $(this).val();

        if (str == null || str.length <8) {
            $('#password-msg').text('密码需要大于8位字符').css({'color': '#ff0000'});
            flagpass = false;
            return;
        }
        if(str.length>20){
            $('#password-msg').text('密码需要小于20位字符').css({'color': '#ff0000'});
            flagpass = false;
            return;
        }
        var reg1 =/^(?![^a-zA-Z]+$)(?!\D+$)/;
        if (!reg1.test(str)) {
            $('#password-msg').text('密码过于单调').css({'color': '#ff0000'});
            flagpass = false;
            return;

        }else{
            $('#password-msg').text('√').css({'color': '#0f0'});
            flagpass = true;
            return;
        }

    });
    //验证密码
    $('#cuserpassword').on('input',function () {
        var str1 = $('#userpassword').val();
        let str2 = $(this).val();


        if(str2 == null || str1 == null)
        {
            $('#password-msg').text('密码为空').css({'color': '#ff0000'});
            flagconp = false;
            return;

        }else if(str1 != str2)
        {
            $('#conpassword-msg').text('两次密码不一致').css({'color': '#ff0000'});
            flagconp = false;
            return;

        }else{
            $('#conpassword-msg').text('√').css({'color': '#0f0'});
            flagconp = true;
            return ;
        }
    });

    //验证邮箱
    $('#usermail').on('input',function () {

        var mail = $('#usermail').val();
        var pattern1 = /^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,4}$/;

            $.ajax({
                url:"/user/checkmail",
                dataType:"json",
                type:"POST",
                data: {"mail":mail},success:function (data) {

                    if(data == 102){
                        $('#mail-msg').text('邮箱已注册').css({'color':'#ff0000'});
                        flagmail = false;
                        return;
                    }
                    if(data ==101)
                    {
                        $('#mail-msg').text('邮箱格式错误').css({'color':'#ff0000'});
                        flagmail = false;
                        return;
                    }
                    if(data ==100)
                    {
                        $('#mail-msg').text('√').css({'color':'#0f0'});
                        flagmail = true;
                        return;
                    }

                }
            })

    })

  


    //提交验证
    $('#submit-btn').click(function () {
        var usersex = $("input[name='usergender']:checked").val();
        if(usersex == undefined)
        {
            alert("信息不全！");
            return false;
        }
        username = $('#username').val();
        userpass = $('#userpassword').val();
        usergender = usersex;
        cuserpassword = $('#cuserpassword').val();
        usermail = $('#usermail').val();
        activecode = $('#activecode').val();
        var user1 = {};
        user1.userName = username;
        user1.userPassword = userpass;
        user1.userGender = usergender;
        user1.userMail = usermail;
        user1.activecode = activecode;
        var user2 = JSON.stringify(user1);
        console.log(user2);
        if(!flagname||!flagpass||!flagconp||!flagmail)
        {
            alert("信息填写有错误！");
            console.log(flagconp+"|"+flagpass+"|"+flagmail+"|"+flagname);
            return false;
        }
        else
        {
            $.ajax({
                url:"/user/register",
                type:"POST",
                dataType:"json",
                data: user2,
                contentType:"application/json;charset=UTF-8",
                success:function (data) {
                    switch (data) {
                        case 200:alert("注册成功！"); window.location="index.jsp";break;
                        case 201:alert("激活码错误！");break;
                        case 202:alert("注册失败！");break;
                        default:break;
                    }
                }
            })
        }
    })







})