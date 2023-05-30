package com.jd.lib.cashier.sdk.quickpay.aac.impl;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.paychannel.wxpay.entity.WXPayInfoEntity;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.core.utils.u;
import com.jd.lib.cashier.sdk.d.g.g.f;
import com.jd.lib.cashier.sdk.i.a.b.c;
import com.jd.lib.cashier.sdk.i.a.b.d;
import com.jd.lib.cashier.sdk.quickpay.aac.viewmodel.CashierQuickPayViewModel;
import com.jd.lib.cashier.sdk.quickpay.bean.JDPayServiceEntity;
import com.jd.lib.cashier.sdk.quickpay.view.CashierQuickPayActivity;

/* loaded from: classes14.dex */
public class CashierQuickPayPayingImpl implements b, Observer<d> {

    /* renamed from: g  reason: collision with root package name */
    private CashierQuickPayActivity f4187g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[f.values().length];
            a = iArr;
            try {
                iArr[f.WEIXIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[f.JDPAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public CashierQuickPayPayingImpl(CashierQuickPayActivity cashierQuickPayActivity) {
        this.f4187g = cashierQuickPayActivity;
    }

    private void a(c cVar, JDPayServiceEntity jDPayServiceEntity) {
        if (jDPayServiceEntity != null && g0.a(this.f4187g)) {
            com.jd.lib.cashier.sdk.d.g.c.c.a aVar = new com.jd.lib.cashier.sdk.d.g.c.c.a();
            aVar.b = jDPayServiceEntity.appId;
            aVar.f3265c = jDPayServiceEntity.sdkParam;
            aVar.d = jDPayServiceEntity.cashierUrl;
            if (!TextUtils.isEmpty(jDPayServiceEntity.controllActionParam)) {
                aVar.f3266e = jDPayServiceEntity.controllActionParam;
            }
            if (cVar != null) {
                String str = cVar.b;
                aVar.a = cVar.a;
                aVar.f3267f = cVar.f3596c;
            }
            new com.jd.lib.cashier.sdk.d.g.c.b.a().b(this.f4187g, aVar);
            return;
        }
        l();
    }

    private void c(c cVar, WXPayInfoEntity wXPayInfoEntity) {
        if (!u.b()) {
            f0.a(this.f4187g, R.string.lib_cashier_sdk_pay_wx_not_installed);
            l();
        } else if (!u.c()) {
            f0.a(this.f4187g, R.string.lib_cashier_sdk_pay_wx_not_supported);
            l();
        } else if (wXPayInfoEntity != null && g0.a(this.f4187g)) {
            com.jd.lib.cashier.sdk.d.g.j.c.a aVar = new com.jd.lib.cashier.sdk.d.g.j.c.a();
            aVar.d = wXPayInfoEntity.getSign();
            wXPayInfoEntity.getPayEnum();
            aVar.f3306e = wXPayInfoEntity.getPrepayId();
            aVar.f3308g = wXPayInfoEntity.getNonceStr();
            aVar.f3307f = wXPayInfoEntity.getPartnerId();
            aVar.b = wXPayInfoEntity.getTimeStamp();
            aVar.f3305c = wXPayInfoEntity.getPackage();
            if (cVar != null) {
                aVar.a = cVar.a;
            }
            new com.jd.lib.cashier.sdk.d.g.j.b.a().b(this.f4187g, aVar);
        } else {
            l();
        }
    }

    private void h(d dVar) {
        if (dVar == null || dVar.a == null) {
            return;
        }
        m(false);
        int i2 = a.a[dVar.a.ordinal()];
        if (i2 == 1) {
            m(true);
            c(dVar.d, dVar.b);
        } else if (i2 != 2) {
            l();
        } else {
            a(dVar.d, dVar.f3597c);
        }
    }

    private void l() {
        p.m(this.f4187g);
        PayTaskStackManager.removeAllCashierTask();
    }

    private void m(boolean z) {
        if (g0.a(this.f4187g)) {
            ((CashierQuickPayViewModel) ViewModelProviders.of(this.f4187g).get(CashierQuickPayViewModel.class)).b().q = z;
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((CashierQuickPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierQuickPayViewModel.class)).f().observe(fragmentActivity, this);
        }
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public void onChanged(d dVar) {
        if (dVar != null && dVar.d != null) {
            h(dVar);
        } else {
            l();
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f4187g != null) {
            this.f4187g = null;
        }
    }
}
