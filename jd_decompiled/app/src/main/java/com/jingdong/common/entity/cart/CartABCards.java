package com.jingdong.common.entity.cart;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CartABCards {
    static HashMap<String, String> abCardsCache;

    public static String getAbResult(String str) {
        if (abCardsCache == null || TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = abCardsCache.get(str);
        return TextUtils.isEmpty(str2) ? "" : str2;
    }

    public static HashMap<String, String> parseJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap<>(jDJSONObject.keySet().size());
        for (String str : jDJSONObject.keySet()) {
            hashMap.put(str, jDJSONObject.optString(str));
        }
        abCardsCache = hashMap;
        return hashMap;
    }

    public static HashMap<String, String> parseJsonToMap(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap<>(jDJSONObject.keySet().size());
        for (String str : jDJSONObject.keySet()) {
            hashMap.put(str, jDJSONObject.optString(str));
        }
        return hashMap;
    }
}
