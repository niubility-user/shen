package com.tencent.wcdb;

import android.util.Pair;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteDatabaseConfiguration;
import com.tencent.wcdb.database.SQLiteException;
import com.tencent.wcdb.database.SQLiteTrace;
import com.tencent.wcdb.support.Log;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public final class DefaultDatabaseErrorHandler implements DatabaseErrorHandler {
    private static final String[] SUFFIX_TO_BACKUP = {"", "-journal", "-wal", ".sm", ".bak", "-vfslog", "-vfslo1"};
    private static final String TAG = "WCDB.DefaultDatabaseErrorHandler";
    private final boolean mNoCorruptionBackup;

    public DefaultDatabaseErrorHandler() {
        this.mNoCorruptionBackup = false;
    }

    private void deleteDatabaseFile(String str) {
        if (str.equalsIgnoreCase(SQLiteDatabaseConfiguration.MEMORY_DB_PATH) || str.trim().length() == 0) {
            return;
        }
        Log.e(TAG, "Remove database file: " + str);
        int i2 = 0;
        if (!this.mNoCorruptionBackup) {
            File file = new File(str);
            File file2 = new File(file.getParentFile(), "corrupted");
            if (!file2.mkdirs()) {
                Log.e(TAG, "Could not create directory for corrupted database. Corruption backup may be unavailable.");
            }
            String str2 = file2.getPath() + "/" + file.getName();
            String[] strArr = SUFFIX_TO_BACKUP;
            int length = strArr.length;
            while (i2 < length) {
                String str3 = strArr[i2];
                moveOrDeleteFile(str + str3, str2 + str3);
                i2++;
            }
            return;
        }
        String[] strArr2 = SUFFIX_TO_BACKUP;
        int length2 = strArr2.length;
        while (i2 < length2) {
            deleteFile(str + strArr2[i2]);
            i2++;
        }
    }

    private static boolean deleteFile(String str) {
        return new File(str).delete();
    }

    private static boolean moveOrDeleteFile(String str, String str2) {
        File file = new File(str);
        boolean renameTo = file.renameTo(new File(str2));
        if (!renameTo) {
            file.delete();
        }
        return renameTo;
    }

    @Override // com.tencent.wcdb.DatabaseErrorHandler
    public void onCorruption(SQLiteDatabase sQLiteDatabase) {
        Log.e(TAG, "Corruption reported by sqlite on database: " + sQLiteDatabase.getPath());
        if (!sQLiteDatabase.isOpen()) {
            deleteDatabaseFile(sQLiteDatabase.getPath());
            return;
        }
        List<Pair<String, String>> list = null;
        try {
            list = sQLiteDatabase.getAttachedDbs();
        } catch (SQLiteException unused) {
        }
        SQLiteTrace traceCallback = sQLiteDatabase.getTraceCallback();
        if (traceCallback != null) {
            traceCallback.onDatabaseCorrupted(sQLiteDatabase);
        }
        try {
            sQLiteDatabase.close();
            if (list != null) {
                Iterator<Pair<String, String>> it = list.iterator();
                while (it.hasNext()) {
                    deleteDatabaseFile((String) it.next().second);
                }
                return;
            }
        } catch (SQLiteException unused2) {
            if (list != null) {
                Iterator<Pair<String, String>> it2 = list.iterator();
                while (it2.hasNext()) {
                    deleteDatabaseFile((String) it2.next().second);
                }
                return;
            }
        } catch (Throwable th) {
            if (list != null) {
                Iterator<Pair<String, String>> it3 = list.iterator();
                while (it3.hasNext()) {
                    deleteDatabaseFile((String) it3.next().second);
                }
            } else {
                deleteDatabaseFile(sQLiteDatabase.getPath());
            }
            throw th;
        }
        deleteDatabaseFile(sQLiteDatabase.getPath());
    }

    public DefaultDatabaseErrorHandler(boolean z) {
        this.mNoCorruptionBackup = z;
    }
}
