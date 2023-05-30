package com.jingdong.common.entity.cart;

import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartRequestOperateCoupon extends CartRequestOperate {
    private ArrayList<CartCouponSummary> skus;

    public CartRequestOperateCoupon(ArrayList<CartCouponSummary> arrayList, String str) {
        super(str);
        this.skus = new ArrayList<>();
        this.skus = arrayList;
    }

    @Override // com.jingdong.common.entity.cart.CartRequestOperate, com.jingdong.common.entity.cart.CartRequestBase
    public JSONObject toParams() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ArrayList<CartCouponSummary> arrayList = this.skus;
        if (arrayList != null && arrayList.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            Iterator<CartCouponSummary> it = this.skus.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toSummaryParams());
            }
            jSONObject.put(CartConstant.KEY_THE_SKUS, jSONArray);
        }
        return jSONObject;
    }
}
