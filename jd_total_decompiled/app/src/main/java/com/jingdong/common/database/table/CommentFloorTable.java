package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.CommentFloorEntity;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.jdsdk.utils.FormatUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CommentFloorTable implements IJdTable {
    private static final int MAX_SIZE = 10;
    public static final String TABLE_NAME = "comment_floor";
    private static final String TAG = "CommentFloorTable";
    private static final String TB_COLUMN_COMMENT_CONTENT = "comment_content";
    private static final String TB_COLUMN_CONTENT_TYPE = "comment_type";
    public static final String TB_COLUMN_ID = "id";
    private static final String TB_COLUMN_LAST_MODIFY = "last_modify";
    private static final String TB_COLUMN_ORDER_ID = "order_id";
    private static final String TB_COLUMN_PRODUCT_ID = "product_id";

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0083, code lost:
        if (r3.isClosed() == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0096, code lost:
        if (r3.isClosed() == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0098, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0027, code lost:
        if (r3.moveToFirst() != false) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0029, code lost:
        r2.add(java.lang.Integer.valueOf(r3.getInt(r3.getColumnIndex("id"))));
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x003c, code lost:
        if (r3.moveToNext() != false) goto L56;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void cleanOld() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                cursor = database.query(TABLE_NAME, new String[]{"id"}, null, null, null, null, "last_modify ASC");
                if (cursor != null) {
                }
                if (arrayList.size() > 10) {
                    database.beginTransaction();
                    for (int i2 = 0; i2 < arrayList.size() - 10; i2++) {
                        try {
                            try {
                                database.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(arrayList.get(i2))});
                            } finally {
                                database.endTransaction();
                            }
                        } catch (Exception e2) {
                            if (OKLog.E) {
                                OKLog.e(TAG, e2);
                            }
                        }
                    }
                    database.setTransactionSuccessful();
                }
                if (cursor != null) {
                }
            } catch (Exception e3) {
                if (OKLog.E) {
                    OKLog.e(TAG, e3);
                }
                if (0 != 0) {
                }
            }
            DBHelperUtil.closeDatabase();
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    public static synchronized void delete(String str, String str2, String str3) {
        synchronized (CommentFloorTable.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "CommentFloorTable delete() -->> ");
            }
            try {
                DBHelperUtil.getDatabase().delete(TABLE_NAME, "order_id = ? AND product_id = ? AND comment_type = ?", new String[]{str, str2, str3});
                if (OKLog.D) {
                    OKLog.d(TAG, "CacheFileTable delete() -->> ok");
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            DBHelperUtil.closeDatabase();
        }
    }

    public static synchronized void insertOrUpdate(CommentFloorEntity commentFloorEntity) {
        Throwable th;
        Cursor cursor;
        Exception e2;
        SQLiteDatabase database;
        ContentValues contentValues;
        String[] strArr;
        synchronized (CommentFloorTable.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "CommentFloorTable insertOrUpdate() -->> ");
            }
            try {
                database = DBHelperUtil.getDatabase();
                contentValues = new ContentValues();
                contentValues.put("order_id", commentFloorEntity.getOrderId());
                contentValues.put(TB_COLUMN_PRODUCT_ID, commentFloorEntity.getProductId());
                contentValues.put(TB_COLUMN_CONTENT_TYPE, commentFloorEntity.getCommentType());
                contentValues.put(TB_COLUMN_COMMENT_CONTENT, commentFloorEntity.getCommentContent());
                contentValues.put("last_modify", FormatUtils.formatDate(commentFloorEntity.getLastModify()));
                strArr = new String[]{commentFloorEntity.getOrderId(), commentFloorEntity.getProductId(), commentFloorEntity.getCommentType()};
                cursor = database.query(TABLE_NAME, null, "order_id = ? AND product_id = ? AND comment_type = ?", strArr, null, null, null);
            } catch (Exception e3) {
                e2 = e3;
                cursor = null;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
                throw th;
            }
            try {
                try {
                    if (OKLog.D && cursor != null) {
                        OKLog.d(TAG, "CommentFloorTable insertOrUpdate() -->> getCount \uff1a " + cursor.getCount());
                    }
                    if (cursor != null && cursor.getCount() > 0) {
                        database.update(TABLE_NAME, contentValues, "order_id = ? AND product_id = ? AND comment_type = ?", strArr);
                    } else {
                        database.insert(TABLE_NAME, null, contentValues);
                    }
                    if (OKLog.D) {
                        OKLog.d(TAG, "CommentFloorTable insertOrUpdate() -->> ok");
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    DBHelperUtil.closeDatabase();
                    throw th;
                }
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
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0054, code lost:
        r10 = new com.jingdong.common.entity.CommentFloorEntity();
        r10.setOrderId(r9.getString(r9.getColumnIndex("order_id")));
        r10.setProductId(r9.getString(r9.getColumnIndex(com.jingdong.common.database.table.CommentFloorTable.TB_COLUMN_PRODUCT_ID)));
        r10.setCommentType(r9.getString(r9.getColumnIndex(com.jingdong.common.database.table.CommentFloorTable.TB_COLUMN_CONTENT_TYPE)));
        r10.setCommentContent(r9.getString(r9.getColumnIndex(com.jingdong.common.database.table.CommentFloorTable.TB_COLUMN_COMMENT_CONTENT)));
        r10.setLastModify(com.jingdong.jdsdk.utils.FormatUtils.parseDate(r9.getString(r9.getColumnIndex("last_modify"))));
        r5.add(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x009b, code lost:
        if (r9.moveToNext() != false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00a6, code lost:
        if (r9.isClosed() == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ba, code lost:
        if (r9.isClosed() == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00bc, code lost:
        r9.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0052, code lost:
        if (r9.moveToFirst() != false) goto L10;
     */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x00d1: MOVE (r7 I:??[OBJECT, ARRAY]) = (r9 I:??[OBJECT, ARRAY]), block:B:35:0x00d1 */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static CommentFloorEntity query(String str, String str2, String str3) {
        Cursor cursor;
        Cursor cursor2;
        if (OKLog.D) {
            OKLog.d(TAG, "CommentFloorTable getListByClean() -->> ");
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursor3 = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, new String[]{"order_id", TB_COLUMN_PRODUCT_ID, TB_COLUMN_CONTENT_TYPE, TB_COLUMN_COMMENT_CONTENT, "last_modify"}, "order_id = ? AND product_id = ? AND comment_type = ?", new String[]{str, str2, str3}, null, null, null);
                if (cursor != null) {
                    try {
                    } catch (Exception e2) {
                        e = e2;
                        if (OKLog.E) {
                            OKLog.e(TAG, e);
                        }
                        if (cursor != null) {
                        }
                        DBHelperUtil.closeDatabase();
                        if (arrayList.size() <= 0) {
                        }
                    }
                }
                if (cursor != null) {
                }
            } catch (Exception e3) {
                e = e3;
                cursor = null;
            } catch (Throwable th) {
                th = th;
                if (cursor3 != null) {
                    cursor3.close();
                }
                DBHelperUtil.closeDatabase();
                throw th;
            }
            DBHelperUtil.closeDatabase();
            if (arrayList.size() <= 0) {
                return (CommentFloorEntity) arrayList.get(0);
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            cursor3 = cursor2;
            if (cursor3 != null && !cursor3.isClosed()) {
                cursor3.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS comment_floor(id INTEGER PRIMARY KEY NOT NULL,order_id TEXT,product_id TEXT,comment_type TEXT,comment_content TEXT,last_modify DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists comment_floor");
    }
}
