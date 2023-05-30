package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes11.dex */
public class i {

    /* renamed from: e  reason: collision with root package name */
    private static volatile i f18728e;
    private ScheduledThreadPoolExecutor a = new ScheduledThreadPoolExecutor(1);
    private Map<String, ScheduledFuture> b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private Object f18729c = new Object();
    private SharedPreferences d;

    /* loaded from: classes11.dex */
    public static abstract class a implements Runnable {
        public abstract String b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        a f18730g;

        public b(a aVar) {
            this.f18730g = aVar;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a() {
        }

        void b() {
            throw null;
        }

        @Override // java.lang.Runnable
        public void run() {
            a();
            this.f18730g.run();
            b();
        }
    }

    private i(Context context) {
        this.d = context.getSharedPreferences("mipush_extra", 0);
    }

    public static i b(Context context) {
        if (f18728e == null) {
            synchronized (i.class) {
                if (f18728e == null) {
                    f18728e = new i(context);
                }
            }
        }
        return f18728e;
    }

    private static String d(String str) {
        return "last_job_time" + str;
    }

    private ScheduledFuture f(a aVar) {
        ScheduledFuture scheduledFuture;
        synchronized (this.f18729c) {
            scheduledFuture = this.b.get(aVar.b());
        }
        return scheduledFuture;
    }

    public void g(Runnable runnable) {
        h(runnable, 0);
    }

    public void h(Runnable runnable, int i2) {
        this.a.schedule(runnable, i2, TimeUnit.SECONDS);
    }

    public boolean i(a aVar) {
        return n(aVar, 0);
    }

    public boolean j(a aVar, int i2) {
        return k(aVar, i2, 0);
    }

    public boolean k(a aVar, int i2, int i3) {
        return l(aVar, i2, i3, false);
    }

    public boolean l(a aVar, int i2, int i3, boolean z) {
        if (aVar == null || f(aVar) != null) {
            return false;
        }
        String d = d(aVar.b());
        j jVar = new j(this, aVar, z, d);
        if (!z) {
            long abs = Math.abs(System.currentTimeMillis() - this.d.getLong(d, 0L)) / 1000;
            if (abs < i2 - i3) {
                i3 = (int) (i2 - abs);
            }
        }
        try {
            ScheduledFuture<?> scheduleAtFixedRate = this.a.scheduleAtFixedRate(jVar, i3, i2, TimeUnit.SECONDS);
            synchronized (this.f18729c) {
                this.b.put(aVar.b(), scheduleAtFixedRate);
            }
            return true;
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
            return true;
        }
    }

    public boolean m(String str) {
        synchronized (this.f18729c) {
            ScheduledFuture scheduledFuture = this.b.get(str);
            if (scheduledFuture == null) {
                return false;
            }
            this.b.remove(str);
            return scheduledFuture.cancel(false);
        }
    }

    public boolean n(a aVar, int i2) {
        if (aVar == null || f(aVar) != null) {
            return false;
        }
        ScheduledFuture<?> schedule = this.a.schedule(new k(this, aVar), i2, TimeUnit.SECONDS);
        synchronized (this.f18729c) {
            this.b.put(aVar.b(), schedule);
        }
        return true;
    }
}
