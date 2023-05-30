package com.jd.manto.center.carouseview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes17.dex */
public class CarouselFigureViewPager extends ViewPager implements View.OnTouchListener {

    /* renamed from: g  reason: collision with root package name */
    private ViewGroup f6521g;

    /* renamed from: h  reason: collision with root package name */
    private ViewPager.OnPageChangeListener f6522h;

    /* renamed from: i  reason: collision with root package name */
    private View.OnTouchListener f6523i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f6524j;

    /* renamed from: k  reason: collision with root package name */
    protected boolean f6525k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f6526l;

    /* renamed from: m  reason: collision with root package name */
    private ViewPager.OnPageChangeListener f6527m;

    /* loaded from: classes17.dex */
    class a implements ViewPager.OnPageChangeListener {

        /* renamed from: g  reason: collision with root package name */
        private float f6528g = -1.0f;

        /* renamed from: h  reason: collision with root package name */
        private float f6529h = -1.0f;

        /* renamed from: com.jd.manto.center.carouseview.CarouselFigureViewPager$a$a  reason: collision with other inner class name */
        /* loaded from: classes17.dex */
        class RunnableC0182a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ int f6531g;

            RunnableC0182a(int i2) {
                this.f6531g = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                CarouselFigureViewPager.this.setCurrentItem(this.f6531g, false);
            }
        }

        a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (CarouselFigureViewPager.this.getAdapter() != null) {
                CarouselFigureViewPager carouselFigureViewPager = CarouselFigureViewPager.this;
                if (carouselFigureViewPager.f6525k && carouselFigureViewPager.getAdapter().getCount() > 3) {
                    int currentItem = CarouselFigureViewPager.this.getCurrentItem();
                    if (i2 == 0 && (currentItem == 0 || currentItem == CarouselFigureViewPager.this.getAdapter().getCount() - 1)) {
                        CarouselFigureViewPager.this.setCurrentItem(CarouselFigureViewPager.this.getEdgeNextPosition(currentItem), false);
                    }
                }
            }
            if (CarouselFigureViewPager.this.f6522h != null) {
                CarouselFigureViewPager.this.f6522h.onPageScrollStateChanged(i2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
            CarouselFigureViewPager carouselFigureViewPager = CarouselFigureViewPager.this;
            if (carouselFigureViewPager.f6525k && carouselFigureViewPager.getAdapter() != null && CarouselFigureViewPager.this.getAdapter().getCount() > 3) {
                int edgeNextPosition = CarouselFigureViewPager.this.getEdgeNextPosition(i2);
                if (f2 == 0.0f && this.f6528g == 0.0f && (i2 == 0 || i2 == CarouselFigureViewPager.this.getAdapter().getCount() - 1)) {
                    CarouselFigureViewPager.this.post(new RunnableC0182a(edgeNextPosition));
                }
            }
            this.f6528g = f2;
            int realPosition = CarouselFigureViewPager.this.toRealPosition(i2);
            if (CarouselFigureViewPager.this.f6522h != null) {
                if (CarouselFigureViewPager.this.getAdapter() != null && (i2 != 0 || i2 != CarouselFigureViewPager.this.getAdapter().getCount() - 1)) {
                    CarouselFigureViewPager.this.f6522h.onPageScrolled(realPosition, f2, i3);
                } else if (f2 > 0.5d) {
                    CarouselFigureViewPager.this.f6522h.onPageScrolled(0, 0.0f, 0);
                } else {
                    CarouselFigureViewPager.this.f6522h.onPageScrolled(realPosition, 0.0f, 0);
                }
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            int realPosition = CarouselFigureViewPager.this.toRealPosition(i2);
            float f2 = realPosition;
            if (this.f6529h != f2) {
                this.f6529h = f2;
                if (CarouselFigureViewPager.this.f6522h != null) {
                    CarouselFigureViewPager.this.f6522h.onPageSelected(realPosition);
                }
            }
        }
    }

    public CarouselFigureViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6524j = true;
        this.f6525k = false;
        this.f6526l = false;
        this.f6527m = new a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getEdgeNextPosition(int i2) {
        if (!this.f6525k || getRealCount() <= 1) {
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
        if (motionEvent.getAction() == 0 && (onTouchListener = this.f6523i) != null) {
            onTouchListener.onTouch(null, motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getRealCount() {
        if (getAdapter() == null) {
            return 0;
        }
        if (this.f6525k && getAdapter().getCount() > 3) {
            return getAdapter().getCount() - 2;
        }
        return getAdapter().getCount();
    }

    public void init(ViewGroup viewGroup, boolean z) {
        this.f6521g = viewGroup;
        this.f6525k = z;
        super.setOnTouchListener(this);
        super.setOnPageChangeListener(this.f6527m);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        if (this.f6524j) {
            super.onAttachedToWindow();
        }
        this.f6524j = false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.f6526l) {
            return onTouchEvent(motionEvent);
        }
        View.OnTouchListener onTouchListener = this.f6523i;
        if (onTouchListener != null) {
            onTouchListener.onTouch(view, motionEvent);
        }
        int action = motionEvent.getAction();
        if (action != 1 && action != 3) {
            ViewGroup viewGroup = this.f6521g;
            if (viewGroup != null) {
                viewGroup.requestDisallowInterceptTouchEvent(true);
            }
        } else {
            ViewGroup viewGroup2 = this.f6521g;
            if (viewGroup2 != null) {
                viewGroup2.requestDisallowInterceptTouchEvent(false);
            }
            View.OnTouchListener onTouchListener2 = this.f6523i;
            if (onTouchListener2 != null) {
                onTouchListener2.onTouch(view, motionEvent);
            }
        }
        try {
            onTouchEvent(motionEvent);
        } catch (Throwable th) {
            if (Log.D) {
                Log.d("Exception", th.toString());
            }
        }
        return true;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setAdapter(PagerAdapter pagerAdapter) {
        super.setAdapter(pagerAdapter);
        if (pagerAdapter == null || !this.f6525k || pagerAdapter.getCount() <= 1) {
            return;
        }
        setCurrentItem(1, false);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.f6522h = onPageChangeListener;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.f6523i = onTouchListener;
    }

    public int toRealPosition(int i2) {
        if (getAdapter() == null) {
            return 0;
        }
        if (this.f6525k) {
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
        this.f6524j = true;
        this.f6525k = false;
        this.f6526l = false;
        this.f6527m = new a();
    }
}
