package com.jingdong.app.mall.crash;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    */
    public static List<d> d() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("select * from JDCallOnTimeTable order by lastModifiedTime DESC", null);
            } catch (Exception e2) {
                if (Log.E) {
                    e2.printStackTrace();
                }
                DBHelperUtil.closeDatabase();
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                DBHelperUtil.closeDatabase();
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return arrayList;
            }
            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                int count = cursor.getCount();
                for (int i2 = 0; i2 < count; i2++) {
                    cursor.moveToPosition(i2);
                    d dVar = new d();
                    dVar.b = cursor.getString(cursor.getColumnIndex("className"));
                    dVar.a = cursor.getString(cursor.getColumnIndex("startTime"));
                    dVar.f8337c = cursor.getString(cursor.getColumnIndex("lastModifiedTime"));
                    arrayList.add(dVar);
                    cursor.moveToNext();
                }
            }
            DBHelperUtil.closeDatabase();
            if (cursor != null) {
            }
            return arrayList;
        } catch (Throwable th) {
            DBHelperUtil.closeDatabase();
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }
}
