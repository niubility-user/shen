package com.jd.lib.cashier.sdk.d.g.g;

import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.d.g.g.b;

/* loaded from: classes14.dex */
public abstract class a<PayApiParam extends b> implements d {

    /* renamed from: g */
    private PayApiParam f3283g;

    /* renamed from: com.jd.lib.cashier.sdk.d.g.g.a$a */
    /* loaded from: classes14.dex */
    class RunnableC0118a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ b f3284g;

        /* renamed from: h */
        final /* synthetic */ FragmentActivity f3285h;

        RunnableC0118a(b bVar, FragmentActivity fragmentActivity) {
            a.this = r1;
            this.f3284g = bVar;
            this.f3285h = fragmentActivity;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            a.this.f3283g = this.f3284g;
            a.this.d(this.f3285h, this.f3284g);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.g.g.d
    public PayApiParam a() {
        return this.f3283g;
    }

    @Override // com.jd.lib.cashier.sdk.d.g.g.d
    public void b(FragmentActivity fragmentActivity, b bVar) {
        try {
            if (g0.a(fragmentActivity)) {
                fragmentActivity.runOnUiThread(new RunnableC0118a(bVar, fragmentActivity));
            }
        } catch (Exception e2) {
            r.d(getClass().getSimpleName(), e2.getMessage());
            com.jd.lib.cashier.sdk.d.h.a.a("PayChannelFunction", "PayChannelException", "AbstractPay.doPay()", e2.getMessage());
        }
    }

    public abstract void d(FragmentActivity fragmentActivity, PayApiParam payapiparam);
}
