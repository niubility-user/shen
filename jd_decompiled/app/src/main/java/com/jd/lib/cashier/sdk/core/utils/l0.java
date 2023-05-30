package com.jd.lib.cashier.sdk.core.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class l0 {
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
            if (Build.VERSION.SDK_INT >= 17) {
                return Settings.Global.getString(context.getApplicationContext().getContentResolver(), "huaweipay_status_prm");
            }
            return BaseInfo.getAndroidIdWithAOPBySystem(context.getApplicationContext().getContentResolver(), "huaweipay_status_prm");
        } catch (Exception e2) {
            r.d("HuaWeiPayUtil", e2.getMessage());
            com.jd.lib.cashier.sdk.d.h.a.a("GetPaySdkStatusFunction", "GetPaySdkStatusNorException", "HuaWeiPayUtil.getSettingValue()", e2.getMessage());
            return "";
        }
    }

    public static String c() {
        return (!d(a("Value5")) || com.jd.lib.cashier.sdk.h.h.d.a(a("Value4")) <= 0) ? "0" : "2";
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
            r.d("HuaWeiPayUtil", e2.getMessage());
            com.jd.lib.cashier.sdk.d.h.a.a("InItPaySdkFunction", "InItPaySdkNorException", "HuaWeiPayUtil.processingData()", e2.getMessage());
        }
    }
}
