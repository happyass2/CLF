package com.clfsys.dao;

import com.clfsys.dao.DySql.PostSearchAdmin;
import com.clfsys.pojo.Post;
import com.clfsys.pojo.Section;
import com.clfsys.pojo.page.*;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

/**
 * @author cdy
 * @date 2021/2/3 2:41
 */
public interface PostMapper {

    //各板块的帖子展示
    @Select("Select * From post WHERE post_section_id = #{sectionId}")
    public List<Post> showAllPost(int sectionId);

    //添加帖子
    @Insert("INSERT INTO post(post_theme_id,post_content,post_section_id,publisher_id,create_time,status,post_title,last_time,post_icon,watch_number,top_post,elite_post,collect_number,comment_number) VALUES (#{postThemeId},#{postContent},#{postSectionId},#{publisherId},#{createTime},#{status},#{postTitle},#{lastTime},#{postIcon},#{watchNumber},#{topPost},#{elitePost},#{collectNumber},#{commentNumber})")
    public void writePost(Post post);


    //按主题与板块查询
    @Select("SELECT * from post where post_section_id = #{sectionId} and post_theme_id = #{themeId} order by top_post desc,last_time desc LIMIT #{pageNum},#{pageSize}")
    public List<Post> showPostBySnT(@Param("sectionId") int sectionId, @Param("themeId") int themeId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) from post where post_section_id = #{sectionId} AND post_theme_id = #{themeId}")
    public int postNum(@Param("sectionId") int sectionId, @Param("themeId") int themeId);


    //按板块查数量
    @Select("SELECT COUNT(*) from post where post_section_id = #{sectionId} AND post_theme_id = #{themeId}")
    public int postNumByS( int sectionId);


