package com.jingdong.common.utils.rx.internal.concurrent;

import java.util.concurrent.ThreadPoolExecutor;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/jingdong/common/utils/rx/internal/concurrent/IThreadPool;", "", "Ljava/util/concurrent/ThreadPoolExecutor;", "getIOThreadPoolExecutor", "()Ljava/util/concurrent/ThreadPoolExecutor;", "Ljava/lang/Runnable;", "runnable", "", "executeTask", "(Ljava/lang/Runnable;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public interface IThreadPool {
    void executeTask(@Nullable Runnable runnable);

    @Nullable
    ThreadPoolExecutor getIOThreadPoolExecutor();
}
