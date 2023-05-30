package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.collection.ArrayMap;
import com.jingdong.common.entity.JDReminder;
import com.jingdong.common.utils.JDReminderUtils;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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

    /* JADX WARN: Code restructure failed: missing block: B:117:0x0084, code lost:
        if (r2.isClosed() == false) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0099, code lost:
        if (r2.isClosed() == false) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x009b, code lost:
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Map<Long, Integer> getAllRemainReminders(long j2) {
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT * FROM JD_ReminderTable WHERE startTimeMillis >= " + (j2 - 300000) + " GROUP BY " + TB_CLOUMN_START_TIME, null);
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
                DBHelperUtil.closeDatabase();
                return arrayMap;
            }
            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                int count = cursor.getCount();
                for (int i2 = 0; i2 < count; i2++) {
                    cursor.moveToPosition(i2);
                    long j3 = cursor.getLong(cursor.getColumnIndex(TB_CLOUMN_START_TIME));
                    int i3 = cursor.getInt(cursor.getColumnIndex("requestCode"));
                    if (JDReminderUtils.getAlignedTime(j3) > 180000 + j2) {
                        arrayMap.put(Long.valueOf(j3), Integer.valueOf(i3));
                    }
                }
            }
            if (cursor != null) {
            }
            DBHelperUtil.closeDatabase();
            return arrayMap;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:105:0x006f, code lost:
        if (r1.isClosed() == false) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0084, code lost:
        if (r1.isClosed() == false) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0086, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<Long> getAllReminderIdBasedOnTypeAndTime(String str, long j2) {
        ArrayList<Long> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT * FROM JD_ReminderTable WHERE reminderType = '" + str + "' AND " + TB_CLOUMN_START_TIME + " >= " + j2, null);
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
                DBHelperUtil.closeDatabase();
                return arrayList;
            }
            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                int count = cursor.getCount();
                for (int i2 = 0; i2 < count; i2++) {
                    cursor.moveToPosition(i2);
                    arrayList.add(Long.valueOf(cursor.getLong(cursor.getColumnIndex(TB_CLOUMN_REMINDER_ID))));
                }
            }
            if (cursor != null) {
            }
            DBHelperUtil.closeDatabase();
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x00a6, code lost:
        if (r1.isClosed() == false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x00bb, code lost:
        if (r1.isClosed() == false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x00bd, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<JDReminder> getAllRemindersAfterTargetTime(long j2) {
        ArrayList<JDReminder> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery(("SELECT * FROM JD_ReminderTable WHERE startTimeMillis >= " + j2) + " ORDER BY startTimeMillis , insertTime ASC", null);
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
                DBHelperUtil.closeDatabase();
                return arrayList;
            }
            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                int count = cursor.getCount();
                for (int i2 = 0; i2 < count; i2++) {
                    cursor.moveToPosition(i2);
                    arrayList.add(new JDReminder(JDReminderUtils.Type.getType(cursor.getString(cursor.getColumnIndex(TB_CLOUMN_REMINDER_TYPE))), cursor.getLong(cursor.getColumnIndex(TB_CLOUMN_REMINDER_ID)), cursor.getString(cursor.getColumnIndex(TB_CLOUMN_REMINDER_TITLE)), cursor.getLong(cursor.getColumnIndex(TB_CLOUMN_START_TIME)), cursor.getLong(cursor.getColumnIndex("insertTime")), cursor.getString(cursor.getColumnIndex(TB_CLOUMN_TARGET_URL))));
                }
            }
            if (cursor != null) {
            }
            DBHelperUtil.closeDatabase();
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:105:0x007e, code lost:
        if (r2.isClosed() == false) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0093, code lost:
        if (r2.isClosed() == false) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0095, code lost:
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Set<Long> getReminderDaysDuringTimePeriod(long j2, long j3) {
        HashSet hashSet = new HashSet();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery(("SELECT * FROM JD_ReminderTable WHERE startTimeMillis >= " + j2 + " AND " + TB_CLOUMN_START_TIME + " < " + j3) + " ORDER BY startTimeMillis , insertTime ASC", null);
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
                DBHelperUtil.closeDatabase();
                return hashSet;
            }
            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                int count = cursor.getCount();
                for (int i2 = 0; i2 < count; i2++) {
                    cursor.moveToPosition(i2);
                    hashSet.add(Long.valueOf(cursor.getLong(cursor.getColumnIndex(TB_CLOUMN_START_TIME))));
                }
            }
            if (cursor != null) {
            }
            DBHelperUtil.closeDatabase();
            return hashSet;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:105:0x0083, code lost:
        if (r1.isClosed() == false) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0098, code lost:
        if (r1.isClosed() == false) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x009a, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static LinkedHashMap<Integer, Integer> getRemindersByStartTime(long j2) {
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT * FROM JD_ReminderTable WHERE startTimeMillis >= " + (j2 - 300000) + " AND " + TB_CLOUMN_START_TIME + " < " + (j2 + 300000), null);
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
                DBHelperUtil.closeDatabase();
                return linkedHashMap;
            }
            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                int count = cursor.getCount();
                for (int i2 = 0; i2 < count; i2++) {
                    cursor.moveToPosition(i2);
                    linkedHashMap.put(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("_id"))), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("requestCode"))));
                }
            }
            if (cursor != null) {
            }
            DBHelperUtil.closeDatabase();
            return linkedHashMap;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x00ba, code lost:
        if (r2.isClosed() == false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x00cf, code lost:
        if (r2.isClosed() == false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x00d1, code lost:
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<JDReminder> getRemindersDuringTimePeriod(long j2, long j3) {
        ArrayList<JDReminder> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery(("SELECT * FROM JD_ReminderTable WHERE startTimeMillis >= " + j2 + " AND " + TB_CLOUMN_START_TIME + " < " + j3) + " ORDER BY startTimeMillis , insertTime ASC", null);
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
                DBHelperUtil.closeDatabase();
                return arrayList;
            }
            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                int count = cursor.getCount();
                for (int i2 = 0; i2 < count; i2++) {
                    cursor.moveToPosition(i2);
                    arrayList.add(new JDReminder(JDReminderUtils.Type.getType(cursor.getString(cursor.getColumnIndex(TB_CLOUMN_REMINDER_TYPE))), cursor.getLong(cursor.getColumnIndex(TB_CLOUMN_REMINDER_ID)), cursor.getString(cursor.getColumnIndex(TB_CLOUMN_REMINDER_TITLE)), cursor.getLong(cursor.getColumnIndex(TB_CLOUMN_START_TIME)), cursor.getLong(cursor.getColumnIndex("insertTime")), cursor.getString(cursor.getColumnIndex(TB_CLOUMN_TARGET_URL))));
                }
            }
            if (cursor != null) {
            }
            DBHelperUtil.closeDatabase();
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:163:0x00ed, code lost:
        if (r4.isClosed() != false) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x00ef, code lost:
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized int insertOrUpdate(String str, long j2, String str2, long j3, long j4, long j5, int i2, String str3) {
        synchronized (JDReminderTable.class) {
            Cursor cursor = null;
            try {
                try {
                    SQLiteDatabase database = DBHelperUtil.getDatabase();
                    String[] strArr = {str, j2 + "", j3 + "", str3};
                    Cursor query = database.query(TB_REMINDER_TABLE, null, "reminderType=? AND reminderID=? AND startTimeMillis=? AND targetURL=?", strArr, null, null, null);
                    if (query != null) {
                        try {
                            if (query.getCount() != 0) {
                                database.delete(TB_REMINDER_TABLE, "reminderType=? AND reminderID=? AND startTimeMillis=? AND targetURL=?", strArr);
                                if (OKLog.D) {
                                    OKLog.d(TAG, "delete \u5df2\u5b58\u5728\u7684\u76f8\u540c\u63d0\u9192");
                                }
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
                            return -1;
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
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(TB_CLOUMN_REMINDER_TYPE, str);
                    contentValues.put(TB_CLOUMN_REMINDER_ID, Long.valueOf(j2));
                    contentValues.put(TB_CLOUMN_REMINDER_TITLE, str2);
                    contentValues.put(TB_CLOUMN_START_TIME, Long.valueOf(j3));
                    contentValues.put(TB_CLOUMN_REMAIN_TIME, Long.valueOf(j4));
                    contentValues.put("insertTime", Long.valueOf(j5));
                    contentValues.put("requestCode", Integer.valueOf(i2));
                    contentValues.put(TB_CLOUMN_TARGET_URL, str3);
                    database.insert(TB_REMINDER_TABLE, null, contentValues);
                    cursor = database.rawQuery("SELECT last_insert_rowid()", null);
                    if (cursor != null && cursor.getCount() != 0) {
                        cursor.moveToPosition(cursor.getCount() - 1);
                        int i3 = cursor.getInt(0);
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        DBHelperUtil.closeDatabase();
                        return i3;
                    }
                    DBHelperUtil.closeDatabase();
                    return -1;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
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
