package kotlin.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a(\u0010\u0004\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0087\b\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a(\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0087\b\u00a2\u0006\u0004\b\u0007\u0010\b\u001a(\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0087\b\u00a2\u0006\u0004\b\t\u0010\b\u00a8\u0006\n"}, d2 = {"T", "Ljava/util/concurrent/locks/Lock;", "Lkotlin/Function0;", "action", "withLock", "(Ljava/util/concurrent/locks/Lock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "read", "(Ljava/util/concurrent/locks/ReentrantReadWriteLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "write", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
@JvmName(name = "LocksKt")
/* loaded from: classes11.dex */
public final class LocksKt {
    @InlineOnly
    private static final <T> T read(@NotNull ReentrantReadWriteLock reentrantReadWriteLock, Function0<? extends T> function0) {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        readLock.lock();
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            readLock.unlock();
            InlineMarker.finallyEnd(1);
        }
    }

    @InlineOnly
    private static final <T> T withLock(@NotNull Lock lock, Function0<? extends T> function0) {
        lock.lock();
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            lock.unlock();
            InlineMarker.finallyEnd(1);
        }
    }

    @InlineOnly
    private static final <T> T write(@NotNull ReentrantReadWriteLock reentrantReadWriteLock, Function0<? extends T> function0) {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i2 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i3 = 0; i3 < readHoldCount; i3++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            while (i2 < readHoldCount) {
                readLock.lock();
                i2++;
            }
            writeLock.unlock();
            InlineMarker.finallyEnd(1);
        }
    }
}
