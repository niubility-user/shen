package com.jingdong.app.mall.home.category.floor.base;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes4.dex */
public class d extends ReplacementSpan {

    /* renamed from: g  reason: collision with root package name */
    private String f8682g;

    /* renamed from: h  reason: collision with root package name */
    private final float f8683h;

    /* renamed from: i  reason: collision with root package name */
    private final float f8684i;

    public d(float f2) {
        this(f2, 0.0f);
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, @NonNull Paint paint) {
        canvas.drawText(this.f8682g, f2 + this.f8684i, i5 + this.f8683h, paint);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(@NonNull Paint paint, CharSequence charSequence, int i2, int i3, @Nullable Paint.FontMetricsInt fontMetricsInt) {
        String charSequence2 = charSequence.subSequence(i2, i3).toString();
        this.f8682g = charSequence2;
        return (int) paint.measureText(charSequence2);
    }

    public d(float f2, float f3) {
        this.f8683h = f2;
        this.f8684i = f3;
    }
}
