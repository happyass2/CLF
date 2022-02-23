package com.clfsys.pojo.page;

/**
 * @author cdy
 * @date 2021/5/2 4:04
 */
public class AdminPost {
    int postId;
    String postTitle;
    String postIcon;
    int watchNumber;
    int collectNumber;
    int commentNumber;
    String createTime;
    String lastTime;
    int topPost;
    String topPostDesc;
    int elitePost;
    String elitePostDesc;
    String sectionName;
    String themeName;
    String userName;
    int publisherId;

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

    public String getPostIcon() {
        return postIcon;
    }

    public void setPostIcon(String postIcon) {
        this.postIcon = postIcon;
    }

    public int getWatchNumber() {
        return watchNumber;
    }

    public void setWatchNumber(int watchNumber) {
        this.watchNumber = watchNumber;
    }

    public int getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(int collectNumber) {
        this.collectNumber = collectNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public int getTopPost() {
        return topPost;
    }

    public void setTopPost(int topPost) {
        this.topPost = topPost;
    }

    public int getElitePost() {
        return elitePost;
    }

    public void setElitePost(int elitePost) {
        this.elitePost = elitePost;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getTopPostDesc() {
        return topPostDesc;
    }

    public void setTopPostDesc(String topPostDesc) {
        this.topPostDesc = topPostDesc;
    }

    public String getElitePostDesc() {
        return elitePostDesc;
    }

    public void setElitePostDesc(String elitePostDesc) {
        this.elitePostDesc = elitePostDesc;
    }

    @Override
    public String toString() {
        return "AdminPost{" +
                "postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", postIcon='" + postIcon + '\'' +
                ", watchNumber=" + watchNumber +
                ", collectNumber=" + collectNumber +
                ", commentNumber=" + commentNumber +
                ", createTime='" + createTime + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", topPost=" + topPost +
                ", topPostDesc='" + topPostDesc + '\'' +
                ", elitePost=" + elitePost +
                ", elitePostDesc='" + elitePostDesc + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", themeName='" + themeName + '\'' +
                ", userName='" + userName + '\'' +
                ", publisherId=" + publisherId +
                '}';
    }
}
