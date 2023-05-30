package com.jingdong.jdpush_new.f;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.jdpush_new.j.m;
import org.json.JSONException;

/* loaded from: classes12.dex */
public class e extends b {

    /* renamed from: c  reason: collision with root package name */
    private static e f12820c;

    public e(Context context) {
        super(context);
    }

    private synchronized void c(ContentValues contentValues) {
        try {
            this.b.insert("jdpush_msg", null, contentValues);
        } catch (Exception unused) {
        }
    }

    public static synchronized void e(SQLiteDatabase sQLiteDatabase) {
        synchronized (e.class) {
            try {
                StringBuilder sb = new StringBuilder(200);
                sb.append("CREATE TABLE IF NOT EXISTS ");
                sb.append("jdpush_msg");
                sb.append(" (");
                sb.append("id");
                sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
                sb.append("app_id");
                sb.append(" VARCHAR,");
                sb.append("msg_seq");
                sb.append(" VARCHAR,");
                sb.append("msg");
                sb.append(" VARCHAR,");
                sb.append("mui");
                sb.append(" VARCHAR,");
                sb.append(NotificationMessageSummary.ECHO);
                sb.append(" VARCHAR,");
                sb.append("create_time");
                sb.append(" VARCHAR,");
                sb.append("status");
                sb.append(" VARCHAR)");
                sQLiteDatabase.execSQL(sb.toString());
            } catch (Exception unused) {
            }
        }
    }

    private synchronized void f() {
        try {
            this.b.execSQL("DELETE FROM jdpush_msg WHERE id =(SELECT MIN(id) FROM jdpush_msg);");
        } catch (Exception unused) {
        }
    }

    public static synchronized void g(SQLiteDatabase sQLiteDatabase) {
        synchronized (e.class) {
            try {
                StringBuilder sb = new StringBuilder(200);
                sb.append("DORP TABLE IF EXISTS ");
                sb.append("jdpush_msg");
                sQLiteDatabase.execSQL(sb.toString());
            } catch (Exception unused) {
            }
        }
    }

    private synchronized ContentValues h(com.jingdong.jdpush_new.g.c.c cVar) throws JSONException {
        ContentValues contentValues;
        ContentValues contentValues2 = null;
        try {
            contentValues = new ContentValues();
            try {
                contentValues.put("app_id", m.d(cVar.a()));
                contentValues.put("msg_seq", m.d(cVar.f()));
                contentValues.put("msg", m.d(cVar.e()));
                contentValues.put("status", m.d(cVar.j()));
                contentValues.put("create_time", m.d(cVar.c()));
                contentValues.put("mui", m.d(cVar.g()));
                contentValues.put(NotificationMessageSummary.ECHO, m.d(cVar.d()));
            } catch (Exception unused) {
                contentValues2 = contentValues;
                contentValues = contentValues2;
                return contentValues;
            }
        } catch (Exception unused2) {
        }
        return contentValues;
    }

    public static e i(Context context) {
        if (f12820c == null) {
            f12820c = new e(context);
        }
        return f12820c;
    }

    private synchronized int j() {
        int count;
        Cursor query = this.b.query("jdpush_msg", null, null, null, null, null, null);
        count = query != null ? query.getCount() : 0;
        if (query != null) {
            query.close();
        }
        return count;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002f, code lost:
        if (r11 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0031, code lost:
        r11.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003c, code lost:
        if (r11 != null) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized boolean k(java.lang.String r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L41
            r1.<init>()     // Catch: java.lang.Throwable -> L41
            java.lang.String r2 = "mui = '"
            r1.append(r2)     // Catch: java.lang.Throwable -> L41
            r1.append(r11)     // Catch: java.lang.Throwable -> L41
            java.lang.String r11 = "'"
            r1.append(r11)     // Catch: java.lang.Throwable -> L41
            java.lang.String r5 = r1.toString()     // Catch: java.lang.Throwable -> L41
            r11 = 0
            android.database.sqlite.SQLiteDatabase r2 = r10.b     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L3c
            java.lang.String r3 = "jdpush_msg"
            r4 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L3c
            if (r11 == 0) goto L2f
            int r1 = r11.getCount()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L3c
            if (r1 <= 0) goto L2f
            r0 = 1
        L2f:
            if (r11 == 0) goto L3f
        L31:
            r11.close()     // Catch: java.lang.Throwable -> L41
            goto L3f
        L35:
            r0 = move-exception
            if (r11 == 0) goto L3b
            r11.close()     // Catch: java.lang.Throwable -> L41
        L3b:
            throw r0     // Catch: java.lang.Throwable -> L41
        L3c:
            if (r11 == 0) goto L3f
            goto L31
        L3f:
            monitor-exit(r10)
            return r0
        L41:
            r11 = move-exception
            monitor-exit(r10)
            goto L45
        L44:
            throw r11
        L45:
            goto L44
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdpush_new.f.e.k(java.lang.String):boolean");
    }

    public synchronized boolean d(com.jingdong.jdpush_new.g.c.c cVar) {
        boolean z;
        z = false;
        try {
            b();
            if (j() >= 50) {
                f();
            }
            if (k(m.d(cVar.g()))) {
                z = true;
            } else {
                c(h(cVar));
            }
        } catch (JSONException unused) {
        }
        a();
        return z;
    }
}
