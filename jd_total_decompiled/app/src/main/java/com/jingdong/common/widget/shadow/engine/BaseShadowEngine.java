package com.jingdong.common.widget.shadow.engine;

import android.graphics.Path;
import android.graphics.Rect;
import com.jingdong.common.widget.shadow.ShadowLayout;

/* loaded from: classes12.dex */
public abstract class BaseShadowEngine implements ShadowEngine {
    protected boolean isSettingClipPath = false;
    protected ShadowLayout mParent;
    protected Rect mShadowBounds;
    protected Path mShadowClipPath;
    protected int mShadowColor;
    protected boolean mShadowEnable;
    protected int mShadowOffsetDx;
    protected int mShadowOffsetDy;
    protected Rect mShadowOriginBounds;
    protected int mShadowRadius;

    public abstract void initConfig();

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void release() {
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void setClipPath(Path path) {
        this.isSettingClipPath = true;
        this.mShadowClipPath = path;
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void setCornerRadii(float[] fArr) {
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void setParameter(ShadowLayout shadowLayout, int i2, int i3, int i4, int i5, Rect rect) {
        this.mParent = shadowLayout;
        this.mShadowColor = i2;
        this.mShadowRadius = i3;
        this.mShadowOffsetDx = i4;
        this.mShadowOffsetDy = i5;
        this.mShadowOriginBounds = rect;
        this.mShadowBounds = new Rect(rect.left + i4, rect.top + i5, rect.right + i4, rect.bottom + i5);
        initConfig();
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void setShadowColor(int i2) {
        this.mShadowColor = i2;
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void setShadowEnable(boolean z) {
        this.mShadowEnable = z;
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void setShadowOffsetDx(int i2) {
        this.mShadowOffsetDx = i2;
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void setShadowOffsetDy(int i2) {
        this.mShadowOffsetDy = i2;
    }

    @Override // com.jingdong.common.widget.shadow.engine.ShadowEngine
    public void setShadowRadius(int i2) {
        this.mShadowRadius = i2;
    }
}
