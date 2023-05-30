package com.laser.open.nfc.hw.entity;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class HwSEInfoEntity implements Serializable {
    private String cplc;
    private String seid;
    private int walletVersionCode;

    public String getCplc() {
        return this.cplc;
    }

    public String getSeid() {
        return this.seid;
    }

    public int getWalletVersionCode() {
        return this.walletVersionCode;
    }

    public void setCplc(String str) {
        this.cplc = str;
    }

    public void setSeid(String str) {
        this.seid = str;
    }

    public void setWalletVersionCode(int i2) {
        this.walletVersionCode = i2;
    }
}
