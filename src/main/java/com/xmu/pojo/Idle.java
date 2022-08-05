package com.xmu.pojo;

import java.math.BigDecimal;

public class Idle {
    private String idleNumber;
    private String userID;
    private String releaseTime;
    private int status;
    private String category;
    private String brand;
    private String brief;
    private String description;
    private String oldAndNew;
    private String image1;
    private String image2;
    private String image3;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }



    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getOldAndNew() {
        return oldAndNew;
    }

    public void setOldAndNew(String oldAndNew) {
        this.oldAndNew = oldAndNew;
    }

    public String getIdleNumber() {
        return idleNumber;
    }

    public void setIdleNumber(String idleNumber) {
        this.idleNumber = idleNumber;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        return "Idle{" +
                "idleNumber='" + idleNumber + '\'' +
                ", userID='" + userID + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", status=" + status +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", brief='" + brief + '\'' +
                ", description='" + description + '\'' +
                ", oldAndNew='" + oldAndNew + '\'' +
                ", image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                ", image3='" + image3 + '\'' +
                ", price=" + price +
                '}';
    }
}
