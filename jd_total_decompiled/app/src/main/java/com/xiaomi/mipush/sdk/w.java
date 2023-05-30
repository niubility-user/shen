package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.i;
import com.xiaomi.push.i7;
import com.xiaomi.push.m7;
import com.xiaomi.push.m8;
import com.xiaomi.push.u7;

/* loaded from: classes11.dex */
public class w extends i.a {

    /* renamed from: g  reason: collision with root package name */
    private Context f18429g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f18430h = false;

    public w(Context context) {
        this.f18429g = context;
    }

    @Override // com.xiaomi.push.i.a
    public String b() {
        return "2";
    }

    @Override // java.lang.Runnable
    public void run() {
        com.xiaomi.push.service.b0 d = com.xiaomi.push.service.b0.d(this.f18429g);
        u7 u7Var = new u7();
        if (this.f18430h) {
            u7Var.a(0);
            u7Var.b(0);
        } else {
            u7Var.a(com.xiaomi.push.service.d0.a(d, i7.MISC_CONFIG));
            u7Var.b(com.xiaomi.push.service.d0.a(d, i7.PLUGIN_CONFIG));
        }
        c8 c8Var = new c8("-1", false);
        c8Var.c(m7.DailyCheckClientConfig.f179a);
        c8Var.a(m8.f(u7Var));
        g.j.a.a.a.c.A("OcVersionCheckJob", "-->check version: checkMessage=", u7Var);
        f0.h(this.f18429g).w(c8Var, c7.Notification, null);
    }
}
