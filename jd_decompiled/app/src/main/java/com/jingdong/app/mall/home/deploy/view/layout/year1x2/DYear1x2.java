package com.jingdong.app.mall.home.deploy.view.layout.year1x2;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DYear1x2 extends YearBaseView {
    private DYear1x2Model p;
    private IconImageText q;
    private SkuLayout r;
    private f s;
    private f t;

    public DYear1x2(Context context) {
        super(context);
        this.q = new IconImageText(context);
        f fVar = new f(-2, 52);
        this.s = fVar;
        RelativeLayout.LayoutParams u = fVar.u(this.q);
        u.addRule(14);
        addView(this.q, u);
        this.r = new SkuLayout(context);
        f fVar2 = new f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        this.t = fVar2;
        fVar2.E(0, 0, 0, 12);
        RelativeLayout.LayoutParams u2 = this.t.u(this.r);
        u2.addRule(12);
        u2.addRule(14);
        addView(this.r, u2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void i(BaseModel baseModel, int i2) {
        super.i(baseModel, i2);
        baseModel.t();
        this.s.R(-2, 52);
        this.t.R(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        this.t.E(0, 0, 0, 12);
        q();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.p = (DYear1x2Model) baseModel;
        return baseModel != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        this.q.i(this.p.n0());
        this.r.b(this.p.l0());
        this.r.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.year1x2.DYear1x2.1
            {
                DYear1x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DYear1x2.this.p.g0(DYear1x2.this, 0, 1);
            }
        });
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.year1x2.DYear1x2.2
            {
                DYear1x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DYear1x2.this.p.e0(DYear1x2.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
        f.c(this.q, this.s);
        f.c(this.r, this.t);
    }
}
