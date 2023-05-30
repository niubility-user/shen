package com.jingdong.common.widget.shadow.strategy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.jingdong.common.widget.shadow.ShadowLayout;
import com.jingdong.common.widget.shadow.engine.AutoFitShadowEngine;

/* loaded from: classes12.dex */
public class AutoFitShadowStrategy extends BaseShadowStrategy {
    private Rect mShadowDrawingRect;

    public AutoFitShadowStrategy(ShadowLayout shadowLayout, Context context, AttributeSet attributeSet) {
        super(shadowLayout, context, attributeSet);
        initConfig(attributeSet);
    }

    private void configDrawingRect() {
        Rect rect = this.mShadowDrawingRect;
        rect.left = 0;
        rect.top = 0;
        rect.right = this.mParent.getMeasuredWidth();
        this.mShadowDrawingRect.bottom = this.mParent.getMeasuredHeight();
    }

    private void configShadowEngineParams() {
        this.mShadowEngine.setParameter(this.mParent, getStateShadowColor(), this.mShadowRadius, this.mShadowOffsetDx, this.mShadowOffsetDy, this.mShadowDrawingRect);
    }

    private int getPaddingLeftAndRight() {
        int i2 = this.mShadowOffsetDx;
        if (i2 <= 0) {
            i2 = -i2;
        }
        return this.mShadowRadius + i2;
    }

    private int getPaddingTopAndBottom() {
        int i2 = this.mShadowOffsetDy;
        if (i2 <= 0) {
            i2 = -i2;
        }
        return this.mShadowRadius + i2;
    }

    private void initConfig(AttributeSet attributeSet) {
        AutoFitShadowEngine autoFitShadowEngine = new AutoFitShadowEngine();
        this.mShadowEngine = autoFitShadowEngine;
        autoFitShadowEngine.setShadowEnable(this.mShadowEnable);
        this.mShadowDrawingRect = new Rect();
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void onAttachToWindow() {
        int paddingLeftAndRight = getPaddingLeftAndRight();
        int paddingTopAndBottom = getPaddingTopAndBottom();
        this.mParent.setPadding(paddingLeftAndRight, paddingTopAndBottom, paddingLeftAndRight, paddingTopAndBottom);
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public boolean onClipCanvas(Canvas canvas, View view) {
        return this.mShadowEngine.onClipChildCanvas(canvas, view);
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void onDetachedFromWindow() {
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void onDraw(Canvas canvas) {
        this.mShadowEngine.onDraw(canvas);
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void onDrawOver(Canvas canvas) {
        this.mShadowEngine.onDrawOver(canvas);
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        configDrawingRect();
        configShadowEngineParams();
        this.mShadowEngine.onLayout(this.mParent, i2, i3, i4, i5);
    }
}
