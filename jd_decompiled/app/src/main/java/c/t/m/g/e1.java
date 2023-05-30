package c.t.m.g;

import android.os.Bundle;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes.dex */
public class e1 extends w0 implements n3 {
    public int b;

    /* renamed from: c */
    public double f373c;
    public int d;

    /* renamed from: e */
    public double f374e;

    /* renamed from: f */
    public double[] f375f = new double[7];

    /* renamed from: g */
    public Bundle f376g = new Bundle();

    public e1() {
        l();
    }

    public static final String d(int i2) {
        if (i2 != 100) {
            if (i2 != 200) {
                if (i2 != 300) {
                    if (i2 != 400) {
                        switch (i2) {
                            case 0:
                                return "unknown";
                            case 1:
                                return "still";
                            case 2:
                                return "walking";
                            case 3:
                                return "in_vehicle";
                            case 4:
                                return "on_bicycle";
                            case 5:
                                return "running";
                            case 6:
                                return "tilting";
                            default:
                                return "not_support_type";
                        }
                    }
                    return "tilting";
                }
                return "vehicle";
            }
            return "on_foot";
        }
        return "still";
    }

    @Override // c.t.m.g.n3
    public int a() {
        return this.d;
    }

    @Override // c.t.m.g.n3
    public double b() {
        return this.d == 0 ? 1.0d - this.f374e : this.f374e;
    }

    public final boolean e(double[] dArr) {
        for (double d : dArr) {
            if (Double.isNaN(d)) {
                return true;
            }
        }
        return false;
    }

    public void f(double[] dArr) {
        double[] dArr2;
        double[] dArr3 = this.f375f;
        System.arraycopy(dArr, 0, dArr3, 0, dArr3.length);
        boolean e2 = e(dArr);
        double[] dArr4 = this.f375f;
        if (dArr4[0] == 1.0d || dArr4[6] == 1.0d || e2) {
            int i2 = (dArr4[0] == 1.0d || e2) ? 0 : 400;
            this.b = i2;
            this.f373c = i2 == 0 ? 0.0d : 1.0d;
            if (dArr4[0] != 1.0d && !e2) {
                r2 = 6;
            }
            this.d = r2;
            this.f374e = r2 == 0 ? 0.0d : 1.0d;
            return;
        }
        int i3 = 1;
        double d = dArr4[1];
        double d2 = dArr4[3] + dArr4[4];
        int i4 = 2;
        double d3 = dArr4[2] + dArr4[5];
        int i5 = 100;
        if (d2 > d) {
            i5 = 300;
            d = d2;
        }
        if (d3 > d) {
            i5 = 200;
        } else {
            d3 = d;
        }
        this.f373c = d3;
        if (d3 < 0.4d) {
            i5 = 0;
        }
        this.b = i5;
        while (true) {
            dArr2 = this.f375f;
            if (i4 > 5) {
                break;
            }
            if (dArr2[i4] > dArr2[i3]) {
                i3 = i4;
            }
            i4++;
        }
        double d4 = dArr2[i3];
        this.f374e = d4;
        this.d = d4 >= 0.4d ? i3 : 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0033, code lost:
        if (r2 < 0.4d) goto L61;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g(double[] r9) {
        /*
            r8 = this;
            if (r9 != 0) goto L3
            return
        L3:
            boolean r0 = r8.e(r9)
            r1 = 6
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r4 = 0
            if (r0 != 0) goto L36
            r5 = r9[r4]
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 == 0) goto L36
            r5 = r9[r1]
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 == 0) goto L36
            r0 = 2
            r1 = 1
        L1b:
            r2 = 5
            if (r0 > r2) goto L2a
            r2 = r9[r0]
            r5 = r9[r1]
            int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r7 <= 0) goto L27
            r1 = r0
        L27:
            int r0 = r0 + 1
            goto L1b
        L2a:
            r2 = r9[r1]
            r5 = 4600877379321698714(0x3fd999999999999a, double:0.4)
            int r0 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r0 >= 0) goto L44
            goto L45
        L36:
            r5 = r9[r4]
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 == 0) goto L3f
            if (r0 != 0) goto L3f
            goto L40
        L3f:
            r1 = 0
        L40:
            if (r1 != 0) goto L44
            r2 = 0
        L44:
            r4 = r1
        L45:
            android.os.Bundle r0 = r8.f376g
            java.lang.String r1 = "ar_no_gps_type"
            r0.putInt(r1, r4)
            android.os.Bundle r0 = r8.f376g
            java.lang.String r1 = "ar_no_gps_conf"
            r0.putDouble(r1, r2)
            android.os.Bundle r0 = r8.f376g
            java.lang.String r1 = "ar_no_gps_conf_arr"
            r0.putDoubleArray(r1, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.e1.g(double[]):void");
    }

    @Deprecated
    public double h() {
        return this.b == 0 ? 1.0d - this.f373c : this.f373c;
    }

    @Deprecated
    public String i() {
        return v3.c(this.b);
    }

    @Deprecated
    public int j() {
        return this.b;
    }

    public String k() {
        return v3.c(this.d);
    }

    public void l() {
        a(System.currentTimeMillis());
        Arrays.fill(this.f375f, 0.0d);
        f(this.f375f);
        this.f376g.clear();
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "ArEvent{time=%d, type=%d, conf=%.4f, desc=%s, subType=%d, subConf=%.4f, subDesc=%s}", Long.valueOf(c()), Integer.valueOf(j()), Double.valueOf(h()), i(), Integer.valueOf(a()), Double.valueOf(b()), k());
    }
}
