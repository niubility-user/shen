package com.jingdong.common.database.table;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void insertOrUpdateFavority(long r16, java.lang.String r18, boolean r19) {
        /*
            java.lang.Class<com.jingdong.common.database.table.FavorityTable> r1 = com.jingdong.common.database.table.FavorityTable.class
            monitor-enter(r1)
            r2 = 0
            android.database.sqlite.SQLiteDatabase r0 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            android.content.ContentValues r11 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            r11.<init>()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            java.lang.String r12 = "productCode =?"
            r3 = 1
            java.lang.String[] r13 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            r3 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            r4.<init>()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            r14 = r16
            r4.append(r14)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            java.lang.String r5 = ""
            r4.append(r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            r13[r3] = r4     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            java.lang.String r3 = "userName"
            java.lang.String r4 = com.jingdong.common.login.LoginUserBase.getLoginUserName()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            r11.put(r3, r4)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            if (r19 == 0) goto L3d
            java.lang.String r3 = "favority"
            r0.delete(r3, r12, r13)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()     // Catch: java.lang.Throwable -> La7
            monitor-exit(r1)
            return
        L3d:
            java.lang.String r4 = "favority"
            r5 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r3 = r0
            r6 = r12
            r7 = r13
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            if (r3 == 0) goto L66
            int r4 = r3.getCount()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            if (r4 == 0) goto L66
            java.lang.String r2 = "browseTime"
            java.util.Date r4 = new java.util.Date     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            r4.<init>()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            java.lang.String r4 = com.jingdong.jdsdk.utils.FormatUtils.formatDate(r4)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            r11.put(r2, r4)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            java.lang.String r2 = "favority"
            r0.update(r2, r11, r12, r13)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            goto L7b
        L66:
            java.lang.String r4 = "productCode"
            java.lang.Long r5 = java.lang.Long.valueOf(r16)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            r11.put(r4, r5)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            java.lang.String r4 = "productName"
            r5 = r18
            r11.put(r4, r5)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            java.lang.String r4 = "favority"
            r0.insert(r4, r2, r11)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
        L7b:
            if (r3 == 0) goto L80
            r3.close()     // Catch: java.lang.Throwable -> La7
        L80:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()     // Catch: java.lang.Throwable -> La7
            goto L9c
        L84:
            r0 = move-exception
            r2 = r3
            goto L9e
        L87:
            r0 = move-exception
            r2 = r3
            goto L8d
        L8a:
            r0 = move-exception
            goto L9e
        L8c:
            r0 = move-exception
        L8d:
            boolean r3 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L8a
            if (r3 == 0) goto L96
            java.lang.String r3 = "FavorityTable"
            com.jingdong.sdk.oklog.OKLog.e(r3, r0)     // Catch: java.lang.Throwable -> L8a
        L96:
            if (r2 == 0) goto L80
            r2.close()     // Catch: java.lang.Throwable -> La7
            goto L80
        L9c:
            monitor-exit(r1)
            return
        L9e:
            if (r2 == 0) goto La3
            r2.close()     // Catch: java.lang.Throwable -> La7
        La3:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()     // Catch: java.lang.Throwable -> La7
            throw r0     // Catch: java.lang.Throwable -> La7
        La7:
            r0 = move-exception
            monitor-exit(r1)
            goto Lab
        Laa:
            throw r0
        Lab:
            goto Laa
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.FavorityTable.insertOrUpdateFavority(long, java.lang.String, boolean):void");
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
