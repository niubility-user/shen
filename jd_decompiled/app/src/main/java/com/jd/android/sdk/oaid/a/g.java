package com.jd.android.sdk.oaid.a;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;

/* loaded from: classes12.dex */
public class g implements com.jd.android.sdk.oaid.a {
    private static final String a = "g";
    private Context b;

    public g(Context context) {
        this.b = context;
    }

    @Override // com.jd.android.sdk.oaid.a
    public final void a(OaidInfoRequestListener oaidInfoRequestListener) {
        ContentProviderClient acquireContentProviderClient;
        if (this.b == null) {
            return;
        }
        if (!a()) {
            oaidInfoRequestListener.onResult(new OaidInfo());
            return;
        }
        try {
            acquireContentProviderClient = this.b.getContentResolver().acquireContentProviderClient(Uri.parse("content://cn.nubia.identity/identity"));
        } catch (Exception e2) {
            com.jd.android.sdk.oaid.b.a(a, "OAID quer exception : ", e2);
        }
        if (acquireContentProviderClient == null) {
            return;
        }
        Bundle call = acquireContentProviderClient.call("getOAID", null, null);
        if (Build.VERSION.SDK_INT >= 24) {
            acquireContentProviderClient.close();
        } else {
            acquireContentProviderClient.release();
        }
        if (call == null) {
            throw new h("OAID query failed: bundle is null");
        }
        r0 = call.getInt("code", -1) == 0 ? call.getString("id") : null;
        if (TextUtils.isEmpty(r0)) {
            r0 = "";
        }
        com.jd.android.sdk.oaid.b.a(a, "OAID query success: ".concat(String.valueOf(r0)));
        oaidInfoRequestListener.onResult(new OaidInfo(r0));
    }

    @Override // com.jd.android.sdk.oaid.a
    public final boolean a() {
        return this.b != null && Build.VERSION.SDK_INT >= 29;
    }
}
