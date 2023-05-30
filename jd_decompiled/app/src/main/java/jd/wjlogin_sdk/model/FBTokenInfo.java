package jd.wjlogin_sdk.model;

/* loaded from: classes.dex */
public class FBTokenInfo {
    private String appid;
    private String declinedPermissions;
    private int expirationDate;
    private String permissions;
    private int refreshDate;
    private String token;
    private String userid;

    public String getAppid() {
        return this.appid;
    }

    public String getDeclinedPermissions() {
        return this.declinedPermissions;
    }

    public int getExpirationDate() {
        return this.expirationDate;
    }

    public String getPermissions() {
        return this.permissions;
    }

    public int getRefreshDate() {
        return this.refreshDate;
    }

    public String getToken() {
        return this.token;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setAppid(String str) {
        this.appid = str;
    }

    public void setDeclinedPermissions(String str) {
        this.declinedPermissions = str;
    }

    public void setExpirationDate(int i2) {
        this.expirationDate = i2;
    }

    public void setPermissions(String str) {
        this.permissions = str;
    }

    public void setRefreshDate(int i2) {
        this.refreshDate = i2;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setUserid(String str) {
        this.userid = str;
    }
}
