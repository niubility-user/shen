package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.i;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class c1 extends i.a {

    /* renamed from: g */
    final /* synthetic */ c8 f18368g;

    /* renamed from: h */
    final /* synthetic */ Context f18369h;

    public c1(c8 c8Var, Context context) {
        this.f18368g = c8Var;
        this.f18369h = context;
    }

    @Override // com.xiaomi.push.i.a
    public String b() {
        return "22";
    }

    @Override // java.lang.Runnable
    public void run() {
        c8 c8Var = this.f18368g;
        if (c8Var != null) {
            c8Var.a(com.xiaomi.push.service.f0.a());
            f0.h(this.f18369h.getApplicationContext()).z(this.f18368g, c7.Notification, true, null, true);
        }
    }
}
