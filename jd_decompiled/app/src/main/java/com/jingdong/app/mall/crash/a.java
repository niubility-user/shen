package com.jingdong.app.mall.crash;

import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import java.util.Date;
import java.util.List;

/* loaded from: classes3.dex */
public class a {
    protected static boolean a;

    public static void a() {
        SQLiteDatabase database;
        try {
            try {
                database = DBHelperUtil.getDatabase();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (e(database, "JDCallOnTimeTable")) {
                return;
            }
            f.a(database);
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static void b() {
        f.b();
    }

    public static void c(String str, String str2, Date date) {
        f.c(str, str2, date);
    }

    public static List<d> d() {
        return f.d();
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x004b, code lost:
        if (r1.isClosed() == false) goto L50;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean e(android.database.sqlite.SQLiteDatabase r4, java.lang.String r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L4
            return r0
        L4:
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r2.<init>()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r3 = "select count(*) as c from sqlite_master where type ='table' and name ='"
            r2.append(r3)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r5 = r5.trim()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r2.append(r5)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r5 = "' "
            r2.append(r5)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r5 = r2.toString()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            android.database.Cursor r1 = r4.rawQuery(r5, r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            boolean r4 = r1.moveToNext()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r5 = 1
            if (r4 == 0) goto L33
            int r4 = r1.getInt(r0)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r4 <= 0) goto L33
            com.jingdong.app.mall.crash.a.a = r5     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r0 = 1
        L33:
            if (r1 == 0) goto L4e
            boolean r4 = r1.isClosed()
            if (r4 != 0) goto L4e
        L3b:
            r1.close()
            goto L4e
        L3f:
            r4 = move-exception
            goto L4f
        L41:
            r4 = move-exception
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L3f
            if (r1 == 0) goto L4e
            boolean r4 = r1.isClosed()
            if (r4 != 0) goto L4e
            goto L3b
        L4e:
            return r0
        L4f:
            if (r1 == 0) goto L5a
            boolean r5 = r1.isClosed()
            if (r5 != 0) goto L5a
            r1.close()
        L5a:
            goto L5c
        L5b:
            throw r4
        L5c:
            goto L5b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.crash.a.e(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }
}
