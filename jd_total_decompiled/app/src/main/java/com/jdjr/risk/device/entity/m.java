package com.jdjr.risk.device.entity;

import com.tencent.connect.common.Constants;

/* loaded from: classes18.dex */
public class m extends a {
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f7439c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f7440e;

    /* renamed from: f  reason: collision with root package name */
    private String f7441f;

    /* renamed from: g  reason: collision with root package name */
    private String f7442g;

    public void a(String str) {
        this.b = str;
        this.a.put(Constants.PARAM_PLATFORM, str);
    }

    public void b(String str) {
        this.f7439c = str;
        this.a.put("linux_version", str);
    }

    public void c(String str) {
        this.f7440e = str;
        this.a.put("userAgent", str);
    }

    public void d(String str) {
        this.f7441f = str;
        this.a.put("fingerprints", str);
    }

    public void e(String str) {
        this.d = str;
        this.a.put("webviewUA", str);
    }

    public void f(String str) {
        this.f7442g = str;
        this.a.put("fingerprintCustom", str);
    }
}
