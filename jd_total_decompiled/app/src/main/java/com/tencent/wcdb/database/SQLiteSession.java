package com.tencent.wcdb.database;

import android.database.sqlite.SQLiteTransactionListener;
import android.os.Process;
import android.util.Pair;
import com.tencent.wcdb.CursorWindow;
import com.tencent.wcdb.DatabaseUtils;
import com.tencent.wcdb.database.SQLiteConnection;
import com.tencent.wcdb.support.CancellationSignal;

/* loaded from: classes9.dex */
public final class SQLiteSession {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int TRANSACTION_MODE_DEFERRED = 0;
    public static final int TRANSACTION_MODE_EXCLUSIVE = 2;
    public static final int TRANSACTION_MODE_IMMEDIATE = 1;
    private SQLiteConnection mConnection;
    private int mConnectionFlags;
    private final SQLiteConnectionPool mConnectionPool;
    private int mConnectionUseCount;
    private Transaction mTransactionPool;
    private Transaction mTransactionStack;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class Transaction {
        public boolean mChildFailed;
        public SQLiteTransactionListener mListener;
        public boolean mMarkedSuccessful;
        public int mMode;
        public Transaction mParent;

        private Transaction() {
        }
    }

    public SQLiteSession(SQLiteConnectionPool sQLiteConnectionPool) {
        if (sQLiteConnectionPool != null) {
            this.mConnectionPool = sQLiteConnectionPool;
            return;
        }
        throw new IllegalArgumentException("connectionPool must not be null");
    }

    private void acquireConnection(String str, int i2, CancellationSignal cancellationSignal) {
        if (this.mConnection == null) {
            SQLiteConnection acquireConnection = this.mConnectionPool.acquireConnection(str, i2, cancellationSignal);
            this.mConnection = acquireConnection;
            this.mConnectionFlags = i2;
            acquireConnection.setAcquisitionState(Thread.currentThread(), Process.myTid());
        }
        this.mConnectionUseCount++;
    }

    private void beginTransactionUnchecked(int i2, SQLiteTransactionListener sQLiteTransactionListener, int i3, CancellationSignal cancellationSignal) {
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        if (this.mTransactionStack == null) {
            acquireConnection(null, i3, cancellationSignal);
        }
        try {
            if (this.mTransactionStack == null) {
                if (i2 == 1) {
                    this.mConnection.execute("BEGIN IMMEDIATE;", null, cancellationSignal);
                } else if (i2 != 2) {
                    this.mConnection.execute("BEGIN;", null, cancellationSignal);
                } else {
                    this.mConnection.execute("BEGIN EXCLUSIVE;", null, cancellationSignal);
                }
            }
            if (sQLiteTransactionListener != null) {
                try {
                    sQLiteTransactionListener.onBegin();
                } catch (RuntimeException e2) {
                    if (this.mTransactionStack == null) {
                        this.mConnection.execute("ROLLBACK;", null, cancellationSignal);
                    }
                    throw e2;
                }
            }
            Transaction obtainTransaction = obtainTransaction(i2, sQLiteTransactionListener);
            obtainTransaction.mParent = this.mTransactionStack;
            this.mTransactionStack = obtainTransaction;
            if (obtainTransaction == null) {
                releaseConnection();
            }
        } catch (Throwable th) {
            if (this.mTransactionStack == null) {
                releaseConnection();
            }
            throw th;
        }
    }

    private void endTransactionUnchecked(CancellationSignal cancellationSignal, boolean z) {
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        Transaction transaction = this.mTransactionStack;
        boolean z2 = false;
        boolean z3 = (transaction.mMarkedSuccessful || z) && !transaction.mChildFailed;
        SQLiteTransactionListener sQLiteTransactionListener = transaction.mListener;
        if (sQLiteTransactionListener != null) {
            try {
                if (z3) {
                    sQLiteTransactionListener.onCommit();
                } else {
                    sQLiteTransactionListener.onRollback();
                }
            } catch (RuntimeException e2) {
                e = e2;
            }
        }
        z2 = z3;
        e = null;
        this.mTransactionStack = transaction.mParent;
        recycleTransaction(transaction);
        Transaction transaction2 = this.mTransactionStack;
        if (transaction2 == null) {
            try {
                if (z2) {
                    this.mConnection.execute("COMMIT;", null, cancellationSignal);
                } else {
                    this.mConnection.execute("ROLLBACK;", null, cancellationSignal);
                }
            } finally {
                releaseConnection();
            }
        } else if (!z2) {
            transaction2.mChildFailed = true;
        }
        if (e != null) {
            throw e;
        }
    }

