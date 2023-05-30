package com.jd.lib.cashier.sdk.g.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.common.bean.CashierGetSuccessUrlEntity;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.viewmodel.FriendPayDialogViewModel;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.b.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements f<CashierGetSuccessUrlEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.b.f.a f3452g;

        a(com.jd.lib.cashier.sdk.b.f.a aVar) {
            this.f3452g = aVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity) {
            if (cashierGetSuccessUrlEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.p(this.f3452g.getActivity(), cashierGetSuccessUrlEntity);
            } else if (!TextUtils.isEmpty(cashierGetSuccessUrlEntity.errorCode)) {
                b.this.p(this.f3452g.getActivity(), cashierGetSuccessUrlEntity);
            } else {
                b.this.r(this.f3452g.getActivity(), cashierGetSuccessUrlEntity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity) {
        if (g0.a(fragmentActivity)) {
            FriendPayDialogViewModel friendPayDialogViewModel = (FriendPayDialogViewModel) ViewModelProviders.of(fragmentActivity).get(FriendPayDialogViewModel.class);
            friendPayDialogViewModel.b().f3468l = false;
            friendPayDialogViewModel.i().a();
        }
        if (cashierGetSuccessUrlEntity == null || TextUtils.isEmpty(cashierGetSuccessUrlEntity.errorMsg)) {
            return;
        }
        f0.c(cashierGetSuccessUrlEntity.errorMsg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity) {
        if (!g0.a(fragmentActivity) || cashierGetSuccessUrlEntity == null) {
            return;
        }
        FriendPayDialogViewModel friendPayDialogViewModel = (FriendPayDialogViewModel) ViewModelProviders.of(fragmentActivity).get(FriendPayDialogViewModel.class);
        friendPayDialogViewModel.b().f3468l = false;
        if (cashierGetSuccessUrlEntity.globalPresaleCombinedPayPopup != null) {
            friendPayDialogViewModel.g().a(cashierGetSuccessUrlEntity);
        } else {
            friendPayDialogViewModel.i().b(friendPayDialogViewModel.b().f3466j, cashierGetSuccessUrlEntity);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.b.f.a aVar) {
        if (aVar != null) {
            j(new a(aVar));
            h(aVar);
        }
    }
}
