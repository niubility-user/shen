package com.jingdong.app.mall.bundle.jd_component.round;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/* loaded from: classes2.dex */
public class DrawSquircleView implements ICornerView {
    int left;
    private int mHeight;
    private float mLeftBottom;
    private float mLeftTop;
    protected float mMaxRadius;
    private float mRatio;
    private float mRightBottom;
    private float mRightTop;
    private int mWidth;
    private final Paint roundPaint;
    int top;

    public DrawSquircleView() {
        this.mRatio = 1.0f;
        Paint paint = new Paint();
        this.roundPaint = paint;
        paint.setColor(-1);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawBottomLeft(Canvas canvas) {
        float min = Math.min(this.mMaxRadius, this.mLeftBottom);
        if (canvas == null || min <= 0.0f) {
            return;
        }
        float f2 = this.mRatio * min;
        Path path = new Path();
        path.moveTo(this.left, (this.top + this.mHeight) - min);
        int i2 = this.left;
        int i3 = this.top;
        int i4 = this.mHeight;
        path.cubicTo(i2, ((i3 + i4) + f2) - min, (i2 + min) - f2, i3 + i4, min + i2, i3 + i4);
        path.lineTo(this.left, this.top + this.mHeight);
        path.close();
        canvas.drawPath(path, this.roundPaint);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawBottomRight(Canvas canvas) {
        float min = Math.min(this.mMaxRadius, this.mRightBottom);
        if (canvas == null || min <= 0.0f) {
            return;
        }
        float f2 = this.mRatio * min;
        Path path = new Path();
        path.moveTo((this.left + this.mWidth) - min, this.top + this.mHeight);
        int i2 = this.left;
        int i3 = this.mWidth;
        int i4 = this.top;
        int i5 = this.mHeight;
        path.cubicTo(((i2 + i3) + f2) - min, i4 + i5, i2 + i3, ((i4 + i5) + f2) - min, i2 + i3, (i4 + i5) - min);
        path.lineTo(this.left + this.mWidth, this.top + this.mHeight);
        path.close();
        canvas.drawPath(path, this.roundPaint);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawCorner(Canvas canvas) {
        canvas.drawPath(getPath(), this.roundPaint);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawTopLeft(Canvas canvas) {
        float min = Math.min(this.mMaxRadius, this.mLeftTop);
        if (canvas == null || min <= 0.0f) {
            return;
        }
        float f2 = this.mRatio * min;
        Path path = new Path();
        path.moveTo(this.left, this.top + min);
        int i2 = this.left;
        int i3 = this.top;
        path.cubicTo(i2, (i3 + min) - f2, (i2 + min) - f2, i3, i2 + min, i3);
        path.lineTo(this.left, this.top);
        path.close();
        canvas.drawPath(path, this.roundPaint);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawTopRight(Canvas canvas) {
        float min = Math.min(this.mMaxRadius, this.mRightTop);
        if (canvas == null || min <= 0.0f) {
            return;
        }
        float f2 = this.mRatio * min;
        Path path = new Path();
        path.moveTo((this.left + this.mWidth) - min, this.top);
        int i2 = this.left;
        int i3 = this.mWidth;
        int i4 = this.top;
        path.cubicTo(((i2 + i3) + f2) - min, i4, i2 + i3, (i4 + min) - f2, i2 + i3, min + i4);
        path.lineTo(this.left + this.mWidth, this.top);
        path.close();
        canvas.drawPath(path, this.roundPaint);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public Path getPath() {
        float min = Math.min(this.mMaxRadius, this.mLeftTop);
        float f2 = this.mRatio * min;
        Path path = new Path();
        path.moveTo(this.left, this.top + min);
        int i2 = this.left;
        int i3 = this.top;
        path.cubicTo(i2, (i3 + min) - f2, (i2 + min) - f2, i3, i2 + min, i3);
        path.lineTo(this.left, this.top);
        path.close();
        float min2 = Math.min(this.mMaxRadius, this.mRightTop);
        float f3 = this.mRatio * min2;
        path.moveTo((this.left + this.mWidth) - min2, this.top);
        int i4 = this.left;
        int i5 = this.mWidth;
        int i6 = this.top;
        path.cubicTo(((i4 + i5) + f3) - min2, i6, i4 + i5, (i6 + min2) - f3, i4 + i5, min2 + i6);
        path.lineTo(this.left + this.mWidth, this.top);
        path.close();
        float min3 = Math.min(this.mMaxRadius, this.mRightBottom);
        float f4 = this.mRatio * min3;
        path.moveTo(this.left + this.mWidth, (this.top + this.mHeight) - min3);
        int i7 = this.left;
        int i8 = this.mWidth;
        int i9 = this.top;
        int i10 = this.mHeight;
        path.cubicTo(i7 + i8, ((i9 + i10) + f4) - min3, ((i7 + i8) + f4) - min3, i9 + i10, (i7 + i8) - min3, i9 + i10);
        path.lineTo(this.left + this.mWidth, this.top + this.mHeight);
        path.close();
        float min4 = Math.min(this.mMaxRadius, this.mLeftBottom);
        float f5 = this.mRatio * min4;
        path.moveTo(this.left + min4, this.top + this.mHeight);
        int i11 = this.left;
        int i12 = this.top;
        int i13 = this.mHeight;
        path.cubicTo((i11 + min4) - f5, i12 + i13, i11, ((i12 + i13) + f5) - min4, i11, (i12 + i13) - min4);
        path.lineTo(this.left, this.top + this.mHeight);
        path.close();
        return path;
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public Path getShadowPath(float f2, float f3) {
        this.left = (int) (this.left + f2);
        this.top = (int) (this.top + f3);
        float min = Math.min(this.mMaxRadius, this.mLeftTop);
        float f4 = this.mRatio * min;
        Path path = new Path();
        path.moveTo(this.left, this.top + min);
        int i2 = this.left;
        int i3 = this.top;
        path.cubicTo(i2, (i3 + min) - f4, (i2 + min) - f4, i3, i2 + min, i3);
        float min2 = Math.min(this.mMaxRadius, this.mRightTop);
        float f5 = this.mRatio * min2;
        path.lineTo((this.left + this.mWidth) - min2, this.top);
        int i4 = this.left;
        int i5 = this.mWidth;
        int i6 = this.top;
        path.cubicTo(((i4 + i5) + f5) - min2, i6, i4 + i5, (i6 + min2) - f5, i4 + i5, min2 + i6);
        float min3 = Math.min(this.mMaxRadius, this.mRightBottom);
        float f6 = this.mRatio * min3;
        path.lineTo(this.left + this.mWidth, (this.top + this.mHeight) - min3);
        int i7 = this.left;
        int i8 = this.mWidth;
        int i9 = this.top;
        int i10 = this.mHeight;
        path.cubicTo(i7 + i8, ((i9 + i10) + f6) - min3, ((i7 + i8) + f6) - min3, i9 + i10, (i7 + i8) - min3, i9 + i10);
        float min4 = Math.min(this.mMaxRadius, this.mLeftBottom);
        float f7 = this.mRatio * min4;
        path.lineTo(this.left + min4, this.top + this.mHeight);
        int i11 = this.left;
        int i12 = this.top;
        int i13 = this.mHeight;
        path.cubicTo((i11 + min4) - f7, i12 + i13, i11, ((i12 + i13) + f7) - min4, i11, (i12 + i13) - min4);
        path.close();
        this.left = (int) (this.left - f2);
        this.top = (int) (this.top - f3);
        return path;
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void onSizeChanged(int i2, int i3) {
        this.mWidth = i2;
        this.mHeight = i3;
        this.mMaxRadius = Math.min(i2, i3) / 2.0f;
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void setPadding(int i2, int i3) {
        this.left = i2;
        this.top = i3;
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void setRadius(float f2) {
        setRadius(f2, f2, f2, f2);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void setRatio(float f2) {
        this.mRatio = Math.max(0.0f, Math.min(1.0f, f2));
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void setRadius(float f2, float f3, float f4, float f5) {
        this.mLeftTop = f2;
        this.mRightTop = f3;
        this.mLeftBottom = f4;
        this.mRightBottom = f5;
    }

    public DrawSquircleView(float f2, float f3, float f4, float f5) {
        this();
        this.mLeftTop = f2;
        this.mRightTop = f3;
        this.mLeftBottom = f4;
        this.mRightBottom = f5;
    }
}
