package com.jingdong.app.mall.home.floor.view.linefloor.base;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.view.b.c;
import com.jingdong.app.mall.home.floor.view.b.e;
import com.jingdong.app.mall.home.floor.view.b.f.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.utils.Md5Encrypt;

/* loaded from: classes4.dex */
public class b extends a {
    private static int[] D = {-376014, -32666};
    private static int[] E = {0, 0};
    private static int[] F = {-381669, -49343, -49304};
    private static int[] G = {-1, -1};
    private static int[] H = {-15066598, -15066598};
    private static int[] I = {-31134, -370613};
    private static int[] J = {-9603973, -11249568};
    private static int[] K = {-1, -1};
    private static int[] L = {-38887, -313831};
    private static int[] M = {-55513, -55513};
    private boolean A;
    private int B;
    private int C;
    public e x;
    private boolean y;
    private boolean z;

    public b(c cVar, f fVar, com.jingdong.app.mall.home.floor.view.b.a aVar) {
        super(cVar, fVar, aVar);
        this.x = new e();
    }

    public boolean A0() {
        return this.y;
    }

    public boolean B0() {
        return this.z;
    }

    public void C0(Context context, int i2, int i3) {
        JumpEntity jump;
        if (context == null || l.k() || (jump = g(i2).getJump()) == null) {
            return;
        }
        int i4 = (i3 <= 1 || k().getSubWeight() >= 2) ? i3 : 1;
        b(i2, "skuposition", String.valueOf(i2));
        String jSONObject = f(i2).toString();
        l.c(jump, W(i2));
        l.onClickJsonEvent(context, jump, "", jump.getSrv(), jSONObject, i4);
    }

    public void D0(boolean z) {
        this.A = z;
    }

    public void E0(boolean z) {
        this.y = z;
    }

    public void F0(boolean z) {
        this.z = z;
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.a
    public void I(int i2, int i3) {
        super.I(i2, i3);
    }

    public void N(int i2) {
        int S = S(i2);
        int i3 = this.C;
        if (i3 >= S) {
            return;
        }
        this.C = i3 + 1;
        d.c("allBubble_".concat(g(i2).s()), R(i2));
    }

    public void O(int i2) {
        int r0 = r0(i2);
        int i3 = this.B;
        if (i3 >= r0) {
            return;
        }
        this.B = i3 + 1;
        d.c("allVideo_".concat(g(i2).s()), o0(i2));
    }

    public String P(int i2) {
        if (i2 == 1 && k().getSubWeight() > 1) {
            return g(i2).getJsonString("benefit2");
        }
        return g(i2).getJsonString("benefit");
    }

    public int Q(int i2) {
        return com.jingdong.app.mall.home.floor.view.b.h.a.d(g(i2).getJsonString("benefitBottomColor"), -1);
    }

    public int R(int i2) {
        return g(i2).S();
    }

    public int S(int i2) {
        return g(i2).M();
    }

    public String T(int i2) {
        if (q() > 1) {
            return i2 == 0 ? g(i2).I() : g(i2).J();
        }
        return g(i2).I();
    }

    public String U(int i2) {
        return g(i2).getJsonString("skuLabelType");
    }

    public String V(int i2) {
        return F() ? "" : g(i2).getJsonString("maskImg");
    }

    public String W(int i2) {
        if (i2 == 1 && k().getSubWeight() > 1) {
            return g(i2).v();
        }
        return g(i2).u();
    }

    public int[] X(int i2) {
        return com.jingdong.app.mall.home.r.e.b.getColor(g(i2).getJsonString("skuLabelAreaColor"), F() ? J : I);
    }

    public String Y(int i2) {
        return g(i2).getJsonString("skuLabelAreaColor");
    }

    public String Z(int i2) {
        return g(i2).getJsonString("skuTagImg");
    }

    public int[] a0(int i2) {
        return com.jingdong.app.mall.home.r.e.b.getColor(g(i2).K(), E() ? L : K);
    }

    public String b0(int i2) {
        if (q() > 1) {
            return i2 == 0 ? g(i2).I() : g(i2).J();
        }
        return g(i2).I();
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.a
    public void c(int i2, int i3) {
        super.c(i2, i3);
        this.a.parseSize(this.x, this, i3);
    }

    public String c0(int i2) {
        return g(i2).d0();
    }

    public int[] d0(int i2) {
        return com.jingdong.app.mall.home.r.e.b.getColor(g(i2).f0(), M);
    }

    public int e0(int i2) {
        int q = g(i2).q();
        if (q != 0) {
            return q != 2 ? 24 : 26;
        }
        return 22;
    }

    public int[] f0(int i2) {
        return com.jingdong.app.mall.home.r.e.b.getColor(g(i2).C(), a.t);
    }

    public String g0(int i2) {
        return g(i2).Q();
    }

    public int h0(int i2) {
        return com.jingdong.app.mall.home.n.h.c.h(g(i2).getJsonString("showNameImgWidth"), 0);
    }

    public String i0() {
        return g(0).W();
    }

    public String j0() {
        return g(0).getJsonString("sloganTagImg");
    }

    public int[] k0() {
        return com.jingdong.app.mall.home.r.e.b.getColor(g(0).getJsonString("sloganAreaColor"), E() ? F : F() ? E : D);
    }

    public int[] l0() {
        return com.jingdong.app.mall.home.r.e.b.getColor(g(0).getJsonString("sloganColor"), (E() || !F()) ? G : H);
    }

    public String m0() {
        return getJsonString("sloganIcon");
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.a
    public int[] n(int i2) {
        return com.jingdong.app.mall.home.r.e.b.getColor(g(i2).getJsonString("maskColor"), this.a.showSkuLabel(this, i2) || B() ? a.w : a.u);
    }

    public String n0(int i2) {
        return g(i2).O();
    }

    public int o0(int i2) {
        return g(i2).T();
    }

    public String p0(int i2) {
        return g(i2).l0();
    }

    public String q0(String str, String str2, String str3) {
        return Md5Encrypt.md5(str + str2 + str3);
    }

    public int r0(int i2) {
        return g(i2).B();
    }

    public String s0(int i2) {
        int m0 = g(i2).m0();
        return m0 < 0 ? "" : q() < 2 ? m0 == 3 ? g(i2).n0() : "" : i2 == m0 ? g(i2).n0() : "";
    }

    public boolean t0(int i2) {
        if (this.C >= S(i2)) {
            return false;
        }
        return d.g("allBubble_".concat(g(i2).s()), R(i2));
    }

    public boolean u0(int i2) {
        if (this.B >= r0(i2)) {
            return false;
        }
        return d.g("allVideo_".concat(g(i2).s()), o0(i2));
    }

    public boolean v0() {
        return this.A;
    }

    public boolean w0(int i2) {
        return (i2 != 0 || q() < 2) && 1 == g(i2).getJsonInt("showBubble");
    }

    public boolean x0() {
        return true;
    }

    public boolean y0() {
        return true;
    }

    public boolean z0() {
        return true ^ TextUtils.isEmpty(n0(1));
    }
}
