package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class CommAddrTable implements IJdTable {
    public static final String COMMON_ADDR_TABLE_NAME = "comm_addr";
    private static final String TAG = "CommAddrTable";
    public static final String TB_COLUMN_AREA = "area";
    public static final String TB_COLUMN_AREA_CODE = "areacode";
    public static final String TB_COLUMN_CITY = "city";
    public static final String TB_COLUMN_CITY_CODE = "citycode";
    public static final String TB_COLUMN_MAIL = "mail";
    public static final String TB_COLUMN_MOBILE = "mobile";
    public static final String TB_COLUMN_PROVINCE = "province";
    public static final String TB_COLUMN_PROVINCE_CODE = "provincecode";
    public static final String TB_COLUMN_USER_NAME = "user_name";
    public static final String TB_COLUMN_WHERE = "swhere";
    public static final String TB_COLUMN_ZIP = "zip";

    public static void delCommAddr(int i2) {
        try {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                new ContentValues().put("id", Integer.valueOf(i2));
                database.delete("comm_addr", "id =?", new String[]{i2 + ""});
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0101, code lost:
        if (r12.isClosed() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0116, code lost:
        if (r12.isClosed() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0118, code lost:
        r12.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.CommAddr> getCommAddrList() {
        /*
            Method dump skipped, instructions count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.CommAddrTable.getCommAddrList():java.util.ArrayList");
    }

    public static void insertCommAddr(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, int i3, int i4) {
        try {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("user_name", str);
                contentValues.put("mobile", str2);
                contentValues.put("province", str3);
                contentValues.put("city", str4);
                contentValues.put("area", str5);
                contentValues.put("swhere", str6);
                contentValues.put("zip", str7);
                contentValues.put("mail", str8);
                contentValues.put("provincecode", Integer.valueOf(i2));
                contentValues.put("citycode", Integer.valueOf(i3));
                contentValues.put("areacode", Integer.valueOf(i4));
                database.insert("comm_addr", null, contentValues);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE comm_addr('id' INTEGER PRIMARY KEY  NOT NULL ,user_name TEXT,mobile TEXT,province TEXT,city TEXT,area TEXT,swhere TEXT,zip TEXT,mail TEXT,provincecode INTEGER,citycode INTEGER,areacode INTEGER)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists comm_addr");
    }
}
