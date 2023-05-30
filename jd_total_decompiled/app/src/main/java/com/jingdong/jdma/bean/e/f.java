package com.jingdong.jdma.bean.e;

import android.text.TextUtils;
import com.jingdong.jdma.common.utils.Constant;
import com.jingdong.jdma.common.utils.m;
import com.jingdong.jdma.common.utils.n;

/* loaded from: classes12.dex */
public class f {
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f12662c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f12663e;

    /* renamed from: f  reason: collision with root package name */
    private int f12664f;

    /* renamed from: g  reason: collision with root package name */
    private int f12665g;

    /* renamed from: h  reason: collision with root package name */
    private int f12666h;

    /* renamed from: i  reason: collision with root package name */
    private int f12667i;

    /* renamed from: j  reason: collision with root package name */
    private int f12668j;

    /* renamed from: k  reason: collision with root package name */
    private int f12669k;

    /* renamed from: l  reason: collision with root package name */
    private int f12670l;

    /* renamed from: m  reason: collision with root package name */
    private String f12671m;

    /* renamed from: n  reason: collision with root package name */
    private int f12672n;
    private int o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private int v;
    private int w;
    private String x;
    private String y = "1";
    private String z;

    public f() {
        B();
        this.o = 2;
        this.f12669k = 1;
        this.f12672n = 30;
        this.q = Constant.STATISTIC_REPORT_DATA_DEFAULT_DOMAIN;
        this.z = Constant.STATISTIC_REPORT_DATA_DEFAULT_H5_URL;
        this.x = "0";
    }

    private void B() {
        this.f12667i = n.a().c() ? 60 : 15;
        int i2 = n.a().c() ? 50 : 10;
        this.f12668j = i2;
        int i3 = this.f12667i;
        this.v = i3;
        this.a = i3;
        this.f12662c = i3;
        this.f12663e = i3;
        this.f12665g = i3;
        this.w = i2;
        this.b = i2;
        this.d = i2;
        this.f12664f = i2;
        this.f12666h = i2;
        this.f12670l = n.a().c() ? 5 : 10;
        this.f12671m = "0.0";
    }

    public int A() {
        return this.f12668j;
    }

    public void C() {
        B();
    }

    public int a() {
        return this.f12670l;
    }

    public void b(int i2) {
        if (i2 > 0) {
            this.v = i2;
        }
    }

    public void c(int i2) {
        if (i2 > 0) {
            this.w = i2;
        }
    }

    public void d(int i2) {
        if (i2 > 0) {
            this.a = i2;
        }
    }

    public int e() {
        return this.a;
    }

    public void f(int i2) {
        if (i2 > 0) {
            this.f12662c = i2;
        }
    }

    public int g() {
        return this.f12662c;
    }

    public int h() {
        return this.d;
    }

    public void i(int i2) {
        if (i2 >= 0) {
            this.f12664f = i2;
        }
    }

    public int j() {
        return this.f12664f;
    }

    public int k() {
        return this.f12665g;
    }

    public int l() {
        return this.f12666h;
    }

    public void m(int i2) {
        if (i2 > 0) {
            this.B = i2;
        }
    }

    public void n(int i2) {
        this.o = i2;
    }

    public void o(int i2) {
        if (i2 > 0) {
            this.C = i2;
        }
    }

    public void p(int i2) {
        if (i2 > 0) {
            this.A = i2;
        }
    }

    public int q() {
        return this.A;
    }

    public String r() {
        return this.x;
    }

    public void s(int i2) {
        this.f12669k = i2;
    }

    public void t(int i2) {
        if (i2 >= 0) {
            this.f12672n = i2;
        }
    }

    public String toString() {
        return "Strategy={g2Int:" + this.a + ",g2Sz:" + this.b + ",g3Int:" + this.f12662c + ",g3Sz:" + this.d + ",g4Int:" + this.f12663e + ",g4Sz:" + this.f12664f + ",g5Int:" + this.f12665g + ",g5Sz:" + this.f12666h + ",wifiInt:" + this.f12667i + ",wifiSz:" + this.f12668j + "\uff0cret:" + this.f12669k + ",cyc:" + this.f12670l + ",v:" + this.f12671m + ",spd:" + this.f12672n + ",loopbtw:" + this.o + ",rules:" + m.a(this.p) + ",domain:" + m.a(this.q) + ",logRules:" + m.a(this.r) + ",monitorSdk:" + m.a(this.s) + ",failureRetry:" + m.a(this.t) + ",sendLimit:" + m.a(this.u) + ",defaultInt:" + this.v + ",defaultSz:" + this.w + ",policyType:" + m.a(this.x) + "}";
    }

    public void u(int i2) {
        if (i2 > 0) {
            this.f12667i = i2;
        }
    }

    public void v(int i2) {
        if (i2 >= 0) {
            this.f12668j = i2;
        }
    }

    public String w() {
        return this.p;
    }

    public int x() {
        return this.f12672n;
    }

    public String y() {
        return this.f12671m;
    }

    public int z() {
        return this.f12667i;
    }

    public void a(int i2) {
        this.f12670l = i2;
    }

    public int b() {
        return this.v;
    }

    public int c() {
        return this.w;
    }

    public String d() {
        return this.q;
    }

    public void e(int i2) {
        if (i2 >= 0) {
            this.b = i2;
        }
    }

    public int f() {
        return this.b;
    }

    public void g(int i2) {
        if (i2 >= 0) {
            this.d = i2;
        }
    }

    public void h(int i2) {
        if (i2 > 0) {
            this.f12663e = i2;
        }
    }

    public int i() {
        return this.f12663e;
    }

    public void j(int i2) {
        if (i2 > 0) {
            this.f12665g = i2;
        }
    }

    public void k(int i2) {
        if (i2 >= 0) {
            this.f12666h = i2;
        }
    }

    public void l(int i2) {
        if (i2 > 0) {
            this.D = i2;
        }
    }

    public int m() {
        return this.D;
    }

    public int n() {
        return this.B;
    }

    public String o() {
        return this.z;
    }

    public int p() {
        return this.C;
    }

    public void q(int i2) {
        if (i2 > 0) {
            this.E = i2;
        }
    }

    public void r(int i2) {
        if (i2 > 0) {
            this.F = i2;
        }
    }

    public String s() {
        return this.y;
    }

    public int t() {
        return this.E;
    }

    public int u() {
        return this.F;
    }

    public int v() {
        return this.f12669k;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.q = str;
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.z = str;
    }

    public void c(String str) {
        this.x = str;
    }

    public void d(String str) {
        this.y = str;
    }

    public void e(String str) {
        this.p = str;
    }

    public void f(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f12671m = str;
    }
}
