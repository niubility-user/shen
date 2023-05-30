package com.jd.lib.productdetail.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/* loaded from: classes15.dex */
public class GiftWrapRelativeLayout extends RelativeLayout {
    private OnDispatchTouchListener listener;

    /* loaded from: classes15.dex */
    public interface OnDispatchTouchListener {
        void doDispatch();

        boolean isNeedDispatch();
    }

    public GiftWrapRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        OnDispatchTouchListener onDispatchTouchListener = this.listener;
        if (onDispatchTouchListener != null && onDispatchTouchListener.isNeedDispatch()) {
            this.listener.doDispatch();
            return false;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setListener(OnDispatchTouchListener onDispatchTouchListener) {
        this.listener = onDispatchTouchListener;
    }

    public GiftWrapRelativeLayout(Context context) {
        super(context);
    }
}
