package com.jingdong.jdsdk.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Iterator;

/* loaded from: classes14.dex */
public class DBHelperUtil {
    private static final String DB_NAME = "jd.db";
    private static final String TAG = "DBHelperUtil";
    private static a mOpenHelper = null;
    private static int versionCode = 25289;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes14.dex */
    public static class a extends SQLiteOpenHelper {
        public a(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i2) {
            super(context, str, cursorFactory, i2);
        }

        private IJdTable f(String str) {
            try {
                return (IJdTable) Class.forName(str).newInstance();
            } catch (ClassNotFoundException e2) {
                OKLog.e(DBHelperUtil.TAG, e2);
                return null;
            } catch (IllegalAccessException e3) {
                OKLog.e(DBHelperUtil.TAG, e3);
                return null;
            } catch (InstantiationException e4) {
                OKLog.e(DBHelperUtil.TAG, e4);
                return null;
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Iterator<String> it = com.jingdong.jdsdk.db.a.a().b().iterator();
            while (it.hasNext()) {
                IJdTable f2 = f(it.next());
                if (f2 != null) {
                    f2.create(sQLiteDatabase);
                }
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
            if (i2 < i3) {
                if (OKLog.I) {
                    OKLog.i("onUpgrade", "++++++++++oldVersion:" + i2 + "newVersion:" + i3);
                }
                Iterator<String> it = com.jingdong.jdsdk.db.a.a().b().iterator();
                while (it.hasNext()) {
                    IJdTable f2 = f(it.next());
                    if (f2 != null) {
                        f2.upgrade(sQLiteDatabase, i2, i3);
                    }
                }
                onCreate(sQLiteDatabase);
            }
        }
    }

    public static void closeDatabase() {
    }

    public static synchronized SQLiteDatabase getDatabase() throws Exception {
        SQLiteDatabase writableDatabase;
        synchronized (DBHelperUtil.class) {
            if (mOpenHelper == null) {
                mOpenHelper = new a(JdSdk.getInstance().getApplicationContext(), DB_NAME, null, versionCode);
            }
            try {
                writableDatabase = mOpenHelper.getWritableDatabase();
                if (OKLog.D) {
                    OKLog.d("Temp", "writableDatabase 1 -->> " + writableDatabase);
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
                JdSdk.getInstance().getApplicationContext().deleteDatabase(DB_NAME);
                SQLiteDatabase writableDatabase2 = mOpenHelper.getWritableDatabase();
                if (OKLog.D) {
                    OKLog.d("Temp", "writableDatabase 2 -->> " + writableDatabase2);
                }
                return writableDatabase2;
            }
        }
        return writableDatabase;
    }
}
