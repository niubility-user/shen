package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class UserNamesTable implements IJdTable {
    private static final String TAG = "UserNamesTable";
    public static final String TB_CLOUMN_NAME = "name";
    public static final String USER_NAMES_TABLE = "usernames";

    public static synchronized void delUserName(String str) {
        synchronized (UserNamesTable.class) {
            try {
                DBHelperUtil.getDatabase().delete(USER_NAMES_TABLE, "name=?", new String[]{str});
            } catch (Exception e2) {
                if (OKLog.D) {
                    OKLog.e(TAG, e2);
                }
            }
            DBHelperUtil.closeDatabase();
        }
    }

    public static synchronized ArrayList<String> getUserNames() {
        ArrayList<String> arrayList;
        synchronized (UserNamesTable.class) {
            arrayList = new ArrayList<>();
            Cursor cursor = null;
            try {
                cursor = DBHelperUtil.getDatabase().query(USER_NAMES_TABLE, new String[]{"name"}, null, null, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    for (int i2 = 0; i2 < cursor.getCount(); i2++) {
                        cursor.moveToPosition(i2);
                        arrayList.add(cursor.getString(cursor.getColumnIndex("name")));
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (cursor != null) {
                    cursor.close();
                }
            }
            DBHelperUtil.closeDatabase();
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x002b, code lost:
        if (r2.getCount() <= 0) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized void insertUserName(String str) {
        synchronized (UserNamesTable.class) {
            Cursor cursor = null;
            try {
                try {
                    SQLiteDatabase database = DBHelperUtil.getDatabase();
                    Cursor query = database.query(USER_NAMES_TABLE, new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
                    if (query != null) {
                        try {
                        } catch (Exception e2) {
                            e = e2;
                            cursor = query;
                            if (OKLog.E) {
                                OKLog.e(TAG, e);
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            DBHelperUtil.closeDatabase();
                        } catch (Throwable th) {
                            th = th;
                            cursor = query;
                            if (cursor != null) {
                                cursor.close();
                            }
                            DBHelperUtil.closeDatabase();
                            throw th;
                        }
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", str);
                    database.insert(USER_NAMES_TABLE, null, contentValues);
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
            DBHelperUtil.closeDatabase();
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE usernames('id' INTEGER PRIMARY KEY  NOT NULL ,name TEXT)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists usernames");
    }
}
