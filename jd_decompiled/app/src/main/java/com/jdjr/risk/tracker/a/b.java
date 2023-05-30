package com.jdjr.risk.tracker.a;

import android.content.Context;
import android.os.Build;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.sdk.baseinfo.BaseInfo;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class b {
    public static String a(Context context) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(BaseInfo.getDeviceBrand());
            sb.append(":");
            sb.append(BaseInfo.getDeviceModel());
            String sb2 = sb.toString();
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            String str2 = Build.VERSION.RELEASE;
            String a = a.a();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CustomThemeConstance.NAVI_MODEL, sb2);
            jSONObject.put(HybridSDK.APP_VERSION, str);
            jSONObject.put(HybridSDK.OS_VERSION, str2);
            jSONObject.put("cpuabi", a);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
