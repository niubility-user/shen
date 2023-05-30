package com.jingdong.jdma.bean.e;

import android.text.TextUtils;
import com.jingdong.jdma.common.utils.n;

/* loaded from: classes12.dex */
public class c {
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f12651c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f12652e;

    /* renamed from: f  reason: collision with root package name */
    private int f12653f;

    /* renamed from: g  reason: collision with root package name */
    private int f12654g;

    /* renamed from: h  reason: collision with root package name */
    private int f12655h;

    /* renamed from: i  reason: collision with root package name */
    private int f12656i;

    /* renamed from: j  reason: collision with root package name */
    private int f12657j;

    /* renamed from: k  reason: collision with root package name */
    private int f12658k;

    /* renamed from: l  reason: collision with root package name */
    private int f12659l;

    public c() {
        a();
    }

    private void a() {
        this.f12656i = n.a().c() ? 60 : 15;
        int i2 = n.a().c() ? 50 : 10;
        this.f12657j = i2;
        int i3 = this.f12656i;
        this.f12658k = i3;
        this.f12654g = i3;
        this.f12652e = i3;
        this.f12651c = i3;
        this.a = i3;
        this.f12659l = i2;
        this.f12655h = i2;
        this.f12653f = i2;
        this.d = i2;
        this.b = i2;
    }

    public void b(int i2) {
        if (i2 > 0) {
            this.f12659l = i2;
        }
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
            this.f12651c = i2;
        }
    }

    public void f(int i2) {
        if (i2 >= 0) {
            this.d = i2;
        }
    }

    public void g(int i2) {
        if (i2 > 0) {
            this.f12652e = i2;
        }
    }

    public void h(int i2) {
        if (i2 >= 0) {
            this.f12653f = i2;
        }
    }

    public void i(int i2) {
        if (i2 > 0) {
            this.f12654g = i2;
        }
    }

    public void j(int i2) {
        if (i2 >= 0) {
            this.f12655h = i2;
        }
    }

    public void k(int i2) {
        if (i2 > 0) {
            this.f12656i = i2;
        }
    }

    public void l(int i2) {
        if (i2 >= 0) {
            this.f12657j = i2;
        }
    }

    public String toString() {
        return "LogRulesAction={g2Int:" + this.a + ",g2Sz:" + this.b + ",g3Int:" + this.f12651c + ",g3Sz:" + this.d + ",g4Int:" + this.f12652e + ",g4Sz:" + this.f12653f + ",g5Int:" + this.f12654g + ",g5Sz:" + this.f12655h + ",wifiInt:" + this.f12656i + ",wifiSz:" + this.f12657j + ",defaultSz:" + this.f12659l + ",defaultInt:" + this.f12658k + "}";
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
                return this.f12653f;
            }
            if (str.equals("5g")) {
                return this.f12655h;
            }
            if (str.equals("wifi")) {
                return this.f12657j;
            }
            return this.f12659l;
        }
        return this.f12659l;
    }

    public void a(int i2) {
        if (i2 > 0) {
            this.f12658k = i2;
        }
    }

    public int a(String str) {
        if (!TextUtils.isEmpty(str) && !str.equalsIgnoreCase("unknown")) {
            if (str.equals("2g")) {
                return this.a;
            }
            if (str.equals("3g")) {
                return this.f12651c;
            }
            if (str.equals("4g")) {
                return this.f12652e;
            }
            if (str.equals("5g")) {
                return this.f12654g;
            }
            if (str.equals("wifi")) {
                return this.f12656i;
            }
            return this.f12658k;
        }
        return this.f12658k;
    }

    public void b() {
        a();
    }
}
