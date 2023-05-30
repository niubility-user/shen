package c.t.m.g;

import com.jingdong.common.jdmiaosha.utils.cache.Final;
import java.util.Arrays;

/* loaded from: classes.dex */
public class c5 {

    /* renamed from: f */
    public t6 f332f;

    /* renamed from: g */
    public t6 f333g;

    /* renamed from: h */
    public t6 f334h;

    /* renamed from: i */
    public t6 f335i;

    /* renamed from: j */
    public t6 f336j;

    /* renamed from: k */
    public t6 f337k;

    /* renamed from: l */
    public double f338l;
    public volatile double d = -1.0d;

    /* renamed from: e */
    public volatile long f331e = 0;

    /* renamed from: m */
    public double[] f339m = new double[7];

    /* renamed from: n */
    public double[] f340n = new double[3];
    public z6 b = new z6();
    public l5 a = new c6();

    /* renamed from: c */
    public r6 f330c = new r6(15);

    public c5() {
        int i2 = k4.a;
        double d = i2;
        Double.isNaN(d);
        this.f338l = 1.0d / d;
        t6 t6Var = new t6(i2, i2);
        this.f332f = t6Var;
        c.c(t6Var, k4.f523e);
        this.f333g = new t6(k4.a, 1);
        this.f337k = new t6(k4.a, 1);
        this.f334h = new t6(k4.a, 1);
        this.f335i = new t6(k4.a, 1);
        this.f336j = new t6(k4.a, 1);
        this.f333g.a(this.f338l);
        this.f334h.a(this.f338l);
        this.f335i.a(this.f338l);
        this.f336j.a(this.f338l);
        j();
    }

    public void a(long j2, double d) {
        this.f331e = j2;
        this.d = d;
        this.f330c.a(j2, d);
    }

    public final void b(t6 t6Var) {
        double d = 0.0d;
        for (int i2 = 0; i2 < t6Var.d(); i2++) {
            d += t6Var.a(i2, 0);
        }
        t6Var.b(1.0d / d);
    }

    public final void c(t6 t6Var, double d) {
        if (Double.isNaN(t6Var.a(0, 0)) || Double.isNaN(t6Var.a(t6Var.d() - 1, t6Var.c() - 1))) {
            t6Var.a(d);
        }
    }

    public final void d(double[] dArr, t6 t6Var) {
        int i2 = 0;
        while (i2 < t6Var.d()) {
            int i3 = i2 + 1;
            dArr[i3] = t6Var.a(i2, 0);
            i2 = i3;
        }
    }

    public final double[] e() {
        char c2 = 1;
        char c3 = 0;
        boolean z = System.currentTimeMillis() - this.f331e <= Final.FIVE_SECOND && this.d > 2.5d && this.d < 1000.0d;
        double d = this.d == 0.0d ? 1.0E-5d : this.d;
        c(this.f332f, this.f338l);
        c(this.f333g, this.f338l);
        c(this.f334h, this.f338l);
        c(this.f335i, this.f338l);
        c(this.f336j, this.f338l);
        Arrays.fill(this.f340n, this.f338l);
        b(this.f334h);
        c.e(this.f337k, this.f332f, this.f333g);
        c.b(this.f333g, this.f337k, this.f334h);
        b(this.f333g);
        c.e(this.f337k, this.f332f, this.f335i);
        c.b(this.f335i, this.f337k, this.f334h);
        b(this.f335i);
        d(t4.f692l, this.f333g);
        if (z) {
            double log = Math.log(d);
            double sqrt = Math.sqrt(6.283185307179586d);
            int i2 = 0;
            while (i2 < 3) {
                double[][] dArr = k4.f524f;
                double d2 = log - dArr[i2][c2];
                this.f340n[i2] = (((dArr[i2][c3] * Math.exp(((-d2) * d2) / ((dArr[i2][2] * 2.0d) * dArr[i2][2]))) / k4.f524f[i2][2]) / sqrt) / d;
                i2++;
                c2 = 1;
                c3 = 0;
            }
            double l2 = h1.l(this.f340n);
            for (int i3 = 0; i3 < 3; i3++) {
                double[] dArr2 = this.f340n;
                dArr2[i3] = dArr2[i3] / l2;
                this.f336j.a(i3, 0, dArr2[i3]);
            }
            t6 t6Var = this.f335i;
            c.b(t6Var, t6Var, this.f336j);
            b(this.f335i);
        }
        d(this.f339m, this.f335i);
        return this.f339m;
    }

