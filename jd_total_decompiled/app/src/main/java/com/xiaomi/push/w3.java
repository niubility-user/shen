package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class w3 implements u3 {
    private void c(Context context, String str) {
        try {
            if (!TextUtils.isEmpty(str) && context != null) {
                String[] split = str.split("/");
                if (split.length > 0 && !TextUtils.isEmpty(split[split.length - 1])) {
                    String str2 = split[split.length - 1];
                    if (TextUtils.isEmpty(str2)) {
                        n3.a(context, "provider", 1008, "B get a incorrect message");
                        return;
                    }
                    String decode = Uri.decode(str2);
                    if (TextUtils.isEmpty(decode)) {
                        n3.a(context, "provider", 1008, "B get a incorrect message");
                        return;
                    }
                    String d = m3.d(decode);
                    if (!TextUtils.isEmpty(d)) {
                        n3.a(context, d, 1007, "play with provider successfully");
                        return;
                    }
                }
            }
            n3.a(context, "provider", 1008, "B get a incorrect message");
        } catch (Exception e2) {
            n3.a(context, "provider", 1008, "B meet a exception" + e2.getMessage());
        }
    }

    private void d(Context context, q3 q3Var) {
        String e2 = q3Var.e();
        String i2 = q3Var.i();
        int a = q3Var.a();
        if (context == null || TextUtils.isEmpty(e2) || TextUtils.isEmpty(i2)) {
            if (TextUtils.isEmpty(i2)) {
                n3.a(context, "provider", 1008, "argument error");
            } else {
                n3.a(context, i2, 1008, "argument error");
            }
        } else if (!com.xiaomi.push.service.j2.e(context, e2)) {
            n3.a(context, i2, 1003, "B is not ready");
        } else {
            n3.a(context, i2, 1002, "B is ready");
            n3.a(context, i2, 1004, "A is ready");
            String b = m3.b(i2);
            try {
                if (TextUtils.isEmpty(b)) {
                    n3.a(context, i2, 1008, "info is empty");
                } else if (a == 1 && !r3.m(context)) {
                    n3.a(context, i2, 1008, "A not in foreground");
                } else {
                    String type = context.getContentResolver().getType(m3.a(e2, b));
                    if (TextUtils.isEmpty(type) || !"success".equals(type)) {
                        n3.a(context, i2, 1008, "A is fail to help B's provider");
                        return;
                    }
                    n3.a(context, i2, 1005, "A is successful");
                    n3.a(context, i2, 1006, "The job is finished");
                }
            } catch (Exception e3) {
                g.j.a.a.a.c.s(e3);
                n3.a(context, i2, 1008, "A meet a exception when help B's provider");
            }
        }
    }

    @Override // com.xiaomi.push.u3
    public void a(Context context, Intent intent, String str) {
        c(context, str);
    }

    @Override // com.xiaomi.push.u3
    public void b(Context context, q3 q3Var) {
        if (q3Var != null) {
            d(context, q3Var);
        } else {
            n3.a(context, "provider", 1008, "A receive incorrect message");
        }
    }
}
