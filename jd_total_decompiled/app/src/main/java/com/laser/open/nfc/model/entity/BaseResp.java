package com.laser.open.nfc.model.entity;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class BaseResp implements Serializable {
    private String desc;
    private int status;

    public BaseResp(int i2, String str) {
        this.status = i2;
        this.desc = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getStatus() {
        return this.status;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void setStatus(int i2) {
        this.status = i2;
    }

    public String toString() {
        return "BaseResp{status=" + this.status + ", desc='" + this.desc + "'}";
    }

    public BaseResp() {
    }
}