    public double[] f(long j2, double[][] dArr) {
        Arrays.fill(this.f339m, 0.0d);
        Arrays.fill(t4.f692l, 0.0d);
        if (!this.f330c.c()) {
            if (k4.f527i) {
                double[] dArr2 = this.f339m;
                dArr2[0] = 1.0d;
                t4.f692l[0] = 1.0d;
                return dArr2;
            }
            this.f330c.d();
        }
        double[] i2 = i(j2, dArr);
        int e2 = h1.e(t4.f692l);
        this.f330c.b(j2, e2, t4.f692l[e2]);
        return i2;
    }

    public final double[] g(double[] dArr) {
        double[] b = this.a.b(dArr);
        for (int i2 = 0; i2 < b.length; i2++) {
            this.f334h.a(i2, 0, b[i2]);
        }
        return e();
    }

    public void h() {
        t1.a("ArStrategy", "destroy().");
        this.a.a();
        this.f330c.d();
        this.f331e = 0L;
        this.d = -1.0d;
    }

    public double[] i(long j2, double[][] dArr) {
        boolean z;
        boolean z2 = true;
        int i2 = 1;
        while (true) {
            try {
                if (i2 >= dArr[3].length) {
                    z = true;
                    break;
                } else if (dArr[3][i2] != dArr[3][i2 - 1]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            } catch (Throwable th) {
                t1.b("ArStrategy", "classify error.", th);
                double[] dArr2 = this.f339m;
                dArr2[0] = 1.0d;
                t4.f692l[0] = 1.0d;
                return dArr2;
            }
        }
        double h2 = h1.h(dArr[3]);
        if (z || Math.abs(h2) < 1.0E-10d) {
            double[] dArr3 = this.f339m;
            dArr3[0] = 1.0d;
            t4.f692l[0] = 1.0d;
            return dArr3;
        }
        if (h1.a(dArr[3], h2) >= 0.06d) {
            z2 = false;
        }
        if (z2) {
            t1.a("ArStrategy", "detect still.");
            l();
            this.f334h.a(0.0d);
            this.f334h.a(0, 0, 1.0d);
            return e();
        } else if (this.b.b(dArr)) {
            t1.a("ArStrategy", "detect tilting.");
            l();
            double[] dArr4 = this.f339m;
            dArr4[6] = 1.0d;
            t4.f692l[6] = 1.0d;
            return dArr4;
        } else {
            double[] c2 = this.a.c(dArr);
            t1.a("DATA_AR", "#DATA,AR," + System.currentTimeMillis() + ",FEA," + e2.a(c2, 4, false));
            return g(c2);
        }
    }

    public void j() {
        t1.a("ArStrategy", "init start[" + this.a.d() + ", f=" + k4.f522c + ", t=" + String.format("%.2f", Float.valueOf(128.0f / k4.f522c)) + ",size=128,numClass=" + k4.a + ",SVM feaLen=141,LR feaLen=141]");
        this.a.e();
        t1.a("ArStrategy", "init finished.");
        this.f330c.d();
    }

    public void k() {
        t1.a("ArStrategy", "reset().");
        l();
        this.f333g.a(this.f338l);
    }

    public final void l() {
        t1.a("ArStrategy", "resetAlgo().");
        this.a.f();
    }
}
