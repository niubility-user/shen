package com.jingdong.manto.provider.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import com.jingdong.manto.utils.MantoLog;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes16.dex */
public abstract class DatabaseProvider extends ContentProvider {
    private static final Object d = new Object();

    /* renamed from: e  reason: collision with root package name */
    private static final UriMatcher f13992e = new UriMatcher(-1);

    /* renamed from: f  reason: collision with root package name */
    private static final SparseArray<String> f13993f = new SparseArray<>();

    /* renamed from: g  reason: collision with root package name */
    private static final SparseArray<LinkedHashMap<String, String>> f13994g = new SparseArray<>();
    private SQLiteOpenHelper a;
    private SQLiteDatabase b;

    /* renamed from: c  reason: collision with root package name */
    private AtomicInteger f13995c = new AtomicInteger();

    private Uri a(Uri uri) {
        String path = uri.getPath();
        String authority = uri.getAuthority();
        if (path.startsWith("/class:")) {
            Class<?> cls = null;
            try {
                cls = Class.forName(path.substring(7));
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
            String c2 = a.c(cls);
            if (f13993f.indexOfValue(c2) < 0) {
                synchronized (d) {
                    a(cls);
                }
            }
            return Uri.parse("content://" + authority + "/" + c2);
        }
        return uri;
    }

    private void a(Class<?> cls) {
        Context context = getContext();
        String c2 = a.c(cls);
        String a = a.a(context);
        SparseArray<String> sparseArray = f13993f;
        int size = sparseArray.size() + 1;
        f13992e.addURI(a, c2, size);
        sparseArray.append(size, c2);
        String[] b = a.b(cls);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        for (int i2 = 0; i2 < b.length; i2++) {
            linkedHashMap.put(b[i2], b[i2]);
        }
        f13994g.append(size, linkedHashMap);
    }

    private void b(Context context, String str) {
        if (this.a != null) {
            if (("" + this.a.getDatabaseName()).equals(str)) {
                MantoLog.d("better", "sample dbName:" + str);
                return;
            }
        }
        synchronized (d) {
            f13993f.clear();
            f13994g.clear();
            SQLiteDatabase sQLiteDatabase = this.b;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
                this.b = null;
            }
            this.f13995c.set(0);
            this.a = a(context, str);
        }
    }

    public abstract SQLiteOpenHelper a(Context context, String str);

    public abstract String a();

    SQLiteDatabase b() {
        if (this.a == null) {
            this.a = a(getContext(), a());
        }
        return this.a.getReadableDatabase();
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        Uri a = a(uri);
        String str = f13993f.get(f13992e.match(a));
        long j2 = -1;
        if (!TextUtils.isEmpty(str)) {
            SQLiteDatabase c2 = c();
            c2.beginTransaction();
            for (int i2 = 0; i2 < contentValuesArr.length; i2++) {
                long insertWithOnConflict = c2.insertWithOnConflict(str, null, contentValuesArr[i2], 5);
                if (i2 == contentValuesArr.length - 1) {
                    j2 = insertWithOnConflict;
                }
            }
            c2.setTransactionSuccessful();
            c2.endTransaction();
            if (j2 > 0) {
                getContext().getContentResolver().notifyChange(ContentUris.withAppendedId(a, j2), null);
            }
        }
        return (int) j2;
    }

    SQLiteDatabase c() {
        SQLiteDatabase sQLiteDatabase;
        if (this.a == null) {
            this.a = a(getContext(), a());
        }
        if (this.f13995c.incrementAndGet() == 1 || (sQLiteDatabase = this.b) == null || !sQLiteDatabase.isOpen()) {
            try {
                this.b = this.a.getWritableDatabase();
            } catch (Exception unused) {
                SQLiteOpenHelper a = a(getContext(), a());
                this.a = a;
                this.b = a.getWritableDatabase();
            }
        }
        return this.b;
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("method", str);
        if ("resetDB".equals(str) && !TextUtils.isEmpty(str2)) {
            b(getContext(), str2);
            bundle2.putBoolean("result", true);
        }
        return bundle2;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        Uri a = a(uri);
        String str2 = f13993f.get(f13992e.match(a));
        if (TextUtils.isEmpty(str2)) {
            return -1;
        }
        int delete = c().delete(str2, str, strArr);
        if (-1 != delete) {
            getContext().getContentResolver().notifyChange(ContentUris.withAppendedId(a, delete), null);
        }
        return delete;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        Uri a = a(uri);
        String str = f13993f.get(f13992e.match(a));
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        long insertWithOnConflict = c().insertWithOnConflict(str, null, contentValues, 5);
        if (insertWithOnConflict > 0) {
            Uri withAppendedId = ContentUris.withAppendedId(a, insertWithOnConflict);
            getContext().getContentResolver().notifyChange(withAppendedId, null);
            return withAppendedId;
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        a.c(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Uri a = a(uri);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        int match = f13992e.match(a);
        String str3 = f13993f.get(match);
        LinkedHashMap<String, String> linkedHashMap = f13994g.get(match);
        if (TextUtils.isEmpty(str3) || linkedHashMap == null) {
            return null;
        }
        sQLiteQueryBuilder.setTables(str3);
        sQLiteQueryBuilder.setProjectionMap(linkedHashMap);
        Cursor query = sQLiteQueryBuilder.query(b(), strArr, str, strArr2, null, null, str2);
        if (query != null) {
            query.setNotificationUri(getContext().getContentResolver(), a);
            return query;
        }
        return query;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Uri a = a(uri);
        String str2 = f13993f.get(f13992e.match(a));
        if (TextUtils.isEmpty(str2)) {
            return -1;
        }
        int update = c().update(str2, contentValues, str, strArr);
        if (-1 != update) {
            getContext().getContentResolver().notifyChange(ContentUris.withAppendedId(a, update), null);
        }
        return update;
    }
}
