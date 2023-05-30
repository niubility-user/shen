package com.jingdong.app.mall.home.deploy.view.layout.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;

/* loaded from: classes4.dex */
public class IconImageText extends RelativeLayout {

    /* renamed from: g */
    private final Context f9014g;

    /* renamed from: h */
    private boolean f9015h;

    /* renamed from: i */
    private Info f9016i;

    /* renamed from: j */
    private SimpleDraweeView f9017j;

    /* renamed from: k */
    private SimpleDraweeView f9018k;

    /* renamed from: l */
    private GradientTextView f9019l;

    /* loaded from: classes4.dex */
    public static class Info {
        boolean a;
        f b;

        /* renamed from: c */
        String f9020c;
        boolean d;

        /* renamed from: e */
        f f9021e;

        /* renamed from: f */
        String f9022f;

        /* renamed from: h */
        String f9024h;

        /* renamed from: i */
        int[] f9025i;

        /* renamed from: j */
        boolean f9026j;

        /* renamed from: k */
        int f9027k;

        /* renamed from: m */
        int f9029m;

        /* renamed from: g */
        int f9023g = 9;

        /* renamed from: l */
        f f9028l = new f(-2, -2);

        /* renamed from: n */
        int f9030n = 130;

        private Info() {
        }

        public static Info a() {
            return new Info();
        }

        public int b() {
            return this.f9029m + this.f9030n;
        }

        public Info c(boolean z) {
            this.f9026j = z;
            return this;
        }

        public Info d(int i2, int i3, String str) {
            if (i2 > 0 && i3 > 0 && !TextUtils.isEmpty(str)) {
                this.f9029m = i2;
                this.b = new f(i2, i3);
                this.f9020c = str;
                this.a = true;
            }
            return this;
        }

        public Info e(int i2, int i3, int i4, int i5) {
            f fVar = this.b;
            if (fVar != null) {
                fVar.E(i2, i3, i4, i5);
            }
            return this;
        }

        public Info f(int i2, int i3, String str) {
            if (i2 > 0 && i3 > 0 && !TextUtils.isEmpty(str)) {
                this.f9030n = i2;
                this.f9021e = new f(i2, i3);
                this.f9022f = str;
                this.d = true;
            }
            return this;
        }

        public Info g(String str) {
            this.f9024h = str;
            return this;
        }

        public Info h(int[] iArr, int i2) {
            this.f9025i = iArr;
            this.f9027k = i2;
            return this;
        }

        public Info i(int i2) {
            this.f9023g = i2;
            return this;
        }
    }

    public IconImageText(Context context) {
        super(context);
        this.f9014g = context;
    }

    private void d(View view, View view2, int i2) {
        if (view == null || view2 == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            e((RelativeLayout.LayoutParams) layoutParams, view2, i2);
        }
    }

    private void e(RelativeLayout.LayoutParams layoutParams, View view, int i2) {
        if (layoutParams == null || view == null) {
            return;
        }
        layoutParams.addRule(i2, view.getId());
    }

