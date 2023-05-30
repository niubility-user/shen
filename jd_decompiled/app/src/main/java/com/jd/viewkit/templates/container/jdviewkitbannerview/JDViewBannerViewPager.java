package com.jd.viewkit.templates.container.jdviewkitbannerview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.IndicatorListener;

/* loaded from: classes18.dex */
public class JDViewBannerViewPager extends ViewPager {
    private static final int MIN_PAGE_LIMIT = 3;
    private boolean autoPlay;
    private Runnable autoPlayTask;
    private BannerPageTransformer bannerPageTransformer;
    private JDViewKitChannelModel channelModel;
    private boolean circle;
    private int currentItem;
    private Handler handler;
    private IndicatorListener indicatorListener;
    private int interval;
    private float itemWidth;
    private int pageMargin;
    private PagerChangeListener pagerChangeListener;
    private float parentWidth;
    private boolean playing;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public static class BannerPageTransformer implements ViewPager.PageTransformer {
        private static final float MAX_SCALE = 1.0f;
        private static final float MIN_SCALE = 0.7f;
        private JDViewKitChannelModel channelModel;
        private float itemWidth;
        private ViewPager mViewPager;
        private float maxScale;
        private float minScale;
        private float offsetPosition;
        private float reduceX;

        public BannerPageTransformer(ViewPager viewPager, JDViewKitChannelModel jDViewKitChannelModel) {
            this(viewPager, 1.0f, MIN_SCALE, jDViewKitChannelModel);
        }

        @Override // androidx.viewpager.widget.ViewPager.PageTransformer
        public void transformPage(@NonNull View view, float f2) {
            if (this.offsetPosition == 0.0f) {
                float realPx = GlobalManage.getInstance().getRealPx(this.mViewPager.getPaddingLeft(), this.channelModel);
                this.offsetPosition = realPx / ((GlobalManage.getInstance().getRealPx(this.mViewPager.getMeasuredWidth(), this.channelModel) - realPx) - GlobalManage.getInstance().getRealPx(this.mViewPager.getPaddingRight(), this.channelModel));
            }
            float f3 = f2 - this.offsetPosition;
            if (this.itemWidth == 0.0f) {
                float width = view.getWidth();
                this.itemWidth = width;
                this.reduceX = (((2.0f - this.maxScale) - this.minScale) * width) / 2.0f;
            }
            if (f3 <= -1.0f) {
                view.setTranslationX(this.reduceX);
                view.setScaleX(this.minScale);
                view.setScaleY(this.minScale);
            } else if (f3 <= 1.0d) {
                float abs = (this.maxScale - this.minScale) * Math.abs(1.0f - Math.abs(f3));
                view.setTranslationX(f3 * (-this.reduceX));
                view.setScaleX(this.minScale + abs);
                view.setScaleY(abs + this.minScale);
            } else {
                view.setScaleX(this.minScale);
                view.setScaleY(this.minScale);
                view.setTranslationX(-this.reduceX);
            }
        }

        public BannerPageTransformer(ViewPager viewPager, float f2, JDViewKitChannelModel jDViewKitChannelModel) {
            this(viewPager, 1.0f, f2, jDViewKitChannelModel);
        }

        public BannerPageTransformer(ViewPager viewPager, float f2, float f3, JDViewKitChannelModel jDViewKitChannelModel) {
            this.reduceX = 0.0f;
            this.itemWidth = 0.0f;
            this.offsetPosition = 0.0f;
            this.minScale = f3;
            this.maxScale = f2;
            this.mViewPager = viewPager;
            this.channelModel = jDViewKitChannelModel;
        }
    }

    /* loaded from: classes18.dex */
    public static class Builder {
        private JDViewKitChannelModel channelModel;
        private int interval;
        private float itemWidth;
        private int pageMargin;
        private float parentWidth;
        private float scale;
        private boolean autoPlay = false;
        private boolean circle = false;

        public void build(JDViewBannerViewPager jDViewBannerViewPager) {
            if (jDViewBannerViewPager != null) {
                jDViewBannerViewPager.autoPlay = this.autoPlay;
                jDViewBannerViewPager.pageMargin = this.pageMargin;
                jDViewBannerViewPager.interval = this.interval;
                jDViewBannerViewPager.itemWidth = this.itemWidth;
                jDViewBannerViewPager.parentWidth = this.parentWidth;
                jDViewBannerViewPager.circle = this.circle;
                jDViewBannerViewPager.bannerPageTransformer = new BannerPageTransformer(jDViewBannerViewPager, this.scale, this.channelModel);
                jDViewBannerViewPager.init();
            }
        }

        public Builder setAutoPlay(boolean z) {
            this.autoPlay = z;
            return this;
        }

        public Builder setChannelModel(JDViewKitChannelModel jDViewKitChannelModel) {
            this.channelModel = jDViewKitChannelModel;
            return this;
        }

