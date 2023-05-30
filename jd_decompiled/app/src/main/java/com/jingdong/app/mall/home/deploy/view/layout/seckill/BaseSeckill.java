package com.jingdong.app.mall.home.deploy.view.layout.seckill;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.a;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public abstract class BaseSeckill extends CoreBaseView {
    protected View p;
    protected IconImageText q;
    protected f r;
    protected SeckillFlipper s;
    protected f t;
    protected boolean u;

    public BaseSeckill(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void k() {
        super.k();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public final void m() {
        super.m();
        v(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void n() {
        super.n();
        this.s.s();
        if (m.I(this, a.f8560i, a.f8562k, false)) {
            x();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void p(int i2, int i3) {
        super.p(i2, i3);
        if (m.H(this, i2, i3, 100, true)) {
            return;
        }
        this.s.s();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int t() {
        return this.q.k() + this.r.l() + this.t.l();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void u(Context context) {
        IconImageText iconImageText = new IconImageText(context);
        this.q = iconImageText;
        iconImageText.setId(R.id.mallfloor_item1);
        f fVar = new f(-2, 54);
        this.r = fVar;
        fVar.E(16, 0, 0, 0);
        View view = this.q;
        addView(view, this.r.u(view));
        this.s = new SeckillFlipper(context);
        f fVar2 = new f(R2.anim.slide_out_to_bottom, 32);
        this.t = fVar2;
        fVar2.E(8, 10, 0, 0);
        RelativeLayout.LayoutParams u = this.t.u(this.s);
        u.addRule(1, this.q.getId());
        addView(this.s, u);
        com.jingdong.app.mall.home.o.a.f.G0(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void v(boolean z) {
        if (z) {
            this.u = false;
        }
        x();
        f.c(this.s, this.t);
        f.c(this.q, this.r);
    }

    public void w() {
        this.u = true;
        z();
    }

    protected void x() {
    }

    public void y() {
        v(false);
        if (this.u) {
            w();
        }
    }

    protected void z() {
        if (this.u) {
            this.s.q();
        }
    }
}
