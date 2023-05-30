package rx.internal.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.schedulers.GenericScheduledExecutorService;
import rx.internal.schedulers.SchedulerLifecycle;
import rx.internal.util.unsafe.MpmcArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;

/* loaded from: classes11.dex */
public abstract class ObjectPool<T> implements SchedulerLifecycle {
    final int maxSize;
    final int minSize;
    private final AtomicReference<Future<?>> periodicTask;
    Queue<T> pool;
    private final long validationInterval;

    public ObjectPool() {
        this(0, 0, 67L);
    }

    private void initialize(int i2) {
        if (UnsafeAccess.isUnsafeAvailable()) {
            this.pool = new MpmcArrayQueue(Math.max(this.maxSize, 1024));
        } else {
            this.pool = new ConcurrentLinkedQueue();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            this.pool.add(createObject());
        }
    }

    public T borrowObject() {
        T poll = this.pool.poll();
        return poll == null ? createObject() : poll;
    }

    protected abstract T createObject();

    public void returnObject(T t) {
        if (t == null) {
            return;
        }
        this.pool.offer(t);
    }

    @Override // rx.internal.schedulers.SchedulerLifecycle
    public void shutdown() {
        Future<?> andSet = this.periodicTask.getAndSet(null);
        if (andSet != null) {
            andSet.cancel(false);
        }
    }

    @Override // rx.internal.schedulers.SchedulerLifecycle
    public void start() {
        while (this.periodicTask.get() == null) {
            ScheduledExecutorService genericScheduledExecutorService = GenericScheduledExecutorService.getInstance();
            try {
                Runnable runnable = new Runnable() { // from class: rx.internal.util.ObjectPool.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.lang.Runnable
                    public void run() {
                        int size = ObjectPool.this.pool.size();
                        ObjectPool objectPool = ObjectPool.this;
                        int i2 = 0;
                        if (size < objectPool.minSize) {
                            int i3 = objectPool.maxSize - size;
                            while (i2 < i3) {
                                ObjectPool objectPool2 = ObjectPool.this;
                                objectPool2.pool.add(objectPool2.createObject());
                                i2++;
                            }
                            return;
                        }
                        int i4 = objectPool.maxSize;
                        if (size > i4) {
                            int i5 = size - i4;
                            while (i2 < i5) {
                                ObjectPool.this.pool.poll();
                                i2++;
                            }
                        }
                    }
                };
                long j2 = this.validationInterval;
                ScheduledFuture<?> scheduleAtFixedRate = genericScheduledExecutorService.scheduleAtFixedRate(runnable, j2, j2, TimeUnit.SECONDS);
                if (this.periodicTask.compareAndSet(null, scheduleAtFixedRate)) {
                    return;
                }
                scheduleAtFixedRate.cancel(false);
            } catch (RejectedExecutionException e2) {
                RxJavaPluginUtils.handleException(e2);
                return;
            }
        }
    }

    private ObjectPool(int i2, int i3, long j2) {
        this.minSize = i2;
        this.maxSize = i3;
        this.validationInterval = j2;
        this.periodicTask = new AtomicReference<>();
        initialize(i2);
        start();
    }
}
