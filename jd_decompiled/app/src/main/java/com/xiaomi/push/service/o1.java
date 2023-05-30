package com.xiaomi.push.service;

import com.xiaomi.push.a6;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class o1 extends XMPushService.j {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ String f19157h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ byte[] f19158i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ XMPushService f19159j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o1(XMPushService xMPushService, int i2, String str, byte[] bArr) {
        super(i2);
        this.f19159j = xMPushService;
        this.f19157h = str;
        this.f19158i = bArr;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        try {
            k.l(this.f19159j, this.f19157h, this.f19158i);
        } catch (a6 e2) {
            g.j.a.a.a.c.s(e2);
            this.f19159j.a(10, e2);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "send mi push message";
    }
}
