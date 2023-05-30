package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CartPrescriptionInfo {
    public String name;

    public static HashMap<String, CartPrescriptionInfo> parseJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null || jDJSONObject.keySet() == null || jDJSONObject.keySet().size() <= 0) {
            return null;
        }
        HashMap<String, CartPrescriptionInfo> hashMap = new HashMap<>(jDJSONObject.keySet().size());
        for (String str : jDJSONObject.keySet()) {
            CartPrescriptionInfo parsePrescriptionInfo = parsePrescriptionInfo(jDJSONObject.optJSONObject(str));
            if (parsePrescriptionInfo != null) {
                hashMap.put(str, parsePrescriptionInfo);
            }
        }
        return hashMap;
    }

    private static CartPrescriptionInfo parsePrescriptionInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        CartPrescriptionInfo cartPrescriptionInfo = new CartPrescriptionInfo();
        cartPrescriptionInfo.name = jDJSONObject.optString("name", "");
        return cartPrescriptionInfo;
    }
}
