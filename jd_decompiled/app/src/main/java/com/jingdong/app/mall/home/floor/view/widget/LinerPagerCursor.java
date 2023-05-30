package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class LinerPagerCursor extends View {

    /* renamed from: g  reason: collision with root package name */
    protected int f10110g;

    /* renamed from: h  reason: collision with root package name */
    protected int f10111h;

    /* renamed from: i  reason: collision with root package name */
    protected int f10112i;

    /* renamed from: j  reason: collision with root package name */
    protected int f10113j;

    /* renamed from: k  reason: collision with root package name */
    protected int f10114k;

    /* renamed from: l  reason: collision with root package name */
    protected int f10115l;

    /* renamed from: m  reason: collision with root package name */
    protected int f10116m;

    /* renamed from: n  reason: collision with root package name */
    protected Paint f10117n;
    protected Paint o;
    protected Paint p;
    protected RectF q;
    protected int r;
    protected boolean s;

    public LinerPagerCursor(Context context) {
        super(context);
        this.f10110g = d.d(12);
        this.f10111h = d.d(12);
        this.f10112i = d.d(5);
        this.f10113j = d.d(8);
        this.f10114k = -1;
        this.f10115l = 0;
        this.f10116m = 1;
        this.r = 0;
        b();
    }

    private void b() {
        f(-1, -1, ViewCompat.MEASURED_SIZE_MASK);
        g(this.f10110g, this.f10112i, this.f10113j);
    }

    private void e() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        int i2 = ((this.f10110g + this.f10113j) * (this.f10116m - 1)) + this.f10111h;
        this.f10114k = i2;
        layoutParams.width = i2;
        layoutParams.height = this.f10112i;
        requestLayout();
    }

    public void a(Canvas canvas) {
        RectF rectF = this.q;
        rectF.left = 0.0f;
        rectF.right = 0.0f;
        int i2 = 0;
        while (i2 < this.f10116m) {
            RectF rectF2 = this.q;
            float f2 = rectF2.right + (i2 == 0 ? 0 : this.f10113j);
            rectF2.left = f2;
            int i3 = this.r;
            rectF2.right = f2 + (i2 == i3 ? this.f10111h : this.f10110g);
            int i4 = this.f10112i;
            canvas.drawRoundRect(rectF2, i4 / 2.0f, i4 / 2.0f, i2 == i3 ? this.p : this.f10117n);
            i2++;
        }
        RectF rectF3 = this.q;
        rectF3.left = 0.0f;
        rectF3.right = this.f10115l;
        int i5 = this.f10112i;
        canvas.drawRoundRect(rectF3, i5 / 2.0f, i5 / 2.0f, this.o);
    }

    public void c(int i2, float f2, int i3) {
        if (Log.D) {
            Log.d("LinerPagerCursor", "onPageScrolled p:" + i2 + " offset:" + f2 + " px:" + i3);
        }
        int i4 = this.s ? this.f10110g : 0;
        int i5 = this.f10110g;
        int i6 = this.f10113j;
        int i7 = (int) (((i5 + i6) * i2) + i5 + ((i4 + i6) * f2));
        this.f10115l = i7;
        int i8 = this.f10114k;
        if (i8 > 0 && i7 > i8) {
            this.f10115l = i8;
        }
        postInvalidate();
    }

    public void d(int i2) {
        if (Log.D) {
            Log.d("LinerPagerCursor", "onPageSelected " + i2);
        }
        this.r = i2;
        postInvalidate();
    }

    public void f(int i2, int i3, int i4) {
        if (this.f10117n == null) {
            Paint paint = new Paint();
            this.f10117n = paint;
            paint.setAntiAlias(true);
            Paint paint2 = new Paint();
            this.o = paint2;
            paint2.setAntiAlias(true);
            Paint paint3 = new Paint();
            this.p = paint3;
            paint3.setAntiAlias(true);
        }
        this.s = i3 != i4;
        this.f10117n.setColor(i2);
        this.o.setColor(i3);
        this.p.setColor(i4);
    }

    public void g(int i2, int i3, int i4) {
        h(i2, i2, i3, i4);
    }

    public void h(int i2, int i3, int i4, int i5) {
        this.f10110g = i2;
        this.f10112i = i4;
        this.f10111h = i3;
        this.f10113j = i5;
        RectF rectF = new RectF();
        this.q = rectF;
        rectF.top = 0.0f;
        rectF.bottom = i4;
        postInvalidate();
    }

    public void i(int i2) {
        this.f10116m = i2;
        if (i2 <= 0) {
            this.f10116m = 1;
        }
        e();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a(canvas);
    }

    public LinerPagerCursor(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10110g = d.d(12);
        this.f10111h = d.d(12);
        this.f10112i = d.d(5);
        this.f10113j = d.d(8);
        this.f10114k = -1;
        this.f10115l = 0;
        this.f10116m = 1;
        this.r = 0;
        b();
    }

    public LinerPagerCursor(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f10110g = d.d(12);
        this.f10111h = d.d(12);
        this.f10112i = d.d(5);
        this.f10113j = d.d(8);
        this.f10114k = -1;
        this.f10115l = 0;
        this.f10116m = 1;
        this.r = 0;
        b();
    }
}
