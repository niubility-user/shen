package com.jingdong.jdpush_new.f;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdpush_new.j.m;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class f extends b {

    /* renamed from: c  reason: collision with root package name */
    private static f f12821c;

    public f(Context context) {
        super(context);
    }

    private synchronized void c(ContentValues contentValues) {
        try {
            this.b.insert("recordOpenPushInfo_msg", null, contentValues);
        } catch (Exception unused) {
        }
    }

    public static synchronized void e(com.jingdong.jdpush_new.g.c.d dVar, com.jingdong.jdpush_new.g.c.d dVar2) {
        synchronized (f.class) {
            if (dVar2 == null) {
                return;
            }
            if (dVar2.c() != null) {
                dVar.j(dVar2.c());
            }
            if (dVar2.a() != null) {
                dVar.h(dVar2.a());
            }
            if (dVar2.b() != null) {
                dVar.i(dVar2.b());
            }
            if (dVar2.d() != null) {
                dVar.k(dVar2.d());
            }
            if (dVar2.e() != null) {
                dVar.l(dVar2.e());
            }
            if (dVar2.f() != null) {
                dVar.m(dVar2.f());
            }
        }
    }

    public static synchronized void f(SQLiteDatabase sQLiteDatabase) {
        synchronized (f.class) {
            try {
                StringBuilder sb = new StringBuilder(200);
                sb.append("CREATE TABLE IF NOT EXISTS ");
                sb.append("recordOpenPushInfo_msg");
                sb.append(" (");
                sb.append("id");
                sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
                sb.append("app_id");
                sb.append(" VARCHAR,");
                sb.append("msg_body");
                sb.append(" VARCHAR,");
                sb.append("command");
                sb.append(" VARCHAR,");
                sb.append("msg_seq");
                sb.append(" VARCHAR,");
                sb.append("status");
                sb.append(" VARCHAR)");
                sQLiteDatabase.execSQL(sb.toString());
            } catch (Exception unused) {
            }
        }
    }

    private synchronized void g() {
        try {
            this.b.execSQL("DELETE FROM recordOpenPushInfo_msg WHERE id =(SELECT MIN(id) FROM recordOpenPushInfo_msg);");
        } catch (Exception unused) {
        }
    }

    public static synchronized void h(SQLiteDatabase sQLiteDatabase) {
        synchronized (f.class) {
            try {
                StringBuilder sb = new StringBuilder(200);
                sb.append("DORP TABLE IF EXISTS ");
                sb.append("recordOpenPushInfo_msg");
                sQLiteDatabase.execSQL(sb.toString());
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002e, code lost:
        if (r11 == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0030, code lost:
        r11.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001e, code lost:
        if (r11 != null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized com.jingdong.jdpush_new.g.c.d j(String str) {
        com.jingdong.jdpush_new.g.c.d dVar;
        Throwable th;
        Cursor cursor;
        dVar = null;
        try {
            cursor = this.b.query("recordOpenPushInfo_msg", null, "msg_seq=?", new String[]{m.d(str)}, null, null, null);
        } catch (Exception unused) {
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
        try {
            dVar = o(cursor);
        } catch (Exception unused2) {
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return dVar;
    }

    private synchronized ContentValues l(com.jingdong.jdpush_new.g.c.d dVar) {
        ContentValues contentValues;
        ContentValues contentValues2 = null;
        try {
            contentValues = new ContentValues();
            try {
                contentValues.put("app_id", m.d(dVar.a()));
                contentValues.put("status", m.d(dVar.f()));
                contentValues.put("command", m.d(dVar.b()));
                contentValues.put("msg_body", m.d(dVar.d()));
                contentValues.put("msg_seq", m.d(dVar.e()));
            } catch (Exception unused) {
                contentValues2 = contentValues;
                contentValues = contentValues2;
                return contentValues;
            }
        } catch (Exception unused2) {
        }
        return contentValues;
    }

    public static f m(Context context) {
        if (f12821c == null) {
            f12821c = new f(context);
        }
        return f12821c;
    }

    private synchronized int n() {
        int count;
        Cursor query = this.b.query("recordOpenPushInfo_msg", null, null, null, null, null, null);
        count = query != null ? query.getCount() : 0;
        if (query != null) {
            query.close();
        }
        return count;
    }

    private synchronized com.jingdong.jdpush_new.g.c.d o(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                com.jingdong.jdpush_new.g.c.d dVar = new com.jingdong.jdpush_new.g.c.d();
                dVar.j(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("id"))));
                dVar.h(m.b(cursor.getString(cursor.getColumnIndex("app_id"))));
                dVar.k(m.b(cursor.getString(cursor.getColumnIndex("msg_body"))));
                dVar.m(m.b(cursor.getString(cursor.getColumnIndex("status"))));
                dVar.i(m.b(cursor.getString(cursor.getColumnIndex("command"))));
                dVar.l(m.b(cursor.getString(cursor.getColumnIndex("msg_seq"))));
                return dVar;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private synchronized void p(ContentValues contentValues, String str) {
        try {
            this.b.update("recordOpenPushInfo_msg", contentValues, "msg_seq=?", new String[]{str});
        } catch (Exception unused) {
        }
    }

    public synchronized void d(com.jingdong.jdpush_new.g.c.d dVar) {
        if (dVar == null) {
            return;
        }
        try {
            b();
            if (n() >= 50) {
                g();
            }
            c(l(dVar));
        } catch (Exception unused) {
        }
        a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0092, code lost:
        if (r0.moveToNext() != false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        if (r0.moveToFirst() != false) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r2 = new com.jingdong.jdpush_new.g.c.d();
        r2.j(java.lang.Integer.valueOf(r0.getInt(r0.getColumnIndex("id"))));
        r2.h(com.jingdong.jdpush_new.j.m.b(r0.getString(r0.getColumnIndex("app_id"))));
        r2.i(com.jingdong.jdpush_new.j.m.b(r0.getString(r0.getColumnIndex("command"))));
        r2.k(com.jingdong.jdpush_new.j.m.b(r0.getString(r0.getColumnIndex("msg_body"))));
        r2.m(com.jingdong.jdpush_new.j.m.b(r0.getString(r0.getColumnIndex("status"))));
        r2.l(com.jingdong.jdpush_new.j.m.b(r0.getString(r0.getColumnIndex("msg_seq"))));
        r1.add(r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized List<com.jingdong.jdpush_new.g.c.d> i() {
        ArrayList arrayList;
        Cursor cursor = null;
        try {
            arrayList = new ArrayList();
        } catch (Exception unused) {
            arrayList = null;
        }
        try {
            b();
            cursor = this.b.query("recordOpenPushInfo_msg", null, null, null, null, null, null);
            if (cursor != null) {
            }
            if (cursor != null) {
                cursor.close();
            }
            a();
            return arrayList;
        } catch (Exception unused2) {
            if (cursor != null) {
                cursor.close();
            }
            a();
            return arrayList;
        }
    }

    public synchronized com.jingdong.jdpush_new.g.c.d k(String str) {
        com.jingdong.jdpush_new.g.c.d o;
        b();
        Cursor query = this.b.query("recordOpenPushInfo_msg", null, "msg_seq=?", new String[]{m.d(str)}, null, null, null);
        o = o(query);
        try {
            query.close();
        } catch (Exception unused) {
        }
        return o;
    }

    public synchronized void q(com.jingdong.jdpush_new.g.c.d dVar) {
        if (dVar.e() == null) {
            return;
        }
        try {
            b();
            com.jingdong.jdpush_new.g.c.d j2 = j(dVar.e());
            if (j2 != null) {
                e(j2, dVar);
                p(l(j2), m.d(j2.e()));
            }
        } catch (Exception unused) {
        }
    }
}
