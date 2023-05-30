package com.jingdong.jdsdk.mta;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.secure.Base64;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes14.dex */
public class JDMtaCacheTable implements IJdTable {
    public static final String TABLE_NAME = "jd_mta_cache_file";
    private static final String TAG = "JDMtaCacheTable";
    public static final String TB_COLUMN_ID = "id";
    public static final String TB_COLUMN_SOURCE = "source";

    public static synchronized void delete(String str) {
        synchronized (JDMtaCacheTable.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "JDMtaCacheTable delete() -->> ");
            }
            try {
                DBHelperUtil.getDatabase().delete(TABLE_NAME, "id = ?", new String[]{str});
                if (OKLog.D) {
                    OKLog.d(TAG, "JDMtaCacheTable delete() -->> ok");
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            DBHelperUtil.closeDatabase();
        }
    }

    @SuppressLint({"NewApi"})
    public static synchronized String findSource(String str) {
        synchronized (JDMtaCacheTable.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "JDMtaCacheTable findSource() -->> ");
            }
            Cursor cursor = null;
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                new ContentValues().put("id", str);
                cursor = database.query(TABLE_NAME, null, "id = ? ", new String[]{str}, null, null, null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
            if (cursor.moveToNext()) {
                String str2 = new String(Base64.decode(cursor.getString(cursor.getColumnIndex("source"))));
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
                return str2;
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            return "";
        }
    }

    public static synchronized void insertOrUpdate(String str, String str2) {
        SQLiteDatabase database;
        ContentValues contentValues;
        String[] strArr;
        Cursor query;
        synchronized (JDMtaCacheTable.class) {
            String encodeBytes = Base64.encodeBytes(str2.getBytes());
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "JDMtaCacheTable insertOrUpdate() -->> ");
            }
            Cursor cursor = null;
            try {
                try {
                    database = DBHelperUtil.getDatabase();
                    contentValues = new ContentValues();
                    contentValues.put("id", str);
                    contentValues.put("source", encodeBytes);
                    strArr = new String[]{str};
                    if (OKLog.D) {
                        OKLog.d(TAG, "id -->> " + str + " , source:" + encodeBytes);
                    }
                    query = database.query(TABLE_NAME, null, "id = ? ", strArr, null, null, null);
                } catch (Exception e2) {
                    e = e2;
                }
                if (query != null) {
                    try {
                    } catch (Exception e3) {
                        cursor = query;
                        e = e3;
                        if (OKLog.E) {
                            OKLog.e(TAG, e);
                        }
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        DBHelperUtil.closeDatabase();
                    } catch (Throwable th) {
                        cursor = query;
                        th = th;
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        DBHelperUtil.closeDatabase();
                        throw th;
                    }
                    if (query.getCount() > 0) {
                        database.update(TABLE_NAME, contentValues, "id = ? ", strArr);
                        if (query != null && !query.isClosed()) {
                            query.close();
                        }
                        DBHelperUtil.closeDatabase();
                    }
                }
                database.insert(TABLE_NAME, null, contentValues);
                if (query != null) {
                    query.close();
                }
                DBHelperUtil.closeDatabase();
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE jd_mta_cache_file('id' TEXT PRIMARY KEY  NOT NULL ,source TEXT)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists jd_mta_cache_file");
    }
}
