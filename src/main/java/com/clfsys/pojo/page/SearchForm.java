package com.clfsys.pojo.page;

/**
 * @author cdy
 * @date 2021/4/30 18:05
 */
public class SearchForm {
    private String userName2;
    private String userMail2;
    private String userGender2;
    private String regTime;
    private String regTime2;
    private String loginTime;
    private String loginTime2;
    private String postNumber2;
    private String loginIp2;

    public String getUserName2() {
        return userName2;
    }

    public void setUserName2(String userName2) {
        this.userName2 = userName2;
    }

    public String getUserMail2() {
        return userMail2;
    }

    public void setUserMail2(String userMail2) {
        this.userMail2 = userMail2;
    }

    public String getUserGender2() {
        return userGender2;
    }

    public void setUserGender2(String userGender2) {
        this.userGender2 = userGender2;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getRegTime2() {
        return regTime2;
    }

    public void setRegTime2(String regTime2) {
        this.regTime2 = regTime2;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginTime2() {
        return loginTime2;
    }

    public void setLoginTime2(String loginTime2) {
        this.loginTime2 = loginTime2;
    }

    public String getPostNumber2() {
        return postNumber2;
    }

    public void setPostNumber2(String postNumber2) {
        this.postNumber2 = postNumber2;
    }

    public String getLoginIp2() {
        return loginIp2;
    }

    public void setLoginIp2(String loginIp2) {
        this.loginIp2 = loginIp2;
    }

    @Override
    public String toString() {
        return "SearchForm{" +
                "userName2='" + userName2 + '\'' +
                ", userMail2='" + userMail2 + '\'' +
                ", userGender2=" + userGender2 +
                ", regTime='" + regTime + '\'' +
                ", regTime2='" + regTime2 + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", loginTime2='" + loginTime2 + '\'' +
                ", postNumber2=" + postNumber2 +
                ", loginIp2='" + loginIp2 + '\'' +
                '}';
    }
}
