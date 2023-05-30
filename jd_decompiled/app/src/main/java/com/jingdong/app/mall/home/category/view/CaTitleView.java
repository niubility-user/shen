package com.jingdong.app.mall.home.category.view;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.n.g.w.e;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class CaTitleView extends RelativeLayout {

    /* renamed from: g */
    protected e f8769g;

    /* renamed from: h */
    protected GradientTextView f8770h;

    /* renamed from: i */
    protected SimpleDraweeView f8771i;

    /* renamed from: j */
    protected CaTitleRightView f8772j;

    /* renamed from: k */
    protected LinearLayout f8773k;

    /* renamed from: l */
    protected f f8774l;

    /* renamed from: m */
    protected f f8775m;

    /* renamed from: n */
    protected f f8776n;
    protected f o;
    private SimpleDraweeView p;

    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ JumpEntity f8777g;

        a(JumpEntity jumpEntity) {
            CaTitleView.this = r1;
            this.f8777g = jumpEntity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f8777g == null) {
                return;
            }
            com.jingdong.app.mall.home.n.g.v.b.c("Category_Main_Title", CaTitleView.this.f8769g.e());
            com.jingdong.app.mall.home.n.h.b.a(CaTitleView.this.getContext(), this.f8777g);
        }
    }

    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ JumpEntity f8779g;

        b(JumpEntity jumpEntity) {
            CaTitleView.this = r1;
            this.f8779g = jumpEntity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f8779g == null) {
                return;
            }
            com.jingdong.app.mall.home.n.g.v.b.c("Category_Main_RightMore", CaTitleView.this.f8769g.e());
            com.jingdong.app.mall.home.n.h.b.a(CaTitleView.this.getContext(), this.f8779g);
        }
    }

    public CaTitleView(Context context) {
        super(context);
        b();
    }

    private void b() {
        HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
        this.p = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar = new f(-1, -1);
        View view = this.p;
        addView(view, fVar.u(view));
        HomeDraweeView homeDraweeView2 = new HomeDraweeView(getContext());
        this.f8771i = homeDraweeView2;
        homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar2 = new f(36, 36);
        this.f8774l = fVar2;
        fVar2.F(new Rect(24, 0, 0, 0));
        View view2 = this.f8771i;
        addView(view2, this.f8774l.u(view2));
        GradientTextView gradientTextView = new GradientTextView(getContext());
        this.f8770h = gradientTextView;
        gradientTextView.setMaxLines(1);
        this.f8770h.setEllipsize(TextUtils.TruncateAt.END);
        this.f8770h.setId(R.id.mallfloor_item1);
        this.f8770h.setGravity(16);
        this.f8770h.getPaint().setFakeBoldText(true);
        f fVar3 = new f(-2, -2);
        this.f8775m = fVar3;
        RelativeLayout.LayoutParams u = fVar3.u(this.f8770h);
        u.addRule(15);
        addView(this.f8770h, u);
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.f8773k = linearLayout;
        linearLayout.setGravity(21);
        this.f8773k.setOrientation(0);
        f fVar4 = new f(-2, -1);
        this.f8776n = fVar4;
        fVar4.K(new Rect(0, 0, 29, 0));
        RelativeLayout.LayoutParams u2 = this.f8776n.u(this.f8773k);
        u2.addRule(11);
        addView(this.f8773k, u2);
        CaTitleRightView caTitleRightView = new CaTitleRightView(getContext());
        this.f8772j = caTitleRightView;
        caTitleRightView.setMaxLines(1);
        this.f8772j.setGravity(8388629);
        f fVar5 = new f(160, -1);
        this.o = fVar5;
        fVar5.K(new Rect(0, 0, 20, 0));
        LinearLayout linearLayout2 = this.f8773k;
        CaTitleRightView caTitleRightView2 = this.f8772j;
        linearLayout2.addView(caTitleRightView2, this.o.i(caTitleRightView2));
    }

    private void c() {
        this.f8772j.setTextSize(0, d.d(24));
        this.f8770h.setTextSize(0, d.d(32));
        this.f8770h.setMaxWidth(d.d(R2.attr.actionbar_icon_dark_back));
        f.c(this.f8771i, this.f8774l);
        f.c(this.f8770h, this.f8775m);
        f.c(this.f8773k, this.f8776n);
        f.c(this.f8772j, this.o);
        com.jingdong.app.mall.home.n.a b2 = this.f8769g.b();
        if (com.jingdong.app.mall.home.state.dark.a.h() && b2.checkDarkTitle()) {
            this.f8770h.setTextColor(-1250068);
        } else {
            this.f8770h.setTextGradient(GradientTextView.GradientType.LeftToRight, this.f8769g.d());
        }
        this.f8772j.setTextColor(this.f8769g.f());
        String decorateBgUrl = this.f8769g.getDecorateBgUrl();
        if (TextUtils.isEmpty(decorateBgUrl)) {
            this.p.setVisibility(8);
        } else {
            this.p.setVisibility(0);
            com.jingdong.app.mall.home.n.h.e.h(this.p, this.f8769g.a());
            com.jingdong.app.mall.home.floor.ctrl.e.f(decorateBgUrl, this.p, com.jingdong.app.mall.home.floor.ctrl.e.f9402h);
        }
        JumpEntity j2 = this.f8769g.j();
        setOnClickListener(new a(j2));
        ViewGroup.LayoutParams layoutParams = this.f8771i.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        ViewGroup.LayoutParams layoutParams3 = this.f8770h.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams3;
        String g2 = this.f8769g.g();
        g2.hashCode();
        char c2 = '\uffff';
        switch (g2.hashCode()) {
            case 49:
                if (g2.equals("1")) {
                    c2 = 0;
                    break;
                }
                break;
            case 50:
                if (g2.equals("2")) {
                    c2 = 1;
                    break;
                }
                break;
            case 51:
                if (g2.equals("3")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                c.l(true, this.f8771i);
                layoutParams4.leftMargin = d.d(24);
                break;
            case 1:
                c.l(false, this.f8771i);
                layoutParams2.width = d.d(162);
                layoutParams2.height = d.d(32);
                layoutParams2.leftMargin = 0;
                layoutParams2.topMargin = (this.f8769g.h() >> 1) - d.d(16);
                layoutParams2.addRule(15, 0);
                layoutParams4.leftMargin = d.d(34);
                com.jingdong.app.mall.home.floor.ctrl.e.m(this.f8771i, this.f8769g.c(), com.jingdong.app.mall.home.floor.ctrl.e.w());
                break;
            case 2:
                c.l(false, this.f8771i);
                layoutParams4.leftMargin = d.d(72);
                layoutParams4.topMargin = 0;
                layoutParams2.width = d.d(36);
                layoutParams2.height = d.d(36);
                layoutParams2.addRule(15);
                com.jingdong.app.mall.home.floor.ctrl.e.m(this.f8771i, this.f8769g.c(), com.jingdong.app.mall.home.floor.ctrl.e.w());
                break;
        }
        this.f8771i.setLayoutParams(layoutParams2);
        this.f8770h.setLayoutParams(layoutParams4);
        this.f8770h.setText(this.f8769g.l());
        if (TextUtils.isEmpty(this.f8769g.k())) {
            this.f8773k.setVisibility(4);
        } else {
            this.f8773k.setVisibility(0);
            this.f8772j.setText(this.f8769g.k());
            this.f8773k.setOnClickListener(new b(j2));
        }
        requestLayout();
    }

    public void a(e eVar, int i2) {
        if (eVar == null) {
            setVisibility(4);
            return;
        }
        setVisibility(0);
        this.f8769g = eVar;
        c();
    }

    public CaTitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public CaTitleView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        b();
    }
}
