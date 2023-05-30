package com.jingdong.app.mall.home.deploy.view.layout.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel;
import com.jingdong.app.mall.home.deploy.view.layout.corelive2x2.LiveSkuLottie;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;

/* loaded from: classes4.dex */
public class SkuLayout extends RelativeLayout {

    /* renamed from: g */
    private Context f9081g;

    /* renamed from: h */
    private Info f9082h;

    /* renamed from: i */
    private CoreModel f9083i;

    /* renamed from: j */
    private DarkWhiteBgImageView f9084j;

    /* renamed from: k */
    private SimpleDraweeView f9085k;

    /* renamed from: l */
    private LiveSkuLottie f9086l;

    /* renamed from: m */
    private SkuLabel f9087m;

    /* renamed from: n */
    private SkuLabel f9088n;
    private SkuLabel o;
    private SkuLabel p;
    private CoreVideoSkuView q;
    private int r;
    private int s;

    /* loaded from: classes4.dex */
    public static class Info {
        int a;
        String b;

        /* renamed from: c */
        String f9089c;
        boolean d;

        /* renamed from: h */
        SkuLabel.Info f9093h;

        /* renamed from: i */
        SkuLabel.Info f9094i;

        /* renamed from: j */
        f f9095j;

        /* renamed from: k */
        f f9096k;

        /* renamed from: e */
        f f9090e = new f(-1, -1);

        /* renamed from: f */
        f f9091f = new f(-1, -1);

        /* renamed from: g */
        f f9092g = new f(-1, -1);

        /* renamed from: l */
        ImageView.ScaleType f9097l = ImageView.ScaleType.FIT_XY;

        private Info() {
        }

        public static Info a() {
            return new Info();
        }

        public boolean b() {
            SkuLabel.Info info = this.f9094i;
            return info != null && info.c();
        }

        public boolean c() {
            SkuLabel.Info info = this.f9093h;
            return info != null && info.c();
        }

        public Info d(SkuLabel.Info info, int i2, int i3, int i4) {
            this.f9094i = info;
            if (info != null) {
                this.f9094i.k(this.f9090e, Math.max(i3, i2));
            }
            this.f9096k = new f(i2, i4);
            return this;
        }

        public Info e(SkuLabel.Info info, int i2, int i3) {
            f(info, i2, i2, i3);
            return this;
        }

        public Info f(SkuLabel.Info info, int i2, int i3, int i4) {
            this.f9093h = info;
            if (info != null) {
                this.f9093h.k(this.f9090e, Math.max(i3, i2));
            }
            this.f9095j = new f(i2, i4);
            return this;
        }

        public Info g(int i2, int i3, int i4, int i5) {
            f fVar = this.f9095j;
            if (fVar != null) {
                fVar.E(i2, i3, i4, i5);
            }
            return this;
        }

        public Info h(int i2, int i3, boolean z) {
            if (com.jingdong.app.mall.home.o.a.f.M0()) {
                this.f9091f.R(i2, i3);
                this.d = z;
            }
            return this;
        }

        public Info i(String str, String str2, int i2) {
            this.b = str;
            this.f9089c = str2;
            this.a = d.d(i2);
            return this;
        }

        public Info j(int i2, int i3, int i4, int i5) {
            f fVar = this.f9090e;
            if (fVar != null) {
                fVar.E(i2, i3, i4, i5);
            }
            return this;
        }

        public Info k(int i2, int i3) {
            this.f9092g.R(i2, i3);
            return this;
        }

        public Info l(int i2, int i3) {
            this.f9090e.R(i2, i3);
            this.f9090e.E(0, 0, 0, 0);
            this.f9092g.R(i2, i3);
            return this;
        }
    }

    public SkuLayout(Context context) {
        super(context);
        this.s = 15;
        this.f9081g = context;
    }

