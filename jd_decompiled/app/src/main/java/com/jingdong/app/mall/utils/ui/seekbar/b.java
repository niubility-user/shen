package com.jingdong.app.mall.utils.ui.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.TypedValue;

/* loaded from: classes4.dex */
class b {
    private final Paint a;
    private final float b;

    /* renamed from: c  reason: collision with root package name */
    private final float f11955c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Context context, float f2, float f3, int i2) {
        float applyDimension = TypedValue.applyDimension(1, f3, context.getResources().getDisplayMetrics());
        this.b = applyDimension;
        Paint paint = new Paint();
        this.a = paint;
        paint.setColor(i2);
        paint.setStrokeWidth(applyDimension);
        paint.setAntiAlias(true);
        this.f11955c = f2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Canvas canvas, c cVar, c cVar2) {
        float f2 = cVar.f11962j;
        float f3 = this.f11955c;
        canvas.drawLine(f2, f3, cVar2.f11962j, f3, this.a);
    }
}
