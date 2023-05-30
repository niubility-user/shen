package cn.com.union.fido.db;

import android.content.ContentValues;
import android.content.Context;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int updateOrAddCounter(java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            android.database.sqlite.SQLiteDatabase r0 = r7.db     // Catch: java.lang.Throwable -> L46
            java.lang.String r1 = "SELECT signCounter FROM signcounter WHERE aaid = ? and keyID = ? and userName = ? "
            r2 = 3
            java.lang.String[] r3 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L46
            r4 = 0
            r3[r4] = r8     // Catch: java.lang.Throwable -> L46
            r5 = 1
            r3[r5] = r9     // Catch: java.lang.Throwable -> L46
            r6 = 2
            r3[r6] = r10     // Catch: java.lang.Throwable -> L46
            android.database.Cursor r0 = r0.rawQuery(r1, r3)     // Catch: java.lang.Throwable -> L46
            boolean r1 = r0.moveToNext()     // Catch: java.lang.Throwable -> L44
            if (r1 == 0) goto L2c
            int r1 = r0.getInt(r4)     // Catch: java.lang.Throwable -> L44
            int r1 = r1 + r5
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L44
            r2[r4] = r8     // Catch: java.lang.Throwable -> L44
            r2[r5] = r9     // Catch: java.lang.Throwable -> L44
            r2[r6] = r10     // Catch: java.lang.Throwable -> L44
            r7.updateCounter(r1, r2)     // Catch: java.lang.Throwable -> L44
            r5 = r1
            goto L3e
        L2c:
            r1 = 4
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L44
            r1[r4] = r8     // Catch: java.lang.Throwable -> L44
            r1[r5] = r9     // Catch: java.lang.Throwable -> L44
            r1[r6] = r10     // Catch: java.lang.Throwable -> L44
            java.lang.Integer r8 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> L44
            r1[r2] = r8     // Catch: java.lang.Throwable -> L44
            r7.addCounter(r1)     // Catch: java.lang.Throwable -> L44
        L3e:
            if (r0 == 0) goto L4b
        L40:
            r0.close()
            goto L4b
        L44:
            goto L47
        L46:
            r0 = 0
        L47:
            r5 = -1
            if (r0 == 0) goto L4b
            goto L40
        L4b:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.union.fido.db.SignCounterManager.updateOrAddCounter(java.lang.String, java.lang.String, java.lang.String):int");
    }
}
