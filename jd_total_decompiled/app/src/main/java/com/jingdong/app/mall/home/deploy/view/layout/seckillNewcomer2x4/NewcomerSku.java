package com.jingdong.app.mall.home.deploy.view.layout.seckillNewcomer2x4;

import android.content.Context;
import android.graphics.Rect;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
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
import com.jingdong.common.search.view.PriceHelper;
import com.jingdong.common.utils.StringUtil;
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
    */
    private void e(DSeckillNewcomer2x4Model dSeckillNewcomer2x4Model, Product product) {
        String str;
        String string = StringUtil.getString(R.string.product_entity_no_price);
        String miaoShaPrice = product.getMiaoShaPrice();
        String jsonString = b.getJsonString(product.prdObject, "finalPrice", "");
        if (!TextUtils.isEmpty(jsonString) && !TextUtils.equals(jsonString, string)) {
            jsonString = a.e(jsonString);
        }
        if (TextUtils.isEmpty(jsonString) && !TextUtils.isEmpty(miaoShaPrice) && !TextUtils.equals(miaoShaPrice, string)) {
            jsonString = a.e(miaoShaPrice);
        }
        if (TextUtils.isEmpty(jsonString)) {
            jsonString = "";
        } else if (jsonString.length() < 6) {
            String str2 = jsonString + "\u9884\u4f30\u4ef7";
            SpannableString spannableString = new SpannableString(a.e(str2));
            spannableString.setSpan(new AbsoluteSizeSpan(d.d(24)), 0, jsonString.length(), 18);
            spannableString.setSpan(new AbsoluteSizeSpan(d.d(22)), jsonString.length(), str2.length(), 18);
            spannableString.setSpan(new com.jingdong.app.mall.home.category.floor.base.d(-d.d(1), d.d(2)), jsonString.length(), str2.length(), 18);
            jsonString = str2;
            str = spannableString;
            if (TextUtils.isEmpty(jsonString)) {
                if (!TextUtils.equals(miaoShaPrice, string)) {
                    miaoShaPrice = a.e(miaoShaPrice);
                }
                jsonString = miaoShaPrice;
            }
            int i2 = (!TextUtils.isEmpty(jsonString) || jsonString.length() < 8) ? 16 : 8;
            Rect rect = new Rect(i2, -3, i2, -3);
            SkuLabel.Info info = this.f8997l;
            boolean z = this.f8992g;
            info.e(z ? q : null, z ? "" : dSeckillNewcomer2x4Model.K0());
            info.l(rect, 0);
            info.q(dSeckillNewcomer2x4Model.I0(), 24);
            String str3 = str;
            if (str == null) {
                str3 = jsonString;
            }
            info.m(str3);
            info.f(false);
            this.f8996k.f(this.f8997l);
        }
        str = null;
        if (TextUtils.isEmpty(jsonString)) {
        }
        if (TextUtils.isEmpty(jsonString)) {
        }
        Rect rect2 = new Rect(i2, -3, i2, -3);
        SkuLabel.Info info2 = this.f8997l;
        boolean z2 = this.f8992g;
        info2.e(z2 ? q : null, z2 ? "" : dSeckillNewcomer2x4Model.K0());
        info2.l(rect2, 0);
        info2.q(dSeckillNewcomer2x4Model.I0(), 24);
        String str32 = str;
        if (str == null) {
        }
        info2.m(str32);
        info2.f(false);
        this.f8996k.f(this.f8997l);
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
