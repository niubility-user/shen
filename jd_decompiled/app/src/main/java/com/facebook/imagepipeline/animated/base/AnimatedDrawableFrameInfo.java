package com.facebook.imagepipeline.animated.base;

/* loaded from: classes.dex */
public class AnimatedDrawableFrameInfo {
    public final BlendOperation blendOperation;
    public final DisposalMethod disposalMethod;
    public final int frameNumber;
    public final int height;
    public final int width;
    public final int xOffset;
    public final int yOffset;

    /* loaded from: classes.dex */
    public enum BlendOperation {
        BLEND_WITH_PREVIOUS,
        NO_BLEND
    }

    /* loaded from: classes.dex */
    public enum DisposalMethod {
        DISPOSE_DO_NOT,
        DISPOSE_TO_BACKGROUND,
        DISPOSE_TO_PREVIOUS
    }

    public AnimatedDrawableFrameInfo(int i2, int i3, int i4, int i5, int i6, BlendOperation blendOperation, DisposalMethod disposalMethod) {
        this.frameNumber = i2;
        this.xOffset = i3;
        this.yOffset = i4;
        this.width = i5;
        this.height = i6;
        this.blendOperation = blendOperation;
        this.disposalMethod = disposalMethod;
    }
}
