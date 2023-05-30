package com.jingdong.app.mall.home.category.floor.floorsub;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.n.g.k;
import com.jingdong.app.mall.home.n.g.x.g;
import com.jingdong.app.mall.home.n.h.b;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.sdk.platform.business.personal.R2;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class CaHotSaleSubFloor extends BaseCaRecycleItem<g> {
    private static final Drawable O = e.w();
    private static final JDDisplayImageOptions P;
    private f A;
    private GradientTextView B;
    private f C;
    private TextView D;
    private f E;
    private TextView F;
    private f G;
    private SimpleDraweeView H;
    private f I;
    private TextView J;
    private f K;
    private SimpleDraweeView L;
    private f M;
    private boolean N;
    private com.jingdong.app.mall.home.category.widget.a q;
    private View r;
    private f s;
    private SimpleDraweeView t;
    private f u;
    private SimpleDraweeView v;
    private f w;
    private TextView x;
    private f y;
    private RelativeLayout z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ g f8712g;

        a(g gVar) {
            this.f8712g = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JumpEntity d = this.f8712g.d();
            if (d == null) {
                return;
            }
            b.a(CaHotSaleSubFloor.this.getContext(), d);
            this.f8712g.k().C("Category_Main_Hot_ProductPrice", this.f8712g.e());
        }
    }

    static {
        JDDisplayImageOptions resetViewBeforeLoading = com.jingdong.app.mall.home.floor.ctrl.f.a().resetViewBeforeLoading(false);
        int i2 = R.drawable.home_webp_empty;
        P = resetViewBeforeLoading.showImageOnFail(i2).showImageOnLoading(i2).showImageForEmptyUri(i2);
    }

    public CaHotSaleSubFloor(Context context, com.jingdong.app.mall.home.n.b bVar) {
        super(context);
        this.N = bVar == com.jingdong.app.mall.home.n.b.S_HOT_SALE;
        this.r = new View(context);
        f fVar = new f(268, -1);
        this.s = fVar;
        RelativeLayout.LayoutParams u = fVar.u(this.r);
        u.addRule(14);
        addView(this.r, u);
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.t = homeDraweeView;
        homeDraweeView.setId(R.id.mallfloor_item1);
        f fVar2 = new f(244, 244);
        this.u = fVar2;
        fVar2.F(new Rect(0, 12, 0, 0));
        RelativeLayout.LayoutParams u2 = this.u.u(this.t);
        u2.addRule(14);
        addView(this.t, u2);
        this.v = new HomeDraweeView(context);
        f fVar3 = new f(46, 46);
        this.w = fVar3;
        fVar3.F(new Rect(0, 0, 12, 0));
        RelativeLayout.LayoutParams u3 = this.w.u(this.v);
        u3.addRule(7, this.t.getId());
        addView(this.v, u3);
        com.jingdong.app.mall.home.floor.common.g gVar = new com.jingdong.app.mall.home.floor.common.g(context, false);
        gVar.h();
        gVar.c(true);
        gVar.l(-14277082);
        gVar.d(16);
        gVar.m(26);
        gVar.j(true);
        this.x = gVar.a();
        f fVar4 = new f(244, 52);
        this.y = fVar4;
        RelativeLayout.LayoutParams u4 = fVar4.u(this.x);
        u4.addRule(3, this.t.getId());
        u4.addRule(14);
        addView(this.x, u4);
        q(context);
    }

    private void o(@NotNull g gVar) {
        boolean z = gVar.g() instanceof k ? !((k) r0).Q() : true;
        f.c(this.r, this.s);
        this.A.C(z ? 98 : R2.anim.pickerview_dialog_scale_in);
        f.c(this.z, this.A);
        this.q.c(gVar.y());
        this.q.b(z);
        c.k(z, this.D, this.F, this.H);
        if (TextUtils.isEmpty(gVar.A())) {
            e.a(this.H, "2630");
        } else {
            e.f(gVar.A(), this.H, P);
        }
        e.m(this.L, gVar.v(), this.q);
        this.B.setText(gVar.B());
        int[] C = gVar.C();
        this.B.setTextGradient(GradientTextView.GradientType.LeftToRight, C);
        this.B.getPaint().setFakeBoldText(C.length > 1);
        this.D.setText(gVar.x());
        this.F.setText(gVar.x());
        this.J.setText(gVar.u());
        this.J.setTextColor(gVar.z());
        this.J.setOnClickListener(new a(gVar));
    }

    private void q(Context context) {
        Rect rect = new Rect(12, 0, 12, 0);
        this.z = new RelativeLayout(context);
        f fVar = new f(268, R2.anim.pickerview_dialog_scale_in);
        this.A = fVar;
        RelativeLayout.LayoutParams u = fVar.u(this.z);
        u.addRule(14);
        u.addRule(12);
        addView(this.z, u);
        com.jingdong.app.mall.home.floor.common.g gVar = new com.jingdong.app.mall.home.floor.common.g(context, true);
        gVar.h();
        gVar.c(true);
        gVar.e(10);
        gVar.c(true);
        gVar.m(20);
        gVar.d(16);
        this.B = gVar.b();
        f fVar2 = new f(-2, 32);
        this.C = fVar2;
        fVar2.F(rect);
        this.z.addView(this.B, this.C.u(this.B));
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.H = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar3 = new f(220, 46);
        this.I = fVar3;
        fVar3.F(new Rect(0, 42, 0, 0));
        RelativeLayout.LayoutParams u2 = this.I.u(this.H);
        u2.addRule(14);
        this.z.addView(this.H, u2);
        com.jingdong.app.mall.home.floor.common.g gVar2 = new com.jingdong.app.mall.home.floor.common.g(context, true);
        gVar2.h();
        gVar2.c(true);
        gVar2.d(17);
        gVar2.m(18);
        gVar2.l(-8355712);
        this.D = gVar2.a();
        f fVar4 = new f(120, 160);
        this.E = fVar4;
        fVar4.K(rect);
        RelativeLayout.LayoutParams u3 = this.E.u(this.D);
        u3.addRule(12);
        this.z.addView(this.D, u3);
        com.jingdong.app.mall.home.floor.common.g gVar3 = new com.jingdong.app.mall.home.floor.common.g(context, true);
        gVar3.h();
        gVar3.c(true);
        gVar3.m(18);
        gVar3.d(17);
        gVar3.l(-8355712);
        this.F = gVar3.a();
        f fVar5 = new f(120, 160);
        this.G = fVar5;
        fVar5.K(rect);
        RelativeLayout.LayoutParams u4 = this.G.u(this.F);
        u4.addRule(11);
        u4.addRule(12);
        this.z.addView(this.F, u4);
        HomeDraweeView homeDraweeView2 = new HomeDraweeView(context);
        this.L = homeDraweeView2;
        homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        this.q = new com.jingdong.app.mall.home.category.widget.a(new int[]{-49073, -381927}, 6, 212, 42);
        f fVar6 = new f(212, 42);
        this.M = fVar6;
        fVar6.E(0, 0, 0, 20);
        RelativeLayout.LayoutParams u5 = this.M.u(this.L);
        u5.addRule(14);
        u5.addRule(12);
        this.z.addView(this.L, u5);
        com.jingdong.app.mall.home.floor.common.g gVar4 = new com.jingdong.app.mall.home.floor.common.g(context, true);
        gVar4.h();
        gVar4.c(true);
        gVar4.m(30);
        gVar4.p(context);
        gVar4.l(-1);
        gVar4.d(17);
        this.J = gVar4.b();
        f fVar7 = new f(212, 36);
        this.K = fVar7;
        fVar7.K(new Rect(8, 0, 8, 0));
        this.K.E(0, 0, 0, 20);
        RelativeLayout.LayoutParams u6 = this.K.u(this.J);
        u6.addRule(14);
        u6.addRule(12);
        this.z.addView(this.J, u6);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public void e(@NotNull g gVar) {
        e.m(this.t, gVar.i(), O);
        e.r(this.v, gVar.w());
        this.x.setText(gVar.D());
        if (this.N) {
            o(gVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: p  reason: merged with bridge method [inline-methods] */
    public void f(g gVar) {
        super.f(gVar);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setCornerRadius(d.d(12));
        this.r.setBackgroundDrawable(gradientDrawable);
        com.jingdong.app.mall.home.n.h.e.d(this.t, d.d(12));
        com.jingdong.app.mall.home.floor.common.g.o(this.x, 28);
        com.jingdong.app.mall.home.floor.common.g.o(this.B, 24);
        com.jingdong.app.mall.home.floor.common.g.o(this.J, 30);
        com.jingdong.app.mall.home.floor.common.g.o(this.D, 20);
        com.jingdong.app.mall.home.floor.common.g.o(this.F, 20);
        f.c(this.r, this.s);
        f.c(this.v, this.w);
        f.c(this.t, this.u);
        f.c(this.x, this.y);
        f.c(this.B, this.C);
        f.c(this.D, this.E);
        f.c(this.F, this.G);
        f.c(this.H, this.I);
        f.c(this.J, this.K);
        f.c(this.L, this.M);
    }
}
