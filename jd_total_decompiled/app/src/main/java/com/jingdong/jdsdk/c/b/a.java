package com.jingdong.jdsdk.c.b;

import android.database.Cursor;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.jdroom.a.c;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

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
    */
    public static synchronized ArrayList<c> b() {
        synchronized (a.class) {
            ArrayList<c> arrayList = new ArrayList<>();
            Cursor cursor = null;
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT * FROM JD_ReminderNewTable", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(a, e2);
                }
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return arrayList;
            }
            while (cursor.moveToNext()) {
                arrayList.add(c(cursor));
            }
            if (cursor != null) {
            }
            return arrayList;
        }
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
    */
    public static boolean d() {
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT COUNT(*) FROM JD_ReminderNewTable", null);
                r1 = (cursor.moveToFirst() ? cursor.getInt(0) : 0) == 0;
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null) {
                }
            }
            return r1;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
}
