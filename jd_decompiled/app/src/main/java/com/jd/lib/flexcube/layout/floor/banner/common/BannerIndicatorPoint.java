package com.jd.lib.flexcube.layout.floor.banner.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/* loaded from: classes14.dex */
public class BannerIndicatorPoint extends View {

    /* renamed from: g  reason: collision with root package name */
    public int f4267g;

    /* renamed from: h  reason: collision with root package name */
    public int f4268h;

    /* renamed from: i  reason: collision with root package name */
    private Paint f4269i;

    public BannerIndicatorPoint(Context context) {
        super(context);
        this.f4267g = -7829368;
        Paint paint = new Paint();
        this.f4269i = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f4269i.setAntiAlias(true);
    }

    public BannerIndicatorPoint a(int i2) {
        this.f4267g = i2;
        return this;
    }

    public BannerIndicatorPoint b(int i2) {
        this.f4268h = i2;
        return this;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.f4269i.setColor(this.f4267g);
        RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        int i2 = this.f4268h;
        canvas.drawRoundRect(rectF, i2, i2, this.f4269i);
    }
}
