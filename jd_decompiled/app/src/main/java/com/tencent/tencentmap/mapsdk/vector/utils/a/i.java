package com.tencent.tencentmap.mapsdk.vector.utils.a;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.TextView;

/* loaded from: classes9.dex */
public class i extends TextView {
    public int a;
    public int b;

    public i(Context context) {
        super(context);
        this.a = 0;
        this.b = 0;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        canvas.translate(this.b / 2, this.a / 2);
        super.draw(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int max = Math.max(measuredWidth, measuredHeight);
        if (measuredWidth > measuredHeight) {
            this.a = measuredWidth - measuredHeight;
            this.b = 0;
        } else {
            this.a = 0;
            this.b = measuredHeight - measuredWidth;
        }
        setMeasuredDimension(max, max);
    }
}
