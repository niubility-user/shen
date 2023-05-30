package com.jingdong.common.database.table;

import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class FxContentIdTable implements IJdTable {
    private static final String CONTENT_ID = "contentId";
    private static final String KEY_ID = "id";
    public static final int MAX_STORE_COUNT = 480;
    private static final String TABLE_NAME = "FxContentIdTable";
    private static final String TAG = "FxContentIdTable";

    public static void clearHistory() {
        long count = count();
        if (count < 960) {
            return;
        }
        try {
            DBHelperUtil.getDatabase().execSQL(String.format("delete from %s where %s in (select %s from %s order by %s limit %d)", "FxContentIdTable", "id", "id", "FxContentIdTable", "id", Long.valueOf(count - 480)));
        } catch (Exception e2) {
            OKLog.e("FxContentIdTable", e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003a, code lost:
        if (r3.isClosed() == false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static long count() {
        /*
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 0
            java.lang.String r2 = "FxContentIdTable"
            r0[r1] = r2
            java.lang.String r3 = "select count(*) from %s"
            java.lang.String r0 = java.lang.String.format(r3, r0)
            r3 = 0
            r4 = 0
            android.database.sqlite.SQLiteDatabase r6 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            android.database.Cursor r3 = r6.rawQuery(r0, r3)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            if (r3 == 0) goto L22
            r3.moveToFirst()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            long r4 = r3.getLong(r1)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
        L22:
            if (r3 == 0) goto L3d
            boolean r0 = r3.isClosed()
            if (r0 != 0) goto L3d
        L2a:
            r3.close()
            goto L3d
        L2e:
            r0 = move-exception
            goto L3e
        L30:
            r0 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r2, r0)     // Catch: java.lang.Throwable -> L2e
            if (r3 == 0) goto L3d
            boolean r0 = r3.isClosed()
            if (r0 != 0) goto L3d
            goto L2a
        L3d:
            return r4
        L3e:
            if (r3 == 0) goto L49
            boolean r1 = r3.isClosed()
            if (r1 != 0) goto L49
            r3.close()
        L49:
            goto L4b
        L4a:
            throw r0
        L4b:
            goto L4a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.FxContentIdTable.count():long");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x006c, code lost:
        if (r4.isClosed() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007b, code lost:
        if (r4.isClosed() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007d, code lost:
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.HashSet<java.lang.String> getIds() {
        /*
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            java.lang.String r3 = "FxContentIdTable"
            r1[r2] = r3
            java.lang.String r4 = "id"
            r5 = 1
            r1[r5] = r4
            r4 = 480(0x1e0, float:6.73E-43)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5 = 2
            r1[r5] = r4
            java.lang.String r4 = "select * from %s order by %s desc limit %d"
            java.lang.String r1 = java.lang.String.format(r4, r1)
            r4 = 0
            android.database.sqlite.SQLiteDatabase r5 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L71
            android.database.Cursor r4 = r5.rawQuery(r1, r4)     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L71
            if (r4 != 0) goto L3b
            if (r4 == 0) goto L37
            boolean r1 = r4.isClosed()
            if (r1 != 0) goto L37
            r4.close()
        L37:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L3b:
            r4.moveToFirst()     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L71
            int r1 = r4.getCount()     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L71
        L42:
            if (r2 >= r1) goto L66
            r4.moveToPosition(r2)     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L71
            java.lang.String r5 = "contentId"
            int r5 = r4.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L71
            java.lang.String r5 = r4.getString(r5)     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L71
            if (r5 == 0) goto L63
            int r6 = r5.length()     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L71
            if (r6 == 0) goto L63
            boolean r6 = r0.contains(r5)     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L71
            if (r6 == 0) goto L60
            goto L63
        L60:
            r0.add(r5)     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L71
        L63:
            int r2 = r2 + 1
            goto L42
        L66:
            if (r4 == 0) goto L80
            boolean r1 = r4.isClosed()
            if (r1 != 0) goto L80
            goto L7d
        L6f:
            r0 = move-exception
            goto L84
        L71:
            r1 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r3, r1)     // Catch: java.lang.Throwable -> L6f
            if (r4 == 0) goto L80
            boolean r1 = r4.isClosed()
            if (r1 != 0) goto L80
        L7d:
            r4.close()
        L80:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L84:
            if (r4 == 0) goto L8f
            boolean r1 = r4.isClosed()
            if (r1 != 0) goto L8f
            r4.close()
        L8f:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto L94
        L93:
            throw r0
        L94:
            goto L93
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.FxContentIdTable.getIds():java.util.HashSet");
    }

    public static void insert(String str) {
        try {
            DBHelperUtil.getDatabase().execSQL(String.format("insert into %s (%s) values (%s)", "FxContentIdTable", CONTENT_ID, str));
        } catch (Exception e2) {
            OKLog.e("FxContentIdTable", e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        if (r11 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002a, code lost:
        if (r11 != null) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002c, code lost:
        r11.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean queryIdExist(java.lang.String r11) {
        /*
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r1 = "contentId"
            r2 = 0
            r0[r2] = r1
            r1 = 1
            r0[r1] = r11
            java.lang.String r11 = "%s = %s"
            java.lang.String r6 = java.lang.String.format(r11, r0)
            r11 = 0
            android.database.sqlite.SQLiteDatabase r3 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L37
            java.lang.String r4 = "FxContentIdTable"
            r5 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r11 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L37
            if (r11 == 0) goto L2a
            int r0 = r11.getCount()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L37
            if (r0 <= 0) goto L2a
            r2 = 1
        L2a:
            if (r11 == 0) goto L3b
        L2c:
            r11.close()
            goto L3b
        L30:
            r0 = move-exception
            if (r11 == 0) goto L36
            r11.close()
        L36:
            throw r0
        L37:
            if (r11 == 0) goto L3b
            goto L2c
        L3b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.FxContentIdTable.queryIdExist(java.lang.String):boolean");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS FxContentIdTable (id INTEGER PRIMARY KEY AUTOINCREMENT,contentId VARCHAR NOT NULL)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }
}
