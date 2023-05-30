package com.jd.lib.cashier.sdk.freindpay.aac.impl;

import android.text.TextUtils;
import android.view.Window;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.freindpay.aac.viewmodel.FriendPayViewModel;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class CashierGetSuccessUrlImpl implements c, Observer<com.jd.lib.cashier.sdk.f.a.b.c> {

    /* renamed from: g */
    private FragmentActivity f3369g;

    /* renamed from: h */
    private com.jd.lib.cashier.sdk.f.a.c.a f3370h;

    public CashierGetSuccessUrlImpl(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.f.a.c.a aVar) {
        this.f3369g = fragmentActivity;
        this.f3370h = aVar;
    }

    private void c(String str) {
        if (g0.a(this.f3369g)) {
            com.jd.lib.cashier.sdk.f.d.a.c(this.f3369g, "weiXinDFPay", str, ((FriendPayViewModel) ViewModelProviders.of(this.f3369g).get(FriendPayViewModel.class)).b().b, m.f().i());
        }
    }

    private void l() {
        FragmentActivity fragmentActivity = this.f3369g;
        if (fragmentActivity != null) {
            fragmentActivity.finish();
        }
    }

    private void m(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || !g0.a(this.f3369g)) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("supportXView", str3);
        hashMap.put("statusBarHint", str2);
        hashMap.put("appId", ((FriendPayViewModel) ViewModelProviders.of(this.f3369g).get(FriendPayViewModel.class)).b().f3349g);
        this.f3370h.d(this.f3369g, hashMap);
    }

    private void o() {
        com.jd.lib.cashier.sdk.f.a.c.a aVar = this.f3370h;
        if (aVar != null) {
            aVar.b(this.f3369g);
        }
        PayTaskStackManager.removeAllCashierTask();
    }

    private void p(com.jd.lib.cashier.sdk.f.a.b.c cVar) {
        if (cVar != null && this.f3370h != null && this.f3369g != null && TextUtils.equals(cVar.f3344f, "1")) {
            this.f3370h.b(this.f3369g);
        }
        l();
    }

    private void q(com.jd.lib.cashier.sdk.f.a.b.c cVar) {
        FragmentActivity fragmentActivity;
        if (cVar != null && this.f3370h != null && (fragmentActivity = this.f3369g) != null) {
            f0.b(fragmentActivity, cVar.d);
            if (TextUtils.equals(cVar.f3343e, "0")) {
                m(cVar.f3342c, "1", cVar.f3345g);
            } else if (TextUtils.equals(cVar.f3343e, "2")) {
                m(cVar.f3342c, "0", cVar.f3345g);
            } else {
                this.f3370h.c(this.f3369g, cVar.f3342c);
            }
        }
        r.b("CashierGetSuccessUrlImpl", "freindpay onPaySucStatus");
        PayTaskStackManager.removeCashierFriendPayDialogTask();
        PayTaskStackManager.removeCashierFriendPayTask();
        PayTaskStackManager.removeCashierPayTask();
    }

    public void a(com.jd.lib.cashier.sdk.f.a.b.c cVar) {
        if (cVar == null) {
            o();
            return;
        }
        r.b("CashierGetSuccessUrlImpl", "friendPayGetSuccessUrl--->" + cVar.toString());
        c(cVar.b);
        if (!TextUtils.equals(cVar.a, "0")) {
            o();
        } else if (TextUtils.equals(cVar.b, "1")) {
            q(cVar);
        } else {
            p(cVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((FriendPayViewModel) ViewModelProviders.of(fragmentActivity).get(FriendPayViewModel.class)).h().observe(fragmentActivity, this);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.c
    public void h(Window window) {
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: n */
    public void onChanged(@Nullable com.jd.lib.cashier.sdk.f.a.b.c cVar) {
        try {
            a(cVar);
        } catch (Exception e2) {
            o();
            e2.printStackTrace();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3369g != null) {
            this.f3369g = null;
        }
        if (this.f3370h != null) {
            this.f3370h = null;
        }
    }
}
