package com.jd.lib.push.c;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import com.heytap.msp.push.HeytapPushManager;
import com.jd.lib.push.c.k;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes16.dex */
public class j extends a {

    /* renamed from: e */
    private static boolean f5669e;

    public j() {
        super(6);
    }

    private static void g(String str, String str2) {
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                NotificationChannel notificationChannel = new NotificationChannel(str, str2, 3);
                notificationChannel.setDescription(str2);
                ((NotificationManager) JdSdk.getInstance().getApplication().getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
            } catch (Exception e2) {
                com.jingdong.jdpush_new.j.g.e("PushChannel", "OPPO\u521b\u5efa\u901a\u77e5\u901a\u9053\u5f02\u5e38", e2);
            }
        }
    }

    private static void h() {
        com.jingdong.jdpush_new.j.g.b("PushChannel", "--------->oppo\u79c1\u4fe1\u901a\u9053\u5f00\u59cb\u521b\u5efa");
        g("oppo_account_100", "\u8d26\u6237\u901a\u77e5");
        g("oppo_IM_101", "\u804a\u5929\u4e92\u52a8");
        g("oppo_logistics_102", "\u7269\u6d41\u63d0\u9192");
        g("oppo_follow_103", "\u5173\u6ce8\u8ba2\u9605");
        g("oppo_transaction_104", "\u4ea4\u6613\u63d0\u9192");
    }

    private static synchronized void i() {
        synchronized (j.class) {
            if (f5669e) {
                return;
            }
            try {
                HeytapPushManager.init(JdSdk.getInstance().getApplication().getApplicationContext(), false);
                f5669e = true;
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.jd.lib.push.c.a
    public void b(Context context) {
    }

    @Override // com.jd.lib.push.c.a
    public String c() {
        return com.jingdong.jdpush_new.j.c.e(this.f5659c, "com.heytap.mcs");
    }

    @Override // com.jd.lib.push.c.a
    public void e(Context context, int i2) {
    }

    @Override // com.jd.lib.push.c.a
    public void f() {
        boolean z;
        com.jingdong.jdpush_new.j.g.b("PushChannel", "--------->OPPO\u8bbe\u5907\u63a8\u9001\u901a\u9053\u5f00\u59cb\u521d\u59cb\u5316");
        k kVar = this.b;
        boolean z2 = true;
        if (kVar != null) {
            k.a f2 = kVar.f();
            boolean b = f2.b();
            z2 = f2.a();
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
            j();
        }
    }

    public void j() {
        try {
            com.jingdong.jdpush_new.mta.b.b().l(6000);
            i();
            if (HeytapPushManager.isSupportPush(JdSdk.getInstance().getApplication().getApplicationContext())) {
                com.jingdong.jdpush_new.j.g.b("PushChannel", "--------->\u8fd9\u4e2a\u662foppo\u7684\u673a\u5668\u652f\u6301\u63a8\u9001\uff0c\u521d\u59cb\u5316OPPO\u63a8\u9001");
                com.jingdong.jdpush_new.mta.b.b().l(R2.dimen.dp_470);
                h();
                if (ProcessUtil.isMainProcess()) {
                    com.jingdong.jdpush_new.mta.b.b().l(R2.dimen.dp_489);
                    HeytapPushManager.register(JdSdk.getInstance().getApplication().getApplicationContext(), "SmlmmCBnT5F4pUitqYnVA6XX", "T61Uiet1FrSKauD7GQVvyoHb", com.jd.lib.push.d.a.a);
                    com.jingdong.jdpush_new.j.g.b("PushChannel", "startOPPOService------->>\u542f\u52a8");
                    com.jingdong.jdpush_new.mta.b.b().l(R2.dimen.dp_542);
                } else {
                    com.jingdong.jdpush_new.j.g.b("PushChannel", "startOPPOService------->>not inMainProcess");
                    com.jingdong.jdpush_new.mta.b.b().l(R2.dimen.dp_48);
                }
            } else {
                com.jingdong.jdpush_new.mta.b.b().l(R2.dimen.dp_461);
            }
        } catch (Exception e2) {
            com.jingdong.jdpush_new.j.g.e("PushChannel", "startOPPOService------->>\u5f02\u5e38", e2);
            com.jingdong.jdpush_new.mta.b.b().i(this.a, e2);
        }
    }
}