    private void a() {
        SkuLabel.Info info;
        this.o = null;
        this.p = null;
        f fVar = this.f9082h.f9095j;
        if (fVar == null) {
            SkuLabel skuLabel = this.f9087m;
            if (skuLabel != null) {
                skuLabel.setVisibility(8);
            }
            SkuLabel skuLabel2 = this.f9088n;
            if (skuLabel2 != null) {
                skuLabel2.setVisibility(8);
                return;
            }
            return;
        }
        SkuLabel skuLabel3 = this.f9087m;
        if (skuLabel3 == null) {
            SkuLabel skuLabel4 = new SkuLabel(this.f9081g);
            this.f9087m = skuLabel4;
            RelativeLayout.LayoutParams u = fVar.u(skuLabel4);
            u.addRule(14);
            u.addRule(12);
            addView(this.f9087m, u);
        } else {
            f.c(skuLabel3, fVar);
        }
        this.f9087m.f(this.f9082h.f9093h);
        f fVar2 = this.f9082h.f9096k;
        if (fVar2 == null) {
            SkuLabel skuLabel5 = this.f9088n;
            if (skuLabel5 != null) {
                skuLabel5.setVisibility(8);
                return;
            }
            return;
        }
        SkuLabel skuLabel6 = this.f9088n;
        if (skuLabel6 == null) {
            SkuLabel skuLabel7 = new SkuLabel(this.f9081g);
            this.f9088n = skuLabel7;
            RelativeLayout.LayoutParams u2 = fVar2.u(skuLabel7);
            u2.addRule(14);
            u2.addRule(12);
            this.f9088n.setLayoutParams(u2);
            m.b(this, this.f9088n, indexOfChild(this.f9087m));
        } else {
            f.c(skuLabel6, fVar2);
        }
        this.f9088n.setAlpha(0.0f);
        this.f9088n.f(this.f9082h.f9094i);
        SkuLabel.Info info2 = this.f9082h.f9093h;
        if (info2 == null || !info2.c() || (info = this.f9082h.f9094i) == null || !info.c()) {
            return;
        }
        this.o = this.f9087m;
        this.p = this.f9088n;
    }

