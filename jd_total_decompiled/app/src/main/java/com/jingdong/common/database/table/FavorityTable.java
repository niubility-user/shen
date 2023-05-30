package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.jdsdk.utils.FormatUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Date;

/* loaded from: classes5.dex */
public class FavorityTable {
    private static final String TAG = "FavorityTable";
    public static final String TB_CLOUMN_BROWSE_TIME = "browseTime";
    public static final String TB_CLOUMN_PRODUCT_CODE = "productCode";
    public static final String TB_CLOUMN_PRODUCT_NAME = "productName";
    public static final String TB_CLOUMN_USER_NAME = "userName";
    public static final String TB_FAVORITY_TABLE = "favority";

    public static void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE favority('id' INTEGER PRIMARY KEY  NOT NULL ,productName TEXT,productCode LONG,userName TEXT,'browseTime' DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    public static void delAllFavority() {
        try {
            try {
                DBHelperUtil.getDatabase().delete("favority", "1=1", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x007d A[Catch: all -> 0x00a7, TRY_ENTER, TryCatch #2 {, blocks: (B:8:0x0038, B:18:0x007d, B:19:0x0080, B:37:0x00a0, B:38:0x00a3, B:39:0x00a6, B:32:0x0098), top: B:46:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized void insertOrUpdateFavority(long j2, String str, boolean z) {
        SQLiteDatabase database;
        ContentValues contentValues;
        String[] strArr;
        synchronized (FavorityTable.class) {
            Cursor cursor = null;
            try {
                try {
                    database = DBHelperUtil.getDatabase();
                    contentValues = new ContentValues();
                    strArr = new String[]{j2 + ""};
                    contentValues.put("userName", LoginUserBase.getLoginUserName());
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            if (z) {
                database.delete("favority", "productCode =?", strArr);
                DBHelperUtil.closeDatabase();
                return;
            }
            Cursor query = database.query("favority", null, "productCode =?", strArr, null, null, null);
            if (query != null) {
                try {
                } catch (Exception e3) {
                    e = e3;
                    cursor = query;
                    if (OKLog.E) {
                        OKLog.e(TAG, e);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    DBHelperUtil.closeDatabase();
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    DBHelperUtil.closeDatabase();
                    throw th;
                }
                if (query.getCount() != 0) {
                    contentValues.put("browseTime", FormatUtils.formatDate(new Date()));
                    database.update("favority", contentValues, "productCode =?", strArr);
                    if (query != null) {
                        query.close();
                    }
                    DBHelperUtil.closeDatabase();
                }
            }
            contentValues.put("productCode", Long.valueOf(j2));
            contentValues.put("productName", str);
            database.insert("favority", null, contentValues);
            if (query != null) {
            }
            DBHelperUtil.closeDatabase();
        }
    }

    public static boolean queryIsFavorited(long j2) {
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().query("favority", null, "productCode =? and userName =?", new String[]{j2 + "", LoginUserBase.getLoginUserName()}, null, null, null);
                if (cursor != null) {
                    if (cursor.getCount() != 0) {
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        DBHelperUtil.closeDatabase();
                        return true;
                    }
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
                return false;
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
                return false;
            }
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    public static void upgrade(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("drop table if exists favority");
    }
}
