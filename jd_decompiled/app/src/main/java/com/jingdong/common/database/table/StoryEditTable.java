package com.jingdong.common.database.table;

import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class StoryEditTable implements IJdTable {
    public static final String TABLE_NAME = "story_edit";
    private static final String TAG = "StoryEditTable";
    public static final String TB_COLUMN_CIRCLEID = "relate_circle_d";
    public static final String TB_COLUMN_CONTENT = "content";
    public static final String TB_COLUMN_ID = "id";
    public static final String TB_COLUMN_LASTMODIFY = "last_modify";
    public static final String TB_COLUMN_PRODUCT_IMG_ENTITYS = "product_img_entitys";
    public static final String TB_COLUMN_SRORY_IMG_ENTITYS = "story_img_entitys";
    public static final String TB_COLUMN_STORY_EDIT_ID = "story_edit_id";

    public static synchronized void delete(String str) {
        synchronized (StoryEditTable.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "StoryEditTable delete() -->> ");
            }
            try {
                DBHelperUtil.getDatabase().delete(TABLE_NAME, "story_edit_id = ?", new String[]{str});
                if (OKLog.D) {
                    OKLog.d(TAG, "StoryEditTable delete() -->> ok");
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0031, code lost:
        r5 = new com.jingdong.common.entity.StoryEditEntity();
        r5.setContent(r13.getString(r13.getColumnIndex("content")));
        r5.setRelatedCircleId(r13.getString(r13.getColumnIndex(com.jingdong.common.database.table.StoryEditTable.TB_COLUMN_CIRCLEID)));
        r5.setLastModify(com.jingdong.jdsdk.utils.FormatUtils.parseDate(r13.getString(r13.getColumnIndex("last_modify"))));
        r5.setStoryEditID(r13.getString(r13.getColumnIndex(com.jingdong.common.database.table.StoryEditTable.TB_COLUMN_STORY_EDIT_ID)));
        r6 = r13.getString(r13.getColumnIndex(com.jingdong.common.database.table.StoryEditTable.TB_COLUMN_SRORY_IMG_ENTITYS));
        r7 = new java.util.ArrayList();
        r7.addAll(java.util.Arrays.asList(r6.split(";")));
        r5.setStoryImgEntities(r7);
        r6 = r13.getString(r13.getColumnIndex(com.jingdong.common.database.table.StoryEditTable.TB_COLUMN_PRODUCT_IMG_ENTITYS));
        r7 = new java.util.ArrayList();
        r7.addAll(java.util.Arrays.asList(r6.split(";")));
        r5.setProductImgEntities(r7);
        r1.add(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x00af, code lost:
        if (r13.moveToNext() != false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x00b4, code lost:
        if (r13 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00c2, code lost:
        if (r13 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00c4, code lost:
        r13.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00c7, code lost:
        com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00ce, code lost:
        if (r1.size() <= 0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00d7, code lost:
        return (com.jingdong.common.entity.StoryEditEntity) r1.get(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:?, code lost:
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002f, code lost:
        if (r13.moveToFirst() != false) goto L10;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00dc  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.common.entity.StoryEditEntity getStoryEditEntity(java.lang.String r13) {
        /*
            java.lang.String r0 = ";"
            boolean r1 = com.jingdong.sdk.oklog.OKLog.D
            java.lang.String r2 = "StoryEditTable"
            if (r1 == 0) goto Ld
            java.lang.String r1 = "StoryEditTable getCommentEdit() -->> "
            com.jingdong.sdk.oklog.OKLog.d(r2, r1)
        Ld:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r3 = 0
            r4 = 0
            android.database.sqlite.SQLiteDatabase r5 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lb9
            java.lang.String r8 = "story_edit_id = ?"
            r6 = 1
            java.lang.String[] r9 = new java.lang.String[r6]     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lb9
            r9[r3] = r13     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lb9
            java.lang.String r6 = "story_edit"
            r7 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            android.database.Cursor r13 = r5.query(r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lb9
            if (r13 == 0) goto Lb4
            boolean r5 = r13.moveToFirst()     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            if (r5 == 0) goto Lb4
        L31:
            com.jingdong.common.entity.StoryEditEntity r5 = new com.jingdong.common.entity.StoryEditEntity     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            r5.<init>()     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String r6 = "content"
            int r6 = r13.getColumnIndex(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String r6 = r13.getString(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            r5.setContent(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String r6 = "relate_circle_d"
            int r6 = r13.getColumnIndex(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String r6 = r13.getString(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            r5.setRelatedCircleId(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String r6 = "last_modify"
            int r6 = r13.getColumnIndex(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String r6 = r13.getString(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.util.Date r6 = com.jingdong.jdsdk.utils.FormatUtils.parseDate(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            r5.setLastModify(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String r6 = "story_edit_id"
            int r6 = r13.getColumnIndex(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String r6 = r13.getString(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            r5.setStoryEditID(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String r6 = "story_img_entitys"
            int r6 = r13.getColumnIndex(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String r6 = r13.getString(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            r7.<init>()     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String[] r6 = r6.split(r0)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.util.List r6 = java.util.Arrays.asList(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            r7.addAll(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            r5.setStoryImgEntities(r7)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String r6 = "product_img_entitys"
            int r6 = r13.getColumnIndex(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String r6 = r13.getString(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            r7.<init>()     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.lang.String[] r6 = r6.split(r0)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            java.util.List r6 = java.util.Arrays.asList(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            r7.addAll(r6)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            r5.setProductImgEntities(r7)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            r1.add(r5)     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            boolean r5 = r13.moveToNext()     // Catch: java.lang.Exception -> Lb2 java.lang.Throwable -> Ld8
            if (r5 != 0) goto L31
            goto Lb4
        Lb2:
            r0 = move-exception
            goto Lbb
        Lb4:
            if (r13 == 0) goto Lc7
            goto Lc4
        Lb7:
            r0 = move-exception
            goto Lda
        Lb9:
            r0 = move-exception
            r13 = r4
        Lbb:
            boolean r5 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> Ld8
            if (r5 == 0) goto Lc2
            com.jingdong.sdk.oklog.OKLog.e(r2, r0)     // Catch: java.lang.Throwable -> Ld8
        Lc2:
            if (r13 == 0) goto Lc7
        Lc4:
            r13.close()
        Lc7:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            int r13 = r1.size()
            if (r13 <= 0) goto Ld7
            java.lang.Object r13 = r1.get(r3)
            r4 = r13
            com.jingdong.common.entity.StoryEditEntity r4 = (com.jingdong.common.entity.StoryEditEntity) r4
        Ld7:
            return r4
        Ld8:
            r0 = move-exception
            r4 = r13
        Lda:
            if (r4 == 0) goto Ldf
            r4.close()
        Ldf:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Le4
        Le3:
            throw r0
        Le4:
            goto Le3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.StoryEditTable.getStoryEditEntity(java.lang.String):com.jingdong.common.entity.StoryEditEntity");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d1 A[Catch: all -> 0x00d8, TRY_ENTER, TryCatch #2 {, blocks: (B:4:0x0003, B:6:0x0007, B:22:0x00ab, B:23:0x00ae, B:35:0x00c8, B:41:0x00d1, B:42:0x00d4, B:43:0x00d7), top: B:49:0x0003 }] */
    /* JADX WARN: Type inference failed for: r15v0, types: [com.jingdong.common.entity.StoryEditEntity] */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v6, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void insertOrUpdate(com.jingdong.common.entity.StoryEditEntity r15) {
        /*
            Method dump skipped, instructions count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.StoryEditTable.insertOrUpdate(com.jingdong.common.entity.StoryEditEntity):void");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS story_edit(id INTEGER PRIMARY KEY  NOT NULL ,story_edit_id TEXT,last_modify DATETIME DEFAULT CURRENT_TIMESTAMP,content TEXT,relate_circle_d TEXT,story_img_entitys TEXT,product_img_entitys TEXT)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (i2 < 25275) {
            sQLiteDatabase.execSQL("drop table if exists story_edit");
        }
    }
}
