package com.jingdong.jdpush_new.f;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdpush_new.j.m;

/* loaded from: classes12.dex */
public class d extends b {

    /* renamed from: c  reason: collision with root package name */
    private static d f12819c;

    public d(Context context) {
        super(context);
    }

    private synchronized void c(ContentValues contentValues) {
        try {
            this.b.insert("necessary_message", null, contentValues);
        } catch (Exception unused) {
        }
    }

    private synchronized boolean d(String str) {
        try {
            return h(str) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static synchronized void e(SQLiteDatabase sQLiteDatabase) {
        synchronized (d.class) {
            try {
                StringBuilder sb = new StringBuilder(200);
                sb.append("CREATE TABLE IF NOT EXISTS ");
                sb.append("necessary_message");
                sb.append(" (");
                sb.append("id");
                sb.append(" INTEGER PRIMARY KEY ,");
                sb.append("app_id");
                sb.append(" VARCHAR,");
                sb.append("command");
                sb.append(" VARCHAR,");
                sb.append("status");
                sb.append(" VARCHAR,");
                sb.append("app_secret");
                sb.append(" VARCHAR,");
                sb.append("version_app");
                sb.append(" VARCHAR,");
                sb.append("version_os");
                sb.append(" VARCHAR,");
                sb.append("time_stamp");
                sb.append(" VARCHAR,");
                sb.append("frequency");
                sb.append(" INT,");
                sb.append("msg_body");
                sb.append(" VARCHAR)");
                sQLiteDatabase.execSQL(sb.toString());
            } catch (Exception unused) {
            }
        }
    }

    private synchronized void f(String str) {
        try {
            this.b.delete("necessary_message", "id=?", new String[]{str});
        } catch (Exception unused) {
        }
    }

    public static synchronized void g(SQLiteDatabase sQLiteDatabase) {
        synchronized (d.class) {
            try {
                StringBuilder sb = new StringBuilder(200);
                sb.append("DORP TABLE IF EXISTS ");
                sb.append("necessary_message");
                sQLiteDatabase.execSQL(sb.toString());
            } catch (Exception unused) {
            }
        }
    }

    private synchronized com.jingdong.jdpush_new.g.c.b h(String str) {
        com.jingdong.jdpush_new.g.c.b l2;
        Cursor query = this.b.query("necessary_message", null, "id=?", new String[]{str}, null, null, null);
        l2 = l(query);
        try {
            query.close();
        } catch (Exception unused) {
        }
        return l2;
    }

    private synchronized ContentValues j(com.jingdong.jdpush_new.g.c.b bVar) {
        ContentValues contentValues;
        ContentValues contentValues2 = null;
        try {
            contentValues = new ContentValues();
            try {
                contentValues.put("id", bVar.d());
                contentValues.put("app_id", m.d(bVar.a()));
                contentValues.put("command", m.d(bVar.c()));
                contentValues.put("status", m.d(bVar.f()));
                contentValues.put("msg_body", m.d(bVar.e()));
                contentValues.put("frequency", Integer.valueOf(bVar.g()));
                contentValues.put("app_secret", m.d(bVar.b()));
                contentValues.put("version_app", m.d(bVar.i()));
                contentValues.put("version_os", m.d(bVar.j()));
                contentValues.put("time_stamp", m.d(bVar.h()));
            } catch (Exception unused) {
                contentValues2 = contentValues;
                contentValues = contentValues2;
                return contentValues;
            }
        } catch (Exception unused2) {
        }
        return contentValues;
    }

    public static d k(Context context) {
        if (f12819c == null) {
            f12819c = new d(context);
        }
        return f12819c;
    }

    private synchronized com.jingdong.jdpush_new.g.c.b l(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                com.jingdong.jdpush_new.g.c.b bVar = new com.jingdong.jdpush_new.g.c.b();
                bVar.o(cursor.getString(cursor.getColumnIndex("id")));
                bVar.l(m.b(cursor.getString(cursor.getColumnIndex("app_id"))));
                bVar.n(m.b(cursor.getString(cursor.getColumnIndex("command"))));
                bVar.q(m.b(cursor.getString(cursor.getColumnIndex("status"))));
                bVar.p(m.b(cursor.getString(cursor.getColumnIndex("msg_body"))));
                bVar.r(cursor.getInt(cursor.getColumnIndex("frequency")));
                bVar.u(m.b(cursor.getString(cursor.getColumnIndex("version_os"))));
                bVar.m(m.b(cursor.getString(cursor.getColumnIndex("app_secret"))));
                bVar.t(m.b(cursor.getString(cursor.getColumnIndex("version_app"))));
                bVar.s(m.b(cursor.getString(cursor.getColumnIndex("time_stamp"))));
                return bVar;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private synchronized void m(com.jingdong.jdpush_new.g.c.b bVar) {
        if (bVar == null) {
            return;
        }
        try {
            if (d(bVar.d())) {
                f(bVar.d());
            }
            c(j(bVar));
        } catch (Exception unused) {
        }
    }

    public synchronized com.jingdong.jdpush_new.g.c.b i(String str) {
        com.jingdong.jdpush_new.g.c.b h2;
        try {
            b();
            h2 = h(str);
            a();
        } catch (Exception unused) {
            a();
            return null;
        }
        return h2;
    }

    public synchronized void n(com.jingdong.jdpush_new.g.c.b bVar) {
        if (bVar == null) {
            return;
        }
        try {
            b();
            m(bVar);
        } catch (Exception unused) {
        } catch (Throwable th) {
            a();
            throw th;
        }
        a();
    }
}
