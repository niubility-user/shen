package com.meizu.cloud.pushsdk.e.c;

import com.meizu.cloud.pushsdk.e.d.k;

/* loaded from: classes14.dex */
public class a extends Exception {
    private String a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private String f15796c;
    private k d;

    public a() {
        this.b = 0;
    }

    public a(k kVar) {
        this.b = 0;
        this.d = kVar;
    }

    public a(Throwable th) {
        super(th);
        this.b = 0;
    }

    public String a() {
        return this.a;
    }

    public void a(int i2) {
        this.b = i2;
    }

    public void a(String str) {
        this.a = str;
    }

    public int b() {
        return this.b;
    }

    public void b(String str) {
        this.f15796c = str;
    }

    public k c() {
        return this.d;
    }
}
