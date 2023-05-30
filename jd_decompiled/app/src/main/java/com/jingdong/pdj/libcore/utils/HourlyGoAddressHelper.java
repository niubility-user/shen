package com.jingdong.pdj.libcore.utils;

import android.text.TextUtils;
import com.jd.jdsdk.security.AesCbcCrypto;
import com.jd.libs.hybrid.HybridSDK;
import java.net.URLEncoder;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class HourlyGoAddressHelper {
    private static final String S_KEY = "8y/B?E(H+MbQeThW";
    private static final String S_PARAMETER = "hWmZq4t7w!z%C*F)";

    private static String encrypt(String str) {
        String encrypt = AesCbcCrypto.encrypt(str, S_KEY, S_PARAMETER.getBytes());
        return encrypt == null ? "" : encrypt;
    }

    public static String generateGeo(double d, double d2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(HybridSDK.LNG, d2);
            jSONObject.put("lat", d);
            String jSONObject2 = jSONObject.toString();
            if (TextUtils.isEmpty(jSONObject2)) {
                return null;
            }
            String encrypt = encrypt(jSONObject2);
            if (TextUtils.isEmpty(encrypt)) {
                return null;
            }
            return URLEncoder.encode(encrypt, "utf-8");
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean isAddressInvalid(double d, double d2, String str) {
        return d == 0.0d || d2 == 0.0d || TextUtils.isEmpty(str);
    }
}
