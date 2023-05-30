package com.laser.open.nfc.model.entity;

/* loaded from: classes12.dex */
public class QueryIssueCardInfoResp extends BaseResp {
    private String issueCardId;
    private String seIssuerChannelId;

    public QueryIssueCardInfoResp(int i2, String str) {
        super(i2, str);
    }

    public String getIssueCardId() {
        return this.issueCardId;
    }

    public String getSeIssuerChannelId() {
        return this.seIssuerChannelId;
    }

    public void setIssueCardId(String str) {
        this.issueCardId = str;
    }

    public void setSeIssuerChannelId(String str) {
        this.seIssuerChannelId = str;
    }

    public QueryIssueCardInfoResp() {
    }
}
