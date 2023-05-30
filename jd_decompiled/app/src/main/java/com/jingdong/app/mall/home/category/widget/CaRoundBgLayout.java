package com.jingdong.app.mall.home.category.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.widget.RelativeLayout;

/* loaded from: classes4.dex */
public class CaRoundBgLayout extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private Paint f8839g;

    /* renamed from: h  reason: collision with root package name */
    private int f8840h;

    /* renamed from: i  reason: collision with root package name */
    private int f8841i;

    /* renamed from: j  reason: collision with root package name */
    private int f8842j;

    /* renamed from: k  reason: collision with root package name */
    private int f8843k;

    /* renamed from: l  reason: collision with root package name */
    private Path f8844l;

    public CaRoundBgLayout(Context context) {
        super(context);
        this.f8839g = new Paint(1);
        this.f8844l = new Path();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        if (this.f8844l.isEmpty()) {
            int width = (getWidth() - this.f8842j) >> 1;
            if (Build.VERSION.SDK_INT >= 21 && this.f8841i > 0) {
                Path path = this.f8844l;
                float f2 = width;
                float f3 = this.f8840h;
                float width2 = getWidth() - width;
                float f4 = this.f8843k + this.f8840h;
                int i2 = this.f8841i;
                path.addRoundRect(f2, f3, width2, f4, i2, i2, Path.Direction.CCW);
            } else {
                this.f8844l.addRect(width, this.f8840h, getWidth() - width, this.f8843k + this.f8840h, Path.Direction.CCW);
            }
        }
        canvas.drawPath(this.f8844l, this.f8839g);
        super.dispatchDraw(canvas);
    }
}
