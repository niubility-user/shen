package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.widget.TextView;

/* loaded from: classes9.dex */
public class oc extends TextView {

    /* renamed from: g  reason: collision with root package name */
    private int f16929g;

    /* renamed from: h  reason: collision with root package name */
    private float f16930h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f16931i;

    public oc(Context context) {
        super(context);
        setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        getPaint().setFakeBoldText(true);
    }

    @Override // android.view.View
    public void invalidate() {
        if (this.f16931i) {
            return;
        }
        super.invalidate();
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f16930h <= 0.0f) {
            super.onDraw(canvas);
            return;
        }
        this.f16931i = true;
        int currentTextColor = getCurrentTextColor();
        TextPaint paint = getPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(this.f16930h);
        setTextColor(this.f16929g);
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(0.0f);
        setTextColor(currentTextColor);
        super.onDraw(canvas);
        setTextColor(currentTextColor);
        this.f16931i = false;
    }

    public void setStrokeColor(int i2) {
        this.f16929g = i2;
    }

    public void setStrokeWidth(float f2) {
        this.f16930h = f2;
    }
}
