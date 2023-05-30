package com.xiaomi.push.service;

import com.xiaomi.push.a6;
import com.xiaomi.push.e5;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class d1 extends XMPushService.j {

    /* renamed from: h  reason: collision with root package name */
    private XMPushService f19076h;

    /* renamed from: i  reason: collision with root package name */
    private e5[] f19077i;

    public d1(XMPushService xMPushService, e5[] e5VarArr) {
        super(4);
        this.f19076h = null;
        this.f19076h = xMPushService;
        this.f19077i = e5VarArr;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        try {
            e5[] e5VarArr = this.f19077i;
            if (e5VarArr != null) {
                this.f19076h.a(e5VarArr);
            }
        } catch (a6 e2) {
            g.j.a.a.a.c.s(e2);
            this.f19076h.a(10, e2);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "batch send message.";
    }
}
