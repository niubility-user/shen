package com.jd.lib.cashier.sdk.pay.floors;

import android.view.View;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.h.g.c0;

/* loaded from: classes14.dex */
public class CashierUnableJDPaymentNewFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, c0> {

    /* renamed from: i  reason: collision with root package name */
    private TextView f4137i;

    public CashierUnableJDPaymentNewFloor(View view) {
        super(view);
    }

    private void d() {
        if (JDDarkUtil.isDarkMode()) {
            this.f4137i.setBackgroundResource(R.drawable.lib_cashier_sdk_regulator_bottom_corner_dark_bg);
        } else {
            this.f4137i.setBackgroundResource(R.drawable.lib_cashier_sdk_regulator_bottom_corner_bg);
        }
        this.f4137i.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
        View view = getView(R.id.lib_cashier_unable_pay_channel_new_floor_root);
        if (view != null) {
            view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F5F5F5, JDDarkUtil.COLOR_141212));
        }
    }

    private void f() {
        TextView textView = this.f4137i;
        if (textView != null) {
            textView.setText(R.string.lib_cashier_sdk_pay_unable_use_text);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, c0 c0Var) {
        f();
        d();
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        this.f4137i = (TextView) getView(R.id.lib_cashier_unable_jd_pay_desc);
    }
}
