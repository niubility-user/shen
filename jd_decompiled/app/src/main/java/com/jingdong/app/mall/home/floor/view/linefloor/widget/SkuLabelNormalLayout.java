package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.widget.PriceLabelView;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class SkuLabelNormalLayout extends RelativeLayout {
    private static ConcurrentHashMap<String, Boolean> p = new ConcurrentHashMap<>();

    /* renamed from: g  reason: collision with root package name */
    private PriceLabelView f9943g;

    /* renamed from: h  reason: collision with root package name */
    private f f9944h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f9945i;

    /* renamed from: j  reason: collision with root package name */
    private f f9946j;

    /* renamed from: k  reason: collision with root package name */
    private c f9947k;

    /* renamed from: l  reason: collision with root package name */
    private f f9948l;

    /* renamed from: m  reason: collision with root package name */
    private String f9949m;

    /* renamed from: n  reason: collision with root package name */
    private ValueAnimator f9950n;
    private GradientDrawable o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (com.jingdong.app.mall.home.a.f8559h) {
                return;
            }
            SkuLabelNormalLayout.this.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f9952g;

        b(int i2) {
            this.f9952g = i2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            int i2 = this.f9952g;
            SkuLabelNormalLayout.this.i(floatValue, (i2 - floatValue) / i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class c extends GradientTextView {

        /* renamed from: g  reason: collision with root package name */
        private boolean f9954g;

        /* renamed from: h  reason: collision with root package name */
        private int[] f9955h;

        /* renamed from: i  reason: collision with root package name */
        private GradientDrawable f9956i;

        /* renamed from: j  reason: collision with root package name */
        private NinePatch f9957j;

        /* renamed from: k  reason: collision with root package name */
        private int f9958k;

        /* renamed from: l  reason: collision with root package name */
        private int f9959l;

        /* renamed from: m  reason: collision with root package name */
        private String f9960m;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public class a extends com.jingdong.app.mall.home.t.a {
            final /* synthetic */ String a;

            a(String str) {
                this.a = str;
            }

            @Override // com.jingdong.app.mall.home.t.a
            public void onBitmapWithUiNull(Bitmap bitmap) {
                if (bitmap == null) {
                    c.this.g();
                } else {
                    c.this.f(bitmap, this.a);
                }
            }
        }

        public c(Context context) {
            super(context);
            this.f9955h = new int[2];
        }

        private void c(Canvas canvas) {
            NinePatch ninePatch = this.f9957j;
            if (ninePatch != null) {
                ninePatch.draw(canvas, new Rect(0, 0, getWidth(), getHeight()));
            } else {
                com.jingdong.app.mall.home.n.h.c.k(true, this);
            }
        }

        private boolean d(String str) {
            return TextUtils.equals(str, this.f9960m) && this.f9958k == d.d(this.f9959l);
        }

        private void e(String str) {
            if (d(str)) {
                return;
            }
            String b = com.jingdong.app.mall.home.m.a.b(str);
            if (!TextUtils.isEmpty(b) && !b.toLowerCase().endsWith(".gif")) {
                com.jingdong.app.mall.home.floor.ctrl.f.i(b, new a(b));
            } else {
                g();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f(Bitmap bitmap, String str) {
            int d = d.d(this.f9959l);
            Bitmap x = e.x(bitmap, d);
            byte[] y = e.y(x, 0.5f);
            if (y != null) {
                com.jingdong.app.mall.home.n.h.c.k(false, this);
                this.f9957j = new NinePatch(x, y, null);
                this.f9958k = d;
                this.f9960m = str;
            } else {
                this.f9958k = 0;
                this.f9957j = null;
                this.f9960m = null;
            }
            postInvalidate();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g() {
            this.f9957j = null;
            this.f9960m = null;
            this.f9958k = 0;
            postInvalidate();
        }

        public void h(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2, int i3) {
            this.f9959l = i3;
            this.f9954g = bVar.E();
            String Y = bVar.Y(i2);
            int[] X = bVar.X(i2);
            if (!this.f9954g) {
                if (TextUtils.isEmpty(Y)) {
                    setPadding(d.d(16), 0, d.d(16), d.d(5));
                    setBackgroundResource(R.drawable.home_prmotion_label_bg);
                } else {
                    int[] f2 = com.jingdong.app.mall.home.floor.view.b.h.a.f(X, 160, 255);
                    if (this.f9956i == null) {
                        this.f9956i = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, f2);
                    }
                    if (Build.VERSION.SDK_INT >= 16) {
                        this.f9956i.setColors(f2);
                    } else {
                        this.f9956i.setColor(f2[0]);
                    }
                    this.f9956i.setShape(0);
                    this.f9956i.setCornerRadius(d.d(14));
                    setBackgroundDrawable(this.f9956i);
                    setPadding(d.d(16), -3, d.d(16), -3);
                }
            } else {
                int[] iArr = this.f9955h;
                iArr[0] = X[0];
                iArr[1] = X.length > 1 ? X[1] : X[0];
                setPadding(d.d(13), -d.d(1), d.d(this.f9954g ? 13 : 16), 0);
                setBackgroundDrawable(null);
            }
            if (this.f9954g) {
                e(bVar.Z(i2));
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.app.mall.home.floor.view.view.GradientTextView, android.widget.TextView, android.view.View
        public void onDraw(Canvas canvas) {
            if (this.f9954g) {
                c(canvas);
            }
            super.onDraw(canvas);
        }
    }

    public SkuLabelNormalLayout(Context context) {
        super(context);
        setGravity(1);
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.o = gradientDrawable;
        gradientDrawable.setShape(0);
        TextView textView = new TextView(context);
        this.f9945i = textView;
        textView.setId(R.id.mallfloor_item1);
        this.f9945i.setMaxLines(1);
        this.f9945i.setGravity(16);
        this.f9945i.getPaint().setFakeBoldText(true);
        this.f9945i.setBackgroundDrawable(this.o);
        f fVar = new f(-2, 30);
        this.f9946j = fVar;
        fVar.J(8, 0, 20, 0);
        View view = this.f9945i;
        addView(view, this.f9946j.u(view));
        PriceLabelView priceLabelView = new PriceLabelView(context);
        this.f9943g = priceLabelView;
        priceLabelView.setMaxLines(1);
        this.f9943g.setId(R.id.mallfloor_item2);
        this.f9943g.c(24, 20, 2, 8);
        f fVar2 = new f(-2, 30);
        this.f9944h = fVar2;
        fVar2.E(0, 4, 0, 0);
        RelativeLayout.LayoutParams u = this.f9944h.u(this.f9943g);
        u.addRule(1, this.f9945i.getId());
        addView(this.f9943g, u);
        c cVar = new c(context);
        this.f9947k = cVar;
        cVar.setMaxLines(1);
        this.f9947k.setGravity(17);
        f fVar3 = new f(-2, -1);
        this.f9948l = fVar3;
        addView(this.f9947k, fVar3.u(this.f9947k));
    }

    public static void c(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2, String str) {
        if (bVar == null) {
            return;
        }
        if (i2 == 1 && bVar.q() == 2) {
            String i3 = bVar.i(i2, "tabstyle");
            if (TextUtils.isEmpty(i3)) {
                i3 = "0";
            }
            int indexOf = i3.indexOf(CartConstant.KEY_YB_INFO_LINK);
            if (indexOf >= 0) {
                str = i3.substring(0, indexOf + 1) + str;
            } else {
                str = i3 + CartConstant.KEY_YB_INFO_LINK + str;
            }
        }
        bVar.b(i2, "tabstyle", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (!TextUtils.isEmpty(this.f9949m) && p.get(this.f9949m) == null && m.I(this.f9947k, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, true)) {
            p.put(this.f9949m, Boolean.TRUE);
            if (this.f9950n == null) {
                int height = getHeight();
                ValueAnimator ofFloat = ValueAnimator.ofFloat(height, 0.0f);
                this.f9950n = ofFloat;
                ofFloat.addUpdateListener(new b(height));
                this.f9950n.setInterpolator(new AccelerateDecelerateInterpolator());
                this.f9950n.setDuration(500L);
            }
            this.f9950n.start();
        }
    }

    private void g(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2, String str) {
        e(false, this.f9943g);
        g.o(this.f9943g, 24);
        SpannableString d = com.jingdong.app.mall.home.category.floor.feedssub.a.d(com.jingdong.app.mall.home.o.a.f.l(this.f9943g, d.d(100), str), OrderISVUtil.MONEY_DECIMAL, 1.0f);
        int[] X = bVar.X(i2);
        int Q = bVar.Q(i2);
        int[] f2 = com.jingdong.app.mall.home.floor.view.b.h.a.f(X, 255, 255);
        this.f9943g.d(f2, d, "5".equals(bVar.U(i2)) ? 255 : 160);
        String P = bVar.P(i2);
        if (TextUtils.isEmpty(P)) {
            e(true, this.f9945i);
            this.f9944h.E(0, 4, 0, 0);
        } else {
            e(false, this.f9945i);
            this.f9945i.setText(P);
            this.f9945i.setTextColor(f2[f2.length - 1]);
            this.f9945i.setMaxWidth(d.d(70));
            this.f9945i.setTextSize(0, d.d(20));
            this.o.setCornerRadius(d.d(8));
            this.o.setColor(Q);
            f.c(this.f9945i, this.f9946j);
            this.f9946j.u(this.f9945i).addRule(8, this.f9943g.getId());
            this.f9944h.E(-20, 4, 0, 0);
        }
        f.d(this.f9943g, this.f9944h, true);
    }

    private void h(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2, String str, int i3) {
        boolean E = bVar.E();
        this.f9947k.h(bVar, i2, i3);
        com.jingdong.app.mall.home.r.e.f g2 = bVar.g(i2);
        this.f9949m = g2.s() + i2;
        boolean z = (!bVar.v() && TextUtils.equals("1", g2.getJsonString("skuLabelAnimation"))) && p.get(this.f9949m) == null;
        ValueAnimator valueAnimator = this.f9950n;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.f9947k.setTextGradient(GradientTextView.GradientType.LeftToRight, bVar.a0(i2));
        i(0.0f, z ? 0.0f : 1.0f);
        if (z) {
            com.jingdong.app.mall.home.o.a.f.F0(new a(), 200L);
            com.jingdong.app.mall.home.o.a.f.G0(this);
        } else {
            j();
        }
        String j2 = com.jingdong.app.mall.home.o.a.f.j(6, str);
        String j3 = com.jingdong.app.mall.home.o.a.f.j(6, j2);
        c(bVar, i2, TextUtils.equals(j3, j2) ? "1" : "2");
        g.o(this.f9947k, E ? 20 : j3.length() < 6 ? 22 : 21);
        e(false, this.f9947k);
        f.c(this.f9947k, this.f9948l);
        this.f9947k.setText(j3);
        this.f9947k.getPaint().setFakeBoldText(E);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(float f2, float f3) {
        c cVar = this.f9947k;
        if (cVar != null) {
            cVar.setTranslationY(f2);
            this.f9947k.setAlpha(f3);
        }
    }

    public void d(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2, int i3) {
        String T = bVar.T(i2);
        e(true, this.f9943g);
        e(true, this.f9945i);
        e(true, this.f9947k);
        if (TextUtils.isEmpty(T)) {
            return;
        }
        String U = bVar.U(i2);
        this.f9949m = null;
        boolean equals = "1".equals(U);
        if (!"5".equals(U) && (bVar.E() || !equals)) {
            if (equals) {
                T = com.jingdong.app.mall.home.category.floor.feedssub.a.d(T, OrderISVUtil.MONEY_DECIMAL, 1.0f).toString();
            }
            h(bVar, i2, T, i3);
        } else {
            g(bVar, i2, T);
        }
        postInvalidate();
    }

    protected void e(boolean z, View... viewArr) {
        com.jingdong.app.mall.home.n.h.c.k(z, viewArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        com.jingdong.app.mall.home.o.a.f.H0(this);
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (!TextUtils.isEmpty(this.f9949m) && p.get(this.f9949m) == null) {
            if ("home_scroll_stop".equals(baseEvent.getType())) {
                f();
                return;
            }
            return;
        }
        j();
    }
}