    private boolean executeSpecial(String str, Object[] objArr, int i2, CancellationSignal cancellationSignal) {
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        int sqlStatementType = DatabaseUtils.getSqlStatementType(str);
        if (sqlStatementType == 4) {
            beginTransaction(2, null, i2, cancellationSignal);
            return true;
        } else if (sqlStatementType == 5) {
            setTransactionSuccessful();
            endTransaction(cancellationSignal);
            return true;
        } else if (sqlStatementType != 6) {
            return false;
        } else {
            endTransaction(cancellationSignal);
            return true;
        }
    }

    private Transaction obtainTransaction(int i2, SQLiteTransactionListener sQLiteTransactionListener) {
        Transaction transaction = this.mTransactionPool;
        if (transaction != null) {
            this.mTransactionPool = transaction.mParent;
            transaction.mParent = null;
            transaction.mMarkedSuccessful = false;
            transaction.mChildFailed = false;
        } else {
            transaction = new Transaction();
        }
        transaction.mMode = i2;
        transaction.mListener = sQLiteTransactionListener;
        return transaction;
    }

    private void recycleTransaction(Transaction transaction) {
        transaction.mParent = this.mTransactionPool;
        transaction.mListener = null;
        this.mTransactionPool = transaction;
    }

    private void releaseConnection() {
        int i2 = this.mConnectionUseCount - 1;
        this.mConnectionUseCount = i2;
        if (i2 == 0) {
            try {
                this.mConnection.setAcquisitionState(null, 0);
                this.mConnectionPool.releaseConnection(this.mConnection);
            } finally {
                this.mConnection = null;
            }
        }
    }

    private void throwIfNestedTransaction() {
        if (hasNestedTransaction()) {
            throw new IllegalStateException("Cannot perform this operation because a nested transaction is in progress.");
        }
    }

    private void throwIfNoTransaction() {
        if (this.mTransactionStack == null) {
            throw new IllegalStateException("Cannot perform this operation because there is no current transaction.");
        }
    }

    private void throwIfTransactionMarkedSuccessful() {
        Transaction transaction = this.mTransactionStack;
        if (transaction != null && transaction.mMarkedSuccessful) {
            throw new IllegalStateException("Cannot perform this operation because the transaction has already been marked successful.  The only thing you can do now is call endTransaction().");
        }
    }

