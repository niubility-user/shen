package com.jd.lib.productdetail.tradein.bean;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes16.dex */
public class TradeInTitleInfo implements Serializable {
    public String ruleClickText;
    public String rulePopupContent;
    public String rulePopupTitle;
    public String threeStepImage;

    public boolean isValid() {
        return (TextUtils.isEmpty(this.threeStepImage) || TextUtils.isEmpty(this.rulePopupContent) || TextUtils.isEmpty(this.rulePopupTitle)) ? false : true;
    }
}
