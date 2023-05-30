package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.app.mall.home.floor.ctrl.IFloorFigureView;
import com.jingdong.app.mall.utils.ui.view.CarouseFigureImagePagerAdapter;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Field;

/* loaded from: classes4.dex */
public class CarouselFigureView extends FrameLayout implements ViewPager.OnPageChangeListener, IFloorFigureView {
    private static final String TAG = "CarouselFigureView";
    private static final int VIEW_CHANGE_INTERVAL = 4000;
    private static final int VIEW_CHANGE_INTERVAL_RESUME = 2000;
    private CarouseFigureImagePagerAdapter.IAccessibilityTextListener accessibilityTextListener;
    private int bannerCornerRadius;
    private CursorLayout cursorContent;
    private Handler handler;
    protected String id;
    private boolean isAutoPlay;
    private boolean isCarousel;
    private boolean isDetached;
    private boolean isPause;
    private Context mContext;
    protected CarouselFigureViewPager pager;
    private long token;

    /* loaded from: classes4.dex */
    public class ViewPagerScroller extends Scroller {
        private int mScrollDuration;

        public ViewPagerScroller(Context context) {
            super(context);
            this.mScrollDuration = 2000;
        }

        public void initViewPagerScroll(ViewPager viewPager) {
            try {
                Field declaredField = ViewPager.class.getDeclaredField("mScroller");
                declaredField.setAccessible(true);
                declaredField.set(viewPager, this);
            } catch (Exception e2) {
                OKLog.e(CarouselFigureView.TAG, e2);
            }
        }

        public void setScrollDuration(int i2) {
            this.mScrollDuration = i2;
        }

        @Override // android.widget.Scroller
        public void startScroll(int i2, int i3, int i4, int i5, int i6) {
            super.startScroll(i2, i3, i4, i5, this.mScrollDuration);
        }

        @Override // android.widget.Scroller
        public void startScroll(int i2, int i3, int i4, int i5) {
            super.startScroll(i2, i3, i4, i5, this.mScrollDuration);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
            this.mScrollDuration = 2000;
        }
    }

