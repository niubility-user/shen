package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jingdong.common.entity.MiniProgramSearchHistory;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class MiniProgramSearchHistoryTable implements IJdTable {
    public static final int MAX_SEARCH_HISTORY_NUM = 30;
    public static final String TABLE_NAME = "miniprogram_search_history";
    private static final String TAG = "MiniProgramSearchHistoryTable";
    public static final String TB_COLUMN_ID = "_id";
    public static final String TB_COLUMN_SEARCH_TIME = "search_time";
    public static final String TB_COLUMN_TAG = "tag";
    public static final String TB_COLUMN_TYPE = "type";
    public static final String TB_COLUMN_WORD = "word";

    private static void addHistory(SQLiteDatabase sQLiteDatabase, String str, String str2, int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, "addHistory word -->> " + str);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("search_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("word", str);
        contentValues.put("tag", str2);
        contentValues.put("type", Integer.valueOf(i2));
        sQLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public static void deleteAllHistory() {
        if (OKLog.D) {
            OKLog.d(TAG, "deleteAllHistory -->>  ");
        }
        try {
            DBHelperUtil.getDatabase().delete(TABLE_NAME, null, null);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void deleteHistory(MiniProgramSearchHistory miniProgramSearchHistory) {
        try {
            try {
                deleteHistory(DBHelperUtil.getDatabase(), miniProgramSearchHistory.getId());
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
        ArrayList<MiniProgramSearchHistory> allSearchHistory = getAllSearchHistory(sQLiteDatabase);
        if (allSearchHistory == null) {
            return;
        }
        int size = (allSearchHistory.size() - 30) + 1;
        if (allSearchHistory.size() < 1 || size < 1) {
            return;
        }
        int i2 = 0;
        for (int size2 = allSearchHistory.size() - 1; size2 >= 0 && i2 < size; size2--) {
            MiniProgramSearchHistory miniProgramSearchHistory = allSearchHistory.get(size2);
            if (miniProgramSearchHistory != null) {
                deleteHistory(sQLiteDatabase, miniProgramSearchHistory.getId());
                i2++;
            }
        }
    }

    public static ArrayList<MiniProgramSearchHistory> getAllSearchHistory() {
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

    /* JADX WARN: Code restructure failed: missing block: B:22:0x00b6, code lost:
        if (r5.isClosed() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ca, code lost:
        if (r5.isClosed() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00cc, code lost:
        r5.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.jingdong.common.entity.MiniProgramSearchHistory getSearchHistoryFromText(android.database.sqlite.SQLiteDatabase r17, java.lang.String r18, int r19) {
        /*
            java.lang.String r0 = "type"
            java.lang.String r1 = "tag"
            java.lang.String r2 = "word"
            java.lang.String r3 = "search_time"
            java.lang.String r4 = "_id"
            boolean r5 = android.text.TextUtils.isEmpty(r18)
            r6 = 0
            if (r5 == 0) goto L12
            return r6
        L12:
            java.lang.String r5 = r18.trim()
            boolean r7 = com.jingdong.sdk.oklog.OKLog.D
            java.lang.String r8 = "MiniProgramSearchHistoryTable"
            if (r7 == 0) goto L30
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "getSearchHistoryFromText word -->>  "
            r7.append(r9)
            r7.append(r5)
            java.lang.String r7 = r7.toString()
            com.jingdong.sdk.oklog.OKLog.d(r8, r7)
        L30:
            java.lang.String r10 = "miniprogram_search_history"
            r7 = 5
            java.lang.String[] r11 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            r7 = 0
            r11[r7] = r4     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            r9 = 1
            r11[r9] = r3     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            r12 = 2
            r11[r12] = r2     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            r13 = 3
            r11[r13] = r1     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            r13 = 4
            r11[r13] = r0     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            java.lang.String r13 = "word = ? and type = ? "
            java.lang.String[] r14 = new java.lang.String[r12]     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            r14[r7] = r5     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            r5.<init>()     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            r7 = r19
            r5.append(r7)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            java.lang.String r7 = ""
            r5.append(r7)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            r14[r9] = r5     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            r5 = 0
            r15 = 0
            r16 = 0
            r9 = r17
            r12 = r13
            r13 = r14
            r14 = r5
            android.database.Cursor r5 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            if (r5 == 0) goto Lb0
            boolean r7 = r5.moveToFirst()     // Catch: java.lang.Exception -> Lae java.lang.Throwable -> Ld0
            if (r7 == 0) goto Lb0
            com.jingdong.common.entity.MiniProgramSearchHistory r7 = new com.jingdong.common.entity.MiniProgramSearchHistory     // Catch: java.lang.Exception -> Lae java.lang.Throwable -> Ld0
            int r4 = r5.getColumnIndex(r4)     // Catch: java.lang.Exception -> Lae java.lang.Throwable -> Ld0
            int r10 = r5.getInt(r4)     // Catch: java.lang.Exception -> Lae java.lang.Throwable -> Ld0
            int r2 = r5.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lae java.lang.Throwable -> Ld0
            java.lang.String r11 = r5.getString(r2)     // Catch: java.lang.Exception -> Lae java.lang.Throwable -> Ld0
            int r1 = r5.getColumnIndex(r1)     // Catch: java.lang.Exception -> Lae java.lang.Throwable -> Ld0
            java.lang.String r12 = r5.getString(r1)     // Catch: java.lang.Exception -> Lae java.lang.Throwable -> Ld0
            int r1 = r5.getColumnIndex(r3)     // Catch: java.lang.Exception -> Lae java.lang.Throwable -> Ld0
            long r13 = r5.getLong(r1)     // Catch: java.lang.Exception -> Lae java.lang.Throwable -> Ld0
            int r0 = r5.getColumnIndex(r0)     // Catch: java.lang.Exception -> Lae java.lang.Throwable -> Ld0
            int r15 = r5.getInt(r0)     // Catch: java.lang.Exception -> Lae java.lang.Throwable -> Ld0
            r9 = r7
            r9.<init>(r10, r11, r12, r13, r15)     // Catch: java.lang.Exception -> Lae java.lang.Throwable -> Ld0
            if (r5 == 0) goto Lad
            boolean r0 = r5.isClosed()
            if (r0 != 0) goto Lad
            r5.close()
        Lad:
            return r7
        Lae:
            r0 = move-exception
            goto Lbd
        Lb0:
            if (r5 == 0) goto Lcf
            boolean r0 = r5.isClosed()
            if (r0 != 0) goto Lcf
            goto Lcc
        Lb9:
            r0 = move-exception
            goto Ld2
        Lbb:
            r0 = move-exception
            r5 = r6
        Lbd:
            boolean r1 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> Ld0
            if (r1 == 0) goto Lc4
            com.jingdong.sdk.oklog.OKLog.e(r8, r0)     // Catch: java.lang.Throwable -> Ld0
        Lc4:
            if (r5 == 0) goto Lcf
            boolean r0 = r5.isClosed()
            if (r0 != 0) goto Lcf
        Lcc:
            r5.close()
        Lcf:
            return r6
        Ld0:
            r0 = move-exception
            r6 = r5
        Ld2:
            if (r6 == 0) goto Ldd
            boolean r1 = r6.isClosed()
            if (r1 != 0) goto Ldd
            r6.close()
        Ldd:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MiniProgramSearchHistoryTable.getSearchHistoryFromText(android.database.sqlite.SQLiteDatabase, java.lang.String, int):com.jingdong.common.entity.MiniProgramSearchHistory");
    }

    public static void saveSearchHistory(String str, int i2) {
        saveSearchHistory(str, "", i2);
    }

    private void upgradeDatabaseToVersion1(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE miniprogram_search_history ADD COLUMN tag TEXT");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS miniprogram_search_history('_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,word TEXT,tag TEXT,type INTEGER,search_time DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (i2 < 20256) {
            sQLiteDatabase.execSQL("drop table if exists miniprogram_search_history");
        } else if (i2 < 25276) {
            sQLiteDatabase.beginTransaction();
            try {
                upgradeDatabaseToVersion1(sQLiteDatabase);
                sQLiteDatabase.setTransactionSuccessful();
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    public static void saveSearchHistory(String str, String str2, int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, "search_history saveSearchHistory() -->> ");
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String trim = str.trim();
        try {
            SQLiteDatabase database = DBHelperUtil.getDatabase();
            MiniProgramSearchHistory searchHistoryFromText = getSearchHistoryFromText(database, trim, i2);
            if (OKLog.D) {
                OKLog.d(TAG, "history -->> " + searchHistoryFromText);
            }
            try {
                try {
                    if (searchHistoryFromText == null) {
                        deleteOlderSearchHistory(database);
                    } else {
                        deleteHistory(database, searchHistoryFromText.getId());
                    }
                    addHistory(database, trim, str2, i2);
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
        if (OKLog.D) {
            OKLog.d(TAG, "deleteHistory id -->>  " + i2);
        }
        sQLiteDatabase.delete(TABLE_NAME, "_id = ?", new String[]{i2 + ""});
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0058, code lost:
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0075, code lost:
        if (r10 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0078, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0056, code lost:
        if (r10 != null) goto L10;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.ArrayList<com.jingdong.common.entity.MiniProgramSearchHistory> getAllSearchHistory(android.database.sqlite.SQLiteDatabase r10) {
        /*
            r0 = 0
            java.lang.String r2 = "miniprogram_search_history"
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.String r8 = "_id desc"
            r1 = r10
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L79
            r1.<init>()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L79
        L14:
            boolean r0 = r10.moveToNext()     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            if (r0 == 0) goto L56
            com.jingdong.common.entity.MiniProgramSearchHistory r0 = new com.jingdong.common.entity.MiniProgramSearchHistory     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            java.lang.String r2 = "_id"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            int r3 = r10.getInt(r2)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            java.lang.String r2 = "word"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            java.lang.String r4 = r10.getString(r2)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            java.lang.String r2 = "tag"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            java.lang.String r5 = r10.getString(r2)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            java.lang.String r2 = "search_time"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            long r6 = r10.getLong(r2)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            java.lang.String r2 = "type"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            int r8 = r10.getInt(r2)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r8)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            r1.add(r0)     // Catch: java.lang.Exception -> L5c java.lang.Throwable -> L79
            goto L14
        L56:
            if (r10 == 0) goto L78
        L58:
            r10.close()
            goto L78
        L5c:
            r0 = move-exception
            goto L6c
        L5e:
            r1 = move-exception
            r9 = r1
            r1 = r0
            r0 = r9
            goto L6c
        L63:
            r10 = move-exception
            r9 = r0
            r0 = r10
            r10 = r9
            goto L7a
        L68:
            r10 = move-exception
            r1 = r0
            r0 = r10
            r10 = r1
        L6c:
            boolean r2 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L79
            if (r2 == 0) goto L75
            java.lang.String r2 = "MiniProgramSearchHistoryTable"
            com.jingdong.sdk.oklog.OKLog.e(r2, r0)     // Catch: java.lang.Throwable -> L79
        L75:
            if (r10 == 0) goto L78
            goto L58
        L78:
            return r1
        L79:
            r0 = move-exception
        L7a:
            if (r10 == 0) goto L7f
            r10.close()
        L7f:
            goto L81
        L80:
            throw r0
        L81:
            goto L80
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MiniProgramSearchHistoryTable.getAllSearchHistory(android.database.sqlite.SQLiteDatabase):java.util.ArrayList");
    }

    public static void saveSearchHistory(String str) {
        saveSearchHistory(str, 0);
    }
}
