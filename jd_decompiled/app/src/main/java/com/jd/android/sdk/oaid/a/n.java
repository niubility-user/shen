package com.jd.android.sdk.oaid.a;

import android.content.Context;
import android.os.Build;
import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;
import com.vivo.identifier.IdentifierManager;

/* loaded from: classes12.dex */
public class n implements com.jd.android.sdk.oaid.a {
    private static final String a = "n";
    private Context b;

    public n(Context context) {
        this.b = context;
    }

    @Override // com.jd.android.sdk.oaid.a
    public final void a(OaidInfoRequestListener oaidInfoRequestListener) {
        String str;
        if (!a()) {
            oaidInfoRequestListener.onResult(new OaidInfo());
            return;
        }
        try {
            str = IdentifierManager.getOAID(this.b);
        } catch (Throwable th) {
            com.jd.android.sdk.oaid.b.a(a, "Catched !! getOaid", th);
            str = "";
        }
        oaidInfoRequestListener.onResult(new OaidInfo(str));
    }

    @Override // com.jd.android.sdk.oaid.a
    public final boolean a() {
        Context context = this.b;
        if (context != null && Build.VERSION.SDK_INT >= 26) {
            return IdentifierManager.isSupported(context);
        }
        return false;
    }
}
