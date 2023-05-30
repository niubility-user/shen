package com.xiaomi.push.service;

import com.xiaomi.push.p5;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class z1 extends XMPushService.j {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ int f19212h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ String f19213i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ byte[] f19214j;

    /* renamed from: k  reason: collision with root package name */
    final /* synthetic */ XMPushService f19215k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z1(XMPushService xMPushService, int i2, int i3, String str, byte[] bArr) {
        super(i2);
        this.f19215k = xMPushService;
        this.f19212h = i3;
        this.f19213i = str;
        this.f19214j = bArr;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        p5 p5Var;
        t2.g(this.f19215k);
        i0.c().m("5");
        com.xiaomi.push.b.b(this.f19212h);
        p5Var = this.f19215k.f227a;
        p5Var.k(p5.b());
        g.j.a.a.a.c.o("clear account and start registration. " + this.f19213i);
        this.f19215k.a(this.f19214j, this.f19213i);
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "clear account cache.";
    }
}
