package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.util.concurrent.Callable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
/* loaded from: classes12.dex */
public final class Callables {
    private Callables() {
    }

    @Beta
    @GwtIncompatible
    public static <T> AsyncCallable<T> asAsyncCallable(final Callable<T> callable, final ListeningExecutorService listeningExecutorService) {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(listeningExecutorService);
        return new AsyncCallable<T>() { // from class: com.google.common.util.concurrent.Callables.2
            @Override // com.google.common.util.concurrent.AsyncCallable
            public ListenableFuture<T> call() throws Exception {
                return ListeningExecutorService.this.submit((Callable) callable);
            }
        };
    }

    public static <T> Callable<T> returning(@NullableDecl final T t) {
        return new Callable<T>() { // from class: com.google.common.util.concurrent.Callables.1
            @Override // java.util.concurrent.Callable
            public T call() {
                return (T) t;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @GwtIncompatible
    public static <T> Callable<T> threadRenaming(final Callable<T> callable, final Supplier<String> supplier) {
        Preconditions.checkNotNull(supplier);
        Preconditions.checkNotNull(callable);
        return new Callable<T>() { // from class: com.google.common.util.concurrent.Callables.3
            @Override // java.util.concurrent.Callable
            public T call() throws Exception {
                Thread currentThread = Thread.currentThread();
                String name = currentThread.getName();
                boolean trySetName = Callables.trySetName((String) Supplier.this.get(), currentThread);
                try {
                    return (T) callable.call();
                } finally {
                    if (trySetName) {
                        Callables.trySetName(name, currentThread);
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GwtIncompatible
    public static boolean trySetName(String str, Thread thread) {
        try {
            thread.setName(str);
            return true;
        } catch (SecurityException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @GwtIncompatible
    public static Runnable threadRenaming(final Runnable runnable, final Supplier<String> supplier) {
        Preconditions.checkNotNull(supplier);
        Preconditions.checkNotNull(runnable);
        return new Runnable() { // from class: com.google.common.util.concurrent.Callables.4
            @Override // java.lang.Runnable
            public void run() {
                Thread currentThread = Thread.currentThread();
                String name = currentThread.getName();
                boolean trySetName = Callables.trySetName((String) Supplier.this.get(), currentThread);
                try {
                    runnable.run();
                } finally {
                    if (trySetName) {
                        Callables.trySetName(name, currentThread);
                    }
                }
            }
        };
    }
}
