package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartCustomDigestInfo {
    public String attrId;
    public String attrName;
    public String attrValueId;
    public String attrValueName;
    public String attrValueNum;
    public String attrValuePrice;
    public String customType;
    public String digestInfoId;
    public String type;
    public String unitPrice;
    public String url;

    public CartCustomDigestInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.digestInfoId = jDJSONObject.optString("digestInfoId");
        this.attrId = jDJSONObject.optString("attrId");
        this.attrName = jDJSONObject.optString("attrName");
        this.attrValueId = jDJSONObject.optString("attrValueId");
        this.attrValueName = jDJSONObject.optString("attrValueName");
        this.unitPrice = jDJSONObject.optString("unitPrice");
        this.customType = jDJSONObject.optString("customType");
        this.url = jDJSONObject.optString("url");
        this.type = jDJSONObject.optString("type");
        this.attrValueNum = jDJSONObject.optString("attrValueNum");
        this.attrValuePrice = jDJSONObject.optString("attrValuePrice");
    }

    public static ArrayList<CartCustomDigestInfo> toList(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.size() <= 0) {
            return null;
        }
        int size = jDJSONArray.size();
        ArrayList<CartCustomDigestInfo> arrayList = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
            if (jSONObject != null) {
                arrayList.add(new CartCustomDigestInfo(jSONObject));
            }
        }
        return arrayList;
    }
}
