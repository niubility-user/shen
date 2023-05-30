package com.jingdong.common.apkcenter;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ApkDownloadTable implements IJdTable {
    public static final String FIELD_BUNDLE_VERSION_CODE = "bundle_version_code";
    public static final String FIELD_CURRENT_SIZE = "current_size";
    public static final String FIELD_DOWNLOAD_TYPE = "download_type";
    public static final String FIELD_DOWNLOAD_URL = "download_url";
    public static final String FIELD_FILE_NAME = "file_name";
    public static final String FIELD_HOST_VERSION_CODE = "host_version_code";
    public static final String FIELD_HOST_VERSION_NAME = "host_version_name";
    public static final String FIELD_ID = "id";
    public static final String FIELD_LOCAL_PATH = "local_path";
    public static final String FIELD_MD5 = "md5";
    public static final String FIELD_SIZE = "size";
    public static final String TABLE_NAME = "apk_download";
    private static final String TAG = "ApkDownloadTable";

    public static boolean checkSameData(List<ApkResult> list) {
        List<ApkResult> queryApks = queryApks();
        if (queryApks == null || list == null || queryApks.size() != list.size()) {
            return false;
        }
        HashMap hashMap = new HashMap();
        for (ApkResult apkResult : queryApks) {
            hashMap.put(apkResult.id, apkResult);
        }
        for (ApkResult apkResult2 : list) {
            if (!hashMap.containsKey(apkResult2.id) || !apkResult2.equals((ApkResult) hashMap.get(apkResult2.id))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002a, code lost:
        if (r5 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002c, code lost:
        com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002f, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
        if (r5 != null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean deleteById(java.lang.String r5) {
        /*
            java.lang.String r0 = "id=?"
            r1 = 1
            java.lang.String[] r2 = new java.lang.String[r1]
            r3 = 0
            r2[r3] = r5
            r5 = 0
            android.database.sqlite.SQLiteDatabase r5 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            java.lang.String r4 = "apk_download"
            int r0 = r5.delete(r4, r0, r2)     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            if (r0 <= 0) goto L1b
            if (r5 == 0) goto L1a
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
        L1a:
            return r1
        L1b:
            if (r5 == 0) goto L2f
            goto L2c
        L1e:
            r0 = move-exception
            goto L30
        L20:
            r0 = move-exception
            boolean r1 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L1e
            if (r1 == 0) goto L2a
            java.lang.String r1 = "ApkDownloadTable"
            com.jingdong.sdk.oklog.OKLog.e(r1, r0)     // Catch: java.lang.Throwable -> L1e
        L2a:
            if (r5 == 0) goto L2f
        L2c:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
        L2f:
            return r3
        L30:
            if (r5 == 0) goto L35
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
        L35:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.apkcenter.ApkDownloadTable.deleteById(java.lang.String):boolean");
    }

    public static void deleteWithoutThisClientVersion() {
        String versionName = PackageInfoUtil.getVersionName();
        if (TextUtils.isEmpty(versionName)) {
            return;
        }
        try {
            DBHelperUtil.getDatabase().delete(TABLE_NAME, "host_version_name!=?", new String[]{versionName});
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean insertData(ApkResult apkResult) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("md5", apkResult.md5);
        contentValues.put(FIELD_HOST_VERSION_CODE, apkResult.hostVersionCode);
        contentValues.put(FIELD_BUNDLE_VERSION_CODE, Integer.valueOf(apkResult.bundleVersionCode));
        contentValues.put(FIELD_FILE_NAME, apkResult.fileName);
        contentValues.put("id", apkResult.id);
        contentValues.put(FIELD_SIZE, Integer.valueOf(apkResult.size));
        contentValues.put(FIELD_CURRENT_SIZE, Integer.valueOf(apkResult.currentSize));
        contentValues.put(FIELD_LOCAL_PATH, apkResult.localPath);
        contentValues.put(FIELD_DOWNLOAD_URL, apkResult.downloadUrl);
        contentValues.put(FIELD_DOWNLOAD_TYPE, Integer.valueOf(apkResult.downloadType));
        contentValues.put(FIELD_HOST_VERSION_NAME, apkResult.hostVersionName);
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                try {
                    if (database.insert(TABLE_NAME, null, contentValues) > 0) {
                        if (database != null) {
                            DBHelperUtil.closeDatabase();
                        }
                        return true;
                    }
                    if (database != null) {
                        DBHelperUtil.closeDatabase();
                    }
                    return false;
                } catch (Exception e2) {
                    e = e2;
                    sQLiteDatabase = database;
                    if (OKLog.E) {
                        OKLog.e(TAG, e);
                    }
                    if (sQLiteDatabase != null) {
                        DBHelperUtil.closeDatabase();
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabase = database;
                    if (sQLiteDatabase != null) {
                        DBHelperUtil.closeDatabase();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static boolean insertDatas(List<ApkResult> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                try {
                    database.beginTransaction();
                    database.delete(TABLE_NAME, null, null);
                    Iterator<ApkResult> it = list.iterator();
                    while (it.hasNext()) {
                        insertData(it.next(), database);
                    }
                    database.setTransactionSuccessful();
                    if (database != null) {
                        database.endTransaction();
                        DBHelperUtil.closeDatabase();
                        return true;
                    }
                    return true;
                } catch (Exception e2) {
                    e = e2;
                    sQLiteDatabase = database;
                    if (OKLog.E) {
                        OKLog.e(TAG, e);
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                        DBHelperUtil.closeDatabase();
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabase = database;
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                        DBHelperUtil.closeDatabase();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00ec, code lost:
        if (r21 != 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00ee, code lost:
        com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x011c, code lost:
        if (r11 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x011f, code lost:
        return r13;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0131  */
    /* JADX WARN: Type inference failed for: r21v0 */
    /* JADX WARN: Type inference failed for: r21v1 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r21v4, types: [android.database.sqlite.SQLiteDatabase] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.jingdong.common.apkcenter.ApkResult> queryApks() {
        /*
            Method dump skipped, instructions count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.apkcenter.ApkDownloadTable.queryApks():java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00f3, code lost:
        if (r21 != 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00f5, code lost:
        com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0123, code lost:
        if (r11 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0126, code lost:
        return r13;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0138  */
    /* JADX WARN: Type inference failed for: r21v0 */
    /* JADX WARN: Type inference failed for: r21v1 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r21v4, types: [android.database.sqlite.SQLiteDatabase] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.jingdong.common.apkcenter.ApkResult> quryApkById(java.lang.String r23) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.apkcenter.ApkDownloadTable.quryApkById(java.lang.String):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean updateByMd5(String str, ContentValues contentValues) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = DBHelperUtil.getDatabase();
                if (sQLiteDatabase.update(TABLE_NAME, contentValues, "md5=?", new String[]{str}) > 0) {
                    if (sQLiteDatabase != null) {
                        DBHelperUtil.closeDatabase();
                    }
                    return true;
                }
                if (sQLiteDatabase != null) {
                    DBHelperUtil.closeDatabase();
                }
                return false;
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (sQLiteDatabase != null) {
                    DBHelperUtil.closeDatabase();
                }
                return false;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                DBHelperUtil.closeDatabase();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean updateCurrentSizeByMd5(String str, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_CURRENT_SIZE, Integer.valueOf(i2));
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = DBHelperUtil.getDatabase();
                if (sQLiteDatabase.update(TABLE_NAME, contentValues, "md5=?", new String[]{str}) > 0) {
                    if (sQLiteDatabase != null) {
                        DBHelperUtil.closeDatabase();
                    }
                    return true;
                }
                if (sQLiteDatabase != null) {
                    DBHelperUtil.closeDatabase();
                }
                return false;
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (sQLiteDatabase != null) {
                    DBHelperUtil.closeDatabase();
                }
                return false;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                DBHelperUtil.closeDatabase();
            }
            throw th;
        }
    }

    public static boolean updateLocalPathByMd5(String str, ApkResult apkResult) {
        if (apkResult == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_LOCAL_PATH, apkResult.localPath);
        contentValues.put(FIELD_FILE_NAME, apkResult.fileName);
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = DBHelperUtil.getDatabase();
                if (sQLiteDatabase.update(TABLE_NAME, contentValues, "md5=?", new String[]{str}) > 0) {
                    if (sQLiteDatabase != null) {
                        DBHelperUtil.closeDatabase();
                    }
                    return true;
                }
                if (sQLiteDatabase != null) {
                    DBHelperUtil.closeDatabase();
                }
                return false;
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
                if (sQLiteDatabase != null) {
                    DBHelperUtil.closeDatabase();
                }
                return false;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                DBHelperUtil.closeDatabase();
            }
            throw th;
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE apk_download(md5 TEXT PRIMARY KEY  NOT NULL,host_version_code TEXT,bundle_version_code TEXT,file_name TEXT,id TEXT,size INTEGER,current_size INTEGER,local_path TEXT,download_url TEXT,download_type INTEGER,host_version_name TEXT)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists apk_download");
    }

    private static boolean insertData(ApkResult apkResult, SQLiteDatabase sQLiteDatabase) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("md5", apkResult.md5);
        contentValues.put(FIELD_HOST_VERSION_CODE, apkResult.hostVersionCode);
        contentValues.put(FIELD_BUNDLE_VERSION_CODE, Integer.valueOf(apkResult.bundleVersionCode));
        contentValues.put(FIELD_FILE_NAME, apkResult.fileName);
        contentValues.put("id", apkResult.id);
        contentValues.put(FIELD_SIZE, Integer.valueOf(apkResult.size));
        contentValues.put(FIELD_CURRENT_SIZE, Integer.valueOf(apkResult.currentSize));
        contentValues.put(FIELD_LOCAL_PATH, apkResult.localPath);
        contentValues.put(FIELD_DOWNLOAD_URL, apkResult.downloadUrl);
        contentValues.put(FIELD_DOWNLOAD_TYPE, Integer.valueOf(apkResult.downloadType));
        contentValues.put(FIELD_HOST_VERSION_NAME, apkResult.hostVersionName);
        return sQLiteDatabase.insert(TABLE_NAME, null, contentValues) > 0;
    }
}
