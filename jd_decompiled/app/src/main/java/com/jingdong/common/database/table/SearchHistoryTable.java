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
public class SearchHistoryTable implements IJdTable {
    public static final int MAX_SEARCH_HISTORY_NUM = 20;
    public static final String TABLE_NAME = "search_history";
    private static final String TAG = "SearchHistoryTable";
    public static final String TB_COLUMN_CID = "cid";
    public static final String TB_COLUMN_ID = "_id";
    public static final String TB_COLUMN_SEARCH_TIME = "search_time";
    public static final String TB_COLUMN_TAG = "tag";
    public static final String TB_COLUMN_TAGLIST = "taglist";
    public static final String TB_COLUMN_TYPE = "type";
    public static final String TB_COLUMN_WORD = "word";

    private static void addHistory(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, int i2, String str4) {
        if (OKLog.D) {
            OKLog.d(TAG, "addHistory word -->> " + str);
        }
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

    /* JADX WARN: Code restructure failed: missing block: B:22:0x00d2, code lost:
        if (r7.isClosed() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00e6, code lost:
        if (r7.isClosed() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00e8, code lost:
        r7.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.jingdong.common.entity.SearchHistory getSearchHistoryFromText(android.database.sqlite.SQLiteDatabase r20, java.lang.String r21, int r22) {
        /*
            Method dump skipped, instructions count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.SearchHistoryTable.getSearchHistoryFromText(android.database.sqlite.SQLiteDatabase, java.lang.String, int):com.jingdong.common.entity.SearchHistory");
    }

    public static void saveSearchHistory(String str, int i2, String str2) {
        saveSearchHistory(str, "", "", i2, str2);
    }

    private void upgradeDatabaseToVersion1(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE search_history ADD COLUMN tag TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE search_history ADD COLUMN taglist TEXT");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS search_history('_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,word TEXT,tag TEXT,taglist TEXT,type INTEGER,cid TEXT,search_time DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (i2 < 20256) {
            sQLiteDatabase.execSQL("drop table if exists search_history");
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

    public static void saveSearchHistory(String str, String str2, String str3, int i2, String str4) {
        if (OKLog.D) {
            OKLog.d(TAG, "search_history saveSearchHistory() -->> ");
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String trim = str.trim();
        try {
            SQLiteDatabase database = DBHelperUtil.getDatabase();
            SearchHistory searchHistoryFromText = getSearchHistoryFromText(database, trim, i2);
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
        if (OKLog.D) {
            OKLog.d(TAG, "deleteHistory id -->>  " + i2);
        }
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
            java.lang.String r2 = "search_history"
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
            java.lang.String r2 = "SearchHistoryTable"
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
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.SearchHistoryTable.getAllSearchHistory(android.database.sqlite.SQLiteDatabase):java.util.ArrayList");
    }

    public static void saveSearchHistory(String str) {
        saveSearchHistory(str, 0, null);
    }
}
