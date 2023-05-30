package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
@GwtIncompatible
/* loaded from: classes12.dex */
public final class ListenerCallQueue<L> {
    private static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    private final List<PerListenerQueue<L>> listeners = Collections.synchronizedList(new ArrayList());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public interface Event<L> {
        void call(L l2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static final class PerListenerQueue<L> implements Runnable {
        final Executor executor;
        @GuardedBy("this")
        boolean isThreadScheduled;
        final L listener;
        @GuardedBy("this")
        final Queue<Event<L>> waitQueue = Queues.newArrayDeque();
        @GuardedBy("this")
        final Queue<Object> labelQueue = Queues.newArrayDeque();

        PerListenerQueue(L l2, Executor executor) {
            this.listener = (L) Preconditions.checkNotNull(l2);
            this.executor = (Executor) Preconditions.checkNotNull(executor);
        }

        synchronized void add(Event<L> event, Object obj) {
            this.waitQueue.add(event);
            this.labelQueue.add(obj);
        }

        void dispatch() {
            boolean z;
            synchronized (this) {
                z = true;
                if (this.isThreadScheduled) {
                    z = false;
                } else {
                    this.isThreadScheduled = true;
                }
            }
            if (z) {
                try {
                    this.executor.execute(this);
                } catch (RuntimeException e2) {
                    synchronized (this) {
                        this.isThreadScheduled = false;
                        ListenerCallQueue.logger.log(Level.SEVERE, "Exception while running callbacks for " + this.listener + " on " + this.executor, (Throwable) e2);
                        throw e2;
                    }
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
            r2.call(r9.listener);
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0026, code lost:
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0027, code lost:
            com.google.common.util.concurrent.ListenerCallQueue.logger.log(java.util.logging.Level.SEVERE, "Exception while executing callback: " + r9.listener + com.jingdong.common.utils.LangUtils.SINGLE_SPACE + r3, (java.lang.Throwable) r2);
         */
        /* JADX WARN: Removed duplicated region for block: B:27:0x005b  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r9 = this;
            L0:
                r0 = 0
                r1 = 1
                monitor-enter(r9)     // Catch: java.lang.Throwable -> L58
                boolean r2 = r9.isThreadScheduled     // Catch: java.lang.Throwable -> L4c
                com.google.common.base.Preconditions.checkState(r2)     // Catch: java.lang.Throwable -> L4c
                java.util.Queue<com.google.common.util.concurrent.ListenerCallQueue$Event<L>> r2 = r9.waitQueue     // Catch: java.lang.Throwable -> L4c
                java.lang.Object r2 = r2.poll()     // Catch: java.lang.Throwable -> L4c
                com.google.common.util.concurrent.ListenerCallQueue$Event r2 = (com.google.common.util.concurrent.ListenerCallQueue.Event) r2     // Catch: java.lang.Throwable -> L4c
                java.util.Queue<java.lang.Object> r3 = r9.labelQueue     // Catch: java.lang.Throwable -> L4c
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L4c
                if (r2 != 0) goto L1f
                r9.isThreadScheduled = r0     // Catch: java.lang.Throwable -> L4c
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L1c
                return
            L1c:
                r1 = move-exception
                r2 = 0
                goto L4f
            L1f:
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L4c
                L r4 = r9.listener     // Catch: java.lang.RuntimeException -> L26 java.lang.Throwable -> L58
                r2.call(r4)     // Catch: java.lang.RuntimeException -> L26 java.lang.Throwable -> L58
                goto L0
            L26:
                r2 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.ListenerCallQueue.access$000()     // Catch: java.lang.Throwable -> L58
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L58
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L58
                r6.<init>()     // Catch: java.lang.Throwable -> L58
                java.lang.String r7 = "Exception while executing callback: "
                r6.append(r7)     // Catch: java.lang.Throwable -> L58
                L r7 = r9.listener     // Catch: java.lang.Throwable -> L58
                r6.append(r7)     // Catch: java.lang.Throwable -> L58
                java.lang.String r7 = " "
                r6.append(r7)     // Catch: java.lang.Throwable -> L58
                r6.append(r3)     // Catch: java.lang.Throwable -> L58
                java.lang.String r3 = r6.toString()     // Catch: java.lang.Throwable -> L58
                r4.log(r5, r3, r2)     // Catch: java.lang.Throwable -> L58
                goto L0
            L4c:
                r2 = move-exception
                r1 = r2
                r2 = 1
            L4f:
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L56
                throw r1     // Catch: java.lang.Throwable -> L51
            L51:
                r1 = move-exception
                r8 = r2
                r2 = r1
                r1 = r8
                goto L59
            L56:
                r1 = move-exception
                goto L4f
            L58:
                r2 = move-exception
            L59:
                if (r1 == 0) goto L63
                monitor-enter(r9)
                r9.isThreadScheduled = r0     // Catch: java.lang.Throwable -> L60
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L60
                goto L63
            L60:
                r0 = move-exception
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L60
                throw r0
            L63:
                goto L65
            L64:
                throw r2
            L65:
                goto L64
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.PerListenerQueue.run():void");
        }
    }

    private void enqueueHelper(Event<L> event, Object obj) {
        Preconditions.checkNotNull(event, "event");
        Preconditions.checkNotNull(obj, Constant.KEY_PROMOTION_LABEL);
        synchronized (this.listeners) {
            Iterator<PerListenerQueue<L>> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().add(event, obj);
            }
        }
    }

    public void addListener(L l2, Executor executor) {
        Preconditions.checkNotNull(l2, CartCleanConstants.CART_CLEAN_DIALOG_LISTENER);
        Preconditions.checkNotNull(executor, "executor");
        this.listeners.add(new PerListenerQueue<>(l2, executor));
    }

    public void dispatch() {
        for (int i2 = 0; i2 < this.listeners.size(); i2++) {
            this.listeners.get(i2).dispatch();
        }
    }

    public void enqueue(Event<L> event) {
        enqueueHelper(event, event);
    }

    public void enqueue(Event<L> event, String str) {
        enqueueHelper(event, str);
    }
}
