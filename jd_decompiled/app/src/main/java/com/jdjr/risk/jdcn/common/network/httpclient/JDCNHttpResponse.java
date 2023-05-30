package com.jdjr.risk.jdcn.common.network.httpclient;

/* loaded from: classes18.dex */
public class JDCNHttpResponse {
    String body;
    int code;
    String message;

    public String body() {
        return this.body;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public boolean success() {
        int i2 = this.code;
        return i2 >= 200 && i2 < 300;
    }
}
