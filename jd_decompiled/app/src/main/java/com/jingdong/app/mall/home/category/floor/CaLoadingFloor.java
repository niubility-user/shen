package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.floor.base.BaseCaFloor;
import com.jingdong.app.mall.home.category.widget.CaLoadingView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.g.n;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010H\u001a\u00020G\u0012\u0006\u0010J\u001a\u00020I\u00a2\u0006\u0004\bK\u0010LJ\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ'\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00022\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eH\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0013\u0010\bJ\r\u0010\u0014\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0014\u0010\nR\u0016\u0010\u0016\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0013R\u0016\u0010\u001a\u001a\u00020\u00178\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001e\u001a\u00020\u001b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010 \u001a\u00020\u001b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001dR\u0016\u0010$\u001a\u00020!8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010(\u001a\u00020%8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010,\u001a\u00020)8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0016\u00100\u001a\u00020-8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00102\u001a\u00020\u00178\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b1\u0010\u0019R\u0016\u00104\u001a\u00020\u00178\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u0010\u0019R\u0016\u00106\u001a\u00020!8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u0010#R$\u0010:\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u00038\u0006@BX\u0086\u000e\u00a2\u0006\f\n\u0004\b\f\u0010\u0013\u001a\u0004\b8\u00109R\u0016\u0010<\u001a\u00020\u00178\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010\u0019R\u0016\u0010>\u001a\u00020!8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010#R\u0016\u0010@\u001a\u00020!8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010#R\u0016\u0010B\u001a\u00020!8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010#R\u0016\u0010D\u001a\u00020!8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010#R\u0016\u0010F\u001a\u00020\u00178\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010\u0019\u00a8\u0006M"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaLoadingFloor;", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaFloor;", "Lcom/jingdong/app/mall/home/n/g/n;", "", XView2Constants.STATE, "feedsCount", "", "J", "(II)V", "E", "()V", CartConstant.KEY_VENDOR_ITEM, AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "(Lcom/jingdong/app/mall/home/n/g/n;)V", "", "", "payloads", DYConstants.LETTER_H, "(Lcom/jingdong/app/mall/home/n/g/n;Ljava/util/List;)V", "I", "F", "p", "mPreWidth", "Landroid/widget/TextView;", "r", "Landroid/widget/TextView;", "mTvLoading", "Landroid/widget/ImageView;", "t", "Landroid/widget/ImageView;", "mNoneImage", JshopConst.JSHOP_PROMOTIO_X, "mEmptyImage", "Lcom/jingdong/app/mall/home/floor/common/f;", "o", "Lcom/jingdong/app/mall/home/floor/common/f;", "mWcSize", "Lcom/jingdong/app/mall/home/category/widget/CaLoadingView;", "C", "Lcom/jingdong/app/mall/home/category/widget/CaLoadingView;", "mLoadingView", "Lcom/jingdong/common/ui/JDProgressBar;", "m", "Lcom/jingdong/common/ui/JDProgressBar;", "mProgressBar", "Landroid/widget/LinearLayout;", "q", "Landroid/widget/LinearLayout;", "mLoadingLayout", "s", "mTvError", "B", "mHeightView", "A", "mEmptyTvSize", "<set-?>", "G", "()I", "loadingState", "z", "mEmptyTv", PersonalConstants.ICON_STYLE_N, "mProgressSize", JshopConst.JSHOP_PROMOTIO_W, "mNoneTvSize", "u", "mNoneImgSize", JshopConst.JSHOP_PROMOTIO_Y, "mEmptyImgSize", "v", "mTvNone", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class CaLoadingFloor extends BaseCaFloor<n> {

    /* renamed from: A  reason: from kotlin metadata */
    private final f mEmptyTvSize;

    /* renamed from: B  reason: from kotlin metadata */
    private final TextView mHeightView;

    /* renamed from: C  reason: from kotlin metadata */
    private final CaLoadingView mLoadingView;

    /* renamed from: D  reason: from kotlin metadata */
    private int loadingState;

    /* renamed from: m  reason: collision with root package name and from kotlin metadata */
    private final JDProgressBar mProgressBar;

    /* renamed from: n  reason: collision with root package name and from kotlin metadata */
    private final f mProgressSize;

    /* renamed from: o  reason: from kotlin metadata */
    private final f mWcSize;

    /* renamed from: p  reason: from kotlin metadata */
    private int mPreWidth;

    /* renamed from: q  reason: from kotlin metadata */
    private final LinearLayout mLoadingLayout;

    /* renamed from: r  reason: from kotlin metadata */
    private final TextView mTvLoading;

    /* renamed from: s  reason: from kotlin metadata */
    private final TextView mTvError;

    /* renamed from: t  reason: from kotlin metadata */
    private final ImageView mNoneImage;

    /* renamed from: u  reason: from kotlin metadata */
    private final f mNoneImgSize;

    /* renamed from: v  reason: from kotlin metadata */
    private final TextView mTvNone;

    /* renamed from: w  reason: from kotlin metadata */
    private final f mNoneTvSize;

    /* renamed from: x  reason: from kotlin metadata */
    private final ImageView mEmptyImage;

    /* renamed from: y  reason: from kotlin metadata */
    private final f mEmptyImgSize;

    /* renamed from: z  reason: from kotlin metadata */
    private final TextView mEmptyTv;

    /* loaded from: classes4.dex */
    static final class a implements View.OnClickListener {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CaAdapter f8659h;

        a(CaAdapter caAdapter) {
            this.f8659h = caAdapter;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            n n2 = CaLoadingFloor.this.n();
            if (n2 == null || n2.G() != 1) {
                return;
            }
            CaLoadingFloor.this.I(0, -1);
            this.f8659h.t();
        }
    }

    /* loaded from: classes4.dex */
    public static final class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f8661h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f8662i;

        b(int i2, int i3) {
            this.f8661h = i2;
            this.f8662i = i3;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            CaLoadingFloor.this.J(this.f8661h, this.f8662i);
        }
    }

    public CaLoadingFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
        f fVar = new f(-2, -2);
        this.mWcSize = fVar;
        TextView textView = new TextView(context);
        this.mHeightView = textView;
        addView(textView);
        CaLoadingView caLoadingView = new CaLoadingView(context, new com.jingdong.app.mall.home.n.a[]{com.jingdong.app.mall.home.n.a.C_FEEDS_SKU});
        this.mLoadingView = caLoadingView;
        caLoadingView.setVisibility(8);
        addView(caLoadingView, new RelativeLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(context);
        this.mLoadingLayout = linearLayout;
        linearLayout.setGravity(16);
        linearLayout.setOrientation(0);
        JDProgressBar jDProgressBar = new JDProgressBar(context);
        this.mProgressBar = jDProgressBar;
        f fVar2 = new f(48, 48);
        this.mProgressSize = fVar2;
        linearLayout.addView(jDProgressBar, fVar2.i(jDProgressBar));
        TextView textView2 = new TextView(context);
        this.mTvLoading = textView2;
        LinearLayout.LayoutParams i2 = fVar.i(textView2);
        textView2.setText("\u52a0\u8f7d\u4e2d...");
        textView2.setTextColor(-8092023);
        i2.leftMargin = d.d(12);
        i2.gravity = 16;
        linearLayout.addView(textView2, i2);
        RelativeLayout.LayoutParams u = fVar.u(linearLayout);
        u.addRule(13);
        addView(linearLayout, u);
        TextView textView3 = new TextView(context);
        this.mTvError = textView3;
        textView3.setVisibility(8);
        textView3.setText("\u7f51\u7edc\u4e0d\u7ed9\u529b\u54e6\uff0c\u8bf7\u91cd\u8bd5\uff01");
        textView3.setTextColor(-10066330);
        RelativeLayout.LayoutParams u2 = fVar.u(textView3);
        u2.addRule(13);
        addView(textView3, u2);
        ImageView imageView = new ImageView(getContext());
        this.mEmptyImage = imageView;
        imageView.setId(R.id.mallfloor_item1);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setVisibility(8);
        int i3 = R.drawable.recommend_footer_joy;
        imageView.setImageResource(i3);
        f fVar3 = new f(160, 100);
        this.mEmptyImgSize = fVar3;
        fVar3.F(new Rect(0, 92, 0, 0));
        RelativeLayout.LayoutParams u3 = fVar3.u(imageView);
        u3.addRule(14);
        addView(imageView, u3);
        TextView textView4 = new TextView(context);
        this.mEmptyTv = textView4;
        textView4.setVisibility(8);
        textView4.setText("\u62b1\u6b49\uff0c\u6ca1\u6709\u627e\u5230\u5546\u54c1\u54e6~");
        textView4.setTextColor(-10066330);
        f fVar4 = new f(-2, 36);
        this.mEmptyTvSize = fVar4;
        RelativeLayout.LayoutParams u4 = fVar4.u(textView4);
        u4.addRule(3, imageView.getId());
        u4.addRule(14);
        addView(textView4, u4);
        ImageView imageView2 = new ImageView(getContext());
        this.mNoneImage = imageView2;
        imageView2.setId(R.id.mallfloor_item2);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView2.setVisibility(8);
        imageView2.setImageResource(i3);
        f fVar5 = new f(160, 100);
        this.mNoneImgSize = fVar5;
        fVar5.F(new Rect(0, 5, 0, 0));
        RelativeLayout.LayoutParams u5 = fVar5.u(imageView2);
        u5.addRule(14);
        addView(imageView2, u5);
        TextView textView5 = new TextView(context);
        this.mTvNone = textView5;
        textView5.setVisibility(8);
        textView5.setText("\u6211\u662f\u6709\u5e95\u7ebf\u7684~");
        textView5.setTextColor(-10066330);
        f fVar6 = new f(-2, 36);
        this.mNoneTvSize = fVar6;
        RelativeLayout.LayoutParams u6 = fVar6.u(textView5);
        u6.addRule(13);
        u6.addRule(3, imageView2.getId());
        addView(textView5, u6);
        setOnClickListener(new a(caAdapter));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void E() {
        /*
            Method dump skipped, instructions count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.category.floor.CaLoadingFloor.E():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void J(int state, int feedsCount) {
        n n2;
        int i2 = this.loadingState;
        if (state == i2) {
            return;
        }
        if (feedsCount == 0 && state == 0 && i2 == 3) {
            return;
        }
        if (feedsCount == 0 && state == 0 && i2 == 4) {
            return;
        }
        this.loadingState = state;
        if (n() != null && (n2 = n()) != null) {
            n2.H(state);
        }
        E();
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /* renamed from: D  reason: merged with bridge method [inline-methods] */
    public void j(@NotNull n item) {
        this.mTvLoading.setTextColor(-8092023);
        this.mTvError.setTextColor(-10066330);
        this.mEmptyTv.setTextColor(-10066330);
        this.mTvNone.setTextColor(-10066330);
        item.H(this.loadingState);
        E();
    }

    public final void F() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null || layoutParams.height == p()) {
            return;
        }
        setLayoutParams(layoutParams);
    }

    /* renamed from: G  reason: from getter */
    public final int getLoadingState() {
        return this.loadingState;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /* renamed from: H  reason: merged with bridge method [inline-methods] */
    public void y(@NotNull n item, @Nullable List<? extends Object> payloads) {
        item.H(this.loadingState);
        E();
    }

    public final void I(int state, int feedsCount) {
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new b(state, feedsCount));
        } else {
            J(state, feedsCount);
        }
    }
}
