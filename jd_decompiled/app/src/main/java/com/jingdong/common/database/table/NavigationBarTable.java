package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.navigationbar.NavigationBar;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class NavigationBarTable implements IJdTable {
    public static final String FIELD_BIG_ICON_TAG = "big_icon_tag";
    public static final String FIELD_DATA_VERSION = "data_version";
    public static final String FIELD_DEFAULT_TAG = "default_tag";
    public static final String FIELD_DISPLAY_TAG = "display_tag";
    public static final String FIELD_END_TIME = "end_time";
    public static final String FIELD_FUNCTION_ID = "function_id";
    public static final String FIELD_ICON_TYPE = "icon_type";
    public static final String FIELD_ID = "id";
    public static final String FIELD_M_URL = "m_url";
    public static final String FIELD_NAVI_LABEL = "navi_label";
    public static final String FIELD_NAVI_ORDER = "navi_order";
    public static final String FIELD_NAVI_TASK = "navi_task";
    public static final String FIELD_OFF_PATH = "off_path";
    public static final String FIELD_OFF_URL = "off_url";
    public static final String FIELD_ON_PATH = "on_path";
    public static final String FIELD_ON_URL = "on_url";
    public static final String FIELD_START_TIME = "start_time";
    public static final String NAVIGATION_ICON_TABLE = "navigation_bar";
    public static final String TAG = "NavigationBarTable";
    public static final int TAG_NO = 0;
    public static final int TAG_YES = 1;

    /* JADX WARN: Code restructure failed: missing block: B:36:0x006e, code lost:
        if (r4 != null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00a7, code lost:
        if (r4 != null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a9, code lost:
        r4.endTransaction();
        com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase();
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00af, code lost:
        return false;
     */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean checkDataIntegrity() {
        /*
            java.lang.String r0 = "select count(*) from navigation_bar where  (off_path is null or off_path ='') or (on_path is null or on_path='') and display_tag=0 and icon_type=0"
            java.lang.String r1 = "select count(*) from navigation_bar where  (off_path is null or off_path ='') and display_tag=0 and icon_type=1"
            r2 = 0
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L85
            r4.beginTransaction()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7e
            java.lang.String[] r5 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7e
            android.database.Cursor r0 = r4.rawQuery(r0, r5)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7e
            java.lang.String[] r5 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L76
            android.database.Cursor r2 = r4.rawQuery(r1, r5)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L76
            boolean r1 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L76
            if (r1 == 0) goto L24
            int r1 = r0.getInt(r3)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L76
            goto L25
        L24:
            r1 = 0
        L25:
            boolean r5 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L76
            if (r5 == 0) goto L30
            int r5 = r2.getInt(r3)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L76
            goto L31
        L30:
            r5 = 0
        L31:
            r4.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L76
            if (r1 != 0) goto L58
            if (r5 != 0) goto L58
            r1 = 1
            if (r0 == 0) goto L44
            boolean r3 = r0.isClosed()
            if (r3 != 0) goto L44
            r0.close()
        L44:
            if (r2 == 0) goto L4f
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L4f
            r2.close()
        L4f:
            if (r4 == 0) goto L57
            r4.endTransaction()
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
        L57:
            return r1
        L58:
            if (r0 == 0) goto L63
            boolean r1 = r0.isClosed()
            if (r1 != 0) goto L63
            r0.close()
        L63:
            if (r2 == 0) goto L6e
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L6e
            r2.close()
        L6e:
            if (r4 == 0) goto Laf
            goto La9
        L71:
            r1 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto Lb1
        L76:
            r1 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L88
        L7b:
            r1 = move-exception
            r0 = r2
            goto Lb1
        L7e:
            r1 = move-exception
            r0 = r2
            goto L88
        L81:
            r1 = move-exception
            r0 = r2
            r4 = r0
            goto Lb1
        L85:
            r1 = move-exception
            r0 = r2
            r4 = r0
        L88:
            boolean r5 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> Lb0
            if (r5 == 0) goto L91
            java.lang.String r5 = com.jingdong.common.database.table.NavigationBarTable.TAG     // Catch: java.lang.Throwable -> Lb0
            com.jingdong.sdk.oklog.OKLog.e(r5, r1)     // Catch: java.lang.Throwable -> Lb0
        L91:
            if (r2 == 0) goto L9c
            boolean r1 = r2.isClosed()
            if (r1 != 0) goto L9c
            r2.close()
        L9c:
            if (r0 == 0) goto La7
            boolean r1 = r0.isClosed()
            if (r1 != 0) goto La7
            r0.close()
        La7:
            if (r4 == 0) goto Laf
        La9:
            r4.endTransaction()
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
        Laf:
            return r3
        Lb0:
            r1 = move-exception
        Lb1:
            if (r2 == 0) goto Lbc
            boolean r3 = r2.isClosed()
            if (r3 != 0) goto Lbc
            r2.close()
        Lbc:
            if (r0 == 0) goto Lc7
            boolean r2 = r0.isClosed()
            if (r2 != 0) goto Lc7
            r0.close()
        Lc7:
            if (r4 == 0) goto Lcf
            r4.endTransaction()
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
        Lcf:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.NavigationBarTable.checkDataIntegrity():boolean");
    }

    public static void deleteAllData() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = DBHelperUtil.getDatabase();
                sQLiteDatabase.execSQL("DELETE FROM navigation_bar");
                if (sQLiteDatabase == null) {
                    return;
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    OKLog.e(TAG, e2);
                }
                if (sQLiteDatabase == null) {
                    return;
                }
            }
            DBHelperUtil.closeDatabase();
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                DBHelperUtil.closeDatabase();
            }
            throw th;
        }
    }

    public static void deleteByDisPlayTag(SQLiteDatabase sQLiteDatabase, int i2) {
        sQLiteDatabase.execSQL("DELETE FROM navigation_bar WHERE display_tag=" + i2);
    }

    private static boolean insertData(SQLiteDatabase sQLiteDatabase, NavigationBar navigationBar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_DEFAULT_TAG, Integer.valueOf(navigationBar.defaultTag));
        contentValues.put(FIELD_DISPLAY_TAG, Integer.valueOf(navigationBar.displayTag));
        contentValues.put("function_id", navigationBar.functionId);
        contentValues.put(FIELD_OFF_PATH, navigationBar.offPath);
        contentValues.put(FIELD_ON_PATH, navigationBar.onPath);
        contentValues.put(FIELD_OFF_URL, navigationBar.offUrl);
        contentValues.put(FIELD_ON_URL, navigationBar.onUrl);
        contentValues.put(FIELD_NAVI_LABEL, navigationBar.naviLabel);
        contentValues.put(FIELD_NAVI_ORDER, navigationBar.naviOrder);
        contentValues.put(FIELD_NAVI_TASK, Integer.valueOf(navigationBar.naviTask));
        contentValues.put(FIELD_START_TIME, navigationBar.startTime);
        contentValues.put(FIELD_END_TIME, navigationBar.endTime);
        contentValues.put(FIELD_M_URL, navigationBar.mUrl);
        contentValues.put(FIELD_BIG_ICON_TAG, Integer.valueOf(navigationBar.bigIconTag));
        contentValues.put(FIELD_ICON_TYPE, Integer.valueOf(navigationBar.iconType));
        contentValues.put(FIELD_DATA_VERSION, navigationBar.dataVersion);
        return sQLiteDatabase.insert(NAVIGATION_ICON_TABLE, null, contentValues) > 0;
    }

    public static boolean insertDatas(List<NavigationBar> list) {
        if (list == null) {
            return false;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = DBHelperUtil.getDatabase();
                sQLiteDatabase.beginTransaction();
                deleteByDisPlayTag(sQLiteDatabase, 0);
                Iterator<NavigationBar> it = list.iterator();
                while (it.hasNext()) {
                    insertData(sQLiteDatabase, it.next());
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                    DBHelperUtil.closeDatabase();
                }
                return true;
            } catch (Exception e2) {
                if (OKLog.D) {
                    OKLog.e(TAG, e2);
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                    DBHelperUtil.closeDatabase();
                }
                return false;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
                DBHelperUtil.closeDatabase();
            }
            throw th;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:10:0x008c
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static java.util.List<com.jingdong.common.entity.navigationbar.NavigationBar> queryDisplayOrNot(int r27, int r28) {
        /*
            Method dump skipped, instructions count: 501
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.NavigationBarTable.queryDisplayOrNot(int, int):java.util.List");
    }

    public static boolean updateByDisplayTag() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = DBHelperUtil.getDatabase();
                sQLiteDatabase.beginTransaction();
                deleteByDisPlayTag(sQLiteDatabase, 1);
                sQLiteDatabase.execSQL("UPDATE navigation_bar SET display_tag=1 WHERE display_tag=0");
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                    DBHelperUtil.closeDatabase();
                }
                return true;
            } catch (Exception e2) {
                if (OKLog.D) {
                    OKLog.e(TAG, e2);
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                    DBHelperUtil.closeDatabase();
                    return false;
                }
                return false;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
                DBHelperUtil.closeDatabase();
            }
            throw th;
        }
    }

    public static boolean updatePath(int i2, String str, String str2) {
        if (i2 < 0 || str2 == null || "".equals(str2)) {
            return false;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = DBHelperUtil.getDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(str2, str);
                boolean z = sQLiteDatabase.update(NAVIGATION_ICON_TABLE, contentValues, "id=?", new String[]{String.valueOf(i2)}) > 0;
                if (sQLiteDatabase != null) {
                    DBHelperUtil.closeDatabase();
                }
                return z;
            } catch (Exception e2) {
                if (OKLog.D) {
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
        sQLiteDatabase.execSQL("CREATE TABLE navigation_bar(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,off_url TEXT,on_url TEXT,off_path TEXT,on_path TEXT,function_id TEXT,navi_label TEXT,navi_order INTEGER,navi_task INTEGER,default_tag INTEGER,display_tag INTEGER,icon_type INTEGER,start_time TEXT,end_time TEXT,data_version TEXT,m_url TEXT ,big_icon_tag INTEGER)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists navigation_bar");
    }
}
