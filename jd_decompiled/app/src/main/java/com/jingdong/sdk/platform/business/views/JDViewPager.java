package com.jingdong.sdk.platform.business.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes10.dex */
public class JDViewPager extends ViewPager {
    private boolean isCanScroll;
    private boolean mFirstLayout;

    public JDViewPager(Context context) {
        super(context);
        this.mFirstLayout = true;
        this.isCanScroll = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        if (this.mFirstLayout) {
            super.onAttachedToWindow();
        }
        this.mFirstLayout = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            childAt.measure(i2, View.MeasureSpec.makeMeasureSpec(0, 0));
            int measuredHeight = childAt.getMeasuredHeight();
            if (measuredHeight > i4) {
                i4 = measuredHeight;
            }
        }
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(i4, 1073741824));
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Exception unused) {
            if (OKLog.D) {
                OKLog.d("JDViewPager", " --->");
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

    public JDViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFirstLayout = true;
        this.isCanScroll = true;
    }
}
