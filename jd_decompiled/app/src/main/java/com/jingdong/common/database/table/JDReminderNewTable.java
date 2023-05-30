package com.jingdong.common.database.table;

import android.database.Cursor;
import com.jingdong.common.entity.JDReminderNewEntity;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.jdroom.JDDataBase;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class JDReminderNewTable {
    private static final String TAG = "HHH_JD_ReminderNewTable";

    public static int checkReminder(String str, String str2, long j2) {
        Cursor cursor = null;
        try {
            try {
                Cursor o = JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().o(str, str2, j2);
                if (o == null) {
                    if (o != null && !o.isClosed()) {
                        o.close();
                    }
                    return -1;
                } else if (o.moveToFirst()) {
                    int i2 = o.getInt(o.getColumnIndex("_id"));
                    if (o != null && !o.isClosed()) {
                        o.close();
                    }
                    return i2;
                } else {
                    if (o != null && !o.isClosed()) {
                        o.close();
                    }
                    return -1;
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0 && !cursor.isClosed()) {
                    cursor.close();
                }
                return -1;
            }
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    public static synchronized void deleteReminder(String str, String str2, long j2) {
        synchronized (JDReminderNewTable.class) {
            try {
                JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().h(str, str2, j2);
                if (OKLog.D) {
                    OKLog.d(TAG, "deleteReminder, type: " + str + ", startTime: " + j2 + ", identificationId: " + str2);
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
    }

    public static void deleteRemindersBeforeTargetTime(long j2) {
        try {
            JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().d(j2);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x003c, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0051, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0053, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.JDReminderNewEntity> getAllRemindersAfterTargetTime(long r3) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            com.jingdong.jdsdk.JdSdk r2 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            android.content.Context r2 = r2.getApplicationContext()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            com.jingdong.sdk.jdroom.JDDataBase r2 = com.jingdong.sdk.jdroom.JDDataBase.a(r2)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            com.jingdong.sdk.jdroom.a.a r2 = r2.b()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            android.database.Cursor r1 = r2.i(r3)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r1 != 0) goto L28
            if (r1 == 0) goto L27
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L27
            r1.close()
        L27:
            return r0
        L28:
            boolean r3 = r1.moveToNext()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r3 == 0) goto L36
            com.jingdong.common.entity.JDReminderNewEntity r3 = getJDReminderNewEntityFromCursor(r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r0.add(r3)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            goto L28
        L36:
            if (r1 == 0) goto L56
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L56
            goto L53
        L3f:
            r3 = move-exception
            goto L57
        L41:
            r3 = move-exception
            boolean r4 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L3f
            if (r4 == 0) goto L4b
            java.lang.String r4 = "HHH_JD_ReminderNewTable"
            com.jingdong.sdk.oklog.OKLog.e(r4, r3)     // Catch: java.lang.Throwable -> L3f
        L4b:
            if (r1 == 0) goto L56
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L56
        L53:
            r1.close()
        L56:
            return r0
        L57:
            if (r1 == 0) goto L62
            boolean r4 = r1.isClosed()
            if (r4 != 0) goto L62
            r1.close()
        L62:
            goto L64
        L63:
            throw r3
        L64:
            goto L63
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderNewTable.getAllRemindersAfterTargetTime(long):java.util.ArrayList");
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x003c, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0051, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0053, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.JDReminderNewEntity> getAllRemindersAtNotificationTime(long r3) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            com.jingdong.jdsdk.JdSdk r2 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            android.content.Context r2 = r2.getApplicationContext()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            com.jingdong.sdk.jdroom.JDDataBase r2 = com.jingdong.sdk.jdroom.JDDataBase.a(r2)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            com.jingdong.sdk.jdroom.a.a r2 = r2.b()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            android.database.Cursor r1 = r2.g(r3)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r1 != 0) goto L28
            if (r1 == 0) goto L27
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L27
            r1.close()
        L27:
            return r0
        L28:
            boolean r3 = r1.moveToNext()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r3 == 0) goto L36
            com.jingdong.common.entity.JDReminderNewEntity r3 = getJDReminderNewEntityFromCursor(r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r0.add(r3)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            goto L28
        L36:
            if (r1 == 0) goto L56
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L56
            goto L53
        L3f:
            r3 = move-exception
            goto L57
        L41:
            r3 = move-exception
            boolean r4 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L3f
            if (r4 == 0) goto L4b
            java.lang.String r4 = "HHH_JD_ReminderNewTable"
            com.jingdong.sdk.oklog.OKLog.e(r4, r3)     // Catch: java.lang.Throwable -> L3f
        L4b:
            if (r1 == 0) goto L56
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L56
        L53:
            r1.close()
        L56:
            return r0
        L57:
            if (r1 == 0) goto L62
            boolean r4 = r1.isClosed()
            if (r4 != 0) goto L62
            r1.close()
        L62:
            goto L64
        L63:
            throw r3
        L64:
            goto L63
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderNewTable.getAllRemindersAtNotificationTime(long):java.util.ArrayList");
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x003c, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0051, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0053, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.JDReminderNewEntity> getAllRemindersBasedOnTypeAndTime(java.lang.String r3, long r4) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            com.jingdong.jdsdk.JdSdk r2 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            android.content.Context r2 = r2.getApplicationContext()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            com.jingdong.sdk.jdroom.JDDataBase r2 = com.jingdong.sdk.jdroom.JDDataBase.a(r2)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            com.jingdong.sdk.jdroom.a.a r2 = r2.b()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            android.database.Cursor r1 = r2.j(r3, r4)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r1 != 0) goto L28
            if (r1 == 0) goto L27
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L27
            r1.close()
        L27:
            return r0
        L28:
            boolean r3 = r1.moveToNext()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r3 == 0) goto L36
            com.jingdong.common.entity.JDReminderNewEntity r3 = getJDReminderNewEntityFromCursor(r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r0.add(r3)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            goto L28
        L36:
            if (r1 == 0) goto L56
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L56
            goto L53
        L3f:
            r3 = move-exception
            goto L57
        L41:
            r3 = move-exception
            boolean r4 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L3f
            if (r4 == 0) goto L4b
            java.lang.String r4 = "HHH_JD_ReminderNewTable"
            com.jingdong.sdk.oklog.OKLog.e(r4, r3)     // Catch: java.lang.Throwable -> L3f
        L4b:
            if (r1 == 0) goto L56
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L56
        L53:
            r1.close()
        L56:
            return r0
        L57:
            if (r1 == 0) goto L62
            boolean r4 = r1.isClosed()
            if (r4 != 0) goto L62
            r1.close()
        L62:
            goto L64
        L63:
            throw r3
        L64:
            goto L63
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderNewTable.getAllRemindersBasedOnTypeAndTime(java.lang.String, long):java.util.ArrayList");
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x003f, code lost:
        if (r1.isClosed() == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0054, code lost:
        if (r1.isClosed() == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0056, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.JDReminderNewEntity> getAllRemindersBasedOnTypeDuringTimePeriod(java.lang.String r9, long r10, long r12) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            com.jingdong.jdsdk.JdSdk r2 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            android.content.Context r2 = r2.getApplicationContext()     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            com.jingdong.sdk.jdroom.JDDataBase r2 = com.jingdong.sdk.jdroom.JDDataBase.a(r2)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            com.jingdong.sdk.jdroom.a.a r3 = r2.b()     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            r4 = r9
            r5 = r10
            r7 = r12
            android.database.Cursor r1 = r3.c(r4, r5, r7)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            if (r1 != 0) goto L2b
            if (r1 == 0) goto L2a
            boolean r9 = r1.isClosed()
            if (r9 != 0) goto L2a
            r1.close()
        L2a:
            return r0
        L2b:
            boolean r9 = r1.moveToNext()     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            if (r9 == 0) goto L39
            com.jingdong.common.entity.JDReminderNewEntity r9 = getJDReminderNewEntityFromCursor(r1)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            r0.add(r9)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            goto L2b
        L39:
            if (r1 == 0) goto L59
            boolean r9 = r1.isClosed()
            if (r9 != 0) goto L59
            goto L56
        L42:
            r9 = move-exception
            goto L5a
        L44:
            r9 = move-exception
            boolean r10 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L42
            if (r10 == 0) goto L4e
            java.lang.String r10 = "HHH_JD_ReminderNewTable"
            com.jingdong.sdk.oklog.OKLog.e(r10, r9)     // Catch: java.lang.Throwable -> L42
        L4e:
            if (r1 == 0) goto L59
            boolean r9 = r1.isClosed()
            if (r9 != 0) goto L59
        L56:
            r1.close()
        L59:
            return r0
        L5a:
            if (r1 == 0) goto L65
            boolean r10 = r1.isClosed()
            if (r10 != 0) goto L65
            r1.close()
        L65:
            goto L67
        L66:
            throw r9
        L67:
            goto L66
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderNewTable.getAllRemindersBasedOnTypeDuringTimePeriod(java.lang.String, long, long):java.util.ArrayList");
    }

    private static JDReminderNewEntity getJDReminderNewEntityFromCursor(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        String string = cursor.getString(cursor.getColumnIndex("businessType"));
        String string2 = cursor.getString(cursor.getColumnIndex("reminderShowTag"));
        String string3 = cursor.getString(cursor.getColumnIndex("identificationId"));
        String string4 = cursor.getString(cursor.getColumnIndex("reminderTitle"));
        String string5 = cursor.getString(cursor.getColumnIndex("reminderImgUrl"));
        long j2 = cursor.getLong(cursor.getColumnIndex("startTimeMillis"));
        long j3 = cursor.getLong(cursor.getColumnIndex("notificationTimeMillis"));
        long j4 = cursor.getLong(cursor.getColumnIndex("insertTime"));
        String string6 = cursor.getString(cursor.getColumnIndex("jump"));
        String string7 = cursor.getString(cursor.getColumnIndex("extra"));
        return new JDReminderNewEntity.ReminderBuilder(string, string2, string3, string4, j2, string6).notificationTimeMillis(j3).reminderImgUrl(string5).extra(string7).more(cursor.getString(cursor.getColumnIndex("more"))).insertTime(j4).build();
    }

    public static synchronized int getLastRequestCode() {
        synchronized (JDReminderNewTable.class) {
            Cursor cursor = null;
            try {
                Cursor k2 = JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().k();
                if (k2 == null) {
                    if (k2 != null && !k2.isClosed()) {
                        k2.close();
                    }
                    return -1;
                } else if (k2.moveToFirst()) {
                    int i2 = k2.getInt(k2.getColumnIndex("requestCode"));
                    if (k2 != null && !k2.isClosed()) {
                        k2.close();
                    }
                    return i2;
                } else {
                    if (k2 != null && !k2.isClosed()) {
                        k2.close();
                    }
                    return -1;
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                    OKLog.e(TAG, "getLastRequestCode, exception: " + e2.toString());
                }
                if (0 != 0 && !cursor.isClosed()) {
                    cursor.close();
                }
                return -1;
            }
        }
    }

    public static long getNotificationTimeMillis(String str, String str2, long j2) {
        Cursor cursor = null;
        try {
            try {
                Cursor o = JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().o(str, str2, j2);
                if (o == null) {
                    if (o != null && !o.isClosed()) {
                        o.close();
                    }
                    return -1L;
                } else if (o.moveToFirst()) {
                    long j3 = o.getLong(o.getColumnIndex("notificationTimeMillis"));
                    if (o != null && !o.isClosed()) {
                        o.close();
                    }
                    return j3;
                } else {
                    if (o != null && !o.isClosed()) {
                        o.close();
                    }
                    return -1L;
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0 && !cursor.isClosed()) {
                    cursor.close();
                }
                return -1L;
            }
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0046, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x005b, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x005d, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Set<java.lang.Long> getReminderDaysDuringTimePeriod(long r3, long r5) {
        /*
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r1 = 0
            com.jingdong.jdsdk.JdSdk r2 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            android.content.Context r2 = r2.getApplicationContext()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            com.jingdong.sdk.jdroom.JDDataBase r2 = com.jingdong.sdk.jdroom.JDDataBase.a(r2)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            com.jingdong.sdk.jdroom.a.a r2 = r2.b()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            android.database.Cursor r1 = r2.e(r3, r5)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            if (r1 != 0) goto L28
            if (r1 == 0) goto L27
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L27
            r1.close()
        L27:
            return r0
        L28:
            boolean r3 = r1.moveToNext()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            if (r3 == 0) goto L40
            java.lang.String r3 = "startTimeMillis"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            long r3 = r1.getLong(r3)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            r0.add(r3)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            goto L28
        L40:
            if (r1 == 0) goto L60
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L60
            goto L5d
        L49:
            r3 = move-exception
            goto L61
        L4b:
            r3 = move-exception
            boolean r4 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L49
            if (r4 == 0) goto L55
            java.lang.String r4 = "HHH_JD_ReminderNewTable"
            com.jingdong.sdk.oklog.OKLog.e(r4, r3)     // Catch: java.lang.Throwable -> L49
        L55:
            if (r1 == 0) goto L60
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L60
        L5d:
            r1.close()
        L60:
            return r0
        L61:
            if (r1 == 0) goto L6c
            boolean r4 = r1.isClosed()
            if (r4 != 0) goto L6c
            r1.close()
        L6c:
            goto L6e
        L6d:
            throw r3
        L6e:
            goto L6d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderNewTable.getReminderDaysDuringTimePeriod(long, long):java.util.Set");
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x003c, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0051, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0053, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.JDReminderNewEntity> getRemindersDuringTimePeriod(long r3, long r5) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            com.jingdong.jdsdk.JdSdk r2 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            android.content.Context r2 = r2.getApplicationContext()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            com.jingdong.sdk.jdroom.JDDataBase r2 = com.jingdong.sdk.jdroom.JDDataBase.a(r2)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            com.jingdong.sdk.jdroom.a.a r2 = r2.b()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            android.database.Cursor r1 = r2.q(r3, r5)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r1 != 0) goto L28
            if (r1 == 0) goto L27
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L27
            r1.close()
        L27:
            return r0
        L28:
            boolean r3 = r1.moveToNext()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r3 == 0) goto L36
            com.jingdong.common.entity.JDReminderNewEntity r3 = getJDReminderNewEntityFromCursor(r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r0.add(r3)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            goto L28
        L36:
            if (r1 == 0) goto L56
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L56
            goto L53
        L3f:
            r3 = move-exception
            goto L57
        L41:
            r3 = move-exception
            boolean r4 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L3f
            if (r4 == 0) goto L4b
            java.lang.String r4 = "HHH_JD_ReminderNewTable"
            com.jingdong.sdk.oklog.OKLog.e(r4, r3)     // Catch: java.lang.Throwable -> L3f
        L4b:
            if (r1 == 0) goto L56
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L56
        L53:
            r1.close()
        L56:
            return r0
        L57:
            if (r1 == 0) goto L62
            boolean r4 = r1.isClosed()
            if (r4 != 0) goto L62
            r1.close()
        L62:
            goto L64
        L63:
            throw r3
        L64:
            goto L63
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderNewTable.getRemindersDuringTimePeriod(long, long):java.util.ArrayList");
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0054, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0069, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x006b, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.collection.ArrayMap<java.lang.Long, java.lang.Integer> getRemindersKeyIdAndRequestCodeByNotificationTime(long r3) {
        /*
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            r1 = 0
            com.jingdong.jdsdk.JdSdk r2 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            android.content.Context r2 = r2.getApplicationContext()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            com.jingdong.sdk.jdroom.JDDataBase r2 = com.jingdong.sdk.jdroom.JDDataBase.a(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            com.jingdong.sdk.jdroom.a.a r2 = r2.b()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            android.database.Cursor r1 = r2.a(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            if (r1 != 0) goto L28
            if (r1 == 0) goto L27
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L27
            r1.close()
        L27:
            return r0
        L28:
            boolean r3 = r1.moveToNext()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            if (r3 == 0) goto L4e
            java.lang.String r3 = "_id"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            long r3 = r1.getLong(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.String r2 = "requestCode"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            int r2 = r1.getInt(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            r0.put(r3, r4)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            goto L28
        L4e:
            if (r1 == 0) goto L6e
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L6e
            goto L6b
        L57:
            r3 = move-exception
            goto L6f
        L59:
            r3 = move-exception
            boolean r4 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L57
            if (r4 == 0) goto L63
            java.lang.String r4 = "HHH_JD_ReminderNewTable"
            com.jingdong.sdk.oklog.OKLog.e(r4, r3)     // Catch: java.lang.Throwable -> L57
        L63:
            if (r1 == 0) goto L6e
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L6e
        L6b:
            r1.close()
        L6e:
            return r0
        L6f:
            if (r1 == 0) goto L7a
            boolean r4 = r1.isClosed()
            if (r4 != 0) goto L7a
            r1.close()
        L7a:
            goto L7c
        L7b:
            throw r3
        L7c:
            goto L7b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderNewTable.getRemindersKeyIdAndRequestCodeByNotificationTime(long):androidx.collection.ArrayMap");
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0054, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0069, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x006b, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.collection.ArrayMap<java.lang.Long, java.lang.Integer> getRemindersNotificationTimeAndRequestCodeAfterTime(long r3) {
        /*
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            r1 = 0
            com.jingdong.jdsdk.JdSdk r2 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            android.content.Context r2 = r2.getApplicationContext()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            com.jingdong.sdk.jdroom.JDDataBase r2 = com.jingdong.sdk.jdroom.JDDataBase.a(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            com.jingdong.sdk.jdroom.a.a r2 = r2.b()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            android.database.Cursor r1 = r2.n(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            if (r1 != 0) goto L28
            if (r1 == 0) goto L27
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L27
            r1.close()
        L27:
            return r0
        L28:
            boolean r3 = r1.moveToNext()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            if (r3 == 0) goto L4e
            java.lang.String r3 = "notificationTimeMillis"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            long r3 = r1.getLong(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.String r2 = "requestCode"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            int r2 = r1.getInt(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            r0.put(r3, r4)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            goto L28
        L4e:
            if (r1 == 0) goto L6e
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L6e
            goto L6b
        L57:
            r3 = move-exception
            goto L6f
        L59:
            r3 = move-exception
            boolean r4 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L57
            if (r4 == 0) goto L63
            java.lang.String r4 = "HHH_JD_ReminderNewTable"
            com.jingdong.sdk.oklog.OKLog.e(r4, r3)     // Catch: java.lang.Throwable -> L57
        L63:
            if (r1 == 0) goto L6e
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L6e
        L6b:
            r1.close()
        L6e:
            return r0
        L6f:
            if (r1 == 0) goto L7a
            boolean r4 = r1.isClosed()
            if (r4 != 0) goto L7a
            r1.close()
        L7a:
            goto L7c
        L7b:
            throw r3
        L7c:
            goto L7b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderNewTable.getRemindersNotificationTimeAndRequestCodeAfterTime(long):androidx.collection.ArrayMap");
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0054, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0069, code lost:
        if (r1.isClosed() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x006b, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.collection.ArrayMap<java.lang.Long, java.lang.Integer> getRemindersStartTimeAndRequestCodeAfterTime(long r3) {
        /*
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            r1 = 0
            com.jingdong.jdsdk.JdSdk r2 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            android.content.Context r2 = r2.getApplicationContext()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            com.jingdong.sdk.jdroom.JDDataBase r2 = com.jingdong.sdk.jdroom.JDDataBase.a(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            com.jingdong.sdk.jdroom.a.a r2 = r2.b()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            android.database.Cursor r1 = r2.m(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            if (r1 != 0) goto L28
            if (r1 == 0) goto L27
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L27
            r1.close()
        L27:
            return r0
        L28:
            boolean r3 = r1.moveToNext()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            if (r3 == 0) goto L4e
            java.lang.String r3 = "startTimeMillis"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            long r3 = r1.getLong(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.String r2 = "requestCode"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            int r2 = r1.getInt(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            r0.put(r3, r4)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            goto L28
        L4e:
            if (r1 == 0) goto L6e
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L6e
            goto L6b
        L57:
            r3 = move-exception
            goto L6f
        L59:
            r3 = move-exception
            boolean r4 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L57
            if (r4 == 0) goto L63
            java.lang.String r4 = "HHH_JD_ReminderNewTable"
            com.jingdong.sdk.oklog.OKLog.e(r4, r3)     // Catch: java.lang.Throwable -> L57
        L63:
            if (r1 == 0) goto L6e
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L6e
        L6b:
            r1.close()
        L6e:
            return r0
        L6f:
            if (r1 == 0) goto L7a
            boolean r4 = r1.isClosed()
            if (r4 != 0) goto L7a
            r1.close()
        L7a:
            goto L7c
        L7b:
            throw r3
        L7c:
            goto L7b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderNewTable.getRemindersStartTimeAndRequestCodeAfterTime(long):androidx.collection.ArrayMap");
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x009d A[Catch: Exception -> 0x00be, all -> 0x0142, TRY_LEAVE, TryCatch #1 {Exception -> 0x00be, blocks: (B:77:0x004f, B:79:0x0056, B:81:0x0063, B:83:0x009d), top: B:124:0x004f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized long insertOrUpdate(java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, long r27, long r29, long r31, java.lang.String r33, java.lang.String r34, java.lang.String r35, int r36) {
        /*
            Method dump skipped, instructions count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.JDReminderNewTable.insertOrUpdate(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, long, long, long, java.lang.String, java.lang.String, java.lang.String, int):long");
    }
}
