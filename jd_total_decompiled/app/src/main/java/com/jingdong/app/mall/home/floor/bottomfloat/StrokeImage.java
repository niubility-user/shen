package com.jingdong.app.mall.home.floor.bottomfloat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.n.h.e;

/* loaded from: classes4.dex */
public class StrokeImage extends HomeDraweeView {

    /* renamed from: g  reason: collision with root package name */
    private final Paint f9186g;

    /* renamed from: h  reason: collision with root package name */
    private int f9187h;

    public StrokeImage(Context context, int i2, int i3) {
        super(context);
        Paint paint = new Paint(1);
        this.f9186g = paint;
        paint.setColor(i3);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(i2);
    }

    private void a(Canvas canvas) {
        RectF rectF = new RectF(this.f9186g.getStrokeWidth() / 2.0f, this.f9186g.getStrokeWidth() / 2.0f, getWidth() - (this.f9186g.getStrokeWidth() / 2.0f), getHeight() - (this.f9186g.getStrokeWidth() / 2.0f));
        int i2 = this.f9187h;
        canvas.drawRoundRect(rectF, i2, i2, this.f9186g);
    }

    public void b(int i2) {
        this.f9187h = i2;
        e.d(this, i2 + 1);
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        a(canvas);
    }
}
