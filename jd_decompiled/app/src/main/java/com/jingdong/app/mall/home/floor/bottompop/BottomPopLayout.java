package com.jingdong.app.mall.home.floor.bottompop;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.R;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.wireless.iconfont.widget.IconImageView;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class BottomPopLayout extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private final com.jingdong.app.mall.home.o.a.b f9200g;

    /* renamed from: h  reason: collision with root package name */
    private BaseFloatPriority f9201h;

    /* renamed from: i  reason: collision with root package name */
    private RelativeLayout f9202i;

    /* renamed from: j  reason: collision with root package name */
    private Context f9203j;

    /* renamed from: k  reason: collision with root package name */
    private HomeDraweeView f9204k;

    /* renamed from: l  reason: collision with root package name */
    private com.jingdong.app.mall.home.floor.common.f f9205l;

    /* renamed from: m  reason: collision with root package name */
    private HomeDraweeView f9206m;

    /* renamed from: n  reason: collision with root package name */
    private com.jingdong.app.mall.home.floor.common.f f9207n;
    private LinearLayout o;
    private com.jingdong.app.mall.home.floor.common.f p;
    private TextView q;
    private com.jingdong.app.mall.home.floor.common.f r;
    private IconImageView s;
    private com.jingdong.app.mall.home.floor.common.f t;
    private com.jingdong.app.mall.home.floor.bottompop.a u;
    private boolean v;
    private boolean w;
    private final AtomicBoolean x;

    /* loaded from: classes4.dex */
    class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            BottomPopLayout.this.m();
        }
    }

    /* loaded from: classes4.dex */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (BottomPopLayout.this.u != null) {
                BottomPopLayout.this.u.h(view.getContext());
            }
        }
    }

    /* loaded from: classes4.dex */
    class c extends JDSimpleImageLoadingListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.bottompop.a f9210g;

        c(com.jingdong.app.mall.home.floor.bottompop.a aVar) {
            this.f9210g = aVar;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            super.onLoadingComplete(str, view, bitmap);
            this.f9210g.b();
            BottomPopLayout.this.n(this.f9210g);
        }
    }

    /* loaded from: classes4.dex */
    class d extends JDSimpleImageLoadingListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.bottompop.a f9212g;

        d(com.jingdong.app.mall.home.floor.bottompop.a aVar) {
            this.f9212g = aVar;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            super.onLoadingComplete(str, view, bitmap);
            this.f9212g.m();
            BottomPopLayout.this.n(this.f9212g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e extends BaseFloatPriority {

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.bottompop.a f9214i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(String str, int i2, com.jingdong.app.mall.home.floor.bottompop.a aVar) {
            super(str, i2);
            this.f9214i = aVar;
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        protected void g(int i2) {
            BottomPopLayout.this.i();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        public void h() {
            BottomPopLayout.this.l(this.f9214i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class f extends JDSimpleImageLoadingListener {
        f() {
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            super.onLoadingComplete(str, view, bitmap);
            if (BottomPopLayout.this.x.getAndSet(true)) {
                return;
            }
            BottomPopLayout.this.setTranslationY(0.0f);
            BottomPopLayout.this.m();
            BottomPopLayout.this.o.animate().alpha(1.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class g extends com.jingdong.app.mall.home.r.a.d {
        g() {
        }

        @Override // com.jingdong.app.mall.home.r.a.d
        protected void onEnd(Animator animator, boolean z) {
            BottomPopLayout.this.j();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class h extends com.jingdong.app.mall.home.o.a.b {
        h() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            BottomPopLayout.this.j();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class i implements View.OnClickListener {
        i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (BottomPopLayout.this.u != null) {
                BottomPopLayout.this.u.i();
            }
            BottomPopLayout.this.i();
        }
    }

    public BottomPopLayout(RelativeLayout relativeLayout) {
        super(relativeLayout.getContext());
        this.f9200g = new a();
        this.f9205l = new com.jingdong.app.mall.home.floor.common.f(-1, 120);
        this.f9207n = new com.jingdong.app.mall.home.floor.common.f(-1, 120);
        this.x = new AtomicBoolean(false);
        this.f9202i = relativeLayout;
        this.f9203j = relativeLayout.getContext();
        HomeDraweeView homeDraweeView = new HomeDraweeView(this.f9203j);
        this.f9204k = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        View view = this.f9204k;
        addView(view, this.f9205l.u(view));
        HomeDraweeView homeDraweeView2 = new HomeDraweeView(this.f9203j);
        this.f9206m = homeDraweeView2;
        homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        View view2 = this.f9206m;
        addView(view2, this.f9207n.u(view2));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        setLayoutParams(layoutParams);
        setOnClickListener(new b());
    }

    private void g() {
        if (this.o != null) {
            return;
        }
        LinearLayout linearLayout = new LinearLayout(this.f9203j);
        this.o = linearLayout;
        linearLayout.setOnClickListener(new i());
        this.o.setAlpha(0.0f);
        this.o.setOrientation(0);
        com.jingdong.app.mall.home.floor.common.f fVar = new com.jingdong.app.mall.home.floor.common.f(68, 32);
        this.p = fVar;
        fVar.E(0, 0, 16, 0);
        RelativeLayout.LayoutParams u = this.p.u(this.o);
        u.addRule(11);
        addView(this.o, u);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(2130706432);
        gradientDrawable.setCornerRadius(this.p.h() >> 1);
        this.o.setBackgroundDrawable(gradientDrawable);
        TextView textView = new TextView(this.f9203j);
        this.q = textView;
        textView.setGravity(17);
        this.q.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(20));
        this.q.setIncludeFontPadding(false);
        this.q.setTextColor(-1);
        this.q.setTypeface(FontsUtil.getTypeFace(this.f9203j));
        com.jingdong.app.mall.home.floor.common.f fVar2 = new com.jingdong.app.mall.home.floor.common.f(32, 32);
        this.r = fVar2;
        LinearLayout linearLayout2 = this.o;
        TextView textView2 = this.q;
        linearLayout2.addView(textView2, fVar2.i(textView2));
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(1593835520);
        gradientDrawable2.setCornerRadius(this.r.h() >> 1);
        this.q.setBackgroundDrawable(gradientDrawable2);
        IconImageView iconImageView = new IconImageView(this.f9203j);
        this.s = iconImageView;
        iconImageView.setColor(-1);
        this.s.setResCode(R.string.jdif_common_guanbi);
        com.jingdong.app.mall.home.floor.common.f fVar3 = new com.jingdong.app.mall.home.floor.common.f(32, 32);
        this.t = fVar3;
        fVar3.E(0, 0, 4, 0);
        this.t.J(6, 6, 6, 6);
        LinearLayout linearLayout3 = this.o;
        IconImageView iconImageView2 = this.s;
        linearLayout3.addView(iconImageView2, this.t.i(iconImageView2));
    }

    private boolean h(com.jingdong.app.mall.home.floor.bottompop.a aVar) {
        return aVar == null || this.w || aVar.g() || !JDHomeFragment.O0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.v || !this.w) {
            return;
        }
        this.v = true;
        this.o.setAlpha(0.0f);
        HomeDraweeView homeDraweeView = this.f9206m;
        homeDraweeView.setPivotX(this.u.e(homeDraweeView.getWidth() >> 1));
        this.f9206m.setPivotY(r0.getHeight());
        long j2 = 500;
        this.f9206m.animate().scaleX(0.0f).scaleY(0.0f).setDuration(j2).setListener(new g());
        this.f9204k.animate().translationY(this.f9205l.h()).setDuration(j2);
        com.jingdong.app.mall.home.o.a.f.F0(new h(), 600);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(com.jingdong.app.mall.home.floor.bottompop.a aVar) {
        if (h(aVar)) {
            return;
        }
        this.u = aVar;
        this.w = true;
        aVar.l();
        com.jingdong.app.mall.home.o.a.d.g();
        g();
        this.f9205l.C(aVar.f9221e);
        this.f9207n.C(aVar.f9221e);
        com.jingdong.app.mall.home.floor.common.f.c(this.f9204k, this.f9205l);
        com.jingdong.app.mall.home.floor.common.f.c(this.f9206m, this.f9207n);
        String str = aVar.f9227k;
        HomeDraweeView homeDraweeView = this.f9204k;
        JDDisplayImageOptions jDDisplayImageOptions = com.jingdong.app.mall.home.floor.ctrl.e.f9402h;
        com.jingdong.app.mall.home.floor.ctrl.f.e(str, homeDraweeView, jDDisplayImageOptions);
        com.jingdong.app.mall.home.floor.ctrl.f.f(aVar.f9226j, this.f9206m, jDDisplayImageOptions, new f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        int max = Math.max(this.u.f(), 0);
        this.q.setText(String.valueOf(max));
        if (max == 0) {
            i();
        } else {
            com.jingdong.app.mall.home.o.a.f.F0(this.f9200g, 1000L);
        }
    }

    public void j() {
        BaseFloatPriority baseFloatPriority = this.f9201h;
        if (baseFloatPriority != null && this.w) {
            baseFloatPriority.b(true);
        }
        com.jingdong.app.mall.home.floor.bottompop.a aVar = this.u;
        if (aVar != null && this.w) {
            aVar.k();
            this.u.j();
        }
        this.w = false;
        m.K(this);
    }

    public void k(com.jingdong.app.mall.home.floor.bottompop.a aVar) {
        if (h(aVar)) {
            return;
        }
        setTranslationY(com.jingdong.app.mall.home.floor.common.d.f9279g << 1);
        m.a(this.f9202i, this);
        com.jingdong.app.mall.home.floor.ctrl.e.z(aVar.f9227k, new c(aVar));
        com.jingdong.app.mall.home.floor.ctrl.e.z(aVar.f9226j, new d(aVar));
    }

    public void n(com.jingdong.app.mall.home.floor.bottompop.a aVar) {
        if (h(aVar) || !aVar.a()) {
            return;
        }
        e eVar = new e("\u5e95\u90e8\u901a\u680f\u8054\u52a8\u5e95\u5bfc", 21, aVar);
        this.f9201h = eVar;
        if (eVar.a()) {
            this.f9201h.l();
        }
    }
}
