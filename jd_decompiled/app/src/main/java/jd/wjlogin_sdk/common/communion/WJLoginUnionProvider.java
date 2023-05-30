package jd.wjlogin_sdk.common.communion;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import jd.wjlogin_sdk.util.p;

/* loaded from: classes11.dex */
public class WJLoginUnionProvider extends ContentProvider {

    /* renamed from: c  reason: collision with root package name */
    private static final String f19748c = "WJLogin.WJLoginUnionProvider";
    private static UriMatcher d = new UriMatcher(-1);

    /* renamed from: e  reason: collision with root package name */
    private static final int f19749e = 1;

    /* renamed from: f  reason: collision with root package name */
    public static final String f19750f = ".WJLoginUnionProvider";

    /* renamed from: g  reason: collision with root package name */
    public static final String f19751g = "/";

    /* renamed from: h  reason: collision with root package name */
    public static Uri f19752h;
    private a a;
    private String b;

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
        if (writableDatabase.isOpen()) {
            return writableDatabase.delete("first", str, strArr);
        }
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
        p.b(f19748c, "" + contentValues.toString());
        if (writableDatabase.isOpen() && d.match(uri) == 1) {
            long insert = writableDatabase.insert("first", null, contentValues);
            if (insert > 0) {
                return ContentUris.withAppendedId(f19752h, insert);
            }
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.a = new a(getContext());
        this.b = getContext().getPackageName();
        f19752h = Uri.parse("content://" + this.b + f19750f + "/first");
        UriMatcher uriMatcher = d;
        StringBuilder sb = new StringBuilder();
        sb.append(this.b);
        sb.append(f19750f);
        uriMatcher.addURI(sb.toString(), "first", 1);
        return false;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
        if (writableDatabase.isOpen()) {
            return writableDatabase.query("first", strArr, str, strArr2, str2, null, null);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
