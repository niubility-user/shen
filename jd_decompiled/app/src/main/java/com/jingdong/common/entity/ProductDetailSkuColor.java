package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ProductDetailSkuColor implements Serializable {
    private static final String TAG = "ProductDetailSkuColor";
    private static final long serialVersionUID = -1810442797591816788L;
    public String color;
    public String skuId;

    public ProductDetailSkuColor() {
    }

    public static ArrayList<ProductDetailSkuColor> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        if (jSONArrayPoxy == null) {
            return null;
        }
        return toList(JDJSON.parseArray(jSONArrayPoxy.toString()), i2);
    }

    public String getColor() {
        return TextUtils.isEmpty(this.color) ? "" : this.color;
    }

    public String getSkuId() {
        return TextUtils.isEmpty(this.skuId) ? "" : this.skuId;
    }

    private ProductDetailSkuColor(JDJSONObject jDJSONObject, int i2) {
        if (i2 == 3 || i2 == 1111) {
            this.skuId = jDJSONObject.getString("skuId");
            this.color = jDJSONObject.getString("color");
        }
    }

    public static ArrayList<ProductDetailSkuColor> toList(JDJSONArray jDJSONArray, int i2) {
        ArrayList<ProductDetailSkuColor> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return null;
        }
        for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
            try {
                arrayList.add(new ProductDetailSkuColor(jDJSONArray.getJSONObject(i3), i2));
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
        return arrayList;
    }
}
