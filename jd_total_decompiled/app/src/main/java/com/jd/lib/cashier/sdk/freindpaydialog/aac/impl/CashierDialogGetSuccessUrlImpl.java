package com.jd.lib.cashier.sdk.freindpaydialog.aac.impl;

import android.text.TextUtils;
import android.view.Window;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.core.aac.e;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.viewmodel.FriendPayDialogViewModel;
import com.jd.lib.cashier.sdk.g.a.b.c;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class CashierDialogGetSuccessUrlImpl implements Object, Observer<c>, com.jd.lib.cashier.sdk.d.d.a, e, Observer {

    /* renamed from: g */
    private FragmentActivity f3414g;

    /* renamed from: h */
    private com.jd.lib.cashier.sdk.g.a.c.a f3415h;

    public CashierDialogGetSuccessUrlImpl(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.g.a.c.a aVar) {
        this.f3414g = fragmentActivity;
        this.f3415h = aVar;
    }

    private void c(String str) {
        if (g0.a(this.f3414g)) {
            com.jd.lib.cashier.sdk.g.e.a.c(this.f3414g, "weiXinDFPay", str, ((FriendPayDialogViewModel) ViewModelProviders.of(this.f3414g).get(FriendPayDialogViewModel.class)).b().b, m.f().i());
        }
    }

    private void l() {
        FragmentActivity fragmentActivity = this.f3414g;
        if (fragmentActivity != null) {
            fragmentActivity.finish();
        }
    }

    private void m(String str, String str2, String str3) {
        if (!g0.a(this.f3414g) || TextUtils.isEmpty(str)) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("supportXView", str3);
        hashMap.put("statusBarHint", str2);
        hashMap.put("appId", ((FriendPayDialogViewModel) ViewModelProviders.of(this.f3414g).get(FriendPayDialogViewModel.class)).b().f3463g);
        this.f3415h.d(this.f3414g, hashMap);
    }

    private void o() {
        com.jd.lib.cashier.sdk.g.a.c.a aVar = this.f3415h;
        if (aVar != null) {
            aVar.b(this.f3414g);
        }
        PayTaskStackManager.removeAllCashierTask();
    }

    private void p(c cVar) {
        if (cVar != null && this.f3415h != null && this.f3414g != null && TextUtils.equals(cVar.f3458f, "1")) {
            this.f3415h.b(this.f3414g);
        }
        l();
    }

    private void q(c cVar) {
        FragmentActivity fragmentActivity;
        if (cVar != null && this.f3415h != null && (fragmentActivity = this.f3414g) != null) {
            f0.b(fragmentActivity, cVar.d);
            if (TextUtils.equals(cVar.f3457e, "0")) {
                m(cVar.f3456c, "1", cVar.f3459g);
            } else if (TextUtils.equals(cVar.f3457e, "2")) {
                m(cVar.f3456c, "0", cVar.f3459g);
            } else {
                this.f3415h.c(this.f3414g, cVar.f3456c);
            }
        }
        r.b("CashierDialogGetSuccessUrlImpl", "freindpay onPaySucStatus");
        PayTaskStackManager.removeCashierFriendPayDialogTask();
        PayTaskStackManager.removeCashierFriendPayTask();
        PayTaskStackManager.removeCashierPayTask();
    }

    public void a(c cVar) {
        if (cVar == null) {
            com.jd.lib.cashier.sdk.g.a.c.a aVar = this.f3415h;
            if (aVar != null) {
                aVar.b(this.f3414g);
            }
            PayTaskStackManager.removeAllCashierTask();
            return;
        }
        r.b("CashierDialogGetSuccessUrlImpl", "friendPayGetSuccessUrl--->" + cVar.toString());
        c(cVar.b);
        if (!TextUtils.equals(cVar.a, "0")) {
            o();
        } else if (TextUtils.equals(cVar.b, "1")) {
            q(cVar);
        } else {
            p(cVar);
        }
    }

    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((FriendPayDialogViewModel) ViewModelProviders.of(fragmentActivity).get(FriendPayDialogViewModel.class)).i().observe(fragmentActivity, this);
        }
    }

    public void h(Window window) {
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: n */
    public void onChanged(@Nullable c cVar) {
        a(cVar);
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3414g != null) {
            this.f3414g = null;
        }
        if (this.f3415h != null) {
            this.f3415h = null;
        }
    }
}
