package com.jingdong.sdk.jdcrashreport.recover;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.ImageView;

/* loaded from: classes7.dex */
class c extends ImageView {

    /* renamed from: g  reason: collision with root package name */
    Paint f14956g;

    /* renamed from: h  reason: collision with root package name */
    Rect f14957h;

    public c(Context context) {
        super(context);
        a();
    }

    private void a() {
        Paint paint = new Paint();
        this.f14956g = paint;
        paint.setAntiAlias(true);
        this.f14956g.setDither(true);
        this.f14956g.setStyle(Paint.Style.FILL);
        this.f14956g.setTextSize(com.jingdong.sdk.jdcrashreport.b.c.k(40));
        this.f14957h = new Rect();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getDrawable() == null) {
            int width = getWidth();
            int height = getHeight();
            this.f14956g.getTextBounds("!", 0, 1, this.f14957h);
            canvas.drawColor(0);
            this.f14956g.setColor(Color.parseColor("#DADADA"));
            float f2 = width >> 1;
            canvas.drawCircle(f2, height >> 1, f2, this.f14956g);
            this.f14956g.setColor(-1);
            this.f14956g.setStrokeWidth(10.0f);
            canvas.drawText("!", (width / 2) - this.f14957h.width(), height - (this.f14957h.height() / 2), this.f14956g);
        }
    }
}
