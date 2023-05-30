package com.jingdong.app.mall.home.r.b;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.bottomfloat.BottomFloatLayout;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.widget.HomeRecycleView;
import com.jingdong.common.XView2.XView2Manager;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: j  reason: collision with root package name */
    private static final Handler f10631j = new Handler(Looper.getMainLooper());

    /* renamed from: k  reason: collision with root package name */
    private static boolean f10632k;

    /* renamed from: l  reason: collision with root package name */
    static int f10633l;
    private com.jingdong.app.mall.home.tips.a a;
    private Context b;

    /* renamed from: c  reason: collision with root package name */
    private RelativeLayout f10634c;
    private BottomFloatLayout d;

    /* renamed from: e  reason: collision with root package name */
    private com.jingdong.app.mall.home.r.b.b f10635e;

    /* renamed from: f  reason: collision with root package name */
    private com.jingdong.app.mall.home.r.b.b f10636f;

    /* renamed from: g  reason: collision with root package name */
    public final AtomicBoolean f10637g = new AtomicBoolean(false);

    /* renamed from: h  reason: collision with root package name */
    private final AtomicBoolean f10638h = new AtomicBoolean(false);

    /* renamed from: i  reason: collision with root package name */
    private final com.jingdong.app.mall.home.floor.bottompop.b f10639i = new com.jingdong.app.mall.home.floor.bottompop.b();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            c.this.a = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (c.this.f10635e == null) {
                return;
            }
            c.this.f10635e.g();
        }
    }

    public static void h() {
        f10632k = true;
    }

    public void c(h hVar) {
        com.jingdong.app.mall.home.r.b.a aVar = new com.jingdong.app.mall.home.r.b.a(this, hVar);
        if (aVar.a()) {
            if (f10633l <= 0) {
                f10633l = d.f9279g;
            }
            if (this.f10635e == null) {
                com.jingdong.app.mall.home.r.b.b bVar = new com.jingdong.app.mall.home.r.b.b(aVar, this);
                this.f10635e = bVar;
                this.f10636f = bVar;
                return;
            }
            com.jingdong.app.mall.home.r.b.b bVar2 = new com.jingdong.app.mall.home.r.b.b(aVar, this);
            this.f10636f.s(bVar2);
            this.f10636f = bVar2;
        }
    }

    public void d() {
        BottomFloatLayout bottomFloatLayout = this.d;
        if (bottomFloatLayout != null) {
            bottomFloatLayout.n();
        }
        this.f10639i.b(this.f10634c);
        if (this.f10635e == null) {
            return;
        }
        com.jingdong.app.mall.home.o.a.d.j();
        if (i.f() == 1 && f10632k && !this.f10635e.h()) {
            f10631j.postDelayed(new b(), 2400L);
        } else {
            this.f10635e.g();
        }
    }

    public void e() {
        com.jingdong.app.mall.home.tips.a aVar = this.a;
        if (aVar != null) {
            aVar.m(false);
        }
    }

    public void f(Context context, RelativeLayout relativeLayout) {
        this.b = context;
        this.f10634c = relativeLayout;
    }

    public boolean g() {
        return this.f10638h.get();
    }

    public void i() {
        this.f10639i.c();
        com.jingdong.app.mall.home.r.b.b bVar = this.f10635e;
        if (bVar != null) {
            bVar.k();
        }
        BottomFloatLayout bottomFloatLayout = this.d;
        if (bottomFloatLayout != null) {
            bottomFloatLayout.w();
        }
        com.jingdong.app.mall.home.tips.a aVar = this.a;
        if (aVar != null) {
            if (aVar.p()) {
                e();
            } else {
                this.a.n();
            }
        }
    }

    public void j(boolean z) {
        this.f10639i.d(this.f10634c, z);
        com.jingdong.app.mall.home.r.b.b bVar = this.f10635e;
        if (bVar != null) {
            bVar.l(z);
        }
        BottomFloatLayout bottomFloatLayout = this.d;
        if (bottomFloatLayout != null) {
            bottomFloatLayout.x();
        }
        com.jingdong.app.mall.home.tips.a aVar = this.a;
        if (aVar != null) {
            if (aVar.p()) {
                p();
            } else {
                this.a.w();
            }
        }
    }

    public void k() {
        com.jingdong.app.mall.home.r.b.b bVar = this.f10635e;
        if (bVar != null) {
            bVar.m();
        }
        com.jingdong.app.mall.home.tips.a aVar = this.a;
        if (aVar != null) {
            aVar.q();
        }
    }

    public void l() {
        com.jingdong.app.mall.home.r.b.b bVar = this.f10635e;
        if (bVar != null) {
            bVar.n();
        }
        com.jingdong.app.mall.home.tips.a aVar = this.a;
        if (aVar != null) {
            aVar.r();
        }
    }

    public void m(HomeRecycleView homeRecycleView, int i2) {
        com.jingdong.app.mall.home.tips.a aVar = this.a;
        if (aVar != null) {
            aVar.s(homeRecycleView, i2);
        }
        if (this.f10635e != null) {
            boolean g2 = com.jingdong.app.mall.home.floor.ctrl.a.g(homeRecycleView, com.jingdong.app.mall.home.a.f8560i);
            if (this.f10637g.get() ^ g2) {
                this.f10637g.set(g2);
                this.f10635e.r();
            }
        }
    }

    public void n() {
        this.f10639i.b(this.f10634c);
        p();
        if (this.f10635e != null && this.f10638h.get()) {
            this.f10635e.p();
        }
        this.f10638h.set(false);
    }

    public void o() {
        this.f10638h.set(true);
        e();
    }

    public void p() {
        com.jingdong.app.mall.home.tips.a aVar = this.a;
        if (aVar != null) {
            aVar.t();
        }
    }

    public void q(boolean z, boolean z2) {
        boolean z3 = this.f10638h.get();
        this.f10638h.set(z);
        if (this.f10635e != null && (z3 ^ this.f10638h.get())) {
            this.f10635e.p();
        }
        BottomFloatLayout bottomFloatLayout = this.d;
        if (bottomFloatLayout != null) {
            bottomFloatLayout.z(z, z2);
        }
        com.jingdong.app.mall.home.tips.a aVar = this.a;
        if (aVar != null) {
            if (z) {
                aVar.n();
            } else {
                aVar.w();
            }
        }
    }

    public void r() {
        f10631j.removeCallbacksAndMessages(null);
        BottomFloatLayout bottomFloatLayout = this.d;
        if (bottomFloatLayout != null) {
            bottomFloatLayout.A();
        }
        com.jingdong.app.mall.home.r.b.b bVar = this.f10635e;
        if (bVar != null) {
            bVar.q();
            this.f10635e = null;
        }
    }

    public void s() {
        this.f10639i.e();
    }

    public void t() {
        f.E0(new a());
    }

    public void u(BottomFloatLayout bottomFloatLayout) {
        this.d = bottomFloatLayout;
    }

    public void v(com.jingdong.app.mall.home.tips.a aVar) {
        this.a = aVar;
    }

    public void w(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        if (this.b == null) {
            return;
        }
        XView2Manager.getInstance().startXView2(this.b, str, jSONObject, jSONObject2, null);
    }
}
