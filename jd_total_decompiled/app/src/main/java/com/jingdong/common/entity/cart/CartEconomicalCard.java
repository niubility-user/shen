package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CartEconomicalCard {
    public String plusCardParam;
    public HashMap<String, String> plusCardShow;

    public static CartEconomicalCard parseJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null || jDJSONObject.size() <= 0) {
            return null;
        }
        CartEconomicalCard cartEconomicalCard = new CartEconomicalCard();
        cartEconomicalCard.plusCardParam = jDJSONObject.optString("plusCardParam");
        cartEconomicalCard.plusCardShow = CartABCards.parseJsonToMap(jDJSONObject.optJSONObject("plusCardShow"));
        return cartEconomicalCard;
    }
}
