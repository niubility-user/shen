package com.jingdong.manto.sdk.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.jingdong.manto.sdk.thread.d;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes16.dex */
public class MantoHandler implements d.a {

    /* renamed from: f  reason: collision with root package name */
    private static a f14189f;
    private d a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private LinkedList<WeakReference<c>> f14190c;
    private ConcurrentHashMap<Runnable, WeakReference<c>> d;

    /* renamed from: e  reason: collision with root package name */
    private String f14191e;

    /* loaded from: classes16.dex */
    public interface a {
        void a(Message message, Runnable runnable, Thread thread, long j2, long j3, float f2);
    }

    public MantoHandler() {
        this.d = new ConcurrentHashMap<>();
        this.f14190c = new LinkedList<>();
        this.f14191e = null;
        this.a = new d(this);
    }

    public MantoHandler(Looper looper) {
        this.d = new ConcurrentHashMap<>();
        this.f14190c = new LinkedList<>();
        this.f14191e = null;
        this.a = new d(looper, this);
    }

    public static Handler fetchFreeHandler(Looper looper) {
        return new Handler(looper);
    }

    public final Looper a() {
        return this.a.getLooper();
    }

    public final void a(int i2) {
        this.a.removeMessages(i2);
    }

    @Override // com.jingdong.manto.sdk.thread.d.a
    public void a(Message message) {
    }

    @Override // com.jingdong.manto.sdk.thread.d.a
    public void a(Message message, Runnable runnable, Thread thread, long j2, long j3, float f2) {
        a aVar = f14189f;
        if (aVar != null) {
            aVar.a(message, runnable, thread, j2, j3, f2);
        }
    }

    @Override // com.jingdong.manto.sdk.thread.d.a
    public final void a(Runnable runnable, c cVar) {
        WeakReference<c> weakReference = this.d.get(runnable);
        if (weakReference == null || weakReference.get() == null || weakReference.get() != cVar) {
            return;
        }
        this.d.remove(runnable);
        if (this.b > 0) {
            if (this.f14190c.size() == this.b) {
                this.f14190c.pop();
            }
            this.f14190c.add(weakReference);
        }
    }

    public final boolean a(int i2, long j2) {
        return this.a.sendEmptyMessageDelayed(i2, j2);
    }

    public final boolean a(Runnable runnable) {
        d dVar = this.a;
        if (dVar == null) {
            return false;
        }
        return dVar.post(runnable);
    }

    public final boolean a(Runnable runnable, long j2) {
        d dVar = this.a;
        if (dVar == null) {
            return false;
        }
        return dVar.postDelayed(runnable, j2);
    }

    public final void b(Runnable runnable) {
        if (runnable != null) {
            WeakReference<c> weakReference = this.d.get(runnable);
            if (weakReference != null && weakReference.get() != null) {
                this.a.removeCallbacks(weakReference.get());
            }
            this.d.remove(runnable);
        }
    }

    @Override // com.jingdong.manto.sdk.thread.d.a
    public final void b(Runnable runnable, c cVar) {
        this.d.put(runnable, new WeakReference<>(cVar));
    }

    public final boolean b(int i2) {
        return this.a.sendEmptyMessage(i2);
    }

    public final boolean b(Message message) {
        return this.a.sendMessage(message);
    }

    public String toString() {
        if (this.f14191e == null) {
            this.f14191e = "MantoHandler(" + getClass().getName() + ")";
        }
        return this.f14191e;
    }
}
