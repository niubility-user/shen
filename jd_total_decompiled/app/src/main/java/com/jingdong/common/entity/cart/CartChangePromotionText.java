package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class CartChangePromotionText {
    public String text;
    public String type;

    public CartChangePromotionText(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.type = jDJSONObject.optString("type");
        this.text = jDJSONObject.optString("text");
    }

    public static List<CartChangePromotionText> parseCartChangePromotionTextList(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            arrayList.add(new CartChangePromotionText(jDJSONArray.optJSONObject(i2)));
        }
        return arrayList;
    }
}