    private void f() {
        Info info = this.f9016i;
        f fVar = info.b;
        if (fVar == null) {
            info.a = false;
            return;
        }
        SimpleDraweeView simpleDraweeView = this.f9017j;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.f9014g);
            this.f9017j = homeDraweeView;
            homeDraweeView.setId(R.id.home_deploy_title_icon);
            this.f9017j.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u = fVar.u(this.f9017j);
            u.addRule(15);
            addView(this.f9017j, u);
        } else {
            f.c(simpleDraweeView, fVar);
        }
        l();
    }

    private void g() {
        Info info = this.f9016i;
        f fVar = info.f9021e;
        if (fVar != null && !TextUtils.isEmpty(info.f9022f)) {
            View view = this.f9018k;
            if (view == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(this.f9014g);
                this.f9018k = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                RelativeLayout.LayoutParams u = fVar.u(this.f9018k);
                u.addRule(15);
                e(u, this.f9017j, 1);
                addView(this.f9018k, u);
            } else {
                d(view, this.f9017j, 1);
                f.c(this.f9018k, fVar);
            }
            m();
            return;
        }
        this.f9016i.d = false;
        this.f9015h = false;
    }

    private void h() {
        f fVar = this.f9016i.f9028l;
        if (fVar == null) {
            return;
        }
        View view = this.f9019l;
        if (view == null) {
            GradientTextView gradientTextView = new GradientTextView(this.f9014g);
            this.f9019l = gradientTextView;
            gradientTextView.setMaxLines(1);
            RelativeLayout.LayoutParams u = fVar.u(this.f9019l);
            u.addRule(15);
            e(u, this.f9017j, 1);
            addView(this.f9019l, u);
        } else {
            d(view, this.f9017j, 1);
            f.c(this.f9019l, fVar);
        }
        this.f9019l.setTextGradient(GradientTextView.GradientType.LeftToRight, this.f9016i.f9025i);
        g.o(this.f9019l, this.f9016i.f9027k);
        g.k(this.f9019l, this.f9016i.f9026j);
        GradientTextView gradientTextView2 = this.f9019l;
        Info info = this.f9016i;
        gradientTextView2.setText(com.jingdong.app.mall.home.o.a.f.j(info.f9023g, info.f9024h));
    }

    public void j() {
        Info info = this.f9016i;
        if (info == null) {
            return;
        }
        GradientTextView gradientTextView = this.f9019l;
        if (gradientTextView != null) {
            gradientTextView.setVisibility((info.d && this.f9015h) ? 8 : 0);
        }
        SimpleDraweeView simpleDraweeView = this.f9018k;
        if (simpleDraweeView != null) {
            simpleDraweeView.setAlpha(this.f9015h ? 1.0f : 0.0f);
            this.f9018k.setVisibility(this.f9016i.d ? 0 : 8);
        }
        SimpleDraweeView simpleDraweeView2 = this.f9017j;
        if (simpleDraweeView2 != null) {
            simpleDraweeView2.setVisibility(this.f9016i.a ? 0 : 8);
        }
    }

    private void l() {
        SimpleDraweeView simpleDraweeView = this.f9017j;
        if (simpleDraweeView == null) {
            return;
        }
        simpleDraweeView.setVisibility(0);
        e.p(this.f9017j, this.f9016i.f9020c, e.b, new e.b() { // from class: com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText.1
            {
                IconImageText.this = this;
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onFailed(String str, View view) {
                IconImageText.this.f9016i.a = false;
                IconImageText.this.j();
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onStart(String str, View view) {
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onSuccess(String str, View view) {
                IconImageText.this.f9016i.a = true;
                IconImageText.this.j();
            }
        });
    }

    private void m() {
        if (this.f9018k != null && !TextUtils.isEmpty(this.f9016i.f9022f)) {
            this.f9018k.setVisibility(0);
            e.p(this.f9018k, this.f9016i.f9022f, e.b, new e.b() { // from class: com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText.2
                {
                    IconImageText.this = this;
                }

                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onFailed(String str, View view) {
                    IconImageText.this.f9016i.d = false;
                    IconImageText.this.j();
                }

                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onStart(String str, View view) {
                }

                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onSuccess(String str, View view) {
                    IconImageText.this.f9016i.d = true;
                    IconImageText.this.f9015h = true;
                    IconImageText.this.j();
                }
            });
            return;
        }
        this.f9016i.d = false;
        j();
    }

    public void i(Info info) {
        if (info == null) {
            return;
        }
        this.f9016i = info;
        f();
        h();
        g();
        j();
    }

    public int k() {
        if (this.f9016i == null) {
            return 0;
        }
        int paddingLeft = getPaddingLeft() + 0 + getPaddingRight();
        f fVar = this.f9016i.b;
        if (fVar != null) {
            paddingLeft = paddingLeft + fVar.v() + fVar.l() + fVar.m();
        }
        f fVar2 = this.f9016i.f9021e;
        int v = fVar2 != null ? fVar2.v() : 0;
        float f2 = 0.0f;
        if (this.f9016i.f9028l != null) {
            GradientTextView gradientTextView = this.f9019l;
            f2 = com.jingdong.app.mall.home.o.a.f.T(gradientTextView, gradientTextView.getText());
        }
        return (int) (paddingLeft + Math.max(v, f2));
    }
}
