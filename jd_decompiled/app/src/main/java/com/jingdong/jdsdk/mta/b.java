package com.jingdong.jdsdk.mta;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.ad.AdUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class b {
    private static final Map<String, Integer> a;

    static {
        HashMap hashMap = new HashMap(16);
        a = hashMap;
        hashMap.put("UnknownHostException", Integer.valueOf((int) R2.attr.layout_constraintCircleAngle));
        hashMap.put(AdUtils.TIMEOUT_EXCEPTION, Integer.valueOf((int) R2.attr.layout_constraintCircleRadius));
        hashMap.put("ConnectException", Integer.valueOf((int) R2.attr.layout_constraintDimensionRatio));
        hashMap.put("IOException", Integer.valueOf((int) R2.attr.layout_constraintEnd_toEndOf));
        hashMap.put("IllegalStateException", Integer.valueOf((int) R2.attr.layout_constraintEnd_toStartOf));
        hashMap.put("SocketException", Integer.valueOf((int) R2.attr.layout_constraintGuide_begin));
        hashMap.put("StreamResetException", Integer.valueOf((int) R2.attr.layout_constraintGuide_end));
    }

    public static void a(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull int i2, Exception exc, String str3) {
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("module", str);
                jSONObject.put("functionId", str2);
                if (!TextUtils.isEmpty(str3)) {
                    jSONObject.put("extParams", str3);
                }
                jSONObject.put("type", CartConstant.KEY_CART_TEXTINFO_FINISH);
                jSONObject.put(XView2Constants.STATE, i2);
                if (exc != null) {
                    jSONObject.put("exceptionCode", b(exc));
                }
                JDMtaUtils.sendExposureDataWithExt(context, "dynBusinessRequest", "", "", "", "", jSONObject.toString(), "", "", "", null);
            } catch (Exception unused) {
            }
        }
    }

    private static int b(Exception exc) {
        if (TextUtils.isEmpty(exc.getMessage())) {
            return R2.attr.layout_constraintGuide_percent;
        }
        for (String str : a.keySet()) {
            if (exc.getMessage().contains(str)) {
                return a.get(str).intValue();
            }
        }
        return R2.attr.layout_constraintGuide_percent;
    }

    public static void c(@NonNull Context context, @NonNull String str, @NonNull String str2, String str3) {
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("module", str);
                jSONObject.put("functionId", str2);
                if (!TextUtils.isEmpty(str3)) {
                    jSONObject.put("extParams", str3);
                }
                jSONObject.put("type", "start");
                JDMtaUtils.sendExposureDataWithExt(context, "dynBusinessRequest", "", "", "", "", jSONObject.toString(), "", "", "", null);
            } catch (Exception unused) {
            }
        }
    }
}
