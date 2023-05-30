package com.jingdong.common.utils.rx.internal.concurrent;

import com.jingdong.app.mall.e;
import com.jingdong.common.utils.rx.internal.IBaseWork;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0016\u0012\u0006\u0010\u001a\u001a\u00020\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0019\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\t\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ+\u0010\t\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016\u00a2\u0006\u0004\b\t\u0010\u000fJ\u0015\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0015R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018\u00a8\u0006\u001d"}, d2 = {"Lcom/jingdong/common/utils/rx/internal/concurrent/IOPriorityQueue;", "Ljava/util/concurrent/PriorityBlockingQueue;", "Ljava/lang/Runnable;", "", "o", "", "isNotAllowOfferQueue", "(Ljava/lang/Object;)Z", e.a, "offer", "(Ljava/lang/Runnable;)Z", "", "timeout", "Ljava/util/concurrent/TimeUnit;", "unit", "(Ljava/lang/Runnable;JLjava/util/concurrent/TimeUnit;)Z", "Ljava/util/concurrent/ThreadPoolExecutor;", "threadPoolExecutor", "", "setThreadPoolExecutor", "(Ljava/util/concurrent/ThreadPoolExecutor;)V", "Ljava/util/concurrent/ThreadPoolExecutor;", "", "queueSize", "I", "initialCapacity", "maxSize", "<init>", "(II)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class IOPriorityQueue extends PriorityBlockingQueue<Runnable> {
    private int queueSize;
    private ThreadPoolExecutor threadPoolExecutor;

    public IOPriorityQueue(int i2, int i3) {
        super(i2);
        this.queueSize = i3;
    }

    private final boolean isNotAllowOfferQueue(Object o) {
        if ((o instanceof IBaseWork) && this.threadPoolExecutor != null) {
            IBaseWork iBaseWork = (IBaseWork) o;
            if (iBaseWork.isNeedCreateNewThread()) {
                return false;
            }
            ThreadPoolExecutor threadPoolExecutor = this.threadPoolExecutor;
            int poolSize = threadPoolExecutor != null ? threadPoolExecutor.getPoolSize() : 0;
            ThreadPoolExecutor threadPoolExecutor2 = this.threadPoolExecutor;
            if (poolSize < (threadPoolExecutor2 != null ? threadPoolExecutor2.getMaximumPoolSize() : 0)) {
                iBaseWork.setNeedCreateNewThread(true);
                return true;
            } else if (iBaseWork.getPriority() >= -10) {
                return false;
            } else {
                int size = size();
                int i2 = this.queueSize;
                if (1 <= i2 && size > i2) {
                    if (OKLog.D) {
                        OKLog.d("IOPriorityQueue", "remove old task: " + iBaseWork);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.concurrent.PriorityBlockingQueue, java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public final /* bridge */ boolean contains(Object obj) {
        if (obj != null ? obj instanceof Runnable : true) {
            return contains((Runnable) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    @Override // java.util.concurrent.PriorityBlockingQueue, java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public final /* bridge */ boolean remove(Object obj) {
        if (obj != null ? obj instanceof Runnable : true) {
            return remove((Runnable) obj);
        }
        return false;
    }

    public final void setThreadPoolExecutor(@NotNull ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override // java.util.concurrent.PriorityBlockingQueue, java.util.AbstractCollection, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    public /* bridge */ boolean contains(Runnable runnable) {
        return super.contains((Object) runnable);
    }

    public /* bridge */ boolean remove(Runnable runnable) {
        return super.remove((Object) runnable);
    }

    @Override // java.util.concurrent.PriorityBlockingQueue, java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean offer(@Nullable Runnable e2) {
        if (isNotAllowOfferQueue(e2)) {
            return false;
        }
        return super.offer((IOPriorityQueue) e2);
    }

    @Override // java.util.concurrent.PriorityBlockingQueue, java.util.concurrent.BlockingQueue
    public boolean offer(@Nullable Runnable e2, long timeout, @Nullable TimeUnit unit) {
        if (isNotAllowOfferQueue(e2)) {
            return false;
        }
        return super.offer((IOPriorityQueue) e2, timeout, unit);
    }
}
