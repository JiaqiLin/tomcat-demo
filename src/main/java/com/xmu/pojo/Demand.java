package com.xmu.pojo;

import java.math.BigDecimal;
import java.util.Arrays;

public class Demand {
    private String demandNumber;
    private String userID;
    private String releaseTime;
    private String category;
    private String brief;
    private String description;
    private BigDecimal lowerPrice;
    private BigDecimal upperPrice;

    private int status;

    private String image1;

    private String image2;

    private String image3;


    public String getDemandNumber() {
        return demandNumber;
    }

    public void setDemandNumber(String demandNumber) {
        this.demandNumber = demandNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getLowerPrice() {
        return lowerPrice;
    }

    public void setLowerPrice(BigDecimal lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public BigDecimal getUpperPrice() {
        return upperPrice;
    }

    public void setUpperPrice(BigDecimal upperPrice) {
        this.upperPrice = upperPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "demandNumber='" + demandNumber + '\'' +
                ", userID='" + userID + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", category='" + category + '\'' +
                ", brief='" + brief + '\'' +
                ", description='" + description + '\'' +
                ", lowerPrice=" + lowerPrice +
                ", upperPrice=" + upperPrice +
                ", status=" + status +
                ", image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                ", image3='" + image3 + '\'' +
                '}';
    }
}
