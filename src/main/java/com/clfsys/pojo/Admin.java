package com.clfsys.pojo;

/**
 * @author cdy
 * @date 2021/2/4 20:03
 */
public class Admin {
    private int adminId;
    private String admingName;
    private String adminPassword;



    public String getAdmingName() {
        return admingName;
    }

    public void setAdmingName(String admingName) {
        this.admingName = admingName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", admingName='" + admingName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                '}';
    }
}
