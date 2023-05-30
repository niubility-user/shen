package com.jingdong.jdma.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.jdma.common.utils.LogUtil;
import com.jingdong.jdma.common.utils.c;
import com.jingdong.jdma.common.utils.i;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes12.dex */
public class a extends SQLiteOpenHelper {

    /* renamed from: c  reason: collision with root package name */
    private static a f12627c;
    private String a;
    private Context b;

    private a(Context context) {
        super(context, "jd_reportStatExp.db", (SQLiteDatabase.CursorFactory) null, 11);
        this.b = context;
        try {
            File cacheDir = context.getCacheDir();
            cacheDir = cacheDir == null ? this.b.getExternalCacheDir() : cacheDir;
            if (cacheDir != null) {
                this.a = cacheDir.getAbsolutePath().concat(File.separator).concat("empty");
            }
            c.f12681k = false;
            if (i.a(this.b)) {
                com.jingdong.jdma.common.utils.a.b(this.a);
            }
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 28) {
                setOpenParams(new SQLiteDatabase.OpenParams.Builder().addOpenFlags(16).build());
            }
            if (i2 >= 16) {
                setWriteAheadLoggingEnabled(true);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static a a(Context context) {
        if (f12627c == null) {
            synchronized (a.class) {
                if (f12627c == null) {
                    f12627c = new a(context);
                }
            }
        }
        return f12627c;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x008a A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean b(String str, String str2, String str3) {
        long j2;
        if (a()) {
            if (LogUtil.isDebug()) {
                LogUtil.e("JDMASDK", String.format("'%s' database file already deleted, can't store any data!", "jd_reportStatExp.db"));
            }
            return false;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("data", str2);
            if (!TextUtils.isEmpty(str3)) {
                contentValues.put("log_type", str3);
            }
            j2 = getWritableDatabase().insert(str, null, contentValues);
        } catch (Throwable th) {
            th = th;
            j2 = -1;
        }
        try {
            if (LogUtil.isDebug()) {
                Object[] objArr = new Object[3];
                objArr[0] = str;
                objArr[1] = j2 > 0 ? "successfully" : "failure";
                objArr[2] = str2;
                LogUtil.d("DBManager", String.format("insert data into table %s %s, data = %s", objArr));
            }
        } catch (Throwable th2) {
            th = th2;
            if (com.jingdong.jdma.f.c.d) {
                c("dbInsertError", str, th.getMessage());
            }
            if (j2 == -1) {
            }
        }
        return j2 == -1;
    }

    private void c(String str, String str2, String str3) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("commonNum", c.f12674c + "");
        hashMap.put("quickNum", c.d + "");
        hashMap.put("dauNum", c.f12675e + "");
        hashMap.put("failureNum", c.f12677g + "");
        hashMap.put("tableName", str2);
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("errorDes", str3);
        }
        com.jingdong.jdma.f.c.a().b(str, hashMap);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE statistic (_id INTEGER PRIMARY KEY, time TEXT, data TEXT);");
            sQLiteDatabase.execSQL("CREATE TABLE dau_log (_id INTEGER PRIMARY KEY, time TEXT, data TEXT);");
            sQLiteDatabase.execSQL("CREATE TABLE quick_log (_id INTEGER PRIMARY KEY, time TEXT, data TEXT);");
            sQLiteDatabase.execSQL("CREATE TABLE failure_log (_id INTEGER PRIMARY KEY, time TEXT, data TEXT, log_type TEXT);");
            sQLiteDatabase.execSQL("CREATE TABLE m_log (_id INTEGER PRIMARY KEY, time TEXT, data TEXT);");
            sQLiteDatabase.execSQL("create index if not exists timeindex on statistic(time)");
            sQLiteDatabase.execSQL("create index if not exists timeindex on dau_log(time)");
            sQLiteDatabase.execSQL("create index if not exists timeindex on quick_log(time)");
            sQLiteDatabase.execSQL("create index if not exists timeindex on failure_log(time)");
            sQLiteDatabase.execSQL("create index if not exists timeindex on m_log(time)");
        } catch (SQLException e2) {
            if (com.jingdong.jdma.f.c.d) {
                c("dbCreateError", "", e2.getMessage());
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        try {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS statistic");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS dau_log");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS quick_log");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS failure_log");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS m_log");
        } catch (SQLException e2) {
            if (com.jingdong.jdma.f.c.d) {
                c("dbUpgradeError", "", e2.getMessage());
            }
        }
        onCreate(sQLiteDatabase);
    }

