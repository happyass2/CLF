package com.clfsys.controller;

import com.clfsys.pojo.Moderator;
import com.clfsys.pojo.Post;
import com.clfsys.pojo.PostComment;
import com.clfsys.pojo.UserInfo;
import com.clfsys.pojo.page.*;
import com.clfsys.service.impl.*;
import com.github.pagehelper.PageHelper;
import io.swagger.models.auth.In;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author cdy
 * @date 2021/2/3 2:46
 * 帖子相关功能
 * 帖子展示页面:展示相应板块与主题的帖子,发布帖子
 * 主页:搜索,获取热门贴,获取用户,帖子数量
 * 我的帖子页面:删除,查看自己的帖子
 * 管理页面:删除,展示,搜索,置顶,加精
 */
@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostServiceimpl postService;

    @Autowired
    UserInfoServiceImpl userInfoService;

    @Autowired
    SectionServiceimpl sectionServiceimpl;

    @Autowired
    SectionThemeServiceimpl sectionThemeServiceimpl;

    @Autowired
    PostCommentSericeimpl postCommentSericeimpl;

//展示所有帖子(测试)
    @RequestMapping(value = "/each",method = RequestMethod.POST)
    @ResponseBody
    public List<Post> showAllPost(int sectionId)
    {
        return postService.showAllPost(sectionId);
    }


