package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.CommentEdit;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.jdsdk.utils.FormatUtils;
import com.jingdong.sdk.oklog.OKLog;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void cleanOld() {
        /*
            java.lang.String r0 = "id"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r11 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r12 = 1
            java.lang.String[] r5 = new java.lang.String[r12]     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r13 = 0
            r5[r13] = r0     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            java.lang.String r4 = "evaluate_edit"
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r10 = "last_modify ASC"
            r3 = r11
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            if (r2 == 0) goto L3c
            boolean r3 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            if (r3 == 0) goto L3c
        L27:
            int r3 = r2.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            int r3 = r2.getInt(r3)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r1.add(r3)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            if (r3 != 0) goto L27
        L3c:
            int r0 = r1.size()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r3 = 5
            if (r0 <= r3) goto L70
            java.lang.String r0 = "id = ?"
            r11.beginTransaction()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r4 = 0
        L49:
            int r5 = r1.size()     // Catch: java.lang.Exception -> L67 java.lang.Throwable -> L6b
            int r5 = r5 - r3
            if (r4 >= r5) goto L64
            java.lang.String r5 = "evaluate_edit"
            java.lang.String[] r6 = new java.lang.String[r12]     // Catch: java.lang.Exception -> L67 java.lang.Throwable -> L6b
            java.lang.Object r7 = r1.get(r4)     // Catch: java.lang.Exception -> L67 java.lang.Throwable -> L6b
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch: java.lang.Exception -> L67 java.lang.Throwable -> L6b
            r6[r13] = r7     // Catch: java.lang.Exception -> L67 java.lang.Throwable -> L6b
            r11.delete(r5, r0, r6)     // Catch: java.lang.Exception -> L67 java.lang.Throwable -> L6b
            int r4 = r4 + 1
            goto L49
        L64:
            r11.setTransactionSuccessful()     // Catch: java.lang.Exception -> L67 java.lang.Throwable -> L6b
        L67:
            r11.endTransaction()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            goto L70
        L6b:
            r0 = move-exception
            r11.endTransaction()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            throw r0     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
        L70:
            if (r2 == 0) goto L90
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L90
            goto L8d
        L79:
            r0 = move-exception
            goto L94
        L7b:
            r0 = move-exception
            boolean r1 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L79
            if (r1 == 0) goto L85
            java.lang.String r1 = "CommentEditTable"
            com.jingdong.sdk.oklog.OKLog.e(r1, r0)     // Catch: java.lang.Throwable -> L79
        L85:
            if (r2 == 0) goto L90
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L90
        L8d:
            r2.close()
        L90:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return
        L94:
            if (r2 == 0) goto L9f
            boolean r1 = r2.isClosed()
            if (r1 != 0) goto L9f
            r2.close()
        L9f:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto La4
        La3:
            throw r0
        La4:
            goto La3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.CommentEditTable.cleanOld():void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.common.entity.CommentEdit getCommentEdit(java.lang.String r23, java.lang.String r24) {
        /*
            Method dump skipped, instructions count: 423
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.CommentEditTable.getCommentEdit(java.lang.String, java.lang.String):com.jingdong.common.entity.CommentEdit");
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
