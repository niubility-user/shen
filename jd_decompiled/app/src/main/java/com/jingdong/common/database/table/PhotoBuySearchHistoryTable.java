package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.PhotobuyRecord;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes5.dex */
public class PhotoBuySearchHistoryTable implements IJdTable {
    public static final int MAX_HISTORY_NUM = 20;
    public static final String TABLE_NAME = "photobuy_search_history";
    private static final String TAG = "PhotoBuySearchHistoryTa";
    public static final String TB_COLUMN_CREATE_TIME = "create_time";
    public static final String TB_COLUMN_IMAGE_URL = "image_url";
    public static final String TB_COLUMN_IS_FROM_ALBUM = "is_from_album";
    public static final String TB_COLUMN_MAIN_BODY_RECTANGLE = "main_body_rectangle";
    public static ArrayList<PhotobuyRecord> presentRecords = new ArrayList<>();

    public static void delAllPhotobuyRecord() {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TABLE_NAME, "1=1", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static void delPhotobuyRecord(SQLiteDatabase sQLiteDatabase, PhotobuyRecord photobuyRecord) {
        try {
            sQLiteDatabase.delete(TABLE_NAME, "create_time=?", new String[]{photobuyRecord.timeStamp});
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x008f, code lost:
        if (r5.isClosed() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a4, code lost:
        if (r5.isClosed() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a6, code lost:
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.PhotobuyRecord> getPhotobuyRecordList() {
        /*
            java.lang.String r0 = "create_time"
            java.lang.String r1 = "main_body_rectangle"
            java.lang.String r2 = "is_from_album"
            java.lang.String r3 = "image_url"
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r5 = 0
            android.database.sqlite.SQLiteDatabase r6 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r7 = 5
            java.lang.String[] r8 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r7 = 0
            java.lang.String r9 = "id"
            r8[r7] = r9     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r14 = 1
            r8[r14] = r3     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r7 = 2
            r8[r7] = r2     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r7 = 3
            r8[r7] = r1     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r7 = 4
            r8[r7] = r0     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            java.lang.String r7 = "photobuy_search_history"
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            java.lang.String r13 = "create_time"
            android.database.Cursor r5 = r6.query(r7, r8, r9, r10, r11, r12, r13)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            if (r5 != 0) goto L43
            if (r5 == 0) goto L3f
            boolean r0 = r5.isClosed()
            if (r0 != 0) goto L3f
            r5.close()
        L3f:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r4
        L43:
            r5.moveToFirst()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            int r6 = r5.getCount()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            if (r6 == 0) goto L89
            int r6 = r5.getCount()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            int r6 = r6 - r14
        L51:
            if (r6 < 0) goto L89
            r5.moveToPosition(r6)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            com.jingdong.common.entity.PhotobuyRecord r7 = new com.jingdong.common.entity.PhotobuyRecord     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r7.<init>()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            int r8 = r5.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            java.lang.String r8 = r5.getString(r8)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r7.imageUrl = r8     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            int r8 = r5.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            int r8 = r5.getInt(r8)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r7.isFromAlbum = r8     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            int r8 = r5.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            java.lang.String r8 = r5.getString(r8)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r7.mainBodyRectangle = r8     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            int r8 = r5.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            java.lang.String r8 = r5.getString(r8)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r7.timeStamp = r8     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            r4.add(r7)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L94
            int r6 = r6 + (-1)
            goto L51
        L89:
            if (r5 == 0) goto La9
            boolean r0 = r5.isClosed()
            if (r0 != 0) goto La9
            goto La6
        L92:
            r0 = move-exception
            goto Lad
        L94:
            r0 = move-exception
            boolean r1 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L92
            if (r1 == 0) goto L9e
            java.lang.String r1 = "PhotoBuySearchHistoryTa"
            com.jingdong.sdk.oklog.OKLog.e(r1, r0)     // Catch: java.lang.Throwable -> L92
        L9e:
            if (r5 == 0) goto La9
            boolean r0 = r5.isClosed()
            if (r0 != 0) goto La9
        La6:
            r5.close()
        La9:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r4
        Lad:
            if (r5 == 0) goto Lb8
            boolean r1 = r5.isClosed()
            if (r1 != 0) goto Lb8
            r5.close()
        Lb8:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Lbd
        Lbc:
            throw r0
        Lbd:
            goto Lbc
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.PhotoBuySearchHistoryTable.getPhotobuyRecordList():java.util.ArrayList");
    }

    public static synchronized void insertOrUpdatePhotobuyRecord(PhotobuyRecord photobuyRecord) {
        synchronized (PhotoBuySearchHistoryTable.class) {
            Cursor cursor = null;
            try {
                try {
                    SQLiteDatabase database = DBHelperUtil.getDatabase();
                    ArrayList<PhotobuyRecord> photobuyRecordList = getPhotobuyRecordList();
                    presentRecords = photobuyRecordList;
                    if (photobuyRecordList.size() >= 20) {
                        ArrayList<PhotobuyRecord> arrayList = presentRecords;
                        delPhotobuyRecord(database, arrayList.get(arrayList.size() - 1));
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("image_url", photobuyRecord.imageUrl);
                    contentValues.put(TB_COLUMN_IS_FROM_ALBUM, Integer.valueOf(photobuyRecord.isFromAlbum));
                    contentValues.put(TB_COLUMN_MAIN_BODY_RECTANGLE, photobuyRecord.mainBodyRectangle);
                    String[] strArr = {photobuyRecord.imageUrl};
                    Cursor query = database.query(TABLE_NAME, null, "image_url=?", strArr, null, null, null);
                    try {
                        contentValues.put("create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        if (query != null && query.getCount() > 0) {
                            database.update(TABLE_NAME, contentValues, "image_url=?", strArr);
                        } else {
                            database.insert(TABLE_NAME, null, contentValues);
                        }
                        if (query != null && !query.isClosed()) {
                            query.close();
                        }
                    } catch (Exception e2) {
                        cursor = query;
                        e = e2;
                        if (OKLog.E) {
                            OKLog.e(TAG, e);
                        }
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        DBHelperUtil.closeDatabase();
                    } catch (Throwable th) {
                        cursor = query;
                        th = th;
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        DBHelperUtil.closeDatabase();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
            DBHelperUtil.closeDatabase();
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS photobuy_search_history('id' INTEGER PRIMARY KEY  NOT NULL ,image_url TEXT,is_from_album INTEGER,main_body_rectangle TEXT,create_time DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }
}
