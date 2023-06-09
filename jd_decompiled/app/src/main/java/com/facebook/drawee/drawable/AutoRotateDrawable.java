package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;

/* loaded from: classes.dex */
public class AutoRotateDrawable extends ForwardingDrawable implements Runnable, CloneableDrawable {
    private static final int DEGREES_IN_FULL_ROTATION = 360;
    private static final int FRAME_INTERVAL_MS = 20;
    private boolean mClockwise;
    private int mInterval;
    private boolean mIsScheduled;
    @VisibleForTesting
    float mRotationAngle;

    public AutoRotateDrawable(Drawable drawable, int i2) {
        this(drawable, i2, true);
    }

    public AutoRotateDrawable(Drawable drawable, int i2, boolean z) {
        super((Drawable) Preconditions.checkNotNull(drawable));
        this.mRotationAngle = 0.0f;
        this.mIsScheduled = false;
        this.mInterval = i2;
        this.mClockwise = z;
    }

    private int getIncrement() {
        return (int) ((20.0f / this.mInterval) * 360.0f);
    }

    private void scheduleNextFrame() {
        if (this.mIsScheduled) {
            return;
        }
        this.mIsScheduled = true;
        scheduleSelf(this, SystemClock.uptimeMillis() + 20);
    }

    @Override // com.facebook.drawee.drawable.CloneableDrawable
    public AutoRotateDrawable cloneDrawable() {
        return new AutoRotateDrawable(DrawableUtils.cloneDrawable(getDrawable()), this.mInterval, this.mClockwise);
    }

    @Override // com.facebook.drawee.drawable.ForwardingDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int save = canvas.save();
        Rect bounds = getBounds();
        int i2 = bounds.right - bounds.left;
        int i3 = bounds.bottom - bounds.top;
        float f2 = this.mRotationAngle;
        if (!this.mClockwise) {
            f2 = 360.0f - f2;
        }
        canvas.rotate(f2, r3 + (i2 / 2), r1 + (i3 / 2));
        super.draw(canvas);
        canvas.restoreToCount(save);
        scheduleNextFrame();
    }

    public void reset() {
        this.mRotationAngle = 0.0f;
        this.mIsScheduled = false;
        unscheduleSelf(this);
        invalidateSelf();
    }

    @Override // java.lang.Runnable
    public void run() {
        this.mIsScheduled = false;
        this.mRotationAngle += getIncrement();
        invalidateSelf();
    }

    public void setClockwise(boolean z) {
        this.mClockwise = z;
    }
}
