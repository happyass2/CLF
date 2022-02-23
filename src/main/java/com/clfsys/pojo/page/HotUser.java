package com.clfsys.pojo.page;

/**
 * @author cdy
 * @date 2021/4/6 4:23
 */
public class HotUser {
    private String userIcon;
    private int userId;
    private String userName;

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "HotUser{" +
                "userIcon='" + userIcon + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
