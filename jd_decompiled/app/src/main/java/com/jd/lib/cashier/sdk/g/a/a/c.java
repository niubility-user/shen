package com.jd.lib.cashier.sdk.g.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.viewmodel.FriendPayDialogViewModel;
import com.jd.lib.cashier.sdk.freindpaydialog.bean.CashierFriendPayDialogEntity;

/* loaded from: classes14.dex */
public class c extends com.jd.lib.cashier.sdk.g.a.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements f<CashierFriendPayDialogEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.g.f.a f3454g;

        a(com.jd.lib.cashier.sdk.g.f.a aVar) {
            this.f3454g = aVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(CashierFriendPayDialogEntity cashierFriendPayDialogEntity) {
            if (cashierFriendPayDialogEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                c.this.p(this.f3454g.getActivity(), cashierFriendPayDialogEntity);
            } else if (!TextUtils.isEmpty(cashierFriendPayDialogEntity.errorCode)) {
                c.this.p(this.f3454g.getActivity(), cashierFriendPayDialogEntity);
            } else {
                c.this.r(this.f3454g.getActivity(), cashierFriendPayDialogEntity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, CashierFriendPayDialogEntity cashierFriendPayDialogEntity) {
        if (g0.a(fragmentActivity) && cashierFriendPayDialogEntity != null) {
            FriendPayDialogViewModel friendPayDialogViewModel = (FriendPayDialogViewModel) ViewModelProviders.of(fragmentActivity).get(FriendPayDialogViewModel.class);
            friendPayDialogViewModel.b().f3469m = cashierFriendPayDialogEntity;
            friendPayDialogViewModel.h().a();
            friendPayDialogViewModel.f().b();
            if (cashierFriendPayDialogEntity.orderExceptionInfo != null) {
                friendPayDialogViewModel.f().c(cashierFriendPayDialogEntity.orderExceptionInfo);
            }
        }
        if (cashierFriendPayDialogEntity == null || TextUtils.isEmpty(cashierFriendPayDialogEntity.errorMsg)) {
            return;
        }
        f0.c(cashierFriendPayDialogEntity.errorMsg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, CashierFriendPayDialogEntity cashierFriendPayDialogEntity) {
        if (!g0.a(fragmentActivity) || cashierFriendPayDialogEntity == null) {
            return;
        }
        FriendPayDialogViewModel friendPayDialogViewModel = (FriendPayDialogViewModel) ViewModelProviders.of(fragmentActivity).get(FriendPayDialogViewModel.class);
        friendPayDialogViewModel.b().f3469m = cashierFriendPayDialogEntity;
        if (!TextUtils.isEmpty(cashierFriendPayDialogEntity.orderId)) {
            friendPayDialogViewModel.b().b = cashierFriendPayDialogEntity.orderId;
        }
        friendPayDialogViewModel.h().b(cashierFriendPayDialogEntity);
        friendPayDialogViewModel.f().a();
        if (cashierFriendPayDialogEntity.orderExceptionInfo != null) {
            friendPayDialogViewModel.f().c(cashierFriendPayDialogEntity.orderExceptionInfo);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.g.f.a aVar) {
        if (aVar != null) {
            j(new a(aVar));
            h(aVar);
        }
    }
}
