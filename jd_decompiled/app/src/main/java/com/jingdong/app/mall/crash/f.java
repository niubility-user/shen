package com.jingdong.app.mall.crash;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes3.dex */
public class f {
    public static void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS JDCallOnTimeTable ('id' INTEGER PRIMARY KEY  NOT NULL ,className TEXT,startTime TEXT,lastModifiedTime TEXT)");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void b() {
        try {
            try {
                DBHelperUtil.getDatabase().delete("JDCallOnTimeTable", null, null);
            } catch (Exception e2) {
                if (Log.E) {
                    e2.printStackTrace();
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static void c(String str, String str2, Date date) {
        try {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("className", str);
                contentValues.put("startTime", str2);
                contentValues.put("lastModifiedTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                database.insert("JDCallOnTimeTable", null, contentValues);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006f, code lost:
        if (r1.isClosed() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0085, code lost:
        if (r1.isClosed() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0087, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.jingdong.app.mall.crash.d> d() {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r3 = "select * from JDCallOnTimeTable order by lastModifiedTime DESC"
            android.database.Cursor r1 = r2.rawQuery(r3, r1)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            if (r1 != 0) goto L21
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            if (r1 == 0) goto L20
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L20
            r1.close()
        L20:
            return r0
        L21:
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            int r2 = r1.getCount()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            if (r2 == 0) goto L66
            int r2 = r1.getCount()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r3 = 0
        L2f:
            if (r3 >= r2) goto L66
            r1.moveToPosition(r3)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            com.jingdong.app.mall.crash.d r4 = new com.jingdong.app.mall.crash.d     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r4.<init>()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r5 = "className"
            int r5 = r1.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r5 = r1.getString(r5)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r4.b = r5     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r5 = "startTime"
            int r5 = r1.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r5 = r1.getString(r5)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r4.a = r5     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r5 = "lastModifiedTime"
            int r5 = r1.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r5 = r1.getString(r5)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r4.f8337c = r5     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r0.add(r4)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r1.moveToNext()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            int r3 = r3 + 1
            goto L2f
        L66:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            if (r1 == 0) goto L8a
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L8a
            goto L87
        L72:
            r0 = move-exception
            goto L8b
        L74:
            r2 = move-exception
            boolean r3 = com.jingdong.corelib.utils.Log.E     // Catch: java.lang.Throwable -> L72
            if (r3 == 0) goto L7c
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L72
        L7c:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            if (r1 == 0) goto L8a
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L8a
        L87:
            r1.close()
        L8a:
            return r0
        L8b:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            if (r1 == 0) goto L99
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L99
            r1.close()
        L99:
            goto L9b
        L9a:
            throw r0
        L9b:
            goto L9a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.crash.f.d():java.util.List");
    }
}
