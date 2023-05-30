package com.jingdong.app.mall.home.deploy.view.layout.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.category.floor.feedssub.a;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class SkuLabel extends RelativeLayout {
    private static final float[] p = {0.0f, 0.4f, 1.0f};
    private static final ConcurrentHashMap<String, BitmapInfo> q = new ConcurrentHashMap<>();

    /* renamed from: g */
    private int f9062g;

    /* renamed from: h */
    private final Paint f9063h;

    /* renamed from: i */
    private final Path f9064i;

    /* renamed from: j */
    private final Context f9065j;

    /* renamed from: k */
    private Info f9066k;

    /* renamed from: l */
    private NinePatch f9067l;

    /* renamed from: m */
    private GradientTextView f9068m;

    /* renamed from: n */
    private HomeDraweeView f9069n;
    private int o;

    /* loaded from: classes4.dex */
    public static class BitmapInfo {
        public Bitmap a;

        public boolean a() {
            Bitmap bitmap = this.a;
            return (bitmap == null || bitmap.isRecycled()) ? false : true;
        }
    }

    /* loaded from: classes4.dex */
    public interface IBitmapListener {
        void a(BitmapInfo bitmapInfo);

        void onFail();
    }

    /* loaded from: classes4.dex */
    public static class Info {
        String a;
        int[] b;

        /* renamed from: c */
        boolean f9070c;

        /* renamed from: f */
        CharSequence f9072f;

        /* renamed from: g */
        int[] f9073g;

        /* renamed from: h */
        boolean f9074h;

        /* renamed from: i */
        int f9075i;

        /* renamed from: l */
        int f9078l;

        /* renamed from: m */
        f f9079m;

        /* renamed from: n */
        ShapeEnum f9080n;
        int o;
        boolean p;
        boolean q;
        boolean r;
        com.jingdong.app.mall.home.r.e.f s;
        int t;
        boolean d = true;

        /* renamed from: e */
        String f9071e = "";

        /* renamed from: j */
        int f9076j = 16;

        /* renamed from: k */
        int f9077k = 7;

        private Info() {
            f fVar = new f(-2, -1);
            this.f9079m = fVar;
            this.f9080n = ShapeEnum.STYLE_ROUND;
            this.o = 13;
            this.q = false;
            this.t = 30;
            fVar.J(10, 0, 10, 0);
        }

        public static Info a() {
            return new Info();
        }

        public static Info b(int i2) {
            Info info = new Info();
            info.o = i2;
            return info;
        }

        public boolean c() {
            return !((TextUtils.isEmpty(this.f9071e) && TextUtils.isEmpty(this.f9072f)) || (TextUtils.isEmpty(this.a) && !this.f9070c && this.d)) || (this.q && !TextUtils.isEmpty(this.a));
        }

        public Info d(String str) {
            e(null, str);
            return this;
        }

        public Info e(int[] iArr, String str) {
            this.b = iArr;
            this.a = str;
            this.f9070c = iArr != null;
            return this;
        }

        public Info f(boolean z) {
            this.f9074h = z;
            return this;
        }

        public Info g(com.jingdong.app.mall.home.r.e.f fVar) {
            this.s = fVar;
            this.r = TextUtils.equals("1", fVar.getJsonString("isUseDollar", "1"));
            return this;
        }

        public Info h(int i2) {
            this.f9076j = i2;
            return this;
        }

        public Info i(boolean z) {
            this.q = z;
            return this;
        }

        public Info j(int i2) {
            this.t = i2;
            return this;
        }

        public Info k(f fVar, int i2) {
            int d = d.d(i2);
            this.f9078l = d;
            if (fVar != null) {
                this.f9078l = Math.max(d, fVar.v());
            }
            return this;
        }

        public Info l(Rect rect, int i2) {
            if (rect == null) {
                this.f9079m.J(i2, -10, i2, -10);
            } else {
                rect.top = -10;
                rect.bottom = -10;
                this.f9079m.K(rect);
            }
            return this;
        }

        public Info m(CharSequence charSequence) {
            this.f9072f = charSequence;
            return this;
        }

        public Info n(String str) {
            if (this.r && c.c(str)) {
                this.f9075i = 26;
                m(a.e(str));
                return this;
            }
            if (str == null) {
                str = "";
            }
            this.f9071e = str;
            return this;
        }

        public Info o(String str, Rect rect) {
            p(str, rect, 5.0f);
            return this;
        }

        public Info p(String str, Rect rect, float f2) {
            int i2 = com.jingdong.app.mall.home.o.a.f.Y(str) >= f2 ? 8 : 16;
            n(str);
            l(rect, i2);
            return this;
        }

        public Info q(int[] iArr, int i2) {
            this.f9073g = iArr;
            this.f9075i = i2;
            return this;
        }

        public Info r(int i2, int i3) {
            this.f9079m.Q(i2);
            this.f9079m.C(i3);
            return this;
        }

        public Info s(Rect rect) {
            if (rect != null) {
                this.f9079m.E(rect.left, 0, rect.right, 0);
            } else {
                this.f9079m.F(null);
            }
            return this;
        }

        public Info t(int i2) {
            this.f9077k = i2;
            return this;
        }

        public Info u(boolean z) {
            this.d = z;
            return this;
        }
    }

    public SkuLabel(Context context) {
        super(context);
        this.f9062g = 30;
        this.f9063h = new Paint(1);
        this.f9064i = new Path();
        this.f9065j = context;
    }

    private void d() {
        HomeDraweeView homeDraweeView = this.f9069n;
        if (homeDraweeView != null) {
            homeDraweeView.setVisibility(8);
        }
        this.f9062g = this.f9066k.t;
        g();
        n(this.f9066k.a);
    }

    private void e() {
        HomeDraweeView homeDraweeView;
        GradientTextView gradientTextView = this.f9068m;
        if (gradientTextView != null) {
            gradientTextView.setVisibility(8);
        }
        HomeDraweeView homeDraweeView2 = this.f9069n;
        if (homeDraweeView2 == null) {
            HomeDraweeView homeDraweeView3 = new HomeDraweeView(getContext());
            this.f9069n = homeDraweeView3;
            homeDraweeView3.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(this.f9069n, new f(-1, -1).u(this.f9069n));
        } else {
            homeDraweeView2.setVisibility(0);
        }
        if (com.jingdong.app.mall.home.o.a.f.m0(this.f9066k.a) && (homeDraweeView = this.f9069n) != null) {
            homeDraweeView.setTag(e.d, null);
        }
        e.u(this.f9069n, this.f9066k.a);
        o();
    }

    private void g() {
        f fVar = this.f9066k.f9079m;
        if (fVar == null) {
            return;
        }
        GradientTextView gradientTextView = this.f9068m;
        if (gradientTextView == null) {
            g gVar = new g(this.f9065j, true);
            gVar.f(1);
            gVar.d(16);
            GradientTextView b = gVar.b();
            this.f9068m = b;
            RelativeLayout.LayoutParams u = fVar.u(b);
            u.addRule(this.f9066k.o);
            addView(this.f9068m, u);
        } else {
            gradientTextView.setVisibility(0);
            f.d(this.f9068m, fVar, true);
        }
        this.f9068m.setTypeface(FontsUtil.getTypeFace(getContext()));
        this.f9068m.setGravity(this.f9066k.f9076j);
        this.f9068m.setTextGradient(GradientTextView.GradientType.LeftToRight, this.f9066k.f9073g);
        g.o(this.f9068m, this.f9066k.f9075i);
        g.k(this.f9068m, this.f9066k.f9074h);
        Info info = this.f9066k;
        CharSequence charSequence = info.f9072f;
        if (charSequence != null) {
            this.f9068m.setText(charSequence);
        } else if (info.f9078l > 0 && info.f9071e.length() >= 6) {
            int q2 = this.f9066k.f9079m.q() + this.f9066k.f9079m.r();
            this.f9068m.setText(com.jingdong.app.mall.home.o.a.f.l(this.f9068m, (r1.f9078l - q2) - 10, this.f9066k.f9071e));
        } else {
            GradientTextView gradientTextView2 = this.f9068m;
            Info info2 = this.f9066k;
            gradientTextView2.setText(com.jingdong.app.mall.home.o.a.f.j(info2.f9077k, info2.f9071e));
        }
    }

    public static void h(String str, final IBitmapListener iBitmapListener) {
        if (TextUtils.isEmpty(str)) {
            m(null, iBitmapListener);
            return;
        }
        ConcurrentHashMap<String, BitmapInfo> concurrentHashMap = q;
        final BitmapInfo bitmapInfo = concurrentHashMap.get(str);
        if (bitmapInfo == null) {
            bitmapInfo = new BitmapInfo();
            concurrentHashMap.put(str, bitmapInfo);
        }
        if (bitmapInfo.a()) {
            iBitmapListener.a(bitmapInfo);
        } else {
            com.jingdong.app.mall.home.floor.ctrl.f.i(str, new com.jingdong.app.mall.home.t.a() { // from class: com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel.2
                @Override // com.jingdong.app.mall.home.t.a
                public void onBitmapWithUiNull(Bitmap bitmap) {
                    if (bitmap == null) {
                        SkuLabel.m(bitmapInfo, iBitmapListener);
                        return;
                    }
                    BitmapInfo bitmapInfo2 = bitmapInfo;
                    bitmapInfo2.a = bitmap;
                    iBitmapListener.a(bitmapInfo2);
                }
            });
        }
    }

    private void i(Canvas canvas) {
        Info info = this.f9066k;
        if (info == null || info.q || !info.d) {
            return;
        }
        int width = getWidth();
        int height = getHeight();
        NinePatch ninePatch = this.f9067l;
        if (ninePatch != null) {
            ninePatch.draw(canvas, new Rect(0, 0, width, height), this.f9063h);
        } else if (this.f9066k.f9070c) {
            j(canvas, width, height);
        }
    }

    private void j(Canvas canvas, int i2, int i3) {
        if (!this.f9066k.p || this.f9064i.isEmpty() || this.o != i2) {
            this.o = i2;
            l(this.f9064i, i2, i3, this.f9066k.f9080n);
            k(i2);
            this.f9066k.p = true;
        }
        canvas.drawPath(this.f9064i, this.f9063h);
    }

    private void k(int i2) {
        int[] iArr;
        Info info = this.f9066k;
        if (info == null || (iArr = info.b) == null) {
            return;
        }
        this.f9063h.setStyle(Paint.Style.FILL);
        if (iArr.length == 1) {
            this.f9063h.setColor(iArr[0]);
            this.f9063h.setShader(null);
        } else if (iArr.length > 1) {
            float f2 = i2;
            int length = iArr.length;
            float[] fArr = p;
            if (length != fArr.length) {
                fArr = null;
            }
            this.f9063h.setShader(new LinearGradient(0.0f, 0.0f, f2, 0.0f, iArr, fArr, Shader.TileMode.CLAMP));
        }
    }

    private void l(Path path, int i2, int i3, ShapeEnum shapeEnum) {
        float d = d.d(4);
        float f2 = d * 0.5522848f;
        float f3 = i3;
        float f4 = f3 / 2.0f;
        float f5 = 0.5522848f * f4;
        path.reset();
        if (shapeEnum == ShapeEnum.STYLE_SQUARE) {
            path.moveTo(f4, 0.0f);
            float f6 = i2;
            path.lineTo(f6 - d, 0.0f);
            com.jingdong.app.mall.home.n.h.g.f(0.0f, f6, d, f2, path);
            path.lineTo(f6, f4 + 0.0f);
            com.jingdong.app.mall.home.n.h.g.d(f6, f3, f4, f5, path);
            path.lineTo(f4, f3);
            com.jingdong.app.mall.home.n.h.g.a(f3, 0.0f, f4, f5, path);
            com.jingdong.app.mall.home.n.h.g.c(0.0f, 0.0f, f4, f5, path);
        } else if (shapeEnum == ShapeEnum.STYLE_ROUND) {
            path.moveTo(f4, 0.0f);
            float f7 = i2;
            path.lineTo(f7 - f4, 0.0f);
            com.jingdong.app.mall.home.n.h.g.f(0.0f, f7, f4, f5, path);
            com.jingdong.app.mall.home.n.h.g.d(f7, f3, f4, f5, path);
            path.lineTo(f4, f3);
            com.jingdong.app.mall.home.n.h.g.a(f3, 0.0f, f4, f5, path);
            com.jingdong.app.mall.home.n.h.g.c(0.0f, 0.0f, f4, f5, path);
        }
        path.close();
    }

    public static void m(BitmapInfo bitmapInfo, IBitmapListener iBitmapListener) {
        if (bitmapInfo != null) {
            bitmapInfo.a = null;
        }
        if (iBitmapListener != null) {
            iBitmapListener.onFail();
        }
    }

    public void o() {
        this.f9067l = null;
        postInvalidate();
    }

    public void p(Bitmap bitmap) {
        Bitmap x = e.x(bitmap, d.d(this.f9062g));
        byte[] y = e.y(x, 0.5f);
        if (y != null) {
            this.f9067l = new NinePatch(x, y, null);
        } else {
            this.f9067l = null;
        }
        postInvalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        i(canvas);
        super.dispatchDraw(canvas);
    }

    public void f(Info info) {
        if (info == null) {
            setVisibility(8);
            return;
        }
        this.f9066k = info;
        if (!info.c()) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        if (this.f9066k.q) {
            e();
        } else {
            d();
        }
    }

    public void n(String str) {
        h(str, new IBitmapListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel.1
            {
                SkuLabel.this = this;
            }

            @Override // com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel.IBitmapListener
            public void a(BitmapInfo bitmapInfo) {
                SkuLabel.this.p(bitmapInfo.a);
            }

            @Override // com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel.IBitmapListener
            public void onFail() {
                SkuLabel.this.o();
            }
        });
    }
}
