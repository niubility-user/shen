package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class DB_CartTable implements IJdTable {
    private static final String TAG = "DB_CartTable";
    public static final String TB_CART_TABLE = "CartTable";
    public static final String TB_CLOUMN_BUY_COUNT = "buyCount";
    public static final String TB_CLOUMN_EXTEND = "extendProt";
    public static final String TB_CLOUMN_PACKS_CODE = "packId";
    public static final String TB_CLOUMN_PRODUCT_CODE = "productCode";
    public static final String TB_CLOUMN_PRODUCT_NAME = "name";
    public static final String TB_CLOUMN_SOURCE_TYPE = "sourceType";
    public static final String TB_CLOUMN_SOURCE_VALUE = "sourceValue";
    public static final String TB_CLOUMN_USER_NAME = "userName";

    public static void delAllCart(Context context) {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TB_CART_TABLE, "1=1", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x00af, code lost:
        if (r6 != null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00c0, code lost:
        if (0 != 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00c2, code lost:
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00c5, code lost:
        com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00c8, code lost:
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static HashMap<String, Product> getCartListForProduct() {
        HashMap<String, Product> hashMap = new HashMap<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().query(TB_CART_TABLE, new String[]{"productCode", "name", "buyCount", "sourceType", "sourceValue"}, null, null, null, null, null);
                if (cursor == null) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    DBHelperUtil.closeDatabase();
                    return hashMap;
                }
                cursor.moveToFirst();
                if (cursor.getCount() != 0) {
                    int count = cursor.getCount();
                    for (int i2 = 0; i2 < count; i2++) {
                        cursor.moveToPosition(i2);
                        Product product = new Product();
                        product.setId(Long.valueOf(cursor.getLong(cursor.getColumnIndex("productCode"))));
                        product.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                        product.setNum(Integer.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("buyCount"))));
                        product.setSourceEntity(new SourceEntity(cursor.getString(cursor.getColumnIndexOrThrow("sourceType")), cursor.getString(cursor.getColumnIndexOrThrow("sourceValue"))));
                        hashMap.put(product.getId() + "", product);
                    }
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("Temp", "getCartListForProduct Exception -->> ", e2);
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    public static synchronized void insertAllCart(List<Product> list) {
        synchronized (DB_CartTable.class) {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                if (list != null) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        ContentValues contentValues = new ContentValues();
                        Product product = list.get(i2);
                        contentValues.put("productCode", product.getId());
                        contentValues.put("name", product.getName());
                        contentValues.put("buyCount", product.getNum());
                        SourceEntity sourceEntity = product.getSourceEntity();
                        if (OKLog.D) {
                            OKLog.i("TEST", " insertAllCart ---> sourceEntity : " + sourceEntity);
                        }
                        if (sourceEntity != null) {
                            if (OKLog.D) {
                                OKLog.d("TEST", " insertAllCart ---> getSourceType : " + sourceEntity.getSourceType());
                                OKLog.d("TEST", " insertAllCart ---> getSourceValue : " + sourceEntity.getSourceValue());
                            }
                            contentValues.put("sourceType", sourceEntity.getSourceType());
                            contentValues.put("sourceValue", sourceEntity.getSourceValue());
                        }
                        database.insert(TB_CART_TABLE, null, contentValues);
                    }
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            DBHelperUtil.closeDatabase();
        }
    }

    public static void insertSingletonCart(Product product) {
        Cursor query;
        Cursor cursor = null;
        try {
            try {
                try {
                    if (OKLog.D) {
                        OKLog.d("Temp", "insertSingletonCart -->> product:" + product);
                    }
                    query = DBHelperUtil.getDatabase().query(TB_CART_TABLE, null, "productCode=?", new String[]{product.getId() + ""}, null, null, null);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            if (query == null) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception unused) {
                        return;
                    }
                }
                DBHelperUtil.closeDatabase();
                return;
            }
            try {
                query.moveToFirst();
                if (query.getCount() == 0) {
                    SQLiteDatabase database = DBHelperUtil.getDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("productCode", product.getId());
                    contentValues.put("name", product.getName());
                    contentValues.put("buyCount", product.getNum());
                    SourceEntity sourceEntity = product.getSourceEntity();
                    if (OKLog.D) {
                        OKLog.i("TEST", " insertSingletonCart ---> sourceEntity : " + sourceEntity);
                    }
                    if (sourceEntity != null) {
                        if (OKLog.D) {
                            OKLog.d("TEST", " insertSingletonCart ---> getSourceType : " + sourceEntity.getSourceType());
                            OKLog.d("TEST", " insertSingletonCart ---> getSourceValue : " + sourceEntity.getSourceValue());
                        }
                        contentValues.put("sourceType", sourceEntity.getSourceType());
                        contentValues.put("sourceValue", sourceEntity.getSourceValue());
                    }
                    database.insert(TB_CART_TABLE, null, contentValues);
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                cursor = query;
                if (OKLog.E) {
                    OKLog.e("Temp", "insertSingletonCart Exception -->> ", e);
                }
                if (cursor != null) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Exception unused2) {
                        throw th;
                    }
                }
                DBHelperUtil.closeDatabase();
                throw th;
            }
            DBHelperUtil.closeDatabase();
        } catch (Exception unused3) {
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE CartTable('id' INTEGER PRIMARY KEY  NOT NULL ,name TEXT,productCode LONG,sourceType TEXT,sourceValue TEXT,buyCount 'browseTime' DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists CartTable");
    }

    public static void delAllCart() {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TB_CART_TABLE, "1=1", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }
}
