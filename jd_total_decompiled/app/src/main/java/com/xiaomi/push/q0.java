package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.i;

/* loaded from: classes11.dex */
public class q0 extends i.a {

    /* renamed from: g  reason: collision with root package name */
    private Context f18953g;

    public q0(Context context) {
        this.f18953g = context;
    }

    private boolean c() {
        return g.j.b.b.b.e(this.f18953g).c().g();
    }

    @Override // com.xiaomi.push.i.a
    public String b() {
        return "100886";
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (c()) {
                g.j.a.a.a.c.B(this.f18953g.getPackageName() + " begin upload event");
                g.j.b.b.b.e(this.f18953g).s();
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
        }
    }
}
