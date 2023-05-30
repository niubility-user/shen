package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.Product;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

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
    */
    public static ArrayList<Long> getAllList() {
        ArrayList<Long> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT * FROM MS_AlarmTable", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("MS_AlarmTable", e2);
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
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT id,productCode FROM MS_AlarmTable", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("MS_AlarmTable", e2);
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
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT * FROM MS_AlarmTable WHERE millis <= " + (l2.longValue() + 60000) + " AND millis >=" + (l2.longValue() - 60000), null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("MS_AlarmTable", e2);
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

    /* JADX WARN: Removed duplicated region for block: B:42:0x00cc A[Catch: all -> 0x00e6, TRY_LEAVE, TryCatch #4 {all -> 0x00e6, blocks: (B:40:0x00c8, B:42:0x00cc), top: B:69:0x00c8 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00de A[Catch: all -> 0x00f2, TryCatch #1 {all -> 0x00f2, blocks: (B:21:0x00a1, B:23:0x00a7, B:25:0x00ac, B:26:0x00af, B:53:0x00e8, B:55:0x00ee, B:59:0x00f6, B:60:0x00f9, B:44:0x00d3, B:46:0x00d9, B:48:0x00de, B:49:0x00e1), top: B:68:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00e8 A[Catch: all -> 0x00f2, TRY_ENTER, TryCatch #1 {all -> 0x00f2, blocks: (B:21:0x00a1, B:23:0x00a7, B:25:0x00ac, B:26:0x00af, B:53:0x00e8, B:55:0x00ee, B:59:0x00f6, B:60:0x00f9, B:44:0x00d3, B:46:0x00d9, B:48:0x00de, B:49:0x00e1), top: B:68:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00f6 A[Catch: all -> 0x00f2, TryCatch #1 {all -> 0x00f2, blocks: (B:21:0x00a1, B:23:0x00a7, B:25:0x00ac, B:26:0x00af, B:53:0x00e8, B:55:0x00ee, B:59:0x00f6, B:60:0x00f9, B:44:0x00d3, B:46:0x00d9, B:48:0x00de, B:49:0x00e1), top: B:68:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized int insertBatch(List<Product> list, long j2, long j3) {
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        int i2;
        synchronized (MS_AlarmTable.class) {
            char c2 = 0;
            try {
                try {
                    sQLiteDatabase = DBHelperUtil.getDatabase();
                    try {
                        sQLiteDatabase.beginTransaction();
                        int i3 = 0;
                        Cursor cursor2 = null;
                        int i4 = 0;
                        while (i3 < list.size()) {
                            try {
                                ContentValues contentValues = new ContentValues();
                                Product product = list.get(i3);
                                String[] strArr = new String[1];
                                strArr[c2] = product.getId() + "";
                                cursor2 = sQLiteDatabase.query("MS_AlarmTable", null, "productCode =?", strArr, null, null, null);
                                if (cursor2 != null && cursor2.getCount() != 0) {
                                    sQLiteDatabase.delete("MS_AlarmTable", "productCode =?", strArr);
                                }
                                contentValues.put("productCode", product.getId());
                                contentValues.put("millis", Long.valueOf(j2));
                                contentValues.put("insertTime", Long.valueOf(j3));
                                contentValues.put("productName", product.getName());
                                if (sQLiteDatabase.insert("MS_AlarmTable", null, contentValues) != -1) {
                                    i4++;
                                }
                                i3++;
                                c2 = 0;
                            } catch (Exception e2) {
                                e = e2;
                                cursor = cursor2;
                                i2 = i4;
                                try {
                                    if (OKLog.E) {
                                    }
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    if (sQLiteDatabase != null) {
                                    }
                                    DBHelperUtil.closeDatabase();
                                    return i2;
                                } catch (Throwable unused) {
                                    if (cursor != null && !cursor.isClosed()) {
                                        cursor.close();
                                    }
                                    if (sQLiteDatabase != null) {
                                        sQLiteDatabase.endTransaction();
                                    }
                                    DBHelperUtil.closeDatabase();
                                    return i2;
                                }
                            } catch (Throwable unused2) {
                                cursor = cursor2;
                                i2 = i4;
                                if (cursor != null) {
                                }
                                if (sQLiteDatabase != null) {
                                }
                                DBHelperUtil.closeDatabase();
                                return i2;
                            }
                        }
                        sQLiteDatabase.setTransactionSuccessful();
                        if (cursor2 != null && !cursor2.isClosed()) {
                            cursor2.close();
                        }
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.endTransaction();
                        }
                        DBHelperUtil.closeDatabase();
                        return i4;
                    } catch (Exception e3) {
                        e = e3;
                        cursor = null;
                        i2 = 0;
                        if (OKLog.E) {
                            OKLog.e("MS_AlarmTable", e);
                        }
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.endTransaction();
                        }
                        DBHelperUtil.closeDatabase();
                        return i2;
                    } catch (Throwable unused3) {
                        cursor = null;
                        i2 = 0;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                        }
                        DBHelperUtil.closeDatabase();
                        return i2;
                    }
                } catch (Exception e4) {
                    e = e4;
                    cursor = null;
                    sQLiteDatabase = null;
                } catch (Throwable unused4) {
                    cursor = null;
                    sQLiteDatabase = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
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
    */
    public static boolean isExists(long j2) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT id,productCode FROM MS_AlarmTable WHERE productCode = " + j2, null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("MS_AlarmTable", e2);
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
