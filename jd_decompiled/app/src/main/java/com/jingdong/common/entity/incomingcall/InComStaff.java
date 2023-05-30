package com.jingdong.common.entity.incomingcall;

/* loaded from: classes5.dex */
public class InComStaff {
    private String name;
    private String number;
    private String photo;
    private String text;
    private int type;

    public InComStaff() {
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public String getPhoto() {
        return this.photo;
    }

    public String getText() {
        return this.text;
    }

    public int getType() {
        return this.type;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public void setPhoto(String str) {
        this.photo = str;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public InComStaff(String str, String str2, String str3, String str4) {
        this.number = str;
        this.name = str2;
        this.photo = str3;
        this.text = str4;
    }

    public InComStaff(String str, String str2, String str3, String str4, int i2) {
        this.number = str;
        this.name = str2;
        this.photo = str3;
        this.text = str4;
        this.type = i2;
    }
}
