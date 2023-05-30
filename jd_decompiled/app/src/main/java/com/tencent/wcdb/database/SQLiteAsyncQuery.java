package com.tencent.wcdb.database;

import com.tencent.wcdb.database.SQLiteConnection;
import com.tencent.wcdb.support.CancellationSignal;
import com.tencent.wcdb.support.Log;

/* loaded from: classes9.dex */
public class SQLiteAsyncQuery extends SQLiteProgram {
    private static final String TAG = "WCDB.SQLiteAsyncQuery";
    private final int mResultColumns;

    public SQLiteAsyncQuery(SQLiteDatabase sQLiteDatabase, String str, Object[] objArr, CancellationSignal cancellationSignal) {
        super(sQLiteDatabase, str, objArr, cancellationSignal);
        this.mResultColumns = getColumnNames().length;
    }

    private static native int nativeCount(long j2);

    private static native int nativeFillRows(long j2, long j3, int i2, int i3);

    void acquire() {
        if (this.mPreparedStatement == null) {
            acquirePreparedStatement();
            this.mPreparedStatement.bindArguments(getBindArgs());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int fillRows(ChunkedCursorWindow chunkedCursorWindow, int i2, int i3) {
        acquire();
        int numColumns = chunkedCursorWindow.getNumColumns();
        int i4 = this.mResultColumns;
        if (numColumns != i4) {
            chunkedCursorWindow.setNumColumns(i4);
        }
        try {
            return nativeFillRows(this.mPreparedStatement.getPtr(), chunkedCursorWindow.mWindowPtr, i2, i3);
        } catch (SQLiteException e2) {
            Log.e(TAG, "Got exception on fillRows: " + e2.getMessage() + ", SQL: " + getSql());
            checkCorruption(e2);
            throw e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCount() {
        acquire();
        try {
            return nativeCount(this.mPreparedStatement.getPtr());
        } catch (SQLiteException e2) {
            Log.e(TAG, "Got exception on getCount: " + e2.getMessage() + ", SQL: " + getSql());
            checkCorruption(e2);
            throw e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void release() {
        releasePreparedStatement();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        SQLiteConnection.PreparedStatement preparedStatement = this.mPreparedStatement;
        if (preparedStatement != null) {
            preparedStatement.reset(false);
        }
    }
}
