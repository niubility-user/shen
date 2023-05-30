package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.m8;
import com.xiaomi.push.p7;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.y7;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
class r2 extends XMPushService.j {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ String f19177h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ List f19178i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ String f19179j;

    /* renamed from: k  reason: collision with root package name */
    final /* synthetic */ q2 f19180k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r2(q2 q2Var, int i2, String str, List list, String str2) {
        super(i2);
        this.f19180k = q2Var;
        this.f19177h = str;
        this.f19178i = list;
        this.f19179j = str2;
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public void a() {
        String d;
        XMPushService xMPushService;
        d = this.f19180k.d(this.f19177h);
        ArrayList<c8> c2 = e1.c(this.f19178i, this.f19177h, d, 32768);
        if (c2 == null) {
            g.j.a.a.a.c.D("TinyData LongConnUploader.upload Get a null XmPushActionNotification list when TinyDataHelper.pack() in XMPushService.");
            return;
        }
        Iterator<c8> it = c2.iterator();
        while (it.hasNext()) {
            c8 next = it.next();
            next.a("uploadWay", "longXMPushService");
            y7 d2 = k.d(this.f19177h, d, next, c7.Notification);
            if (!TextUtils.isEmpty(this.f19179j) && !TextUtils.equals(this.f19177h, this.f19179j)) {
                if (d2.m185a() == null) {
                    p7 p7Var = new p7();
                    p7Var.a("-1");
                    d2.a(p7Var);
                }
                d2.m185a().b("ext_traffic_source_pkg", this.f19179j);
            }
            byte[] f2 = m8.f(d2);
            xMPushService = this.f19180k.a;
            xMPushService.a(this.f19177h, f2, true);
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.j
    public String b() {
        return "Send tiny data.";
    }
}
