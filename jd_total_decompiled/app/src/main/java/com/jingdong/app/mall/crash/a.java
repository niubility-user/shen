package com.jingdong.app.mall.crash;

import android.database.Cursor;
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

    /* JADX WARN: Code restructure failed: missing block: B:92:0x004b, code lost:
        if (r1.isClosed() == false) goto L85;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean e(SQLiteDatabase sQLiteDatabase, String str) {
        boolean z = false;
        if (str == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            try {
                cursor = sQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type ='table' and name ='" + str.trim() + "' ", null);
                if (cursor.moveToNext() && cursor.getInt(0) > 0) {
                    a = true;
                    z = true;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null) {
                }
            }
            return z;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
}
