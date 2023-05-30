package com.jingdong.app.mall.home.deploy.view.layout.core1x2lr;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearSkuItem;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.b.h.a;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public abstract class DCore1x2LRModel<V extends CoreBaseView> extends CoreModel<V> {
    public static final Rect C = new Rect(0, 0, 0, 0);
    public static final int[] D = {-381927, -381927};
    public static final int[] E = {-1286, -1286};
    private int A;
    private String B;
    private IconImageText.Info s;
    private SkuLayout.Info t;
    private SkuLayout.Info u;
    private SkuLabel.Info v;
    private String w;
    private ArrayList<YearSkuItem> r = new ArrayList<>();
    private String x = "";
    private boolean y = true;
    private boolean z = false;

    private void L0(boolean z, int i2) {
        this.A = 0;
        int jsonInt = this.f8920m.getJsonInt("skuRotateType", 0);
        this.u = null;
        if (jsonInt == 0) {
            return;
        }
        SkuLabel.Info a = SkuLabel.Info.a();
        a.g(this.f8920m);
        a.d(this.f8920m.getJsonString("skuLabelBgImg2"));
        a.q(Z(), g.i());
        a.o(this.f8920m.getJsonString("secondPrice1"), b0("skuLabelBorder2"));
        if (jsonInt == 1) {
            String N = N();
            if (TextUtils.isEmpty(N)) {
                return;
            }
            SkuLayout.Info a2 = SkuLayout.Info.a();
            a2.l(i2, i2);
            a2.i(N, c0(), 8);
            a2.e(a, -2, 30);
            this.u = a2;
            this.A = 1;
        } else if (jsonInt == 2 && this.t.c()) {
            int i3 = z ? 144 : -2;
            this.t.d(a, i3, i3, 30);
            this.A = 2;
        }
    }

    private void M0(boolean z, int i2) {
        int m2 = m(126, 116);
        int jsonInt = this.f8920m.getJsonInt("skuFrameNum");
        SkuLayout.Info a = SkuLayout.Info.a();
        boolean z2 = true;
        if (jsonInt != 1 && jsonInt != 2) {
            z2 = false;
        }
        a.h(m2, m2, z2);
        a.l(i2, i2);
        a.i(M(), c0(), 8);
        this.t = a;
        N0(z);
        P0(z);
    }

    private void N0(boolean z) {
        String E0;
        SkuLabel.Info a = SkuLabel.Info.a();
        this.v = a;
        this.z = false;
        if (z) {
            a.l(C, 0);
            a.g(this.f8920m);
            a.e(F0(), G0());
            a.q(H0(), I0());
            a.f(K0());
            int d = d.d(R2.anim.lib_cashier_sdk_fragment_right_out);
            String B0 = B0();
            if (!TextUtils.isEmpty(B0) && f.R(I0(), K0(), B0) <= d) {
                E0 = B0();
            } else {
                E0 = E0();
            }
            this.v.n(E0);
            return;
        }
        this.w = this.f8920m.getJsonString("guideImg");
        this.y = this.f8920m.getJsonInt("showOriginBenefit", 1) == 1;
        int jsonInt = this.f8920m.getJsonInt("dayLimit", 0);
        int jsonInt2 = this.f8920m.getJsonInt("allLimit", 0);
        if (!TextUtils.isEmpty(this.w)) {
            if (!TextUtils.equals(this.f8920m.s(), f.O("Home_Core1x2L_Click_Id", "empty")) && com.jingdong.app.mall.home.floor.view.b.f.d.f(this.x, jsonInt) && com.jingdong.app.mall.home.floor.view.b.f.d.g(this.x, jsonInt2)) {
                SkuLabel.Info info = this.v;
                info.d(this.w);
                info.i(true);
                this.z = true;
                com.jingdong.app.mall.home.floor.view.b.f.d.a(this.x, jsonInt);
                com.jingdong.app.mall.home.floor.view.b.f.d.c(this.x, jsonInt2);
                return;
            } else if (!this.y) {
                this.v.d("");
                return;
            } else {
                SkuLabel.Info info2 = this.v;
                info2.g(this.f8920m);
                info2.d(Y());
                info2.q(Z(), g.i());
                info2.j(m(30, 28));
                info2.o(W(), a0());
                return;
            }
        }
        SkuLabel.Info info3 = this.v;
        info3.g(this.f8920m);
        info3.d(Y());
        info3.q(Z(), g.i());
        info3.j(m(30, 28));
        info3.o(W(), a0());
    }

    private void P0(boolean z) {
        int m2;
        if (z) {
            m2 = 144;
        } else {
            m2 = this.z ? m(R2.anim.pickerview_dialog_scale_in, R2.anim.mtrl_bottom_sheet_slide_in) : -2;
        }
        this.t.f(this.v, m2, z ? 144 : R2.anim.manto_translate_dialog_in, z ? 32 : 30);
    }

    public String A0() {
        return this.B;
    }

    public String B0() {
        YearSkuItem C0 = C0(0);
        return C0 != null ? C0.e() : "";
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        boolean v = v();
        this.B = this.f8920m.getJsonString("markedImg");
        this.x = "core1x2lr_label_".concat(this.f8920m.s());
        this.r = YearSkuItem.i(this.f8920m.getJsonArr(CartConstant.KEY_ITEMS));
        IconImageText.Info a = IconImageText.Info.a();
        a.d(v ? i0() : 0, 28, h0());
        a.e(0, 0, 4, 0);
        a.h(f0(), l0());
        a.g(m0());
        a.c(true);
        a.i(4);
        a.f(k0(), v ? 30 : 32, j0());
        this.s = a;
        int m2 = v() ? 132 : m(R2.anim.pickerview_dialog_scale_in, 140);
        M0(v, m2);
        L0(v, m2);
    }

    public YearSkuItem C0(int i2) {
        ArrayList<YearSkuItem> arrayList = this.r;
        if (arrayList == null || i2 < 0 || i2 >= arrayList.size()) {
            return null;
        }
        return this.r.get(i2);
    }

    public SkuLayout.Info D0() {
        return this.t;
    }

    public String E0() {
        YearSkuItem C0 = C0(0);
        return C0 != null ? C0.f() : "";
    }

    public int[] F0() {
        return m.n(this.f8920m.getJsonString("subTitleBgColor"), E, true);
    }

    public String G0() {
        return this.f8920m.getJsonString("subTitleBgImg");
    }

    public int[] H0() {
        return a.e(this.f8920m.getJsonString("subTitleColor"), D);
    }

    public int I0() {
        return Math.max(Math.min(this.f8920m.getJsonInt("subTitleFontSize", 24), 26), 20);
    }

    public IconImageText.Info J0() {
        return this.s;
    }

    public boolean K0() {
        return 1 == this.f8920m.getJsonInt("subTitleBoldSwitch");
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    public String M() {
        if (v()) {
            YearSkuItem C0 = C0(0);
            return C0 == null ? "" : C0.c();
        }
        return super.M();
    }

    public void O0(Core1x2LRAnimateSku core1x2LRAnimateSku) {
        if (core1x2LRAnimateSku == null || v() || TextUtils.isEmpty(this.w) || !this.z) {
            return;
        }
        f.A0("Home_Core1x2L_Click_Id", this.f8920m.s());
        this.z = false;
        SkuLabel.Info a = SkuLabel.Info.a();
        this.v = a;
        if (this.y) {
            a.g(this.f8920m);
            a.d(Y());
            a.q(Z(), g.i());
            a.o(W(), a0());
        } else {
            a.d("");
        }
        P0(false);
        core1x2LRAnimateSku.a(this);
    }

    public void Q0(int i2) {
        b k2 = this.f8920m.k();
        if (k2 != null) {
            k2.a("skurotatestatus", String.valueOf(i2));
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    public int[] f0() {
        if (v()) {
            return m.n(this.f8920m.getJsonString(Constant.KEY_TITLE_COLOR), CoreModel.p, true);
        }
        return super.f0();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    public String h0() {
        if (v()) {
            return this.f8920m.getJsonString("tagImg");
        }
        return super.h0();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    public int i0() {
        if (v()) {
            return this.f8920m.getJsonInt("tagImgWidth");
        }
        return super.i0();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    public String j0() {
        if (v()) {
            return this.f8920m.getJsonString("titleImg");
        }
        return super.j0();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    public int k0() {
        if (v()) {
            h d = this.f8877g.d();
            if (d != null && "1".equals(d.getJsonString("fontShape"))) {
                return this.f8920m.getJsonInt("titleImgWidth");
            }
            return 0;
        }
        return super.k0();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    public String m0() {
        if (v()) {
            return this.f8920m.getJsonString("title");
        }
        return super.m0();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    public void v0(View view, int i2, boolean z) {
        if (view == null) {
            return;
        }
        JumpEntity R = i2 == 1 ? R("skuRotateJump") : null;
        if (R == null) {
            R = Q();
        }
        JumpEntity jumpEntity = R;
        l.p(view, jumpEntity, J(jumpEntity, 0), i2 == 0 ? M() : N(), 0, 1);
        new com.jingdong.app.mall.home.q.a("\u6838\u5fc3\u697c\u5c42\u70b9\u51fb", K()).b();
    }

    public int y0() {
        return this.A;
    }

    public SkuLayout.Info z0() {
        return this.u;
    }
}
