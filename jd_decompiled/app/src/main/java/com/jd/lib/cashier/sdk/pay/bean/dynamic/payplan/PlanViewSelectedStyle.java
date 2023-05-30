package com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan;

import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;

/* loaded from: classes14.dex */
public class PlanViewSelectedStyle extends AbsPlanItemViewStyle {
    public PlanViewSelectedStyle() {
        initPlanViewStyle();
    }

    @Override // com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan.AbsPlanItemViewStyle
    void initPlanViewStyle() {
        this.borderColor = "#FA210C";
        this.backgroundColor = "#FAEFED";
        this.topTextColor = JDDarkUtil.COLOR_FA2C19;
        this.bottomTextColor = JDDarkUtil.COLOR_FA2C19;
        this.flagTextColor = "#FFFFFF";
        this.flagBackgroundColor = "#E7422E";
        this.flagBackgroundImage = "1";
    }
}
