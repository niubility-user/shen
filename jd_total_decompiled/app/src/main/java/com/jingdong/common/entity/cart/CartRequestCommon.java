package com.jingdong.common.entity.cart;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartRequestCommon extends CartRequestBase {
    public CartRequestCommon(ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2) {
        super(arrayList, arrayList2);
    }

    @Override // com.jingdong.common.entity.cart.CartRequestBase
    public JSONObject toParams() throws JSONException {
        return super.toParams();
    }

    public String toString() {
        return "CartRequestCommon [skus=" + this.skus + ", packs=" + this.packs + "]";
    }
}
