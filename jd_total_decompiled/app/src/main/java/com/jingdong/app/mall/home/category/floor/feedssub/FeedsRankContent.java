package com.jingdong.app.mall.home.category.floor.feedssub;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.n.g.g;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class FeedsRankContent extends RelativeLayout {

    /* renamed from: m  reason: collision with root package name */
    public static JDDisplayImageOptions f8691m;

    /* renamed from: n  reason: collision with root package name */
    public static JDDisplayImageOptions f8692n;

    /* renamed from: g  reason: collision with root package name */
    private SimpleDraweeView f8693g;

    /* renamed from: h  reason: collision with root package name */
    private SimpleDraweeView f8694h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f8695i;

    /* renamed from: j  reason: collision with root package name */
    private GradientTextView f8696j;

    /* renamed from: k  reason: collision with root package name */
    private f f8697k;

    /* renamed from: l  reason: collision with root package name */
    private f f8698l;

    static {
        JDDisplayImageOptions showImageOnLoading = com.jingdong.app.mall.home.floor.ctrl.f.a().resetViewBeforeLoading(false).showImageOnLoading(e.b);
        int i2 = R.drawable.home_webp_empty;
        f8691m = showImageOnLoading.showImageOnFail(i2).showImageForEmptyUri(i2);
        f8692n = com.jingdong.app.mall.home.floor.ctrl.f.a().resetViewBeforeLoading(false).showImageOnLoading(e.b).showImageOnFail(i2).showImageForEmptyUri(i2);
    }

    public FeedsRankContent(Context context) {
        super(context);
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.f8693g = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar = new f(-1, R2.anim.mtrl_bottom_sheet_slide_in);
        this.f8697k = fVar;
        addView(this.f8693g, 0, fVar.u(this.f8693g));
        HomeDraweeView homeDraweeView2 = new HomeDraweeView(context);
        this.f8694h = homeDraweeView2;
        homeDraweeView2.setId(R.id.mallfloor_item2);
        this.f8694h.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar2 = new f(160, 32);
        this.f8698l = fVar2;
        RelativeLayout.LayoutParams u = fVar2.u(this.f8694h);
        u.topMargin = d.d(16);
        u.leftMargin = d.d(24);
        addView(this.f8694h, u);
        TextView textView = new TextView(context);
        this.f8695i = textView;
        textView.setId(R.id.mallfloor_item3);
        this.f8695i.setMaxLines(1);
        this.f8695i.setTextColor(-15066598);
        this.f8695i.setGravity(17);
        this.f8695i.setMaxWidth(d.d(308));
        this.f8695i.setEllipsize(TextUtils.TruncateAt.END);
        this.f8695i.setTypeface(FontsUtil.getTypeFace(context, 4097));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, d.d(40));
        layoutParams.topMargin = d.d(12);
        layoutParams.leftMargin = d.d(24);
        layoutParams.addRule(3, this.f8694h.getId());
        addView(this.f8695i, layoutParams);
        GradientTextView gradientTextView = new GradientTextView(context);
        this.f8696j = gradientTextView;
        gradientTextView.setMaxLines(1);
        this.f8696j.setMaxWidth(d.d(308));
        this.f8696j.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.leftMargin = d.d(24);
        layoutParams2.addRule(3, this.f8695i.getId());
        addView(this.f8696j, layoutParams2);
    }

    public void a(g gVar) {
        f.c(this.f8693g, this.f8697k);
        f.c(this.f8694h, this.f8698l);
        if (TextUtils.isEmpty(gVar.I())) {
            e.a(this.f8693g, "2628");
        } else {
            e.f(gVar.I(), this.f8693g, f8691m);
        }
        if (TextUtils.isEmpty(gVar.K())) {
            e.a(this.f8694h, "2629");
        } else {
            e.f(gVar.K(), this.f8694h, f8692n);
        }
        this.f8695i.setText(gVar.Q());
        this.f8696j.setText(gVar.O());
        this.f8695i.setTextSize(0, d.d(28));
        this.f8696j.setTextSize(0, d.d(24));
        this.f8696j.setTextGradient(GradientTextView.GradientType.LeftToRight, gVar.J());
    }
}
