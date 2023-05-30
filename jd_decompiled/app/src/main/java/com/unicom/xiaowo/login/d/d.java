package com.unicom.xiaowo.login.d;

import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.mapsdk.internal.l4;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class d {
    private static ExecutorService a = Executors.newFixedThreadPool(2);

    public static void a(String str, String str2) {
        final String str3 = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appId", f.a());
            jSONObject.put("deviceId", f.i());
            jSONObject.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand());
            jSONObject.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
            jSONObject.put("os", "" + Build.VERSION.SDK_INT);
            jSONObject.put("errCode", str);
            jSONObject.put("message", str2);
            jSONObject.put(l4.f16791e, "5.1.1AR02B0825");
            jSONObject.put("apn", f.d());
            jSONObject.put("appName", f.h());
            jSONObject.put("pip", f.e());
            jSONObject.put("netType", "" + f.g());
            jSONObject.put("userTimeout", "" + f.f());
            jSONObject.put("operateTime", "0");
            str3 = jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        a.submit(new Runnable() { // from class: com.unicom.xiaowo.login.d.d.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    new com.unicom.xiaowo.login.c.b().a("https://opencloud.wostore.cn/client/sdk/receive", str3);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        });
    }
}
