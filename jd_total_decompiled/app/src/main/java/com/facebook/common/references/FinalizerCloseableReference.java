package com.facebook.common.references;

import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class FinalizerCloseableReference<T> extends CloseableReference<T> {
    private static final String TAG = "FinalizerCloseableReference";

    /* JADX INFO: Access modifiers changed from: package-private */
    public FinalizerCloseableReference(T t, ResourceReleaser<T> resourceReleaser, CloseableReference.LeakHandler leakHandler, @Nullable Throwable th) {
        super(t, resourceReleaser, leakHandler, th);
    }

    @Override // com.facebook.common.references.CloseableReference
    /* renamed from: clone */
    public CloseableReference<T> mo9clone() {
        return this;
    }

    @Override // com.facebook.common.references.CloseableReference, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.common.references.CloseableReference
    public void finalize() {
        try {
            synchronized (this) {
                if (this.mIsClosed) {
                    return;
                }
                FLog.w(TAG, "Finalized without closing: %x %x (type = %s)", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(this.mSharedReference)), this.mSharedReference.get().getClass().getName());
                this.mSharedReference.deleteReference();
            }
        } finally {
            super.finalize();
        }
    }
}
