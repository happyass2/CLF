$(function () {

    $.ajax({
        url:"/user/setChangePasswordPage",
        type:"post",
        success:function (data) {
            console.log(data)
            var inp = document.getElementById("username");
            inp.value = data;
        }
    })



    var flagpass = false;
    var flagconp = false;

    var p = document.getElementById("userpassword");
    var c = document.getElementById("cuserpassword");

    //密码是否合规
    $('#userpassword').on('input',function () {
        var str = $(this).val();

        if (str == null || str.length <8) {
            $('#password-msg').text('more than 8').css({'color': '#ff0000'});
            p.style.borderColor="#f00";
            flagpass = false;
            return;
        }
        if(str.length>20){
            str = str.substr(0,20);
            $(this).val(str);
        }
        var reg1 =/^(?![^a-zA-Z]+$)(?!\D+$)/;
        if (!reg1.test(str)) {
            $('#password-msg').text('number and charater').css({'color': '#ff0000'});
            p.style.borderColor="#f00";
            flagpass = false;
            return;

        }else{
            $('#password-msg').text('can use').css({'color': '#0f0'});
            p.style.borderColor="#0f0";
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
            $('#password-msg').text('password null').css({'color': '#ff0000'});
            c.style.borderColor="#f00";
            flagconp = false;
            return;

        }else if(str1 != str2)
        {
            $('#conpassword-msg').text('password error').css({'color': '#ff0000'});
            c.style.borderColor="#f00";
            flagconp = false;
            return;

        }else{
            $('#conpassword-msg').text('√').css({'color': '#0f0'});
            c.style.borderColor="#0f0";
            flagconp = true;
            return ;
        }
    });

    $("#submit-btn").click(function () {
        if (!(flagpass)&&!(flagconp))
        {
            alert("mima error！")
            return;
        }
        var userId = getQueryVariable("userId");
        var userPass=$("#userpassword").val();
        $.ajax({
            url:"/user/updPassword",
            data:{
                "userId":userId,
                "userPass":userPass
            },type:"post",
            success:function (data) {
                    if(data==200)
                    {
                        alert("修改密码成功！确认返回主页登录");
                        window.location = "localhost:8080/"
                    }

            }
        })
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