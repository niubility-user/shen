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

    /* JADX WARN: Code restructure failed: missing block: B:84:0x0033, code lost:
        if (r2 < 0.4d) goto L94;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void g(double[] dArr) {
        int i2;
        if (dArr == null) {
            return;
        }
        boolean e2 = e(dArr);
        double d = 1.0d;
        int i3 = 0;
        if (e2 || dArr[0] == 1.0d || dArr[6] == 1.0d) {
            i2 = (dArr[0] == 1.0d || e2) ? 0 : 6;
            if (i2 == 0) {
                d = 0.0d;
            }
        } else {
            i2 = 1;
            for (int i4 = 2; i4 <= 5; i4++) {
                if (dArr[i4] > dArr[i2]) {
                    i2 = i4;
                }
            }
            d = dArr[i2];
        }
        i3 = i2;
        this.f376g.putInt("ar_no_gps_type", i3);
        this.f376g.putDouble("ar_no_gps_conf", d);
        this.f376g.putDoubleArray("ar_no_gps_conf_arr", dArr);
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
