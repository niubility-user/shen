package performance.jd.jdreportperformance.d;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class a extends SQLiteOpenHelper {
    private static volatile a a;

    private a() {
        super(performance.jd.jdreportperformance.a.b().a(), "jd_mallperformancereport.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    private boolean a(long j2) {
        return 10000 <= j2;
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
    public synchronized long b() {
        long j2;
        Cursor cursor = null;
        j2 = 0;
        try {
            cursor = getReadableDatabase().query("performance", new String[]{"count(*)"}, null, null, null, null, null);
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
    public synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE performance (id INTEGER PRIMARY KEY, time TEXT, data TEXT, reserve TEXT);");
        sQLiteDatabase.execSQL("create index if not exists timeindex on performance(time)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public synchronized void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS performance");
        onCreate(sQLiteDatabase);
    }

    public synchronized boolean a(performance.jd.jdreportperformance.e.b bVar, long j2) {
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!a(j2)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("data", bVar.b());
            contentValues.put("reserve", "");
            getWritableDatabase().insert("performance", null, contentValues);
            return true;
        }
        performance.jd.jdreportperformance.b.b.b.a("DBManager", "current db data num is over 10000");
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x008f, code lost:
        if (r3.isClosed() == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x009f, code lost:
        if (r3.isClosed() == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ab, code lost:
        if (r3.isClosed() == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ad, code lost:
        r3.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized List<performance.jd.jdreportperformance.e.b> b(long j2) {
        int i2;
        Cursor query;
        ArrayList arrayList = new ArrayList();
        int i3 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i3 == 0) {
            return arrayList;
        }
        Cursor cursor = null;
        try {
            try {
                String[] strArr = {"id", "time", "data", "reserve"};
                if (i3 > 0) {
                    i2 = 2;
                    query = getReadableDatabase().query("performance", strArr, null, null, null, null, "time asc", "" + j2);
                } else {
                    i2 = 2;
                    query = getReadableDatabase().query("performance", strArr, null, null, null, null, "time asc");
                }
                cursor = query;
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    performance.jd.jdreportperformance.e.b bVar = new performance.jd.jdreportperformance.e.b();
                    bVar.a(cursor.getString(0));
                    bVar.b(cursor.getString(i2));
                    arrayList.add(bVar);
                    cursor.moveToNext();
                }
                if (cursor != null) {
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null) {
                }
            }
        } catch (StackOverflowError e3) {
            e3.printStackTrace();
            if (cursor != null) {
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0068, code lost:
        if (r10.isClosed() == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007a, code lost:
        if (r10.isClosed() == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x007c, code lost:
        r10.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized int a(long j2, long j3) {
        Cursor cursor;
        int delete;
        int i2 = 0;
        try {
            String[] strArr = new String[2];
            if (j2 <= j3) {
                strArr[0] = "" + j2;
                strArr[1] = "" + j3;
                return getWritableDatabase().delete("performance", "id>=? and id<=?", strArr) + 0;
            }
            Cursor cursor2 = null;
            r9 = null;
            r9 = null;
            String str = null;
            try {
                cursor = getReadableDatabase().query("performance", new String[]{"max(id)"}, null, null, null, null, null);
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
                    delete = getWritableDatabase().delete("performance", "id>=? and id<=?", strArr) + 0;
                    strArr[0] = "0";
                    strArr[1] = "" + j3;
                    return delete + getWritableDatabase().delete("performance", "id>=? and id<=?", strArr);
                }
                strArr[0] = "0";
                strArr[1] = "" + j3;
                return delete + getWritableDatabase().delete("performance", "id>=? and id<=?", strArr);
            } catch (Exception e4) {
                e = e4;
                i2 = delete;
                e.printStackTrace();
                return i2;
            }
            strArr[0] = "" + j2;
            strArr[1] = str;
            delete = getWritableDatabase().delete("performance", "id>=? and id<=?", strArr) + 0;
        } catch (Exception e5) {
            e = e5;
        }
    }
}
