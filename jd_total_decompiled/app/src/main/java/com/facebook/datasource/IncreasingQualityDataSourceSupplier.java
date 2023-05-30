package com.facebook.datasource;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* loaded from: classes.dex */
public class IncreasingQualityDataSourceSupplier<T> implements Supplier<DataSource<T>> {
    private final boolean mDataSourceLazy;
    private final List<Supplier<DataSource<T>>> mDataSourceSuppliers;

    /* JADX INFO: Access modifiers changed from: private */
    @ThreadSafe
    /* loaded from: classes.dex */
    public class IncreasingQualityDataSource extends AbstractDataSource<T> {
        @GuardedBy("IncreasingQualityDataSource.this")
        @Nullable
        private ArrayList<DataSource<T>> mDataSources;
        @Nullable
        private Throwable mDelayedError;
        private AtomicInteger mFinishedDataSources;
        @GuardedBy("IncreasingQualityDataSource.this")
        private int mIndexOfDataSourceWithResult;
        private int mNumberOfDataSources;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public class InternalDataSubscriber implements DataSubscriber<T> {
            private int mIndex;

            public InternalDataSubscriber(int i2) {
                this.mIndex = i2;
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onCancellation(DataSource<T> dataSource) {
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onFailure(DataSource<T> dataSource) {
                IncreasingQualityDataSource.this.onDataSourceFailed(this.mIndex, dataSource);
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onNewResult(DataSource<T> dataSource) {
                if (dataSource.hasResult()) {
                    IncreasingQualityDataSource.this.onDataSourceNewResult(this.mIndex, dataSource);
                } else if (dataSource.isFinished()) {
                    IncreasingQualityDataSource.this.onDataSourceFailed(this.mIndex, dataSource);
                }
            }

            @Override // com.facebook.datasource.DataSubscriber
            public void onProgressUpdate(DataSource<T> dataSource) {
                if (this.mIndex == 0) {
                    IncreasingQualityDataSource.this.setProgress(dataSource.getProgress());
                }
            }
        }

        public IncreasingQualityDataSource() {
            if (IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                return;
            }
            ensureDataSourceInitialized();
        }

        private void closeSafely(DataSource<T> dataSource) {
            if (dataSource != null) {
                dataSource.close();
            }
        }

        private void ensureDataSourceInitialized() {
            if (this.mFinishedDataSources != null) {
                return;
            }
            synchronized (this) {
                if (this.mFinishedDataSources == null) {
                    this.mFinishedDataSources = new AtomicInteger(0);
                    int size = IncreasingQualityDataSourceSupplier.this.mDataSourceSuppliers.size();
                    this.mNumberOfDataSources = size;
                    this.mIndexOfDataSourceWithResult = size;
                    this.mDataSources = new ArrayList<>(size);
                    for (int i2 = 0; i2 < size; i2++) {
                        DataSource<T> dataSource = (DataSource) ((Supplier) IncreasingQualityDataSourceSupplier.this.mDataSourceSuppliers.get(i2)).get();
                        this.mDataSources.add(dataSource);
                        dataSource.subscribe(new InternalDataSubscriber(i2), CallerThreadExecutor.getInstance());
                        if (dataSource.hasResult()) {
                            break;
                        }
                    }
                }
            }
        }

        @Nullable
        private synchronized DataSource<T> getAndClearDataSource(int i2) {
            DataSource<T> dataSource;
            ArrayList<DataSource<T>> arrayList = this.mDataSources;
            dataSource = null;
            if (arrayList != null && i2 < arrayList.size()) {
                dataSource = this.mDataSources.set(i2, null);
            }
            return dataSource;
        }

        @Nullable
        private synchronized DataSource<T> getDataSource(int i2) {
            ArrayList<DataSource<T>> arrayList;
            arrayList = this.mDataSources;
            return (arrayList == null || i2 >= arrayList.size()) ? null : this.mDataSources.get(i2);
        }

        @Nullable
        private synchronized DataSource<T> getDataSourceWithResult() {
            return getDataSource(this.mIndexOfDataSourceWithResult);
        }

        private void maybeSetFailure() {
            Throwable th;
            if (this.mFinishedDataSources.incrementAndGet() != this.mNumberOfDataSources || (th = this.mDelayedError) == null) {
                return;
            }
            setFailure(th);
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0022 A[LOOP:0: B:17:0x0020->B:18:0x0022, LOOP_END] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private void maybeSetIndexOfDataSourceWithResult(int i2, DataSource<T> dataSource, boolean z) {
            synchronized (this) {
                int i3 = this.mIndexOfDataSourceWithResult;
                if (dataSource == getDataSource(i2) && i2 != this.mIndexOfDataSourceWithResult) {
                    if (getDataSourceWithResult() != null && (!z || i2 >= this.mIndexOfDataSourceWithResult)) {
                        i2 = i3;
                        while (i3 > i2) {
                            closeSafely(getAndClearDataSource(i3));
                            i3--;
                        }
                    }
                    this.mIndexOfDataSourceWithResult = i2;
                    while (i3 > i2) {
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDataSourceFailed(int i2, DataSource<T> dataSource) {
            closeSafely(tryGetAndClearDataSource(i2, dataSource));
            if (i2 == 0) {
                this.mDelayedError = dataSource.getFailureCause();
            }
            maybeSetFailure();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDataSourceNewResult(int i2, DataSource<T> dataSource) {
            maybeSetIndexOfDataSourceWithResult(i2, dataSource, dataSource.isFinished());
            if (dataSource == getDataSourceWithResult()) {
                setResult(null, i2 == 0 && dataSource.isFinished());
            }
            maybeSetFailure();
        }

        @Nullable
        private synchronized DataSource<T> tryGetAndClearDataSource(int i2, DataSource<T> dataSource) {
            if (dataSource == getDataSourceWithResult()) {
                return null;
            }
            if (dataSource == getDataSource(i2)) {
                return getAndClearDataSource(i2);
            }
            return dataSource;
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        public boolean close() {
            if (IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                ensureDataSourceInitialized();
            }
            synchronized (this) {
                if (super.close()) {
                    ArrayList<DataSource<T>> arrayList = this.mDataSources;
                    this.mDataSources = null;
                    if (arrayList != null) {
                        for (int i2 = 0; i2 < arrayList.size(); i2++) {
                            closeSafely(arrayList.get(i2));
                        }
                        return true;
                    }
                    return true;
                }
                return false;
            }
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        @Nullable
        public synchronized T getResult() {
            DataSource<T> dataSourceWithResult;
            if (IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                ensureDataSourceInitialized();
            }
            dataSourceWithResult = getDataSourceWithResult();
            return dataSourceWithResult != null ? dataSourceWithResult.getResult() : null;
        }

        @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
        public synchronized boolean hasResult() {
            boolean z;
            if (IncreasingQualityDataSourceSupplier.this.mDataSourceLazy) {
                ensureDataSourceInitialized();
            }
            DataSource<T> dataSourceWithResult = getDataSourceWithResult();
            if (dataSourceWithResult != null) {
                z = dataSourceWithResult.hasResult();
            }
            return z;
        }
    }

    private IncreasingQualityDataSourceSupplier(List<Supplier<DataSource<T>>> list, boolean z) {
        Preconditions.checkArgument(!list.isEmpty(), "List of suppliers is empty!");
        this.mDataSourceSuppliers = list;
        this.mDataSourceLazy = z;
    }

    public static <T> IncreasingQualityDataSourceSupplier<T> create(List<Supplier<DataSource<T>>> list) {
        return create(list, false);
    }

    public static <T> IncreasingQualityDataSourceSupplier<T> create(List<Supplier<DataSource<T>>> list, boolean z) {
        return new IncreasingQualityDataSourceSupplier<>(list, z);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof IncreasingQualityDataSourceSupplier) {
            return Objects.equal(this.mDataSourceSuppliers, ((IncreasingQualityDataSourceSupplier) obj).mDataSourceSuppliers);
        }
        return false;
    }

    @Override // com.facebook.common.internal.Supplier
    public DataSource<T> get() {
        return new IncreasingQualityDataSource();
    }

    public int hashCode() {
        return this.mDataSourceSuppliers.hashCode();
    }

    public String toString() {
        return Objects.toStringHelper(this).add(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID, this.mDataSourceSuppliers).toString();
    }
}
