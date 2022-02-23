package com.clfsys.pojo;

import java.util.Date;

/**
 * @author cdy
 * @date 2021/2/4 19:48
 */
public class UserMessage {
    private int messageId;
    private int recivedUserId;
    private int sendUserId;
    private Date createTime;
    private int messageStatus;
    private String description;
    private int postId;



    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getRecivedUserId() {
        return recivedUserId;
    }

    public void setRecivedUserId(int recivedUserId) {
        this.recivedUserId = recivedUserId;
    }

    public int getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(int sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "messageId=" + messageId +
                ", recivedUserId=" + recivedUserId +
                ", sendUserId=" + sendUserId +
                ", createTime=" + createTime +
                ", messageStatus=" + messageStatus +
                ", description='" + description + '\'' +
                ", postId=" + postId +
                '}';
    }
}
