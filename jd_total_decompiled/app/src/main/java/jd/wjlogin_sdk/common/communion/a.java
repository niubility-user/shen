package jd.wjlogin_sdk.common.communion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes11.dex */
public class a extends SQLiteOpenHelper {
    public static final String a = "wj_communion_sl.db";
    public static final int b = 2;

    /* renamed from: c  reason: collision with root package name */
    public static final String f19753c = "first";
    public static final String d = "CREATE TABLE first(_id INTEGER PRIMARY KEY AUTOINCREMENT,finger TEXT);";

    public a(Context context) {
        super(context, a, (SQLiteDatabase.CursorFactory) null, 2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(d);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS first");
        onCreate(sQLiteDatabase);
    }
}
