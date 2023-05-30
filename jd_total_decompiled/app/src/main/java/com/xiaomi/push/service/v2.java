package com.xiaomi.push.service;

import com.xiaomi.push.a6;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.i0;
import java.util.Collection;

/* loaded from: classes11.dex */
public class v2 extends XMPushService.j {

    /* renamed from: h  reason: collision with root package name */
    private XMPushService f19194h;

    /* renamed from: i  reason: collision with root package name */
    private byte[] f19195i;

    /* renamed from: j  reason: collision with root package name */
    private String f19196j;

    /* renamed from: k  reason: collision with root package name */
    private String f19197k;

    /* renamed from: l  reason: collision with root package name */
    private String f19198l;

    public v2(XMPushService xMPushService, String str, String str2, String str3, byte[] bArr) {
        super(9);
        this.f19194h = xMPushService;
        this.f19196j = str;
        this.f19195i = bArr;
        this.f19197k = str2;
        this.f19198l = str3;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        i0.b next;
        s2 b = t2.b(this.f19194h);
        if (b == null) {
            try {
                b = t2.c(this.f19194h, this.f19196j, this.f19197k, this.f19198l);
            } catch (Exception e2) {
                g.j.a.a.a.c.D("fail to register push account. " + e2);
            }
        }
        if (b == null) {
            g.j.a.a.a.c.D("no account for registration.");
            w2.a(this.f19194h, 70000002, "no account.");
            return;
        }
        g.j.a.a.a.c.o("do registration now.");
        Collection<i0.b> f2 = i0.c().f("5");
        if (f2.isEmpty()) {
            next = b.a(this.f19194h);
            k.j(this.f19194h, next);
            i0.c().l(next);
        } else {
            next = f2.iterator().next();
        }
        if (!this.f19194h.m159c()) {
            w2.e(this.f19196j, this.f19195i);
            this.f19194h.a(true);
            return;
        }
        try {
            i0.c cVar = next.f19105m;
            if (cVar == i0.c.binded) {
                k.l(this.f19194h, this.f19196j, this.f19195i);
            } else if (cVar == i0.c.unbind) {
                w2.e(this.f19196j, this.f19195i);
                XMPushService xMPushService = this.f19194h;
                xMPushService.getClass();
                xMPushService.a(new XMPushService.b(next));
            }
        } catch (a6 e3) {
            g.j.a.a.a.c.D("meet error, disconnect connection. " + e3);
            this.f19194h.a(10, e3);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "register app";
    }
}
