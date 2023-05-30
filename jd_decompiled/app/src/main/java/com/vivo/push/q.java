package com.vivo.push;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes11.dex */
public abstract class q {
    protected Context a;
    protected Handler b;

    /* renamed from: c  reason: collision with root package name */
    private final Object f18290c = new Object();

    /* loaded from: classes11.dex */
    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            q.this.b(message);
        }
    }

    public q() {
        HandlerThread handlerThread = new HandlerThread(getClass().getSimpleName(), 1);
        handlerThread.start();
        this.b = new a(handlerThread.getLooper());
    }

    public final void a(Context context) {
        this.a = context;
    }

    public abstract void b(Message message);

    public final void a(Message message) {
        synchronized (this.f18290c) {
            Handler handler = this.b;
            if (handler == null) {
                String str = "Dead worker dropping a message: " + message.what;
                com.vivo.push.util.p.e(getClass().getSimpleName(), str + " (Thread " + Thread.currentThread().getId() + ")");
            } else {
                handler.sendMessage(message);
            }
        }
    }
}
