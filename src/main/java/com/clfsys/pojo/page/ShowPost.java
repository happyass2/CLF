package com.clfsys.pojo.page;

/**
 * @author cdy
 * @date 2021/3/31 17:28
 */
public class ShowPost {
    private int postId;
    private String img;
    private String theme;
    private String postTitle;
    private String username;
    private String createtime;
    private String lasttime;
    private int watchnumber;
    private int commentnumber;
    private int themeId;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public int getWatchnumber() {
        return watchnumber;
    }

    public void setWatchnumber(int watchnumber) {
        this.watchnumber = watchnumber;
    }

    public int getCommentnumber() {
        return commentnumber;
    }

    public void setCommentnumber(int commentnumber) {
        this.commentnumber = commentnumber;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }


    @Override
    public String toString() {
        return "ShowPost{" +
                "postId=" + postId +
                ", img='" + img + '\'' +
                ", theme='" + theme + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", username='" + username + '\'' +
                ", createtime='" + createtime + '\'' +
                ", lasttime='" + lasttime + '\'' +
                ", watchnumber=" + watchnumber +
                ", commentnumber=" + commentnumber +
                ", themeId=" + themeId +
                '}';
    }
}
