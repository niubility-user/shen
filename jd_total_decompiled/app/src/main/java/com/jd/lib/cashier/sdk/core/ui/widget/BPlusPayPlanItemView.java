package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import com.jd.lib.cashier.sdk.R;

/* loaded from: classes14.dex */
public class BPlusPayPlanItemView extends AbsPlusPayPlanItemView {
    public BPlusPayPlanItemView(Context context, String str) {
        super(context, str);
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanItemView
    public int g() {
        return R.layout.lib_cashier_sdk_b_pay_plan_item_content;
    }
}
