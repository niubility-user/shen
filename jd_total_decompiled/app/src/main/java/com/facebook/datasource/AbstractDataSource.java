package com.facebook.datasource;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* loaded from: classes.dex */
public abstract class AbstractDataSource<T> implements DataSource<T> {
    @Nullable
    private static volatile DataSourceInstrumenter sDataSourceInstrumenter;
    @GuardedBy("this")
    @Nullable
    private T mResult = null;
    @GuardedBy("this")
    private Throwable mFailureThrowable = null;
    @GuardedBy("this")
    private float mProgress = 0.0f;
    @GuardedBy("this")
    private boolean mIsClosed = false;
    @GuardedBy("this")
    private DataSourceStatus mDataSourceStatus = DataSourceStatus.IN_PROGRESS;
    private final ConcurrentLinkedQueue<Pair<DataSubscriber<T>, Executor>> mSubscribers = new ConcurrentLinkedQueue<>();

    /* loaded from: classes.dex */
    public interface DataSourceInstrumenter {
        Runnable decorateRunnable(Runnable runnable, String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum DataSourceStatus {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }

    @Nullable
    public static DataSourceInstrumenter getDataSourceInstrumenter() {
        return sDataSourceInstrumenter;
    }

    private void notifyDataSubscribers() {
        boolean hasFailed = hasFailed();
        boolean wasCancelled = wasCancelled();
        Iterator<Pair<DataSubscriber<T>, Executor>> it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair<DataSubscriber<T>, Executor> next = it.next();
            notifyDataSubscriber((DataSubscriber) next.first, (Executor) next.second, hasFailed, wasCancelled);
        }
    }

    public static void provideInstrumenter(@Nullable DataSourceInstrumenter dataSourceInstrumenter) {
        sDataSourceInstrumenter = dataSourceInstrumenter;
    }

    private synchronized boolean setFailureInternal(Throwable th) {
        boolean z;
        if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
            this.mDataSourceStatus = DataSourceStatus.FAILURE;
            this.mFailureThrowable = th;
            z = true;
        }
        z = false;
        return z;
    }

    private synchronized boolean setProgressInternal(float f2) {
        if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
            if (f2 < this.mProgress) {
                return false;
            }
            this.mProgress = f2;
            return true;
        }
        return false;
    }

    private boolean setResultInternal(@Nullable T t, boolean z) {
        T t2;
        T t3 = null;
        try {
            synchronized (this) {
                try {
                    try {
                        if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
                            if (z) {
                                this.mDataSourceStatus = DataSourceStatus.SUCCESS;
                                this.mProgress = 1.0f;
                            }
                            T t4 = this.mResult;
                            if (t4 != t) {
                                try {
                                    this.mResult = t;
                                    t2 = t4;
                                } catch (Throwable th) {
                                    th = th;
                                    t3 = t4;
                                    throw th;
                                }
                            } else {
                                t2 = null;
                            }
                            return true;
                        }
                        if (t != null) {
                            closeResult(t);
                        }
                        return false;
                    } catch (Throwable th2) {
                        t3 = t;
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        } finally {
            if (t3 != null) {
                closeResult(t3);
            }
        }
    }

    private synchronized boolean wasCancelled() {
        boolean z;
        if (isClosed()) {
            z = isFinished() ? false : true;
        }
        return z;
    }

    @Override // com.facebook.datasource.DataSource
    public boolean close() {
        synchronized (this) {
            if (this.mIsClosed) {
                return false;
            }
            this.mIsClosed = true;
            T t = this.mResult;
            this.mResult = null;
            if (t != null) {
                closeResult(t);
            }
            if (!isFinished()) {
                notifyDataSubscribers();
            }
            synchronized (this) {
                this.mSubscribers.clear();
            }
            return true;
        }
    }

    protected void closeResult(@Nullable T t) {
    }

    @Override // com.facebook.datasource.DataSource
    @Nullable
    public synchronized Throwable getFailureCause() {
        return this.mFailureThrowable;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized float getProgress() {
        return this.mProgress;
    }

    @Override // com.facebook.datasource.DataSource
    @Nullable
    public synchronized T getResult() {
        return this.mResult;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean hasFailed() {
        return this.mDataSourceStatus == DataSourceStatus.FAILURE;
    }

    @Override // com.facebook.datasource.DataSource
    public boolean hasMultipleResults() {
        return false;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean hasResult() {
        return this.mResult != null;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean isClosed() {
        return this.mIsClosed;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean isFinished() {
        return this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS;
    }

    protected void notifyDataSubscriber(final DataSubscriber<T> dataSubscriber, Executor executor, final boolean z, final boolean z2) {
        Runnable runnable = new Runnable() { // from class: com.facebook.datasource.AbstractDataSource.1
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    dataSubscriber.onFailure(AbstractDataSource.this);
                } else if (z2) {
                    dataSubscriber.onCancellation(AbstractDataSource.this);
                } else {
                    dataSubscriber.onNewResult(AbstractDataSource.this);
                }
            }
        };
        DataSourceInstrumenter dataSourceInstrumenter = getDataSourceInstrumenter();
        if (dataSourceInstrumenter != null) {
            runnable = dataSourceInstrumenter.decorateRunnable(runnable, "AbstractDataSource_notifyDataSubscriber");
        }
        executor.execute(runnable);
    }

    protected void notifyProgressUpdate() {
        Iterator<Pair<DataSubscriber<T>, Executor>> it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair<DataSubscriber<T>, Executor> next = it.next();
            final DataSubscriber dataSubscriber = (DataSubscriber) next.first;
            ((Executor) next.second).execute(new Runnable() { // from class: com.facebook.datasource.AbstractDataSource.2
                @Override // java.lang.Runnable
                public void run() {
                    dataSubscriber.onProgressUpdate(AbstractDataSource.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean setFailure(Throwable th) {
        boolean failureInternal = setFailureInternal(th);
        if (failureInternal) {
            notifyDataSubscribers();
        }
        return failureInternal;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean setProgress(float f2) {
        boolean progressInternal = setProgressInternal(f2);
        if (progressInternal) {
            notifyProgressUpdate();
        }
        return progressInternal;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean setResult(@Nullable T t, boolean z) {
        boolean resultInternal = setResultInternal(t, z);
        if (resultInternal) {
            notifyDataSubscribers();
        }
        return resultInternal;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    @Override // com.facebook.datasource.DataSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void subscribe(DataSubscriber<T> dataSubscriber, Executor executor) {
        boolean z;
        Preconditions.checkNotNull(dataSubscriber);
        Preconditions.checkNotNull(executor);
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            if (this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
                this.mSubscribers.add(Pair.create(dataSubscriber, executor));
            }
            if (!hasResult() && !isFinished() && !wasCancelled()) {
                z = false;
                if (z) {
                    return;
                }
                notifyDataSubscriber(dataSubscriber, executor, hasFailed(), wasCancelled());
                return;
            }
            z = true;
            if (z) {
            }
        }
    }
}
