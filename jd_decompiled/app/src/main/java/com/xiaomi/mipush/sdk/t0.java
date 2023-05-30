package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.h7;
import com.xiaomi.push.service.b0;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class t0 extends b0.a {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ s0 f18427h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t0(s0 s0Var, int i2, String str) {
        super(i2, str);
        this.f18427h = s0Var;
    }

    @Override // com.xiaomi.push.service.b0.a
    protected void b() {
        Context context;
        boolean z;
        Context context2;
        context = this.f18427h.a;
        boolean m2 = com.xiaomi.push.service.b0.d(context).m(h7.AggregatePushSwitch.a(), true);
        z = this.f18427h.f18425c;
        if (z != m2) {
            this.f18427h.f18425c = m2;
            context2 = this.f18427h.a;
            v0.l(context2);
        }
    }
}
