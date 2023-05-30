package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.mipush.sdk.b;
import com.xiaomi.mipush.sdk.e;
import com.xiaomi.mipush.sdk.f;
import com.xiaomi.mipush.sdk.f0;
import com.xiaomi.mipush.sdk.l0;
import com.xiaomi.mipush.sdk.m;
import com.xiaomi.mipush.sdk.o0;
import com.xiaomi.mipush.sdk.r0;
import com.xiaomi.mipush.sdk.x;
import com.xiaomi.push.j0;
import com.xiaomi.push.l9;
import com.xiaomi.push.u6;
import g.j.a.a.a.c;

/* loaded from: classes11.dex */
public class NetworkStatusReceiver extends BroadcastReceiver {
    private static boolean a;
    private boolean b;

    public NetworkStatusReceiver() {
        this.b = false;
        this.b = true;
    }

    public NetworkStatusReceiver(Object obj) {
        this.b = false;
        a = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        if (!f0.h(context).J() && o0.c(context).s() && !o0.c(context).x()) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(context, "com.xiaomi.push.service.XMPushService"));
                intent.setAction("com.xiaomi.push.network_status_changed");
                com.xiaomi.push.service.a.h(context).i(intent);
            } catch (Exception e2) {
                c.s(e2);
            }
        }
        u6.h(context);
        if (j0.p(context) && f0.h(context).P()) {
            f0.h(context).R();
        }
        if (j0.p(context)) {
            if ("syncing".equals(x.b(context).c(l0.DISABLE_PUSH))) {
                m.r(context);
            }
            if ("syncing".equals(x.b(context).c(l0.ENABLE_PUSH))) {
                m.s(context);
            }
            x b = x.b(context);
            l0 l0Var = l0.UPLOAD_HUAWEI_TOKEN;
            if ("syncing".equals(b.c(l0Var))) {
                f0.h(context).E(null, l0Var, r0.ASSEMBLE_PUSH_HUAWEI, "net");
            }
            if ("syncing".equals(x.b(context).c(l0.UPLOAD_FCM_TOKEN))) {
                f0.h(context).E(null, l0Var, r0.ASSEMBLE_PUSH_HUAWEI, "net");
            }
            x b2 = x.b(context);
            l0 l0Var2 = l0.UPLOAD_COS_TOKEN;
            if ("syncing".equals(b2.c(l0Var2))) {
                f0.h(context).E(null, l0Var2, r0.ASSEMBLE_PUSH_COS, "net");
            }
            x b3 = x.b(context);
            l0 l0Var3 = l0.UPLOAD_FTOS_TOKEN;
            if ("syncing".equals(b3.c(l0Var3))) {
                f0.h(context).E(null, l0Var3, r0.ASSEMBLE_PUSH_FTOS, "net");
            }
            if (f.a() && f.d(context)) {
                f.c(context);
                f.b(context);
            }
            b.a(context);
            e.b(context);
        }
    }

    public static boolean a() {
        return a;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (this.b) {
            return;
        }
        l9.d().post(new a(this, context));
    }
}
