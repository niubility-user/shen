package com.jingdong.common.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.pool.bitmappool.GlideBitmapPool;

/* loaded from: classes6.dex */
public class RoundRectFrameLayout extends FrameLayout {
    private float bottomLeftRadius;
    private float bottomRightRadius;
    Paint mClipPaint;
    Path mClipPath;
    private RectF mClipRect;
    private float[] radiusArray;
    private float topLeftRadius;
    private float topRightRadius;

    public RoundRectFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    private void initPaint() {
        Paint paint = new Paint(1);
        this.mClipPaint = paint;
        paint.setColor(-16777216);
        this.mClipPaint.setStyle(Paint.Style.FILL);
        this.mClipPaint.setFilterBitmap(true);
        this.mClipPaint.setDither(true);
    }

    private void initRadius(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundRectFrameLayout);
        this.topLeftRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundRectFrameLayout_roundRadiusTopLeft, 0);
        this.topRightRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundRectFrameLayout_roundRadiusTopRight, 0);
        this.bottomLeftRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundRectFrameLayout_roundRadiusBottomLeft, 0);
        float dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundRectFrameLayout_roundRadiusBottomRight, 0);
        this.bottomRightRadius = dimensionPixelSize;
        float[] fArr = this.radiusArray;
        float f2 = this.topLeftRadius;
        fArr[0] = f2;
        fArr[1] = f2;
        float f3 = this.topRightRadius;
        fArr[2] = f3;
        fArr[3] = f3;
        float f4 = this.bottomLeftRadius;
        fArr[4] = f4;
        fArr[5] = f4;
        fArr[6] = dimensionPixelSize;
        fArr[7] = dimensionPixelSize;
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j2) {
        super.drawChild(canvas, view, j2);
        this.mClipPath.reset();
        this.mClipRect.left = view.getLeft();
        this.mClipRect.top = view.getTop();
        this.mClipRect.right = view.getRight();
        this.mClipRect.bottom = view.getBottom();
        this.mClipPath.addRoundRect(this.mClipRect, this.radiusArray, Path.Direction.CW);
        Bitmap bitmap = GlideBitmapPool.getBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        new Canvas(bitmap).drawPath(this.mClipPath, this.mClipPaint);
        this.mClipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mClipPaint);
        this.mClipPaint.setXfermode(null);
        GlideBitmapPool.putBitmap(bitmap);
        return true;
    }

    public RoundRectFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundRectFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.topLeftRadius = 0.0f;
        this.topRightRadius = 0.0f;
        this.bottomLeftRadius = 0.0f;
        this.bottomRightRadius = 0.0f;
        this.radiusArray = new float[8];
        this.mClipPath = new Path();
        this.mClipPaint = new Paint();
        this.mClipRect = new RectF();
        initRadius(context, attributeSet);
        initPaint();
    }
}
