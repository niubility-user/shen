package com.tencent.tmsqmsp.oaid2;

/* loaded from: classes9.dex */
public class w {
    public int a;
    public long b;

    /* renamed from: c  reason: collision with root package name */
    public String f18097c;
    public String d;

    public w(String str) {
        this.f18097c = str;
    }

    public void a(int i2) {
        this.a = i2;
    }

    public void a(long j2) {
        this.b = j2;
    }

    public void a(String str) {
        this.d = str;
    }

    public boolean a() {
        return this.b > System.currentTimeMillis();
    }

    public void b() {
        this.b = 0L;
    }
}
