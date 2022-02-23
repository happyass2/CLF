package com.clfsys.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author cdy
 * @date 2021/2/3 4:54
 */

public class PostComment {
    private int commentId;
    private int postId;
    private int commentorId;
    private String commentContent;
    private Date createTime;
    private int commentFloor;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
        return "PostComment{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", commentorId=" + commentorId +
                ", commentContent='" + commentContent + '\'' +
                ", createTime=" + createTime +
                ", commentFloor=" + commentFloor +
                '}';
    }
}
