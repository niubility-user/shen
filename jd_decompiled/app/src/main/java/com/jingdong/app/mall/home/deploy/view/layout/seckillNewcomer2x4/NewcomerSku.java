package com.jingdong.app.mall.home.deploy.view.layout.seckillNewcomer2x4;

import android.content.Context;
import android.graphics.Rect;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.r.e.b;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;
import com.jingdong.common.entity.Product;
import com.jingdong.common.search.view.PriceHelper;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class NewcomerSku extends RelativeLayout {
    private static final int[] q = {0, 0};

    /* renamed from: g  reason: collision with root package name */
    private final boolean f8992g;

    /* renamed from: h  reason: collision with root package name */
    private int f8993h;

    /* renamed from: i  reason: collision with root package name */
    private final DarkWhiteBgImageView f8994i;

    /* renamed from: j  reason: collision with root package name */
    private final f f8995j;

    /* renamed from: k  reason: collision with root package name */
    private final SkuLabel f8996k;

    /* renamed from: l  reason: collision with root package name */
    private final SkuLabel.Info f8997l;

    /* renamed from: m  reason: collision with root package name */
    private final f f8998m;

    /* renamed from: n  reason: collision with root package name */
    private SkuLabel f8999n;
    private final f o;
    private final SkuLabel.Info p;

    public NewcomerSku(Context context, boolean z) {
        super(context);
        f fVar = new f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        this.f8995j = fVar;
        f fVar2 = new f(-2, 30);
        this.f8998m = fVar2;
        this.f8999n = null;
        f fVar3 = new f(154, 30);
        this.o = fVar3;
        this.f8992g = z;
        DarkWhiteBgImageView darkWhiteBgImageView = new DarkWhiteBgImageView(getContext());
        this.f8994i = darkWhiteBgImageView;
        darkWhiteBgImageView.c(true);
        darkWhiteBgImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(darkWhiteBgImageView, fVar.u(darkWhiteBgImageView));
        fVar2.E(0, z ? 200 : 126, 0, 0);
        SkuLabel skuLabel = new SkuLabel(getContext());
        this.f8996k = skuLabel;
        RelativeLayout.LayoutParams u = fVar2.u(skuLabel);
        u.addRule(14);
        addView(skuLabel, u);
        this.f8997l = SkuLabel.Info.a();
        if (z) {
            fVar3.E(0, R2.anim.pop_left_bottom_out, 0, 0);
            SkuLabel skuLabel2 = new SkuLabel(getContext());
            this.f8999n = skuLabel2;
            RelativeLayout.LayoutParams u2 = fVar3.u(skuLabel2);
            u2.addRule(14);
            addView(this.f8999n, u2);
        }
        this.p = SkuLabel.Info.b(9);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void c(DSeckillNewcomer2x4Model dSeckillNewcomer2x4Model, Product product) {
        if (this.f8992g) {
            String jsonString = b.getJsonString(product.prdObject, "benefitWord", "");
            int indexOf = jsonString.indexOf(getContext().getResources().getString(R.string.yangjiao));
            if (indexOf < 0) {
                indexOf = jsonString.indexOf(PriceHelper.PRODUCT_PRICE_LABEL_FULL);
            }
            String str = null;
            if (indexOf >= 0) {
                SpannableString spannableString = new SpannableString(jsonString);
                spannableString.setSpan(new AbsoluteSizeSpan(d.d(22)), 0, indexOf, 17);
                spannableString.setSpan(new AbsoluteSizeSpan(d.d(24)), indexOf, jsonString.length(), 18);
                spannableString.setSpan(new com.jingdong.app.mall.home.category.floor.base.d(-d.d(1)), 0, indexOf, 17);
                str = spannableString;
            }
            SkuLabel.Info info = this.p;
            info.d(dSeckillNewcomer2x4Model.f1());
            info.r(-1, -1);
            info.s(new Rect(dSeckillNewcomer2x4Model.h1(), 0, 0, 0));
            info.l(new Rect(0, -3, 0, -3), 0);
            info.h(17);
            info.q(dSeckillNewcomer2x4Model.g1(), 24);
            if (str != null) {
                jsonString = str;
            }
            info.m(jsonString);
            info.f(false);
            this.f8999n.f(this.p);
        }
    }

    private void d(Product product) {
        e.u(this.f8994i, product.getImageUrl());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00f4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void e(com.jingdong.app.mall.home.deploy.view.layout.seckillNewcomer2x4.DSeckillNewcomer2x4Model r13, com.jingdong.common.entity.Product r14) {
        /*
            r12 = this;
            int r0 = com.jingdong.app.mall.home.R.string.product_entity_no_price
            java.lang.String r0 = com.jingdong.common.utils.StringUtil.getString(r0)
            java.lang.String r1 = r14.getMiaoShaPrice()
            com.jd.framework.json.JDJSONObject r14 = r14.prdObject
            java.lang.String r2 = "finalPrice"
            java.lang.String r3 = ""
            java.lang.String r14 = com.jingdong.app.mall.home.r.e.b.getJsonString(r14, r2, r3)
            boolean r2 = android.text.TextUtils.isEmpty(r14)
            if (r2 != 0) goto L24
            boolean r2 = android.text.TextUtils.equals(r14, r0)
            if (r2 != 0) goto L24
            java.lang.String r14 = com.jingdong.app.mall.home.category.floor.feedssub.a.e(r14)
        L24:
            boolean r2 = android.text.TextUtils.isEmpty(r14)
            if (r2 == 0) goto L3a
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L3a
            boolean r2 = android.text.TextUtils.equals(r1, r0)
            if (r2 != 0) goto L3a
            java.lang.String r14 = com.jingdong.app.mall.home.category.floor.feedssub.a.e(r1)
        L3a:
            boolean r2 = android.text.TextUtils.isEmpty(r14)
            r4 = 24
            r5 = 0
            r6 = 0
            if (r2 != 0) goto Lac
            int r2 = r14.length()
            r7 = 6
            if (r2 >= r7) goto Lad
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r14)
            java.lang.String r7 = "\u9884\u4f30\u4ef7"
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            android.text.SpannableString r7 = new android.text.SpannableString
            java.lang.String r8 = com.jingdong.app.mall.home.category.floor.feedssub.a.e(r2)
            r7.<init>(r8)
            android.text.style.AbsoluteSizeSpan r8 = new android.text.style.AbsoluteSizeSpan
            int r9 = com.jingdong.app.mall.home.floor.common.d.d(r4)
            r8.<init>(r9)
            int r9 = r14.length()
            r10 = 18
            r7.setSpan(r8, r6, r9, r10)
            android.text.style.AbsoluteSizeSpan r8 = new android.text.style.AbsoluteSizeSpan
            r9 = 22
            int r9 = com.jingdong.app.mall.home.floor.common.d.d(r9)
            r8.<init>(r9)
            int r9 = r14.length()
            int r11 = r2.length()
            r7.setSpan(r8, r9, r11, r10)
            com.jingdong.app.mall.home.category.floor.base.d r8 = new com.jingdong.app.mall.home.category.floor.base.d
            r9 = 1
            int r9 = com.jingdong.app.mall.home.floor.common.d.d(r9)
            int r9 = -r9
            float r9 = (float) r9
            r11 = 2
            int r11 = com.jingdong.app.mall.home.floor.common.d.d(r11)
            float r11 = (float) r11
            r8.<init>(r9, r11)
            int r14 = r14.length()
            int r9 = r2.length()
            r7.setSpan(r8, r14, r9, r10)
            r14 = r2
            goto Lae
        Lac:
            r14 = r3
        Lad:
            r7 = r5
        Lae:
            boolean r2 = android.text.TextUtils.isEmpty(r14)
            if (r2 == 0) goto Lc0
            boolean r14 = android.text.TextUtils.equals(r1, r0)
            if (r14 == 0) goto Lbb
            goto Lbf
        Lbb:
            java.lang.String r1 = com.jingdong.app.mall.home.category.floor.feedssub.a.e(r1)
        Lbf:
            r14 = r1
        Lc0:
            boolean r0 = android.text.TextUtils.isEmpty(r14)
            r1 = 8
            if (r0 != 0) goto Lce
            int r0 = r14.length()
            if (r0 >= r1) goto Ld0
        Lce:
            r1 = 16
        Ld0:
            android.graphics.Rect r0 = new android.graphics.Rect
            r2 = -3
            r0.<init>(r1, r2, r1, r2)
            com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel$Info r1 = r12.f8997l
            boolean r2 = r12.f8992g
            if (r2 == 0) goto Lde
            int[] r5 = com.jingdong.app.mall.home.deploy.view.layout.seckillNewcomer2x4.NewcomerSku.q
        Lde:
            if (r2 == 0) goto Le1
            goto Le5
        Le1:
            java.lang.String r3 = r13.K0()
        Le5:
            r1.e(r5, r3)
            r1.l(r0, r6)
            int[] r13 = r13.I0()
            r1.q(r13, r4)
            if (r7 != 0) goto Lf5
            r7 = r14
        Lf5:
            r1.m(r7)
            r1.f(r6)
            com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel r13 = r12.f8996k
            com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel$Info r14 = r12.f8997l
            r13.f(r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.deploy.view.layout.seckillNewcomer2x4.NewcomerSku.e(com.jingdong.app.mall.home.deploy.view.layout.seckillNewcomer2x4.DSeckillNewcomer2x4Model, com.jingdong.common.entity.Product):void");
    }

    public void b(final DSeckillNewcomer2x4Model dSeckillNewcomer2x4Model, Product product, int i2) {
        f.c(this.f8994i, this.f8995j);
        f.c(this.f8996k, this.f8998m);
        f.c(this.f8999n, this.o);
        if (product == null) {
            return;
        }
        this.f8993h = i2;
        d(product);
        e(dSeckillNewcomer2x4Model, product);
        c(dSeckillNewcomer2x4Model, product);
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckillNewcomer2x4.NewcomerSku.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dSeckillNewcomer2x4Model.Z0(NewcomerSku.this.f8993h, NewcomerSku.this);
            }
        });
    }
}
