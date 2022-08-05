package com.xmu.pojo;

public class User {
    private String userID;
    private String userName;
    private String password;

    private String name;
    private String academy;
    private String major;

    private String level;
    private String phoneNumber;
    private String QQ;
    private String WeChat;
    private String address;
    private String completeOrder;
    private String creditScore;
    private String brief;

    private String studentCardFront;

    private String studentCardContrary;

    private int verifyStatus;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getWeChat() {
        return WeChat;
    }

    public void setWeChat(String WeChat) {this.WeChat = WeChat;}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
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

    public int getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(int verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", academy='" + academy + '\'' +
                ", major='" + major + '\'' +
                ", level='" + level + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", QQ='" + QQ + '\'' +
                ", WeChat='" + WeChat + '\'' +
                ", address='" + address + '\'' +
                ", completeOrder='" + completeOrder + '\'' +
                ", creditScore='" + creditScore + '\'' +
                ", brief='" + brief + '\'' +
                ", studentCardFront='" + studentCardFront + '\'' +
                ", studentCardContrary='" + studentCardContrary + '\'' +
                ", verifyStatus=" + verifyStatus +
                '}';
    }
}
