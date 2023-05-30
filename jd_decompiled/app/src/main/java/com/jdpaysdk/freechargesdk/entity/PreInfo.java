package com.jdpaysdk.freechargesdk.entity;

import com.laser.open.nfc.model.entity.BaseResp;
import java.io.Serializable;

/* loaded from: classes18.dex */
public class PreInfo extends BaseResp implements Serializable {
    private String android_version;
    private String app_name;
    private String app_version;
    private boolean isHaveNFC;
    private boolean isOpenNFC;
    private String mobile_type;
    private String sdk_version;
    private String wallet_version;

    public String getAndroid_version() {
        return this.android_version;
    }

    public String getApp_name() {
        return this.app_name;
    }

    public String getApp_version() {
        return this.app_version;
    }

    public String getMobile_type() {
        return this.mobile_type;
    }

    public String getSdk_version() {
        return this.sdk_version;
    }

    public String getWallet_version() {
        return this.wallet_version;
    }

    public boolean isHaveNFC() {
        return this.isHaveNFC;
    }

    public boolean isOpenNFC() {
        return this.isOpenNFC;
    }

    public void setAndroid_version(String str) {
        this.android_version = str;
    }

    public void setApp_name(String str) {
        this.app_name = str;
    }

    public void setApp_version(String str) {
        this.app_version = str;
    }

    public void setHaveNFC(boolean z) {
        this.isHaveNFC = z;
    }

    public void setMobile_type(String str) {
        this.mobile_type = str;
    }

    public void setOpenNFC(boolean z) {
        this.isOpenNFC = z;
    }

    public void setSdk_version(String str) {
        this.sdk_version = str;
    }

    public void setWallet_version(String str) {
        this.wallet_version = str;
    }
}
