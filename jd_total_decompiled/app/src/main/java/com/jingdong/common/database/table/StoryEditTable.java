package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.StoryEditEntity;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.jdsdk.utils.FormatUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

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
    */
    public static StoryEditEntity getStoryEditEntity(String str) {
        Cursor cursor;
        if (OKLog.D) {
            OKLog.d(TAG, "StoryEditTable getCommentEdit() -->> ");
        }
        ArrayList arrayList = new ArrayList();
        ?? r4 = 0;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, null, "story_edit_id = ?", new String[]{str}, null, null, null);
                if (cursor != null) {
                    try {
                    } catch (Exception e2) {
                        e = e2;
                        if (OKLog.E) {
                            OKLog.e(TAG, e);
                        }
                    }
                }
            } catch (Exception e3) {
                e = e3;
                cursor = null;
            } catch (Throwable th) {
                th = th;
                if (r4 != 0) {
                }
                DBHelperUtil.closeDatabase();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            r4 = str;
            if (r4 != 0) {
                r4.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d1 A[Catch: all -> 0x00d8, TRY_ENTER, TryCatch #2 {, blocks: (B:4:0x0003, B:6:0x0007, B:22:0x00ab, B:23:0x00ae, B:35:0x00c8, B:41:0x00d1, B:42:0x00d4, B:43:0x00d7), top: B:49:0x0003 }] */
    /* JADX WARN: Type inference failed for: r15v0, types: [com.jingdong.common.entity.StoryEditEntity] */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v6, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized void insertOrUpdate(StoryEditEntity storyEditEntity) {
        Throwable th;
        Exception e2;
        Cursor cursor;
        SQLiteDatabase database;
        ContentValues contentValues;
        String[] strArr;
        synchronized (StoryEditTable.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "StoryEditTable insertOrUpdate() -->> ");
            }
            try {
                try {
                    database = DBHelperUtil.getDatabase();
                    contentValues = new ContentValues();
                    contentValues.put(TB_COLUMN_STORY_EDIT_ID, storyEditEntity.getStoryEditID());
                    contentValues.put("content", storyEditEntity.getContent());
                    contentValues.put(TB_COLUMN_CIRCLEID, storyEditEntity.getRelatedCircleId());
                    contentValues.put(TB_COLUMN_SRORY_IMG_ENTITYS, storyEditEntity.getStoryImgEntitiesStr());
                    contentValues.put(TB_COLUMN_PRODUCT_IMG_ENTITYS, storyEditEntity.getProductImgEntitiesStr());
                    contentValues.put("last_modify", FormatUtils.formatDate(storyEditEntity.getLastModify()));
                    strArr = new String[]{storyEditEntity.getStoryEditID()};
                    cursor = database.query(TABLE_NAME, null, "story_edit_id = ?", strArr, null, null, null);
                } catch (Exception e3) {
                    e2 = e3;
                    cursor = null;
                } catch (Throwable th2) {
                    th = th2;
                    storyEditEntity = 0;
                    if (storyEditEntity != 0) {
                    }
                    DBHelperUtil.closeDatabase();
                    throw th;
                }
                try {
                    if (OKLog.D && cursor != null) {
                        OKLog.d(TAG, "StoryEditTable insertOrUpdate() -->> getCount \uff1a " + cursor.getCount());
                    }
                    if (cursor != null && cursor.getCount() > 0) {
                        database.update(TABLE_NAME, contentValues, "story_edit_id = ?", strArr);
                    } else {
                        database.insert(TABLE_NAME, null, contentValues);
                    }
                    if (OKLog.D) {
                        OKLog.d(TAG, "StoryEditTable insertOrUpdate() -->> ok");
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Exception e4) {
                    e2 = e4;
                    if (OKLog.E) {
                        OKLog.e(TAG, e2);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    DBHelperUtil.closeDatabase();
                }
                DBHelperUtil.closeDatabase();
            } catch (Throwable th3) {
                th = th3;
                if (storyEditEntity != 0) {
                    storyEditEntity.close();
                }
                DBHelperUtil.closeDatabase();
                throw th;
            }
        }
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
