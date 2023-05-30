package com.jingdong.app.mall.home.deploy.view.layout.newcomer2x4;

import android.content.Context;
import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DNewcomer2x4Model extends CoreModel<DNewcomer2x4> {
    private String r;
    private String s;
    private JumpEntity t;
    private JumpEntity u;
    private IconImageText.Info v;
    private final SkuLayout.Info[] w = new SkuLayout.Info[4];

    private void F0() {
        JDJSONObject jsonObject = this.f8920m.getJsonObject("banner");
        if (jsonObject == null) {
            return;
        }
        String optString = jsonObject.optString("bannerImg");
        String optString2 = jsonObject.optString("bannerJump");
        this.r = optString;
        this.t = (JumpEntity) JDJSON.parseObject(optString2, JumpEntity.class);
    }

    private void G0() {
        JDJSONObject jsonObject = this.f8920m.getJsonObject("rightCornerInfo");
        if (jsonObject == null) {
            return;
        }
        String optString = jsonObject.optString("img");
        String optString2 = jsonObject.optString("jump");
        this.s = optString;
        this.u = (JumpEntity) JDJSON.parseObject(optString2, JumpEntity.class);
    }

    private String z0(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return M();
                }
                return P();
            }
            return O();
        }
        return N();
    }

    public String A0() {
        return this.s;
    }

    public SkuLayout.Info[] B0() {
        return this.w;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        if (this.f8920m == null) {
            return;
        }
        IconImageText.Info a = IconImageText.Info.a();
        a.d(i0(), 32, h0());
        a.e(0, 0, 8, 0);
        a.h(m.n(this.f8920m.C(), CoreModel.p, true), l0());
        a.g(this.f8920m.getJsonString(DYConstants.SHOW_NAME));
        a.c(true);
        a.f(k0(), 32, j0());
        this.v = a;
        int m2 = m(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        for (int i2 = 0; i2 < 4; i2++) {
            SkuLayout.Info a2 = SkuLayout.Info.a();
            a2.l(m2, m2);
            a2.i(z0(i2), c0(), 8);
            a2.e(null, 150, 30);
            this.w[i2] = a2;
        }
        F0();
        G0();
    }

    public IconImageText.Info C0() {
        return this.v;
    }

    public void D0(View view, Context context) {
        JumpEntity jumpEntity = this.t;
        if (jumpEntity == null) {
            v0(view, 10, false);
        } else {
            l.q(view, jumpEntity, "", 10, 1);
        }
    }

    public void E0(View view, Context context) {
        JumpEntity jumpEntity = this.u;
        if (jumpEntity == null) {
            v0(view, 0, false);
            return;
        }
        a.s("Home_TopRight", "", b.c(jumpEntity.getSrvJson()).toString());
        l.e(context, this.u);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel
    protected void s0(com.jingdong.app.mall.home.p.b.e.b bVar) {
        this.f8880j.R(R2.attr.actionTextColorAlpha, m(226, 226) << 1);
    }

    public String y0() {
        return this.r;
    }
}
