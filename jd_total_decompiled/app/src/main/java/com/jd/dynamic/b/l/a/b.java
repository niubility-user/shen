package com.jd.dynamic.b.l.a;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/* loaded from: classes13.dex */
public class b extends a {

    /* renamed from: e  reason: collision with root package name */
    private Paint f1798e;

    /* renamed from: f  reason: collision with root package name */
    private Paint f1799f;

    /* renamed from: g  reason: collision with root package name */
    private float[] f1800g;

    /* renamed from: h  reason: collision with root package name */
    private RectF f1801h;

    /* renamed from: i  reason: collision with root package name */
    private Path f1802i;

    @Override // com.jd.dynamic.b.l.a.a
    public void a() {
        Paint paint;
        BlurMaskFilter blurMaskFilter;
        Paint paint2 = new Paint(1);
        this.f1798e = paint2;
        paint2.setColor(this.b);
        if (this.a > 0) {
            paint = this.f1798e;
            blurMaskFilter = new BlurMaskFilter(this.a, BlurMaskFilter.Blur.NORMAL);
        } else {
            paint = this.f1798e;
            blurMaskFilter = null;
        }
        paint.setMaskFilter(blurMaskFilter);
        Paint paint3 = new Paint(1);
        this.f1799f = paint3;
        paint3.setColor(-16777216);
        this.f1799f.setStyle(Paint.Style.FILL);
        this.f1799f.setFilterBitmap(true);
        this.f1799f.setDither(true);
        this.f1801h = new RectF();
        new RectF();
        new Path();
        this.f1802i = new Path();
    }

    @Override // com.jd.dynamic.b.l.a.a, com.jd.dynamic.b.l.a.c
    public void a(int i2) {
        super.a(i2);
        if (i2 > 0) {
            this.f1798e.setMaskFilter(new BlurMaskFilter(i2, BlurMaskFilter.Blur.NORMAL));
        } else {
            this.f1798e.setMaskFilter(null);
        }
    }

    @Override // com.jd.dynamic.b.l.a.c
    public void a(Canvas canvas) {
        if (this.d) {
            if (this.f1800g == null) {
                canvas.drawRect(this.f1797c, this.f1798e);
                return;
            }
            RectF rectF = this.f1801h;
            Rect rect = this.f1797c;
            rectF.left = rect.left;
            rectF.top = rect.top;
            rectF.right = rect.right;
            rectF.bottom = rect.bottom;
            this.f1802i.reset();
            this.f1802i.addRoundRect(this.f1801h, this.f1800g, Path.Direction.CW);
            canvas.drawPath(this.f1802i, this.f1798e);
        }
    }

    @Override // com.jd.dynamic.b.l.a.c
    public void b(Canvas canvas) {
    }

    @Override // com.jd.dynamic.b.l.a.c
    public void c(float[] fArr) {
        this.f1800g = fArr;
    }

    @Override // com.jd.dynamic.b.l.a.a, com.jd.dynamic.b.l.a.c
    public void d(int i2) {
        super.d(i2);
        Paint paint = this.f1798e;
        if (paint != null) {
            paint.setColor(i2);
        }
    }

    @Override // com.jd.dynamic.b.l.a.c
    public void d(View view, int i2, int i3, int i4, int i5) {
    }
}
