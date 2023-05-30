package com.jd.lib.cashier.sdk.pay.handler;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public class h implements d {

    /* renamed from: g  reason: collision with root package name */
    private CashierPayActivity f4168g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements com.jd.lib.cashier.sdk.b.d.b.c {
        a() {
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void a(@Nullable String str, @Nullable String str2) {
            h.this.f(str2, true);
            h.this.h();
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.c
        public void b(@Nullable String str, @Nullable String str2) {
            h.this.f(str2, false);
            h.this.h();
        }
    }

    public h(CashierPayActivity cashierPayActivity) {
        this.f4168g = cashierPayActivity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            p.a(this.f4168g, str);
        } else if (z) {
            p.m(this.f4168g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        PayTaskStackManager.removeAllCashierTask();
    }

    private void l(Bundle bundle) {
        if (g0.a(this.f4168g) && bundle != null) {
            String string = bundle.getString("query_pay_api_fail_msg_key");
            CashierCommonPopConfig cashierCommonPopConfig = (CashierCommonPopConfig) bundle.getParcelable("query_pay_api_fail_common_pop_key");
            CashierCommonPopConfig cashierCommonPopConfig2 = (CashierCommonPopConfig) bundle.getParcelable("query_pay_api_fail_pop_order_exception_key");
            if (cashierCommonPopConfig != null && cashierCommonPopConfig.canDialogShow()) {
                com.jd.lib.cashier.sdk.b.d.a.c(this.f4168g, cashierCommonPopConfig);
            } else if (cashierCommonPopConfig2 != null && cashierCommonPopConfig2.canDialogShow()) {
                com.jd.lib.cashier.sdk.b.d.a.k(this.f4168g, cashierCommonPopConfig2, new a());
            } else if (TextUtils.isEmpty(string)) {
            } else {
                com.jd.lib.cashier.sdk.b.d.a.f(this.f4168g, string);
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
        if (intent == null || !TextUtils.equals(intent.getAction(), "com.jd.query.pay.api.fail.action")) {
            return;
        }
        l(intent.getExtras());
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f4168g != null) {
            this.f4168g = null;
        }
    }
}
