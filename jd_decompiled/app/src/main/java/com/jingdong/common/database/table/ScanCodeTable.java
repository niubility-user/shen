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
import java.util.Date;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.BarcodeRecord> getBarcodeRecordList() {
        /*
            Method dump skipped, instructions count: 545
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.ScanCodeTable.getBarcodeRecordList():java.util.ArrayList");
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
