package com.tencent.wcdb.database;

import android.os.Environment;
import android.os.StatFs;

/* loaded from: classes9.dex */
public final class SQLiteGlobal {
    private static final String TAG = "WCDB.SQLiteGlobal";
    public static final String defaultJournalMode = "PERSIST";
    public static final int defaultPageSize;
    public static final String defaultSyncMode = "FULL";
    public static final int journalSizeLimit = 524288;
    public static final int walAutoCheckpoint = 100;
    public static final int walConnectionPoolSize = 4;
    public static final String walSyncMode = "FULL";

    static {
        int i2;
        if (!WCDBInitializationProbe.libLoaded) {
            System.loadLibrary("wcdb");
        }
        try {
            i2 = new StatFs(Environment.getDataDirectory().getAbsolutePath()).getBlockSize();
        } catch (RuntimeException unused) {
            i2 = 4096;
        }
        defaultPageSize = i2;
        nativeSetDefaultPageSize(i2);
    }

    private SQLiteGlobal() {
    }

    public static void loadLib() {
    }

    private static native int nativeReleaseMemory();

    private static native void nativeSetDefaultPageSize(int i2);

    public static int releaseMemory() {
        return nativeReleaseMemory();
    }
}
