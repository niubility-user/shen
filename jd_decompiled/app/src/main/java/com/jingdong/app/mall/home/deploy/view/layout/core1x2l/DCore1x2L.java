package com.jingdong.app.mall.home.deploy.view.layout.core1x2l;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView;
import com.jingdong.app.mall.home.deploy.view.layout.core1x2lr.Core1x2LRAnimateSku;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.q.a;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DCore1x2L extends CoreBaseView {
    private DCore1x2LModel p;
    private IconImageText q;
    private Core1x2LRAnimateSku r;
    private f s;
    private f t;
    private HomeDraweeView u;

    public DCore1x2L(Context context) {
        super(context);
        setClipChildren(false);
        this.f8919n.changeScaleType(ImageView.ScaleType.FIT_XY);
        IconImageText iconImageText = new IconImageText(context);
        this.q = iconImageText;
        iconImageText.setId(R.id.homefloor_child_item1);
        f fVar = new f(-2, 52);
        this.s = fVar;
        fVar.J(16, 0, 0, 0);
        View view = this.q;
        addView(view, this.s.u(view));
        this.r = new Core1x2LRAnimateSku(context);
        f fVar2 = new f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        this.t = fVar2;
        fVar2.E(12, 0, 0, 12);
        RelativeLayout.LayoutParams u = this.t.u(this.r);
        u.addRule(12);
        addView(this.r, u);
        w(context);
    }

    private void v() {
        DCore1x2LModel dCore1x2LModel = this.p;
        if (dCore1x2LModel == null || this.u == null) {
            return;
        }
        String A0 = dCore1x2LModel.A0();
        if (!TextUtils.isEmpty(A0)) {
            this.u.setVisibility(0);
            this.u.bringToFront();
            e.u(this.u, A0);
            return;
        }
        this.u.setVisibility(8);
    }

    private void w(Context context) {
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.u = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.u, new f(-1, -1).u(this.u));
        this.u.setVisibility(8);
    }

    private void x(BaseModel baseModel) {
        boolean v = baseModel.v();
        this.s.C(v ? 46 : 52);
        this.s.J(v ? 36 : 16, 0, 0, 0);
        int m2 = v ? 144 : baseModel.m(R2.anim.pickerview_dialog_scale_in, 140);
        int m3 = v ? 140 : baseModel.m(R2.anim.pickerview_dialog_scale_in, 140);
        int m4 = v ? 24 : baseModel.m(12, 20);
        int i2 = v ? 10 : 12;
        this.t.Q(m2);
        this.t.C(m3);
        this.t.E(m4, 0, 0, i2);
        q();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void i(BaseModel baseModel, int i2) {
        super.i(baseModel, i2);
        x(baseModel);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.p = (DCore1x2LModel) baseModel;
        return baseModel != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        x(this.p);
        this.r.setClipChildren(!this.p.t());
        this.q.i(this.p.J0());
        this.r.a(this.p);
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.core1x2l.DCore1x2L.1
            {
                DCore1x2L.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCore1x2L.this.p.O0(DCore1x2L.this.r);
                DCore1x2LModel dCore1x2LModel = DCore1x2L.this.p;
                DCore1x2L dCore1x2L = DCore1x2L.this;
                dCore1x2LModel.u0(dCore1x2L, dCore1x2L, dCore1x2L.r.b());
            }
        });
        setContentDescription(this.p.m0());
        this.r.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.core1x2l.DCore1x2L.2
            {
                DCore1x2L.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCore1x2L.this.p.O0(DCore1x2L.this.r);
                DCore1x2LModel dCore1x2LModel = DCore1x2L.this.p;
                DCore1x2L dCore1x2L = DCore1x2L.this;
                dCore1x2LModel.u0(dCore1x2L, dCore1x2L, dCore1x2L.r.b());
            }
        });
        if (!this.p.v()) {
            new a("\u6838\u5fc3\u697c\u5c42\u66dd\u5149", true, this.p.L()).b();
        }
        v();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
        f.d(this.q, this.s, true);
        f.d(this.r, this.t, true);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView
    protected float[] r(int i2) {
        com.jingdong.app.mall.home.n.h.e.b(this.f8919n, s());
        float f2 = i2;
        return new float[]{f2, f2, 0.0f, 0.0f, 0.0f, 0.0f, f2, f2};
    }
}
