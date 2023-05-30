package com.laser.open.nfc.model.entity;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class TrafficCardTradeInfoEntity implements Serializable {
    private String transAmount;
    private String transDate;
    private int transType;

    public String getTransAmount() {
        return this.transAmount;
    }

    public String getTransDate() {
        return this.transDate;
    }

    public int getTransType() {
        return this.transType;
    }

    public void setTransAmount(String str) {
        this.transAmount = str;
    }

    public void setTransDate(String str) {
        this.transDate = str;
    }

    public void setTransType(int i2) {
        this.transType = i2;
    }

    public String toString() {
        return "TrafficCardTradeInfoEntity{transType='" + this.transType + "', transAmount='" + this.transAmount + "', transDate='" + this.transDate + "'}";
    }
}
