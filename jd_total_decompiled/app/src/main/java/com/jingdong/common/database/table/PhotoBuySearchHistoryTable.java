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
    */
    public static ArrayList<PhotobuyRecord> getPhotobuyRecordList() {
        ArrayList<PhotobuyRecord> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, new String[]{"id", "image_url", TB_COLUMN_IS_FROM_ALBUM, TB_COLUMN_MAIN_BODY_RECTANGLE, "create_time"}, null, null, null, null, "create_time");
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
                return arrayList;
            }
            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                for (int count = cursor.getCount() - 1; count >= 0; count--) {
                    cursor.moveToPosition(count);
                    PhotobuyRecord photobuyRecord = new PhotobuyRecord();
                    photobuyRecord.imageUrl = cursor.getString(cursor.getColumnIndex("image_url"));
                    photobuyRecord.isFromAlbum = cursor.getInt(cursor.getColumnIndex(TB_COLUMN_IS_FROM_ALBUM));
                    photobuyRecord.mainBodyRectangle = cursor.getString(cursor.getColumnIndex(TB_COLUMN_MAIN_BODY_RECTANGLE));
                    photobuyRecord.timeStamp = cursor.getString(cursor.getColumnIndex("create_time"));
                    arrayList.add(photobuyRecord);
                }
            }
            if (cursor != null) {
            }
            DBHelperUtil.closeDatabase();
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
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
