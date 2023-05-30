package com.tencent.mapsdk.internal;

import java.io.Serializable;

/* loaded from: classes9.dex */
public final class r2 implements Serializable {
    public static final int d = 0;

    /* renamed from: f  reason: collision with root package name */
    public static final int f17167f = 1;

    /* renamed from: h  reason: collision with root package name */
    public static final int f17169h = 2;

    /* renamed from: j  reason: collision with root package name */
    public static final int f17171j = 3;

    /* renamed from: l  reason: collision with root package name */
    public static final int f17173l = 4;

    /* renamed from: n  reason: collision with root package name */
    public static final int f17175n = 5;
    public static final int p = 6;
    public static final int r = 7;
    private int a;
    private String b;
    public static final /* synthetic */ boolean t = true;

    /* renamed from: c  reason: collision with root package name */
    private static r2[] f17165c = new r2[8];

    /* renamed from: e  reason: collision with root package name */
    public static final r2 f17166e = new r2(0, 0, "RST_SUCC");

    /* renamed from: g  reason: collision with root package name */
    public static final r2 f17168g = new r2(1, 1, "RST_DECODE_FAIL");

    /* renamed from: i  reason: collision with root package name */
    public static final r2 f17170i = new r2(2, 2, "RST_SYS_ERR");

    /* renamed from: k  reason: collision with root package name */
    public static final r2 f17172k = new r2(3, 3, "RST_INVALID_USER");

    /* renamed from: m  reason: collision with root package name */
    public static final r2 f17174m = new r2(4, 4, "RST_USE_INVALID_KEY");
    public static final r2 o = new r2(5, 5, "RST_INVALID_UIN");
    public static final r2 q = new r2(6, 6, "RST_INVALID_CMD");
    public static final r2 s = new r2(7, 7, "RST_PACKAGE_ERR");

    private r2(int i2, int i3, String str) {
        this.b = new String();
        this.b = str;
        this.a = i3;
        f17165c[i2] = this;
    }

    public static r2 a(int i2) {
        int i3 = 0;
        while (true) {
            r2[] r2VarArr = f17165c;
            if (i3 >= r2VarArr.length) {
                if (t) {
                    return null;
                }
                throw new AssertionError();
            } else if (r2VarArr[i3].a() == i2) {
                return f17165c[i3];
            } else {
                i3++;
            }
        }
    }

    public static r2 a(String str) {
        int i2 = 0;
        while (true) {
            r2[] r2VarArr = f17165c;
            if (i2 >= r2VarArr.length) {
                if (t) {
                    return null;
                }
                throw new AssertionError();
            } else if (r2VarArr[i2].toString().equals(str)) {
                return f17165c[i2];
            } else {
                i2++;
            }
        }
    }

    public int a() {
        return this.a;
    }

    public String toString() {
        return this.b;
    }
}
