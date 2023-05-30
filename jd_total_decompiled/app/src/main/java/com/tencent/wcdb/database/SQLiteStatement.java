package com.tencent.wcdb.database;

import com.tencent.wcdb.support.CancellationSignal;

/* loaded from: classes9.dex */
public final class SQLiteStatement extends SQLiteProgram {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteStatement(SQLiteDatabase sQLiteDatabase, String str, Object[] objArr) {
        super(sQLiteDatabase, str, objArr, null);
    }

    public void execute() {
        execute(null);
    }

    public long executeInsert() {
        return executeInsert(null);
    }

    public int executeUpdateDelete() {
        return executeUpdateDelete(null);
    }

    public long simpleQueryForLong() {
        return simpleQueryForLong(null);
    }

    public String simpleQueryForString() {
        return simpleQueryForString(null);
    }

    public String toString() {
        return "SQLiteProgram: " + getSql();
    }

    public void execute(CancellationSignal cancellationSignal) {
        acquireReference();
        try {
            try {
                getSession().execute(getSql(), getBindArgs(), getConnectionFlags(), cancellationSignal);
            } catch (SQLiteException e2) {
                checkCorruption(e2);
                throw e2;
            }
        } finally {
            releaseReference();
        }
    }

    public long executeInsert(CancellationSignal cancellationSignal) {
        acquireReference();
        try {
            try {
                return getSession().executeForLastInsertedRowId(getSql(), getBindArgs(), getConnectionFlags(), cancellationSignal);
            } catch (SQLiteDatabaseCorruptException e2) {
                checkCorruption(e2);
                throw e2;
            }
        } finally {
            releaseReference();
        }
    }

    public int executeUpdateDelete(CancellationSignal cancellationSignal) {
        acquireReference();
        try {
            try {
                return getSession().executeForChangedRowCount(getSql(), getBindArgs(), getConnectionFlags(), cancellationSignal);
            } catch (SQLiteDatabaseCorruptException e2) {
                checkCorruption(e2);
                throw e2;
            }
        } finally {
            releaseReference();
        }
    }

    public long simpleQueryForLong(CancellationSignal cancellationSignal) {
        acquireReference();
        try {
            try {
                return getSession().executeForLong(getSql(), getBindArgs(), getConnectionFlags(), cancellationSignal);
            } catch (SQLiteDatabaseCorruptException e2) {
                checkCorruption(e2);
                throw e2;
            }
        } finally {
            releaseReference();
        }
    }

    public String simpleQueryForString(CancellationSignal cancellationSignal) {
        acquireReference();
        try {
            try {
                return getSession().executeForString(getSql(), getBindArgs(), getConnectionFlags(), cancellationSignal);
            } catch (SQLiteDatabaseCorruptException e2) {
                checkCorruption(e2);
                throw e2;
            }
        } finally {
            releaseReference();
        }
    }
}
