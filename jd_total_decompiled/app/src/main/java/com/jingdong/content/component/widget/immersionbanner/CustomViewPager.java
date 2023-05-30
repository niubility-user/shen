package com.jingdong.content.component.widget.immersionbanner;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes12.dex */
public class CustomViewPager extends ContentLoopViewPager {
    private float customOffset;

    public CustomViewPager(@NonNull Context context) {
        super(context, null);
    }

    @Override // com.jingdong.content.component.widget.immersionbanner.ContentLoopViewPager, android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float f2 = this.customOffset;
        if (f2 != 0.0f) {
            motionEvent.offsetLocation(-f2, 0.0f);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public void draw(Canvas canvas) {
        if (this.customOffset != 0.0f) {
            canvas.save();
            canvas.translate(this.customOffset, 0.0f);
            super.draw(canvas);
            canvas.restore();
            return;
        }
        super.draw(canvas);
    }

    public void setCustomOffset(float f2) {
        this.customOffset = f2;
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
