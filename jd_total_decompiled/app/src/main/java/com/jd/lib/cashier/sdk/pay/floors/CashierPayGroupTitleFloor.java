package com.jd.lib.cashier.sdk.pay.floors;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.h.g.a0;

/* loaded from: classes14.dex */
public class CashierPayGroupTitleFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, a0> {

    /* renamed from: i  reason: collision with root package name */
    private TextView f4131i;

    public CashierPayGroupTitleFloor(View view) {
        super(view);
    }

    private void d() {
        View convertView = getConvertView();
        TextView textView = this.f4131i;
        if (textView != null) {
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
        }
        if (convertView != null) {
            convertView.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F5F5F5, JDDarkUtil.COLOR_141212));
        }
    }

    private void f(a0 a0Var) {
        if (a0Var != null && !TextUtils.isEmpty(a0Var.a)) {
            j0.d(this.f4131i);
            this.f4131i.setText(a0Var.a);
            return;
        }
        j0.b(this.f4131i);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, a0 a0Var) {
        f(a0Var);
        d();
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4131i == null) {
            this.f4131i = (TextView) getView(R.id.lib_cashier_pay_group_title_desc);
        }
    }
}
