package com.jingdong.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

/* loaded from: classes6.dex */
public class JDGallery extends Gallery {
    public JDGallery(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void initPressed() {
        int childCount = getChildCount();
        while (true) {
            childCount--;
            if (childCount >= 0) {
                getChildAt(childCount).setPressed(false);
            } else {
                setPressed(false);
                return;
            }
        }
    }

    @Override // android.widget.Gallery, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        boolean onSingleTapUp = super.onSingleTapUp(motionEvent);
        onDown(motionEvent);
        return onSingleTapUp;
    }

    @Override // android.widget.Gallery, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 1 || action == 3) {
            initPressed();
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                getChildAt(childCount).onTouchEvent(motionEvent);
            }
        }
        return true;
    }

    public JDGallery(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public JDGallery(Context context) {
        super(context);
    }
}
