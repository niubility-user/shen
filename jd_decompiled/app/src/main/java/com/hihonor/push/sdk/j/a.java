package com.hihonor.push.sdk.j;

import android.text.TextUtils;

/* loaded from: classes12.dex */
public class a {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f1099c;
    private String d;

    public boolean a() {
        return ((TextUtils.isEmpty(this.d) && TextUtils.isEmpty(this.f1099c)) || TextUtils.isEmpty(this.a)) ? false : true;
    }

    public String b() {
        return this.f1099c;
    }

    public String c() {
        return this.a;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.b;
    }

    public void f(String str) {
        this.a = str;
    }

    public void g(String str) {
        this.d = str;
    }

    public void h(String str) {
        this.b = str;
    }

    public String toString() {
        return this.a + "|" + this.f1099c + "|" + this.d;
    }
}
