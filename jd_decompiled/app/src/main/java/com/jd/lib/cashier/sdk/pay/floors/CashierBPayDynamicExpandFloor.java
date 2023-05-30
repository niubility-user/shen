package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.h.b.n;
import com.jd.lib.cashier.sdk.h.g.q;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class CashierBPayDynamicExpandFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, q> {

    /* renamed from: i  reason: collision with root package name */
    private boolean f4085i;

    /* renamed from: j  reason: collision with root package name */
    private ViewGroup f4086j;

    /* renamed from: k  reason: collision with root package name */
    private ViewGroup f4087k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ q f4088j;

        a(q qVar) {
            this.f4088j = qVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            Context context = CashierBPayDynamicExpandFloor.this.getConvertView().getContext();
            if (context instanceof CashierPayActivity) {
                CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of((FragmentActivity) context).get(CashierPayViewModel.class);
                List<com.jd.lib.cashier.sdk.d.a.e.a> g2 = n.f().g(cashierPayViewModel.b().K);
                List<com.jd.lib.cashier.sdk.d.a.e.a> g3 = com.jd.lib.cashier.sdk.h.b.j.f().g(cashierPayViewModel.b().K);
                if (cashierPayViewModel.b().K != null) {
                    cashierPayViewModel.u().a(this.f4088j, cashierPayViewModel.b().K.otherPayChannelList, g2, g3);
                }
            }
        }
    }

    public CashierBPayDynamicExpandFloor(View view) {
        super(view);
    }

    private void d(q qVar) {
        ViewGroup viewGroup;
        if (qVar == null || (viewGroup = this.f4086j) == null) {
            return;
        }
        viewGroup.setOnClickListener(new a(qVar));
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, q qVar) {
        d(qVar);
        if (qVar != null) {
            try {
                if (qVar.a != null && this.f4086j != null) {
                    IDynamic dynamic = DependInitializer.getDynamic();
                    JSONObject jSONObject = new JSONObject(DependInitializer.getJsonParser().toJsonString(qVar.a));
                    ViewGroup viewGroup = this.f4087k;
                    if (viewGroup == null && dynamic != null) {
                        this.f4087k = dynamic.createDynamicContainer(this.f4086j.getContext(), "pay", "BPayFoldView", null);
                        this.f4086j.removeAllViews();
                        this.f4086j.addView(this.f4087k);
                        this.f4085i = dynamic.asyncLoad(this.f4087k, jSONObject);
                    } else if (dynamic != null && this.f4085i) {
                        dynamic.bindData(viewGroup, jSONObject);
                    } else if (dynamic != null) {
                        this.f4085i = dynamic.asyncLoad(viewGroup, jSONObject);
                    }
                }
            } catch (Exception e2) {
                r.e("CashierBPayDynamicExpandFloor", e2.getMessage(), e2);
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4086j == null) {
            this.f4086j = (ViewGroup) getView(R.id.lib_cashier_b_pay_dynamic_expand_view_root);
        }
    }
}
