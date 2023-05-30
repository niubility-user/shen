package com.laser.open.nfc.model.entity;

/* loaded from: classes12.dex */
public class GetDeleteSignInfoResp extends BaseResp {
    private String signData;
    private String timestamp;

    public GetDeleteSignInfoResp(int i2, String str) {
        super(i2, str);
    }

    public String getSignData() {
        return this.signData;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setSignData(String str) {
        this.signData = str;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public GetDeleteSignInfoResp() {
    }
}
