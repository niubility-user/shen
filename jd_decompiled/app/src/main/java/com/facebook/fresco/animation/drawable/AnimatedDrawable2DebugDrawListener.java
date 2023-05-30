package com.facebook.fresco.animation.drawable;

import com.facebook.common.logging.FLog;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.fresco.animation.frame.FrameScheduler;

/* loaded from: classes.dex */
public class AnimatedDrawable2DebugDrawListener implements AnimatedDrawable2.DrawListener {
    private static final Class<?> TAG = AnimatedDrawable2DebugDrawListener.class;
    private int mDrawCalls;
    private int mDuplicateFrames;
    private int mLastFrameNumber = -1;
    private int mSkippedFrames;

    @Override // com.facebook.fresco.animation.drawable.AnimatedDrawable2.DrawListener
    public void onDraw(AnimatedDrawable2 animatedDrawable2, FrameScheduler frameScheduler, int i2, boolean z, boolean z2, long j2, long j3, long j4, long j5, long j6, long j7, long j8) {
        if (animatedDrawable2.getAnimationBackend() == null) {
            return;
        }
        int frameCount = animatedDrawable2.getAnimationBackend().getFrameCount();
        long j9 = j3 - j4;
        this.mDrawCalls++;
        int i3 = this.mLastFrameNumber;
        int i4 = (i3 + 1) % frameCount;
        if (i4 != i2) {
            if (i3 == i2) {
                this.mDuplicateFrames++;
            } else {
                int i5 = (i2 - i4) % frameCount;
                if (i5 < 0) {
                    i5 += frameCount;
                }
                this.mSkippedFrames += i5;
            }
        }
        this.mLastFrameNumber = i2;
        FLog.d(TAG, "draw: frame: %2d, drawn: %b, delay: %3d ms, rendering: %3d ms, prev: %3d ms ago, duplicates: %3d, skipped: %3d, draw calls: %4d, anim time: %6d ms, next start: %6d ms, next scheduled: %6d ms", Integer.valueOf(i2), Boolean.valueOf(z), Long.valueOf((j3 % frameScheduler.getLoopDurationMs()) - frameScheduler.getTargetRenderTimeMs(i2)), Long.valueOf(j6 - j5), Long.valueOf(j9), Integer.valueOf(this.mDuplicateFrames), Integer.valueOf(this.mSkippedFrames), Integer.valueOf(this.mDrawCalls), Long.valueOf(j3), Long.valueOf(j7), Long.valueOf(j8));
    }
}
