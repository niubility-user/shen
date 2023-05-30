package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.mp4parser.aspectj.lang.JoinPoint;

@Beta
@GwtIncompatible
/* loaded from: classes12.dex */
public final class Monitor {
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private Guard activeGuards;
    private final boolean fair;
    private final ReentrantLock lock;

    @Beta
    /* loaded from: classes12.dex */
    public static abstract class Guard {
        final Condition condition;
        @Weak
        final Monitor monitor;
        @GuardedBy("monitor.lock")
        Guard next;
        @GuardedBy("monitor.lock")
        int waiterCount = 0;

        /* JADX INFO: Access modifiers changed from: protected */
        public Guard(Monitor monitor) {
            this.monitor = (Monitor) Preconditions.checkNotNull(monitor, "monitor");
            this.condition = monitor.lock.newCondition();
        }

        public abstract boolean isSatisfied();
    }

    public Monitor() {
        this(false);
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private void await(Guard guard, boolean z) throws InterruptedException {
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.await();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private boolean awaitNanos(Guard guard, long j2, boolean z) throws InterruptedException {
        boolean z2 = true;
        while (j2 > 0) {
            if (z2) {
                if (z) {
                    try {
                        signalNextWaiter();
                    } finally {
                        if (!z2) {
                            endWaitingFor(guard);
                        }
                    }
                }
                beginWaitingFor(guard);
                z2 = false;
            }
            j2 = guard.condition.awaitNanos(j2);
            if (guard.isSatisfied()) {
                if (!z2) {
                    endWaitingFor(guard);
                }
                return true;
            }
        }
        return false;
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private void awaitUninterruptibly(Guard guard, boolean z) {
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.awaitUninterruptibly();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private void beginWaitingFor(Guard guard) {
        int i2 = guard.waiterCount;
        guard.waiterCount = i2 + 1;
        if (i2 == 0) {
            guard.next = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private void endWaitingFor(Guard guard) {
        int i2 = guard.waiterCount - 1;
        guard.waiterCount = i2;
        if (i2 == 0) {
            Guard guard2 = this.activeGuards;
            Guard guard3 = null;
            while (guard2 != guard) {
                guard3 = guard2;
                guard2 = guard2.next;
            }
            if (guard3 == null) {
                this.activeGuards = guard2.next;
            } else {
                guard3.next = guard2.next;
            }
            guard2.next = null;
        }
    }

    private static long initNanoTime(long j2) {
        if (j2 <= 0) {
            return 0L;
        }
        long nanoTime = System.nanoTime();
        if (nanoTime == 0) {
            return 1L;
        }
        return nanoTime;
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private boolean isSatisfied(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Throwable th) {
            signalAllWaiters();
            throw Throwables.propagate(th);
        }
    }

    private static long remainingNanos(long j2, long j3) {
        if (j3 <= 0) {
            return 0L;
        }
        return j3 - (System.nanoTime() - j2);
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private void signalAllWaiters() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            guard.condition.signalAll();
        }
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private void signalNextWaiter() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            if (isSatisfied(guard)) {
                guard.condition.signal();
                return;
            }
        }
    }

    private static long toSafeNanos(long j2, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j2);
        if (nanos <= 0) {
            return 0L;
        }
        if (nanos > 6917529027641081853L) {
            return 6917529027641081853L;
        }
        return nanos;
    }

    public void enter() {
        this.lock.lock();
    }

    public boolean enterIf(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        }
        throw new IllegalMonitorStateException();
    }

    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lockInterruptibly();
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        }
        throw new IllegalMonitorStateException();
    }

