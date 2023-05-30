package com.huawei.hms.stats;

import android.os.Handler;
import android.os.Looper;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtils;
import com.huawei.hms.support.log.HMSLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class a {

    /* renamed from: f */
    private static final a f1480f = new a();
    private final Object a = new Object();
    private boolean b = false;

    /* renamed from: c */
    private final List<Runnable> f1481c = new ArrayList();
    private final Handler d = new Handler(Looper.getMainLooper());

    /* renamed from: e */
    private final Runnable f1482e = new RunnableC0063a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.stats.a$a */
    /* loaded from: classes12.dex */
    public class RunnableC0063a implements Runnable {
        RunnableC0063a() {
            a.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            HMSLog.i("AnalyticsCacheManager", "Timeout execCacheBi.");
            if (!HiAnalyticsUtils.getInstance().getInitFlag()) {
                a.this.a();
            } else {
                a.this.b();
            }
        }
    }

    private a() {
    }

    public static a c() {
        return f1480f;
    }

    public void a(Runnable runnable) {
        synchronized (this.a) {
            if (runnable == null) {
                return;
            }
            if (this.b) {
                return;
            }
            if (this.f1481c.size() >= 60) {
                return;
            }
            this.f1481c.add(runnable);
            this.d.removeCallbacks(this.f1482e);
            this.d.postDelayed(this.f1482e, 10000L);
        }
    }

    public void b() {
        synchronized (this.a) {
            HMSLog.i("AnalyticsCacheManager", "execCacheBi: cache size: " + this.f1481c.size());
            this.b = true;
            Iterator<Runnable> it = this.f1481c.iterator();
            while (it.hasNext()) {
                it.next().run();
                it.remove();
            }
            this.b = false;
        }
    }

    public void a() {
        synchronized (this.a) {
            HMSLog.i("AnalyticsCacheManager", "clear AnalyticsCache.");
            this.f1481c.clear();
        }
    }
}
