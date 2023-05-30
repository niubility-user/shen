package com.jd.lib.cashier.sdk.core.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class d {
    private static HashMap<String, String> a = new HashMap<>();

    public static String a(String str) {
        HashMap<String, String> hashMap = a;
        if (hashMap != null) {
            return hashMap.get(str);
        }
        return null;
    }

    public static String b(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return Build.VERSION.SDK_INT >= 17 ? Settings.Global.getString(context.getApplicationContext().getContentResolver(), "cupmobilepaystatus") : "";
        } catch (Exception e2) {
            r.d("AndroidPayUtil", e2.getMessage());
            com.jd.lib.cashier.sdk.d.h.a.a("GetPaySdkStatusFunction", "GetPaySdkStatusNorException", "AndroidPayUtil.getSettingValue()", e2.getMessage());
            return "";
        }
    }

    public static String c() {
        return (d(a("Value1")) && d(a("Value5")) && com.jd.lib.cashier.sdk.h.h.d.a(a("Value4")) > 0) ? "2" : "0";
    }

    private static boolean d(String str) {
        return "1".equals(str);
    }

    public static void e(Context context) {
        String[] split;
        try {
            HashMap<String, String> hashMap = a;
            if (hashMap != null) {
                hashMap.clear();
            }
            String b = b(context);
            if (TextUtils.isEmpty(b) || !b.contains("|") || (split = b.split(DYConstants.DY_REGEX_VERTICAL_LINE)) == null) {
                return;
            }
            int i2 = 0;
            while (i2 < split.length) {
                HashMap<String, String> hashMap2 = a;
                StringBuilder sb = new StringBuilder();
                sb.append("Value");
                int i3 = i2 + 1;
                sb.append(i3);
                hashMap2.put(sb.toString(), split[i2]);
                i2 = i3;
            }
        } catch (Exception e2) {
            r.d("AndroidPayUtil", e2.getMessage());
            com.jd.lib.cashier.sdk.d.h.a.a("InItPaySdkFunction", "InItPaySdkNorException", "AndroidPayUtil.processingData()", e2.getMessage());
        }
    }
}
