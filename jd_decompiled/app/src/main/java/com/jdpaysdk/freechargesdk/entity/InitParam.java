package com.jdpaysdk.freechargesdk.entity;

import java.io.Serializable;

/* loaded from: classes18.dex */
public class InitParam implements Serializable {
    private String partnerId;
    private String phoneNum;
    private String requestUrl;

    public String getPartnerId() {
        return this.partnerId;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public String getRequestUrl() {
        return this.requestUrl;
    }

    public void setPartnerId(String str) {
        this.partnerId = str;
    }

    public void setPhoneNum(String str) {
        this.phoneNum = str;
    }

    public void setRequestUrl(String str) {
        this.requestUrl = str;
    }
}
