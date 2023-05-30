package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;

/* loaded from: classes11.dex */
class w5 extends XMPushService.j {

    /* renamed from: h */
    final /* synthetic */ long f19284h;

    /* renamed from: i */
    final /* synthetic */ long f19285i;

    /* renamed from: j */
    final /* synthetic */ v5 f19286j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w5(v5 v5Var, int i2, long j2, long j3) {
        super(i2);
        this.f19286j = v5Var;
        this.f19284h = j2;
        this.f19285i = j3;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        Thread.yield();
        if (!this.f19286j.D() || this.f19286j.r(this.f19284h)) {
            return;
        }
        com.xiaomi.push.service.n2.c(this.f19286j.t).m();
        this.f19286j.t.a(22, (Exception) null);
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "check the ping-pong." + this.f19285i;
    }
}
