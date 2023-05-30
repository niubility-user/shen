package com.jd.lib.productdetail.tradein.estimate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;

/* loaded from: classes16.dex */
public class HorizontalProgressBarWithNumber extends ProgressBar {

    /* renamed from: g  reason: collision with root package name */
    public Paint f5290g;

    /* renamed from: h  reason: collision with root package name */
    public String f5291h;

    /* renamed from: i  reason: collision with root package name */
    public Rect f5292i;

    /* renamed from: j  reason: collision with root package name */
    public int f5293j;

    public HorizontalProgressBarWithNumber(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public final void a() {
        Paint paint = new Paint();
        this.f5290g = paint;
        paint.setAntiAlias(true);
        this.f5290g.setColor(-1);
        this.f5290g.setTextSize((int) TypedValue.applyDimension(2, 10.0f, getResources().getDisplayMetrics()));
        this.f5292i = new Rect();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f2 = this.f5293j;
        float progress = (int) (((getProgress() * 1.0f) / getMax()) * f2);
        if (progress <= f2) {
            f2 = progress;
        }
        String str = getProgress() + "/" + getMax();
        this.f5291h = str;
        float measureText = this.f5290g.measureText(str);
        this.f5290g.setTextAlign(Paint.Align.CENTER);
        this.f5290g.setStrokeCap(Paint.Cap.ROUND);
        Paint paint = this.f5290g;
        String str2 = this.f5291h;
        paint.getTextBounds(str2, 0, str2.length(), this.f5292i);
        float f3 = f2 - measureText;
        if (f3 > measureText) {
            canvas.drawText(this.f5291h, f3, (getHeight() / 2) - this.f5292i.centerY(), this.f5290g);
        } else {
            canvas.drawText(this.f5291h, measureText, (getHeight() / 2) - this.f5292i.centerY(), this.f5290g);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        setMeasuredDimension(View.MeasureSpec.getSize(i2), View.MeasureSpec.getSize(i3));
        this.f5293j = (getMeasuredWidth() - getPaddingRight()) - getPaddingLeft();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i2) {
        super.setProgress(i2);
    }

    public HorizontalProgressBarWithNumber(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
