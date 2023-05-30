package c.t.m.g;

import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public class c6 extends l5 {
    public j6 b;

    /* renamed from: c  reason: collision with root package name */
    public double[] f341c = new double[3];
    public ArrayList<l5> a = new ArrayList<>();

    public c6() {
        j6 j6Var = new j6();
        this.b = j6Var;
        this.a.add(j6Var);
    }

    @Override // c.t.m.g.l5
    public void a() {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            this.a.get(i2).a();
        }
    }

    @Override // c.t.m.g.l5
    public double[] b(double[] dArr) {
        Arrays.fill(this.f341c, 0.0d);
        double[] b = this.b.b(dArr);
        new StringBuilder("pObserver SVM,").append(e2.a(b, 4, false));
        double[] dArr2 = this.f341c;
        System.arraycopy(b, 0, dArr2, 0, dArr2.length);
        return this.f341c;
    }

    @Override // c.t.m.g.l5
    public double[] c(double[][] dArr) {
        return a.i(dArr);
    }

    @Override // c.t.m.g.l5
    public String d() {
        StringBuilder sb = new StringBuilder(this.a.get(0).d());
        for (int i2 = 1; i2 < this.a.size(); i2++) {
            sb.append('_');
            sb.append(this.a.get(i2).d());
        }
        return sb.toString();
    }

    @Override // c.t.m.g.l5
    public void e() {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            this.a.get(i2).e();
        }
    }

    @Override // c.t.m.g.l5
    public void f() {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            this.a.get(i2).f();
        }
    }
}
