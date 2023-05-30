package com.jd.android.sdk.oaid.a;

import android.content.Context;
import android.os.Build;
import com.heytap.openid.sdk.OpenIDSDK;
import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;

/* loaded from: classes12.dex */
public class k implements com.jd.android.sdk.oaid.a {
    private static final String b = "k";
    Context a;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1696c = false;

    public k(Context context) {
        this.a = context;
        c();
    }

    private void c() {
        try {
            OpenIDSDK.init(this.a);
            this.f1696c = true;
        } catch (Throwable th) {
            com.jd.android.sdk.oaid.b.a(b, "OPPO HeytapIDSDK init Exception: ", th);
        }
    }

    @Override // com.jd.android.sdk.oaid.a
    public final void a(final OaidInfoRequestListener oaidInfoRequestListener) {
        if (a()) {
            new Thread(new Runnable() { // from class: com.jd.android.sdk.oaid.a.k.1
                @Override // java.lang.Runnable
                public final void run() {
                    String str;
                    try {
                        str = OpenIDSDK.getOAID(k.this.a);
                    } catch (Throwable th) {
                        com.jd.android.sdk.oaid.b.a(k.b, "getOaid", th);
                        str = "";
                    }
                    oaidInfoRequestListener.onResult(new OaidInfo(str));
                }
            }).start();
        } else {
            oaidInfoRequestListener.onResult(new OaidInfo());
        }
    }

    @Override // com.jd.android.sdk.oaid.a
    public final boolean a() {
        if (this.a != null && Build.VERSION.SDK_INT >= 26) {
            if (!this.f1696c) {
                c();
            }
            return OpenIDSDK.isSupported();
        }
        return false;
    }
}
