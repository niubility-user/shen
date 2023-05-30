package c.t.m.g;

import android.os.Looper;

/* loaded from: classes.dex */
public abstract class b0 extends i0 {
    @Override // c.t.m.g.i0
    public int e() {
        return g(Looper.myLooper());
    }

    @Override // c.t.m.g.i0
    public int f() {
        return 0;
    }

    public int g(Looper looper) {
        synchronized (this.b) {
            if (this.a) {
                return -1;
            }
            this.a = true;
            if (z0.e()) {
                a();
            }
            return h(looper);
        }
    }

    public abstract int h(Looper looper);
}
