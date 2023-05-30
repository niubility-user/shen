package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class SkuOrderInfo {
    public String authUrl;
    public String orderSubTitle;
    public String orderTitle;
    public String subTitleType;

    public static HashMap<String, SkuOrderInfo> parseJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        HashMap<String, SkuOrderInfo> hashMap = new HashMap<>(jDJSONObject.keySet().size());
        for (String str : jDJSONObject.keySet()) {
            SkuOrderInfo parseOrderInfo = parseOrderInfo(jDJSONObject.optJSONObject(str));
            if (parseOrderInfo != null) {
                hashMap.put(str, parseOrderInfo);
            }
        }
        return hashMap;
    }

    private static SkuOrderInfo parseOrderInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        SkuOrderInfo skuOrderInfo = new SkuOrderInfo();
        skuOrderInfo.authUrl = jDJSONObject.optString("authUrl");
        skuOrderInfo.orderTitle = jDJSONObject.optString("orderTitle");
        skuOrderInfo.orderSubTitle = jDJSONObject.optString("orderSubTitle");
        skuOrderInfo.subTitleType = jDJSONObject.optString("subTitleType", "0");
        return skuOrderInfo;
    }
}
