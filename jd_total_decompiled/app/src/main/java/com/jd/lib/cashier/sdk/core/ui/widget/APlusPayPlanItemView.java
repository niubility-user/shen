package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;

/* loaded from: classes14.dex */
public class APlusPayPlanItemView extends AbsPlusPayPlanItemView {
    public APlusPayPlanItemView(Context context, String str) {
        super(context, str);
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsPlusPayPlanItemView, com.jd.lib.cashier.sdk.core.ui.widget.b
    public void a(boolean z) {
        TextView textView = this.f3014k;
        if (textView == null) {
            return;
        }
        textView.setTextColor(Color.parseColor("#FFF2CC"));
        this.f3014k.setBackgroundResource(R.drawable.lib_cashier_sdk_a_bg_pay_item_plus_view_flag_select);
        this.f3015l.setImageResource(R.drawable.lib_cashier_sdk_a_plan_fee_plus_flag_bubble);
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsPlusPayPlanItemView, com.jd.lib.cashier.sdk.core.ui.widget.b
    public void b(boolean z) {
        if (z) {
            j(R.drawable.lib_cashier_sdk_a_pay_plan_item_plus_select_bg);
        } else {
            j(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_a_pay_plan_item_normal_bg_dark : R.drawable.lib_cashier_sdk_pay_plan_item_normal_bg);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsPlusPayPlanItemView, com.jd.lib.cashier.sdk.core.ui.widget.b
    public void c(boolean z) {
        TextView textView = this.f3012i;
        if (textView == null || this.f3013j == null) {
            return;
        }
        if (z) {
            textView.setTextColor(JDDarkUtil.getDarkColor("#663300", false));
            this.f3013j.setTextColor(JDDarkUtil.getDarkColor("#663300", false));
            return;
        }
        textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A, JDDarkUtil.COLOR_ECECEC));
        if (TextUtils.equals("1", this.p)) {
            this.f3013j.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FA2C19, JDDarkUtil.COLOR_FF3826));
        } else {
            this.f3013j.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_ECECEC));
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanItemView
    public int g() {
        return R.layout.lib_cashier_sdk_a_pay_plan_item_content;
    }
}
