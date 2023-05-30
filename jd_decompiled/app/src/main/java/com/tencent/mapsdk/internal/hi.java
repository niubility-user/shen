package com.tencent.mapsdk.internal;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.tmsqmsp.oaid2.IVendorCallback;
import com.tencent.tmsqmsp.oaid2.VendorManager;

/* loaded from: classes9.dex */
public final class hi {
    private static final String a = "TMS-Oaid";
    private static String b = "";

    /* renamed from: c */
    private static boolean f16667c = true;
    private static IVendorCallback d = new a();

    /* loaded from: classes9.dex */
    public static class a implements IVendorCallback {
        @Override // com.tencent.tmsqmsp.oaid2.IVendorCallback
        public void onResult(boolean z, String str, String str2) {
            String str3 = "isSupport: " + z + " s: " + str + " oaid: " + str2;
            boolean unused = hi.f16667c = z;
            if (z) {
                String unused2 = hi.b = str2;
            }
        }
    }

    public static String a(Context context) {
        if (f16667c) {
            if (TextUtils.isEmpty(b) || !f16667c) {
                try {
                    new VendorManager().getVendorInfo(context, d);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return b;
            }
            return b;
        }
        return "undefined";
    }
}
