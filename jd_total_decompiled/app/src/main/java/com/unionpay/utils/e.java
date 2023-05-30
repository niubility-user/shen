package com.unionpay.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import java.util.UUID;

/* loaded from: classes11.dex */
public final class e {
    public static String a(Context context) {
        try {
            String packageName = context instanceof Activity ? ((Activity) context).getPackageName() : "";
            return packageName == null ? "" : packageName;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Context context) {
        if (context != null) {
            try {
                String a = UPUtils.a(context, "merchant_id");
                if (TextUtils.isEmpty(a)) {
                    a = UUID.randomUUID().toString();
                    if (!TextUtils.isEmpty(a)) {
                        a = a.replaceAll("-", "");
                        UPUtils.a(context, a, "merchant_id");
                    }
                }
                return a;
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }
}
