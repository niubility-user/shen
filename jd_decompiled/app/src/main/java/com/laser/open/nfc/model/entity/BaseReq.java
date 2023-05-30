package com.laser.open.nfc.model.entity;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class BaseReq implements Serializable {
    private String appVersion;
    private String basePhoneVersion;
    private String callAppHash;
    private String callAppPackageName;
    private String cplc;
    private String deviceModel;
    private String msisdn;
    private String partnerId;
    private String phoneModel;
    private String phoneOsVersion;
    private int phoneType;
    private String seid;
    private String version;

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getBasePhoneVersion() {
        return this.basePhoneVersion;
    }

    public String getCallAppHash() {
        return this.callAppHash;
    }

    public String getCallAppPackageName() {
        return this.callAppPackageName;
    }

    public String getCplc() {
        return this.cplc;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getMsisdn() {
        return this.msisdn;
    }

    public String getPartnerId() {
        return this.partnerId;
    }

    public String getPhoneModel() {
        return this.phoneModel;
    }

    public String getPhoneOsVersion() {
        return this.phoneOsVersion;
    }

    public int getPhoneType() {
        return this.phoneType;
    }

    public String getSeid() {
        return this.seid;
    }

    public String getVersion() {
        return this.version;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setBasePhoneVersion(String str) {
        this.basePhoneVersion = str;
    }

    public void setCallAppHash(String str) {
        this.callAppHash = str;
    }

    public void setCallAppPackageName(String str) {
        this.callAppPackageName = str;
    }

    public void setCplc(String str) {
        this.cplc = str;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public void setMsisdn(String str) {
        this.msisdn = str;
    }

    public void setPartnerId(String str) {
        this.partnerId = str;
    }

    public void setPhoneModel(String str) {
        this.phoneModel = str;
    }

    public void setPhoneOsVersion(String str) {
        this.phoneOsVersion = str;
    }

    public void setPhoneType(int i2) {
        this.phoneType = i2;
    }

    public void setSeid(String str) {
        this.seid = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
