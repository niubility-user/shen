package com.tencent.wcdb.repair;

import android.database.Cursor;
import com.tencent.wcdb.AbstractCursor;
import com.tencent.wcdb.database.SQLiteCipherSpec;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteException;
import com.tencent.wcdb.support.CancellationSignal;

/* loaded from: classes9.dex */
public class RepairKit implements CancellationSignal.OnCancelListener {
    public static final int FLAG_ALL_TABLES = 2;
    public static final int FLAG_NO_CREATE_TABLES = 1;
    private static final int INTEGRITY_DATA = 2;
    private static final int INTEGRITY_HEADER = 1;
    private static final int INTEGRITY_KDF_SALT = 4;
    public static final int RESULT_CANCELED = 1;
    public static final int RESULT_FAILED = -1;
    public static final int RESULT_IGNORE = 2;
    public static final int RESULT_OK = 0;
    private Callback mCallback;
    private RepairCursor mCurrentCursor;
    private int mIntegrityFlags;
    private MasterInfo mMasterInfo;
    private long mNativePtr;

    /* loaded from: classes9.dex */
    public interface Callback {
        int onProgress(String str, int i2, Cursor cursor);
    }

    /* loaded from: classes9.dex */
    public static class MasterInfo {
        private byte[] mKDFSalt;
        private long mMasterPtr;

        private MasterInfo(long j2, byte[] bArr) {
            this.mMasterPtr = j2;
            this.mKDFSalt = bArr;
        }

        public static MasterInfo load(String str, byte[] bArr, String[] strArr) {
            if (str == null) {
                return make(strArr);
            }
            byte[] bArr2 = new byte[16];
            long nativeLoadMaster = RepairKit.nativeLoadMaster(str, bArr, strArr, bArr2);
            if (nativeLoadMaster != 0) {
                return new MasterInfo(nativeLoadMaster, bArr2);
            }
            throw new SQLiteException("Cannot create MasterInfo.");
        }

        public static MasterInfo make(String[] strArr) {
            long nativeMakeMaster = RepairKit.nativeMakeMaster(strArr);
            if (nativeMakeMaster != 0) {
                return new MasterInfo(nativeMakeMaster, null);
            }
            throw new SQLiteException("Cannot create MasterInfo.");
        }

        public static boolean save(SQLiteDatabase sQLiteDatabase, String str, byte[] bArr) {
            long acquireNativeConnectionHandle = sQLiteDatabase.acquireNativeConnectionHandle("backupMaster", true, false);
            boolean nativeSaveMaster = RepairKit.nativeSaveMaster(acquireNativeConnectionHandle, str, bArr);
            sQLiteDatabase.releaseNativeConnection(acquireNativeConnectionHandle, null);
            return nativeSaveMaster;
        }

        protected void finalize() throws Throwable {
            release();
            super.finalize();
        }

