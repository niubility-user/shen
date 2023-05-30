package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;

/* loaded from: classes5.dex */
public class CartTitleInfo {
    public int style;
    public String value;

    public CartTitleInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.value = jDJSONObject.optString("value");
        this.style = jDJSONObject.optInt(DeeplinkProductDetailHelper.LAYER_STYLE);
    }
}
