package com.jingdong.app.mall.home.floor.view.widget.bubblebanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.BubbleBannerEntity;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.view.IBubbleBannerSmall;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class BubbleBannerSmallLayout extends RelativeLayout implements IBubbleBannerSmall {

    /* renamed from: g  reason: collision with root package name */
    private Context f10188g;

    /* renamed from: h  reason: collision with root package name */
    private int f10189h;

    /* renamed from: i  reason: collision with root package name */
    private f f10190i;

    /* renamed from: j  reason: collision with root package name */
    private f f10191j;

    /* renamed from: k  reason: collision with root package name */
    private f f10192k;

    /* renamed from: l  reason: collision with root package name */
    private f f10193l;

    /* renamed from: m  reason: collision with root package name */
    private SimpleDraweeView f10194m;

    /* renamed from: n  reason: collision with root package name */
    private SimpleDraweeView f10195n;
    private DarkWhiteBgImageView o;
    private GradientTextView p;
    private GradientDrawable q;
    private com.jingdong.app.mall.home.r.e.f r;
    private int s;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends JDSimpleImageLoadingListener {
        a() {
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            super.onLoadingComplete(str, view, bitmap);
            BubbleBannerSmallLayout.this.setBackgroundDrawable(e.b);
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            super.onLoadingFailed(str, view, jDFailReason);
            BubbleBannerSmallLayout.this.setBackgroundDrawable(e.w());
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingStarted(String str, View view) {
            super.onLoadingStarted(str, view);
            BubbleBannerSmallLayout.this.setBackgroundDrawable(e.w());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BubbleBannerSmallLayout.this.g(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BubbleBannerSmallLayout.this.g(1);
        }
    }

    public BubbleBannerSmallLayout(Context context, int i2, int i3) {
        super(context);
        this.f10190i = new f(-1, -1);
        this.f10188g = context;
        this.f10189h = i2;
        this.s = i3;
        f();
    }

    private void b(com.jingdong.app.mall.home.r.e.f fVar) {
        int t = fVar.t();
        String G = fVar.G();
        String u = fVar.u();
        SimpleDraweeView simpleDraweeView = this.f10194m;
        if (t == 0) {
            G = u;
        }
        e.s(simpleDraweeView, G, new a());
        this.f10194m.setOnClickListener(new b());
    }

    private void c(int i2, com.jingdong.app.mall.home.r.e.f fVar) {
        int t = fVar.t();
        String u = fVar.u();
        if (t == 0) {
            this.o.setVisibility(4);
            this.f10195n.setVisibility(4);
        } else {
            this.o.setVisibility(0);
            this.f10195n.setVisibility(0);
            e.d(this.o, u);
        }
        this.o.setOnClickListener(new c());
    }

    private void d(com.jingdong.app.mall.home.r.e.f fVar) {
        int t = fVar.t();
        String O = fVar.O();
        if (t != 0 && !TextUtils.isEmpty(O)) {
            this.p.setVisibility(0);
            this.p.setText(O);
            int i2 = this.s;
            this.p.setTextSize(0, d.d((i2 == BubbleBannerEntity.TYPE_08008_V936 || i2 == BubbleBannerEntity.TYPE_08008) ? 26 : 24));
            this.p.setTextGradient(GradientTextView.GradientType.LeftToRight, m.o(fVar.C(), -16777216));
            return;
        }
        this.p.setVisibility(4);
    }

    private void e() {
        int i2 = this.s;
        if (i2 == BubbleBannerEntity.TYPE_08008_V936) {
            f fVar = this.f10191j;
            int i3 = this.f10189h;
            fVar.F(new Rect(i3 < 2 ? 28 : 9, i3 % 2 == 0 ? 12 : 0, 0, 0));
            f fVar2 = this.f10192k;
            int i4 = this.f10189h;
            fVar2.F(new Rect(i4 < 2 ? 60 : 41, i4 % 2 != 0 ? 0 : 12, 0, 0));
            f fVar3 = this.f10193l;
            int i5 = this.f10189h;
            fVar3.E(i5 >= 2 ? 9 : 28, i5 % 2 == 0 ? 108 : 97, 0, 0);
            return;
        }
        if (i2 == BubbleBannerEntity.TYPE_08008) {
            f fVar4 = this.f10191j;
            int i6 = this.f10189h;
            fVar4.F(new Rect(i6 < 2 ? 24 : 16, i6 % 2 == 0 ? 0 : 8, 0, 0));
            f fVar5 = this.f10192k;
            int i7 = this.f10189h;
            fVar5.F(new Rect(i7 >= 2 ? 24 : 32, i7 % 2 != 0 ? 14 : 6, 0, 0));
            f fVar6 = this.f10193l;
            int i8 = this.f10189h;
            fVar6.E(i8 >= 2 ? 4 : 12, i8 % 2 == 0 ? 101 : 109, 0, 0);
            return;
        }
        f fVar7 = this.f10191j;
        int i9 = this.f10189h;
        fVar7.F(new Rect(i9 < 2 ? 24 : 16, i9 % 2 == 0 ? 0 : 8, 0, 0));
        f fVar8 = this.f10192k;
        int i10 = this.f10189h;
        fVar8.F(new Rect(i10 >= 2 ? 24 : 32, i10 % 2 != 0 ? 14 : 6, 0, 0));
        f fVar9 = this.f10193l;
        int i11 = this.f10189h;
        fVar9.E(i11 < 2 ? 24 : 16, i11 % 2 == 0 ? 102 : 110, 0, 0);
    }

    private void f() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.q = gradientDrawable;
        gradientDrawable.setShape(0);
        this.q.setColor(-1);
        if (this.s == BubbleBannerEntity.TYPE_08008_V936) {
            this.f10191j = new f(160, 130);
        } else {
            this.f10191j = new f(R2.anim.lib_cashier_sdk_fragment_right_out, R2.anim.lib_cashier_sdk_fragment_right_out);
        }
        HomeDraweeView homeDraweeView = new HomeDraweeView(this.f10188g);
        this.f10195n = homeDraweeView;
        homeDraweeView.setBackgroundDrawable(this.q);
        SimpleDraweeView simpleDraweeView = this.f10195n;
        addView(simpleDraweeView, this.f10191j.u(simpleDraweeView));
        if (this.s == BubbleBannerEntity.TYPE_08008_V936) {
            this.f10192k = new f(96, 96);
        } else {
            this.f10192k = new f(120, 120);
        }
        DarkWhiteBgImageView darkWhiteBgImageView = new DarkWhiteBgImageView(this.f10188g);
        this.o = darkWhiteBgImageView;
        darkWhiteBgImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        DarkWhiteBgImageView darkWhiteBgImageView2 = this.o;
        addView(darkWhiteBgImageView2, this.f10192k.u(darkWhiteBgImageView2));
        HomeDraweeView homeDraweeView2 = new HomeDraweeView(this.f10188g);
        this.f10194m = homeDraweeView2;
        homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        SimpleDraweeView simpleDraweeView2 = this.f10194m;
        addView(simpleDraweeView2, this.f10190i.u(simpleDraweeView2));
        int i2 = this.s;
        if (i2 != BubbleBannerEntity.TYPE_08008_V936 && i2 != BubbleBannerEntity.TYPE_08008) {
            this.f10193l = new f(R2.anim.lib_cashier_sdk_fragment_right_out, 36);
        } else {
            this.f10193l = new f(160, 34);
        }
        GradientTextView gradientTextView = new GradientTextView(this.f10188g);
        this.p = gradientTextView;
        gradientTextView.setGravity(17);
        this.p.getPaint().setFakeBoldText(true);
        this.p.setMaxLines(1);
        this.p.setEllipsize(TextUtils.TruncateAt.END);
        GradientTextView gradientTextView2 = this.p;
        addView(gradientTextView2, this.f10193l.u(gradientTextView2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i2) {
        com.jingdong.app.mall.home.r.e.f fVar;
        JumpEntity jump;
        if (l.k() || (fVar = this.r) == null || (jump = fVar.getJump()) == null) {
            return;
        }
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(jump.srvJson);
        c2.a("skuposition", i2 + "");
        l.onClickJsonEvent(getContext(), jump, "", jump.getSrv(), c2.toString(), i2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.IBubbleBannerSmall
    public void onViewBind(com.jingdong.app.mall.home.r.e.f fVar, int i2) {
        this.r = fVar;
        this.q.setCornerRadius(d.d(24));
        b(fVar);
        d(fVar);
        c(i2, fVar);
        e();
        f.d(this.p, this.f10193l, true);
        f.d(this.o, this.f10192k, true);
        f.d(this.f10195n, this.f10191j, true);
    }
}
