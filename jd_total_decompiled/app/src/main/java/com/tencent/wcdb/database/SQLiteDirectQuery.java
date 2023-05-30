package com.tencent.wcdb.database;

import com.tencent.wcdb.database.SQLiteConnection;
import com.tencent.wcdb.support.CancellationSignal;
import com.tencent.wcdb.support.Log;

/* loaded from: classes9.dex */
public class SQLiteDirectQuery extends SQLiteProgram {
    private static final int[] SQLITE_TYPE_MAPPING = {3, 1, 2, 3, 4, 0};
    private static final String TAG = "WCDB.SQLiteDirectQuery";
    private final CancellationSignal mCancellationSignal;

    public SQLiteDirectQuery(SQLiteDatabase sQLiteDatabase, String str, Object[] objArr, CancellationSignal cancellationSignal) {
        super(sQLiteDatabase, str, objArr, cancellationSignal);
        this.mCancellationSignal = cancellationSignal;
    }

    private static native byte[] nativeGetBlob(long j2, int i2);

    private static native double nativeGetDouble(long j2, int i2);

    private static native long nativeGetLong(long j2, int i2);

    private static native String nativeGetString(long j2, int i2);

    private static native int nativeGetType(long j2, int i2);

    private static native int nativeStep(long j2, int i2);

    public byte[] getBlob(int i2) {
        return nativeGetBlob(this.mPreparedStatement.getPtr(), i2);
    }

    public double getDouble(int i2) {
        return nativeGetDouble(this.mPreparedStatement.getPtr(), i2);
    }

    public long getLong(int i2) {
        return nativeGetLong(this.mPreparedStatement.getPtr(), i2);
    }

    public String getString(int i2) {
        return nativeGetString(this.mPreparedStatement.getPtr(), i2);
    }

    public int getType(int i2) {
        return SQLITE_TYPE_MAPPING[nativeGetType(this.mPreparedStatement.getPtr(), i2)];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.wcdb.database.SQLiteProgram, com.tencent.wcdb.database.SQLiteClosable
    public void onAllReferencesReleased() {
        synchronized (this) {
            SQLiteConnection.PreparedStatement preparedStatement = this.mPreparedStatement;
            if (preparedStatement != null) {
                preparedStatement.detachCancellationSignal(this.mCancellationSignal);
                this.mPreparedStatement.endOperation(null);
            }
        }
        super.onAllReferencesReleased();
    }

    public synchronized void reset(boolean z) {
        SQLiteConnection.PreparedStatement preparedStatement = this.mPreparedStatement;
        if (preparedStatement != null) {
            preparedStatement.reset(false);
            if (z) {
                this.mPreparedStatement.detachCancellationSignal(this.mCancellationSignal);
                this.mPreparedStatement.endOperation(null);
                releasePreparedStatement();
            }
        }
    }

    public int step(int i2) {
        try {
            if (acquirePreparedStatement()) {
                this.mPreparedStatement.beginOperation("directQuery", getBindArgs());
                this.mPreparedStatement.attachCancellationSignal(this.mCancellationSignal);
            }
            return nativeStep(this.mPreparedStatement.getPtr(), i2);
        } catch (RuntimeException e2) {
            if (e2 instanceof SQLiteException) {
                Log.e(TAG, "Got exception on stepping: " + e2.getMessage() + ", SQL: " + getSql());
                checkCorruption((SQLiteException) e2);
            }
            SQLiteConnection.PreparedStatement preparedStatement = this.mPreparedStatement;
            if (preparedStatement != null) {
                preparedStatement.detachCancellationSignal(this.mCancellationSignal);
                this.mPreparedStatement.failOperation(e2);
            }
            releasePreparedStatement();
            throw e2;
        }
    }
}
