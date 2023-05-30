package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jingdong.common.entity.CommentEdit;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.jdsdk.utils.FormatUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class CommentEditTable implements IJdTable {
    private static final int MAX_SIZE = 5;
    public static final String TABLE_NAME = "evaluate_edit";
    private static final String TAG = "CommentEditTable";
    public static final String TB_COLUMN_BUSINESS_MEDIA_TAGS = "business_media_tags";
    public static final String TB_COLUMN_COMMENTS = "comments";
    public static final String TB_COLUMN_CONTENT = "content";
    public static final String TB_COLUMN_ID = "id";
    public static final String TB_COLUMN_IMGS = "imgs";
    public static final String TB_COLUMN_LASTMODIFY = "last_modify";
    public static final String TB_COLUMN_MEDIA_IDS = "media_ids";
    public static final String TB_COLUMN_MULTI_DIMEN_GRADE = "multi_dimen_grade";
    public static final String TB_COLUMN_ORDER_ID = "order_id";
    public static final String TB_COLUMN_SCORE = "score";
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
        synchronized (CommentEditTable.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "CommentEditTable delete() -->> ");
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

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0167, code lost:
        if (r13.isClosed() == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x017c, code lost:
        if (r13.isClosed() == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x017e, code lost:
        r13.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0193 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static CommentEdit getCommentEdit(String str, String str2) {
        Cursor cursor;
        String str3 = TB_COLUMN_COMMENTS;
        String str4 = "imgs";
        if (OKLog.D) {
            OKLog.d(TAG, "CommentEditTable getListByClean() -->> ");
        }
        ArrayList arrayList = new ArrayList();
        try {
            cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, new String[]{"order_id", "sku", "content", TB_COLUMN_SCORE, "last_modify", "imgs", TB_COLUMN_COMMENTS, "media_ids", "business_media_tags", TB_COLUMN_MULTI_DIMEN_GRADE}, "order_id = ? AND sku = ?", new String[]{str, str2}, null, null, null);
            if (cursor != null) {
                try {
                    try {
                        if (cursor.moveToFirst()) {
                            while (true) {
                                CommentEdit commentEdit = new CommentEdit();
                                commentEdit.setOrderId(cursor.getString(cursor.getColumnIndex("order_id")));
                                commentEdit.setSku(cursor.getString(cursor.getColumnIndex("sku")));
                                commentEdit.setContent(cursor.getString(cursor.getColumnIndex("content")));
                                commentEdit.setScore(cursor.getInt(cursor.getColumnIndex(TB_COLUMN_SCORE)));
                                commentEdit.setLastModify(FormatUtils.parseDate(cursor.getString(cursor.getColumnIndex("last_modify"))));
                                String string = cursor.getString(cursor.getColumnIndex(str4));
                                String str5 = str4;
                                ArrayList arrayList2 = new ArrayList();
                                arrayList2.addAll(Arrays.asList(string.split(";")));
                                commentEdit.setImgs(arrayList2);
                                String string2 = cursor.getString(cursor.getColumnIndex(str3));
                                ArrayList arrayList3 = new ArrayList();
                                String str6 = str3;
                                arrayList3.addAll(Arrays.asList(string2.split("/")));
                                commentEdit.setComments(arrayList3);
                                String string3 = cursor.getString(cursor.getColumnIndex("media_ids"));
                                if (!TextUtils.isEmpty(string3)) {
                                    ArrayList arrayList4 = new ArrayList();
                                    arrayList4.addAll(Arrays.asList(string3.split(";")));
                                    commentEdit.setMediaIds(arrayList4);
                                }
                                String string4 = cursor.getString(cursor.getColumnIndex("business_media_tags"));
                                ArrayList arrayList5 = new ArrayList();
                                arrayList5.addAll(Arrays.asList(string4.split(";")));
                                commentEdit.setBusinessMediaTags(arrayList5);
                                String string5 = cursor.getString(cursor.getColumnIndex(TB_COLUMN_MULTI_DIMEN_GRADE));
                                if (!TextUtils.isEmpty(string5)) {
                                    ArrayList arrayList6 = new ArrayList();
                                    arrayList6.addAll(Arrays.asList(string5.split(";")));
                                    commentEdit.setMultiDimenInfos(arrayList6);
                                }
                                arrayList.add(commentEdit);
                                if (!cursor.moveToNext()) {
                                    break;
                                }
                                str4 = str5;
                                str3 = str6;
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
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
            cursor = null;
            if (cursor != null) {
                cursor.close();
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0, types: [com.jingdong.common.entity.CommentEdit] */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v6, types: [android.database.Cursor] */
    public static synchronized void insertOrUpdate(CommentEdit commentEdit) {
        Throwable th;
        Exception e2;
        Cursor cursor;
        SQLiteDatabase database;
        ContentValues contentValues;
        String[] strArr;
        synchronized (CommentEditTable.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "CommentEditTable insertOrUpdate() -->> ");
            }
            try {
                try {
                    database = DBHelperUtil.getDatabase();
                    contentValues = new ContentValues();
                    contentValues.put("order_id", commentEdit.getOrderId());
                    contentValues.put("sku", commentEdit.getSku());
                    contentValues.put("content", commentEdit.getContent());
                    contentValues.put(TB_COLUMN_SCORE, Integer.valueOf(commentEdit.getScore()));
                    contentValues.put("imgs", commentEdit.getImgsString());
                    contentValues.put(TB_COLUMN_COMMENTS, commentEdit.getCommentsString());
                    contentValues.put("last_modify", FormatUtils.formatDate(commentEdit.getLastModify()));
                    contentValues.put("media_ids", commentEdit.getMediaIdsString());
                    contentValues.put("business_media_tags", commentEdit.getBusinessMediaTagsString());
                    contentValues.put(TB_COLUMN_MULTI_DIMEN_GRADE, commentEdit.getMultiDimenInfoString());
                    strArr = new String[]{commentEdit.getOrderId(), commentEdit.getSku()};
                    cursor = database.query(TABLE_NAME, null, "order_id = ? AND sku = ?", strArr, null, null, null);
                } catch (Exception e3) {
                    e2 = e3;
                    cursor = null;
                } catch (Throwable th2) {
                    th = th2;
                    commentEdit = 0;
                    if (commentEdit != 0) {
                        commentEdit.close();
                    }
                    DBHelperUtil.closeDatabase();
                    throw th;
                }
                try {
                    if (OKLog.D && cursor != null) {
                        OKLog.d(TAG, "CommentEditTable insertOrUpdate() -->> getCount \uff1a " + cursor.getCount());
                    }
                    if (cursor != null && cursor.getCount() > 0) {
                        database.update(TABLE_NAME, contentValues, "order_id = ? AND sku = ?", strArr);
                    } else {
                        database.insert(TABLE_NAME, null, contentValues);
                    }
                    if (OKLog.D) {
                        OKLog.d(TAG, "CommentEditTable insertOrUpdate() -->> ok");
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
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
            } catch (Throwable th3) {
                th = th3;
                if (commentEdit != 0 && !commentEdit.isClosed()) {
                    commentEdit.close();
                }
                DBHelperUtil.closeDatabase();
                throw th;
            }
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS evaluate_edit(id INTEGER PRIMARY KEY  NOT NULL ,order_id TEXT,sku TEXT,last_modify DATETIME DEFAULT CURRENT_TIMESTAMP,content TEXT,score INTEGER,imgs TEXT,comments TEXT,media_ids TEXT,business_media_tags TEXT,multi_dimen_grade TEXT)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists evaluate_edit");
    }
}
