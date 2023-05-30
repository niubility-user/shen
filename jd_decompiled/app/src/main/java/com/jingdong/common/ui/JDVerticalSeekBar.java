package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/* loaded from: classes6.dex */
public class JDVerticalSeekBar extends SeekBar {
    public JDVerticalSeekBar(Context context) {
        super(context);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.rotate(-90.0f);
        canvas.translate(-getHeight(), 0.0f);
        super.onDraw(canvas);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i2, int i3) {
        super.onMeasure(i3, i2);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i3, i2, i5, i4);
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            int action = motionEvent.getAction();
            if (action == 0 || action == 1 || action == 2) {
                setProgress(getMax() - ((int) ((getMax() * motionEvent.getY()) / getHeight())));
                onSizeChanged(getWidth(), getHeight(), 0, 0);
            }
            return true;
        }
        return false;
    }

    @Override // android.widget.ProgressBar
    public void setProgress(int i2) {
        super.setProgress(i2);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
    }

    public JDVerticalSeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public JDVerticalSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
