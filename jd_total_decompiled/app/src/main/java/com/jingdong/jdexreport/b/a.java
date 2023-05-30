package com.jingdong.jdexreport.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jingdong.jdexreport.e.d;
import java.util.ArrayList;
import jpbury.t;

/* loaded from: classes.dex */
public class a extends SQLiteOpenHelper {
    private static a b;
    private Context a;

    protected a(Context context) {
        super(context, "jd_mallreportExp.db", (SQLiteDatabase.CursorFactory) null, 1);
        this.a = context;
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a(context);
            }
            aVar = b;
        }
        return aVar;
    }

    private boolean a(long j2) {
        return 10000 <= j2;
    }

    public static synchronized void b() {
        synchronized (a.class) {
            a aVar = b;
            if (aVar != null) {
                aVar.close();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
        if (r1.isClosed() == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003a, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r1.isClosed() == false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized long c() {
        long j2;
        Cursor cursor = null;
        j2 = 0;
        try {
            cursor = getReadableDatabase().query(t.f20145j, new String[]{"count(*)"}, null, null, null, null, null);
            cursor.moveToFirst();
            j2 = cursor.getInt(0);
            if (cursor != null) {
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (cursor != null) {
            }
        }
        return j2;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS exception (id INTEGER PRIMARY KEY, time INTEGER, data TEXT, reserve TEXT);");
        sQLiteDatabase.execSQL("create index if not exists timeindex on exception(time)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        onCreate(sQLiteDatabase);
    }

    public synchronized void b(long j2) {
        try {
            Context context = this.a;
            if (context != null && com.jingdong.jdexreport.a.a.a.a(context)) {
                long currentTimeMillis = System.currentTimeMillis() - (j2 * 1000);
                getWritableDatabase().execSQL("DELETE FROM exception where time<" + currentTimeMillis);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public synchronized void a(d dVar, long j2) {
        try {
            if (!a(j2)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
                contentValues.put("data", dVar.b());
                contentValues.put("reserve", "");
                getWritableDatabase().insert(t.f20145j, null, contentValues);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0070, code lost:
        if (r10.isClosed() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0082, code lost:
        if (r10.isClosed() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0084, code lost:
        r10.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized int a(ArrayList<d> arrayList, long j2, long j3) {
        Cursor cursor;
        int delete;
        int i2 = 0;
        if (arrayList.size() == 0) {
            return 0;
        }
        try {
            String[] strArr = new String[2];
            if (j2 <= j3) {
                strArr[0] = "" + j2;
                strArr[1] = "" + j3;
                return getWritableDatabase().delete(t.f20145j, "id>=? and id<=?", strArr) + 0;
            }
            Cursor cursor2 = null;
            r9 = null;
            r9 = null;
            String str = null;
            try {
                cursor = getReadableDatabase().query(t.f20145j, new String[]{"max(id)"}, null, null, null, null, null);
            } catch (Exception e2) {
                e = e2;
                cursor = null;
            } catch (Throwable th) {
                th = th;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
            try {
                try {
                    cursor.moveToFirst();
                    str = cursor.getString(0);
                    if (cursor != null) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = cursor;
                    if (cursor2 != null && !cursor2.isClosed()) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                if (cursor != null) {
                }
                strArr[0] = "" + j2;
                strArr[1] = str;
                delete = getWritableDatabase().delete(t.f20145j, "id>=? and id<=?", strArr) + 0;
                strArr[0] = "0";
                strArr[1] = "" + j3;
                return delete + getWritableDatabase().delete(t.f20145j, "id>=? and id<=?", strArr);
            }
            strArr[0] = "" + j2;
            strArr[1] = str;
            delete = getWritableDatabase().delete(t.f20145j, "id>=? and id<=?", strArr) + 0;
            try {
                strArr[0] = "0";
                strArr[1] = "" + j3;
                return delete + getWritableDatabase().delete(t.f20145j, "id>=? and id<=?", strArr);
            } catch (Exception e4) {
                e = e4;
                i2 = delete;
                e.printStackTrace();
                return i2;
            }
        } catch (Exception e5) {
            e = e5;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0096, code lost:
        if (r2.isClosed() == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a6, code lost:
        if (r2.isClosed() == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b2, code lost:
        if (r2.isClosed() == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized ArrayList<d> a(Long l2) {
        Cursor query;
        ArrayList<d> arrayList = new ArrayList<>();
        if (l2.longValue() == 0) {
            return arrayList;
        }
        Cursor cursor = null;
        try {
            try {
                String[] strArr = {"id", "time", "data", "reserve"};
                if (l2.longValue() > 0) {
                    query = getReadableDatabase().query(t.f20145j, strArr, null, null, null, null, "time asc", "" + l2);
                } else {
                    query = getReadableDatabase().query(t.f20145j, strArr, null, null, null, null, "time asc");
                }
                cursor = query;
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    d dVar = new d();
                    dVar.a(cursor.getString(0));
                    dVar.b(cursor.getString(2));
                    arrayList.add(dVar);
                    cursor.moveToNext();
                }
                if (cursor != null) {
                }
            } catch (StackOverflowError e2) {
                e2.printStackTrace();
                if (cursor != null) {
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            if (cursor != null) {
            }
        }
        return arrayList;
    }

    public void a() {
        getWritableDatabase().execSQL("DELETE FROM exception");
    }
}
