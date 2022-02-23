package com.clfsys.pojo.page;

/**
 * @author cdy
 * @date 2021/4/19 20:01
 */
public class MessagePage {
    private int messageId;
    private int sendUserId;
    private String description;
    private int postId;
    private String statusStr;
    private String postTitle;
    private int messageStatus;
    private String createTime;
    private String userName;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(int sendUserId) {
        this.sendUserId = sendUserId;
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

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Override
    public String toString() {
        return "MessagePage{" +
                "messageId=" + messageId +
                ", sendUserId=" + sendUserId +
                ", description='" + description + '\'' +
                ", postId=" + postId +
                ", statusStr='" + statusStr + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", messageStatus=" + messageStatus +
                ", createTime='" + createTime + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
