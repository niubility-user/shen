package com.jd.hybrid.downloader.l;

import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes13.dex */
public class c implements com.jd.hybrid.downloader.l.a {
    private final Object a;
    private Timer b;

    /* renamed from: c  reason: collision with root package name */
    private TimerTask f2694c;

    /* loaded from: classes13.dex */
    class a extends TimerTask {
        a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (!c.this.c()) {
                Log.d("ThreadDownloadCondition", "Download condition(thread count) DISABLE");
                return;
            }
            Log.d("ThreadDownloadCondition", "Download condition(thread count) ENABLE");
            c.this.d();
            synchronized (c.this.a) {
                c.this.a.notifyAll();
            }
        }
    }

    public c(Object obj) {
        this.a = obj;
    }

    @Override // com.jd.hybrid.downloader.l.a
    public void a() {
    }

    @Override // com.jd.hybrid.downloader.l.a
    public void b() {
    }

    @Override // com.jd.hybrid.downloader.l.a
    public boolean c() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (true) {
            ThreadGroup parent = threadGroup.getParent();
            if (parent == null) {
                break;
            }
            threadGroup = parent;
        }
        try {
            int enumerate = threadGroup.enumerate(new Thread[threadGroup.activeCount() * 2]);
            boolean z = enumerate <= HybridSettings.CONDITION_DOWNLOAD_MAX_THREAD;
            Log.d("ThreadDownloadCondition", "Current thread count: " + enumerate + ", requirement = " + HybridSettings.CONDITION_DOWNLOAD_MAX_THREAD + ", condition pass = " + z);
            return z;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.jd.hybrid.downloader.l.a
    public void d() {
        Timer timer = this.b;
        if (timer != null) {
            timer.cancel();
            Log.d("ThreadDownloadCondition", "unregister timer of thread count listener.");
        }
        this.b = null;
        this.f2694c = null;
    }

    @Override // com.jd.hybrid.downloader.l.a
    public void e() {
        if (this.b != null) {
            return;
        }
        this.b = new Timer();
        a aVar = new a();
        this.f2694c = aVar;
        this.b.scheduleAtFixedRate(aVar, 10000L, 10000L);
        Log.d("ThreadDownloadCondition", "register timer of thread count listener.");
    }
}
