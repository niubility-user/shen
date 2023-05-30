package c.t.m.g;

import android.content.Context;

/* loaded from: classes.dex */
public class v3 {

    /* renamed from: c  reason: collision with root package name */
    public static volatile v3 f721c;
    public byte[] a = new byte[0];
    public z b;

    public v3(Context context) {
        this.b = null;
        y3.b(context);
        this.b = new z();
    }

    public static v3 a(Context context) {
        if (f721c == null) {
            synchronized (v3.class) {
                if (f721c == null) {
                    Context applicationContext = context == null ? null : context.getApplicationContext();
                    if (applicationContext == null) {
                        throw new NullPointerException("context is null." + b());
                    }
                    f721c = new v3(applicationContext);
                }
            }
        }
        return f721c;
    }

    public static String b() {
        return "0.5.28_220414";
    }

    public static String c(int i2) {
        return e1.d(i2);
    }

    public void d(c4 c4Var) {
        if (c4Var == null) {
            throw new NullPointerException("listener cannot be null.");
        }
        synchronized (this.a) {
            this.b.s(c4Var);
        }
    }

    public boolean e(String str, String str2) {
        boolean u;
        synchronized (this.a) {
            u = this.b.u(str, str2);
        }
        return u;
    }

    public void f() {
        synchronized (this.a) {
            this.b.e(200L);
        }
    }

    public void g(c4 c4Var) {
        synchronized (this.a) {
            this.b.y(c4Var);
        }
    }

    public int h() {
        int n2;
        synchronized (this.a) {
            n2 = this.b.n();
        }
        return n2;
    }
}
