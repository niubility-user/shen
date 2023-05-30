package com.jingdong.app.mall.home.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;

/* loaded from: classes4.dex */
public class HomeTitleRightView extends GradientTextView {

    /* renamed from: g  reason: collision with root package name */
    private float f11037g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f11038h;

    /* renamed from: i  reason: collision with root package name */
    private Path f11039i;

    /* renamed from: j  reason: collision with root package name */
    private int f11040j;

    /* renamed from: k  reason: collision with root package name */
    private int f11041k;

    public HomeTitleRightView(Context context) {
        super(context);
        this.f11038h = new Paint();
        this.f11039i = new Path();
        this.f11037g = d.d(1) + 2;
        this.f11038h.setStyle(Paint.Style.STROKE);
        this.f11038h.setStrokeWidth(this.f11037g);
        this.f11038h.setStrokeCap(Paint.Cap.ROUND);
        this.f11038h.setAntiAlias(true);
    }

    public void a(int i2) {
        this.f11038h.setColor(i2);
    }

    public void b(int i2) {
        this.f11041k = i2;
    }

    public void c(boolean z) {
        getPaint().setFakeBoldText(z);
        float d = d.d(z ? 2 : 1) + 2;
        this.f11037g = d;
        this.f11038h.setStrokeWidth(d);
    }

    @Override // android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f11039i.isEmpty() || this.f11040j != getWidth()) {
            this.f11040j = getWidth();
            this.f11039i.reset();
            float height = getHeight() >> 1;
            float width = getWidth() - this.f11037g;
            float d = d.d(8);
            float d2 = width - d.d(6);
            this.f11039i.moveTo(d2 - this.f11041k, height - d);
            this.f11039i.lineTo((width - this.f11041k) + 1.0f, height);
            this.f11039i.lineTo(d2 - this.f11041k, height + d);
        }
        canvas.drawPath(this.f11039i, this.f11038h);
    }
}
