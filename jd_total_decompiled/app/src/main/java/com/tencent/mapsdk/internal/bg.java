package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

/* loaded from: classes9.dex */
public class bg extends LinearLayout {

    /* renamed from: g  reason: collision with root package name */
    private Path f16330g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f16331h;

    /* renamed from: i  reason: collision with root package name */
    private Paint f16332i;

    /* renamed from: j  reason: collision with root package name */
    private RectF f16333j;

    /* renamed from: k  reason: collision with root package name */
    private float f16334k;

    /* renamed from: l  reason: collision with root package name */
    private float f16335l;

    /* renamed from: m  reason: collision with root package name */
    private int f16336m;

    public bg(Context context) {
        super(context);
        this.f16336m = -1;
        a();
    }

    public bg(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f16336m = -1;
        a();
    }

    private void a() {
        this.f16335l = getResources().getDisplayMetrics().density / 2.0f;
        this.f16330g = new Path();
        b();
    }

    private void a(Canvas canvas) {
        Path path;
        if (Build.VERSION.SDK_INT <= 27) {
            this.f16331h.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            path = this.f16330g;
        } else {
            this.f16331h.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            path = new Path();
            path.addRect(0.0f, 0.0f, getWidth(), getHeight(), Path.Direction.CW);
            path.op(this.f16330g, Path.Op.DIFFERENCE);
        }
        canvas.drawPath(path, this.f16331h);
    }

    private void b() {
        Paint paint = new Paint();
        this.f16331h = paint;
        paint.setColor(this.f16336m);
        this.f16331h.setAntiAlias(true);
        this.f16331h.setStyle(Paint.Style.FILL);
        this.f16331h.setShadowLayer(this.f16335l, 0.0f, 0.0f, -1);
        Paint paint2 = new Paint();
        this.f16332i = paint2;
        paint2.setColor(this.f16336m);
        this.f16332i.setAntiAlias(true);
        this.f16332i.setStyle(Paint.Style.STROKE);
        this.f16332i.setShadowLayer(this.f16335l, 0.0f, 0.0f, -16777216);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saveLayer = canvas.saveLayer(this.f16333j, null, 31);
        canvas.drawColor(this.f16336m);
        canvas.drawPath(this.f16330g, this.f16332i);
        a(canvas);
        canvas.restoreToCount(saveLayer);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f16333j == null) {
            this.f16333j = new RectF();
        }
        this.f16333j.right = getMeasuredWidth();
        this.f16333j.bottom = getMeasuredHeight();
        this.f16334k = (this.f16333j.width() < this.f16333j.height() ? this.f16333j.width() : this.f16333j.height()) / 2.0f;
        this.f16330g.reset();
        Path path = this.f16330g;
        RectF rectF = this.f16333j;
        float f2 = this.f16334k;
        path.addRoundRect(rectF, f2, f2, Path.Direction.CW);
    }

    public void setDarkStyle(boolean z) {
        this.f16336m = z ? Color.parseColor("#2C2C2C") : -1;
        b();
        invalidate();
    }
}
