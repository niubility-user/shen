package com.jingdong.sdk.platform.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.jingdong.sdk.platform.business.personal.R;

/* loaded from: classes10.dex */
public class RoundAngleFrameLayout extends FrameLayout {
    private float bottomLeftRadius;
    private float bottomRightRadius;
    private Paint imagePaint;
    private Paint roundPaint;
    private float topLeftRadius;
    private float topRightRadius;

    public RoundAngleFrameLayout(Context context) {
        this(context, null);
    }

    private void drawBottomLeft(Canvas canvas) {
        if (this.bottomLeftRadius > 0.0f) {
            int height = getHeight();
            Path path = new Path();
            float f2 = height;
            path.moveTo(0.0f, f2 - this.bottomLeftRadius);
            path.lineTo(0.0f, f2);
            path.lineTo(this.bottomLeftRadius, f2);
            float f3 = this.bottomLeftRadius;
            path.arcTo(new RectF(0.0f, f2 - (f3 * 2.0f), f3 * 2.0f, f2), 90.0f, 90.0f);
            path.close();
            canvas.drawPath(path, this.roundPaint);
        }
    }

    private void drawBottomRight(Canvas canvas) {
        if (this.bottomRightRadius > 0.0f) {
            int height = getHeight();
            int width = getWidth();
            Path path = new Path();
            float f2 = width;
            float f3 = height;
            path.moveTo(f2 - this.bottomRightRadius, f3);
            path.lineTo(f2, f3);
            path.lineTo(f2, f3 - this.bottomRightRadius);
            float f4 = this.bottomRightRadius;
            path.arcTo(new RectF(f2 - (f4 * 2.0f), f3 - (f4 * 2.0f), f2, f3), 0.0f, 90.0f);
            path.close();
            canvas.drawPath(path, this.roundPaint);
        }
    }

    private void drawTopLeft(Canvas canvas) {
        if (this.topLeftRadius > 0.0f) {
            Path path = new Path();
            path.moveTo(0.0f, this.topLeftRadius);
            path.lineTo(0.0f, 0.0f);
            path.lineTo(this.topLeftRadius, 0.0f);
            float f2 = this.topLeftRadius;
            path.arcTo(new RectF(0.0f, 0.0f, f2 * 2.0f, f2 * 2.0f), -90.0f, -90.0f);
            path.close();
            canvas.drawPath(path, this.roundPaint);
        }
    }

    private void drawTopRight(Canvas canvas) {
        if (this.topRightRadius > 0.0f) {
            int width = getWidth();
            Path path = new Path();
            float f2 = width;
            path.moveTo(f2 - this.topRightRadius, 0.0f);
            path.lineTo(f2, 0.0f);
            path.lineTo(f2, this.topRightRadius);
            float f3 = this.topRightRadius;
            path.arcTo(new RectF(f2 - (f3 * 2.0f), 0.0f, f2, f3 * 2.0f), 0.0f, -90.0f);
            path.close();
            canvas.drawPath(path, this.roundPaint);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight()), this.imagePaint, 31);
        super.dispatchDraw(canvas);
        drawTopLeft(canvas);
        drawTopRight(canvas);
        drawBottomLeft(canvas);
        drawBottomRight(canvas);
        canvas.restore();
    }

    public void setBottomRadius(float f2) {
        this.bottomLeftRadius = f2;
        this.bottomRightRadius = f2;
    }

    public void setRadius(float f2) {
        this.topLeftRadius = f2;
        this.topRightRadius = f2;
        this.bottomLeftRadius = f2;
        this.bottomRightRadius = f2;
    }

    public void setTopRadius(float f2) {
        this.topLeftRadius = f2;
        this.topRightRadius = f2;
    }

    public RoundAngleFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundAngleFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundAngleFrameLayout);
            float dimension = obtainStyledAttributes.getDimension(R.styleable.RoundAngleFrameLayout_radius, 0.0f);
            this.topLeftRadius = obtainStyledAttributes.getDimension(R.styleable.RoundAngleFrameLayout_topLeftRadius, dimension);
            this.topRightRadius = obtainStyledAttributes.getDimension(R.styleable.RoundAngleFrameLayout_topRightRadius, dimension);
            this.bottomLeftRadius = obtainStyledAttributes.getDimension(R.styleable.RoundAngleFrameLayout_bottomLeftRadius, dimension);
            this.bottomRightRadius = obtainStyledAttributes.getDimension(R.styleable.RoundAngleFrameLayout_bottomRightRadius, dimension);
            obtainStyledAttributes.recycle();
        }
        Paint paint = new Paint();
        this.roundPaint = paint;
        paint.setColor(-1);
        this.roundPaint.setAntiAlias(true);
        this.roundPaint.setStyle(Paint.Style.FILL);
        this.roundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        Paint paint2 = new Paint();
        this.imagePaint = paint2;
        paint2.setXfermode(null);
    }
}
