package g.j.b.b;

import android.content.Context;
import com.jingdong.sdk.platform.business.personal.R2;
import com.xiaomi.push.a8;
import com.xiaomi.push.i;
import com.xiaomi.push.p0;
import com.xiaomi.push.q0;
import com.xiaomi.push.r0;
import com.xiaomi.push.s0;
import com.xiaomi.push.t0;
import com.xiaomi.push.w0;
import g.j.b.a.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes11.dex */
public class b {

    /* renamed from: i */
    private static final int f19681i;

    /* renamed from: j */
    private static volatile b f19682j;
    private ExecutorService a = Executors.newSingleThreadExecutor();
    private HashMap<String, HashMap<String, g.j.b.a.d>> b = new HashMap<>();

    /* renamed from: c */
    private HashMap<String, ArrayList<g.j.b.a.d>> f19683c = new HashMap<>();
    private Context d;

    /* renamed from: e */
    private g.j.b.a.a f19684e;

    /* renamed from: f */
    private String f19685f;

    /* renamed from: g */
    private g.j.b.c.a f19686g;

    /* renamed from: h */
    private g.j.b.c.b f19687h;

    static {
        f19681i = a8.i() ? 30 : 10;
    }

    private b(Context context) {
        this.d = context;
    }

    private void A() {
        if (e(this.d).c().h()) {
            Context context = this.d;
            r0 r0Var = new r0(context);
            int e2 = (int) e(context).c().e();
            if (e2 < 1800) {
                e2 = R2.attr.showAnimationBehavior;
            }
            if (System.currentTimeMillis() - w0.b(this.d).a("sp_client_report_status", "perf_last_upload_time", 0L) > e2 * 1000) {
                com.xiaomi.push.i.b(this.d).h(new j(this, r0Var), 15);
            }
            synchronized (b.class) {
                if (!com.xiaomi.push.i.b(this.d).j(r0Var, e2)) {
                    com.xiaomi.push.i.b(this.d).m("100887");
                    com.xiaomi.push.i.b(this.d).j(r0Var, e2);
                }
            }
        }
    }

    public int a() {
        HashMap<String, ArrayList<g.j.b.a.d>> hashMap = this.f19683c;
        if (hashMap != null) {
            Iterator<String> it = hashMap.keySet().iterator();
            int i2 = 0;
            while (it.hasNext()) {
                ArrayList<g.j.b.a.d> arrayList = this.f19683c.get(it.next());
                i2 += arrayList != null ? arrayList.size() : 0;
            }
            return i2;
        }
        return 0;
    }

    public static b e(Context context) {
        if (f19682j == null) {
            synchronized (b.class) {
                if (f19682j == null) {
                    f19682j = new b(context);
                }
            }
        }
        return f19682j;
    }

    private void n(i.a aVar, int i2) {
        com.xiaomi.push.i.b(this.d).n(aVar, i2);
    }

    public int q() {
        HashMap<String, HashMap<String, g.j.b.a.d>> hashMap = this.b;
        int i2 = 0;
        if (hashMap != null) {
            Iterator<String> it = hashMap.keySet().iterator();
            while (it.hasNext()) {
                HashMap<String, g.j.b.a.d> hashMap2 = this.b.get(it.next());
                if (hashMap2 != null) {
                    Iterator<String> it2 = hashMap2.keySet().iterator();
                    while (it2.hasNext()) {
                        g.j.b.a.d dVar = hashMap2.get(it2.next());
                        if (dVar instanceof g.j.b.a.c) {
                            i2 = (int) (i2 + ((g.j.b.a.c) dVar).f19675i);
                        }
                    }
                }
            }
        }
        return i2;
    }

    public void t(g.j.b.a.b bVar) {
        g.j.b.c.a aVar = this.f19686g;
        if (aVar != null) {
            aVar.c(bVar);
            if (a() < 10) {
                n(new e(this), f19681i);
                return;
            }
            x();
            com.xiaomi.push.i.b(this.d).m("100888");
        }
    }

    public void u(g.j.b.a.c cVar) {
        g.j.b.c.b bVar = this.f19687h;
        if (bVar != null) {
            bVar.c(cVar);
            if (q() < 10) {
                n(new g(this), f19681i);
                return;
            }
            y();
            com.xiaomi.push.i.b(this.d).m("100889");
        }
    }

