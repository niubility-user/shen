package com.laser.open.nfc.model.entity;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class TrafficCardEntity implements Serializable {
    private String appletAid;
    private int appletHolder;
    private String cardImg;
    private String cardName;
    private String cardNo;
    private int cardPrice;
    private int cardStatus;
    private String loginNo;

    public String getAppletAid() {
        return this.appletAid;
    }

    public int getAppletHolder() {
        return this.appletHolder;
    }

    public String getCardImg() {
        return this.cardImg;
    }

    public String getCardName() {
        return this.cardName;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public int getCardPrice() {
        return this.cardPrice;
    }

    public int getCardStatus() {
        return this.cardStatus;
    }

    public String getLoginNo() {
        return this.loginNo;
    }

    public void setAppletAid(String str) {
        this.appletAid = str;
    }

    public void setAppletHolder(int i2) {
        this.appletHolder = i2;
    }

    public void setCardImg(String str) {
        this.cardImg = str;
    }

    public void setCardName(String str) {
        this.cardName = str;
    }

    public void setCardNo(String str) {
        this.cardNo = str;
    }

    public void setCardPrice(int i2) {
        this.cardPrice = i2;
    }

    public void setCardStatus(int i2) {
        this.cardStatus = i2;
    }

    public void setLoginNo(String str) {
        this.loginNo = str;
    }

    public String toString() {
        return "TrafficCardEntity{appletAid='" + this.appletAid + "', cardName='" + this.cardName + "', cardPrice=" + this.cardPrice + ", cardStatus=" + this.cardStatus + ", cardImg='" + this.cardImg + "', loginNo='" + this.loginNo + "', cardNo='" + this.cardNo + "', appletHolder=" + this.appletHolder + '}';
    }
}
