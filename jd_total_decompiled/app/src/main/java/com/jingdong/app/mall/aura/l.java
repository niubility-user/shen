package com.jingdong.app.mall.aura;

import com.jingdong.corelib.utils.Log;
import org.json.JSONObject;

/* loaded from: classes19.dex */
public class l {
    public static String a(String str, int i2, String str2, String str3, String str4, String str5) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bundleName", str);
            jSONObject.put("bundleVersion", i2);
            jSONObject.put("apkVersionName", str2);
            jSONObject.put("apkVersionCode", str3);
            jSONObject.put("flag", str4);
            jSONObject.put("msg", str5);
        } catch (Throwable unused) {
            Log.i("BiDataFormat", "format error");
        }
        return jSONObject.toString();
    }
}
