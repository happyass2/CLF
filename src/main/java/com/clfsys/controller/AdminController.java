package com.clfsys.controller;

import com.clfsys.pojo.Admin;
import com.clfsys.pojo.Moderator;
import com.clfsys.service.impl.AdminServiceimpl;
import com.clfsys.utils.md5Util.MD5Util;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author cdy
 * @date 2021/2/4 20:06
 * 管理页面的功能
 * 版主、管理员的登录与检查登录
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    AdminServiceimpl adminServiceimpl;

//    管理员身份登录
    @RequestMapping(value = "/loginAdmin",method = RequestMethod.POST)
    @ResponseBody
    public int loginAdmin(String adminName,String adminPassword,HttpSession httpSession)
    {

        Admin admin = adminServiceimpl.loginAdmin(adminName,adminPassword);

        if (admin!=null)
        {
            httpSession.setAttribute("admin",admin);
            httpSession.setAttribute("user",admin);

            delvoce(httpSession,"user");
            delvoce(httpSession,"admin");

        }else
        {
            return 201;
        }
        return 200;
    }


//版主登录
    @RequestMapping(value = "/loginModerator",method = RequestMethod.POST)
    @ResponseBody
    public int loginModerator(String userName, String userPassword, HttpSession httpSession){


        List<Moderator> moderators = adminServiceimpl.loginModerator(userName, MD5Util.md5(userPassword));

        if (moderators!=null)
        {
            httpSession.setAttribute("moderator",moderators.get(0));
            httpSession.setAttribute("user",moderators.get(0));

            delvoce(httpSession,"user");
            delvoce(httpSession,"moderator");


        }
        else
        {
            return 201;
        }

        return 200;

    }


    @RequestMapping(value = "/checkLogin",method = RequestMethod.POST)
    @ResponseBody
    public int checkLogin(HttpSession httpSession){
        if (httpSession.getAttribute("admin")!=null){
            return 200;
        }
        return 201;

    }




    @RequestMapping(value = "/checkLoginModer",method = RequestMethod.POST)
    @ResponseBody
    public int checkLoginModer(HttpSession httpSession){
        if (httpSession.getAttribute("moderator")!=null){
            return 200;
        }
        return 201;

    }

    //24小时后删除session
    public void delvoce(final HttpSession httpSession,final String sessionname)
    {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                httpSession.removeAttribute(sessionname);
                timer.cancel();
            }
        },24 * 60 * 1000);

    }

}
