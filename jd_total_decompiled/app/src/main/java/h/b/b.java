package h.b;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
    */
    public final synchronized void h() {
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase = this.f19701h;
        if (sQLiteDatabase != null) {
            if (sQLiteDatabase == null) {
                Intrinsics.throwNpe();
            }
            if (sQLiteDatabase.isOpen()) {
                return;
            }
        }
        int i2 = 0;
        while (true) {
            cursor = 1;
            if (i2 <= 1) {
                if (i2 > 0) {
                    try {
                        g();
                    } catch (SQLiteException e2) {
                        e2.printStackTrace();
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        }
                        i2++;
                    }
                }
                cursor = getWritableDatabase();
                this.f19701h = cursor;
                break;
            }
            break;
        }
        SQLiteDatabase sQLiteDatabase2 = this.f19701h;
        if (sQLiteDatabase2 == null) {
            return;
        }
        if (sQLiteDatabase2 == null) {
            Intrinsics.throwNpe();
        }
        try {
            try {
                cursor = sQLiteDatabase2.rawQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = 'data_snapshot'", null);
            } catch (Exception e3) {
                e = e3;
                cursor = null;
            } catch (Throwable th) {
                th = th;
                cursor = 0;
                if (cursor != 0) {
                }
                throw th;
            }
            if (cursor != null) {
                try {
                } catch (Exception e4) {
                    e = e4;
                    e.printStackTrace();
                }
                if (cursor.getCount() > 0) {
                    cursor.close();
                    SQLiteDatabase sQLiteDatabase3 = this.f19701h;
                    if (sQLiteDatabase3 == null) {
                        Intrinsics.throwNpe();
                    }
                    sQLiteDatabase3.setMaximumSize(this.f19700g);
                    return;
                }
            }
            sQLiteDatabase2.execSQL("CREATE TABLE IF NOT EXISTS data_snapshot (key TEXT PRIMARY KEY,value TEXT NOT NULL,timestamp TEXT NOT NULL,persistent INTEGER DEFAULT 0)");
        } catch (Throwable th2) {
            th = th2;
            if (cursor != 0) {
                cursor.close();
            }
            throw th;
        }
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