    private boolean yieldTransactionUnchecked(long j2, CancellationSignal cancellationSignal) {
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        if (this.mConnectionPool.shouldYieldConnection(this.mConnection, this.mConnectionFlags)) {
            Transaction transaction = this.mTransactionStack;
            int i2 = transaction.mMode;
            SQLiteTransactionListener sQLiteTransactionListener = transaction.mListener;
            int i3 = this.mConnectionFlags;
            endTransactionUnchecked(cancellationSignal, true);
            if (j2 > 0) {
                try {
                    Thread.sleep(j2);
                } catch (InterruptedException unused) {
                }
            }
            beginTransactionUnchecked(i2, sQLiteTransactionListener, i3, cancellationSignal);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteConnection acquireConnectionForNativeHandle(int i2) {
        acquireConnection(null, i2, null);
        return this.mConnection;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteConnection.PreparedStatement acquirePreparedStatement(String str, int i2) {
        acquireConnection(str, i2, null);
        return this.mConnection.acquirePreparedStatement(str);
    }

    public void beginTransaction(int i2, SQLiteTransactionListener sQLiteTransactionListener, int i3, CancellationSignal cancellationSignal) {
        throwIfTransactionMarkedSuccessful();
        beginTransactionUnchecked(i2, sQLiteTransactionListener, i3, cancellationSignal);
    }

    public void endTransaction(CancellationSignal cancellationSignal) {
        throwIfNoTransaction();
        endTransactionUnchecked(cancellationSignal, false);
    }

    public void execute(String str, Object[] objArr, int i2, CancellationSignal cancellationSignal) {
        if (str != null) {
            if (executeSpecial(str, objArr, i2, cancellationSignal)) {
                return;
            }
            acquireConnection(str, i2, cancellationSignal);
            try {
                this.mConnection.execute(str, objArr, cancellationSignal);
                return;
            } finally {
                releaseConnection();
            }
        }
        throw new IllegalArgumentException("sql must not be null.");
    }

    public int executeForChangedRowCount(String str, Object[] objArr, int i2, CancellationSignal cancellationSignal) {
        if (str != null) {
            if (executeSpecial(str, objArr, i2, cancellationSignal)) {
                return 0;
            }
            acquireConnection(str, i2, cancellationSignal);
            try {
                return this.mConnection.executeForChangedRowCount(str, objArr, cancellationSignal);
            } finally {
                releaseConnection();
            }
        }
        throw new IllegalArgumentException("sql must not be null.");
    }

    public int executeForCursorWindow(String str, Object[] objArr, CursorWindow cursorWindow, int i2, int i3, boolean z, int i4, CancellationSignal cancellationSignal) {
        if (str != null) {
            if (cursorWindow != null) {
                if (executeSpecial(str, objArr, i4, cancellationSignal)) {
                    cursorWindow.clear();
                    return 0;
                }
                acquireConnection(str, i4, cancellationSignal);
                try {
                    return this.mConnection.executeForCursorWindow(str, objArr, cursorWindow, i2, i3, z, cancellationSignal);
                } finally {
                    releaseConnection();
                }
            }
            throw new IllegalArgumentException("window must not be null.");
        }
        throw new IllegalArgumentException("sql must not be null.");
    }

    public long executeForLastInsertedRowId(String str, Object[] objArr, int i2, CancellationSignal cancellationSignal) {
        if (str != null) {
            if (executeSpecial(str, objArr, i2, cancellationSignal)) {
                return 0L;
            }
            acquireConnection(str, i2, cancellationSignal);
            try {
                return this.mConnection.executeForLastInsertedRowId(str, objArr, cancellationSignal);
            } finally {
                releaseConnection();
            }
        }
        throw new IllegalArgumentException("sql must not be null.");
    }

    public long executeForLong(String str, Object[] objArr, int i2, CancellationSignal cancellationSignal) {
        if (str != null) {
            if (executeSpecial(str, objArr, i2, cancellationSignal)) {
                return 0L;
            }
            acquireConnection(str, i2, cancellationSignal);
            try {
                return this.mConnection.executeForLong(str, objArr, cancellationSignal);
            } finally {
                releaseConnection();
            }
        }
        throw new IllegalArgumentException("sql must not be null.");
    }

    public String executeForString(String str, Object[] objArr, int i2, CancellationSignal cancellationSignal) {
        if (str != null) {
            if (executeSpecial(str, objArr, i2, cancellationSignal)) {
                return null;
            }
            acquireConnection(str, i2, cancellationSignal);
            try {
                return this.mConnection.executeForString(str, objArr, cancellationSignal);
            } finally {
                releaseConnection();
            }
        }
        throw new IllegalArgumentException("sql must not be null.");
    }

    public boolean hasConnection() {
        return this.mConnection != null;
    }

    public boolean hasNestedTransaction() {
        Transaction transaction = this.mTransactionStack;
        return (transaction == null || transaction.mParent == null) ? false : true;
    }

    public boolean hasTransaction() {
        return this.mTransactionStack != null;
    }

    public void prepare(String str, int i2, CancellationSignal cancellationSignal, SQLiteStatementInfo sQLiteStatementInfo) {
        if (str != null) {
            if (cancellationSignal != null) {
                cancellationSignal.throwIfCanceled();
            }
            acquireConnection(str, i2, cancellationSignal);
            try {
                this.mConnection.prepare(str, sQLiteStatementInfo);
                return;
            } finally {
                releaseConnection();
            }
        }
        throw new IllegalArgumentException("sql must not be null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseConnectionForNativeHandle(Exception exc) {
        SQLiteConnection sQLiteConnection = this.mConnection;
        if (sQLiteConnection != null) {
            sQLiteConnection.endNativeHandle(exc);
        }
        releaseConnection();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releasePreparedStatement(SQLiteConnection.PreparedStatement preparedStatement) {
        SQLiteConnection sQLiteConnection = this.mConnection;
        if (sQLiteConnection != null) {
            sQLiteConnection.releasePreparedStatement(preparedStatement);
            releaseConnection();
        }
    }

    public void setTransactionSuccessful() {
        throwIfNoTransaction();
        throwIfTransactionMarkedSuccessful();
        this.mTransactionStack.mMarkedSuccessful = true;
    }

    public Pair<Integer, Integer> walCheckpoint(String str, int i2) {
        acquireConnection(null, i2, null);
        try {
            return this.mConnection.walCheckpoint(str);
        } finally {
            releaseConnection();
        }
    }

    public boolean yieldTransaction(long j2, boolean z, CancellationSignal cancellationSignal) {
        if (z) {
            throwIfNoTransaction();
            throwIfTransactionMarkedSuccessful();
            throwIfNestedTransaction();
        } else {
            Transaction transaction = this.mTransactionStack;
            if (transaction == null || transaction.mMarkedSuccessful || transaction.mParent != null) {
                return false;
            }
        }
        if (this.mTransactionStack.mChildFailed) {
            return false;
        }
        return yieldTransactionUnchecked(j2, cancellationSignal);
    }
}
