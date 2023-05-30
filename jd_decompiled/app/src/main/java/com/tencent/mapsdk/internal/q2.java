package com.tencent.mapsdk.internal;

import java.io.Serializable;

/* loaded from: classes9.dex */
public final class q2 implements Serializable {
    public static final int d = 0;

    /* renamed from: f  reason: collision with root package name */
    public static final int f17009f = 1;

    /* renamed from: h  reason: collision with root package name */
    public static final int f17011h = 2;

    /* renamed from: j  reason: collision with root package name */
    public static final int f17013j = 3;
    private int a;
    private String b;

    /* renamed from: l  reason: collision with root package name */
    public static final /* synthetic */ boolean f17015l = true;

    /* renamed from: c  reason: collision with root package name */
    private static q2[] f17007c = new q2[4];

    /* renamed from: e  reason: collision with root package name */
    public static final q2 f17008e = new q2(0, 0, "DT_NORMAL");

    /* renamed from: g  reason: collision with root package name */
    public static final q2 f17010g = new q2(1, 1, "DT_ONLY_LINK");

    /* renamed from: i  reason: collision with root package name */
    public static final q2 f17012i = new q2(2, 2, "DT_ONLY_STATUS");

    /* renamed from: k  reason: collision with root package name */
    public static final q2 f17014k = new q2(3, 3, "DT_LINK_AND_STATUS");

    private q2(int i2, int i3, String str) {
        this.b = new String();
        this.b = str;
        this.a = i3;
        f17007c[i2] = this;
    }

    public static q2 a(int i2) {
        int i3 = 0;
        while (true) {
            q2[] q2VarArr = f17007c;
            if (i3 >= q2VarArr.length) {
                if (f17015l) {
                    return null;
                }
                throw new AssertionError();
            } else if (q2VarArr[i3].a() == i2) {
                return f17007c[i3];
            } else {
                i3++;
            }
        }
    }

    public static q2 a(String str) {
        int i2 = 0;
        while (true) {
            q2[] q2VarArr = f17007c;
            if (i2 >= q2VarArr.length) {
                if (f17015l) {
                    return null;
                }
                throw new AssertionError();
            } else if (q2VarArr[i2].toString().equals(str)) {
                return f17007c[i2];
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
