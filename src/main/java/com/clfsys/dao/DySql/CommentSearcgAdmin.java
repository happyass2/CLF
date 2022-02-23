package com.clfsys.dao.DySql;

import com.clfsys.pojo.page.CommentPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cdy
 * @date 2021/5/4 6:11
 */
public class CommentSearcgAdmin {
    public String getSearchComment(@Param("postId") int postId,@Param("commentorId") int commentorId,@Param("commentContent") String commentContent){
        String sql ="SELECT a.`user_name`,a.`user_icon`,a.`user_act`,a.`user_post_number`,a.`register_time`,b.* FROM user_info AS a JOIN post_comment AS b ON a.`user_id` = b.commentor_id where post_id=#{postId}";


        StringBuilder sb = new StringBuilder(sql);
        if (commentorId!=0){
            sb.append(" and commentor_id = #{commentorId}");
        }
        if (commentContent!=null&&!"".equals(commentContent))
        {
            sb.append(" and comment_content like concat ('%',#{commentContent},'%')");
        }

        sql = sb.toString();
        return sql;

    }
}
