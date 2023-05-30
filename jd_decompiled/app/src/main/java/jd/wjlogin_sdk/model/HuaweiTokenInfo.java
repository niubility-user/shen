package jd.wjlogin_sdk.model;

/* loaded from: classes.dex */
public class HuaweiTokenInfo {
    private String accessToken;
    private String appId;
    private int expireTime = 0;
    private String refreshToken = "";
    private String openid = "";
    private String scope = "";

    public HuaweiTokenInfo(String str, String str2) {
        this.appId = str;
        this.accessToken = str2;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getAppId() {
        return this.appId;
    }

    public int getExpireTime() {
        return this.expireTime;
    }

    public String getOpenid() {
        return this.openid;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public String getScope() {
        return this.scope;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public HuaweiTokenInfo() {
    }
}
