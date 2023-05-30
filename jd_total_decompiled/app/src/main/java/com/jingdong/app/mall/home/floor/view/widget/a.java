package com.jingdong.app.mall.home.floor.view.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class a extends Drawable {

    /* renamed from: e  reason: collision with root package name */
    private TextPaint f10178e;

    /* renamed from: f  reason: collision with root package name */
    private TextPaint f10179f;

    /* renamed from: g  reason: collision with root package name */
    private TextPaint f10180g;

    /* renamed from: m  reason: collision with root package name */
    private Typeface f10186m;
    private String[] r;
    private int[] s;
    private int t;
    private int u;
    private CharSequence a = "00";
    private CharSequence b = "00";

    /* renamed from: c  reason: collision with root package name */
    private CharSequence f10177c = "00";
    private CharSequence d = "00";

    /* renamed from: h  reason: collision with root package name */
    private int f10181h = -16777216;

    /* renamed from: i  reason: collision with root package name */
    private int f10182i = -16777216;

    /* renamed from: j  reason: collision with root package name */
    private int f10183j = -1;

    /* renamed from: k  reason: collision with root package name */
    private int f10184k = 0;

    /* renamed from: l  reason: collision with root package name */
    private int f10185l = 0;

    /* renamed from: n  reason: collision with root package name */
    private boolean f10187n = true;
    private boolean o = false;
    private boolean p = true;
    private boolean q = false;
    private int v = 3;

    public a() {
        TextPaint textPaint = new TextPaint(1);
        this.f10179f = textPaint;
        textPaint.setAntiAlias(true);
        this.f10179f.setStyle(Paint.Style.FILL);
        this.f10179f.setStrokeWidth(2.0f);
        TextPaint textPaint2 = new TextPaint(1);
        this.f10178e = textPaint2;
        textPaint2.setAntiAlias(true);
        TextPaint textPaint3 = new TextPaint(1);
        this.f10180g = textPaint3;
        textPaint3.setAntiAlias(true);
        this.f10180g.setStyle(Paint.Style.STROKE);
        this.f10180g.setStrokeWidth(2.0f);
        b();
    }

    private float a(Canvas canvas, CharSequence charSequence, int i2, float f2, float f3, float f4, int i3, int i4) {
        float f5;
        float f6;
        int i5;
        if (this.u != 1) {
            canvas.drawRect(f2, i2, f3, this.f10185l + i2, this.f10180g);
        } else {
            canvas.drawRoundRect(new RectF(f2, i2 / 2, f3, r2 + this.f10185l), d.d(8), d.d(8), this.f10180g);
        }
        canvas.drawText(charSequence, 0, charSequence.length(), f2 + d(this.f10179f, charSequence), f4, this.f10179f);
        if (!this.f10187n) {
            if (i4 != 3 || this.q) {
                Paint.FontMetricsInt fontMetricsInt = this.f10178e.getFontMetricsInt();
                canvas.drawText(this.r[i4], (i2 / 2.0f) + f3, ((i3 - fontMetricsInt.bottom) - fontMetricsInt.top) / 2, this.f10178e);
            }
            f5 = i2;
            f6 = f3 + f5;
            i5 = this.s[i4];
        } else if (i4 == 3) {
            return i2 + f2;
        } else {
            f5 = i2;
            f6 = f3 + f5;
            canvas.drawText(":", f6, f4, this.f10179f);
            i5 = this.t;
        }
        return f6 + i5 + f5;
    }

    private int c() {
        int i2;
        this.s = new int[4];
        Rect rect = new Rect();
        int i3 = 0;
        try {
            if (this.o) {
                TextPaint textPaint = this.f10178e;
                String[] strArr = this.r;
                textPaint.getTextBounds(strArr[0], 0, strArr[0].length(), rect);
                this.s[0] = rect.width();
                i2 = rect.width() + 0;
            } else {
                i2 = 0;
            }
            try {
                if (this.p) {
                    TextPaint textPaint2 = this.f10178e;
                    String[] strArr2 = this.r;
                    textPaint2.getTextBounds(strArr2[1], 0, strArr2[1].length(), rect);
                    this.s[1] = rect.width();
                    i2 += rect.width();
                }
                TextPaint textPaint3 = this.f10178e;
                String[] strArr3 = this.r;
                textPaint3.getTextBounds(strArr3[2], 0, strArr3[2].length(), rect);
                this.s[2] = rect.width();
                i2 += rect.width();
                if (this.q) {
                    TextPaint textPaint4 = this.f10178e;
                    String[] strArr4 = this.r;
                    textPaint4.getTextBounds(strArr4[3], 0, strArr4[3].length(), rect);
                    this.s[3] = rect.width();
                    return i2 + rect.width();
                }
                return i2;
            } catch (Exception e2) {
                e = e2;
                i3 = i2;
                f.s0(this, e);
                return i3;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    private float d(Paint paint, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return 0.0f;
        }
        return (this.f10184k - paint.measureText(charSequence.toString())) / 2.0f;
    }

    public void b() {
        this.f10179f.setTextSize(d.d(28));
        this.f10178e.setTextSize(d.d(28));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int c2;
        try {
            Typeface typeface = this.f10186m;
            if (typeface == null) {
                typeface = Typeface.MONOSPACE;
            }
            this.f10179f.setTypeface(typeface);
            Rect bounds = getBounds();
            int d = d.d(this.v);
            Rect rect = new Rect();
            this.f10179f.getTextBounds(":", 0, 1, rect);
            this.t = rect.width();
            if (this.f10187n) {
                int i2 = d * 2;
                int i3 = (this.p ? i2 : 0) + i2 + (this.o ? i2 : 0);
                if (!this.q) {
                    i2 = 0;
                }
                c2 = i3 + i2;
            } else {
                c2 = c();
            }
            int i4 = this.f10184k;
            float f2 = (i4 * 2) + (d * 4) + (this.p ? (d * 2) + i4 : 0) + (this.o ? i4 + (d * 2) : 0) + c2;
            int i5 = bounds.right;
            float f3 = 0.0f;
            if (i5 - f2 >= 0.0f) {
                f3 = i5 - f2;
            }
            float f4 = f3 / 2.0f;
            this.f10179f.getTextBounds("00", 0, 2, rect);
            float height = (bounds.height() / 2) + (rect.height() / 2);
            this.f10180g.setColor(this.f10183j);
            this.f10179f.setColor(this.f10181h);
            this.f10178e.setColor(this.f10182i);
            float a = this.o ? a(canvas, this.a, d, f4, f4 + this.f10184k, height, bounds.height(), 0) : f4;
            if (this.p) {
                a = a(canvas, this.b, d, a, a + this.f10184k, height, bounds.height(), 1);
            }
            float a2 = a(canvas, this.f10177c, d, a, a + this.f10184k, height, bounds.height(), 2);
            a(canvas, this.d, d, a2, a2 + this.f10184k, height, bounds.height(), 3);
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
    }

    public void e(int i2) {
        this.f10183j = i2;
    }

    public void f(int i2) {
        this.f10185l = i2;
    }

    public void g(int i2) {
        this.u = i2;
        if (i2 == 0) {
            this.f10180g.setStyle(Paint.Style.STROKE);
        } else if (i2 != 1) {
        } else {
            this.f10180g.setStyle(Paint.Style.FILL);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public void h(int i2) {
        this.f10184k = i2;
    }

    public void i(boolean z) {
        this.p = z;
    }

    public void j(CharSequence charSequence) {
        this.b = charSequence;
    }

    public void k(CharSequence charSequence) {
        this.f10177c = charSequence;
    }

    public void l(CharSequence charSequence) {
        this.d = charSequence;
    }

    public void m(int i2) {
        this.f10181h = i2;
    }

    public void n(float f2) {
        TextPaint textPaint = this.f10179f;
        if (textPaint != null) {
            textPaint.setTextSize(f2);
        }
    }

    public void o(Typeface typeface) {
        this.f10186m = typeface;
        TextPaint textPaint = this.f10179f;
        if (textPaint != null) {
            textPaint.setTypeface(typeface);
        }
    }

    public void p(String str, String str2, String str3, String str4) {
        this.f10187n = false;
        this.r = new String[4];
        if (!TextUtils.isEmpty(str)) {
            this.r[0] = str;
        } else {
            this.r[0] = ":";
        }
        if (!TextUtils.isEmpty(str2)) {
            this.r[1] = str2;
        } else {
            this.r[1] = ":";
        }
        if (!TextUtils.isEmpty(str3)) {
            this.r[2] = str3;
        } else {
            this.r[2] = ":";
        }
        this.r[3] = str4;
        this.q = !TextUtils.isEmpty(str4);
    }

    public void q(float f2) {
        TextPaint textPaint = this.f10178e;
        if (textPaint != null) {
            textPaint.setTextSize(f2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
