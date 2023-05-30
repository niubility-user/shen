package com.tencent.wcdb.repair;

import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteException;
import com.tencent.wcdb.support.CancellationSignal;

/* loaded from: classes9.dex */
public class RecoverKit implements CancellationSignal.OnCancelListener {
    public static final int RESULT_CANCELED = 1;
    public static final int RESULT_FAILED = -1;
    public static final int RESULT_OK = 0;
    static final String TAG = "WCDB.DBBackup";
    private SQLiteDatabase mDB;
    private int mFailedCount;
    private String mLastError = null;
    private long mNativePtr;
    private int mSuccessCount;

    public RecoverKit(SQLiteDatabase sQLiteDatabase, String str, byte[] bArr) throws SQLiteException {
        this.mDB = sQLiteDatabase;
        long nativeInit = nativeInit(str, bArr);
        this.mNativePtr = nativeInit;
        if (nativeInit == 0) {
            throw new SQLiteException("Failed initialize recover context.");
        }
    }

    private static native void nativeCancel(long j2);

    private static native int nativeFailureCount(long j2);

    private static native void nativeFinish(long j2);

    private static native long nativeInit(String str, byte[] bArr);

    private static native String nativeLastError(long j2);

    private static native int nativeRun(long j2, long j3, boolean z);

    private static native int nativeSuccessCount(long j2);

    public int failureCount() {
        return this.mFailedCount;
    }

    protected void finalize() throws Throwable {
        release();
        super.finalize();
    }

    public String lastError() {
        return this.mLastError;
    }

    @Override // com.tencent.wcdb.support.CancellationSignal.OnCancelListener
    public void onCancel() {
        long j2 = this.mNativePtr;
        if (j2 != 0) {
            nativeCancel(j2);
        }
    }

    public void release() {
        long j2 = this.mNativePtr;
        if (j2 != 0) {
            nativeFinish(j2);
            this.mNativePtr = 0L;
        }
    }

    public int run(boolean z) {
        if (this.mNativePtr != 0) {
            long acquireNativeConnectionHandle = this.mDB.acquireNativeConnectionHandle("recover", false, false);
            int nativeRun = nativeRun(this.mNativePtr, acquireNativeConnectionHandle, z);
            this.mDB.releaseNativeConnection(acquireNativeConnectionHandle, null);
            this.mSuccessCount = nativeSuccessCount(this.mNativePtr);
            this.mFailedCount = nativeFailureCount(this.mNativePtr);
            this.mLastError = nativeLastError(this.mNativePtr);
            nativeFinish(this.mNativePtr);
            this.mNativePtr = 0L;
            return nativeRun;
        }
        throw new IllegalStateException("RecoverKit not initialized.");
    }

    public int successCount() {
        return this.mSuccessCount;
    }

    public int run(boolean z, CancellationSignal cancellationSignal) {
        if (cancellationSignal.isCanceled()) {
            return 1;
        }
        cancellationSignal.setOnCancelListener(this);
        int run = run(z);
        cancellationSignal.setOnCancelListener(null);
        return run;
    }
}
