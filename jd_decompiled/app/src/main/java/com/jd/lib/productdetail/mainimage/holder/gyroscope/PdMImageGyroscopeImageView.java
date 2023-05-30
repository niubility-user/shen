package com.jd.lib.productdetail.mainimage.holder.gyroscope;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.mainimage.holder.gyroscope.a;

/* loaded from: classes15.dex */
public class PdMImageGyroscopeImageView extends SimpleDraweeView {

    /* renamed from: g  reason: collision with root package name */
    public double f4856g;

    /* renamed from: h  reason: collision with root package name */
    public double f4857h;

    /* renamed from: i  reason: collision with root package name */
    public float f4858i;

    /* renamed from: j  reason: collision with root package name */
    public float f4859j;

    /* renamed from: k  reason: collision with root package name */
    public float f4860k;

    /* renamed from: l  reason: collision with root package name */
    public float f4861l;

    /* renamed from: m  reason: collision with root package name */
    public double f4862m;

    /* renamed from: n  reason: collision with root package name */
    public double f4863n;
    public int o;

    public PdMImageGyroscopeImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
    }

    public final void d() {
        setScaleType(ImageView.ScaleType.CENTER);
    }

    public void e(int i2) {
        this.o = i2;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a aVar = a.C0157a.a;
        if (aVar != null) {
            aVar.f4868g.put(this, Boolean.TRUE);
        }
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = a.C0157a.a;
        if (aVar != null) {
            aVar.f4868g.remove(this);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        if (getDrawable() != null && !isInEditMode()) {
            double d = this.f4858i;
            double d2 = this.f4856g;
            Double.isNaN(d);
            this.f4860k = (float) (d * d2);
            double d3 = this.f4859j;
            double d4 = this.f4857h;
            Double.isNaN(d3);
            this.f4861l = (float) (d3 * d4);
            canvas.save();
            canvas.translate(this.f4860k, this.f4861l);
            super.onDraw(canvas);
            canvas.restore();
            return;
        }
        super.onDraw(canvas);
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int size = (View.MeasureSpec.getSize(i2) - getPaddingLeft()) - getPaddingRight();
        int size2 = (View.MeasureSpec.getSize(i3) - getPaddingTop()) - getPaddingBottom();
        if (getDrawable() != null) {
            this.f4858i = Math.abs((this.o - size) * 0.5f);
            this.f4859j = Math.abs((this.o - size2) * 0.5f);
        }
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        super.setScaleType(ImageView.ScaleType.CENTER);
    }
}
