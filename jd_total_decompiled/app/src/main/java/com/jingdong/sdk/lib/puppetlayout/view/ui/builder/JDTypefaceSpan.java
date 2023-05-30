package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

/* loaded from: classes8.dex */
public class JDTypefaceSpan extends TypefaceSpan {

    /* renamed from: g  reason: collision with root package name */
    private final int f15235g;

    /* renamed from: h  reason: collision with root package name */
    private Context f15236h;

    public JDTypefaceSpan(Context context, int i2) {
        super("");
        this.f15236h = context;
        this.f15235g = i2;
    }

    private static void a(Paint paint, Context context, int i2) {
        Typeface typeface = paint.getTypeface();
        int style = typeface == null ? 0 : typeface.getStyle();
        Typeface b = com.jingdong.sdk.lib.puppetlayout.e.a.b(context, i2);
        int style2 = (b.getStyle() ^ (-1)) & style;
        if ((style2 & 1) != 0) {
            paint.setFakeBoldText(true);
        }
        if ((style2 & 2) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(b);
    }

    @Override // android.text.style.TypefaceSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        try {
            a(textPaint, this.f15236h, this.f15235g);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.text.style.TypefaceSpan, android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        try {
            a(textPaint, this.f15236h, this.f15235g);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
