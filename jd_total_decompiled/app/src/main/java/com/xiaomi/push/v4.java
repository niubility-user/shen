package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;

/* loaded from: classes11.dex */
class v4 extends XMPushService.j {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ u4 f19279h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v4(u4 u4Var, int i2) {
        super(i2);
        this.f19279h = u4Var;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        this.f19279h.e();
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "Handling bind stats";
    }
}
