package com.clfsys.pojo.page;

/**
 * @author cdy
 * @date 2021/5/4 0:46
 */
public class SearchPostAdmin {
    private String postTitle;
    private String createTime;
    private String createTime2;
    private String lastTime;
    private String lastTime2;
    private int publisherId;
    private int postSectionId;
    private int postThemeId;

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

    public String getCreateTime2() {
        return createTime2;
    }

    public void setCreateTime2(String createTime2) {
        this.createTime2 = createTime2;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastTime2() {
        return lastTime2;
    }

    public void setLastTime2(String lastTime2) {
        this.lastTime2 = lastTime2;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

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

    @Override
    public String toString() {
        return "SearchPostAdmin{" +
                "postTitle='" + postTitle + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createTime2='" + createTime2 + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", lastTime2='" + lastTime2 + '\'' +
                ", publisherId=" + publisherId +
                ", postSectionId=" + postSectionId +
                ", postThemeId=" + postThemeId +
                '}';
    }
}
