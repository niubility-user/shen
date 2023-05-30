package com.jd.lib.cashier.sdk.pay.floors;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.j0;

/* loaded from: classes14.dex */
public class CashierAPayGroupTitleFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, com.jd.lib.cashier.sdk.h.g.d> {

    /* renamed from: i  reason: collision with root package name */
    private TextView f4003i;

    public CashierAPayGroupTitleFloor(View view) {
        super(view);
    }

    private void d() {
        View convertView = getConvertView();
        TextView textView = this.f4003i;
        if (textView != null) {
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
        }
        if (convertView != null) {
            convertView.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F6F6F6, JDDarkUtil.COLOR_141212));
        }
    }

    private void f(com.jd.lib.cashier.sdk.h.g.d dVar) {
        if (dVar != null && !TextUtils.isEmpty(dVar.a)) {
            j0.d(this.f4003i);
            this.f4003i.setText(dVar.a);
            return;
        }
        j0.b(this.f4003i);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, com.jd.lib.cashier.sdk.h.g.d dVar) {
        f(dVar);
        d();
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4003i == null) {
            this.f4003i = (TextView) getView(R.id.lib_cashier_a_pay_group_title_desc);
        }
    }
}
