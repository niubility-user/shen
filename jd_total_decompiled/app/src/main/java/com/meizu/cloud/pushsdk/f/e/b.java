package com.meizu.cloud.pushsdk.f.e;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes14.dex */
public class b extends SQLiteOpenHelper {

    /* renamed from: g  reason: collision with root package name */
    private static final String f15917g = b.class.getName();

    /* renamed from: h  reason: collision with root package name */
    private static b f15918h;

    private b(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public static b f(Context context, String str) {
        if (f15918h == null) {
            f15918h = new b(context.getApplicationContext(), str);
        }
        return f15918h;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS 'events' (id INTEGER PRIMARY KEY, eventData BLOB, dateCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        com.meizu.cloud.pushsdk.f.g.c.e(f15917g, "Upgrading database from version " + i2 + " to " + i3 + ". Destroying old data now..", new Object[0]);
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS 'events'");
        onCreate(sQLiteDatabase);
    }
}
