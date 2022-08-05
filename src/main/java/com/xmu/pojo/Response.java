package com.xmu.pojo;

public class Response {

    private String demandNumber;
    private String responseTime;
    private User user;

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDemandNumber() {
        return demandNumber;
    }

    public void setDemandNumber(String demandNumber) {
        this.demandNumber = demandNumber;
    }

    @Override
    public String toString() {
        return "Response{" +
                "demandNumber='" + demandNumber + '\'' +
                ", responseTime='" + responseTime + '\'' +
                ", user=" + user +
                '}';
    }
}
