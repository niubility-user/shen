package com.jingdong.sdk.jdcrashreport.recover;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.widget.Button;

/* loaded from: classes7.dex */
class f extends Button {

    /* renamed from: g  reason: collision with root package name */
    private float f14962g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f14963h;

    /* renamed from: i  reason: collision with root package name */
    private Path f14964i;

    public f(Context context) {
        super(context);
        this.f14962g = 100.0f;
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(null);
        } else {
            setBackgroundDrawable(null);
        }
        a();
    }

    private void a() {
        this.f14962g = com.jingdong.sdk.jdcrashreport.b.c.k(100);
        Paint paint = new Paint(1);
        this.f14963h = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f14963h.setColor(Color.parseColor("#448AFF"));
        this.f14964i = new Path();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        canvas.drawColor(0);
        this.f14963h.setColor(Color.parseColor("#448AFF"));
        if (Build.VERSION.SDK_INT >= 21) {
            float f2 = this.f14962g;
            canvas.drawRoundRect(0.0f, 0.0f, width, height, f2, f2, this.f14963h);
        } else {
            int i2 = width / 12;
            int i3 = height / 4;
            int i4 = i2 / 5;
            float f3 = i2;
            this.f14964i.moveTo(f3, 0.0f);
            float f4 = width - i2;
            this.f14964i.lineTo(f4, 0.0f);
            float f5 = width - i4;
            float f6 = i3;
            float f7 = i3 * 3;
            float f8 = height;
            this.f14964i.cubicTo(f5, f6, f5, f7, f4, f8);
            this.f14964i.lineTo(f3, f8);
            float f9 = i4;
            this.f14964i.cubicTo(f9, f7, f9, f6, f3, 0.0f);
            canvas.drawPath(this.f14964i, this.f14963h);
        }
        super.onDraw(canvas);
    }
}
