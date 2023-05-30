package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class CouponAlarmTable implements IJdTable {
    private static final String TAG = "CouponAlarmTable";
    public static final String TB_ALARM_TABLE = "CouponAlarmTable";
    public static final String TB_CLOUMN_COUPON_ID = "couponId";

    public static void delAll() {
        try {
            try {
                DBHelperUtil.getDatabase().delete("CouponAlarmTable", "1=1", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("CouponAlarmTable", e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static void delById(long j2) {
        try {
            try {
                DBHelperUtil.getDatabase().delete("CouponAlarmTable", "couponId =?", new String[]{j2 + ""});
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("CouponAlarmTable", e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
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
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT id,couponId FROM CouponAlarmTable", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("CouponAlarmTable", e2);
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
                    arrayList.add(Long.valueOf(cursor.getLong(cursor.getColumnIndex("couponId"))));
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

    /* JADX WARN: Removed duplicated region for block: B:41:0x00af A[Catch: all -> 0x00c9, TRY_LEAVE, TryCatch #5 {all -> 0x00c9, blocks: (B:39:0x00ab, B:41:0x00af), top: B:69:0x00ab }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c1 A[Catch: all -> 0x00d5, TryCatch #1 {all -> 0x00d5, blocks: (B:21:0x0089, B:23:0x008f, B:25:0x0094, B:26:0x0097, B:52:0x00cb, B:54:0x00d1, B:58:0x00d9, B:59:0x00dc, B:43:0x00b6, B:45:0x00bc, B:47:0x00c1, B:48:0x00c4), top: B:68:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00cb A[Catch: all -> 0x00d5, TRY_ENTER, TryCatch #1 {all -> 0x00d5, blocks: (B:21:0x0089, B:23:0x008f, B:25:0x0094, B:26:0x0097, B:52:0x00cb, B:54:0x00d1, B:58:0x00d9, B:59:0x00dc, B:43:0x00b6, B:45:0x00bc, B:47:0x00c1, B:48:0x00c4), top: B:68:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00d9 A[Catch: all -> 0x00d5, TryCatch #1 {all -> 0x00d5, blocks: (B:21:0x0089, B:23:0x008f, B:25:0x0094, B:26:0x0097, B:52:0x00cb, B:54:0x00d1, B:58:0x00d9, B:59:0x00dc, B:43:0x00b6, B:45:0x00bc, B:47:0x00c1, B:48:0x00c4), top: B:68:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized int insertBatch(List<Long> list) {
        SQLiteDatabase sQLiteDatabase;
        int i2;
        synchronized (CouponAlarmTable.class) {
            char c2 = 0;
            Cursor cursor = null;
            try {
                try {
                    sQLiteDatabase = DBHelperUtil.getDatabase();
                    try {
                        sQLiteDatabase.beginTransaction();
                        Cursor cursor2 = null;
                        int i3 = 0;
                        int i4 = 0;
                        while (i3 < list.size()) {
                            try {
                                ContentValues contentValues = new ContentValues();
                                long longValue = list.get(i3).longValue();
                                String[] strArr = new String[1];
                                strArr[c2] = longValue + "";
                                cursor2 = sQLiteDatabase.query("CouponAlarmTable", null, "couponId =?", strArr, null, null, null);
                                if (cursor2 != null && cursor2.getCount() != 0) {
                                    sQLiteDatabase.delete("CouponAlarmTable", "couponId =?", strArr);
                                }
                                contentValues.put("couponId", Long.valueOf(longValue));
                                if (sQLiteDatabase.insert("CouponAlarmTable", null, contentValues) != -1) {
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
                                        OKLog.e("CouponAlarmTable", e);
                                    }
                                    if (cursor != null && !cursor.isClosed()) {
                                        cursor.close();
                                    }
                                    if (sQLiteDatabase != null) {
                                        sQLiteDatabase.endTransaction();
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
                                    cursor.close();
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
                        i2 = 0;
                        if (OKLog.E) {
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                        }
                        DBHelperUtil.closeDatabase();
                        return i2;
                    } catch (Throwable unused3) {
                        i2 = 0;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase != null) {
                        }
                        DBHelperUtil.closeDatabase();
                        return i2;
                    }
                } catch (Exception e4) {
                    e = e4;
                    sQLiteDatabase = null;
                } catch (Throwable unused4) {
                    sQLiteDatabase = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static synchronized void insertOrUpdate(long j2) {
        synchronized (CouponAlarmTable.class) {
            try {
                ArrayList<Long> list = getList();
                if (list != null && list.size() > 100) {
                    delAll();
                }
                Cursor cursor = null;
                try {
                    try {
                        SQLiteDatabase database = DBHelperUtil.getDatabase();
                        ContentValues contentValues = new ContentValues();
                        String[] strArr = {j2 + ""};
                        Cursor query = database.query("CouponAlarmTable", null, "couponId =?", strArr, null, null, null);
                        if (query != null) {
                            try {
                                if (query.getCount() != 0) {
                                    database.delete("CouponAlarmTable", "couponId =?", strArr);
                                }
                            } catch (Exception e2) {
                                e = e2;
                                cursor = query;
                                if (OKLog.E) {
                                    OKLog.e("CouponAlarmTable", e);
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
                        contentValues.put("couponId", Long.valueOf(j2));
                        database.insert("CouponAlarmTable", null, contentValues);
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
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT id,couponId FROM CouponAlarmTable WHERE couponId = " + j2, null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("CouponAlarmTable", e2);
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
                    arrayList.add(Long.valueOf(cursor.getLong(cursor.getColumnIndex("couponId"))));
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
        sQLiteDatabase.execSQL("CREATE TABLE CouponAlarmTable('id' INTEGER PRIMARY KEY  NOT NULL ,couponId LONG DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists CouponAlarmTable");
    }
}
