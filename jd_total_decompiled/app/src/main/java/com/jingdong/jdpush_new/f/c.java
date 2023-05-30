package com.jingdong.jdpush_new.f;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes12.dex */
public class c extends SQLiteOpenHelper {
    public c(Context context) {
        this(context, "s_msg_jd_push.db", null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a.e(sQLiteDatabase);
        d.e(sQLiteDatabase);
        e.e(sQLiteDatabase);
        f.f(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        a.f(sQLiteDatabase);
        d.g(sQLiteDatabase);
        e.g(sQLiteDatabase);
        f.h(sQLiteDatabase);
        a.e(sQLiteDatabase);
        d.e(sQLiteDatabase);
        e.e(sQLiteDatabase);
        f.f(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        a.f(sQLiteDatabase);
        d.g(sQLiteDatabase);
        e.g(sQLiteDatabase);
        f.h(sQLiteDatabase);
        a.e(sQLiteDatabase);
        d.e(sQLiteDatabase);
        e.e(sQLiteDatabase);
        f.f(sQLiteDatabase);
    }

    public c(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i2) {
        super(context, str, cursorFactory, i2);
    }
}
