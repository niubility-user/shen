package com.jingdong.jdpush_new.f;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/* loaded from: classes12.dex */
public class b {
    public c a;
    public SQLiteDatabase b;

    public b(Context context) {
        this.a = new c(context);
    }

    public void a() {
        try {
            this.b.close();
        } catch (Exception unused) {
        }
    }

    public SQLiteDatabase b() {
        try {
            SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
            this.b = writableDatabase;
            return writableDatabase;
        } catch (Exception unused) {
            return null;
        }
    }
}
