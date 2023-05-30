package com.xiaomi.push;

import android.content.Context;

/* loaded from: classes11.dex */
public class s0 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    private g.j.b.c.e f18998g;

    /* renamed from: h  reason: collision with root package name */
    private Context f18999h;

    public void a(Context context) {
        this.f18999h = context;
    }

    public void b(g.j.b.c.e eVar) {
        this.f18998g = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        w0 b;
        String str;
        long currentTimeMillis;
        try {
            g.j.b.c.e eVar = this.f18998g;
            if (eVar != null) {
                eVar.a();
            }
            g.j.a.a.a.c.B("begin read and send perf / event");
            g.j.b.c.e eVar2 = this.f18998g;
            if (eVar2 instanceof g.j.b.c.a) {
                b = w0.b(this.f18999h);
                str = "event_last_upload_time";
                currentTimeMillis = System.currentTimeMillis();
            } else if (!(eVar2 instanceof g.j.b.c.b)) {
                return;
            } else {
                b = w0.b(this.f18999h);
                str = "perf_last_upload_time";
                currentTimeMillis = System.currentTimeMillis();
            }
            b.d("sp_client_report_status", str, currentTimeMillis);
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
        }
    }
}
