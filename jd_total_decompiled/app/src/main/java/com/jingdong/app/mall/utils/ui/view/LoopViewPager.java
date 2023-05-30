package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes4.dex */
public class LoopViewPager extends ViewPager {

    /* renamed from: g  reason: collision with root package name */
    ViewPager.OnPageChangeListener f11981g;

    /* renamed from: h  reason: collision with root package name */
    private LoopPagerAdapterWrapper f11982h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f11983i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f11984j;

    /* renamed from: k  reason: collision with root package name */
    private ViewPager.OnPageChangeListener f11985k;

    /* loaded from: classes4.dex */
    class a implements ViewPager.OnPageChangeListener {

        /* renamed from: g  reason: collision with root package name */
        private float f11986g = -1.0f;

        /* renamed from: h  reason: collision with root package name */
        private float f11987h = -1.0f;

        a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (LoopViewPager.this.f11982h != null) {
                int currentItem = LoopViewPager.super.getCurrentItem();
                int i3 = LoopViewPager.this.f11982h.i(currentItem);
                if (i2 == 0 && (currentItem == 0 || currentItem == LoopViewPager.this.f11982h.getCount() - 1)) {
                    LoopViewPager.this.setCurrentItem(i3, false);
                }
            }
            ViewPager.OnPageChangeListener onPageChangeListener = LoopViewPager.this.f11981g;
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageScrollStateChanged(i2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
            if (LoopViewPager.this.f11982h != null) {
                int i4 = LoopViewPager.this.f11982h.i(i2);
                if (f2 == 0.0f && this.f11986g == 0.0f && (i2 == 0 || i2 == LoopViewPager.this.f11982h.getCount() - 1)) {
                    LoopViewPager.this.setCurrentItem(i4, false);
                }
                i2 = i4;
            }
            this.f11986g = f2;
            if (LoopViewPager.this.f11981g != null) {
                if (i2 != r0.f11982h.getRealCount() - 1) {
                    LoopViewPager.this.f11981g.onPageScrolled(i2, f2, i3);
                } else if (f2 > 0.5d) {
                    LoopViewPager.this.f11981g.onPageScrolled(0, 0.0f, 0);
                } else {
                    LoopViewPager.this.f11981g.onPageScrolled(i2, 0.0f, 0);
                }
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            int i3 = LoopViewPager.this.f11982h.i(i2);
            float f2 = i3;
            if (this.f11987h != f2) {
                this.f11987h = f2;
                ViewPager.OnPageChangeListener onPageChangeListener = LoopViewPager.this.f11981g;
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageSelected(i3);
                }
            }
        }
    }

    public LoopViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11983i = true;
        this.f11984j = true;
        this.f11985k = new a();
        init();
    }

    private void init() {
        super.setOnPageChangeListener(this.f11985k);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public PagerAdapter getAdapter() {
        LoopPagerAdapterWrapper loopPagerAdapterWrapper = this.f11982h;
        return loopPagerAdapterWrapper != null ? loopPagerAdapterWrapper.d() : loopPagerAdapterWrapper;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public int getCurrentItem() {
        LoopPagerAdapterWrapper loopPagerAdapterWrapper = this.f11982h;
        if (loopPagerAdapterWrapper != null) {
            return loopPagerAdapterWrapper.i(super.getCurrentItem());
        }
        return 0;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f11984j) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f11984j) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setAdapter(PagerAdapter pagerAdapter) {
        LoopPagerAdapterWrapper loopPagerAdapterWrapper = new LoopPagerAdapterWrapper(pagerAdapter);
        this.f11982h = loopPagerAdapterWrapper;
        loopPagerAdapterWrapper.g(this.f11983i);
        super.setAdapter(this.f11982h);
        setCurrentItem(this.f11982h.getRealCount(), false);
        setCanScroll(this.f11982h.getRealCount() >= 2);
    }

    public void setCanScroll(boolean z) {
        this.f11984j = z;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i2, boolean z) {
        super.setCurrentItem(this.f11982h.h(i2), z);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.f11981g = onPageChangeListener;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i2) {
        if (getCurrentItem() != i2) {
            setCurrentItem(i2, true);
        }
    }
}
