package c.t.m.g;

import android.content.Context;

/* loaded from: classes.dex */
public class z1 implements d0 {

    /* renamed from: c  reason: collision with root package name */
    public static volatile z1 f797c;
    public boolean a = true;
    public e3 b;

    public z1(Context context) {
        this.b = new e3(context);
    }

    public static z1 a(Context context) {
        if (f797c == null) {
            synchronized (z1.class) {
                if (f797c == null) {
                    if (context == null) {
                        throw new NullPointerException("context is null");
                    }
                    f797c = new z1(context);
                }
            }
        }
        return f797c;
    }

    @Override // c.t.m.g.d0
    public boolean a() {
        return this.b.f();
    }

    @Override // c.t.m.g.d0
    public double[] getPosition() {
        return this.b.e();
    }

    @Override // c.t.m.g.d0
    public boolean isSupport() {
        return this.b.g();
    }

    @Override // c.t.m.g.d0
    public int startDrEngine(int i2) {
        if (this.a) {
            try {
                return this.b.a(i2);
            } catch (Exception unused) {
                return -999;
            }
        }
        return -7;
    }

    @Override // c.t.m.g.d0
    public void terminateDrEngine() {
        this.b.l();
    }
}
