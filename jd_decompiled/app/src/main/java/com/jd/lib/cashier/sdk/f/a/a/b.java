package com.jd.lib.cashier.sdk.f.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.freindpay.aac.viewmodel.FriendPayViewModel;
import com.jd.lib.cashier.sdk.freindpay.bean.CashierFriendPayEntity;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.f.a.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements f<CashierFriendPayEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.f.e.a f3338g;

        a(com.jd.lib.cashier.sdk.f.e.a aVar) {
            this.f3338g = aVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(CashierFriendPayEntity cashierFriendPayEntity) {
            if (cashierFriendPayEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.p(this.f3338g.getActivity(), cashierFriendPayEntity);
            } else if (!TextUtils.isEmpty(cashierFriendPayEntity.errorCode)) {
                b.this.p(this.f3338g.getActivity(), cashierFriendPayEntity);
            } else {
                b.this.r(this.f3338g.getActivity(), cashierFriendPayEntity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, CashierFriendPayEntity cashierFriendPayEntity) {
        if (g0.a(fragmentActivity) && cashierFriendPayEntity != null) {
            FriendPayViewModel friendPayViewModel = (FriendPayViewModel) ViewModelProviders.of(fragmentActivity).get(FriendPayViewModel.class);
            friendPayViewModel.b().o = cashierFriendPayEntity;
            friendPayViewModel.g().a();
            friendPayViewModel.f().b();
            if (cashierFriendPayEntity.orderExceptionInfo != null) {
                friendPayViewModel.f().c(cashierFriendPayEntity.orderExceptionInfo);
            }
        }
        if (cashierFriendPayEntity == null || TextUtils.isEmpty(cashierFriendPayEntity.errorMsg)) {
            return;
        }
        f0.c(cashierFriendPayEntity.errorMsg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, CashierFriendPayEntity cashierFriendPayEntity) {
        if (!g0.a(fragmentActivity) || cashierFriendPayEntity == null) {
            return;
        }
        FriendPayViewModel friendPayViewModel = (FriendPayViewModel) ViewModelProviders.of(fragmentActivity).get(FriendPayViewModel.class);
        friendPayViewModel.b().o = cashierFriendPayEntity;
        if (!TextUtils.isEmpty(cashierFriendPayEntity.orderId)) {
            friendPayViewModel.b().b = cashierFriendPayEntity.orderId;
        }
        friendPayViewModel.g().b(cashierFriendPayEntity);
        friendPayViewModel.f().a();
        if (cashierFriendPayEntity.orderExceptionInfo != null) {
            friendPayViewModel.f().c(cashierFriendPayEntity.orderExceptionInfo);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
        IDynamic dynamic = DependInitializer.getDynamic();
        if (dynamic != null) {
            dynamic.prepare("pay", "");
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.f.e.a aVar) {
        if (aVar != null) {
            j(new a(aVar));
            h(aVar);
        }
    }
}
