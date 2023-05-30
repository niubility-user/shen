package com.jingdong.app.mall.home.category.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;

/* loaded from: classes4.dex */
public class CaBottomLine extends View {

    /* renamed from: g  reason: collision with root package name */
    private Paint f8811g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f8812h;

    /* renamed from: i  reason: collision with root package name */
    private LinearGradient f8813i;

    /* renamed from: j  reason: collision with root package name */
    private int[] f8814j;

    /* renamed from: k  reason: collision with root package name */
    private int f8815k;

    /* renamed from: l  reason: collision with root package name */
    private int f8816l;

    /* renamed from: m  reason: collision with root package name */
    private int f8817m;

    public CaBottomLine(Context context) {
        super(context);
        this.f8811g = new Paint(1);
        this.f8812h = new Paint(1);
    }

    public void a(int i2, int i3, int i4, int i5, int... iArr) {
        if (this.f8816l == i4 && this.f8817m == i3) {
            return;
        }
        this.f8815k = i2;
        this.f8813i = null;
        this.f8814j = iArr;
        this.f8816l = i4;
        this.f8817m = i3;
        this.f8812h.setColor(i5);
        postInvalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            int width = (getWidth() - this.f8816l) >> 1;
            if (this.f8813i == null) {
                float f2 = this.f8816l + width;
                int[] iArr = this.f8814j;
                LinearGradient linearGradient = new LinearGradient(width, 0.0f, f2, 0.0f, new int[]{iArr[0], iArr[1]}, (float[]) null, Shader.TileMode.CLAMP);
                this.f8813i = linearGradient;
                this.f8811g.setShader(linearGradient);
            }
            float f3 = width;
            canvas.drawRect(f3, getHeight() - this.f8815k, this.f8816l + width, getHeight(), this.f8811g);
            canvas.drawRect(f3, getHeight() - this.f8815k, width + this.f8817m, getHeight(), this.f8812h);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
