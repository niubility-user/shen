package com.jingdong.common.widget.shadow.strategy;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.widget.shadow.ShadowLayout;
import com.jingdong.common.widget.shadow.engine.ShadowEngine;

/* loaded from: classes12.dex */
public abstract class BaseShadowStrategy implements ShadowStrategy {
    protected Context mContext;
    protected ShadowLayout mParent;
    protected float mShadowAlpha;
    protected Path mShadowClipPath;
    protected ColorStateList mShadowColor;
    protected ShadowEngine mShadowEngine;
    protected int mShadowOffsetDx;
    protected int mShadowOffsetDy;
    protected View mShadowOriginView;
    protected int mShadowRadius;
    protected boolean mShadowEnable = true;
    protected boolean mShadowClipCanvas = true;

    public BaseShadowStrategy(ShadowLayout shadowLayout, Context context, AttributeSet attributeSet) {
        this.mParent = shadowLayout;
        shadowLayout.setClipToPadding(false);
        this.mParent.setLayerType(1, null);
        this.mContext = context.getApplicationContext();
        initBaseConfig(attributeSet);
    }

    private void defaultConfig() {
        this.mShadowEnable = true;
        this.mShadowClipCanvas = true;
        this.mShadowColor = ColorStateList.valueOf(-12303292);
        this.mShadowOffsetDx = 0;
        this.mShadowOffsetDy = 0;
        this.mShadowRadius = 0;
        this.mShadowAlpha = 0.4f;
    }

    private void initBaseConfig(AttributeSet attributeSet) {
        defaultConfig();
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.ShadowLayout);
        this.mShadowEnable = obtainStyledAttributes.getBoolean(R.styleable.ShadowLayout_shadowEnable, this.mShadowEnable);
        this.mShadowClipCanvas = obtainStyledAttributes.getBoolean(R.styleable.ShadowLayout_shadowClipCanvas, this.mShadowClipCanvas);
        this.mShadowColor = obtainStyledAttributes.getColorStateList(R.styleable.ShadowLayout_shadowColor);
        this.mShadowOffsetDx = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShadowLayout_shadowOffsetDx, this.mShadowOffsetDx);
        this.mShadowOffsetDy = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShadowLayout_shadowOffsetDy, this.mShadowOffsetDy);
        this.mShadowRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShadowLayout_shadowRadius, this.mShadowRadius);
        float f2 = obtainStyledAttributes.getFloat(R.styleable.ShadowLayout_shadowAlpha, this.mShadowAlpha);
        this.mShadowAlpha = f2;
        float max = Math.max(0.0f, f2);
        this.mShadowAlpha = max;
        this.mShadowAlpha = Math.min(1.0f, max);
        ColorStateList colorStateList = this.mShadowColor;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(-12303292);
        }
        this.mShadowColor = colorStateList;
        obtainStyledAttributes.recycle();
    }

    public int adjustAlpha(float f2, int i2) {
        return Color.argb((int) (f2 * 255.0f), Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    public Context getContext() {
        return this.mContext;
    }

    public ShadowLayout getParent() {
        return this.mParent;
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public float getShadowAlpha() {
        return this.mShadowAlpha;
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public int getShadowColor() {
        return getStateShadowColor();
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public int getShadowOffsetDx() {
        return this.mShadowOffsetDx;
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public int getShadowOffsetDy() {
        return this.mShadowOffsetDy;
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public int getShadowRadius() {
        return this.mShadowRadius;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getStateShadowColor() {
        int defaultColor = this.mShadowColor.getDefaultColor();
        View view = this.mShadowOriginView;
        if (view != null) {
            defaultColor = this.mShadowColor.getColorForState(view.getDrawableState(), defaultColor);
        }
        return adjustAlpha(this.mShadowAlpha, defaultColor);
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void invalidateShadow() {
        this.mParent.invalidate();
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public boolean isShadowClipCanvas() {
        return this.mShadowClipCanvas;
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public boolean isShadowEnable() {
        return this.mShadowEnable;
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void refreshShadow(View view) {
        this.mShadowOriginView = view;
        this.mShadowEngine.setShadowColor(getStateShadowColor());
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void setClipPath(Path path) {
        this.mShadowClipPath = path;
        this.mShadowEngine.setClipPath(path);
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void setCornerRadii(float[] fArr) {
    }

    public void setParent(ShadowLayout shadowLayout) {
        this.mParent = shadowLayout;
        shadowLayout.setClipToPadding(false);
        this.mParent.setLayerType(1, null);
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void setShadowAlpha(float f2) {
        this.mShadowAlpha = f2;
        this.mShadowAlpha = Math.max(0.0f, f2);
        float min = Math.min(1.0f, f2);
        this.mShadowAlpha = min;
        this.mShadowEngine.setShadowColor(adjustAlpha(min, getStateShadowColor()));
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void setShadowClipCanvas(boolean z) {
        this.mShadowClipCanvas = z;
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void setShadowColor(ColorStateList colorStateList) {
        this.mShadowColor = colorStateList;
        this.mShadowEngine.setShadowColor(adjustAlpha(this.mShadowAlpha, getStateShadowColor()));
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void setShadowEnable(boolean z) {
        this.mShadowEnable = z;
        this.mShadowEngine.setShadowEnable(z);
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void setShadowOffsetDx(int i2) {
        this.mShadowOffsetDx = i2;
        this.mShadowEngine.setShadowOffsetDx(i2);
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void setShadowOffsetDy(int i2) {
        this.mShadowOffsetDy = i2;
        this.mShadowEngine.setShadowOffsetDy(i2);
    }

    @Override // com.jingdong.common.widget.shadow.strategy.ShadowStrategy
    public void setShadowRadius(int i2) {
        this.mShadowRadius = i2;
        this.mShadowEngine.setShadowRadius(i2);
    }
}
