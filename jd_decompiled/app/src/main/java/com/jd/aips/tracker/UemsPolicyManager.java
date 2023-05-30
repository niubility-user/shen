package com.jd.aips.tracker;

import com.jd.aips.tracker.util.UemsClock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes12.dex */
public class UemsPolicyManager {
    private long a;
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private ReadWriteLock f1640c;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class Builder {
        private static UemsPolicyManager a = new UemsPolicyManager();

        private Builder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        try {
            long longValue = Long.valueOf(str).longValue();
            if (longValue <= 0 || longValue > 25200000) {
                return;
            }
            this.f1640c.writeLock().lock();
            try {
                this.b = UemsClock.a().b();
                this.a = longValue;
            } catch (Throwable unused) {
            }
            this.f1640c.writeLock().unlock();
        } catch (NumberFormatException unused2) {
        }
    }

    private UemsPolicyManager() {
        this.a = 0L;
        this.b = 0L;
        this.f1640c = new ReentrantReadWriteLock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        boolean z;
        this.f1640c.readLock().lock();
        if (this.b > 0 && this.a > 0) {
            if (UemsClock.a().b() < this.b + this.a) {
                z = false;
                this.f1640c.readLock().unlock();
                return z;
            }
        }
        z = true;
        this.f1640c.readLock().unlock();
        return z;
    }
}
