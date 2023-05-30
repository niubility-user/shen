package com.jingdong.app.mall.bundle.styleinfoview.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes3.dex */
public class PDTabViewPager extends ViewPager {
    private boolean isCanScroll;
    private boolean isRecommendTouch;
    private ViewPager mRecommendViewPager;

    public PDTabViewPager(Context context) {
        super(context);
        this.isCanScroll = true;
    }

    private boolean checkArea(MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        int[] iArr = new int[2];
        this.mRecommendViewPager.getLocationOnScreen(iArr);
        int i2 = iArr[0];
        int width = this.mRecommendViewPager.getWidth() + i2;
        int i3 = iArr[1];
        return ((float) i2) < rawX && rawX < ((float) width) && ((float) i3) < rawY && rawY < ((float) (this.mRecommendViewPager.getHeight() + i3));
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.isRecommendTouch = this.mRecommendViewPager != null && checkArea(motionEvent);
        }
        if (this.isRecommendTouch) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        if (this.isCanScroll) {
            super.scrollTo(i2, i3);
        }
    }

    public void setChildViewpager(ViewPager viewPager) {
        this.mRecommendViewPager = viewPager;
    }

    public void setScanScroll(boolean z) {
        this.isCanScroll = z;
    }

    public PDTabViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isCanScroll = true;
    }
}
