package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ProductDetailSkuSize implements Serializable {
    private static final String TAG = "ProductDetailSkuSize";
    private static final long serialVersionUID = 5496553802650926805L;
    public String size;
    public String skuId;

    public ProductDetailSkuSize() {
    }

    public static ArrayList<ProductDetailSkuSize> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        if (jSONArrayPoxy == null) {
            return null;
        }
        return toList(JDJSON.parseArray(jSONArrayPoxy.toString()), i2);
    }

    public String getSize() {
        return TextUtils.isEmpty(this.size) ? "" : this.size;
    }

    public String getSkuId() {
        return TextUtils.isEmpty(this.skuId) ? "" : this.skuId;
    }

    private ProductDetailSkuSize(JDJSONObject jDJSONObject, int i2) {
        if (i2 == 3 || i2 == 1111) {
            this.skuId = jDJSONObject.getString("skuId");
            this.size = jDJSONObject.getString(ApkDownloadTable.FIELD_SIZE);
        }
    }

    public static ArrayList<ProductDetailSkuSize> toList(JDJSONArray jDJSONArray, int i2) {
        ArrayList<ProductDetailSkuSize> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return null;
        }
        for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
            try {
                arrayList.add(new ProductDetailSkuSize(jDJSONArray.getJSONObject(i3), i2));
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
        return arrayList;
    }
}
