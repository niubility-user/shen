package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.Product;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.jdsdk.utils.FormatUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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
    */
    public static Map<Long, Product> getBrowserHistoryByPage(int i2, int i3) {
        int i4;
        if (OKLog.D) {
            OKLog.d(TAG, "db startIndex:" + i2 + ",perlMax:" + i3);
        }
        Cursor cursor = null;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT id,productCode,browseTime FROM BrowseHistoryTable ORDER BY browseTime desc LIMIT ?,? ", new String[]{String.valueOf(i2), String.valueOf(i3)});
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
                for (i4 = 0; i4 < count; i4++) {
                    cursor.moveToPosition(i4);
                    Product product = new Product();
                    long j2 = cursor.getLong(cursor.getColumnIndex("productCode"));
                    product.setId(Long.valueOf(j2));
                    product.setBrowserTime(FormatUtils.parseDate(cursor.getString(cursor.getColumnIndex("browseTime"))).getTime());
                    linkedHashMap.put(Long.valueOf(j2), product);
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
    */
    public static ArrayList<Product> getHistoryByPage(int i2, int i3) {
        int i4;
        ArrayList<Product> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                String[] strArr = new String[2];
                strArr[0] = String.valueOf(i2 == 1 ? 0 : (i2 - 1) * i3);
                strArr[1] = String.valueOf(i3);
                cursor = database.rawQuery("SELECT id,productCode FROM BrowseHistoryTable ORDER BY browseTime desc LIMIT ?,? ", strArr);
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
                for (i4 = 0; i4 < count; i4++) {
                    cursor.moveToPosition(i4);
                    Product product = new Product();
                    product.setId(Long.valueOf(cursor.getLong(cursor.getColumnIndex("productCode"))));
                    arrayList.add(product);
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
    */
    public static ArrayList<Product> getHistoryByPage(int i2, int i3, int i4) {
        int i5;
        ArrayList<Product> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                String[] strArr = new String[2];
                strArr[0] = String.valueOf((i2 == 1 ? 0 : (i2 - 1) * i3) - i4);
                strArr[1] = String.valueOf(i3);
                cursor = database.rawQuery("SELECT id,productCode FROM BrowseHistoryTable ORDER BY browseTime desc LIMIT ?,? ", strArr);
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
                for (i5 = 0; i5 < count; i5++) {
                    cursor.moveToPosition(i5);
                    Product product = new Product();
                    product.setId(Long.valueOf(cursor.getLong(cursor.getColumnIndex("productCode"))));
                    arrayList.add(product);
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
}
