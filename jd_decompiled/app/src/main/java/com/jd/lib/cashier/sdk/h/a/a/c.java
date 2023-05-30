package com.jd.lib.cashier.sdk.h.a.a;

import android.content.res.Resources;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.BankCouponResponse;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class c extends com.jd.lib.cashier.sdk.h.a.a.l.c {

    /* renamed from: c  reason: collision with root package name */
    private static final String f3485c = "c";
    @Nullable
    private Payment b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a<T> implements com.jd.lib.cashier.sdk.core.utils.f<BankCouponResponse> {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.h.f.c f3487h;

        a(com.jd.lib.cashier.sdk.h.f.c cVar) {
            this.f3487h = cVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void callBack(BankCouponResponse entity) {
            entity.checkNullObjAndInit();
            r.b(c.f3485c, "entity=" + entity);
            Intrinsics.checkExpressionValueIsNotNull(entity, "entity");
            if (entity.getResultCode() == com.jd.lib.cashier.sdk.d.b.b.SUC) {
                if (TextUtils.isEmpty(entity.errorCode)) {
                    c.this.s(this.f3487h.getActivity(), entity);
                    return;
                }
                c cVar = c.this;
                FragmentActivity activity = this.f3487h.getActivity();
                String str = entity.errorMsg;
                Intrinsics.checkExpressionValueIsNotNull(str, "entity.errorMsg");
                cVar.q(activity, str);
                return;
            }
            c cVar2 = c.this;
            FragmentActivity activity2 = this.f3487h.getActivity();
            String str2 = entity.errorMsg;
            Intrinsics.checkExpressionValueIsNotNull(str2, "entity.errorMsg");
            cVar2.q(activity2, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void q(FragmentActivity fragmentActivity, String str) {
        Resources resources;
        if (g0.a(fragmentActivity)) {
            if (TextUtils.isEmpty(str)) {
                str = (fragmentActivity == null || (resources = fragmentActivity.getResources()) == null) ? null : resources.getString(R.string.lib_cashier_sdk_credit_pay_service_exception);
            }
            f0.c(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void s(FragmentActivity fragmentActivity, BankCouponResponse bankCouponResponse) {
        if (fragmentActivity != null && g0.a(fragmentActivity)) {
            String str = f3485c;
            r.b(str, "CashierBankCouponAction canUseCouponList = " + bankCouponResponse.getCanUseCouponList());
            r.b(str, "CashierBankCouponAction cantUseCouponList = " + bankCouponResponse.getCantUseCouponList());
            ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
            ((CashierPayViewModel) viewModel).s().a(bankCouponResponse, this.b);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: r  reason: merged with bridge method [inline-methods] */
    public void e(@Nullable com.jd.lib.cashier.sdk.h.f.c cVar) {
        if (cVar != null) {
            r.b(f3485c, "param=" + cVar);
            j(new a(cVar));
            h(cVar);
        }
    }

    public final void t(@Nullable Payment payment) {
        this.b = payment;
    }
}
