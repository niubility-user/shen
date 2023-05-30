package com.jingdong.common.XView2.container;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;

/* loaded from: classes5.dex */
public class ContentFrameLayout extends FrameLayout {
    private boolean controlDispatchTouchEvent;
    private boolean dispatchTouchEventResult;

    public ContentFrameLayout(@NonNull Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.controlDispatchTouchEvent) {
            return this.dispatchTouchEventResult;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setControlDispatchTouchEvent(boolean z, boolean z2) {
        this.controlDispatchTouchEvent = z;
        this.dispatchTouchEventResult = z2;
    }
}
