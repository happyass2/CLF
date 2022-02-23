package com.clfsys.pojo.page;

/**
 * @author cdy
 * @date 2021/4/7 21:36
 */
public class CommentTitle {
    private int themeId;
    private int sectionId;
    private int watchNumber;
    private int commentNumber;
    private String postTitle;
    private String themeName;

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
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

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    @Override
    public String toString() {
        return "CommentTitle{" +
                "themeId=" + themeId +
                ", sectionId=" + sectionId +
                ", watchNumber=" + watchNumber +
                ", commentNumber=" + commentNumber +
                ", postTitle='" + postTitle + '\'' +
                ", themeName='" + themeName + '\'' +
                '}';
    }
}
