package com.jingdong.manto.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.a;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.utils.MantoLog;
import java.util.List;

/* loaded from: classes16.dex */
public class LoginInfoProvider extends ContentProvider {
    private final UriMatcher a = new UriMatcher(-1);

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
        this.a.addURI(getContext().getPackageName() + ".manto.loginInfoProvider", "#", 0);
        return true;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        ILogin iLogin = (ILogin) a.n(ILogin.class);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"colName", "result"});
        if (iLogin == null) {
            return matrixCursor;
        }
        int i2 = -1;
        if (this.a.match(uri) == 0) {
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments != null && pathSegments.size() > 0) {
                try {
                    i2 = Integer.parseInt(pathSegments.get(0));
                } catch (Exception unused) {
                }
            }
            if (i2 == 1) {
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(i2);
                objArr[1] = iLogin.hasLogin() ? "1" : "0";
                matrixCursor.addRow(objArr);
            } else if (i2 == 2) {
                matrixCursor.addRow(new Object[]{Integer.valueOf(i2), iLogin.getPin(getContext())});
            } else if (i2 == 3) {
                matrixCursor.addRow(new Object[]{Integer.valueOf(i2), iLogin.getA2(getContext())});
            } else if (i2 == 4) {
                matrixCursor.addRow(new Object[]{Integer.valueOf(i2), iLogin.getCookie(getContext())});
            }
        }
        MantoLog.d("uProvide", String.format("invoked:%s", Integer.valueOf(i2)));
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }
}
