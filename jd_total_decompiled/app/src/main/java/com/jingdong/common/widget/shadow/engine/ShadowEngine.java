package com.jingdong.common.widget.shadow.engine;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;
import com.jingdong.common.widget.shadow.ShadowLayout;

/* loaded from: classes12.dex */
public interface ShadowEngine {
    boolean onClipChildCanvas(Canvas canvas, View view);

    void onDraw(Canvas canvas);

    void onDrawOver(Canvas canvas);

    void onLayout(View view, int i2, int i3, int i4, int i5);

    void release();

    void setClipPath(Path path);

    void setCornerRadii(float[] fArr);

    void setParameter(ShadowLayout shadowLayout, int i2, int i3, int i4, int i5, Rect rect);

    void setShadowColor(int i2);

    void setShadowEnable(boolean z);

    void setShadowOffsetDx(int i2);

    void setShadowOffsetDy(int i2);

    void setShadowRadius(int i2);
}
