package com.jd.lib.cashier.sdk.j.a.d;

import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.j.a.a.b;

/* loaded from: classes14.dex */
public class a {
    public void a(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.j.a.e.a aVar) {
        if (fragmentActivity == null || aVar == null) {
            return;
        }
        com.jd.lib.cashier.sdk.j.b.a aVar2 = new com.jd.lib.cashier.sdk.j.b.a();
        b bVar = new b();
        aVar2.orderId = aVar.f3633c;
        aVar2.a = aVar.d;
        aVar2.setActivity(fragmentActivity);
        bVar.e(aVar2);
    }
}
