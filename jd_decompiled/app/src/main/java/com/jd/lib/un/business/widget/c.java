package com.jd.lib.un.business.widget;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jingdong.common.unification.uniconfig.UnNewIconTable;

/* loaded from: classes16.dex */
public class c extends SQLiteOpenHelper {

    /* renamed from: g  reason: collision with root package name */
    private static c f5875g;

    public c() {
        super(a.g().d(), "un_widget.db", (SQLiteDatabase.CursorFactory) null, 4);
    }

    public static synchronized void f() {
        synchronized (c.class) {
        }
    }

    public static synchronized SQLiteDatabase g() {
        SQLiteDatabase writableDatabase;
        synchronized (c.class) {
            if (f5875g == null) {
                f5875g = new c();
            }
            try {
                writableDatabase = f5875g.getWritableDatabase();
            } catch (Exception unused) {
                a.g().d().deleteDatabase("un_widget.db");
                return f5875g.getWritableDatabase();
            }
        }
        return writableDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        new UnNewIconTable().create(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        new UnNewIconTable().upgrade(sQLiteDatabase, i2, 4);
    }
}
