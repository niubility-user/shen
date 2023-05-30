package com.jingdong.manto.sdk.thread;

import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;

/* loaded from: classes16.dex */
public abstract class b<R> {
    long a;
    private long b;
    private R d;

    /* renamed from: f  reason: collision with root package name */
    long f14195f;

    /* renamed from: c  reason: collision with root package name */
    private Object f14193c = new Object();

    /* renamed from: g  reason: collision with root package name */
    boolean f14196g = false;

    /* renamed from: e  reason: collision with root package name */
    private Runnable f14194e = new a();

    /* loaded from: classes16.dex */
    class a implements Runnable {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public final void run() {
            b bVar = b.this;
            if (bVar.f14196g) {
                bVar.a();
            } else {
                bVar.a((b) bVar.a());
            }
            b bVar2 = b.this;
            bVar2.f14195f = MantoUtils.getTimeCost(bVar2.a);
        }
    }

    public b(long j2, R r) {
        this.b = j2;
        this.d = r;
    }

    public abstract R a();

    public final R a(MantoHandler mantoHandler) {
        if (mantoHandler != null && Thread.currentThread().getId() != mantoHandler.a().getThread().getId()) {
            this.a = MantoUtils.getTime();
            try {
                synchronized (this.f14193c) {
                    mantoHandler.a(this.f14194e);
                    this.f14193c.wait(this.b);
                }
            } catch (Throwable th) {
                MantoLog.e("SyncJob", "", th);
            }
            return this.d;
        }
        return a();
    }

    public final void a(R r) {
        this.d = r;
        synchronized (this.f14193c) {
            this.f14193c.notify();
        }
    }
}
