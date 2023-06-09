package com.tencent.wcdb.database;

import android.annotation.SuppressLint;
import android.util.Printer;
import com.tencent.wcdb.support.Log;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public final class SQLiteDebug {
    private static final String TAG = "WCDB.SQLiteDebug";
    private static volatile int sLastErrorLine;
    private static volatile ArrayList<IOTraceStats> sLastIOTraceStats;

    /* loaded from: classes9.dex */
    public static class DbStats {
        public String cache;
        public String dbName;
        public long dbSize;
        public int lookaside;
        public long pageSize;

        public DbStats(String str, long j2, long j3, int i2, int i3, int i4, int i5) {
            this.dbName = str;
            this.pageSize = j3 / 1024;
            this.dbSize = (j2 * j3) / 1024;
            this.lookaside = i2;
            this.cache = i3 + "/" + i4 + "/" + i5;
        }
    }

    /* loaded from: classes9.dex */
    public static class IOTraceStats {
        public String dbName;
        public String journalMode;
        public long lastJournalReadOffset;
        public byte[] lastJournalReadPage;
        public long lastJournalWriteOffset;
        public byte[] lastJournalWritePage;
        public long lastReadOffset;
        public byte[] lastReadPage;
        public long lastWriteOffset;
        public byte[] lastWritePage;
        public long pageCount;
        public long pageSize;
        public String path;

        @SuppressLint({"DefaultLocale"})
        public String toString() {
            return String.format("[%s | %s] pageSize: %d, pageCount: %d, journal: %s, lastRead: %d, lastWrite: %d, lastJournalRead: %d, lastJournalWrite: %d", this.dbName, this.path, Long.valueOf(this.pageSize), Long.valueOf(this.pageCount), this.journalMode, Long.valueOf(this.lastReadOffset), Long.valueOf(this.lastWriteOffset), Long.valueOf(this.lastJournalReadOffset), Long.valueOf(this.lastJournalWriteOffset));
        }
    }

    /* loaded from: classes9.dex */
    public static class PagerStats {
        public ArrayList<DbStats> dbStats;
        public int largestMemAlloc;
        public int memoryUsed;
        public int pageCacheOverflow;
    }

    static {
        SQLiteGlobal.loadLib();
    }

    private SQLiteDebug() {
    }

    public static void collectLastIOTraceStats(SQLiteConnection sQLiteConnection) {
        try {
            sLastErrorLine = nativeGetLastErrorLine();
            ArrayList<IOTraceStats> arrayList = new ArrayList<>();
            long nativeHandle = sQLiteConnection.getNativeHandle(null);
            if (nativeHandle != 0) {
                nativeGetIOTraceStats(nativeHandle, arrayList);
                sQLiteConnection.endNativeHandle(null);
            }
            sLastIOTraceStats = arrayList;
        } catch (RuntimeException e2) {
            Log.e(TAG, "Cannot collect I/O trace statistics: " + e2.getMessage());
        }
    }

    public static void dump(Printer printer, String[] strArr) {
        boolean z = false;
        for (String str : strArr) {
            if (str.equals("-v")) {
                z = true;
            }
        }
        SQLiteDatabase.dumpAll(printer, z);
    }

    public static PagerStats getDatabaseInfo() {
        PagerStats pagerStats = new PagerStats();
        nativeGetPagerStats(pagerStats);
        pagerStats.dbStats = SQLiteDatabase.getDbStats();
        return pagerStats;
    }

    public static int getLastErrorLine() {
        return sLastErrorLine;
    }

    public static ArrayList<IOTraceStats> getLastIOTraceStats() {
        return sLastIOTraceStats;
    }

    private static native void nativeGetIOTraceStats(long j2, ArrayList<IOTraceStats> arrayList);

    private static native int nativeGetLastErrorLine();

    private static native void nativeGetPagerStats(PagerStats pagerStats);

    private static native void nativeSetIOTraceFlags(int i2);

    public static void setIOTraceFlags(int i2) {
        nativeSetIOTraceFlags(i2);
    }

    public static final boolean shouldLogSlowQuery(long j2) {
        return j2 > 300;
    }

    public static void collectLastIOTraceStats(SQLiteDatabase sQLiteDatabase) {
        try {
            sLastErrorLine = nativeGetLastErrorLine();
            ArrayList<IOTraceStats> arrayList = new ArrayList<>();
            long acquireNativeConnectionHandle = sQLiteDatabase.acquireNativeConnectionHandle("collectIoStat", false, false);
            if (acquireNativeConnectionHandle != 0) {
                nativeGetIOTraceStats(acquireNativeConnectionHandle, arrayList);
            }
            sQLiteDatabase.releaseNativeConnection(acquireNativeConnectionHandle, null);
            sLastIOTraceStats = arrayList;
        } catch (RuntimeException e2) {
            Log.e(TAG, "Cannot collect I/O trace statistics: " + e2.getMessage());
        }
    }
}
