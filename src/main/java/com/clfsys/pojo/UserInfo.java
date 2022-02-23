package com.clfsys.pojo;

import java.util.Date;

/**
 * @author cdy
 * @date 2021/2/1 17:38
 */
public class UserInfo {
    private int userId;
    private int userGender;

    private String userMail;
    private String userName;
    private String userPassword;
    private Date lastLoginTime;
    private String lastLoginIp;
    private int userAct;
    private String userIcon;
    private Date registerTime;
    private String activeCode;
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
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

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
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
        return "UserInfo{" +
                "userId=" + userId +
                ", userGender=" + userGender +
                ", userMail='" + userMail + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", userAct=" + userAct +
                ", userIcon='" + userIcon + '\'' +
                ", registerTime=" + registerTime +
                ", activeCode='" + activeCode + '\'' +
                ", userPostNumber=" + userPostNumber +
                ", collectionNumber=" + collectionNumber +
                '}';
    }
}
