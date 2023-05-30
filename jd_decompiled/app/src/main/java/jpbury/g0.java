package jpbury;

import android.os.Looper;
import android.os.Process;

/* loaded from: classes11.dex */
public abstract class g0 extends Thread {
    public int a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public Looper f20122c;

    public g0(String str) {
        super(str);
        this.b = -1;
        this.a = 0;
    }

    public g0(String str, int i2) {
        super(str);
        this.b = -1;
        this.a = i2;
    }

    public Looper a() {
        if (isAlive()) {
            synchronized (this) {
                while (isAlive() && this.f20122c == null) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
            return this.f20122c;
        }
        return null;
    }

    public abstract void a(Throwable th);

    public int b() {
        return this.b;
    }

    public void c() {
    }

    public boolean d() {
        Looper a = a();
        if (a != null) {
            a.quit();
            return true;
        }
        return false;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        this.b = Process.myTid();
        Looper.prepare();
        synchronized (this) {
            this.f20122c = Looper.myLooper();
            notifyAll();
        }
        Process.setThreadPriority(this.a);
        c();
        try {
            Looper.loop();
        } catch (Throwable th) {
            th.printStackTrace();
            a(th);
        }
        this.b = -1;
    }
}
