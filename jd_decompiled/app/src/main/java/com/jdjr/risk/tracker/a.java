package com.jdjr.risk.tracker;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes18.dex */
public class a {
    private static a a = new a();
    private long b = 0;

    /* renamed from: c  reason: collision with root package name */
    private long f7493c = 0;
    private ReadWriteLock d = new ReentrantReadWriteLock();

    private a() {
    }

    public static a a() {
        return a;
    }

    public void a(String str) {
        try {
            long longValue = Long.valueOf(str).longValue();
            if (longValue <= 0) {
                return;
            }
            this.d.writeLock().lock();
            try {
                this.f7493c = System.currentTimeMillis();
                this.b = longValue;
            } catch (Throwable unused) {
            }
            this.d.writeLock().unlock();
        } catch (NumberFormatException unused2) {
        }
    }

    public boolean b() {
        boolean z;
        this.d.readLock().lock();
        try {
        } catch (Throwable unused) {
        }
        if (System.currentTimeMillis() < this.f7493c + this.b) {
            z = false;
            this.d.readLock().unlock();
            return z;
        }
        z = true;
        this.d.readLock().unlock();
        return z;
    }
}
