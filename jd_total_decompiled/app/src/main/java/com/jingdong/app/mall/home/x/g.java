package com.jingdong.app.mall.home.x;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.jingdong.corelib.utils.Log;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class g {
    private boolean a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private ConcurrentHashMap<Long, com.jingdong.app.mall.home.x.b> f11090c;
    private Handler d;

    /* loaded from: classes4.dex */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            synchronized (g.this) {
                g.this.e();
                sendMessageDelayed(obtainMessage(1), 1000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class b {
        private static final g a = new g(null);
    }

    /* synthetic */ g(a aVar) {
        this();
    }

    public static g b() {
        return b.a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void e() {
        try {
            for (com.jingdong.app.mall.home.x.b bVar : this.f11090c.values()) {
                if (bVar != null && !bVar.c() && !bVar.d()) {
                    long b2 = bVar.b();
                    if (b2 > 0) {
                        bVar.f(b2, b2);
                    } else {
                        bVar.h(true);
                        bVar.e();
                    }
                }
            }
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
    }

    public synchronized void c() {
        if (this.a) {
            this.b = true;
            this.d.removeMessages(1);
            if (Log.D) {
                Log.d("HHH_TimeTicker", "-----> onPause()");
            }
        }
    }

    public synchronized void d() {
        if (this.a && this.b) {
            this.b = false;
            Handler handler = this.d;
            handler.sendMessage(handler.obtainMessage(1));
            if (Log.D) {
                Log.d("HHH_TimeTicker", "-----> onResume()");
            }
        }
    }

    public synchronized com.jingdong.app.mall.home.x.b f(long j2, long j3) {
        com.jingdong.app.mall.home.x.b bVar;
        bVar = this.f11090c.get(Long.valueOf(j2));
        if (bVar == null) {
            if (Log.D) {
                Log.d("HHH_TimeTicker", "registerCountdownTimer, new timer, key: " + j2);
            }
            bVar = new com.jingdong.app.mall.home.x.b(j2);
            this.f11090c.put(Long.valueOf(j2), bVar);
        }
        bVar.h(false);
        bVar.k(false);
        bVar.j(j3, true);
        return bVar;
    }

    public synchronized void g() {
        this.f11090c.clear();
        if (Log.D) {
            Log.d("HHH_TimeTicker", "-----> initial.");
        }
        if (!this.a) {
            this.a = true;
            Handler handler = this.d;
            handler.sendMessage(handler.obtainMessage(1));
            if (Log.D) {
                Log.d("HHH_TimeTicker", "-----> start().");
            }
        }
    }

    private g() {
        this.a = false;
        this.b = false;
        this.f11090c = new ConcurrentHashMap<>();
        this.d = new a(Looper.getMainLooper());
    }
}
