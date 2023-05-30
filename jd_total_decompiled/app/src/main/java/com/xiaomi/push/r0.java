package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.i;

/* loaded from: classes11.dex */
public class r0 extends i.a {

    /* renamed from: g  reason: collision with root package name */
    private Context f18968g;

    public r0(Context context) {
        this.f18968g = context;
    }

    private boolean c() {
        return g.j.b.b.b.e(this.f18968g).c().h();
    }

    @Override // com.xiaomi.push.i.a
    public String b() {
        return "100887";
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (c()) {
                g.j.b.b.b.e(this.f18968g).w();
                g.j.a.a.a.c.B(this.f18968g.getPackageName() + " perf begin upload");
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.D("fail to send perf data. " + e2);
        }
    }
}
