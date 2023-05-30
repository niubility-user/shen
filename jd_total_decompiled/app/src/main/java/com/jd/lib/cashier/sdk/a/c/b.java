package com.jd.lib.cashier.sdk.a.c;

import android.content.Intent;
import android.os.Bundle;
import com.jd.lib.cashier.sdk.btcombinationpay.view.BtCombinationPayActivity;
import com.jd.lib.cashier.sdk.pay.handler.d;

/* loaded from: classes14.dex */
public class b implements com.jd.lib.cashier.sdk.d.d.a {

    /* renamed from: g  reason: collision with root package name */
    private BtCombinationPayActivity f2799g;

    /* renamed from: h  reason: collision with root package name */
    private d f2800h;

    /* renamed from: i  reason: collision with root package name */
    private d f2801i;

    public b(BtCombinationPayActivity btCombinationPayActivity, String str, String str2) {
        this.f2799g = btCombinationPayActivity;
        this.f2801i = new a(btCombinationPayActivity);
        this.f2800h = new c(btCombinationPayActivity, str, str2);
    }

    public void a(Bundle bundle) {
        d dVar = this.f2801i;
        if (dVar != null) {
            ((a) dVar).l(bundle);
        }
    }

    public void j(int i2, int i3, Intent intent) {
        d dVar = this.f2800h;
        if (dVar != null) {
            dVar.j(i2, i3, intent);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        d dVar = this.f2800h;
        if (dVar != null) {
            dVar.onDestroy();
            this.f2800h = null;
        }
        d dVar2 = this.f2801i;
        if (dVar2 != null) {
            dVar2.onDestroy();
            this.f2801i = null;
        }
        if (this.f2799g != null) {
            this.f2799g = null;
        }
    }
}
