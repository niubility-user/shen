package cn.com.union.fido.bean.db;

/* loaded from: classes.dex */
public class SignCounterEntity {
    private String aaid;
    private int id;
    private String keyID;
    private int signCounter;
    private String userName;

    public SignCounterEntity() {
    }

    public SignCounterEntity(String str, String str2, String str3, int i2) {
        this.aaid = str;
        this.keyID = str2;
        this.userName = str3;
        this.signCounter = i2;
    }

    public String getAaid() {
        return this.aaid;
    }

    public int getId() {
        return this.id;
    }

    public String getKeyID() {
        return this.keyID;
    }

    public int getSignCounter() {
        return this.signCounter;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setAaid(String str) {
        this.aaid = str;
    }

    public void setId(int i2) {
        this.id = i2;
    }

    public void setKeyID(String str) {
        this.keyID = str;
    }

    public void setSignCounter(int i2) {
        this.signCounter = i2;
    }

    public void setUserName(String str) {
        this.userName = str;
    }
}
