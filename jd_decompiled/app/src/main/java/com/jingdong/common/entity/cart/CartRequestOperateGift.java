package com.jingdong.common.entity.cart;

import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartRequestOperateGift extends CartRequestOperate {
    private ArrayList<CartPackGiftSummary> packsWithMustGifts;
    private ArrayList<CartSkuGiftSummary> skusWithMustGifts;

    public CartRequestOperateGift(ArrayList<CartSkuGiftSummary> arrayList, ArrayList<CartPackGiftSummary> arrayList2, String str) {
        super(str);
        this.skusWithMustGifts = new ArrayList<>();
        this.packsWithMustGifts = new ArrayList<>();
        this.skusWithMustGifts = arrayList;
        this.packsWithMustGifts = arrayList2;
    }

    @Override // com.jingdong.common.entity.cart.CartRequestOperate, com.jingdong.common.entity.cart.CartRequestBase
    public JSONObject toParams() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ArrayList<CartSkuGiftSummary> arrayList = this.skusWithMustGifts;
        if (arrayList != null && arrayList.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            Iterator<CartSkuGiftSummary> it = this.skusWithMustGifts.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toSummaryParams());
            }
            jSONObject.put(CartConstant.KEY_THE_SKUS, jSONArray);
        }
        ArrayList<CartPackGiftSummary> arrayList2 = this.packsWithMustGifts;
        if (arrayList2 != null && arrayList2.size() > 0) {
            JSONArray jSONArray2 = new JSONArray();
            Iterator<CartPackGiftSummary> it2 = this.packsWithMustGifts.iterator();
            while (it2.hasNext()) {
                jSONArray2.put(it2.next().toSummaryParams());
            }
            jSONObject.put(CartConstant.KEY_THE_PACKS, jSONArray2);
        }
        jSONObject.put(CartConstant.KEY_OPERATE_TYPE, getOperateType());
        return jSONObject;
    }

    public CartRequestOperateGift(String str) {
        super(str);
        this.skusWithMustGifts = new ArrayList<>();
        this.packsWithMustGifts = new ArrayList<>();
    }
}
