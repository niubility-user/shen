package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import com.jingdong.app.mall.home.floor.common.d;

/* loaded from: classes4.dex */
public class IconIndicatorView extends View {

    /* renamed from: g  reason: collision with root package name */
    private Paint f10082g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f10083h;

    /* renamed from: i  reason: collision with root package name */
    private float f10084i;

    /* renamed from: j  reason: collision with root package name */
    private float f10085j;

    /* renamed from: k  reason: collision with root package name */
    private float f10086k;

    /* renamed from: l  reason: collision with root package name */
    private float f10087l;

    /* renamed from: m  reason: collision with root package name */
    private float f10088m;

    /* renamed from: n  reason: collision with root package name */
    private int f10089n;
    private int o;

    public IconIndicatorView(Context context, int i2, int i3, int i4, int i5) {
        super(context);
        this.f10089n = i4;
        this.o = i5;
        b(i2, i3);
        a();
    }

    private void a() {
        this.f10084i = d.d(80);
        this.f10085j = d.d(40);
        this.f10088m = 0.0f;
    }

    private void b(int i2, int i3) {
        Paint paint = new Paint(1);
        this.f10082g = paint;
        paint.setColor(i2);
        this.f10082g.setStrokeCap(Paint.Cap.ROUND);
        this.f10082g.setColor(i2);
        float d = d.d(this.f10089n);
        this.f10086k = d;
        this.f10082g.setStrokeWidth(d);
        Paint paint2 = new Paint(1);
        this.f10083h = paint2;
        paint2.setColor(i3);
        this.f10083h.setStrokeCap(Paint.Cap.ROUND);
        this.f10083h.setColor(i3);
        float d2 = d.d(this.o);
        this.f10087l = d2;
        this.f10083h.setStrokeWidth(d2);
    }

    public void c(int i2, int i3) {
        this.f10082g.setColor(i2);
        this.f10083h.setColor(i3);
    }

    public void d(int i2, int i3) {
        this.f10089n = i2;
        this.o = i3;
    }

    public void e(int i2) {
        if (i2 != 1 && i2 != 2) {
            this.f10084i = d.d(48);
            this.f10085j = d.d(24);
        } else {
            this.f10084i = d.d(72);
            this.f10085j = d.d(24);
        }
        float d = d.d(this.f10089n);
        this.f10086k = d;
        this.f10082g.setStrokeWidth(d);
        float d2 = d.d(this.o);
        this.f10087l = d2;
        this.f10083h.setStrokeWidth(d2);
        invalidate();
    }

    public void f(float f2) {
        this.f10088m = (this.f10084i - this.f10085j) * f2;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f2 = this.f10086k;
        canvas.drawLine(f2 / 2.0f, f2 / 2.0f, this.f10084i - (f2 / 2.0f), f2 / 2.0f, this.f10082g);
        float f3 = this.f10087l;
        float f4 = this.f10088m;
        canvas.drawLine((f3 / 2.0f) + f4, f3 / 2.0f, (this.f10085j - (f3 / 2.0f)) + f4, f3 / 2.0f, this.f10083h);
    }
}
