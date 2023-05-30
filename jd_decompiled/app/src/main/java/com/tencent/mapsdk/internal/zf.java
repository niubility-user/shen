package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.view.View;

/* loaded from: classes9.dex */
public class zf extends View {

    /* renamed from: g  reason: collision with root package name */
    public yf f17577g;

    public zf(Context context) {
        super(context);
        yf yfVar = new yf();
        this.f17577g = yfVar;
        if (Build.VERSION.SDK_INT < 16) {
            setBackgroundDrawable(yfVar);
        } else {
            setBackground(yfVar);
        }
        this.f17577g.a(getResources().getDisplayMetrics().density * 1.0f);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.f17577g.setBounds(0, 0, i4 - i2, i5 - i3);
    }

    public void setActivate(boolean z) {
        yf yfVar = this.f17577g;
        if (yfVar == null) {
            return;
        }
        yfVar.a(z);
        postInvalidate();
    }

    public void setArrowStrokeWidth(float f2) {
        yf yfVar = this.f17577g;
        if (yfVar == null) {
            return;
        }
        yfVar.a(f2);
        postInvalidate();
    }

    public void setDarkStyle(boolean z) {
        this.f17577g.b(z);
        invalidate();
    }
}
