package com.jd.android.sdk.oaid.a;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;

/* loaded from: classes12.dex */
public class f implements com.jd.android.sdk.oaid.a {
    private static final String a = "f";
    private final Context b;

    public f(Context context) {
        this.b = context;
    }

    @Override // com.jd.android.sdk.oaid.a
    public final void a(OaidInfoRequestListener oaidInfoRequestListener) {
        if (this.b == null) {
            return;
        }
        if (!a()) {
            oaidInfoRequestListener.onResult(new OaidInfo());
            return;
        }
        String str = "";
        try {
            Cursor query = this.b.getContentResolver().query(Uri.parse("content://com.meizu.flyme.openidsdk/"), null, null, new String[]{"oaid"}, null);
            query.getClass();
            query.moveToFirst();
            str = query.getString(query.getColumnIndex("value"));
            com.jd.android.sdk.oaid.b.a(a, "OAID query result: ".concat(String.valueOf(str)));
            if (query != null) {
                query.close();
            }
        } catch (Exception e2) {
            com.jd.android.sdk.oaid.b.a(a, "getOaid exception : ", e2);
        }
        oaidInfoRequestListener.onResult(new OaidInfo(str));
    }

    @Override // com.jd.android.sdk.oaid.a
    public final boolean a() {
        Context context = this.b;
        if (context != null && Build.VERSION.SDK_INT >= 29) {
            try {
                return context.getPackageManager().resolveContentProvider("com.meizu.flyme.openidsdk", 0) != null;
            } catch (Exception e2) {
                com.jd.android.sdk.oaid.b.a(a, "", e2);
                return false;
            }
        }
        return false;
    }
}
