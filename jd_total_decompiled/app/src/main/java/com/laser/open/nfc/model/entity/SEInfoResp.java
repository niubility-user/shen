package com.laser.open.nfc.model.entity;

/* loaded from: classes12.dex */
public class SEInfoResp extends BaseResp {
    private String cplc;
    private String seid;
    private int walletVersionCode;

    public SEInfoResp(int i2, String str) {
        super(i2, str);
    }

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

    public SEInfoResp() {
    }
}
