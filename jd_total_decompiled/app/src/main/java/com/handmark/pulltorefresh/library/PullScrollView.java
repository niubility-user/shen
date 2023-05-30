package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/* loaded from: classes12.dex */
public class PullScrollView extends ScrollView {
    private OnMyScrollListener onScrollListener;

    /* loaded from: classes12.dex */
    public interface OnMyScrollListener {
        void onScroll(int i2, int i3, int i4, int i5);
    }

    public PullScrollView(Context context) {
        super(context);
    }

    public OnMyScrollListener getOnScrollListener() {
        return this.onScrollListener;
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        OnMyScrollListener onMyScrollListener = this.onScrollListener;
        if (onMyScrollListener != null) {
            onMyScrollListener.onScroll(i2, i3, i4, i5);
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Exception unused) {
            return false;
        }
    }

    public void setOnMyScrollListener(OnMyScrollListener onMyScrollListener) {
        this.onScrollListener = onMyScrollListener;
    }

    public PullScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullScrollView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
