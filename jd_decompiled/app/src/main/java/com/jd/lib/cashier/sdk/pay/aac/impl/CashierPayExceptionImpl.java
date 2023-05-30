package com.jd.lib.cashier.sdk.pay.aac.impl;

import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.freindpay.view.CashierErrorView;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0007\u00a2\u0006\u0004\b(\u0010\u0006J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\u0006J\u000f\u0010\t\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\u0006J\u0017\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u0010\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0006J\u0019\u0010\u0014\u001a\u00020\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0006R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010 \u001a\u00020\n8\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010$\u001a\u00020!8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0018\u0010'\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010&\u00a8\u0006)"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/CashierPayExceptionImpl;", "Lcom/jd/lib/cashier/sdk/pay/aac/impl/e/c;", "Landroidx/lifecycle/Observer;", "Lcom/jd/lib/cashier/sdk/h/a/b/a;", "", "p", "()V", "o", "r", PersonalConstants.ICON_STYLE_N, "Landroidx/fragment/app/FragmentActivity;", "activity", "f", "(Landroidx/fragment/app/FragmentActivity;)V", "Landroid/view/Window;", "window", JshopConst.JSHOP_PROMOTIO_H, "(Landroid/view/Window;)V", "onDestroy", "failData", "q", "(Lcom/jd/lib/cashier/sdk/h/a/b/a;)V", "onChangeSkin", "Lcom/jd/lib/cashier/sdk/freindpay/view/CashierErrorView;", "i", "Lcom/jd/lib/cashier/sdk/freindpay/view/CashierErrorView;", "errorView", "k", "Lcom/jd/lib/cashier/sdk/h/a/b/a;", "mLastCashierPayFailData", "j", "Landroidx/fragment/app/FragmentActivity;", "mFragmentActivity", "", "g", "Z", "mOnRequestAction", "Landroid/view/ViewStub;", "Landroid/view/ViewStub;", "errorViewStub", "<init>", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CashierPayExceptionImpl implements com.jd.lib.cashier.sdk.pay.aac.impl.e.c, Observer<com.jd.lib.cashier.sdk.h.a.b.a> {

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    private boolean mOnRequestAction;

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private ViewStub errorViewStub;

    /* renamed from: i  reason: collision with root package name and from kotlin metadata */
    private CashierErrorView errorView;

    /* renamed from: j  reason: collision with root package name and from kotlin metadata */
    private FragmentActivity mFragmentActivity;

    /* renamed from: k  reason: collision with root package name and from kotlin metadata */
    private com.jd.lib.cashier.sdk.h.a.b.a mLastCashierPayFailData;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (!g0.a(CashierPayExceptionImpl.c(CashierPayExceptionImpl.this)) || CashierPayExceptionImpl.this.mOnRequestAction) {
                return;
            }
            CashierPayExceptionImpl.this.mOnRequestAction = true;
            ViewModel viewModel = ViewModelProviders.of(CashierPayExceptionImpl.c(CashierPayExceptionImpl.this)).get(CashierPayViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(mF\u2026PayViewModel::class.java)");
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) viewModel;
            FragmentActivity c2 = CashierPayExceptionImpl.c(CashierPayExceptionImpl.this);
            if (!(c2 instanceof CashierPayActivity)) {
                c2 = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) c2;
            if (cashierPayActivity != null) {
                CashierPayViewModel.m(cashierPayViewModel, cashierPayActivity, null, null, 6, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            CashierPayExceptionImpl.this.n();
        }
    }

    public static final /* synthetic */ FragmentActivity c(CashierPayExceptionImpl cashierPayExceptionImpl) {
        FragmentActivity fragmentActivity = cashierPayExceptionImpl.mFragmentActivity;
        if (fragmentActivity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragmentActivity");
        }
        return fragmentActivity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void n() {
        com.jd.lib.cashier.sdk.h.a.c.a w;
        FragmentActivity fragmentActivity = this.mFragmentActivity;
        if (fragmentActivity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragmentActivity");
        }
        if (g0.a(fragmentActivity)) {
            FragmentActivity fragmentActivity2 = this.mFragmentActivity;
            if (fragmentActivity2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFragmentActivity");
            }
            if (!(fragmentActivity2 instanceof CashierPayActivity)) {
                fragmentActivity2 = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity2;
            if (cashierPayActivity != null && (w = cashierPayActivity.w()) != null) {
                FragmentActivity fragmentActivity3 = this.mFragmentActivity;
                if (fragmentActivity3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFragmentActivity");
                }
                w.b(fragmentActivity3);
            }
        }
        PayTaskStackManager.removeAllCashierTask();
    }

    private final void o() {
        p();
        j0.b(this.errorView);
    }

    private final void p() {
        if (this.errorView == null) {
            ViewStub viewStub = this.errorViewStub;
            View inflate = viewStub != null ? viewStub.inflate() : null;
            this.errorView = inflate instanceof CashierErrorView ? inflate : null;
        }
    }

    private final void r() {
        p();
        CashierErrorView cashierErrorView = this.errorView;
        if (cashierErrorView == null) {
            return;
        }
        j0.d(cashierErrorView);
        CashierErrorView cashierErrorView2 = this.errorView;
        if (cashierErrorView2 != null) {
            cashierErrorView2.setErrorButtonListener(new a());
        }
        CashierErrorView cashierErrorView3 = this.errorView;
        if (cashierErrorView3 != null) {
            cashierErrorView3.setErrorOrderListButtonListener(new b());
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(@NotNull FragmentActivity activity) {
        this.mFragmentActivity = activity;
        if (g0.a(activity)) {
            FragmentActivity fragmentActivity = this.mFragmentActivity;
            if (fragmentActivity == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFragmentActivity");
            }
            ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(mF\u2026PayViewModel::class.java)");
            ((CashierPayViewModel) viewModel).D().observe(activity, this);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.c
    public void h(@Nullable Window window) {
        this.errorViewStub = window != null ? (ViewStub) window.findViewById(R.id.lib_cashier_pay_view_stub) : null;
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        CashierErrorView cashierErrorView = this.errorView;
        if (cashierErrorView != null) {
            cashierErrorView.onHandModeSkin();
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void onChanged(@Nullable com.jd.lib.cashier.sdk.h.a.b.a failData) {
        this.mOnRequestAction = false;
        if (failData != null && (!Intrinsics.areEqual(failData, this.mLastCashierPayFailData)) != false) {
            int a2 = failData.a();
            if (a2 == 0) {
                r();
            } else if (a2 == 8) {
                o();
            }
        }
        this.mLastCashierPayFailData = failData;
    }
}
