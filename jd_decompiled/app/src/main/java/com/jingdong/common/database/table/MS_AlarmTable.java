package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class MS_AlarmTable implements IJdTable {
    private static final String TAG = "MS_AlarmTable";
    public static final String TB_ALARM_TABLE = "MS_AlarmTable";
    public static final String TB_CLOUMN_INSERT_TIME = "insertTime";
    public static final String TB_CLOUMN_MILLIS_TIME = "millis";
    public static final String TB_CLOUMN_PRODUCT_CODE = "productCode";
    public static final String TB_CLOUMN_PRODUCT_NAME = "productName";

    public static void delAll() {
        try {
            try {
                DBHelperUtil.getDatabase().delete("MS_AlarmTable", "1=1", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("MS_AlarmTable", e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static void delById(long j2) {
        try {
            try {
                DBHelperUtil.getDatabase().delete("MS_AlarmTable", "productCode =?", new String[]{j2 + ""});
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("MS_AlarmTable", e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static synchronized int delOutOfTime() {
        int delete;
        synchronized (MS_AlarmTable.class) {
            try {
                delete = DBHelperUtil.getDatabase().delete("MS_AlarmTable", "millis<=" + System.currentTimeMillis(), null);
                DBHelperUtil.closeDatabase();
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("MS_AlarmTable", e2);
                }
                DBHelperUtil.closeDatabase();
                return 0;
            }
        }
        return delete;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x00d3, code lost:
        if (r1.isClosed() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00e8, code lost:
        if (r1.isClosed() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00ea, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<java.lang.Long> getAllList() {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r3 = "SELECT * FROM MS_AlarmTable"
            android.database.Cursor r1 = r2.rawQuery(r3, r1)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            if (r1 != 0) goto L21
            if (r1 == 0) goto L1d
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L1d
            r1.close()
        L1d:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L21:
            r1.moveToFirst()     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            int r2 = r1.getCount()     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            if (r2 == 0) goto Lcd
            int r2 = r1.getCount()     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r3 = 0
        L2f:
            if (r3 >= r2) goto Lcd
            r1.moveToPosition(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r4 = "millis"
            int r4 = r1.getColumnIndex(r4)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            long r4 = r1.getLong(r4)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.Long r6 = java.lang.Long.valueOf(r4)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r0.add(r6)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            boolean r6 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            if (r6 == 0) goto Lc9
            java.lang.String r6 = "productCode"
            int r6 = r1.getColumnIndex(r6)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            long r6 = r1.getLong(r6)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r8 = "insertTime"
            int r8 = r1.getColumnIndex(r8)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            long r8 = r1.getLong(r8)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r10 = "productName"
            int r10 = r1.getColumnIndex(r10)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r10 = r1.getString(r10)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.text.SimpleDateFormat r11 = new java.text.SimpleDateFormat     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r12 = "yyyy-MM-dd HH:mm:ss"
            r11.<init>(r12)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.io.PrintStream r12 = java.lang.System.out     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r13.<init>()     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r14 = "name:"
            r13.append(r14)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r13.append(r10)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r10 = r13.toString()     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r12.println(r10)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.io.PrintStream r10 = java.lang.System.out     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r12.<init>()     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r13 = "id:"
            r12.append(r13)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r12.append(r6)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r6 = ",millis:"
            r12.append(r6)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r12.append(r4)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r6 = ",insertTime:"
            r12.append(r6)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r12.append(r8)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r6 = r12.toString()     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r10.println(r6)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.io.PrintStream r6 = java.lang.System.out     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r7.<init>()     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r8 = ""
            r7.append(r8)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.util.Date r8 = new java.util.Date     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r8.<init>(r4)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r4 = r11.format(r8)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r7.append(r4)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r4 = r7.toString()     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r6.println(r4)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
        Lc9:
            int r3 = r3 + 1
            goto L2f
        Lcd:
            if (r1 == 0) goto Led
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto Led
            goto Lea
        Ld6:
            r0 = move-exception
            goto Lf1
        Ld8:
            r2 = move-exception
            boolean r3 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> Ld6
            if (r3 == 0) goto Le2
            java.lang.String r3 = "MS_AlarmTable"
            com.jingdong.sdk.oklog.OKLog.e(r3, r2)     // Catch: java.lang.Throwable -> Ld6
        Le2:
            if (r1 == 0) goto Led
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto Led
        Lea:
            r1.close()
        Led:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        Lf1:
            if (r1 == 0) goto Lfc
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto Lfc
            r1.close()
        Lfc:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto L101
        L100:
            throw r0
        L101:
            goto L100
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MS_AlarmTable.getAllList():java.util.ArrayList");
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
            java.lang.String r3 = "SELECT id,productCode FROM MS_AlarmTable"
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
            java.lang.String r4 = "productCode"
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
            java.lang.String r3 = "MS_AlarmTable"
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
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MS_AlarmTable.getList():java.util.ArrayList");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0086, code lost:
        if (r1.isClosed() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x009b, code lost:
        if (r1.isClosed() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009d, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.LinkedHashMap<java.lang.Long, java.lang.String> getMap(java.lang.Long r8) {
        /*
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            r3 = 60000(0xea60, double:2.9644E-319)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            r5.<init>()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            java.lang.String r6 = "SELECT * FROM MS_AlarmTable WHERE millis <= "
            r5.append(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            long r6 = r8.longValue()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            long r6 = r6 + r3
            r5.append(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            java.lang.String r6 = " AND "
            r5.append(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            java.lang.String r6 = "millis"
            r5.append(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            java.lang.String r6 = " >="
            r5.append(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            long r6 = r8.longValue()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            long r6 = r6 - r3
            r5.append(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            java.lang.String r8 = r5.toString()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            android.database.Cursor r1 = r2.rawQuery(r8, r1)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            if (r1 != 0) goto L4f
            if (r1 == 0) goto L4b
            boolean r8 = r1.isClosed()
            if (r8 != 0) goto L4b
            r1.close()
        L4b:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L4f:
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            int r8 = r1.getCount()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            if (r8 == 0) goto L80
            int r8 = r1.getCount()     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            r2 = 0
        L5d:
            if (r2 >= r8) goto L80
            r1.moveToPosition(r2)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            java.lang.String r3 = "productCode"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            long r3 = r1.getLong(r3)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            java.lang.String r5 = "productName"
            int r5 = r1.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            java.lang.String r5 = r1.getString(r5)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            r0.put(r3, r5)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            int r2 = r2 + 1
            goto L5d
        L80:
            if (r1 == 0) goto La0
            boolean r8 = r1.isClosed()
            if (r8 != 0) goto La0
            goto L9d
        L89:
            r8 = move-exception
            goto La4
        L8b:
            r8 = move-exception
            boolean r2 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L89
            if (r2 == 0) goto L95
            java.lang.String r2 = "MS_AlarmTable"
            com.jingdong.sdk.oklog.OKLog.e(r2, r8)     // Catch: java.lang.Throwable -> L89
        L95:
            if (r1 == 0) goto La0
            boolean r8 = r1.isClosed()
            if (r8 != 0) goto La0
        L9d:
            r1.close()
        La0:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        La4:
            if (r1 == 0) goto Laf
            boolean r0 = r1.isClosed()
            if (r0 != 0) goto Laf
            r1.close()
        Laf:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Lb4
        Lb3:
            throw r8
        Lb4:
            goto Lb3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MS_AlarmTable.getMap(java.lang.Long):java.util.LinkedHashMap");
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00cc A[Catch: all -> 0x00e6, TRY_LEAVE, TryCatch #4 {all -> 0x00e6, blocks: (B:40:0x00c8, B:42:0x00cc), top: B:69:0x00c8 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00de A[Catch: all -> 0x00f2, TryCatch #1 {all -> 0x00f2, blocks: (B:21:0x00a1, B:23:0x00a7, B:25:0x00ac, B:26:0x00af, B:53:0x00e8, B:55:0x00ee, B:59:0x00f6, B:60:0x00f9, B:44:0x00d3, B:46:0x00d9, B:48:0x00de, B:49:0x00e1), top: B:68:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00e8 A[Catch: all -> 0x00f2, TRY_ENTER, TryCatch #1 {all -> 0x00f2, blocks: (B:21:0x00a1, B:23:0x00a7, B:25:0x00ac, B:26:0x00af, B:53:0x00e8, B:55:0x00ee, B:59:0x00f6, B:60:0x00f9, B:44:0x00d3, B:46:0x00d9, B:48:0x00de, B:49:0x00e1), top: B:68:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00f6 A[Catch: all -> 0x00f2, TryCatch #1 {all -> 0x00f2, blocks: (B:21:0x00a1, B:23:0x00a7, B:25:0x00ac, B:26:0x00af, B:53:0x00e8, B:55:0x00ee, B:59:0x00f6, B:60:0x00f9, B:44:0x00d3, B:46:0x00d9, B:48:0x00de, B:49:0x00e1), top: B:68:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized int insertBatch(java.util.List<com.jingdong.common.entity.Product> r20, long r21, long r23) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MS_AlarmTable.insertBatch(java.util.List, long, long):int");
    }

    public static synchronized void insertOrUpdate(long j2, long j3, long j4, String str) {
        synchronized (MS_AlarmTable.class) {
            Cursor cursor = null;
            try {
                try {
                    SQLiteDatabase database = DBHelperUtil.getDatabase();
                    ContentValues contentValues = new ContentValues();
                    String[] strArr = {j2 + ""};
                    Cursor query = database.query("MS_AlarmTable", null, "productCode =?", strArr, null, null, null);
                    if (query != null) {
                        try {
                            if (query.getCount() != 0) {
                                database.delete("MS_AlarmTable", "productCode =?", strArr);
                            }
                        } catch (Exception e2) {
                            e = e2;
                            cursor = query;
                            if (OKLog.E) {
                                OKLog.e("MS_AlarmTable", e);
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
                    contentValues.put("productCode", Long.valueOf(j2));
                    contentValues.put("millis", Long.valueOf(j3));
                    contentValues.put("insertTime", Long.valueOf(j4));
                    contentValues.put("productName", str);
                    database.insert("MS_AlarmTable", null, contentValues);
                    if (query != null && !query.isClosed()) {
                        query.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
            DBHelperUtil.closeDatabase();
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
            java.lang.String r5 = "SELECT id,productCode FROM MS_AlarmTable WHERE productCode = "
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
            java.lang.String r3 = "productCode"
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
            java.lang.String r7 = "MS_AlarmTable"
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
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MS_AlarmTable.isExists(long):boolean");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE MS_AlarmTable('id' INTEGER PRIMARY KEY  NOT NULL ,productCode LONG,productName TEXT,millis LONG,insertTime DATETIME DEFAULT CURRENT_TIMESTAMP)");
        if (OKLog.D) {
            System.out.println("create MS_Alarm Table");
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists MS_AlarmTable");
    }
}
