package com.jingdong.app.mall.home.deploy.view.layout.seckillNewcomer2x4;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.a;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconLabel;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DSeckillNewcomer2x4 extends CoreBaseView {
    private final IconImageText p;
    private final f q;
    private final IconLabel r;
    private final f s;
    private final NewcomerSku[] t;
    private final f[] u;
    private DSeckillNewcomer2x4Model v;

    public DSeckillNewcomer2x4(Context context) {
        super(context);
        this.t = new NewcomerSku[4];
        this.u = new f[4];
        IconImageText iconImageText = new IconImageText(context);
        this.p = iconImageText;
        iconImageText.setId(R.id.mallfloor_item1);
        f fVar = new f(-2, 54);
        this.q = fVar;
        fVar.E(16, 0, 0, 0);
        addView(iconImageText, fVar.u(iconImageText));
        IconLabel iconLabel = new IconLabel(context);
        this.r = iconLabel;
        f fVar2 = new f(-2, 30);
        this.s = fVar2;
        fVar2.E(8, 12, 0, 0);
        RelativeLayout.LayoutParams u = fVar2.u(iconLabel);
        u.addRule(1, iconImageText.getId());
        addView(iconLabel, u);
        setContentDescription("\u4eac\u4e1c\u79d2\u6740");
        com.jingdong.app.mall.home.o.a.f.G0(this);
    }

    private int u() {
        return this.p.k() + this.q.l() + this.s.l();
    }

    private void v() {
        this.v.e1();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.v = (DSeckillNewcomer2x4Model) baseModel;
        return baseModel != null;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void k() {
        super.k();
        int i2 = 0;
        while (i2 < 2) {
            int i3 = i2 == 0 ? 48 : 214;
            int i4 = i2 == 0 ? R2.anim.pickerview_dialog_scale_in : 236;
            int i5 = 0;
            while (i5 < 2) {
                boolean z = i5 == 0;
                View newcomerSku = new NewcomerSku(getContext(), i2 == 1);
                f fVar = new f(R2.anim.pickerview_dialog_scale_in, i4);
                fVar.E(z ? 12 : 0, i3, z ? 0 : 12, 0);
                RelativeLayout.LayoutParams u = fVar.u(newcomerSku);
                u.addRule(z ? 9 : 11);
                int i6 = (i2 * 2) + i5;
                this.t[i6] = newcomerSku;
                this.u[i6] = fVar;
                addView(newcomerSku, u);
                i5++;
            }
            i2++;
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public final void m() {
        super.m();
        v();
        f.c(this.p, this.q);
        this.p.i(this.v.P0());
        f.c(this.r, this.s);
        this.r.h(this.v.D0(), this.v.l().v(), u());
        w();
        this.r.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckillNewcomer2x4.DSeckillNewcomer2x4.1
            {
                DSeckillNewcomer2x4.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DSeckillNewcomer2x4.this.v.b1(DSeckillNewcomer2x4.this);
            }
        });
        for (int i2 = 0; i2 < 4; i2++) {
            NewcomerSku newcomerSku = this.t[i2];
            DSeckillNewcomer2x4Model dSeckillNewcomer2x4Model = this.v;
            newcomerSku.b(dSeckillNewcomer2x4Model, dSeckillNewcomer2x4Model.J0(i2), i2);
            f.c(this.t[i2], this.u[i2]);
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckillNewcomer2x4.DSeckillNewcomer2x4.2
            {
                DSeckillNewcomer2x4.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DSeckillNewcomer2x4.this.v.a1(DSeckillNewcomer2x4.this);
            }
        });
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void n() {
        super.n();
        if (m.I(this, a.f8560i, a.f8562k, false)) {
            v();
        }
    }

    public void w() {
        JumpEntity G0 = this.v.G0();
        if (G0 != null) {
            com.jingdong.app.mall.home.r.c.a.y("Home_SeckillOperaWordExpo", "", G0.getSrvJson());
        }
    }
}
