package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.floor.base.BaseCaEventFloor;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.n.g.c;
import com.jingdong.app.mall.home.n.h.b;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\n\u001a\u00020\u00078\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\u0011"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaBannerCardFloor;", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaEventFloor;", "Lcom/jingdong/app/mall/home/n/g/c;", CartConstant.KEY_VENDOR_ITEM, "", IShareAdapter.SHARE_ACTION_PANE, "(Lcom/jingdong/app/mall/home/n/g/c;)V", "Lcom/facebook/drawee/view/SimpleDraweeView;", "s", "Lcom/facebook/drawee/view/SimpleDraweeView;", "mBannerImg", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class CaBannerCardFloor extends BaseCaEventFloor<c> {

    /* renamed from: s  reason: from kotlin metadata */
    private final SimpleDraweeView mBannerImg;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ c f8650g;

        a(c cVar) {
            this.f8650g = cVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View v) {
            JumpEntity H = this.f8650g.H();
            if (H != null) {
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                b.b(v.getContext(), H, this.f8650g.e());
            }
        }
    }

    public CaBannerCardFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        this.mBannerImg = simpleDraweeView;
        simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(simpleDraweeView, new RelativeLayout.LayoutParams(-1, -1));
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /* renamed from: P  reason: merged with bridge method [inline-methods] */
    public void j(@NotNull c item) {
        setOnClickListener(new a(item));
        e.d(this.mBannerImg, d.d(24));
        com.jingdong.app.mall.home.floor.ctrl.e.m(this.mBannerImg, item.G(), com.jingdong.app.mall.home.floor.ctrl.e.w());
    }
}
