package rx.schedulers;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.annotations.Experimental;
import rx.internal.schedulers.ExecutorScheduler;
import rx.internal.schedulers.GenericScheduledExecutorService;
import rx.internal.schedulers.SchedulerLifecycle;
import rx.internal.util.RxRingBuffer;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;

/* loaded from: classes11.dex */
public final class Schedulers {
    private static final AtomicReference<Schedulers> INSTANCE = new AtomicReference<>();
    private final Scheduler computationScheduler;
    private final Scheduler ioScheduler;
    private final Scheduler newThreadScheduler;

    private Schedulers() {
        RxJavaSchedulersHook schedulersHook = RxJavaPlugins.getInstance().getSchedulersHook();
        Scheduler computationScheduler = schedulersHook.getComputationScheduler();
        if (computationScheduler != null) {
            this.computationScheduler = computationScheduler;
        } else {
            this.computationScheduler = RxJavaSchedulersHook.createComputationScheduler();
        }
        Scheduler iOScheduler = schedulersHook.getIOScheduler();
        if (iOScheduler != null) {
            this.ioScheduler = iOScheduler;
        } else {
            this.ioScheduler = RxJavaSchedulersHook.createIoScheduler();
        }
        Scheduler newThreadScheduler = schedulersHook.getNewThreadScheduler();
        if (newThreadScheduler != null) {
            this.newThreadScheduler = newThreadScheduler;
        } else {
            this.newThreadScheduler = RxJavaSchedulersHook.createNewThreadScheduler();
        }
    }

    public static Scheduler computation() {
        return getInstance().computationScheduler;
    }

    public static Scheduler from(Executor executor) {
        return new ExecutorScheduler(executor);
    }

    private static Schedulers getInstance() {
        while (true) {
            AtomicReference<Schedulers> atomicReference = INSTANCE;
            Schedulers schedulers = atomicReference.get();
            if (schedulers != null) {
                return schedulers;
            }
            Schedulers schedulers2 = new Schedulers();
            if (atomicReference.compareAndSet(null, schedulers2)) {
                return schedulers2;
            }
            schedulers2.shutdownInstance();
        }
    }

    public static Scheduler immediate() {
        return rx.internal.schedulers.ImmediateScheduler.INSTANCE;
    }

    public static Scheduler io() {
        return getInstance().ioScheduler;
    }

    public static Scheduler newThread() {
        return getInstance().newThreadScheduler;
    }

    @Experimental
    public static void reset() {
        Schedulers andSet = INSTANCE.getAndSet(null);
        if (andSet != null) {
            andSet.shutdownInstance();
        }
    }

    public static void shutdown() {
        Schedulers schedulers = getInstance();
        schedulers.shutdownInstance();
        synchronized (schedulers) {
            GenericScheduledExecutorService.INSTANCE.shutdown();
            RxRingBuffer.SPSC_POOL.shutdown();
            RxRingBuffer.SPMC_POOL.shutdown();
        }
    }

    static void start() {
        Schedulers schedulers = getInstance();
        schedulers.startInstance();
        synchronized (schedulers) {
            GenericScheduledExecutorService.INSTANCE.start();
            RxRingBuffer.SPSC_POOL.start();
            RxRingBuffer.SPMC_POOL.start();
        }
    }

    public static TestScheduler test() {
        return new TestScheduler();
    }

    public static Scheduler trampoline() {
        return rx.internal.schedulers.TrampolineScheduler.INSTANCE;
    }

    synchronized void shutdownInstance() {
        Scheduler scheduler = this.computationScheduler;
        if (scheduler instanceof SchedulerLifecycle) {
            ((SchedulerLifecycle) scheduler).shutdown();
        }
        Scheduler scheduler2 = this.ioScheduler;
        if (scheduler2 instanceof SchedulerLifecycle) {
            ((SchedulerLifecycle) scheduler2).shutdown();
        }
        Scheduler scheduler3 = this.newThreadScheduler;
        if (scheduler3 instanceof SchedulerLifecycle) {
            ((SchedulerLifecycle) scheduler3).shutdown();
        }
    }

    synchronized void startInstance() {
        Scheduler scheduler = this.computationScheduler;
        if (scheduler instanceof SchedulerLifecycle) {
            ((SchedulerLifecycle) scheduler).start();
        }
        Scheduler scheduler2 = this.ioScheduler;
        if (scheduler2 instanceof SchedulerLifecycle) {
            ((SchedulerLifecycle) scheduler2).start();
        }
        Scheduler scheduler3 = this.newThreadScheduler;
        if (scheduler3 instanceof SchedulerLifecycle) {
            ((SchedulerLifecycle) scheduler3).start();
        }
    }
}
