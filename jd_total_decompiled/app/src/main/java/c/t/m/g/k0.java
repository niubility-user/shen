package c.t.m.g;

import android.location.Location;
import android.os.Bundle;

/* loaded from: classes.dex */
public class k0 {

    /* renamed from: c */
    public static k0 f505c = new k0();
    public static boolean d;
    public int a = 0;
    public boolean b = false;

    public static void e(boolean z) {
        d = z;
    }

    public static boolean f() {
        return d;
    }

    public static k0 g() {
        if (f505c == null) {
            synchronized (k0.class) {
                if (f505c == null) {
                    f505c = new k0();
                }
            }
        }
        return f505c;
    }

    public int a(Location location) {
        Bundle extras = location.getExtras();
        if (extras != null) {
            return extras.getInt("fakeCode");
        }
        return 0;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [int, boolean] */
    public int b(n nVar) {
        if (d || i1.d(nVar.a)) {
            return 0;
        }
        ?? r0 = this.b;
        int i2 = r0;
        if (c3.e().d(nVar) == -2) {
            i2 = r0 + 4;
        }
        if (a(new Location(nVar.a)) != 0) {
            double d2 = i2;
            double pow = Math.pow(2.0d, r7 + 3);
            Double.isNaN(d2);
            return (int) (d2 + pow);
        }
        return i2;
    }

    public int c(q5 q5Var) {
        int i2 = 0;
        if (d) {
            return 0;
        }
        if (q5Var.isMockGps() > 0) {
            this.b = true;
            i2 = 1;
        } else {
            this.b = false;
        }
        return i2 + this.a;
    }

    public void d(int i2) {
        this.a = (int) Math.pow(2.0d, i2 + 3);
    }

    public void h() {
        this.a = 0;
        this.b = false;
    }
}
