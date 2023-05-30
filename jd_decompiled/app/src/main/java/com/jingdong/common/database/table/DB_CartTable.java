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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.HashMap<java.lang.String, com.jingdong.common.entity.Product> getCartListForProduct() {
        /*
            java.lang.String r0 = "sourceValue"
            java.lang.String r1 = "sourceType"
            java.lang.String r2 = "buyCount"
            java.lang.String r3 = "name"
            java.lang.String r4 = "productCode"
            java.util.HashMap r5 = new java.util.HashMap
            r5.<init>()
            r6 = 0
            android.database.sqlite.SQLiteDatabase r7 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r8 = 5
            java.lang.String[] r9 = new java.lang.String[r8]     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r15 = 0
            r9[r15] = r4     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r8 = 1
            r9[r8] = r3     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r8 = 2
            r9[r8] = r2     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r8 = 3
            r9[r8] = r1     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r8 = 4
            r9[r8] = r0     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            java.lang.String r8 = "CartTable"
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            android.database.Cursor r6 = r7.query(r8, r9, r10, r11, r12, r13, r14)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            if (r6 != 0) goto L3c
            if (r6 == 0) goto L38
            r6.close()
        L38:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r5
        L3c:
            r6.moveToFirst()     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            int r7 = r6.getCount()     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            if (r7 == 0) goto Laf
            int r7 = r6.getCount()     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
        L49:
            if (r15 >= r7) goto Laf
            r6.moveToPosition(r15)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            com.jingdong.common.entity.Product r8 = new com.jingdong.common.entity.Product     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r8.<init>()     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            int r9 = r6.getColumnIndex(r4)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            long r9 = r6.getLong(r9)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r8.setId(r9)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            int r9 = r6.getColumnIndexOrThrow(r3)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            java.lang.String r9 = r6.getString(r9)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r8.setName(r9)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            int r9 = r6.getColumnIndexOrThrow(r2)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            int r9 = r6.getInt(r9)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r8.setNum(r9)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            com.jingdong.common.entity.SourceEntity r9 = new com.jingdong.common.entity.SourceEntity     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            int r10 = r6.getColumnIndexOrThrow(r1)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            java.lang.String r10 = r6.getString(r10)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            int r11 = r6.getColumnIndexOrThrow(r0)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            java.lang.String r11 = r6.getString(r11)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r9.<init>(r10, r11)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r8.setSourceEntity(r9)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r9.<init>()     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            java.lang.Long r10 = r8.getId()     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r9.append(r10)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            java.lang.String r10 = ""
            r9.append(r10)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r5.put(r9, r8)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            int r15 = r15 + 1
            goto L49
        Laf:
            if (r6 == 0) goto Lc5
            goto Lc2
        Lb2:
            r0 = move-exception
            goto Lc9
        Lb4:
            r0 = move-exception
            boolean r1 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> Lb2
            if (r1 == 0) goto Lc0
            java.lang.String r1 = "Temp"
            java.lang.String r2 = "getCartListForProduct Exception -->> "
            com.jingdong.sdk.oklog.OKLog.e(r1, r2, r0)     // Catch: java.lang.Throwable -> Lb2
        Lc0:
            if (r6 == 0) goto Lc5
        Lc2:
            r6.close()
        Lc5:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r5
        Lc9:
            if (r6 == 0) goto Lce
            r6.close()
        Lce:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Ld3
        Ld2:
            throw r0
        Ld3:
            goto Ld2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.DB_CartTable.getCartListForProduct():java.util.HashMap");
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
