package com.jd.lib.productdetail.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes15.dex */
public class PDViewPager extends ViewPager {
    private boolean isCanScroll;
    private boolean mFirstLayout;
    private int mLastX;
    private int mLastY;
    private boolean needMeasure;
    private boolean needResetHeight;

    public PDViewPager(Context context) {
        super(context);
        this.mFirstLayout = true;
        this.isCanScroll = true;
        this.needResetHeight = true;
    }

    public void needResetHeight(boolean z) {
        this.needResetHeight = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        if (this.mFirstLayout) {
            super.onAttachedToWindow();
        }
        this.mFirstLayout = false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mLastX = (int) motionEvent.getX();
            this.mLastY = (int) motionEvent.getY();
        } else if (action == 2) {
            float x = this.mLastX - motionEvent.getX();
            float y = this.mLastY - motionEvent.getY();
            if (Math.abs(x) > Math.abs(y) && Math.abs(x) - Math.abs(y) > 20.0f) {
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
            } else if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public void onMeasure(int i2, int i3) {
        int measuredHeight;
        if ((this.needResetHeight && !PDUtils.isNormalScreen()) || this.needMeasure) {
            measureChildren(i2, View.MeasureSpec.makeMeasureSpec(0, 0));
            int i4 = 0;
            for (int i5 = 0; i5 < getChildCount(); i5++) {
                View childAt = getChildAt(i5);
                if (childAt.getVisibility() != 8 && (measuredHeight = childAt.getMeasuredHeight()) > i4) {
                    i4 = measuredHeight;
                }
            }
            i3 = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
        }
        super.onMeasure(i2, i3);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Exception unused) {
            if (Log.D) {
                Log.d("JDViewPager", " ---> ");
            }
            return false;
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        if (this.isCanScroll) {
            super.scrollTo(i2, i3);
        }
    }

    public void setCanScroll(boolean z) {
        this.isCanScroll = z;
    }

    public void setNeedMeasure(boolean z) {
        this.needMeasure = z;
    }

    public PDViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFirstLayout = true;
        this.isCanScroll = true;
        this.needResetHeight = true;
    }
}
