package com.tencent.mapsdk.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes9.dex */
public class yf extends Drawable {
    private static final int d = -2829100;

    /* renamed from: e  reason: collision with root package name */
    private static final int f17515e = -10066330;

    /* renamed from: f  reason: collision with root package name */
    private static final int f17516f = 16777215;
    private boolean a = false;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private Paint f17517c;

    public yf() {
        Paint paint = new Paint();
        this.f17517c = paint;
        paint.setAntiAlias(true);
        this.f17517c.setStrokeJoin(Paint.Join.BEVEL);
    }

    public void a(float f2) {
        this.f17517c.setStrokeWidth(f2);
    }

    public void a(boolean z) {
        this.a = z;
    }

    public void b(boolean z) {
        this.b = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        Paint paint;
        int i2;
        if (this.a) {
            paint = this.f17517c;
            i2 = this.b ? f17515e : d;
        } else {
            paint = this.f17517c;
            i2 = 16777215;
        }
        paint.setColor(i2);
        canvas.drawLines(new float[]{0.0f, getBounds().height(), getBounds().width() / 2.0f, 0.0f, getBounds().width() / 2.0f, 0.0f, getBounds().width(), getBounds().height()}, this.f17517c);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return getBounds().height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return getBounds().width();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f17517c.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.f17517c.setColorFilter(colorFilter);
    }
}
