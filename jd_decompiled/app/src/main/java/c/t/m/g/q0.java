package c.t.m.g;

import android.os.Looper;

/* loaded from: classes.dex */
public abstract class q0 extends b3 {

    /* renamed from: g  reason: collision with root package name */
    public volatile boolean f613g = false;

    /* renamed from: h  reason: collision with root package name */
    public byte[] f614h = new byte[0];

    public abstract int a(Looper looper);

    public abstract String b();

    public boolean c() {
        boolean z;
        synchronized (this.f614h) {
            z = this.f613g;
        }
        return z;
    }

    public abstract void d();
}
