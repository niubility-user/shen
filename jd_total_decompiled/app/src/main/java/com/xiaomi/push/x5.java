package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;

/* loaded from: classes11.dex */
public class x5 extends XMPushService.j {

    /* renamed from: h */
    final /* synthetic */ int f19305h;

    /* renamed from: i */
    final /* synthetic */ Exception f19306i;

    /* renamed from: j */
    final /* synthetic */ v5 f19307j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x5(v5 v5Var, int i2, int i3, Exception exc) {
        super(i2);
        this.f19307j = v5Var;
        this.f19305h = i3;
        this.f19306i = exc;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        this.f19307j.t.a(this.f19305h, this.f19306i);
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "shutdown the connection. " + this.f19305h + ", " + this.f19306i;
    }
}
