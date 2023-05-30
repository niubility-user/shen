package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes5.dex */
public class CartLuxuryTopFloors {
    public JDJSONArray simpleVendorList;

    public CartLuxuryTopFloors() {
    }

    public static CartLuxuryTopFloors parseJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        try {
            return new CartLuxuryTopFloors(jDJSONObject);
        } catch (Throwable th) {
            ExceptionReporter.reportExceptionToBugly(th);
            return null;
        }
    }

    public CartLuxuryTopFloors(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.simpleVendorList = jDJSONObject.optJSONArray("simpleVendorList");
        }
    }
}