    public void enterInterruptibly() throws InterruptedException {
        this.lock.lockInterruptibly();
    }

    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            reentrantLock.lockInterruptibly();
            try {
                if (guard.isSatisfied()) {
                    return;
                }
                await(guard, isHeldByCurrentThread);
                return;
            } catch (Throwable th) {
                leave();
                throw th;
            }
        }
        throw new IllegalMonitorStateException();
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            reentrantLock.lock();
            try {
                if (guard.isSatisfied()) {
                    return;
                }
                awaitUninterruptibly(guard, isHeldByCurrentThread);
                return;
            } catch (Throwable th) {
                leave();
                throw th;
            }
        }
        throw new IllegalMonitorStateException();
    }

    public int getOccupiedDepth() {
        return this.lock.getHoldCount();
    }

    public int getQueueLength() {
        return this.lock.getQueueLength();
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.monitor == this) {
            this.lock.lock();
            try {
                return guard.waiterCount;
            } finally {
                this.lock.unlock();
            }
        }
        throw new IllegalMonitorStateException();
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.lock.hasQueuedThread(thread);
    }

    public boolean hasQueuedThreads() {
        return this.lock.hasQueuedThreads();
    }

    public boolean hasWaiters(Guard guard) {
        return getWaitQueueLength(guard) > 0;
    }

    public boolean isFair() {
        return this.fair;
    }

    public boolean isOccupied() {
        return this.lock.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public void leave() {
        ReentrantLock reentrantLock = this.lock;
        try {
            if (reentrantLock.getHoldCount() == 1) {
                signalNextWaiter();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    public boolean tryEnterIf(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            if (reentrantLock.tryLock()) {
                try {
                    boolean isSatisfied = guard.isSatisfied();
                    if (!isSatisfied) {
                    }
                    return isSatisfied;
                } finally {
                    reentrantLock.unlock();
                }
            }
            return false;
        }
        throw new IllegalMonitorStateException();
    }

    public void waitFor(Guard guard) throws InterruptedException {
        if ((guard.monitor == this) & this.lock.isHeldByCurrentThread()) {
            if (guard.isSatisfied()) {
                return;
            }
            await(guard, true);
            return;
        }
        throw new IllegalMonitorStateException();
    }

    public void waitForUninterruptibly(Guard guard) {
        if ((guard.monitor == this) & this.lock.isHeldByCurrentThread()) {
            if (guard.isSatisfied()) {
                return;
            }
            awaitUninterruptibly(guard, true);
            return;
        }
        throw new IllegalMonitorStateException();
    }

    public Monitor(boolean z) {
        this.activeGuards = null;
        this.fair = z;
        this.lock = new ReentrantLock(z);
    }

    public boolean enter(long j2, TimeUnit timeUnit) {
        boolean tryLock;
        long safeNanos = toSafeNanos(j2, timeUnit);
        ReentrantLock reentrantLock = this.lock;
        boolean z = true;
        if (!this.fair && reentrantLock.tryLock()) {
            return true;
        }
        boolean interrupted = Thread.interrupted();
        try {
            long nanoTime = System.nanoTime();
            long j3 = safeNanos;
            while (true) {
                try {
                    try {
                        tryLock = reentrantLock.tryLock(j3, TimeUnit.NANOSECONDS);
                        break;
                    } catch (InterruptedException unused) {
                        j3 = remainingNanos(nanoTime, safeNanos);
                        interrupted = true;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            return tryLock;
        } catch (Throwable th2) {
            th = th2;
            z = interrupted;
        }
    }

    public boolean enterInterruptibly(long j2, TimeUnit timeUnit) throws InterruptedException {
        return this.lock.tryLock(j2, timeUnit);
    }

    public boolean waitFor(Guard guard, long j2, TimeUnit timeUnit) throws InterruptedException {
        long safeNanos = toSafeNanos(j2, timeUnit);
        if ((guard.monitor == this) & this.lock.isHeldByCurrentThread()) {
            if (guard.isSatisfied()) {
                return true;
            }
            if (!Thread.interrupted()) {
                return awaitNanos(guard, safeNanos, true);
            }
            throw new InterruptedException();
        }
        throw new IllegalMonitorStateException();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean waitForUninterruptibly(Guard guard, long j2, TimeUnit timeUnit) {
        long safeNanos = toSafeNanos(j2, timeUnit);
        boolean z = true;
        if ((guard.monitor == this) & this.lock.isHeldByCurrentThread()) {
            if (guard.isSatisfied()) {
                return true;
            }
            long initNanoTime = initNanoTime(safeNanos);
            boolean interrupted = Thread.interrupted();
            long j3 = safeNanos;
            boolean z2 = true;
            while (true) {
                try {
                    try {
                        boolean awaitNanos = awaitNanos(guard, j3, z2);
                        if (interrupted) {
                            Thread.currentThread().interrupt();
                        }
                        return awaitNanos;
                    } catch (InterruptedException unused) {
                        if (guard.isSatisfied()) {
                            Thread.currentThread().interrupt();
                            return true;
                        }
                        j3 = remainingNanos(initNanoTime, safeNanos);
                        interrupted = true;
                        z2 = false;
                    } catch (Throwable th) {
                        th = th;
                        z = interrupted;
                        if (z) {
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterIf(Guard guard, long j2, TimeUnit timeUnit) {
        if (guard.monitor == this) {
            if (enter(j2, timeUnit)) {
                try {
                    boolean isSatisfied = guard.isSatisfied();
                    if (!isSatisfied) {
                    }
                    return isSatisfied;
                } finally {
                    this.lock.unlock();
                }
            }
            return false;
        }
        throw new IllegalMonitorStateException();
    }

    public boolean enterIfInterruptibly(Guard guard, long j2, TimeUnit timeUnit) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            if (reentrantLock.tryLock(j2, timeUnit)) {
                try {
                    boolean isSatisfied = guard.isSatisfied();
                    if (!isSatisfied) {
                    }
                    return isSatisfied;
                } finally {
                    reentrantLock.unlock();
                }
            }
            return false;
        }
        throw new IllegalMonitorStateException();
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0047, code lost:
        if (awaitNanos(r11, r0, r3) != false) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean enterWhen(Guard guard, long j2, TimeUnit timeUnit) throws InterruptedException {
        long initNanoTime;
        long safeNanos = toSafeNanos(j2, timeUnit);
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            boolean z = false;
            try {
                if (!this.fair) {
                    if (!Thread.interrupted()) {
                        if (reentrantLock.tryLock()) {
                            initNanoTime = 0;
                            if (!guard.isSatisfied()) {
                                if (initNanoTime != 0) {
                                    safeNanos = remainingNanos(initNanoTime, safeNanos);
                                }
                            }
                            z = true;
                            if (!z) {
                            }
                            return z;
                        }
                    } else {
                        throw new InterruptedException();
                    }
                }
                if (!guard.isSatisfied()) {
                }
                z = true;
                if (!z) {
                }
                return z;
            } catch (Throwable th) {
                if (!isHeldByCurrentThread) {
                    try {
                        signalNextWaiter();
                    } finally {
                        reentrantLock.unlock();
                    }
                }
                throw th;
            }
            initNanoTime = initNanoTime(safeNanos);
            if (!reentrantLock.tryLock(j2, timeUnit)) {
                return false;
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004b A[Catch: all -> 0x0073, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0073, blocks: (B:5:0x0013, B:7:0x001a, B:24:0x004b, B:11:0x0023, B:13:0x0028, B:15:0x0030, B:20:0x003b, B:22:0x0045, B:21:0x0041), top: B:46:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean enterWhenUninterruptibly(Guard guard, long j2, TimeUnit timeUnit) {
        long initNanoTime;
        long remainingNanos;
        long safeNanos = toSafeNanos(j2, timeUnit);
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            boolean interrupted = Thread.interrupted();
            boolean z = true;
            try {
                if (!this.fair && reentrantLock.tryLock()) {
                    initNanoTime = 0;
                    while (!guard.isSatisfied()) {
                        try {
                            if (initNanoTime == 0) {
                                initNanoTime = initNanoTime(safeNanos);
                                remainingNanos = safeNanos;
                            } else {
                                remainingNanos = remainingNanos(initNanoTime, safeNanos);
                            }
                            z = awaitNanos(guard, remainingNanos, isHeldByCurrentThread);
                        } catch (InterruptedException unused) {
                            isHeldByCurrentThread = false;
                            interrupted = true;
                        }
                    }
                    if (!z) {
                        reentrantLock.unlock();
                    }
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    return z;
                }
                initNanoTime = initNanoTime(safeNanos);
                long j3 = safeNanos;
                while (true) {
                    try {
                        try {
                            break;
                        } catch (Throwable th) {
                            th = th;
                            interrupted = true;
                            if (interrupted) {
                                Thread.currentThread().interrupt();
                            }
                            throw th;
                        }
                    } catch (InterruptedException unused2) {
                        j3 = remainingNanos(initNanoTime, safeNanos);
                        interrupted = true;
                    }
                }
                if (!reentrantLock.tryLock(j3, TimeUnit.NANOSECONDS)) {
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    return false;
                }
                while (!guard.isSatisfied()) {
                }
                if (!z) {
                }
                if (interrupted) {
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }
}
