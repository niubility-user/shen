package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.m7;
import com.xiaomi.push.m8;
import com.xiaomi.push.service.b1;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class l extends b1.a {

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ XMPushService f19122i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ s2 f19123j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(String str, long j2, XMPushService xMPushService, s2 s2Var) {
        super(str, j2);
        this.f19122i = xMPushService;
        this.f19123j = s2Var;
    }

    @Override // com.xiaomi.push.service.b1.a
    void a(b1 b1Var) {
        com.xiaomi.push.z a = com.xiaomi.push.z.a(this.f19122i);
        String d = b1Var.d("MSAID", "msaid");
        String a2 = a.a();
        if (TextUtils.isEmpty(a2) || TextUtils.equals(d, a2)) {
            return;
        }
        b1Var.g("MSAID", "msaid", a2);
        c8 c8Var = new c8();
        c8Var.b(this.f19123j.d);
        c8Var.c(m7.ClientInfoUpdate.f179a);
        c8Var.a(f0.a());
        c8Var.a(new HashMap());
        a.d(c8Var.m35a());
        byte[] f2 = m8.f(k.d(this.f19122i.getPackageName(), this.f19123j.d, c8Var, c7.Notification));
        XMPushService xMPushService = this.f19122i;
        xMPushService.a(xMPushService.getPackageName(), f2, true);
    }
}
