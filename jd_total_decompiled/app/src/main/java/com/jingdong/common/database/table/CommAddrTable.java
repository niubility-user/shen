package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.entity.CommAddr;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

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
    */
    public static ArrayList<CommAddr> getCommAddrList() {
        ArrayList<CommAddr> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().query("comm_addr", new String[]{"user_name", "mobile", "province", "city", "area", "swhere", "zip", "mail", "provincecode", "citycode", "areacode"}, null, null, null, null, null);
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
                int count = cursor.getCount();
                int i2 = 0;
                while (i2 < count) {
                    cursor.moveToPosition(i2);
                    CommAddr commAddr = new CommAddr();
                    int i3 = count;
                    commAddr.sUser_name = cursor.getString(cursor.getColumnIndex("user_name"));
                    commAddr.sMobile = cursor.getString(cursor.getColumnIndex("mobile"));
                    commAddr.sProvince = cursor.getString(cursor.getColumnIndex("province"));
                    commAddr.sCity = cursor.getString(cursor.getColumnIndex("city"));
                    commAddr.sArea = cursor.getString(cursor.getColumnIndex("area"));
                    commAddr.sWhere = cursor.getString(cursor.getColumnIndex("swhere"));
                    commAddr.sZip = cursor.getString(cursor.getColumnIndex("zip"));
                    commAddr.sMail = cursor.getString(cursor.getColumnIndex("mail"));
                    commAddr.province_code = cursor.getInt(cursor.getColumnIndex("provincecode"));
                    commAddr.city_code = cursor.getInt(cursor.getColumnIndex("citycode"));
                    commAddr.area_code = cursor.getInt(cursor.getColumnIndex("areacode"));
                    arrayList.add(commAddr);
                    i2++;
                    count = i3;
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
