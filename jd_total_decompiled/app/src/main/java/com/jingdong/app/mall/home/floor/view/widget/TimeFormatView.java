package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class TimeFormatView extends View {
    private static String C = TimeFormatView.class.getSimpleName();
    private int A;
    private Rect B;

    /* renamed from: g  reason: collision with root package name */
    private TextPaint f10148g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f10149h;

    /* renamed from: i  reason: collision with root package name */
    private Paint f10150i;

    /* renamed from: j  reason: collision with root package name */
    private String f10151j;

    /* renamed from: k  reason: collision with root package name */
    private String f10152k;

    /* renamed from: l  reason: collision with root package name */
    private String f10153l;

    /* renamed from: m  reason: collision with root package name */
    private int f10154m;

    /* renamed from: n  reason: collision with root package name */
    private int f10155n;
    private int o;
    private RectF p;
    private RectF q;
    private RectF r;
    private int s;
    private int t;
    private float u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    public TimeFormatView(Context context) {
        super(context);
        this.f10151j = "00";
        this.f10152k = "00";
        this.f10153l = "00";
        this.o = d.d(8);
        this.s = d.d(9);
        this.t = 3;
        this.u = d.d(3) / 2.0f;
        this.B = new Rect();
        b();
    }

    private int a(String str) {
        this.f10148g.getTextBounds(str, 0, str.length(), this.B);
        return ((this.f10154m - this.B.width()) - 1) / 2;
    }

    private void b() {
        TextPaint textPaint = new TextPaint(1);
        this.f10148g = textPaint;
        textPaint.setAntiAlias(true);
        this.f10148g.setTextSize(40.0f);
        this.f10148g.setTypeface(Typeface.create(Typeface.MONOSPACE, 1));
        this.f10148g.setStyle(Paint.Style.FILL);
        Paint paint = new Paint(1);
        this.f10149h = paint;
        paint.setAntiAlias(true);
        this.f10149h.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.f10150i = paint2;
        paint2.setAntiAlias(true);
        this.f10150i.setStyle(Paint.Style.FILL);
        this.p = new RectF();
        this.q = new RectF();
        this.r = new RectF();
    }

    private void c() {
        int i2;
        int width = getWidth();
        int height = getHeight();
        if (width == 0 || width == 0) {
            return;
        }
        if (this.f10149h != null) {
            i2 = (width - (this.f10154m * 3)) / 2;
        } else {
            i2 = 0;
            this.f10154m = width / 3;
        }
        this.o = d.d(8);
        this.s = d.d(9);
        this.u = d.d(this.t) / 2.0f;
        RectF rectF = this.p;
        rectF.left = 0.0f;
        RectF rectF2 = this.q;
        int i3 = this.f10154m;
        float f2 = i2;
        float f3 = 0.0f + i3 + f2;
        rectF2.left = f3;
        RectF rectF3 = this.r;
        float f4 = f3 + i3 + f2;
        rectF3.left = f4;
        rectF.right = rectF.left + i3;
        rectF2.right = rectF2.left + i3;
        rectF3.right = f4 + i3;
        int i4 = this.f10155n;
        float f5 = (height - i4) >> 1;
        rectF.top = f5;
        rectF2.top = f5;
        rectF3.top = f5;
        rectF.bottom = rectF.top + i4;
        rectF2.bottom = rectF2.top + i4;
        rectF3.bottom = f5 + i4;
        float f6 = i2 / 2;
        this.v = (int) (rectF.right + f6);
        this.w = (int) (rectF2.right + f6);
        int i5 = this.s;
        int i6 = (height - i5) / 2;
        this.x = i6;
        this.y = i6 + i5;
        d();
        if (Log.D) {
            Log.d(C, "viewW:" + width + " viewH:" + height + " bgW:" + this.f10154m + " space:" + i2);
            String str = C;
            StringBuilder sb = new StringBuilder();
            sb.append("bgX1:");
            sb.append(this.p.left);
            sb.append(" bgX2:");
            sb.append(this.q.left);
            sb.append(" bgX3:");
            sb.append(this.q.left);
            Log.d(str, sb.toString());
            Log.d(C, "circleX1:" + this.v + " circleX2:" + this.w + " circleY1:" + this.x + " circleY2:" + this.y);
        }
    }

    private void d() {
        int height = getHeight();
        if (height == 0) {
            return;
        }
        this.f10148g.getTextBounds("88", 0, 2, this.B);
        this.z = ((this.f10154m - this.B.width()) - 1) / 2;
        this.A = (height + this.B.height()) / 2;
        if (Log.D) {
            Log.d(C, "textW:" + this.B.width() + " textH:" + this.B.height() + " textOffsetX:" + this.z + " textY:" + this.A);
        }
    }

    public void e(int i2) {
        if (((-16777216) & i2) == 0) {
            this.f10149h = null;
        } else if (this.f10149h == null) {
            Paint paint = new Paint(1);
            this.f10149h = paint;
            paint.setAntiAlias(true);
            this.f10149h.setStyle(Paint.Style.FILL);
        }
        Paint paint2 = this.f10149h;
        if (paint2 != null) {
            paint2.setShader(null);
            this.f10149h.setColor(i2);
        }
    }

    public void f(int[] iArr) {
        if (this.f10149h != null) {
            if (iArr.length == 1) {
                e(iArr[0]);
            } else if (iArr.length > 1) {
                this.f10149h.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, this.f10155n, iArr, (float[]) null, Shader.TileMode.CLAMP));
            }
        }
    }

    public void g(int i2) {
        this.f10155n = i2;
    }

    public void h(int i2) {
        this.f10154m = i2;
    }

    public void i(int i2) {
        this.f10150i.setColor(i2);
    }

    public void j(int i2) {
        this.t = i2;
        this.u = d.d(i2) / 2.0f;
    }

    public void k(int i2) {
        this.f10148g.setColor(i2);
    }

    public void l(float f2) {
        this.f10148g.setTextSize(f2);
        d();
    }

    public void m(String str, String str2, String str3) {
        this.f10151j = str;
        this.f10152k = str2;
        this.f10153l = str3;
        postInvalidate();
    }

    public void n(Typeface typeface) {
        this.f10148g.setTypeface(typeface);
        d();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Paint paint = this.f10149h;
        if (paint != null) {
            RectF rectF = this.p;
            int i2 = this.o;
            canvas.drawRoundRect(rectF, i2, i2, paint);
            RectF rectF2 = this.q;
            int i3 = this.o;
            canvas.drawRoundRect(rectF2, i3, i3, this.f10149h);
            RectF rectF3 = this.r;
            int i4 = this.o;
            canvas.drawRoundRect(rectF3, i4, i4, this.f10149h);
        }
        String str = this.f10151j;
        canvas.drawText(str, 0, str.length(), a(this.f10151j) + this.p.left, this.A, (Paint) this.f10148g);
        String str2 = this.f10152k;
        canvas.drawText(str2, 0, str2.length(), a(this.f10152k) + this.q.left, this.A, (Paint) this.f10148g);
        String str3 = this.f10153l;
        canvas.drawText(str3, 0, str3.length(), a(this.f10153l) + this.r.left, this.A, (Paint) this.f10148g);
        canvas.drawCircle(this.v, this.x, this.u, this.f10150i);
        canvas.drawCircle(this.v, this.y, this.u, this.f10150i);
        canvas.drawCircle(this.w, this.x, this.u, this.f10150i);
        canvas.drawCircle(this.w, this.y, this.u, this.f10150i);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        c();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (Log.D) {
            Log.d(C, "onSizeChanged");
        }
        c();
    }

    public TimeFormatView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10151j = "00";
        this.f10152k = "00";
        this.f10153l = "00";
        this.o = d.d(8);
        this.s = d.d(9);
        this.t = 3;
        this.u = d.d(3) / 2.0f;
        this.B = new Rect();
        b();
    }

    public TimeFormatView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f10151j = "00";
        this.f10152k = "00";
        this.f10153l = "00";
        this.o = d.d(8);
        this.s = d.d(9);
        this.t = 3;
        this.u = d.d(3) / 2.0f;
        this.B = new Rect();
        b();
    }
}
