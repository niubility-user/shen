package com.jingdong.jdsdk.a;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes14.dex */
public class a {
    public static final String a = "a";

    private static boolean a() {
        return "xiaomi".equalsIgnoreCase(BaseInfo.getDeviceManufacture()) || "nubia".equalsIgnoreCase(BaseInfo.getDeviceBrand());
    }

    public static String b(Context context) {
        String userAgent;
        String str = "";
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (Build.VERSION.SDK_INT >= 17 && c() && !a()) {
                userAgent = WebSettings.getDefaultUserAgent(context);
            } else {
                userAgent = BaseInfo.getUserAgent(context.getApplicationContext(), 3000L);
            }
            str = userAgent;
            long currentTimeMillis2 = System.currentTimeMillis();
            if (OKLog.D) {
                String str2 = a;
                OKLog.d(str2, "get user-agent : " + str);
                OKLog.d(str2, "get user-agent cost time " + (currentTimeMillis2 - currentTimeMillis) + " ms");
            }
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(a, th);
            }
        }
        return str;
    }

    private static boolean c() {
        String config = JDMobileConfig.getInstance().getConfig("BaseInfo", "switch", "useDefaultUA", "0");
        if (OKLog.D) {
            OKLog.d(a, "useDefaultUA switch\uff1a " + config);
        }
        return TextUtils.equals(config, "1");
    }
}
