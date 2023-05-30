package com.jingdong.common.database.table;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import com.jingdong.common.entity.JDReminderNewEntity;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.jdroom.JDDataBase;
import com.jingdong.sdk.jdroom.a.a;
import com.jingdong.sdk.jdroom.a.c;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    /* JADX WARN: Code restructure failed: missing block: B:106:0x0051, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0053, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x003c, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<JDReminderNewEntity> getAllRemindersAfterTargetTime(long j2) {
        ArrayList<JDReminderNewEntity> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().i(j2);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return arrayList;
            }
            while (cursor.moveToNext()) {
                arrayList.add(getJDReminderNewEntityFromCursor(cursor));
            }
            if (cursor != null) {
            }
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x0051, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0053, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x003c, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<JDReminderNewEntity> getAllRemindersAtNotificationTime(long j2) {
        ArrayList<JDReminderNewEntity> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().g(j2);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return arrayList;
            }
            while (cursor.moveToNext()) {
                arrayList.add(getJDReminderNewEntityFromCursor(cursor));
            }
            if (cursor != null) {
            }
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x0051, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0053, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x003c, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<JDReminderNewEntity> getAllRemindersBasedOnTypeAndTime(String str, long j2) {
        ArrayList<JDReminderNewEntity> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().j(str, j2);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return arrayList;
            }
            while (cursor.moveToNext()) {
                arrayList.add(getJDReminderNewEntityFromCursor(cursor));
            }
            if (cursor != null) {
            }
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x0054, code lost:
        if (r1.isClosed() == false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0056, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x003f, code lost:
        if (r1.isClosed() == false) goto L105;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<JDReminderNewEntity> getAllRemindersBasedOnTypeDuringTimePeriod(String str, long j2, long j3) {
        ArrayList<JDReminderNewEntity> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().c(str, j2, j3);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return arrayList;
            }
            while (cursor.moveToNext()) {
                arrayList.add(getJDReminderNewEntityFromCursor(cursor));
            }
            if (cursor != null) {
            }
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
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

    /* JADX WARN: Code restructure failed: missing block: B:106:0x005b, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x005d, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0046, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Set<Long> getReminderDaysDuringTimePeriod(long j2, long j3) {
        HashSet hashSet = new HashSet();
        Cursor cursor = null;
        try {
            try {
                cursor = JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().e(j2, j3);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return hashSet;
            }
            while (cursor.moveToNext()) {
                hashSet.add(Long.valueOf(cursor.getLong(cursor.getColumnIndex("startTimeMillis"))));
            }
            if (cursor != null) {
            }
            return hashSet;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x0051, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0053, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x003c, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<JDReminderNewEntity> getRemindersDuringTimePeriod(long j2, long j3) {
        ArrayList<JDReminderNewEntity> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().q(j2, j3);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return arrayList;
            }
            while (cursor.moveToNext()) {
                arrayList.add(getJDReminderNewEntityFromCursor(cursor));
            }
            if (cursor != null) {
            }
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x0069, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x006b, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0054, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayMap<Long, Integer> getRemindersKeyIdAndRequestCodeByNotificationTime(long j2) {
        ArrayMap<Long, Integer> arrayMap = new ArrayMap<>();
        Cursor cursor = null;
        try {
            try {
                cursor = JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().a(j2);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return arrayMap;
            }
            while (cursor.moveToNext()) {
                arrayMap.put(Long.valueOf(cursor.getLong(cursor.getColumnIndex("_id"))), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("requestCode"))));
            }
            if (cursor != null) {
            }
            return arrayMap;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x0069, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x006b, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0054, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayMap<Long, Integer> getRemindersNotificationTimeAndRequestCodeAfterTime(long j2) {
        ArrayMap<Long, Integer> arrayMap = new ArrayMap<>();
        Cursor cursor = null;
        try {
            try {
                cursor = JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().n(j2);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return arrayMap;
            }
            while (cursor.moveToNext()) {
                arrayMap.put(Long.valueOf(cursor.getLong(cursor.getColumnIndex("notificationTimeMillis"))), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("requestCode"))));
            }
            if (cursor != null) {
            }
            return arrayMap;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x0069, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x006b, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0054, code lost:
        if (r1.isClosed() == false) goto L107;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayMap<Long, Integer> getRemindersStartTimeAndRequestCodeAfterTime(long j2) {
        ArrayMap<Long, Integer> arrayMap = new ArrayMap<>();
        Cursor cursor = null;
        try {
            try {
                cursor = JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b().m(j2);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return arrayMap;
            }
            while (cursor.moveToNext()) {
                arrayMap.put(Long.valueOf(cursor.getLong(cursor.getColumnIndex("startTimeMillis"))), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("requestCode"))));
            }
            if (cursor != null) {
            }
            return arrayMap;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:148:0x009d A[Catch: Exception -> 0x00be, all -> 0x0142, TRY_LEAVE, TryCatch #1 {Exception -> 0x00be, blocks: (B:142:0x004f, B:144:0x0056, B:146:0x0063, B:148:0x009d), top: B:189:0x004f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized long insertOrUpdate(String str, String str2, String str3, String str4, String str5, long j2, long j3, long j4, String str6, String str7, String str8, int i2) {
        String str9;
        Object obj;
        a aVar;
        String str10;
        long f2;
        synchronized (JDReminderNewTable.class) {
            Cursor cursor = null;
            try {
                a b = JDDataBase.a(JdSdk.getInstance().getApplicationContext()).b();
                double d = j2;
                double d2 = j3;
                obj = JDReminderNewTable.class;
                try {
                    try {
                        cursor = b.p(str, str2, str3, str4, str5, d, d2, str6, str7, str8);
                        if (cursor != null) {
                            if (cursor.getCount() != 0) {
                                str9 = str;
                                aVar = b;
                                str10 = str3;
                                try {
                                    aVar.h(str9, str10, j2);
                                    if (OKLog.D) {
                                        OKLog.d(TAG, "insertOrUpdate, delete \u5df2\u5b58\u5728\u7684\u76f8\u540c\u63d0\u9192");
                                    }
                                    c cVar = new c();
                                    cVar.b = str9;
                                    cVar.f14966c = str2;
                                    cVar.d = str10;
                                    cVar.f14967e = str4;
                                    cVar.f14968f = str5;
                                    cVar.f14969g = d;
                                    cVar.f14970h = d2;
                                    cVar.f14971i = j4;
                                    cVar.f14972j = str6;
                                    cVar.f14973k = str7;
                                    cVar.f14974l = str8;
                                    cVar.f14975m = i2;
                                    f2 = aVar.f(cVar);
                                    if (f2 == -1) {
                                        ExceptionReporter.reportNormalRemindErr(str9 + "_HHHInsertError");
                                    }
                                    if (cursor != null && !cursor.isClosed()) {
                                        cursor.close();
                                    }
                                    return f2;
                                } catch (Exception e2) {
                                    e = e2;
                                    if (OKLog.E) {
                                        OKLog.e(TAG, e);
                                        OKLog.e(TAG, "insertOrUpdate, exception: " + e.toString());
                                    }
                                    StringBuilder sb = new StringBuilder();
                                    for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                                        sb.append(stackTraceElement.toString() + "\\n");
                                    }
                                    ExceptionReporter.reportNormalRemindErr(str9 + CartConstant.KEY_YB_INFO_LINK + sb.toString());
                                    if (cursor != null && !cursor.isClosed()) {
                                        cursor.close();
                                    }
                                    return -1L;
                                }
                            }
                        }
                        str9 = str;
                        aVar = b;
                        str10 = str3;
                        c cVar2 = new c();
                        cVar2.b = str9;
                        cVar2.f14966c = str2;
                        cVar2.d = str10;
                        cVar2.f14967e = str4;
                        cVar2.f14968f = str5;
                        cVar2.f14969g = d;
                        cVar2.f14970h = d2;
                        cVar2.f14971i = j4;
                        cVar2.f14972j = str6;
                        cVar2.f14973k = str7;
                        cVar2.f14974l = str8;
                        cVar2.f14975m = i2;
                        f2 = aVar.f(cVar2);
                        if (f2 == -1) {
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        return f2;
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                    str9 = str;
                }
            } catch (Exception e4) {
                e = e4;
                str9 = str;
                obj = JDReminderNewTable.class;
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
    }
}
