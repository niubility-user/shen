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
import com.jd.lib.cashier.sdk.h.b.s;
import com.jd.lib.cashier.sdk.h.g.z;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class CashierPayExpandFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, z> {

    /* renamed from: i  reason: collision with root package name */
    private TextView f4128i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ z f4129j;

        a(z zVar) {
            this.f4129j = zVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            Context context = CashierPayExpandFloor.this.getConvertView().getContext();
            if (context instanceof CashierPayActivity) {
                FragmentActivity fragmentActivity = (FragmentActivity) context;
                CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
                cashierPayViewModel.C().a(this.f4129j, s.e().f(cashierPayViewModel.b().K));
                CashierPayEntity cashierPayEntity = cashierPayViewModel.b().K;
                if (cashierPayEntity != null) {
                    com.jd.lib.cashier.sdk.h.e.a.d().E(fragmentActivity, cashierPayEntity.orderId, cashierPayEntity.thirdPaychannelFoldStrategyId, cashierPayEntity.buttonStatus);
                }
            }
        }
    }

    public CashierPayExpandFloor(View view) {
        super(view);
    }

    private void c(String str) {
        View convertView = getConvertView();
        TextView textView = this.f4128i;
        if (textView != null) {
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
            if (TextUtils.equals(str, "1")) {
                if (JDDarkUtil.isDarkMode()) {
                    this.f4128i.setBackgroundResource(R.drawable.lib_cashier_sdk_expand_pay_floor_dark);
                } else {
                    this.f4128i.setBackgroundResource(R.drawable.lib_cashier_sdk_expand_pay_floor);
                }
            } else {
                this.f4128i.setBackgroundColor(0);
            }
        }
        if (convertView != null) {
            convertView.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F5F5F5, JDDarkUtil.COLOR_141212));
        }
    }

    private void e(z zVar) {
        TextView textView = this.f4128i;
        if (textView == null) {
            return;
        }
        if (!j0.a(textView)) {
            this.f4128i.setOnClickListener(null);
        } else {
            this.f4128i.setOnClickListener(new a(zVar));
        }
    }

    private void f(String str) {
        if (this.f4128i == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            this.f4128i.setVisibility(8);
            return;
        }
        this.f4128i.setVisibility(0);
        this.f4128i.setText(str);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, z zVar) {
        if (zVar != null) {
            f(zVar.a);
            c(zVar.b);
            e(zVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4128i == null) {
            this.f4128i = (TextView) getView(R.id.lib_cashier_pay_other_pay_expand_floor_desc);
        }
    }
}
