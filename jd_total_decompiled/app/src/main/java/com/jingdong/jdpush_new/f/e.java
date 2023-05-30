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
    */
    private synchronized boolean k(String str) {
        boolean z;
        z = false;
        Cursor cursor = null;
        try {
            cursor = this.b.query("jdpush_msg", null, "mui = '" + str + "'", null, null, null, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    z = true;
                }
            }
        } catch (Exception unused) {
        }
        return z;
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
