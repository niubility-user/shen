package com.jd.aips.common.network.httpclient;

/* loaded from: classes12.dex */
public class JDCNHttpResponse {
    int a;
    String b;

    /* renamed from: c  reason: collision with root package name */
    String f1584c;

    public String body() {
        return this.f1584c;
    }

    public int code() {
        return this.a;
    }

    public String message() {
        return this.b;
    }

    public boolean success() {
        int i2 = this.a;
        return i2 >= 200 && i2 < 300;
    }
}
