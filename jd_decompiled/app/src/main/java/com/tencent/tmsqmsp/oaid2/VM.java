package com.tencent.tmsqmsp.oaid2;

import android.content.Context;

/* loaded from: classes9.dex */
public class VM {
    public static int getVendorInfo(Context context, IVendorCallback iVendorCallback) {
        return new VendorManager().getVendorInfo(context, iVendorCallback);
    }
}
