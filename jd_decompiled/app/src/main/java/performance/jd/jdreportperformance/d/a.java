package performance.jd.jdreportperformance.d;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized long b() {
        /*
            r13 = this;
            monitor-enter(r13)
            r0 = 1
            r1 = 0
            r2 = 0
            java.lang.String[] r6 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            java.lang.String r0 = "count(*)"
            r12 = 0
            r6[r12] = r0     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            android.database.sqlite.SQLiteDatabase r4 = r13.getReadableDatabase()     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            java.lang.String r5 = "performance"
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            int r0 = r1.getInt(r12)     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            long r2 = (long) r0
            if (r1 == 0) goto L40
            boolean r0 = r1.isClosed()     // Catch: java.lang.Throwable -> L3e
            if (r0 != 0) goto L40
            goto L3a
        L2c:
            r0 = move-exception
            goto L42
        L2e:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L2c
            if (r1 == 0) goto L40
            boolean r0 = r1.isClosed()     // Catch: java.lang.Throwable -> L3e
            if (r0 != 0) goto L40
        L3a:
            r1.close()     // Catch: java.lang.Throwable -> L3e
            goto L40
        L3e:
            r0 = move-exception
            goto L4e
        L40:
            monitor-exit(r13)
            return r2
        L42:
            if (r1 == 0) goto L4d
            boolean r2 = r1.isClosed()     // Catch: java.lang.Throwable -> L3e
            if (r2 != 0) goto L4d
            r1.close()     // Catch: java.lang.Throwable -> L3e
        L4d:
            throw r0     // Catch: java.lang.Throwable -> L3e
        L4e:
            monitor-exit(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: performance.jd.jdreportperformance.d.a.b():long");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.util.List<performance.jd.jdreportperformance.e.b> b(long r17) {
        /*
            r16 = this;
            r0 = r17
            monitor-enter(r16)
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> Lbe
            r2.<init>()     // Catch: java.lang.Throwable -> Lbe
            r3 = 0
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 != 0) goto L10
            monitor-exit(r16)
            return r2
        L10:
            r3 = 0
            r4 = 4
            java.lang.String[] r8 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            java.lang.String r4 = "id"
            r15 = 0
            r8[r15] = r4     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            r4 = 1
            java.lang.String r6 = "time"
            r8[r4] = r6     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            java.lang.String r4 = "data"
            r14 = 2
            r8[r14] = r4     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            r4 = 3
            java.lang.String r6 = "reserve"
            r8[r4] = r6     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            if (r5 <= 0) goto L53
            android.database.sqlite.SQLiteDatabase r6 = r16.getReadableDatabase()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            java.lang.String r7 = "performance"
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            java.lang.String r13 = "time asc"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            r4.<init>()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            java.lang.String r5 = ""
            r4.append(r5)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            r4.append(r0)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            r1 = 2
            r14 = r0
            android.database.Cursor r0 = r6.query(r7, r8, r9, r10, r11, r12, r13, r14)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            goto L65
        L51:
            r0 = move-exception
            goto Lb2
        L53:
            r1 = 2
            android.database.sqlite.SQLiteDatabase r6 = r16.getReadableDatabase()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            java.lang.String r7 = "performance"
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            java.lang.String r13 = "time asc"
            android.database.Cursor r0 = r6.query(r7, r8, r9, r10, r11, r12, r13)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
        L65:
            r3 = r0
            r3.moveToFirst()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
        L69:
            boolean r0 = r3.isAfterLast()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            if (r0 != 0) goto L89
            performance.jd.jdreportperformance.e.b r0 = new performance.jd.jdreportperformance.e.b     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            r0.<init>()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            java.lang.String r4 = r3.getString(r15)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            r0.a(r4)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            java.lang.String r4 = r3.getString(r1)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            r0.b(r4)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            r2.add(r0)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            r3.moveToNext()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L92 java.lang.StackOverflowError -> L94
            goto L69
        L89:
            if (r3 == 0) goto Lb0
            boolean r0 = r3.isClosed()     // Catch: java.lang.Throwable -> Lbe
            if (r0 != 0) goto Lb0
            goto Lad
        L92:
            r0 = move-exception
            goto L96
        L94:
            r0 = move-exception
            goto La2
        L96:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L51
            if (r3 == 0) goto Lb0
            boolean r0 = r3.isClosed()     // Catch: java.lang.Throwable -> Lbe
            if (r0 != 0) goto Lb0
            goto Lad
        La2:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L51
            if (r3 == 0) goto Lb0
            boolean r0 = r3.isClosed()     // Catch: java.lang.Throwable -> Lbe
            if (r0 != 0) goto Lb0
        Lad:
            r3.close()     // Catch: java.lang.Throwable -> Lbe
        Lb0:
            monitor-exit(r16)
            return r2
        Lb2:
            if (r3 == 0) goto Lbd
            boolean r1 = r3.isClosed()     // Catch: java.lang.Throwable -> Lbe
            if (r1 != 0) goto Lbd
            r3.close()     // Catch: java.lang.Throwable -> Lbe
        Lbd:
            throw r0     // Catch: java.lang.Throwable -> Lbe
        Lbe:
            r0 = move-exception
            monitor-exit(r16)
            goto Lc2
        Lc1:
            throw r0
        Lc2:
            goto Lc1
        */
        throw new UnsupportedOperationException("Method not decompiled: performance.jd.jdreportperformance.d.a.b(long):java.util.List");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int a(long r19, long r21) {
        /*
            Method dump skipped, instructions count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: performance.jd.jdreportperformance.d.a.a(long, long):int");
    }
}
