package com.jd.android.sdk.oaid.a;

import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;

/* loaded from: classes12.dex */
public class c implements com.jd.android.sdk.oaid.a {
    private static final String a = "c";

    @Override // com.jd.android.sdk.oaid.a
    public final void a(OaidInfoRequestListener oaidInfoRequestListener) {
        oaidInfoRequestListener.onResult(new OaidInfo());
    }

    @Override // com.jd.android.sdk.oaid.a
    public final boolean a() {
        return false;
    }
}
