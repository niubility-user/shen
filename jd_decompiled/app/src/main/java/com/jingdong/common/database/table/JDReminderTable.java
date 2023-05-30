package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class JDReminderTable implements IJdTable {
    private static final long DEVIATION_TIME = 300000;
    private static final String TAG = "JDReminderTable";
    private static final String TB_CLOUMN_INSERT_TIME = "insertTime";
    private static final String TB_CLOUMN_REMAIN_TIME = "remainTimeMillis";
    private static final String TB_CLOUMN_REMINDER_ID = "reminderID";
    private static final String TB_CLOUMN_REMINDER_TITLE = "reminderTitle";
    private static final String TB_CLOUMN_REMINDER_TYPE = "reminderType";
    private static final String TB_CLOUMN_REQUESTCODE = "requestCode";
    private static final String TB_CLOUMN_START_TIME = "startTimeMillis";
    private static final String TB_CLOUMN_TARGET_URL = "targetURL";
    private static final String TB_PRIMARY_KEY = "_id";
    private static final String TB_REMINDER_TABLE = "JD_ReminderTable";

    public static int checkExistByTypeAndId(String str, long j2, long j3) {
        Cursor cursor = null;
        try {
            try {
                Cursor rawQuery = DBHelperUtil.getDatabase().rawQuery("SELECT _id,reminderType,reminderID FROM JD_ReminderTable WHERE reminderType = '" + str + "' AND " + TB_CLOUMN_REMINDER_ID + " = " + j2 + " AND " + TB_CLOUMN_START_TIME + " >= " + (j3 - 300000) + " AND " + TB_CLOUMN_START_TIME + " < " + (j3 + 300000), null);
                if (rawQuery == null) {
                    if (rawQuery != null && !rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                    DBHelperUtil.closeDatabase();
                    return -1;
                }
                rawQuery.moveToFirst();
                if (rawQuery.getCount() != 0) {
                    rawQuery.moveToPosition(rawQuery.getCount() - 1);
                    int i2 = rawQuery.getInt(rawQuery.getColumnIndex("_id"));
                    if (rawQuery != null && !rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                    DBHelperUtil.closeDatabase();
                    return i2;
                }
                if (rawQuery != null && !rawQuery.isClosed()) {
                    rawQuery.close();
                }
                DBHelperUtil.closeDatabase();
                return -1;
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0 && !cursor.isClosed()) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
                return -1;
            }
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    public static int checkExistByTypeAndURL(String str, long j2, String str2) {
        Cursor cursor = null;
        try {
            try {
                Cursor rawQuery = DBHelperUtil.getDatabase().rawQuery("SELECT _id,reminderType,targetURL FROM JD_ReminderTable WHERE reminderType = '" + str + "' AND " + TB_CLOUMN_START_TIME + " >= " + (j2 - 300000) + " AND " + TB_CLOUMN_START_TIME + " < " + (j2 + 300000) + " AND " + TB_CLOUMN_TARGET_URL + " = '" + str2 + "'", null);
                if (rawQuery == null) {
                    if (rawQuery != null && !rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                    DBHelperUtil.closeDatabase();
                    return -1;
                }
                rawQuery.moveToFirst();
                if (rawQuery.getCount() != 0) {
                    rawQuery.moveToPosition(rawQuery.getCount() - 1);
                    int i2 = rawQuery.getInt(rawQuery.getColumnIndex("_id"));
                    if (rawQuery != null && !rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                    DBHelperUtil.closeDatabase();
                    return i2;
                }
                if (rawQuery != null && !rawQuery.isClosed()) {
                    rawQuery.close();
                }
                DBHelperUtil.closeDatabase();
                return -1;
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0 && !cursor.isClosed()) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
                return -1;
            }
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    public static synchronized void deleteByTypeAndId(String str, long j2, long j3) {
        synchronized (JDReminderTable.class) {
            try {
                DBHelperUtil.getDatabase().execSQL("delete from JD_ReminderTable where reminderType = '" + str + "' and " + TB_CLOUMN_REMINDER_ID + " = " + j2 + " AND " + TB_CLOUMN_START_TIME + " >= " + (j3 - 300000) + " AND " + TB_CLOUMN_START_TIME + " < " + (300000 + j3));
                if (OKLog.D) {
                    OKLog.d(TAG, "delete, type: " + str + ", id: " + j2 + ", startTime(aligned): " + j3);
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            DBHelperUtil.closeDatabase();
        }
    }

    public static synchronized void deleteByTypeAndURL(String str, long j2, String str2) {
        synchronized (JDReminderTable.class) {
            try {
                DBHelperUtil.getDatabase().execSQL("delete from JD_ReminderTable where reminderType = '" + str + "' AND " + TB_CLOUMN_START_TIME + " >= " + (j2 - 300000) + " AND " + TB_CLOUMN_START_TIME + " < " + (300000 + j2) + " AND " + TB_CLOUMN_TARGET_URL + " = '" + str2 + "'");
                if (OKLog.D) {
                    OKLog.d(TAG, "delete, type: " + str + ", startTime(aligned): " + j2 + ", targetURL: " + str2);
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            DBHelperUtil.closeDatabase();
        }
    }

    public static void deleteRemindersBeforeTargetTime(long j2) {
        try {
            try {
                DBHelperUtil.getDatabase().execSQL("DELETE FROM JD_ReminderTable WHERE startTimeMillis < " + j2);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x0084, code lost:
        if (r2.isClosed() == false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0099, code lost:
        if (r2.isClosed() == false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x009b, code lost:
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Map<java.lang.Long, java.lang.Integer> getAllRemainReminders(long r13) {
        /*
            java.lang.String r0 = "startTimeMillis"
            androidx.collection.ArrayMap r1 = new androidx.collection.ArrayMap
            r1.<init>()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            r4.<init>()     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            java.lang.String r5 = "SELECT * FROM JD_ReminderTable WHERE startTimeMillis >= "
            r4.append(r5)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            r5 = 300000(0x493e0, double:1.482197E-318)
            long r5 = r13 - r5
            r4.append(r5)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            java.lang.String r5 = " GROUP BY "
            r4.append(r5)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            r4.append(r0)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            android.database.Cursor r2 = r3.rawQuery(r4, r2)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            if (r2 != 0) goto L3f
            if (r2 == 0) goto L3b
            boolean r13 = r2.isClosed()
            if (r13 != 0) goto L3b
            r2.close()
        L3b:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r1
        L3f:
            r2.moveToFirst()     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            int r3 = r2.getCount()     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            if (r3 == 0) goto L7e
            int r3 = r2.getCount()     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            r4 = 0
        L4d:
            if (r4 >= r3) goto L7e
            r2.moveToPosition(r4)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            int r5 = r2.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            long r5 = r2.getLong(r5)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            java.lang.String r7 = "requestCode"
            int r7 = r2.getColumnIndex(r7)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            int r7 = r2.getInt(r7)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            long r8 = com.jingdong.common.utils.JDReminderUtils.getAlignedTime(r5)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            r10 = 180000(0x2bf20, double:8.8932E-319)
            long r10 = r10 + r13
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 <= 0) goto L7b
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
            r1.put(r5, r6)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L89
        L7b:
            int r4 = r4 + 1
            goto L4d
        L7e:
            if (r2 == 0) goto L9e
            boolean r13 = r2.isClosed()
            if (r13 != 0) goto L9e
            goto L9b
        L87:
            r13 = move-exception
            goto La2
        L89:
            r13 = move-exception
            boolean r14 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L87
            if (r14 == 0) goto L93
            java.lang.String r14 = "JDReminderTable"
            com.jingdong.sdk.oklog.OKLog.e(r14, r13)     // Catch: java.lang.Throwable -> L87
        L93:
            if (r2 == 0) goto L9e
            boolean r13 = r2.isClosed()
            if (r13 != 0) goto L9e
        L9b:
            r2.close()
        L9e:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r1
        La2:
            if (r2 == 0) goto Lad
            boolean r14 = r2.isClosed()
            if (r14 != 0) goto Lad
            r2.close()
        Lad:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Lb2
        Lb1:
            throw r13
        Lb2:
            goto Lb1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderTable.getAllRemainReminders(long):java.util.Map");
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x006f, code lost:
        if (r1.isClosed() == false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0084, code lost:
        if (r1.isClosed() == false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0086, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<java.lang.Long> getAllReminderIdBasedOnTypeAndTime(java.lang.String r5, long r6) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r3.<init>()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r4 = "SELECT * FROM JD_ReminderTable WHERE reminderType = '"
            r3.append(r4)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r3.append(r5)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r5 = "' AND "
            r3.append(r5)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r5 = "startTimeMillis"
            r3.append(r5)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r5 = " >= "
            r3.append(r5)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r3.append(r6)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r5 = r3.toString()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            android.database.Cursor r1 = r2.rawQuery(r5, r1)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            if (r1 != 0) goto L42
            if (r1 == 0) goto L3e
            boolean r5 = r1.isClosed()
            if (r5 != 0) goto L3e
            r1.close()
        L3e:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L42:
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            int r5 = r1.getCount()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            if (r5 == 0) goto L69
            int r5 = r1.getCount()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r6 = 0
        L50:
            if (r6 >= r5) goto L69
            r1.moveToPosition(r6)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r7 = "reminderID"
            int r7 = r1.getColumnIndex(r7)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            long r2 = r1.getLong(r7)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.Long r7 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r0.add(r7)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            int r6 = r6 + 1
            goto L50
        L69:
            if (r1 == 0) goto L89
            boolean r5 = r1.isClosed()
            if (r5 != 0) goto L89
            goto L86
        L72:
            r5 = move-exception
            goto L8d
        L74:
            r5 = move-exception
            boolean r6 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L72
            if (r6 == 0) goto L7e
            java.lang.String r6 = "JDReminderTable"
            com.jingdong.sdk.oklog.OKLog.e(r6, r5)     // Catch: java.lang.Throwable -> L72
        L7e:
            if (r1 == 0) goto L89
            boolean r5 = r1.isClosed()
            if (r5 != 0) goto L89
        L86:
            r1.close()
        L89:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L8d:
            if (r1 == 0) goto L98
            boolean r6 = r1.isClosed()
            if (r6 != 0) goto L98
            r1.close()
        L98:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto L9d
        L9c:
            throw r5
        L9d:
            goto L9c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderTable.getAllReminderIdBasedOnTypeAndTime(java.lang.String, long):java.util.ArrayList");
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a6, code lost:
        if (r1.isClosed() == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00bb, code lost:
        if (r1.isClosed() == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00bd, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.JDReminder> getAllRemindersAfterTargetTime(long r14) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r3.<init>()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r4 = "SELECT * FROM JD_ReminderTable WHERE startTimeMillis >= "
            r3.append(r4)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r3.append(r14)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r14 = r3.toString()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r15.<init>()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r15.append(r14)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r14 = " ORDER BY startTimeMillis , insertTime ASC"
            r15.append(r14)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r14 = r15.toString()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            android.database.Cursor r1 = r2.rawQuery(r14, r1)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            if (r1 != 0) goto L41
            if (r1 == 0) goto L3d
            boolean r14 = r1.isClosed()
            if (r14 != 0) goto L3d
            r1.close()
        L3d:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L41:
            r1.moveToFirst()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            int r14 = r1.getCount()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            if (r14 == 0) goto La0
            int r14 = r1.getCount()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r15 = 0
        L4f:
            if (r15 >= r14) goto La0
            r1.moveToPosition(r15)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r2 = "reminderType"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r3 = "reminderID"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            long r6 = r1.getLong(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r3 = "reminderTitle"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r8 = r1.getString(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r3 = "startTimeMillis"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            long r9 = r1.getLong(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r3 = "insertTime"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            long r11 = r1.getLong(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r3 = "targetURL"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r13 = r1.getString(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            com.jingdong.common.entity.JDReminder r3 = new com.jingdong.common.entity.JDReminder     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            com.jingdong.common.utils.JDReminderUtils$Type r5 = com.jingdong.common.utils.JDReminderUtils.Type.getType(r2)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r4 = r3
            r4.<init>(r5, r6, r8, r9, r11, r13)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r0.add(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            int r15 = r15 + 1
            goto L4f
        La0:
            if (r1 == 0) goto Lc0
            boolean r14 = r1.isClosed()
            if (r14 != 0) goto Lc0
            goto Lbd
        La9:
            r14 = move-exception
            goto Lc4
        Lab:
            r14 = move-exception
            boolean r15 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> La9
            if (r15 == 0) goto Lb5
            java.lang.String r15 = "JDReminderTable"
            com.jingdong.sdk.oklog.OKLog.e(r15, r14)     // Catch: java.lang.Throwable -> La9
        Lb5:
            if (r1 == 0) goto Lc0
            boolean r14 = r1.isClosed()
            if (r14 != 0) goto Lc0
        Lbd:
            r1.close()
        Lc0:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        Lc4:
            if (r1 == 0) goto Lcf
            boolean r15 = r1.isClosed()
            if (r15 != 0) goto Lcf
            r1.close()
        Lcf:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Ld4
        Ld3:
            throw r14
        Ld4:
            goto Ld3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderTable.getAllRemindersAfterTargetTime(long):java.util.ArrayList");
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x007e, code lost:
        if (r2.isClosed() == false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0093, code lost:
        if (r2.isClosed() == false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0095, code lost:
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Set<java.lang.Long> getReminderDaysDuringTimePeriod(long r6, long r8) {
        /*
            java.lang.String r0 = "startTimeMillis"
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            r4.<init>()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            java.lang.String r5 = "SELECT * FROM JD_ReminderTable WHERE startTimeMillis >= "
            r4.append(r5)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            r4.append(r6)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            java.lang.String r6 = " AND "
            r4.append(r6)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            r4.append(r0)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            java.lang.String r6 = " < "
            r4.append(r6)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            r4.append(r8)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            r7.<init>()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            r7.append(r6)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            java.lang.String r6 = " ORDER BY startTimeMillis , insertTime ASC"
            r7.append(r6)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            java.lang.String r6 = r7.toString()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            android.database.Cursor r2 = r3.rawQuery(r6, r2)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            if (r2 != 0) goto L53
            if (r2 == 0) goto L4f
            boolean r6 = r2.isClosed()
            if (r6 != 0) goto L4f
            r2.close()
        L4f:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r1
        L53:
            r2.moveToFirst()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            int r6 = r2.getCount()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            if (r6 == 0) goto L78
            int r6 = r2.getCount()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            r7 = 0
        L61:
            if (r7 >= r6) goto L78
            r2.moveToPosition(r7)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            int r8 = r2.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            long r8 = r2.getLong(r8)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            r1.add(r8)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            int r7 = r7 + 1
            goto L61
        L78:
            if (r2 == 0) goto L98
            boolean r6 = r2.isClosed()
            if (r6 != 0) goto L98
            goto L95
        L81:
            r6 = move-exception
            goto L9c
        L83:
            r6 = move-exception
            boolean r7 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L81
            if (r7 == 0) goto L8d
            java.lang.String r7 = "JDReminderTable"
            com.jingdong.sdk.oklog.OKLog.e(r7, r6)     // Catch: java.lang.Throwable -> L81
        L8d:
            if (r2 == 0) goto L98
            boolean r6 = r2.isClosed()
            if (r6 != 0) goto L98
        L95:
            r2.close()
        L98:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r1
        L9c:
            if (r2 == 0) goto La7
            boolean r7 = r2.isClosed()
            if (r7 != 0) goto La7
            r2.close()
        La7:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Lac
        Lab:
            throw r6
        Lac:
            goto Lab
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderTable.getReminderDaysDuringTimePeriod(long, long):java.util.Set");
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0083, code lost:
        if (r1.isClosed() == false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0098, code lost:
        if (r1.isClosed() == false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x009a, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.LinkedHashMap<java.lang.Integer, java.lang.Integer> getRemindersByStartTime(long r8) {
        /*
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r3.<init>()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r4 = "SELECT * FROM JD_ReminderTable WHERE startTimeMillis >= "
            r3.append(r4)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r4 = 300000(0x493e0, double:1.482197E-318)
            long r6 = r8 - r4
            r3.append(r6)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r6 = " AND "
            r3.append(r6)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r6 = "startTimeMillis"
            r3.append(r6)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r6 = " < "
            r3.append(r6)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            long r8 = r8 + r4
            r3.append(r8)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r8 = r3.toString()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            android.database.Cursor r1 = r2.rawQuery(r8, r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            if (r1 != 0) goto L48
            if (r1 == 0) goto L44
            boolean r8 = r1.isClosed()
            if (r8 != 0) goto L44
            r1.close()
        L44:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L48:
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            int r8 = r1.getCount()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            if (r8 == 0) goto L7d
            int r8 = r1.getCount()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r9 = 0
        L56:
            if (r9 >= r8) goto L7d
            r1.moveToPosition(r9)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r2 = "_id"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            int r2 = r1.getInt(r2)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r3 = "requestCode"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            int r3 = r1.getInt(r3)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r0.put(r2, r3)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            int r9 = r9 + 1
            goto L56
        L7d:
            if (r1 == 0) goto L9d
            boolean r8 = r1.isClosed()
            if (r8 != 0) goto L9d
            goto L9a
        L86:
            r8 = move-exception
            goto La1
        L88:
            r8 = move-exception
            boolean r9 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L86
            if (r9 == 0) goto L92
            java.lang.String r9 = "JDReminderTable"
            com.jingdong.sdk.oklog.OKLog.e(r9, r8)     // Catch: java.lang.Throwable -> L86
        L92:
            if (r1 == 0) goto L9d
            boolean r8 = r1.isClosed()
            if (r8 != 0) goto L9d
        L9a:
            r1.close()
        L9d:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        La1:
            if (r1 == 0) goto Lac
            boolean r9 = r1.isClosed()
            if (r9 != 0) goto Lac
            r1.close()
        Lac:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Lb1
        Lb0:
            throw r8
        Lb1:
            goto Lb0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderTable.getRemindersByStartTime(long):java.util.LinkedHashMap");
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x00ba, code lost:
        if (r2.isClosed() == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00cf, code lost:
        if (r2.isClosed() == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00d1, code lost:
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.JDReminder> getRemindersDuringTimePeriod(long r17, long r19) {
        /*
            java.lang.String r0 = "startTimeMillis"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            r4.<init>()     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r5 = "SELECT * FROM JD_ReminderTable WHERE startTimeMillis >= "
            r4.append(r5)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            r5 = r17
            r4.append(r5)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r5 = " AND "
            r4.append(r5)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            r4.append(r0)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r5 = " < "
            r4.append(r5)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            r5 = r19
            r4.append(r5)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            r5.<init>()     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            r5.append(r4)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r4 = " ORDER BY startTimeMillis , insertTime ASC"
            r5.append(r4)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r4 = r5.toString()     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            android.database.Cursor r2 = r3.rawQuery(r4, r2)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            if (r2 != 0) goto L57
            if (r2 == 0) goto L53
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L53
            r2.close()
        L53:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r1
        L57:
            r2.moveToFirst()     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            int r3 = r2.getCount()     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            if (r3 == 0) goto Lb4
            int r3 = r2.getCount()     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            r4 = 0
        L65:
            if (r4 >= r3) goto Lb4
            r2.moveToPosition(r4)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r5 = "reminderType"
            int r5 = r2.getColumnIndex(r5)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r5 = r2.getString(r5)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r6 = "reminderID"
            int r6 = r2.getColumnIndex(r6)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            long r9 = r2.getLong(r6)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r6 = "reminderTitle"
            int r6 = r2.getColumnIndex(r6)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r11 = r2.getString(r6)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            int r6 = r2.getColumnIndex(r0)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            long r12 = r2.getLong(r6)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r6 = "insertTime"
            int r6 = r2.getColumnIndex(r6)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            long r14 = r2.getLong(r6)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r6 = "targetURL"
            int r6 = r2.getColumnIndex(r6)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r16 = r2.getString(r6)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            com.jingdong.common.entity.JDReminder r6 = new com.jingdong.common.entity.JDReminder     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            com.jingdong.common.utils.JDReminderUtils$Type r8 = com.jingdong.common.utils.JDReminderUtils.Type.getType(r5)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            r7 = r6
            r7.<init>(r8, r9, r11, r12, r14, r16)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            r1.add(r6)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            int r4 = r4 + 1
            goto L65
        Lb4:
            if (r2 == 0) goto Ld4
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto Ld4
            goto Ld1
        Lbd:
            r0 = move-exception
            goto Ld8
        Lbf:
            r0 = move-exception
            boolean r3 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> Lbd
            if (r3 == 0) goto Lc9
            java.lang.String r3 = "JDReminderTable"
            com.jingdong.sdk.oklog.OKLog.e(r3, r0)     // Catch: java.lang.Throwable -> Lbd
        Lc9:
            if (r2 == 0) goto Ld4
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto Ld4
        Ld1:
            r2.close()
        Ld4:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r1
        Ld8:
            if (r2 == 0) goto Le3
            boolean r1 = r2.isClosed()
            if (r1 != 0) goto Le3
            r2.close()
        Le3:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Le8
        Le7:
            throw r0
        Le8:
            goto Le7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderTable.getRemindersDuringTimePeriod(long, long):java.util.ArrayList");
    }

    /* JADX WARN: Code restructure failed: missing block: B:95:0x00ed, code lost:
        if (r4.isClosed() != false) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x00ef, code lost:
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized int insertOrUpdate(java.lang.String r20, long r21, java.lang.String r23, long r24, long r26, long r28, int r30, java.lang.String r31) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderTable.insertOrUpdate(java.lang.String, long, java.lang.String, long, long, long, int, java.lang.String):int");
    }

    public static synchronized void updateByIdAndRequestCode(int i2, int i3) {
        synchronized (JDReminderTable.class) {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                String[] strArr = {i2 + ""};
                ContentValues contentValues = new ContentValues();
                contentValues.put("requestCode", Integer.valueOf(i3));
                database.update(TB_REMINDER_TABLE, contentValues, "_id = ?", strArr);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            DBHelperUtil.closeDatabase();
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS JD_ReminderTable(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,reminderType TEXT,reminderID LONG,reminderTitle TEXT,startTimeMillis LONG,remainTimeMillis LONG,insertTime DATETIME DEFAULT CURRENT_TIMESTAMP,targetURL TEXT,requestCode INTEGER DEFAULT 0)");
        if (OKLog.D) {
            OKLog.d(TAG, "create JD_ReminderTable");
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }
}
