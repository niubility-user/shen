package com.jingdong.app.mall.global;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes3.dex */
public class CommonProvider extends ContentProvider {

    /* renamed from: g  reason: collision with root package name */
    private a f8435g;

    /* renamed from: h  reason: collision with root package name */
    private UriMatcher f8436h;

    private Cursor a() {
        if (this.f8435g == null) {
            this.f8435g = new a();
        }
        return this.f8435g;
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        try {
            this.f8435g = new a();
            UriMatcher uriMatcher = new UriMatcher(-1);
            this.f8436h = uriMatcher;
            uriMatcher.addURI("com.jingdong.app.mall.global.CommonProvider", "common", 1);
        } catch (Throwable unused) {
        }
        return true;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        if (Log.D) {
            Log.i("CommonProvider", "query\uff1a" + uri);
        }
        if (uri == null || this.f8436h.match(uri) != 1) {
            return null;
        }
        return a();
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }
}
