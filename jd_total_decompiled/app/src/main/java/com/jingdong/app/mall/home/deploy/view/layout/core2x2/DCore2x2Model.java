package com.jingdong.app.mall.home.deploy.view.layout.core2x2;

import com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconLabel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.o.a.m;
import com.jingdong.app.mall.home.p.b.e.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DCore2x2Model extends CoreModel<DCore2x2> {
    private IconImageText.Info r;
    private IconLabel.Info s;
    private SkuLayout.Info t;
    private SkuLayout.Info u;
    private String v = "";

    public String A0() {
        return this.v;
    }

    public SkuLayout.Info B0() {
        return this.u;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        f fVar = this.f8920m;
        if (fVar != null) {
            this.v = fVar.getJsonString("markedImg");
        }
        boolean D0 = D0();
        IconImageText.Info a = IconImageText.Info.a();
        a.d(i0(), 32, h0());
        boolean z = false;
        a.e(0, 0, 8, 0);
        a.h(f0(), l0());
        a.g(m0());
        a.c(true);
        a.f(k0(), 32, j0());
        this.r = a;
        IconLabel.Info a2 = IconLabel.Info.a();
        a2.d(20, 20, U());
        a2.b(S(), T());
        a2.g(CoreModel.o, 22);
        a2.f(V());
        a2.e(this.f8920m.getJsonInt("sloganLottie") == 1, 28);
        this.s = a2;
        SkuLabel.Info a3 = SkuLabel.Info.a();
        a3.g(this.f8920m);
        a3.d(Y());
        a3.q(Z(), g.i());
        a3.j(m(30, 28));
        a3.o(W(), a0());
        int m2 = m(R2.anim.pickerview_dialog_scale_in, 140);
        int m3 = m(126, 116);
        int jsonInt = this.f8920m.getJsonInt("skuFrameNum");
        boolean z2 = jsonInt == 1 || jsonInt == 2;
        if (!D0 && jsonInt == 2) {
            z = true;
        }
        SkuLayout.Info a4 = SkuLayout.Info.a();
        a4.l(D0 ? 140 : m2, D0 ? 140 : m2);
        a4.h(D0 ? 112 : m3, D0 ? 112 : m3, z2);
        a4.i(M(), c0(), 8);
        a4.f(a3, -2, m(R2.anim.mtrl_bottom_sheet_slide_in, R2.anim.manto_translate_dialog_in), 30);
        this.t = a4;
        SkuLabel.Info a5 = SkuLabel.Info.a();
        a5.g(this.f8920m);
        a5.d(Y());
        a5.q(Z(), g.i());
        a5.j(m(30, 28));
        a5.o(X(), a0());
        SkuLayout.Info a6 = SkuLayout.Info.a();
        int i2 = D0 ? R2.anim.popup_center_enter : m2;
        if (D0) {
            m2 = R2.anim.popup_center_enter;
        }
        a6.l(i2, m2);
        a6.h(m3, m3, z);
        a6.i(N(), c0(), 8);
        a6.f(a5, -2, m(R2.anim.mtrl_bottom_sheet_slide_in, R2.anim.manto_translate_dialog_in), 30);
        this.u = a6;
    }

    public IconImageText.Info C0() {
        return this.r;
    }

    public boolean D0() {
        return m.c(this.f8920m) && !t();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    protected void s0(b bVar) {
        this.f8880j.R(m(R2.anim.popdown_anim_feedback, R2.anim.popup_center_enter) << 1, m(109, 102) << 1);
    }

    public IconLabel.Info y0() {
        return this.s;
    }

    public SkuLayout.Info z0() {
        return this.t;
    }
}
