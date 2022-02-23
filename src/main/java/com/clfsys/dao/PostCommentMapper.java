package com.clfsys.dao;

import com.clfsys.dao.DySql.CommentSearcgAdmin;
import com.clfsys.pojo.PostComment;
import com.clfsys.pojo.page.CommentPage;
import com.clfsys.pojo.page.ShowPost;
import com.clfsys.pojo.page.ShowUserComment;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author cdy
 * @date 2021/2/3 14:28
 */
public interface PostCommentMapper {
    //添加评论
    @Insert("INSERT INTO post_comment(post_id,commentor_id,comment_content,create_time,comment_floor) VALUES(#{postId},#{commentorId},#{commentContent},#{createTime},#{commentFloor})")
    public void addPostComment(PostComment postComment);
    //获取所有楼层
    @Select("SELECT * FROM post_comment where post_id = #{postId} LIMIT #{pageNum},#{pageSize}")
    public List<PostComment> getPostComment(@Param("postId") int postId,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);
    //获取帖子页面所需评论信息
    @Select("SELECT a.`user_name`,a.`user_icon`,a.`user_act`,a.`user_post_number`,a.`register_time`,b.* FROM user_info AS a JOIN post_comment AS b ON a.`user_id` = b.commentor_id WHERE b.`post_id` = #{postId} order by b.comment_id LIMIT #{pageNum},#{pageSize}")
    public List<CommentPage> getPostCommentPage(@Param("postId") int postId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    //获取评论帖子总数
    @Select("SELECT COUNT(*) FROM post_comment WHERE post_id=#{postId}")
    public int getCommentNum(int postId);
    //获取最新楼层
    @Select("SELECT comment_floor FROM post_comment WHERE post_id=#{postId} ORDER BY comment_floor DESC LIMIT 0,1")
    public int getCommentFloor(int postId);
    //查询用户回复贴
    @Select("SELECT \n" +
            "  d.*,\n" +
            "  c.`user_name` \n" +
            "FROM\n" +
            "  (SELECT \n" +
            "    a.`comment_id`,\n" +
            "    a.`commentor_id`,\n" +
            "    a.`create_time`,\n" +
            "    a.`post_id`,\n" +
            "    b.`post_title`,\n" +
            "    a.`comment_content`,\n" +
            "    a.`comment_floor` \n" +
            "  FROM\n" +
            "    post_comment AS a \n" +
            "    LEFT JOIN post AS b \n" +
            "      ON a.`post_id` = b.`post_id` where a.`commentor_id` = #{userId} AND a.`comment_floor` != 1) AS d \n" +
            "  LEFT JOIN user_info AS c \n" +
            "    ON d.`commentor_id` = c.`user_id` ORDER BY d.`create_time` DESC LIMIT #{pageNum},#{pageSize}")
    public List<ShowUserComment> getUserComment(@Param("userId") int userId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    //获取用户评论数量

    @Select("SELECT COUNT(*) FROM post_comment WHERE commentor_id = #{userId} AND comment_floor != 1")
    public int getUserCommentNum(int userId);

    //获取我帖子的评论
    @Select("SELECT \n" +
            "  d.*,\n" +
            "  c.`user_name` \n" +
            "FROM\n" +
            "  (SELECT \n" +
            "    a.`comment_id`,\n" +
            "    a.`commentor_id`,\n" +
            "    a.`create_time`,\n" +
            "    a.`post_id`,\n" +
            "    b.`post_title`,\n" +
            "    a.`comment_content`,\n" +
            "    a.`comment_floor` \n" +
            "  FROM\n" +
            "    post_comment AS a \n" +
            "    LEFT JOIN post AS b \n" +
            "      ON a.`post_id` = b.`post_id` where b.`publisher_id` = #{userId} AND a.`comment_floor` != 1) AS d \n" +
            "  LEFT JOIN user_info AS c \n" +
            "    ON d.`commentor_id` = c.`user_id` ORDER BY d.`create_time` DESC LIMIT #{pageNum},#{pageSize}")
    public List<ShowUserComment> getMyPostComment(@Param("userId") int userId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

//    获取用户评论的数量
    @Select("SELECT COUNT(*) FROM post_comment AS a LEFT JOIN post AS b ON a.`post_id` = b.`post_id` WHERE b.`publisher_id` = #{userId} AND a.`comment_floor` != 1")
    public int getMyPostCommentNum(int userId);

//    删除评论
    @Delete("DELETE FROM post_comment WHERE comment_id = #{commentId}")
    public void delComment(int commentId);
//    获取搜索评论
    @SelectProvider(type = CommentSearcgAdmin.class,method = "getSearchComment")
    public List<CommentPage> getSearchComment(@Param("postId") int postId,@Param("commentorId") int commentorId,@Param("commentContent") String commentContent);
}
