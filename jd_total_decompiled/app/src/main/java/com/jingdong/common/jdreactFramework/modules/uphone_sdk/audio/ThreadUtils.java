package com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class ThreadUtils {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.ThreadUtils$1CaughtException  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class C1CaughtException {

        /* renamed from: e  reason: collision with root package name */
        Exception f12359e;

        C1CaughtException() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Field signature parse error: value
    jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: TV at position 1 ('V'), unexpected: T
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
    	at jadx.core.dex.visitors.SignatureProcessor.parseFieldSignature(SignatureProcessor.java:128)
    	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:36)
     */
    /* renamed from: com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.ThreadUtils$1Result  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class C1Result {
        public Object value;

        C1Result() {
        }
    }

    /* loaded from: classes5.dex */
    public interface BlockingOperation {
        void run() throws InterruptedException;
    }

    /* loaded from: classes5.dex */
    public static class ThreadChecker {
        private Thread thread = Thread.currentThread();

        public void checkIsOnValidThread() {
            if (this.thread == null) {
                this.thread = Thread.currentThread();
            }
            if (Thread.currentThread() != this.thread) {
                throw new IllegalStateException("Wrong thread");
            }
        }

        public void detachThread() {
            this.thread = null;
        }
    }

    public static void awaitUninterruptibly(final CountDownLatch countDownLatch) {
        executeUninterruptibly(new BlockingOperation() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.ThreadUtils.2
            @Override // com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.ThreadUtils.BlockingOperation
            public void run() throws InterruptedException {
                countDownLatch.await();
            }
        });
    }

    public static void checkIsOnMainThread() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new IllegalStateException("Not on main thread!");
        }
    }

    static StackTraceElement[] concatStackTraces(StackTraceElement[] stackTraceElementArr, StackTraceElement[] stackTraceElementArr2) {
        StackTraceElement[] stackTraceElementArr3 = new StackTraceElement[stackTraceElementArr.length + stackTraceElementArr2.length];
        System.arraycopy(stackTraceElementArr, 0, stackTraceElementArr3, 0, stackTraceElementArr.length);
        System.arraycopy(stackTraceElementArr2, 0, stackTraceElementArr3, stackTraceElementArr.length, stackTraceElementArr2.length);
        return stackTraceElementArr3;
    }

    public static void executeUninterruptibly(BlockingOperation blockingOperation) {
        boolean z = false;
        while (true) {
            try {
                blockingOperation.run();
                break;
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public static <V> V invokeAtFrontUninterruptibly(Handler handler, final Callable<V> callable) {
        if (handler.getLooper().getThread() == Thread.currentThread()) {
            try {
                return callable.call();
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
        final C1Result c1Result = new C1Result();
        final C1CaughtException c1CaughtException = new C1CaughtException();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        handler.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.ThreadUtils.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    C1Result.this.value = callable.call();
                } catch (Exception e3) {
                    c1CaughtException.f12359e = e3;
                }
                countDownLatch.countDown();
            }
        });
        awaitUninterruptibly(countDownLatch);
        if (c1CaughtException.f12359e == null) {
            return (V) c1Result.value;
        }
        RuntimeException runtimeException = new RuntimeException(c1CaughtException.f12359e);
        runtimeException.setStackTrace(concatStackTraces(c1CaughtException.f12359e.getStackTrace(), runtimeException.getStackTrace()));
        throw runtimeException;
    }

    public static boolean joinUninterruptibly(Thread thread, long j2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean z = false;
        long j3 = j2;
        while (j3 > 0) {
            try {
                thread.join(j3);
                break;
            } catch (InterruptedException unused) {
                j3 = j2 - (SystemClock.elapsedRealtime() - elapsedRealtime);
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return !thread.isAlive();
    }

    public static void waitUninterruptibly(final Object obj) {
        executeUninterruptibly(new BlockingOperation() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.ThreadUtils.3
            @Override // com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.ThreadUtils.BlockingOperation
            public void run() throws InterruptedException {
                obj.wait();
            }
        });
    }

    public static boolean awaitUninterruptibly(CountDownLatch countDownLatch, long j2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean z = false;
        long j3 = j2;
        boolean z2 = false;
        do {
            try {
                z = countDownLatch.await(j3, TimeUnit.MILLISECONDS);
                break;
            } catch (InterruptedException unused) {
                z2 = true;
                j3 = j2 - (SystemClock.elapsedRealtime() - elapsedRealtime);
            }
        } while (j3 > 0);
        if (z2) {
            Thread.currentThread().interrupt();
        }
        return z;
    }

    public static void joinUninterruptibly(final Thread thread) {
        executeUninterruptibly(new BlockingOperation() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.ThreadUtils.1
            @Override // com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.ThreadUtils.BlockingOperation
            public void run() throws InterruptedException {
                thread.join();
            }
        });
    }

    public static void invokeAtFrontUninterruptibly(Handler handler, final Runnable runnable) {
        invokeAtFrontUninterruptibly(handler, new Callable<Void>() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.ThreadUtils.5
            @Override // java.util.concurrent.Callable
            public Void call() {
                runnable.run();
                return null;
            }
        });
    }
}
