package com.jingdong.app.mall.home.deploy.view.layout.core1x2;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.q.a;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DCore1x2 extends CoreBaseView {
    private DCore1x2Model p;
    private IconImageText q;
    private SkuLayout r;
    private f s;
    private f t;

    public DCore1x2(Context context) {
        super(context);
        IconImageText iconImageText = new IconImageText(context);
        this.q = iconImageText;
        iconImageText.setId(R.id.homefloor_child_item1);
        f fVar = new f(-2, 52);
        this.s = fVar;
        fVar.J(16, 0, 0, 0);
        View view = this.q;
        addView(view, this.s.u(view));
        this.r = new SkuLayout(context);
        f fVar2 = new f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        this.t = fVar2;
        fVar2.E(0, 0, 0, 8);
        RelativeLayout.LayoutParams u = this.t.u(this.r);
        u.addRule(12);
        u.addRule(14);
        addView(this.r, u);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void i(BaseModel baseModel, int i2) {
        super.i(baseModel, i2);
        baseModel.t();
        this.t.R(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        this.t.E(0, 0, 0, 8);
        q();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.p = (DCore1x2Model) baseModel;
        return baseModel != null;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        this.q.i(this.p.z0());
        this.r.b(this.p.y0());
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.core1x2.DCore1x2.1
            {
                DCore1x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCore1x2Model dCore1x2Model = DCore1x2.this.p;
                DCore1x2 dCore1x2 = DCore1x2.this;
                dCore1x2Model.u0(dCore1x2, dCore1x2, 0);
            }
        });
        new a("\u6838\u5fc3\u697c\u5c42\u66dd\u5149", true, this.p.L()).b();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
        f.c(this.q, this.s);
        f.c(this.r, this.t);
    }
}
