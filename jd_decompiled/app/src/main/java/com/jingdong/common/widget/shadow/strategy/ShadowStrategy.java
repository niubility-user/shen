package com.jingdong.common.widget.shadow.strategy;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Path;
import android.view.View;

/* loaded from: classes12.dex */
public interface ShadowStrategy {
    float getShadowAlpha();

    int getShadowColor();

    int getShadowOffsetDx();

    int getShadowOffsetDy();

    int getShadowRadius();

    void invalidateShadow();

    boolean isShadowClipCanvas();

    boolean isShadowEnable();

    void onAttachToWindow();

    boolean onClipCanvas(Canvas canvas, View view);

    void onDetachedFromWindow();

    void onDraw(Canvas canvas);

    void onDrawOver(Canvas canvas);

    void onLayout(boolean z, int i2, int i3, int i4, int i5);

    void refreshShadow(View view);

    void setClipPath(Path path);

    void setCornerRadii(float[] fArr);

    void setShadowAlpha(float f2);

    void setShadowClipCanvas(boolean z);

    void setShadowColor(ColorStateList colorStateList);

    void setShadowEnable(boolean z);

    void setShadowOffsetDx(int i2);

    void setShadowOffsetDy(int i2);

    void setShadowRadius(int i2);
}
