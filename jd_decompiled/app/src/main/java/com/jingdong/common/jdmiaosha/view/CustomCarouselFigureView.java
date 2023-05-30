package com.jingdong.common.jdmiaosha.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.jingdong.app.mall.utils.ui.view.CarouselFigureView;
import com.jingdong.common.jdmiaosha.utils.DarkUtil;

/* loaded from: classes5.dex */
public class CustomCarouselFigureView extends CarouselFigureView {
    private BannerListener bannerListener;

    /* loaded from: classes5.dex */
    public interface BannerListener {
        void attach();

        void detach();

        void onPageSelected(int i2);
    }

    public CustomCarouselFigureView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.ui.view.CarouselFigureView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        BannerListener bannerListener = this.bannerListener;
        if (bannerListener != null) {
            bannerListener.attach();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.ui.view.CarouselFigureView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BannerListener bannerListener = this.bannerListener;
        if (bannerListener != null) {
            bannerListener.detach();
        }
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        if (DarkUtil.isDarkTheme()) {
            canvas.drawColor(DarkUtil.PIC_FOREGROUND_COLOR);
        }
    }

    @Override // com.jingdong.app.mall.utils.ui.view.CarouselFigureView, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        super.onPageSelected(i2);
        BannerListener bannerListener = this.bannerListener;
        if (bannerListener != null) {
            bannerListener.onPageSelected(i2);
        }
    }

    public void setBannerListener(BannerListener bannerListener) {
        this.bannerListener = bannerListener;
    }

    public CustomCarouselFigureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);
    }
}
