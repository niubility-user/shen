package com.jd.lib.push.c;

import android.content.Context;
import com.jd.lib.push.c.k;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.xiaomi.mipush.sdk.m;

/* loaded from: classes16.dex */
public class i extends com.jd.lib.push.c.a {

    /* renamed from: e */
    private String f5668e;

    /* loaded from: classes16.dex */
    public class a implements g.j.a.a.a.a {
        a() {
            i.this = r1;
        }

        @Override // g.j.a.a.a.a
        public void a(String str, Throwable th) {
            com.jingdong.jdpush_new.j.g.e(i.this.f5668e, str, th);
        }

        @Override // g.j.a.a.a.a
        public void log(String str) {
            com.jingdong.jdpush_new.j.g.b(i.this.f5668e, str);
        }
    }

    public i() {
        super(1);
        this.f5668e = "MiChannel";
    }

    @Override // com.jd.lib.push.c.a
    public void b(Context context) {
    }

    @Override // com.jd.lib.push.c.a
    public String c() {
        return com.jingdong.jdpush_new.j.c.e(this.f5659c, "com.xiaomi.xmsf");
    }

    @Override // com.jd.lib.push.c.a
    public void e(Context context, int i2) {
    }

    @Override // com.jd.lib.push.c.a
    public void f() {
        boolean z;
        com.jingdong.jdpush_new.j.g.b("MiChannel", "--------->\u5c0f\u7c73\u8bbe\u5907\u63a8\u9001\u901a\u9053\u5f00\u59cb\u521d\u59cb\u5316");
        k kVar = this.b;
        boolean z2 = true;
        if (kVar != null) {
            k.a d = kVar.d();
            boolean b = d.b();
            z2 = d.a();
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
            h();
        }
    }

    public void h() {
        try {
            Context applicationContext = JdSdk.getInstance().getApplicationContext();
            com.jingdong.jdpush_new.mta.b.b().l(1000);
            if (ProcessUtil.isMainProcess()) {
                com.jingdong.jdpush_new.mta.b.b().l(1010);
                m.I(applicationContext, Configuration.MIAppId, Configuration.MIAppKey);
                com.xiaomi.mipush.sdk.g.c(applicationContext, new a());
                com.jingdong.jdpush_new.j.g.b("MiChannel", "openMsgService------>>startMiService------->>\u5c0f\u7c73\u670d\u52a1\u5f00\u542f");
            } else {
                com.jingdong.jdpush_new.mta.b.b().l(1020);
            }
        } catch (Exception e2) {
            com.jingdong.jdpush_new.j.g.e("MiChannel", "openMsgService------>>startMiService------->>\u5f02\u5e38", e2);
            com.jingdong.jdpush_new.mta.b.b().i(this.a, e2);
        }
    }
}
