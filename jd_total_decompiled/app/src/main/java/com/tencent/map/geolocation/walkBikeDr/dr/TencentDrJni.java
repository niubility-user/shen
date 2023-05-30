package com.tencent.map.geolocation.walkBikeDr.dr;

import c.t.m.g.z0;

/* loaded from: classes9.dex */
public class TencentDrJni {
    public static native void a(long j2, float f2, float f3, float f4, long j3, float f5, float f6, float f7, long j4, float f8, float f9, float f10, long j5, float f11, float f12, float f13);

    public static native void e();

    public static native void g(String str, boolean z);

    public static native double[] gp();

    public static native String gv();

    public static void logToCoreLog(String str, String str2) {
        z0.b(3, str, str2);
    }

    public static native void s();

    public static native void sa(int i2, double d);

    public static native void sg(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8);

    public static native void sn(long j2, long j3, double d, double d2, int i2);

    public static native void sr(double[][] dArr, int i2);

    public static native void ss(int i2, double d);
}
