package com.jingdong.app.mall.home.x;

/* loaded from: classes4.dex */
public class e {
    public long a;
    public long b;

    /* renamed from: c  reason: collision with root package name */
    public long f11087c;
    public long d;

    /* renamed from: e  reason: collision with root package name */
    public long f11088e;

    /* renamed from: f  reason: collision with root package name */
    public long f11089f;

    public e(long j2) {
        d(j2);
    }

    public String a() {
        return String.valueOf(this.d);
    }

    public String b() {
        return String.valueOf(this.f11088e);
    }

    public String c() {
        return String.valueOf(this.f11089f);
    }

    public void d(long j2) {
        this.a = j2;
        this.b = j2 / 86400000;
        this.f11087c = (j2 % 86400000) / 3600000;
        long j3 = j2 / 3600000;
        this.d = j3;
        this.f11088e = (j2 % 3600000) / 60000;
        this.f11089f = (j2 % 60000) / 1000;
        this.d = Math.min(j3, 99L);
        this.f11088e = Math.max(this.f11088e, 0L);
        this.f11089f = Math.max(this.f11089f, 0L);
    }
}
