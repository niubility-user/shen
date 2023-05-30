package com.jingdong.common.recommend.entity;

/* loaded from: classes6.dex */
public class FeedBackReason {
    public String closeLog;
    public String closeUrl;
    public String icon;
    public int id;
    public boolean isCheck;
    public String name;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setCheck(boolean z) {
        this.isCheck = z;
    }

    public void setId(int i2) {
        this.id = i2;
    }

    public void setName(String str) {
        this.name = str;
    }
}
