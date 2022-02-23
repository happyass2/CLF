package com.clfsys.pojo.page;

import java.util.Date;

/**
 * @author cdy
 * @date 2021/4/7 20:55
 */
public class CommentPage {
    //用户信息
    private String userName;
    private String userIcon;
    private int userAct;
    private int userPostNumber;
    private String registerTime;
    //楼层信息
    private int commentId;
    private int postId;
    private int commentorId;
    private String commentContent;
    private String createTime;
    private int commentFloor;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public int getUserAct() {
        return userAct;
    }

    public void setUserAct(int userAct) {
        this.userAct = userAct;
    }

    public int getUserPostNumber() {
        return userPostNumber;
    }

    public void setUserPostNumber(int userPostNumber) {
        this.userPostNumber = userPostNumber;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCommentorId() {
        return commentorId;
    }

    public void setCommentorId(int commentorId) {
        this.commentorId = commentorId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCommentFloor() {
        return commentFloor;
    }

    public void setCommentFloor(int commentFloor) {
        this.commentFloor = commentFloor;
    }

    @Override
    public String toString() {
        return "CommentPage{" +
                "userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", userAct=" + userAct +
                ", userPostNumber=" + userPostNumber +
                ", registerTime='" + registerTime + '\'' +
                ", commentId=" + commentId +
                ", postId=" + postId +
                ", commentorId=" + commentorId +
                ", commentContent='" + commentContent + '\'' +
                ", createTime='" + createTime + '\'' +
                ", commentFloor=" + commentFloor +
                '}';
    }
}
