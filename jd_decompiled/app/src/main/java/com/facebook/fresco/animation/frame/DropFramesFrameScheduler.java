package com.facebook.fresco.animation.frame;

import com.facebook.common.internal.VisibleForTesting;
import com.facebook.fresco.animation.backend.AnimationInformation;

/* loaded from: classes.dex */
public class DropFramesFrameScheduler implements FrameScheduler {
    private static final int UNSET = -1;
    private final AnimationInformation mAnimationInformation;
    private long mLoopDurationMs = -1;

    public DropFramesFrameScheduler(AnimationInformation animationInformation) {
        this.mAnimationInformation = animationInformation;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public int getFrameNumberToRender(long j2, long j3) {
        long loopDurationMs = getLoopDurationMs();
        if (loopDurationMs == 0) {
            return getFrameNumberWithinLoop(0L);
        }
        if (isInfiniteAnimation() || j2 / loopDurationMs < this.mAnimationInformation.getLoopCount()) {
            return getFrameNumberWithinLoop(j2 % loopDurationMs);
        }
        return -1;
    }

    @VisibleForTesting
    int getFrameNumberWithinLoop(long j2) {
        int i2 = 0;
        long j3 = 0;
        do {
            j3 += this.mAnimationInformation.getFrameDurationMs(i2);
            i2++;
        } while (j2 >= j3);
        return i2 - 1;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public long getLoopDurationMs() {
        long j2 = this.mLoopDurationMs;
        if (j2 != -1) {
            return j2;
        }
        this.mLoopDurationMs = 0L;
        int frameCount = this.mAnimationInformation.getFrameCount();
        for (int i2 = 0; i2 < frameCount; i2++) {
            this.mLoopDurationMs += this.mAnimationInformation.getFrameDurationMs(i2);
        }
        return this.mLoopDurationMs;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public long getTargetRenderTimeForNextFrameMs(long j2) {
        long loopDurationMs = getLoopDurationMs();
        long j3 = 0;
        if (loopDurationMs == 0) {
            return -1L;
        }
        if (isInfiniteAnimation() || j2 / getLoopDurationMs() < this.mAnimationInformation.getLoopCount()) {
            long j4 = j2 % loopDurationMs;
            int frameCount = this.mAnimationInformation.getFrameCount();
            for (int i2 = 0; i2 < frameCount && j3 <= j4; i2++) {
                j3 += this.mAnimationInformation.getFrameDurationMs(i2);
            }
            return j2 + (j3 - j4);
        }
        return -1L;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public long getTargetRenderTimeMs(int i2) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j2 += this.mAnimationInformation.getFrameDurationMs(i2);
        }
        return j2;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public boolean isInfiniteAnimation() {
        return this.mAnimationInformation.getLoopCount() == 0;
    }
}
