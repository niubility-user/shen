package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class CartCard {
    public int cardId;
    public List<CartElement> elements;

    public static ArrayList<CartCard> toList(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.size() == 0) {
            return null;
        }
        int size = jDJSONArray.size();
        ArrayList<CartCard> arrayList = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                CartCard cartCard = new CartCard();
                cartCard.cardId = optJSONObject.optInt(CartConstant.KEY_CARDID, -1);
                ArrayList<CartElement> list = CartElement.toList(optJSONObject.optJSONArray(CartConstant.KEY_ELEMS));
                cartCard.elements = list;
                if (list != null) {
                    arrayList.add(cartCard);
                }
            }
        }
        return arrayList;
    }
}
