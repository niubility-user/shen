package c.t.m.g;

/* loaded from: classes.dex */
public abstract class i0 {
    public volatile boolean a = false;
    public byte[] b = new byte[0];

    public abstract String a();

    public boolean b() {
        boolean z;
        synchronized (this.b) {
            z = this.a;
        }
        return z;
    }

    public void c() {
        synchronized (this.b) {
            if (this.a) {
                if (z0.e()) {
                    a();
                }
                d();
                this.a = false;
            }
        }
    }

    public abstract void d();

    public int e() {
        synchronized (this.b) {
            if (this.a) {
                return -1;
            }
            this.a = true;
            if (z0.e()) {
                a();
            }
            return f();
        }
    }

    public abstract int f();
}
