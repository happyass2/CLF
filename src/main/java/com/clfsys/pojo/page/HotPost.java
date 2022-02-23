package com.clfsys.pojo.page;

import java.util.Date;

/**
 * @author cdy
 * @date 2021/4/6 4:23
 */
public class HotPost {
    private int postSectionId;
    private int postThemeId;
    private int postId;
    private String postTitle;
    private Date createTime;
    private String themeName;

    public int getPostSectionId() {
        return postSectionId;
    }

    public void setPostSectionId(int postSectionId) {
        this.postSectionId = postSectionId;
    }

    public int getPostThemeId() {
        return postThemeId;
    }

    public void setPostThemeId(int postThemeId) {
        this.postThemeId = postThemeId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    @Override
    public String toString() {
        return "HotPost{" +
                "postSectionId=" + postSectionId +
                ", postThemeId=" + postThemeId +
                ", postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", createTime=" + createTime +
                ", themeName='" + themeName + '\'' +
                '}';
    }
}
