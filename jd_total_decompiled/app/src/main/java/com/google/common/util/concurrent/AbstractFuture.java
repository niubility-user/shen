package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import com.google.errorprone.annotations.ForOverride;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import sun.misc.Unsafe;

@DoNotMock("Use Futures.immediate*Future or SettableFuture")
@GwtCompatible(emulated = true)
/* loaded from: classes12.dex */
public abstract class AbstractFuture<V> extends FluentFuture<V> {
    private static final AtomicHelper ATOMIC_HELPER;
    private static final Object NULL;
    private static final long SPIN_THRESHOLD_NANOS = 1000;
    private volatile Listener listeners;
    private volatile Object value;
    private volatile Waiter waiters;
    private static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", DYConstants.DY_FALSE));
    private static final Logger log = Logger.getLogger(AbstractFuture.class.getName());

    /* loaded from: classes12.dex */
    public static abstract class AtomicHelper {
        private AtomicHelper() {
        }

        abstract boolean casListeners(AbstractFuture<?> abstractFuture, Listener listener, Listener listener2);

        abstract boolean casValue(AbstractFuture<?> abstractFuture, Object obj, Object obj2);

        abstract boolean casWaiters(AbstractFuture<?> abstractFuture, Waiter waiter, Waiter waiter2);

        abstract void putNext(Waiter waiter, Waiter waiter2);

        abstract void putThread(Waiter waiter, Thread thread);
    }

    /* loaded from: classes12.dex */
    public static final class Cancellation {
        static final Cancellation CAUSELESS_CANCELLED;
        static final Cancellation CAUSELESS_INTERRUPTED;
        @NullableDecl
        final Throwable cause;
        final boolean wasInterrupted;

        static {
            if (AbstractFuture.GENERATE_CANCELLATION_CAUSES) {
                CAUSELESS_CANCELLED = null;
                CAUSELESS_INTERRUPTED = null;
                return;
            }
            CAUSELESS_CANCELLED = new Cancellation(false, null);
            CAUSELESS_INTERRUPTED = new Cancellation(true, null);
        }

        Cancellation(boolean z, @NullableDecl Throwable th) {
            this.wasInterrupted = z;
            this.cause = th;
        }
    }

