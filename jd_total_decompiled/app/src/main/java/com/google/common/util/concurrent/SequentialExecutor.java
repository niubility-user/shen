package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
@GwtIncompatible
/* loaded from: classes12.dex */
public final class SequentialExecutor implements Executor {
    private static final Logger log = Logger.getLogger(SequentialExecutor.class.getName());
    private final Executor executor;
    @GuardedBy("queue")
    private final Queue<Runnable> queue = new ArrayDeque();
    @GuardedBy("queue")
    private boolean isWorkerRunning = false;
    private final QueueWorker worker = new QueueWorker();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public final class QueueWorker implements Runnable {
        private QueueWorker() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x002d, code lost:
            r3.run();
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
            com.google.common.util.concurrent.SequentialExecutor.log.log(java.util.logging.Level.SEVERE, "Exception while executing runnable " + r3, (java.lang.Throwable) r2);
         */
        /* JADX WARN: Removed duplicated region for block: B:10:0x0024 A[DONT_GENERATE] */
        /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private void workOnQueue() {
            boolean z = false;
            while (true) {
                try {
                    z |= Thread.interrupted();
                } finally {
                }
                synchronized (SequentialExecutor.this.queue) {
                    Runnable runnable = (Runnable) SequentialExecutor.this.queue.poll();
                    if (runnable == null) {
                        break;
                    }
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (z) {
                    return;
                }
                return;
            }
            SequentialExecutor.this.isWorkerRunning = false;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                workOnQueue();
            } catch (Error e2) {
                synchronized (SequentialExecutor.this.queue) {
                    SequentialExecutor.this.isWorkerRunning = false;
                    throw e2;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SequentialExecutor(Executor executor) {
        this.executor = (Executor) Preconditions.checkNotNull(executor);
    }

    private void startQueueWorker() {
        try {
            this.executor.execute(this.worker);
        } catch (Throwable th) {
            synchronized (this.queue) {
                this.isWorkerRunning = false;
                throw th;
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        synchronized (this.queue) {
            this.queue.add(runnable);
            if (this.isWorkerRunning) {
                return;
            }
            this.isWorkerRunning = true;
            startQueueWorker();
        }
    }
}
