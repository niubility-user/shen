package com.jingdong.app.mall.bundle.jdrhsdk.c;

import android.text.TextUtils;

/* loaded from: classes2.dex */
public class b {
    private int a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f8159c;

    public int a() {
        return this.a;
    }

    public void b(int i2) {
        this.a = i2;
    }

    public void c(String str) {
        this.f8159c = str;
    }

    public String d() {
        return TextUtils.isEmpty(this.f8159c) ? "" : this.f8159c;
    }

    public void e(String str) {
        this.b = str;
    }

    public String f() {
        return TextUtils.isEmpty(this.b) ? "" : this.b;
    }
}
