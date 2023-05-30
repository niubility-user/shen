package com.jingdong.jdsdk.c.b;

import android.database.Cursor;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.jdroom.a.c;

/* loaded from: classes14.dex */
public class a {
    private static final String a = "a";

    public static synchronized void a() {
        synchronized (a.class) {
            try {
                DBHelperUtil.getDatabase().execSQL("drop table if exists JD_ReminderNewTable");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0036, code lost:
        if (r2.isClosed() == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0038, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x004e, code lost:
        if (r2.isClosed() == false) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized java.util.ArrayList<com.jingdong.sdk.jdroom.a.c> b() {
        /*
            java.lang.Class<com.jingdong.jdsdk.c.b.a> r0 = com.jingdong.jdsdk.c.b.a.class
            monitor-enter(r0)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L5f
            r1.<init>()     // Catch: java.lang.Throwable -> L5f
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            java.lang.String r4 = "SELECT * FROM JD_ReminderNewTable"
            android.database.Cursor r2 = r3.rawQuery(r4, r2)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            if (r2 != 0) goto L22
            if (r2 == 0) goto L20
            boolean r3 = r2.isClosed()     // Catch: java.lang.Throwable -> L5f
            if (r3 != 0) goto L20
            r2.close()     // Catch: java.lang.Throwable -> L5f
        L20:
            monitor-exit(r0)
            return r1
        L22:
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            if (r3 == 0) goto L30
            com.jingdong.sdk.jdroom.a.c r3 = c(r2)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r1.add(r3)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            goto L22
        L30:
            if (r2 == 0) goto L51
            boolean r3 = r2.isClosed()     // Catch: java.lang.Throwable -> L5f
            if (r3 != 0) goto L51
        L38:
            r2.close()     // Catch: java.lang.Throwable -> L5f
            goto L51
        L3c:
            r1 = move-exception
            goto L53
        L3e:
            r3 = move-exception
            boolean r4 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L3c
            if (r4 == 0) goto L48
            java.lang.String r4 = com.jingdong.jdsdk.c.b.a.a     // Catch: java.lang.Throwable -> L3c
            com.jingdong.sdk.oklog.OKLog.e(r4, r3)     // Catch: java.lang.Throwable -> L3c
        L48:
            if (r2 == 0) goto L51
            boolean r3 = r2.isClosed()     // Catch: java.lang.Throwable -> L5f
            if (r3 != 0) goto L51
            goto L38
        L51:
            monitor-exit(r0)
            return r1
        L53:
            if (r2 == 0) goto L5e
            boolean r3 = r2.isClosed()     // Catch: java.lang.Throwable -> L5f
            if (r3 != 0) goto L5e
            r2.close()     // Catch: java.lang.Throwable -> L5f
        L5e:
            throw r1     // Catch: java.lang.Throwable -> L5f
        L5f:
            r1 = move-exception
            monitor-exit(r0)
            goto L63
        L62:
            throw r1
        L63:
            goto L62
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdsdk.c.b.a.b():java.util.ArrayList");
    }

    private static c c(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        c cVar = new c();
        cVar.b = cursor.getString(cursor.getColumnIndex("businessType"));
        cVar.f14966c = cursor.getString(cursor.getColumnIndex("reminderShowTag"));
        cVar.d = cursor.getString(cursor.getColumnIndex("identificationId"));
        cVar.f14967e = cursor.getString(cursor.getColumnIndex("reminderTitle"));
        cVar.f14968f = cursor.getString(cursor.getColumnIndex("reminderImgUrl"));
        cVar.f14969g = cursor.getDouble(cursor.getColumnIndex("startTimeMillis"));
        cVar.f14970h = cursor.getDouble(cursor.getColumnIndex("notificationTimeMillis"));
        cVar.f14971i = cursor.getDouble(cursor.getColumnIndex("insertTime"));
        cVar.f14972j = cursor.getString(cursor.getColumnIndex("jump"));
        cVar.f14973k = cursor.getString(cursor.getColumnIndex("extra"));
        cVar.f14974l = cursor.getString(cursor.getColumnIndex("more"));
        cVar.f14975m = cursor.getInt(cursor.getColumnIndex("requestCode"));
        return cVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0034, code lost:
        if (r0.isClosed() == false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean d() {
        /*
            r0 = 0
            r1 = 1
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            java.lang.String r3 = "SELECT COUNT(*) FROM JD_ReminderNewTable"
            android.database.Cursor r0 = r2.rawQuery(r3, r0)     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            boolean r2 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            r3 = 0
            if (r2 == 0) goto L18
            int r2 = r0.getInt(r3)     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            goto L19
        L18:
            r2 = 0
        L19:
            if (r2 == 0) goto L1c
            r1 = 0
        L1c:
            if (r0 == 0) goto L37
            boolean r2 = r0.isClosed()
            if (r2 != 0) goto L37
        L24:
            r0.close()
            goto L37
        L28:
            r1 = move-exception
            goto L38
        L2a:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L28
            if (r0 == 0) goto L37
            boolean r2 = r0.isClosed()
            if (r2 != 0) goto L37
            goto L24
        L37:
            return r1
        L38:
            if (r0 == 0) goto L43
            boolean r2 = r0.isClosed()
            if (r2 != 0) goto L43
            r0.close()
        L43:
            goto L45
        L44:
            throw r1
        L45:
            goto L44
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdsdk.c.b.a.d():boolean");
    }
}
