package com.clfsys.controller;

import com.clfsys.pojo.UserCollection;
import com.clfsys.pojo.UserInfo;
import com.clfsys.service.impl.UserCollectionServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cdy
 * @date 2021/2/4 19:54
 * 用户收藏相关功能
 * 帖子页面:收藏,检查是否收藏
 * 我的收藏页面:获取我的收藏,删除收藏
 */
@Controller
@RequestMapping("/collect")
public class UserCollectionController {
    @Autowired
    UserCollectionServiceimpl userCollectionServiceimpl;
//添加收藏
    @RequestMapping(value = "/addCollection",method = RequestMethod.POST)
    @ResponseBody
    public int addCollection(int postId, HttpSession httpSession){
        if (httpSession.getAttribute("user") == null)
            return 501;  //未登录
        int userId = ((UserInfo)httpSession.getAttribute("user")).getUserId();
        int ck = userCollectionServiceimpl.checkCollection(postId,userId);
        if (ck !=0)
            return 502;  //已有收藏

        UserCollection userCollection = new UserCollection();
        userCollection.setCollectDatetime(new Date());
        userCollection.setPostId(postId);
        userCollection.setCollectorId(userId);

        userCollectionServiceimpl.addCollection(userCollection);

        return 500;

    }
//检查是否已收藏
    @RequestMapping(value = "/checkCollection",method = RequestMethod.POST)
    @ResponseBody
    public int checkCollection(int postId,HttpSession httpSession){
        if (httpSession.getAttribute("user") == null)
            return 501;  //未登录
        int userId = ((UserInfo)httpSession.getAttribute("user")).getUserId();
        int code = userCollectionServiceimpl.checkCollection(postId,userId);

        if (code == 1)
            return 500; //有了该收藏

        return 502;//没有该收藏
    }

//获取收藏
    @RequestMapping(value = "/getCollection",method = RequestMethod.POST)
    @ResponseBody
    public Map getUserCollection(int pageNum, @RequestParam(defaultValue = "10") int pageSize, HttpSession httpSession){

        int userId = ((UserInfo)httpSession.getAttribute("user")).getUserId();
        Map<String,Object> map = new HashMap<>();
        int totalpage = userCollectionServiceimpl.getCollectNum(userId);
        if(totalpage%pageSize!=0) totalpage = totalpage/pageSize +1;
        else totalpage = totalpage/pageSize;
        map.put("toaPage",totalpage);
        map.put("currPage",pageNum);
        map.put("obj",userCollectionServiceimpl.getUserCollection(userId,(pageNum - 1) * pageSize,pageSize));

        return map;
    };
//删除收藏
    @RequestMapping(value = "/delCollection",method = RequestMethod.POST)
    @ResponseBody
    public int delCollection(int[] postId,HttpSession httpSession){

        int collectorId = ((UserInfo)httpSession.getAttribute("user")).getUserId();

        for (int i =0;i<postId.length;i++)
        {
           userCollectionServiceimpl.delCollection(postId[i],collectorId);
        }

        return 700;
    }


}
