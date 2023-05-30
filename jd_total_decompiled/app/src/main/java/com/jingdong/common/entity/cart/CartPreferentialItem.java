package com.jingdong.common.entity.cart;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CartPreferentialItem {
    public String businessType;
    public String itemName;
    public String itemType;
    public String label;
    public String price;
    public String subItemName;
    public ArrayList<String> subItemNames;
    public ArrayList<HashMap<String, String>> subItemTypes;
    public String subPrice;

    public CartPreferentialItem(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.itemType = jDJSONObject.optString(CartConstant.KEY_VENDOR_ITEM_TYPE);
        this.itemName = jDJSONObject.optString("itemName");
        this.price = jDJSONObject.optString("price");
        this.subItemName = jDJSONObject.optString("subItemName");
        this.subPrice = jDJSONObject.optString("subPrice");
        this.label = jDJSONObject.optString(Constant.KEY_PROMOTION_LABEL);
        this.businessType = jDJSONObject.optString("businessType");
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray("subItemTypes");
        if (optJSONArray != null && optJSONArray.size() > 0) {
            int size = jDJSONObject.size();
            this.subItemTypes = new ArrayList<>(size);
            for (int i2 = 0; i2 < size; i2++) {
                JDJSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null && optJSONObject.size() > 0) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("subTitle", optJSONObject.optString("subTitle"));
                    hashMap.put("subTag", optJSONObject.optString("subTag"));
                    this.subItemTypes.add(hashMap);
                }
            }
        }
        JDJSONArray optJSONArray2 = jDJSONObject.optJSONArray("subItemNames");
        if (optJSONArray2 == null || optJSONArray2.size() <= 0) {
            return;
        }
        int size2 = optJSONArray2.size();
        this.subItemNames = new ArrayList<>(size2);
        for (int i3 = 0; i3 < size2; i3++) {
            String optString = optJSONArray2.optString(i3);
            if (!TextUtils.isEmpty(optString)) {
                this.subItemNames.add(optString);
            }
        }
    }

    public static ArrayList<CartPreferentialItem> toList(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.size() <= 0) {
            return null;
        }
        int size = jDJSONArray.size();
        ArrayList<CartPreferentialItem> arrayList = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
            if (jSONObject != null) {
                arrayList.add(new CartPreferentialItem(jSONObject));
            }
        }
        return arrayList;
    }
}
