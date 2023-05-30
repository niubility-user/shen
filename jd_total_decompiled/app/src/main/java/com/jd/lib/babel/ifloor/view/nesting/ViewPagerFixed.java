package com.jd.lib.babel.ifloor.view.nesting;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes13.dex */
public class ViewPagerFixed extends ViewPager {
    private boolean isScroll;

    public ViewPagerFixed(Context context) {
        super(context);
        this.isScroll = true;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            boolean z = this.isScroll;
            return !z ? z : super.onInterceptTouchEvent(motionEvent);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            boolean z = this.isScroll;
            return !z ? z : super.onTouchEvent(motionEvent);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void setScroll(boolean z) {
        this.isScroll = z;
    }

    public ViewPagerFixed(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isScroll = true;
    }
}
