package cn.com.union.fido.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.com.union.fido.db.help.SQLiteHelper;

/* loaded from: classes.dex */
public class SignCounterManager {
    private SQLiteDatabase db;
    private SQLiteHelper helper;

    public SignCounterManager(Context context) {
        SQLiteHelper sQLiteHelper = SQLiteHelper.getInstance(context);
        this.helper = sQLiteHelper;
        this.db = sQLiteHelper.getWritableDatabase();
    }

    private void addCounter(Object[] objArr) {
        this.db.beginTransaction();
        try {
            this.db.execSQL("INSERT INTO signcounter VALUES(null, ?, ?, ?, ?)", objArr);
            this.db.setTransactionSuccessful();
        } finally {
            this.db.endTransaction();
        }
    }

    private void updateCounter(int i2, String[] strArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("signCounter", Integer.valueOf(i2));
        SQLiteDatabase sQLiteDatabase = this.db;
        this.helper.getClass();
        sQLiteDatabase.update("signcounter", contentValues, " aaid = ? and keyID = ? and userName = ? ", strArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0048, code lost:
        if (r0 == null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004b, code lost:
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x003e, code lost:
        if (r0 != null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0040, code lost:
        r0.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int updateOrAddCounter(String str, String str2, String str3) {
        Cursor cursor;
        int i2;
        try {
            i2 = 1;
            cursor = this.db.rawQuery("SELECT signCounter FROM signcounter WHERE aaid = ? and keyID = ? and userName = ? ", new String[]{str, str2, str3});
        } catch (Throwable unused) {
            cursor = null;
        }
        try {
            if (cursor.moveToNext()) {
                int i3 = cursor.getInt(0) + 1;
                updateCounter(i3, new String[]{str, str2, str3});
                i2 = i3;
            } else {
                addCounter(new Object[]{str, str2, str3, 1});
            }
        } catch (Throwable unused2) {
            i2 = -1;
        }
    }
}
