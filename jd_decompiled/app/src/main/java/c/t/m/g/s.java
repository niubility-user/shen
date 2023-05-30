package c.t.m.g;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
public abstract class s extends q0 {

    /* renamed from: i */
    public volatile HandlerThread f660i = null;

    /* renamed from: j */
    public volatile a f661j = null;

    /* loaded from: classes.dex */
    public class a extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Looper looper) {
            super(looper);
            s.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                s.this.f(message);
            } catch (Throwable unused) {
                if (z0.e()) {
                    s.this.b();
                    StringBuilder sb = new StringBuilder("handleInnerMessage[");
                    sb.append(message.what);
                    sb.append("] error.");
                }
            }
        }
    }

    public void e(long j2) {
        synchronized (this.f614h) {
            if (this.f613g) {
                if (z0.e()) {
                    b();
                }
                d();
                j(j2);
                this.f613g = false;
            }
        }
    }

    public abstract void f(Message message);

    public boolean g(int i2, long j2) {
        boolean d;
        synchronized (this.f614h) {
            d = t.d(this.f661j, i2, j2);
        }
        return d;
    }

    public boolean h(Message message, long j2) {
        boolean f2;
        synchronized (this.f614h) {
            f2 = t.f(this.f661j, message, j2);
        }
        return f2;
    }

    public int i(Looper looper) {
        synchronized (this.f614h) {
            if (c()) {
                return -1;
            }
            this.f613g = true;
            if (z0.e()) {
                b();
            }
            if (looper == null) {
                this.f660i = new HandlerThread("th_" + b());
                this.f660i.start();
                this.f661j = new a(this.f660i.getLooper());
            } else {
                this.f661j = new a(looper);
            }
            return a(this.f661j.getLooper());
        }
    }

    public final void j(long j2) {
        try {
            l.a(this.f660i, this.f661j, j2, false);
            this.f660i = null;
            this.f661j = null;
        } catch (Throwable unused) {
            if (z0.e()) {
                b();
            }
        }
    }

    public Handler k() {
        a aVar;
        synchronized (this.f614h) {
            aVar = this.f661j;
        }
        return aVar;
    }

    public HandlerThread l() {
        HandlerThread handlerThread;
        synchronized (this.f614h) {
            handlerThread = this.f660i;
        }
        return handlerThread;
    }

    public void m() {
        e(0L);
    }

    public int n() {
        return i(null);
    }
}
