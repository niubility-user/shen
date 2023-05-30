package com.jingdong.common.database.table;

import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.incomingcall.InComStaff;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.List;

/* loaded from: classes5.dex */
public class LandlineNumTable implements IJdTable {
    public static final String TABLE_NAME = "landline_number_table";
    private static final String TAG = "LandlineNumTable";
    public static final String TB_COLUMN_AVATAR = "avatar";
    public static final String TB_COLUMN_ID = "_id";
    public static final String TB_COLUMN_MOBILE = "mobile";
    public static final String TB_COLUMN_NAME = "name";
    public static final String TB_COLUMN_TIP = "tip";
    public static final String TB_COLUMN_TYPE = "type";

    public static synchronized void addAllLandlineNum(List<InComStaff> list) {
        synchronized (LandlineNumTable.class) {
            if (list != null) {
                if (!list.isEmpty()) {
                    try {
                        DBHelperUtil.getDatabase().delete(TABLE_NAME, null, null);
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            addSingleLandlineNum(list.get(i2));
                        }
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e(TAG, e2);
                        }
                    }
                    DBHelperUtil.closeDatabase();
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x007b A[Catch: all -> 0x00a8, TRY_ENTER, TryCatch #2 {, blocks: (B:4:0x0003, B:14:0x007b, B:15:0x007e, B:27:0x0098, B:30:0x009f, B:31:0x00a2, B:32:0x00a5), top: B:40:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009f A[Catch: all -> 0x00a8, TryCatch #2 {, blocks: (B:4:0x0003, B:14:0x007b, B:15:0x007e, B:27:0x0098, B:30:0x009f, B:31:0x00a2, B:32:0x00a5), top: B:40:0x0003 }] */
    /* JADX WARN: Type inference failed for: r15v0, types: [com.jingdong.common.entity.incomingcall.InComStaff] */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v6, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void addSingleLandlineNum(com.jingdong.common.entity.incomingcall.InComStaff r15) {
        /*
            java.lang.Class<com.jingdong.common.database.table.LandlineNumTable> r0 = com.jingdong.common.database.table.LandlineNumTable.class
            monitor-enter(r0)
            java.lang.String r1 = r15.getNumber()     // Catch: java.lang.Throwable -> La8
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> La8
            if (r1 != 0) goto La6
            r1 = 0
            android.database.sqlite.SQLiteDatabase r10 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            android.content.ContentValues r11 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            r11.<init>()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            java.lang.String r12 = "mobile = ? "
            r2 = 1
            java.lang.String[] r13 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            r2 = 0
            java.lang.String r3 = r15.getNumber()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            r13[r2] = r3     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            java.lang.String r2 = "name"
            java.lang.String r3 = r15.getName()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            r11.put(r2, r3)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            java.lang.String r2 = "avatar"
            java.lang.String r3 = r15.getPhoto()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            r11.put(r2, r3)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            java.lang.String r2 = "mobile"
            java.lang.String r3 = r15.getNumber()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            r11.put(r2, r3)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            java.lang.String r2 = "tip"
            java.lang.String r3 = r15.getText()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            r11.put(r2, r3)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            java.lang.String r2 = "type"
            int r15 = r15.getType()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            r11.put(r2, r15)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            java.lang.String r3 = "landline_number_table"
            r4 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r10
            r5 = r12
            r6 = r13
            android.database.Cursor r15 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L89
            if (r15 == 0) goto L74
            int r2 = r15.getCount()     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L9c
            if (r2 == 0) goto L74
            java.lang.String r2 = "landline_number_table"
            r10.delete(r2, r12, r13)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L9c
            java.lang.String r2 = "landline_number_table"
            r10.insert(r2, r1, r11)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L9c
            goto L79
        L74:
            java.lang.String r2 = "landline_number_table"
            r10.insert(r2, r1, r11)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L9c
        L79:
            if (r15 == 0) goto L7e
            r15.close()     // Catch: java.lang.Throwable -> La8
        L7e:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()     // Catch: java.lang.Throwable -> La8
            goto La6
        L82:
            r1 = move-exception
            goto L8d
        L84:
            r15 = move-exception
            r14 = r1
            r1 = r15
            r15 = r14
            goto L9d
        L89:
            r15 = move-exception
            r14 = r1
            r1 = r15
            r15 = r14
        L8d:
            boolean r2 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L9c
            if (r2 == 0) goto L96
            java.lang.String r2 = "LandlineNumTable"
            com.jingdong.sdk.oklog.OKLog.e(r2, r1)     // Catch: java.lang.Throwable -> L9c
        L96:
            if (r15 == 0) goto L7e
            r15.close()     // Catch: java.lang.Throwable -> La8
            goto L7e
        L9c:
            r1 = move-exception
        L9d:
            if (r15 == 0) goto La2
            r15.close()     // Catch: java.lang.Throwable -> La8
        La2:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()     // Catch: java.lang.Throwable -> La8
            throw r1     // Catch: java.lang.Throwable -> La8
        La6:
            monitor-exit(r0)
            return
        La8:
            r15 = move-exception
            monitor-exit(r0)
            goto Lac
        Lab:
            throw r15
        Lac:
            goto Lab
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.LandlineNumTable.addSingleLandlineNum(com.jingdong.common.entity.incomingcall.InComStaff):void");
    }

    public static void deleteAllLandlineNum() {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TABLE_NAME, null, null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            if (OKLog.D) {
                OKLog.d(TAG, "delete all landlineNum");
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static List<InComStaff> getLandlineNum() {
        try {
            return getLandlineNum(DBHelperUtil.getDatabase());
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return null;
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0093 A[Catch: all -> 0x009a, TRY_ENTER, TryCatch #4 {, blocks: (B:13:0x0062, B:14:0x0065, B:20:0x006e, B:21:0x0071, B:32:0x0088, B:33:0x008b, B:38:0x0093, B:39:0x0096, B:40:0x0099), top: B:47:0x0004 }] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v5, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized com.jingdong.common.entity.incomingcall.InComStaff getStaff(java.lang.String r12) {
        /*
            java.lang.Class<com.jingdong.common.database.table.LandlineNumTable> r0 = com.jingdong.common.database.table.LandlineNumTable.class
            monitor-enter(r0)
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            java.lang.String r5 = "mobile = ? "
            r10 = 1
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            r3 = 0
            r6[r3] = r12     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            java.lang.String r3 = "landline_number_table"
            r4 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            if (r12 == 0) goto L6c
            int r2 = r12.getCount()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            if (r2 < r10) goto L6c
            boolean r2 = r12.moveToNext()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            if (r2 == 0) goto L6c
            com.jingdong.common.entity.incomingcall.InComStaff r2 = new com.jingdong.common.entity.incomingcall.InComStaff     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            java.lang.String r3 = "mobile"
            int r3 = r12.getColumnIndex(r3)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            java.lang.String r4 = r12.getString(r3)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            java.lang.String r3 = "name"
            int r3 = r12.getColumnIndex(r3)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            java.lang.String r5 = r12.getString(r3)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            java.lang.String r3 = "avatar"
            int r3 = r12.getColumnIndex(r3)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            java.lang.String r6 = r12.getString(r3)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            java.lang.String r3 = "tip"
            int r3 = r12.getColumnIndex(r3)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            java.lang.String r7 = r12.getString(r3)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            java.lang.String r3 = "type"
            int r3 = r12.getColumnIndex(r3)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            int r8 = r12.getInt(r3)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            r3 = r2
            r3.<init>(r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L90
            if (r12 == 0) goto L65
            r12.close()     // Catch: java.lang.Throwable -> L9a
        L65:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()     // Catch: java.lang.Throwable -> L9a
            monitor-exit(r0)
            return r2
        L6a:
            r2 = move-exception
            goto L7d
        L6c:
            if (r12 == 0) goto L71
            r12.close()     // Catch: java.lang.Throwable -> L9a
        L71:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()     // Catch: java.lang.Throwable -> L9a
            monitor-exit(r0)
            return r1
        L76:
            r12 = move-exception
            r11 = r1
            r1 = r12
            r12 = r11
            goto L91
        L7b:
            r2 = move-exception
            r12 = r1
        L7d:
            boolean r3 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L90
            if (r3 == 0) goto L86
            java.lang.String r3 = "LandlineNumTable"
            com.jingdong.sdk.oklog.OKLog.e(r3, r2)     // Catch: java.lang.Throwable -> L90
        L86:
            if (r12 == 0) goto L8b
            r12.close()     // Catch: java.lang.Throwable -> L9a
        L8b:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()     // Catch: java.lang.Throwable -> L9a
            monitor-exit(r0)
            return r1
        L90:
            r1 = move-exception
        L91:
            if (r12 == 0) goto L96
            r12.close()     // Catch: java.lang.Throwable -> L9a
        L96:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()     // Catch: java.lang.Throwable -> L9a
            throw r1     // Catch: java.lang.Throwable -> L9a
        L9a:
            r12 = move-exception
            monitor-exit(r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.LandlineNumTable.getStaff(java.lang.String):com.jingdong.common.entity.incomingcall.InComStaff");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase == null) {
            return;
        }
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS landline_number_table('_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,mobile TEXT,type INTEGER,name TEXT,avatar TEXT,tip TEXT)");
        if (OKLog.D) {
            OKLog.d(TAG, "landline_number_tableis created suc");
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0058, code lost:
        if (r10 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x005a, code lost:
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0077, code lost:
        if (r10 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x007a, code lost:
        return r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.List<com.jingdong.common.entity.incomingcall.InComStaff> getLandlineNum(android.database.sqlite.SQLiteDatabase r10) {
        /*
            r0 = 0
            if (r10 != 0) goto L4
            return r0
        L4:
            java.lang.String r2 = "landline_number_table"
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r10
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L6a
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Exception -> L60 java.lang.Throwable -> L7b
            r1.<init>()     // Catch: java.lang.Exception -> L60 java.lang.Throwable -> L7b
        L16:
            boolean r0 = r10.moveToNext()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            if (r0 == 0) goto L58
            com.jingdong.common.entity.incomingcall.InComStaff r0 = new com.jingdong.common.entity.incomingcall.InComStaff     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            java.lang.String r2 = "mobile"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            java.lang.String r3 = r10.getString(r2)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            java.lang.String r2 = "name"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            java.lang.String r4 = r10.getString(r2)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            java.lang.String r2 = "avatar"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            java.lang.String r5 = r10.getString(r2)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            java.lang.String r2 = "tip"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            java.lang.String r6 = r10.getString(r2)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            java.lang.String r2 = "type"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            int r7 = r10.getInt(r2)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            r1.add(r0)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7b
            goto L16
        L58:
            if (r10 == 0) goto L7a
        L5a:
            r10.close()
            goto L7a
        L5e:
            r0 = move-exception
            goto L6e
        L60:
            r1 = move-exception
            r9 = r1
            r1 = r0
            r0 = r9
            goto L6e
        L65:
            r10 = move-exception
            r9 = r0
            r0 = r10
            r10 = r9
            goto L7c
        L6a:
            r10 = move-exception
            r1 = r0
            r0 = r10
            r10 = r1
        L6e:
            boolean r2 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L7b
            if (r2 == 0) goto L77
            java.lang.String r2 = "LandlineNumTable"
            com.jingdong.sdk.oklog.OKLog.e(r2, r0)     // Catch: java.lang.Throwable -> L7b
        L77:
            if (r10 == 0) goto L7a
            goto L5a
        L7a:
            return r1
        L7b:
            r0 = move-exception
        L7c:
            if (r10 == 0) goto L81
            r10.close()
        L81:
            goto L83
        L82:
            throw r0
        L83:
            goto L82
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.LandlineNumTable.getLandlineNum(android.database.sqlite.SQLiteDatabase):java.util.List");
    }
}
