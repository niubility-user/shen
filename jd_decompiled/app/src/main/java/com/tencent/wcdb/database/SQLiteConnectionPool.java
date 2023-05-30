package com.tencent.wcdb.database;

import android.os.SystemClock;
import android.util.Printer;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.tencent.wcdb.database.SQLiteDebug;
import com.tencent.wcdb.support.CancellationSignal;
import com.tencent.wcdb.support.Log;
import com.tencent.wcdb.support.OperationCanceledException;
import com.tencent.wcdb.support.PrefixPrinter;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/* loaded from: classes9.dex */
public final class SQLiteConnectionPool implements Closeable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CONNECTION_FLAG_INTERACTIVE = 4;
    public static final int CONNECTION_FLAG_PRIMARY_CONNECTION_AFFINITY = 2;
    public static final int CONNECTION_FLAG_READ_ONLY = 1;
    private static final long CONNECTION_POOL_BUSY_MILLIS = 3000;
    private static final int OPEN_FLAG_REOPEN_MASK = 268435473;
    private static final String TAG = "WCDB.SQLiteConnectionPool";
    private SQLiteConnection mAvailablePrimaryConnection;
    private volatile SQLiteChangeListener mChangeListener;
    private volatile SQLiteCheckpointListener mCheckpointListener;
    private SQLiteCipherSpec mCipher;
    private final SQLiteDatabaseConfiguration mConfiguration;
    private ConnectionWaiter mConnectionWaiterPool;
    private ConnectionWaiter mConnectionWaiterQueue;
    private final WeakReference<SQLiteDatabase> mDB;
    private boolean mIsOpen;
    private int mMaxConnectionPoolSize;
    private int mNextConnectionId;
    private byte[] mPassword;
    private volatile SQLiteTrace mTraceCallback;
    private final Object mLock = new Object();
    private final AtomicBoolean mConnectionLeaked = new AtomicBoolean();
    private final ArrayList<SQLiteConnection> mAvailableNonPrimaryConnections = new ArrayList<>();
    private final WeakHashMap<SQLiteConnection, AcquiredConnectionStatus> mAcquiredConnections = new WeakHashMap<>();

    /* loaded from: classes9.dex */
    public enum AcquiredConnectionStatus {
        NORMAL,
        RECONFIGURE,
        DISCARD
    }

    /* loaded from: classes9.dex */
    public static final class ConnectionWaiter {
        public SQLiteConnection mAssignedConnection;
        public int mConnectionFlags;
        public RuntimeException mException;
        public ConnectionWaiter mNext;
        public int mNonce;
        public int mPriority;
        public String mSql;
        public long mStartTime;
        public Thread mThread;
        public boolean mWantPrimaryConnection;

        private ConnectionWaiter() {
        }
    }

    private SQLiteConnectionPool(SQLiteDatabase sQLiteDatabase, SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration, int i2) {
        this.mDB = new WeakReference<>(sQLiteDatabase);
        this.mConfiguration = new SQLiteDatabaseConfiguration(sQLiteDatabaseConfiguration);
        setMaxConnectionPoolSizeLocked(i2);
    }

    public void cancelConnectionWaiterLocked(ConnectionWaiter connectionWaiter) {
        ConnectionWaiter connectionWaiter2;
        if (connectionWaiter.mAssignedConnection == null && connectionWaiter.mException == null) {
            ConnectionWaiter connectionWaiter3 = null;
            ConnectionWaiter connectionWaiter4 = this.mConnectionWaiterQueue;
            while (true) {
                ConnectionWaiter connectionWaiter5 = connectionWaiter4;
                connectionWaiter2 = connectionWaiter3;
                connectionWaiter3 = connectionWaiter5;
                if (connectionWaiter3 == connectionWaiter) {
                    break;
                }
                connectionWaiter4 = connectionWaiter3.mNext;
            }
            if (connectionWaiter2 != null) {
                connectionWaiter2.mNext = connectionWaiter.mNext;
            } else {
                this.mConnectionWaiterQueue = connectionWaiter.mNext;
            }
            connectionWaiter.mException = new OperationCanceledException();
            LockSupport.unpark(connectionWaiter.mThread);
            wakeConnectionWaitersLocked();
        }
    }

    private void closeAvailableConnectionsAndLogExceptionsLocked() {
        closeAvailableNonPrimaryConnectionsAndLogExceptionsLocked();
        SQLiteConnection sQLiteConnection = this.mAvailablePrimaryConnection;
        if (sQLiteConnection != null) {
            closeConnectionAndLogExceptionsLocked(sQLiteConnection);
            this.mAvailablePrimaryConnection = null;
        }
    }

    private void closeAvailableNonPrimaryConnectionsAndLogExceptionsLocked() {
        int size = this.mAvailableNonPrimaryConnections.size();
        for (int i2 = 0; i2 < size; i2++) {
            closeConnectionAndLogExceptionsLocked(this.mAvailableNonPrimaryConnections.get(i2));
        }
        this.mAvailableNonPrimaryConnections.clear();
    }

    private void closeConnectionAndLogExceptionsLocked(SQLiteConnection sQLiteConnection) {
        try {
            sQLiteConnection.close();
        } catch (RuntimeException e2) {
            Log.e(TAG, "Failed to close connection, its fate is now in the hands of the merciful GC: " + sQLiteConnection + e2.getMessage());
        }
    }

    private void closeExcessConnectionsAndLogExceptionsLocked() {
        int size = this.mAvailableNonPrimaryConnections.size();
        while (true) {
            int i2 = size - 1;
            if (size <= this.mMaxConnectionPoolSize - 1) {
                return;
            }
            closeConnectionAndLogExceptionsLocked(this.mAvailableNonPrimaryConnections.remove(i2));
            size = i2;
        }
    }

    private void discardAcquiredConnectionsLocked() {
        markAcquiredConnectionsLocked(AcquiredConnectionStatus.DISCARD);
    }

    private void dispose(boolean z) {
        if (z) {
            return;
        }
        synchronized (this.mLock) {
            throwIfClosedLocked();
            this.mIsOpen = false;
            closeAvailableConnectionsAndLogExceptionsLocked();
            int size = this.mAcquiredConnections.size();
            if (size != 0) {
                Log.i(TAG, "The connection pool for " + this.mConfiguration.label + " has been closed but there are still " + size + " connections in use.  They will be closed as they are released back to the pool.");
            }
            wakeConnectionWaitersLocked();
        }
    }

    private void finishAcquireConnectionLocked(SQLiteConnection sQLiteConnection, int i2) {
        try {
            sQLiteConnection.setOnlyAllowReadOnlyOperations((i2 & 1) != 0);
            this.mAcquiredConnections.put(sQLiteConnection, AcquiredConnectionStatus.NORMAL);
        } catch (RuntimeException e2) {
            Log.e(TAG, "Failed to prepare acquired connection for session, closing it: " + sQLiteConnection + ", connectionFlags=" + i2);
            closeConnectionAndLogExceptionsLocked(sQLiteConnection);
            throw e2;
        }
    }

    private static int getPriority(int i2) {
        return (i2 & 4) != 0 ? 1 : 0;
    }

    private boolean isSessionBlockingImportantConnectionWaitersLocked(boolean z, int i2) {
        ConnectionWaiter connectionWaiter = this.mConnectionWaiterQueue;
        if (connectionWaiter != null) {
            int priority = getPriority(i2);
            while (priority <= connectionWaiter.mPriority) {
                if (z || !connectionWaiter.mWantPrimaryConnection) {
                    return true;
                }
                connectionWaiter = connectionWaiter.mNext;
                if (connectionWaiter == null) {
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    private void logConnectionPoolBusyLocked(String str, long j2, int i2) {
        int i3;
        StringBuilder sb = new StringBuilder();
        if (j2 != 0) {
            Thread currentThread = Thread.currentThread();
            sb.append("The connection pool for database '");
            sb.append(this.mConfiguration.label);
            sb.append("' has been unable to grant a connection to thread ");
            sb.append(currentThread.getId());
            sb.append(" (");
            sb.append(currentThread.getName());
            sb.append(") ");
            sb.append("with flags 0x");
            sb.append(Integer.toHexString(i2));
            sb.append(" for ");
            sb.append(((float) j2) * 0.001f);
            sb.append(" seconds.\n");
        }
        ArrayList arrayList = new ArrayList();
        int i4 = 0;
        if (this.mAcquiredConnections.isEmpty()) {
            i3 = 0;
        } else {
            Iterator<SQLiteConnection> it = this.mAcquiredConnections.keySet().iterator();
            i3 = 0;
            while (it.hasNext()) {
                String describeCurrentOperationUnsafe = it.next().describeCurrentOperationUnsafe();
                if (describeCurrentOperationUnsafe != null) {
                    arrayList.add(describeCurrentOperationUnsafe);
                    i4++;
                } else {
                    i3++;
                }
            }
        }
        int size = this.mAvailableNonPrimaryConnections.size();
        if (this.mAvailablePrimaryConnection != null) {
            size++;
        }
        sb.append("Connections: ");
        sb.append(i4);
        sb.append(" active, ");
        sb.append(i3);
        sb.append(" idle, ");
        sb.append(size);
        sb.append(" available.\n");
        if (!arrayList.isEmpty()) {
            sb.append("\nRequests in progress:\n");
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                sb.append("  ");
                sb.append((String) it2.next());
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
        String sb2 = sb.toString();
        Log.w(TAG, sb2);
        SQLiteDatabase sQLiteDatabase = this.mDB.get();
        if (sQLiteDatabase == null || this.mTraceCallback == null) {
            return;
        }
        this.mTraceCallback.onConnectionPoolBusy(sQLiteDatabase, str, arrayList, sb2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void markAcquiredConnectionsLocked(AcquiredConnectionStatus acquiredConnectionStatus) {
        if (this.mAcquiredConnections.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.mAcquiredConnections.size());
        for (Map.Entry<SQLiteConnection, AcquiredConnectionStatus> entry : this.mAcquiredConnections.entrySet()) {
            AcquiredConnectionStatus value = entry.getValue();
            if (acquiredConnectionStatus != value && value != AcquiredConnectionStatus.DISCARD) {
                arrayList.add(entry.getKey());
            }
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mAcquiredConnections.put(arrayList.get(i2), acquiredConnectionStatus);
        }
    }

    private ConnectionWaiter obtainConnectionWaiterLocked(Thread thread, long j2, int i2, boolean z, String str, int i3) {
        ConnectionWaiter connectionWaiter = this.mConnectionWaiterPool;
        if (connectionWaiter != null) {
            this.mConnectionWaiterPool = connectionWaiter.mNext;
            connectionWaiter.mNext = null;
        } else {
            connectionWaiter = new ConnectionWaiter();
        }
        connectionWaiter.mThread = thread;
        connectionWaiter.mStartTime = j2;
        connectionWaiter.mPriority = i2;
        connectionWaiter.mWantPrimaryConnection = z;
        connectionWaiter.mSql = str;
        connectionWaiter.mConnectionFlags = i3;
        return connectionWaiter;
    }

    public static SQLiteConnectionPool open(SQLiteDatabase sQLiteDatabase, SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec) {
        return open(sQLiteDatabase, sQLiteDatabaseConfiguration, bArr, sQLiteCipherSpec, 1);
    }

    private SQLiteConnection openConnectionLocked(SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration, boolean z) {
        int i2 = this.mNextConnectionId;
        this.mNextConnectionId = i2 + 1;
        return SQLiteConnection.open(this, sQLiteDatabaseConfiguration, i2, z, this.mPassword, this.mCipher);
    }

    private void reconfigureAllConnectionsLocked() {
        SQLiteConnection sQLiteConnection = this.mAvailablePrimaryConnection;
        if (sQLiteConnection != null) {
            try {
                sQLiteConnection.reconfigure(this.mConfiguration);
            } catch (RuntimeException e2) {
                Log.e(TAG, "Failed to reconfigure available primary connection, closing it: " + this.mAvailablePrimaryConnection, e2);
                closeConnectionAndLogExceptionsLocked(this.mAvailablePrimaryConnection);
                this.mAvailablePrimaryConnection = null;
            }
        }
        int size = this.mAvailableNonPrimaryConnections.size();
        int i2 = 0;
        while (i2 < size) {
            SQLiteConnection sQLiteConnection2 = this.mAvailableNonPrimaryConnections.get(i2);
            try {
                sQLiteConnection2.reconfigure(this.mConfiguration);
            } catch (RuntimeException e3) {
                Log.e(TAG, "Failed to reconfigure available non-primary connection, closing it: " + sQLiteConnection2, e3);
                closeConnectionAndLogExceptionsLocked(sQLiteConnection2);
                this.mAvailableNonPrimaryConnections.remove(i2);
                size += -1;
                i2--;
            }
            i2++;
        }
        markAcquiredConnectionsLocked(AcquiredConnectionStatus.RECONFIGURE);
    }

    private boolean recycleConnectionLocked(SQLiteConnection sQLiteConnection, AcquiredConnectionStatus acquiredConnectionStatus) {
        if (acquiredConnectionStatus == AcquiredConnectionStatus.RECONFIGURE) {
            try {
                sQLiteConnection.reconfigure(this.mConfiguration);
            } catch (RuntimeException e2) {
                Log.e(TAG, "Failed to reconfigure released connection, closing it: " + sQLiteConnection, e2);
                acquiredConnectionStatus = AcquiredConnectionStatus.DISCARD;
            }
        }
        if (acquiredConnectionStatus == AcquiredConnectionStatus.DISCARD) {
            closeConnectionAndLogExceptionsLocked(sQLiteConnection);
            return false;
        }
        return true;
    }

    private void recycleConnectionWaiterLocked(ConnectionWaiter connectionWaiter) {
        connectionWaiter.mNext = this.mConnectionWaiterPool;
        connectionWaiter.mThread = null;
        connectionWaiter.mSql = null;
        connectionWaiter.mAssignedConnection = null;
        connectionWaiter.mException = null;
        connectionWaiter.mNonce++;
        this.mConnectionWaiterPool = connectionWaiter;
    }

    private void setMaxConnectionPoolSizeLocked(int i2) {
        if (i2 <= 0) {
            i2 = (this.mConfiguration.openFlags & SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING) != 0 ? 4 : 1;
        }
        this.mMaxConnectionPoolSize = i2;
        Log.i(TAG, "Max connection pool size is %d.", Integer.valueOf(i2));
    }

    private void throwIfClosedLocked() {
        if (!this.mIsOpen) {
            throw new IllegalStateException("Cannot perform this operation because the connection pool has been closed.");
        }
    }

    private SQLiteConnection tryAcquireNonPrimaryConnectionLocked(String str, int i2) {
        int size = this.mAvailableNonPrimaryConnections.size();
        if (size > 1 && str != null) {
            for (int i3 = 0; i3 < size; i3++) {
                SQLiteConnection sQLiteConnection = this.mAvailableNonPrimaryConnections.get(i3);
                if (sQLiteConnection.isPreparedStatementInCache(str)) {
                    this.mAvailableNonPrimaryConnections.remove(i3);
                    finishAcquireConnectionLocked(sQLiteConnection, i2);
                    return sQLiteConnection;
                }
            }
        }
        if (size > 0) {
            SQLiteConnection remove = this.mAvailableNonPrimaryConnections.remove(size - 1);
            finishAcquireConnectionLocked(remove, i2);
            return remove;
        }
        int size2 = this.mAcquiredConnections.size();
        if (this.mAvailablePrimaryConnection != null) {
            size2++;
        }
        if (size2 >= this.mMaxConnectionPoolSize) {
            return null;
        }
        SQLiteConnection openConnectionLocked = openConnectionLocked(this.mConfiguration, false);
        finishAcquireConnectionLocked(openConnectionLocked, i2);
        return openConnectionLocked;
    }

    private SQLiteConnection tryAcquirePrimaryConnectionLocked(int i2) {
        SQLiteConnection sQLiteConnection = this.mAvailablePrimaryConnection;
        if (sQLiteConnection != null) {
            this.mAvailablePrimaryConnection = null;
            finishAcquireConnectionLocked(sQLiteConnection, i2);
            return sQLiteConnection;
        }
        Iterator<SQLiteConnection> it = this.mAcquiredConnections.keySet().iterator();
        while (it.hasNext()) {
            if (it.next().isPrimaryConnection()) {
                return null;
            }
        }
        SQLiteConnection openConnectionLocked = openConnectionLocked(this.mConfiguration, true);
        finishAcquireConnectionLocked(openConnectionLocked, i2);
        return openConnectionLocked;
    }

    /* JADX WARN: Removed duplicated region for block: B:141:0x00c2 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.tencent.wcdb.database.SQLiteConnection waitForConnection(java.lang.String r19, int r20, com.tencent.wcdb.support.CancellationSignal r21) {
        /*
            Method dump skipped, instructions count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wcdb.database.SQLiteConnectionPool.waitForConnection(java.lang.String, int, com.tencent.wcdb.support.CancellationSignal):com.tencent.wcdb.database.SQLiteConnection");
    }

    private void wakeConnectionWaitersLocked() {
        SQLiteConnection sQLiteConnection;
        ConnectionWaiter connectionWaiter = this.mConnectionWaiterQueue;
        ConnectionWaiter connectionWaiter2 = null;
        boolean z = false;
        boolean z2 = false;
        while (connectionWaiter != null) {
            boolean z3 = true;
            if (this.mIsOpen) {
                try {
                    if (connectionWaiter.mWantPrimaryConnection || z) {
                        sQLiteConnection = null;
                    } else {
                        sQLiteConnection = tryAcquireNonPrimaryConnectionLocked(connectionWaiter.mSql, connectionWaiter.mConnectionFlags);
                        if (sQLiteConnection == null) {
                            z = true;
                        }
                    }
                    if (sQLiteConnection == null && !z2 && (sQLiteConnection = tryAcquirePrimaryConnectionLocked(connectionWaiter.mConnectionFlags)) == null) {
                        z2 = true;
                    }
                    if (sQLiteConnection != null) {
                        connectionWaiter.mAssignedConnection = sQLiteConnection;
                    } else if (z && z2) {
                        return;
                    } else {
                        z3 = false;
                    }
                } catch (RuntimeException e2) {
                    connectionWaiter.mException = e2;
                }
            }
            ConnectionWaiter connectionWaiter3 = connectionWaiter.mNext;
            if (z3) {
                if (connectionWaiter2 != null) {
                    connectionWaiter2.mNext = connectionWaiter3;
                } else {
                    this.mConnectionWaiterQueue = connectionWaiter3;
                }
                connectionWaiter.mNext = null;
                LockSupport.unpark(connectionWaiter.mThread);
            } else {
                connectionWaiter2 = connectionWaiter;
            }
            connectionWaiter = connectionWaiter3;
        }
    }

    public SQLiteConnection acquireConnection(String str, int i2, CancellationSignal cancellationSignal) {
        long uptimeMillis = SystemClock.uptimeMillis();
        SQLiteConnection waitForConnection = waitForConnection(str, i2, cancellationSignal);
        if (this.mTraceCallback != null) {
            long uptimeMillis2 = SystemClock.uptimeMillis() - uptimeMillis;
            SQLiteDatabase sQLiteDatabase = this.mDB.get();
            if (sQLiteDatabase != null) {
                this.mTraceCallback.onConnectionObtained(sQLiteDatabase, str, uptimeMillis2, (i2 & 2) != 0);
            }
        }
        return waitForConnection;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        dispose(false);
    }

    public void collectDbStats(ArrayList<SQLiteDebug.DbStats> arrayList) {
        synchronized (this.mLock) {
            SQLiteConnection sQLiteConnection = this.mAvailablePrimaryConnection;
            if (sQLiteConnection != null) {
                sQLiteConnection.collectDbStats(arrayList);
            }
            Iterator<SQLiteConnection> it = this.mAvailableNonPrimaryConnections.iterator();
            while (it.hasNext()) {
                it.next().collectDbStats(arrayList);
            }
            Iterator<SQLiteConnection> it2 = this.mAcquiredConnections.keySet().iterator();
            while (it2.hasNext()) {
                it2.next().collectDbStatsUnsafe(arrayList);
            }
        }
    }

    public void dump(Printer printer, boolean z) {
        Printer create = PrefixPrinter.create(printer, "    ");
        synchronized (this.mLock) {
            printer.println("Connection pool for " + this.mConfiguration.path + ":");
            StringBuilder sb = new StringBuilder();
            sb.append("  Open: ");
            sb.append(this.mIsOpen);
            printer.println(sb.toString());
            printer.println("  Max connections: " + this.mMaxConnectionPoolSize);
            printer.println("  Available primary connection:");
            SQLiteConnection sQLiteConnection = this.mAvailablePrimaryConnection;
            if (sQLiteConnection != null) {
                sQLiteConnection.dump(create, z);
            } else {
                create.println("<none>");
            }
            printer.println("  Available non-primary connections:");
            int i2 = 0;
            if (!this.mAvailableNonPrimaryConnections.isEmpty()) {
                int size = this.mAvailableNonPrimaryConnections.size();
                for (int i3 = 0; i3 < size; i3++) {
                    this.mAvailableNonPrimaryConnections.get(i3).dump(create, z);
                }
            } else {
                create.println("<none>");
            }
            printer.println("  Acquired connections:");
            if (!this.mAcquiredConnections.isEmpty()) {
                for (Map.Entry<SQLiteConnection, AcquiredConnectionStatus> entry : this.mAcquiredConnections.entrySet()) {
                    entry.getKey().dumpUnsafe(create, z);
                    create.println("  Status: " + entry.getValue());
                }
            } else {
                create.println("<none>");
            }
            printer.println("  Connection waiters:");
            if (this.mConnectionWaiterQueue != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                ConnectionWaiter connectionWaiter = this.mConnectionWaiterQueue;
                while (connectionWaiter != null) {
                    create.println(i2 + ": waited for " + (uptimeMillis - connectionWaiter.mStartTime) + " ms - thread=" + connectionWaiter.mThread + ", priority=" + connectionWaiter.mPriority + ", sql='" + connectionWaiter.mSql + "'");
                    connectionWaiter = connectionWaiter.mNext;
                    i2++;
                }
            } else {
                create.println("<none>");
            }
        }
    }

    protected void finalize() throws Throwable {
        try {
            dispose(true);
        } finally {
            super.finalize();
        }
    }

    public SQLiteChangeListener getChangeListener() {
        return this.mChangeListener;
    }

    public SQLiteCheckpointListener getCheckpointListener() {
        return this.mCheckpointListener;
    }

    public SQLiteTrace getTraceCallback() {
        return this.mTraceCallback;
    }

    public void logConnectionPoolBusy(String str) {
        synchronized (this.mLock) {
            logConnectionPoolBusyLocked(str, 0L, 0);
        }
    }

    public void notifyChanges(String str, String str2, long[] jArr, long[] jArr2, long[] jArr3) {
        SQLiteDatabase sQLiteDatabase = this.mDB.get();
        SQLiteChangeListener sQLiteChangeListener = this.mChangeListener;
        if (sQLiteChangeListener == null || sQLiteDatabase == null) {
            return;
        }
        sQLiteChangeListener.onChange(sQLiteDatabase, str, str2, jArr, jArr2, jArr3);
    }

    public void notifyCheckpoint(String str, int i2) {
        SQLiteDatabase sQLiteDatabase = this.mDB.get();
        SQLiteCheckpointListener sQLiteCheckpointListener = this.mCheckpointListener;
        if (sQLiteCheckpointListener == null || sQLiteDatabase == null) {
            return;
        }
        sQLiteCheckpointListener.onWALCommit(sQLiteDatabase, str, i2);
    }

    public void onConnectionLeaked() {
        Log.w(TAG, "A SQLiteConnection object for database '" + this.mConfiguration.label + "' was leaked!  Please fix your application to end transactions in progress properly and to close the database when it is no longer needed.");
        this.mConnectionLeaked.set(true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x0076, code lost:
        closeAvailableConnectionsAndLogExceptionsLocked();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void reconfigure(com.tencent.wcdb.database.SQLiteDatabaseConfiguration r8) {
        /*
            r7 = this;
            if (r8 == 0) goto L95
            java.lang.Object r0 = r7.mLock
            monitor-enter(r0)
            r7.throwIfClosedLocked()     // Catch: java.lang.Throwable -> L92
            int r1 = r8.openFlags     // Catch: java.lang.Throwable -> L92
            com.tencent.wcdb.database.SQLiteDatabaseConfiguration r2 = r7.mConfiguration     // Catch: java.lang.Throwable -> L92
            int r2 = r2.openFlags     // Catch: java.lang.Throwable -> L92
            r1 = r1 ^ r2
            r2 = 536870912(0x20000000, float:1.0842022E-19)
            r1 = r1 & r2
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L18
            r1 = 1
            goto L19
        L18:
            r1 = 0
        L19:
            if (r1 == 0) goto L2f
            java.util.WeakHashMap<com.tencent.wcdb.database.SQLiteConnection, com.tencent.wcdb.database.SQLiteConnectionPool$AcquiredConnectionStatus> r4 = r7.mAcquiredConnections     // Catch: java.lang.Throwable -> L92
            boolean r4 = r4.isEmpty()     // Catch: java.lang.Throwable -> L92
            if (r4 == 0) goto L27
            r7.closeAvailableNonPrimaryConnectionsAndLogExceptionsLocked()     // Catch: java.lang.Throwable -> L92
            goto L2f
        L27:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L92
            java.lang.String r1 = "Write Ahead Logging (WAL) mode cannot be enabled or disabled while there are transactions in progress.  Finish all transactions and release all active database connections first."
            r8.<init>(r1)     // Catch: java.lang.Throwable -> L92
            throw r8     // Catch: java.lang.Throwable -> L92
        L2f:
            boolean r4 = r8.foreignKeyConstraintsEnabled     // Catch: java.lang.Throwable -> L92
            com.tencent.wcdb.database.SQLiteDatabaseConfiguration r5 = r7.mConfiguration     // Catch: java.lang.Throwable -> L92
            boolean r5 = r5.foreignKeyConstraintsEnabled     // Catch: java.lang.Throwable -> L92
            if (r4 == r5) goto L39
            r4 = 1
            goto L3a
        L39:
            r4 = 0
        L3a:
            if (r4 == 0) goto L4d
            java.util.WeakHashMap<com.tencent.wcdb.database.SQLiteConnection, com.tencent.wcdb.database.SQLiteConnectionPool$AcquiredConnectionStatus> r4 = r7.mAcquiredConnections     // Catch: java.lang.Throwable -> L92
            boolean r4 = r4.isEmpty()     // Catch: java.lang.Throwable -> L92
            if (r4 == 0) goto L45
            goto L4d
        L45:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L92
            java.lang.String r1 = "Foreign Key Constraints cannot be enabled or disabled while there are transactions in progress.  Finish all transactions and release all active database connections first."
            r8.<init>(r1)     // Catch: java.lang.Throwable -> L92
            throw r8     // Catch: java.lang.Throwable -> L92
        L4d:
            com.tencent.wcdb.database.SQLiteDatabaseConfiguration r4 = r7.mConfiguration     // Catch: java.lang.Throwable -> L92
            int r5 = r4.openFlags     // Catch: java.lang.Throwable -> L92
            int r6 = r8.openFlags     // Catch: java.lang.Throwable -> L92
            r5 = r5 ^ r6
            r6 = 268435473(0x10000011, float:2.52436E-29)
            r5 = r5 & r6
            if (r5 != 0) goto L74
            java.lang.String r4 = r4.vfsName     // Catch: java.lang.Throwable -> L92
            java.lang.String r5 = r8.vfsName     // Catch: java.lang.Throwable -> L92
            boolean r4 = com.tencent.wcdb.DatabaseUtils.objectEquals(r4, r5)     // Catch: java.lang.Throwable -> L92
            if (r4 != 0) goto L65
            goto L74
        L65:
            com.tencent.wcdb.database.SQLiteDatabaseConfiguration r1 = r7.mConfiguration     // Catch: java.lang.Throwable -> L92
            r1.updateParametersFrom(r8)     // Catch: java.lang.Throwable -> L92
            r7.setMaxConnectionPoolSizeLocked(r3)     // Catch: java.lang.Throwable -> L92
            r7.closeExcessConnectionsAndLogExceptionsLocked()     // Catch: java.lang.Throwable -> L92
            r7.reconfigureAllConnectionsLocked()     // Catch: java.lang.Throwable -> L92
            goto L8d
        L74:
            if (r1 == 0) goto L79
            r7.closeAvailableConnectionsAndLogExceptionsLocked()     // Catch: java.lang.Throwable -> L92
        L79:
            com.tencent.wcdb.database.SQLiteConnection r1 = r7.openConnectionLocked(r8, r2)     // Catch: java.lang.Throwable -> L92
            r7.closeAvailableConnectionsAndLogExceptionsLocked()     // Catch: java.lang.Throwable -> L92
            r7.discardAcquiredConnectionsLocked()     // Catch: java.lang.Throwable -> L92
            r7.mAvailablePrimaryConnection = r1     // Catch: java.lang.Throwable -> L92
            com.tencent.wcdb.database.SQLiteDatabaseConfiguration r1 = r7.mConfiguration     // Catch: java.lang.Throwable -> L92
            r1.updateParametersFrom(r8)     // Catch: java.lang.Throwable -> L92
            r7.setMaxConnectionPoolSizeLocked(r3)     // Catch: java.lang.Throwable -> L92
        L8d:
            r7.wakeConnectionWaitersLocked()     // Catch: java.lang.Throwable -> L92
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L92
            return
        L92:
            r8 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L92
            throw r8
        L95:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "configuration must not be null."
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wcdb.database.SQLiteConnectionPool.reconfigure(com.tencent.wcdb.database.SQLiteDatabaseConfiguration):void");
    }

    public void releaseConnection(SQLiteConnection sQLiteConnection) {
        synchronized (this.mLock) {
            AcquiredConnectionStatus remove = this.mAcquiredConnections.remove(sQLiteConnection);
            if (remove != null) {
                if (!this.mIsOpen) {
                    closeConnectionAndLogExceptionsLocked(sQLiteConnection);
                } else if (sQLiteConnection.isPrimaryConnection()) {
                    if (recycleConnectionLocked(sQLiteConnection, remove)) {
                        this.mAvailablePrimaryConnection = sQLiteConnection;
                    }
                    wakeConnectionWaitersLocked();
                } else if (this.mAvailableNonPrimaryConnections.size() >= this.mMaxConnectionPoolSize - 1) {
                    closeConnectionAndLogExceptionsLocked(sQLiteConnection);
                } else {
                    if (recycleConnectionLocked(sQLiteConnection, remove)) {
                        this.mAvailableNonPrimaryConnections.add(sQLiteConnection);
                    }
                    wakeConnectionWaitersLocked();
                }
            } else {
                throw new IllegalStateException("Cannot perform this operation because the specified connection was not acquired from this pool or has already been released.");
            }
        }
    }

    public void setChangeListener(SQLiteChangeListener sQLiteChangeListener, boolean z) {
        boolean z2 = sQLiteChangeListener != null;
        if (!z2) {
            z = false;
        }
        synchronized (this.mLock) {
            SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration = this.mConfiguration;
            if (sQLiteDatabaseConfiguration.updateNotificationEnabled != z2 || sQLiteDatabaseConfiguration.updateNotificationRowID != z) {
                sQLiteDatabaseConfiguration.updateNotificationEnabled = z2;
                sQLiteDatabaseConfiguration.updateNotificationRowID = z;
                closeExcessConnectionsAndLogExceptionsLocked();
                reconfigureAllConnectionsLocked();
            }
            this.mChangeListener = sQLiteChangeListener;
        }
    }

    public void setCheckpointListener(SQLiteCheckpointListener sQLiteCheckpointListener) {
        SQLiteDatabase sQLiteDatabase = this.mDB.get();
        if (this.mCheckpointListener != null) {
            this.mCheckpointListener.onDetach(sQLiteDatabase);
        }
        this.mCheckpointListener = sQLiteCheckpointListener;
        if (this.mCheckpointListener != null) {
            this.mCheckpointListener.onAttach(sQLiteDatabase);
        }
    }

    public void setTraceCallback(SQLiteTrace sQLiteTrace) {
        this.mTraceCallback = sQLiteTrace;
    }

    public boolean shouldYieldConnection(SQLiteConnection sQLiteConnection, int i2) {
        synchronized (this.mLock) {
            if (this.mAcquiredConnections.containsKey(sQLiteConnection)) {
                if (this.mIsOpen) {
                    return isSessionBlockingImportantConnectionWaitersLocked(sQLiteConnection.isPrimaryConnection(), i2);
                }
                return false;
            }
            throw new IllegalStateException("Cannot perform this operation because the specified connection was not acquired from this pool or has already been released.");
        }
    }

    public String toString() {
        return "SQLiteConnectionPool: " + this.mConfiguration.path;
    }

    public void traceExecute(String str, int i2, long j2) {
        SQLiteDatabase sQLiteDatabase = this.mDB.get();
        SQLiteTrace sQLiteTrace = this.mTraceCallback;
        if (sQLiteTrace == null || sQLiteDatabase == null) {
            return;
        }
        sQLiteTrace.onSQLExecuted(sQLiteDatabase, str, i2, j2);
    }

    public static SQLiteConnectionPool open(SQLiteDatabase sQLiteDatabase, SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec, int i2) {
        if (sQLiteDatabaseConfiguration != null) {
            SQLiteConnectionPool sQLiteConnectionPool = new SQLiteConnectionPool(sQLiteDatabase, sQLiteDatabaseConfiguration, i2);
            sQLiteConnectionPool.mPassword = bArr;
            sQLiteConnectionPool.mCipher = sQLiteCipherSpec == null ? null : new SQLiteCipherSpec(sQLiteCipherSpec);
            sQLiteConnectionPool.open();
            return sQLiteConnectionPool;
        }
        throw new IllegalArgumentException("configuration must not be null.");
    }

    private void open() {
        this.mAvailablePrimaryConnection = openConnectionLocked(this.mConfiguration, true);
        this.mIsOpen = true;
    }
}
