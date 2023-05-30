package com.tencent.wcdb.database;

import android.annotation.SuppressLint;
import android.util.Pair;
import android.util.Printer;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.utils.LangUtils;
import com.tencent.wcdb.DatabaseUtils;
import com.tencent.wcdb.database.SQLiteDebug;
import com.tencent.wcdb.support.CancellationSignal;
import com.tencent.wcdb.support.Log;
import com.tencent.wcdb.support.LruCache;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public final class SQLiteConnection implements CancellationSignal.OnCancelListener {
    private static final boolean DEBUG = false;
    private static final String TAG = "WCDB.SQLiteConnection";
    private Thread mAcquiredThread;
    private int mAcquiredTid;
    private int mCancellationSignalAttachCount;
    private SQLiteCipherSpec mCipher;
    private final SQLiteDatabaseConfiguration mConfiguration;
    private final int mConnectionId;
    private long mConnectionPtr;
    private final boolean mIsPrimaryConnection;
    private final boolean mIsReadOnlyConnection;
    private int mNativeHandleCount;
    private Operation mNativeOperation;
    private boolean mOnlyAllowReadOnlyOperations;
    private byte[] mPassword;
    private final SQLiteConnectionPool mPool;
    private final PreparedStatementCache mPreparedStatementCache;
    private PreparedStatement mPreparedStatementPool;
    private final OperationLog mRecentOperations = new OperationLog();
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final Pattern TRIM_SQL_PATTERN = Pattern.compile("[\\s]*\\n+[\\s]*");

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"SimpleDateFormat"})
    /* loaded from: classes9.dex */
    public static final class Operation {
        private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        public ArrayList<Object> mBindArgs;
        public int mCookie;
        public long mEndTime;
        public Exception mException;
        public boolean mFinished;
        public String mKind;
        public String mSql;
        public long mStartTime;
        public int mTid;
        public int mType;

        private Operation() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getFormattedStartTime() {
            return sDateFormat.format(new Date(this.mStartTime));
        }

        private String getStatus() {
            return !this.mFinished ? "running" : this.mException != null ? JDReactConstant.FAILED : "succeeded";
        }

        public void describe(StringBuilder sb, boolean z) {
            ArrayList<Object> arrayList;
            sb.append(this.mKind);
            if (this.mFinished) {
                sb.append(" took ");
                sb.append(this.mEndTime - this.mStartTime);
                sb.append("ms");
            } else {
                sb.append(" started ");
                sb.append(System.currentTimeMillis() - this.mStartTime);
                sb.append("ms ago");
            }
            sb.append(" - ");
            sb.append(getStatus());
            if (this.mSql != null) {
                sb.append(", sql=\"");
                sb.append(SQLiteConnection.trimSqlForDisplay(this.mSql));
                sb.append("\"");
            }
            if (this.mTid > 0) {
                sb.append(", tid=");
                sb.append(this.mTid);
            }
            if (z && (arrayList = this.mBindArgs) != null && arrayList.size() != 0) {
                sb.append(", bindArgs=[");
                int size = this.mBindArgs.size();
                for (int i2 = 0; i2 < size; i2++) {
                    Object obj = this.mBindArgs.get(i2);
                    if (i2 != 0) {
                        sb.append(", ");
                    }
                    if (obj == null) {
                        sb.append(DYConstants.DY_NULL_STR);
                    } else if (obj instanceof byte[]) {
                        sb.append("<byte[]>");
                    } else if (obj instanceof String) {
                        sb.append("\"");
                        sb.append((String) obj);
                        sb.append("\"");
                    } else {
                        sb.append(obj);
                    }
                }
                sb.append("]");
            }
            Exception exc = this.mException;
            if (exc == null || exc.getMessage() == null) {
                return;
            }
            sb.append(", exception=\"");
            sb.append(this.mException.getMessage());
            sb.append("\"");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public final class OperationLog {
        private static final int COOKIE_GENERATION_SHIFT = 8;
        private static final int COOKIE_INDEX_MASK = 255;
        private static final int MAX_RECENT_OPERATIONS = 20;
        private int mGeneration;
        private int mIndex;
        private final Operation[] mOperations;

        private OperationLog() {
            this.mOperations = new Operation[20];
        }

        private boolean endOperationDeferLogLocked(Operation operation) {
            if (operation != null) {
                operation.mEndTime = System.currentTimeMillis();
                operation.mFinished = true;
                Exception exc = operation.mException;
                if (exc == null || exc.getMessage() == null) {
                    return SQLiteDebug.shouldLogSlowQuery(operation.mEndTime - operation.mStartTime);
                }
                return true;
            }
            return false;
        }

        private Operation getOperationLocked(int i2) {
            Operation operation = this.mOperations[i2 & 255];
            if (operation.mCookie == i2) {
                return operation;
            }
            return null;
        }

        private void logOperationLocked(Operation operation, String str) {
            StringBuilder sb = new StringBuilder();
            operation.describe(sb, false);
            if (str != null) {
                sb.append(", ");
                sb.append(str);
            }
            Log.i(SQLiteConnection.TAG, sb.toString());
        }

        private int newOperationCookieLocked(int i2) {
            int i3 = this.mGeneration;
            this.mGeneration = i3 + 1;
            return i2 | (i3 << 8);
        }

        public Operation beginOperation(String str, String str2, Object[] objArr) {
            Operation operation;
            synchronized (this.mOperations) {
                int i2 = (this.mIndex + 1) % 20;
                operation = this.mOperations[i2];
                if (operation == null) {
                    operation = new Operation();
                    this.mOperations[i2] = operation;
                } else {
                    operation.mFinished = false;
                    operation.mException = null;
                    ArrayList<Object> arrayList = operation.mBindArgs;
                    if (arrayList != null) {
                        arrayList.clear();
                    }
                }
                operation.mStartTime = System.currentTimeMillis();
                operation.mKind = str;
                operation.mSql = str2;
                if (objArr != null) {
                    ArrayList<Object> arrayList2 = operation.mBindArgs;
                    if (arrayList2 == null) {
                        operation.mBindArgs = new ArrayList<>();
                    } else {
                        arrayList2.clear();
                    }
                    for (Object obj : objArr) {
                        if (obj != null && (obj instanceof byte[])) {
                            operation.mBindArgs.add(SQLiteConnection.EMPTY_BYTE_ARRAY);
                        } else {
                            operation.mBindArgs.add(obj);
                        }
                    }
                }
                operation.mCookie = newOperationCookieLocked(i2);
                operation.mTid = SQLiteConnection.this.mAcquiredTid;
                this.mIndex = i2;
            }
            return operation;
        }

        public String describeCurrentOperation() {
            synchronized (this.mOperations) {
                Operation operation = this.mOperations[this.mIndex];
                if (operation == null || operation.mFinished) {
                    return null;
                }
                StringBuilder sb = new StringBuilder();
                operation.describe(sb, false);
                return sb.toString();
            }
        }

        public void dump(Printer printer, boolean z) {
            synchronized (this.mOperations) {
                printer.println("  Most recently executed operations:");
                int i2 = this.mIndex;
                Operation operation = this.mOperations[i2];
                if (operation != null) {
                    int i3 = 0;
                    do {
                        StringBuilder sb = new StringBuilder();
                        sb.append("    ");
                        sb.append(i3);
                        sb.append(": [");
                        sb.append(operation.getFormattedStartTime());
                        sb.append("] ");
                        operation.describe(sb, z);
                        printer.println(sb.toString());
                        i2 = i2 > 0 ? i2 - 1 : 19;
                        i3++;
                        operation = this.mOperations[i2];
                        if (operation == null) {
                            break;
                        }
                    } while (i3 < 20);
                } else {
                    printer.println("    <none>");
                }
            }
        }

        public void endOperation(int i2) {
            String str;
            String str2;
            int i3;
            long j2;
            synchronized (this.mOperations) {
                Operation operationLocked = getOperationLocked(i2);
                if (endOperationDeferLogLocked(operationLocked)) {
                    logOperationLocked(operationLocked, null);
                }
                str = operationLocked.mSql;
                str2 = operationLocked.mKind;
                i3 = operationLocked.mType;
                j2 = operationLocked.mEndTime - operationLocked.mStartTime;
            }
            if (JDReactConstant.PREPARE.equals(str2)) {
                return;
            }
            SQLiteConnection.this.mPool.traceExecute(str, i3, j2);
        }

        public boolean endOperationDeferLog(int i2) {
            synchronized (this.mOperations) {
                Operation operationLocked = getOperationLocked(i2);
                if (operationLocked == null) {
                    return false;
                }
                boolean endOperationDeferLogLocked = endOperationDeferLogLocked(operationLocked);
                String str = operationLocked.mSql;
                String str2 = operationLocked.mKind;
                int i3 = operationLocked.mType;
                long j2 = operationLocked.mEndTime - operationLocked.mStartTime;
                if (!JDReactConstant.PREPARE.equals(str2)) {
                    SQLiteConnection.this.mPool.traceExecute(str, i3, j2);
                }
                return endOperationDeferLogLocked;
            }
        }

        public void failOperation(int i2, Exception exc) {
            synchronized (this.mOperations) {
                Operation operationLocked = getOperationLocked(i2);
                if (operationLocked != null) {
                    operationLocked.mException = exc;
                }
            }
        }

        public void logOperation(int i2, String str) {
            synchronized (this.mOperations) {
                Operation operationLocked = getOperationLocked(i2);
                if (operationLocked != null) {
                    logOperationLocked(operationLocked, str);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class PreparedStatement {
        private WeakReference<SQLiteConnection> mConnection;
        private boolean mInCache;
        private boolean mInUse;
        private int mNumParameters;
        private Operation mOperation;
        private PreparedStatement mPoolNext;
        private boolean mReadOnly;
        private String mSql;
        private long mStatementPtr;
        private int mType;

        PreparedStatement(SQLiteConnection sQLiteConnection) {
            this.mConnection = new WeakReference<>(sQLiteConnection);
        }

        public void attachCancellationSignal(CancellationSignal cancellationSignal) {
            SQLiteConnection sQLiteConnection = this.mConnection.get();
            if (sQLiteConnection == null) {
                return;
            }
            sQLiteConnection.attachCancellationSignal(cancellationSignal);
        }

        public void beginOperation(String str, Object[] objArr) {
            SQLiteConnection sQLiteConnection = this.mConnection.get();
            if (sQLiteConnection == null) {
                return;
            }
            Operation beginOperation = sQLiteConnection.mRecentOperations.beginOperation(str, this.mSql, objArr);
            this.mOperation = beginOperation;
            beginOperation.mType = this.mType;
        }

        public void bindArguments(Object[] objArr) {
            SQLiteConnection sQLiteConnection = this.mConnection.get();
            if (sQLiteConnection == null) {
                return;
            }
            sQLiteConnection.bindArguments(this, objArr);
        }

        public void detachCancellationSignal(CancellationSignal cancellationSignal) {
            SQLiteConnection sQLiteConnection = this.mConnection.get();
            if (sQLiteConnection == null) {
                return;
            }
            sQLiteConnection.detachCancellationSignal(cancellationSignal);
        }

        public void endOperation(String str) {
            SQLiteConnection sQLiteConnection;
            if (this.mOperation == null || (sQLiteConnection = this.mConnection.get()) == null) {
                return;
            }
            if (sQLiteConnection.mRecentOperations.endOperationDeferLog(this.mOperation.mCookie)) {
                sQLiteConnection.mRecentOperations.logOperation(this.mOperation.mCookie, str);
            }
            this.mOperation = null;
        }

        public void failOperation(Exception exc) {
            SQLiteConnection sQLiteConnection;
            if (this.mOperation == null || (sQLiteConnection = this.mConnection.get()) == null) {
                return;
            }
            sQLiteConnection.mRecentOperations.failOperation(this.mOperation.mCookie, exc);
        }

        public long getPtr() {
            return this.mStatementPtr;
        }

        public String getSQL() {
            return this.mSql;
        }

        public int getType() {
            return this.mType;
        }

        public boolean isReadOnly() {
            return this.mReadOnly;
        }

        public void reset(boolean z) {
            SQLiteConnection sQLiteConnection = this.mConnection.get();
            if (sQLiteConnection == null) {
                return;
            }
            sQLiteConnection.resetStatement(this, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public final class PreparedStatementCache extends LruCache<String, PreparedStatement> {
        public PreparedStatementCache(int i2) {
            super(i2);
        }

        public void dump(Printer printer) {
            printer.println("  Prepared statement cache:");
            Map<String, PreparedStatement> snapshot = snapshot();
            if (!snapshot.isEmpty()) {
                int i2 = 0;
                for (Map.Entry<String, PreparedStatement> entry : snapshot.entrySet()) {
                    PreparedStatement value = entry.getValue();
                    if (value.mInCache) {
                        printer.println("    " + i2 + ": statementPtr=0x" + Long.toHexString(value.getPtr()) + ", numParameters=" + value.mNumParameters + ", type=" + value.mType + ", readOnly=" + value.mReadOnly + ", sql=\"" + SQLiteConnection.trimSqlForDisplay(entry.getKey()) + "\"");
                    }
                    i2++;
                }
                return;
            }
            printer.println("    <none>");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.tencent.wcdb.support.LruCache
        public void entryRemoved(boolean z, String str, PreparedStatement preparedStatement, PreparedStatement preparedStatement2) {
            preparedStatement.mInCache = false;
            if (preparedStatement.mInUse) {
                return;
            }
            SQLiteConnection.this.finalizePreparedStatement(preparedStatement);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.tencent.wcdb.database.SQLiteConnection$1] */
    /* JADX WARN: Type inference failed for: r1v3 */
    private SQLiteConnection(SQLiteConnectionPool sQLiteConnectionPool, SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration, int i2, boolean z, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec) {
        this.mPassword = bArr;
        this.mCipher = sQLiteCipherSpec != null ? new SQLiteCipherSpec(sQLiteCipherSpec) : 0;
        this.mPool = sQLiteConnectionPool;
        SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration2 = new SQLiteDatabaseConfiguration(sQLiteDatabaseConfiguration);
        this.mConfiguration = sQLiteDatabaseConfiguration2;
        this.mConnectionId = i2;
        this.mIsPrimaryConnection = z;
        this.mIsReadOnlyConnection = (sQLiteDatabaseConfiguration.openFlags & 1) != 0;
        this.mPreparedStatementCache = new PreparedStatementCache(sQLiteDatabaseConfiguration2.maxSqlCacheSize);
    }

    private void applyBlockGuardPolicy(PreparedStatement preparedStatement) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attachCancellationSignal(CancellationSignal cancellationSignal) {
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
            int i2 = this.mCancellationSignalAttachCount + 1;
            this.mCancellationSignalAttachCount = i2;
            if (i2 == 1) {
                nativeResetCancel(this.mConnectionPtr, true);
                cancellationSignal.setOnCancelListener(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindArguments(PreparedStatement preparedStatement, Object[] objArr) {
        int length = objArr != null ? objArr.length : 0;
        if (length != preparedStatement.mNumParameters) {
            throw new SQLiteBindOrColumnIndexOutOfRangeException("Expected " + preparedStatement.mNumParameters + " bind arguments but " + length + " were provided.");
        } else if (length != 0) {
            long ptr = preparedStatement.getPtr();
            for (int i2 = 0; i2 < length; i2++) {
                Object obj = objArr[i2];
                int typeOfObject = DatabaseUtils.getTypeOfObject(obj);
                if (typeOfObject == 0) {
                    nativeBindNull(this.mConnectionPtr, ptr, i2 + 1);
                } else if (typeOfObject == 1) {
                    nativeBindLong(this.mConnectionPtr, ptr, i2 + 1, ((Number) obj).longValue());
                } else if (typeOfObject == 2) {
                    nativeBindDouble(this.mConnectionPtr, ptr, i2 + 1, ((Number) obj).doubleValue());
                } else if (typeOfObject != 4) {
                    if (obj instanceof Boolean) {
                        nativeBindLong(this.mConnectionPtr, ptr, i2 + 1, ((Boolean) obj).booleanValue() ? 1L : 0L);
                    } else {
                        nativeBindString(this.mConnectionPtr, ptr, i2 + 1, obj.toString());
                    }
                } else {
                    nativeBindBlob(this.mConnectionPtr, ptr, i2 + 1, (byte[]) obj);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void detachCancellationSignal(CancellationSignal cancellationSignal) {
        if (cancellationSignal != null) {
            int i2 = this.mCancellationSignalAttachCount - 1;
            this.mCancellationSignalAttachCount = i2;
            if (i2 == 0) {
                cancellationSignal.setOnCancelListener(null);
                nativeResetCancel(this.mConnectionPtr, false);
            }
        }
    }

    private void dispose(boolean z) {
        if (this.mConnectionPtr != 0) {
            int i2 = this.mRecentOperations.beginOperation("close", null, null).mCookie;
            try {
                this.mPreparedStatementCache.evictAll();
                nativeClose(this.mConnectionPtr);
                this.mConnectionPtr = 0L;
            } finally {
                this.mRecentOperations.endOperation(i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finalizePreparedStatement(PreparedStatement preparedStatement) {
        nativeFinalizeStatement(this.mConnectionPtr, preparedStatement.getPtr());
        recyclePreparedStatement(preparedStatement);
    }

    private SQLiteDebug.DbStats getMainDbStatsUnsafe(int i2, long j2, long j3) {
        String str = this.mConfiguration.path;
        if (!this.mIsPrimaryConnection) {
            str = str + " (" + this.mConnectionId + ")";
        }
        return new SQLiteDebug.DbStats(str, j2, j3, i2, this.mPreparedStatementCache.hitCount(), this.mPreparedStatementCache.missCount(), this.mPreparedStatementCache.size());
    }

    private static boolean isCacheable(int i2) {
        return i2 == 2 || i2 == 1;
    }

    private static native void nativeBindBlob(long j2, long j3, int i2, byte[] bArr);

    private static native void nativeBindDouble(long j2, long j3, int i2, double d);

    private static native void nativeBindLong(long j2, long j3, int i2, long j4);

    private static native void nativeBindNull(long j2, long j3, int i2);

    private static native void nativeBindString(long j2, long j3, int i2, String str);

    private static native void nativeCancel(long j2);

    private static native void nativeClose(long j2);

    private static native void nativeExecute(long j2, long j3);

    private static native int nativeExecuteForChangedRowCount(long j2, long j3);

    private static native long nativeExecuteForCursorWindow(long j2, long j3, long j4, int i2, int i3, boolean z);

    private static native long nativeExecuteForLastInsertedRowId(long j2, long j3);

    private static native long nativeExecuteForLong(long j2, long j3);

    private static native String nativeExecuteForString(long j2, long j3);

    private static native void nativeFinalizeStatement(long j2, long j3);

    private static native int nativeGetColumnCount(long j2, long j3);

    private static native String nativeGetColumnName(long j2, long j3, int i2);

    private static native int nativeGetDbLookaside(long j2);

    private static native int nativeGetParameterCount(long j2, long j3);

    private static native boolean nativeIsReadOnly(long j2, long j3);

    private native long nativeOpen(String str, int i2, String str2);

    private static native long nativePrepareStatement(long j2, String str);

    private static native void nativeRegisterCustomFunction(long j2, SQLiteCustomFunction sQLiteCustomFunction);

    private static native void nativeRegisterLocalizedCollators(long j2, String str);

    private static native void nativeResetCancel(long j2, boolean z);

    private static native void nativeResetStatement(long j2, long j3, boolean z);

    private static native long nativeSQLiteHandle(long j2, boolean z);

    private static native void nativeSetKey(long j2, byte[] bArr);

    private static native void nativeSetUpdateNotification(long j2, boolean z, boolean z2);

    private static native void nativeSetWalHook(long j2);

    private static native long nativeWalCheckpoint(long j2, String str);

    private void notifyChange(String str, String str2, long[] jArr, long[] jArr2, long[] jArr3) {
        this.mPool.notifyChanges(str, str2, jArr, jArr2, jArr3);
    }

    private void notifyCheckpoint(String str, int i2) {
        this.mPool.notifyCheckpoint(str, i2);
    }

    private PreparedStatement obtainPreparedStatement(String str, long j2, int i2, int i3, boolean z) {
        PreparedStatement preparedStatement = this.mPreparedStatementPool;
        if (preparedStatement != null) {
            this.mPreparedStatementPool = preparedStatement.mPoolNext;
            preparedStatement.mPoolNext = null;
            preparedStatement.mInCache = false;
        } else {
            preparedStatement = new PreparedStatement(this);
        }
        preparedStatement.mSql = str;
        preparedStatement.mStatementPtr = j2;
        preparedStatement.mNumParameters = i2;
        preparedStatement.mType = i3;
        preparedStatement.mReadOnly = z;
        return preparedStatement;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SQLiteConnection open(SQLiteConnectionPool sQLiteConnectionPool, SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration, int i2, boolean z, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec) {
        SQLiteConnection sQLiteConnection = new SQLiteConnection(sQLiteConnectionPool, sQLiteDatabaseConfiguration, i2, z, bArr, sQLiteCipherSpec);
        try {
            sQLiteConnection.open();
            return sQLiteConnection;
        } catch (SQLiteException e2) {
            SQLiteDebug.collectLastIOTraceStats(sQLiteConnection);
            sQLiteConnection.dispose(false);
            throw e2;
        }
    }

    private void recyclePreparedStatement(PreparedStatement preparedStatement) {
        preparedStatement.mSql = null;
        preparedStatement.mPoolNext = this.mPreparedStatementPool;
        this.mPreparedStatementPool = preparedStatement;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetStatement(PreparedStatement preparedStatement, boolean z) {
        nativeResetStatement(this.mConnectionPtr, preparedStatement.getPtr(), z);
    }

    private void setCheckpointStrategy() {
        if (this.mConfiguration.isInMemoryDb() || this.mIsReadOnlyConnection) {
            return;
        }
        if (this.mConfiguration.customWALHookEnabled) {
            nativeSetWalHook(this.mConnectionPtr);
        } else if (executeForLong("PRAGMA wal_autocheckpoint", null, null) != 100) {
            executeForLong("PRAGMA wal_autocheckpoint=100", null, null);
        }
    }

    private void setCipherSpec() {
        SQLiteCipherSpec sQLiteCipherSpec = this.mCipher;
        if (sQLiteCipherSpec != null) {
            if (sQLiteCipherSpec.cipher != null) {
                execute("PRAGMA cipher=" + DatabaseUtils.sqlEscapeString(this.mCipher.cipher), null, null);
            }
            if (this.mCipher.kdfIteration != 0) {
                execute("PRAGMA kdf_iter=" + this.mCipher.kdfIteration, null, null);
            }
            execute("PRAGMA cipher_use_hmac=" + this.mCipher.hmacEnabled, null, null);
        }
    }

    private void setForeignKeyModeFromConfiguration() {
        if (this.mIsReadOnlyConnection) {
            return;
        }
        long j2 = this.mConfiguration.foreignKeyConstraintsEnabled ? 1L : 0L;
        if (executeForLong("PRAGMA foreign_keys", null, null) != j2) {
            execute("PRAGMA foreign_keys=" + j2, null, null);
        }
    }

    private void setJournalMode(String str) {
        String executeForString = executeForString("PRAGMA journal_mode", null, null);
        if (executeForString.equalsIgnoreCase(str)) {
            return;
        }
        try {
            if (executeForString("PRAGMA journal_mode=" + str, null, null).equalsIgnoreCase(str)) {
                return;
            }
        } catch (SQLiteDatabaseLockedException unused) {
        }
        Log.w(TAG, "Could not change the database journal mode of '" + this.mConfiguration.label + "' from '" + executeForString + "' to '" + str + "' because the database is locked.  This usually means that there are other open connections to the database which prevents the database from enabling or disabling write-ahead logging mode.  Proceeding without changing the journal mode.");
    }

    private void setJournalSizeLimit() {
        if (this.mConfiguration.isInMemoryDb() || this.mIsReadOnlyConnection || executeForLong("PRAGMA journal_size_limit", null, null) == 524288) {
            return;
        }
        executeForLong("PRAGMA journal_size_limit=524288", null, null);
    }

    private void setLocaleFromConfiguration() {
        SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration = this.mConfiguration;
        int i2 = sQLiteDatabaseConfiguration.openFlags | 16;
        sQLiteDatabaseConfiguration.openFlags = i2;
        if ((i2 & 16) != 0) {
            return;
        }
        String locale = sQLiteDatabaseConfiguration.locale.toString();
        nativeRegisterLocalizedCollators(this.mConnectionPtr, locale);
        if (this.mIsReadOnlyConnection) {
            return;
        }
        try {
            execute("CREATE TABLE IF NOT EXISTS android_metadata (locale TEXT)", null, null);
            String executeForString = executeForString("SELECT locale FROM android_metadata UNION SELECT NULL ORDER BY locale DESC LIMIT 1", null, null);
            if (executeForString == null || !executeForString.equals(locale)) {
                execute("BEGIN", null, null);
                execute("DELETE FROM android_metadata", null, null);
                execute("INSERT INTO android_metadata (locale) VALUES(?)", new Object[]{locale}, null);
                execute("REINDEX LOCALIZED", null, null);
                execute("COMMIT", null, null);
            }
        } catch (RuntimeException e2) {
            throw new SQLiteException("Failed to change locale for db '" + this.mConfiguration.label + "' to '" + locale + "'.", e2);
        }
    }

    private void setPageSize() {
        long j2;
        String str;
        int i2;
        if (this.mConfiguration.isInMemoryDb()) {
            return;
        }
        if (this.mPassword != null) {
            SQLiteCipherSpec sQLiteCipherSpec = this.mCipher;
            if (sQLiteCipherSpec == null || (i2 = sQLiteCipherSpec.pageSize) <= 0) {
                i2 = SQLiteGlobal.defaultPageSize;
            }
            j2 = i2;
            str = "PRAGMA cipher_page_size";
        } else {
            j2 = SQLiteGlobal.defaultPageSize;
            str = "PRAGMA page_size";
        }
        if (executeForLong(str, null, null) != j2) {
            execute(str + ContainerUtils.KEY_VALUE_DELIMITER + j2, null, null);
        }
    }

    private void setReadOnlyFromConfiguration() {
        if (this.mIsReadOnlyConnection) {
            execute("PRAGMA query_only = 1", null, null);
        }
    }

    private void setSyncModeFromConfiguration() {
        execute("PRAGMA synchronous=" + this.mConfiguration.synchronousMode, null, null);
    }

    private void setUpdateNotificationFromConfiguration() {
        long j2 = this.mConnectionPtr;
        SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration = this.mConfiguration;
        nativeSetUpdateNotification(j2, sQLiteDatabaseConfiguration.updateNotificationEnabled, sQLiteDatabaseConfiguration.updateNotificationRowID);
    }

    private void setWalModeFromConfiguration() {
        if (this.mConfiguration.isInMemoryDb() || this.mIsReadOnlyConnection) {
            return;
        }
        setJournalMode((this.mConfiguration.openFlags & SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING) != 0 ? "WAL" : SQLiteGlobal.defaultJournalMode);
    }

    private void throwIfStatementForbidden(PreparedStatement preparedStatement) {
        if (this.mOnlyAllowReadOnlyOperations && !preparedStatement.mReadOnly) {
            throw new SQLiteException("Cannot execute this statement because it might modify the database but the connection is read-only.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String trimSqlForDisplay(String str) {
        return TRIM_SQL_PATTERN.matcher(str).replaceAll(LangUtils.SINGLE_SPACE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PreparedStatement acquirePreparedStatement(String str) {
        boolean z;
        PreparedStatement preparedStatement = this.mPreparedStatementCache.get(str);
        if (preparedStatement == null) {
            z = false;
        } else if (!preparedStatement.mInUse) {
            preparedStatement.mInUse = true;
            return preparedStatement;
        } else {
            z = true;
        }
        long nativePrepareStatement = nativePrepareStatement(this.mConnectionPtr, str);
        try {
            int nativeGetParameterCount = nativeGetParameterCount(this.mConnectionPtr, nativePrepareStatement);
            int sqlStatementType = DatabaseUtils.getSqlStatementType(str);
            preparedStatement = obtainPreparedStatement(str, nativePrepareStatement, nativeGetParameterCount, sqlStatementType, nativeIsReadOnly(this.mConnectionPtr, nativePrepareStatement));
            if (!z && isCacheable(sqlStatementType)) {
                this.mPreparedStatementCache.put(str, preparedStatement);
                preparedStatement.mInCache = true;
            }
            preparedStatement.mInUse = true;
            return preparedStatement;
        } catch (RuntimeException e2) {
            if (preparedStatement == null || !preparedStatement.mInCache) {
                nativeFinalizeStatement(this.mConnectionPtr, nativePrepareStatement);
            }
            throw e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void close() {
        dispose(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0043 A[Catch: all -> 0x00c8, SQLiteException -> 0x00cd, TRY_LEAVE, TryCatch #1 {SQLiteException -> 0x00cd, blocks: (B:9:0x002e, B:10:0x003d, B:12:0x0043, B:18:0x0086, B:20:0x009d, B:21:0x00b1), top: B:33:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x009d A[Catch: all -> 0x00c8, SQLiteException -> 0x00cd, TryCatch #1 {SQLiteException -> 0x00cd, blocks: (B:9:0x002e, B:10:0x003d, B:12:0x0043, B:18:0x0086, B:20:0x009d, B:21:0x00b1), top: B:33:0x002e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void collectDbStats(java.util.ArrayList<com.tencent.wcdb.database.SQLiteDebug.DbStats> r26) {
        /*
            r25 = this;
            r9 = r25
            r0 = r26
            java.lang.String r10 = "PRAGMA "
            long r1 = r9.mConnectionPtr
            int r2 = nativeGetDbLookaside(r1)
            r11 = 0
            r13 = 0
            java.lang.String r1 = "PRAGMA page_count;"
            long r3 = r9.executeForLong(r1, r13, r13)     // Catch: com.tencent.wcdb.database.SQLiteException -> L1c
            java.lang.String r1 = "PRAGMA page_size;"
            long r5 = r9.executeForLong(r1, r13, r13)     // Catch: com.tencent.wcdb.database.SQLiteException -> L1d
            goto L1e
        L1c:
            r3 = r11
        L1d:
            r5 = r11
        L1e:
            r1 = r25
            com.tencent.wcdb.database.SQLiteDebug$DbStats r1 = r1.getMainDbStatsUnsafe(r2, r3, r5)
            r0.add(r1)
            com.tencent.wcdb.CursorWindow r14 = new com.tencent.wcdb.CursorWindow
            java.lang.String r1 = "collectDbStats"
            r14.<init>(r1)
            java.lang.String r2 = "PRAGMA database_list;"
            r3 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r25
            r4 = r14
            r1.executeForCursorWindow(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            r1 = 1
            r2 = 1
        L3d:
            int r3 = r14.getNumRows()     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            if (r2 >= r3) goto Lcd
            java.lang.String r3 = r14.getString(r2, r1)     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            r4 = 2
            java.lang.String r4 = r14.getString(r2, r4)     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: com.tencent.wcdb.database.SQLiteException -> L81 java.lang.Throwable -> Lc8
            r5.<init>()     // Catch: com.tencent.wcdb.database.SQLiteException -> L81 java.lang.Throwable -> Lc8
            r5.append(r10)     // Catch: com.tencent.wcdb.database.SQLiteException -> L81 java.lang.Throwable -> Lc8
            r5.append(r3)     // Catch: com.tencent.wcdb.database.SQLiteException -> L81 java.lang.Throwable -> Lc8
            java.lang.String r6 = ".page_count;"
            r5.append(r6)     // Catch: com.tencent.wcdb.database.SQLiteException -> L81 java.lang.Throwable -> Lc8
            java.lang.String r5 = r5.toString()     // Catch: com.tencent.wcdb.database.SQLiteException -> L81 java.lang.Throwable -> Lc8
            long r5 = r9.executeForLong(r5, r13, r13)     // Catch: com.tencent.wcdb.database.SQLiteException -> L81 java.lang.Throwable -> Lc8
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: com.tencent.wcdb.database.SQLiteException -> L82 java.lang.Throwable -> Lc8
            r7.<init>()     // Catch: com.tencent.wcdb.database.SQLiteException -> L82 java.lang.Throwable -> Lc8
            r7.append(r10)     // Catch: com.tencent.wcdb.database.SQLiteException -> L82 java.lang.Throwable -> Lc8
            r7.append(r3)     // Catch: com.tencent.wcdb.database.SQLiteException -> L82 java.lang.Throwable -> Lc8
            java.lang.String r8 = ".page_size;"
            r7.append(r8)     // Catch: com.tencent.wcdb.database.SQLiteException -> L82 java.lang.Throwable -> Lc8
            java.lang.String r7 = r7.toString()     // Catch: com.tencent.wcdb.database.SQLiteException -> L82 java.lang.Throwable -> Lc8
            long r7 = r9.executeForLong(r7, r13, r13)     // Catch: com.tencent.wcdb.database.SQLiteException -> L82 java.lang.Throwable -> Lc8
            r17 = r5
            r19 = r7
            goto L86
        L81:
            r5 = r11
        L82:
            r17 = r5
            r19 = r11
        L86:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            r5.<init>()     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            java.lang.String r6 = "  (attached) "
            r5.append(r6)     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            r5.append(r3)     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            java.lang.String r3 = r5.toString()     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            int r5 = r4.length()     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            if (r5 == 0) goto Lb1
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            r5.<init>()     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            r5.append(r3)     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            java.lang.String r3 = ": "
            r5.append(r3)     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            r5.append(r4)     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            java.lang.String r3 = r5.toString()     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
        Lb1:
            r16 = r3
            com.tencent.wcdb.database.SQLiteDebug$DbStats r3 = new com.tencent.wcdb.database.SQLiteDebug$DbStats     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r15 = r3
            r15.<init>(r16, r17, r19, r21, r22, r23, r24)     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            r0.add(r3)     // Catch: java.lang.Throwable -> Lc8 com.tencent.wcdb.database.SQLiteException -> Lcd
            int r2 = r2 + 1
            goto L3d
        Lc8:
            r0 = move-exception
            r14.close()
            throw r0
        Lcd:
            r14.close()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wcdb.database.SQLiteConnection.collectDbStats(java.util.ArrayList):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void collectDbStatsUnsafe(ArrayList<SQLiteDebug.DbStats> arrayList) {
        arrayList.add(getMainDbStatsUnsafe(0, 0L, 0L));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String describeCurrentOperationUnsafe() {
        return this.mRecentOperations.describeCurrentOperation();
    }

    public void dump(Printer printer, boolean z) {
        dumpUnsafe(printer, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dumpUnsafe(Printer printer, boolean z) {
        printer.println("Connection #" + this.mConnectionId + ":");
        if (z) {
            printer.println("  connectionPtr: 0x" + Long.toHexString(this.mConnectionPtr));
        }
        printer.println("  isPrimaryConnection: " + this.mIsPrimaryConnection);
        printer.println("  onlyAllowReadOnlyOperations: " + this.mOnlyAllowReadOnlyOperations);
        if (this.mAcquiredThread != null) {
            printer.println("  acquiredThread: " + this.mAcquiredThread + " (tid: " + this.mAcquiredTid + ")");
        }
        this.mRecentOperations.dump(printer, z);
        if (z) {
            this.mPreparedStatementCache.dump(printer);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void endNativeHandle(Exception exc) {
        int i2 = this.mNativeHandleCount - 1;
        this.mNativeHandleCount = i2;
        if (i2 != 0 || this.mNativeOperation == null) {
            return;
        }
        nativeSQLiteHandle(this.mConnectionPtr, false);
        if (exc == null) {
            this.mRecentOperations.endOperationDeferLog(this.mNativeOperation.mCookie);
        } else {
            this.mRecentOperations.failOperation(this.mNativeOperation.mCookie, exc);
        }
        this.mNativeOperation = null;
    }

    public void execute(String str, Object[] objArr, CancellationSignal cancellationSignal) {
        SQLiteConnectionPool sQLiteConnectionPool;
        if (str != null) {
            Operation beginOperation = this.mRecentOperations.beginOperation("execute", str, objArr);
            int i2 = beginOperation.mCookie;
            try {
                try {
                    PreparedStatement acquirePreparedStatement = acquirePreparedStatement(str);
                    beginOperation.mType = acquirePreparedStatement.mType;
                    try {
                        throwIfStatementForbidden(acquirePreparedStatement);
                        bindArguments(acquirePreparedStatement, objArr);
                        applyBlockGuardPolicy(acquirePreparedStatement);
                        attachCancellationSignal(cancellationSignal);
                        nativeExecute(this.mConnectionPtr, acquirePreparedStatement.getPtr());
                        detachCancellationSignal(cancellationSignal);
                        return;
                    } finally {
                        releasePreparedStatement(acquirePreparedStatement);
                    }
                } catch (RuntimeException e2) {
                    if (((e2 instanceof SQLiteDatabaseLockedException) || (e2 instanceof SQLiteTableLockedException)) && (sQLiteConnectionPool = this.mPool) != null) {
                        sQLiteConnectionPool.logConnectionPoolBusy(str);
                    }
                    this.mRecentOperations.failOperation(i2, e2);
                    throw e2;
                }
            } finally {
                this.mRecentOperations.endOperation(i2);
            }
        }
        throw new IllegalArgumentException("sql must not be null.");
    }

    public int executeForChangedRowCount(String str, Object[] objArr, CancellationSignal cancellationSignal) {
        SQLiteConnectionPool sQLiteConnectionPool;
        if (str != null) {
            Operation beginOperation = this.mRecentOperations.beginOperation("executeForChangedRowCount", str, objArr);
            int i2 = beginOperation.mCookie;
            try {
                try {
                    PreparedStatement acquirePreparedStatement = acquirePreparedStatement(str);
                    beginOperation.mType = acquirePreparedStatement.mType;
                    try {
                        throwIfStatementForbidden(acquirePreparedStatement);
                        bindArguments(acquirePreparedStatement, objArr);
                        applyBlockGuardPolicy(acquirePreparedStatement);
                        attachCancellationSignal(cancellationSignal);
                        int nativeExecuteForChangedRowCount = nativeExecuteForChangedRowCount(this.mConnectionPtr, acquirePreparedStatement.getPtr());
                        detachCancellationSignal(cancellationSignal);
                        if (this.mRecentOperations.endOperationDeferLog(i2)) {
                            this.mRecentOperations.logOperation(i2, "changedRows=" + nativeExecuteForChangedRowCount);
                        }
                        return nativeExecuteForChangedRowCount;
                    } finally {
                        releasePreparedStatement(acquirePreparedStatement);
                    }
                } catch (RuntimeException e2) {
                    if (((e2 instanceof SQLiteDatabaseLockedException) || (e2 instanceof SQLiteTableLockedException)) && (sQLiteConnectionPool = this.mPool) != null) {
                        sQLiteConnectionPool.logConnectionPoolBusy(str);
                    }
                    this.mRecentOperations.failOperation(i2, e2);
                    throw e2;
                }
            } catch (Throwable th) {
                if (this.mRecentOperations.endOperationDeferLog(i2)) {
                    this.mRecentOperations.logOperation(i2, "changedRows=0");
                }
                throw th;
            }
        }
        throw new IllegalArgumentException("sql must not be null.");
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0185 A[Catch: all -> 0x01b2, TryCatch #6 {all -> 0x01b2, blocks: (B:6:0x001d, B:20:0x0077, B:22:0x007f, B:67:0x017d, B:69:0x0185, B:70:0x01b1), top: B:86:0x001d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int executeForCursorWindow(java.lang.String r26, java.lang.Object[] r27, com.tencent.wcdb.CursorWindow r28, int r29, int r30, boolean r31, com.tencent.wcdb.support.CancellationSignal r32) {
        /*
            Method dump skipped, instructions count: 455
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wcdb.database.SQLiteConnection.executeForCursorWindow(java.lang.String, java.lang.Object[], com.tencent.wcdb.CursorWindow, int, int, boolean, com.tencent.wcdb.support.CancellationSignal):int");
    }

    public long executeForLastInsertedRowId(String str, Object[] objArr, CancellationSignal cancellationSignal) {
        SQLiteConnectionPool sQLiteConnectionPool;
        if (str != null) {
            Operation beginOperation = this.mRecentOperations.beginOperation("executeForLastInsertedRowId", str, objArr);
            int i2 = beginOperation.mCookie;
            try {
                try {
                    PreparedStatement acquirePreparedStatement = acquirePreparedStatement(str);
                    beginOperation.mType = acquirePreparedStatement.mType;
                    try {
                        throwIfStatementForbidden(acquirePreparedStatement);
                        bindArguments(acquirePreparedStatement, objArr);
                        applyBlockGuardPolicy(acquirePreparedStatement);
                        attachCancellationSignal(cancellationSignal);
                        long nativeExecuteForLastInsertedRowId = nativeExecuteForLastInsertedRowId(this.mConnectionPtr, acquirePreparedStatement.getPtr());
                        detachCancellationSignal(cancellationSignal);
                        return nativeExecuteForLastInsertedRowId;
                    } finally {
                        releasePreparedStatement(acquirePreparedStatement);
                    }
                } catch (RuntimeException e2) {
                    if (((e2 instanceof SQLiteDatabaseLockedException) || (e2 instanceof SQLiteTableLockedException)) && (sQLiteConnectionPool = this.mPool) != null) {
                        sQLiteConnectionPool.logConnectionPoolBusy(str);
                    }
                    this.mRecentOperations.failOperation(i2, e2);
                    throw e2;
                }
            } finally {
                this.mRecentOperations.endOperation(i2);
            }
        }
        throw new IllegalArgumentException("sql must not be null.");
    }

    public long executeForLong(String str, Object[] objArr, CancellationSignal cancellationSignal) {
        SQLiteConnectionPool sQLiteConnectionPool;
        if (str != null) {
            Operation beginOperation = this.mRecentOperations.beginOperation("executeForLong", str, objArr);
            int i2 = beginOperation.mCookie;
            try {
                try {
                    PreparedStatement acquirePreparedStatement = acquirePreparedStatement(str);
                    beginOperation.mType = acquirePreparedStatement.mType;
                    try {
                        throwIfStatementForbidden(acquirePreparedStatement);
                        bindArguments(acquirePreparedStatement, objArr);
                        applyBlockGuardPolicy(acquirePreparedStatement);
                        attachCancellationSignal(cancellationSignal);
                        long nativeExecuteForLong = nativeExecuteForLong(this.mConnectionPtr, acquirePreparedStatement.getPtr());
                        detachCancellationSignal(cancellationSignal);
                        return nativeExecuteForLong;
                    } finally {
                        releasePreparedStatement(acquirePreparedStatement);
                    }
                } catch (RuntimeException e2) {
                    if (((e2 instanceof SQLiteDatabaseLockedException) || (e2 instanceof SQLiteTableLockedException)) && (sQLiteConnectionPool = this.mPool) != null) {
                        sQLiteConnectionPool.logConnectionPoolBusy(str);
                    }
                    this.mRecentOperations.failOperation(i2, e2);
                    throw e2;
                }
            } finally {
                this.mRecentOperations.endOperation(i2);
            }
        }
        throw new IllegalArgumentException("sql must not be null.");
    }

    public String executeForString(String str, Object[] objArr, CancellationSignal cancellationSignal) {
        SQLiteConnectionPool sQLiteConnectionPool;
        if (str != null) {
            Operation beginOperation = this.mRecentOperations.beginOperation("executeForString", str, objArr);
            int i2 = beginOperation.mCookie;
            try {
                try {
                    PreparedStatement acquirePreparedStatement = acquirePreparedStatement(str);
                    beginOperation.mType = acquirePreparedStatement.mType;
                    try {
                        throwIfStatementForbidden(acquirePreparedStatement);
                        bindArguments(acquirePreparedStatement, objArr);
                        applyBlockGuardPolicy(acquirePreparedStatement);
                        attachCancellationSignal(cancellationSignal);
                        String nativeExecuteForString = nativeExecuteForString(this.mConnectionPtr, acquirePreparedStatement.getPtr());
                        detachCancellationSignal(cancellationSignal);
                        return nativeExecuteForString;
                    } finally {
                        releasePreparedStatement(acquirePreparedStatement);
                    }
                } catch (RuntimeException e2) {
                    if (((e2 instanceof SQLiteDatabaseLockedException) || (e2 instanceof SQLiteTableLockedException)) && (sQLiteConnectionPool = this.mPool) != null) {
                        sQLiteConnectionPool.logConnectionPoolBusy(str);
                    }
                    this.mRecentOperations.failOperation(i2, e2);
                    throw e2;
                }
            } finally {
                this.mRecentOperations.endOperation(i2);
            }
        }
        throw new IllegalArgumentException("sql must not be null.");
    }

    protected void finalize() throws Throwable {
        try {
            SQLiteConnectionPool sQLiteConnectionPool = this.mPool;
            if (sQLiteConnectionPool != null && this.mConnectionPtr != 0) {
                sQLiteConnectionPool.onConnectionLeaked();
            }
            dispose(true);
        } finally {
            super.finalize();
        }
    }

    public int getConnectionId() {
        return this.mConnectionId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getNativeHandle(String str) {
        if (this.mConnectionPtr == 0) {
            return 0L;
        }
        if (str != null && this.mNativeOperation == null) {
            Operation beginOperation = this.mRecentOperations.beginOperation(str, null, null);
            this.mNativeOperation = beginOperation;
            beginOperation.mType = 99;
        }
        this.mNativeHandleCount++;
        return nativeSQLiteHandle(this.mConnectionPtr, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isPreparedStatementInCache(String str) {
        return this.mPreparedStatementCache.get(str) != null;
    }

    public boolean isPrimaryConnection() {
        return this.mIsPrimaryConnection;
    }

    @Override // com.tencent.wcdb.support.CancellationSignal.OnCancelListener
    public void onCancel() {
        nativeCancel(this.mConnectionPtr);
    }

    public void prepare(String str, SQLiteStatementInfo sQLiteStatementInfo) {
        SQLiteConnectionPool sQLiteConnectionPool;
        if (str != null) {
            Operation beginOperation = this.mRecentOperations.beginOperation(JDReactConstant.PREPARE, str, null);
            int i2 = beginOperation.mCookie;
            try {
                try {
                    PreparedStatement acquirePreparedStatement = acquirePreparedStatement(str);
                    beginOperation.mType = acquirePreparedStatement.mType;
                    if (sQLiteStatementInfo != null) {
                        try {
                            sQLiteStatementInfo.numParameters = acquirePreparedStatement.mNumParameters;
                            sQLiteStatementInfo.readOnly = acquirePreparedStatement.mReadOnly;
                            int nativeGetColumnCount = nativeGetColumnCount(this.mConnectionPtr, acquirePreparedStatement.getPtr());
                            if (nativeGetColumnCount == 0) {
                                sQLiteStatementInfo.columnNames = EMPTY_STRING_ARRAY;
                            } else {
                                sQLiteStatementInfo.columnNames = new String[nativeGetColumnCount];
                                for (int i3 = 0; i3 < nativeGetColumnCount; i3++) {
                                    sQLiteStatementInfo.columnNames[i3] = nativeGetColumnName(this.mConnectionPtr, acquirePreparedStatement.getPtr(), i3);
                                }
                            }
                        } finally {
                            releasePreparedStatement(acquirePreparedStatement);
                        }
                    }
                    return;
                } catch (RuntimeException e2) {
                    if (((e2 instanceof SQLiteDatabaseLockedException) || (e2 instanceof SQLiteTableLockedException)) && (sQLiteConnectionPool = this.mPool) != null) {
                        sQLiteConnectionPool.logConnectionPoolBusy(str);
                    }
                    this.mRecentOperations.failOperation(i2, e2);
                    throw e2;
                }
            } finally {
                this.mRecentOperations.endOperation(i2);
            }
        }
        throw new IllegalArgumentException("sql must not be null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reconfigure(SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration) {
        this.mOnlyAllowReadOnlyOperations = false;
        int size = sQLiteDatabaseConfiguration.customFunctions.size();
        for (int i2 = 0; i2 < size; i2++) {
            SQLiteCustomFunction sQLiteCustomFunction = sQLiteDatabaseConfiguration.customFunctions.get(i2);
            if (!this.mConfiguration.customFunctions.contains(sQLiteCustomFunction)) {
                nativeRegisterCustomFunction(this.mConnectionPtr, sQLiteCustomFunction);
            }
        }
        int i3 = sQLiteDatabaseConfiguration.openFlags;
        SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration2 = this.mConfiguration;
        boolean z = ((i3 ^ sQLiteDatabaseConfiguration2.openFlags) & SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING) != 0;
        boolean z2 = sQLiteDatabaseConfiguration.foreignKeyConstraintsEnabled != sQLiteDatabaseConfiguration2.foreignKeyConstraintsEnabled;
        boolean z3 = !sQLiteDatabaseConfiguration.locale.equals(sQLiteDatabaseConfiguration2.locale);
        boolean z4 = sQLiteDatabaseConfiguration.customWALHookEnabled;
        SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration3 = this.mConfiguration;
        boolean z5 = z4 != sQLiteDatabaseConfiguration3.customWALHookEnabled;
        boolean z6 = sQLiteDatabaseConfiguration.synchronousMode != sQLiteDatabaseConfiguration3.synchronousMode;
        boolean z7 = (sQLiteDatabaseConfiguration.updateNotificationEnabled == sQLiteDatabaseConfiguration3.updateNotificationEnabled && sQLiteDatabaseConfiguration.updateNotificationRowID == sQLiteDatabaseConfiguration3.updateNotificationRowID) ? false : true;
        sQLiteDatabaseConfiguration3.updateParametersFrom(sQLiteDatabaseConfiguration);
        this.mPreparedStatementCache.resize(sQLiteDatabaseConfiguration.maxSqlCacheSize);
        if (z2) {
            setForeignKeyModeFromConfiguration();
        }
        if (z) {
            setWalModeFromConfiguration();
        }
        if (z6) {
            setSyncModeFromConfiguration();
        }
        if (z5) {
            setCheckpointStrategy();
        }
        if (z3) {
            setLocaleFromConfiguration();
        }
        if (z7) {
            setUpdateNotificationFromConfiguration();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releasePreparedStatement(PreparedStatement preparedStatement) {
        preparedStatement.mInUse = false;
        if (preparedStatement.mInCache) {
            try {
                resetStatement(preparedStatement, true);
                return;
            } catch (SQLiteException unused) {
                this.mPreparedStatementCache.remove(preparedStatement.mSql);
                return;
            }
        }
        finalizePreparedStatement(preparedStatement);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAcquisitionState(Thread thread, int i2) {
        this.mAcquiredThread = thread;
        this.mAcquiredTid = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnlyAllowReadOnlyOperations(boolean z) {
        this.mOnlyAllowReadOnlyOperations = z;
    }

    public String toString() {
        return "SQLiteConnection: " + this.mConfiguration.path + " (" + this.mConnectionId + ")";
    }

    public Pair<Integer, Integer> walCheckpoint(String str) {
        if (str == null || str.isEmpty()) {
            str = "main";
        }
        long nativeWalCheckpoint = nativeWalCheckpoint(this.mConnectionPtr, str);
        return new Pair<>(Integer.valueOf((int) (nativeWalCheckpoint >> 32)), Integer.valueOf((int) (nativeWalCheckpoint & 4294967295L)));
    }

    private void open() {
        SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration = this.mConfiguration;
        long nativeOpen = nativeOpen(sQLiteDatabaseConfiguration.path, sQLiteDatabaseConfiguration.openFlags, sQLiteDatabaseConfiguration.vfsName);
        this.mConnectionPtr = nativeOpen;
        byte[] bArr = this.mPassword;
        if (bArr != null && bArr.length == 0) {
            this.mPassword = null;
        }
        byte[] bArr2 = this.mPassword;
        if (bArr2 != null) {
            nativeSetKey(nativeOpen, bArr2);
            setCipherSpec();
        }
        setPageSize();
        setReadOnlyFromConfiguration();
        setForeignKeyModeFromConfiguration();
        setWalModeFromConfiguration();
        setSyncModeFromConfiguration();
        setJournalSizeLimit();
        setCheckpointStrategy();
        setLocaleFromConfiguration();
        setUpdateNotificationFromConfiguration();
        int size = this.mConfiguration.customFunctions.size();
        for (int i2 = 0; i2 < size; i2++) {
            nativeRegisterCustomFunction(this.mConnectionPtr, this.mConfiguration.customFunctions.get(i2));
        }
    }
}
