package com.jingdong.jdsdk.network.db.entry;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.jdsdk.utils.FormatUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes14.dex */
public class CacheFileTable implements IJdTable {
    public static final String TABLE_NAME = "cache_file";
    private static final String TAG = "CacheFileTable";
    public static final String TB_COLUMN_BUSINESS_ID = "bussiness_id";
    public static final String TB_COLUMN_CLEAN_TIME = "clean_time";
    public static final String TB_COLUMN_DIR_PATH = "dir_path";
    public static final String TB_COLUMN_DIR_SPACE = "dir_space";
    public static final String TB_COLUMN_FIRST_NAME = "first_name";
    public static final String TB_COLUMN_LAST_NAME = "last_name";

    public static void clearCacheFiles() {
        FileService.Directory directory;
        ArrayList<CacheFile> listByClean = getListByClean();
        boolean externalMemoryAvailable = FileService.externalMemoryAvailable();
        for (int i2 = 0; i2 < listByClean.size(); i2++) {
            CacheFile cacheFile = listByClean.get(i2);
            if (cacheFile != null && (directory = cacheFile.getDirectory()) != null && (directory.getSpace() == 1 || (directory.getSpace() == 2 && externalMemoryAvailable))) {
                boolean delete = cacheFile.getFile().delete();
                if (OKLog.D) {
                    OKLog.d(TAG, "cacheFile.getName() -->> " + cacheFile.getName());
                }
                if (delete) {
                    delete(cacheFile);
                }
            }
        }
    }

