package h.b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.datasnapshot.DataSnapshotSDK;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes11.dex */
public final class b extends SQLiteOpenHelper {

    /* renamed from: g  reason: collision with root package name */
    public long f19700g;

    /* renamed from: h  reason: collision with root package name */
    public SQLiteDatabase f19701h;

    /* renamed from: i  reason: collision with root package name */
    public final Context f19702i;

    /* renamed from: k  reason: collision with root package name */
    public static final a f19699k = new a();
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public static SimpleDateFormat f19698j = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    /* loaded from: classes11.dex */
    public static final class a {
    }

    public b(@NotNull Context context) {
        super(context, "DataSnapshot", (SQLiteDatabase.CursorFactory) null, 2);
        this.f19702i = context;
        this.f19700g = 52428800L;
    }

    public final synchronized void f() {
        SQLiteDatabase sQLiteDatabase = this.f19701h;
        if (sQLiteDatabase != null) {
            if (sQLiteDatabase == null) {
                Intrinsics.throwNpe();
            }
            if (sQLiteDatabase.isOpen()) {
                SQLiteDatabase sQLiteDatabase2 = this.f19701h;
                if (sQLiteDatabase2 == null) {
                    Intrinsics.throwNpe();
                }
                sQLiteDatabase2.close();
                this.f19701h = null;
            }
        }
    }

