package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.internal.m9;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes9.dex */
public abstract class t9<D extends m9> implements l9<D>, s9<D> {
    private ReentrantReadWriteLock a = new ReentrantReadWriteLock();

    @Override // com.tencent.mapsdk.internal.s9
    public final boolean a(String str) {
        try {
            this.a.writeLock().lock();
            return b(str);
        } finally {
            this.a.writeLock().unlock();
        }
    }

    @Override // com.tencent.mapsdk.internal.s9
    public final D b(String str, Class<D> cls) {
        try {
            this.a.readLock().lock();
            return a(str, cls);
        } finally {
            this.a.readLock().unlock();
        }
    }

    @Override // com.tencent.mapsdk.internal.s9
    public final void b() {
        try {
            this.a.writeLock().lock();
            clear();
        } finally {
            this.a.writeLock().unlock();
        }
    }

    @Override // com.tencent.mapsdk.internal.s9
    public final void b(String str, D d) {
        try {
            this.a.writeLock().lock();
            a(str, (String) d);
        } finally {
            this.a.writeLock().unlock();
        }
    }

    @Override // com.tencent.mapsdk.internal.s9
    public final long c() {
        try {
            this.a.readLock().lock();
            return getCount();
        } finally {
            this.a.readLock().unlock();
        }
    }

    @Override // com.tencent.mapsdk.internal.s9
    public final long e() {
        try {
            this.a.readLock().lock();
            return f();
        } finally {
            this.a.readLock().unlock();
        }
    }

    @Override // com.tencent.mapsdk.internal.s9
    public s9<D> g() {
        return this;
    }
}
