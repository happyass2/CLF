package com.clfsys.pojo.page;

/**
 * @author cdy
 * @date 2021/5/7 1:49
 */
public class ModeratorPage {
    private int moderatorId;
    private int userId;
    private int sectionId;
    private String userName;
    private String sectionName;
    private String userMail;
    private String lastLoginIp;
    private String lastLoginTime;

    public int getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(int moderatorId) {
        this.moderatorId = moderatorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
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

    @Override
    public String toString() {
        return "Moderator{" +
                "moderatorId=" + moderatorId +
                ", userId=" + userId +
                ", sectionId=" + sectionId +
                ", userName='" + userName + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", userMail='" + userMail + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                '}';
    }
}
