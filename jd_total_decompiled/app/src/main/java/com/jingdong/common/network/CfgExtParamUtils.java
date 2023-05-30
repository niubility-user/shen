package com.jingdong.common.network;

import androidx.annotation.Nullable;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CfgExtParamUtils {
    private static final String KEY_PRIVACY_OFFLINE = "privacyOffline";

    @Nullable
    public static String getExtParamsStr() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_PRIVACY_OFFLINE, JdSdk.getInstance().getPrivacyOffLineStatus() ? "1" : "0");
            return jSONObject.toString();
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d("CfgExtParamUtils", "getExtParamsStr error.", th);
            }
            return null;
        }
    }
}
