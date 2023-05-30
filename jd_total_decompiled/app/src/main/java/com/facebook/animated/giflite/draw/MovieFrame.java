package com.facebook.animated.giflite.draw;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedImageFrame;

/* loaded from: classes.dex */
public class MovieFrame implements AnimatedImageFrame {
    private final AnimatedDrawableFrameInfo.DisposalMethod mDisposalMethod;
    private final int mFrameDuration;
    private final int mFrameHeight;
    private final int mFrameStart;
    private final int mFrameWidth;
    private final MovieDrawer mMovieDrawer;

    public MovieFrame(MovieDrawer movieDrawer, int i2, int i3, int i4, int i5, AnimatedDrawableFrameInfo.DisposalMethod disposalMethod) {
        this.mMovieDrawer = movieDrawer;
        this.mFrameStart = i2;
        this.mFrameDuration = i3;
        this.mFrameWidth = i4;
        this.mFrameHeight = i5;
        this.mDisposalMethod = disposalMethod;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public void dispose() {
    }

    public AnimatedDrawableFrameInfo.DisposalMethod getDisposalMode() {
        return this.mDisposalMethod;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getDurationMs() {
        return this.mFrameDuration;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getHeight() {
        return this.mFrameHeight;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getWidth() {
        return this.mFrameWidth;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getXOffset() {
        return 0;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getYOffset() {
        return 0;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public void renderFrame(int i2, int i3, Bitmap bitmap) {
        this.mMovieDrawer.drawFrame(this.mFrameStart, i2, i3, bitmap);
    }
}
