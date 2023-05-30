package com.jingdong.app.mall.utils.ui.seekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.jingdong.app.mall.R;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class RangeBar extends View {
    private int A;
    private float B;

    /* renamed from: g  reason: collision with root package name */
    private int f11929g;

    /* renamed from: h  reason: collision with root package name */
    private float f11930h;

    /* renamed from: i  reason: collision with root package name */
    private float f11931i;

    /* renamed from: j  reason: collision with root package name */
    private int f11932j;

    /* renamed from: k  reason: collision with root package name */
    private float f11933k;

    /* renamed from: l  reason: collision with root package name */
    private int f11934l;

    /* renamed from: m  reason: collision with root package name */
    private int f11935m;

    /* renamed from: n  reason: collision with root package name */
    private int f11936n;
    private float o;
    private int p;
    private int q;
    private boolean r;
    private int s;
    private int t;
    private c u;
    private c v;
    private com.jingdong.app.mall.utils.ui.seekbar.a w;
    private b x;
    private a y;
    private int z;

    /* loaded from: classes4.dex */
    public interface a {
        void a(RangeBar rangeBar, int i2, int i3);
    }

    public RangeBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11929g = 3;
        this.f11930h = 24.0f;
        this.f11931i = 2.0f;
        this.f11932j = -3355444;
        this.f11933k = 4.0f;
        this.f11934l = -13388315;
        this.f11935m = R.drawable.seek_thumb_normal;
        this.f11936n = R.drawable.seek_thumb_pressed;
        this.o = -1.0f;
        this.p = -1;
        this.q = -1;
        this.r = true;
        this.s = 500;
        this.t = 100;
        this.z = 0;
        this.A = 3 - 1;
        this.B = DPIUtil.dip2px(15.0f);
        DPIUtil.dip2px(15.0f);
        l(context, attributeSet);
    }

    private void a() {
        Context context = getContext();
        float d = d();
        this.u = new c(context, d, this.p, this.q, this.o, this.f11935m, this.f11936n);
        this.v = new c(context, d, this.p, this.q, this.o, this.f11935m, this.f11936n);
        float c2 = c();
        float b = b();
        c cVar = this.u;
        int i2 = this.f11929g;
        cVar.f11962j = ((this.z / ((float) (i2 - 1))) * b) + c2;
        this.v.f11962j = c2 + ((this.A / ((float) (i2 - 1))) * b);
        invalidate();
    }

    private float b() {
        return getWidth() - (c() * 2.0f);
    }

    private float c() {
        c cVar = this.u;
        if (cVar != null) {
            return cVar.b();
        }
        return 0.0f;
    }

    private float d() {
        return getHeight() / 2.0f;
    }

    private boolean e(int i2, int i3) {
        int i4;
        return i2 < 0 || i2 >= (i4 = this.f11929g) || i3 < 0 || i3 >= i4;
    }

    private boolean f(int i2) {
        return i2 > 1;
    }

    private void g(c cVar, float f2) {
        if (f2 < this.w.c() || f2 > this.w.f()) {
            return;
        }
        cVar.f11962j = f2;
        invalidate();
    }

    private void h(float f2, float f3) {
        if (!this.u.d() && this.u.c(f2, f3)) {
            k(this.u);
        } else if (this.u.d() || !this.v.c(f2, f3)) {
        } else {
            k(this.v);
        }
    }

    private void i(float f2) {
        if (this.u.d()) {
            g(this.u, f2);
        } else if (this.v.d()) {
            g(this.v, f2);
        }
        c cVar = this.u;
        float f3 = cVar.f11962j;
        c cVar2 = this.v;
        if (f3 > cVar2.f11962j) {
            this.u = cVar2;
            this.v = cVar;
        }
        int e2 = this.w.e(this.u);
        int e3 = this.w.e(this.v);
        if (e2 == this.z && e3 == this.A) {
            return;
        }
        this.z = e2;
        this.A = e3;
        com.jingdong.app.mall.utils.ui.seekbar.a aVar = this.w;
        if (aVar != null) {
            aVar.g(e2);
            this.w.h(e3);
        }
        a aVar2 = this.y;
        if (aVar2 != null) {
            aVar2.a(this, this.z, this.A);
        }
    }

    private void j(float f2, float f3) {
        if (this.u.d()) {
            m(this.u);
        } else if (this.v.d()) {
            m(this.v);
        } else {
            if (Math.abs(this.u.f11962j - f2) < Math.abs(this.v.f11962j - f2)) {
                c cVar = this.u;
                cVar.f11962j = f2;
                m(cVar);
            } else {
                c cVar2 = this.v;
                cVar2.f11962j = f2;
                m(cVar2);
            }
            int e2 = this.w.e(this.u);
            int e3 = this.w.e(this.v);
            if (e2 == this.z && e3 == this.A) {
                return;
            }
            this.z = e2;
            this.A = e3;
            com.jingdong.app.mall.utils.ui.seekbar.a aVar = this.w;
            if (aVar != null) {
                aVar.g(e2);
                this.w.h(this.A);
            }
            a aVar2 = this.y;
            if (aVar2 != null) {
                aVar2.a(this, this.z, this.A);
            }
        }
    }

    private void k(c cVar) {
        if (this.r) {
            this.r = false;
        }
        cVar.e();
        invalidate();
    }

    private void l(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RangeBar, 0, 0);
        try {
            Integer valueOf = Integer.valueOf(obtainStyledAttributes.getInteger(9, 3));
            if (f(valueOf.intValue())) {
                int intValue = valueOf.intValue();
                this.f11929g = intValue;
                this.z = 0;
                this.A = intValue - 1;
                com.jingdong.app.mall.utils.ui.seekbar.a aVar = this.w;
                if (aVar != null) {
                    aVar.g(0);
                    this.w.h(this.A);
                }
                a aVar2 = this.y;
                if (aVar2 != null) {
                    aVar2.a(this, this.z, this.A);
                }
            }
            this.f11930h = obtainStyledAttributes.getDimension(10, 24.0f);
            this.f11931i = obtainStyledAttributes.getDimension(1, 2.0f);
            this.f11932j = obtainStyledAttributes.getColor(0, -3355444);
            this.f11933k = obtainStyledAttributes.getDimension(3, 4.0f);
            this.f11934l = obtainStyledAttributes.getColor(2, -13388315);
            this.o = obtainStyledAttributes.getDimension(8, -1.0f);
            this.f11935m = obtainStyledAttributes.getResourceId(6, R.drawable.seek_thumb_normal);
            this.f11936n = obtainStyledAttributes.getResourceId(7, R.drawable.seek_thumb_pressed);
            this.p = obtainStyledAttributes.getColor(4, -1);
            this.q = obtainStyledAttributes.getColor(5, -1);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void m(c cVar) {
        cVar.f11962j = this.w.d(cVar);
        cVar.f();
        invalidate();
    }

    public void n(int i2, int i3) {
        if (!e(i2, i3)) {
            if (this.r) {
                this.r = false;
            }
            this.z = i2;
            this.A = i3;
            a();
            com.jingdong.app.mall.utils.ui.seekbar.a aVar = this.w;
            if (aVar != null) {
                aVar.g(this.z);
                this.w.h(this.A);
            }
            a aVar2 = this.y;
            if (aVar2 != null) {
                aVar2.a(this, this.z, this.A);
            }
            invalidate();
            requestLayout();
            return;
        }
        throw new IllegalArgumentException("A thumb index is out of bounds. Check that it is between 0 and mTickCount - 1");
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.w.a(canvas);
        this.x.a(canvas, this.u, this.v);
        this.u.a(canvas);
        this.v.a(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode != Integer.MIN_VALUE && mode != 1073741824) {
            size = this.s;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(this.t, size2);
        } else if (mode2 != 1073741824) {
            size2 = this.t;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.f11929g = bundle.getInt("TICK_COUNT");
            this.f11930h = bundle.getFloat("TICK_HEIGHT_DP");
            this.f11931i = bundle.getFloat("BAR_WEIGHT");
            this.f11932j = bundle.getInt("BAR_COLOR");
            this.f11933k = bundle.getFloat("CONNECTING_LINE_WEIGHT");
            this.f11934l = bundle.getInt("CONNECTING_LINE_COLOR");
            this.f11935m = bundle.getInt("THUMB_IMAGE_NORMAL");
            this.f11936n = bundle.getInt("THUMB_IMAGE_PRESSED");
            this.o = bundle.getFloat("THUMB_RADIUS_DP");
            this.p = bundle.getInt("THUMB_COLOR_NORMAL");
            this.q = bundle.getInt("THUMB_COLOR_PRESSED");
            this.z = bundle.getInt("LEFT_INDEX");
            this.A = bundle.getInt("RIGHT_INDEX");
            this.r = bundle.getBoolean("FIRST_SET_TICK_COUNT");
            n(this.z, this.A);
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("TICK_COUNT", this.f11929g);
        bundle.putFloat("TICK_HEIGHT_DP", this.f11930h);
        bundle.putFloat("BAR_WEIGHT", this.f11931i);
        bundle.putInt("BAR_COLOR", this.f11932j);
        bundle.putFloat("CONNECTING_LINE_WEIGHT", this.f11933k);
        bundle.putInt("CONNECTING_LINE_COLOR", this.f11934l);
        bundle.putInt("THUMB_IMAGE_NORMAL", this.f11935m);
        bundle.putInt("THUMB_IMAGE_PRESSED", this.f11936n);
        bundle.putFloat("THUMB_RADIUS_DP", this.o);
        bundle.putInt("THUMB_COLOR_NORMAL", this.p);
        bundle.putInt("THUMB_COLOR_PRESSED", this.q);
        bundle.putInt("LEFT_INDEX", this.z);
        bundle.putInt("RIGHT_INDEX", this.A);
        bundle.putBoolean("FIRST_SET_TICK_COUNT", this.r);
        return bundle;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        Context context = getContext();
        float f2 = i3 / 2.0f;
        this.u = new c(context, f2, this.p, this.q, this.o, this.f11935m, this.f11936n);
        this.v = new c(context, f2, this.p, this.q, this.o, this.f11935m, this.f11936n);
        float b = this.u.b();
        float f3 = i2 - (2.0f * b);
        com.jingdong.app.mall.utils.ui.seekbar.a aVar = new com.jingdong.app.mall.utils.ui.seekbar.a(context, b, f2, f3, this.f11929g, this.f11930h, this.f11931i, this.f11932j, this.B);
        this.w = aVar;
        c cVar = this.u;
        int i6 = this.f11929g;
        cVar.f11962j = ((this.z / ((float) (i6 - 1))) * f3) + b;
        this.v.f11962j = b + ((this.A / ((float) (i6 - 1))) * f3);
        int e2 = aVar.e(cVar);
        int e3 = this.w.e(this.v);
        if (e2 != this.z || e3 != this.A) {
            this.z = e2;
            this.A = e3;
            com.jingdong.app.mall.utils.ui.seekbar.a aVar2 = this.w;
            if (aVar2 != null) {
                aVar2.g(e2);
                this.w.h(e3);
            }
            a aVar3 = this.y;
            if (aVar3 != null) {
                aVar3.a(this, this.z, this.A);
            }
        }
        this.x = new b(context, f2, this.f11933k, this.f11934l);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        i(motionEvent.getX());
                        getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    } else if (action != 3) {
                        return false;
                    }
                }
                getParent().requestDisallowInterceptTouchEvent(false);
                j(motionEvent.getX(), motionEvent.getY());
                return true;
            }
            h(motionEvent.getX(), motionEvent.getY());
            return true;
        }
        return false;
    }

    public RangeBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f11929g = 3;
        this.f11930h = 24.0f;
        this.f11931i = 2.0f;
        this.f11932j = -3355444;
        this.f11933k = 4.0f;
        this.f11934l = -13388315;
        this.f11935m = R.drawable.seek_thumb_normal;
        this.f11936n = R.drawable.seek_thumb_pressed;
        this.o = -1.0f;
        this.p = -1;
        this.q = -1;
        this.r = true;
        this.s = 500;
        this.t = 100;
        this.z = 0;
        this.A = 3 - 1;
        this.B = DPIUtil.dip2px(15.0f);
        DPIUtil.dip2px(15.0f);
        l(context, attributeSet);
    }
}
