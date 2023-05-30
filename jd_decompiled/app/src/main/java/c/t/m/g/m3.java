package c.t.m.g;

import com.tencent.map.geolocation.walkBikeDr.dr.TencentDrJni;

/* loaded from: classes.dex */
public class m3 {
    public static volatile m3 a;

    public static m3 f() {
        if (a == null) {
            synchronized (m3.class) {
                if (a == null) {
                    a = new m3();
                }
            }
        }
        return a;
    }

    public void a() {
        TencentDrJni.e();
    }

    public void b(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        TencentDrJni.sg(d, d2, d3, d4, d5, d6, d7, d8);
    }

    public void c(int i2, double d) {
        TencentDrJni.ss(i2, d);
    }

    public void d(long j2, float f2, float f3, float f4, long j3, float f5, float f6, float f7, long j4, float f8, float f9, float f10, long j5, float f11, float f12, float f13) {
        TencentDrJni.a(j2, f2, f3, f4, j3, f5, f6, f7, j4, f8, f9, f10, j5, f11, f12, f13);
    }

    public void e(double[][] dArr, int i2) {
        TencentDrJni.sr(dArr, i2);
    }

    public double[] g() {
        return TencentDrJni.gp();
    }

    public void h() {
        TencentDrJni.s();
    }
}
