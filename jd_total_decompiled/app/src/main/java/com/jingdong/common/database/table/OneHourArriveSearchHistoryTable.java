package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jingdong.common.entity.OneHourArriveSearchHistory;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class OneHourArriveSearchHistoryTable implements IJdTable {
    public static final int MAX_SEARCH_HISTORY_NUM = 20;
    public static final String TABLE_NAME = "onehour_arrive_search_history";
    private static final String TAG = "OneHourArriveSearchHistoryTable";
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

    public static void deleteHistory(OneHourArriveSearchHistory oneHourArriveSearchHistory) {
        try {
            try {
                deleteHistory(DBHelperUtil.getDatabase(), oneHourArriveSearchHistory.getId());
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
        ArrayList<OneHourArriveSearchHistory> allSearchHistory = getAllSearchHistory(sQLiteDatabase);
        if (allSearchHistory == null) {
            return;
        }
        int size = (allSearchHistory.size() - 20) + 1;
        if (allSearchHistory.size() < 1 || size < 1) {
            return;
        }
        int i2 = 0;
        for (int size2 = allSearchHistory.size() - 1; size2 >= 0 && i2 < size; size2--) {
            OneHourArriveSearchHistory oneHourArriveSearchHistory = allSearchHistory.get(size2);
            if (oneHourArriveSearchHistory != null) {
                deleteHistory(sQLiteDatabase, oneHourArriveSearchHistory.getId());
                i2++;
            }
        }
    }

    public static ArrayList<OneHourArriveSearchHistory> getAllSearchHistory() {
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
    */
    private static OneHourArriveSearchHistory getSearchHistoryFromText(SQLiteDatabase sQLiteDatabase, String str, int i2) {
        Cursor cursor;
        ?? r6 = 0;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        if (OKLog.D) {
            OKLog.d(TAG, "getSearchHistoryFromText word -->>  " + trim);
        }
        try {
            try {
                cursor = sQLiteDatabase.query(TABLE_NAME, new String[]{"_id", "search_time", "word", "tag", "type"}, "word = ? and type = ? ", new String[]{trim, i2 + ""}, null, null, null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            OneHourArriveSearchHistory oneHourArriveSearchHistory = new OneHourArriveSearchHistory(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("word")), cursor.getString(cursor.getColumnIndex("tag")), cursor.getLong(cursor.getColumnIndex("search_time")), cursor.getInt(cursor.getColumnIndex("type")));
                            if (cursor != null && !cursor.isClosed()) {
                                cursor.close();
                            }
                            return oneHourArriveSearchHistory;
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
                if (r6 != 0) {
                    r6.close();
                }
                throw th;
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            r6 = trim;
            if (r6 != 0 && !r6.isClosed()) {
                r6.close();
            }
            throw th;
        }
    }

    public static void saveSearchHistory(String str, int i2) {
        saveSearchHistory(str, "", i2);
    }

    private void upgradeDatabaseToVersion1(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE onehour_arrive_search_history ADD COLUMN tag TEXT");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS onehour_arrive_search_history('_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,word TEXT,tag TEXT,type INTEGER,search_time DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (i2 < 20256) {
            sQLiteDatabase.execSQL("drop table if exists onehour_arrive_search_history");
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
            OneHourArriveSearchHistory searchHistoryFromText = getSearchHistoryFromText(database, trim, i2);
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
    */
    private static ArrayList<OneHourArriveSearchHistory> getAllSearchHistory(SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        Cursor cursor;
        ArrayList<OneHourArriveSearchHistory> arrayList;
        Exception e2;
        try {
            cursor = sQLiteDatabase.query(TABLE_NAME, null, null, null, null, null, "_id desc");
            try {
                try {
                    arrayList = new ArrayList<>();
                    while (cursor.moveToNext()) {
                        try {
                            arrayList.add(new OneHourArriveSearchHistory(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("word")), cursor.getString(cursor.getColumnIndex("tag")), cursor.getLong(cursor.getColumnIndex("search_time")), cursor.getInt(cursor.getColumnIndex("type"))));
                        } catch (Exception e3) {
                            e2 = e3;
                            if (OKLog.E) {
                                OKLog.e(TAG, e2);
                            }
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Exception e4) {
                arrayList = null;
                e2 = e4;
            }
        } catch (Exception e5) {
            arrayList = null;
            e2 = e5;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
    }

    public static void saveSearchHistory(String str) {
        saveSearchHistory(str, 0);
    }
}
