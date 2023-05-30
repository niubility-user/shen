package com.xiaomi.push;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class x3 implements u3 {
    private void c(Service service, Intent intent) {
        String stringExtra = intent.getStringExtra("awake_info");
        if (!TextUtils.isEmpty(stringExtra)) {
            String d = m3.d(stringExtra);
            if (!TextUtils.isEmpty(d)) {
                n3.a(service.getApplicationContext(), d, 1007, "play with service successfully");
                return;
            }
        }
        n3.a(service.getApplicationContext(), "service", 1008, "B get a incorrect message");
    }

    private void d(Context context, q3 q3Var) {
        String b = q3Var.b();
        String e2 = q3Var.e();
        String i2 = q3Var.i();
        int a = q3Var.a();
        if (context == null || TextUtils.isEmpty(b) || TextUtils.isEmpty(e2) || TextUtils.isEmpty(i2)) {
            if (TextUtils.isEmpty(i2)) {
                n3.a(context, "service", 1008, "argument error");
            } else {
                n3.a(context, i2, 1008, "argument error");
            }
        } else if (!com.xiaomi.push.service.j2.d(context, b, e2)) {
            n3.a(context, i2, 1003, "B is not ready");
        } else {
            n3.a(context, i2, 1002, "B is ready");
            n3.a(context, i2, 1004, "A is ready");
            try {
                Intent intent = new Intent();
                intent.setAction(e2);
                intent.setPackage(b);
                intent.putExtra("awake_info", m3.b(i2));
                if (a == 1 && !r3.m(context)) {
                    n3.a(context, i2, 1008, "A not in foreground");
                } else if (context.startService(intent) == null) {
                    n3.a(context, i2, 1008, "A is fail to help B's service");
                } else {
                    n3.a(context, i2, 1005, "A is successful");
                    n3.a(context, i2, 1006, "The job is finished");
                }
            } catch (Exception e3) {
                g.j.a.a.a.c.s(e3);
                n3.a(context, i2, 1008, "A meet a exception when help B's service");
            }
        }
    }

    @Override // com.xiaomi.push.u3
    public void a(Context context, Intent intent, String str) {
        if (context == null || !(context instanceof Service)) {
            n3.a(context, "service", 1008, "A receive incorrect message");
        } else {
            c((Service) context, intent);
        }
    }

    @Override // com.xiaomi.push.u3
    public void b(Context context, q3 q3Var) {
        if (q3Var != null) {
            d(context, q3Var);
        } else {
            n3.a(context, "service", 1008, "A receive incorrect message");
        }
    }
}
