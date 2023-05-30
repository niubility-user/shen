package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.x.e;
import com.jingdong.app.mall.home.x.g;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes4.dex */
public class CountDownLayout extends View {

    /* renamed from: g  reason: collision with root package name */
    private com.jingdong.app.mall.home.x.b f10007g;

    /* renamed from: h  reason: collision with root package name */
    private com.jingdong.app.mall.home.x.c f10008h;

    /* renamed from: i  reason: collision with root package name */
    private b f10009i;

    /* renamed from: j  reason: collision with root package name */
    private String f10010j;

    /* renamed from: k  reason: collision with root package name */
    private String f10011k;

    /* renamed from: l  reason: collision with root package name */
    private String f10012l;

    /* renamed from: m  reason: collision with root package name */
    private String f10013m;

    /* renamed from: n  reason: collision with root package name */
    private String f10014n;
    private String o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.x.c {
        a() {
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void a() {
            CountDownLayout.this.d("00", "00", "00");
            CountDownLayout.this.e();
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void c(long j2, e eVar) {
            if (eVar == null) {
                CountDownLayout.this.d("00", "00", "00");
                return;
            }
            String a = eVar.a();
            String b = eVar.b();
            String c2 = eVar.c();
            CountDownLayout countDownLayout = CountDownLayout.this;
            if (a.length() == 1) {
                a = "0" + a;
            }
            if (b.length() == 1) {
                b = "0" + b;
            }
            if (c2.length() == 1) {
                c2 = "0" + c2;
            }
            countDownLayout.d(a, b, c2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class b {
        float A;
        float B;
        RectF C;
        RectF D;
        RectF E;
        float F;
        private int G;
        private final Rect H;
        float I;
        float J;
        boolean a;
        int b;

        /* renamed from: c  reason: collision with root package name */
        int f10015c;
        int d;

        /* renamed from: e  reason: collision with root package name */
        int f10016e;

        /* renamed from: f  reason: collision with root package name */
        int f10017f;

        /* renamed from: g  reason: collision with root package name */
        float f10018g;

        /* renamed from: h  reason: collision with root package name */
        int f10019h;

        /* renamed from: i  reason: collision with root package name */
        float f10020i;

        /* renamed from: j  reason: collision with root package name */
        float f10021j;

        /* renamed from: k  reason: collision with root package name */
        int f10022k;

        /* renamed from: l  reason: collision with root package name */
        int f10023l;

        /* renamed from: m  reason: collision with root package name */
        final Paint f10024m;

        /* renamed from: n  reason: collision with root package name */
        final Paint f10025n;
        final Paint o;
        int p;
        int q;
        float r;
        float s;
        float t;
        float u;
        float v;
        float w;
        float x;
        float y;
        float z;

        public b(String str, Context context) {
            Paint paint = new Paint(1);
            this.f10024m = paint;
            Paint paint2 = new Paint(1);
            this.f10025n = paint2;
            Paint paint3 = new Paint(1);
            this.o = paint3;
            this.H = new Rect();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint2.setStyle(Paint.Style.FILL);
            paint3.setAntiAlias(true);
            paint3.setTypeface(FontsUtil.getTypeFace(context, 4099));
            paint3.setStyle(Paint.Style.FILL);
            paint3.setTextAlign(Paint.Align.CENTER);
            String[] split = TextUtils.split(str, CartConstant.KEY_YB_INFO_LINK);
            boolean z = split.length >= 11;
            this.a = z;
            if (z) {
                try {
                    e(split);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    this.a = false;
                }
            }
        }

        private void e(String[] strArr) {
            this.b = com.jingdong.app.mall.home.n.h.c.g(strArr[0]);
            this.f10015c = com.jingdong.app.mall.home.n.h.c.g(strArr[1]);
            this.d = com.jingdong.app.mall.home.n.h.c.g(strArr[2]);
            this.f10016e = com.jingdong.app.mall.home.n.h.c.g(strArr[3]);
            this.f10017f = com.jingdong.app.mall.home.n.h.c.g(strArr[4]);
            this.f10018g = com.jingdong.app.mall.home.n.h.c.e(strArr[5], 0.0f);
            this.f10019h = com.jingdong.app.mall.home.floor.view.b.h.a.d(strArr[6], -1);
            this.f10020i = com.jingdong.app.mall.home.n.h.c.e(strArr[7], 0.0f);
            this.f10021j = com.jingdong.app.mall.home.n.h.c.e(strArr[8], 0.0f);
            this.f10022k = com.jingdong.app.mall.home.floor.view.b.h.a.d(strArr[9], -1);
            this.f10023l = com.jingdong.app.mall.home.n.h.c.g(strArr[10]);
        }

        public void a(Canvas canvas) {
            float f2 = this.v;
            canvas.drawRoundRect(this.C, f2, f2, this.f10024m);
            canvas.drawRoundRect(this.D, f2, f2, this.f10024m);
            canvas.drawRoundRect(this.E, f2, f2, this.f10024m);
        }

        public void b(Canvas canvas) {
            canvas.drawCircle(this.r, this.t, this.F, this.f10025n);
            canvas.drawCircle(this.r, this.u, this.F, this.f10025n);
            canvas.drawCircle(this.s, this.t, this.F, this.f10025n);
            canvas.drawCircle(this.s, this.u, this.F, this.f10025n);
        }

        public void c(Canvas canvas, String str, String str2, String str3, String str4, String str5, String str6) {
            Paint.FontMetrics fontMetrics = this.o.getFontMetrics();
            float f2 = this.I / 2.0f;
            float f3 = this.w + this.J + f2;
            float f4 = (int) ((this.p / 2) - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f));
            canvas.drawText(str, 0, 1, f3, f4, this.o);
            canvas.drawText(str2, 0, 1, (this.z - this.J) - f2, f4, this.o);
            canvas.drawText(str3, 0, 1, this.x + this.J + f2, f4, this.o);
            canvas.drawText(str4, 0, 1, (this.A - this.J) - f2, f4, this.o);
            canvas.drawText(str5, 0, 1, this.y + this.J + f2, f4, this.o);
            canvas.drawText(str6, 0, 1, (this.B - this.J) - f2, f4, this.o);
        }

        float d(float f2) {
            return ((d.f9279g * f2) / 750.0f) + 0.5f;
        }

        public void f() {
            if (this.G == d.f9279g) {
                return;
            }
            this.G = d.f9279g;
            this.f10024m.setColor(this.f10019h);
            this.f10025n.setColor(this.f10019h);
            this.o.setColor(this.f10022k);
            this.o.setTextSize(d(this.f10023l));
            this.I = 0.0f;
            this.o.getTextBounds("4", 0, 1, this.H);
            this.I = this.H.width();
            this.J = (d(this.f10017f) - (this.I * 2.0f)) / 2.2f;
            this.v = d(this.f10018g);
            this.F = d(this.f10020i) / 2.0f;
            this.p = (int) d(this.d);
            this.q = (int) d(this.f10016e);
            this.w = 0.0f;
            this.z = d(this.f10017f);
            float d = (this.q - d(this.f10017f)) / 2.0f;
            this.x = d;
            this.A = d + d(this.f10017f);
            this.y = this.q - d(this.f10017f);
            this.B = this.q;
            this.C = new RectF(this.w, 0.0f, this.z, this.p);
            this.D = new RectF(this.x, 0.0f, this.A, this.p);
            this.E = new RectF(this.y, 0.0f, this.B, this.p);
            this.r = (this.z + this.x) / 2.0f;
            this.s = (this.A + this.y) / 2.0f;
            float d2 = (this.p - d(this.f10021j)) / 2.0f;
            this.t = d2;
            this.u = d2 + d(this.f10021j);
        }
    }

    public CountDownLayout(Context context) {
        super(context);
        this.f10010j = "0";
        this.f10011k = "0";
        this.f10012l = "0";
        this.f10013m = "0";
        this.f10014n = "0";
        this.o = "0";
    }

    private void b(Canvas canvas) {
        this.f10009i.a(canvas);
        this.f10009i.b(canvas);
        this.f10009i.c(canvas, this.f10010j, this.f10011k, this.f10012l, this.f10013m, this.f10014n, this.o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, String str2, String str3) {
        this.f10010j = str.substring(0, 1);
        this.f10011k = str.substring(1, 2);
        this.f10012l = str2.substring(0, 1);
        this.f10013m = str2.substring(1, 2);
        this.f10014n = str3.substring(0, 1);
        this.o = str3.substring(1, 2);
        postInvalidate();
    }

    private void f(ViewGroup.MarginLayoutParams marginLayoutParams) {
        b bVar = this.f10009i;
        if (bVar == null) {
            return;
        }
        marginLayoutParams.width = bVar.q;
        marginLayoutParams.height = bVar.p;
        marginLayoutParams.topMargin = (int) bVar.d(bVar.f10015c);
        marginLayoutParams.leftMargin = (int) this.f10009i.d(r0.b);
    }

    protected boolean c() {
        b bVar = this.f10009i;
        return bVar != null && bVar.a;
    }

    protected void e() {
    }

    public void g(String str) {
        b bVar = new b(str, getContext());
        this.f10009i = bVar;
        bVar.f();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            setLayoutParams(layoutParams);
        }
    }

    public void h(long j2, long j3) {
        if (!c()) {
            setVisibility(8);
            e();
            return;
        }
        setVisibility(0);
        com.jingdong.app.mall.home.x.b bVar = this.f10007g;
        if (bVar != null) {
            bVar.g(this.f10008h);
        }
        long elapsedRealtime = (j2 * 1000) - (SystemClock.elapsedRealtime() - s.f9357c);
        if (elapsedRealtime <= 0) {
            d("00", "00", "00");
            e();
            return;
        }
        this.f10007g = g.b().f(j3, elapsedRealtime);
        if (this.f10008h == null) {
            this.f10008h = new a();
        }
        com.jingdong.app.mall.home.x.b bVar2 = this.f10007g;
        if (bVar2 != null) {
            bVar2.a(this.f10008h);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (c()) {
            b(canvas);
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            f((ViewGroup.MarginLayoutParams) layoutParams);
        }
        super.setLayoutParams(layoutParams);
    }
}
