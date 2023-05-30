package com.jingdong.common.entity;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jump.JumpUtil;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class GraduallyPayPopupEntity implements Serializable {
    public String alreadyPaid;
    public String continueToPay;
    public String continueToPayUrl;
    public String discount;
    public String payRemain;
    public String payRemainTitle;
    public String paySuccess;
    public String viewOrder;
    public String viewOrderUrl;

    public GraduallyPayPopupEntity(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.paySuccess = jDJSONObject.getString(JumpUtil.VAULE_DES_REACTNATIVE_PAYSUCCESS);
            this.payRemain = jDJSONObject.getString("payRemain");
            this.viewOrder = jDJSONObject.getString("viewOrder");
            this.continueToPay = jDJSONObject.getString("continueToPay");
            this.viewOrderUrl = jDJSONObject.getString("viewOrderUrl");
            this.continueToPayUrl = jDJSONObject.getString("continueToPayUrl");
            this.alreadyPaid = jDJSONObject.getString("alreadyPaid");
            this.discount = jDJSONObject.getString("discount");
            this.payRemainTitle = jDJSONObject.getString("payRemainTitle");
        }
    }

    public GraduallyPayPopupEntity() {
    }
}
