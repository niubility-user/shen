package com.xiaomi.push;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class y3 implements u3 {
    private void c(Service service, Intent intent) {
        if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("waker_pkgname");
            String stringExtra2 = intent.getStringExtra("awake_info");
            if (TextUtils.isEmpty(stringExtra)) {
                n3.a(service.getApplicationContext(), "service", 1007, "old version message");
            } else if (TextUtils.isEmpty(stringExtra2)) {
                n3.a(service.getApplicationContext(), stringExtra, 1007, "play with service ");
            } else {
                String d = m3.d(stringExtra2);
                boolean isEmpty = TextUtils.isEmpty(d);
                Context applicationContext = service.getApplicationContext();
                if (isEmpty) {
                    n3.a(applicationContext, "service", 1008, "B get a incorrect message");
                } else {
                    n3.a(applicationContext, d, 1007, "old version message ");
                }
            }
        }
    }

    private void d(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (TextUtils.isEmpty(str3)) {
                n3.a(context, "service", 1008, "argument error");
            } else {
                n3.a(context, str3, 1008, "argument error");
            }
        } else if (!com.xiaomi.push.service.j2.c(context, str)) {
            n3.a(context, str3, 1003, "B is not ready");
        } else {
            n3.a(context, str3, 1002, "B is ready");
            n3.a(context, str3, 1004, "A is ready");
            try {
                Intent intent = new Intent();
                intent.setClassName(str, str2);
                intent.setAction("com.xiaomi.mipush.sdk.WAKEUP");
                intent.putExtra("waker_pkgname", context.getPackageName());
                intent.putExtra("awake_info", m3.b(str3));
                if (context.startService(intent) == null) {
                    n3.a(context, str3, 1008, "A is fail to help B's service");
                    return;
                }
                n3.a(context, str3, 1005, "A is successful");
                n3.a(context, str3, 1006, "The job is finished");
            } catch (Exception e2) {
                g.j.a.a.a.c.s(e2);
                n3.a(context, str3, 1008, "A meet a exception when help B's service");
            }
        }
    }

    @Override // com.xiaomi.push.u3
    public void a(Context context, Intent intent, String str) {
        if (context == null || !(context instanceof Service)) {
            return;
        }
        c((Service) context, intent);
    }

    @Override // com.xiaomi.push.u3
    public void b(Context context, q3 q3Var) {
        if (q3Var != null) {
            d(context, q3Var.b(), q3Var.g(), q3Var.i());
        }
    }
}
