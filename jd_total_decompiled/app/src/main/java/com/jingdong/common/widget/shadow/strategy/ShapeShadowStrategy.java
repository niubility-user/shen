package com.jingdong.common.widget.shadow.strategy;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.widget.shadow.ShadowLayout;
import com.jingdong.common.widget.shadow.engine.OvalShadowEngine;
import com.jingdong.common.widget.shadow.engine.RectShadowEngine;

/* loaded from: classes12.dex */
public class ShapeShadowStrategy extends BaseShadowStrategy {
    private Rect mShadowDrawingRect;
    private float[] mShadowRectRoundRadius;

    public ShapeShadowStrategy(ShadowLayout shadowLayout, Context context, AttributeSet attributeSet) {
        super(shadowLayout, context, attributeSet);
        initConfig(attributeSet);
    }

    private void configDrawingRect() {
        this.mShadowDrawingRect.setEmpty();
        if (this.mParent.getChildCount() > 0) {
            int childCount = this.mParent.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.mParent.getChildAt(i2);
                if (i2 == 0) {
                    this.mShadowDrawingRect.set(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
                } else {
                    this.mShadowDrawingRect.union(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
                }
            }
        }
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
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.ShadowLayout);
        if (obtainStyledAttributes.getInt(R.styleable.ShadowLayout_shadowShape, 0) == 0) {
            initRadius(attributeSet);
            RectShadowEngine rectShadowEngine = new RectShadowEngine();
            this.mShadowEngine = rectShadowEngine;
            rectShadowEngine.setCornerRadii(this.mShadowRectRoundRadius);
        } else {
            this.mShadowEngine = new OvalShadowEngine();
        }
        this.mShadowEngine.setShadowEnable(this.mShadowEnable);
        obtainStyledAttributes.recycle();
        this.mShadowDrawingRect = new Rect();
    }

    private void initRadius(AttributeSet attributeSet) {
        float dimensionPixelSize = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.ShadowLayout).getDimensionPixelSize(R.styleable.ShadowLayout_shadowRectRoundRadius, 0);
        if (dimensionPixelSize != 0.0f) {
            this.mShadowRectRoundRadius = new float[]{dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize};
            return;
        }
        float[] fArr = new float[8];
        this.mShadowRectRoundRadius = fArr;
        int i2 = R.styleable.ShadowLayout_shadowRectRoundRadiusTopLeft;
        fArr[0] = r12.getDimensionPixelSize(i2, 0);
        this.mShadowRectRoundRadius[1] = r12.getDimensionPixelSize(i2, 0);
        float[] fArr2 = this.mShadowRectRoundRadius;
        int i3 = R.styleable.ShadowLayout_shadowRectRoundRadiusTopRight;
        fArr2[2] = r12.getDimensionPixelSize(i3, 0);
        this.mShadowRectRoundRadius[3] = r12.getDimensionPixelSize(i3, 0);
        float[] fArr3 = this.mShadowRectRoundRadius;
        int i4 = R.styleable.ShadowLayout_shadowRectRoundRadiusBottomRight;
        fArr3[4] = r12.getDimensionPixelSize(i4, 0);
        this.mShadowRectRoundRadius[5] = r12.getDimensionPixelSize(i4, 0);
        float[] fArr4 = this.mShadowRectRoundRadius;
        int i5 = R.styleable.ShadowLayout_shadowRectRoundRadiusBottomLeft;
        fArr4[6] = r12.getDimensionPixelSize(i5, 0);
        this.mShadowRectRoundRadius[7] = r12.getDimensionPixelSize(i5, 0);
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void onAttachToWindow() {
        int paddingLeftAndRight = getPaddingLeftAndRight();
        int paddingTopAndBottom = getPaddingTopAndBottom();
        this.mParent.setPadding(paddingLeftAndRight, paddingTopAndBottom, paddingLeftAndRight, paddingTopAndBottom);
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public boolean onClipCanvas(Canvas canvas, View view) {
        if (this.mShadowClipCanvas) {
            return this.mShadowEngine.onClipChildCanvas(canvas, view);
        }
        return true;
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void onDetachedFromWindow() {
        this.mShadowEngine.release();
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void onDraw(Canvas canvas) {
        this.mShadowEngine.onDraw(canvas);
        this.mParent.superDispatchDraw(canvas);
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

    @Override // com.jingdong.common.widget.shadow.strategy.BaseShadowStrategy, com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void setCornerRadii(float[] fArr) {
        this.mShadowRectRoundRadius = fArr;
        this.mShadowEngine.setCornerRadii(fArr);
    }
}
