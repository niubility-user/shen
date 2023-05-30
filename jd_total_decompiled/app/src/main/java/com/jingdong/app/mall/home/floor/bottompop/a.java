package com.jingdong.app.mall.home.floor.bottompop;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.ctrl.t.n;
import com.jingdong.app.mall.home.floor.view.b.f.d;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.h;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import com.jingdong.common.unification.navigationbar.NavigationTabLocationEntry;
import com.jingdong.common.unification.navigationbar.newbar.NavigationButton;
import com.jingdong.common.unification.router.JDRouter;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class a extends com.jingdong.app.mall.home.r.e.b {
    private static final AtomicBoolean x = new AtomicBoolean(false);
    protected NavigationButton a;
    private NavigationTabLocationEntry b;

    /* renamed from: c */
    private int f9220c;
    private int d;

    /* renamed from: e */
    int f9221e;

    /* renamed from: f */
    private int f9222f;

    /* renamed from: g */
    private int f9223g;

    /* renamed from: h */
    private String f9224h;

    /* renamed from: i */
    private String f9225i;

    /* renamed from: j */
    String f9226j;

    /* renamed from: k */
    String f9227k;

    /* renamed from: l */
    private String f9228l;

    /* renamed from: m */
    private String f9229m;

    /* renamed from: n */
    private String f9230n;
    public String o;
    public String p;
    public String q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;

    public a(JDJSONObject jDJSONObject) {
        super(jDJSONObject);
        this.f9220c = com.jingdong.app.mall.home.v.b.a();
        this.f9221e = getJsonInt("botAtmosphereHeight", 0);
        this.f9222f = getJsonInt("disappearTime", 0);
        this.f9224h = getJsonString("linkTab");
        this.f9226j = getJsonString("botAtmosphereImg");
        this.f9227k = getJsonString("botAtmosphereBgImg");
        if (this.f9221e <= 0 || this.f9222f <= 2 || TextUtils.isEmpty(this.f9224h) || TextUtils.isEmpty(this.f9226j) || TextUtils.isEmpty(this.f9227k)) {
            return;
        }
        int jsonInt = getJsonInt("dailyShow", 0);
        this.f9223g = jsonInt;
        if (d.f("bottom_pop_times", Math.max(jsonInt, 1))) {
            String jsonString = getJsonString("botAtmoSourceId");
            this.f9225i = jsonString;
            NavigationButton F = f.F(this.f9224h, jsonString);
            this.a = F;
            if (F != null || k.w()) {
                com.jingdong.app.mall.home.o.a.d.j();
                this.d = com.jingdong.app.mall.home.floor.common.d.f9279g;
                e.z(this.f9226j, null);
                e.z(this.f9227k, null);
                this.f9228l = getJsonString("exposureTime");
                this.s = TextUtils.equals(getJsonString("isBotAtomJump"), "1");
                this.f9229m = getJsonString("expoJson");
                this.f9230n = getJsonString("srvJson");
                this.p = getJsonString("expoLog");
                this.q = getJsonString("clkLog");
                this.o = getJsonString("closeLog");
                this.r = true;
            }
        }
    }

    public boolean a() {
        return this.v && this.w;
    }

    public void b() {
        this.v = true;
    }

    public boolean c() {
        AtomicBoolean atomicBoolean = x;
        if (atomicBoolean.get() || !this.r || !isSameType() || n.s() || i.i() || this.d != com.jingdong.app.mall.home.floor.common.d.f9279g) {
            return false;
        }
        if (h.b()) {
            atomicBoolean.set(true);
            return false;
        } else if (!TextUtils.equals(this.f9228l, "1") || (i.g() <= 0 && i.f() <= 1)) {
            return !atomicBoolean.get();
        } else {
            return false;
        }
    }

    public boolean d() {
        return TextUtils.equals(this.f9228l, "3");
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public float e(int i2) {
        int i3;
        int i4;
        NavigationButton navigationButton = this.a;
        if (navigationButton == null || navigationButton.getParent() == null) {
            this.a = f.F(this.f9224h, this.f9225i);
        }
        NavigationButton navigationButton2 = this.a;
        if (navigationButton2 != null && navigationButton2.getParent() != null) {
            NavigationTabLocationEntry iconLocation = this.a.getIconLocation();
            this.b = iconLocation;
            if (iconLocation != null && (i4 = iconLocation.topX) > 0) {
                i3 = i4 + (iconLocation.width >> 1);
                return i3 <= 0 ? i3 : i2;
            }
        }
        i3 = 0;
        if (i3 <= 0) {
        }
    }

    public int f() {
        int i2 = this.f9222f;
        this.f9222f = i2 - 1;
        return i2;
    }

    public boolean g() {
        return x.get();
    }

    public void h(Context context) {
        NavigationButton navigationButton;
        NavigationInfo navigationInfo;
        if (this.t || !this.s || (navigationButton = this.a) == null || context == null || (navigationInfo = navigationButton.getNavigationInfo()) == null) {
            return;
        }
        this.t = true;
        JDRouter.to(context, "router://JDNavigationModule/setCheckedNavigationButton?index=navigationId".replace(CustomThemeConstance.NAVI_NAVIGATION_ID, String.valueOf(navigationInfo.navigationId))).open();
        new com.jingdong.app.mall.home.q.a("\u5e95\u5bfc\u8054\u52a8\u901a\u680f\u70b9\u51fb", this.q).b();
        com.jingdong.app.mall.home.r.c.d b = com.jingdong.app.mall.home.r.c.d.b("Home_BottomAtmosphereClick");
        b.f(this.f9230n);
        b.c();
    }

    public void i() {
        if (this.u) {
            return;
        }
        this.u = true;
        new com.jingdong.app.mall.home.q.a("\u5e95\u5bfc\u8054\u52a8\u901a\u680f\u5173\u95ed", this.o).b();
        com.jingdong.app.mall.home.r.c.d b = com.jingdong.app.mall.home.r.c.d.b("Home_BottomAtmosphereClose");
        b.f(this.f9229m);
        b.a("closetype", "0");
        b.c();
    }

    public boolean isSameType() {
        return this.f9220c == com.jingdong.app.mall.home.v.b.a();
    }

    public void j() {
        NavigationButton navigationButton = this.a;
        if (navigationButton == null || navigationButton.getParent() == null) {
            this.a = f.F(this.f9224h, this.f9225i);
        }
        NavigationButton navigationButton2 = this.a;
        if (navigationButton2 == null || navigationButton2.getParent() == null) {
            return;
        }
        this.a.showNavigationButtonEffect(0);
    }

    public void k() {
        if (this.u) {
            return;
        }
        this.u = true;
        com.jingdong.app.mall.home.r.c.d b = com.jingdong.app.mall.home.r.c.d.b("Home_BottomAtmosphereClose");
        b.f(this.f9229m);
        b.a("closetype", "1");
        b.c();
    }

    public void l() {
        AtomicBoolean atomicBoolean = x;
        if (atomicBoolean.get()) {
            return;
        }
        atomicBoolean.set(true);
        h.f();
        d.b("bottom_pop_times", this.f9223g, true);
        new com.jingdong.app.mall.home.q.a("\u5e95\u5bfc\u8054\u52a8\u901a\u680f\u66dd\u5149", true, this.p).b();
        com.jingdong.app.mall.home.r.c.d b = com.jingdong.app.mall.home.r.c.d.b("Home_BottomAtmosphereExpo");
        b.f(this.f9229m);
        b.d();
    }

    public void m() {
        this.w = true;
    }
}
