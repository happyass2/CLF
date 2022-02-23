package com.clfsys.dao;

import com.clfsys.dao.DySql.MessageSearch;
import com.clfsys.pojo.UserMessage;
import com.clfsys.pojo.page.MessagePage;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/4 19:52
 */
public interface UserMessageMapper {

    //在页面展示信息
    @Select("SELECT \n" +
            "  d.*,\n" +
            "  c.`user_name` \n" +
            "FROM\n" +
            "  (SELECT \n" +
            "    b.`post_id`,\n" +
            "    b.`post_title`,\n" +
            "    a.`message_id`,\n" +
            "    a.`description`,\n" +
            "    a.`create_time`,\n" +
            "    a.`message_status`,\n" +
            "    a.`send_user_id` \n" +
            "  FROM\n" +
            "    user_message AS a \n" +
            "    LEFT JOIN post AS b \n" +
            "      ON a.`post_id` = b.`post_id` \n" +
            "  WHERE a.`recived_user_id` = #{userId}) AS d \n" +
            "  LEFT JOIN user_info AS c \n" +
            "    ON d.`send_user_id` = c.user_id ORDER BY d.`create_time` limit #{pageNum},#{pageSize}")
    public List<MessagePage> getMessageByReciveId(@Param("userId") int userId,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);
    //查询未读信息
    @Select("SELECT COUNT(*) FROM user_message WHERE recived_user_id =#{userId} and message_status = 0")
    public int getMessageNum(int userId);
    //查询所有信息
    @Select("SELECT COUNT(*) FROM user_message WHERE recived_user_id =#{userId}")
    public int getAllMessageNum(int userId);

    //插入信息
    @Insert("INSERT INTO user_message(recived_user_id,send_user_id,create_time,message_status,description,post_id)VALUES(#{recivedUserId},#{sendUserId},#{createTime},#{messageStatus},#{description},#{postId})")
    public void addMessage(UserMessage userMessage);

    //删除信息
    @Delete("DELETE FROM user_message WHERE message_id = #{messageId}")
    public void delMessage(int messageId);

    //已读信息
    @Update("update user_message set message_status = 1 where message_id = #{messageId}")
    public void readMessage(int messageId);

    //根据发送者id获取信息
    @Select("SELECT \n" +
            "  d.*,\n" +
            "  c.`user_name` \n" +
            "FROM\n" +
            "  (SELECT \n" +
            "    b.`post_id`,\n" +
            "    b.`post_title`,\n" +
            "    a.`message_id`,\n" +
            "    a.`description`,\n" +
            "    a.`create_time`,\n" +
            "    a.`message_status`,\n" +
            "    a.`send_user_id` \n" +
            "  FROM\n" +
            "    user_message AS a \n" +
            "    LEFT JOIN post AS b \n" +
            "      ON a.`post_id` = b.`post_id` \n" +
            "  WHERE a.`send_user_id` = #{userId}) AS d \n" +
            "  LEFT JOIN user_info AS c \n" +
            "    ON d.`send_user_id` = c.user_id ORDER BY d.`create_time` limit #{pageNum},#{pageSize}")
    public List<MessagePage> getMessageBySendId(@Param("userId") int userId,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);
    //根据发送者id获取所有信息数量
    @Select("SELECT COUNT(*) FROM user_message WHERE send_user_id =#{userId}")
    public int getAllMessageNum2(int userId);

    //更新信息为已读
    @Update("UPDATE user_message SET message_status = 1 WHERE message_id = #{messageId}")
    public void dealMessage(int messageId);
    //获取条件查询的信息
    @SelectProvider(type = MessageSearch.class,method = "getMessageSearch")
    public List<MessagePage> getMessage(@Param("sendId") int sendId, @Param("postId")int postId,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);
    //获取条件查询的信息的数量
    @SelectProvider(type = MessageSearch.class,method = "getMessageSearchNum")
    public int getMessageSearchNum(@Param("sendId") int sendId, @Param("postId")int postId);

}
