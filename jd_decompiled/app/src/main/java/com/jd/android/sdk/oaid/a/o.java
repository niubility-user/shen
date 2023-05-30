package com.jd.android.sdk.oaid.a;

import android.content.Context;
import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;

/* loaded from: classes12.dex */
public final class o implements com.jd.android.sdk.oaid.a {
    private final Context a;
    private Class<?> b;

    /* renamed from: c  reason: collision with root package name */
    private Object f1698c;

    public o(Context context) {
        this.a = context;
        try {
            Class<?> cls = Class.forName("com.android.id.impl.IdProviderImpl");
            this.b = cls;
            this.f1698c = cls.newInstance();
        } catch (Exception unused) {
        }
    }

    @Override // com.jd.android.sdk.oaid.a
    public final void a(OaidInfoRequestListener oaidInfoRequestListener) {
        OaidInfo oaidInfo;
        Class<?> cls;
        if (this.a == null || (cls = this.b) == null || this.f1698c == null) {
            oaidInfo = new OaidInfo();
        } else {
            String str = "";
            try {
                Object invoke = cls.getMethod("getOAID", Context.class).invoke(this.f1698c, this.a);
                if (invoke != null) {
                    str = (String) invoke;
                }
            } catch (Exception e2) {
                com.jd.android.sdk.oaid.b.a("XiaomiImpl", "Catched !! getOaid", e2);
            }
            oaidInfo = new OaidInfo(str);
        }
        oaidInfoRequestListener.onResult(oaidInfo);
    }

    @Override // com.jd.android.sdk.oaid.a
    public final boolean a() {
        return (this.f1698c == null || this.b == null) ? false : true;
    }
}
