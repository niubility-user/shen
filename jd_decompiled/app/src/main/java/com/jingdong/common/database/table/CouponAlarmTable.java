package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CouponAlarmTable implements IJdTable {
    private static final String TAG = "CouponAlarmTable";
    public static final String TB_ALARM_TABLE = "CouponAlarmTable";
    public static final String TB_CLOUMN_COUPON_ID = "couponId";

    public static void delAll() {
        try {
            try {
                DBHelperUtil.getDatabase().delete("CouponAlarmTable", "1=1", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("CouponAlarmTable", e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static void delById(long j2) {
        try {
            try {
                DBHelperUtil.getDatabase().delete("CouponAlarmTable", "couponId =?", new String[]{j2 + ""});
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("CouponAlarmTable", e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
        if (r1.isClosed() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0063, code lost:
        if (r1.isClosed() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0065, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<java.lang.Long> getList() {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.lang.String r3 = "SELECT id,couponId FROM CouponAlarmTable"
            android.database.Cursor r1 = r2.rawQuery(r3, r1)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            if (r1 != 0) goto L21
            if (r1 == 0) goto L1d
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L1d
            r1.close()
        L1d:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L21:
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            int r2 = r1.getCount()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            if (r2 == 0) goto L48
            int r2 = r1.getCount()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r3 = 0
        L2f:
            if (r3 >= r2) goto L48
            r1.moveToPosition(r3)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.lang.String r4 = "couponId"
            int r4 = r1.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            long r4 = r1.getLong(r4)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r0.add(r4)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            int r3 = r3 + 1
            goto L2f
        L48:
            if (r1 == 0) goto L68
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L68
            goto L65
        L51:
            r0 = move-exception
            goto L6c
        L53:
            r2 = move-exception
            boolean r3 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L51
            if (r3 == 0) goto L5d
            java.lang.String r3 = "CouponAlarmTable"
            com.jingdong.sdk.oklog.OKLog.e(r3, r2)     // Catch: java.lang.Throwable -> L51
        L5d:
            if (r1 == 0) goto L68
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L68
        L65:
            r1.close()
        L68:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L6c:
            if (r1 == 0) goto L77
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L77
            r1.close()
        L77:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto L7c
        L7b:
            throw r0
        L7c:
            goto L7b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.CouponAlarmTable.getList():java.util.ArrayList");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00af A[Catch: all -> 0x00c9, TRY_LEAVE, TryCatch #5 {all -> 0x00c9, blocks: (B:39:0x00ab, B:41:0x00af), top: B:69:0x00ab }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c1 A[Catch: all -> 0x00d5, TryCatch #1 {all -> 0x00d5, blocks: (B:21:0x0089, B:23:0x008f, B:25:0x0094, B:26:0x0097, B:52:0x00cb, B:54:0x00d1, B:58:0x00d9, B:59:0x00dc, B:43:0x00b6, B:45:0x00bc, B:47:0x00c1, B:48:0x00c4), top: B:68:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00cb A[Catch: all -> 0x00d5, TRY_ENTER, TryCatch #1 {all -> 0x00d5, blocks: (B:21:0x0089, B:23:0x008f, B:25:0x0094, B:26:0x0097, B:52:0x00cb, B:54:0x00d1, B:58:0x00d9, B:59:0x00dc, B:43:0x00b6, B:45:0x00bc, B:47:0x00c1, B:48:0x00c4), top: B:68:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00d9 A[Catch: all -> 0x00d5, TryCatch #1 {all -> 0x00d5, blocks: (B:21:0x0089, B:23:0x008f, B:25:0x0094, B:26:0x0097, B:52:0x00cb, B:54:0x00d1, B:58:0x00d9, B:59:0x00dc, B:43:0x00b6, B:45:0x00bc, B:47:0x00c1, B:48:0x00c4), top: B:68:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized int insertBatch(java.util.List<java.lang.Long> r22) {
        /*
            Method dump skipped, instructions count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.CouponAlarmTable.insertBatch(java.util.List):int");
    }

    public static synchronized void insertOrUpdate(long j2) {
        synchronized (CouponAlarmTable.class) {
            try {
                ArrayList<Long> list = getList();
                if (list != null && list.size() > 100) {
                    delAll();
                }
                Cursor cursor = null;
                try {
                    try {
                        SQLiteDatabase database = DBHelperUtil.getDatabase();
                        ContentValues contentValues = new ContentValues();
                        String[] strArr = {j2 + ""};
                        Cursor query = database.query("CouponAlarmTable", null, "couponId =?", strArr, null, null, null);
                        if (query != null) {
                            try {
                                if (query.getCount() != 0) {
                                    database.delete("CouponAlarmTable", "couponId =?", strArr);
                                }
                            } catch (Exception e2) {
                                e = e2;
                                cursor = query;
                                if (OKLog.E) {
                                    OKLog.e("CouponAlarmTable", e);
                                }
                                if (cursor != null && !cursor.isClosed()) {
                                    cursor.close();
                                }
                                DBHelperUtil.closeDatabase();
                            } catch (Throwable th) {
                                th = th;
                                cursor = query;
                                if (cursor != null && !cursor.isClosed()) {
                                    cursor.close();
                                }
                                DBHelperUtil.closeDatabase();
                                throw th;
                            }
                        }
                        contentValues.put("couponId", Long.valueOf(j2));
                        database.insert("CouponAlarmTable", null, contentValues);
                        if (query != null && !query.isClosed()) {
                            query.close();
                        }
                    } catch (Exception e3) {
                        e = e3;
                    }
                    DBHelperUtil.closeDatabase();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x005e, code lost:
        if (r1.isClosed() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0073, code lost:
        if (r1.isClosed() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0075, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isExists(long r6) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            r4.<init>()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            java.lang.String r5 = "SELECT id,couponId FROM CouponAlarmTable WHERE couponId = "
            r4.append(r5)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            r4.append(r6)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            android.database.Cursor r1 = r3.rawQuery(r6, r1)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            if (r1 != 0) goto L31
            if (r1 == 0) goto L2d
            boolean r6 = r1.isClosed()
            if (r6 != 0) goto L2d
            r1.close()
        L2d:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r2
        L31:
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            int r6 = r1.getCount()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            if (r6 == 0) goto L58
            int r6 = r1.getCount()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            r7 = 0
        L3f:
            if (r7 >= r6) goto L58
            r1.moveToPosition(r7)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            java.lang.String r3 = "couponId"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            long r3 = r1.getLong(r3)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            r0.add(r3)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            int r7 = r7 + 1
            goto L3f
        L58:
            if (r1 == 0) goto L78
            boolean r6 = r1.isClosed()
            if (r6 != 0) goto L78
            goto L75
        L61:
            r6 = move-exception
            goto L84
        L63:
            r6 = move-exception
            boolean r7 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L61
            if (r7 == 0) goto L6d
            java.lang.String r7 = "CouponAlarmTable"
            com.jingdong.sdk.oklog.OKLog.e(r7, r6)     // Catch: java.lang.Throwable -> L61
        L6d:
            if (r1 == 0) goto L78
            boolean r6 = r1.isClosed()
            if (r6 != 0) goto L78
        L75:
            r1.close()
        L78:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            int r6 = r0.size()
            if (r6 <= 0) goto L83
            r6 = 1
            return r6
        L83:
            return r2
        L84:
            if (r1 == 0) goto L8f
            boolean r7 = r1.isClosed()
            if (r7 != 0) goto L8f
            r1.close()
        L8f:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto L94
        L93:
            throw r6
        L94:
            goto L93
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.CouponAlarmTable.isExists(long):boolean");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE CouponAlarmTable('id' INTEGER PRIMARY KEY  NOT NULL ,couponId LONG DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists CouponAlarmTable");
    }
}
