package com.facebook.fresco.animation.backend;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.IntRange;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface AnimationBackend extends AnimationInformation {
    public static final int INTRINSIC_DIMENSION_UNSET = -1;

    void clear();

    boolean drawFrame(Drawable drawable, Canvas canvas, int i2);

    int getIntrinsicHeight();

    int getIntrinsicWidth();

    int getSizeInBytes();

    void setAlpha(@IntRange(from = 0, to = 255) int i2);

    void setBounds(Rect rect);

    void setColorFilter(@Nullable ColorFilter colorFilter);
}
