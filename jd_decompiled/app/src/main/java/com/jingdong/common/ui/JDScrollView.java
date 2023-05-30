package com.jingdong.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/* loaded from: classes6.dex */
public class JDScrollView extends ScrollView {
    private OnJDScrollListener onScrollListener;
    private float xDistance;
    private float xLast;
    private float yDistance;
    private float yLast;

    /* loaded from: classes6.dex */
    public interface OnJDScrollListener {
        void onScroll(int i2, int i3, int i4, int i5);
    }

    public JDScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public OnJDScrollListener getOnScrollListener() {
        return this.onScrollListener;
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.yDistance = 0.0f;
            this.xDistance = 0.0f;
            this.xLast = motionEvent.getX();
            this.yLast = motionEvent.getY();
        } else if (action == 2) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.xDistance += Math.abs(x - this.xLast);
            float abs = this.yDistance + Math.abs(y - this.yLast);
            this.yDistance = abs;
            this.xLast = x;
            this.yLast = y;
            if (this.xDistance > abs) {
                return false;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        OnJDScrollListener onJDScrollListener = this.onScrollListener;
        if (onJDScrollListener != null) {
            onJDScrollListener.onScroll(i2, i3, i4, i5);
        }
    }

    public void setOnJDScrollListener(OnJDScrollListener onJDScrollListener) {
        this.onScrollListener = onJDScrollListener;
    }
}
