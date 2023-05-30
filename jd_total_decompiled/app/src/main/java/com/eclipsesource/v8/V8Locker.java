package com.eclipsesource.v8;

/* loaded from: classes.dex */
public class V8Locker {
    private V8 runtime;
    private Thread thread = null;
    private boolean released = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public V8Locker(V8 v8) {
        this.runtime = v8;
        acquire();
    }

    public synchronized void acquire() {
        Thread thread = this.thread;
        if (thread != null && thread != Thread.currentThread()) {
            throw new Error("Invalid V8 thread access: current thread is " + Thread.currentThread() + " while the locker has thread " + this.thread);
        }
        if (this.thread == Thread.currentThread()) {
            return;
        }
        V8 v8 = this.runtime;
        v8.acquireLock(v8.getV8RuntimePtr());
        this.thread = Thread.currentThread();
        this.released = false;
    }

    public void checkThread() {
        if (this.released && this.thread == null) {
            throw new Error("Invalid V8 thread access: the locker has been released!");
        }
        if (this.thread == Thread.currentThread()) {
            return;
        }
        throw new Error("Invalid V8 thread access: current thread is " + Thread.currentThread() + " while the locker has thread " + this.thread);
    }

    public Thread getThread() {
        return this.thread;
    }

    public boolean hasLock() {
        return this.thread == Thread.currentThread();
    }

    public synchronized void release() {
        if ((this.released && this.thread == null) || this.runtime.isReleased()) {
            return;
        }
        checkThread();
        V8 v8 = this.runtime;
        v8.releaseLock(v8.getV8RuntimePtr());
        this.thread = null;
        this.released = true;
    }

    public synchronized boolean tryAcquire() {
        Thread thread = this.thread;
        if (thread == null || thread == Thread.currentThread()) {
            if (this.thread == Thread.currentThread()) {
                return true;
            }
            V8 v8 = this.runtime;
            v8.acquireLock(v8.getV8RuntimePtr());
            this.thread = Thread.currentThread();
            this.released = false;
            return true;
        }
        return false;
    }
}