        public void release() {
            long j2 = this.mMasterPtr;
            if (j2 == 0) {
                return;
            }
            RepairKit.nativeFreeMaster(j2);
            this.mMasterPtr = 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class RepairCursor extends AbstractCursor {
        long mPtr;

        private RepairCursor() {
        }

        private static native byte[] nativeGetBlob(long j2, int i2);

        private static native int nativeGetColumnCount(long j2);

        private static native double nativeGetDouble(long j2, int i2);

        private static native long nativeGetLong(long j2, int i2);

        private static native String nativeGetString(long j2, int i2);

        private static native int nativeGetType(long j2, int i2);

        @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
        public byte[] getBlob(int i2) {
            return nativeGetBlob(this.mPtr, i2);
        }

        @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
        public int getColumnCount() {
            return nativeGetColumnCount(this.mPtr);
        }

        @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
        public String[] getColumnNames() {
            throw new UnsupportedOperationException();
        }

        @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
        public int getCount() {
            throw new UnsupportedOperationException();
        }

        @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
        public double getDouble(int i2) {
            return nativeGetDouble(this.mPtr, i2);
        }

        @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
        public float getFloat(int i2) {
            return (float) getDouble(i2);
        }

        @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
        public int getInt(int i2) {
            return (int) getLong(i2);
        }

        @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
        public long getLong(int i2) {
            return nativeGetLong(this.mPtr, i2);
        }

        @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
        public short getShort(int i2) {
            return (short) getLong(i2);
        }

        @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
        public String getString(int i2) {
            return nativeGetString(this.mPtr, i2);
        }

        @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
        public int getType(int i2) {
            return nativeGetType(this.mPtr, i2);
        }

        @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
        public boolean isNull(int i2) {
            return getType(i2) == 0;
        }
    }

    public RepairKit(String str, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec, MasterInfo masterInfo) {
        if (str != null) {
            long nativeInit = nativeInit(str, bArr, sQLiteCipherSpec, masterInfo == null ? null : masterInfo.mKDFSalt);
            this.mNativePtr = nativeInit;
            if (nativeInit != 0) {
                this.mIntegrityFlags = nativeIntegrityFlags(nativeInit);
                this.mMasterInfo = masterInfo;
                return;
            }
            throw new SQLiteException("Failed initialize RepairKit.");
        }
        throw new IllegalArgumentException();
    }

    public static String lastError() {
        return nativeLastError();
    }

    private static native void nativeCancel(long j2);

    private static native void nativeFini(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeFreeMaster(long j2);

    private static native long nativeInit(String str, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec, byte[] bArr2);

    private static native int nativeIntegrityFlags(long j2);

    private static native String nativeLastError();

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeLoadMaster(String str, byte[] bArr, String[] strArr, byte[] bArr2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeMakeMaster(String[] strArr);

    private native int nativeOutput(long j2, long j3, long j4, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native boolean nativeSaveMaster(long j2, String str, byte[] bArr);

    private int onProgress(String str, int i2, long j2) {
        if (this.mCallback == null) {
            return 0;
        }
        if (this.mCurrentCursor == null) {
            this.mCurrentCursor = new RepairCursor();
        }
        RepairCursor repairCursor = this.mCurrentCursor;
        repairCursor.mPtr = j2;
        return this.mCallback.onProgress(str, i2, repairCursor);
    }

    protected void finalize() throws Throwable {
        release();
        super.finalize();
    }

    public Callback getCallback() {
        return this.mCallback;
    }

    public boolean isDataCorrupted() {
        return (this.mIntegrityFlags & 2) == 0;
    }

    public boolean isHeaderCorrupted() {
        return (this.mIntegrityFlags & 1) == 0;
    }

    public boolean isSaltCorrupted() {
        return (this.mIntegrityFlags & 4) == 0;
    }

    @Override // com.tencent.wcdb.support.CancellationSignal.OnCancelListener
    public void onCancel() {
        long j2 = this.mNativePtr;
        if (j2 == 0) {
            return;
        }
        nativeCancel(j2);
    }

    public int output(SQLiteDatabase sQLiteDatabase, int i2) {
        if (this.mNativePtr != 0) {
            MasterInfo masterInfo = this.mMasterInfo;
            long j2 = masterInfo != null ? masterInfo.mMasterPtr : 0L;
            long acquireNativeConnectionHandle = sQLiteDatabase.acquireNativeConnectionHandle("repair", false, false);
            int nativeOutput = nativeOutput(this.mNativePtr, acquireNativeConnectionHandle, j2, i2);
            sQLiteDatabase.releaseNativeConnection(acquireNativeConnectionHandle, null);
            this.mCurrentCursor = null;
            this.mIntegrityFlags = nativeIntegrityFlags(this.mNativePtr);
            return nativeOutput;
        }
        throw new IllegalArgumentException();
    }

    public void release() {
        MasterInfo masterInfo = this.mMasterInfo;
        if (masterInfo != null) {
            masterInfo.release();
            this.mMasterInfo = null;
        }
        long j2 = this.mNativePtr;
        if (j2 != 0) {
            nativeFini(j2);
            this.mNativePtr = 0L;
        }
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public int output(SQLiteDatabase sQLiteDatabase, int i2, CancellationSignal cancellationSignal) {
        if (cancellationSignal.isCanceled()) {
            return 1;
        }
        cancellationSignal.setOnCancelListener(this);
        int output = output(sQLiteDatabase, i2);
        cancellationSignal.setOnCancelListener(null);
        return output;
    }
}
