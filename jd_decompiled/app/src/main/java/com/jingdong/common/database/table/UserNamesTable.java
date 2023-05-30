package com.jingdong.common.database.table;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void insertUserName(java.lang.String r12) {
        /*
            java.lang.Class<com.jingdong.common.database.table.UserNamesTable> r0 = com.jingdong.common.database.table.UserNamesTable.class
            monitor-enter(r0)
            r1 = 0
            android.database.sqlite.SQLiteDatabase r10 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            r2 = 1
            java.lang.String[] r4 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            java.lang.String r3 = "name"
            r5 = 0
            r4[r5] = r3     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            java.lang.String r6 = "name=?"
            java.lang.String[] r7 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            r7[r5] = r12     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            java.lang.String r3 = "usernames"
            r8 = 0
            r9 = 0
            r11 = 0
            r2 = r10
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r11
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            if (r2 == 0) goto L2d
            int r3 = r2.getCount()     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L48
            if (r3 > 0) goto L3c
        L2d:
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L48
            r3.<init>()     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L48
            java.lang.String r4 = "name"
            r3.put(r4, r12)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L48
            java.lang.String r12 = "usernames"
            r10.insert(r12, r1, r3)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L48
        L3c:
            if (r2 == 0) goto L41
            r2.close()     // Catch: java.lang.Throwable -> L68
        L41:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()     // Catch: java.lang.Throwable -> L68
            goto L5d
        L45:
            r12 = move-exception
            r1 = r2
            goto L5f
        L48:
            r12 = move-exception
            r1 = r2
            goto L4e
        L4b:
            r12 = move-exception
            goto L5f
        L4d:
            r12 = move-exception
        L4e:
            boolean r2 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L4b
            if (r2 == 0) goto L57
            java.lang.String r2 = "UserNamesTable"
            com.jingdong.sdk.oklog.OKLog.e(r2, r12)     // Catch: java.lang.Throwable -> L4b
        L57:
            if (r1 == 0) goto L41
            r1.close()     // Catch: java.lang.Throwable -> L68
            goto L41
        L5d:
            monitor-exit(r0)
            return
        L5f:
            if (r1 == 0) goto L64
            r1.close()     // Catch: java.lang.Throwable -> L68
        L64:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()     // Catch: java.lang.Throwable -> L68
            throw r12     // Catch: java.lang.Throwable -> L68
        L68:
            r12 = move-exception
            monitor-exit(r0)
            goto L6c
        L6b:
            throw r12
        L6c:
            goto L6b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.UserNamesTable.insertUserName(java.lang.String):void");
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
