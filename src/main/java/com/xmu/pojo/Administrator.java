package com.xmu.pojo;

public class Administrator {

    private String adminID;
    private String password;

    public String getAdministratorID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "adminID='" + adminID + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
