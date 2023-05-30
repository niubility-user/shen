package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.tencent.map.lib.models.GeoPoint;

/* loaded from: classes9.dex */
public class ig {
    public static final int A = 65536;
    public static final int t = 0;
    public static final int u = 1;
    public static final int v = 2;
    public static final int w = 1;
    public static final int x = 16;
    public static final int y = 256;
    public static final int z = 4096;
    private GeoPoint a;
    private Bitmap[] b;

    /* renamed from: g  reason: collision with root package name */
    private int f16705g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f16706h;

    /* renamed from: i  reason: collision with root package name */
    private int f16707i;

    /* renamed from: j  reason: collision with root package name */
    private int f16708j;

    /* renamed from: k  reason: collision with root package name */
    private Rect f16709k;

    /* renamed from: l  reason: collision with root package name */
    private int f16710l;

    /* renamed from: n  reason: collision with root package name */
    private boolean f16712n;
    public String p;
    private boolean q;
    private boolean r;

    /* renamed from: c  reason: collision with root package name */
    private float f16702c = 0.5f;
    private float d = 0.5f;

    /* renamed from: e  reason: collision with root package name */
    private float f16703e = 1.0f;

    /* renamed from: f  reason: collision with root package name */
    private boolean f16704f = false;

    /* renamed from: m  reason: collision with root package name */
    private int f16711m = 2;
    private boolean o = true;
    private boolean s = true;

    public float a() {
        return this.f16703e;
    }

    public int a(int i2) {
        return b()[0];
    }

    public ig a(float f2) {
        this.f16703e = f2;
        return this;
    }

    public ig a(float f2, float f3) {
        this.f16702c = f2;
        this.d = f3;
        return this;
    }

    public ig a(int i2, int i3) {
        this.f16707i = i2;
        this.f16708j = i3;
        return this;
    }

    public ig a(int i2, int i3, int i4, int i5) {
        this.f16709k = new Rect(i2, i3, i4, i5);
        return this;
    }

    public ig a(GeoPoint geoPoint) {
        this.a = geoPoint;
        return this;
    }

    public ig a(String str, Bitmap... bitmapArr) {
        this.p = str;
        this.b = bitmapArr;
        return this;
    }

    public ig a(boolean z2) {
        this.r = z2;
        return this;
    }

    public ig a(int... iArr) {
        this.f16702c = 0.5f;
        if (iArr == null) {
            this.d = 1.0f;
            return this;
        }
        this.d = 0.5f;
        if (iArr.length != 1) {
            return this;
        }
        if ((iArr[0] & 256) == 256) {
            this.d = 0.0f;
        } else if ((iArr[0] & 16) == 16) {
            this.d = 1.0f;
        }
        if ((iArr[0] & 4096) == 4096) {
            this.f16702c = 0.0f;
        } else if ((iArr[0] & 65536) == 65536) {
            this.f16702c = 1.0f;
        }
        return this;
    }

    public ig b(int i2) {
        this.f16711m = i2;
        return this;
    }

    public ig b(boolean z2) {
        this.q = z2;
        return this;
    }

    public int[] b() {
        float f2 = this.f16702c;
        int i2 = f2 == 0.0f ? 4096 : f2 == 1.0f ? 65536 : 1;
        float f3 = this.d;
        return new int[]{i2 | (f3 == 0.0f ? 256 : f3 == 1.0f ? 16 : 1)};
    }

    public float c() {
        return this.f16702c;
    }

    public ig c(int i2) {
        this.f16705g = i2;
        return this;
    }

    public ig c(boolean z2) {
        this.s = z2;
        return this;
    }

    public float d() {
        return this.d;
    }

    public ig d(int i2) {
        this.f16710l = i2;
        return this;
    }

    public ig d(boolean z2) {
        this.o = z2;
        return this;
    }

    public Rect e() {
        return this.f16709k;
    }

    public ig e(boolean z2) {
        this.f16712n = z2;
        return this;
    }

    public ig f(boolean z2) {
        this.f16704f = z2;
        return this;
    }

    public Bitmap[] f() {
        return this.b;
    }

    public ig g(boolean z2) {
        this.f16706h = z2;
        return this;
    }

    public String g() {
        return this.p;
    }

    public int h() {
        return this.f16711m;
    }

    public int i() {
        return this.f16707i;
    }

    public int j() {
        return this.f16708j;
    }

    public GeoPoint k() {
        return this.a;
    }

    public int l() {
        return this.f16705g;
    }

    public int m() {
        return this.f16710l;
    }

    public boolean n() {
        return this.r;
    }

    public boolean o() {
        return this.q;
    }

    public boolean p() {
        return this.s;
    }

    public boolean q() {
        return this.o;
    }

    public boolean r() {
        return this.f16712n;
    }

    public boolean s() {
        return this.f16704f;
    }

    public boolean t() {
        return this.f16706h;
    }
}
