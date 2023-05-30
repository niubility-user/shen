package cn.com.union.fido.bean.db;

/* loaded from: classes.dex */
public class AuthenticationEntity {
    private String aaid;
    private String appID;
    private String callerID;
    private String currentTimestamp;
    private int id;
    private String keyHandle;
    private String keyID;
    private String status;
    private String userName;

    public AuthenticationEntity() {
    }

    public AuthenticationEntity(String str, String str2, String str3) {
        this.aaid = str;
        this.keyID = str2;
        this.status = str3;
    }

    public AuthenticationEntity(String str, String str2, String str3, String str4, String str5, String str6) {
        this.callerID = str;
        this.appID = str2;
        this.keyHandle = str3;
        this.keyID = str4;
        this.currentTimestamp = str5;
        this.status = str6;
    }

    public AuthenticationEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.aaid = str;
        this.callerID = str2;
        this.appID = str3;
        this.keyHandle = str4;
        this.keyID = str5;
        this.currentTimestamp = str6;
        this.status = str7;
    }

    public AuthenticationEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.userName = str;
        this.aaid = str2;
        this.callerID = str3;
        this.appID = str4;
        this.keyHandle = str5;
        this.keyID = str6;
        this.currentTimestamp = str7;
        this.status = str8;
    }

    public String getAaid() {
        return this.aaid;
    }

    public String getAppID() {
        return this.appID;
    }

    public String getCallerID() {
        return this.callerID;
    }

    public String getCurrentTimestamp() {
        return this.currentTimestamp;
    }

    public int getId() {
        return this.id;
    }

    public String getKeyHandle() {
        return this.keyHandle;
    }

    public String getKeyID() {
        return this.keyID;
    }

    public String getStatus() {
        return this.status;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setAaid(String str) {
        this.aaid = str;
    }

    public void setAppID(String str) {
        this.appID = str;
    }

    public void setCallerID(String str) {
        this.callerID = str;
    }

    public void setCurrentTimestamp(String str) {
        this.currentTimestamp = str;
    }

    public void setId(int i2) {
        this.id = i2;
    }

    public void setKeyHandle(String str) {
        this.keyHandle = str;
    }

    public void setKeyID(String str) {
        this.keyID = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String toString() {
        return "--------- AuthenticationEntity{id=" + this.id + ", callerID='" + this.callerID + "', appID='" + this.appID + "', keyHandle='" + this.keyHandle + "', keyID='" + this.keyID + "', currentTimestamp='" + this.currentTimestamp + "', status='" + this.status + "', aaid='" + this.aaid + "', userName='" + this.userName + "'}";
    }
}
