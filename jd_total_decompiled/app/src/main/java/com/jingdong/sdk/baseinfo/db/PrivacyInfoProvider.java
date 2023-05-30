package com.jingdong.sdk.baseinfo.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.android.sdk.coreinfo.util.Logger;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes7.dex */
public class PrivacyInfoProvider extends ContentProvider {
    private static final String TAG = "BaseInfoCP";
    private SQLiteDatabase db;
    private a dbHelper;
    private UriMatcher uriMatcher;

    private UriMatcher getUriMatcher() {
        if (this.uriMatcher == null) {
            try {
                this.uriMatcher = new UriMatcher(-1);
                String str = BaseInfo.getContext().getPackageName() + ".baseinfo.PrivacyInfoProvider";
                this.uriMatcher.addURI(str, "baseinfo_db2", 1);
                this.uriMatcher.addURI(str, "baseinfo_db2/#", 2);
            } catch (Exception e2) {
                Logger.e(TAG, "", e2);
            }
        }
        return this.uriMatcher;
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
        Logger.e(TAG, "try insert ".concat(String.valueOf(contentValues)));
        long insert = this.db.insert("baseinfo_db2", null, contentValues);
        if (insert > 0) {
            return ContentUris.withAppendedId(uri, insert);
        }
        throw new SQLException("Failed to add a record into ".concat(String.valueOf(uri)));
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        if (this.dbHelper == null) {
            a aVar = new a(getContext());
            this.dbHelper = aVar;
            this.db = aVar.getWritableDatabase();
            return true;
        }
        return true;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        int match = getUriMatcher().match(uri);
        Logger.e(TAG, "code=".concat(String.valueOf(match)));
        if (match == 1) {
            return this.db.query("baseinfo_db2", strArr, str, strArr2, null, null, str2, null);
        }
        throw new SQLException("Failed to find record  ");
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }
}
