package com.jingdong.app.mall.home.deploy.view.layout.seckill2x4;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.seckill.BaseSeckill;
import com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillSku;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.b.f.h;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DSeckill2x4 extends BaseSeckill {
    private int A;
    private boolean B;
    private DSeckill2x4Model v;
    private final SeckillSku[] w;
    private final f[] x;
    private SimpleDraweeView y;
    private f z;

    public DSeckill2x4(Context context) {
        super(context);
        this.w = new SeckillSku[4];
        this.x = new f[4];
        u(context);
        setContentDescription("\u4eac\u4e1c\u79d2\u6740");
    }

    private void B(boolean z, boolean z2) {
        SimpleDraweeView simpleDraweeView = this.y;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(z2 ? 0 : 8);
        } else if (z2) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.y = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            f fVar = new f(324, 60);
            this.z = fVar;
            fVar.E(0, 0, 0, 12);
            RelativeLayout.LayoutParams u = this.z.u(this.y);
            u.addRule(12);
            u.addRule(14);
            addView(this.y, u);
            this.y.setContentDescription(getContext().getString(R.string.home_obstacle_free));
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void i(BaseModel baseModel, int i2) {
        super.i(baseModel, i2);
        this.A = i2;
        this.B = this.v.k1();
        B(baseModel.t(), this.B);
        int i3 = 0;
        while (i3 < 4) {
            this.w[i3].k(R2.anim.pickerview_dialog_scale_in);
            this.x[i3].R(R2.anim.pop_left_bottom_out, !this.B ? R2.anim.slide_out_to_bottom : R2.anim.pickerview_dialog_scale_in);
            boolean z = i3 % 2 == 0;
            int i4 = 8;
            f fVar = this.x[i3];
            int i5 = z ? 8 : 0;
            int i6 = i3 < 2 ? 48 : !this.B ? 248 : 214;
            if (z) {
                i4 = 0;
            }
            fVar.E(i5, i6, i4, 0);
            this.w[i3].j(this.x[i3]);
            f.c(this.w[i3], this.x[i3]);
            i3++;
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.v = (DSeckill2x4Model) baseModel;
        return baseModel != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.seckill.BaseSeckill, com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void k() {
        int i2;
        super.k();
        for (int i3 = 0; i3 < 4; i3++) {
            this.B = this.v.k1();
            this.v.t();
            boolean z = i3 % 2 == 0;
            int i4 = !this.B ? R2.anim.slide_out_to_bottom : R2.anim.pickerview_dialog_scale_in;
            View seckillSku = new SeckillSku(getContext(), R2.anim.pickerview_dialog_scale_in, 14);
            f fVar = new f(R2.anim.pop_left_bottom_out, i4);
            int i5 = z ? 8 : 0;
            if (i3 < 2) {
                i2 = 48;
            } else {
                i2 = !this.B ? 248 : 214;
            }
            fVar.E(i5, i2, z ? 0 : 8, 0);
            RelativeLayout.LayoutParams u = fVar.u(seckillSku);
            u.addRule(z ? 9 : 11);
            this.w[i3] = seckillSku;
            this.x[i3] = fVar;
            addView(seckillSku, u);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.seckill.BaseSeckill
    public void v(boolean z) {
        super.v(z);
        if (this.v.k1() ^ this.B) {
            i(this.v, this.A);
        }
        this.q.i(this.v.P0());
        this.s.e(this.v, t());
        this.s.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill2x4.DSeckill2x4.1
            {
                DSeckill2x4.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DSeckill2x4.this.v.a1(DSeckill2x4.this);
            }
        });
        this.s.l(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill2x4.DSeckill2x4.2
            {
                DSeckill2x4.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DSeckill2x4.this.v.b1(DSeckill2x4.this);
            }
        });
        for (int i2 = 0; i2 < 4; i2++) {
            SeckillSku seckillSku = this.w[i2];
            DSeckill2x4Model dSeckill2x4Model = this.v;
            seckillSku.c(true, dSeckill2x4Model, dSeckill2x4Model.J0(i2), i2);
            f.c(this.w[i2], this.x[i2]);
        }
        SimpleDraweeView simpleDraweeView = this.y;
        if (simpleDraweeView != null) {
            f.c(simpleDraweeView, this.z);
            e.m(this.y, this.v.f1(), e.b);
            com.jingdong.app.mall.home.n.h.e.d(this.y, d.d(8));
            this.y.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill2x4.DSeckill2x4.3
                {
                    DSeckill2x4.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DSeckill2x4.this.v.g1(DSeckill2x4.this.getContext());
                }
            });
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill2x4.DSeckill2x4.4
            {
                DSeckill2x4.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DSeckill2x4.this.v.a1(DSeckill2x4.this);
            }
        });
        if (z) {
            com.jingdong.app.mall.home.floor.view.b.f.e.j().e(new h() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill2x4.DSeckill2x4.5
                {
                    DSeckill2x4.this = this;
                }

                @Override // com.jingdong.app.mall.home.floor.view.b.f.g
                public boolean g() {
                    return true;
                }

                @Override // com.jingdong.app.mall.home.floor.view.b.f.g
                public void onEnd(boolean z2) {
                    if (z2) {
                        DSeckill2x4.this.w();
                    }
                }
            });
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.seckill.BaseSeckill
    protected void x() {
        this.v.e1();
    }
}
