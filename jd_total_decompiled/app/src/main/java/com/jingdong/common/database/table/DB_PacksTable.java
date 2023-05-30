package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.Pack;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class DB_PacksTable implements IJdTable {
    private static final String TAG = "DB_PacksTable";
    public static final String TB_CLOUMN_SOURCE_TYPE = "sourceType";
    public static final String TB_CLOUMN_SOURCE_VALUE = "sourceValue";
    public static final String TB_COLOUMN_BUY_COUNT = "buyCount";
    public static final String TB_COLOUMN_CHILD_COUNT = "childCount";
    public static final String TB_COLOUMN_MAIN_SKU_ID = "mainSkuId";
    public static final String TB_COLOUMN_PACK_ID = "packId";
    public static final String TB_COLOUMN_PRODUCT_CODE = "productCode";
    public static final String TB_COLOUMN_PRODUCT_NAME = "name";
    public static final String TB_COLOUMN_USER_NAME = "userName";
    public static final String TB_PACKS_TABLE = "PacksTable";

    public static void delAllPacksCart(Context context) {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TB_PACKS_TABLE, "1=1", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00e3, code lost:
        if (r7 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00f2, code lost:
        if (0 != 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00f4, code lost:
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00f7, code lost:
        com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00fa, code lost:
        return r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static HashMap<String, Pack> getPacksListForPack() {
        HashMap<String, Pack> hashMap = new HashMap<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().query(TB_PACKS_TABLE, new String[]{"packId", "name", "buyCount", TB_COLOUMN_CHILD_COUNT, "sourceType", "sourceValue"}, null, null, null, null, null);
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
                    if (OKLog.D) {
                        OKLog.d("Temp", "c.getCount() -->> " + cursor.getCount());
                    }
                    for (int i2 = 0; i2 < count; i2++) {
                        cursor.moveToPosition(i2);
                        Pack pack = new Pack();
                        pack.setId(Long.valueOf(cursor.getLong(cursor.getColumnIndex("packId"))));
                        pack.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                        pack.setNum(Integer.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("buyCount"))));
                        pack.setProductCount(Integer.valueOf(cursor.getInt(cursor.getColumnIndex(TB_COLOUMN_CHILD_COUNT))));
                        pack.setSourceEntity(new SourceEntity(cursor.getString(cursor.getColumnIndexOrThrow("sourceType")), cursor.getString(cursor.getColumnIndexOrThrow("sourceValue"))));
                        hashMap.put(pack.getId() + "", pack);
                    }
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
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

    public static synchronized void insertAllPacksCart(List<Pack> list) {
        synchronized (DB_PacksTable.class) {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                if (list != null) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        ContentValues contentValues = new ContentValues();
                        Pack pack = list.get(i2);
                        contentValues.put("packId", pack.getId());
                        contentValues.put("name", pack.getName());
                        contentValues.put("buyCount", pack.getNum());
                        contentValues.put(TB_COLOUMN_CHILD_COUNT, pack.getProductCount());
                        SourceEntity sourceEntity = pack.getSourceEntity();
                        if (OKLog.D) {
                            OKLog.i("TEST", " insertAllPacksCart ---> sourceEntity : " + sourceEntity);
                        }
                        if (sourceEntity != null) {
                            if (OKLog.D) {
                                OKLog.d("TEST", " insertAllPacksCart ---> getSourceType : " + sourceEntity.getSourceType());
                                OKLog.d("TEST", " insertAllPacksCart ---> getSourceValue : " + sourceEntity.getSourceValue());
                            }
                            contentValues.put("sourceType", sourceEntity.getSourceType());
                            contentValues.put("sourceValue", sourceEntity.getSourceValue());
                        }
                        database.insert(TB_PACKS_TABLE, null, contentValues);
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

    public static synchronized void insertSingletonPacksCart(Pack pack) {
        SQLiteDatabase database;
        Cursor query;
        synchronized (DB_PacksTable.class) {
            Cursor cursor = null;
            try {
                try {
                    try {
                        if (OKLog.D) {
                            OKLog.d("Temp", "insertSingletonPacksCart -->> pack:" + pack);
                        }
                        database = DBHelperUtil.getDatabase();
                        query = database.query(TB_PACKS_TABLE, null, "packId=?", new String[]{pack.getId() + ""}, null, null, null);
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception unused) {
            }
            if (query == null) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception unused2) {
                    }
                }
                DBHelperUtil.closeDatabase();
                return;
            }
            try {
                query.moveToFirst();
                if (query.getCount() == 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("packId", pack.getId());
                    contentValues.put("name", pack.getName());
                    contentValues.put("buyCount", pack.getNum());
                    contentValues.put(TB_COLOUMN_CHILD_COUNT, pack.getProductCount());
                    SourceEntity sourceEntity = pack.getSourceEntity();
                    if (OKLog.D) {
                        OKLog.i("TEST", " insertSingletonPacksCart ---> sourceEntity : " + sourceEntity);
                    }
                    if (sourceEntity != null) {
                        if (OKLog.D) {
                            OKLog.d("TEST", " insertSingletonPacksCart ---> getSourceType : " + sourceEntity.getSourceType());
                            OKLog.d("TEST", " insertSingletonPacksCart ---> getSourceValue : " + sourceEntity.getSourceValue());
                        }
                        contentValues.put("sourceType", sourceEntity.getSourceType());
                        contentValues.put("sourceValue", sourceEntity.getSourceValue());
                    }
                    database.insert(TB_PACKS_TABLE, null, contentValues);
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                cursor = query;
                if (OKLog.E) {
                    OKLog.e("Temp", "insertSingletonPacksCart Exception -->> ", e);
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
                    } catch (Exception unused3) {
                        throw th;
                    }
                }
                DBHelperUtil.closeDatabase();
                throw th;
            }
            DBHelperUtil.closeDatabase();
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE PacksTable('id' INTEGER PRIMARY KEY  NOT NULL ,packId LONG,name TEXT,buyCount INTEGER,childCount INTEGER,sourceType TEXT,sourceValue TEXT, 'browseTime' DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists PacksTable");
    }

    public static void delAllPacksCart() {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TB_PACKS_TABLE, "1=1", null);
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