        public Builder setCircle(boolean z) {
            this.circle = z;
            return this;
        }

        public Builder setInterval(int i2) {
            this.interval = i2;
            return this;
        }

        public Builder setItemWidth(float f2) {
            this.itemWidth = f2;
            return this;
        }

        public Builder setPageMargin(int i2) {
            this.pageMargin = i2;
            return this;
        }

        public Builder setParentWidth(float f2) {
            this.parentWidth = f2;
            return this;
        }

        public Builder setScale(float f2) {
            this.scale = f2;
            return this;
        }
    }

    /* loaded from: classes18.dex */
    public interface PagerChangeListener {
        void onPageSelected(int i2);
    }

    public JDViewBannerViewPager(@NonNull Context context, JDViewKitChannelModel jDViewKitChannelModel) {
        this(context, null, jDViewKitChannelModel);
    }

    static /* synthetic */ int access$208(JDViewBannerViewPager jDViewBannerViewPager) {
        int i2 = jDViewBannerViewPager.currentItem;
        jDViewBannerViewPager.currentItem = i2 + 1;
        return i2;
    }

    public PagerChangeListener getPagerChangeListener() {
        return this.pagerChangeListener;
    }

    public void init() {
        setPageMargin(this.pageMargin);
        setOffscreenPageLimit(3);
        if (this.bannerPageTransformer == null) {
            this.bannerPageTransformer = new BannerPageTransformer(this, this.channelModel);
        }
        setPageTransformer(false, this.bannerPageTransformer);
        int i2 = ((int) (this.parentWidth - this.itemWidth)) / 2;
        setPadding(i2, 0, i2, 0);
        clearOnPageChangeListeners();
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jd.viewkit.templates.container.jdviewkitbannerview.JDViewBannerViewPager.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i3) {
                if (i3 == 1) {
                    JDViewBannerViewPager.this.pause();
                } else if (i3 != 2) {
                } else {
                    JDViewBannerViewPager.this.start();
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i3, float f2, int i4) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i3) {
                if (JDViewBannerViewPager.this.indicatorListener != null) {
                    JDViewBannerViewPager.this.indicatorListener.update(i3 % JDViewBannerViewPager.this.indicatorListener.getRealCount());
                    if (JDViewBannerViewPager.this.pagerChangeListener != null) {
                        JDViewBannerViewPager.this.pagerChangeListener.onPageSelected(i3);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        pause();
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            start();
        } else {
            pause();
        }
    }

    public void pause() {
        this.playing = false;
        this.handler.removeCallbacks(this.autoPlayTask);
    }

    public void setAutoPlay(boolean z) {
        this.autoPlay = z;
    }

    public void setIndicatorListener(IndicatorListener indicatorListener) {
        this.indicatorListener = indicatorListener;
    }

    public void setPagerChangeListener(PagerChangeListener pagerChangeListener) {
        this.pagerChangeListener = pagerChangeListener;
    }

    public void start() {
        if (getAdapter() != null && this.autoPlay) {
            pause();
            this.playing = true;
            this.handler.postDelayed(this.autoPlayTask, this.interval);
        }
    }

    public JDViewBannerViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet, JDViewKitChannelModel jDViewKitChannelModel) {
        super(context, attributeSet);
        this.handler = new Handler();
        this.autoPlay = false;
        this.playing = false;
        this.circle = false;
        this.interval = 3000;
        this.currentItem = 0;
        this.autoPlayTask = new Runnable() { // from class: com.jd.viewkit.templates.container.jdviewkitbannerview.JDViewBannerViewPager.1
            @Override // java.lang.Runnable
            public void run() {
                if (JDViewBannerViewPager.this.autoPlay && JDViewBannerViewPager.this.playing) {
                    JDViewBannerViewPager jDViewBannerViewPager = JDViewBannerViewPager.this;
                    jDViewBannerViewPager.currentItem = jDViewBannerViewPager.getCurrentItem();
                    JDViewBannerViewPager.access$208(JDViewBannerViewPager.this);
                    if (JDViewBannerViewPager.this.getAdapter() != null && JDViewBannerViewPager.this.getCurrentItem() == JDViewBannerViewPager.this.getAdapter().getCount() - 1) {
                        if (JDViewBannerViewPager.this.circle) {
                            JDViewBannerViewPager.this.setCurrentItem(0, true);
                        }
                    } else {
                        JDViewBannerViewPager jDViewBannerViewPager2 = JDViewBannerViewPager.this;
                        jDViewBannerViewPager2.setCurrentItem(jDViewBannerViewPager2.currentItem, true);
                    }
                }
                JDViewBannerViewPager.this.handler.postDelayed(this, JDViewBannerViewPager.this.interval);
            }
        };
        setClipToPadding(false);
        setOverScrollMode(2);
        this.channelModel = jDViewKitChannelModel;
    }
}
