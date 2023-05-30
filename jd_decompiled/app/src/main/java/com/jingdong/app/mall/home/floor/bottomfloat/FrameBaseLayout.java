package com.jingdong.app.mall.home.floor.bottomfloat;

import android.content.Context;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.r.e.h;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class FrameBaseLayout extends RelativeLayout {
    private static final String t = FrameBaseLayout.class.getSimpleName();

    /* renamed from: g */
    protected FrameBaseLayout f9157g;

    /* renamed from: h */
    protected b f9158h;

    /* renamed from: i */
    protected b f9159i;

    /* renamed from: j */
    protected boolean f9160j;

    /* renamed from: k */
    protected FrameTimeLayout f9161k;

    /* renamed from: l */
    protected f f9162l;

    /* renamed from: m */
    protected ConcurrentHashMap<String, Boolean> f9163m;

    /* renamed from: n */
    protected AtomicBoolean f9164n;
    protected AtomicBoolean o;
    protected AtomicBoolean p;
    protected AtomicBoolean q;
    protected AtomicBoolean r;
    protected AtomicBoolean s;

    public FrameBaseLayout(Context context) {
        super(context);
        this.f9163m = new ConcurrentHashMap<>();
        this.f9164n = new AtomicBoolean(false);
        this.o = new AtomicBoolean(true);
        this.p = new AtomicBoolean(false);
        this.q = new AtomicBoolean(false);
        this.r = new AtomicBoolean(false);
        this.s = new AtomicBoolean(false);
    }

    public boolean a() {
        return false;
    }

    public void b() {
        if (q() || this.f9164n.getAndSet(true)) {
            return;
        }
        n();
    }

    public final void c(BottomFloatLayout bottomFloatLayout, h hVar, b bVar, b bVar2) {
        this.s.set(false);
        this.r.set(false);
        this.p.set(false);
        this.f9164n.set(false);
        this.o.set(true);
        if (bVar == null) {
            bVar = new b(false);
        }
        this.f9158h = bVar;
        if (bVar2 == null) {
            bVar2 = new b(false);
        }
        this.f9159i = bVar2;
        d();
    }

    public void d() {
    }

    public boolean e(String str) {
        Boolean bool = this.f9163m.get(str);
        return bool != null && bool.booleanValue();
    }

    public boolean f() {
        return true;
    }

    public void g(boolean z) {
        this.r.set(true);
        this.q.set(true);
    }

    public void h() {
        this.f9164n.set(true);
    }

    public void i() {
        this.f9164n.set(false);
    }

    public void j() {
    }

    public void k(boolean z) {
        this.f9160j = z;
        b();
    }

    public void l(b bVar, String str, String str2, String str3) {
        com.jingdong.app.mall.home.r.c.b bVar2;
        if (bVar == null || (bVar2 = bVar.H) == null) {
            return;
        }
        bVar2.a("type", str);
        bVar.H.a("loc", str2);
        bVar.H.a("des", str3);
        String jSONObject = bVar.H.toString();
        if (k.v()) {
            com.jingdong.app.mall.home.o.a.f.r0(t, "click: " + jSONObject);
        }
        com.jingdong.app.mall.home.r.c.a.s("Home_RedEnvelopedRain", "", jSONObject);
    }

    public void m(b bVar, String str, String str2, String str3) {
        com.jingdong.app.mall.home.r.c.b bVar2;
        if (bVar == null || (bVar2 = bVar.I) == null) {
            return;
        }
        bVar2.a("type", str);
        bVar.I.a("isdynamic", str2);
        bVar.I.a("iscountdown", str3);
        String jSONObject = bVar.I.toString();
        if (k.v()) {
            com.jingdong.app.mall.home.o.a.f.r0(t, "expo: " + jSONObject);
        }
        com.jingdong.app.mall.home.r.c.a.y("Home_RedEnvelopedRain_Expo", "", jSONObject);
    }

    public void n() {
    }

    public void o(FrameBaseLayout frameBaseLayout) {
        this.f9157g = frameBaseLayout;
    }

    public void p() {
    }

    public boolean q() {
        return !this.f9160j || this.r.get() || this.q.get() || !f();
    }
}
