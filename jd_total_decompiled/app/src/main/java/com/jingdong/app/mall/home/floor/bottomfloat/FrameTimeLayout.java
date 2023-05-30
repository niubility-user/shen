package com.jingdong.app.mall.home.floor.bottomfloat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.x.e;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class FrameTimeLayout extends LinearLayout {
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_NORMAL_TEXT = 2;
    public static final int TYPE_STROKE_TEXT = 1;
    private final RectF bg1Rect;
    private final RectF bg2Rect;
    private final RectF bg3Rect;
    private final Paint bgPaint;
    private boolean changeSize;
    private String d0;
    private final RectF d0Rect;
    private String d1;
    private final RectF d1Rect;
    private final Paint dayPaint;
    private int daySize;
    private String h0;
    private final RectF h0Rect;
    private String h1;
    private final RectF h1Rect;
    private String h2;
    private final RectF h2Rect;
    private String h3;
    private final RectF h3Rect;
    private int leftStart;
    private String m0;
    private final RectF m0Rect;
    private String m1;
    private final RectF m1Rect;
    private String s0;
    private final RectF s0Rect;
    private String s1;
    private final RectF s1Rect;
    private final HashMap<String, Pair<Integer, Integer>> sBounds;
    boolean showBG;
    boolean showDay;
    boolean showMinute;
    boolean showTime;
    private final a sizeInfo;
    private SpannableString text;
    private final Rect textBounds;
    private LinearLayout.LayoutParams textParams;
    private int textSize;
    private final Paint timePaint;
    private int timeSize;
    private TextView timeText;
    private int type;
    int yCenter;

    /* loaded from: classes4.dex */
    public static class a {
        int a;
        int b;

        /* renamed from: c  reason: collision with root package name */
        int f9170c;
        int d;

        /* renamed from: e  reason: collision with root package name */
        int f9171e;

        /* renamed from: f  reason: collision with root package name */
        int f9172f;

        /* renamed from: g  reason: collision with root package name */
        int f9173g;

        /* renamed from: h  reason: collision with root package name */
        int f9174h;

        /* renamed from: i  reason: collision with root package name */
        int f9175i;

        /* renamed from: j  reason: collision with root package name */
        int f9176j;

        /* renamed from: k  reason: collision with root package name */
        int f9177k;

        /* renamed from: l  reason: collision with root package name */
        int f9178l;

        /* JADX INFO: Access modifiers changed from: private */
        public void c(b bVar) {
            this.a = d.d(2);
            this.b = d.d(6);
            this.f9170c = d.d(15);
            this.d = d.d(28);
            this.f9171e = d.d(12);
            this.f9172f = d.d(16);
            this.f9173g = d.d(1);
            this.f9174h = d.d(10);
            this.f9175i = d.d(5);
            this.f9176j = d.d(4);
            this.f9177k = d.d(2);
            int i2 = bVar.C;
            int i3 = R2.attr.chipGroupStyle;
            if (i2 != 1 && i2 != 2) {
                if (i2 == 3) {
                    this.f9172f = d.d(8);
                    if (bVar.f9189c) {
                        i3 = R2.attr.blendSrc;
                    }
                    this.f9178l = d.d(i3);
                }
            } else if (bVar.B == 0) {
                this.f9172f = d.d(12);
                this.f9174h = d.d(20);
                if (bVar.f9189c) {
                    i3 = R2.attr.banner_looper;
                }
                this.f9178l = d.d(i3);
            } else {
                this.a = d.d(1) + 1;
                this.f9170c = d.d(11);
                this.d = d.d(20);
                this.f9171e = d.d(10);
                this.f9172f = d.d(8);
                this.f9174h = d.d(6);
                this.f9175i = d.d(1);
                if (bVar.f9189c) {
                    i3 = R2.attr.animSize;
                }
                this.f9178l = d.d(i3);
            }
        }

        public void b() {
            this.a = d.d(2);
            this.b = d.d(6);
            this.f9170c = d.d(15);
            this.d = d.d(28);
            this.f9171e = d.d(12);
            this.f9172f = d.d(16);
            this.f9173g = d.d(1);
            this.f9174h = d.d(10);
            this.f9175i = d.d(5);
            this.f9176j = d.d(4);
            this.f9177k = d.d(4);
        }
    }

    public FrameTimeLayout(Context context) {
        super(context);
        this.dayPaint = new Paint(1);
        this.timePaint = new Paint(1);
        this.bgPaint = new Paint(1);
        this.textBounds = new Rect();
        this.sBounds = new HashMap<>();
        this.d0 = "";
        this.d0Rect = new RectF();
        this.d1 = "";
        this.d1Rect = new RectF();
        this.h0Rect = new RectF();
        this.h1Rect = new RectF();
        this.h2Rect = new RectF();
        this.h3Rect = new RectF();
        this.m0Rect = new RectF();
        this.m1Rect = new RectF();
        this.s0Rect = new RectF();
        this.s1Rect = new RectF();
        this.bg1Rect = new RectF();
        this.bg2Rect = new RectF();
        this.bg3Rect = new RectF();
        a aVar = new a();
        this.sizeInfo = aVar;
        this.type = 0;
        this.textSize = 26;
        this.daySize = d.d(24);
        this.timeSize = 28;
        g gVar = new g(context, false);
        gVar.h();
        gVar.c(true);
        gVar.d(16);
        this.timeText = gVar.a();
        this.textParams = new LinearLayout.LayoutParams(-2, -1);
        aVar.b();
        addView(this.timeText, this.textParams);
    }

    private void drawBg(Canvas canvas, RectF rectF) {
        int i2 = this.sizeInfo.b;
        canvas.drawRoundRect(rectF, i2, i2, this.bgPaint);
    }

    private void drawPoint(Canvas canvas, RectF rectF, RectF rectF2) {
        if (this.showMinute) {
            float f2 = (rectF.right + rectF2.left) / 2.0f;
            int i2 = this.sizeInfo.a;
            float f3 = i2 * 2.0f;
            canvas.drawCircle(f2, this.yCenter - f3, i2, this.timePaint);
            canvas.drawCircle(f2, this.yCenter + f3, this.sizeInfo.a, this.timePaint);
        }
    }

    private void drawText(Canvas canvas, String str, RectF rectF) {
        drawText(canvas, this.timePaint, str, 0, rectF);
    }

    private void safeDraw(Canvas canvas) {
        try {
            drawText(canvas, this.d0, this.d0Rect);
            drawText(canvas, this.dayPaint, this.d1, -d.d(2), this.d1Rect);
            drawText(canvas, this.h0, this.h0Rect);
            drawText(canvas, this.h1, this.h1Rect);
            drawText(canvas, this.dayPaint, this.h2, -d.d(2), this.h2Rect);
            drawText(canvas, this.dayPaint, this.h3, -d.d(2), this.h3Rect);
            drawPoint(canvas, this.h1Rect, this.m0Rect);
            drawText(canvas, this.m0, this.m0Rect);
            drawText(canvas, this.m1, this.m1Rect);
            drawPoint(canvas, this.m1Rect, this.s0Rect);
            drawText(canvas, this.s0, this.s0Rect);
            drawText(canvas, this.s1, this.s1Rect);
            if (this.showBG) {
                drawBg(canvas, this.bg1Rect);
                drawBg(canvas, this.bg2Rect);
                drawBg(canvas, this.bg3Rect);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void setInfo(float f2, int i2) {
        boolean z = this.showDay;
        if (f2 == (z ? this.d0Rect : this.h0Rect).left && i2 == this.yCenter) {
            return;
        }
        this.yCenter = i2;
        int i3 = this.sizeInfo.f9171e;
        int i4 = i2 - i3;
        int i5 = i2 + i3;
        if (z) {
            float f3 = i4;
            float f4 = i5;
            this.d0Rect.set(f2, f3, r1.f9170c + f2, f4);
            RectF rectF = this.d1Rect;
            float f5 = this.d0Rect.right;
            int i6 = this.sizeInfo.f9173g;
            rectF.set(i6 + f5, f3, f5 + i6 + r1.d, f4);
        } else {
            this.d0Rect.set(0.0f, 0.0f, 0.0f, 0.0f);
            RectF rectF2 = this.d1Rect;
            int i7 = this.sizeInfo.f9175i;
            rectF2.set(f2 - i7, 0.0f, f2 - i7, 0.0f);
        }
        RectF rectF3 = this.h0Rect;
        float f6 = this.d1Rect.right;
        int i8 = this.sizeInfo.f9175i;
        float f7 = i4;
        float f8 = i5;
        rectF3.set(i8 + f6, f7, f6 + i8 + r1.f9170c, f8);
        RectF rectF4 = this.h1Rect;
        float f9 = this.h0Rect.right;
        int i9 = this.sizeInfo.f9173g;
        rectF4.set(i9 + f9, f7, f9 + i9 + r1.f9170c, f8);
        RectF rectF5 = this.h2Rect;
        float f10 = this.h1Rect.right;
        int i10 = this.sizeInfo.f9173g;
        rectF5.set(i10 + f10, f7, f10 + i10 + r1.d, f8);
        RectF rectF6 = this.h3Rect;
        float f11 = this.h2Rect.right;
        rectF6.set(f11, f7, this.sizeInfo.d + f11, f8);
        RectF rectF7 = this.m0Rect;
        float f12 = this.h1Rect.right;
        int i11 = this.sizeInfo.f9174h;
        rectF7.set(i11 + f12, f7, f12 + i11 + r1.f9170c, f8);
        RectF rectF8 = this.m1Rect;
        float f13 = this.m0Rect.right;
        int i12 = this.sizeInfo.f9173g;
        rectF8.set(i12 + f13, f7, f13 + i12 + r1.f9170c, f8);
        RectF rectF9 = this.s0Rect;
        float f14 = this.m1Rect.right;
        int i13 = this.sizeInfo.f9174h;
        rectF9.set(i13 + f14, f7, f14 + i13 + r1.f9170c, f8);
        RectF rectF10 = this.s1Rect;
        float f15 = this.s0Rect.right;
        int i14 = this.sizeInfo.f9173g;
        rectF10.set(i14 + f15, f7, f15 + i14 + r1.f9170c, f8);
        RectF rectF11 = this.bg1Rect;
        RectF rectF12 = this.h0Rect;
        float f16 = rectF12.left;
        a aVar = this.sizeInfo;
        float f17 = rectF12.top;
        int i15 = aVar.f9177k;
        rectF11.set(f16 - aVar.f9176j, f17 - i15, this.h1Rect.right + aVar.f9175i, rectF12.bottom + i15);
        RectF rectF13 = this.bg2Rect;
        RectF rectF14 = this.m0Rect;
        float f18 = rectF14.left;
        a aVar2 = this.sizeInfo;
        float f19 = rectF14.top;
        int i16 = aVar2.f9177k;
        rectF13.set(f18 - aVar2.f9176j, f19 - i16, this.m1Rect.right + aVar2.f9175i, rectF14.bottom + i16);
        RectF rectF15 = this.bg3Rect;
        RectF rectF16 = this.s0Rect;
        float f20 = rectF16.left;
        a aVar3 = this.sizeInfo;
        float f21 = rectF16.top;
        int i17 = aVar3.f9177k;
        rectF15.set(f20 - aVar3.f9176j, f21 - i17, this.s1Rect.right + aVar3.f9175i, rectF16.bottom + i17);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.showTime) {
            setInfo(this.leftStart, getHeight() >> 1);
            safeDraw(canvas);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initDefSize() {
        this.sizeInfo.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initFrameThree(b bVar) {
        this.sizeInfo.c(bVar);
    }

    public void setDayTextSize(int i2) {
        this.daySize = i2;
    }

    public void setFrameInfo(Context context, b bVar) {
        setTextPaint(context, bVar.f9194i);
        int d = d.d(bVar.F);
        setPadding(d, 0, 0, 0);
        int i2 = TextUtils.isEmpty(this.text) ? 0 : this.sizeInfo.f9172f;
        int i3 = this.sizeInfo.f9178l;
        if (i3 <= 0) {
            i3 = d.d(bVar.f9189c ? 415 : R2.attr.chipGroupStyle);
        }
        int max = Math.max((i3 - d) - i2, 0);
        g.o(this.timeText, this.textSize);
        LinearLayout.LayoutParams layoutParams = this.textParams;
        layoutParams.width = max;
        this.timeText.setLayoutParams(layoutParams);
        this.timeText.setTextColor((bVar.b() && bVar.B == 1) ? -381927 : bVar.E);
        this.timeText.setText(this.text);
        this.leftStart = d + Math.min((int) this.timeText.getPaint().measureText(this.timeText.getText().toString()), max) + i2;
    }

    public void setText(SpannableString spannableString) {
        this.text = spannableString;
    }

    public void setTextPaint(Context context, int i2) {
        this.sBounds.clear();
        this.changeSize = false;
        this.dayPaint.setTextSize(this.daySize);
        this.dayPaint.setColor(i2);
        this.timePaint.setTextSize(d.d(this.timeSize));
        this.timePaint.setColor(i2);
        this.timePaint.setTypeface(FontsUtil.getTypeFace(context, 4099));
        this.bgPaint.setColor(-152669);
        this.bgPaint.setStyle(Paint.Style.STROKE);
        this.bgPaint.setStrokeWidth(d.d(1) + 1);
    }

    public void setTextSize(int i2) {
        this.textSize = i2;
    }

    public void setTime(e eVar) {
        if (eVar.b > 0) {
            this.d0 = Math.min(eVar.b, 9L) + "";
            this.d1 = "\u5929";
            this.showDay = true;
        } else {
            this.showDay = false;
            this.d1 = "";
            this.d0 = "";
        }
        this.h0 = (((int) eVar.f11087c) / 10) + "";
        this.h1 = (((int) eVar.f11087c) % 10) + "";
        int i2 = this.type;
        if ((i2 == 1 || i2 == 2) && this.showDay) {
            this.showMinute = false;
            this.showBG = false;
            this.h2 = "\u5c0f";
            this.h3 = "\u65f6";
            this.s1 = "";
            this.s0 = "";
            this.m1 = "";
            this.m0 = "";
        } else {
            this.showMinute = true;
            this.showBG = i2 == 1;
            this.h3 = "";
            this.h2 = "";
            this.m0 = (((int) eVar.f11088e) / 10) + "";
            this.m1 = (((int) eVar.f11088e) % 10) + "";
            this.s0 = (((int) eVar.f11089f) / 10) + "";
            this.s1 = (((int) eVar.f11089f) % 10) + "";
        }
        if (this.showBG && !this.changeSize) {
            this.sBounds.clear();
            this.changeSize = true;
            this.timePaint.setTextSize(d.d(this.timeSize - 4));
            this.sizeInfo.f9170c = d.d(13);
        }
        if (this.showTime) {
            invalidate();
        }
    }

    public void setTimeSize(int i2) {
        this.timeSize = i2;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public void setTypeface(Typeface typeface) {
        TextView textView = this.timeText;
        if (textView == null) {
            return;
        }
        textView.setTypeface(typeface);
    }

    public void showTime(boolean z) {
        this.showTime = z;
        if (f.o0()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    private void drawText(Canvas canvas, Paint paint, String str, int i2, RectF rectF) {
        int intValue;
        int i3;
        Pair<Integer, Integer> pair = this.sBounds.get(str);
        if (pair == null) {
            paint.getTextBounds(str, 0, str.length(), this.textBounds);
            i3 = this.textBounds.width();
            intValue = this.textBounds.height();
            this.sBounds.put(str, new Pair<>(Integer.valueOf(i3), Integer.valueOf(intValue)));
        } else {
            int intValue2 = ((Integer) pair.first).intValue();
            intValue = ((Integer) pair.second).intValue();
            i3 = intValue2;
        }
        canvas.drawText(str, 0, str.length(), ((rectF.left + rectF.right) - i3) / 2.0f, this.yCenter + (intValue >> 1) + i2, paint);
    }
}
