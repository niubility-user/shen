package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.graphics.Rect;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.floor.base.BaseCaFloor;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.g.j;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\n\u001a\u00020\u00078\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0016\u0010\u000e\u001a\u00020\u000b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0016\u0010\u0012\u001a\u00020\u000f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0014\u001a\u00020\u000f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0011\u00a8\u0006\u001b"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaFootFloor;", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaFloor;", "Lcom/jingdong/app/mall/home/n/g/j;", CartConstant.KEY_VENDOR_ITEM, "", "C", "(Lcom/jingdong/app/mall/home/n/g/j;)V", "Landroid/widget/TextView;", "o", "Landroid/widget/TextView;", "mFooterText", "Landroid/widget/ImageView;", "m", "Landroid/widget/ImageView;", "mImgView", "Lcom/jingdong/app/mall/home/floor/common/f;", PersonalConstants.ICON_STYLE_N, "Lcom/jingdong/app/mall/home/floor/common/f;", "mImgSize", "p", "mFooterSize", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class CaFootFloor extends BaseCaFloor<j> {

    /* renamed from: m  reason: collision with root package name and from kotlin metadata */
    private final ImageView mImgView;

    /* renamed from: n  reason: collision with root package name and from kotlin metadata */
    private final f mImgSize;

    /* renamed from: o  reason: from kotlin metadata */
    private final TextView mFooterText;

    /* renamed from: p  reason: from kotlin metadata */
    private final f mFooterSize;

    public CaFootFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
        ImageView imageView = new ImageView(context);
        this.mImgView = imageView;
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setVisibility(0);
        imageView.setImageResource(R.drawable.recommend_footer_joy);
        f fVar = new f(140, 82);
        this.mImgSize = fVar;
        fVar.F(new Rect(0, 24, 0, 0));
        RelativeLayout.LayoutParams u = fVar.u(imageView);
        u.addRule(14);
        addView(imageView, u);
        TextView textView = new TextView(context);
        this.mFooterText = textView;
        textView.setText("\u6211\u662f\u6709\u5e95\u7ebf\u7684~");
        textView.setGravity(17);
        f fVar2 = new f(200, -2);
        this.mFooterSize = fVar2;
        fVar2.F(new Rect(0, 114, 0, 0));
        RelativeLayout.LayoutParams u2 = fVar2.u(textView);
        u2.addRule(14);
        addView(textView, u2);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /* renamed from: C  reason: merged with bridge method [inline-methods] */
    public void j(@NotNull j item) {
        this.mFooterText.setTextColor(-4210753);
        this.mFooterText.setTextSize(0, d.d(20));
        f.c(this.mImgView, this.mImgSize);
        f.c(this.mFooterText, this.mFooterSize);
    }
}
