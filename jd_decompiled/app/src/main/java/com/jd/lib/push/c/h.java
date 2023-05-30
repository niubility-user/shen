package com.jd.lib.push.c;

import android.content.Context;
import com.jd.lib.push.c.k;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.platform.business.personal.R2;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.constants.PushConstants;

/* loaded from: classes16.dex */
public class h extends a {
    public h() {
        super(3);
    }

    @Override // com.jd.lib.push.c.a
    public void b(Context context) {
    }

    @Override // com.jd.lib.push.c.a
    public String c() {
        return com.jingdong.jdpush_new.j.c.e(this.f5659c, PushConstants.PUSH_PACKAGE_NAME);
    }

    @Override // com.jd.lib.push.c.a
    public void e(Context context, int i2) {
    }

    @Override // com.jd.lib.push.c.a
    public void f() {
        boolean z;
        com.jingdong.jdpush_new.j.g.b("PushChannel", "--------->\u9b45\u65cf\u8bbe\u5907\u63a8\u9001\u901a\u9053\u5f00\u59cb\u521d\u59cb\u5316");
        k kVar = this.b;
        boolean z2 = true;
        if (kVar != null) {
            k.a e2 = kVar.e();
            boolean b = e2.b();
            z2 = e2.a();
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
            com.jingdong.jdpush_new.mta.b.b().l(3000);
            PushManager.register(JdSdk.getInstance().getApplication().getApplicationContext());
            PushManager.register(JdSdk.getInstance().getApplication().getApplicationContext(), Configuration.MZAppId, Configuration.MZAppKey);
            com.jingdong.jdpush_new.mta.b.b().l(R2.color.btn_white_color_pressed);
            com.jingdong.jdpush_new.j.g.b("PushChannel", "openMsgService------>>startMZService------->>\u9b45\u65cf\u670d\u52a1\u5f00\u542f");
        } catch (Exception e2) {
            com.jingdong.jdpush_new.j.g.e("PushChannel", "openMsgService------>>startMZService------->>\u5f02\u5e38", e2);
            com.jingdong.jdpush_new.mta.b.b().i(this.a, e2);
        }
    }
}
