package com.huawei.hms.push.ups.entity;

/* loaded from: classes12.dex */
public class CodeResult {
    private int a;
    private String b;

    public CodeResult() {
    }

    public String getReason() {
        return this.b;
    }

    public int getReturnCode() {
        return this.a;
    }

    public void setReason(String str) {
        this.b = str;
    }

    public void setReturnCode(int i2) {
        this.a = i2;
    }

    public CodeResult(int i2) {
        this.a = i2;
    }

    public CodeResult(int i2, String str) {
        this.a = i2;
        this.b = str;
    }
}
