package com.vivo.push;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes11.dex */
public final class m {
    private static final Handler a = new Handler(Looper.getMainLooper());
    private static final HandlerThread b;

    /* renamed from: c  reason: collision with root package name */
    private static final Handler f18285c;

    static {
        HandlerThread handlerThread = new HandlerThread("push_client_thread");
        b = handlerThread;
        handlerThread.start();
        f18285c = new n(handlerThread.getLooper());
    }

    public static void a(l lVar) {
        if (lVar == null) {
            com.vivo.push.util.p.a("PushClientThread", "client thread error, task is null!");
            return;
        }
        int a2 = lVar.a();
        Message message = new Message();
        message.what = a2;
        message.obj = lVar;
        f18285c.sendMessageDelayed(message, 0L);
    }

    public static void b(Runnable runnable) {
        a.post(runnable);
    }

    public static void c(Runnable runnable) {
        Handler handler = f18285c;
        if (handler != null) {
            handler.post(runnable);
        }
    }

    public static void a(Runnable runnable) {
        Handler handler = f18285c;
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 15000L);
    }
}
