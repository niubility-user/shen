package com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan;

import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;

/* loaded from: classes14.dex */
public class PlanViewUnSelectedStyle extends AbsPlanItemViewStyle {
    private final boolean hightLight;

    public PlanViewUnSelectedStyle(boolean z) {
        this.hightLight = z;
        initPlanViewStyle();
    }

    @Override // com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan.AbsPlanItemViewStyle
    void initPlanViewStyle() {
        this.borderColor = "#FA210C";
        this.backgroundColor = "#f7f7f7";
        this.topTextColor = JDDarkUtil.COLOR_1A1A1A;
        if (this.hightLight) {
            this.bottomTextColor = JDDarkUtil.COLOR_FA2C19;
        } else {
            this.bottomTextColor = JDDarkUtil.COLOR_808080;
        }
        this.flagTextColor = JDDarkUtil.COLOR_FA2C19;
        this.flagBackgroundColor = "#F9EEEC";
        this.flagBackgroundImage = "0";
    }
}
