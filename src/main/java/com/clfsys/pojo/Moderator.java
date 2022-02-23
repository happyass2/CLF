package com.clfsys.pojo;

/**
 * @author cdy
 * @date 2021/5/7 2:04
 */
public class Moderator {

    private int moderatorId;
    private int userId;
    private int sectionId;

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

    @Override
    public String toString() {
        return "Moderator{" +
                "moderatorId=" + moderatorId +
                ", userId=" + userId +
                ", sectionId=" + sectionId +
                '}';
    }
}
