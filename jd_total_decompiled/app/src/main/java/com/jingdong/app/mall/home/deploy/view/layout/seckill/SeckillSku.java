package com.jingdong.app.mall.home.deploy.view.layout.seckill;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.category.floor.feedssub.a;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.r.e.b;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;
import com.jingdong.common.entity.Product;
import com.jingdong.common.utils.StringUtil;

/* loaded from: classes4.dex */
public class SeckillSku extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private int f8966g;

    /* renamed from: h  reason: collision with root package name */
    private final DarkWhiteBgImageView f8967h;

    /* renamed from: i  reason: collision with root package name */
    private final f f8968i;

    /* renamed from: j  reason: collision with root package name */
    private final SkuLabel f8969j;

    /* renamed from: k  reason: collision with root package name */
    private final f f8970k;

    /* renamed from: l  reason: collision with root package name */
    private f f8971l;

    public SeckillSku(Context context) {
        this(context, -1);
    }

    private void d(SeckillModel seckillModel, Product product) {
        SkuLabel.Info a;
        int i2 = 8;
        if (product != null && product.msItemType != 2) {
            this.f8969j.setVisibility(0);
            f fVar = this.f8971l;
            if (fVar == null) {
                fVar = this.f8968i;
            }
            String string = StringUtil.getString(R.string.product_entity_no_price);
            String miaoShaPrice = product.getMiaoShaPrice();
            String jsonString = b.getJsonString(product.prdObject, "finalPrice", "");
            float d = d.d(32);
            if (!TextUtils.isEmpty(jsonString) && !TextUtils.equals(jsonString, string)) {
                jsonString = a.e(jsonString);
            }
            if (TextUtils.isEmpty(jsonString) && !TextUtils.isEmpty(miaoShaPrice) && !TextUtils.equals(miaoShaPrice, string)) {
                jsonString = a.e(miaoShaPrice);
            }
            if (TextUtils.isEmpty(jsonString)) {
                jsonString = "";
            } else {
                d = (fVar.v() - com.jingdong.app.mall.home.o.a.f.U(d.d(26), jsonString)) - d.d(7);
            }
            if (TextUtils.isEmpty(jsonString)) {
                if (!TextUtils.equals(miaoShaPrice, string)) {
                    miaoShaPrice = a.e(miaoShaPrice);
                }
                jsonString = miaoShaPrice;
            }
            int i3 = (com.jingdong.app.mall.home.o.a.f.Y(jsonString) >= 4.5f || d < ((float) d.d(36))) ? 8 : 16;
            float d2 = d.d(32);
            String jsonString2 = b.getJsonString(product.prdObject, "benefitWord", "");
            if (!TextUtils.isEmpty(jsonString2)) {
                d2 = fVar.v() - com.jingdong.app.mall.home.o.a.f.U(d.d(22), jsonString2);
            }
            if (com.jingdong.app.mall.home.o.a.f.Y(jsonString2) < 4.5f && d2 >= d.d(36)) {
                i2 = 16;
            }
            if (!TextUtils.isEmpty(jsonString2)) {
                a = SkuLabel.Info.a();
                a.d(seckillModel.K0());
                a.l(seckillModel.a0(), i2);
                a.q(seckillModel.I0(), 22);
                a.m(jsonString2);
                a.f(false);
            } else {
                a = SkuLabel.Info.a();
                a.d(seckillModel.K0());
                a.l(seckillModel.a0(), i3);
                a.q(seckillModel.I0(), 26);
                a.m(jsonString);
                a.f(false);
            }
            this.f8969j.f(a);
            return;
        }
        this.f8969j.setVisibility(8);
    }

    private void g(float f2) {
        setScaleX(f2);
        setScaleY(f2);
    }

    public void b(SeckillModel seckillModel, Product product, int i2) {
        c(false, seckillModel, product, i2);
    }

    public void c(boolean z, final SeckillModel seckillModel, Product product, int i2) {
        f.c(this.f8967h, this.f8968i);
        if (product == null) {
            return;
        }
        this.f8966g = i2;
        e.u(this.f8967h, product.getImageUrl());
        com.jingdong.app.mall.home.n.h.e.d(this.f8967h, d.d(8));
        f.c(this.f8969j, this.f8970k);
        d(seckillModel, product);
        if (z) {
            setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillSku.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    seckillModel.Z0(SeckillSku.this.f8966g, SeckillSku.this);
                }
            });
        }
    }

    public void e(int i2) {
        if (i2 >= 100) {
            float f2 = i2 - 100;
            setAlpha(f2 / 400.0f);
            g(((f2 * 0.3f) / 400.0f) + 0.7f);
        }
    }

    public void f(int i2) {
        if (i2 < 400) {
            setAlpha((400 - i2) / 400.0f);
            g(1.0f - ((i2 * 0.3f) / 400));
        }
        if (i2 < 400 || i2 >= 450) {
            return;
        }
        setAlpha(0.0f);
        g(0.7f);
    }

    public int h() {
        return this.f8966g;
    }

    public void i(float f2, float f3) {
        setAlpha(f2);
        g(f3);
    }

    public void j(f fVar) {
        this.f8971l = fVar;
    }

    public void k(int i2) {
        this.f8968i.R(i2, i2);
    }

    public SeckillSku(Context context, int i2) {
        this(context, i2, 13);
    }

    public SeckillSku(Context context, int i2, int i3) {
        super(context);
        DarkWhiteBgImageView darkWhiteBgImageView = new DarkWhiteBgImageView(context);
        this.f8967h = darkWhiteBgImageView;
        darkWhiteBgImageView.c(true);
        darkWhiteBgImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar = new f(i2, i2);
        this.f8968i = fVar;
        RelativeLayout.LayoutParams u = fVar.u(darkWhiteBgImageView);
        u.addRule(i3);
        addView(darkWhiteBgImageView, u);
        SkuLabel skuLabel = new SkuLabel(context);
        this.f8969j = skuLabel;
        f fVar2 = new f(-2, 30);
        this.f8970k = fVar2;
        fVar2.E(0, 0, 0, 2);
        RelativeLayout.LayoutParams u2 = fVar2.u(skuLabel);
        u2.addRule(12);
        u2.addRule(14);
        addView(skuLabel, u2);
    }
}
