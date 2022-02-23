package com.clfsys.pojo.page;

/**
 * @author cdy
 * @date 2021/3/30 16:42
 */
public class EditPost {
    private String postTitle;
    private String postContent;
    private int postThemeId;
    private int postSection;

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

    public int getPostThemeId() {
        return postThemeId;
    }

    public void setPostThemeId(int postThemeId) {
        this.postThemeId = postThemeId;
    }

    public int getPostSection() {
        return postSection;
    }

    public void setPostSection(int postSection) {
        this.postSection = postSection;
    }

    @Override
    public String toString() {
        return "EditPost{" +
                "postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postThemeId=" + postThemeId +
                ", postSection=" + postSection +
                '}';
    }
}
