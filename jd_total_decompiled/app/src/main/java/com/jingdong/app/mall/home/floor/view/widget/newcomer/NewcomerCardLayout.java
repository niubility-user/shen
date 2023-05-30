package com.jingdong.app.mall.home.floor.view.widget.newcomer;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.NewcomerFloorEntity;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class NewcomerCardLayout extends NewcomerBaseItem {

    /* renamed from: g  reason: collision with root package name */
    private Context f10272g;

    /* renamed from: h  reason: collision with root package name */
    private NewcomerFloorEntity.NewcomerCardModel f10273h;

    /* renamed from: i  reason: collision with root package name */
    private SimpleDraweeView f10274i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f10275j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f10276k;

    /* renamed from: l  reason: collision with root package name */
    private GradientTextView f10277l;

    /* renamed from: m  reason: collision with root package name */
    private NewcomerCardCoupon f10278m;

    /* renamed from: n  reason: collision with root package name */
    private NewcomerCardCoupon f10279n;

    /* loaded from: classes4.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NewcomerFloorEntity.onItemClick(NewcomerCardLayout.this.f10272g, NewcomerCardLayout.this.f10273h);
        }
    }

    public NewcomerCardLayout(Context context) {
        super(context);
        this.f10272g = context;
    }

    private void d() {
        f fVar = new f(-1, -1);
        SimpleDraweeView simpleDraweeView = this.f10274i;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.f10272g);
            this.f10274i = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u = fVar.u(this.f10274i);
            u.addRule(13);
            addView(this.f10274i, u);
        } else {
            f.c(simpleDraweeView, fVar);
        }
        e.m(this.f10274i, this.f10273h.img, e.f9398c);
    }

    private void f() {
        if (this.f10273h.moduleSize != 3) {
            return;
        }
        f fVar = new f(R2.anim.pickerview_dialog_scale_in, -2);
        fVar.F(new Rect(0, 4, 0, 0));
        TextView textView = this.f10275j;
        if (textView == null) {
            g gVar = new g(this.f10272g, true);
            gVar.f(1);
            gVar.d(17);
            GradientTextView b = gVar.b();
            this.f10275j = b;
            addView(this.f10275j, fVar.u(b));
        } else {
            f.d(textView, fVar, true);
        }
        this.f10275j.setTypeface(FontsUtil.getTypeFace(getContext(), 4097));
        this.f10275j.setTextColor(this.f10273h.couponTextColor);
        g.o(this.f10275j, 56);
        this.f10275j.setText(com.jingdong.app.mall.home.o.a.f.I(this.f10273h.cardInfo, 0.5f));
        f fVar2 = new f(R2.anim.pickerview_dialog_scale_in, -2);
        fVar2.F(new Rect(0, 66, 0, 0));
        TextView textView2 = this.f10276k;
        if (textView2 == null) {
            g gVar2 = new g(this.f10272g, true);
            gVar2.f(1);
            gVar2.d(17);
            GradientTextView b2 = gVar2.b();
            this.f10276k = b2;
            addView(this.f10276k, fVar2.u(b2));
        } else {
            f.d(textView2, fVar2, true);
        }
        this.f10276k.setTextColor(this.f10273h.couponTextColor);
        g.o(this.f10276k, 22);
        TextView textView3 = this.f10276k;
        textView3.setText(com.jingdong.app.mall.home.o.a.f.l(textView3, d.d(R2.anim.pickerview_dialog_scale_in), this.f10273h.cardBenefit));
        f fVar3 = new f(134, 34);
        fVar3.F(new Rect(12, 116, 0, 0));
        GradientTextView gradientTextView = this.f10277l;
        if (gradientTextView == null) {
            g gVar3 = new g(this.f10272g, true);
            gVar3.f(1);
            gVar3.d(17);
            GradientTextView b3 = gVar3.b();
            this.f10277l = b3;
            addView(this.f10277l, fVar3.u(b3));
        } else {
            f.d(gradientTextView, fVar3, true);
        }
        this.f10277l.setTextGradient(GradientTextView.GradientType.LeftToRight, this.f10273h.priceColor);
        this.f10277l.setMaxWidth(d.d(134));
        g.o(this.f10277l, 22);
        g.k(this.f10277l, true);
        GradientTextView gradientTextView2 = this.f10277l;
        gradientTextView2.setText(com.jingdong.app.mall.home.o.a.f.l(gradientTextView2, d.d(134), this.f10273h.cardPrice));
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.newcomer.NewcomerBaseItem
    public void a(NewcomerFloorEntity.NewcomerBaseModel newcomerBaseModel) {
        if (newcomerBaseModel == null) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.n(newcomerBaseModel);
        this.f10273h = (NewcomerFloorEntity.NewcomerCardModel) newcomerBaseModel;
        d();
        f();
        e();
        setOnClickListener(new a());
    }

    public void e() {
        f fVar = new f(this.f10273h.moduleSize == 3 ? 150 : 122, R2.anim.lib_cashier_sdk_pop_out_animation_bottom);
        fVar.F(new Rect(this.f10273h.moduleSize == 3 ? R2.anim.slide_down : 66, 0, 0, 0));
        NewcomerCardCoupon newcomerCardCoupon = this.f10278m;
        if (newcomerCardCoupon == null) {
            NewcomerCardCoupon newcomerCardCoupon2 = new NewcomerCardCoupon(this.f10272g);
            this.f10278m = newcomerCardCoupon2;
            RelativeLayout.LayoutParams u = fVar.u(newcomerCardCoupon2);
            u.addRule(15);
            addView(this.f10278m, u);
        } else {
            f.c(newcomerCardCoupon, fVar);
        }
        this.f10278m.a(this.f10273h, 1);
        f fVar2 = new f(this.f10273h.moduleSize != 3 ? 122 : 150, R2.anim.lib_cashier_sdk_pop_out_animation_bottom);
        fVar2.F(new Rect(this.f10273h.moduleSize == 3 ? R2.attr.actionTextColorAlpha : 200, 0, 0, 0));
        NewcomerCardCoupon newcomerCardCoupon3 = this.f10279n;
        if (newcomerCardCoupon3 == null) {
            NewcomerCardCoupon newcomerCardCoupon4 = new NewcomerCardCoupon(this.f10272g);
            this.f10279n = newcomerCardCoupon4;
            RelativeLayout.LayoutParams u2 = fVar2.u(newcomerCardCoupon4);
            u2.addRule(15);
            addView(this.f10279n, u2);
        } else {
            f.c(newcomerCardCoupon3, fVar2);
        }
        this.f10279n.a(this.f10273h, 2);
    }
}
