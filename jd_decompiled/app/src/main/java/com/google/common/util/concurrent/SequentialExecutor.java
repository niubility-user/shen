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
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void workOnQueue() {
            /*
                r8 = this;
                r0 = 0
                r1 = 0
            L2:
                boolean r2 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L50
                r1 = r1 | r2
                com.google.common.util.concurrent.SequentialExecutor r2 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L50
                java.util.Queue r2 = com.google.common.util.concurrent.SequentialExecutor.access$100(r2)     // Catch: java.lang.Throwable -> L50
                monitor-enter(r2)     // Catch: java.lang.Throwable -> L50
                com.google.common.util.concurrent.SequentialExecutor r3 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L4d
                java.util.Queue r3 = com.google.common.util.concurrent.SequentialExecutor.access$100(r3)     // Catch: java.lang.Throwable -> L4d
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L4d
                java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch: java.lang.Throwable -> L4d
                if (r3 != 0) goto L2c
                com.google.common.util.concurrent.SequentialExecutor r3 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L4d
                com.google.common.util.concurrent.SequentialExecutor.access$202(r3, r0)     // Catch: java.lang.Throwable -> L4d
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L4d
                if (r1 == 0) goto L2b
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L2b:
                return
            L2c:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L4d
                r3.run()     // Catch: java.lang.RuntimeException -> L31 java.lang.Throwable -> L50
                goto L2
            L31:
                r2 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.SequentialExecutor.access$300()     // Catch: java.lang.Throwable -> L50
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L50
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L50
                r6.<init>()     // Catch: java.lang.Throwable -> L50
                java.lang.String r7 = "Exception while executing runnable "
                r6.append(r7)     // Catch: java.lang.Throwable -> L50
                r6.append(r3)     // Catch: java.lang.Throwable -> L50
                java.lang.String r3 = r6.toString()     // Catch: java.lang.Throwable -> L50
                r4.log(r5, r3, r2)     // Catch: java.lang.Throwable -> L50
                goto L2
            L4d:
                r0 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L4d
                throw r0     // Catch: java.lang.Throwable -> L50
            L50:
                r0 = move-exception
                if (r1 == 0) goto L5a
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                r1.interrupt()
            L5a:
                goto L5c
            L5b:
                throw r0
            L5c:
                goto L5b
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SequentialExecutor.QueueWorker.workOnQueue():void");
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