    public void x() {
        try {
            this.f19686g.b();
        } catch (Exception e2) {
            g.j.a.a.a.c.D("we: " + e2.getMessage());
        }
    }

    public void y() {
        try {
            this.f19687h.b();
        } catch (Exception e2) {
            g.j.a.a.a.c.D("wp: " + e2.getMessage());
        }
    }

    private void z() {
        if (e(this.d).c().g()) {
            Context context = this.d;
            q0 q0Var = new q0(context);
            int c2 = (int) e(context).c().c();
            if (c2 < 1800) {
                c2 = R2.attr.showAnimationBehavior;
            }
            if (System.currentTimeMillis() - w0.b(this.d).a("sp_client_report_status", "event_last_upload_time", 0L) > c2 * 1000) {
                com.xiaomi.push.i.b(this.d).h(new i(this, q0Var), 10);
            }
            synchronized (b.class) {
                if (!com.xiaomi.push.i.b(this.d).j(q0Var, c2)) {
                    com.xiaomi.push.i.b(this.d).m("100886");
                    com.xiaomi.push.i.b(this.d).j(q0Var, c2);
                }
            }
        }
    }

    public synchronized g.j.b.a.a c() {
        if (this.f19684e == null) {
            this.f19684e = g.j.b.a.a.a(this.d);
        }
        return this.f19684e;
    }

    public g.j.b.a.b d(int i2, String str) {
        g.j.b.a.b bVar = new g.j.b.a.b();
        bVar.f19673k = str;
        bVar.f19672j = System.currentTimeMillis();
        bVar.f19671i = i2;
        bVar.f19670h = p0.a(6);
        bVar.a = 1000;
        bVar.f19677c = 1001;
        bVar.b = "E100004";
        bVar.a(this.d.getPackageName());
        bVar.b(this.f19685f);
        return bVar;
    }

    public void g() {
        e(this.d).z();
        e(this.d).A();
    }

    public void h(g.j.b.a.a aVar, g.j.b.c.a aVar2, g.j.b.c.b bVar) {
        this.f19684e = aVar;
        this.f19686g = aVar2;
        this.f19687h = bVar;
        aVar2.a(this.f19683c);
        this.f19687h.b(this.b);
    }

    public void i(g.j.b.a.b bVar) {
        if (c().g()) {
            this.a.execute(new c(this, bVar));
        }
    }

    public void j(g.j.b.a.c cVar) {
        if (c().h()) {
            this.a.execute(new d(this, cVar));
        }
    }

    public void o(String str) {
        this.f19685f = str;
    }

    public void p(boolean z, boolean z2, long j2, long j3) {
        g.j.b.a.a aVar = this.f19684e;
        if (aVar != null) {
            if (z == aVar.g() && z2 == this.f19684e.h() && j2 == this.f19684e.c() && j3 == this.f19684e.e()) {
                return;
            }
            long c2 = this.f19684e.c();
            long e2 = this.f19684e.e();
            a.C0842a b = g.j.b.a.a.b();
            b.i(t0.b(this.d));
            b.j(this.f19684e.f());
            b.l(z);
            b.k(j2);
            b.o(z2);
            b.n(j3);
            g.j.b.a.a h2 = b.h(this.d);
            this.f19684e = h2;
            if (!h2.g()) {
                com.xiaomi.push.i.b(this.d).m("100886");
            } else if (c2 != h2.c()) {
                g.j.a.a.a.c.B(this.d.getPackageName() + "reset event job " + h2.c());
                z();
            }
            if (!this.f19684e.h()) {
                com.xiaomi.push.i.b(this.d).m("100887");
            } else if (e2 != h2.e()) {
                g.j.a.a.a.c.B(this.d.getPackageName() + " reset perf job " + h2.e());
                A();
            }
        }
    }

    public void s() {
        if (c().g()) {
            s0 s0Var = new s0();
            s0Var.a(this.d);
            s0Var.b(this.f19686g);
            this.a.execute(s0Var);
        }
    }

    public void w() {
        if (c().h()) {
            s0 s0Var = new s0();
            s0Var.b(this.f19687h);
            s0Var.a(this.d);
            this.a.execute(s0Var);
        }
    }
}
