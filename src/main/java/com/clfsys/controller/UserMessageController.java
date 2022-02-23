package com.clfsys.controller;

import com.clfsys.pojo.UserInfo;
import com.clfsys.pojo.UserMessage;
import com.clfsys.pojo.page.MessagePage;
import com.clfsys.service.impl.UserInfoServiceImpl;
import com.clfsys.service.impl.UserMessageServiceimpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author cdy
 * @date 2021/2/4 19:53
 * 获取用户的信息,获取未读信息
 * 删除自己信息,已读信息
 * 对帖子进行举报,
 * 管理页面:展示举报信息,已读,删除,搜索信息
 */
@RequestMapping("/message")
@Controller
public class UserMessageController {
    @Autowired
    UserMessageServiceimpl userMessageServiceimpl;

    @Autowired
    UserInfoServiceImpl userInfoService;
//获取用户的信息
    @RequestMapping(value = "/getUserMessage" ,method = RequestMethod.POST)
    @ResponseBody
    public Map getMessageByReciveId(@RequestParam(defaultValue = "0")int userId,int pageNum,@RequestParam(defaultValue = "10")int pageSize, HttpSession httpSession) {

        if (userId == 0)
           userId =  ((UserInfo)httpSession.getAttribute("user")).getUserId();

        Map<String,Object> map = new HashMap<>();
        List<MessagePage> messagePages = userMessageServiceimpl.getMessageByReciveId(userId,(pageNum - 1) * pageSize,pageSize);
        int totalpage = userMessageServiceimpl.getAllMessageNum(userId);
        if(totalpage%pageSize!=0) totalpage = totalpage/pageSize +1;
        else totalpage = totalpage/pageSize;



        map.put("obj",messagePages);


        map.put("toaPage",totalpage);
        map.put("curPage",pageNum);

        return map;

    }
//获取未读信息条数
    @RequestMapping(value = "/getUnKnowMessage",method = RequestMethod.POST)
    @ResponseBody
    public int getUnKnowMessage(HttpSession httpSession){
        int userId =  ((UserInfo)httpSession.getAttribute("user")).getUserId();

        return userMessageServiceimpl.getMessageNum(userId);
    }
//删除信息
    @RequestMapping(value = "/delMessage",method = RequestMethod.POST)
    @ResponseBody
    public int delMessage(int messageId){
       userMessageServiceimpl.delMessage(messageId);

        return 300;
    }
//把未读消息处理成已读
    @RequestMapping(value = "/readMessage",method = RequestMethod.POST)
    @ResponseBody
    public int readMessage(int messageId){
       userMessageServiceimpl.readMessage(messageId);


        return 300;
    }
//举报信息
    @RequestMapping("/reportPost")
    @ResponseBody
    public int setReportMessage(HttpSession httpSession,int postId,String descr){
        int userId =  ((UserInfo)httpSession.getAttribute("user")).getUserId();
        UserMessage userMessage = new UserMessage();
        userMessage.setCreateTime(new Date());
        userMessage.setDescription(descr);
        userMessage.setPostId(postId);
        userMessage.setSendUserId(userId);
        userMessage.setRecivedUserId(0);
        userMessageServiceimpl.addMessage(userMessage);
        return 200;
    }
//管理系统，获取举报信息
    @RequestMapping(value = "/getMessageAdmin" ,method = RequestMethod.POST)
    @ResponseBody
    public Map getMessageAdmin(@RequestParam(defaultValue = "0")int userId,int pageNum,int pageSize) {

        Map<String,Object> map = new HashMap<>();
        List<MessagePage> messagePages = userMessageServiceimpl.getMessageByReciveId(userId,(pageNum - 1) * pageSize,pageSize);
        int totalpage = userMessageServiceimpl.getAllMessageNum(userId);

        for (int i = 0;i<messagePages.size();i++)
        {
            if (messagePages.get(i).getMessageStatus()==0)
            {
                messagePages.get(i).setStatusStr("<red>未读</red>");
            }
            else{
                messagePages.get(i).setStatusStr("<green>已读</green>");
            }
        }


        map.put("data",messagePages);
        map.put("count",totalpage);
        map.put("msg",null);
        map.put("code",0);

        return map;

    }

//删除信息（管理系统）
    @RequestMapping(value = "/delMessageAdmin",method = RequestMethod.POST)
    @ResponseBody
    public int delMessageAdmin(int[] messageId){

        for (int i = 0;i<messageId.length;i++)
        {
            userMessageServiceimpl.delMessage(messageId[i]);
        }
        return 300;
    }

//获取管理信息
    @RequestMapping(value = "/getMessageAdmin2" ,method = RequestMethod.POST)
    @ResponseBody
    public Map getMessageBySendId(int userId, int pageNum, int pageSize) {

        Map<String,Object> map = new HashMap<>();
        List<MessagePage> messagePages = userMessageServiceimpl.getMessageBySendId(userId,(pageNum - 1) * pageSize,pageSize);
        int totalpage = userMessageServiceimpl.getAllMessageNum2(userId);

        for (int i = 0;i<messagePages.size();i++)
        {
            if (messagePages.get(i).getMessageStatus()==0)
            {
                messagePages.get(i).setStatusStr("<red>未处理</red>");
            }
            else{
                messagePages.get(i).setStatusStr("<green>已处理</green>");
            }
        }
        map.put("data",messagePages);
        map.put("count",totalpage);
        map.put("msg",null);
        map.put("code",0);

        return map;
    }


    @RequestMapping(value = "/addImportMessage",method = RequestMethod.POST)
    @ResponseBody
    public int addImportMessage(HttpSession httpSession,String descr){
        int userId =  ((UserInfo)httpSession.getAttribute("user")).getUserId();

        UserMessage userMessage = new UserMessage();
        userMessage.setCreateTime(new Date());
        userMessage.setDescription(descr);
        userMessage.setPostId(0);
        userMessage.setSendUserId(userId);

        List<Integer> tt =userInfoService.getAllUserId();

        for (int i = 0;i<tt.size();i++)
        {
            userMessage.setRecivedUserId(tt.get(i));
            userMessageServiceimpl.addMessage(userMessage);
        }
        return 200;
    }
//    处理消息为已读
    @RequestMapping(value = "/dealMessage",method = RequestMethod.POST)
    @ResponseBody
    public void dealMessage(int[] messageId) {
        for (int i = 0;i<messageId.length;i++)
        {
            userMessageServiceimpl.dealMessage(messageId[i]);
        }
    }
//搜索举报消息（管理）
    @RequestMapping(value = "/getMessageSearch",method = RequestMethod.POST)
    @ResponseBody
    public Map getMessageSearch(int sendId,int postId,int pageNum,int pageSize){

        Map<String,Object> map = new HashMap<>();
        map.put("data",userMessageServiceimpl.getMessage(sendId,postId,(pageNum - 1) * pageSize,pageSize));
        map.put("count",userMessageServiceimpl.getMessageSearchNum(sendId,postId));
        map.put("msg",null);
        map.put("code",0);

        return map;
    }

}
