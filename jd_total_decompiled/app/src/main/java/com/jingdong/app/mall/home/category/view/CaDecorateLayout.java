package com.jingdong.app.mall.home.category.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.ctrl.f;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.floor.view.widget.FitTopImage;
import com.jingdong.app.mall.home.n.e;
import com.jingdong.app.util.image.JDDisplayImageOptions;

/* loaded from: classes4.dex */
public class CaDecorateLayout extends RelativeLayout {

    /* renamed from: m  reason: collision with root package name */
    public static JDDisplayImageOptions f8729m = f.a().resetViewBeforeLoading(false);

    /* renamed from: g  reason: collision with root package name */
    private CategoryEntity.DecorateInfo f8730g;

    /* renamed from: h  reason: collision with root package name */
    private FitTopImage f8731h;

    /* renamed from: i  reason: collision with root package name */
    private com.jingdong.app.mall.home.floor.common.f f8732i;

    /* renamed from: j  reason: collision with root package name */
    private int f8733j;

    /* renamed from: k  reason: collision with root package name */
    private Paint f8734k;

    /* renamed from: l  reason: collision with root package name */
    private int f8735l;

    /* loaded from: classes4.dex */
    class a extends FitTopImage {
        a(Context context) {
            super(context);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView, android.widget.ImageView, android.view.View
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (com.jingdong.app.mall.home.state.dark.a.h()) {
                return;
            }
            canvas.drawRect(0.0f, CaDecorateLayout.this.f8733j - d.d(160), getRight(), getBottom(), CaDecorateLayout.this.f8734k);
        }
    }

    public CaDecorateLayout(Context context) {
        super(context);
        this.f8734k = new Paint(1);
        a aVar = new a(context);
        this.f8731h = aVar;
        aVar.setScaleType(ImageView.ScaleType.FIT_XY);
        this.f8732i = new com.jingdong.app.mall.home.floor.common.f(-1, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(CategoryEntity.DecorateInfo decorateInfo, CaPullRefreshLayout caPullRefreshLayout) {
        if (caPullRefreshLayout != null) {
            caPullRefreshLayout.setRefreshTextColor(decorateInfo == null ? -7566196 : decorateInfo.getTextColor());
        }
        if (decorateInfo == null) {
            removeAllViews();
            return;
        }
        int min = Math.min(Math.max(e.d() - d.d(96), com.jingdong.app.mall.home.n.a.C_TITLE.getFloorHeight()), d.d(decorateInfo.getBgHeight()));
        if (min != this.f8733j) {
            this.f8733j = min;
            this.f8734k.setShader(new LinearGradient(0.0f, this.f8733j - d.d(160), 0.0f, this.f8733j, new int[]{32962294, -592138}, (float[]) null, Shader.TileMode.CLAMP));
            this.f8731h.postInvalidate();
        }
        if (this.f8730g == decorateInfo && this.f8735l == d.f9279g) {
            return;
        }
        this.f8730g = decorateInfo;
        this.f8735l = d.f9279g;
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, decorateInfo.getBgColors());
        f8729m.showImageOnFail(gradientDrawable).showImageOnLoading(gradientDrawable).showImageForEmptyUri(gradientDrawable);
        f8729m.bitmapConfig(Bitmap.Config.ARGB_8888);
        f8729m.isScale(false);
        if (this.f8731h.getParent() == null) {
            FitTopImage fitTopImage = this.f8731h;
            addView(fitTopImage, this.f8732i.u(fitTopImage));
        }
        com.jingdong.app.mall.home.floor.ctrl.e.f(this.f8730g.getDecorateBgUrl(), this.f8731h, f8729m);
    }
}
