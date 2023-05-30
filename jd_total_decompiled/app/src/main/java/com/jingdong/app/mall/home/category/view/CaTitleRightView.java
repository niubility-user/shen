package com.jingdong.app.mall.home.category.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.appcompat.widget.AppCompatTextView;
import com.jingdong.app.mall.home.floor.common.d;

/* loaded from: classes4.dex */
public class CaTitleRightView extends AppCompatTextView {

    /* renamed from: g  reason: collision with root package name */
    private final float f8762g;

    /* renamed from: h  reason: collision with root package name */
    private final Paint f8763h;

    /* renamed from: i  reason: collision with root package name */
    private final Path f8764i;

    /* renamed from: j  reason: collision with root package name */
    private final int f8765j;

    /* renamed from: k  reason: collision with root package name */
    private final int f8766k;

    /* renamed from: l  reason: collision with root package name */
    private int f8767l;

    /* renamed from: m  reason: collision with root package name */
    private int f8768m;

    public CaTitleRightView(Context context) {
        this(context, 7, 9);
    }

    @Override // android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f8764i.isEmpty() || this.f8767l != getWidth()) {
            this.f8767l = getWidth();
            this.f8764i.reset();
            float height = getHeight() >> 1;
            float width = getWidth() - this.f8762g;
            float d = d.d(this.f8766k);
            float d2 = width - d.d(this.f8765j);
            this.f8764i.moveTo(d2 - this.f8768m, height - d);
            this.f8764i.lineTo(width - this.f8768m, height);
            this.f8764i.lineTo(d2 - this.f8768m, height + d);
        }
        canvas.drawPath(this.f8764i, this.f8763h);
    }

    @Override // android.widget.TextView
    public void setTextColor(int i2) {
        super.setTextColor(i2);
        this.f8763h.setColor(i2);
    }

    public CaTitleRightView(Context context, int i2, int i3) {
        super(context);
        Paint paint = new Paint();
        this.f8763h = paint;
        this.f8764i = new Path();
        this.f8765j = i2;
        this.f8766k = i3;
        float d = d.d(2);
        this.f8762g = d;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(d);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
    }
}