    //按板块查post
    @Select("SELECT * from post where post_section_id = #{sectionId} order by top_post desc,last_time desc LIMIT #{pageNum},#{pageSize}")
    public List<Post> showPostByS(@Param("sectionId") int sectionId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    //查帖子数量
    @Select("SELECT COUNT(*) from post")
    public int getPostNum();

    //查询当天帖子数
    @Select("SELECT COUNT(*) FROM post WHERE TO_DAYS(NOW()) = TO_DAYS(create_time) ")
    public int getPostNumByDate();

    //根据section查询帖子数量
    @Select("SELECT COUNT(*) from post WHERE post_section_id = #{sectionId}")
    public int getPostNumBySection(int sectionId);

    //获取最新帖子
    @Select("SELECT *  FROM post WHERE post_section_id = #{sectionId} ORDER BY create_time DESC LIMIT 0,1")
    public Post getNewestPost(int sectionId);

    @Select("SELECT a.post_id,a.post_section_id,a.post_theme_id,b.theme_name,a.post_title,create_time FROM post AS a JOIN section_theme AS b ON a.`post_theme_id` = b.`theme_id` ORDER BY comment_number DESC LIMIT 0,5")
    public List<HotPost> getHostPost();
    //获取楼层页面帖子需要的信息
    @Select("SELECT a.`post_title`,a.`comment_number`,a.`watch_number`,b.* FROM post AS a JOIN section_theme AS b ON a.`post_theme_id` = b.`theme_id` WHERE post_id = #{postId}")
    public CommentTitle getCommentTitle(int postId);

    //模糊查询
    @Select("select \n" +
            "  a.`post_id`,\n" +
            "  a.`post_title`,\n" +
            "  a.`post_content`,\n" +
            "  a.`create_time`,\n" +
            "  a.`watch_number`,\n" +
            "  a.`comment_number`,\n" +
            "  b.`user_name`,\n" +
            "  c.`section_name` \n" +
            "from\n" +
            "  post as a \n" +
            "  left join user_info as b \n" +
            "    on a.`publisher_id` = b.`user_id` \n" +
            "  left join section as c \n" +
            "    on a.`post_section_id` = c.`section_id` \n" +
            "where a.post_title like CONCAT ('%',#{searchStr},'%') \n" +
            "  or a.`post_content` like CONCAT ('%',#{searchStr},'%') \n" +
            "order by a.`create_time` desc LIMIT #{pageNum},#{pageSize} ")
    public List<SearchPost> getSearchPost(@Param("searchStr") String searchStr,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);

//    获取帖子搜索的数量
    @Select("SELECT count(*) FROM post WHERE post_content LIKE CONCAT ('%',#{searchStr},'%') or post_title like CONCAT ('%',#{searchStr},'%')")
    public int getSearchNum(String searchStr);

    //获取用户的帖子
    @Select("SELECT a.`post_id`,a.`post_icon`,a.`post_title`,a.`watch_number`,a.`comment_number`,a.`create_time`,a.`last_time` FROM post AS a WHERE a.`publisher_id` = #{userId} ORDER BY create_time DESC LIMIT #{pageNum},#{pageSize} ")
    public List<ShowPost> getUserPosts(@Param("userId") int userId,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);
//    获取用户帖子的数量
    @Select("SELECT COUNT(*) from post where publisher_id = #{userId}")
    public int getUserPostsNum(int userId);
//删除帖子
    @Select("DELETE FROM post WHERE post_id = #{postId}")
    public void delPost(int postId);

//获取管理页面的帖子信息
    @Select("select \n" +
            "  a.`post_id`,\n" +
            "  a.`post_title`,\n" +
            "  a.`post_icon`,\n" +
            "  a.`watch_number`,\n" +
            "  a.`collect_number`,\n" +
            "  a.`comment_number`,\n" +
            "  a.`create_time`,\n" +
            "  a.`last_time`,\n" +
            "  a.`top_post`,\n" +
            "  a.`elite_post`,\n" +
            "  a.`publisher_id`,\n" +
            "  b.`section_name`,\n" +
            "  c.`theme_name`,\n" +
            "  d.`user_name` \n" +
            "from\n" +
            "  post as a \n" +
            "  left join section as b \n" +
            "    on a.`post_section_id` = b.`section_id` \n" +
            "  left join section_theme as c \n" +
            "    on a.`post_theme_id` = c.`theme_id` \n" +
            "  left join user_info as d \n" +
            "    on a.`publisher_id` = d.`user_id` LIMIT #{page},#{limit} ")
    public List<AdminPost> getAllpost(@Param("page") int page,@Param("limit") int limit);

//获取搜索的帖子
    @SelectProvider(type = PostSearchAdmin.class,method = "getSearchPostAdmin")
    public List<AdminPost> getSearchPostAdmin(SearchPostAdmin searchPostAdmin);
//置顶
    @Update("UPDATE post SET top_post = 1,post_icon='/img/top.png' WHERE post_id = #{postId}")
    public void setTop(int postId);
//取消置顶
    @Update("UPDATE post SET elite_post = 1,post_icon='/img/elite.png' WHERE post_id = #{postId}")
    public void setElite(int postId);
//加精
    @Update("UPDATE post SET top_post = 0,post_icon='/img/post1.png' WHERE post_id = #{postId}")
    public void cancelTop(int postId);
//取消加精
    @Update("UPDATE post SET elite_post = 0,post_icon='/img/post1.png' WHERE post_id = #{postId}")
    public void cancelElite(int postId);
//增加相应评论人数
    @Update("UPDATE post SET comment_number = comment_number+1 WHERE post_id = #{postId}")
    public void addPostCommentNumber(int postId);
//增加相应帖子观看人数
    @Update("UPDATE post SET watch_number = watch_number+1 WHERE post_id = #{postId}")
    public void addPostWatchNumber(int postId);
//获取版主相应的帖子信息
    @Select("select \n" +
            "  a.`post_id`,\n" +
            "  a.`post_title`,\n" +
            "  a.`post_icon`,\n" +
            "  a.`watch_number`,\n" +
            "  a.`collect_number`,\n" +
            "  a.`comment_number`,\n" +
            "  a.`create_time`,\n" +
            "  a.`last_time`,\n" +
            "  a.`top_post`,\n" +
            "  a.`elite_post`,\n" +
            "  a.`publisher_id`,\n" +
            "  b.`section_name`,\n" +
            "  c.`theme_name`,\n" +
            "  d.`user_name` \n" +
            "from\n" +
            "  post as a \n" +
            "  left join section as b \n" +
            "    on a.`post_section_id` = b.`section_id` \n" +
            "  left join section_theme as c \n" +
            "    on a.`post_theme_id` = c.`theme_id` \n" +
            "  left join user_info as d \n" +
            "    on a.`publisher_id` = d.`user_id` where a.post_section_id = #{sectionId} LIMIT #{page},#{limit} ")
    public List<AdminPost> getAllpostModerator(@Param("page") int page,@Param("limit") int limit,@Param("sectionId") int sectionId);





}
