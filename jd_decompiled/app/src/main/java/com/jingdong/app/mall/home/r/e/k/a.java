package com.jingdong.app.mall.home.r.e.k;

import android.os.SystemClock;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.r.e.f;
import java.lang.reflect.Array;

/* loaded from: classes4.dex */
public class a {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private int[] f10703c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f10704e;

    /* renamed from: f  reason: collision with root package name */
    private String f10705f;

    /* renamed from: g  reason: collision with root package name */
    private int[] f10706g;

    /* renamed from: h  reason: collision with root package name */
    private int f10707h;

    /* renamed from: i  reason: collision with root package name */
    private long f10708i;

    /* renamed from: j  reason: collision with root package name */
    private long f10709j;

    /* renamed from: k  reason: collision with root package name */
    private long f10710k;

    /* renamed from: l  reason: collision with root package name */
    private int f10711l;

    /* renamed from: m  reason: collision with root package name */
    private String f10712m;

    /* renamed from: n  reason: collision with root package name */
    private String f10713n;
    private String[][] o = (String[][]) Array.newInstance(String.class, 2, 2);
    private int p;
    private boolean q;

    public a(f fVar, int i2, int i3) {
        this.p = i3;
        this.a = fVar.getJsonString("interestPoint");
        this.b = fVar.getJsonString("interestPoint2");
        this.f10703c = m.o(fVar.getJsonString("interestPointColor"), -16777216);
        String jsonString = fVar.getJsonString("topTextColor");
        this.f10706g = m.o(jsonString, -16777216);
        this.q = jsonString == null || jsonString.isEmpty();
        this.f10705f = fVar.getJsonString("textMode");
        String jsonString2 = fVar.getJsonString("topText");
        boolean contentEquals = "1".contentEquals(this.f10705f);
        this.d = contentEquals ? jsonString2 : fVar.getJsonString("textBefore");
        this.f10704e = contentEquals ? jsonString2 : fVar.getJsonString("textAfter");
        if ("2".equals(this.f10705f)) {
            this.f10709j = com.jingdong.app.mall.home.n.h.c.i(fVar.getJsonString("countdownStart"));
            this.f10710k = com.jingdong.app.mall.home.n.h.c.i(fVar.getJsonString("countdownEnd"));
            this.f10708i = com.jingdong.app.mall.home.n.h.c.i(fVar.getJsonString("countdownTimestamp"));
            this.f10707h = m.i(fVar.getJsonString("countdownColor"), -16777216);
        }
        this.f10711l = i2 < 500 ? 3000 : i2;
        this.f10712m = fVar.w();
        this.f10713n = fVar.x();
        this.o[0][0] = fVar.u();
        this.o[0][1] = fVar.v();
        this.o[1][0] = fVar.w();
        this.o[1][1] = fVar.x();
    }

    public int[] a() {
        return this.f10703c;
    }

    public String b(boolean z) {
        return z ? this.a : this.b;
    }

    public int c() {
        return this.f10707h;
    }

    public long d() {
        return this.f10708i;
    }

    public long e() {
        return (this.f10710k * 1000) - (SystemClock.elapsedRealtime() - s.f9357c);
    }

    public long f() {
        return this.f10711l;
    }

    public String g(int i2, int i3) {
        return this.o[i2][i3];
    }

    public long h() {
        return (this.f10709j * 1000) - (SystemClock.elapsedRealtime() - s.f9357c);
    }

    public String i() {
        return this.f10704e;
    }

    public String j() {
        return this.d;
    }

    public int[] k() {
        return this.f10706g;
    }

    public int l() {
        return this.p;
    }

    public boolean m() {
        return (TextUtils.isEmpty(this.f10712m) || TextUtils.isEmpty(this.f10713n)) ? false : true;
    }

    public boolean n() {
        return "1".equals(this.f10705f) || "2".equals(this.f10705f);
    }

    public boolean o() {
        return this.q;
    }
}