    public final boolean g() {
        f();
        return this.f19702i.deleteDatabase("DataSnapshot");
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0057, code lost:
        if (r1 != 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0064, code lost:
        if (r1 == null) goto L48;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0079 A[Catch: all -> 0x007d, TryCatch #7 {, blocks: (B:3:0x0001, B:6:0x0007, B:7:0x000a, B:15:0x0019, B:16:0x001c, B:19:0x0024, B:21:0x002a, B:24:0x0035, B:23:0x002e, B:25:0x0038, B:30:0x0040, B:47:0x0066, B:48:0x0069, B:50:0x006d, B:51:0x0070, B:54:0x0079, B:55:0x007c), top: B:76:0x0001 }] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v13, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void h() {
        /*
            r5 = this;
            monitor-enter(r5)
            android.database.sqlite.SQLiteDatabase r0 = r5.f19701h     // Catch: java.lang.Throwable -> Lb3
            if (r0 == 0) goto L12
            if (r0 != 0) goto La
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Throwable -> Lb3
        La:
            boolean r0 = r0.isOpen()     // Catch: java.lang.Throwable -> Lb3
            if (r0 == 0) goto L12
            monitor-exit(r5)
            return
        L12:
            r0 = 0
        L13:
            r1 = 1
            r2 = 0
            if (r0 > r1) goto L38
            if (r0 <= 0) goto L1c
            r5.g()     // Catch: android.database.sqlite.SQLiteException -> L23 java.lang.Throwable -> L7d
        L1c:
            android.database.sqlite.SQLiteDatabase r1 = r5.getWritableDatabase()     // Catch: android.database.sqlite.SQLiteException -> L23 java.lang.Throwable -> L7d
            r5.f19701h = r1     // Catch: android.database.sqlite.SQLiteException -> L23 java.lang.Throwable -> L7d
            goto L38
        L23:
            r1 = move-exception
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L7d
            r1 = 30
            long r3 = (long) r1
            java.lang.Thread.sleep(r3)     // Catch: java.lang.InterruptedException -> L2e java.lang.Throwable -> L7d
            goto L35
        L2e:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L7d
            r1.interrupt()     // Catch: java.lang.Throwable -> L7d
        L35:
            int r0 = r0 + 1
            goto L13
        L38:
            android.database.sqlite.SQLiteDatabase r0 = r5.f19701h     // Catch: java.lang.Throwable -> L7d
            if (r0 != 0) goto L3e
            monitor-exit(r5)
            return
        L3e:
            if (r0 != 0) goto L43
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Throwable -> L7d
        L43:
            java.lang.String r1 = "SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = 'data_snapshot'"
            android.database.Cursor r1 = r0.rawQuery(r1, r2)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            if (r1 == 0) goto L52
            int r3 = r1.getCount()     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L76
            if (r3 <= 0) goto L52
            goto L66
        L52:
            java.lang.String r3 = "CREATE TABLE IF NOT EXISTS data_snapshot (key TEXT PRIMARY KEY,value TEXT NOT NULL,timestamp TEXT NOT NULL,persistent INTEGER DEFAULT 0)"
            r0.execSQL(r3)     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L76
            if (r1 == 0) goto L69
            goto L66
        L5a:
            r0 = move-exception
            goto L61
        L5c:
            r0 = move-exception
            r1 = r2
            goto L77
        L5f:
            r0 = move-exception
            r1 = r2
        L61:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L76
            if (r1 == 0) goto L69
        L66:
            r1.close()     // Catch: java.lang.Throwable -> L7d
        L69:
            android.database.sqlite.SQLiteDatabase r0 = r5.f19701h     // Catch: java.lang.Throwable -> L7d
            if (r0 != 0) goto L70
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Throwable -> L7d
        L70:
            long r3 = r5.f19700g     // Catch: java.lang.Throwable -> L7d
            r0.setMaximumSize(r3)     // Catch: java.lang.Throwable -> L7d
            goto Lb1
        L76:
            r0 = move-exception
        L77:
            if (r1 == 0) goto L7c
            r1.close()     // Catch: java.lang.Throwable -> L7d
        L7c:
            throw r0     // Catch: java.lang.Throwable -> L7d
        L7d:
            r0 = move-exception
            r5.f19701h = r2     // Catch: java.lang.Throwable -> Lb3
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb3
            r1.<init>()     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r2 = "ensureDatabase failed, throwable = "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> Lb3
            r1.append(r0)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> Lb3
            boolean r1 = com.jd.libs.hybrid.base.util.Log.isDebug()     // Catch: java.lang.Throwable -> Lb3
            if (r1 == 0) goto Lb1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb3
            r1.<init>()     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r2 = "\u6570\u636e\u5feb\u7167\uff1a"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lb3
            r1.append(r0)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r1 = "DataSnapshotSDK"
            com.jd.libs.hybrid.base.util.Log.xLogD(r1, r0)     // Catch: java.lang.Throwable -> Lb3
        Lb1:
            monitor-exit(r5)
            return
        Lb3:
            r0 = move-exception
            monitor-exit(r5)
            goto Lb7
        Lb6:
            throw r0
        Lb7:
            goto Lb6
        */
        throw new UnsupportedOperationException("Method not decompiled: h.b.b.h():void");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(@NotNull SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS data_snapshot (key TEXT PRIMARY KEY,value TEXT NOT NULL,timestamp TEXT NOT NULL,persistent INTEGER DEFAULT 0)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(@NotNull SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (i2 != i3) {
            if (i3 == 2) {
                boolean z = true;
                if (i2 == 1) {
                    String str = "storage is updating from version " + i2 + " to version " + i3;
                    if (Log.isDebug()) {
                        Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + str);
                    }
                    try {
                        long currentTimeMillis = System.currentTimeMillis();
                        sQLiteDatabase.beginTransaction();
                        sQLiteDatabase.execSQL("ALTER TABLE data_snapshot ADD COLUMN timestamp TEXT;");
                        sQLiteDatabase.execSQL("ALTER TABLE data_snapshot ADD COLUMN persistent INTEGER;");
                        sQLiteDatabase.execSQL("UPDATE data_snapshot SET timestamp = '" + f19698j.format(new Date()) + "' , persistent = 0");
                        sQLiteDatabase.setTransactionSuccessful();
                        StringBuilder sb = new StringBuilder();
                        sb.append("storage updated success (");
                        sb.append(System.currentTimeMillis() - currentTimeMillis);
                        sb.append("ms)");
                        String sb2 = sb.toString();
                        if (Log.isDebug()) {
                            Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + sb2);
                        }
                    } catch (Exception e2) {
                        String str2 = "storage updated failed from version " + i2 + " to version " + i3 + DYConstants.DY_REGEX_COMMA + e2.getMessage();
                        if (Log.isDebug()) {
                            Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + str2);
                        }
                        z = false;
                    } finally {
                        sQLiteDatabase.endTransaction();
                    }
                    if (z) {
                        return;
                    }
                    if (Log.isDebug()) {
                        Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1astorage is rollback,all data will be removed");
                    }
                }
            }
            g();
            onCreate(sQLiteDatabase);
        }
    }
}
