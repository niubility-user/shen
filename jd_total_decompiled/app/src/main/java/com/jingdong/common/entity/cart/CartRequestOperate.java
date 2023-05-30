package com.jingdong.common.entity.cart;

import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartRequestOperate extends CartRequestBase {
    private String operateType;

    public CartRequestOperate(ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, String str) {
        super(arrayList, arrayList2);
        this.operateType = str;
    }

    public String getOperateType() {
        String str = this.operateType;
        return str == null ? "" : str;
    }

    public void setOperateType(String str) {
        this.operateType = str;
    }

    @Override // com.jingdong.common.entity.cart.CartRequestBase
    public JSONObject toParams() throws JSONException {
        JSONObject params = super.toParams();
        params.put(CartConstant.KEY_OPERATE_TYPE, getOperateType());
        return params;
    }

    public String toString() {
        return "CartRequestOperate [operateType=" + this.operateType + ", skus=" + this.skus + ", packs=" + this.packs + "]";
    }

    public CartRequestOperate(CartSkuSummary cartSkuSummary, CartPackSummary cartPackSummary, String str) {
        super(cartSkuSummary, cartPackSummary);
        this.operateType = str;
    }

    public CartRequestOperate(String str) {
        this.operateType = str;
    }
}
