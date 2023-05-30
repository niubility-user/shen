package com.jd.dynamic.b.l.b;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import com.jd.dynamic.yoga.android.YogaLayout;

/* loaded from: classes13.dex */
public abstract class a implements b {
    protected YogaLayout a;
    protected com.jd.dynamic.b.l.a.c b;

    /* renamed from: c  reason: collision with root package name */
    protected boolean f1803c = true;
    protected boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    protected int f1804e;

    /* renamed from: f  reason: collision with root package name */
    protected int f1805f;

    /* renamed from: g  reason: collision with root package name */
    protected int f1806g;

    /* renamed from: h  reason: collision with root package name */
    protected ColorStateList f1807h;

    /* renamed from: i  reason: collision with root package name */
    protected float f1808i;

    /* renamed from: j  reason: collision with root package name */
    protected View f1809j;

    public a(YogaLayout yogaLayout, Context context, AttributeSet attributeSet) {
        this.a = yogaLayout;
        yogaLayout.setClipToPadding(false);
        this.a.setLayerType(1, null);
        context.getApplicationContext();
        f(attributeSet);
    }

    private void f(AttributeSet attributeSet) {
        l();
    }

    private void l() {
        this.f1803c = true;
        this.d = true;
        this.f1807h = ColorStateList.valueOf(-12303292);
        this.f1805f = 0;
        this.f1806g = 0;
        this.f1804e = 0;
        this.f1808i = 0.4f;
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void a(float f2) {
        this.f1808i = f2;
        this.f1808i = Math.max(0.0f, f2);
        float min = Math.min(1.0f, f2);
        this.f1808i = min;
        this.b.d(e(min, i()));
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void a(int i2) {
        this.f1804e = i2;
        this.b.a(i2);
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void a(ColorStateList colorStateList) {
        this.f1807h = colorStateList;
        this.b.d(e(this.f1808i, i()));
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void a(View view) {
        this.f1809j = view;
        this.b.d(i());
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void a(boolean z) {
        this.f1803c = z;
        this.b.a(z);
    }

    @Override // com.jd.dynamic.b.l.b.b
    public boolean a() {
        return this.f1803c;
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void b(int i2) {
        this.f1805f = i2;
        this.b.b(i2);
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void b(boolean z) {
        this.d = z;
    }

    @Override // com.jd.dynamic.b.l.b.b
    public boolean b() {
        return this.d;
    }

    @Override // com.jd.dynamic.b.l.b.b
    public int c() {
        return this.f1804e;
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void c(int i2) {
        this.f1806g = i2;
        this.b.c(i2);
    }

    @Override // com.jd.dynamic.b.l.b.b
    public int d() {
        return this.f1805f;
    }

    @Override // com.jd.dynamic.b.l.b.b
    public int e() {
        return this.f1806g;
    }

    public int e(float f2, int i2) {
        return Color.argb((int) (f2 * 255.0f), Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    @Override // com.jd.dynamic.b.l.b.b
    public int f() {
        return i();
    }

    @Override // com.jd.dynamic.b.l.b.b
    public float g() {
        return this.f1808i;
    }

    @Override // com.jd.dynamic.b.l.b.b
    public void h() {
        this.a.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int i() {
        int defaultColor = this.f1807h.getDefaultColor();
        View view = this.f1809j;
        if (view != null) {
            defaultColor = this.f1807h.getColorForState(view.getDrawableState(), defaultColor);
        }
        return e(this.f1808i, defaultColor);
    }
}
