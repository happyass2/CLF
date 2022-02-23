package com.clfsys.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mysql.jdbc.Blob;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

/**
 * @author cdy
 * @date 2021/2/3 2:34
 */
//@JsonIgnoreProperties(value = {"postContent"})  //不需要序列化
public class Post {

    private int postId;
    private int postThemeId;
    private String postContent;
    private int postSectionId;
    private int publisherId;
    private Date createTime;
    private int commentNumber;
    private int collectNumber;
    private int status;
    private int elitePost;
    private int topPost;
    private String postTitle;
    private int watchNumber;
    private Date lastTime;
    private String postIcon;



    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getWatchNumber() {
        return watchNumber;
    }

    public void setWatchNumber(int watchNumber) {
        this.watchNumber = watchNumber;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public int getPostThemeId() {
        return postThemeId;
    }

    public void setPostThemeId(int postThemeId) {
        this.postThemeId = postThemeId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getPostSectionId() {
        return postSectionId;
    }

    public void setPostSectionId(int postSectionId) {
        this.postSectionId = postSectionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public int getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(int collectNumber) {
        this.collectNumber = collectNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getElitePost() {
        return elitePost;
    }

    public void setElitePost(int elitePost) {
        this.elitePost = elitePost;
    }

    public int getTopPost() {
        return topPost;
    }

    public void setTopPost(int topPost) {
        this.topPost = topPost;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostIcon() {
        return postIcon;
    }

    public void setPostIcon(String postIcon) {
        this.postIcon = postIcon;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postThemeId=" + postThemeId +
                ", postContent=" + postContent +
                ", postSectionId=" + postSectionId +
                ", publisherId=" + publisherId +
                ", createTime=" + createTime +
                ", commentNumber=" + commentNumber +
                ", collectNumber=" + collectNumber +
                ", status=" + status +
                ", elitePost=" + elitePost +
                ", topPost=" + topPost +
                ", postTitle='" + postTitle + '\'' +
                ", watchNumber=" + watchNumber +
                ", lastTime=" + lastTime +
                ", postIcon='" + postIcon + '\'' +
                '}';
    }
}
