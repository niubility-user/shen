package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.m3;
import com.xiaomi.push.m7;
import com.xiaomi.push.r3;
import com.xiaomi.push.v3;
import java.util.HashMap;

/* loaded from: classes11.dex */
public class p0 implements v3 {
    @Override // com.xiaomi.push.v3
    public void a(Context context, HashMap<String, String> hashMap) {
        c8 c8Var = new c8();
        c8Var.b(r3.b(context).d());
        c8Var.d(r3.b(context).n());
        c8Var.c(m7.AwakeAppResponse.f179a);
        c8Var.a(com.xiaomi.push.service.f0.a());
        c8Var.f113a = hashMap;
        f0.h(context).z(c8Var, c7.Notification, true, null, true);
        g.j.a.a.a.c.o("MoleInfo\uff1a\u3000send data in app layer");
    }

    @Override // com.xiaomi.push.v3
    public void b(Context context, HashMap<String, String> hashMap) {
        g.j.a.a.a.c.o("MoleInfo\uff1a\u3000" + m3.e(hashMap));
        String str = hashMap.get("awake_info");
        if (String.valueOf(1007).equals(hashMap.get("event_type"))) {
            b1.d(context, str);
        }
    }

    @Override // com.xiaomi.push.v3
    public void c(Context context, HashMap<String, String> hashMap) {
        o.b("category_awake_app", "wake_up_app", 1L, m3.c(hashMap));
        g.j.a.a.a.c.o("MoleInfo\uff1a\u3000send data in app layer");
    }
}
