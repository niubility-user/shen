package com.jingdong.common.database.table;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashSet;

/* loaded from: classes5.dex */
public class FxContentIdTable implements IJdTable {
    private static final String CONTENT_ID = "contentId";
    private static final String KEY_ID = "id";
    public static final int MAX_STORE_COUNT = 480;
    private static final String TABLE_NAME = "FxContentIdTable";
    private static final String TAG = "FxContentIdTable";

    public static void clearHistory() {
        long count = count();
        if (count < 960) {
            return;
        }
        try {
            DBHelperUtil.getDatabase().execSQL(String.format("delete from %s where %s in (select %s from %s order by %s limit %d)", "FxContentIdTable", "id", "id", "FxContentIdTable", "id", Long.valueOf(count - 480)));
        } catch (Exception e2) {
            OKLog.e("FxContentIdTable", e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003a, code lost:
        if (r3.isClosed() == false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static long count() {
        Cursor cursor = null;
        long j2 = 0;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery(String.format("select count(*) from %s", "FxContentIdTable"), null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    j2 = cursor.getLong(0);
                }
            } catch (Exception e2) {
                OKLog.e("FxContentIdTable", e2);
                if (cursor != null) {
                }
            }
            return j2;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x006c, code lost:
        if (r4.isClosed() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007b, code lost:
        if (r4.isClosed() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007d, code lost:
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static HashSet<String> getIds() {
        HashSet<String> hashSet = new HashSet<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery(String.format("select * from %s order by %s desc limit %d", "FxContentIdTable", "id", 480), null);
            } catch (Exception e2) {
                OKLog.e("FxContentIdTable", e2);
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
                return hashSet;
            }
            cursor.moveToFirst();
            int count = cursor.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                cursor.moveToPosition(i2);
                String string = cursor.getString(cursor.getColumnIndex(CONTENT_ID));
                if (string != null && string.length() != 0 && !hashSet.contains(string)) {
                    hashSet.add(string);
                }
            }
            if (cursor != null) {
            }
            DBHelperUtil.closeDatabase();
            return hashSet;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    public static void insert(String str) {
        try {
            DBHelperUtil.getDatabase().execSQL(String.format("insert into %s (%s) values (%s)", "FxContentIdTable", CONTENT_ID, str));
        } catch (Exception e2) {
            OKLog.e("FxContentIdTable", e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        if (r11 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002a, code lost:
        if (r11 != null) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002c, code lost:
        r11.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean queryIdExist(String str) {
        boolean z = false;
        String format = String.format("%s = %s", CONTENT_ID, str);
        Cursor cursor = null;
        try {
            cursor = DBHelperUtil.getDatabase().query("FxContentIdTable", null, format, null, null, null, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    z = true;
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS FxContentIdTable (id INTEGER PRIMARY KEY AUTOINCREMENT,contentId VARCHAR NOT NULL)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }
}