//发布帖子
    @RequestMapping(value = "/writepost",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public int writePost(@RequestBody EditPost editPost, HttpSession httpSession) throws Exception
    {
        Post post = new Post();
        String title = editPost.getPostTitle();
        int theme = editPost.getPostThemeId();
        int sec = editPost.getPostSection();
        String content = editPost.getPostContent();

        UserInfo userInfo=new UserInfo();
        userInfo = (UserInfo)httpSession.getAttribute("user");

        if (userInfo==null||userInfo.getUserId()==0)
        {
            return 302;
        }

        Date date = new Date();


        try{
            //作者id
            post.setPublisherId(userInfo.getUserId());
            //内容
            post.setPostContent(content);
            //主题
            post.setPostThemeId(theme);
            //默认收藏人数
            post.setCollectNumber(0);
            //创建时间
            post.setCreateTime(date);
            //是否精华帖，1为精华帖子
            post.setElitePost(0);
            //最后回复时间
            post.setLastTime(new Date());
            //帖子板块
            post.setPostSectionId(sec);
            //默认回复人数
            post.setCommentNumber(1);
            //帖子主题默认对应图标
            post.setPostIcon("/img/post1.png");
            //是否为置顶贴
            post.setTopPost(0);
            //帖子标题
            post.setPostTitle(title);
            //查看人数
            post.setWatchNumber(0);
            //帖子状态
            post.setStatus(1);

            postService.writePost(post);



        }catch (Exception e){
            return 301;
        }


        return 300;
    }



    //分页展示相应板块与主题的帖子
    @RequestMapping(value = "/showpost",method = RequestMethod.GET)
    @ResponseBody
    public Map showPostBySnT(int sectionId, int themeId,int pageNum, @RequestParam(defaultValue = "20")int pageSize) {

            List<ShowPost> posts = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Map<String,Object> data = new HashMap<>();

            int totalpage = 0;

            List<Post> post2 = new ArrayList<>();

            //0为全部
            if (themeId != 0) {
                 post2 = postService.showPostBySnT(sectionId, themeId, (pageNum - 1) * pageSize, pageSize);
                 totalpage = postService.postNum(sectionId,themeId);
            }
            else{
                post2 = postService.showPostByS(sectionId, (pageNum - 1) * pageSize, pageSize);
                totalpage = postService.postNumByS(sectionId);
            }
            //传给页面的页码总数与当前页

            if(totalpage%pageSize!=0) totalpage = totalpage/pageSize +1;
            else totalpage = totalpage/pageSize;

            data.put("curpage",pageNum);

            data.put("totalpage",totalpage);



            try{
                Date d1 = new Date();

                for(int i = 0; i<post2.size();i++){
                    ShowPost post = new ShowPost();
                    post.setPostTitle(post2.get(i).getPostTitle());
                    post.setCommentnumber(post2.get(i).getCommentNumber());
                    post.setImg(post2.get(i).getPostIcon());
                    post.setPostId(post2.get(i).getPostId());
                    post.setUsername(userInfoService.checkUserNameById(post2.get(i).getPublisherId()));
                    post.setTheme(sectionThemeServiceimpl.showThemeNameById(post2.get(i).getPostThemeId()));
                    post.setCreatetime(sdf.format(post2.get(i).getCreateTime()));
                    post.setLasttime(sdf.format(post2.get(i).getLastTime()));
                    post.setWatchnumber(post2.get(i).getWatchNumber());
                    post.setThemeId(post2.get(i).getPostThemeId());
                    posts.add(post);
                }



                data.put("obj",posts);

                Date d2 = new Date();
            }catch (Exception e){
                System.out.println("展示错误");
            }


            return data;
    };

//  主页展示今日会员,帖子等数量
    @RequestMapping(value = "/showpostandusernum",method = RequestMethod.GET)
    @ResponseBody
    public Map showPostNUser(){



        Map<String,Integer> map= new HashMap();

        int postNumToday = postService.getPostNumByDate();

        int postNum = postService.getPostNum();

        int userNum = userInfoService.selectUserNum();

        map.put("postNumToday",postNumToday);

        map.put("postNum",postNum);

        map.put("userNum",userNum);



        return map;

    }

//   主页展示热门贴
    @RequestMapping(value = "/gethotpost",method = RequestMethod.GET)
    @ResponseBody
    public List<HotPost> getHostPost(){
        List<HotPost> hotPosts = postService.getHostPost();

        return hotPosts;
    }
//    帖子搜索
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ResponseBody
    public Map getSearchPost(String searchStr,int pageNum,@RequestParam(defaultValue = "20")int pageSize){
        List<SearchPost> searchPosts = postService.getSearchPost(searchStr,(pageNum - 1) * pageSize,pageSize);

        //结果过长处理
        for (int i = 0;i<searchPosts.size();i++){
            String strContent =searchPosts.get(i).getPostContent();
            if (strContent!=null&&strContent.length()>=100)
            {
                searchPosts.get(i).setPostContent(strContent.substring(0,100)+"....");
            }
        }

        Map<String,Object> map =new HashMap<>();
        map.put("obj",searchPosts);
        int totalpage = postService.getSearchNum(searchStr);
        if(totalpage%pageSize!=0) totalpage = totalpage/pageSize +1;
        else totalpage = totalpage/pageSize;
        map.put("toaPage",totalpage);
        return map;
    };


//获取用户的帖子(用户的帖子管理)
    @RequestMapping("/getUserPosts")
    @ResponseBody
    public Map getUserPosts(@RequestParam(defaultValue = "0") int userId, int pageNum, @RequestParam(defaultValue = "10") int pageSize,HttpSession httpSession) {

        if (userId==0)
            userId = ((UserInfo)httpSession.getAttribute("user")).getUserId();

        Map<String,Object> map = new HashMap<>();

        map.put("obj",postService.getUserPosts(userId,(pageNum - 1) * pageSize,pageSize));

        int totalpage = postService.getUserPostsNum(userId);
        if(totalpage%pageSize!=0) totalpage = totalpage/pageSize +1;
        else totalpage = totalpage/pageSize;

        map.put("toaPage",totalpage);

        map.put("currPage",pageNum);

        return map;



    }

    //    删除帖子
    @RequestMapping(value = "/delPost",method = RequestMethod.POST)
    public void delPost(int[] postId) {

        for (int i = 0; i<postId.length;i++)
        {
            postService.delPost(postId[i]);

        }

    }


//获取管理界面帖子
    @RequestMapping(value = "/getAllPost",method = RequestMethod.POST)
    @ResponseBody
    public Map getAllPost(int page,int limit){
        Map<String,Object> map = new HashMap<>();
        List<AdminPost> posts = postService.getAllpost((page-1)*limit,limit);

        for (int i = 0;i<posts.size();i++){
            if (posts.get(i).getElitePost()==0)
            {
                posts.get(i).setElitePostDesc("x");
            }else{
                posts.get(i).setElitePostDesc("√");
            }

            if (posts.get(i).getTopPost()==0)
            {
                posts.get(i).setTopPostDesc("x");
            }else{
                posts.get(i).setTopPostDesc("√");
            }
        }
        map.put("data",posts);
        map.put("count",postService.getPostNum());
        map.put("msg",null);
        map.put("code",0);
        return map;
    }
//管理页面搜索帖子
    @RequestMapping(value = "/getSearchPostAdmin",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public Map getSearchPostAdmin(@RequestBody SearchPostAdmin searchPostAdmin){

        Map<String,Object> map = new HashMap<>();
        List<AdminPost> posts = postService.getSearchPostAdmin(searchPostAdmin);

        for (int i = 0;i<posts.size();i++){
            if (posts.get(i).getElitePost()==0)
            {
                posts.get(i).setElitePostDesc("x");
            }else{
                posts.get(i).setElitePostDesc("√");
            }

            if (posts.get(i).getTopPost()==0)
            {
                posts.get(i).setTopPostDesc("x");
            }else{
                posts.get(i).setTopPostDesc("√");
            }
        }

        map.put("data",posts);
        map.put("count",posts.size());
        map.put("msg",null);
        map.put("code",0);
        return map;
    };
//设置置顶
    @RequestMapping(value = "/setTop",method = RequestMethod.POST)
    public void setTop(int[] postId){
        for (int i =0;i<postId.length;i++)
        {
            postService.setTop(postId[i]);
        }
    }
//设置精华
    @RequestMapping(value = "/setElite",method = RequestMethod.POST)
    public void setElite(int[] postId){
        for (int i =0;i<postId.length;i++)
        {
            postService.setElite(postId[i]);
        }
    }
//取消置顶
    @RequestMapping(value = "/cancelTop",method = RequestMethod.POST)
    public void cancelTop(int[] postId){
        for (int i =0;i<postId.length;i++)
        {
            postService.cancelTop(postId[i]);
        }
    }
//取消精华
    @RequestMapping(value = "/cancelElite",method = RequestMethod.POST)
    public void cancelElite(int[] postId){
        for (int i =0;i<postId.length;i++)
        {
            postService.cancelElite(postId[i]);
        }
    }


    //获取管理界面帖子
    @RequestMapping(value = "/getAllPostModerator",method = RequestMethod.POST)
    @ResponseBody
    public Map getAllPostModerator(int page,int limit,HttpSession httpSession){
        Map<String,Object> map = new HashMap<>();
        int sectionId = ((Moderator)httpSession.getAttribute("moderator")).getSectionId();
        List<AdminPost> posts = postService.getAllpostModerator((page-1)*limit,limit,sectionId);

        for (int i = 0;i<posts.size();i++){
            if (posts.get(i).getElitePost()==0)
            {
                posts.get(i).setElitePostDesc("x");
            }else{
                posts.get(i).setElitePostDesc("√");
            }

            if (posts.get(i).getTopPost()==0)
            {
                posts.get(i).setTopPostDesc("x");
            }else{
                posts.get(i).setTopPostDesc("√");
            }
        }
        map.put("data",posts);
        map.put("count",postService.getPostNum());
        map.put("msg",null);
        map.put("code",0);
        return map;
    }
    //管理页面搜索帖子
    @RequestMapping(value = "/getSearchPostModerator",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public Map getSearchPostModerator(@RequestBody SearchPostAdmin searchPostAdmin,HttpSession httpSession){

        Map<String,Object> map = new HashMap<>();
        int sectionId = ((Moderator)httpSession.getAttribute("moderator")).getSectionId();
        searchPostAdmin.setPostSectionId(sectionId);
        List<AdminPost> posts = postService.getSearchPostAdmin(searchPostAdmin);

        for (int i = 0;i<posts.size();i++){
            if (posts.get(i).getElitePost()==0)
            {
                posts.get(i).setElitePostDesc("x");
            }else{
                posts.get(i).setElitePostDesc("√");
            }

            if (posts.get(i).getTopPost()==0)
            {
                posts.get(i).setTopPostDesc("x");
            }else{
                posts.get(i).setTopPostDesc("√");
            }
        }

        map.put("data",posts);
        map.put("count",posts.size());
        map.put("msg",null);
        map.put("code",0);
        return map;
    };

}