    public synchronized boolean a(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        if ("failure_log".equals(str)) {
            return b(str, str2, str3);
        }
        return b(str, str2, (String) null);
    }

    public synchronized int a(String str) {
        int i2 = 0;
        if (a()) {
            if (LogUtil.isDebug()) {
                LogUtil.e("JDMASDK", String.format("'%s' database file already deleted!", "jd_reportStatExp.db"));
            }
            return 0;
        }
        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().query(str, new String[]{"count(*)"}, null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                i2 = cursor.getInt(0);
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return i2;
    }

    private int b(String str, String[] strArr) {
        int length;
        int i2 = -1;
        if (strArr != null) {
            try {
                length = strArr.length;
            } catch (Throwable th) {
                if (com.jingdong.jdma.f.c.d) {
                    c("dbDeleteError", str, th.getMessage());
                }
            }
        } else {
            length = 0;
        }
        i2 = getWritableDatabase().delete(str, String.format("%s in (%s)", "_id", a(strArr.length)), strArr);
        if (LogUtil.isDebug()) {
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(length);
            objArr[1] = str;
            objArr[2] = i2 == length ? "successfully" : "failure";
            LogUtil.d("DBManager", String.format("delete %s line data from table %s %s", objArr));
        }
        return i2;
    }

    private List<com.jingdong.jdma.bean.c.a> b(String str, String str2, long j2) {
        Cursor query;
        ArrayList arrayList = new ArrayList();
        if (a()) {
            if (LogUtil.isDebug()) {
                LogUtil.e("DBManager", String.format("'%s' database file already deleted!", "jd_reportStatExp.db"));
            }
            return arrayList;
        } else if (j2 == 0) {
            return arrayList;
        } else {
            long j3 = j2 <= 30 ? j2 : 30L;
            Cursor cursor = null;
            try {
                if (str2 != null) {
                    query = getReadableDatabase().query(str, new String[]{"_id", "time", "data", "log_type"}, null, null, null, null, "time asc", "" + j3);
                } else {
                    query = getReadableDatabase().query(str, new String[]{"_id", "time", "data"}, null, null, null, null, "time asc", "" + j3);
                }
                cursor = query;
                if (cursor != null) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        com.jingdong.jdma.bean.c.a aVar = new com.jingdong.jdma.bean.c.a();
                        aVar.a(cursor.getInt(0));
                        aVar.b(cursor.getString(2));
                        if (str2 != null) {
                            aVar.a(cursor.getString(3));
                        }
                        arrayList.add(aVar);
                        cursor.moveToNext();
                    }
                }
                if (cursor != null && !cursor.isClosed()) {
                    try {
                        cursor.close();
                    } catch (Throwable th) {
                        th = th;
                        th.printStackTrace();
                        return arrayList;
                    }
                }
            } catch (Throwable th2) {
                try {
                    th2.printStackTrace();
                    if (com.jingdong.jdma.f.c.d) {
                        try {
                            c("dbQueryLimitError", str, th2.getMessage());
                        } catch (Throwable th3) {
                            th = th3;
                            Throwable th4 = th;
                            if (cursor != null && !cursor.isClosed()) {
                                try {
                                    cursor.close();
                                } catch (Throwable th5) {
                                    th5.printStackTrace();
                                }
                            }
                            throw th4;
                        }
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        try {
                            cursor.close();
                        } catch (Throwable th6) {
                            th = th6;
                            th.printStackTrace();
                            return arrayList;
                        }
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }
            return arrayList;
        }
    }

    public synchronized int a(String str, String[] strArr) {
        if (a()) {
            if (LogUtil.isDebug()) {
                LogUtil.e("JDMASDK", String.format("'%s' database file already deleted!", "jd_reportStatExp.db"));
            }
            return strArr != null ? strArr.length : 0;
        }
        int b = b(str, strArr);
        if (b != -1) {
            return b;
        }
        int b2 = b(str, strArr);
        if (b2 != -1) {
            return b2;
        }
        int b3 = b(str, strArr);
        if (b3 != -1) {
            return b3;
        }
        getWritableDatabase().close();
        boolean deleteDatabase = this.b.deleteDatabase("jd_reportStatExp.db");
        c.f12681k = deleteDatabase;
        if (deleteDatabase) {
            com.jingdong.jdma.common.utils.a.a(this.a);
        }
        if (com.jingdong.jdma.f.c.d) {
            c("deleteDB", "", Boolean.toString(c.f12681k));
        }
        return strArr != null ? strArr.length : 0;
    }

    public synchronized List<com.jingdong.jdma.bean.c.a> a(@NonNull String str, @Nullable String str2, long j2) {
        if ("failure_log".equals(str)) {
            return b(str, str2, j2);
        }
        return b(str, (String) null, j2);
    }

    public synchronized List<com.jingdong.jdma.bean.c.a> a(@NonNull String str, String[] strArr, String str2) {
        if ("failure_log".equals(str)) {
            return b(str, strArr, str2);
        }
        return b(str, strArr, (String) null);
    }

    private List<com.jingdong.jdma.bean.c.a> b(String str, String[] strArr, String str2) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str) || strArr == null) {
            return arrayList;
        }
        if (a()) {
            if (LogUtil.isDebug()) {
                LogUtil.e("JDMASDK", String.format("'%s' database file already deleted!", "jd_reportStatExp.db"));
            }
            return arrayList;
        }
        Cursor cursor = null;
        try {
            String format = String.format("select %s,%s,%s from " + str + " where %s in (%s) order by %s asc", "_id", "time", "data", "_id", a(strArr.length), "time");
            if (str2 != null) {
                format = String.format("select %s,%s,%s,%s from " + str + " where %s in (%s) order by %s asc", "_id", "time", "data", "log_type", "_id", a(strArr.length), "time");
            }
            cursor = getReadableDatabase().rawQuery(format, strArr);
            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    com.jingdong.jdma.bean.c.a aVar = new com.jingdong.jdma.bean.c.a();
                    aVar.a(cursor.getInt(0));
                    aVar.b(cursor.getString(2));
                    if (str2 != null) {
                        aVar.a(cursor.getString(3));
                    }
                    arrayList.add(aVar);
                    cursor.moveToNext();
                }
            }
            if (cursor != null && !cursor.isClosed()) {
                try {
                    cursor.close();
                } catch (Throwable th) {
                    th = th;
                    th.printStackTrace();
                    return arrayList;
                }
            }
        } catch (Throwable th2) {
            try {
                th2.printStackTrace();
                if (com.jingdong.jdma.f.c.d) {
                    try {
                        c("dbQueryError", str, th2.getMessage());
                    } catch (Throwable th3) {
                        th = th3;
                        Throwable th4 = th;
                        if (cursor != null && !cursor.isClosed()) {
                            try {
                                cursor.close();
                            } catch (Throwable th5) {
                                th5.printStackTrace();
                            }
                        }
                        throw th4;
                    }
                }
                if (cursor != null && !cursor.isClosed()) {
                    try {
                        cursor.close();
                    } catch (Throwable th6) {
                        th = th6;
                        th.printStackTrace();
                        return arrayList;
                    }
                }
            } catch (Throwable th7) {
                th = th7;
            }
        }
        return arrayList;
    }

    private static String a(int i2) {
        if (i2 >= 1) {
            StringBuilder sb = new StringBuilder((i2 * 2) - 1);
            sb.append("?");
            for (int i3 = 1; i3 < i2; i3++) {
                sb.append(",?");
            }
            return sb.toString();
        }
        throw new RuntimeException("No placeholders");
    }

    private boolean a() {
        if (c.f12681k) {
            return true;
        }
        boolean c2 = com.jingdong.jdma.common.utils.a.c(this.a);
        c.f12681k = c2;
        return c2;
    }
}
