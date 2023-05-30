package com.jingdong.common.widget.shadow.strategy;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.jingdong.common.widget.shadow.ShadowLayout;

/* loaded from: classes12.dex */
public class PathShadowStrategy extends BaseShadowStrategy {
    public PathShadowStrategy(ShadowLayout shadowLayout, Context context, AttributeSet attributeSet) {
        super(shadowLayout, context, attributeSet);
    }

    @Override // com.jingdong.common.widget.shadow.strategy.BaseShadowStrategy, com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void invalidateShadow() {
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void onAttachToWindow() {
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public boolean onClipCanvas(Canvas canvas, View view) {
        return false;
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void onDetachedFromWindow() {
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void onDraw(Canvas canvas) {
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void onDrawOver(Canvas canvas) {
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
    }
}
