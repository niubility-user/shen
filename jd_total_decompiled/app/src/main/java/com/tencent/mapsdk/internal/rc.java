package com.tencent.mapsdk.internal;

import android.util.Log;

/* loaded from: classes9.dex */
public class rc extends Thread {

    /* renamed from: e  reason: collision with root package name */
    private static final int f17202e = 80;
    private qc a;
    private volatile boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f17203c = false;
    private volatile boolean d = false;

    public rc(qc qcVar) {
        setName("tms-texture");
        this.a = qcVar;
    }

    private boolean d() {
        qc qcVar = this.a;
        if (qcVar != null) {
            return qcVar.L();
        }
        return false;
    }

    public void a() {
        this.f17203c = true;
    }

    public void b() {
        this.f17203c = false;
        synchronized (this) {
            notifyAll();
        }
    }

    public void c() {
        this.f17203c = false;
        this.b = true;
        synchronized (this) {
            notifyAll();
        }
    }

    public boolean e() {
        return this.d;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (!this.b) {
            if (!(this.f17203c ? false : d())) {
                try {
                    synchronized (this) {
                        wait(80L);
                    }
                } catch (InterruptedException e2) {
                    ma.c(Log.getStackTraceString(e2));
                    Thread.currentThread().interrupt();
                }
            }
        }
        this.d = true;
    }
}
