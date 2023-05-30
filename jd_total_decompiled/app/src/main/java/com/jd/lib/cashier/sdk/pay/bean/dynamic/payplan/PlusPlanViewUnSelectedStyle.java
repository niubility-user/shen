package com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan;

import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;

/* loaded from: classes14.dex */
public class PlusPlanViewUnSelectedStyle extends AbsPlanItemViewStyle {
    private final boolean hightlight;

    public PlusPlanViewUnSelectedStyle(boolean z) {
        this.hightlight = z;
        initPlanViewStyle();
    }

    @Override // com.jd.lib.cashier.sdk.pay.bean.dynamic.payplan.AbsPlanItemViewStyle
    void initPlanViewStyle() {
        this.borderColor = "#FFD866";
        this.backgroundColor = "#f7f7f7";
        this.topTextColor = JDDarkUtil.COLOR_1A1A1A;
        if (this.hightlight) {
            this.bottomTextColor = JDDarkUtil.COLOR_FA2C19;
        } else {
            this.bottomTextColor = JDDarkUtil.COLOR_808080;
        }
        this.flagTextColor = "#0C246A";
        this.flagBackgroundColor = "#FFD866";
        this.flagBackgroundImage = "2";
    }
}
