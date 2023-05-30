package com.jingdong.app.mall.home.deploy.view.layout.year2x4;

import android.text.TextUtils;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearModel;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearSkuItem;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DYear2x4Model extends YearModel<DYear2x4> {
    private IconImageText.Info v;
    private final IconImageText.Info[] w = new IconImageText.Info[4];
    private final SkuLayout.Info[] x = new SkuLayout.Info[4];

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        String f2;
        super.C();
        boolean z = true;
        if (TextUtils.isEmpty(T()) && TextUtils.isEmpty(V())) {
            this.v = null;
        } else {
            IconImageText.Info a = IconImageText.Info.a();
            a.d(U(), 28, T());
            a.e(0, 0, 8, 0);
            a.h(W(), a0());
            a.g(V());
            a.c(true);
            a.f(Z(), 32, Y());
            this.v = a;
        }
        int i2 = 0;
        while (i2 < 4) {
            YearSkuItem N = N(i2);
            if (N != null) {
                IconImageText.Info a2 = IconImageText.Info.a();
                a2.h(N.h(), 26);
                a2.g(N.g());
                a2.c(z);
                a2.i(5);
                SkuLabel.Info a3 = SkuLabel.Info.a();
                a3.g(this.f9101m);
                a3.l(YearModel.u, 0);
                a3.e(P(), Q());
                a3.q(R(), S());
                a3.f(c0());
                int d = d.d(R2.anim.lib_cashier_sdk_fragment_right_out);
                String e2 = N.e();
                if (!TextUtils.isEmpty(e2) && f.R(S(), c0(), e2) <= d) {
                    f2 = N.e();
                } else {
                    f2 = N.f();
                }
                a3.n(f2);
                SkuLayout.Info a4 = SkuLayout.Info.a();
                a4.l(132, 132);
                a4.j(0, 0, 0, 24);
                a4.i(N.c(), O(), 8);
                a4.k(170, 200);
                a4.e(a3, 144, 32);
                a4.g(0, 0, 0, 4);
                this.w[i2] = a2;
                this.x[i2] = a4;
            }
            i2++;
            z = true;
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearModel
    protected void b0() {
        this.f8880j.R(R2.attr.actualImageResource, m(226, 226) << 1);
    }

    public SkuLayout.Info[] k0() {
        return this.x;
    }

    public IconImageText.Info l0() {
        return this.v;
    }

    public IconImageText.Info[] m0() {
        return this.w;
    }
}
