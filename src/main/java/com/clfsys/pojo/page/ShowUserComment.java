package com.clfsys.pojo.page;

/**
 * @author cdy
 * @date 2021/4/17 20:52
 */
public class ShowUserComment {
    private String userName;
    private int postId;
    private int commentFloor;
    private String commentContent;
    private int commentId;
    private int commentorId;
    private String postTitle;
    private String createTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }


    public int getCommentFloor() {
        return commentFloor;
    }

    public void setCommentFloor(int commentFloor) {
        this.commentFloor = commentFloor;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getCommentorId() {
        return commentorId;
    }

    public void setCommentorId(int commentorId) {
        this.commentorId = commentorId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "ShowUserComment{" +
                "userName='" + userName + '\'' +
                ", postId=" + postId +
                ", commentFloor=" + commentFloor +
                ", commentContent='" + commentContent + '\'' +
                ", commentId=" + commentId +
                ", commentorId=" + commentorId +
                ", postTitle='" + postTitle + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
