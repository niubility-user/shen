package com.jingdong.app.mall.bundle.jd_component.round;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/* loaded from: classes2.dex */
public class DrawRoundView implements ICornerView {
    private float bottomLeftRadius;
    private float bottomRightRadius;
    private int height;
    private int left;
    private Paint roundPaint;
    private int top;
    private float topLeftRadius;
    private float topRightRadius;
    private int width;

    public DrawRoundView() {
        Paint paint = new Paint();
        this.roundPaint = paint;
        paint.setColor(-1);
        this.roundPaint.setAntiAlias(true);
        this.roundPaint.setStyle(Paint.Style.FILL);
        this.roundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawBottomLeft(Canvas canvas) {
        if (canvas == null || this.bottomLeftRadius <= 0.0f) {
            return;
        }
        Path path = new Path();
        path.moveTo(this.left, (this.top + this.height) - this.bottomLeftRadius);
        path.lineTo(this.left, this.top + this.height);
        path.lineTo(this.left + this.bottomLeftRadius, this.top + this.height);
        int i2 = this.height;
        float f2 = this.bottomLeftRadius;
        path.arcTo(new RectF(this.left, i2 - (f2 * 2.0f), f2 * 2.0f, this.top + i2), 90.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.roundPaint);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawBottomRight(Canvas canvas) {
        if (canvas == null || this.bottomRightRadius <= 0.0f) {
            return;
        }
        Path path = new Path();
        path.moveTo((this.left + this.width) - this.bottomRightRadius, this.top + this.height);
        path.lineTo(this.left + this.width, this.top + this.height);
        path.lineTo(this.left + this.width, (this.top + this.height) - this.bottomRightRadius);
        int i2 = this.width;
        float f2 = this.bottomRightRadius;
        path.arcTo(new RectF(i2 - (f2 * 2.0f), this.height - (f2 * 2.0f), this.left + i2, this.top + r7), 0.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.roundPaint);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawCorner(Canvas canvas) {
        drawTopLeft(canvas);
        drawTopRight(canvas);
        drawBottomLeft(canvas);
        drawBottomRight(canvas);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawTopLeft(Canvas canvas) {
        if (canvas == null || this.topLeftRadius <= 0.0f) {
            return;
        }
        Path path = new Path();
        path.moveTo(this.left, this.top + this.topLeftRadius);
        path.lineTo(this.left, this.top);
        path.lineTo(this.left + this.topLeftRadius, this.top);
        float f2 = this.topLeftRadius;
        path.arcTo(new RectF(this.left, this.top, f2 * 2.0f, f2 * 2.0f), -90.0f, -90.0f);
        path.close();
        canvas.drawPath(path, this.roundPaint);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void drawTopRight(Canvas canvas) {
        if (canvas == null || this.topRightRadius <= 0.0f) {
            return;
        }
        Path path = new Path();
        path.moveTo((this.left + this.width) - this.topRightRadius, this.top);
        path.lineTo(this.left + this.width, this.top);
        path.lineTo(this.left + this.width, this.top + this.topRightRadius);
        int i2 = this.width;
        float f2 = this.topRightRadius;
        path.arcTo(new RectF(i2 - (f2 * 2.0f), this.top, this.left + i2, f2 * 2.0f), 0.0f, -90.0f);
        path.close();
        canvas.drawPath(path, this.roundPaint);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public Path getPath() {
        Path path = new Path();
        float f2 = this.topLeftRadius;
        float f3 = this.topRightRadius;
        float f4 = this.bottomRightRadius;
        float f5 = this.bottomLeftRadius;
        float[] fArr = {f2, f2, f3, f3, f4, f4, f5, f5};
        path.addRoundRect(this.left, this.top, r0 + this.width, r2 + this.height, fArr, Path.Direction.CCW);
        return path;
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public Path getShadowPath(float f2, float f3) {
        Path path = new Path();
        float f4 = this.topLeftRadius;
        float f5 = this.topRightRadius;
        float f6 = this.bottomRightRadius;
        float f7 = this.bottomLeftRadius;
        float[] fArr = {f4, f4, f5, f5, f6, f6, f7, f7};
        int i2 = this.left;
        int i3 = this.top;
        path.addRoundRect(i2 + f2, i3 + f3, f2 + i2 + this.width, f3 + i3 + this.height, fArr, Path.Direction.CCW);
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
        setRadius(f2, f2, f2, f2);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void setRatio(float f2) {
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.round.ICornerView
    public void setRadius(float f2, float f3, float f4, float f5) {
        this.topLeftRadius = f2;
        this.topRightRadius = f3;
        this.bottomLeftRadius = f4;
        this.bottomRightRadius = f5;
    }

    public DrawRoundView(float f2, float f3, float f4, float f5) {
        this();
        this.topLeftRadius = f2;
        this.topRightRadius = f3;
        this.bottomLeftRadius = f4;
        this.bottomRightRadius = f5;
    }
}
