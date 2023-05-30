package com.xiaomi.push.service;

import com.xiaomi.push.a6;
import com.xiaomi.push.a8;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.y7;
import java.util.Map;

/* loaded from: classes11.dex */
public class d extends XMPushService.j {

    /* renamed from: h */
    final /* synthetic */ XMPushService f19074h;

    /* renamed from: i */
    final /* synthetic */ y7 f19075i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(int i2, XMPushService xMPushService, y7 y7Var) {
        super(i2);
        this.f19074h = xMPushService;
        this.f19075i = y7Var;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        Map<String, String> map = null;
        try {
            if (a8.j(this.f19074h)) {
                try {
                    map = j.a(this.f19074h, this.f19075i);
                } catch (Throwable unused) {
                }
            }
            k.i(this.f19074h, x2.c(this.f19074h, this.f19075i, map));
        } catch (a6 e2) {
            g.j.a.a.a.c.s(e2);
            this.f19074h.a(10, e2);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "send ack message for message.";
    }
}
