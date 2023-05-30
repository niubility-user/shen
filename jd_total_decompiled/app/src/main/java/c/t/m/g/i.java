package c.t.m.g;

import com.jd.dynamic.DYConstants;

/* loaded from: classes.dex */
public class i {
    public static byte[] b = new byte[0];

    /* renamed from: c  reason: collision with root package name */
    public static i f486c;
    public t4 a = new t4();

    public static i f() {
        i iVar;
        synchronized (b) {
            iVar = f486c;
        }
        return iVar;
    }

    public synchronized void a(int i2, int i3, float f2, double[][] dArr, double[][] dArr2, double[][] dArr3, double[] dArr4) {
        synchronized (b) {
            f486c = this;
        }
        this.a.b(i2, i3, f2, dArr, dArr2, dArr3, dArr4);
    }

    public synchronized void b(long j2, float f2) {
        if (z0.e()) {
            StringBuilder sb = new StringBuilder("setGps(");
            sb.append(j2);
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(f2);
            sb.append(")");
        }
        this.a.c(j2, f2);
    }

    public synchronized void c(long j2, float[] fArr, float[] fArr2) {
        this.a.d(j2, fArr, fArr2);
    }

    public synchronized double[] d() {
        z0.e();
        return t4.f692l;
    }

    public synchronized double[] e(long j2) {
        double[] g2;
        if (z0.e()) {
            StringBuilder sb = new StringBuilder("getArClassifyResult(");
            sb.append(j2);
            sb.append(")");
        }
        g2 = this.a.g(j2);
        if (z0.e()) {
            StringBuilder sb2 = new StringBuilder("getArClassifyResult(");
            sb2.append(j2);
            sb2.append("),resArr=");
            sb2.append(e2.a(g2, 4, true));
        }
        return g2;
    }

    public synchronized void g() {
        this.a.a();
    }

    public synchronized void h() {
        this.a.h();
        synchronized (b) {
            f486c = null;
        }
    }
}
