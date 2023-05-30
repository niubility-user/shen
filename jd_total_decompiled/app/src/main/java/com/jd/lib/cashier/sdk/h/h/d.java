package com.jd.lib.cashier.sdk.h.h;

import android.text.TextUtils;

/* loaded from: classes14.dex */
public class d {
    public static int a(String str) {
        return b(str, 0);
    }

    public static int b(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return i2;
        }
    }
}
