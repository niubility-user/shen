package com.jingdong.aura;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes4.dex */
public class XTimeProvider extends ContentProvider {
    public static String HOST = null;
    private static final String TAG = "XProvider";
    private XTimeCursor xTimeCursor;

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
            this.xTimeCursor = new XTimeCursor();
            HOST = JdSdk.getInstance().getApplication().getPackageName() + ".xtimeprovider";
            return true;
        } catch (Throwable unused) {
            HOST = "com.jingdong.app.mall.xtimeprovider";
            return true;
        }
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        if (Log.D) {
            Log.i(TAG, "query\uff1a" + uri);
        }
        if (uri == null || !HOST.equals(uri.getHost())) {
            return null;
        }
        return this.xTimeCursor;
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }
}
