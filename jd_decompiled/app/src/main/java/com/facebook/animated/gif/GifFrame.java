package com.facebook.animated.gif;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.imagepipeline.animated.base.AnimatedImageFrame;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* loaded from: classes.dex */
public class GifFrame implements AnimatedImageFrame {
    @DoNotStrip
    private long mNativeContext;

    @DoNotStrip
    GifFrame(long j2) {
        this.mNativeContext = j2;
    }

    @DoNotStrip
    private native void nativeDispose();

    @DoNotStrip
    private native void nativeFinalize();

    @DoNotStrip
    private native int nativeGetDisposalMode();

    @DoNotStrip
    private native int nativeGetDurationMs();

    @DoNotStrip
    private native int nativeGetHeight();

    @DoNotStrip
    private native int nativeGetTransparentPixelColor();

    @DoNotStrip
    private native int nativeGetWidth();

    @DoNotStrip
    private native int nativeGetXOffset();

    @DoNotStrip
    private native int nativeGetYOffset();

    @DoNotStrip
    private native boolean nativeHasTransparency();

    @DoNotStrip
    private native void nativeRenderFrame(int i2, int i3, Bitmap bitmap);

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public void dispose() {
        nativeDispose();
    }

    protected void finalize() {
        nativeFinalize();
    }

    public int getDisposalMode() {
        return nativeGetDisposalMode();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getDurationMs() {
        return nativeGetDurationMs();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getHeight() {
        return nativeGetHeight();
    }

    public int getTransparentPixelColor() {
        return nativeGetTransparentPixelColor();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getWidth() {
        return nativeGetWidth();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getXOffset() {
        return nativeGetXOffset();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getYOffset() {
        return nativeGetYOffset();
    }

    public boolean hasTransparency() {
        return nativeHasTransparency();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public void renderFrame(int i2, int i3, Bitmap bitmap) {
        nativeRenderFrame(i2, i3, bitmap);
    }
}
