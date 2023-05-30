package com.jingdong.common.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes12.dex */
public class TransparentRelativeLayout extends RelativeLayout {
    private int firstX;
    private OnTouchUpListener mOnTouchUpListener;
    private int secondX;

    /* loaded from: classes12.dex */
    public interface OnTouchUpListener {
        void onTouchUp();
    }

    public TransparentRelativeLayout(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        OnTouchUpListener onTouchUpListener;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.firstX = (int) motionEvent.getX();
        } else if (action == 1 || action == 3) {
            int x = (int) motionEvent.getX();
            this.secondX = x;
            int i2 = x - this.firstX;
            OKLog.d("wyz", i2 + "");
            if (Math.abs(i2) <= 5 && (onTouchUpListener = this.mOnTouchUpListener) != null) {
                onTouchUpListener.onTouchUp();
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setOnTouchUpListener(OnTouchUpListener onTouchUpListener) {
        this.mOnTouchUpListener = onTouchUpListener;
    }

    public TransparentRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @TargetApi(21)
    public TransparentRelativeLayout(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    public TransparentRelativeLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
