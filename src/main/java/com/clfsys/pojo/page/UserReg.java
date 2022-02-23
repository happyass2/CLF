package com.clfsys.pojo.page;

/**
 * @author cdy
 * @date 2021/3/21 0:26
 */
public class UserReg {
    private String userName;
    private String userPassword;
    private String userMail;
    private String activecode;
    private int userGender;


    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
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

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getActivecode() {
        return activecode;
    }

    public void setActivecode(String activecode) {
        this.activecode = activecode;
    }

    @Override
    public String toString() {
        return "UserReg{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userMail='" + userMail + '\'' +
                ", activecode='" + activecode + '\'' +
                ", userGender=" + userGender +
                '}';
    }
}
