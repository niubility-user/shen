package com.jd.dynamic.b.l.a;

import android.graphics.Rect;
import com.jd.dynamic.yoga.android.YogaLayout;

/* loaded from: classes13.dex */
public abstract class a implements c {
    protected int a;
    protected int b;

    /* renamed from: c  reason: collision with root package name */
    protected Rect f1797c;
    protected boolean d;

    public abstract void a();

    @Override // com.jd.dynamic.b.l.a.c
    public void a(int i2) {
        this.a = i2;
    }

    @Override // com.jd.dynamic.b.l.a.c
    public void a(boolean z) {
        this.d = z;
    }

    @Override // com.jd.dynamic.b.l.a.c
    public void b() {
    }

    @Override // com.jd.dynamic.b.l.a.c
    public void b(int i2) {
    }

    @Override // com.jd.dynamic.b.l.a.c
    public void c(int i2) {
    }

    @Override // com.jd.dynamic.b.l.a.c
    public void d(int i2) {
        this.b = i2;
    }

    @Override // com.jd.dynamic.b.l.a.c
    public void e(YogaLayout yogaLayout, int i2, int i3, int i4, int i5, Rect rect) {
        this.b = i2;
        this.a = i3;
        this.f1797c = new Rect(rect.left + i4, rect.top + i5, rect.right + i4, rect.bottom + i5);
        a();
    }
}
