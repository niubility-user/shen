package com.jingdong.jdpush_new.f;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.jdpush_new.j.m;

/* loaded from: classes12.dex */
public class a extends b {

    /* renamed from: c */
    private static a f12818c;

    public a(Context context) {
        super(context);
    }

    private synchronized void c(ContentValues contentValues) {
        try {
            this.b.insert("app_info", null, contentValues);
        } catch (Exception unused) {
        }
    }

    public static synchronized void d(com.jingdong.jdpush_new.g.c.a aVar, com.jingdong.jdpush_new.g.c.a aVar2) {
        synchronized (a.class) {
            if (aVar2 == null) {
                return;
            }
            if (aVar2.d() != null) {
                aVar.n(aVar2.d());
            }
            if (aVar2.a() != null) {
                aVar.k(aVar2.a());
            }
            if (aVar2.b() != null) {
                aVar.l(aVar2.b());
            }
            if (aVar2.c() != null) {
                aVar.m(aVar2.c());
            }
            if (aVar2.e() != null) {
                aVar.o(aVar2.e());
            }
            if (aVar2.h() != null) {
                aVar.r(aVar2.h());
            }
            if (aVar2.i() != null) {
                aVar.s(aVar2.i());
            }
            if (aVar2.f() != null) {
                aVar.p(aVar2.f());
            }
            if (aVar2.g() != null) {
                aVar.q(aVar2.g());
            }
        }
    }

    public static synchronized void e(SQLiteDatabase sQLiteDatabase) {
        synchronized (a.class) {
            try {
                StringBuilder sb = new StringBuilder(200);
                sb.append("CREATE TABLE IF NOT EXISTS ");
                sb.append("app_info");
                sb.append(" (");
                sb.append("id");
                sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
                sb.append("app_id");
                sb.append(" VARCHAR,");
                sb.append("app_secret");
                sb.append(" VARCHAR,");
                sb.append(RemoteMessageConst.DEVICE_TOKEN);
                sb.append(" VARCHAR,");
                sb.append("package_name");
                sb.append(" VARCHAR,");
                sb.append("update_status");
                sb.append(" VARCHAR,");
                sb.append("version_app");
                sb.append(" VARCHAR,");
                sb.append("time");
                sb.append(" VARCHAR,");
                sb.append("version_os");
                sb.append(" VARCHAR)");
                sQLiteDatabase.execSQL(sb.toString());
            } catch (Exception unused) {
            }
        }
    }

    public static synchronized void f(SQLiteDatabase sQLiteDatabase) {
        synchronized (a.class) {
            try {
                StringBuilder sb = new StringBuilder(200);
                sb.append("DORP TABLE IF EXISTS ");
                sb.append("app_info");
                sQLiteDatabase.execSQL(sb.toString());
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x001a, code lost:
        if (r11 != null) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x002a, code lost:
        if (r11 == null) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x002c, code lost:
        r11.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized com.jingdong.jdpush_new.g.c.a g(String str) {
        com.jingdong.jdpush_new.g.c.a aVar;
        Throwable th;
        Cursor cursor;
        aVar = null;
        try {
            cursor = this.b.query("app_info", null, "app_id=?", new String[]{str}, null, null, null);
        } catch (Exception unused) {
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
        try {
            aVar = i(cursor);
        } catch (Exception unused2) {
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return aVar;
    }

    private synchronized com.jingdong.jdpush_new.g.c.a i(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                com.jingdong.jdpush_new.g.c.a aVar = new com.jingdong.jdpush_new.g.c.a();
                aVar.n(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("id"))));
                aVar.k(m.b(cursor.getString(cursor.getColumnIndex("app_id"))));
                aVar.l(m.b(cursor.getString(cursor.getColumnIndex("app_secret"))));
                aVar.m(m.b(cursor.getString(cursor.getColumnIndex(RemoteMessageConst.DEVICE_TOKEN))));
                aVar.o(m.b(cursor.getString(cursor.getColumnIndex("package_name"))));
                aVar.r(m.b(cursor.getString(cursor.getColumnIndex("version_app"))));
                aVar.p(m.b(cursor.getString(cursor.getColumnIndex("time"))));
                aVar.q(m.b(cursor.getString(cursor.getColumnIndex("update_status"))));
                aVar.s(m.b(cursor.getString(cursor.getColumnIndex("version_os"))));
                return aVar;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private synchronized ContentValues j(com.jingdong.jdpush_new.g.c.a aVar) {
        ContentValues contentValues;
        ContentValues contentValues2 = null;
        try {
            contentValues = new ContentValues();
        } catch (Exception unused) {
        }
        try {
            contentValues.put("id", aVar.d());
            contentValues.put("app_id", m.d(aVar.a()));
            contentValues.put("app_secret", m.d(aVar.b()));
            contentValues.put(RemoteMessageConst.DEVICE_TOKEN, m.d(aVar.c()));
            contentValues.put("package_name", m.d(aVar.e()));
            contentValues.put("update_status", m.d(aVar.g()));
            contentValues.put("version_app", m.d(aVar.h()));
            contentValues.put("version_os", m.d(aVar.i()));
            contentValues.put("time", m.d(aVar.f()));
        } catch (Exception unused2) {
            contentValues2 = contentValues;
            contentValues = contentValues2;
            return contentValues;
        }
        return contentValues;
    }

    public static a k(Context context) {
        if (f12818c == null) {
            f12818c = new a(context);
        }
        return f12818c;
    }

    private synchronized void l(ContentValues contentValues, String str) {
        try {
            this.b.update("app_info", contentValues, "app_id = ?", new String[]{str});
        } catch (Exception unused) {
        }
    }

    private synchronized void n(com.jingdong.jdpush_new.g.c.a aVar) {
        if (aVar == null) {
            return;
        }
        try {
            com.jingdong.jdpush_new.g.c.a g2 = g(m.d(aVar.a()));
            if (g2 != null) {
                d(g2, aVar);
                l(j(g2), m.d(g2.a()));
            } else {
                c(j(aVar));
            }
        } catch (Exception unused) {
        }
    }

    public synchronized com.jingdong.jdpush_new.g.c.a h(String str) {
        com.jingdong.jdpush_new.g.c.a aVar;
        aVar = null;
        try {
            b();
            aVar = g(m.d(str));
            com.jingdong.jdpush_new.d.c.b().c(aVar);
        } catch (Exception unused) {
        } catch (Throwable th) {
            a();
            throw th;
        }
        a();
        return aVar;
    }

    public synchronized void m(com.jingdong.jdpush_new.g.c.a aVar) {
        if (aVar == null) {
            return;
        }
        try {
            b();
            n(aVar);
            com.jingdong.jdpush_new.d.c.b().c(aVar);
        } catch (Exception unused) {
        } catch (Throwable th) {
            a();
            throw th;
        }
        a();
    }
}
