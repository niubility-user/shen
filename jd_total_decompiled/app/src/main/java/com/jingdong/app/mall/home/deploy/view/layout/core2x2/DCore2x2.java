package com.jingdong.app.mall.home.deploy.view.layout.core2x2;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.lifecycle.Observer;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconLabel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.p.b.d.b;
import com.jingdong.app.mall.home.p.b.d.c;
import com.jingdong.app.mall.home.p.b.d.d;
import com.jingdong.app.mall.home.q.a;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DCore2x2 extends CoreBaseView implements Observer<d> {
    private int A;
    private boolean B;
    private View p;
    private DCore2x2Model q;
    private IconImageText r;
    private IconLabel s;
    private SkuLayout t;
    private SkuLayout u;
    private HomeDraweeView v;
    private f w;
    private f x;
    private f y;
    private f z;

    public DCore2x2(Context context) {
        super(context);
        y(context);
        x(context);
        z(context);
    }

    private void w() {
        DCore2x2Model dCore2x2Model = this.q;
        if (dCore2x2Model == null || this.v == null) {
            return;
        }
        String A0 = dCore2x2Model.A0();
        if (!TextUtils.isEmpty(A0)) {
            this.v.setVisibility(0);
            this.v.bringToFront();
            e.u(this.v, A0);
            return;
        }
        this.v.setVisibility(8);
    }

    private void x(Context context) {
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.v = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.v, new f(-1, -1).u(this.v));
        this.v.setVisibility(8);
    }

    private void y(Context context) {
        this.t = new SkuLayout(context);
        f fVar = new f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        this.y = fVar;
        fVar.E(12, 0, 0, 12);
        RelativeLayout.LayoutParams u = this.y.u(this.t);
        u.addRule(12);
        addView(this.t, u);
        this.u = new SkuLayout(context);
        f fVar2 = new f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        this.z = fVar2;
        fVar2.E(0, 0, 12, 12);
        RelativeLayout.LayoutParams u2 = this.z.u(this.u);
        u2.addRule(11);
        u2.addRule(12);
        addView(this.u, u2);
    }

    private void z(Context context) {
        IconImageText iconImageText = new IconImageText(context);
        this.r = iconImageText;
        iconImageText.setId(R.id.homefloor_child_item1);
        f fVar = new f(-2, 52);
        this.w = fVar;
        fVar.J(16, 0, 0, 0);
        View view = this.r;
        addView(view, this.w.u(view));
        this.s = new IconLabel(context);
        f fVar2 = new f(-2, 28);
        this.x = fVar2;
        fVar2.E(8, 12, 0, 0);
        RelativeLayout.LayoutParams u = this.x.u(this.s);
        u.addRule(1, this.r.getId());
        addView(this.s, u);
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: A */
    public void onChanged(d dVar) {
        if (dVar == null || !dVar.c(this, this.q)) {
            return;
        }
        View b = dVar.b(this, this.q, false);
        this.p = b;
        if (b instanceof b) {
            ((b) b).a(this);
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void i(BaseModel baseModel, int i2) {
        super.i(baseModel, i2);
        this.A = i2;
        this.B = this.q.D0();
        this.w.J(baseModel.m(16, 16), 0, 0, 0);
        if (this.B) {
            this.y.R(R2.anim.pickerview_dialog_scale_in, R2.anim.pop_in);
            this.y.E(3, 0, 0, 12);
            this.z.R(R2.anim.popup_center_enter, 212);
            this.z.E(0, 0, 12, 12);
        } else {
            int m2 = baseModel.m(R2.anim.pickerview_dialog_scale_in, 140);
            int m3 = baseModel.m(R2.anim.pickerview_dialog_scale_in, 140);
            this.y.R(m2, m3);
            this.y.E(baseModel.m(12, 20), 0, 0, 12);
            this.z.R(m2, m3);
            this.z.E(0, 0, baseModel.m(12, 20), 12);
        }
        q();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.q = (DCore2x2Model) baseModel;
        return baseModel != null;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        if (this.q.D0() ^ this.B) {
            i(this.q, this.A);
        }
        c.g().j(this, this.q, this);
        if (this.p != null && this.q.k() != null) {
            m.K(this.p);
            this.p = null;
        }
        this.r.i(this.q.C0());
        this.s.h(this.q.y0(), this.q.l().v(), this.r.k());
        this.s.setVisibility(this.q.D0() ? 8 : 0);
        w();
        this.t.c(this.q.z0(), this.q);
        this.u.b(this.q.B0());
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.core2x2.DCore2x2.1
            {
                DCore2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCore2x2Model dCore2x2Model = DCore2x2.this.q;
                DCore2x2 dCore2x2 = DCore2x2.this;
                dCore2x2Model.u0(dCore2x2, dCore2x2, 0);
            }
        });
        this.t.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.core2x2.DCore2x2.2
            {
                DCore2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCore2x2Model dCore2x2Model = DCore2x2.this.q;
                DCore2x2 dCore2x2 = DCore2x2.this;
                dCore2x2Model.u0(dCore2x2, dCore2x2.t, 0);
            }
        });
        this.u.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.core2x2.DCore2x2.3
            {
                DCore2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCore2x2Model dCore2x2Model = DCore2x2.this.q;
                DCore2x2 dCore2x2 = DCore2x2.this;
                dCore2x2Model.u0(dCore2x2, dCore2x2.u, 1);
            }
        });
        setContentDescription(this.q.m0());
        new a("\u6838\u5fc3\u697c\u5c42\u66dd\u5149", true, this.q.L()).b();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
        f.c(this.r, this.w);
        f.c(this.s, this.x);
        f.c(this.t, this.y);
        f.c(this.u, this.z);
    }
}
