package jd.wjlogin_sdk.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class WJUserInfo implements Serializable {
    private String Account = null;
    private String Pin = null;
    private String countryCode;
    private boolean isCurrentMainUser;
    private String jsonReserve;
    private String nickName;
    private String phoneNum;
    private String userIconUrl;

    public String getAccount() {
        return this.Account;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getJsonReserve() {
        return this.jsonReserve;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public String getPin() {
        return this.Pin;
    }

    public String getUserIconUrl() {
        return this.userIconUrl;
    }

    public boolean isCurrentMainUser() {
        return this.isCurrentMainUser;
    }

    public void setAccount(String str) {
        this.Account = str;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setCurrentMainUser(boolean z) {
        this.isCurrentMainUser = z;
    }

    public void setJsonReserve(String str) {
        this.jsonReserve = str;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public void setPhoneNum(String str) {
        this.phoneNum = str;
    }

    public void setPin(String str) {
        this.Pin = str;
    }

    public void setUserIconUrl(String str) {
        this.userIconUrl = str;
    }
}
