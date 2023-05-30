package com.jingdong.common.database.table;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.incomingcall.InComStaff;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.List;

/* loaded from: classes5.dex */
public class MobileNumTable implements IJdTable {
    public static final String TABLE_NAME = "mobile_number_table";
    private static final String TAG = "MobileNumTable";
    public static final String TB_COLUMN_AVATAR = "avatar";
    public static final String TB_COLUMN_ID = "_id";
    public static final String TB_COLUMN_MOBILE = "mobile";
    public static final String TB_COLUMN_NAME = "name";
    public static final String TB_COLUMN_PIN = "pin";
    public static final String TB_COLUMN_TIP = "tip";

    public static synchronized void addAllMobileNum(List<InComStaff> list, String str) {
        synchronized (MobileNumTable.class) {
            if (list != null) {
                if (!list.isEmpty()) {
                    try {
                        deleteAllMobileNum(str);
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            addSingleMobileNum(list.get(i2), str);
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

    /* JADX WARN: Removed duplicated region for block: B:14:0x0076 A[Catch: all -> 0x00a0, TRY_ENTER, TryCatch #1 {, blocks: (B:4:0x0003, B:14:0x0076, B:15:0x0079, B:30:0x0097, B:31:0x009a, B:32:0x009d, B:28:0x0091), top: B:40:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void addSingleMobileNum(com.jingdong.common.entity.incomingcall.InComStaff r14, java.lang.String r15) {
        /*
            java.lang.Class<com.jingdong.common.database.table.MobileNumTable> r0 = com.jingdong.common.database.table.MobileNumTable.class
            monitor-enter(r0)
            java.lang.String r1 = r14.getNumber()     // Catch: java.lang.Throwable -> La0
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> La0
            if (r1 != 0) goto L9e
            r1 = 0
            android.database.sqlite.SQLiteDatabase r10 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            android.content.ContentValues r11 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r11.<init>()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r12 = "pin = ? and mobile = ? "
            r2 = 2
            java.lang.String[] r13 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r2 = 0
            r13[r2] = r15     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r2 = 1
            java.lang.String r3 = r14.getNumber()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r13[r2] = r3     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r2 = "name"
            java.lang.String r3 = r14.getName()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r11.put(r2, r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r2 = "avatar"
            java.lang.String r3 = r14.getPhoto()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r11.put(r2, r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r2 = "mobile"
            java.lang.String r3 = r14.getNumber()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r11.put(r2, r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r2 = "tip"
            java.lang.String r14 = r14.getText()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r11.put(r2, r14)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r14 = "pin"
            r11.put(r14, r15)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            java.lang.String r3 = "mobile_number_table"
            r4 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r10
            r5 = r12
            r6 = r13
            android.database.Cursor r14 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            if (r14 == 0) goto L6f
            int r15 = r14.getCount()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L80
            if (r15 == 0) goto L6f
            java.lang.String r15 = "mobile_number_table"
            r10.delete(r15, r12, r13)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L80
            java.lang.String r15 = "mobile_number_table"
            r10.insert(r15, r1, r11)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L80
            goto L74
        L6f:
            java.lang.String r15 = "mobile_number_table"
            r10.insert(r15, r1, r11)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L80
        L74:
            if (r14 == 0) goto L79
            r14.close()     // Catch: java.lang.Throwable -> La0
        L79:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()     // Catch: java.lang.Throwable -> La0
            goto L9e
        L7d:
            r15 = move-exception
            r1 = r14
            goto L95
        L80:
            r15 = move-exception
            r1 = r14
            goto L86
        L83:
            r15 = move-exception
            goto L95
        L85:
            r15 = move-exception
        L86:
            boolean r14 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L83
            if (r14 == 0) goto L8f
            java.lang.String r14 = "MobileNumTable"
            com.jingdong.sdk.oklog.OKLog.e(r14, r15)     // Catch: java.lang.Throwable -> L83
        L8f:
            if (r1 == 0) goto L79
            r1.close()     // Catch: java.lang.Throwable -> La0
            goto L79
        L95:
            if (r1 == 0) goto L9a
            r1.close()     // Catch: java.lang.Throwable -> La0
        L9a:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()     // Catch: java.lang.Throwable -> La0
            throw r15     // Catch: java.lang.Throwable -> La0
        L9e:
            monitor-exit(r0)
            return
        La0:
            r14 = move-exception
            monitor-exit(r0)
            goto La4
        La3:
            throw r14
        La4:
            goto La3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MobileNumTable.addSingleMobileNum(com.jingdong.common.entity.incomingcall.InComStaff, java.lang.String):void");
    }

    public static void deleteAllMobileNum(String str) {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TABLE_NAME, "pin = ? ", new String[]{str});
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            DBHelperUtil.closeDatabase();
            if (OKLog.D) {
                OKLog.d(TAG, "delete all mobile num");
            }
        } catch (Throwable th) {
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    public static List<InComStaff> getMobileNum(String str) {
        try {
            return getMobileNum(DBHelperUtil.getDatabase(), str);
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
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.database.Cursor] */
    public static synchronized InComStaff getStaff(String str, String str2) {
        Cursor cursor;
        synchronized (MobileNumTable.class) {
            ?? r1 = 0;
            try {
                try {
                    cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, null, "pin = ? and mobile = ? ", new String[]{str2, str}, null, null, null);
                    if (cursor != null) {
                        try {
                            if (cursor.getCount() >= 1 && cursor.moveToNext()) {
                                InComStaff inComStaff = new InComStaff(cursor.getString(cursor.getColumnIndex("mobile")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("avatar")), cursor.getString(cursor.getColumnIndex("tip")));
                                if (cursor != null) {
                                    cursor.close();
                                }
                                DBHelperUtil.closeDatabase();
                                return inComStaff;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            if (OKLog.E) {
                                OKLog.e(TAG, e);
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            DBHelperUtil.closeDatabase();
                            return null;
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    DBHelperUtil.closeDatabase();
                    return null;
                } catch (Exception e3) {
                    e = e3;
                    cursor = null;
                } catch (Throwable th) {
                    th = th;
                    if (r1 != 0) {
                        r1.close();
                    }
                    DBHelperUtil.closeDatabase();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                r1 = str;
            }
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase == null) {
            return;
        }
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS mobile_number_table('_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,pin TEXT,mobile TEXT,name TEXT,avatar TEXT,tip TEXT)");
        if (OKLog.D) {
            OKLog.d(TAG, "mobile_number_tabletable is created suc");
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (sQLiteDatabase == null) {
            return;
        }
        sQLiteDatabase.execSQL("drop table if exists mobile_number_table");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0053, code lost:
        if (r10 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0055, code lost:
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006f, code lost:
        if (r10 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0072, code lost:
        return r11;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.List<com.jingdong.common.entity.incomingcall.InComStaff> getMobileNum(android.database.sqlite.SQLiteDatabase r10, java.lang.String r11) {
        /*
            r0 = 0
            if (r10 != 0) goto L4
            return r0
        L4:
            java.lang.String r4 = "pin = ? "
            r1 = 1
            java.lang.String[] r5 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            r1 = 0
            r5[r1] = r11     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            java.lang.String r2 = "mobile_number_table"
            r3 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r10
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L73
            r11.<init>()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L73
        L1c:
            boolean r0 = r10.moveToNext()     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L73
            if (r0 == 0) goto L53
            com.jingdong.common.entity.incomingcall.InComStaff r0 = new com.jingdong.common.entity.incomingcall.InComStaff     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L73
            java.lang.String r1 = "mobile"
            int r1 = r10.getColumnIndex(r1)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L73
            java.lang.String r1 = r10.getString(r1)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L73
            java.lang.String r2 = "name"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L73
            java.lang.String r2 = r10.getString(r2)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L73
            java.lang.String r3 = "avatar"
            int r3 = r10.getColumnIndex(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L73
            java.lang.String r3 = r10.getString(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L73
            java.lang.String r4 = "tip"
            int r4 = r10.getColumnIndex(r4)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L73
            java.lang.String r4 = r10.getString(r4)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L73
            r0.<init>(r1, r2, r3, r4)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L73
            r11.add(r0)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L73
            goto L1c
        L53:
            if (r10 == 0) goto L72
        L55:
            r10.close()
            goto L72
        L59:
            r0 = move-exception
            goto L66
        L5b:
            r11 = move-exception
            r9 = r0
            r0 = r11
            r11 = r9
            goto L66
        L60:
            r11 = move-exception
            goto L75
        L62:
            r10 = move-exception
            r11 = r0
            r0 = r10
            r10 = r11
        L66:
            boolean r1 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L73
            if (r1 == 0) goto L6f
            java.lang.String r1 = "MobileNumTable"
            com.jingdong.sdk.oklog.OKLog.e(r1, r0)     // Catch: java.lang.Throwable -> L73
        L6f:
            if (r10 == 0) goto L72
            goto L55
        L72:
            return r11
        L73:
            r11 = move-exception
            r0 = r10
        L75:
            if (r0 == 0) goto L7a
            r0.close()
        L7a:
            goto L7c
        L7b:
            throw r11
        L7c:
            goto L7b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MobileNumTable.getMobileNum(android.database.sqlite.SQLiteDatabase, java.lang.String):java.util.List");
    }
}
