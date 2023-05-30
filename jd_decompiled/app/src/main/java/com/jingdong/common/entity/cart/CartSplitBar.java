package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CartSplitBar {
    public String text;

    public static HashMap<String, CartSplitBar> parseJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        HashMap<String, CartSplitBar> hashMap = new HashMap<>(jDJSONObject.keySet().size());
        for (String str : jDJSONObject.keySet()) {
            CartSplitBar parseSplitBar = parseSplitBar(jDJSONObject.optJSONObject(str));
            if (parseSplitBar != null) {
                hashMap.put(str, parseSplitBar);
            }
        }
        return hashMap;
    }

    private static CartSplitBar parseSplitBar(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        CartSplitBar cartSplitBar = new CartSplitBar();
        cartSplitBar.text = jDJSONObject.optString("text");
        return cartSplitBar;
    }
}
