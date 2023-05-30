package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.category.view.CaTitleRightView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class Line1To4Title extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private GradientTextView f9897g;

    /* renamed from: h  reason: collision with root package name */
    private CaTitleRightView f9898h;

    /* renamed from: i  reason: collision with root package name */
    private SimpleDraweeView f9899i;

    /* renamed from: j  reason: collision with root package name */
    private com.jingdong.app.mall.home.floor.view.b.g.a f9900j;

    /* renamed from: k  reason: collision with root package name */
    private f f9901k;

    /* renamed from: l  reason: collision with root package name */
    private f f9902l;

    /* renamed from: m  reason: collision with root package name */
    private f f9903m;

    /* renamed from: n  reason: collision with root package name */
    private e.b f9904n;

    /* loaded from: classes4.dex */
    class a implements e.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            Line1To4Title.this.c(true);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
            Line1To4Title.this.c(true);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            Line1To4Title line1To4Title = Line1To4Title.this;
            line1To4Title.c(line1To4Title.f9900j == null || TextUtils.isEmpty(Line1To4Title.this.f9900j.j()));
        }
    }

    /* loaded from: classes4.dex */
    class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.view.b.g.a f9905g;

        b(com.jingdong.app.mall.home.floor.view.b.g.a aVar) {
            this.f9905g = aVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean m2 = this.f9905g.m();
            JumpEntity e2 = this.f9905g.e();
            if (!m2 || e2 == null) {
                return;
            }
            com.jingdong.app.mall.home.r.c.a.s("Home_TopRight", "", com.jingdong.app.mall.home.r.c.b.c(e2.getSrvJson()).toString());
            l.e(Line1To4Title.this.getContext(), e2);
        }
    }

    public Line1To4Title(Context context) {
        super(context);
        this.f9901k = new f(-2, -1);
        this.f9902l = new f(-2, 70);
        this.f9903m = new f(-1, -1);
        this.f9904n = new a();
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.f9899i = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        View view = this.f9899i;
        addView(view, this.f9903m.u(view));
        GradientTextView gradientTextView = new GradientTextView(context);
        this.f9897g = gradientTextView;
        gradientTextView.getPaint().setFakeBoldText(true);
        this.f9897g.setMaxLines(1);
        this.f9897g.setGravity(17);
        this.f9897g.setEllipsize(TextUtils.TruncateAt.END);
        this.f9901k.E(24, 0, 0, 0);
        View view2 = this.f9897g;
        addView(view2, this.f9901k.u(view2));
        CaTitleRightView caTitleRightView = new CaTitleRightView(context, 4, 6);
        this.f9898h = caTitleRightView;
        caTitleRightView.setMaxLines(1);
        this.f9898h.setTextSize(0, d.d(24));
        this.f9898h.setIncludeFontPadding(false);
        this.f9898h.setEllipsize(TextUtils.TruncateAt.END);
        this.f9898h.setGravity(16);
        this.f9902l.J(0, 0, 18, 0);
        this.f9902l.E(0, 0, 22, 0);
        RelativeLayout.LayoutParams u = this.f9902l.u(this.f9898h);
        u.addRule(11);
        addView(this.f9898h, u);
    }

    public void b(com.jingdong.app.mall.home.floor.view.b.g.a aVar) {
        this.f9900j = aVar;
        setOnClickListener(new b(aVar));
        this.f9897g.setText(aVar.i());
        this.f9897g.setTextSize(0, d.d(32));
        this.f9897g.setTextGradient(GradientTextView.GradientType.LeftBottomToRightTop, m.o(aVar.h(), -15066598));
        String j2 = aVar.j();
        if (TextUtils.isEmpty(j2)) {
            c(true);
            this.f9899i.setVisibility(4);
        } else {
            this.f9899i.setVisibility(0);
            e.p(this.f9899i, j2, e.b, this.f9904n);
        }
        String k2 = aVar.k();
        int i2 = m.i(aVar.f(), -381927);
        if (TextUtils.isEmpty(k2)) {
            this.f9898h.setVisibility(4);
        } else {
            this.f9898h.setTextColor(i2);
            this.f9898h.setMaxWidth(d.d(R2.anim.slide_out_to_bottom_medium_time));
            this.f9898h.setVisibility(0);
            this.f9898h.setText(k2);
        }
        f.c(this.f9897g, this.f9901k);
        f.c(this.f9898h, this.f9902l);
        f.c(this.f9899i, this.f9903m);
    }

    protected void c(boolean z) {
        this.f9897g.setVisibility(z ? 0 : 8);
    }
}
