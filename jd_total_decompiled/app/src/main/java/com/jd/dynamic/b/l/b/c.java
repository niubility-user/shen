package com.jd.dynamic.b.l.b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.jd.dynamic.yoga.android.YogaLayout;

/* loaded from: classes13.dex */
public class c extends a {

    /* renamed from: k  reason: collision with root package name */
    private float[] f1810k;

    /* renamed from: l  reason: collision with root package name */
    private Rect f1811l;

    public c(YogaLayout yogaLayout, Context context, AttributeSet attributeSet) {
        super(yogaLayout, context, attributeSet);
        f(attributeSet);
    }

    private void f(AttributeSet attributeSet) {
        m(attributeSet);
        com.jd.dynamic.b.l.a.b bVar = new com.jd.dynamic.b.l.a.b();
        this.b = bVar;
        bVar.c(this.f1810k);
        this.b.a(this.f1803c);
        this.f1811l = new Rect();
    }

    private void l() {
        this.b.e(this.a, i(), this.f1804e, this.f1805f, this.f1806g, this.f1811l);
    }

    private void m(AttributeSet attributeSet) {
        this.f1810k = new float[]{8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f};
    }

    private void n() {
        this.f1811l.setEmpty();
        if (this.a.getChildCount() > 0) {
            int childCount = this.a.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.a.getChildAt(i2);
                Rect rect = this.f1811l;
                int left = childAt.getLeft();
                int top = childAt.getTop();
                int right = childAt.getRight();
                int bottom = childAt.getBottom();
                if (i2 == 0) {
                    rect.set(left, top, right, bottom);
                } else {
                    rect.union(left, top, right, bottom);
                }
            }
        }
    }

    private int o() {
        int i2 = this.f1805f;
        if (i2 <= 0) {
            i2 = -i2;
        }
        return this.f1804e + i2;
    }

    private int p() {
        int i2 = this.f1806g;
        if (i2 <= 0) {
            i2 = -i2;
        }
        return this.f1804e + i2;
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void a(Canvas canvas) {
        this.b.a(canvas);
        this.a.superDispatchDraw(canvas);
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void b(Canvas canvas) {
        this.b.b(canvas);
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void c(float[] fArr) {
        this.f1810k = fArr;
        this.b.c(fArr);
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void d(boolean z, int i2, int i3, int i4, int i5) {
        n();
        l();
        this.b.d(this.a, i2, i3, i4, i5);
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void j() {
        int o = o();
        int p = p();
        this.a.setPadding(o, p, o, p);
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void k() {
        this.b.b();
    }
}
