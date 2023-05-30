package h.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteStatement;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.datasnapshot.DataSnapshotSDK;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import h.b.b;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes11.dex */
public final class a implements h.a.a {
    public final b a;

    public a(@NotNull Context context) {
        this.a = new b(context);
    }

    @Override // h.a.a
    public long a() {
        b bVar = this.a;
        bVar.h();
        SQLiteDatabase sQLiteDatabase = bVar.f19701h;
        long j2 = 0;
        if (sQLiteDatabase != null) {
            SQLiteStatement sQLiteStatement = null;
            try {
                try {
                    sQLiteStatement = sQLiteDatabase.compileStatement("SELECT count(key) FROM data_snapshot");
                    j2 = sQLiteStatement.simpleQueryForLong();
                    sQLiteStatement.close();
                } catch (Exception e2) {
                    String str = "DefaultWXStorage occurred an exception when execute getLength:" + e2.getMessage();
                    if (Log.isDebug()) {
                        Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + str);
                    }
                    if (sQLiteStatement != null) {
                        sQLiteStatement.close();
                    }
                }
                return j2;
            } catch (Throwable th) {
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
                throw th;
            }
        }
        return 0L;
    }

    @Override // h.a.a
    @Nullable
    public String b(@NotNull String str) {
        String str2;
        b bVar = this.a;
        bVar.h();
        SQLiteDatabase sQLiteDatabase = bVar.f19701h;
        if (sQLiteDatabase != null) {
            Cursor query = sQLiteDatabase.query("data_snapshot", new String[]{"value"}, "key=?", new String[]{str}, null, null, null);
            try {
                try {
                    if (query.moveToNext()) {
                        ContentValues contentValues = new ContentValues();
                        b.a aVar = b.f19699k;
                        contentValues.put(VerifyTracker.KEY_TIMESTAMP, b.f19698j.format(new Date()));
                        int update = sQLiteDatabase.update("data_snapshot", contentValues, "key= ?", new String[]{str});
                        StringBuilder sb = new StringBuilder();
                        sb.append("update timestamp ");
                        sb.append(update == 1 ? "success" : JDReactConstant.FAILED);
                        sb.append(" for operation [getItem(key = ");
                        sb.append(str);
                        sb.append(")]");
                        String sb2 = sb.toString();
                        if (Log.isDebug()) {
                            Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + sb2);
                        }
                        str2 = query.getString(query.getColumnIndex("value"));
                    } else {
                        str2 = null;
                    }
                    return str2;
                } catch (Exception e2) {
                    String str3 = "DefaultWXStorage occurred an exception when execute getItem:" + e2.getMessage();
                    if (Log.isDebug()) {
                        Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + str3);
                    }
                    query.close();
                    return null;
                }
            } finally {
                query.close();
            }
        }
        return null;
    }

    @Override // h.a.a
    @Nullable
    public List<String> c() {
        b bVar = this.a;
        bVar.h();
        SQLiteDatabase sQLiteDatabase = bVar.f19701h;
        if (sQLiteDatabase != null) {
            ArrayList arrayList = new ArrayList();
            Cursor query = sQLiteDatabase.query("data_snapshot", new String[]{"key"}, null, null, null, null, null);
            while (query.moveToNext()) {
                try {
                    try {
                        String string = query.getString(query.getColumnIndex("key"));
                        Intrinsics.checkExpressionValueIsNotNull(string, "c.getString(c.getColumnI\u2026teOpenHelper.COLUMN_KEY))");
                        arrayList.add(string);
                    } catch (Exception e2) {
                        String str = "DefaultWXStorage occurred an exception when execute getAllKeys:" + e2.getMessage();
                        if (Log.isDebug()) {
                            Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + str);
                        }
                    }
                } finally {
                    query.close();
                }
            }
            return arrayList;
        }
        return null;
    }

    @Override // h.a.a
    public void close() {
        try {
            this.a.f();
        } catch (Exception e2) {
            String str = "database close error! " + e2.getMessage();
            if (Log.isDebug()) {
                Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + str);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0097 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean d() {
        int i2;
        b bVar = this.a;
        bVar.h();
        SQLiteDatabase sQLiteDatabase = bVar.f19701h;
        if (sQLiteDatabase != null) {
            ArrayList arrayList = new ArrayList();
            Cursor c2 = sQLiteDatabase.query("data_snapshot", new String[]{"key", "persistent"}, null, null, null, null, "timestamp ASC");
            try {
                try {
                    Intrinsics.checkExpressionValueIsNotNull(c2, "c");
                    int count = c2.getCount() / 10;
                    i2 = 0;
                    while (c2.moveToNext()) {
                        try {
                            String string = c2.getString(c2.getColumnIndex("key"));
                            if (!(c2.getInt(c2.getColumnIndex("persistent")) == 1) && string != null) {
                                i2++;
                                arrayList.add(string);
                                if (i2 == count) {
                                    break;
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            String str = "DefaultWXStorage occurred an exception when execute trimToSize:" + e.getMessage();
                            if (Log.isDebug()) {
                                Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + str);
                            }
                            if (i2 > 0) {
                            }
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    i2 = 0;
                }
                if (i2 > 0) {
                    return false;
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    a((String) it.next());
                }
                String str2 = "remove " + i2 + " items by lru";
                if (Log.isDebug()) {
                    Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + str2);
                }
                return true;
            } finally {
                c2.close();
            }
        }
        return false;
    }

    @Override // h.a.a
    public boolean a(@NotNull String str) {
        b bVar = this.a;
        bVar.h();
        SQLiteDatabase sQLiteDatabase = bVar.f19701h;
        if (sQLiteDatabase != null) {
            try {
                return sQLiteDatabase.delete("data_snapshot", "key=?", new String[]{str}) == 1;
            } catch (Exception e2) {
                String str2 = "DefaultWXStorage occurred an exception when execute removeItem:" + e2.getMessage();
                if (Log.isDebug()) {
                    Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + str2);
                }
            }
        }
        return false;
    }

    @Override // h.a.a
    public boolean a(@NotNull String str, @NotNull String str2, boolean z, boolean z2) {
        b bVar = this.a;
        bVar.h();
        SQLiteDatabase sQLiteDatabase = bVar.f19701h;
        if (sQLiteDatabase != null) {
            String str3 = "setItem(key:" + str + ",value:" + str2 + ')';
            if (Log.isDebug()) {
                Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + str3);
            }
            SQLiteStatement sQLiteStatement = null;
            b.a aVar = b.f19699k;
            String format = b.f19698j.format(new Date());
            Intrinsics.checkExpressionValueIsNotNull(format, "MySQLiteOpenHelper.sDateFormatter.format(Date())");
            try {
                try {
                    sQLiteStatement = sQLiteDatabase.compileStatement("INSERT OR REPLACE INTO data_snapshot VALUES (?,?,?,?);");
                    sQLiteStatement.clearBindings();
                    sQLiteStatement.bindString(1, str);
                    sQLiteStatement.bindString(2, str2);
                    sQLiteStatement.bindString(3, format);
                    sQLiteStatement.bindLong(4, z ? 1L : 0L);
                    sQLiteStatement.execute();
                    sQLiteStatement.close();
                    return true;
                } catch (Exception e2) {
                    String str4 = "DefaultWXStorage occurred an exception when execute setItem :" + e2.getMessage();
                    if (Log.isDebug()) {
                        Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + str4);
                    }
                    if ((e2 instanceof SQLiteFullException) && d()) {
                        boolean a = a(str, str2, z, false);
                        if (sQLiteStatement != null) {
                            sQLiteStatement.close();
                        }
                        return a;
                    } else if (sQLiteStatement != null) {
                        sQLiteStatement.close();
                        return false;
                    } else {
                        return false;
                    }
                }
            } catch (Throwable th) {
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
                throw th;
            }
        }
        return false;
    }

    @Override // h.a.a
    public void b() {
        try {
            this.a.h();
        } catch (Exception e2) {
            String str = "database open error! " + e2.getMessage();
            if (Log.isDebug()) {
                Log.xLogD(DataSnapshotSDK.TAG, "\u6570\u636e\u5feb\u7167\uff1a" + str);
            }
        }
    }
}
