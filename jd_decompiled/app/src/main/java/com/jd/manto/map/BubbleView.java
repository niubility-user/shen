package com.jd.manto.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.dynamic.DYConstants;

/* loaded from: classes17.dex */
public class BubbleView extends LinearLayout {
    private float DEFAULT_RADIUS;
    private int bgColor;
    private int borderColor;
    private int borderWidth;
    private final int bubble_size;
    private float gap;
    private final Paint mPaint;
    private final Paint mPaint2;
    private final Paint mPaintBG;
    private final Path mPath;
    private RectF rectF;
    public final TextView tvTitle;

    public BubbleView(@NonNull Context context) {
        this(context, null);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i2 = (int) this.gap;
        int width = getWidth() - i2;
        int height = getHeight() - i2;
        float f2 = i2;
        this.mPath.moveTo(this.DEFAULT_RADIUS + f2, f2);
        float f3 = width;
        this.mPath.lineTo(f3 - this.DEFAULT_RADIUS, f2);
        float f4 = this.DEFAULT_RADIUS;
        RectF rectF = new RectF(f3 - f4, f2, f3, f4 + f2);
        this.rectF = rectF;
        this.mPath.arcTo(rectF, 270.0f, 90.0f);
        float f5 = height;
        this.mPath.lineTo(f3, (f5 - this.DEFAULT_RADIUS) - 10.0f);
        float f6 = this.DEFAULT_RADIUS;
        float f7 = (float) (height - 10);
        RectF rectF2 = new RectF(f3 - f6, (f5 - f6) - 10.0f, f3, f7);
        this.rectF = rectF2;
        this.mPath.arcTo(rectF2, 0.0f, 90.0f);
        int i3 = (width + i2) / 2;
        this.mPath.lineTo(i3 + 10, f7);
        this.mPath.lineTo(i3, f5);
        this.mPath.lineTo((float) (i3 - 10), f7);
        this.mPath.lineTo(this.DEFAULT_RADIUS + f2, f7);
        float f8 = this.DEFAULT_RADIUS;
        RectF rectF3 = new RectF(f2, (f5 - f8) - 10.0f, f8, f7);
        this.rectF = rectF3;
        this.mPath.arcTo(rectF3, 90.0f, 90.0f);
        this.mPath.lineTo(f2, this.DEFAULT_RADIUS + f2);
        float f9 = this.DEFAULT_RADIUS;
        RectF rectF4 = new RectF(f2, f2, f2 + f9, f9 + f2);
        this.rectF = rectF4;
        this.mPath.arcTo(rectF4, 180.0f, 90.0f);
        this.mPath.close();
        canvas.drawPath(this.mPath, this.mPaint);
        canvas.drawPath(this.mPath, this.mPaintBG);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        this.bgColor = i2;
        this.mPaintBG.setColor(i2);
        invalidate();
    }

    public void setBorderColor(int i2) {
        this.borderColor = i2;
        this.mPaint.setColor(i2);
        invalidate();
    }

    public void setBorderWidth(int i2) {
        this.borderWidth = i2;
        this.gap = Math.round((i2 * 1.0f) + 0.5f) / 2;
        this.mPaint.setStrokeWidth(i2);
        invalidate();
    }

    public void setCornerRadius(int i2) {
        this.DEFAULT_RADIUS = i2;
        invalidate();
    }

    public final void setGravity(String str) {
        if (str.equals("left")) {
            this.tvTitle.setGravity(3);
        } else if (str.equals("right")) {
            this.tvTitle.setGravity(5);
        } else {
            str.equals(DYConstants.DY_CENTER);
            this.tvTitle.setGravity(17);
        }
    }

    public BubbleView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.DEFAULT_RADIUS = 20.0f;
        this.bubble_size = 10;
        int parseColor = Color.parseColor("#00000000");
        this.borderColor = parseColor;
        this.bgColor = parseColor;
        this.borderWidth = 1;
        this.rectF = new RectF();
        this.mPath = new Path();
        Paint paint = new Paint(1);
        this.mPaint = paint;
        Paint paint2 = new Paint(1);
        this.mPaintBG = paint2;
        this.gap = 2.0f;
        Paint paint3 = new Paint(1);
        this.mPaint2 = paint3;
        setWillNotDraw(false);
        paint.setColor(this.borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(this.borderWidth);
        paint2.setStyle(Paint.Style.FILL);
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        setOrientation(1);
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom() + 10);
        setGravity(17);
        TextView textView = new TextView(context);
        this.tvTitle = textView;
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        addView(textView);
        paint3.setColor(-16776961);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(this.borderWidth);
    }
}
