package com.jingdong.app.mall.home.category.floor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.floor.base.BaseCaFeeds;
import com.jingdong.app.mall.home.category.floor.decoration.CaFloatDecoration;
import com.jingdong.app.mall.home.category.floor.feedssub.FeedsEvaluate;
import com.jingdong.app.mall.home.category.floor.feedssub.FeedsPlusPrice;
import com.jingdong.app.mall.home.category.floor.feedssub.FeedsPriceContent;
import com.jingdong.app.mall.home.category.floor.feedssub.FeedsReasonContent;
import com.jingdong.app.mall.home.category.floor.feedssub.FeedsTitle;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.g.g;
import com.jingdong.app.mall.home.n.h.d;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.unification.translation.JDTransitionManager;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010C\u001a\u00020B\u0012\u0006\u0010E\u001a\u00020D\u00a2\u0006\u0004\bF\u0010GJ\u0017\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\b\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\t\u0010\u0006J\u0017\u0010\n\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\n\u0010\u0006J\u0017\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u000b\u0010\u0006J\u0017\u0010\f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\f\u0010\u0006J\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0012R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0018\u0010#\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0018\u0010'\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010+\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010.\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b,\u0010-R\u0016\u00102\u001a\u00020/8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b0\u00101R\u0018\u00104\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u0010&R\u0018\u00108\u001a\u0004\u0018\u0001058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0018\u0010;\u001a\u0004\u0018\u0001098\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u0010:R\u0018\u0010=\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010\u0016R\u0018\u0010A\u001a\u0004\u0018\u00010>8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u0010@\u00a8\u0006H"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaFeedsSkuFloor;", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaFeeds;", "Lcom/jingdong/app/mall/home/n/g/g;", "", "id", "Y", "(I)I", "below", "Z", "X", "W", "V", "U", "", "T", "(I)V", CartConstant.KEY_VENDOR_ITEM, "S", "(Lcom/jingdong/app/mall/home/n/g/g;)V", "a0", "Landroid/view/View;", "A", "Landroid/view/View;", "mBottomView", "Lcom/jingdong/app/mall/home/category/floor/feedssub/FeedsEvaluate;", "z", "Lcom/jingdong/app/mall/home/category/floor/feedssub/FeedsEvaluate;", "mEvaluate", "Lcom/jingdong/app/mall/home/category/floor/feedssub/FeedsTitle;", "v", "Lcom/jingdong/app/mall/home/category/floor/feedssub/FeedsTitle;", "mTitle", "Lcom/jingdong/app/mall/home/category/floor/feedssub/FeedsReasonContent;", JshopConst.JSHOP_PROMOTIO_W, "Lcom/jingdong/app/mall/home/category/floor/feedssub/FeedsReasonContent;", "mReasonContent", "Lcom/jingdong/app/mall/home/floor/common/f;", "B", "Lcom/jingdong/app/mall/home/floor/common/f;", "mBottomSize", "Lcom/jingdong/app/mall/home/category/floor/feedssub/FeedsPriceContent;", JshopConst.JSHOP_PROMOTIO_X, "Lcom/jingdong/app/mall/home/category/floor/feedssub/FeedsPriceContent;", "mPriceContent", "o", "()I", "floorBgColor", "", "u", "()Z", "isFloorDisplay", "t", "mSkuSize", "Lcom/jingdong/app/mall/home/state/dark/DarkWhiteBgImageView;", "s", "Lcom/jingdong/app/mall/home/state/dark/DarkWhiteBgImageView;", "mSkuImg", "Landroid/widget/ImageView;", "Landroid/widget/ImageView;", "mCloseView", "C", "mAdLabel", "Lcom/jingdong/app/mall/home/category/floor/feedssub/FeedsPlusPrice;", JshopConst.JSHOP_PROMOTIO_Y, "Lcom/jingdong/app/mall/home/category/floor/feedssub/FeedsPlusPrice;", "mPlusPrice", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public class CaFeedsSkuFloor extends BaseCaFeeds<g> {
    private static long D;
    private static f E;

    /* renamed from: A  reason: from kotlin metadata */
    private View mBottomView;

    /* renamed from: B  reason: from kotlin metadata */
    private f mBottomSize;

    /* renamed from: C  reason: from kotlin metadata */
    private View mAdLabel;

    /* renamed from: s  reason: from kotlin metadata */
    private DarkWhiteBgImageView mSkuImg;

    /* renamed from: t  reason: from kotlin metadata */
    private f mSkuSize;

    /* renamed from: u  reason: from kotlin metadata */
    private ImageView mCloseView;

    /* renamed from: v  reason: from kotlin metadata */
    private FeedsTitle mTitle;

    /* renamed from: w  reason: from kotlin metadata */
    private FeedsReasonContent mReasonContent;

    /* renamed from: x  reason: from kotlin metadata */
    private FeedsPriceContent mPriceContent;

    /* renamed from: y  reason: from kotlin metadata */
    private FeedsPlusPrice mPlusPrice;

    /* renamed from: z  reason: from kotlin metadata */
    private FeedsEvaluate mEvaluate;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class a implements View.OnClickListener {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ g f8653h;

        a(g gVar) {
            this.f8653h = gVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (SystemClock.elapsedRealtime() - CaFeedsSkuFloor.D < 1000) {
                return;
            }
            this.f8653h.e().B(this.f8653h.h());
            this.f8653h.Z(false);
            CaFeedsSkuFloor.D = SystemClock.elapsedRealtime();
            Bundle build = DeeplinkProductDetailHelper.BundleBuilder.from(this.f8653h.P()).imageTitlePrice(this.f8653h.D(), this.f8653h.Q(), this.f8653h.E()).build();
            try {
                if (JDTransitionManager.appendViewDataToBundle(build, CaFeedsSkuFloor.this.mSkuImg, this.f8653h.D())) {
                    build.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                    Context context = CaFeedsSkuFloor.this.getContext();
                    com.jingdong.app.mall.home.o.a.f.n(context);
                    DeeplinkProductDetailHelper.startProductDetail(CaFeedsSkuFloor.this.getContext(), build, ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, new Pair[0]).toBundle());
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            DeeplinkProductDetailHelper.startProductDetail(CaFeedsSkuFloor.this.getContext(), build);
        }
    }

    public CaFeedsSkuFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
        int i2 = R.id.mallfloor_item1;
        Y(i2);
        T(U(V(W(X(Z(i2))))));
    }

    private final void S(g item) {
        if (item.S() != 1) {
            View view = this.mAdLabel;
            if (view != null) {
                view.setVisibility(8);
                return;
            }
            return;
        }
        int i2 = k.v() ? 5 : 1;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i2, i2);
        layoutParams.rightMargin = DPIUtil.dip2px(24.0f);
        layoutParams.bottomMargin = DPIUtil.dip2px(1.0f);
        layoutParams.addRule(12);
        layoutParams.addRule(11);
        View view2 = this.mAdLabel;
        if (view2 == null) {
            View view3 = new View(getContext());
            this.mAdLabel = view3;
            view3.setBackgroundColor(-16777216);
            addView(this.mAdLabel, layoutParams);
            return;
        }
        if (view2 == null) {
            Intrinsics.throwNpe();
        }
        view2.setLayoutParams(layoutParams);
        View view4 = this.mAdLabel;
        if (view4 == null) {
            Intrinsics.throwNpe();
        }
        view4.setVisibility(0);
    }

    private final void T(int below) {
        this.mBottomView = new View(getContext());
        f fVar = new f(1, 22);
        this.mBottomSize = fVar;
        if (fVar == null) {
            Intrinsics.throwNpe();
        }
        View view = this.mBottomView;
        if (view == null) {
            Intrinsics.throwNpe();
        }
        RelativeLayout.LayoutParams u = fVar.u(view);
        u.addRule(3, below);
        addView(this.mBottomView, u);
    }

    private final int U(int below) {
        int i2 = below + 1;
        FeedsEvaluate feedsEvaluate = new FeedsEvaluate(getContext());
        this.mEvaluate = feedsEvaluate;
        if (feedsEvaluate == null) {
            Intrinsics.throwNpe();
        }
        feedsEvaluate.setId(i2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 0);
        int dip2px = DPIUtil.dip2px(12.0f);
        layoutParams.rightMargin = dip2px;
        layoutParams.leftMargin = dip2px;
        layoutParams.addRule(3, below);
        addView(this.mEvaluate, layoutParams);
        return i2;
    }

    private final int V(int below) {
        int i2 = below + 1;
        FeedsPlusPrice feedsPlusPrice = new FeedsPlusPrice(getContext());
        this.mPlusPrice = feedsPlusPrice;
        if (feedsPlusPrice == null) {
            Intrinsics.throwNpe();
        }
        feedsPlusPrice.setId(i2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        int dip2px = DPIUtil.dip2px(12.0f);
        layoutParams.rightMargin = dip2px;
        layoutParams.leftMargin = dip2px;
        layoutParams.addRule(3, below);
        addView(this.mPlusPrice, layoutParams);
        return i2;
    }

    private final int W(int below) {
        int i2 = below + 1;
        FeedsPriceContent feedsPriceContent = new FeedsPriceContent(getContext());
        this.mPriceContent = feedsPriceContent;
        if (feedsPriceContent == null) {
            Intrinsics.throwNpe();
        }
        feedsPriceContent.setId(i2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        int dip2px = DPIUtil.dip2px(12.0f);
        layoutParams.rightMargin = dip2px;
        layoutParams.leftMargin = dip2px;
        layoutParams.addRule(3, below);
        addView(this.mPriceContent, layoutParams);
        return i2;
    }

    private final int X(int below) {
        int i2 = below + 1;
        FeedsReasonContent feedsReasonContent = new FeedsReasonContent(getContext());
        this.mReasonContent = feedsReasonContent;
        if (feedsReasonContent == null) {
            Intrinsics.throwNpe();
        }
        feedsReasonContent.setId(i2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 0);
        int dip2px = DPIUtil.dip2px(12.0f);
        layoutParams.rightMargin = dip2px;
        layoutParams.leftMargin = dip2px;
        layoutParams.addRule(3, below);
        addView(this.mReasonContent, layoutParams);
        return i2;
    }

    private final int Y(int id) {
        DarkWhiteBgImageView darkWhiteBgImageView = new DarkWhiteBgImageView(getContext());
        this.mSkuImg = darkWhiteBgImageView;
        if (darkWhiteBgImageView == null) {
            Intrinsics.throwNpe();
        }
        darkWhiteBgImageView.setId(id);
        DarkWhiteBgImageView darkWhiteBgImageView2 = this.mSkuImg;
        if (darkWhiteBgImageView2 == null) {
            Intrinsics.throwNpe();
        }
        darkWhiteBgImageView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
        DarkWhiteBgImageView darkWhiteBgImageView3 = this.mSkuImg;
        if (darkWhiteBgImageView3 == null) {
            Intrinsics.throwNpe();
        }
        GenericDraweeHierarchy hierarchy = darkWhiteBgImageView3.getHierarchy();
        Intrinsics.checkExpressionValueIsNotNull(hierarchy, "mSkuImg!!.hierarchy");
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        f fVar = new f(-1, R2.attr.actionOverflowButtonStyle);
        this.mSkuSize = fVar;
        if (fVar == null) {
            Intrinsics.throwNpe();
        }
        DarkWhiteBgImageView darkWhiteBgImageView4 = this.mSkuImg;
        if (darkWhiteBgImageView4 == null) {
            Intrinsics.throwNpe();
        }
        addView(this.mSkuImg, fVar.u(darkWhiteBgImageView4));
        return id;
    }

    private final int Z(int below) {
        int i2 = below + 1;
        FeedsTitle feedsTitle = new FeedsTitle(getContext());
        this.mTitle = feedsTitle;
        if (feedsTitle == null) {
            Intrinsics.throwNpe();
        }
        feedsTitle.setId(i2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 0);
        int dip2px = DPIUtil.dip2px(12.0f);
        layoutParams.rightMargin = dip2px;
        layoutParams.leftMargin = dip2px;
        layoutParams.topMargin = DPIUtil.dip2px(12.0f);
        layoutParams.addRule(3, below);
        addView(this.mTitle, layoutParams);
        return i2;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /* renamed from: a0  reason: merged with bridge method [inline-methods] */
    public void j(@NotNull g item) {
        f.c(this.mSkuImg, this.mSkuSize);
        f.c(this.mCloseView, E);
        f.c(this.mBottomView, this.mBottomSize);
        d.a(this.mSkuImg, item.D());
        FeedsTitle feedsTitle = this.mTitle;
        if (feedsTitle == null) {
            Intrinsics.throwNpe();
        }
        feedsTitle.a(item);
        FeedsReasonContent feedsReasonContent = this.mReasonContent;
        if (feedsReasonContent == null) {
            Intrinsics.throwNpe();
        }
        feedsReasonContent.bindData(item);
        FeedsPriceContent feedsPriceContent = this.mPriceContent;
        if (feedsPriceContent == null) {
            Intrinsics.throwNpe();
        }
        feedsPriceContent.bindData(item);
        FeedsPlusPrice feedsPlusPrice = this.mPlusPrice;
        if (feedsPlusPrice == null) {
            Intrinsics.throwNpe();
        }
        feedsPlusPrice.a(item);
        FeedsEvaluate feedsEvaluate = this.mEvaluate;
        if (feedsEvaluate == null) {
            Intrinsics.throwNpe();
        }
        feedsEvaluate.a(item);
        S(item);
        item.Z(true);
        setOnClickListener(new a(item));
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    public int o() {
        return com.jingdong.app.mall.home.state.dark.a.g(true);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    public boolean u() {
        return super.u() && getBottom() > CaFloatDecoration.f8689e;
    }
}
