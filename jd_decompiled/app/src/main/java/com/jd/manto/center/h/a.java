package com.jd.manto.center.h;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class a {
    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "-1" : str;
    }

    public static void b(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, JDJSONObject jDJSONObject) {
        JDMtaUtils.sendClickDataWithExt(context, str, str2, str3, str4, str5, str6, str7, jDJSONObject == null ? "" : jDJSONObject.toString(), "", "", "", "", null);
    }

    public static void c(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, JSONObject jSONObject) {
        JDMtaUtils.sendClickDataWithExt(context, str, str2, str3, str4, str5, str6, str7, jSONObject == null ? "" : jSONObject.toString(), "", "", "", "", null);
    }

    public static void d(Context context, Object obj, String str, String str2, String str3) {
        JDMtaUtils.sendPagePv(context, obj, str, str2, str3);
    }
}
