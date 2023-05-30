package com.facebook.imageutils;

import android.graphics.ColorSpace;
import android.util.Pair;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImageMetaData {
    @Nullable
    private final ColorSpace mColorSpace;
    @Nullable
    private final Pair<Integer, Integer> mDimensions;

    public ImageMetaData(int i2, int i3, @Nullable ColorSpace colorSpace) {
        this.mDimensions = (i2 == -1 || i3 == -1) ? null : new Pair<>(Integer.valueOf(i2), Integer.valueOf(i3));
        this.mColorSpace = colorSpace;
    }

    @Nullable
    public ColorSpace getColorSpace() {
        return this.mColorSpace;
    }

    @Nullable
    public Pair<Integer, Integer> getDimensions() {
        return this.mDimensions;
    }
}
