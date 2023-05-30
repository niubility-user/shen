package com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan;

import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;

/* loaded from: classes14.dex */
public class PlanViewUncheckableStyle extends AbsPlanItemViewStyle {
    public PlanViewUncheckableStyle() {
        initPlanViewStyle();
    }

    @Override // com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan.AbsPlanItemViewStyle
    void initPlanViewStyle() {
        this.borderColor = "#00FA210C";
        this.backgroundColor = "#f7f7f7";
        this.topTextColor = "#D4D4D4";
        this.bottomTextColor = "#D4D4D4";
        this.flagTextColor = JDDarkUtil.COLOR_FA2C19;
        this.flagBackgroundColor = "#F9EEEC";
        this.flagBackgroundImage = "0";
    }
}
