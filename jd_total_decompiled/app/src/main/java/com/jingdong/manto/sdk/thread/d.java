package com.jingdong.manto.sdk.thread;

import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.jingdong.manto.sdk.thread.c;

/* loaded from: classes16.dex */
final class d extends Handler implements c.a {
    private String a;
    private Looper b;

    /* renamed from: c  reason: collision with root package name */
    private Handler.Callback f14204c;
    a d;

    /* loaded from: classes16.dex */
    public interface a {
        void a(Message message);

        void a(Message message, Runnable runnable, Thread thread, long j2, long j3, float f2);

        void a(Runnable runnable, c cVar);

        void b(Runnable runnable, c cVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Looper looper, a aVar) {
        super(looper);
        this.a = null;
        this.b = getLooper();
        this.d = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(a aVar) {
        this.a = null;
        this.b = getLooper();
        this.d = aVar;
    }

    @Override // com.jingdong.manto.sdk.thread.c.a
    public final void a(Runnable runnable, c cVar) {
        a aVar = this.d;
        if (aVar != null) {
            aVar.a(runnable, cVar);
        }
    }

    @Override // com.jingdong.manto.sdk.thread.c.a
    public final void a(Runnable runnable, Thread thread, long j2, long j3, float f2) {
        a aVar = this.d;
        if (aVar != null) {
            aVar.a(null, runnable, thread, j2, j3, f2);
        }
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message message) {
        if (message.getCallback() != null || this.f14204c != null) {
            super.dispatchMessage(message);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long threadCpuTimeNanos = Debug.threadCpuTimeNanos();
        handleMessage(message);
        a aVar = this.d;
        if (aVar != null) {
            aVar.a(message, null, this.b.getThread(), System.currentTimeMillis() - currentTimeMillis, Debug.threadCpuTimeNanos() - threadCpuTimeNanos, -1.0f);
        }
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        a aVar = this.d;
        if (aVar != null) {
            aVar.a(message);
        }
    }

    @Override // android.os.Handler
    public final boolean sendMessageAtTime(Message message, long j2) {
        a aVar;
        if (message != null) {
            Runnable callback = message.getCallback();
            if (callback == null) {
                return super.sendMessageAtTime(message, j2);
            }
            SystemClock.uptimeMillis();
            c cVar = new c(this.b.getThread(), message.getTarget() == null ? this : message.getTarget(), callback, message.obj, this);
            Message obtain = Message.obtain(message.getTarget(), cVar);
            obtain.what = message.what;
            obtain.arg1 = message.arg1;
            obtain.arg2 = message.arg2;
            obtain.obj = message.obj;
            obtain.replyTo = message.replyTo;
            obtain.setData(message.getData());
            message.recycle();
            if (getLooper() == null || getLooper().getThread().isAlive()) {
                a aVar2 = this.d;
                if (aVar2 != null) {
                    aVar2.b(callback, cVar);
                }
                boolean sendMessageAtTime = super.sendMessageAtTime(obtain, j2);
                if (!sendMessageAtTime && (aVar = this.d) != null) {
                    aVar.a(callback, cVar);
                }
                return sendMessageAtTime;
            }
            return false;
        }
        throw new NullPointerException("msg is null");
    }

    @Override // android.os.Handler
    public final String toString() {
        if (this.a == null) {
            this.a = "JDInnerHandler{listener = " + this.d + "}";
        }
        return this.a;
    }
}
