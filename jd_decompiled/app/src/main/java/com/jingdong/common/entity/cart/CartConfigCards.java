package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class CartConfigCards {
    public HashMap<String, String> emptyABCards;
    public List<CartCard> emptyCards;
    public List<CartCard> recomCards;

    public static CartConfigCards toCartConfigCards(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray(CartConstant.KEY_CARDSEMPTY);
        JDJSONArray optJSONArray2 = jDJSONObject.optJSONArray(CartConstant.KEY_CARDSRECOM);
        if (optJSONArray == null && optJSONArray2 == null) {
            return null;
        }
        CartConfigCards cartConfigCards = new CartConfigCards();
        cartConfigCards.emptyCards = CartCard.toList(optJSONArray);
        cartConfigCards.recomCards = CartCard.toList(optJSONArray2);
        cartConfigCards.emptyABCards = CartABCards.parseJson(jDJSONObject.optJSONObject(CartConstant.KEY_SKU_ABCARDS));
        return cartConfigCards;
    }
}
