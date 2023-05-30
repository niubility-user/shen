package com.jingdong.app.mall.home.deploy.view.layout.seckill;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.deploy.view.layout.seckill2x2.DSeckill2x2;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.p.b.d.c;
import com.jingdong.common.entity.Product;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class SeckllAnimateSku extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private SeckillModel f8974g;

    /* renamed from: h  reason: collision with root package name */
    private final SeckillSku f8975h;

    /* renamed from: i  reason: collision with root package name */
    private final SeckillSku f8976i;

    /* renamed from: j  reason: collision with root package name */
    private SeckillSku f8977j;

    /* renamed from: k  reason: collision with root package name */
    private SeckillSku f8978k;

    /* renamed from: l  reason: collision with root package name */
    private int f8979l;

    public SeckllAnimateSku(Context context, BaseSeckill baseSeckill) {
        super(context);
        SeckillSku seckillSku = new SeckillSku(context, 124);
        this.f8976i = seckillSku;
        seckillSku.i(0.0f, 1.0f);
        addView(seckillSku, new f(-1, -1).u(seckillSku));
        this.f8978k = seckillSku;
        SeckillSku seckillSku2 = new SeckillSku(context, 124);
        this.f8975h = seckillSku2;
        seckillSku2.i(1.0f, 1.0f);
        addView(seckillSku2, new f(-1, -1).u(seckillSku2));
        this.f8977j = seckillSku2;
    }

    private void g() {
        SeckillSku seckillSku = this.f8977j;
        if (seckillSku != null) {
            seckillSku.i(1.0f, 1.0f);
        }
        SeckillSku seckillSku2 = this.f8978k;
        if (seckillSku2 != null) {
            seckillSku2.i(0.0f, 0.7f);
        }
        this.f8979l = 0;
    }

    private void i() {
        SeckillSku seckillSku = this.f8977j;
        this.f8977j = this.f8978k;
        this.f8978k = seckillSku;
    }

    public void b(final DSeckill2x2 dSeckill2x2, boolean z, SeckillModel seckillModel, int i2) {
        boolean X0 = seckillModel.X0();
        int m2 = seckillModel.m(R2.anim.pickerview_dialog_scale_in, 140);
        if (X0) {
            m2 = i2 != 0 ? R2.anim.popup_center_enter : 140;
        }
        this.f8975h.k(m2);
        this.f8976i.k(m2);
        this.f8974g = seckillModel;
        if (z) {
            SeckillSku seckillSku = this.f8975h;
            this.f8977j = seckillSku;
            this.f8978k = this.f8976i;
            seckillSku.i(1.0f, 1.0f);
            this.f8978k.i(0.0f, 1.0f);
        }
        Product J0 = seckillModel.J0(i2);
        int i3 = i2 + 2;
        Product J02 = seckillModel.J0(i3);
        this.f8975h.b(seckillModel, J0, i2);
        this.f8976i.b(seckillModel, J02, i3);
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckllAnimateSku.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SeckllAnimateSku.this.f8974g.Z0(SeckllAnimateSku.this.c(), SeckllAnimateSku.this);
                c.g().c(dSeckill2x2, SeckllAnimateSku.this.f8974g);
            }
        });
    }

    public int c() {
        return (this.f8979l < 250 ? this.f8977j : this.f8978k).h();
    }

    public void d(int i2) {
        this.f8979l = i2;
        SeckillSku seckillSku = this.f8977j;
        if (seckillSku != null) {
            seckillSku.f(i2);
        }
        SeckillSku seckillSku2 = this.f8978k;
        if (seckillSku2 != null) {
            seckillSku2.e(i2);
        }
    }

    public void e() {
        i();
        g();
    }

    public void f() {
        g();
    }

    public void h(f fVar) {
        this.f8975h.j(fVar);
        this.f8976i.j(fVar);
    }
}
