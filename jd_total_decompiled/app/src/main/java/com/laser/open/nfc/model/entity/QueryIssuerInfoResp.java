package com.laser.open.nfc.model.entity;

/* loaded from: classes12.dex */
public class QueryIssuerInfoResp extends BaseResp {
    private String seIssuerId;

    public QueryIssuerInfoResp(int i2, String str) {
        super(i2, str);
    }

    public String getSeIssuerId() {
        return this.seIssuerId;
    }

    public void setSeIssuerId(String str) {
        this.seIssuerId = str;
    }

    public QueryIssuerInfoResp() {
    }
}
