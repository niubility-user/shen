package com.jingdong.app.mall.home.floor.view.widget.bubblebanner936;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class BubbleBannerItemView extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private Context f10200g;

    /* renamed from: h  reason: collision with root package name */
    private f f10201h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f10202i;

    /* renamed from: j  reason: collision with root package name */
    private com.jingdong.app.mall.home.floor.view.widget.bubblebanner936.a f10203j;

    /* renamed from: k  reason: collision with root package name */
    private SimpleDraweeView f10204k;

    /* renamed from: l  reason: collision with root package name */
    private SimpleDraweeView f10205l;

    /* renamed from: m  reason: collision with root package name */
    private SimpleDraweeView f10206m;

    /* renamed from: n  reason: collision with root package name */
    private TextView f10207n;
    private TextView o;
    private f p;
    private f q;
    private f r;
    private f s;
    private GradientDrawable t;
    private GradientDrawable u;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (BubbleBannerItemView.this.f10203j != null) {
                BubbleBannerItemView.this.f10203j.j(BubbleBannerItemView.this.f10200g);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements e.b {
        int a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ SimpleDraweeView f10209c;

        b(String str, SimpleDraweeView simpleDraweeView) {
            this.b = str;
            this.f10209c = simpleDraweeView;
            this.a = m.i(str, -1644826);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            SimpleDraweeView simpleDraweeView = this.f10209c;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(4);
            }
            if (BubbleBannerItemView.this.u == null) {
                BubbleBannerItemView.this.u = new GradientDrawable();
                BubbleBannerItemView.this.u.setShape(0);
            }
            BubbleBannerItemView.this.u.setColor(this.a);
            float d = d.d(20);
            float d2 = d.d(24);
            BubbleBannerItemView.this.u.setCornerRadii(com.jingdong.app.mall.home.o.a.f.M0() ? new float[]{d2, d2, d2, d2, d2, d2, d2, d2} : new float[]{d, d, d, d, d2, d2, d2, d2});
            BubbleBannerItemView bubbleBannerItemView = BubbleBannerItemView.this;
            bubbleBannerItemView.setBackgroundDrawable(bubbleBannerItemView.u);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
        }
    }

    public BubbleBannerItemView(Context context, f fVar, boolean z) {
        super(context);
        this.f10200g = context;
        this.f10201h = fVar;
        this.f10202i = z;
    }

    private void e() {
        SimpleDraweeView simpleDraweeView = this.f10206m;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.f10200g);
            this.f10206m = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            f fVar = new f(R2.anim.pickerview_dialog_scale_in, 80);
            this.q = fVar;
            RelativeLayout.LayoutParams u = fVar.u(this.f10206m);
            u.addRule(12);
            addView(this.f10206m, u);
        } else {
            f.c(simpleDraweeView, this.q);
            this.f10206m.setVisibility(0);
        }
        n(this.f10206m, this.f10203j.b(), this.f10203j.a());
    }

    private void f() {
        setOnClickListener(new a());
    }

    private void i() {
        TextView textView = this.f10207n;
        if (textView == null) {
            TextView textView2 = new TextView(this.f10200g);
            this.f10207n = textView2;
            textView2.setGravity(17);
            this.f10207n.setMaxLines(1);
            this.f10207n.setEllipsize(TextUtils.TruncateAt.END);
            this.f10207n.getPaint().setFakeBoldText(true);
            f fVar = new f(-1, 46);
            this.r = fVar;
            RelativeLayout.LayoutParams u = fVar.u(this.f10207n);
            u.addRule(12);
            u.addRule(14);
            addView(this.f10207n, u);
        } else {
            f.c(textView, this.r);
            this.f10207n.setVisibility(0);
        }
        this.f10207n.setMaxWidth(d.d(R2.anim.pickerview_dialog_scale_in));
        this.f10207n.setPadding(10, -3, 10, -3);
        this.f10207n.setTextSize(0, d.d(26));
        this.f10207n.setTextColor(m.j(this.f10203j.c(), -1));
        this.f10207n.setText(this.f10203j.d());
    }

    private void j() {
        SimpleDraweeView simpleDraweeView = this.f10205l;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.f10200g);
            this.f10205l = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            int i2 = this.f10202i ? 132 : 116;
            f fVar = new f(i2, i2);
            this.p = fVar;
            fVar.F(new Rect(0, this.f10202i ? 3 : 1, 0, 0));
            RelativeLayout.LayoutParams u = this.p.u(this.f10205l);
            u.addRule(10);
            u.addRule(14);
            addView(this.f10205l, u);
        } else {
            f.c(simpleDraweeView, this.p);
            this.f10205l.setVisibility(0);
        }
        e.d(this.f10205l, this.f10203j.e());
        com.jingdong.app.mall.home.n.h.e.d(this.f10205l, d.d(16));
    }

    private void k() {
        if (this.f10203j.k() && !TextUtils.isEmpty(this.f10203j.h())) {
            TextView textView = this.o;
            if (textView == null) {
                TextView textView2 = new TextView(this.f10200g);
                this.o = textView2;
                textView2.setGravity(17);
                this.o.setMaxLines(1);
                this.o.setEllipsize(TextUtils.TruncateAt.END);
                f fVar = new f(-2, 34);
                this.s = fVar;
                fVar.F(new Rect(0, 0, 0, 44));
                RelativeLayout.LayoutParams u = this.s.u(this.o);
                u.addRule(12);
                u.addRule(14);
                addView(this.o, u);
            } else {
                f.c(textView, this.s);
                this.o.setVisibility(0);
            }
            this.o.setMaxWidth(d.d(R2.anim.pickerview_dialog_scale_in));
            this.o.setMinWidth(d.d(60));
            int d = d.d(10);
            this.o.setPadding(d, -3, d, -3);
            this.o.setTextSize(0, d.d(20));
            this.o.setTextColor(m.j(this.f10203j.g(), -1));
            this.o.setText(this.f10203j.h());
            String f2 = this.f10203j.f();
            if (!TextUtils.isEmpty(f2)) {
                int[] e2 = com.jingdong.app.mall.home.floor.view.b.h.a.e(f2, -1);
                if (e2 == null || e2.length < 1) {
                    return;
                }
                if (this.t == null) {
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    this.t = gradientDrawable;
                    gradientDrawable.setShape(0);
                }
                if (Build.VERSION.SDK_INT >= 16 && e2.length > 1) {
                    this.t.setColors(e2);
                } else {
                    this.t.setColor(e2[0]);
                }
                this.t.setCornerRadius(d.d(17));
                this.o.setBackgroundDrawable(this.t);
                return;
            }
            this.o.setBackgroundDrawable(null);
            return;
        }
        TextView textView3 = this.o;
        if (textView3 != null) {
            textView3.setVisibility(4);
        }
    }

    private void m() {
        SimpleDraweeView simpleDraweeView = this.f10205l;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(4);
        }
        SimpleDraweeView simpleDraweeView2 = this.f10206m;
        if (simpleDraweeView2 != null) {
            simpleDraweeView2.setVisibility(4);
        }
        TextView textView = this.f10207n;
        if (textView != null) {
            textView.setVisibility(4);
        }
        TextView textView2 = this.o;
        if (textView2 != null) {
            textView2.setVisibility(4);
        }
    }

    private void n(SimpleDraweeView simpleDraweeView, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            str = "https://emptyUrl";
        }
        e.p(simpleDraweeView, str, e.b, new b(str2, simpleDraweeView));
    }

    public void g(com.jingdong.app.mall.home.floor.view.widget.bubblebanner936.a aVar) {
        f.c(this, this.f10201h);
        this.f10203j = aVar;
        if (this.u == null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            this.u = gradientDrawable;
            gradientDrawable.setShape(0);
        }
        this.u.setColor(-1);
        float d = d.d(20);
        float d2 = d.d(24);
        this.u.setCornerRadii(com.jingdong.app.mall.home.o.a.f.M0() ? new float[]{d2, d2, d2, d2, d2, d2, d2, d2} : new float[]{d, d, d, d, d2, d2, d2, d2});
        setBackgroundDrawable(this.u);
        if (aVar.i()) {
            h();
        } else {
            l();
        }
        com.jingdong.app.mall.home.n.h.e.d(this, d.d(24));
        f();
    }

    public void h() {
        m();
        SimpleDraweeView simpleDraweeView = this.f10204k;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.f10200g);
            this.f10204k = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(this.f10204k, new RelativeLayout.LayoutParams(-1, -1));
        } else {
            simpleDraweeView.setVisibility(0);
        }
        n(this.f10204k, this.f10203j.b(), this.f10203j.a());
    }

    public void l() {
        SimpleDraweeView simpleDraweeView = this.f10204k;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(4);
        }
        j();
        e();
        k();
        i();
    }
}
