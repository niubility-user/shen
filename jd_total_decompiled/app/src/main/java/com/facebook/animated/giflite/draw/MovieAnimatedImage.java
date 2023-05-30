package com.facebook.animated.giflite.draw;

import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.animated.base.AnimatedImageFrame;

/* loaded from: classes.dex */
public class MovieAnimatedImage implements AnimatedImage {
    private final int mDuration;
    private final int[] mFrameDurations;
    private final MovieFrame[] mFrames;
    private final int mLoopCount;
    private final int mSizeInBytes;

    public MovieAnimatedImage(MovieFrame[] movieFrameArr, int i2, int i3, int i4) {
        this.mFrames = movieFrameArr;
        this.mSizeInBytes = i2;
        this.mDuration = i3;
        this.mLoopCount = i4;
        this.mFrameDurations = new int[movieFrameArr.length];
        int length = movieFrameArr.length;
        for (int i5 = 0; i5 < length; i5++) {
            this.mFrameDurations[i5] = this.mFrames[i5].getDurationMs();
        }
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public void dispose() {
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public boolean doesRenderSupportScaling() {
        return true;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getDuration() {
        return this.mDuration;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public AnimatedImageFrame getFrame(int i2) {
        return this.mFrames[i2];
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getFrameCount() {
        return this.mFrames.length;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int[] getFrameDurations() {
        return this.mFrameDurations;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public AnimatedDrawableFrameInfo getFrameInfo(int i2) {
        MovieFrame movieFrame = this.mFrames[i2];
        return new AnimatedDrawableFrameInfo(i2, movieFrame.getXOffset(), movieFrame.getYOffset(), movieFrame.getWidth(), movieFrame.getHeight(), AnimatedDrawableFrameInfo.BlendOperation.BLEND_WITH_PREVIOUS, this.mFrames[i2].getDisposalMode());
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getHeight() {
        return this.mFrames[0].getHeight();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getLoopCount() {
        return this.mLoopCount;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getSizeInBytes() {
        return this.mSizeInBytes;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getWidth() {
        return this.mFrames[0].getWidth();
    }
}
