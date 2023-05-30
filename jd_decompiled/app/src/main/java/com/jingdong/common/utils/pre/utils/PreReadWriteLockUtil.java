package com.jingdong.common.utils.pre.utils;

import com.jingdong.app.mall.e;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u00132\u00020\u0001:\u0003\u0014\u0015\u0013B\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\t\u0010\nR\u001d\u0010\u0010\u001a\u00020\u000b8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0016"}, d2 = {"Lcom/jingdong/common/utils/pre/utils/PreReadWriteLockUtil;", "", "Lcom/jingdong/common/utils/pre/utils/PreReadWriteLockUtil$CallbackWriteLock;", "callbackWrite", "", "doWrite", "(Lcom/jingdong/common/utils/pre/utils/PreReadWriteLockUtil$CallbackWriteLock;)V", "Lcom/jingdong/common/utils/pre/utils/PreReadWriteLockUtil$CallbackReadLock;", "callbackRead", "doRead", "(Lcom/jingdong/common/utils/pre/utils/PreReadWriteLockUtil$CallbackReadLock;)V", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "reentrantReadWriteLock$delegate", "Lkotlin/Lazy;", "getReentrantReadWriteLock", "()Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "reentrantReadWriteLock", "<init>", "()V", "Companion", "CallbackReadLock", "CallbackWriteLock", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PreReadWriteLockUtil {
    public static final long LOCK_TIME_OUT = 2;
    @NotNull
    public static final String TAG = "ReadWriteLockUtil";

    /* renamed from: reentrantReadWriteLock$delegate  reason: from kotlin metadata */
    private final Lazy reentrantReadWriteLock;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/jingdong/common/utils/pre/utils/PreReadWriteLockUtil$CallbackReadLock;", "", "", "doRead", "()V", "", e.a, "onException", "(Ljava/lang/Throwable;)V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public interface CallbackReadLock {
        void doRead();

        void onException(@NotNull Throwable e2);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/jingdong/common/utils/pre/utils/PreReadWriteLockUtil$CallbackWriteLock;", "", "", "doWrite", "()V", "", e.a, "onException", "(Ljava/lang/Throwable;)V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public interface CallbackWriteLock {
        void doWrite();

        void onException(@NotNull Throwable e2);
    }

    public PreReadWriteLockUtil() {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<ReentrantReadWriteLock>() { // from class: com.jingdong.common.utils.pre.utils.PreReadWriteLockUtil$reentrantReadWriteLock$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ReentrantReadWriteLock invoke() {
                return new ReentrantReadWriteLock();
            }
        });
        this.reentrantReadWriteLock = lazy;
    }

    private final ReentrantReadWriteLock getReentrantReadWriteLock() {
        return (ReentrantReadWriteLock) this.reentrantReadWriteLock.getValue();
    }

    public final void doRead(@Nullable CallbackReadLock callbackRead) {
        try {
            if (getReentrantReadWriteLock().readLock().tryLock(2L, TimeUnit.SECONDS)) {
                if (callbackRead != null) {
                    callbackRead.doRead();
                }
                getReentrantReadWriteLock().readLock().unlock();
            }
        } catch (Throwable th) {
            if (OKLog.D) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("ReadWriteLockUtil doRead get error2: %s", Arrays.copyOf(new Object[]{th}, 1));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                OKLog.d(TAG, format);
            }
            if (callbackRead != null) {
                callbackRead.onException(th);
            }
            JdCrashReport.postCaughtException(new IllegalArgumentException("ReadWriteLockUtil doRead error2:", th));
        }
    }

    public final void doWrite(@Nullable CallbackWriteLock callbackWrite) {
        try {
            if (getReentrantReadWriteLock().writeLock().tryLock(2L, TimeUnit.SECONDS)) {
                if (callbackWrite != null) {
                    callbackWrite.doWrite();
                }
                getReentrantReadWriteLock().writeLock().unlock();
            }
        } catch (Throwable th) {
            if (OKLog.D) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("ReadWriteLockUtil doWrite get error: %s2", Arrays.copyOf(new Object[]{th}, 1));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                OKLog.d(TAG, format);
            }
            if (callbackWrite != null) {
                callbackWrite.onException(th);
            }
            JdCrashReport.postCaughtException(new IllegalArgumentException("ReadWriteLockUtil doWrite error2:", th));
        }
    }
}
