package jd.wjlogin_sdk.model;

/* loaded from: classes.dex */
public class OneKeyTokenInfo {
    private short operateType;
    private String accessToken = "";
    private String reserve = "";

    public String getAccessToken() {
        String str = this.accessToken;
        return str != null ? str : "";
    }

    public short getOperateType() {
        return this.operateType;
    }

    public String getReserve() {
        String str = this.reserve;
        return str != null ? str : "";
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setOperateType(short s) {
        this.operateType = s;
    }

    public void setReserve(String str) {
        this.reserve = str;
    }
}