    private void d() {
        Info info = this.f9082h;
        f fVar = info.f9090e;
        if (fVar == null) {
            return;
        }
        boolean z = info.d;
        if (this.f9084j == null) {
            DarkWhiteBgImageView darkWhiteBgImageView = new DarkWhiteBgImageView(this.f9081g);
            this.f9084j = darkWhiteBgImageView;
            darkWhiteBgImageView.c(true);
            this.f9084j.setBackgroundColor(-1);
            RelativeLayout.LayoutParams u = fVar.u(this.f9084j);
            u.addRule(14);
            u.addRule(this.s);
            addView(this.f9084j, u);
        }
        this.f9084j.setScaleType(this.f9082h.f9097l);
        f.c(this.f9084j, z ? this.f9082h.f9091f : fVar);
        DarkWhiteBgImageView darkWhiteBgImageView2 = this.f9084j;
        Info info2 = this.f9082h;
        e.d(darkWhiteBgImageView2, z ? info2.f9091f.v() >> 1 : info2.a);
        com.jingdong.app.mall.home.floor.ctrl.e.u(this.f9084j, this.f9082h.b);
        if (this.f9085k == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.f9081g);
            this.f9085k = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u2 = this.f9082h.f9092g.u(this.f9085k);
            u2.addRule(14);
            u2.addRule(this.s);
            addView(this.f9085k, u2);
        }
        if ((z || TextUtils.isEmpty(this.f9082h.f9089c)) ? false : true) {
            this.f9085k.setAlpha(1.0f);
            f.c(this.f9085k, this.f9082h.f9092g);
            e.d(this.f9085k, this.f9082h.a - 1);
            com.jingdong.app.mall.home.floor.ctrl.e.u(this.f9085k, this.f9082h.f9089c);
        } else {
            SimpleDraweeView simpleDraweeView = this.f9085k;
            if (simpleDraweeView != null) {
                simpleDraweeView.setAlpha(0.0f);
            }
        }
        if (z) {
            LiveSkuLottie liveSkuLottie = this.f9086l;
            if (liveSkuLottie == null) {
                LiveSkuLottie liveSkuLottie2 = new LiveSkuLottie(this.f9081g);
                this.f9086l = liveSkuLottie2;
                RelativeLayout.LayoutParams u3 = fVar.u(liveSkuLottie2);
                u3.addRule(13);
                this.f9086l.setLayoutParams(u3);
                m.b(this, this.f9086l, indexOfChild(this.f9087m));
            } else {
                f.c(liveSkuLottie, fVar);
            }
            if (this.f9086l.g()) {
                this.f9086l.k();
            } else {
                this.f9086l.h();
                z = false;
            }
            this.f9086l.setAlpha(z ? 1.0f : 0.0f);
            return;
        }
        LiveSkuLottie liveSkuLottie3 = this.f9086l;
        if (liveSkuLottie3 != null) {
            liveSkuLottie3.h();
            this.f9086l.setAlpha(0.0f);
        }
    }

    private void e() {
        if (this.f9083i.g().Z) {
            return;
        }
        f fVar = this.f9082h.f9090e;
        String o0 = this.f9083i.o0();
        String q0 = this.f9083i.q0();
        if (fVar != null && !TextUtils.isEmpty(o0) && !TextUtils.isEmpty(q0)) {
            String p0 = this.f9083i.p0(this.f9083i.e().s(), o0, q0);
            CoreVideoSkuView p = CoreVideoSkuView.p(getContext(), this.f9084j, p0);
            CoreVideoSkuView coreVideoSkuView = this.q;
            if (p != coreVideoSkuView) {
                k();
                this.q = p;
                RelativeLayout.LayoutParams u = fVar.u(p);
                u.addRule(13);
                this.q.setLayoutParams(u);
                m.b(this, this.q, -1);
            } else {
                f.c(coreVideoSkuView, fVar);
            }
            this.q.setVisibility(4);
            e.d(this.q, this.f9082h.a);
            this.q.i(this.f9083i, p0, q0);
            return;
        }
        String str = CoreVideoSkuView.r;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append(fVar == null ? "size\u4e3a\u7a7a" : "");
        sb.append(TextUtils.isEmpty(q0) ? "videoUrl\u4e3a\u7a7a" : "");
        sb.append(TextUtils.isEmpty(o0) ? "videoId\u4e3a\u7a7a" : "");
        objArr[0] = sb.toString();
        com.jingdong.app.mall.home.o.a.f.r0(str, objArr);
        k();
    }

    private void l(View view, float f2, float f3) {
        if (view != null) {
            view.setAlpha(f2);
            view.setScaleX(f3);
            view.setScaleY(f3);
        }
    }

    public void b(Info info) {
        if (info == null) {
            return;
        }
        this.f9082h = info;
        d();
        a();
    }

    public void c(Info info, CoreModel coreModel) {
        b(info);
        if (coreModel == null) {
            return;
        }
        this.f9083i = coreModel;
        e();
    }

    public void f(int i2) {
        if (i2 < 100) {
            return;
        }
        float f2 = i2 - 100;
        l(this.p, f2 / 400.0f, ((f2 * 0.3f) / 400.0f) + 0.7f);
    }

    public void g(int i2) {
        if (i2 > 450) {
            return;
        }
        l(this.o, Math.max((400 - i2) / 400.0f, 0.0f), Math.max(1.0f - ((i2 * 0.3f) / 400), 0.7f));
    }

    public void h(int i2) {
        if (i2 < 100) {
            return;
        }
        float f2 = i2 - 100;
        q(f2 / 400.0f, ((f2 * 0.3f) / 400.0f) + 0.7f);
    }

    public void i(int i2) {
        if (i2 > 450) {
            return;
        }
        q(Math.max((400 - i2) / 400.0f, 0.0f), Math.max(1.0f - ((i2 * 0.3f) / 400), 0.7f));
    }

    public int j() {
        return this.r;
    }

    public void k() {
        CoreVideoSkuView coreVideoSkuView = this.q;
        if (coreVideoSkuView != null) {
            m.K(coreVideoSkuView);
            this.q.x();
            this.q = null;
        }
    }

    public void m(float f2, float f3) {
        l(this.f9088n, f2, f3);
    }

    public void n(int i2) {
        this.r = i2;
    }

    public void o(float f2, float f3) {
        l(this.f9087m, f2, f3);
    }

    public void p() {
        l(this.o, 1.0f, 1.0f);
        l(this.p, 0.0f, 0.7f);
    }

    public void q(float f2, float f3) {
        l(this, f2, f3);
    }

    public void r() {
        SkuLabel skuLabel = this.o;
        this.o = this.p;
        this.p = skuLabel;
    }

    public SkuLayout(Context context, int i2) {
        super(context);
        this.s = 15;
        this.f9081g = context;
        this.s = i2;
    }
}
