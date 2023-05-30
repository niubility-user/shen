package com.xiaomi.push.service;

import com.xiaomi.push.c3;
import com.xiaomi.push.p5;
import com.xiaomi.push.s5;
import java.util.Map;

/* loaded from: classes11.dex */
class u1 extends p5 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public u1(XMPushService xMPushService, Map map, int i2, String str, s5 s5Var) {
        super(map, i2, str, s5Var);
    }

    @Override // com.xiaomi.push.p5
    public byte[] g() {
        try {
            c3 c3Var = new c3();
            c3Var.j(z0.f().a());
            return c3Var.h();
        } catch (Exception e2) {
            g.j.a.a.a.c.o("getOBBString err: " + e2.toString());
            return null;
        }
    }
}
