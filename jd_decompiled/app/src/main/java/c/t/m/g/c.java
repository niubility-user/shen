package c.t.m.g;

/* loaded from: classes.dex */
public class c {
    public static void a() {
        throw new IllegalArgumentException("Illegal matrix dimensions.");
    }

    public static void b(t6 t6Var, t6 t6Var2, t6 t6Var3) {
        int i2 = t6Var.b;
        int i3 = t6Var2.b;
        if (i2 == i3) {
            int i4 = t6Var.f701c;
            int i5 = t6Var2.f701c;
            if (i4 == i5 && i3 == t6Var3.b && i5 == t6Var3.f701c) {
                for (int i6 = 0; i6 < t6Var.b; i6++) {
                    for (int i7 = 0; i7 < t6Var.f701c; i7++) {
                        t6Var.a[i6][i7] = t6Var2.a[i6][i7] * t6Var3.a[i6][i7];
                    }
                }
                return;
            }
        }
        a();
        throw null;
    }

    public static void c(t6 t6Var, double[][] dArr) {
        d(t6Var.a, dArr);
    }

    public static void d(double[][] dArr, double[][] dArr2) {
        if (dArr.length != dArr2.length || (dArr.length != 0 && dArr[0].length != dArr2[0].length)) {
            a();
            throw null;
        }
        for (int i2 = 0; i2 < dArr.length; i2++) {
            System.arraycopy(dArr2[i2], 0, dArr[i2], 0, dArr2[i2].length);
        }
    }

    public static void e(t6 t6Var, t6 t6Var2, t6 t6Var3) {
        if (t6Var == t6Var2 || t6Var == t6Var3) {
            throw new IllegalArgumentException("resMatrix cannot be mA,mB.");
        }
        if (t6Var.b != t6Var2.b || t6Var.f701c != t6Var3.f701c || t6Var2.f701c != t6Var3.b) {
            a();
            throw null;
        }
        for (int i2 = 0; i2 < t6Var.b; i2++) {
            for (int i3 = 0; i3 < t6Var.f701c; i3++) {
                double d = 0.0d;
                for (int i4 = 0; i4 < t6Var2.f701c; i4++) {
                    d += t6Var2.a[i2][i4] * t6Var3.a[i4][i3];
                }
                t6Var.a[i2][i3] = d;
            }
        }
    }
}
