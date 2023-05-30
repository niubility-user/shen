package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.BarcodeRecord;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class ScanCodeTable implements IJdTable {
    public static final String TABLE_NAME = "scan_code";
    private static final String TAG = "ScanCodeTable";
    public static final String TB_COLUMN_BARCODETYPE = "barcode_type";
    public static final String TB_COLUMN_CODE = "code";
    public static final String TB_COLUMN_CREATE_TIME = "create_time";
    public static final String TB_COLUMN_FORMAT = "format";
    public static final String TB_COLUMN_IMAGE_URL = "image_url";
    public static final String TB_COLUMN_PRODUCT_NAME = "product_name";
    public static final String TB_COLUMN_PRODUCT_PRICE = "product_price";
    public static final String TB_COLUMN_TYPE = "type";
    public static final String TB_COLUMN_WAREID = "wareid";

    public static void delAllBarcodeRecord(Context context) {
        try {
            try {
                DBHelperUtil.getDatabase().delete("scan_code", "1=1", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static void delBarcodeRecord(BarcodeRecord barcodeRecord) {
        try {
            try {
                DBHelperUtil.getDatabase().delete("scan_code", "code=?", new String[]{barcodeRecord.content});
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:76:0x01c7 A[Catch: all -> 0x01f5, TRY_LEAVE, TryCatch #9 {all -> 0x01f5, blocks: (B:74:0x01c3, B:76:0x01c7), top: B:99:0x01c3 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01e1 A[LOOP:3: B:82:0x01db->B:84:0x01e1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x020b A[LOOP:4: B:93:0x0205->B:95:0x020b, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<BarcodeRecord> getBarcodeRecordList() {
        String str;
        String str2;
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Iterator it;
        Iterator it2;
        SQLiteDatabase database;
        Cursor query;
        SQLiteDatabase sQLiteDatabase2;
        String str3;
        StringBuilder sb;
        String str4 = "scan_code";
        ArrayList<BarcodeRecord> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        try {
            database = DBHelperUtil.getDatabase();
        } catch (Exception e2) {
            e = e2;
            str = "id=?";
            str2 = "scan_code";
            sQLiteDatabase = null;
        } catch (Throwable th) {
            th = th;
            str = "id=?";
            str2 = "scan_code";
            sQLiteDatabase = null;
        }
        try {
            query = database.query("scan_code", new String[]{"id", "code", "format", "product_name", TB_COLUMN_PRODUCT_PRICE, "image_url", "type", TB_COLUMN_WAREID, TB_COLUMN_BARCODETYPE, "create_time"}, null, null, null, null, "create_time");
        } catch (Exception e3) {
            e = e3;
            str = "id=?";
            str2 = "scan_code";
            sQLiteDatabase = database;
            cursor = null;
            try {
                if (OKLog.E) {
                }
                if (cursor != null) {
                    cursor.close();
                }
                it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                }
                DBHelperUtil.closeDatabase();
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                it = arrayList2.iterator();
                while (it.hasNext()) {
                    sQLiteDatabase.delete(str2, str, new String[]{(String) it.next()});
                }
                DBHelperUtil.closeDatabase();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            str = "id=?";
            str2 = "scan_code";
            sQLiteDatabase = database;
            cursor = null;
            if (cursor != null) {
            }
            it = arrayList2.iterator();
            while (it.hasNext()) {
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
        if (query == null) {
            if (query != null && !query.isClosed()) {
                query.close();
            }
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                database.delete("scan_code", "id=?", new String[]{(String) it3.next()});
            }
            DBHelperUtil.closeDatabase();
            return arrayList;
        }
        try {
            query.moveToFirst();
            if (query.getCount() != 0) {
                try {
                    int count = query.getCount();
                    str3 = "id=?";
                    int i2 = count - 1;
                    while (i2 >= 0) {
                        try {
                            query.moveToPosition(i2);
                            String str5 = str4;
                            if (i2 < count - 50) {
                                try {
                                    sb = new StringBuilder();
                                    sQLiteDatabase2 = database;
                                } catch (Exception e4) {
                                    e = e4;
                                    sQLiteDatabase2 = database;
                                } catch (Throwable th4) {
                                    th = th4;
                                    sQLiteDatabase2 = database;
                                }
                                try {
                                    sb.append("");
                                    sb.append(query.getInt(query.getColumnIndex("id")));
                                    arrayList2.add(sb.toString());
                                } catch (Exception e5) {
                                    e = e5;
                                    cursor = query;
                                    str2 = str5;
                                    str = str3;
                                    sQLiteDatabase = sQLiteDatabase2;
                                    if (OKLog.E) {
                                        OKLog.e(TAG, e);
                                    }
                                    if (cursor != null && !cursor.isClosed()) {
                                        cursor.close();
                                    }
                                    it2 = arrayList2.iterator();
                                    while (it2.hasNext()) {
                                        sQLiteDatabase.delete(str2, str, new String[]{(String) it2.next()});
                                    }
                                    DBHelperUtil.closeDatabase();
                                    return arrayList;
                                } catch (Throwable th5) {
                                    th = th5;
                                    cursor = query;
                                    str2 = str5;
                                    str = str3;
                                    sQLiteDatabase = sQLiteDatabase2;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    it = arrayList2.iterator();
                                    while (it.hasNext()) {
                                    }
                                    DBHelperUtil.closeDatabase();
                                    throw th;
                                }
                            } else {
                                sQLiteDatabase2 = database;
                                BarcodeRecord barcodeRecord = new BarcodeRecord();
                                barcodeRecord.content = query.getString(query.getColumnIndex("code"));
                                barcodeRecord.format = query.getString(query.getColumnIndex("format"));
                                barcodeRecord.setProductName(query.getString(query.getColumnIndex("product_name")));
                                barcodeRecord.productPrice = query.getString(query.getColumnIndex(TB_COLUMN_PRODUCT_PRICE));
                                barcodeRecord.imageUrl = query.getString(query.getColumnIndex("image_url"));
                                barcodeRecord.type = query.getString(query.getColumnIndex("type"));
                                barcodeRecord.wareId = query.getString(query.getColumnIndex(TB_COLUMN_WAREID));
                                barcodeRecord.barcodeType = query.getString(query.getColumnIndex(TB_COLUMN_BARCODETYPE));
                                barcodeRecord.timeStamp = query.getString(query.getColumnIndex("create_time"));
                                arrayList.add(barcodeRecord);
                            }
                            i2--;
                            str4 = str5;
                            database = sQLiteDatabase2;
                        } catch (Exception e6) {
                            e = e6;
                            sQLiteDatabase2 = database;
                            str2 = str4;
                            cursor = query;
                        } catch (Throwable th6) {
                            th = th6;
                            sQLiteDatabase2 = database;
                            str2 = str4;
                            cursor = query;
                        }
                    }
                } catch (Exception e7) {
                    e = e7;
                    sQLiteDatabase2 = database;
                    str = "id=?";
                    str2 = "scan_code";
                    cursor = query;
                } catch (Throwable th7) {
                    th = th7;
                    sQLiteDatabase2 = database;
                    str = "id=?";
                    str2 = "scan_code";
                    cursor = query;
                }
            } else {
                str3 = "id=?";
            }
            String str6 = str4;
            SQLiteDatabase sQLiteDatabase3 = database;
            if (query != null && !query.isClosed()) {
                query.close();
            }
            Iterator it4 = arrayList2.iterator();
            while (it4.hasNext()) {
                sQLiteDatabase3.delete(str6, str3, new String[]{(String) it4.next()});
            }
        } catch (Exception e8) {
            e = e8;
            str = "id=?";
            str2 = "scan_code";
            sQLiteDatabase = database;
            cursor = query;
        } catch (Throwable th8) {
            th = th8;
            str = "id=?";
            str2 = "scan_code";
            sQLiteDatabase = database;
            cursor = query;
        }
        DBHelperUtil.closeDatabase();
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0, types: [com.jingdong.common.entity.BarcodeRecord] */
    /* JADX WARN: Type inference failed for: r15v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r15v3 */
    public static synchronized void insertOrUpdateBarcodeRecord(BarcodeRecord barcodeRecord) {
        Exception e2;
        Cursor cursor;
        SQLiteDatabase database;
        ContentValues contentValues;
        String[] strArr;
        synchronized (ScanCodeTable.class) {
            try {
                try {
                    database = DBHelperUtil.getDatabase();
                    contentValues = new ContentValues();
                    contentValues.put("code", barcodeRecord.content);
                    contentValues.put("format", barcodeRecord.format);
                    contentValues.put("product_name", barcodeRecord.getProductName());
                    contentValues.put(TB_COLUMN_PRODUCT_PRICE, barcodeRecord.productPrice);
                    contentValues.put("image_url", barcodeRecord.imageUrl);
                    contentValues.put("type", barcodeRecord.type);
                    contentValues.put(TB_COLUMN_WAREID, barcodeRecord.wareId);
                    contentValues.put(TB_COLUMN_BARCODETYPE, barcodeRecord.barcodeType);
                    strArr = new String[]{barcodeRecord.content};
                    cursor = database.query("scan_code", null, "code=?", strArr, null, null, null);
                } catch (Exception e3) {
                    e2 = e3;
                    cursor = null;
                } catch (Throwable th) {
                    th = th;
                    barcodeRecord = 0;
                    if (barcodeRecord != 0 && !barcodeRecord.isClosed()) {
                        barcodeRecord.close();
                    }
                    DBHelperUtil.closeDatabase();
                    throw th;
                }
                if (cursor != null) {
                    try {
                    } catch (Exception e4) {
                        e2 = e4;
                        if (OKLog.E) {
                            OKLog.e(TAG, e2);
                        }
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        DBHelperUtil.closeDatabase();
                    }
                    if (cursor.getCount() > 0) {
                        contentValues.put("create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        database.update("scan_code", contentValues, "code=?", strArr);
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        DBHelperUtil.closeDatabase();
                    }
                }
                database.insert("scan_code", null, contentValues);
                if (cursor != null) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE scan_code('id' INTEGER PRIMARY KEY  NOT NULL ,code TEXT,format TEXT,product_name TEXT,product_price TEXT,image_url TEXT,type TEXT,wareid TEXT,barcode_type TEXT,create_time DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists scan_code");
    }
}
