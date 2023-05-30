package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.CommentEdit;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.jdsdk.utils.FormatUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CommentAgainTable implements IJdTable {
    private static final int MAX_SIZE = 5;
    public static final String TABLE_NAME = "evaluate_again";
    private static final String TAG = "CommentAgainTable";
    public static final String TB_COLUMN_BUSINESS_MEDIA_TAGS = "business_media_tags";
    public static final String TB_COLUMN_CONTENT = "content";
    public static final String TB_COLUMN_ID = "id";
    public static final String TB_COLUMN_IMGS = "imgs";
    public static final String TB_COLUMN_LASTMODIFY = "last_modify";
    public static final String TB_COLUMN_MEDIA_IDS = "media_ids";
    public static final String TB_COLUMN_ORDER_ID = "order_id";
    public static final String TB_COLUMN_SKU = "sku";

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0076, code lost:
        if (r2.isClosed() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008b, code lost:
        if (r2.isClosed() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x008d, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0025, code lost:
        if (r2.moveToFirst() != false) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0027, code lost:
        r1.add(java.lang.Integer.valueOf(r2.getInt(r2.getColumnIndex("id"))));
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x003a, code lost:
        if (r2.moveToNext() != false) goto L50;
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
                if (arrayList.size() > 5) {
                    database.beginTransaction();
                    for (int i2 = 0; i2 < arrayList.size() - 5; i2++) {
                        try {
                            database.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(arrayList.get(i2))});
                        } catch (Exception unused) {
                        } catch (Throwable th) {
                            database.endTransaction();
                            throw th;
                        }
                    }
                    database.setTransactionSuccessful();
                    database.endTransaction();
                }
                if (cursor != null) {
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0) {
                }
            }
            DBHelperUtil.closeDatabase();
        } catch (Throwable th2) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th2;
        }
    }

    public static synchronized void delete(String str, String str2) {
        synchronized (CommentAgainTable.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "CommentAgainTable delete() -->> ");
            }
            try {
                DBHelperUtil.getDatabase().delete(TABLE_NAME, "order_id = ? AND sku = ?", new String[]{str, str2});
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

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0063, code lost:
        r13 = new com.jingdong.common.entity.CommentEdit();
        r13.setOrderId(r12.getString(r12.getColumnIndex("order_id")));
        r13.setSku(r12.getString(r12.getColumnIndex("sku")));
        r13.setContent(r12.getString(r12.getColumnIndex("content")));
        r13.setLastModify(com.jingdong.jdsdk.utils.FormatUtils.parseDate(r12.getString(r12.getColumnIndex("last_modify"))));
        r14 = r12.getString(r12.getColumnIndex("imgs"));
        r15 = new java.util.ArrayList();
        r15.addAll(java.util.Arrays.asList(r14.split(";")));
        r13.setImgs(r15);
        r14 = r12.getString(r12.getColumnIndex("media_ids"));
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x00bf, code lost:
        if (android.text.TextUtils.isEmpty(r14) != false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x00c1, code lost:
        r15 = new java.util.ArrayList();
        r15.addAll(java.util.Arrays.asList(r14.split(";")));
        r13.setMediaIds(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00d4, code lost:
        r14 = r12.getString(r12.getColumnIndex("business_media_tags"));
        r15 = new java.util.ArrayList();
        r15.addAll(java.util.Arrays.asList(r14.split(";")));
        r13.setComments(r15);
        r8.add(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00f6, code lost:
        if (r12.moveToNext() != false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0101, code lost:
        if (r12.isClosed() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0115, code lost:
        if (r12.isClosed() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0117, code lost:
        r12.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0061, code lost:
        if (r12.moveToFirst() != false) goto L10;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static CommentEdit getCommentEdit(String str, String str2) {
        Cursor cursor;
        if (OKLog.D) {
            OKLog.d(TAG, "CommentAgainTable getListByClean() -->> ");
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, new String[]{"order_id", "sku", "content", "last_modify", "imgs", "media_ids", "business_media_tags"}, "order_id = ? AND sku = ?", new String[]{str, str2}, null, null, null);
            if (cursor != null) {
                try {
                    try {
                    } catch (Throwable th) {
                        th = th;
                        cursor2 = cursor;
                        if (cursor2 != null && !cursor2.isClosed()) {
                            cursor2.close();
                        }
                        DBHelperUtil.closeDatabase();
                        throw th;
                    }
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
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
                cursor2.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
        DBHelperUtil.closeDatabase();
        if (arrayList.size() <= 0) {
            return (CommentEdit) arrayList.get(0);
        }
        return null;
    }

    public static synchronized void insertOrUpdate(CommentEdit commentEdit) {
        Throwable th;
        Cursor cursor;
        Exception e2;
        SQLiteDatabase database;
        ContentValues contentValues;
        String[] strArr;
        synchronized (CommentAgainTable.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "CommentAgainTable insertOrUpdate() -->> ");
            }
            try {
                database = DBHelperUtil.getDatabase();
                contentValues = new ContentValues();
                contentValues.put("order_id", commentEdit.getOrderId());
                contentValues.put("sku", commentEdit.getSku());
                contentValues.put("content", commentEdit.getContent());
                contentValues.put("imgs", commentEdit.getImgsString());
                contentValues.put("last_modify", FormatUtils.formatDate(commentEdit.getLastModify()));
                contentValues.put("media_ids", commentEdit.getMediaIdsString());
                contentValues.put("business_media_tags", commentEdit.getBusinessMediaTagsString());
                strArr = new String[]{commentEdit.getOrderId(), commentEdit.getSku()};
                cursor = database.query(TABLE_NAME, null, "order_id = ? AND sku = ?", strArr, null, null, null);
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
                        OKLog.d(TAG, "CommentAgainTable insertOrUpdate() -->> getCount \uff1a " + cursor.getCount());
                    }
                    if (cursor != null && cursor.getCount() > 0) {
                        database.update(TABLE_NAME, contentValues, "order_id = ? AND sku = ?", strArr);
                    } else {
                        database.insert(TABLE_NAME, null, contentValues);
                    }
                    if (OKLog.D) {
                        OKLog.d(TAG, "CommentAgainTable insertOrUpdate() -->> ok");
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

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS evaluate_again(id INTEGER PRIMARY KEY  NOT NULL ,order_id TEXT,sku TEXT,last_modify DATETIME DEFAULT CURRENT_TIMESTAMP,content TEXT,imgs TEXT,media_ids TEXT,business_media_tags TEXT)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists evaluate_again");
    }
}
