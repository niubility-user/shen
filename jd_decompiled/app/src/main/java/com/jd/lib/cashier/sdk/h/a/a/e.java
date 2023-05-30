package com.jd.lib.cashier.sdk.h.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.l;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCardPayPlanResponse;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.UUID;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class e extends com.jd.lib.cashier.sdk.h.a.a.l.f {

    /* renamed from: c  reason: collision with root package name */
    private static final String f3488c = "e";
    public static final a d = new a(null);
    private l b;

    /* loaded from: classes14.dex */
    public static final class a {
        private a() {
        }

        public final String a() {
            return e.f3488c;
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class b<T> implements com.jd.lib.cashier.sdk.core.utils.f<CreditCardPayPlanResponse> {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ UUID f3490h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.h.c.b f3491i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.h.f.f f3492j;

        b(UUID uuid, com.jd.lib.cashier.sdk.h.c.b bVar, com.jd.lib.cashier.sdk.h.f.f fVar) {
            this.f3490h = uuid;
            this.f3491i = bVar;
            this.f3492j = fVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void callBack(CreditCardPayPlanResponse entity) {
            a aVar = e.d;
            r.b(aVar.a(), "currentRequestUUID = " + this.f3490h);
            if ((!Intrinsics.areEqual(this.f3491i != null ? r1.a() : null, this.f3490h)) == true) {
                return;
            }
            r.b(aVar.a(), "SUCCESS currentRequestUUID = " + this.f3490h);
            entity.checkNullObjAndInit();
            Intrinsics.checkExpressionValueIsNotNull(entity, "entity");
            if (entity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                e.this.q(this.f3492j.getActivity(), entity);
            } else if (!TextUtils.isEmpty(entity.errorCode)) {
                e.this.q(this.f3492j.getActivity(), entity);
            } else {
                e.this.s(this.f3492j.getActivity(), entity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void q(FragmentActivity fragmentActivity, CreditCardPayPlanResponse creditCardPayPlanResponse) {
        if (fragmentActivity != null && g0.a(fragmentActivity)) {
            String str = null;
            if (Intrinsics.areEqual("1312", creditCardPayPlanResponse != null ? creditCardPayPlanResponse.errorCode : null)) {
                f0.c(!TextUtils.isEmpty(creditCardPayPlanResponse.errorMsg) ? creditCardPayPlanResponse.errorMsg : fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_credit_card_plan_error_message));
            } else {
                if (TextUtils.isEmpty(creditCardPayPlanResponse != null ? creditCardPayPlanResponse.errorMsg : null)) {
                    str = fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_plan_exception_message);
                } else if (creditCardPayPlanResponse != null) {
                    str = creditCardPayPlanResponse.errorMsg;
                }
                f0.c(str);
            }
            ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
            ((CashierPayViewModel) viewModel).x().a(this.b, creditCardPayPlanResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void s(FragmentActivity fragmentActivity, CreditCardPayPlanResponse creditCardPayPlanResponse) {
        if (creditCardPayPlanResponse == null || fragmentActivity == null || !g0.a(fragmentActivity)) {
            return;
        }
        ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
        ((CashierPayViewModel) viewModel).y().a(this.b, creditCardPayPlanResponse);
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: r  reason: merged with bridge method [inline-methods] */
    public void e(@Nullable com.jd.lib.cashier.sdk.h.f.f fVar) {
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b2;
        if (fVar != null) {
            this.b = new l(fVar.a(), fVar.f(), fVar.e());
            FragmentActivity activity = fVar.getActivity();
            com.jd.lib.cashier.sdk.h.c.b bVar = null;
            if (!(activity instanceof CashierPayActivity)) {
                activity = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) activity;
            if (cashierPayActivity != null && (x = cashierPayActivity.x()) != null && (b2 = x.b()) != null) {
                bVar = b2.Q;
            }
            UUID randomUUID = UUID.randomUUID();
            if (bVar != null) {
                bVar.d(randomUUID);
            }
            r.b(f3488c, "currentRequestUUID request = " + randomUUID);
            j(new b(randomUUID, bVar, fVar));
            h(fVar);
        }
    }
}
