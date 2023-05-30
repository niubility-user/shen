package com.jingdong.jdsdk.network.db.entry;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes14.dex */
public class UnExcuteFunctionTable implements IJdTable {
    private static final String TAG = "UnExcuteFunctionTable";
    public static final String TB_CLOUMN_CALL_BACK = "call_back";
    public static final String TB_CLOUMN_FUNCTION_ID = "function_id";
    public static final String TB_CLOUMN_HOST = "host";
    public static final String TB_CLOUMN_IF_NEED_LOADING_MODEL = "if_need_loading_model";
    public static final String TB_CLOUMN_IF_NOTIFY_USER = "if_notify_user";
    public static final String TB_CLOUMN_JSON_PARAMS = "function_json_params";
    public static final String TB_CLOUMN_MD5 = "md5";
    public static final String TB_UN_EXCUTE_FUCTION_TABLE = "un_excute_function_table";

    public static void deleteUnExcuteFunction(int i2) {
        try {
            try {
                DBHelperUtil.getDatabase().delete(TB_UN_EXCUTE_FUCTION_TABLE, "_id = ?", new String[]{i2 + ""});
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    public static void deleteUnExcuteFunctionWithFuctionMd5(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            try {
                DBHelperUtil.getDatabase().delete(TB_UN_EXCUTE_FUCTION_TABLE, "md5 = ?", new String[]{str});
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.jdsdk.network.db.entry.UnExcuteFunction> getAllUnExcuteFuntionWithFuntionId(java.lang.String r26) {
        /*
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.database.sqlite.SQLiteDatabase r3 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            java.lang.String r4 = "un_excute_function_table"
            r0 = 8
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            java.lang.String r0 = "_id"
            r11 = 0
            r5[r11] = r0     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            java.lang.String r0 = "function_id"
            r12 = 1
            r5[r12] = r0     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            java.lang.String r0 = "if_notify_user"
            r13 = 2
            r5[r13] = r0     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            java.lang.String r0 = "if_need_loading_model"
            r14 = 3
            r5[r14] = r0     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            java.lang.String r0 = "function_json_params"
            r15 = 4
            r5[r15] = r0     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            java.lang.String r0 = "call_back"
            r10 = 5
            r5[r10] = r0     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            java.lang.String r0 = "host"
            r9 = 6
            r5[r9] = r0     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            java.lang.String r0 = "md5"
            r8 = 7
            r5[r8] = r0     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            java.lang.String r6 = "md5 = ? "
            java.lang.String[] r7 = new java.lang.String[r12]     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            r7[r11] = r26     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            r0 = 0
            r16 = 0
            r17 = 0
            r2 = 7
            r8 = r0
            r0 = 6
            r9 = r16
            r2 = 5
            r10 = r17
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            if (r3 == 0) goto L88
        L50:
            boolean r4 = r3.moveToNext()     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
            if (r4 == 0) goto L88
            com.jingdong.jdsdk.network.db.entry.UnExcuteFunction r4 = new com.jingdong.jdsdk.network.db.entry.UnExcuteFunction     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
            int r18 = r3.getInt(r11)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
            java.lang.String r19 = r3.getString(r12)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
            int r20 = r3.getInt(r13)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
            int r21 = r3.getInt(r14)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
            java.lang.String r22 = r3.getString(r15)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
            java.lang.String r23 = r3.getString(r2)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
            java.lang.String r24 = r3.getString(r0)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
            r5 = 7
            java.lang.String r25 = r3.getString(r5)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
            r17 = r4
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
            r1.add(r4)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
            goto L50
        L82:
            r0 = move-exception
            r2 = r3
            goto La6
        L85:
            r0 = move-exception
            r2 = r3
            goto L93
        L88:
            if (r3 == 0) goto La1
            r3.close()
            goto La1
        L8e:
            r0 = move-exception
            r2 = 0
            goto La6
        L91:
            r0 = move-exception
            r2 = 0
        L93:
            boolean r3 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> La5
            if (r3 == 0) goto L9c
            java.lang.String r3 = "UnExcuteFunctionTable"
            com.jingdong.sdk.oklog.OKLog.e(r3, r0)     // Catch: java.lang.Throwable -> La5
        L9c:
            if (r2 == 0) goto La1
            r2.close()
        La1:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r1
        La5:
            r0 = move-exception
        La6:
            if (r2 == 0) goto Lab
            r2.close()
        Lab:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Lb0
        Laf:
            throw r0
        Lb0:
            goto Laf
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdsdk.network.db.entry.UnExcuteFunctionTable.getAllUnExcuteFuntionWithFuntionId(java.lang.String):java.util.ArrayList");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.jdsdk.network.db.entry.UnExcuteFunction> getUnExcuteFunctionList() {
        /*
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.database.sqlite.SQLiteDatabase r3 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            java.lang.String r4 = "un_excute_function_table"
            r0 = 8
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            java.lang.String r0 = "_id"
            r11 = 0
            r5[r11] = r0     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            java.lang.String r0 = "function_id"
            r12 = 1
            r5[r12] = r0     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            java.lang.String r0 = "if_notify_user"
            r13 = 2
            r5[r13] = r0     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            java.lang.String r0 = "if_need_loading_model"
            r14 = 3
            r5[r14] = r0     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            java.lang.String r0 = "function_json_params"
            r15 = 4
            r5[r15] = r0     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            java.lang.String r0 = "call_back"
            r10 = 5
            r5[r10] = r0     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            java.lang.String r0 = "host"
            r9 = 6
            r5[r9] = r0     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            java.lang.String r0 = "md5"
            r8 = 7
            r5[r8] = r0     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            r6 = 0
            r7 = 0
            r0 = 0
            r16 = 0
            r17 = 0
            r2 = 7
            r8 = r0
            r0 = 6
            r9 = r16
            r2 = 5
            r10 = r17
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            if (r3 == 0) goto L84
        L4c:
            boolean r4 = r3.moveToNext()     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            if (r4 == 0) goto L84
            com.jingdong.jdsdk.network.db.entry.UnExcuteFunction r4 = new com.jingdong.jdsdk.network.db.entry.UnExcuteFunction     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            int r18 = r3.getInt(r11)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            java.lang.String r19 = r3.getString(r12)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            int r20 = r3.getInt(r13)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            int r21 = r3.getInt(r14)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            java.lang.String r22 = r3.getString(r15)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            java.lang.String r23 = r3.getString(r2)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            java.lang.String r24 = r3.getString(r0)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            r5 = 7
            java.lang.String r25 = r3.getString(r5)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            r17 = r4
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            r1.add(r4)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L81
            goto L4c
        L7e:
            r0 = move-exception
            r2 = r3
            goto La2
        L81:
            r0 = move-exception
            r2 = r3
            goto L8f
        L84:
            if (r3 == 0) goto L9d
            r3.close()
            goto L9d
        L8a:
            r0 = move-exception
            r2 = 0
            goto La2
        L8d:
            r0 = move-exception
            r2 = 0
        L8f:
            boolean r3 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> La1
            if (r3 == 0) goto L98
            java.lang.String r3 = "UnExcuteFunctionTable"
            com.jingdong.sdk.oklog.OKLog.e(r3, r0)     // Catch: java.lang.Throwable -> La1
        L98:
            if (r2 == 0) goto L9d
            r2.close()
        L9d:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r1
        La1:
            r0 = move-exception
        La2:
            if (r2 == 0) goto La7
            r2.close()
        La7:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto Lac
        Lab:
            throw r0
        Lac:
            goto Lab
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdsdk.network.db.entry.UnExcuteFunctionTable.getUnExcuteFunctionList():java.util.ArrayList");
    }

    public static void saveUnExcuteFunction(UnExcuteFunction unExcuteFunction) {
        if (unExcuteFunction == null) {
            return;
        }
        deleteUnExcuteFunctionWithFuctionMd5(unExcuteFunction.getMd5());
        try {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(TB_CLOUMN_CALL_BACK, unExcuteFunction.getCallBack());
                contentValues.put("function_id", unExcuteFunction.getFunctionId());
                contentValues.put(TB_CLOUMN_JSON_PARAMS, unExcuteFunction.getJsonParams());
                contentValues.put(TB_CLOUMN_IF_NEED_LOADING_MODEL, Boolean.valueOf(unExcuteFunction.isIfNeedLodingModel()));
                contentValues.put(TB_CLOUMN_IF_NOTIFY_USER, Boolean.valueOf(unExcuteFunction.isIfNeedNotifyUser()));
                contentValues.put("host", unExcuteFunction.getHost());
                contentValues.put("md5", unExcuteFunction.getMd5());
                database.insert(TB_UN_EXCUTE_FUCTION_TABLE, null, contentValues);
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
        sQLiteDatabase.execSQL("CREATE TABLE un_excute_function_table('_id' INTEGER PRIMARY KEY  NOT NULL ,function_id TEXT,if_notify_user BOOLEAN,if_need_loading_model BOOLEAN,function_json_params TEXT,call_back TEXT,md5 TEXT,host TEXT) ");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists un_excute_function_table");
    }
}
