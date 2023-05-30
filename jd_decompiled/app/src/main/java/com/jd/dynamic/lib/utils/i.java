package com.jd.dynamic.lib.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;

/* loaded from: classes13.dex */
public class i extends MetricAffectingSpan {

    /* renamed from: h  reason: collision with root package name */
    private static Typeface f2269h;

    /* renamed from: i  reason: collision with root package name */
    private static Typeface f2270i;

    /* renamed from: j  reason: collision with root package name */
    private static Typeface f2271j;

    /* renamed from: g  reason: collision with root package name */
    private final Typeface f2272g;

    public i(Typeface typeface) {
        this.f2272g = typeface;
    }

    public static Typeface a(Context context, String str) {
        if (context != null) {
            if (DYConstants.DY_JD_ZH_BOLD.equals(str) || "JDZH".equals(str)) {
                if (f2269h == null && DynamicSdk.getEngine().getUniConfig() != null) {
                    f2269h = DynamicSdk.getEngine().getUniConfig().getTypefaceWithName(DYConstants.DY_JD_ZH_BOLD);
                }
                return f2269h;
            } else if (DYConstants.DY_JD_ZH_NORMAL.equals(str)) {
                if (f2270i == null && DynamicSdk.getEngine().getUniConfig() != null) {
                    f2270i = DynamicSdk.getEngine().getUniConfig().getTypefaceWithName(DYConstants.DY_JD_ZH_NORMAL);
                }
                return f2270i;
            } else if (DYConstants.DY_JD_BOLD.equals(str)) {
                if (f2271j == null && DynamicSdk.getEngine().getUniConfig() != null) {
                    f2271j = DynamicSdk.getEngine().getUniConfig().getTypefaceWithName(DYConstants.DY_JD_BOLD);
                }
                return f2271j;
            } else if (DYConstants.DY_JD_NORMAL.equals(str)) {
                if (f2269h == null && DynamicSdk.getEngine().getUniConfig() != null) {
                    f2269h = DynamicSdk.getEngine().getUniConfig().getTypefaceWithName(DYConstants.DY_JD_NORMAL);
                }
                return f2269h;
            } else if (DYConstants.DY_JD_LIGHT.equals(str)) {
                if (f2270i == null && DynamicSdk.getEngine().getUniConfig() != null) {
                    f2270i = DynamicSdk.getEngine().getUniConfig().getTypefaceWithName(DYConstants.DY_JD_LIGHT);
                }
                return f2270i;
            }
        }
        return f2269h;
    }

    private void b(Paint paint) {
        if (this.f2272g == null || paint == null) {
            return;
        }
        Typeface typeface = paint.getTypeface();
        int style = (typeface != null ? typeface.getStyle() : 0) & (this.f2272g.getStyle() ^ (-1));
        if ((style & 1) != 0) {
            paint.setFakeBoldText(true);
        }
        if ((style & 2) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(this.f2272g);
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        b(textPaint);
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        b(textPaint);
    }
}
