package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.CommentFloorEntity;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.jdsdk.utils.FormatUtils;
import com.jingdong.sdk.oklog.OKLog;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void cleanOld() {
        /*
            java.lang.String r0 = "CommentFloorTable"
            java.lang.String r1 = "id"
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r3 = 0
            android.database.sqlite.SQLiteDatabase r12 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r13 = 1
            java.lang.String[] r6 = new java.lang.String[r13]     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r14 = 0
            r6[r14] = r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r5 = "comment_floor"
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            java.lang.String r11 = "last_modify ASC"
            r4 = r12
            android.database.Cursor r3 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            if (r3 == 0) goto L3e
            boolean r4 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            if (r4 == 0) goto L3e
        L29:
            int r4 = r3.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            int r4 = r3.getInt(r4)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r2.add(r4)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            boolean r4 = r3.moveToNext()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            if (r4 != 0) goto L29
        L3e:
            int r1 = r2.size()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r4 = 10
            if (r1 <= r4) goto L7d
            java.lang.String r1 = "id = ?"
            r12.beginTransaction()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r5 = 0
        L4c:
            int r6 = r2.size()     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            int r6 = r6 - r4
            if (r5 >= r6) goto L67
            java.lang.String r6 = "comment_floor"
            java.lang.String[] r7 = new java.lang.String[r13]     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            java.lang.Object r8 = r2.get(r5)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            r7[r14] = r8     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            r12.delete(r6, r1, r7)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            int r5 = r5 + 1
            goto L4c
        L67:
            r12.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
        L6a:
            r12.endTransaction()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            goto L7d
        L6e:
            r1 = move-exception
            goto L79
        L70:
            r1 = move-exception
            boolean r2 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L6e
            if (r2 == 0) goto L6a
            com.jingdong.sdk.oklog.OKLog.e(r0, r1)     // Catch: java.lang.Throwable -> L6e
            goto L6a
        L79:
            r12.endTransaction()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            throw r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
        L7d:
            if (r3 == 0) goto L9b
            boolean r0 = r3.isClosed()
            if (r0 != 0) goto L9b
            goto L98
        L86:
            r0 = move-exception
            goto L9f
        L88:
            r1 = move-exception
            boolean r2 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L86
            if (r2 == 0) goto L90
            com.jingdong.sdk.oklog.OKLog.e(r0, r1)     // Catch: java.lang.Throwable -> L86
        L90:
            if (r3 == 0) goto L9b
            boolean r0 = r3.isClosed()
            if (r0 != 0) goto L9b
        L98:
            r3.close()
        L9b:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return
        L9f:
            if (r3 == 0) goto Laa
            boolean r1 = r3.isClosed()
            if (r1 != 0) goto Laa
            r3.close()
        Laa:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Laf
        Lae:
            throw r0
        Laf:
            goto Lae
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.CommentFloorTable.cleanOld():void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.common.entity.CommentFloorEntity query(java.lang.String r18, java.lang.String r19, java.lang.String r20) {
        /*
            java.lang.String r0 = "last_modify"
            java.lang.String r1 = "comment_content"
            java.lang.String r2 = "comment_type"
            java.lang.String r3 = "product_id"
            java.lang.String r4 = "order_id"
            boolean r5 = com.jingdong.sdk.oklog.OKLog.D
            java.lang.String r6 = "CommentFloorTable"
            if (r5 == 0) goto L15
            java.lang.String r5 = "CommentFloorTable getListByClean() -->> "
            com.jingdong.sdk.oklog.OKLog.d(r6, r5)
        L15:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r7 = 0
            r8 = 0
            android.database.sqlite.SQLiteDatabase r9 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r10 = 5
            java.lang.String[] r11 = new java.lang.String[r10]     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r11[r8] = r4     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r10 = 1
            r11[r10] = r3     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r12 = 2
            r11[r12] = r2     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r13 = 3
            r11[r13] = r1     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r14 = 4
            r11[r14] = r0     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r14 = "order_id = ? AND product_id = ? AND comment_type = ?"
            java.lang.String[] r13 = new java.lang.String[r13]     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r13[r8] = r18     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r13[r10] = r19     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            r13[r12] = r20     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            java.lang.String r10 = "comment_floor"
            r15 = 0
            r16 = 0
            r17 = 0
            r12 = r14
            r14 = r15
            r15 = r16
            r16 = r17
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab
            if (r9 == 0) goto La0
            boolean r10 = r9.moveToFirst()     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            if (r10 == 0) goto La0
        L54:
            com.jingdong.common.entity.CommentFloorEntity r10 = new com.jingdong.common.entity.CommentFloorEntity     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            r10.<init>()     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            int r11 = r9.getColumnIndex(r4)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            java.lang.String r11 = r9.getString(r11)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            r10.setOrderId(r11)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            int r11 = r9.getColumnIndex(r3)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            java.lang.String r11 = r9.getString(r11)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            r10.setProductId(r11)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            int r11 = r9.getColumnIndex(r2)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            java.lang.String r11 = r9.getString(r11)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            r10.setCommentType(r11)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            int r11 = r9.getColumnIndex(r1)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            java.lang.String r11 = r9.getString(r11)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            r10.setCommentContent(r11)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            int r11 = r9.getColumnIndex(r0)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            java.lang.String r11 = r9.getString(r11)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            java.util.Date r11 = com.jingdong.jdsdk.utils.FormatUtils.parseDate(r11)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            r10.setLastModify(r11)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            r5.add(r10)     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            boolean r10 = r9.moveToNext()     // Catch: java.lang.Exception -> L9e java.lang.Throwable -> Ld0
            if (r10 != 0) goto L54
            goto La0
        L9e:
            r0 = move-exception
            goto Lad
        La0:
            if (r9 == 0) goto Lbf
            boolean r0 = r9.isClosed()
            if (r0 != 0) goto Lbf
            goto Lbc
        La9:
            r0 = move-exception
            goto Ld2
        Lab:
            r0 = move-exception
            r9 = r7
        Lad:
            boolean r1 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> Ld0
            if (r1 == 0) goto Lb4
            com.jingdong.sdk.oklog.OKLog.e(r6, r0)     // Catch: java.lang.Throwable -> Ld0
        Lb4:
            if (r9 == 0) goto Lbf
            boolean r0 = r9.isClosed()
            if (r0 != 0) goto Lbf
        Lbc:
            r9.close()
        Lbf:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            int r0 = r5.size()
            if (r0 <= 0) goto Lcf
            java.lang.Object r0 = r5.get(r8)
            r7 = r0
            com.jingdong.common.entity.CommentFloorEntity r7 = (com.jingdong.common.entity.CommentFloorEntity) r7
        Lcf:
            return r7
        Ld0:
            r0 = move-exception
            r7 = r9
        Ld2:
            if (r7 == 0) goto Ldd
            boolean r1 = r7.isClosed()
            if (r1 != 0) goto Ldd
            r7.close()
        Ldd:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Le2
        Le1:
            throw r0
        Le2:
            goto Le1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.CommentFloorTable.query(java.lang.String, java.lang.String, java.lang.String):com.jingdong.common.entity.CommentFloorEntity");
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
