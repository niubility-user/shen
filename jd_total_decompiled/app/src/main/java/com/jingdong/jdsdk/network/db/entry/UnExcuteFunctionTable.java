package com.jingdong.jdsdk.network.db.entry;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

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
    */
    public static ArrayList<UnExcuteFunction> getAllUnExcuteFuntionWithFuntionId(String str) {
        Cursor cursor;
        ArrayList<UnExcuteFunction> arrayList = new ArrayList<>();
        try {
            Cursor query = DBHelperUtil.getDatabase().query(TB_UN_EXCUTE_FUCTION_TABLE, new String[]{"_id", "function_id", TB_CLOUMN_IF_NOTIFY_USER, TB_CLOUMN_IF_NEED_LOADING_MODEL, TB_CLOUMN_JSON_PARAMS, TB_CLOUMN_CALL_BACK, "host", "md5"}, "md5 = ? ", new String[]{str}, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    try {
                        arrayList.add(new UnExcuteFunction(query.getInt(0), query.getString(1), query.getInt(2), query.getInt(3), query.getString(4), query.getString(5), query.getString(6), query.getString(7)));
                    } catch (Exception e2) {
                        e = e2;
                        cursor = query;
                        try {
                            if (OKLog.E) {
                                OKLog.e(TAG, e);
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            DBHelperUtil.closeDatabase();
                            return arrayList;
                        } catch (Throwable th) {
                            th = th;
                            if (cursor != null) {
                                cursor.close();
                            }
                            DBHelperUtil.closeDatabase();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        if (cursor != null) {
                        }
                        DBHelperUtil.closeDatabase();
                        throw th;
                    }
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
        DBHelperUtil.closeDatabase();
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<UnExcuteFunction> getUnExcuteFunctionList() {
        Cursor cursor;
        ArrayList<UnExcuteFunction> arrayList = new ArrayList<>();
        try {
            Cursor query = DBHelperUtil.getDatabase().query(TB_UN_EXCUTE_FUCTION_TABLE, new String[]{"_id", "function_id", TB_CLOUMN_IF_NOTIFY_USER, TB_CLOUMN_IF_NEED_LOADING_MODEL, TB_CLOUMN_JSON_PARAMS, TB_CLOUMN_CALL_BACK, "host", "md5"}, null, null, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    try {
                        arrayList.add(new UnExcuteFunction(query.getInt(0), query.getString(1), query.getInt(2), query.getInt(3), query.getString(4), query.getString(5), query.getString(6), query.getString(7)));
                    } catch (Exception e2) {
                        e = e2;
                        cursor = query;
                        try {
                            if (OKLog.E) {
                                OKLog.e(TAG, e);
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            DBHelperUtil.closeDatabase();
                            return arrayList;
                        } catch (Throwable th) {
                            th = th;
                            if (cursor != null) {
                                cursor.close();
                            }
                            DBHelperUtil.closeDatabase();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        if (cursor != null) {
                        }
                        DBHelperUtil.closeDatabase();
                        throw th;
                    }
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
        DBHelperUtil.closeDatabase();
        return arrayList;
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
