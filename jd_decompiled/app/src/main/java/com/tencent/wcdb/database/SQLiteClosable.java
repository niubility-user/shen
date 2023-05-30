package com.tencent.wcdb.database;

import java.io.Closeable;

/* loaded from: classes9.dex */
public abstract class SQLiteClosable implements Closeable {
    private int mReferenceCount = 1;

    public void acquireReference() {
        synchronized (this) {
            int i2 = this.mReferenceCount;
            if (i2 > 0) {
                this.mReferenceCount = i2 + 1;
            } else {
                throw new IllegalStateException("attempt to re-open an already-closed object: " + this);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        releaseReference();
    }

    protected abstract void onAllReferencesReleased();

    @Deprecated
    protected void onAllReferencesReleasedFromContainer() {
        onAllReferencesReleased();
    }

    public void releaseReference() {
        boolean z;
        synchronized (this) {
            z = true;
            int i2 = this.mReferenceCount - 1;
            this.mReferenceCount = i2;
            if (i2 != 0) {
                z = false;
            }
        }
        if (z) {
            onAllReferencesReleased();
        }
    }

    @Deprecated
    public void releaseReferenceFromContainer() {
        boolean z;
        synchronized (this) {
            z = true;
            int i2 = this.mReferenceCount - 1;
            this.mReferenceCount = i2;
            if (i2 != 0) {
                z = false;
            }
        }
        if (z) {
            onAllReferencesReleased();
        }
    }
}
