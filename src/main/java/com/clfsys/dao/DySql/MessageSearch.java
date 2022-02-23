package com.clfsys.dao.DySql;

import org.apache.ibatis.annotations.Param;

/**
 * @author cdy
 * @date 2021/5/6 18:34
 */
public class MessageSearch {

    public String getMessageSearch(@Param("sendId") int sendId, @Param("postId")int postId,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize){

        String sql = "SELECT \n" +
                "  c.`user_name`,\n" +
                "  b.`post_id`,\n" +
                "  b.`post_title`,\n" +
                "  a.`message_id`,\n" +
                "  a.`description`,\n" +
                "  a.`create_time`,\n" +
                "  a.`message_status`,\n" +
                "  a.`send_user_id` \n" +
                "FROM\n" +
                "  user_message AS a \n" +
                "  LEFT JOIN post AS b \n" +
                "    ON a.`post_id` = b.`post_id` \n" +
                "  LEFT JOIN user_info AS c \n" +
                "    ON a.`send_user_id` = c.user_id  where a.recived_user_id=0  \n" ;
        StringBuilder sb = new StringBuilder(sql);

        if (sendId!=0)
        {
            sb.append(" and a.`send_user_id` = #{sendId} ");
        }
        if (postId!=0){
            sb.append(" and a.`post_id` = #{postId} ");
        }

        sb.append(" ORDER BY a.`create_time` limit #{pageNum},#{pageSize}");

        return sb.toString();

    }


    public String getMessageSearchNum(@Param("sendId") int sendId, @Param("postId")int postId){

        String sql = "SELECT \n" +
                "  count(*)\n" +
                "FROM\n" +
                "  user_message \n" +
                "  where recived_user_id=0 \n" ;
        StringBuilder sb = new StringBuilder(sql);

        if (sendId!=0)
        {
            sb.append(" and send_user_id = #{sendId} ");
        }
        if (postId!=0){
            sb.append(" and post_id=#{postId}");
        }


        return sb.toString();

    }
}