    public CarouselFigureView(Context context) {
        super(context);
        this.token = -1L;
        this.handler = new Handler() { // from class: com.jingdong.app.mall.utils.ui.view.CarouselFigureView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                CarouselFigureViewPager carouselFigureViewPager;
                if (!CarouselFigureView.this.isPause && !CarouselFigureView.this.isDetached && (carouselFigureViewPager = CarouselFigureView.this.pager) != null && carouselFigureViewPager.getChildCount() > 1 && CarouselFigureView.this.pager.getAdapter() != null && CarouselFigureView.this.pager.getAdapter().getCount() >= 2) {
                    try {
                        if (CarouselFigureView.this.token - ((Long) message.obj).longValue() != 0) {
                            return;
                        }
                        int i2 = message.what;
                        if (CarouselFigureView.this.isCarousel) {
                            if (i2 == 0 && CarouselFigureView.this.pager.getCurrentItem() != 0) {
                                CarouselFigureViewPager carouselFigureViewPager2 = CarouselFigureView.this.pager;
                                carouselFigureViewPager2.setCurrentItem(carouselFigureViewPager2.getRealCount() + 1);
                                return;
                            } else if (i2 == CarouselFigureView.this.pager.getRealCount() + 1) {
                                CarouselFigureView.this.pager.setCurrentItem(2);
                                return;
                            } else {
                                CarouselFigureView.this.pager.setCurrentItem(i2 + 1);
                                return;
                            }
                        }
                        CarouselFigureViewPager carouselFigureViewPager3 = CarouselFigureView.this.pager;
                        carouselFigureViewPager3.setCurrentItem((i2 + 1) % carouselFigureViewPager3.getAdapter().getCount());
                    } catch (Exception unused) {
                    }
                }
            }
        };
    }

    private synchronized void autoChangeViewPagerPosition(int i2) {
        if (this.pager != null && this.isAutoPlay) {
            this.token = System.currentTimeMillis();
            Message obtain = Message.obtain();
            obtain.what = this.pager.getCurrentItem();
            obtain.obj = Long.valueOf(this.token);
            this.handler.sendMessageDelayed(obtain, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCarouseFigureImagePagerAdapter(CarouseFigureImagePagerAdapter.CarouseFigureImageAdapterListener carouseFigureImageAdapterListener) {
        CarouseFigureImagePagerAdapter carouseFigureImagePagerAdapter = new CarouseFigureImagePagerAdapter(this.mContext, this.isCarousel, this.bannerCornerRadius, carouseFigureImageAdapterListener);
        CarouseFigureImagePagerAdapter.IAccessibilityTextListener iAccessibilityTextListener = this.accessibilityTextListener;
        if (iAccessibilityTextListener != null) {
            carouseFigureImagePagerAdapter.setAccessibilityTextListener(iAccessibilityTextListener);
        }
        this.pager.setAdapter(carouseFigureImagePagerAdapter);
        createCursor(this.pager.getRealCount());
        autoChangeViewPagerPosition(4000);
    }

    public void createCursor(int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, " -->> createCursor size = " + i2);
        }
        if (i2 < 1) {
            setVisibility(8);
            return;
        }
        if (getVisibility() == 8) {
            setVisibility(0);
        }
        this.cursorContent.createCursor(i2);
        CursorLayout cursorLayout = this.cursorContent;
        CarouselFigureViewPager carouselFigureViewPager = this.pager;
        cursorLayout.openLight(carouselFigureViewPager.toRealPosition(carouselFigureViewPager.getCurrentItem()));
        if (this.cursorContent.getParent() == null) {
            addView(this.cursorContent);
        }
    }

    public View getViewPager() {
        return this.pager;
    }

    public void init(Context context, ViewGroup viewGroup, int i2, boolean z, boolean z2, int i3) {
        this.mContext = context;
        this.isCarousel = z;
        this.isAutoPlay = z2;
        if (this.pager == null) {
            this.pager = new CarouselFigureViewPager(this.mContext);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, i2);
            layoutParams.setMargins(0, 0, 0, 0);
            this.pager.setLayoutParams(layoutParams);
            this.pager.setOnPageChangeListener(this);
            addView(this.pager);
        }
        if (this.cursorContent == null) {
            this.cursorContent = new CursorLayout(getContext());
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
            if (i3 <= 0) {
                i3 = DPIUtil.dip2px(6.0f);
            }
            layoutParams2.setMargins(0, 0, 0, i3);
            layoutParams2.gravity = 81;
            this.cursorContent.setPadding(0, 0, 0, 0);
            this.cursorContent.setLayoutParams(layoutParams2);
        }
        this.pager.init(viewGroup, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        this.isDetached = false;
        super.onAttachedToWindow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.isDetached = true;
        super.onDetachedFromWindow();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        this.cursorContent.openLight(i2);
        autoChangeViewPagerPosition(4000);
    }

    public void onPause() {
        this.token = System.currentTimeMillis();
        this.isPause = true;
    }

    public void onResume() {
        this.isPause = false;
        autoChangeViewPagerPosition(2000);
    }

    public void setAccessibilityImageAdapterListener(CarouseFigureImagePagerAdapter.IAccessibilityTextListener iAccessibilityTextListener) {
        this.accessibilityTextListener = iAccessibilityTextListener;
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (pagerAdapter != null) {
            this.pager.setAdapter(pagerAdapter);
            createCursor(this.pager.getRealCount());
            autoChangeViewPagerPosition(4000);
        }
    }

    protected void setBannerCornerRadius(int i2) {
        this.bannerCornerRadius = i2;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.IFloorFigureView
    public void setCarouseFigureImageAdapterListener(final CarouseFigureImagePagerAdapter.CarouseFigureImageAdapterListener carouseFigureImageAdapterListener) {
        if (this.pager == null || carouseFigureImageAdapterListener == null) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.handler.post(new Runnable() { // from class: com.jingdong.app.mall.utils.ui.view.CarouselFigureView.3
                @Override // java.lang.Runnable
                public void run() {
                    CarouselFigureView.this.setCarouseFigureImagePagerAdapter(carouseFigureImageAdapterListener);
                }
            });
        } else {
            setCarouseFigureImagePagerAdapter(carouseFigureImageAdapterListener);
        }
    }

    public void setCursor(int i2, int i3, int i4, int i5, int i6) {
        this.cursorContent.setCursor(i2, i3, i4, i5, i6);
    }

    public void setCursorContentBg(int i2) {
        CursorLayout cursorLayout = this.cursorContent;
        if (cursorLayout != null) {
            cursorLayout.setBackgroundColor(i2);
        }
    }

    public void setCursorContentRadius(final int i2) {
        CursorLayout cursorLayout = this.cursorContent;
        if (cursorLayout == null || Build.VERSION.SDK_INT < 21) {
            return;
        }
        cursorLayout.setOutlineProvider(new ViewOutlineProvider() { // from class: com.jingdong.app.mall.utils.ui.view.CarouselFigureView.4
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), i2);
            }
        });
        this.cursorContent.setClipToOutline(true);
    }

    public void setCursorGravity(int i2, int i3) {
        CursorLayout cursorLayout = this.cursorContent;
        if (cursorLayout == null || !(cursorLayout.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.cursorContent.getLayoutParams();
        layoutParams.gravity = i2;
        if ((i2 & 5) == 5) {
            layoutParams.rightMargin = i3;
        } else if ((i2 & 3) == 3) {
            layoutParams.leftMargin = i3;
        }
        this.cursorContent.requestLayout();
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setPagerOffset(int i2, int i3) {
        CarouselFigureViewPager carouselFigureViewPager = this.pager;
        if (carouselFigureViewPager != null) {
            carouselFigureViewPager.setClipToPadding(false);
            this.pager.setPadding(i2, 0, i2, 0);
            this.pager.setPageMargin(i3);
        }
    }

    public void setPagerPadding(int i2, int i3, int i4, int i5) {
        CarouselFigureViewPager carouselFigureViewPager = this.pager;
        if (carouselFigureViewPager != null) {
            carouselFigureViewPager.setPadding(i2, i3, i4, i5);
        }
    }

    public void toFirstItem() {
        post(new Runnable() { // from class: com.jingdong.app.mall.utils.ui.view.CarouselFigureView.2
            @Override // java.lang.Runnable
            public void run() {
                CarouselFigureViewPager carouselFigureViewPager = CarouselFigureView.this.pager;
                if (carouselFigureViewPager == null || carouselFigureViewPager.getAdapter() == null) {
                    return;
                }
                if (CarouselFigureView.this.pager.getAdapter().getCount() > 1 && CarouselFigureView.this.isCarousel) {
                    CarouselFigureView.this.pager.setCurrentItem(1, false);
                } else {
                    CarouselFigureView.this.pager.setCurrentItem(0, false);
                }
            }
        });
    }

    public CarouselFigureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.token = -1L;
        this.handler = new Handler() { // from class: com.jingdong.app.mall.utils.ui.view.CarouselFigureView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                CarouselFigureViewPager carouselFigureViewPager;
                if (!CarouselFigureView.this.isPause && !CarouselFigureView.this.isDetached && (carouselFigureViewPager = CarouselFigureView.this.pager) != null && carouselFigureViewPager.getChildCount() > 1 && CarouselFigureView.this.pager.getAdapter() != null && CarouselFigureView.this.pager.getAdapter().getCount() >= 2) {
                    try {
                        if (CarouselFigureView.this.token - ((Long) message.obj).longValue() != 0) {
                            return;
                        }
                        int i2 = message.what;
                        if (CarouselFigureView.this.isCarousel) {
                            if (i2 == 0 && CarouselFigureView.this.pager.getCurrentItem() != 0) {
                                CarouselFigureViewPager carouselFigureViewPager2 = CarouselFigureView.this.pager;
                                carouselFigureViewPager2.setCurrentItem(carouselFigureViewPager2.getRealCount() + 1);
                                return;
                            } else if (i2 == CarouselFigureView.this.pager.getRealCount() + 1) {
                                CarouselFigureView.this.pager.setCurrentItem(2);
                                return;
                            } else {
                                CarouselFigureView.this.pager.setCurrentItem(i2 + 1);
                                return;
                            }
                        }
                        CarouselFigureViewPager carouselFigureViewPager3 = CarouselFigureView.this.pager;
                        carouselFigureViewPager3.setCurrentItem((i2 + 1) % carouselFigureViewPager3.getAdapter().getCount());
                    } catch (Exception unused) {
                    }
                }
            }
        };
    }

    public void init(Context context, ViewGroup viewGroup, int i2) {
        init(context, viewGroup, i2, true, true, 0);
    }

    public void init(Context context, ViewGroup viewGroup, int i2, boolean z, boolean z2, int i3, int i4) {
        init(context, viewGroup, i2, z, z2, i3);
        if (i4 > 0) {
            ViewPagerScroller viewPagerScroller = new ViewPagerScroller(context);
            viewPagerScroller.setScrollDuration(i4);
            viewPagerScroller.initViewPagerScroll(this.pager);
        }
    }
}
