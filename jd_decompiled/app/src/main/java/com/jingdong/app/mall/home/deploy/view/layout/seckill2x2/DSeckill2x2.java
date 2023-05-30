package com.jingdong.app.mall.home.deploy.view.layout.seckill2x2;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.lifecycle.Observer;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.seckill.BaseSeckill;
import com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckllAnimateSku;
import com.jingdong.app.mall.home.deploy.view.layout.widget.LottieAnimationMask;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.p.b.c.a;
import com.jingdong.app.mall.home.p.b.c.b;
import com.jingdong.app.mall.home.p.b.d.c;
import com.jingdong.app.mall.home.p.b.d.d;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DSeckill2x2 extends BaseSeckill implements Observer<d>, b {
    private HomeDraweeView A;
    private LottieAnimationMask B;
    private int C;
    private boolean D;
    private DSeckill2x2Model v;
    private SeckllAnimateSku w;
    private f x;
    private SeckllAnimateSku y;
    private f z;

    public DSeckill2x2(Context context) {
        super(context);
        setContentDescription("\u4eac\u4e1c\u79d2\u6740");
        F(context);
        E(context);
        u(context);
        D(context);
    }

    private void B() {
        DSeckill2x2Model dSeckill2x2Model = this.v;
        if (dSeckill2x2Model == null || this.A == null) {
            return;
        }
        String h1 = dSeckill2x2Model.h1();
        if (!TextUtils.isEmpty(h1)) {
            this.A.setVisibility(0);
            this.A.bringToFront();
            e.u(this.A, h1);
            return;
        }
        this.A.setVisibility(8);
    }

    private void C() {
        LottieAnimationMask lottieAnimationMask = this.B;
        if (lottieAnimationMask == null || !lottieAnimationMask.isAnimating()) {
            return;
        }
        this.B.cancelAnimation();
    }

    private void D(Context context) {
        LottieAnimationMask lottieAnimationMask = new LottieAnimationMask(context, "HOME_CORE_DSeckill2x2_Lottie");
        this.B = lottieAnimationMask;
        addView(lottieAnimationMask);
        this.B.setVisibility(8);
    }

    private void E(Context context) {
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.A = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.A, new f(-1, -1).u(this.A));
        this.A.setVisibility(8);
    }

    private void F(Context context) {
        this.w = new SeckllAnimateSku(context, this);
        f fVar = new f(R2.anim.pop_left_bottom_out, R2.anim.pickerview_dialog_scale_in);
        this.x = fVar;
        fVar.E(8, 0, 0, 12);
        RelativeLayout.LayoutParams u = this.x.u(this.w);
        u.addRule(12);
        addView(this.w, u);
        this.y = new SeckllAnimateSku(context, this);
        f fVar2 = new f(R2.anim.pop_left_bottom_out, R2.anim.pickerview_dialog_scale_in);
        this.z = fVar2;
        fVar2.E(0, 0, 8, 12);
        RelativeLayout.LayoutParams u2 = this.z.u(this.y);
        u2.addRule(12);
        u2.addRule(11);
        addView(this.y, u2);
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: G */
    public void onChanged(d dVar) {
        if (dVar == null || !dVar.c(this, this.v)) {
            return;
        }
        View b = dVar.b(this, this.v, true);
        this.p = b;
        if (b instanceof com.jingdong.app.mall.home.p.b.d.b) {
            ((com.jingdong.app.mall.home.p.b.d.b) b).a(this);
        }
    }

    @Override // com.jingdong.app.mall.home.p.b.c.b
    public void c(boolean z) {
        if (z) {
            this.w.e();
            this.y.e();
        }
    }

    @Override // com.jingdong.app.mall.home.p.b.c.b
    public void d(boolean z) {
        if (z) {
            this.w.f();
            this.y.f();
        }
    }

    @Override // com.jingdong.app.mall.home.p.b.c.b
    public void e(boolean z, int i2) {
        if (z) {
            this.w.d(i2);
            this.y.d(i2);
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void i(BaseModel baseModel, int i2) {
        super.i(baseModel, i2);
        this.C = i2;
        boolean X0 = this.v.X0();
        this.D = X0;
        if (X0) {
            this.x.R(R2.anim.pickerview_dialog_scale_in, R2.anim.pop_in);
            this.x.E(3, 0, 0, 12);
            this.z.R(R2.anim.popup_center_enter, 212);
            this.z.E(0, 0, 12, 12);
            return;
        }
        int m2 = baseModel.m(R2.anim.pop_left_bottom_out, R2.anim.pop_in);
        int m3 = baseModel.m(R2.anim.pickerview_dialog_scale_in, 140);
        this.x.R(m2, m3);
        this.x.E(baseModel.m(8, 8), 0, 0, 12);
        this.w.h(this.x);
        this.z.R(m2, m3);
        this.z.E(0, 0, baseModel.m(8, 8), 12);
        this.y.h(this.z);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.v = (DSeckill2x2Model) baseModel;
        return baseModel != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.seckill.BaseSeckill
    public void v(boolean z) {
        LottieAnimationMask lottieAnimationMask;
        super.v(z);
        if (this.v.X0() ^ this.D) {
            i(this.v, this.C);
        }
        f.c(this.w, this.x);
        f.c(this.y, this.z);
        C();
        if (this.p != null && this.v.k() != null) {
            m.K(this.p);
            this.p = null;
        }
        this.q.i(this.v.P0());
        this.s.e(this.v, t());
        this.s.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill2x2.DSeckill2x2.1
            {
                DSeckill2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DSeckill2x2.this.v.a1(DSeckill2x2.this);
                c g2 = c.g();
                DSeckill2x2 dSeckill2x2 = DSeckill2x2.this;
                g2.c(dSeckill2x2, dSeckill2x2.v);
            }
        });
        this.s.l(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill2x2.DSeckill2x2.2
            {
                DSeckill2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DSeckill2x2.this.v.b1(DSeckill2x2.this);
                c g2 = c.g();
                DSeckill2x2 dSeckill2x2 = DSeckill2x2.this;
                g2.c(dSeckill2x2, dSeckill2x2.v);
            }
        });
        B();
        boolean z2 = false;
        this.w.b(this, z, this.v, 0);
        this.y.b(this, z, this.v, 1);
        if (this.v.j1()) {
            a.r().p(this);
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill2x2.DSeckill2x2.3
            {
                DSeckill2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DSeckill2x2.this.v.a1(DSeckill2x2.this);
                c g2 = c.g();
                DSeckill2x2 dSeckill2x2 = DSeckill2x2.this;
                g2.c(dSeckill2x2, dSeckill2x2.v);
            }
        });
        if (z && !this.v.V0() && this.v.i1() && !TextUtils.isEmpty(this.v.f1()) && (lottieAnimationMask = this.B) != null && lottieAnimationMask.b(this.v.g1())) {
            z2 = true;
        }
        if (z2) {
            this.B.f(this.v.f1());
            this.B.g();
        }
        c.g().j(this, this.v, this);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.seckill.BaseSeckill
    protected void x() {
        this.v.e1();
    }
}
