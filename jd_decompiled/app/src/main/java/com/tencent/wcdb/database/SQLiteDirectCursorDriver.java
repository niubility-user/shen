package com.tencent.wcdb.database;

import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.support.CancellationSignal;

/* loaded from: classes9.dex */
public final class SQLiteDirectCursorDriver implements SQLiteCursorDriver {
    private static SQLiteDatabase.CursorFactory DEFAULT_FACTORY = SQLiteCursor.FACTORY;
    private final CancellationSignal mCancellationSignal;
    private final SQLiteDatabase mDatabase;
    private final String mEditTable;
    private SQLiteProgram mQuery;
    private final String mSql;

    public SQLiteDirectCursorDriver(SQLiteDatabase sQLiteDatabase, String str, String str2, CancellationSignal cancellationSignal) {
        this.mDatabase = sQLiteDatabase;
        this.mEditTable = str2;
        this.mSql = str;
        this.mCancellationSignal = cancellationSignal;
    }

    @Override // com.tencent.wcdb.database.SQLiteCursorDriver
    public void cursorClosed() {
    }

    @Override // com.tencent.wcdb.database.SQLiteCursorDriver
    public void cursorDeactivated() {
    }

    @Override // com.tencent.wcdb.database.SQLiteCursorDriver
    public void cursorRequeried(Cursor cursor) {
    }

    @Override // com.tencent.wcdb.database.SQLiteCursorDriver
    public Cursor query(SQLiteDatabase.CursorFactory cursorFactory, Object[] objArr) {
        if (cursorFactory == null) {
            cursorFactory = DEFAULT_FACTORY;
        }
        SQLiteProgram sQLiteProgram = null;
        try {
            sQLiteProgram = cursorFactory.newQuery(this.mDatabase, this.mSql, objArr, this.mCancellationSignal);
            Cursor newCursor = cursorFactory.newCursor(this.mDatabase, this, this.mEditTable, sQLiteProgram);
            this.mQuery = sQLiteProgram;
            return newCursor;
        } catch (RuntimeException e2) {
            if (sQLiteProgram != null) {
                sQLiteProgram.close();
            }
            throw e2;
        }
    }

    @Override // com.tencent.wcdb.database.SQLiteCursorDriver
    public void setBindArguments(String[] strArr) {
        this.mQuery.bindAllArgsAsStrings(strArr);
    }

    public String toString() {
        return "SQLiteDirectCursorDriver: " + this.mSql;
    }
}
