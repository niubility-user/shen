package com.tencent.mapsdk.internal;

import java.io.Serializable;

/* loaded from: classes9.dex */
public final class p2 implements Serializable {
    public static final int d = 29;

    /* renamed from: e  reason: collision with root package name */
    public static final int f16975e = 1;

    /* renamed from: f  reason: collision with root package name */
    public static final int f16976f = 8;
    private int a;
    private String b;

    /* renamed from: h  reason: collision with root package name */
    public static final /* synthetic */ boolean f16978h = true;

    /* renamed from: c  reason: collision with root package name */
    private static p2[] f16974c = new p2[28];

    /* renamed from: g  reason: collision with root package name */
    public static final p2 f16977g = new p2(26, 29, "REQ_CONFIG");

    private p2(int i2, int i3, String str) {
        this.b = new String();
        this.b = str;
        this.a = i3;
        f16974c[i2] = this;
    }

    public static p2 a(int i2) {
        int i3 = 0;
        while (true) {
            p2[] p2VarArr = f16974c;
            if (i3 >= p2VarArr.length) {
                if (f16978h) {
                    return null;
                }
                throw new AssertionError();
            } else if (p2VarArr[i3].a() == i2) {
                return f16974c[i3];
            } else {
                i3++;
            }
        }
    }

    public static p2 a(String str) {
        int i2 = 0;
        while (true) {
            p2[] p2VarArr = f16974c;
            if (i2 >= p2VarArr.length) {
                if (f16978h) {
                    return null;
                }
                throw new AssertionError();
            } else if (p2VarArr[i2].toString().equals(str)) {
                return f16974c[i2];
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
