package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes4.dex */
public class CarouselFigureViewPager extends ViewPager implements View.OnTouchListener {
    private static final String TAG = "CarouselFigureViewPager";
    protected boolean isCarousel;
    private boolean mFirstLayout;
    private boolean mOnTouchFlag;
    private ViewPager.OnPageChangeListener mOuterPageChangeListener;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private View.OnTouchListener onTouchListener;
    private ViewGroup parent;

    /* loaded from: classes4.dex */
    class a implements ViewPager.OnPageChangeListener {

        /* renamed from: g  reason: collision with root package name */
        private float f11967g = -1.0f;

        /* renamed from: h  reason: collision with root package name */
        private float f11968h = -1.0f;

        /* renamed from: com.jingdong.app.mall.utils.ui.view.CarouselFigureViewPager$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class RunnableC0389a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ int f11970g;

            RunnableC0389a(int i2) {
                this.f11970g = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                CarouselFigureViewPager.this.setCurrentItem(this.f11970g, false);
            }
        }

        a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (CarouselFigureViewPager.this.getAdapter() != null) {
                CarouselFigureViewPager carouselFigureViewPager = CarouselFigureViewPager.this;
                if (carouselFigureViewPager.isCarousel && carouselFigureViewPager.getAdapter().getCount() > 3) {
                    int currentItem = CarouselFigureViewPager.this.getCurrentItem();
                    if (i2 == 0 && (currentItem == 0 || currentItem == CarouselFigureViewPager.this.getAdapter().getCount() - 1)) {
                        CarouselFigureViewPager.this.setCurrentItem(CarouselFigureViewPager.this.getEdgeNextPosition(currentItem), false);
                    }
                }
            }
            if (CarouselFigureViewPager.this.mOuterPageChangeListener != null) {
                CarouselFigureViewPager.this.mOuterPageChangeListener.onPageScrollStateChanged(i2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
            CarouselFigureViewPager carouselFigureViewPager = CarouselFigureViewPager.this;
            if (carouselFigureViewPager.isCarousel && carouselFigureViewPager.getAdapter() != null && CarouselFigureViewPager.this.getAdapter().getCount() > 3) {
                int edgeNextPosition = CarouselFigureViewPager.this.getEdgeNextPosition(i2);
                if (f2 == 0.0f && this.f11967g == 0.0f && (i2 == 0 || i2 == CarouselFigureViewPager.this.getAdapter().getCount() - 1)) {
                    CarouselFigureViewPager.this.post(new RunnableC0389a(edgeNextPosition));
                }
            }
            this.f11967g = f2;
            int realPosition = CarouselFigureViewPager.this.toRealPosition(i2);
            if (CarouselFigureViewPager.this.mOuterPageChangeListener != null) {
                if (CarouselFigureViewPager.this.getAdapter() != null && (i2 != 0 || i2 != CarouselFigureViewPager.this.getAdapter().getCount() - 1)) {
                    CarouselFigureViewPager.this.mOuterPageChangeListener.onPageScrolled(realPosition, f2, i3);
                } else if (f2 > 0.5d) {
                    CarouselFigureViewPager.this.mOuterPageChangeListener.onPageScrolled(0, 0.0f, 0);
                } else {
                    CarouselFigureViewPager.this.mOuterPageChangeListener.onPageScrolled(realPosition, 0.0f, 0);
                }
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            int realPosition = CarouselFigureViewPager.this.toRealPosition(i2);
            float f2 = realPosition;
            if (this.f11968h != f2) {
                this.f11968h = f2;
                if (CarouselFigureViewPager.this.mOuterPageChangeListener != null) {
                    CarouselFigureViewPager.this.mOuterPageChangeListener.onPageSelected(realPosition);
                }
            }
        }
    }

    public CarouselFigureViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFirstLayout = true;
        this.isCarousel = false;
        this.mOnTouchFlag = false;
        this.onPageChangeListener = new a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getEdgeNextPosition(int i2) {
        if (!this.isCarousel || getRealCount() <= 1) {
            return i2;
        }
        if (i2 == 0) {
            return getRealCount();
        }
        if (i2 == getRealCount() + 1) {
            return 1;
        }
        return i2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        View.OnTouchListener onTouchListener;
        if (motionEvent.getAction() == 0 && (onTouchListener = this.onTouchListener) != null) {
            onTouchListener.onTouch(null, motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getRealCount() {
        if (getAdapter() == null) {
            return 0;
        }
        if (this.isCarousel && getAdapter().getCount() > 3) {
            return getAdapter().getCount() - 2;
        }
        return getAdapter().getCount();
    }

    public void init(ViewGroup viewGroup, boolean z) {
        this.parent = viewGroup;
        this.isCarousel = z;
        super.setOnTouchListener(this);
        super.setOnPageChangeListener(this.onPageChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        if (this.mFirstLayout) {
            super.onAttachedToWindow();
        }
        this.mFirstLayout = false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.mOnTouchFlag) {
            return onTouchEvent(motionEvent);
        }
        View.OnTouchListener onTouchListener = this.onTouchListener;
        if (onTouchListener != null) {
            onTouchListener.onTouch(view, motionEvent);
        }
        int action = motionEvent.getAction();
        if (action != 1 && action != 3) {
            ViewGroup viewGroup = this.parent;
            if (viewGroup != null) {
                viewGroup.requestDisallowInterceptTouchEvent(true);
            }
        } else {
            ViewGroup viewGroup2 = this.parent;
            if (viewGroup2 != null) {
                viewGroup2.requestDisallowInterceptTouchEvent(false);
            }
            View.OnTouchListener onTouchListener2 = this.onTouchListener;
            if (onTouchListener2 != null) {
                onTouchListener2.onTouch(view, motionEvent);
            }
        }
        try {
            onTouchEvent(motionEvent);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
        return true;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setAdapter(PagerAdapter pagerAdapter) {
        super.setAdapter(pagerAdapter);
        if (pagerAdapter == null || !this.isCarousel || pagerAdapter.getCount() <= 1) {
            return;
        }
        setCurrentItem(1, false);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOuterPageChangeListener = onPageChangeListener;
    }

    public void setOnTouchFlag(boolean z) {
        this.mOnTouchFlag = z;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    public int toRealPosition(int i2) {
        if (getAdapter() == null) {
            return 0;
        }
        if (this.isCarousel) {
            int realCount = getRealCount();
            if (realCount == 0) {
                return 0;
            }
            int i3 = (i2 - 1) % realCount;
            return i3 < 0 ? i3 + realCount : i3;
        }
        return i2;
    }

    public CarouselFigureViewPager(Context context) {
        super(context);
        this.mFirstLayout = true;
        this.isCarousel = false;
        this.mOnTouchFlag = false;
        this.onPageChangeListener = new a();
    }
}
