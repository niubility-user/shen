package com.jd.lib.cashier.sdk.a.c;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.btcombinationpay.view.BtCombinationPayActivity;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.pay.handler.d;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public class a implements d {

    /* renamed from: g  reason: collision with root package name */
    private BtCombinationPayActivity f2798g;

    /* renamed from: com.jd.lib.cashier.sdk.a.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    class C0094a implements com.jd.lib.cashier.sdk.b.d.b.c {
        C0094a() {
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void a(@Nullable String str, @Nullable String str2) {
            a.this.f(str2, true);
            a.this.h();
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void b(@Nullable String str, @Nullable String str2) {
            a.this.f(str2, false);
            a.this.h();
        }
    }

    public a(BtCombinationPayActivity btCombinationPayActivity) {
        this.f2798g = btCombinationPayActivity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            p.a(this.f2798g, str);
        } else if (z) {
            p.m(this.f2798g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        BtCombinationPayActivity btCombinationPayActivity = this.f2798g;
        if (btCombinationPayActivity != null) {
            btCombinationPayActivity.finish();
        }
        PayTaskStackManager.removeAllCashierTask();
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
    }

    public void l(Bundle bundle) {
        if (g0.a(this.f2798g) && bundle != null) {
            String string = bundle.getString("query_pay_api_fail_msg_key");
            CashierCommonPopConfig cashierCommonPopConfig = (CashierCommonPopConfig) bundle.getParcelable("query_pay_api_fail_common_pop_key");
            CashierCommonPopConfig cashierCommonPopConfig2 = (CashierCommonPopConfig) bundle.getParcelable("query_pay_api_fail_pop_order_exception_key");
            if (cashierCommonPopConfig != null && cashierCommonPopConfig.canDialogShow()) {
                com.jd.lib.cashier.sdk.b.d.a.c(this.f2798g, cashierCommonPopConfig);
            } else if (cashierCommonPopConfig2 != null && cashierCommonPopConfig2.canDialogShow()) {
                com.jd.lib.cashier.sdk.b.d.a.k(this.f2798g, cashierCommonPopConfig2, new C0094a());
            } else if (TextUtils.isEmpty(string)) {
            } else {
                com.jd.lib.cashier.sdk.b.d.a.f(this.f2798g, string);
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f2798g != null) {
            this.f2798g = null;
        }
    }
}
