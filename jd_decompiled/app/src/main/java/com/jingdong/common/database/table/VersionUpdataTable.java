package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class VersionUpdataTable implements IJdTable {
    public static final int MAX_SAVE_NUM = 1000;
    public static final String TAG = "VersionUpdataTable";
    public static final String TB_CLOUMN_FILE_MD5 = "file_md";
    public static final String TB_CLOUMN_VERSION_ID = "version";
    public static final String TB_VERSION_UPDATE_TABLE = "VersionUpdateTable";

    public static void delAll() {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TB_VERSION_UPDATE_TABLE, "1=1", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static void deleteVersion(String str) {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TB_VERSION_UPDATE_TABLE, "version = ? ", new String[]{str});
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static String getMD5(String str) {
        Cursor cursor = null;
        try {
            try {
                Cursor rawQuery = DBHelperUtil.getDatabase().rawQuery("SELECT file_md FROM VersionUpdateTable WHERE version = '" + str + "'", null);
                if (rawQuery == null) {
                    if (rawQuery != null && !rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                    DBHelperUtil.closeDatabase();
                    return "";
                }
                if (OKLog.D) {
                    OKLog.d(TAG, " c:" + rawQuery);
                }
                rawQuery.moveToFirst();
                String string = rawQuery.getString(rawQuery.getColumnIndex(TB_CLOUMN_FILE_MD5));
                if (rawQuery != null && !rawQuery.isClosed()) {
                    rawQuery.close();
                }
                DBHelperUtil.closeDatabase();
                return string;
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0 && !cursor.isClosed()) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
                return "";
            }
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    public static void insertVersion(String str, String str2) {
        delAll();
        try {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("version", str);
                contentValues.put(TB_CLOUMN_FILE_MD5, str2);
                long insert = database.insert(TB_VERSION_UPDATE_TABLE, null, contentValues);
                if (OKLog.D) {
                    OKLog.d(TAG, " value:" + insert);
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE VersionUpdateTable('_id' INTEGER PRIMARY KEY  NOT NULL ,version TEXT , file_md TEXT ) ");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists VersionUpdateTable");
    }
}
