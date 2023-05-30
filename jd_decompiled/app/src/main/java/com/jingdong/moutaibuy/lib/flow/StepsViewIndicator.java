package com.jingdong.moutaibuy.lib.flow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class StepsViewIndicator extends View {

    /* renamed from: g  reason: collision with root package name */
    private Paint f14601g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f14602h;

    /* renamed from: i  reason: collision with root package name */
    private Paint f14603i;

    /* renamed from: j  reason: collision with root package name */
    private Paint f14604j;

    /* renamed from: k  reason: collision with root package name */
    private int f14605k;

    /* renamed from: l  reason: collision with root package name */
    private float f14606l;

    /* renamed from: m  reason: collision with root package name */
    private float f14607m;

    /* renamed from: n  reason: collision with root package name */
    private float f14608n;
    private float o;
    private int p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private float u;
    private float v;
    private float w;
    private List<Float> x;
    private int y;
    private a z;

    /* loaded from: classes16.dex */
    public interface a {
        void onReady();
    }

    public StepsViewIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void a() {
        this.f14607m = this.f14606l;
    }

    protected int b(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        return mode == 1073741824 ? size : mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
    }

    public void c(int i2) {
        this.q = i2;
    }

    public void d(float f2) {
        this.f14608n = f2;
    }

    public void e(int i2) {
        this.y = i2;
    }

    public void f(a aVar) {
        this.z = aVar;
    }

    public void g(boolean z) {
        this.t = z;
    }

    public void h(float f2) {
        this.o = f2;
    }

    public void i(int i2) {
        this.p = i2;
    }

    public void j(float f2) {
        this.f14606l = f2;
    }

    public void k(int i2) {
        this.r = i2;
    }

    public void l(int i2) {
        this.s = i2;
    }

    public void m(int i2) {
        this.f14605k = i2;
        invalidate();
    }

    @Override // android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.z.onReady();
        this.f14601g.setAntiAlias(true);
        this.f14601g.setColor(this.q);
        this.f14601g.setStyle(Paint.Style.STROKE);
        this.f14601g.setStrokeWidth(1.0f);
        this.f14602h.setAntiAlias(true);
        this.f14602h.setColor(this.p);
        this.f14602h.setStyle(Paint.Style.STROKE);
        this.f14602h.setStrokeWidth(1.0f);
        this.f14603i.setAntiAlias(true);
        this.f14603i.setTextSize(this.f14608n);
        this.f14603i.setColor(this.r);
        this.f14604j.setTextSize(this.f14608n);
        this.f14604j.setColor(this.s);
        if (this.u <= 0.0f) {
            Paint.FontMetrics fontMetrics = this.f14602h.getFontMetrics();
            float f2 = fontMetrics.descent;
            this.u = Math.abs((((-fontMetrics.ascent) + f2) / 2.0f) - f2) / 2.0f;
        }
        this.f14601g.setStyle(Paint.Style.FILL);
        this.f14602h.setStyle(Paint.Style.FILL);
        int i2 = 0;
        int i3 = 0;
        while (i3 < this.x.size() - 1) {
            float floatValue = this.x.get(i3).floatValue();
            int i4 = i3 + 1;
            float floatValue2 = this.x.get(i4).floatValue();
            float f3 = this.w;
            canvas.drawRect(floatValue, f3, floatValue2, f3 + this.f14606l, i3 < this.y ? this.f14602h : this.f14601g);
            i3 = i4;
        }
        float f4 = this.f14608n / 4.0f;
        while (i2 < this.x.size()) {
            float floatValue3 = this.x.get(i2).floatValue();
            canvas.drawCircle(floatValue3, this.v, this.f14608n, i2 <= this.y ? this.f14602h : this.f14601g);
            if (!this.t) {
                String valueOf = String.valueOf(i2 + 1);
                float f5 = this.u;
                canvas.drawText(valueOf, (floatValue3 - f4) - (f5 / 2.0f), this.v + f4 + f5, i2 <= this.y ? this.f14604j : this.f14603i);
            }
            i2++;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        setMeasuredDimension(b(getWidth(), i2), b(200, i3));
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.x = new ArrayList();
        float height = getHeight() * 0.5f;
        this.v = height;
        float f2 = this.o;
        this.w = height - (this.f14607m / 2.0f);
        float width = getWidth() - this.o;
        float f3 = (width - f2) / (this.f14605k - 1);
        this.x.add(Float.valueOf(f2));
        for (int i6 = 1; i6 < this.f14605k - 1; i6++) {
            this.x.add(Float.valueOf((i6 * f3) + f2));
        }
        this.x.add(Float.valueOf(width));
        this.z.onReady();
    }

    public StepsViewIndicator(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f14601g = new Paint();
        this.f14602h = new Paint();
        this.f14603i = new Paint();
        this.f14604j = new Paint();
        this.x = new ArrayList();
        a();
    }
}
