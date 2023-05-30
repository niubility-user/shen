package com.jd.verify.j;

import android.text.TextUtils;

/* loaded from: classes18.dex */
public class c {
    private String a = "https://h5.360buyimg.com/jcap/html/app-captcha-v2.html";
    private String b = "http://beta-jcap.m.jd.com/dist/app-captcha-v2.html";

    /* renamed from: c  reason: collision with root package name */
    private String f7179c = "";

    private String b() {
        return com.jd.verify.a.c() ? this.b : this.a;
    }

    public void a(String str) {
        this.f7179c = str;
    }

    public String c() {
        if (TextUtils.isEmpty(this.f7179c)) {
            return b();
        }
        return this.f7179c;
    }

    public static c a() {
        return new c();
    }
}
