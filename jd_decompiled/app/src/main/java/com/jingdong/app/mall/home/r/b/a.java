package com.jingdong.app.mall.home.r.b;

import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.o.a.n;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.SwitchQueryFetcher;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a extends com.jingdong.app.mall.home.r.e.b {
    private static final ConcurrentHashMap<String, Boolean> z = new ConcurrentHashMap<>();
    int a;
    private String b;

    /* renamed from: c */
    private boolean f10610c;
    private boolean d;

    /* renamed from: e */
    private boolean f10611e;

    /* renamed from: f */
    private int f10612f;

    /* renamed from: g */
    private String f10613g;

    /* renamed from: h */
    private String f10614h;

    /* renamed from: i */
    private int f10615i;

    /* renamed from: j */
    private long f10616j;

    /* renamed from: k */
    private long f10617k;

    /* renamed from: l */
    private int f10618l;

    /* renamed from: m */
    private int f10619m;

    /* renamed from: n */
    private int f10620n;
    private int o;
    private int p;
    private int q;
    private JSONObject r;
    private JSONObject s;
    private c t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;

    public a(c cVar, h hVar) {
        super(null);
        this.a = 19;
        this.f10613g = "";
        JDJSONObject d = hVar.d(0);
        this.srcJson = d;
        if (d == null) {
            return;
        }
        this.t = cVar;
        this.f10613g = getJsonString("id");
        int jsonInt = getJsonInt("showTimes", 1);
        this.f10612f = jsonInt;
        this.f10612f = Math.max(jsonInt, 1);
        int h2 = com.jingdong.app.mall.home.n.h.c.h(getJsonString("tipsShowTime"), 0);
        this.f10615i = h2;
        if (h2 == 1) {
            this.f10616j = com.jingdong.app.mall.home.n.h.c.j(getJsonString("tipsShowInterval"), 0) * 1000;
        } else if (h2 == 2) {
            this.a = 19;
        }
        this.f10618l = com.jingdong.app.mall.home.n.h.c.h(getJsonString("tipsShowScene"), 1);
        this.f10619m = com.jingdong.app.mall.home.n.h.c.h(getJsonString("showCondition"), 0);
        this.f10620n = com.jingdong.app.mall.home.n.h.c.h(getJsonString("returnDisappear"), 1);
        this.o = com.jingdong.app.mall.home.n.h.c.h(getJsonString("showAfterClick"), 1);
        this.p = com.jingdong.app.mall.home.n.h.c.h(getJsonString("signType"), 0);
        int h3 = com.jingdong.app.mall.home.n.h.c.h(getJsonString("showType"), 0);
        this.q = h3;
        if (h3 == 2) {
            this.f10617k = com.jingdong.app.mall.home.n.h.c.j(getJsonString("showTime"), 0) * 1000;
        }
        this.u = getJsonString("expoJson");
        this.v = getJsonString("srvJson");
        this.w = getJsonString("expoLog");
        this.x = getJsonString("clkLog");
        this.y = getJsonString("closeLog");
        try {
            this.f10614h = getJsonString("timeId");
            this.r = new JSONObject();
            this.s = new JSONObject(this.srcJson.toJSONString());
            this.r.put(XView2Constants.LAYER_ID, this.f10614h);
            this.r.put("improvementTime", s.f9357c);
            this.f10610c = true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean a() {
        int i2 = c.f10633l;
        if (i2 > 0 && i2 != d.f9279g) {
            return TextUtils.isEmpty(SwitchQueryFetcher.getSwitchStringValue("mp_bottom_x_view", ""));
        }
        Boolean bool = z.get(this.f10613g);
        if (!this.f10610c || this.f10611e || bool != null || TextUtils.isEmpty(this.f10614h)) {
            return false;
        }
        if (i.f() <= 1 || this.f10618l != 0) {
            if (this.f10619m == 1 && n.b()) {
                return false;
            }
            String userPin = LoginUserBase.getUserPin();
            if (TextUtils.isEmpty(userPin) && this.p == 1) {
                return false;
            }
            if (TextUtils.isEmpty(userPin) || this.p != 2) {
                return com.jingdong.app.mall.home.floor.view.b.f.d.f("BottomXView_".concat(this.f10613g), this.f10612f);
            }
            return false;
        }
        return false;
    }

    public boolean b() {
        boolean z2 = this.o == 0;
        if (z2) {
            z.put(this.f10613g, Boolean.TRUE);
        }
        return z2;
    }

    public void c() {
        o();
        this.t.w("closeLayer", this.r, this.s);
    }

    public JSONObject d() {
        return this.r;
    }

    public String e() {
        return this.f10613g;
    }

    public String f() {
        return this.f10614h;
    }

    public long g() {
        return this.f10617k;
    }

    public boolean h() {
        return this.f10615i == 2;
    }

    public boolean i() {
        return this.d;
    }

    public void j() {
        if (this.f10611e) {
            return;
        }
        o();
        new com.jingdong.app.mall.home.q.a("\u5e95\u90e8XView\u5173\u95ed", this.y).b();
        com.jingdong.app.mall.home.r.c.d b = com.jingdong.app.mall.home.r.c.d.b("Home_BottomBannerClose");
        b.f(this.v);
        b.c();
        com.jingdong.app.mall.home.floor.view.b.f.d.b("BottomXView_".concat(this.f10613g), this.f10612f, true);
        z.put(this.f10613g, Boolean.TRUE);
    }

    public void k(com.jingdong.app.mall.home.r.c.b bVar) {
        new com.jingdong.app.mall.home.q.a("\u5e95\u90e8XView\u70b9\u51fb", this.x).b();
        com.jingdong.app.mall.home.r.c.d b = com.jingdong.app.mall.home.r.c.d.b("Home_BottomBanner");
        b.f(this.v);
        b.a("loc", bVar.optString("name"));
        b.c();
    }

    public void l() {
        if (this.d) {
            return;
        }
        this.d = true;
        SystemClock.elapsedRealtime();
        this.b = this.f10613g;
        new com.jingdong.app.mall.home.q.a("\u5e95\u90e8XView\u66dd\u5149", true, this.w).b();
        com.jingdong.app.mall.home.r.c.d b = com.jingdong.app.mall.home.r.c.d.b("Home_BottomBannerExpo");
        b.f(this.u);
        b.d();
    }

    public boolean m() {
        if (this.f10620n == 0 || this.f10615i == 1 || this.f10618l == 0) {
            return true;
        }
        return this.q == 2 && this.d;
    }

    public void n(boolean z2) {
        try {
            this.r.put(XView2Constants.ISVISIBLE, z2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.t.w(XView2Constants.SET_LAYER_VISIBLE, this.r, this.s);
    }

    public void o() {
        this.f10611e = true;
    }

    public void p() {
        this.t.w("showLayer", this.r, this.s);
    }

    public long q() {
        if (this.f10616j <= 0 || TextUtils.equals(this.f10613g, this.b)) {
            return 0L;
        }
        return this.f10616j - (SystemClock.elapsedRealtime() - s.f9357c);
    }
}
