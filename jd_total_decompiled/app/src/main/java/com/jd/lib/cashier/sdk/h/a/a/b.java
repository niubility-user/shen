package com.jd.lib.cashier.sdk.h.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.p;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoPayPlanResponse;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class b extends com.jd.lib.cashier.sdk.h.a.a.l.b {

    /* renamed from: c  reason: collision with root package name */
    private static final String f3482c = "b";
    private p b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a<T> implements com.jd.lib.cashier.sdk.core.utils.f<BaiTiaoPayPlanResponse> {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.h.f.b f3484h;

        a(com.jd.lib.cashier.sdk.h.f.b bVar) {
            this.f3484h = bVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void callBack(BaiTiaoPayPlanResponse entity) {
            entity.checkNullObjAndInit();
            r.b(b.f3482c, "entity=" + entity);
            Intrinsics.checkExpressionValueIsNotNull(entity, "entity");
            if (entity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.q(this.f3484h.getActivity(), entity);
            } else if (!TextUtils.isEmpty(entity.errorCode)) {
                b.this.q(this.f3484h.getActivity(), entity);
            } else {
                b.this.s(this.f3484h.getActivity(), entity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void q(FragmentActivity fragmentActivity, BaiTiaoPayPlanResponse baiTiaoPayPlanResponse) {
        if (fragmentActivity != null && g0.a(fragmentActivity)) {
            if (baiTiaoPayPlanResponse != null) {
                baiTiaoPayPlanResponse.clearCouponInfo = !TextUtils.isEmpty(baiTiaoPayPlanResponse.errorCode);
            }
            String str = null;
            if (Intrinsics.areEqual("1312", baiTiaoPayPlanResponse != null ? baiTiaoPayPlanResponse.errorCode : null)) {
                f0.c(!TextUtils.isEmpty(baiTiaoPayPlanResponse.errorMsg) ? baiTiaoPayPlanResponse.errorMsg : fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_baitiao_plan_error_message));
            } else {
                if (TextUtils.isEmpty(baiTiaoPayPlanResponse != null ? baiTiaoPayPlanResponse.errorMsg : null)) {
                    str = fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_plan_exception_message);
                } else if (baiTiaoPayPlanResponse != null) {
                    str = baiTiaoPayPlanResponse.errorMsg;
                }
                f0.c(str);
            }
            ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
            ((CashierPayViewModel) viewModel).q().a(this.b, baiTiaoPayPlanResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void s(FragmentActivity fragmentActivity, BaiTiaoPayPlanResponse baiTiaoPayPlanResponse) {
        String str = f3482c;
        r.b(str, "BaiTiao BaiTiaoPayPlanResponseEntity=" + baiTiaoPayPlanResponse);
        if (baiTiaoPayPlanResponse == null || fragmentActivity == null || !g0.a(fragmentActivity)) {
            return;
        }
        r.b(str, "EventBusManager CashierBaiTiaoPayPlanAction doSuc");
        ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
        ((CashierPayViewModel) viewModel).r().a(this.b, baiTiaoPayPlanResponse);
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: r  reason: merged with bridge method [inline-methods] */
    public void e(@Nullable com.jd.lib.cashier.sdk.h.f.b bVar) {
        if (bVar != null) {
            this.b = new p(bVar.f(), bVar.m(), bVar.b(), bVar.h(), bVar.g(), bVar.a());
            r.b(f3482c, "param=" + bVar);
            j(new a(bVar));
            h(bVar);
        }
    }
}
