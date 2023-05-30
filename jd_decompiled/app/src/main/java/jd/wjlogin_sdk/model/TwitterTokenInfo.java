package jd.wjlogin_sdk.model;

/* loaded from: classes.dex */
public class TwitterTokenInfo {
    private String accessToken;
    private String accessTokenSecret;
    private String appid;
    private String userID;
    private String userName;

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getAccessTokenSecret() {
        return this.accessTokenSecret;
    }

    public String getAppid() {
        return this.appid;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setAccessTokenSecret(String str) {
        this.accessTokenSecret = str;
    }

    public void setAppid(String str) {
        this.appid = str;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public void setUserName(String str) {
        this.userName = str;
    }
}
