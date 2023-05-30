package com.jingdong.app.mall.home.deploy.view.layout.year2x2_1;

import android.text.TextUtils;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearModel;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearSkuItem;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.view.b.h.a;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DYear2x2_1Model extends YearModel<DYear2x2_1> {
    private IconImageText.Info v;
    private YearSkuItem w;

    private int[] n0() {
        return a.e(this.f9101m.X(), -1);
    }

    private int o0() {
        return this.f9101m.getJsonInt("sloganFont", 24);
    }

    private String s0() {
        return this.f9101m.getJsonString("price", "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        if (TextUtils.isEmpty(T()) && TextUtils.isEmpty(V())) {
            this.v = null;
        } else {
            IconImageText.Info a = IconImageText.Info.a();
            a.d(U(), 30, T());
            a.e(0, 0, 8, 0);
            a.h(X(new int[]{-1}), a0());
            a.g(V());
            a.c(true);
            a.i(8);
            this.v = a;
        }
        this.w = N(0);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearModel
    public int[] R() {
        return a.e(this.f9101m.getJsonString("subTitleColor"), -16777216);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearModel
    protected void b0() {
        this.f8880j.R(R2.attr.actualImageResource, 204);
    }

    public String k0() {
        return this.f9101m.getJsonString("timeLayout");
    }

    public SkuLabel.Info l0() {
        if (m0() != null) {
            return null;
        }
        String jsonString = this.f9101m.getJsonString("sloganDefaultImg");
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        SkuLabel.Info a = SkuLabel.Info.a();
        a.j(40);
        a.i(true);
        a.d(jsonString);
        return a;
    }

    public SkuLabel.Info m0() {
        String e2 = com.jingdong.app.mall.home.category.floor.feedssub.a.e(s0());
        int d = d.d(132);
        if (TextUtils.isEmpty(e2) || f.R(o0(), false, e2) > d) {
            return null;
        }
        SkuLabel.Info a = SkuLabel.Info.a();
        a.j(40);
        a.q(n0(), o0());
        String jsonString = this.f9101m.getJsonString("sloganImg");
        if (TextUtils.isEmpty(jsonString)) {
            a.u(false);
            a.l(null, 8);
        } else {
            a.e(new int[]{-49073, -381927}, jsonString);
            a.l(null, 8);
        }
        a.n(e2);
        return a;
    }

    public int[] p0() {
        return a.e(this.f9101m.C(), -16777216);
    }

    public String q0() {
        return this.f9101m.getJsonString("maintitle", "");
    }

    public long r0() {
        return this.f9101m.getJsonLong("timeEnd", 0L);
    }

    public String t0() {
        YearSkuItem yearSkuItem = this.w;
        return yearSkuItem == null ? "" : yearSkuItem.c();
    }

    public String u0() {
        YearSkuItem yearSkuItem = this.w;
        return yearSkuItem == null ? "" : yearSkuItem.f();
    }

    public long v0() {
        return this.f9101m.g0().longValue();
    }

    public IconImageText.Info w0() {
        return this.v;
    }

    public boolean x0() {
        return this.f9101m.getJsonInt("isShowTime", 0) == 1;
    }
}
