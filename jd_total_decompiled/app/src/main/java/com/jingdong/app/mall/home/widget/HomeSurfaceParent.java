package com.jingdong.app.mall.home.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/* loaded from: classes4.dex */
public class HomeSurfaceParent extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private boolean f11028g;

    public HomeSurfaceParent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11028g = false;
    }

    public void a(boolean z) {
        this.f11028g = z;
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.f11028g) {
            canvas.drawColor(-1);
        }
        super.dispatchDraw(canvas);
    }

    public HomeSurfaceParent(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f11028g = false;
    }
}
