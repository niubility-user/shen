package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jingdong.common.entity.SearchHistory;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class MeSearchHistoryTable implements IJdTable {
    public static final int MAX_SEARCH_HISTORY_NUM = 20;
    public static final String TABLE_NAME = "me_search_history";
    private static final String TAG = "MeSearchHistoryTable";
    public static final String TB_COLUMN_CID = "cid";
    public static final String TB_COLUMN_ID = "_id";
    public static final String TB_COLUMN_SEARCH_TIME = "search_time";
    public static final String TB_COLUMN_TAG = "tag";
    public static final String TB_COLUMN_TAGLIST = "taglist";
    public static final String TB_COLUMN_TYPE = "type";
    public static final String TB_COLUMN_WORD = "word";

    private static void addHistory(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, int i2, String str4) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("search_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("word", str);
        contentValues.put("tag", str2);
        contentValues.put("taglist", str3);
        contentValues.put("type", Integer.valueOf(i2));
        contentValues.put("cid", str4);
        sQLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public static void deleteAllHistory() {
        try {
            DBHelperUtil.getDatabase().delete(TABLE_NAME, null, null);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void deleteHistory(SearchHistory searchHistory) {
        try {
            try {
                deleteHistory(DBHelperUtil.getDatabase(), searchHistory.getId());
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    private static void deleteOlderSearchHistory(SQLiteDatabase sQLiteDatabase) {
        ArrayList<SearchHistory> allSearchHistory = getAllSearchHistory(sQLiteDatabase);
        if (allSearchHistory == null) {
            return;
        }
        int size = (allSearchHistory.size() - 20) + 1;
        if (allSearchHistory.size() < 1 || size < 1) {
            return;
        }
        int i2 = 0;
        for (int size2 = allSearchHistory.size() - 1; size2 >= 0 && i2 < size; size2--) {
            SearchHistory searchHistory = allSearchHistory.get(size2);
            if (searchHistory != null) {
                deleteHistory(sQLiteDatabase, searchHistory.getId());
                i2++;
            }
        }
    }

    public static ArrayList<SearchHistory> getAllSearchHistory() {
        try {
            return getAllSearchHistory(DBHelperUtil.getDatabase());
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return null;
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x00b5, code lost:
        if (r7.isClosed() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00cb, code lost:
        if (r7.isClosed() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00cd, code lost:
        r7.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.jingdong.common.entity.SearchHistory getSearchHistoryFromText(android.database.sqlite.SQLiteDatabase r19, java.lang.String r20, int r21) {
        /*
            java.lang.String r0 = "cid"
            java.lang.String r1 = "type"
            java.lang.String r2 = "taglist"
            java.lang.String r3 = "tag"
            java.lang.String r4 = "word"
            java.lang.String r5 = "search_time"
            java.lang.String r6 = "_id"
            boolean r7 = android.text.TextUtils.isEmpty(r20)
            r8 = 0
            if (r7 == 0) goto L16
            return r8
        L16:
            java.lang.String r7 = r20.trim()
            java.lang.String r10 = "me_search_history"
            r9 = 7
            java.lang.String[] r11 = new java.lang.String[r9]     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            r9 = 0
            r11[r9] = r6     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            r12 = 1
            r11[r12] = r5     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            r13 = 2
            r11[r13] = r4     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            r14 = 3
            r11[r14] = r3     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            r14 = 4
            r11[r14] = r2     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            r14 = 5
            r11[r14] = r1     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            r14 = 6
            r11[r14] = r0     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            java.lang.String r14 = "word = ? and type = ? "
            java.lang.String[] r13 = new java.lang.String[r13]     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            r13[r9] = r7     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            r7.<init>()     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            r9 = r21
            r7.append(r9)     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            java.lang.String r9 = ""
            r7.append(r9)     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            r13[r12] = r7     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            r7 = 0
            r15 = 0
            r16 = 0
            r9 = r19
            r12 = r14
            r14 = r7
            android.database.Cursor r7 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch: java.lang.Throwable -> Lb8 java.lang.Exception -> Lba
            if (r7 == 0) goto Laf
            boolean r9 = r7.moveToFirst()     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            if (r9 == 0) goto Laf
            com.jingdong.common.entity.SearchHistory r9 = new com.jingdong.common.entity.SearchHistory     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            int r6 = r7.getColumnIndex(r6)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            int r11 = r7.getInt(r6)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            int r4 = r7.getColumnIndex(r4)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            java.lang.String r12 = r7.getString(r4)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            int r3 = r7.getColumnIndex(r3)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            java.lang.String r13 = r7.getString(r3)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            int r2 = r7.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            java.lang.String r14 = r7.getString(r2)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            int r2 = r7.getColumnIndex(r5)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            long r15 = r7.getLong(r2)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            int r17 = r7.getInt(r1)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            int r0 = r7.getColumnIndex(r0)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            java.lang.String r18 = r7.getString(r0)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            r10 = r9
            r10.<init>(r11, r12, r13, r14, r15, r17, r18)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Ld1
            if (r7 == 0) goto Lac
            boolean r0 = r7.isClosed()
            if (r0 != 0) goto Lac
            r7.close()
        Lac:
            return r9
        Lad:
            r0 = move-exception
            goto Lbc
        Laf:
            if (r7 == 0) goto Ld0
            boolean r0 = r7.isClosed()
            if (r0 != 0) goto Ld0
            goto Lcd
        Lb8:
            r0 = move-exception
            goto Ld3
        Lba:
            r0 = move-exception
            r7 = r8
        Lbc:
            boolean r1 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> Ld1
            if (r1 == 0) goto Lc5
            java.lang.String r1 = "MeSearchHistoryTable"
            com.jingdong.sdk.oklog.OKLog.e(r1, r0)     // Catch: java.lang.Throwable -> Ld1
        Lc5:
            if (r7 == 0) goto Ld0
            boolean r0 = r7.isClosed()
            if (r0 != 0) goto Ld0
        Lcd:
            r7.close()
        Ld0:
            return r8
        Ld1:
            r0 = move-exception
            r8 = r7
        Ld3:
            if (r8 == 0) goto Lde
            boolean r1 = r8.isClosed()
            if (r1 != 0) goto Lde
            r8.close()
        Lde:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MeSearchHistoryTable.getSearchHistoryFromText(android.database.sqlite.SQLiteDatabase, java.lang.String, int):com.jingdong.common.entity.SearchHistory");
    }

    public static void saveSearchHistory(String str, int i2, String str2) {
        saveSearchHistory(str, "", "", i2, str2);
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS me_search_history('_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,word TEXT,tag TEXT,taglist TEXT,type INTEGER,cid TEXT,search_time DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    public static void saveSearchHistory(String str, String str2, String str3, int i2, String str4) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String trim = str.trim();
        try {
            SQLiteDatabase database = DBHelperUtil.getDatabase();
            SearchHistory searchHistoryFromText = getSearchHistoryFromText(database, trim, i2);
            try {
                try {
                    if (searchHistoryFromText == null) {
                        deleteOlderSearchHistory(database);
                    } else {
                        deleteHistory(database, searchHistoryFromText.getId());
                    }
                    addHistory(database, trim, str2, str3, i2, str4);
                } finally {
                    DBHelperUtil.closeDatabase();
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } catch (Exception e3) {
            if (OKLog.E) {
                OKLog.e(TAG, e3);
            }
        }
    }

    private static void deleteHistory(SQLiteDatabase sQLiteDatabase, int i2) {
        sQLiteDatabase.delete(TABLE_NAME, "_id = ?", new String[]{i2 + ""});
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x006c, code lost:
        r12.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0089, code lost:
        if (r12 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008c, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x006a, code lost:
        if (r12 != null) goto L10;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.ArrayList<com.jingdong.common.entity.SearchHistory> getAllSearchHistory(android.database.sqlite.SQLiteDatabase r12) {
        /*
            r0 = 0
            java.lang.String r2 = "me_search_history"
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.String r8 = "_id desc"
            r1 = r12
            android.database.Cursor r12 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L7c
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Exception -> L72 java.lang.Throwable -> L8d
            r1.<init>()     // Catch: java.lang.Exception -> L72 java.lang.Throwable -> L8d
        L14:
            boolean r0 = r12.moveToNext()     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            if (r0 == 0) goto L6a
            com.jingdong.common.entity.SearchHistory r0 = new com.jingdong.common.entity.SearchHistory     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r2 = "_id"
            int r2 = r12.getColumnIndex(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            int r3 = r12.getInt(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r2 = "word"
            int r2 = r12.getColumnIndex(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r4 = r12.getString(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r2 = "tag"
            int r2 = r12.getColumnIndex(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r5 = r12.getString(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r2 = "taglist"
            int r2 = r12.getColumnIndex(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r6 = r12.getString(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r2 = "search_time"
            int r2 = r12.getColumnIndex(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            long r7 = r12.getLong(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r2 = "type"
            int r2 = r12.getColumnIndex(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            int r9 = r12.getInt(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r2 = "cid"
            int r2 = r12.getColumnIndex(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            java.lang.String r10 = r12.getString(r2)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7, r9, r10)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            r1.add(r0)     // Catch: java.lang.Exception -> L70 java.lang.Throwable -> L8d
            goto L14
        L6a:
            if (r12 == 0) goto L8c
        L6c:
            r12.close()
            goto L8c
        L70:
            r0 = move-exception
            goto L80
        L72:
            r1 = move-exception
            r11 = r1
            r1 = r0
            r0 = r11
            goto L80
        L77:
            r12 = move-exception
            r11 = r0
            r0 = r12
            r12 = r11
            goto L8e
        L7c:
            r12 = move-exception
            r1 = r0
            r0 = r12
            r12 = r1
        L80:
            boolean r2 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L8d
            if (r2 == 0) goto L89
            java.lang.String r2 = "MeSearchHistoryTable"
            com.jingdong.sdk.oklog.OKLog.e(r2, r0)     // Catch: java.lang.Throwable -> L8d
        L89:
            if (r12 == 0) goto L8c
            goto L6c
        L8c:
            return r1
        L8d:
            r0 = move-exception
        L8e:
            if (r12 == 0) goto L93
            r12.close()
        L93:
            goto L95
        L94:
            throw r0
        L95:
            goto L94
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MeSearchHistoryTable.getAllSearchHistory(android.database.sqlite.SQLiteDatabase):java.util.ArrayList");
    }

    public static void saveSearchHistory(String str) {
        saveSearchHistory(str, 0, null);
    }
}
