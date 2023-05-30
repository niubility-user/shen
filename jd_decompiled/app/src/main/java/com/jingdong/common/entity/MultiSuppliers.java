package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class MultiSuppliers implements Serializable {
    private static final String TAG = "MultiSuppliers";
    private String fatherId;
    private String minPrice;
    private String supplierCount;

    public MultiSuppliers(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()));
    }

    private void update(JDJSONObject jDJSONObject) {
        if (OKLog.D) {
            OKLog.d(TAG, "MultiSuppliers() -->> json = " + jDJSONObject);
        }
        JDJSONObject jSONObject = jDJSONObject.getJSONObject("multiSuppliers");
        if (jSONObject != null) {
            setFatherId(jSONObject.getString("father_id"));
            setMinPrice(jSONObject.getString("min_price"));
            setSupplierCount(jSONObject.getString("supplier_count"));
        }
    }

    public String getFatherId() {
        return this.fatherId;
    }

    public String getMinPrice() {
        return this.minPrice;
    }

    public String getSupplierCount() {
        return this.supplierCount;
    }

    public String getText() {
        if (TextUtils.isEmpty(this.minPrice) || TextUtils.isEmpty(this.supplierCount)) {
            return null;
        }
        return this.supplierCount + String.format(StringUtil.productListMultiSeller, "\u00a5" + this.minPrice);
    }

    public boolean isMultiFlag() {
        return (TextUtils.isEmpty(this.minPrice) || TextUtils.isEmpty(this.supplierCount)) ? false : true;
    }

    public void setFatherId(String str) {
        this.fatherId = str;
    }

    public void setMinPrice(String str) {
        this.minPrice = str;
    }

    public void setSupplierCount(String str) {
        this.supplierCount = str;
    }

    public MultiSuppliers(JDJSONObject jDJSONObject) {
        update(jDJSONObject);
    }
}
