package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class CartConfigInfo {
    public static final String KEY_CONFIG_DATA = "config";
    public static final String KEY_VERSION = "configVersion";
    public volatile CartConfigDetail cartConfigDetail;
    public volatile int configVersion;

    public static JDJSONObject jointData(JDJSONObject jDJSONObject, int i2) {
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        jDJSONObject2.put("configVersion", (Object) Integer.valueOf(i2));
        jDJSONObject2.put("config", (Object) jDJSONObject);
        return jDJSONObject2;
    }

    public static CartConfigInfo parseConfigInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        CartConfigInfo cartConfigInfo = new CartConfigInfo();
        cartConfigInfo.configVersion = jDJSONObject.optInt("configVersion");
        cartConfigInfo.cartConfigDetail = CartConfigDetail.parseDetail(jDJSONObject.optJSONObject("config"));
        return cartConfigInfo;
    }
}
