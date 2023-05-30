package jd.wjlogin_sdk.model;

/* loaded from: classes.dex */
public class GlobalRegisterInfo {
    private String email;
    private String firstName;
    private String lastName;
    private String mobile;
    private int regType;
    private String reserve;

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getMobile() {
        return this.mobile;
    }

    public int getRegType() {
        return this.regType;
    }

    public String getReserve() {
        return this.reserve;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public void setRegType(int i2) {
        this.regType = i2;
    }

    public void setReserve(String str) {
        this.reserve = str;
    }
}
