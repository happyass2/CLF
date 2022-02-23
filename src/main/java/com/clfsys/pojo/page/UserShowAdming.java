package com.clfsys.pojo.page;

import java.util.Date;

/**
 * @author cdy
 * @date 2021/4/26 13:33
 */
public class UserShowAdming {
    private int userId;
    private int userGender;
    private String userGender22;
    private String userMail;
    private String userName;
    private String lastLoginTime;
    private String lastLoginIp;
    private int userAct;
    private String userIcon;
    private String registerTime;
    private int userPostNumber;
    private int collectionNumber;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public String getUserGender22() {
        return userGender22;
    }

    public void setUserGender22(String userGender22) {
        this.userGender22 = userGender22;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public int getUserAct() {
        return userAct;
    }

    public void setUserAct(int userAct) {
        this.userAct = userAct;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public int getUserPostNumber() {
        return userPostNumber;
    }

    public void setUserPostNumber(int userPostNumber) {
        this.userPostNumber = userPostNumber;
    }

    public int getCollectionNumber() {
        return collectionNumber;
    }

    public void setCollectionNumber(int collectionNumber) {
        this.collectionNumber = collectionNumber;
    }

    @Override
    public String toString() {
        return "UserShowAdming{" +
                "userId=" + userId +
                ", userGender=" + userGender +
                ", userGender22='" + userGender22 + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userName='" + userName + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", userAct=" + userAct +
                ", userIcon='" + userIcon + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", userPostNumber=" + userPostNumber +
                ", collectionNumber=" + collectionNumber +
                '}';
    }
}
