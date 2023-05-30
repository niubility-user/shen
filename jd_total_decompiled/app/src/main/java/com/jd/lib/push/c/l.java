package com.jd.lib.push.c;

import android.content.Context;
import com.jd.lib.push.c.k;
import com.jingdong.jdsdk.JdSdk;
import com.vivo.push.IPushActionListener;
import com.vivo.push.PushClient;

/* loaded from: classes16.dex */
public class l extends com.jd.lib.push.c.a {

    /* loaded from: classes16.dex */
    public class a implements IPushActionListener {
        final /* synthetic */ PushClient a;

        a(PushClient pushClient) {
            l.this = r1;
            this.a = pushClient;
        }

        @Override // com.vivo.push.IPushActionListener
        public void onStateChanged(int i2) {
            if (i2 != 0) {
                com.jingdong.jdpush_new.j.g.d("PushChannel", "\u6253\u5f00VIVO push\u5931\u8d25" + i2);
                com.jingdong.jdpush_new.mta.b.b().h(l.this.a, i2);
                return;
            }
            com.jingdong.jdpush_new.j.g.b("PushChannel", "\u6253\u5f00VIVO push\u6210\u529f");
            com.jingdong.jdpush_new.mta.b.b().l(8200);
            com.jingdong.jdpush_new.mta.b.b().c().romDT = this.a.getRegId();
            com.jd.lib.push.a.b(8, this.a.getRegId());
        }
    }

    public l() {
        super(8);
    }

    @Override // com.jd.lib.push.c.a
    public void b(Context context) {
    }

    @Override // com.jd.lib.push.c.a
    public String c() {
        return com.jingdong.jdpush_new.j.c.e(this.f5659c, "com.vivo.pushservice");
    }

    @Override // com.jd.lib.push.c.a
    public void e(Context context, int i2) {
    }

    @Override // com.jd.lib.push.c.a
    public void f() {
        boolean z;
        com.jingdong.jdpush_new.j.g.b("PushChannel", "--------->VIVO\u8bbe\u5907\u63a8\u9001\u901a\u9053\u5f00\u59cb\u521d\u59cb\u5316");
        k kVar = this.b;
        boolean z2 = true;
        if (kVar != null) {
            k.a h2 = kVar.h();
            boolean b = h2.b();
            z2 = h2.a();
            z = b;
        } else {
            z = true;
        }
        com.jingdong.jdpush_new.mta.b.b().l(100300);
        if (z2) {
            com.jingdong.jdpush_new.mta.b.b().l(100320);
            g.h();
        }
        if (z) {
            com.jingdong.jdpush_new.mta.b.b().l(260);
            g();
        }
    }

    public void g() {
        try {
            com.jingdong.jdpush_new.mta.b.b().l(8000);
            PushClient pushClient = PushClient.getInstance(JdSdk.getInstance().getApplicationContext());
            pushClient.initialize();
            com.jingdong.jdpush_new.mta.b.b().l(8100);
            com.jingdong.jdpush_new.j.g.i("PushChannel", "VIVO\u5382\u5546SDK initialize \u6210\u529f");
            pushClient.turnOnPush(new a(pushClient));
        } catch (Throwable th) {
            com.jingdong.jdpush_new.j.g.e("PushChannel", "vivo\u901a\u9053\u521d\u59cb\u5316\u5931\u8d25", th);
            com.jingdong.jdpush_new.mta.b.b().i(this.a, th);
        }
    }
}
