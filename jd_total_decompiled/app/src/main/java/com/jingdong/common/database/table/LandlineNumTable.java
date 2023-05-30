package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jingdong.common.entity.incomingcall.InComStaff;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class LandlineNumTable implements IJdTable {
    public static final String TABLE_NAME = "landline_number_table";
    private static final String TAG = "LandlineNumTable";
    public static final String TB_COLUMN_AVATAR = "avatar";
    public static final String TB_COLUMN_ID = "_id";
    public static final String TB_COLUMN_MOBILE = "mobile";
    public static final String TB_COLUMN_NAME = "name";
    public static final String TB_COLUMN_TIP = "tip";
    public static final String TB_COLUMN_TYPE = "type";

    public static synchronized void addAllLandlineNum(List<InComStaff> list) {
        synchronized (LandlineNumTable.class) {
            if (list != null) {
                if (!list.isEmpty()) {
                    try {
                        DBHelperUtil.getDatabase().delete(TABLE_NAME, null, null);
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            addSingleLandlineNum(list.get(i2));
                        }
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e(TAG, e2);
                        }
                    }
                    DBHelperUtil.closeDatabase();
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x007b A[Catch: all -> 0x00a8, TRY_ENTER, TryCatch #2 {, blocks: (B:4:0x0003, B:14:0x007b, B:15:0x007e, B:27:0x0098, B:30:0x009f, B:31:0x00a2, B:32:0x00a5), top: B:40:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009f A[Catch: all -> 0x00a8, TryCatch #2 {, blocks: (B:4:0x0003, B:14:0x007b, B:15:0x007e, B:27:0x0098, B:30:0x009f, B:31:0x00a2, B:32:0x00a5), top: B:40:0x0003 }] */
    /* JADX WARN: Type inference failed for: r15v0, types: [com.jingdong.common.entity.incomingcall.InComStaff] */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v6, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized void addSingleLandlineNum(InComStaff inComStaff) {
        Throwable th;
        Exception e2;
        Cursor cursor;
        SQLiteDatabase database;
        ContentValues contentValues;
        String[] strArr;
        synchronized (LandlineNumTable.class) {
            if (!TextUtils.isEmpty(inComStaff.getNumber())) {
                try {
                    try {
                        database = DBHelperUtil.getDatabase();
                        contentValues = new ContentValues();
                        strArr = new String[]{inComStaff.getNumber()};
                        contentValues.put("name", inComStaff.getName());
                        contentValues.put("avatar", inComStaff.getPhoto());
                        contentValues.put("mobile", inComStaff.getNumber());
                        contentValues.put("tip", inComStaff.getText());
                        contentValues.put("type", Integer.valueOf(inComStaff.getType()));
                        cursor = database.query(TABLE_NAME, null, "mobile = ? ", strArr, null, null, null);
                    } catch (Exception e3) {
                        e2 = e3;
                        cursor = null;
                    } catch (Throwable th2) {
                        th = th2;
                        inComStaff = 0;
                        if (inComStaff != 0) {
                        }
                        DBHelperUtil.closeDatabase();
                        throw th;
                    }
                    if (cursor != null) {
                        try {
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
                        if (cursor.getCount() != 0) {
                            database.delete(TABLE_NAME, "mobile = ? ", strArr);
                            database.insert(TABLE_NAME, null, contentValues);
                            if (cursor != null) {
                                cursor.close();
                            }
                            DBHelperUtil.closeDatabase();
                        }
                    }
                    database.insert(TABLE_NAME, null, contentValues);
                    if (cursor != null) {
                    }
                    DBHelperUtil.closeDatabase();
                } catch (Throwable th3) {
                    th = th3;
                    if (inComStaff != 0) {
                        inComStaff.close();
                    }
                    DBHelperUtil.closeDatabase();
                    throw th;
                }
            }
        }
    }

    public static void deleteAllLandlineNum() {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TABLE_NAME, null, null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            if (OKLog.D) {
                OKLog.d(TAG, "delete all landlineNum");
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static List<InComStaff> getLandlineNum() {
        try {
            return getLandlineNum(DBHelperUtil.getDatabase());
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return null;
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0093 A[Catch: all -> 0x009a, TRY_ENTER, TryCatch #4 {, blocks: (B:13:0x0062, B:14:0x0065, B:20:0x006e, B:21:0x0071, B:32:0x0088, B:33:0x008b, B:38:0x0093, B:39:0x0096, B:40:0x0099), top: B:47:0x0004 }] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v5, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized InComStaff getStaff(String str) {
        Throwable th;
        Cursor cursor;
        synchronized (LandlineNumTable.class) {
            try {
                try {
                    cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, null, "mobile = ? ", new String[]{str}, null, null, null);
                    if (cursor != null) {
                        try {
                            if (cursor.getCount() >= 1 && cursor.moveToNext()) {
                                InComStaff inComStaff = new InComStaff(cursor.getString(cursor.getColumnIndex("mobile")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("avatar")), cursor.getString(cursor.getColumnIndex("tip")), cursor.getInt(cursor.getColumnIndex("type")));
                                if (cursor != null) {
                                    cursor.close();
                                }
                                DBHelperUtil.closeDatabase();
                                return inComStaff;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            if (OKLog.E) {
                                OKLog.e(TAG, e);
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            DBHelperUtil.closeDatabase();
                            return null;
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    DBHelperUtil.closeDatabase();
                    return null;
                } catch (Exception e3) {
                    e = e3;
                    cursor = null;
                } catch (Throwable th2) {
                    th = th2;
                    str = 0;
                    if (str != 0) {
                    }
                    DBHelperUtil.closeDatabase();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (str != 0) {
                    str.close();
                }
                DBHelperUtil.closeDatabase();
                throw th;
            }
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase == null) {
            return;
        }
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS landline_number_table('_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,mobile TEXT,type INTEGER,name TEXT,avatar TEXT,tip TEXT)");
        if (OKLog.D) {
            OKLog.d(TAG, "landline_number_tableis created suc");
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0058, code lost:
        if (r10 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x005a, code lost:
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0077, code lost:
        if (r10 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x007a, code lost:
        return r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static List<InComStaff> getLandlineNum(SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        Cursor cursor;
        ArrayList arrayList;
        Exception e2;
        if (sQLiteDatabase == null) {
            return null;
        }
        try {
            cursor = sQLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
            try {
                try {
                    arrayList = new ArrayList();
                    while (cursor.moveToNext()) {
                        try {
                            arrayList.add(new InComStaff(cursor.getString(cursor.getColumnIndex("mobile")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("avatar")), cursor.getString(cursor.getColumnIndex("tip")), cursor.getInt(cursor.getColumnIndex("type"))));
                        } catch (Exception e3) {
                            e2 = e3;
                            if (OKLog.E) {
                                OKLog.e(TAG, e2);
                            }
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Exception e4) {
                arrayList = null;
                e2 = e4;
            }
        } catch (Exception e5) {
            arrayList = null;
            e2 = e5;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
    }
}
