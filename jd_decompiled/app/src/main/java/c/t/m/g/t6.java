package c.t.m.g;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

/* loaded from: classes.dex */
public class t6 implements Serializable, Cloneable {
    public double[][] a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f701c;

    public t6(int i2, int i3) {
        this.b = i2;
        this.f701c = i3;
        this.a = (double[][]) Array.newInstance(double.class, i2, i3);
    }

    public double a(int i2, int i3) {
        return this.a[i2][i3];
    }

    public t6 a() {
        t6 t6Var = new t6(this.b, this.f701c);
        double[][] b = t6Var.b();
        for (int i2 = 0; i2 < this.b; i2++) {
            for (int i3 = 0; i3 < this.f701c; i3++) {
                b[i2][i3] = this.a[i2][i3];
            }
        }
        return t6Var;
    }

    public void a(double d) {
        int i2 = 0;
        while (true) {
            double[][] dArr = this.a;
            if (i2 >= dArr.length) {
                return;
            }
            Arrays.fill(dArr[i2], d);
            i2++;
        }
    }

    public void a(int i2, int i3, double d) {
        this.a[i2][i3] = d;
    }

    public t6 b(double d) {
        for (int i2 = 0; i2 < this.b; i2++) {
            for (int i3 = 0; i3 < this.f701c; i3++) {
                double[][] dArr = this.a;
                dArr[i2][i3] = dArr[i2][i3] * d;
            }
        }
        return this;
    }

    public double[][] b() {
        return this.a;
    }

    public int c() {
        return this.f701c;
    }

    public Object clone() {
        return a();
    }

    public int d() {
        return this.b;
    }
}
