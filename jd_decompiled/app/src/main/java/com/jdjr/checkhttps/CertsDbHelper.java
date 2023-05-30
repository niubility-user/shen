package com.jdjr.checkhttps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes18.dex */
public class CertsDbHelper extends SQLiteOpenHelper {
    public static final String COLUMN_CERT_CONTENT = "cert_content";
    public static final String DB_NAME = "certs_content";
    public static final int DB_VERSION = 1;

    public CertsDbHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS certs_content(_id INTEGER PRIMARY KEY AUTOINCREMENT, cert_content TEXT)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }
}
