package com.clfsys.controller;

import com.clfsys.pojo.PostComment;
import com.clfsys.pojo.UserInfo;
import com.clfsys.pojo.page.*;
import com.clfsys.service.impl.PostCommentSericeimpl;
import com.clfsys.service.impl.PostServiceimpl;
import com.clfsys.service.impl.UserInfoServiceImpl;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cdy
 * @date 2021/2/3 16:37
 * 回帖相关功能
 * 帖子页面:展示,添加回帖
 * 我的评论页面:获取我的历史回帖,获取我的帖子的回帖
 * 管理页面:展示,删除,查询回帖
 */
//回帖
@Controller
@RequestMapping("/comment")
public class PostCommentController {

    @Autowired
    PostCommentSericeimpl postCommentSericeimpl;

    @Autowired
    PostServiceimpl postServiceimpl;

//    根据id展示回帖
    @Autowired
    UserInfoServiceImpl userInfoService;
    @RequestMapping(value = "/showcomment",method = RequestMethod.GET)
    @ResponseBody
    public Map showcomment(int postId,int pageNum,@RequestParam(defaultValue = "20")int pageSize){
        Map<String,Object> map = new HashMap<>();
        List<CommentPage> commentPage = postCommentSericeimpl.getPostCommentPage(postId,(pageNum - 1) * pageSize,pageSize);
        map.put("commentPage",commentPage);

        int totalpage = postCommentSericeimpl.getCommentNum(postId);
        if(totalpage%pageSize!=0) totalpage = totalpage/pageSize +1;
        else totalpage = totalpage/pageSize;

        map.put("totalpage",totalpage);

        CommentTitle commentTitle = postServiceimpl.getCommentTitle(postId);

        map.put("commentTitle",commentTitle);

        postServiceimpl.addPostWatchNumber(postId);

        return map;
    }


//    添加回帖
    @RequestMapping(value = "/addPostComment",method = RequestMethod.POST)
    @ResponseBody
    public int addPostComment(int postId, String commentContent, HttpSession httpSession){

        UserInfo u = (UserInfo) httpSession.getAttribute("user");
//        判断登录
        if (u==null||u.getUserId()==0)
        {
            return 401;
        }
        int commentFloor = postCommentSericeimpl.getCommentFloor(postId)+1;
        PostComment postComment = new PostComment();

        postComment.setCreateTime(new Date());
        postComment.setCommentContent(commentContent);
        postComment.setCommentorId(u.getUserId());
        postComment.setPostId(postId);
        postComment.setCommentFloor(commentFloor);

        postCommentSericeimpl.addPostComment(postComment);
        postServiceimpl.addPostCommentNumber(postId);


        return 400;
    }

//    获取用户信息的的回帖
    @RequestMapping(value = "/getUserComment",method = RequestMethod.POST)
    @ResponseBody
    public Map getUserComment(@RequestParam(defaultValue = "0") int userId, int pageNum,@RequestParam(defaultValue = "10") int pageSize,HttpSession httpSession){
        Map<String,Object> map = new HashMap<>();

        if (userId == 0)
            userId =((UserInfo)httpSession.getAttribute("user")).getUserId();

        int totalpage = postCommentSericeimpl.getUserCommentNum(userId);
        if(totalpage%pageSize!=0) totalpage = totalpage/pageSize +1;
        else totalpage = totalpage/pageSize;

        map.put("toaPage",totalpage);

        map.put("currPage",pageNum);

        map.put("obj",postCommentSericeimpl.getUserComment(userId,pageNum,pageSize));

        return map;

    }


//    获取用户发布帖子的回帖
    @RequestMapping(value = "/getMyPostComment",method = RequestMethod.POST)
    @ResponseBody
    public Map getMyPostComment(@RequestParam(defaultValue = "0") int userId, int pageNum,@RequestParam(defaultValue = "10") int pageSize,HttpSession httpSession) {
        Map<String,Object> map = new HashMap<>();

        if (userId == 0)
            userId =((UserInfo)httpSession.getAttribute("user")).getUserId();

        int totalpage = postCommentSericeimpl.getMyPostCommentNum(userId);
        if(totalpage%pageSize!=0) totalpage = totalpage/pageSize +1;
        else totalpage = totalpage/pageSize;

        map.put("toaPage",totalpage);

        map.put("currPage",pageNum);


        List<ShowUserComment> showUserComments = postCommentSericeimpl.getMyPostComment(userId,pageNum,pageSize);

        for (int i = 0;i<showUserComments.size();i++)
        {
            if (showUserComments.get(i).getCommentContent()==null)
            {
                showUserComments.get(i).setCommentContent("this is null");
            }

        }

        map.put("obj",showUserComments);

        return map;


    }

//展示回帖管理页面数据
    @RequestMapping(value = "/showAdminComment",method = RequestMethod.POST)
    @ResponseBody
    public Map showAdminComment(int postId,@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "10")int pageSize){
        Map<String,Object> map = new HashMap<>();
        List<CommentPage> commentPage = postCommentSericeimpl.getPostCommentPage(postId,(pageNum - 1) * pageSize,pageSize);


        map.put("data",commentPage);
        map.put("code",0);
        map.put("count",postCommentSericeimpl.getCommentNum(postId));
        map.put("msg",null);

        return map;
    }

//删除回帖
    @RequestMapping(value = "/delComment",method = RequestMethod.POST)
    public void delComment(int[] commentId){

        for (int i = 0;i<commentId.length;i++)
        {
            postCommentSericeimpl.delComment(commentId[i]);
        }
    }
//    搜索回帖
    @RequestMapping(value = "/getSearchComment",method = RequestMethod.POST)
    @ResponseBody
    public Map getSearchComment(int postId, int commentorId, String commentContent){
        Map<String,Object> map = new HashMap<>();
        List<CommentPage> commentPages = postCommentSericeimpl.getSearchComment(postId,commentorId,commentContent);

        map.put("data",commentPages);
        map.put("code",0);
        map.put("count",commentPages.size());
        map.put("msg",null);

        return map;
    }





}
