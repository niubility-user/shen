package com.jingdong.jdpush_new.f;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdpush_new.j.m;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized com.jingdong.jdpush_new.g.c.d j(java.lang.String r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            r0 = 0
            java.lang.String r4 = "msg_seq=?"
            r1 = 1
            java.lang.String[] r5 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L2d
            r1 = 0
            java.lang.String r11 = com.jingdong.jdpush_new.j.m.d(r11)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L2d
            r5[r1] = r11     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L2d
            android.database.sqlite.SQLiteDatabase r1 = r10.b     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L2d
            java.lang.String r2 = "recordOpenPushInfo_msg"
            r3 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L2d
            com.jingdong.jdpush_new.g.c.d r0 = r10.o(r11)     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L2e
            if (r11 == 0) goto L37
            goto L30
        L21:
            r0 = move-exception
            goto L27
        L23:
            r11 = move-exception
            r9 = r0
            r0 = r11
            r11 = r9
        L27:
            if (r11 == 0) goto L2c
            r11.close()     // Catch: java.lang.Throwable -> L34
        L2c:
            throw r0     // Catch: java.lang.Throwable -> L34
        L2d:
            r11 = r0
        L2e:
            if (r11 == 0) goto L37
        L30:
            r11.close()     // Catch: java.lang.Throwable -> L34
            goto L37
        L34:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        L37:
            monitor-exit(r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdpush_new.f.f.j(java.lang.String):com.jingdong.jdpush_new.g.c.d");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.util.List<com.jingdong.jdpush_new.g.c.d> i() {
        /*
            r10 = this;
            monitor-enter(r10)
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> Laa
            r1.<init>()     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> Laa
            r10.b()     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            android.database.sqlite.SQLiteDatabase r2 = r10.b     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = "recordOpenPushInfo_msg"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            if (r0 == 0) goto L94
            boolean r2 = r0.moveToFirst()     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            if (r2 == 0) goto L94
        L20:
            com.jingdong.jdpush_new.g.c.d r2 = new com.jingdong.jdpush_new.g.c.d     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            r2.<init>()     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = "id"
            int r3 = r0.getColumnIndex(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            int r3 = r0.getInt(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            r2.j(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = "app_id"
            int r3 = r0.getColumnIndex(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = r0.getString(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = com.jingdong.jdpush_new.j.m.b(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            r2.h(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = "command"
            int r3 = r0.getColumnIndex(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = r0.getString(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = com.jingdong.jdpush_new.j.m.b(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            r2.i(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = "msg_body"
            int r3 = r0.getColumnIndex(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = r0.getString(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = com.jingdong.jdpush_new.j.m.b(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            r2.k(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = "status"
            int r3 = r0.getColumnIndex(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = r0.getString(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = com.jingdong.jdpush_new.j.m.b(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            r2.m(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = "msg_seq"
            int r3 = r0.getColumnIndex(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = r0.getString(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            java.lang.String r3 = com.jingdong.jdpush_new.j.m.b(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            r2.l(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            r1.add(r2)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            boolean r2 = r0.moveToNext()     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> La0
            if (r2 != 0) goto L20
        L94:
            if (r0 == 0) goto L99
            r0.close()     // Catch: java.lang.Throwable -> Lb5
        L99:
            r10.a()     // Catch: java.lang.Throwable -> Lb5
            monitor-exit(r10)
            return r1
        L9e:
            goto Lab
        La0:
            r1 = move-exception
            if (r0 == 0) goto La6
            r0.close()     // Catch: java.lang.Throwable -> Lb5
        La6:
            r10.a()     // Catch: java.lang.Throwable -> Lb5
            throw r1     // Catch: java.lang.Throwable -> Lb5
        Laa:
            r1 = r0
        Lab:
            if (r0 == 0) goto Lb0
            r0.close()     // Catch: java.lang.Throwable -> Lb5
        Lb0:
            r10.a()     // Catch: java.lang.Throwable -> Lb5
            monitor-exit(r10)
            return r1
        Lb5:
            r0 = move-exception
            monitor-exit(r10)
            goto Lb9
        Lb8:
            throw r0
        Lb9:
            goto Lb8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdpush_new.f.f.i():java.util.List");
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
