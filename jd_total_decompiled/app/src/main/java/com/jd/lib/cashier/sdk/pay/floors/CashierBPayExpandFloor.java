package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.h.b.m;
import com.jd.lib.cashier.sdk.h.g.s;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierBPayExpandFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, s> {

    /* renamed from: i  reason: collision with root package name */
    private TextView f4094i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ s f4095j;

        a(s sVar) {
            this.f4095j = sVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            Context context = CashierBPayExpandFloor.this.getConvertView().getContext();
            if (context instanceof CashierPayActivity) {
                CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of((FragmentActivity) context).get(CashierPayViewModel.class);
                List<com.jd.lib.cashier.sdk.d.a.e.a> g2 = m.f().g(cashierPayViewModel.b().K);
                List<com.jd.lib.cashier.sdk.d.a.e.a> g3 = com.jd.lib.cashier.sdk.h.b.i.f().g(cashierPayViewModel.b().K);
                if (cashierPayViewModel.b().K != null) {
                    cashierPayViewModel.v().a(this.f4095j, cashierPayViewModel.b().K.otherPayChannelList, g2, g3);
                }
            }
        }
    }

    public CashierBPayExpandFloor(View view) {
        super(view);
    }

    private void d() {
        View convertView = getConvertView();
        TextView textView = this.f4094i;
        if (textView != null) {
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_757575));
        }
        if (convertView != null) {
            convertView.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F5F5F5, JDDarkUtil.COLOR_141212));
        }
    }

    private void f(s sVar) {
        TextView textView = this.f4094i;
        if (textView == null) {
            return;
        }
        if (!j0.a(textView)) {
            this.f4094i.setOnClickListener(null);
        } else {
            this.f4094i.setOnClickListener(new a(sVar));
        }
    }

    private void g(String str) {
        if (this.f4094i == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            this.f4094i.setVisibility(8);
            return;
        }
        this.f4094i.setVisibility(0);
        this.f4094i.setText(str);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, s sVar) {
        if (sVar != null) {
            g(sVar.a);
            d();
            f(sVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4094i == null) {
            this.f4094i = (TextView) getView(R.id.lib_cashier_b_pay_other_pay_expand_floor_desc);
        }
    }
}
