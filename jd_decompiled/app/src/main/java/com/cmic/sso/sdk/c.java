package com.cmic.sso.sdk;

/* loaded from: classes.dex */
public class c {
    private final String a = "LockManager";
    private final Object b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f984c = false;

    public void a(long j2) {
        synchronized (this.b) {
            if (this.f984c) {
                com.cmic.sso.sdk.e.c.b("LockManager", "wait for pre");
                try {
                    this.b.wait(j2);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            this.f984c = true;
        }
    }

    public void a() {
        com.cmic.sso.sdk.e.c.b("LockManager", "unLockPre");
        synchronized (this.b) {
            try {
                this.f984c = false;
                this.b.notify();
            } catch (Exception e2) {
                e2.printStackTrace();
                com.cmic.sso.sdk.e.c.a("LockManager", "unLock error ");
            }
        }
    }
}
