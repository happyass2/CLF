package com.clfsys.service;

import com.clfsys.pojo.UserMessage;
import com.clfsys.pojo.page.MessagePage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/4 19:52
 */
public interface UserMessageService {

    public List<MessagePage> getMessageByReciveId(int userId,int pageNum,int pageSize);

    public int getMessageNum(int userId);

    public int getAllMessageNum(int userId);

    public void addMessage(UserMessage userMessage);

    public void delMessage(int messageId);

    public void readMessage(int messageId);

    public List<MessagePage> getMessageBySendId( int userId,int pageNum, int pageSize);

    public int getAllMessageNum2(int userId);

    public void dealMessage(int messageId);

    public List<MessagePage> getMessage(int sendId,int postId,int pageNum,int pageSize);

    public int getMessageSearchNum(@Param("sendId") int sendId, @Param("postId")int postId);
}
