package com.xmu.pojo;

public class VerifyInfo {
    private String userID;
    private String studentCardFront;
    private String studentCardContrary;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStudentCardFront() {
        return studentCardFront;
    }

    public void setStudentCardFront(String studentCardFront) {
        this.studentCardFront = studentCardFront;
    }

    public String getStudentCardContrary() {
        return studentCardContrary;
    }

    public void setStudentCardContrary(String studentCardContrary) {
        this.studentCardContrary = studentCardContrary;
    }

    @Override
    public String toString() {
        return "VerifyInfo{" +
                "userID='" + userID + '\'' +
                ", studentCardFront='" + studentCardFront + '\'' +
                ", studentCardContrary='" + studentCardContrary + '\'' +
                '}';
    }
}
