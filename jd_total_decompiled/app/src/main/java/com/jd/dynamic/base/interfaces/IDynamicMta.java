package com.jd.dynamic.base.interfaces;

import android.content.Context;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public interface IDynamicMta {
    void clickMtaEvent(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7);

    void clickMtaEventWithExtParams(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, JSONObject jSONObject);

    void expoMtaEvent(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7);

    void expoMtaEventWithExtParams(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, JSONObject jSONObject);

    String getMtaValue(String str);

    void pvMtaEvent(Context context, Object obj, String str, String str2);

    void pvMtaEventWithExtParams(Context context, Object obj, String str, String str2, JSONObject jSONObject);
}
