package com.clfsys.pojo.page;

import java.util.Date;

/**
 * @author cdy
 * @date 2021/4/14 15:47
 */
public class SearchPost {
    private int postId;
    private String userName;
    private String postTitle;
    private String postContent;
    private String sectionName;
    private int watchNumber;
    private int commentNumber;
    private Date createTime;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getWatchNumber() {
        return watchNumber;
    }

    public void setWatchNumber(int watchNumber) {
        this.watchNumber = watchNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SearchPost{" +
                "postId=" + postId +
                ", userName='" + userName + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", watchNumber=" + watchNumber +
                ", commentNumber=" + commentNumber +
                ", createTime=" + createTime +
                '}';
    }
}
