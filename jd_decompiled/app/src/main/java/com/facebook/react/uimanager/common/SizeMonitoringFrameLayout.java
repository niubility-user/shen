package com.facebook.react.uimanager.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class SizeMonitoringFrameLayout extends FrameLayout {
    @Nullable
    private OnSizeChangedListener mOnSizeChangedListener;

    /* loaded from: classes12.dex */
    public interface OnSizeChangedListener {
        void onSizeChanged(int i2, int i3, int i4, int i5);
    }

    public SizeMonitoringFrameLayout(Context context) {
        super(context);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        OnSizeChangedListener onSizeChangedListener = this.mOnSizeChangedListener;
        if (onSizeChangedListener != null) {
            onSizeChangedListener.onSizeChanged(i2, i3, i4, i5);
        }
    }

    public void setOnSizeChangedListener(OnSizeChangedListener onSizeChangedListener) {
        this.mOnSizeChangedListener = onSizeChangedListener;
    }

    public SizeMonitoringFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SizeMonitoringFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
