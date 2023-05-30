package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class HistoryTable implements IJdTable {
    public static final int MAX_HISTORY_NUM = 100;
    private static final String TAG = "HistoryTable";
    public static final String TB_CLOUMN_BROWSE_TIME = "browseTime";
    public static final String TB_CLOUMN_PRODUCT_CODE = "productCode";
    public static final String TB_CLOUMN_USER_NAME = "userName";
    public static final String TB_HISTORY_TABLE = "BrowseHistoryTable";

    public static void delAllHistory() {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TB_HISTORY_TABLE, "1=1", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static void delHistoryById(long j2) {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TB_HISTORY_TABLE, "productCode =?", new String[]{j2 + ""});
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static int deleteHistoryById(long j2) {
        int i2 = 0;
        try {
            try {
                i2 = DBHelperUtil.getDatabase().delete(TB_HISTORY_TABLE, "productCode =?", new String[]{j2 + ""});
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            return i2;
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a1, code lost:
        if (r0.isClosed() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b4, code lost:
        if (r0.isClosed() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b6, code lost:
        r0.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Map<java.lang.Long, com.jingdong.common.entity.Product> getBrowserHistoryByPage(int r9, int r10) {
        /*
            boolean r0 = com.jingdong.sdk.oklog.OKLog.D
            java.lang.String r1 = "HistoryTable"
            if (r0 == 0) goto L22
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "db startIndex:"
            r0.append(r2)
            r0.append(r9)
            java.lang.String r2 = ",perlMax:"
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            com.jingdong.sdk.oklog.OKLog.d(r1, r0)
        L22:
            r0 = 0
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>()
            android.database.sqlite.SQLiteDatabase r3 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            java.lang.String r4 = "SELECT id,productCode,browseTime FROM BrowseHistoryTable ORDER BY browseTime desc LIMIT ?,? "
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            r6 = 0
            r5[r6] = r9     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            java.lang.String r9 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            r10 = 1
            r5[r10] = r9     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            android.database.Cursor r0 = r3.rawQuery(r4, r5)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            if (r0 != 0) goto L54
            if (r0 == 0) goto L50
            boolean r9 = r0.isClosed()
            if (r9 != 0) goto L50
            r0.close()
        L50:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r2
        L54:
            r0.moveToFirst()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            int r9 = r0.getCount()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            if (r9 == 0) goto L9b
            int r9 = r0.getCount()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
        L61:
            if (r6 >= r9) goto L9b
            r0.moveToPosition(r6)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            com.jingdong.common.entity.Product r10 = new com.jingdong.common.entity.Product     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            r10.<init>()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            java.lang.String r3 = "productCode"
            int r3 = r0.getColumnIndex(r3)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            long r3 = r0.getLong(r3)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            java.lang.Long r5 = java.lang.Long.valueOf(r3)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            r10.setId(r5)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            java.lang.String r5 = "browseTime"
            int r5 = r0.getColumnIndex(r5)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            java.lang.String r5 = r0.getString(r5)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            java.util.Date r5 = com.jingdong.jdsdk.utils.FormatUtils.parseDate(r5)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            long r7 = r5.getTime()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            r10.setBrowserTime(r7)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            r2.put(r3, r10)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La6
            int r6 = r6 + 1
            goto L61
        L9b:
            if (r0 == 0) goto Lb9
            boolean r9 = r0.isClosed()
            if (r9 != 0) goto Lb9
            goto Lb6
        La4:
            r9 = move-exception
            goto Lbd
        La6:
            r9 = move-exception
            boolean r10 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> La4
            if (r10 == 0) goto Lae
            com.jingdong.sdk.oklog.OKLog.e(r1, r9)     // Catch: java.lang.Throwable -> La4
        Lae:
            if (r0 == 0) goto Lb9
            boolean r9 = r0.isClosed()
            if (r9 != 0) goto Lb9
        Lb6:
            r0.close()
        Lb9:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r2
        Lbd:
            if (r0 == 0) goto Lc8
            boolean r10 = r0.isClosed()
            if (r10 != 0) goto Lc8
            r0.close()
        Lc8:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Lcd
        Lcc:
            throw r9
        Lcd:
            goto Lcc
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.HistoryTable.getBrowserHistoryByPage(int, int):java.util.Map");
    }

    public static int getCount() {
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("select * from BrowseHistoryTable", null);
                int count = cursor.getCount();
                if (cursor != null) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
                return count;
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (cursor != null) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
                return 0;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x006d, code lost:
        if (r1.isClosed() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0082, code lost:
        if (r1.isClosed() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0084, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.Product> getHistoryByPage(int r7, int r8) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            java.lang.String r3 = "SELECT id,productCode FROM BrowseHistoryTable ORDER BY browseTime desc LIMIT ?,? "
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r5 = 0
            r6 = 1
            if (r7 != r6) goto L15
            r7 = 0
            goto L18
        L15:
            int r7 = r7 - r6
            int r7 = r7 * r8
        L18:
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r4[r5] = r7     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            java.lang.String r7 = java.lang.String.valueOf(r8)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r4[r6] = r7     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            android.database.Cursor r1 = r2.rawQuery(r3, r4)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            if (r1 != 0) goto L39
            if (r1 == 0) goto L35
            boolean r7 = r1.isClosed()
            if (r7 != 0) goto L35
            r1.close()
        L35:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L39:
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            int r7 = r1.getCount()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            if (r7 == 0) goto L67
            int r7 = r1.getCount()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
        L46:
            if (r5 >= r7) goto L67
            r1.moveToPosition(r5)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            com.jingdong.common.entity.Product r8 = new com.jingdong.common.entity.Product     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r8.<init>()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            java.lang.String r2 = "productCode"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            long r2 = r1.getLong(r2)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r8.setId(r2)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            r0.add(r8)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            int r5 = r5 + 1
            goto L46
        L67:
            if (r1 == 0) goto L87
            boolean r7 = r1.isClosed()
            if (r7 != 0) goto L87
            goto L84
        L70:
            r7 = move-exception
            goto L8b
        L72:
            r7 = move-exception
            boolean r8 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L70
            if (r8 == 0) goto L7c
            java.lang.String r8 = "HistoryTable"
            com.jingdong.sdk.oklog.OKLog.e(r8, r7)     // Catch: java.lang.Throwable -> L70
        L7c:
            if (r1 == 0) goto L87
            boolean r7 = r1.isClosed()
            if (r7 != 0) goto L87
        L84:
            r1.close()
        L87:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L8b:
            if (r1 == 0) goto L96
            boolean r8 = r1.isClosed()
            if (r8 != 0) goto L96
            r1.close()
        L96:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto L9b
        L9a:
            throw r7
        L9b:
            goto L9a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.HistoryTable.getHistoryByPage(int, int):java.util.ArrayList");
    }

    public static synchronized void insertOrUpdateBrowseHistory(long j2) {
        synchronized (HistoryTable.class) {
            Cursor cursor = null;
            try {
                try {
                    SQLiteDatabase database = DBHelperUtil.getDatabase();
                    ContentValues contentValues = new ContentValues();
                    String[] strArr = {j2 + ""};
                    Cursor query = database.query(TB_HISTORY_TABLE, null, "productCode =?", strArr, null, null, null);
                    if (query != null) {
                        try {
                            if (query.getCount() != 0) {
                                database.delete(TB_HISTORY_TABLE, "productCode =?", strArr);
                            }
                        } catch (Exception e2) {
                            e = e2;
                            cursor = query;
                            if (OKLog.E) {
                                OKLog.e(TAG, e);
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
                    database.insert(TB_HISTORY_TABLE, null, contentValues);
                    int count = getCount();
                    if (count > 100) {
                        database.execSQL("delete from BrowseHistoryTable where productCode in (select productCode from BrowseHistoryTable order by browseTime asc limit " + (count - 100) + ");");
                    }
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

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS BrowseHistoryTable ('id' INTEGER PRIMARY KEY  NOT NULL ,productCode LONG,userName TEXT,browseTime DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x006e, code lost:
        if (r1.isClosed() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0083, code lost:
        if (r1.isClosed() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0085, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.Product> getHistoryByPage(int r7, int r8, int r9) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            java.lang.String r3 = "SELECT id,productCode FROM BrowseHistoryTable ORDER BY browseTime desc LIMIT ?,? "
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r5 = 0
            r6 = 1
            if (r7 != r6) goto L15
            r7 = 0
            goto L18
        L15:
            int r7 = r7 - r6
            int r7 = r7 * r8
        L18:
            int r7 = r7 - r9
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r4[r5] = r7     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            java.lang.String r7 = java.lang.String.valueOf(r8)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r4[r6] = r7     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            android.database.Cursor r1 = r2.rawQuery(r3, r4)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            if (r1 != 0) goto L3a
            if (r1 == 0) goto L36
            boolean r7 = r1.isClosed()
            if (r7 != 0) goto L36
            r1.close()
        L36:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L3a:
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            int r7 = r1.getCount()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            if (r7 == 0) goto L68
            int r7 = r1.getCount()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
        L47:
            if (r5 >= r7) goto L68
            r1.moveToPosition(r5)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            com.jingdong.common.entity.Product r8 = new com.jingdong.common.entity.Product     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r8.<init>()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            java.lang.String r9 = "productCode"
            int r9 = r1.getColumnIndex(r9)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            long r2 = r1.getLong(r9)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            java.lang.Long r9 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r8.setId(r9)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r0.add(r8)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            int r5 = r5 + 1
            goto L47
        L68:
            if (r1 == 0) goto L88
            boolean r7 = r1.isClosed()
            if (r7 != 0) goto L88
            goto L85
        L71:
            r7 = move-exception
            goto L8c
        L73:
            r7 = move-exception
            boolean r8 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L71
            if (r8 == 0) goto L7d
            java.lang.String r8 = "HistoryTable"
            com.jingdong.sdk.oklog.OKLog.e(r8, r7)     // Catch: java.lang.Throwable -> L71
        L7d:
            if (r1 == 0) goto L88
            boolean r7 = r1.isClosed()
            if (r7 != 0) goto L88
        L85:
            r1.close()
        L88:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L8c:
            if (r1 == 0) goto L97
            boolean r8 = r1.isClosed()
            if (r8 != 0) goto L97
            r1.close()
        L97:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto L9c
        L9b:
            throw r7
        L9c:
            goto L9b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.HistoryTable.getHistoryByPage(int, int, int):java.util.ArrayList");
    }
}
