package com.jingdong.jdma.bean.e;

import android.text.TextUtils;
import com.jingdong.jdma.common.utils.n;

/* loaded from: classes12.dex */
public class b {
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f12642c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f12643e;

    /* renamed from: f  reason: collision with root package name */
    private int f12644f;

    /* renamed from: g  reason: collision with root package name */
    private int f12645g;

    /* renamed from: h  reason: collision with root package name */
    private int f12646h;

    /* renamed from: i  reason: collision with root package name */
    private int f12647i;

    /* renamed from: j  reason: collision with root package name */
    private int f12648j;

    /* renamed from: k  reason: collision with root package name */
    private int f12649k;

    /* renamed from: l  reason: collision with root package name */
    private int f12650l;

    public b() {
        k();
    }

    private void k() {
        this.a = n.a().c() ? 60 : 15;
        int i2 = n.a().c() ? 50 : 10;
        this.b = i2;
        int i3 = this.a;
        this.f12642c = i3;
        this.f12643e = i3;
        this.f12645g = i3;
        this.f12649k = i3;
        this.f12647i = i3;
        this.d = i2;
        this.f12644f = i2;
        this.f12646h = i2;
        this.f12650l = i2;
        this.f12648j = i2;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public void c(int i2) {
        if (i2 > 0) {
            this.a = i2;
        }
    }

    public void d(int i2) {
        if (i2 >= 0) {
            this.b = i2;
        }
    }

    public void e(int i2) {
        if (i2 > 0) {
            this.f12642c = i2;
        }
    }

    public void f(int i2) {
        if (i2 >= 0) {
            this.d = i2;
        }
    }

    public void g(int i2) {
        if (i2 > 0) {
            this.f12643e = i2;
        }
    }

    public void h(int i2) {
        if (i2 >= 0) {
            this.f12644f = i2;
        }
    }

    public void i(int i2) {
        if (i2 > 0) {
            this.f12645g = i2;
        }
    }

    public void j(int i2) {
        if (i2 >= 0) {
            this.f12646h = i2;
        }
    }

    public void l(int i2) {
        if (i2 >= 0) {
            this.f12650l = i2;
        }
    }

    public String toString() {
        return "LogRulesAction={g2Int:" + this.a + ",g2Sz:" + this.b + ",g3Int:" + this.f12642c + ",g3Sz:" + this.d + ",g4Int:" + this.f12643e + ",g4Sz:" + this.f12644f + ",g5Int:" + this.f12645g + ",g5Sz:" + this.f12646h + ",wifiInt:" + this.f12649k + ",wifiSz:" + this.f12650l + ",defaultSz:" + this.f12648j + ",defaultInt:" + this.f12647i + "}";
    }

    public void a(int i2) {
        if (i2 > 0) {
            this.f12647i = i2;
        }
    }

    public void b(int i2) {
        if (i2 > 0) {
            this.f12648j = i2;
        }
    }

    public int c() {
        return this.f12642c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.f12643e;
    }

    public int f() {
        return this.f12644f;
    }

    public int g() {
        return this.f12645g;
    }

    public int h() {
        return this.f12646h;
    }

    public int i() {
        return this.f12649k;
    }

    public int j() {
        return this.f12650l;
    }

    public void l() {
        k();
    }

    public int a(String str) {
        if (!TextUtils.isEmpty(str) && !str.equalsIgnoreCase("unknown")) {
            if (str.equals("2g")) {
                return this.a;
            }
            if (str.equals("3g")) {
                return this.f12642c;
            }
            if (str.equals("4g")) {
                return this.f12643e;
            }
            if (str.equals("5g")) {
                return this.f12645g;
            }
            if (str.equals("wifi")) {
                return this.f12649k;
            }
            return this.f12647i;
        }
        return this.f12647i;
    }

    public int b(String str) {
        if (!TextUtils.isEmpty(str) && !str.equalsIgnoreCase("unknown")) {
            if (str.equals("2g")) {
                return this.b;
            }
            if (str.equals("3g")) {
                return this.d;
            }
            if (str.equals("4g")) {
                return this.f12644f;
            }
            if (str.equals("5g")) {
                return this.f12646h;
            }
            if (str.equals("wifi")) {
                return this.f12650l;
            }
            return this.f12648j;
        }
        return this.f12648j;
    }

    public void k(int i2) {
        if (i2 > 0) {
            this.f12649k = i2;
        }
    }
}
