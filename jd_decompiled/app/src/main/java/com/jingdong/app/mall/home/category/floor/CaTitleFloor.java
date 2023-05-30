package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.floor.base.BaseCaFloor;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.n.g.t;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.platform.business.personal.R2;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010)\u001a\u00020(\u0012\u0006\u0010+\u001a\u00020*\u00a2\u0006\u0004\b,\u0010-J\u000f\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\b\u0010\tR\u0016\u0010\r\u001a\u00020\n8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0016\u0010\u0011\u001a\u00020\u000e8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0013\u001a\u00020\n8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\fR\u0016\u0010\u0015\u001a\u00020\n8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\fR\u0016\u0010\u0019\u001a\u00020\u00168\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001d\u001a\u00020\u001a8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010!\u001a\u00020\u001e8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010#\u001a\u00020\u000e8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010\u0010R\u0016\u0010%\u001a\u00020\n8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010\fR\u0016\u0010'\u001a\u00020\n8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010\f\u00a8\u0006."}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaTitleFloor;", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaFloor;", "Lcom/jingdong/app/mall/home/n/g/t;", "", "G", "()V", "F", CartConstant.KEY_VENDOR_ITEM, "E", "(Lcom/jingdong/app/mall/home/n/g/t;)V", "Lcom/jingdong/app/mall/home/floor/common/f;", "s", "Lcom/jingdong/app/mall/home/floor/common/f;", "mSizeLeft", "Landroid/view/View;", "o", "Landroid/view/View;", "mViewLeft", "u", "mSizeTitle", "v", "mSizeSdvBg", "Lcom/facebook/drawee/view/SimpleDraweeView;", "q", "Lcom/facebook/drawee/view/SimpleDraweeView;", "mSdvBg", "Landroid/widget/ImageView;", PersonalConstants.ICON_STYLE_N, "Landroid/widget/ImageView;", "mSdvIcon", "Landroid/widget/TextView;", "m", "Landroid/widget/TextView;", "mTextView", "p", "mViewRight", "t", "mSizeRight", "r", "mSizeIcon", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class CaTitleFloor extends BaseCaFloor<t> {

    /* renamed from: m  reason: collision with root package name and from kotlin metadata */
    private final TextView mTextView;

    /* renamed from: n  reason: collision with root package name and from kotlin metadata */
    private final ImageView mSdvIcon;

    /* renamed from: o  reason: from kotlin metadata */
    private final View mViewLeft;

    /* renamed from: p  reason: from kotlin metadata */
    private final View mViewRight;

    /* renamed from: q  reason: from kotlin metadata */
    private final SimpleDraweeView mSdvBg;

    /* renamed from: r  reason: from kotlin metadata */
    private final f mSizeIcon;

    /* renamed from: s  reason: from kotlin metadata */
    private final f mSizeLeft;

    /* renamed from: t  reason: from kotlin metadata */
    private final f mSizeRight;

    /* renamed from: u  reason: from kotlin metadata */
    private final f mSizeTitle;

    /* renamed from: v  reason: from kotlin metadata */
    private final f mSizeSdvBg;

    /* loaded from: classes4.dex */
    public static final class a extends com.jingdong.app.mall.home.t.a {
        final /* synthetic */ String b;

        a(String str) {
            this.b = str;
        }

        @Override // com.jingdong.app.mall.home.t.a
        public void onBitmapWithUiNull(@NotNull Bitmap bitmap) {
            CaTitleFloor.this.G();
            CaTitleFloor.this.mSdvBg.setImageBitmap(bitmap);
            com.jingdong.app.mall.home.n.h.a.h(this.b, bitmap);
        }
    }

    public CaTitleFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
        View view = new View(context);
        this.mViewLeft = view;
        view.setBackgroundColor(-2039584);
        f fVar = new f(50, 1);
        this.mSizeLeft = fVar;
        fVar.F(new Rect(R2.anim.slide_out_to_bottom, 52, 0, 0));
        addView(view, fVar.u(view));
        ImageView imageView = new ImageView(context);
        this.mSdvIcon = imageView;
        imageView.setId(R.id.mallfloor_item1);
        e.a(imageView, "2625");
        f fVar2 = new f(42, 32);
        this.mSizeIcon = fVar2;
        fVar2.F(new Rect(288, 39, 0, 0));
        addView(imageView, fVar2.u(imageView));
        GradientTextView gradientTextView = new GradientTextView(context);
        this.mTextView = gradientTextView;
        gradientTextView.setMaxLines(1);
        gradientTextView.setTypeface(FontsUtil.getTypeFace(context, 4097));
        gradientTextView.setPadding(0, -d.d(3), 0, -d.d(3));
        gradientTextView.setEllipsize(TextUtils.TruncateAt.END);
        gradientTextView.setGravity(16);
        f fVar3 = new f(130, 32);
        this.mSizeTitle = fVar3;
        fVar3.F(new Rect(9, 0, 0, 2));
        RelativeLayout.LayoutParams u = fVar.u(gradientTextView);
        u.addRule(1, imageView.getId());
        u.addRule(8, imageView.getId());
        addView(gradientTextView, u);
        View view2 = new View(context);
        this.mViewRight = view2;
        view2.setBackgroundColor(-2039584);
        f fVar4 = new f(50, 1);
        this.mSizeRight = fVar4;
        fVar4.F(new Rect(0, 52, R2.anim.slide_out_to_bottom, 0));
        RelativeLayout.LayoutParams u2 = fVar4.u(view2);
        u2.addRule(11);
        addView(view2, u2);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        this.mSdvBg = simpleDraweeView;
        simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        f fVar5 = new f(R2.attr.decimalNumber, 70);
        this.mSizeSdvBg = fVar5;
        addView(simpleDraweeView, fVar5.u(simpleDraweeView));
    }

    private final void F() {
        c.l(false, this.mViewLeft, this.mSdvIcon, this.mTextView, this.mViewRight);
        c.l(true, this.mSdvBg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void G() {
        c.l(false, this.mSdvBg);
        c.l(true, this.mViewLeft, this.mSdvIcon, this.mTextView, this.mViewRight);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /* renamed from: E  reason: merged with bridge method [inline-methods] */
    public void j(@NotNull t item) {
        f.c(this.mViewLeft, this.mSizeLeft);
        f.c(this.mViewRight, this.mSizeRight);
        f.c(this.mSdvIcon, this.mSizeIcon);
        f.c(this.mTextView, this.mSizeTitle);
        f.c(this.mSdvBg, this.mSizeSdvBg);
        this.mTextView.setTextSize(0, d.d(30));
        com.jingdong.app.mall.home.n.g.w.e D = item.D();
        if (D == null) {
            setVisibility(4);
            return;
        }
        String l2 = D.l();
        setVisibility(0);
        c.l(true, this.mViewLeft, this.mSdvIcon, this.mTextView, this.mViewRight, this.mSdvBg);
        this.mTextView.setText(TextUtils.isEmpty(l2) ? "\u4eac\u6311\u60e0\u9009" : l2);
        this.mTextView.setTextColor(com.jingdong.app.mall.home.state.dark.a.e(-1, -13750995));
        if (TextUtils.isEmpty(l2)) {
            l2 = "\u4eac\u6311\u60e0\u9009";
        }
        setContentDescription(l2);
        String decorateBgUrl = D.getDecorateBgUrl();
        if (TextUtils.isEmpty(decorateBgUrl)) {
            decorateBgUrl = D.i();
        }
        if (decorateBgUrl != null && !TextUtils.isEmpty(decorateBgUrl) && !com.jingdong.app.mall.home.state.dark.a.h()) {
            String md5 = Md5Encrypt.md5(decorateBgUrl);
            Bitmap e2 = com.jingdong.app.mall.home.n.h.a.e(md5);
            if (e2 != null && !e2.isRecycled()) {
                G();
                this.mSdvBg.setImageBitmap(e2);
                return;
            }
            F();
            com.jingdong.app.mall.home.floor.ctrl.f.i(decorateBgUrl, new a(md5));
            return;
        }
        F();
    }
}
