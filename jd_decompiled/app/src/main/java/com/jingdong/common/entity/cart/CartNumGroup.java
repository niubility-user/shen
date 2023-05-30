package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CartNumGroup {
    public static HashMap<String, Integer> parseJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return new HashMap<>(16);
        }
        HashMap<String, Integer> hashMap = new HashMap<>(jDJSONObject.keySet().size());
        for (String str : jDJSONObject.keySet()) {
            hashMap.put(str, Integer.valueOf(jDJSONObject.optInt(str)));
        }
        return hashMap;
    }
}
