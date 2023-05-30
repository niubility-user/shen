package com.jingdong.app.mall.home.category.floor.floorsub;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.n.g.u.c;
import com.jingdong.app.mall.home.n.g.x.a;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import kotlin.TypeCastException;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\n\u001a\u00020\u00078\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0016\u0010\f\u001a\u00020\u00078\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\tR\u0016\u0010\u0010\u001a\u00020\r8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0014\u001a\u00020\u00118\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0016\u001a\u00020\u00078\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\tR\u0016\u0010\u0018\u001a\u00020\r8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u000fR\u0016\u0010\u001a\u001a\u00020\u00078\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\tR\u0016\u0010\u001c\u001a\u00020\u00118\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0013\u00a8\u0006!"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/floorsub/CaBannerIconSubFloor;", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaRecycleItem;", "Lcom/jingdong/app/mall/home/n/g/x/a;", CartConstant.KEY_VENDOR_ITEM, "", PersonalConstants.ICON_STYLE_N, "(Lcom/jingdong/app/mall/home/n/g/x/a;)V", "Lcom/jingdong/app/mall/home/floor/common/f;", "u", "Lcom/jingdong/app/mall/home/floor/common/f;", "mSizeImg", "v", "mSizeIcon", "Lcom/facebook/drawee/view/SimpleDraweeView;", "q", "Lcom/facebook/drawee/view/SimpleDraweeView;", "mSdvImg", "Lcom/jingdong/app/mall/home/floor/view/view/GradientTextView;", "s", "Lcom/jingdong/app/mall/home/floor/view/view/GradientTextView;", "mTvTitle", JshopConst.JSHOP_PROMOTIO_W, "mSizeTitle", "r", "mSdvIcon", JshopConst.JSHOP_PROMOTIO_X, "mSizeSubTitle", "t", "mTvSubTitle", "Landroid/content/Context;", AnnoConst.Constructor_Context, "<init>", "(Landroid/content/Context;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class CaBannerIconSubFloor extends BaseCaRecycleItem<a> {

    /* renamed from: q  reason: from kotlin metadata */
    private final SimpleDraweeView mSdvImg;

    /* renamed from: r  reason: from kotlin metadata */
    private final SimpleDraweeView mSdvIcon;

    /* renamed from: s  reason: from kotlin metadata */
    private final GradientTextView mTvTitle;

    /* renamed from: t  reason: from kotlin metadata */
    private final GradientTextView mTvSubTitle;

    /* renamed from: u  reason: from kotlin metadata */
    private final f mSizeImg;

    /* renamed from: v  reason: from kotlin metadata */
    private final f mSizeIcon;

    /* renamed from: w  reason: from kotlin metadata */
    private final f mSizeTitle;

    /* renamed from: x  reason: from kotlin metadata */
    private final f mSizeSubTitle;

    public CaBannerIconSubFloor(@NotNull Context context) {
        super(context);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        this.mSdvImg = simpleDraweeView;
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        simpleDraweeView.setId(R.id.mallfloor_item1);
        f fVar = new f(90, 90);
        this.mSizeImg = fVar;
        fVar.f9282f = new Rect(0, 24, 0, 0);
        RelativeLayout.LayoutParams u = fVar.u(simpleDraweeView);
        u.addRule(14);
        addView(simpleDraweeView, u);
        GradientTextView gradientTextView = new GradientTextView(context);
        this.mTvTitle = gradientTextView;
        gradientTextView.setMaxLines(1);
        gradientTextView.setGravity(17);
        f fVar2 = new f(-1, 54);
        this.mSizeTitle = fVar2;
        RelativeLayout.LayoutParams u2 = fVar2.u(gradientTextView);
        u2.addRule(3, simpleDraweeView.getId());
        addView(gradientTextView, u2);
        GradientTextView gradientTextView2 = new GradientTextView(context);
        this.mTvSubTitle = gradientTextView2;
        gradientTextView2.setMaxLines(1);
        gradientTextView2.setGravity(17);
        f fVar3 = new f(-1, 68);
        this.mSizeSubTitle = fVar3;
        RelativeLayout.LayoutParams u3 = fVar3.u(gradientTextView2);
        u3.addRule(12);
        addView(gradientTextView2, u3);
        SimpleDraweeView simpleDraweeView2 = new SimpleDraweeView(context);
        this.mSdvIcon = simpleDraweeView2;
        simpleDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar4 = new f(74, 28);
        this.mSizeIcon = fVar4;
        fVar4.E(d.i(fVar.v() >> 1), 0, 0, 0);
        RelativeLayout.LayoutParams u4 = fVar4.u(simpleDraweeView2);
        u4.addRule(6, simpleDraweeView.getId());
        u4.addRule(5, simpleDraweeView.getId());
        addView(simpleDraweeView2, u4);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public void e(@NotNull a item) {
        boolean z;
        if (item.g() instanceof com.jingdong.app.mall.home.n.g.a) {
            c g2 = item.g();
            if (g2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.jingdong.app.mall.home.category.model.CaBannerIconModel");
            }
            z = ((com.jingdong.app.mall.home.n.g.a) g2).Q();
        } else {
            z = false;
        }
        this.mTvSubTitle.setVisibility(z ? 0 : 4);
        this.mTvSubTitle.setText(item.w());
        this.mTvSubTitle.setMaxWidth(d.d(140));
        GradientTextView gradientTextView = this.mTvSubTitle;
        GradientTextView.GradientType gradientType = GradientTextView.GradientType.LeftToRight;
        gradientTextView.setTextGradient(gradientType, item.x());
        this.mTvSubTitle.setTextSize(0, d.d(20));
        this.mTvTitle.setText(item.y());
        this.mTvTitle.setMaxWidth(d.d(130));
        this.mTvTitle.setTextGradient(gradientType, item.z());
        this.mTvTitle.setTextSize(0, d.d(24));
        e.m(this.mSdvImg, item.u(), e.b);
        if (TextUtils.isEmpty(item.v())) {
            this.mSdvIcon.setVisibility(4);
        } else {
            this.mSdvIcon.setVisibility(0);
            e.m(this.mSdvIcon, item.v(), e.b);
        }
        f.c(this.mSdvImg, this.mSizeImg);
        f.c(this.mTvSubTitle, this.mSizeSubTitle);
        f.c(this.mTvTitle, this.mSizeTitle);
        f.c(this.mSdvIcon, this.mSizeIcon);
    }
}