    public static synchronized void delete(CacheFile cacheFile) {
        synchronized (CacheFileTable.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "CacheFileTable delete() -->> ");
            }
            try {
                DBHelperUtil.getDatabase().delete(TABLE_NAME, "first_name = ? AND last_name = ?", new String[]{cacheFile.firstName, cacheFile.lastName});
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

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0058, code lost:
        r8 = new com.jingdong.jdsdk.network.db.entry.CacheFile();
        r8.firstName = r7.getString(r7.getColumnIndex(com.jingdong.jdsdk.network.db.entry.CacheFileTable.TB_COLUMN_FIRST_NAME));
        r8.lastName = r7.getString(r7.getColumnIndex(com.jingdong.jdsdk.network.db.entry.CacheFileTable.TB_COLUMN_LAST_NAME));
        r8.setCleanTime(com.jingdong.jdsdk.utils.FormatUtils.parseDate(r7.getString(r7.getColumnIndex(com.jingdong.jdsdk.network.db.entry.CacheFileTable.TB_COLUMN_CLEAN_TIME))));
        r8.setDirectory(new com.jingdong.jdsdk.network.toolbox.FileService.Directory(r7.getString(r7.getColumnIndex(com.jingdong.jdsdk.network.db.entry.CacheFileTable.TB_COLUMN_DIR_PATH)), r7.getInt(r7.getColumnIndex(com.jingdong.jdsdk.network.db.entry.CacheFileTable.TB_COLUMN_DIR_SPACE))));
        r5.add(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x009f, code lost:
        if (r7.moveToNext() != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00b0, code lost:
        if (r7.isClosed() == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00c3, code lost:
        if (r7.isClosed() == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00c5, code lost:
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0056, code lost:
        if (r7.moveToFirst() != false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<CacheFile> getListByClean() {
        if (OKLog.D) {
            OKLog.d(TAG, "CacheFileTable getListByClean() -->> ");
        }
        ArrayList<CacheFile> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, new String[]{TB_COLUMN_FIRST_NAME, TB_COLUMN_LAST_NAME, TB_COLUMN_CLEAN_TIME, TB_COLUMN_DIR_PATH, TB_COLUMN_DIR_SPACE}, "clean_time < ?", new String[]{FormatUtils.formatDate(new Date())}, null, null, null);
                if (cursor != null) {
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "CacheFileTable getListByClean() -->> ok");
                }
                if (cursor != null) {
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (cursor != null) {
                }
            }
            DBHelperUtil.closeDatabase();
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    public static synchronized void insertOrUpdate(CacheFile cacheFile) {
        synchronized (CacheFileTable.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "CacheFileTable insertOrUpdate() -->> ");
            }
            Cursor cursor = null;
            try {
                try {
                    SQLiteDatabase database = DBHelperUtil.getDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(TB_COLUMN_FIRST_NAME, cacheFile.firstName);
                    contentValues.put(TB_COLUMN_LAST_NAME, cacheFile.lastName);
                    contentValues.put(TB_COLUMN_BUSINESS_ID, Integer.valueOf(cacheFile.getBussinessId()));
                    FileService.Directory directory = cacheFile.getDirectory();
                    contentValues.put(TB_COLUMN_DIR_PATH, directory.getPath());
                    contentValues.put(TB_COLUMN_DIR_SPACE, Integer.valueOf(directory.getSpace()));
                    contentValues.put(TB_COLUMN_CLEAN_TIME, FormatUtils.formatDate(cacheFile.getCleanTime()));
                    if (OKLog.D) {
                        OKLog.d(TAG, "CacheFileTable insertOrUpdate() -->> getBussinessId \uff1a " + cacheFile.getBussinessId());
                        OKLog.d(TAG, "CacheFileTable insertOrUpdate() -->> getFirstName \uff1a " + cacheFile.firstName);
                        OKLog.d(TAG, "CacheFileTable insertOrUpdate() -->> getCleanTime \uff1a " + FormatUtils.formatDate(cacheFile.getCleanTime()));
                    }
                    String[] strArr = {cacheFile.firstName, cacheFile.lastName};
                    Cursor query = database.query(TABLE_NAME, null, "first_name = ? AND last_name = ?", strArr, null, null, null);
                    try {
                        if (OKLog.D && query != null) {
                            OKLog.d(TAG, "CacheFileTable insertOrUpdate() -->> getCount \uff1a " + query.getCount());
                        }
                        if (query != null && query.getCount() > 0) {
                            database.update(TABLE_NAME, contentValues, "first_name = ? AND last_name = ?", strArr);
                        } else {
                            database.insert(TABLE_NAME, null, contentValues);
                        }
                        if (OKLog.D) {
                            OKLog.d(TAG, "CacheFileTable insertOrUpdate() -->> ok");
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
                } catch (Exception e3) {
                    e = e3;
                }
                DBHelperUtil.closeDatabase();
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00fc, code lost:
        if (r0.isClosed() == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x010f, code lost:
        if (r0.isClosed() == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0111, code lost:
        r0.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean isExpired(CacheFile cacheFile) {
        if (OKLog.D) {
            OKLog.d(TAG, "CacheFileTable isExpired() -->> ");
        }
        Cursor cursor = null;
        boolean z = true;
        try {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                if (OKLog.D) {
                    OKLog.d(TAG, "CacheFileTable isExpired() -->> getBussinessId : " + cacheFile.getBussinessId());
                }
                cursor = database.query(TABLE_NAME, null, "first_name = ? AND last_name = ?", new String[]{cacheFile.firstName, cacheFile.lastName}, null, null, null);
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "CacheFileTable isExpired() in -->> getBussinessId : " + cacheFile.getBussinessId());
                        OKLog.d(TAG, "CacheFileTable isExpired() in -->> getFirstName : " + cacheFile.firstName);
                        OKLog.d(TAG, "CacheFileTable isExpired() in -->> getLastName : " + cacheFile.lastName);
                    }
                    long time = FormatUtils.parseDate(cursor.getString(cursor.getColumnIndex(TB_COLUMN_CLEAN_TIME))).getTime();
                    long time2 = new Date().getTime();
                    if (OKLog.D) {
                        OKLog.d(TAG, " isExpired -->> fileDateTime : " + time + " , nowDateTime : " + time2);
                        StringBuilder sb = new StringBuilder();
                        sb.append(" isExpired -->> fileDateTime > nowDateTime : ");
                        sb.append(time > time2);
                        OKLog.d(TAG, sb.toString());
                    }
                    if (time > time2) {
                        z = false;
                    }
                }
                if (cursor != null) {
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (cursor != null) {
                }
            }
            DBHelperUtil.closeDatabase();
            if (OKLog.D) {
                OKLog.d(TAG, "CacheFileTable isExpired() -->> " + z);
            }
            return z;
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    public static void updateAllCacheTime(ArrayList<CacheFile> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, " updateAllCacheTime -->> ");
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            updateCacheTime(arrayList.get(i2));
        }
    }

    public static synchronized void updateCacheTime(CacheFile cacheFile) {
        synchronized (CacheFileTable.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "CacheFileTable updateCacheTime() -->> ");
            }
            if (cacheFile == null) {
                return;
            }
            Cursor cursor = null;
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                String[] strArr = {cacheFile.getBussinessId() + ""};
                cursor = database.query(TABLE_NAME, null, "bussiness_id = ?  ", strArr, null, null, null);
                ContentValues contentValues = new ContentValues();
                contentValues.put(TB_COLUMN_CLEAN_TIME, FormatUtils.formatDate(cacheFile.getCleanTime()));
                if (cursor != null && cursor.getCount() > 0) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "CacheFileTable updateCacheTime() -->> in cacheFile.getBussinessId() : " + cacheFile.getBussinessId());
                        OKLog.d(TAG, "CacheFileTable updateCacheTime() -->> in cacheFile.getCleanTime() : " + FormatUtils.formatDate(cacheFile.getCleanTime()));
                    }
                    int update = database.update(TABLE_NAME, contentValues, "bussiness_id = ?  ", strArr);
                    if (OKLog.D) {
                        OKLog.d(TAG, "CacheFileTable updateCacheTime() -->> in update : " + update);
                    }
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "CacheFileTable updateCacheTime() -->> ok");
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
            DBHelperUtil.closeDatabase();
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE cache_file('id' INTEGER PRIMARY KEY  NOT NULL ,first_name TEXT,last_name TEXT,clean_time DATETIME DEFAULT CURRENT_TIMESTAMP,dir_path TEXT,dir_space INTEGER,bussiness_id TEXT)");
        sQLiteDatabase.execSQL("CREATE INDEX clean_time_index ON cache_file(clean_time)");
        sQLiteDatabase.execSQL("CREATE INDEX name_index ON cache_file(first_name, last_name)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop index if exists clean_time_index");
        sQLiteDatabase.execSQL("drop index if exists name_index");
        sQLiteDatabase.execSQL("drop table if exists cache_file");
    }
}
