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
public class MobileNumTable implements IJdTable {
    public static final String TABLE_NAME = "mobile_number_table";
    private static final String TAG = "MobileNumTable";
    public static final String TB_COLUMN_AVATAR = "avatar";
    public static final String TB_COLUMN_ID = "_id";
    public static final String TB_COLUMN_MOBILE = "mobile";
    public static final String TB_COLUMN_NAME = "name";
    public static final String TB_COLUMN_PIN = "pin";
    public static final String TB_COLUMN_TIP = "tip";

    public static synchronized void addAllMobileNum(List<InComStaff> list, String str) {
        synchronized (MobileNumTable.class) {
            if (list != null) {
                if (!list.isEmpty()) {
                    try {
                        deleteAllMobileNum(str);
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            addSingleMobileNum(list.get(i2), str);
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

    /* JADX WARN: Removed duplicated region for block: B:14:0x0076 A[Catch: all -> 0x00a0, TRY_ENTER, TryCatch #1 {, blocks: (B:4:0x0003, B:14:0x0076, B:15:0x0079, B:30:0x0097, B:31:0x009a, B:32:0x009d, B:28:0x0091), top: B:40:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized void addSingleMobileNum(InComStaff inComStaff, String str) {
        SQLiteDatabase database;
        ContentValues contentValues;
        String[] strArr;
        Cursor query;
        synchronized (MobileNumTable.class) {
            if (!TextUtils.isEmpty(inComStaff.getNumber())) {
                Cursor cursor = null;
                try {
                    try {
                        database = DBHelperUtil.getDatabase();
                        contentValues = new ContentValues();
                        strArr = new String[]{str, inComStaff.getNumber()};
                        contentValues.put("name", inComStaff.getName());
                        contentValues.put("avatar", inComStaff.getPhoto());
                        contentValues.put("mobile", inComStaff.getNumber());
                        contentValues.put("tip", inComStaff.getText());
                        contentValues.put("pin", str);
                        query = database.query(TABLE_NAME, null, "pin = ? and mobile = ? ", strArr, null, null, null);
                    } catch (Exception e2) {
                        e = e2;
                    }
                    if (query != null) {
                        try {
                        } catch (Exception e3) {
                            e = e3;
                            cursor = query;
                            if (OKLog.E) {
                                OKLog.e(TAG, e);
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            DBHelperUtil.closeDatabase();
                        } catch (Throwable th) {
                            th = th;
                            cursor = query;
                            if (cursor != null) {
                                cursor.close();
                            }
                            DBHelperUtil.closeDatabase();
                            throw th;
                        }
                        if (query.getCount() != 0) {
                            database.delete(TABLE_NAME, "pin = ? and mobile = ? ", strArr);
                            database.insert(TABLE_NAME, null, contentValues);
                            if (query != null) {
                                query.close();
                            }
                            DBHelperUtil.closeDatabase();
                        }
                    }
                    database.insert(TABLE_NAME, null, contentValues);
                    if (query != null) {
                    }
                    DBHelperUtil.closeDatabase();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
    }

    public static void deleteAllMobileNum(String str) {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TABLE_NAME, "pin = ? ", new String[]{str});
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            DBHelperUtil.closeDatabase();
            if (OKLog.D) {
                OKLog.d(TAG, "delete all mobile num");
            }
        } catch (Throwable th) {
            DBHelperUtil.closeDatabase();
            throw th;
        }
    }

    public static List<InComStaff> getMobileNum(String str) {
        try {
            return getMobileNum(DBHelperUtil.getDatabase(), str);
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
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.database.Cursor] */
    public static synchronized InComStaff getStaff(String str, String str2) {
        Cursor cursor;
        synchronized (MobileNumTable.class) {
            ?? r1 = 0;
            try {
                try {
                    cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, null, "pin = ? and mobile = ? ", new String[]{str2, str}, null, null, null);
                    if (cursor != null) {
                        try {
                            if (cursor.getCount() >= 1 && cursor.moveToNext()) {
                                InComStaff inComStaff = new InComStaff(cursor.getString(cursor.getColumnIndex("mobile")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("avatar")), cursor.getString(cursor.getColumnIndex("tip")));
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
                } catch (Throwable th) {
                    th = th;
                    if (r1 != 0) {
                        r1.close();
                    }
                    DBHelperUtil.closeDatabase();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                r1 = str;
            }
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase == null) {
            return;
        }
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS mobile_number_table('_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,pin TEXT,mobile TEXT,name TEXT,avatar TEXT,tip TEXT)");
        if (OKLog.D) {
            OKLog.d(TAG, "mobile_number_tabletable is created suc");
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (sQLiteDatabase == null) {
            return;
        }
        sQLiteDatabase.execSQL("drop table if exists mobile_number_table");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0053, code lost:
        if (r10 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0055, code lost:
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006f, code lost:
        if (r10 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0072, code lost:
        return r11;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static List<InComStaff> getMobileNum(SQLiteDatabase sQLiteDatabase, String str) {
        ArrayList arrayList;
        Exception e2;
        Cursor cursor;
        ?? r0 = 0;
        try {
            if (sQLiteDatabase == null) {
                return null;
            }
            try {
                cursor = sQLiteDatabase.query(TABLE_NAME, null, "pin = ? ", new String[]{str}, null, null, null);
                try {
                    arrayList = new ArrayList();
                    while (cursor.moveToNext()) {
                        try {
                            arrayList.add(new InComStaff(cursor.getString(cursor.getColumnIndex("mobile")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("avatar")), cursor.getString(cursor.getColumnIndex("tip"))));
                        } catch (Exception e3) {
                            e2 = e3;
                            if (OKLog.E) {
                                OKLog.e(TAG, e2);
                            }
                        }
                    }
                } catch (Exception e4) {
                    e2 = e4;
                    arrayList = null;
                }
            } catch (Exception e5) {
                arrayList = null;
                e2 = e5;
                cursor = null;
            } catch (Throwable th) {
                th = th;
                if (r0 != 0) {
                    r0.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            r0 = sQLiteDatabase;
        }
    }
}
