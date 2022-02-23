package com.clfsys.controller;


import com.clfsys.pojo.UserInfo;
import com.clfsys.pojo.page.HotUser;
import com.clfsys.pojo.page.SearchForm;
import com.clfsys.pojo.page.UserReg;
import com.clfsys.pojo.page.UserShowAdming;
import com.clfsys.service.impl.PostCommentSericeimpl;
import com.clfsys.service.impl.UserInfoServiceImpl;
import com.clfsys.utils.email.SendMail;
import com.clfsys.utils.email.verifycode;
import com.clfsys.utils.md5Util.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.Inet4Address;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cdy
 * @date 2021/2/1 19:19
 * 用户相关功能
 * 注册,登录,展示登录条,注销
 * 获取热门用户,检查是否登录
 * 找回密码,获取用户信息
 * 管理页面:展示,删除,修改,新增用户
 *
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {
    private final String USER_INFO="USER_INFO";
    private static final Logger log = Logger.getLogger(UserInfoController.class);
    @Autowired
    UserInfoServiceImpl userInfoService;
//    注册
    @RequestMapping(value = "/register",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public int register(@RequestBody UserReg userInfo,HttpSession httpSession)
    {

        String name = userInfo.getUserName();
        String pass = userInfo.getUserPassword();
        String mail = userInfo.getUserMail();
        String active = userInfo.getActivecode();
        int gender = userInfo.getUserGender();
        String vcodecorr = (String)httpSession.getAttribute("vcode");

        if(checkcode(httpSession,active,mail))
            System.out.println("验证码邮箱正确");
        else {
            System.out.println("验证码邮箱错误");
            return 201;
        }
        //注册账户
        try{
            UserInfo user = new UserInfo();
            user.setUserName(name);
            user.setUserPassword(MD5Util.md5(pass));
            user.setRegisterTime(new Date());
            user.setActiveCode(active);
            user.setUserMail(mail);
            user.setUserIcon("/img/t1.png");
            user.setLastLoginIp(null);
            user.setLastLoginTime(null);
            user.setUserPostNumber(0);
            user.setUserGender(gender);
            user.setUserAct(0);
            user.setCollectionNumber(0);
            userInfoService.register(user);
            return 200;

        }catch (Exception e){
            System.out.println("注册失败");
            return 202;
        }


    }

    //管理员添加注册用户
    @RequestMapping(value = "/register2",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public int register2(@RequestBody UserReg userInfo)
    {

        String name = userInfo.getUserName();
        String pass = userInfo.getUserPassword();
        String mail = userInfo.getUserMail();
        String active = userInfo.getActivecode();
        int gender = userInfo.getUserGender();

        int nameNum = userInfoService.checkUserName(name);

        if (nameNum != 0) return 201;

        int mailNum = userInfoService.checkUserMail(mail);

        if (mailNum != 0) return 202;


        //注册账户
        try{
            UserInfo user = new UserInfo();
            user.setUserName(name);
            user.setUserPassword(MD5Util.md5(pass));
            user.setRegisterTime(new Date());
            user.setActiveCode(active);
            user.setUserMail(mail);
            user.setUserIcon("icon");
            user.setLastLoginIp("192.168.2.15");
            user.setLastLoginTime(new Date());
            user.setUserPostNumber(0);
            user.setUserGender(gender);
            user.setUserAct(0);
            user.setCollectionNumber(0);
            userInfoService.register(user);
            return 200;
        }catch (Exception e){
            System.out.println("注册失败");
            return 203;
        }


    }

//     登录
    @RequestMapping(value ="/login",method = RequestMethod.POST)
    @ResponseBody
    public Map loginUserInfo(String userName,String userPassword, HttpSession httpSession)
    {

        userPassword = MD5Util.md5(userPassword);

        String tempPass = userInfoService.loginUserInfo(userName);
        Map<String,Object> map = new HashMap<>();
        if("".equals(tempPass)||tempPass.equals(null)) System.out.println("无此用户");
        else {
            UserInfo userInfo = userInfoService.selectByUserName(userName);
            if(userPassword.equals(tempPass)) System.out.println("登录成功");
            httpSession.setAttribute("user",userInfo);
            delvoce(httpSession,"user");

            map.put("userId",userInfo.getUserId());
            map.put("userName",userInfo.getUserName());
            map.put("userIcon",userInfo.getUserIcon());
        }

        return map;

    }


//检查邮箱是否被注册
    @RequestMapping(value="/checkmail",method = RequestMethod.POST)
    @ResponseBody
    public int checkmail(HttpServletRequest httpServletRequest)
    {

        String mail = httpServletRequest.getParameter("mail");


        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  //邮箱格式验证
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m= p.matcher(mail);
        if(!(m.matches())){
            return 101;//邮箱格式不正确
        }
        int tt = userInfoService.checkUserMail(mail);
        if(tt!=0)
        {
            return 102;//邮箱已被注册
        }

        return 100;
    }



//    检查用户名是否被注册
    @RequestMapping(value = "/checkname",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map checkUserName(HttpServletRequest request) {
        //查看id是否重复
        int t = userInfoService.checkUserName(request.getParameter("username"));
        Map<String, Object> map = new HashMap<>();
        if(t == 0)
            map.put("code",0);
        else
            map.put("code",1);
        return map;

    }
//    邮箱发送验证码
    @RequestMapping("/sendmail")
    @ResponseBody
    public Map sendMail(HttpServletRequest request, HttpSession httpSession){

        Map<String,Object> map = new HashMap<>();
        String mail = request.getParameter("usermail");



        String vcode = verifycode.generateVerifyCode(6);  //生成验证码保存到session
        //创建session
        httpSession.setAttribute("vcode",vcode);
        httpSession.setAttribute("vmail",mail);
        //定时删除session
        delemailsession(httpSession,"vcode");
        delemailsession(httpSession,"vmail");



        try {
            SendMail.sendEmail(mail,vcode);
            map.put("code",1); 
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",0);
        }

        return map;

    }

//验证邮箱验证码
    public boolean checkcode(HttpSession httpSession,String vcode,String usermail)
    {

        String vcodecorr = (String)httpSession.getAttribute("vcode");



        if(!(httpSession.getAttribute("vmail").equals(usermail)))//验证邮箱是否匹配
        {
            System.out.println("邮箱不匹配");
            return false;
        }
        if(vcode.equals(vcodecorr)) {
            System.out.println("邮箱验证成功");
            return true;
        }
        else {
            System.out.println("邮箱验证失败");
            return false;
        }
    }

    //定时24小时删除session
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
//定时1分钟删除session
    public void delemailsession(final HttpSession httpSession,final String sessionname){
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                httpSession.removeAttribute(sessionname);
                timer.cancel();
            }
        },1 * 60 * 1000);
    }


    //获取session内的用户信息返回给前端(主页用户条)
    @RequestMapping(value = "/usersession",method = RequestMethod.POST)
    @ResponseBody
    public Map getUserSession(HttpSession httpSession){

        Map map = new HashMap();

        UserInfo userInfo = new UserInfo();

        try{
             userInfo =  (UserInfo) httpSession.getAttribute("user");
             map.put("userId",userInfo.getUserId());
             map.put("userName",userInfo.getUserName());
             map.put("userIcon",userInfo.getUserIcon());
        }catch (Exception e){
            return null;
        }

        return map;
    }

    //注销，把user从session删除
    @RequestMapping(value = "/loginout",method = RequestMethod.GET)
    public void loginout(HttpSession httpSession){
        httpSession.removeAttribute("user");
    }

    //获取热门用户
    @RequestMapping(value = "gethotuser",method = RequestMethod.POST)
    @ResponseBody
    public List<HotUser> getHotUser(){
        List<HotUser> hotUsers = userInfoService.getHotUser();
        return hotUsers;
    }

//检查用户是否存在
    @RequestMapping(value = "/checkUser",method = RequestMethod.POST)
    @ResponseBody
    public int checkUserExist(String userName,String userMail,HttpSession httpSession){

        int code = 0;
        int userId = userInfoService.getUserByMailAndName(userName,userMail);
        if (userId!=0) {
            code = 200;
            SendMail.sendEmail(userMail,"localhost:8080/change_password.jsp?userId="+userId);
            httpSession.setAttribute("userName",userName);
            delvoce(httpSession,"userName");
        }


        return code;
    }

//为改变密码页面展示用户名
    @RequestMapping(value = "/setChangePasswordPage",method = RequestMethod.POST)
    @ResponseBody
    public String setChangePasswordPage(HttpSession httpSession)
    {
       String name = (String) httpSession.getAttribute("userName");
        if (name == null)
            return "null";
        return name;
    }
//更新密码
    @RequestMapping(value = "/updPassword",method = RequestMethod.POST)
    @ResponseBody
    public int updPassword(int userId,String userPass){

        userInfoService.updPassword(userId,userPass);
        return 200;
    }

    @Autowired
    PostCommentSericeimpl postCommentSericeimpl;
//获取用户信息
    @RequestMapping(value = "/getUserinfoById",method = RequestMethod.GET)
    @ResponseBody
    public Map getUserinfoById(int userId,HttpSession httpSession){
        Map<String,Object> map = new HashMap<>();
        if (((UserInfo) httpSession.getAttribute("user")).getUserId()==userId)
            map.put("code",200);
        else
            map.put("code",201);
        map.put("obj",userInfoService.getUserinfoById(userId));
        map.put("commentNum",postCommentSericeimpl.getUserCommentNum(userId));

        return map;
    }
//获取管理员页面用户
    @RequestMapping(value = "/getAllUser",method = RequestMethod.POST)
    @ResponseBody
    public Map getAllUser(int page,int limit){
        Map<String,Object> map = new HashMap<>();
        List<UserShowAdming> userInfos = userInfoService.getAllUser(page,limit);
        for (int i =0;i<userInfos.size();i++)
        {
            if (userInfos.get(i).getUserGender()==0)
                userInfos.get(i).setUserGender22("男");
            else
                userInfos.get(i).setUserGender22("女");
        }
        map.put("data",userInfos);
        map.put("count",userInfoService.selectUserNum());
        map.put("msg",null);
        map.put("code",0);
        return map;
    }
    //管理员删除
    @RequestMapping(value = "/delUser",method =RequestMethod.POST )
    @ResponseBody
    public void delUser(int[] userId){
        for (int i = 0; i<userId.length;i++)
        {
            userInfoService.delUser(userId[i]);
        }
    }
    //管理员修改
    @RequestMapping(value = "/updUser",method = RequestMethod.POST)
    @ResponseBody
    public int updUser(String userName, String userMail, int userId){

        if (userName==null||userMail==null||userId==0){
            return 201;
        }
        if (userInfoService.checkUserName(userName)!=0) {
            if (userInfoService.getUidByName(userName)!=userId) return 202;
        }

        if (userInfoService.checkUserMail(userMail)!=0) {
            if (userInfoService.getUidByMail(userMail)!=userId) return 203;
        }

        userInfoService.updUser(userName,userMail,userId);
        return 200;

    }

//    管理页面搜索用户
    @RequestMapping(value = "/searchUser",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public Map searchUser(@RequestBody SearchForm searchForm){



        Map<String,Object> map = new HashMap<>();
        String lastlogin = searchForm.getLoginTime2();
        String lastreg = searchForm.getRegTime2();

        if (lastlogin == ""||lastlogin == null)
        {
            searchForm.setLoginTime2("2070-12-31");
        }

        if (lastreg == ""||lastreg == null)
        {
            searchForm.setRegTime2("2070-12-31");
        }

        List<UserShowAdming> userShowAdmings = userInfoService.getSearchUser(searchForm);

        for (int i= 0 ;i<userShowAdmings.size();i++)
        {
            if (userShowAdmings.get(i).getUserGender()==0)
            {
                userShowAdmings.get(i).setUserGender22("男");
            }else{
                userShowAdmings.get(i).setUserGender22("女");
            }
        };
        map.put("data",userShowAdmings);
        map.put("count",userShowAdmings.size());
        map.put("msg",null);
        map.put("code",0);

        return map;
    }

}
