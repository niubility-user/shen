package com.jd.lib.push;

import android.app.Application;
import com.jd.lib.push.utils.PushMessageUtils;
import com.jd.lib.push.utils.d;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.jdpush_new.c;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.j.o;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes16.dex */
public class b implements BackForegroundWatcher.BackForegroundListener {

    /* loaded from: classes16.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ long f5656g;

        a(b bVar, long j2) {
            this.f5656g = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Application application = JdSdk.getInstance().getApplication();
            c.a.d(this.f5656g);
            com.jd.lib.push.a.a();
            g.i("PushBundle", "tryRestartService");
            if (d.f() == 1) {
                c.a(application);
            } else if (d.f() == 2) {
                c.e(application);
            }
        }
    }

    /* renamed from: com.jd.lib.push.b$b  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    class RunnableC0164b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ long f5657g;

        RunnableC0164b(b bVar, long j2) {
            this.f5657g = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.c(this.f5657g);
        }
    }

    @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
    public void onBackToForeground() {
        try {
            o.b().a(new a(this, System.currentTimeMillis()));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
    public void onForeToBackground() {
        try {
            o.b().a(new RunnableC0164b(this, System.currentTimeMillis()));
            PushMessageUtils.tryBindAgain();
            g.i("PushBundle", "tryBindAgain");
        } catch (Throwable th) {
            g.g(th);
        }
    }
}
