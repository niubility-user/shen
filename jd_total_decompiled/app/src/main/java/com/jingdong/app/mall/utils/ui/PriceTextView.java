package com.jingdong.app.mall.utils.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class PriceTextView extends TextView {
    public static final String o = PriceTextView.class.getSimpleName();

    /* renamed from: g  reason: collision with root package name */
    private Paint f11894g;

    /* renamed from: h  reason: collision with root package name */
    private float f11895h;

    /* renamed from: i  reason: collision with root package name */
    private float f11896i;

    /* renamed from: j  reason: collision with root package name */
    private DisplayMetrics f11897j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f11898k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f11899l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f11900m;

    /* renamed from: n  reason: collision with root package name */
    private int f11901n;

    public PriceTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11894g = null;
        this.f11897j = null;
        this.f11898k = false;
        this.f11899l = false;
        this.f11900m = false;
        this.f11894g = new TextPaint();
        this.f11897j = context.getResources().getDisplayMetrics();
        this.f11895h = getTextSize();
        if (Log.D) {
            Log.d(o, " PriceTextView -->>defaultTextSize \uff1a  " + this.f11895h);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.pricetext);
        this.f11898k = obtainStyledAttributes.getBoolean(0, false);
        this.f11899l = obtainStyledAttributes.getBoolean(1, false);
        this.f11900m = obtainStyledAttributes.getBoolean(2, false);
        this.f11901n = context.getResources().getDimensionPixelSize(R.dimen.cart_limit_max_width);
    }

    private float a(float f2, String str, float f3) {
        this.f11894g.setTextSize(f3);
        if (this.f11894g.measureText(str) >= (f2 - (str.length() * 1.2f)) - 5.0f) {
            if (Log.D) {
                Log.d(o, " caleTextSize ---> in : ");
            }
            return a(f2, str, f3 - 1.0f);
        }
        if (Log.D) {
            Log.d(o, " caleTextSize ---> out : ");
        }
        return f3;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        String charSequence = getText().toString();
        float measureText = getPaint().measureText(charSequence);
        if (this.f11900m && getWidth() < this.f11901n) {
            getPaint().setColor(getCurrentTextColor());
            canvas.drawText(charSequence, (getWidth() - measureText) / 2.0f, (getHeight() >> 1) + (getTextSize() / 3.0f), getPaint());
            return;
        }
        getPaint().setTextSize(TypedValue.applyDimension(2, DPIUtil.px2sp(getContext(), this.f11895h), this.f11897j));
        this.f11896i = a(getWidth(), charSequence, this.f11895h);
        float f2 = this.f11896i;
        float height = (getHeight() >> 1) + (f2 / 3.0f);
        if (f2 != this.f11895h) {
            getPaint().setTextSize(TypedValue.applyDimension(2, DPIUtil.px2sp(getContext(), this.f11896i), this.f11897j));
        }
        getPaint().setColor(getCurrentTextColor());
        if (this.f11898k) {
            canvas.drawText(charSequence, 0.0f, height, getPaint());
        } else if (this.f11899l) {
            canvas.drawText(charSequence, (getWidth() - measureText) / 2.0f, height, getPaint());
        } else {
            float width = getWidth() - measureText;
            if (Log.D) {
                Log.d(o, "text -->> :" + charSequence);
            }
            canvas.drawText(charSequence, width, height, getPaint());
        }
    }
}
