package com.jingdong.jdexreport.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jingdong.jdexreport.e.d;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized long c() {
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
            java.lang.String r5 = "exception"
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
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdexreport.b.a.c():long");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int a(java.util.ArrayList<com.jingdong.jdexreport.e.d> r19, long r20, long r22) {
        /*
            Method dump skipped, instructions count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdexreport.b.a.a(java.util.ArrayList, long, long):int");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.util.ArrayList<com.jingdong.jdexreport.e.d> a(java.lang.Long r17) {
        /*
            r16 = this;
            monitor-enter(r16)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> Lc5
            r1.<init>()     // Catch: java.lang.Throwable -> Lc5
            long r2 = r17.longValue()     // Catch: java.lang.Throwable -> Lc5
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 != 0) goto L12
            monitor-exit(r16)
            return r1
        L12:
            r2 = 0
            r0 = 4
            java.lang.String[] r8 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            java.lang.String r0 = "id"
            r3 = 0
            r8[r3] = r0     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            r0 = 1
            java.lang.String r6 = "time"
            r8[r0] = r6     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            java.lang.String r0 = "data"
            r15 = 2
            r8[r15] = r0     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            r0 = 3
            java.lang.String r6 = "reserve"
            r8[r0] = r6     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            long r6 = r17.longValue()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r0 <= 0) goto L5b
            android.database.sqlite.SQLiteDatabase r6 = r16.getReadableDatabase()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            java.lang.String r7 = "exception"
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            java.lang.String r13 = "time asc"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            r0.<init>()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            java.lang.String r4 = ""
            r0.append(r4)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            r4 = r17
            r0.append(r4)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            java.lang.String r14 = r0.toString()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            android.database.Cursor r0 = r6.query(r7, r8, r9, r10, r11, r12, r13, r14)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            goto L6c
        L59:
            r0 = move-exception
            goto Lb9
        L5b:
            android.database.sqlite.SQLiteDatabase r6 = r16.getReadableDatabase()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            java.lang.String r7 = "exception"
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            java.lang.String r13 = "time asc"
            android.database.Cursor r0 = r6.query(r7, r8, r9, r10, r11, r12, r13)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
        L6c:
            r2 = r0
            r2.moveToFirst()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
        L70:
            boolean r0 = r2.isAfterLast()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            if (r0 != 0) goto L90
            com.jingdong.jdexreport.e.d r0 = new com.jingdong.jdexreport.e.d     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            r0.<init>()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            java.lang.String r4 = r2.getString(r3)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            r0.a(r4)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            java.lang.String r4 = r2.getString(r15)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            r0.b(r4)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            r1.add(r0)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            r2.moveToNext()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L99 java.lang.StackOverflowError -> L9b
            goto L70
        L90:
            if (r2 == 0) goto Lb7
            boolean r0 = r2.isClosed()     // Catch: java.lang.Throwable -> Lc5
            if (r0 != 0) goto Lb7
            goto Lb4
        L99:
            r0 = move-exception
            goto L9d
        L9b:
            r0 = move-exception
            goto La9
        L9d:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L59
            if (r2 == 0) goto Lb7
            boolean r0 = r2.isClosed()     // Catch: java.lang.Throwable -> Lc5
            if (r0 != 0) goto Lb7
            goto Lb4
        La9:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L59
            if (r2 == 0) goto Lb7
            boolean r0 = r2.isClosed()     // Catch: java.lang.Throwable -> Lc5
            if (r0 != 0) goto Lb7
        Lb4:
            r2.close()     // Catch: java.lang.Throwable -> Lc5
        Lb7:
            monitor-exit(r16)
            return r1
        Lb9:
            if (r2 == 0) goto Lc4
            boolean r1 = r2.isClosed()     // Catch: java.lang.Throwable -> Lc5
            if (r1 != 0) goto Lc4
            r2.close()     // Catch: java.lang.Throwable -> Lc5
        Lc4:
            throw r0     // Catch: java.lang.Throwable -> Lc5
        Lc5:
            r0 = move-exception
            monitor-exit(r16)
            goto Lc9
        Lc8:
            throw r0
        Lc9:
            goto Lc8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdexreport.b.a.a(java.lang.Long):java.util.ArrayList");
    }

    public void a() {
        getWritableDatabase().execSQL("DELETE FROM exception");
    }
}
