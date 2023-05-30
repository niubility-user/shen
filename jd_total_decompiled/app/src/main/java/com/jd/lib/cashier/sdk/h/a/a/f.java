package com.jd.lib.cashier.sdk.h.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.OctopusRateResponse;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class f extends com.jd.lib.cashier.sdk.h.a.a.l.h {
    private static final String b = "f";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a<T> implements com.jd.lib.cashier.sdk.core.utils.f<OctopusRateResponse> {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.h.f.h f3494h;

        a(com.jd.lib.cashier.sdk.h.f.h hVar) {
            this.f3494h = hVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void callBack(OctopusRateResponse entity) {
            entity.checkNullObjAndInit();
            r.b(f.b, "entity=" + entity);
            Intrinsics.checkExpressionValueIsNotNull(entity, "entity");
            if (entity.getResultCode() == com.jd.lib.cashier.sdk.d.b.b.SUC) {
                if (TextUtils.isEmpty(entity.errorCode)) {
                    f.this.s(this.f3494h.getActivity(), entity);
                    return;
                }
                f fVar = f.this;
                FragmentActivity activity = this.f3494h.getActivity();
                String str = entity.errorMsg;
                Intrinsics.checkExpressionValueIsNotNull(str, "entity.errorMsg");
                fVar.q(activity, str);
                return;
            }
            f fVar2 = f.this;
            FragmentActivity activity2 = this.f3494h.getActivity();
            String str2 = entity.errorMsg;
            Intrinsics.checkExpressionValueIsNotNull(str2, "entity.errorMsg");
            fVar2.q(activity2, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void q(FragmentActivity fragmentActivity, String str) {
        if (g0.a(fragmentActivity)) {
            if (!TextUtils.isEmpty(str)) {
                f0.c(str);
            }
            if (fragmentActivity != null) {
                ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
                Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
                ((CashierPayViewModel) viewModel).B().b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void s(FragmentActivity fragmentActivity, OctopusRateResponse octopusRateResponse) {
        r.b(b, "OctopusRateResponse entity = " + octopusRateResponse);
        if (fragmentActivity != null && g0.a(fragmentActivity)) {
            ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
            ((CashierPayViewModel) viewModel).B().a(octopusRateResponse);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: r  reason: merged with bridge method [inline-methods] */
    public void e(@Nullable com.jd.lib.cashier.sdk.h.f.h hVar) {
        if (hVar == null) {
            return;
        }
        r.b(b, "param=" + hVar);
        j(new a(hVar));
        h(hVar);
    }
}
