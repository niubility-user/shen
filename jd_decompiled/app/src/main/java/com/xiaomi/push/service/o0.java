package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.a7;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.m3;
import com.xiaomi.push.m7;
import com.xiaomi.push.m8;
import com.xiaomi.push.r3;
import com.xiaomi.push.v3;
import java.util.HashMap;

/* loaded from: classes11.dex */
public class o0 implements v3 {
    @Override // com.xiaomi.push.v3
    public void a(Context context, HashMap<String, String> hashMap) {
        c8 c8Var = new c8();
        c8Var.b(r3.b(context).d());
        c8Var.d(r3.b(context).n());
        c8Var.c(m7.AwakeAppResponse.f179a);
        c8Var.a(f0.a());
        c8Var.f113a = hashMap;
        byte[] f2 = m8.f(k.d(c8Var.c(), c8Var.b(), c8Var, c7.Notification));
        if (!(context instanceof XMPushService)) {
            g.j.a.a.a.c.o("MoleInfo : context is not correct in pushLayer " + c8Var.m34a());
            return;
        }
        g.j.a.a.a.c.o("MoleInfo : send data directly in pushLayer " + c8Var.m34a());
        ((XMPushService) context).a(context.getPackageName(), f2, true);
    }

    @Override // com.xiaomi.push.v3
    public void b(Context context, HashMap<String, String> hashMap) {
        g.j.a.a.a.c.o("MoleInfo\uff1a\u3000" + m3.e(hashMap));
    }

    @Override // com.xiaomi.push.v3
    public void c(Context context, HashMap<String, String> hashMap) {
        a7 a = a7.a(context);
        if (a != null) {
            a.f("category_awake_app", "wake_up_app", 1L, m3.c(hashMap));
        }
    }
}
