package com.jingdong.app.mall.home.deploy.view.layout.core;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.b.h.a;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.p.b.e.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.utils.Md5Encrypt;

/* loaded from: classes4.dex */
public abstract class CoreModel<V extends CoreBaseView> extends BaseModel<V> {
    public static final int[] o = {-1, -1};
    public static final int[] p = {-15066598, -15066598};
    public static final int[] q = {-381927, -381927};

    /* renamed from: m */
    public f f8920m;

    /* renamed from: n */
    private boolean f8921n;

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    protected void A(View view) {
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        this.f8920m = e();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void E(View view, b bVar) {
        s0(bVar);
        int[] j2 = j("mar", 0);
        if (j2 != null && j2.length > 3) {
            this.f8880j.E(j2[0], j2[1], j2[2], j2[3]);
        }
        if (((CoreBaseView) this.f8882l).getParent() == view) {
            com.jingdong.app.mall.home.floor.common.f.d(this.f8882l, this.f8880j, true);
        } else {
            super.E(view, bVar);
        }
    }

    public com.jingdong.app.mall.home.r.c.b J(JumpEntity jumpEntity, int i2) {
        String str;
        if (jumpEntity == null) {
            return null;
        }
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(jumpEntity.getSrvJson());
        if (i2 == 0 && r0()) {
            str = t0() ? "1" : "0";
        } else {
            str = "-100";
        }
        c2.put("videotype", str);
        return c2;
    }

    public String K() {
        return this.f8920m.e();
    }

    public String L() {
        return this.f8920m.m();
    }

    public String M() {
        return this.f8920m.u();
    }

    public String N() {
        return this.f8920m.v();
    }

    public String O() {
        return this.f8920m.w();
    }

    public String P() {
        return this.f8920m.x();
    }

    public JumpEntity Q() {
        return this.f8920m.getJump();
    }

    public JumpEntity R(String str) {
        return this.f8920m.A(str);
    }

    public int[] S() {
        return m.n(this.f8920m.X(), q, true);
    }

    public String T() {
        return this.f8920m.getJsonString("sloganTagImg");
    }

    public String U() {
        return this.f8920m.Z();
    }

    public String V() {
        return this.f8920m.W();
    }

    public String W() {
        return this.f8920m.I();
    }

    public String X() {
        return this.f8920m.J();
    }

    public String Y() {
        return this.f8920m.getJsonString("skuTagImg");
    }

    public int[] Z() {
        return m.o(this.f8920m.K(), -381927);
    }

    public Rect a0() {
        return b0("skuLabelBorder");
    }

    public Rect b0(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = TextUtils.split(this.f8920m.getJsonString(str), DYConstants.DY_REGEX_COMMA);
        if (split.length < 2) {
            return null;
        }
        return new Rect(Math.max(4, c.g(split[0])), 0, Math.max(4, c.g(split[1])), 0);
    }

    public String c0() {
        return com.jingdong.app.mall.home.o.a.m.a(this.f8920m);
    }

    public int[] d0() {
        return a.e(this.f8920m.f0(), o);
    }

    public String e0() {
        return this.f8920m.d0();
    }

    public int[] f0() {
        return g0(p);
    }

    public int[] g0(int[] iArr) {
        return m.n(this.f8920m.C(), iArr, true);
    }

    public String h0() {
        return this.f8920m.i0();
    }

    public int i0() {
        return this.f8920m.getJsonInt("titleIconWidth");
    }

    public String j0() {
        return this.f8920m.Q();
    }

    public int k0() {
        h d = this.f8877g.d();
        if (d != null && "1".equals(d.getJsonString("fontShape"))) {
            return this.f8920m.getJsonInt("showNameImgWidth");
        }
        return 0;
    }

    public int l0() {
        return com.jingdong.app.mall.home.o.a.m.b(this.f8920m);
    }

    public String m0() {
        return this.f8920m.O();
    }

    public int n0() {
        return this.f8920m.getJsonInt("videoDelayTime", 1);
    }

    public String o0() {
        return this.f8920m.getJsonString("videoId");
    }

    public String p0(String str, String str2, String str3) {
        return Md5Encrypt.md5(str + str2 + str3);
    }

    public String q0() {
        return this.f8920m.getJsonString("videoUrl");
    }

    public boolean r0() {
        return !TextUtils.isEmpty(q0());
    }

    protected abstract void s0(b bVar);

    public boolean t0() {
        return this.f8921n;
    }

    public void u0(View view, View view2, int i2) {
        if (view == null || view2 == null) {
            return;
        }
        com.jingdong.app.mall.home.p.b.d.c.g().c(view, this);
        v0(view2, i2, false);
    }

    public void v0(View view, int i2, boolean z) {
        int i3;
        if (view == null) {
            return;
        }
        int i4 = i2 + 1;
        JumpEntity jumpEntity = null;
        if (z && i2 == 1) {
            jumpEntity = R("jump1");
            i3 = 1;
        } else {
            i3 = i4;
        }
        JumpEntity Q = jumpEntity == null ? Q() : jumpEntity;
        if (w()) {
            l.e(view.getContext(), Q);
            com.jingdong.app.mall.home.r.c.a.s("Home_HandSeckill", "", Q.getSrvJson());
        } else {
            l.p(view, Q, J(Q, i2), i2 == 0 ? M() : N(), i2, i3);
        }
        new com.jingdong.app.mall.home.q.a("\u6838\u5fc3\u697c\u5c42\u70b9\u51fb", K()).b();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    /* renamed from: w0 */
    public void y(V v) {
        v.b(this);
    }

    public void x0(boolean z) {
        this.f8921n = z;
    }
}
