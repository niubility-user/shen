package com.jdjr.generalKeyboard.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes18.dex */
public class KeyboardViewPager extends ViewPager {
    private boolean isScroll;

    public KeyboardViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isScroll = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isScroll) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.isScroll) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void setScroll(boolean z) {
        this.isScroll = z;
    }

    public KeyboardViewPager(Context context) {
        super(context);
        this.isScroll = false;
    }
}
