package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.xiaomi.push.a8;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.m7;
import com.xiaomi.push.z6;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class j implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        Context context;
        Context context2;
        Context context3;
        Context context4;
        Context context5;
        Context context6;
        Context context7;
        if (a8.t()) {
            return;
        }
        context = m.a;
        if (z6.s(context) == null) {
            context7 = m.a;
            if (!com.xiaomi.push.z.a(context7).mo30a()) {
                return;
            }
        }
        c8 c8Var = new c8();
        context2 = m.a;
        c8Var.b(o0.c(context2).d());
        c8Var.c(m7.ClientInfoUpdate.f179a);
        c8Var.a(com.xiaomi.push.service.f0.a());
        c8Var.a(new HashMap());
        String str = "";
        context3 = m.a;
        String s = z6.s(context3);
        if (!TextUtils.isEmpty(s)) {
            str = "" + com.xiaomi.push.p0.b(s);
        }
        context4 = m.a;
        String w = z6.w(context4);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(w)) {
            str = str + DYConstants.DY_REGEX_COMMA + w;
        }
        if (!TextUtils.isEmpty(str)) {
            c8Var.m35a().put("imei_md5", str);
        }
        context5 = m.a;
        com.xiaomi.push.z.a(context5).d(c8Var.m35a());
        int c2 = z6.c();
        if (c2 >= 0) {
            c8Var.m35a().put("space_id", Integer.toString(c2));
        }
        context6 = m.a;
        f0.h(context6).y(c8Var, c7.Notification, false, null);
    }
}
