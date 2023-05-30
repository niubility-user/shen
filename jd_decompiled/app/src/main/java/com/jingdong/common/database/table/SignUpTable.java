package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jingdong.common.entity.SignUpInfo;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class SignUpTable implements IJdTable {
    public static final String TABLE_NAME = "sign_up_table";
    private static final String TAG = "sign_up_table";
    public static final String TB_COLUMN_ADDR = "addr";
    public static final String TB_COLUMN_DEFINE_1 = "define_1";
    public static final String TB_COLUMN_DEFINE_2 = "define_2";
    public static final String TB_COLUMN_DEFINE_3 = "define_3";
    public static final String TB_COLUMN_ID = "_id";
    public static final String TB_COLUMN_NAME = "name";
    public static final String TB_COLUMN_PHONE = "phone";
    public static final String TB_COLUMN_PIN = "pin";
    public static final String TB_COLUMN_QQ = "qq";
    public static final String TB_COLUMN_REMARK = "remark";

    private static void addSignUpInfo(SQLiteDatabase sQLiteDatabase, SignUpInfo signUpInfo) {
        if (signUpInfo == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d("sign_up_table", "addSignUpInfo signUpInfo.pin -->> " + signUpInfo.pin);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("pin", signUpInfo.pin);
        contentValues.put("name", signUpInfo.name);
        contentValues.put(TB_COLUMN_PHONE, signUpInfo.phone);
        contentValues.put(TB_COLUMN_QQ, signUpInfo.qq);
        contentValues.put(TB_COLUMN_ADDR, signUpInfo.addr);
        contentValues.put(TB_COLUMN_REMARK, signUpInfo.remark);
        sQLiteDatabase.insert("sign_up_table", null, contentValues);
    }

    public static void deleteSignUpInfo(SQLiteDatabase sQLiteDatabase, int i2) {
        if (OKLog.D) {
            OKLog.d("sign_up_table", "deleteSignUpInfo id -->>  " + i2);
        }
        sQLiteDatabase.delete("sign_up_table", "_id = ?", new String[]{i2 + ""});
    }

    public static SignUpInfo getSignUpInfoFromTable(String str) {
        if (OKLog.D) {
            OKLog.d("sign_up_table", "getSignUpInfoFromTable(pin) pin -->>  " + str);
        }
        try {
            return getSignUpInfoFromTable(DBHelperUtil.getDatabase(), str);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("sign_up_table", e2);
                return null;
            }
            return null;
        }
    }

    public static void saveSignUpInfo(SignUpInfo signUpInfo) {
        if (OKLog.D) {
            OKLog.d("sign_up_table", "SignUpTable saveSignUpInfo() -->> ");
        }
        if (signUpInfo == null || TextUtils.isEmpty(signUpInfo.pin)) {
            return;
        }
        try {
            SQLiteDatabase database = DBHelperUtil.getDatabase();
            try {
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("pin", signUpInfo.pin);
                    contentValues.put("name", signUpInfo.name);
                    contentValues.put(TB_COLUMN_PHONE, signUpInfo.phone);
                    contentValues.put(TB_COLUMN_QQ, signUpInfo.qq);
                    contentValues.put(TB_COLUMN_ADDR, signUpInfo.addr);
                    contentValues.put(TB_COLUMN_REMARK, signUpInfo.remark);
                    database.replace("sign_up_table", null, contentValues);
                } finally {
                    DBHelperUtil.closeDatabase();
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("sign_up_table", e2);
                }
            }
        } catch (Exception e3) {
            if (OKLog.E) {
                OKLog.e("sign_up_table", e3);
            }
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS sign_up_table('_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,pin TEXT,name TEXT,phone TEXT,qq TEXT,addr TEXT,remark TEXT,define_1 TEXT,define_2 TEXT,define_3 TEXT)");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS pin_index ON sign_up_table (pin)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x00d1, code lost:
        if (r9.isClosed() == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00e8, code lost:
        if (r9.isClosed() == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ea, code lost:
        r9.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00df A[Catch: all -> 0x00ee, TRY_LEAVE, TryCatch #4 {all -> 0x00ee, blocks: (B:13:0x006c, B:15:0x0072, B:34:0x00db, B:36:0x00df), top: B:52:0x006c }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.jingdong.common.entity.SignUpInfo getSignUpInfoFromTable(android.database.sqlite.SQLiteDatabase r18, java.lang.String r19) {
        /*
            Method dump skipped, instructions count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.SignUpTable.getSignUpInfoFromTable(android.database.sqlite.SQLiteDatabase, java.lang.String):com.jingdong.common.entity.SignUpInfo");
    }
}
