package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

/* loaded from: classes5.dex */
public class MobileChannelAlarmTable implements IJdTable {
    private static final String TAG = "MobileChannelAlarmTable";
    public static final String TB_ALARM_TABLE = "MobileChannelAlarmTable";
    public static final String TB_CLOUMN_INSERT_TIME = "insertTime";
    public static final String TB_CLOUMN_MILLIS_TIME = "millis";
    public static final String TB_CLOUMN_PRODUCT_CODE = "productCode";
    public static final String TB_CLOUMN_PRODUCT_NAME = "productName";

    public static void delAll() {
        try {
            try {
                DBHelperUtil.getDatabase().delete("MobileChannelAlarmTable", "1=1", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("MobileChannelAlarmTable", e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static void delById(long j2) {
        try {
            try {
                DBHelperUtil.getDatabase().delete("MobileChannelAlarmTable", "productCode =?", new String[]{j2 + ""});
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("MobileChannelAlarmTable", e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
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
    */
    public static ArrayList<Long> getAllList() {
        ArrayList<Long> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT * FROM MobileChannelAlarmTable", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("MobileChannelAlarmTable", e2);
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
                    long j2 = cursor.getLong(cursor.getColumnIndex("millis"));
                    arrayList.add(Long.valueOf(j2));
                    if (OKLog.D) {
                        long j3 = cursor.getLong(cursor.getColumnIndex("productCode"));
                        long j4 = cursor.getLong(cursor.getColumnIndex("insertTime"));
                        String string = cursor.getString(cursor.getColumnIndex("productName"));
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        System.out.println("name:" + string);
                        System.out.println("id:" + j3 + ",millis:" + j2 + ",insertTime:" + j4);
                        PrintStream printStream = System.out;
                        StringBuilder sb = new StringBuilder();
                        sb.append("");
                        sb.append(simpleDateFormat.format(new Date(j2)));
                        printStream.println(sb.toString());
                    }
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
    */
    public static ArrayList<Long> getList() {
        ArrayList<Long> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT id,productCode FROM MobileChannelAlarmTable", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("MobileChannelAlarmTable", e2);
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
                    arrayList.add(Long.valueOf(cursor.getLong(cursor.getColumnIndex("productCode"))));
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
    */
    public static LinkedHashMap<Long, String> getMap(Long l2) {
        LinkedHashMap<Long, String> linkedHashMap = new LinkedHashMap<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT * FROM MobileChannelAlarmTable WHERE millis <= " + (l2.longValue() + 180000) + " AND millis >=" + (l2.longValue() - 180000), null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("MobileChannelAlarmTable", e2);
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
                    linkedHashMap.put(Long.valueOf(cursor.getLong(cursor.getColumnIndex("productCode"))), cursor.getString(cursor.getColumnIndex("productName")));
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

    public static synchronized void insertOrUpdate(long j2, long j3, long j4, String str) {
        synchronized (MobileChannelAlarmTable.class) {
            Cursor cursor = null;
            try {
                try {
                    SQLiteDatabase database = DBHelperUtil.getDatabase();
                    ContentValues contentValues = new ContentValues();
                    String[] strArr = {j2 + ""};
                    Cursor query = database.query("MobileChannelAlarmTable", null, "productCode =?", strArr, null, null, null);
                    if (query != null) {
                        try {
                            if (query.getCount() != 0) {
                                database.delete("MobileChannelAlarmTable", "productCode =?", strArr);
                            }
                        } catch (Exception e2) {
                            e = e2;
                            cursor = query;
                            if (OKLog.E) {
                                OKLog.e("MobileChannelAlarmTable", e);
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
                    database.insert("MobileChannelAlarmTable", null, contentValues);
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
    */
    public static boolean isExists(long j2) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT id,productCode FROM MobileChannelAlarmTable WHERE productCode = " + j2, null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("MobileChannelAlarmTable", e2);
                }
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
                return false;
            }
            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                int count = cursor.getCount();
                for (int i2 = 0; i2 < count; i2++) {
                    cursor.moveToPosition(i2);
                    arrayList.add(Long.valueOf(cursor.getLong(cursor.getColumnIndex("productCode"))));
                }
            }
            if (cursor != null) {
            }
            DBHelperUtil.closeDatabase();
            return arrayList.size() > 0;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE MobileChannelAlarmTable('id' INTEGER PRIMARY KEY  NOT NULL ,productCode LONG,productName TEXT,millis LONG,insertTime DATETIME DEFAULT CURRENT_TIMESTAMP)");
        if (OKLog.D) {
            System.out.println("create MS_Alarm Table");
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists MobileChannelAlarmTable");
    }
}
