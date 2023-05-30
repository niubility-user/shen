package com.jd.lib.cashier.sdk.core.paychannel.wxpay.entity;

/* loaded from: classes14.dex */
public class WXPayInfoEntity {
    private String nonceStr;
    private String packageValue;
    private String partnerId;
    private String payEnum;
    private String prepayId;
    private String sign;
    private String timeStamp;

    public String getNonceStr() {
        return this.nonceStr;
    }

    public String getPackage() {
        return this.packageValue;
    }

    public String getPartnerId() {
        return this.partnerId;
    }

    public String getPayEnum() {
        return this.payEnum;
    }

    public String getPrepayId() {
        return this.prepayId;
    }

    public String getSign() {
        return this.sign;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setNonceStr(String str) {
        this.nonceStr = str;
    }

    public void setPackage(String str) {
        this.packageValue = str;
    }

    public void setPartnerId(String str) {
        this.partnerId = str;
    }

    public void setPayEnum(String str) {
        this.payEnum = str;
    }

    public void setPrepayId(String str) {
        this.prepayId = str;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setTimeStamp(String str) {
        this.timeStamp = str;
    }
}
