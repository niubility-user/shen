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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d() {
        /*
            r15 = this;
            java.lang.String r0 = "DataSnapshotSDK"
            java.lang.String r1 = "\u6570\u636e\u5feb\u7167\uff1a"
            h.b.b r2 = r15.a
            r2.h()
            android.database.sqlite.SQLiteDatabase r3 = r2.f19701h
            r2 = 0
            if (r3 == 0) goto Ldf
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r4 = 2
            java.lang.String[] r5 = new java.lang.String[r4]
            java.lang.String r12 = "key"
            r5[r2] = r12
            r13 = 1
            java.lang.String r14 = "persistent"
            r5[r13] = r14
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r4 = "data_snapshot"
            java.lang.String r10 = "timestamp ASC"
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)
            java.lang.String r4 = "c"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L63
            int r4 = r3.getCount()     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L63
            int r4 = r4 / 10
            r5 = 0
        L37:
            boolean r6 = r3.moveToNext()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L60
            if (r6 == 0) goto L92
            int r6 = r3.getColumnIndex(r12)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L60
            java.lang.String r6 = r3.getString(r6)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L60
            int r7 = r3.getColumnIndex(r14)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L60
            int r7 = r3.getInt(r7)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L60
            if (r7 != r13) goto L51
            r7 = 1
            goto L52
        L51:
            r7 = 0
        L52:
            if (r7 != 0) goto L37
            if (r6 == 0) goto L37
            int r5 = r5 + 1
            r11.add(r6)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L60
            if (r5 != r4) goto L37
            goto L92
        L5e:
            r4 = move-exception
            goto L65
        L60:
            r0 = move-exception
            goto Ldb
        L63:
            r4 = move-exception
            r5 = 0
        L65:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L60
            r6.<init>()     // Catch: java.lang.Throwable -> L60
            java.lang.String r7 = "DefaultWXStorage occurred an exception when execute trimToSize:"
            r6.append(r7)     // Catch: java.lang.Throwable -> L60
            java.lang.String r4 = r4.getMessage()     // Catch: java.lang.Throwable -> L60
            r6.append(r4)     // Catch: java.lang.Throwable -> L60
            java.lang.String r4 = r6.toString()     // Catch: java.lang.Throwable -> L60
            boolean r6 = com.jd.libs.hybrid.base.util.Log.isDebug()     // Catch: java.lang.Throwable -> L60
            if (r6 == 0) goto L92
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L60
            r6.<init>()     // Catch: java.lang.Throwable -> L60
            r6.append(r1)     // Catch: java.lang.Throwable -> L60
            r6.append(r4)     // Catch: java.lang.Throwable -> L60
            java.lang.String r4 = r6.toString()     // Catch: java.lang.Throwable -> L60
            com.jd.libs.hybrid.base.util.Log.xLogD(r0, r4)     // Catch: java.lang.Throwable -> L60
        L92:
            r3.close()
            if (r5 > 0) goto L98
            return r2
        L98:
            java.util.Iterator r2 = r11.iterator()
        L9c:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto Lac
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            r15.a(r3)
            goto L9c
        Lac:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "remove "
            r2.append(r3)
            r2.append(r5)
            java.lang.String r3 = " items by lru"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            boolean r3 = com.jd.libs.hybrid.base.util.Log.isDebug()
            if (r3 == 0) goto Lda
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            r3.append(r2)
            java.lang.String r1 = r3.toString()
            com.jd.libs.hybrid.base.util.Log.xLogD(r0, r1)
        Lda:
            return r13
        Ldb:
            r3.close()
            throw r0
        Ldf:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: h.b.a.d():boolean");
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
