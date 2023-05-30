package com.facebook.react.modules.storage;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.common.ModuleDataCleaner;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.concurrent.Executor;

@ReactModule(name = AsyncStorageModule.NAME)
/* loaded from: classes12.dex */
public final class AsyncStorageModule extends ReactContextBaseJavaModule implements ModuleDataCleaner.Cleanable {
    private static final int MAX_SQL_KEYS = 999;
    public static final String NAME = "AsyncSQLiteDBStorage";
    private final SerialExecutor executor;
    private ReactDatabaseSupplier mReactDatabaseSupplier;
    private boolean mShuttingDown;

    /* loaded from: classes12.dex */
    private class SerialExecutor implements Executor {
        private final Executor executor;
        private Runnable mActive;
        private final ArrayDeque<Runnable> mTasks = new ArrayDeque<>();

        SerialExecutor(Executor executor) {
            this.executor = executor;
        }

        @Override // java.util.concurrent.Executor
        public synchronized void execute(final Runnable runnable) {
            this.mTasks.offer(new Runnable() { // from class: com.facebook.react.modules.storage.AsyncStorageModule.SerialExecutor.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        SerialExecutor.this.scheduleNext();
                    }
                }
            });
            if (this.mActive == null) {
                scheduleNext();
            }
        }

        synchronized void scheduleNext() {
            Runnable poll = this.mTasks.poll();
            this.mActive = poll;
            if (poll != null) {
                this.executor.execute(poll);
            }
        }
    }

    public AsyncStorageModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ensureDatabase() {
        return !this.mShuttingDown && this.mReactDatabaseSupplier.ensureDatabase();
    }

    @ReactMethod
    public void clear(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.storage.AsyncStorageModule.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                if (AsyncStorageModule.this.mReactDatabaseSupplier.ensureDatabase()) {
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.clear();
                        callback.invoke(new Object[0]);
                        return;
                    } catch (Exception e2) {
                        FLog.w(ReactConstants.TAG, e2.getMessage(), e2);
                        callback.invoke(AsyncStorageErrorUtil.getError(null, e2.getMessage()));
                        return;
                    }
                }
                callback.invoke(AsyncStorageErrorUtil.getDBError(null));
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    @Override // com.facebook.react.modules.common.ModuleDataCleaner.Cleanable
    public void clearSensitiveData() {
        this.mReactDatabaseSupplier.clearAndCloseDatabase();
    }

    @ReactMethod
    public void getAllKeys(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.storage.AsyncStorageModule.6
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Code restructure failed: missing block: B:10:0x004c, code lost:
                if (r4.moveToNext() != false) goto L26;
             */
            /* JADX WARN: Code restructure failed: missing block: B:11:0x004e, code lost:
                r4.close();
                r3.invoke(null, r13);
             */
            /* JADX WARN: Code restructure failed: missing block: B:12:0x005c, code lost:
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:8:0x003f, code lost:
                if (r4.moveToFirst() != false) goto L9;
             */
            /* JADX WARN: Code restructure failed: missing block: B:9:0x0041, code lost:
                r13.pushString(r4.getString(0));
             */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void doInBackgroundGuarded(Void... voidArr) {
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null), null);
                    return;
                }
                WritableArray createArray = Arguments.createArray();
                Cursor query = AsyncStorageModule.this.mReactDatabaseSupplier.get().query("catalystLocalStorage", new String[]{"key"}, null, null, null, null, null);
                try {
                    try {
                    } catch (Exception e2) {
                        FLog.w(ReactConstants.TAG, e2.getMessage(), e2);
                        callback.invoke(AsyncStorageErrorUtil.getError(null, e2.getMessage()), null);
                        query.close();
                    }
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        super.initialize();
        this.mShuttingDown = false;
    }

    @ReactMethod
    public void multiGet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray == null) {
            callback.invoke(AsyncStorageErrorUtil.getInvalidKeyError(null), null);
        } else {
            new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.storage.AsyncStorageModule.1
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Code restructure failed: missing block: B:17:0x0093, code lost:
                    if (r7.moveToFirst() != false) goto L18;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:18:0x0095, code lost:
                    r8 = com.facebook.react.bridge.Arguments.createArray();
                    r8.pushString(r7.getString(0));
                    r8.pushString(r7.getString(1));
                    r15.pushArray(r8);
                    r6.remove(r7.getString(0));
                 */
                /* JADX WARN: Code restructure failed: missing block: B:19:0x00b5, code lost:
                    if (r7.moveToNext() != false) goto L41;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:20:0x00b7, code lost:
                    r7.close();
                    r7 = r6.iterator();
                 */
                /* JADX WARN: Code restructure failed: missing block: B:22:0x00c2, code lost:
                    if (r7.hasNext() == false) goto L42;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:23:0x00c4, code lost:
                    r9 = com.facebook.react.bridge.Arguments.createArray();
                    r9.pushString((java.lang.String) r7.next());
                    r9.pushNull();
                    r15.pushArray(r9);
                 */
                /* JADX WARN: Code restructure failed: missing block: B:24:0x00d8, code lost:
                    r6.clear();
                    r14 = r3 + 999;
                 */
                @Override // com.facebook.react.bridge.GuardedAsyncTask
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public void doInBackgroundGuarded(Void... voidArr) {
                    if (!AsyncStorageModule.this.ensureDatabase()) {
                        callback.invoke(AsyncStorageErrorUtil.getDBError(null), null);
                        return;
                    }
                    String[] strArr = {"key", "value"};
                    HashSet hashSet = new HashSet();
                    WritableArray createArray = Arguments.createArray();
                    int i2 = 0;
                    while (i2 < readableArray.size()) {
                        int min = Math.min(readableArray.size() - i2, 999);
                        int i3 = i2;
                        Cursor query = AsyncStorageModule.this.mReactDatabaseSupplier.get().query("catalystLocalStorage", strArr, AsyncLocalStorageUtil.buildKeySelection(min), AsyncLocalStorageUtil.buildKeySelectionArgs(readableArray, i2, min), null, null, null);
                        hashSet.clear();
                        try {
                            try {
                                if (query.getCount() != readableArray.size()) {
                                    for (int i4 = i3; i4 < i3 + min; i4++) {
                                        hashSet.add(readableArray.getString(i4));
                                    }
                                }
                            } catch (Exception e2) {
                                FLog.w(ReactConstants.TAG, e2.getMessage(), e2);
                                callback.invoke(AsyncStorageErrorUtil.getError(null, e2.getMessage()), null);
                                query.close();
                                return;
                            }
                        } catch (Throwable th) {
                            query.close();
                            throw th;
                        }
                    }
                    callback.invoke(null, createArray);
                }
            }.executeOnExecutor(this.executor, new Void[0]);
        }
    }

    @ReactMethod
    public void multiMerge(final ReadableArray readableArray, final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.storage.AsyncStorageModule.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                WritableMap writableMap = null;
                try {
                    if (AsyncStorageModule.this.ensureDatabase()) {
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                                if (readableArray.getArray(i2).size() != 2) {
                                    WritableMap invalidValueError = AsyncStorageErrorUtil.getInvalidValueError(null);
                                    try {
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    } catch (Exception e2) {
                                        FLog.w(ReactConstants.TAG, e2.getMessage(), e2);
                                        if (invalidValueError == null) {
                                            AsyncStorageErrorUtil.getError(null, e2.getMessage());
                                            return;
                                        }
                                        return;
                                    }
                                } else if (readableArray.getArray(i2).getString(0) == null) {
                                    WritableMap invalidKeyError = AsyncStorageErrorUtil.getInvalidKeyError(null);
                                    try {
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    } catch (Exception e3) {
                                        FLog.w(ReactConstants.TAG, e3.getMessage(), e3);
                                        if (invalidKeyError == null) {
                                            AsyncStorageErrorUtil.getError(null, e3.getMessage());
                                            return;
                                        }
                                        return;
                                    }
                                } else if (readableArray.getArray(i2).getString(1) == null) {
                                    WritableMap invalidValueError2 = AsyncStorageErrorUtil.getInvalidValueError(null);
                                    try {
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    } catch (Exception e4) {
                                        FLog.w(ReactConstants.TAG, e4.getMessage(), e4);
                                        if (invalidValueError2 == null) {
                                            AsyncStorageErrorUtil.getError(null, e4.getMessage());
                                            return;
                                        }
                                        return;
                                    }
                                } else if (!AsyncLocalStorageUtil.mergeImpl(AsyncStorageModule.this.mReactDatabaseSupplier.get(), readableArray.getArray(i2).getString(0), readableArray.getArray(i2).getString(1))) {
                                    WritableMap dBError = AsyncStorageErrorUtil.getDBError(null);
                                    try {
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                        return;
                                    } catch (Exception e5) {
                                        FLog.w(ReactConstants.TAG, e5.getMessage(), e5);
                                        if (dBError == null) {
                                            AsyncStorageErrorUtil.getError(null, e5.getMessage());
                                            return;
                                        }
                                        return;
                                    }
                                }
                            }
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e6) {
                                FLog.w(ReactConstants.TAG, e6.getMessage(), e6);
                                writableMap = AsyncStorageErrorUtil.getError(null, e6.getMessage());
                            }
                        } catch (Exception e7) {
                            FLog.w(ReactConstants.TAG, e7.getMessage(), e7);
                            WritableMap error = AsyncStorageErrorUtil.getError(null, e7.getMessage());
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e8) {
                                FLog.w(ReactConstants.TAG, e8.getMessage(), e8);
                                if (error == null) {
                                    writableMap = AsyncStorageErrorUtil.getError(null, e8.getMessage());
                                }
                            }
                            writableMap = error;
                        }
                        if (writableMap != null) {
                            callback.invoke(writableMap);
                            return;
                        } else {
                            callback.invoke(new Object[0]);
                            return;
                        }
                    }
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                } catch (Throwable th) {
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                    } catch (Exception e9) {
                        FLog.w(ReactConstants.TAG, e9.getMessage(), e9);
                        AsyncStorageErrorUtil.getError(null, e9.getMessage());
                    }
                    throw th;
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    @ReactMethod
    public void multiRemove(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(AsyncStorageErrorUtil.getInvalidKeyError(null));
        } else {
            new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.storage.AsyncStorageModule.3
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.facebook.react.bridge.GuardedAsyncTask
                public void doInBackgroundGuarded(Void... voidArr) {
                    WritableMap writableMap = null;
                    try {
                        if (AsyncStorageModule.this.ensureDatabase()) {
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                                for (int i2 = 0; i2 < readableArray.size(); i2 += 999) {
                                    int min = Math.min(readableArray.size() - i2, 999);
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().delete("catalystLocalStorage", AsyncLocalStorageUtil.buildKeySelection(min), AsyncLocalStorageUtil.buildKeySelectionArgs(readableArray, i2, min));
                                }
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                                try {
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                } catch (Exception e2) {
                                    FLog.w(ReactConstants.TAG, e2.getMessage(), e2);
                                    writableMap = AsyncStorageErrorUtil.getError(null, e2.getMessage());
                                }
                            } catch (Exception e3) {
                                FLog.w(ReactConstants.TAG, e3.getMessage(), e3);
                                WritableMap error = AsyncStorageErrorUtil.getError(null, e3.getMessage());
                                try {
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                } catch (Exception e4) {
                                    FLog.w(ReactConstants.TAG, e4.getMessage(), e4);
                                    if (error == null) {
                                        writableMap = AsyncStorageErrorUtil.getError(null, e4.getMessage());
                                    }
                                }
                                writableMap = error;
                            }
                            if (writableMap != null) {
                                callback.invoke(writableMap);
                                return;
                            } else {
                                callback.invoke(new Object[0]);
                                return;
                            }
                        }
                        callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                    } catch (Throwable th) {
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                        } catch (Exception e5) {
                            FLog.w(ReactConstants.TAG, e5.getMessage(), e5);
                            AsyncStorageErrorUtil.getError(null, e5.getMessage());
                        }
                        throw th;
                    }
                }
            }.executeOnExecutor(this.executor, new Void[0]);
        }
    }

    @ReactMethod
    public void multiSet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(AsyncStorageErrorUtil.getInvalidKeyError(null));
        } else {
            new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.storage.AsyncStorageModule.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.facebook.react.bridge.GuardedAsyncTask
                public void doInBackgroundGuarded(Void... voidArr) {
                    WritableMap writableMap = null;
                    if (AsyncStorageModule.this.ensureDatabase()) {
                        SQLiteStatement compileStatement = AsyncStorageModule.this.mReactDatabaseSupplier.get().compileStatement("INSERT OR REPLACE INTO catalystLocalStorage VALUES (?, ?);");
                        try {
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                                for (int i2 = 0; i2 < readableArray.size(); i2++) {
                                    if (readableArray.getArray(i2).size() != 2) {
                                        WritableMap invalidValueError = AsyncStorageErrorUtil.getInvalidValueError(null);
                                        try {
                                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                            return;
                                        } catch (Exception e2) {
                                            FLog.w(ReactConstants.TAG, e2.getMessage(), e2);
                                            if (invalidValueError == null) {
                                                AsyncStorageErrorUtil.getError(null, e2.getMessage());
                                                return;
                                            }
                                            return;
                                        }
                                    } else if (readableArray.getArray(i2).getString(0) == null) {
                                        WritableMap invalidKeyError = AsyncStorageErrorUtil.getInvalidKeyError(null);
                                        try {
                                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                            return;
                                        } catch (Exception e3) {
                                            FLog.w(ReactConstants.TAG, e3.getMessage(), e3);
                                            if (invalidKeyError == null) {
                                                AsyncStorageErrorUtil.getError(null, e3.getMessage());
                                                return;
                                            }
                                            return;
                                        }
                                    } else if (readableArray.getArray(i2).getString(1) == null) {
                                        WritableMap invalidValueError2 = AsyncStorageErrorUtil.getInvalidValueError(null);
                                        try {
                                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                            return;
                                        } catch (Exception e4) {
                                            FLog.w(ReactConstants.TAG, e4.getMessage(), e4);
                                            if (invalidValueError2 == null) {
                                                AsyncStorageErrorUtil.getError(null, e4.getMessage());
                                                return;
                                            }
                                            return;
                                        }
                                    } else {
                                        compileStatement.clearBindings();
                                        compileStatement.bindString(1, readableArray.getArray(i2).getString(0));
                                        compileStatement.bindString(2, readableArray.getArray(i2).getString(1));
                                        compileStatement.execute();
                                    }
                                }
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                                try {
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                } catch (Exception e5) {
                                    FLog.w(ReactConstants.TAG, e5.getMessage(), e5);
                                    writableMap = AsyncStorageErrorUtil.getError(null, e5.getMessage());
                                }
                            } catch (Exception e6) {
                                FLog.w(ReactConstants.TAG, e6.getMessage(), e6);
                                WritableMap error = AsyncStorageErrorUtil.getError(null, e6.getMessage());
                                try {
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                } catch (Exception e7) {
                                    FLog.w(ReactConstants.TAG, e7.getMessage(), e7);
                                    if (error == null) {
                                        writableMap = AsyncStorageErrorUtil.getError(null, e7.getMessage());
                                    }
                                }
                                writableMap = error;
                            }
                            if (writableMap != null) {
                                callback.invoke(writableMap);
                                return;
                            } else {
                                callback.invoke(new Object[0]);
                                return;
                            }
                        } catch (Throwable th) {
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e8) {
                                FLog.w(ReactConstants.TAG, e8.getMessage(), e8);
                                AsyncStorageErrorUtil.getError(null, e8.getMessage());
                            }
                            throw th;
                        }
                    }
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                }
            }.executeOnExecutor(this.executor, new Void[0]);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
    }

    @VisibleForTesting
    AsyncStorageModule(ReactApplicationContext reactApplicationContext, Executor executor) {
        super(reactApplicationContext);
        this.mShuttingDown = false;
        this.executor = new SerialExecutor(executor);
        this.mReactDatabaseSupplier = ReactDatabaseSupplier.getInstance(reactApplicationContext);
    }
}