    /* loaded from: classes12.dex */
    public static final class Failure {
        static final Failure FALLBACK_INSTANCE = new Failure(new Throwable("Failure occurred while trying to finish a future.") { // from class: com.google.common.util.concurrent.AbstractFuture.Failure.1
            @Override // java.lang.Throwable
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable exception;

        Failure(Throwable th) {
            this.exception = (Throwable) Preconditions.checkNotNull(th);
        }
    }

    /* loaded from: classes12.dex */
    public static final class Listener {
        static final Listener TOMBSTONE = new Listener(null, null);
        final Executor executor;
        @NullableDecl
        Listener next;
        final Runnable task;

        Listener(Runnable runnable, Executor executor) {
            this.task = runnable;
            this.executor = executor;
        }
    }

    /* loaded from: classes12.dex */
    private static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicReferenceFieldUpdater<AbstractFuture, Listener> listenersUpdater;
        final AtomicReferenceFieldUpdater<AbstractFuture, Object> valueUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Waiter> waiterNextUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Thread> waiterThreadUpdater;
        final AtomicReferenceFieldUpdater<AbstractFuture, Waiter> waitersUpdater;

        SafeAtomicHelper(AtomicReferenceFieldUpdater<Waiter, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<Waiter, Waiter> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<AbstractFuture, Waiter> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<AbstractFuture, Listener> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<AbstractFuture, Object> atomicReferenceFieldUpdater5) {
            super();
            this.waiterThreadUpdater = atomicReferenceFieldUpdater;
            this.waiterNextUpdater = atomicReferenceFieldUpdater2;
            this.waitersUpdater = atomicReferenceFieldUpdater3;
            this.listenersUpdater = atomicReferenceFieldUpdater4;
            this.valueUpdater = atomicReferenceFieldUpdater5;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        boolean casListeners(AbstractFuture<?> abstractFuture, Listener listener, Listener listener2) {
            return this.listenersUpdater.compareAndSet(abstractFuture, listener, listener2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        boolean casValue(AbstractFuture<?> abstractFuture, Object obj, Object obj2) {
            return this.valueUpdater.compareAndSet(abstractFuture, obj, obj2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        boolean casWaiters(AbstractFuture<?> abstractFuture, Waiter waiter, Waiter waiter2) {
            return this.waitersUpdater.compareAndSet(abstractFuture, waiter, waiter2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        void putNext(Waiter waiter, Waiter waiter2) {
            this.waiterNextUpdater.lazySet(waiter, waiter2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        void putThread(Waiter waiter, Thread thread) {
            this.waiterThreadUpdater.lazySet(waiter, thread);
        }
    }

    /* loaded from: classes12.dex */
    public static final class SetFuture<V> implements Runnable {
        final ListenableFuture<? extends V> future;
        final AbstractFuture<V> owner;

        SetFuture(AbstractFuture<V> abstractFuture, ListenableFuture<? extends V> listenableFuture) {
            this.owner = abstractFuture;
            this.future = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((AbstractFuture) this.owner).value != this) {
                return;
            }
            if (AbstractFuture.ATOMIC_HELPER.casValue(this.owner, this, AbstractFuture.getFutureValue(this.future))) {
                AbstractFuture.complete(this.owner);
            }
        }
    }

    /* loaded from: classes12.dex */
    private static final class SynchronizedHelper extends AtomicHelper {
        private SynchronizedHelper() {
            super();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        boolean casListeners(AbstractFuture<?> abstractFuture, Listener listener, Listener listener2) {
            synchronized (abstractFuture) {
                if (((AbstractFuture) abstractFuture).listeners == listener) {
                    ((AbstractFuture) abstractFuture).listeners = listener2;
                    return true;
                }
                return false;
            }
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        boolean casValue(AbstractFuture<?> abstractFuture, Object obj, Object obj2) {
            synchronized (abstractFuture) {
                if (((AbstractFuture) abstractFuture).value == obj) {
                    ((AbstractFuture) abstractFuture).value = obj2;
                    return true;
                }
                return false;
            }
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        boolean casWaiters(AbstractFuture<?> abstractFuture, Waiter waiter, Waiter waiter2) {
            synchronized (abstractFuture) {
                if (((AbstractFuture) abstractFuture).waiters == waiter) {
                    ((AbstractFuture) abstractFuture).waiters = waiter2;
                    return true;
                }
                return false;
            }
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        void putNext(Waiter waiter, Waiter waiter2) {
            waiter.next = waiter2;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        void putThread(Waiter waiter, Thread thread) {
            waiter.thread = thread;
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class TrustedFuture<V> extends AbstractFuture<V> {
        @Override // com.google.common.util.concurrent.AbstractFuture, com.google.common.util.concurrent.ListenableFuture
        public final void addListener(Runnable runnable, Executor executor) {
            super.addListener(runnable, executor);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        @CanIgnoreReturnValue
        public final boolean cancel(boolean z) {
            return super.cancel(z);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        @CanIgnoreReturnValue
        public final V get() throws InterruptedException, ExecutionException {
            return (V) super.get();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final boolean isCancelled() {
            return super.isCancelled();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final boolean isDone() {
            return super.isDone();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        @CanIgnoreReturnValue
        public final V get(long j2, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return (V) super.get(j2, timeUnit);
        }
    }

    /* loaded from: classes12.dex */
    private static final class UnsafeAtomicHelper extends AtomicHelper {
        static final long LISTENERS_OFFSET;
        static final Unsafe UNSAFE;
        static final long VALUE_OFFSET;
        static final long WAITERS_OFFSET;
        static final long WAITER_NEXT_OFFSET;
        static final long WAITER_THREAD_OFFSET;

        static {
            Unsafe unsafe;
            try {
                try {
                    unsafe = Unsafe.getUnsafe();
                } catch (SecurityException unused) {
                    unsafe = (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.common.util.concurrent.AbstractFuture.UnsafeAtomicHelper.1
                        @Override // java.security.PrivilegedExceptionAction
                        public Unsafe run() throws Exception {
                            for (Field field : Unsafe.class.getDeclaredFields()) {
                                field.setAccessible(true);
                                Object obj = field.get(null);
                                if (Unsafe.class.isInstance(obj)) {
                                    return (Unsafe) Unsafe.class.cast(obj);
                                }
                            }
                            throw new NoSuchFieldError("the Unsafe");
                        }
                    });
                }
                try {
                    WAITERS_OFFSET = unsafe.objectFieldOffset(AbstractFuture.class.getDeclaredField("waiters"));
                    LISTENERS_OFFSET = unsafe.objectFieldOffset(AbstractFuture.class.getDeclaredField("listeners"));
                    VALUE_OFFSET = unsafe.objectFieldOffset(AbstractFuture.class.getDeclaredField("value"));
                    WAITER_THREAD_OFFSET = unsafe.objectFieldOffset(Waiter.class.getDeclaredField("thread"));
                    WAITER_NEXT_OFFSET = unsafe.objectFieldOffset(Waiter.class.getDeclaredField("next"));
                    UNSAFE = unsafe;
                } catch (Exception e2) {
                    Throwables.throwIfUnchecked(e2);
                    throw new RuntimeException(e2);
                }
            } catch (PrivilegedActionException e3) {
                throw new RuntimeException("Could not initialize intrinsics", e3.getCause());
            }
        }

        private UnsafeAtomicHelper() {
            super();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        boolean casListeners(AbstractFuture<?> abstractFuture, Listener listener, Listener listener2) {
            return UNSAFE.compareAndSwapObject(abstractFuture, LISTENERS_OFFSET, listener, listener2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        boolean casValue(AbstractFuture<?> abstractFuture, Object obj, Object obj2) {
            return UNSAFE.compareAndSwapObject(abstractFuture, VALUE_OFFSET, obj, obj2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        boolean casWaiters(AbstractFuture<?> abstractFuture, Waiter waiter, Waiter waiter2) {
            return UNSAFE.compareAndSwapObject(abstractFuture, WAITERS_OFFSET, waiter, waiter2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        void putNext(Waiter waiter, Waiter waiter2) {
            UNSAFE.putObject(waiter, WAITER_NEXT_OFFSET, waiter2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        void putThread(Waiter waiter, Thread thread) {
            UNSAFE.putObject(waiter, WAITER_THREAD_OFFSET, thread);
        }
    }

    /* loaded from: classes12.dex */
    public static final class Waiter {
        static final Waiter TOMBSTONE = new Waiter(false);
        @NullableDecl
        volatile Waiter next;
        @NullableDecl
        volatile Thread thread;

        Waiter(boolean z) {
        }

        void setNext(Waiter waiter) {
            AbstractFuture.ATOMIC_HELPER.putNext(this, waiter);
        }

        void unpark() {
            Thread thread = this.thread;
            if (thread != null) {
                this.thread = null;
                LockSupport.unpark(thread);
            }
        }

        Waiter() {
            AbstractFuture.ATOMIC_HELPER.putThread(this, Thread.currentThread());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.logging.Logger] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.google.common.util.concurrent.AbstractFuture$1] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.google.common.util.concurrent.AbstractFuture$UnsafeAtomicHelper] */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.google.common.util.concurrent.AbstractFuture$SafeAtomicHelper] */
    static {
        SynchronizedHelper synchronizedHelper;
        ?? r1 = 0;
        r1 = 0;
        try {
            synchronizedHelper = new UnsafeAtomicHelper();
            th = null;
        } catch (Throwable th) {
            th = th;
            try {
                synchronizedHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Waiter.class, "next"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Waiter.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Listener.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Object.class, "value"));
            } catch (Throwable th2) {
                synchronizedHelper = new SynchronizedHelper();
                r1 = th2;
            }
        }
        ATOMIC_HELPER = synchronizedHelper;
        if (r1 != 0) {
            ?? r0 = log;
            Level level = Level.SEVERE;
            r0.log(level, "UnsafeAtomicHelper is broken!", th);
            r0.log(level, "SafeAtomicHelper is broken!", r1);
        }
        NULL = new Object();
    }

    private void addDoneString(StringBuilder sb) {
        try {
            Object done = Futures.getDone(this);
            sb.append("SUCCESS, result=[");
            sb.append(done);
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e3) {
            sb.append("FAILURE, cause=[");
            sb.append(e3.getCause());
            sb.append("]");
        }
    }

    private static CancellationException cancellationExceptionWithCause(@NullableDecl String str, @NullableDecl Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    private Listener clearListeners(Listener listener) {
        Listener listener2;
        do {
            listener2 = this.listeners;
        } while (!ATOMIC_HELPER.casListeners(this, listener2, Listener.TOMBSTONE));
        Listener listener3 = listener;
        Listener listener4 = listener2;
        while (listener4 != null) {
            Listener listener5 = listener4.next;
            listener4.next = listener3;
            listener3 = listener4;
            listener4 = listener5;
        }
        return listener3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.google.common.util.concurrent.AbstractFuture$AtomicHelper] */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.google.common.util.concurrent.AbstractFuture<?>] */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.common.util.concurrent.AbstractFuture] */
    /* JADX WARN: Type inference failed for: r4v6, types: [com.google.common.util.concurrent.AbstractFuture<V>, com.google.common.util.concurrent.AbstractFuture] */
    public static void complete(AbstractFuture<?> abstractFuture) {
        Listener listener = null;
        while (true) {
            abstractFuture.releaseWaiters();
            abstractFuture.afterDone();
            Listener clearListeners = abstractFuture.clearListeners(listener);
            while (clearListeners != null) {
                listener = clearListeners.next;
                Runnable runnable = clearListeners.task;
                if (runnable instanceof SetFuture) {
                    SetFuture setFuture = (SetFuture) runnable;
                    abstractFuture = setFuture.owner;
                    if (((AbstractFuture) abstractFuture).value == setFuture) {
                        if (ATOMIC_HELPER.casValue(abstractFuture, setFuture, getFutureValue(setFuture.future))) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    executeListener(runnable, clearListeners.executor);
                }
                clearListeners = listener;
            }
            return;
        }
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e2) {
            log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private V getDoneValue(Object obj) throws ExecutionException {
        if (!(obj instanceof Cancellation)) {
            if (!(obj instanceof Failure)) {
                if (obj == NULL) {
                    return null;
                }
                return obj;
            }
            throw new ExecutionException(((Failure) obj).exception);
        }
        throw cancellationExceptionWithCause("Task was cancelled.", ((Cancellation) obj).cause);
    }

    public static Object getFutureValue(ListenableFuture<?> listenableFuture) {
        Object failure;
        if (listenableFuture instanceof TrustedFuture) {
            Object obj = ((AbstractFuture) listenableFuture).value;
            if (obj instanceof Cancellation) {
                Cancellation cancellation = (Cancellation) obj;
                return cancellation.wasInterrupted ? cancellation.cause != null ? new Cancellation(false, cancellation.cause) : Cancellation.CAUSELESS_CANCELLED : obj;
            }
            return obj;
        }
        try {
            Object done = Futures.getDone(listenableFuture);
            return done == null ? NULL : done;
        } catch (CancellationException e2) {
            failure = new Cancellation(false, e2);
            return failure;
        } catch (ExecutionException e3) {
            failure = new Failure(e3.getCause());
            return failure;
        } catch (Throwable th) {
            failure = new Failure(th);
            return failure;
        }
    }

    private void releaseWaiters() {
        Waiter waiter;
        do {
            waiter = this.waiters;
        } while (!ATOMIC_HELPER.casWaiters(this, waiter, Waiter.TOMBSTONE));
        while (waiter != null) {
            waiter.unpark();
            waiter = waiter.next;
        }
    }

    private void removeWaiter(Waiter waiter) {
        waiter.thread = null;
        while (true) {
            Waiter waiter2 = this.waiters;
            if (waiter2 == Waiter.TOMBSTONE) {
                return;
            }
            Waiter waiter3 = null;
            while (waiter2 != null) {
                Waiter waiter4 = waiter2.next;
                if (waiter2.thread != null) {
                    waiter3 = waiter2;
                } else if (waiter3 != null) {
                    waiter3.next = waiter4;
                    if (waiter3.thread == null) {
                        break;
                    }
                } else if (!ATOMIC_HELPER.casWaiters(this, waiter2, waiter4)) {
                    break;
                }
                waiter2 = waiter4;
            }
            return;
        }
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        Listener listener = this.listeners;
        if (listener != Listener.TOMBSTONE) {
            Listener listener2 = new Listener(runnable, executor);
            do {
                listener2.next = listener;
                if (ATOMIC_HELPER.casListeners(this, listener, listener2)) {
                    return;
                }
                listener = this.listeners;
            } while (listener != Listener.TOMBSTONE);
        }
        executeListener(runnable, executor);
    }

    @Beta
    @ForOverride
    public void afterDone() {
    }

    @Override // java.util.concurrent.Future
    @CanIgnoreReturnValue
    public boolean cancel(boolean z) {
        Object obj = this.value;
        if (!(obj == null) && !(obj instanceof SetFuture)) {
            return false;
        }
        Cancellation cancellation = GENERATE_CANCELLATION_CAUSES ? new Cancellation(z, new CancellationException("Future.cancel() was called.")) : z ? Cancellation.CAUSELESS_INTERRUPTED : Cancellation.CAUSELESS_CANCELLED;
        boolean z2 = false;
        AbstractFuture<V> abstractFuture = this;
        while (true) {
            if (ATOMIC_HELPER.casValue(abstractFuture, obj, cancellation)) {
                if (z) {
                    abstractFuture.interruptTask();
                }
                complete(abstractFuture);
                if (!(obj instanceof SetFuture)) {
                    return true;
                }
                ListenableFuture<? extends V> listenableFuture = ((SetFuture) obj).future;
                if (listenableFuture instanceof TrustedFuture) {
                    abstractFuture = (AbstractFuture) listenableFuture;
                    obj = abstractFuture.value;
                    if (!(obj == null) && !(obj instanceof SetFuture)) {
                        return true;
                    }
                    z2 = true;
                } else {
                    listenableFuture.cancel(z);
                    return true;
                }
            } else {
                obj = abstractFuture.value;
                if (!(obj instanceof SetFuture)) {
                    return z2;
                }
            }
        }
    }

    @Override // java.util.concurrent.Future
    @CanIgnoreReturnValue
    public V get(long j2, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        long nanos = timeUnit.toNanos(j2);
        if (!Thread.interrupted()) {
            Object obj = this.value;
            if ((obj != null) & (!(obj instanceof SetFuture))) {
                return getDoneValue(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
            if (nanos >= 1000) {
                Waiter waiter = this.waiters;
                if (waiter != Waiter.TOMBSTONE) {
                    Waiter waiter2 = new Waiter();
                    do {
                        waiter2.setNext(waiter);
                        if (ATOMIC_HELPER.casWaiters(this, waiter, waiter2)) {
                            do {
                                LockSupport.parkNanos(this, nanos);
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.value;
                                    if ((obj2 != null) & (!(obj2 instanceof SetFuture))) {
                                        return getDoneValue(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    removeWaiter(waiter2);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            removeWaiter(waiter2);
                        } else {
                            waiter = this.waiters;
                        }
                    } while (waiter != Waiter.TOMBSTONE);
                }
                return getDoneValue(this.value);
            }
            while (nanos > 0) {
                Object obj3 = this.value;
                if ((obj3 != null) & (!(obj3 instanceof SetFuture))) {
                    return getDoneValue(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String abstractFuture = toString();
            if (isDone()) {
                throw new TimeoutException("Waited " + j2 + LangUtils.SINGLE_SPACE + Ascii.toLowerCase(timeUnit.toString()) + " but future completed as timeout expired");
            }
            throw new TimeoutException("Waited " + j2 + LangUtils.SINGLE_SPACE + Ascii.toLowerCase(timeUnit.toString()) + " for " + abstractFuture);
        }
        throw new InterruptedException();
    }

    protected void interruptTask() {
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.value instanceof Cancellation;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return (!(r0 instanceof SetFuture)) & (this.value != null);
    }

    public final void maybePropagateCancellationTo(@NullableDecl Future<?> future) {
        if ((future != null) && isCancelled()) {
            future.cancel(wasInterrupted());
        }
    }

    @NullableDecl
    protected String pendingToString() {
        Object obj = this.value;
        if (obj instanceof SetFuture) {
            return "setFuture=[" + ((SetFuture) obj).future + "]";
        } else if (this instanceof ScheduledFuture) {
            return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
        } else {
            return null;
        }
    }

    @CanIgnoreReturnValue
    public boolean set(@NullableDecl V v) {
        if (v == null) {
            v = (V) NULL;
        }
        if (ATOMIC_HELPER.casValue(this, null, v)) {
            complete(this);
            return true;
        }
        return false;
    }

    @CanIgnoreReturnValue
    public boolean setException(Throwable th) {
        if (ATOMIC_HELPER.casValue(this, null, new Failure((Throwable) Preconditions.checkNotNull(th)))) {
            complete(this);
            return true;
        }
        return false;
    }

    @CanIgnoreReturnValue
    @Beta
    public boolean setFuture(ListenableFuture<? extends V> listenableFuture) {
        Failure failure;
        Preconditions.checkNotNull(listenableFuture);
        Object obj = this.value;
        if (obj == null) {
            if (listenableFuture.isDone()) {
                if (ATOMIC_HELPER.casValue(this, null, getFutureValue(listenableFuture))) {
                    complete(this);
                    return true;
                }
                return false;
            }
            SetFuture setFuture = new SetFuture(this, listenableFuture);
            if (ATOMIC_HELPER.casValue(this, null, setFuture)) {
                try {
                    listenableFuture.addListener(setFuture, MoreExecutors.directExecutor());
                } catch (Throwable th) {
                    try {
                        failure = new Failure(th);
                    } catch (Throwable unused) {
                        failure = Failure.FALLBACK_INSTANCE;
                    }
                    ATOMIC_HELPER.casValue(this, setFuture, failure);
                }
                return true;
            }
            obj = this.value;
        }
        if (obj instanceof Cancellation) {
            listenableFuture.cancel(((Cancellation) obj).wasInterrupted);
        }
        return false;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            addDoneString(sb);
        } else {
            try {
                str = pendingToString();
            } catch (RuntimeException e2) {
                str = "Exception thrown from implementation: " + e2.getClass();
            }
            if (!Strings.isNullOrEmpty(str)) {
                sb.append("PENDING, info=[");
                sb.append(str);
                sb.append("]");
            } else if (isDone()) {
                addDoneString(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public final Throwable trustedGetException() {
        return ((Failure) this.value).exception;
    }

    public final boolean wasInterrupted() {
        Object obj = this.value;
        return (obj instanceof Cancellation) && ((Cancellation) obj).wasInterrupted;
    }

    @Override // java.util.concurrent.Future
    @CanIgnoreReturnValue
    public V get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) & (!(obj2 instanceof SetFuture))) {
                return getDoneValue(obj2);
            }
            Waiter waiter = this.waiters;
            if (waiter != Waiter.TOMBSTONE) {
                Waiter waiter2 = new Waiter();
                do {
                    waiter2.setNext(waiter);
                    if (ATOMIC_HELPER.casWaiters(this, waiter, waiter2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                removeWaiter(waiter2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof SetFuture))));
                        return getDoneValue(obj);
                    }
                    waiter = this.waiters;
                } while (waiter != Waiter.TOMBSTONE);
            }
            return getDoneValue(this.value);
        }
        throw new InterruptedException();
    }
}
