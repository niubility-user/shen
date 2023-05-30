package com.jingdong.app.mall.home.floor.bottomfloat;

import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.sdk.platform.business.personal.R2;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class b {
    String A;
    int B;
    int C;
    int D;
    int E;
    int F;
    JumpEntity G;
    com.jingdong.app.mall.home.r.c.b H;
    com.jingdong.app.mall.home.r.c.b I;
    boolean a;
    boolean b;

    /* renamed from: c  reason: collision with root package name */
    boolean f9189c;
    boolean d;

    /* renamed from: e  reason: collision with root package name */
    boolean f9190e;

    /* renamed from: f  reason: collision with root package name */
    boolean f9191f;

    /* renamed from: g  reason: collision with root package name */
    boolean f9192g;

    /* renamed from: h  reason: collision with root package name */
    int f9193h;

    /* renamed from: i  reason: collision with root package name */
    int f9194i;

    /* renamed from: j  reason: collision with root package name */
    long f9195j;

    /* renamed from: k  reason: collision with root package name */
    long f9196k;

    /* renamed from: l  reason: collision with root package name */
    long f9197l;

    /* renamed from: m  reason: collision with root package name */
    int f9198m;

    /* renamed from: n  reason: collision with root package name */
    String f9199n;
    String o;
    String p;
    String q;
    String r;
    String s;
    String t;
    String u;
    String v;
    String w;
    String x;
    String y;
    String z;

    public b(boolean z) {
        this.a = true;
        this.f9191f = true;
        this.b = z;
    }

    public static boolean d(d dVar) {
        f c2 = dVar.c(0);
        f c3 = dVar.c(1);
        return (c2 != null && c2.getJsonInt("showType") == 1) || (c3 != null && c3.getJsonInt("showType") == 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        boolean z = this.b && com.jingdong.app.mall.home.floor.view.b.f.d.f("FloatFrame".concat(this.q), this.f9198m);
        if (this.D > 0) {
            return z && com.jingdong.app.mall.home.floor.view.b.f.d.g("HOME_FRAME_DAY_CLOSE".concat(this.q), this.D);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b() {
        int i2 = this.C;
        return i2 == 1 || i2 == 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        com.jingdong.app.mall.home.floor.view.b.f.d.b("FloatFrame".concat(this.q), this.f9198m, true);
        if (this.D > 0) {
            com.jingdong.app.mall.home.floor.view.b.f.d.d("HOME_FRAME_DAY_CLOSE".concat(this.q), this.D);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean e(FrameBaseLayout frameBaseLayout) {
        return frameBaseLayout == null || !frameBaseLayout.e(this.q);
    }

    public b(@NotNull d dVar, f fVar) {
        this.a = true;
        this.f9191f = true;
        if (fVar == null) {
            return;
        }
        t tVar = dVar.q;
        h hVar = dVar.mParentModel;
        this.f9193h = hVar == null ? 60 : Math.min(180, Math.max(50, hVar.getJsonInt("height")));
        int jsonInt = fVar.getJsonInt("showTimes", 1);
        this.f9198m = jsonInt;
        this.f9198m = Math.max(jsonInt, 1);
        this.f9192g = !TextUtils.equals("1", fVar.getJsonString("returnDisappear", "1"));
        this.f9191f = !TextUtils.equals("1", fVar.getJsonString("showAfterClick", "1"));
        this.b = 1 == fVar.getJsonInt("canShow", 1);
        this.f9189c = 1 == fVar.getJsonInt("showClock", 0);
        this.d = 1 == fVar.getJsonInt("needRelease", 0);
        this.u = fVar.getJsonString("countDownText");
        this.E = m.i(fVar.getJsonString("countDownTextColor"), -1);
        this.F = Math.max(fVar.getJsonInt("gapToLeft", R2.attr.backgroundTintMode), 48);
        this.f9196k = fVar.getJsonLong("timeRemain");
        this.f9197l = fVar.getJsonLong("endTimestamp");
        long jsonLong = fVar.getJsonLong("activityStartTime");
        this.f9195j = jsonLong <= 0 ? -1L : 1000 * (this.f9196k - jsonLong);
        this.f9194i = m.i(fVar.getJsonString("inCountdownDigitColor"), -1);
        this.q = fVar.s();
        fVar.j();
        this.r = fVar.u();
        String jsonString = fVar.getJsonString("animationImg");
        this.s = jsonString;
        e.z(jsonString, null);
        this.t = fVar.getJsonString("xviewUrl");
        this.f9199n = fVar.m();
        this.o = fVar.f();
        this.p = fVar.g();
        JumpEntity jump = fVar.getJump();
        this.G = jump;
        this.H = com.jingdong.app.mall.home.r.c.b.c(jump == null ? "" : jump.srvJson);
        this.I = com.jingdong.app.mall.home.r.c.b.c(fVar.l());
        this.v = fVar.getJsonString("discount");
        this.w = fVar.getJsonString("buttonText");
        this.x = fVar.getJsonString("buttonBgImg");
        this.y = fVar.getJsonString("interestPoint");
        this.z = fVar.getJsonString("skuImg");
        this.A = fVar.getJsonString("popToastText", "\u7ed9\u6211\u70b9\u65f6\u95f4\uff0c\u518d\u70b9\u6211\u8bd5\u8bd5\uff5e");
        this.B = fVar.getJsonInt("displayStyle");
        this.C = fVar.getJsonInt("businessType");
        this.D = Math.max(fVar.getJsonInt("closeDays", 0), 0);
    }
}
