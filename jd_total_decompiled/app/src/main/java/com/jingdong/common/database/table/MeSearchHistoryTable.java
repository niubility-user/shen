package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
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
    */
    private static SearchHistory getSearchHistoryFromText(SQLiteDatabase sQLiteDatabase, String str, int i2) {
        Cursor cursor;
        ?? r8 = 0;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        try {
            try {
                cursor = sQLiteDatabase.query(TABLE_NAME, new String[]{"_id", "search_time", "word", "tag", "taglist", "type", "cid"}, "word = ? and type = ? ", new String[]{trim, i2 + ""}, null, null, null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            SearchHistory searchHistory = new SearchHistory(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("word")), cursor.getString(cursor.getColumnIndex("tag")), cursor.getString(cursor.getColumnIndex("taglist")), cursor.getLong(cursor.getColumnIndex("search_time")), cursor.getInt(cursor.getColumnIndex("type")), cursor.getString(cursor.getColumnIndex("cid")));
                            if (cursor != null && !cursor.isClosed()) {
                                cursor.close();
                            }
                            return searchHistory;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        if (OKLog.E) {
                            OKLog.e(TAG, e);
                        }
                        if (cursor != null) {
                        }
                        return null;
                    }
                }
                if (cursor != null) {
                }
            } catch (Exception e3) {
                e = e3;
                cursor = null;
            } catch (Throwable th) {
                th = th;
                if (r8 != 0) {
                    r8.close();
                }
                throw th;
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            r8 = trim;
            if (r8 != 0 && !r8.isClosed()) {
                r8.close();
            }
            throw th;
        }
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
    */
    private static ArrayList<SearchHistory> getAllSearchHistory(SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        Cursor cursor;
        ArrayList<SearchHistory> arrayList;
        Exception e2;
        try {
            cursor = sQLiteDatabase.query(TABLE_NAME, null, null, null, null, null, "_id desc");
        } catch (Exception e3) {
            arrayList = null;
            e2 = e3;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
        try {
            try {
                arrayList = new ArrayList<>();
                while (cursor.moveToNext()) {
                    try {
                        arrayList.add(new SearchHistory(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("word")), cursor.getString(cursor.getColumnIndex("tag")), cursor.getString(cursor.getColumnIndex("taglist")), cursor.getLong(cursor.getColumnIndex("search_time")), cursor.getInt(cursor.getColumnIndex("type")), cursor.getString(cursor.getColumnIndex("cid"))));
                    } catch (Exception e4) {
                        e2 = e4;
                        if (OKLog.E) {
                            OKLog.e(TAG, e2);
                        }
                    }
                }
            } catch (Exception e5) {
                arrayList = null;
                e2 = e5;
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static void saveSearchHistory(String str) {
        saveSearchHistory(str, 0, null);
    }
}
