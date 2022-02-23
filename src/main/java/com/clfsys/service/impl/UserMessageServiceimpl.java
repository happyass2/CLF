package com.clfsys.service.impl;

import com.clfsys.dao.UserMessageMapper;
import com.clfsys.pojo.UserMessage;
import com.clfsys.pojo.page.MessagePage;
import com.clfsys.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/4 19:52
 */
@Service
public class UserMessageServiceimpl implements UserMessageService {
    @Autowired
    UserMessageMapper userMessageMapper;

    @Override
    public List<MessagePage> getMessageByReciveId(int userId,int pageNum,int pageSize) {
        return userMessageMapper.getMessageByReciveId(userId,pageNum,pageSize);
    }

    @Override
    public int getMessageNum(int userId) {
        return userMessageMapper.getMessageNum(userId);
    }

    @Override
    public int getAllMessageNum(int userId) {
        return userMessageMapper.getAllMessageNum(userId);
    }

    @Override
    public void addMessage(UserMessage userMessage) {
        userMessageMapper.addMessage(userMessage);
    }

    @Override
    public void delMessage(int messageId) {
        userMessageMapper.delMessage(messageId);
    }

    @Override
    public void readMessage(int messageId) {
        userMessageMapper.readMessage(messageId);
    }

    @Override
    public List<MessagePage> getMessageBySendId(int userId, int pageNum, int pageSize) {
        return userMessageMapper.getMessageBySendId(userId,pageNum,pageSize);
    }

    @Override
    public int getAllMessageNum2(int userId) {
        return userMessageMapper.getAllMessageNum2(userId);
    }

    @Override
    public void dealMessage(int messageId) {
        userMessageMapper.dealMessage(messageId);
    }

    @Override
    public List<MessagePage> getMessage(int sendId, int postId, int pageNum, int pageSize) {
        return userMessageMapper.getMessage(sendId,postId,pageNum,pageSize);
    }

    @Override
    public int getMessageSearchNum(int sendId, int postId) {
        return userMessageMapper.getMessageSearchNum(sendId,postId);
    }
}
