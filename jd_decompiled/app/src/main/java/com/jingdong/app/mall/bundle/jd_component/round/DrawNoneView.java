package com.jingdong.app.mall.bundle.jd_component.round;

import android.graphics.Canvas;
import android.graphics.Path;

/* loaded from: classes2.dex */
public class DrawNoneView implements ICornerView {
    private int height;
    private int left;
    private int top;
    private int width;

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawBottomLeft(Canvas canvas) {
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawBottomRight(Canvas canvas) {
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawCorner(Canvas canvas) {
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawTopLeft(Canvas canvas) {
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawTopRight(Canvas canvas) {
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public Path getPath() {
        Path path = new Path();
        path.addRect(this.left, this.top, r0 + this.width, r2 + this.height, Path.Direction.CCW);
        return path;
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public Path getShadowPath(float f2, float f3) {
        Path path = new Path();
        path.addRect(this.left + f2, this.top + f3, f2 + r0 + this.width, r2 + this.height + f3, Path.Direction.CCW);
        return path;
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void onSizeChanged(int i2, int i3) {
        this.width = i2;
        this.height = i3;
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void setPadding(int i2, int i3) {
        this.left = i2;
        this.top = i3;
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void setRadius(float f2) {
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void setRadius(float f2, float f3, float f4, float f5) {
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void setRatio(float f2) {
    }
}
