package com.jd.lib.push.c;

import android.content.Context;
import com.jd.lib.push.pushIntentService.MessageIntentService;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;

/* loaded from: classes16.dex */
public class g extends a {

    /* renamed from: e */
    private static Boolean f5667e;

    public g() {
        super(0);
    }

    public static boolean g() {
        if (f5667e == null) {
            f5667e = Configuration.getBooleanProperty(Configuration.PUSH_MSG_MODE, Boolean.FALSE);
        }
        return f5667e.booleanValue();
    }

    public static void h() {
        if (g()) {
            try {
                com.jingdong.jdpush_new.j.g.i("PushChannel", "openMsgService-->>startJdService--->>\u81ea\u5efa\u901a\u9053\u5f00\u542f");
                com.jingdong.jdpush_new.a.h(JdSdk.getInstance().getApplication().getApplicationContext(), MessageIntentService.class);
            } catch (Throwable th) {
                com.jingdong.jdpush_new.j.g.f("\u81ea\u5efa\u901a\u9053\u5f00\u542f\u5f02\u5e38", th);
            }
        }
    }

    @Override // com.jd.lib.push.c.a
    public void b(Context context) {
    }

    @Override // com.jd.lib.push.c.a
    public String c() {
        return com.jingdong.jdpush_new.j.c.m();
    }

    @Override // com.jd.lib.push.c.a
    public void e(Context context, int i2) {
    }

    @Override // com.jd.lib.push.c.a
    public void f() {
        com.jingdong.jdpush_new.j.g.b("PushChannel", "--------->\u4e09\u65b9\u5382\u5546\u8bbe\u5907\u63a8\u9001\u901a\u9053\u5f00\u59cb\u521d\u59cb\u5316");
        k kVar = this.b;
        boolean a = kVar != null ? kVar.c().a() : true;
        com.jingdong.jdpush_new.mta.b.b().l(100300);
        if (a) {
            com.jingdong.jdpush_new.mta.b.b().l(100320);
            h();
        }
    }
}
