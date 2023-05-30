package com.jingdong.app.mall.bundle.dolphinlib.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.HorizontalScrollView;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes19.dex */
public class DolphinHorizontalScrollView extends HorizontalScrollView {
    private boolean disallowIntercept;
    private ScrollViewListener mScrollViewListener;
    final int offsetX;
    int startX;
    int startY;

    /* loaded from: classes19.dex */
    public interface ScrollViewListener {
        void onScrollChanged(DolphinHorizontalScrollView dolphinHorizontalScrollView, int i2, int i3, int i4, int i5);

        void slideLeft();

        void slideRight();
    }

    public DolphinHorizontalScrollView(Context context) {
        super(context);
        this.startX = 0;
        this.startY = 0;
        this.offsetX = 50;
        this.disallowIntercept = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        ScrollViewListener scrollViewListener;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.startX = (int) motionEvent.getRawX();
            this.startY = (int) motionEvent.getRawY();
        } else if (action == 1 || action == 3) {
            if (this.startX > motionEvent.getRawX() + 50.0f) {
                ScrollViewListener scrollViewListener2 = this.mScrollViewListener;
                if (scrollViewListener2 != null) {
                    scrollViewListener2.slideLeft();
                }
            } else if (this.startX < motionEvent.getRawX() - 50.0f && (scrollViewListener = this.mScrollViewListener) != null) {
                scrollViewListener.slideRight();
            }
        }
        ViewParent viewParent = this;
        while (viewParent != null) {
            viewParent = viewParent.getParent();
            if (viewParent instanceof ViewPager) {
                break;
            }
        }
        if (viewParent != null) {
            viewParent.requestDisallowInterceptTouchEvent(this.disallowIntercept);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        ScrollViewListener scrollViewListener = this.mScrollViewListener;
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, i2, i3, i4, i5);
        }
    }

    public void setDisallowIntercept(boolean z) {
        this.disallowIntercept = z;
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.mScrollViewListener = scrollViewListener;
    }

    public DolphinHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.startX = 0;
        this.startY = 0;
        this.offsetX = 50;
        this.disallowIntercept = true;
    }
}
