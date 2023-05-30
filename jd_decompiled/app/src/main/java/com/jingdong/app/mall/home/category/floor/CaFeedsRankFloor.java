package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.floor.base.BaseCaFeeds;
import com.jingdong.app.mall.home.category.floor.decoration.CaFloatDecoration;
import com.jingdong.app.mall.home.category.floor.feedssub.FeedsRankContent;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.g.g;
import com.jingdong.app.mall.home.n.h.b;
import com.jingdong.app.mall.home.n.h.d;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010!\u001a\u00020 \u0012\u0006\u0010#\u001a\u00020\"\u00a2\u0006\u0004\b$\u0010%J\u0017\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\f\u0010\rR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001c\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001f\u001a\u00020\u001d8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u001e\u00a8\u0006&"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaFeedsRankFloor;", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaFeeds;", "Lcom/jingdong/app/mall/home/n/g/g;", "", "id", "Q", "(I)I", "below", "", IShareAdapter.SHARE_ACTION_PANE, "(I)V", CartConstant.KEY_VENDOR_ITEM, "R", "(Lcom/jingdong/app/mall/home/n/g/g;)V", "Lcom/jingdong/app/mall/home/state/dark/DarkWhiteBgImageView;", "s", "Lcom/jingdong/app/mall/home/state/dark/DarkWhiteBgImageView;", "mSkuImg", "Lcom/jingdong/app/mall/home/floor/common/f;", "t", "Lcom/jingdong/app/mall/home/floor/common/f;", "mSkuSize", "Lcom/jingdong/app/mall/home/category/floor/feedssub/FeedsRankContent;", "u", "Lcom/jingdong/app/mall/home/category/floor/feedssub/FeedsRankContent;", "mRankContent", "o", "()I", "floorBgColor", "", "()Z", "isFloorDisplay", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public class CaFeedsRankFloor extends BaseCaFeeds<g> {

    /* renamed from: s  reason: from kotlin metadata */
    private DarkWhiteBgImageView mSkuImg;

    /* renamed from: t  reason: from kotlin metadata */
    private f mSkuSize;

    /* renamed from: u  reason: from kotlin metadata */
    private FeedsRankContent mRankContent;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ g f8651g;

        a(g gVar) {
            this.f8651g = gVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View v) {
            JumpEntity jump = this.f8651g.getJump();
            if (jump != null) {
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                b.a(v.getContext(), jump);
                this.f8651g.e().B(this.f8651g.h());
            }
        }
    }

    public CaFeedsRankFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
        int i2 = R.id.mallfloor_item1;
        Q(i2);
        P(i2);
    }

    private final void P(int below) {
        this.mRankContent = new FeedsRankContent(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, below);
        addView(this.mRankContent, layoutParams);
    }

    private final int Q(int id) {
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

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /* renamed from: R  reason: merged with bridge method [inline-methods] */
    public void j(@NotNull g item) {
        f.c(this.mSkuImg, this.mSkuSize);
        d.a(this.mSkuImg, item.D());
        FeedsRankContent feedsRankContent = this.mRankContent;
        if (feedsRankContent == null) {
            Intrinsics.throwNpe();
        }
        feedsRankContent.a(item);
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
