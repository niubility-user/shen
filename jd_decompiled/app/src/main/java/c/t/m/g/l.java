package c.t.m.g;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class l {

    /* loaded from: classes.dex */
    public static class a extends TimerTask {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ HandlerThread f536g;

        /* renamed from: h  reason: collision with root package name */
        public final /* synthetic */ Handler f537h;

        /* renamed from: i  reason: collision with root package name */
        public final /* synthetic */ boolean f538i;

        /* renamed from: j  reason: collision with root package name */
        public final /* synthetic */ Timer f539j;

        public a(HandlerThread handlerThread, Handler handler, boolean z, Timer timer) {
            this.f536g = handlerThread;
            this.f537h = handler;
            this.f538i = z;
            this.f539j = timer;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            try {
                l.c(this.f536g, this.f537h, this.f538i);
                Timer timer = this.f539j;
                if (timer != null) {
                    timer.cancel();
                }
            } catch (Throwable unused) {
                z0.e();
            }
        }
    }

    public static void a(HandlerThread handlerThread, Handler handler, long j2, boolean z) {
        if (handlerThread == null && handler == null) {
            return;
        }
        if (j2 <= 0) {
            c(handlerThread, handler, z);
            return;
        }
        Timer timer = new Timer("th_loc_tmp");
        timer.schedule(new a(handlerThread, handler, z, timer), j2);
    }

    public static void c(HandlerThread handlerThread, Handler handler, boolean z) {
        if (z) {
            try {
                t.j(handler);
            } catch (Throwable unused) {
                z0.e();
                return;
            }
        }
        if (handlerThread != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                handlerThread.quitSafely();
            } else {
                handlerThread.quit();
            }
        }
    }
}
