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
public class NewcomerCardItem extends NewcomerBaseItem {

    /* renamed from: g  reason: collision with root package name */
    private Context f10263g;

    /* renamed from: h  reason: collision with root package name */
    private NewcomerFloorEntity.NewcomerCardModel f10264h;

    /* renamed from: i  reason: collision with root package name */
    private SimpleDraweeView f10265i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f10266j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f10267k;

    /* renamed from: l  reason: collision with root package name */
    private GradientTextView f10268l;

    /* renamed from: m  reason: collision with root package name */
    private final f f10269m;

    /* renamed from: n  reason: collision with root package name */
    private final f f10270n;
    private final f o;
    private final f p;

    /* loaded from: classes4.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NewcomerFloorEntity.onItemClick(NewcomerCardItem.this.f10263g, NewcomerCardItem.this.f10264h);
        }
    }

    public NewcomerCardItem(Context context) {
        super(context);
        this.f10269m = new f(-1, -1);
        this.f10270n = new f(-2, -2);
        this.o = new f(-2, -2);
        this.p = new f(-2, 34);
        this.f10263g = context;
    }

    private void d() {
        SimpleDraweeView simpleDraweeView = this.f10265i;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.f10263g);
            this.f10265i = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u = this.f10269m.u(this.f10265i);
            u.addRule(13);
            addView(this.f10265i, u);
        } else {
            f.c(simpleDraweeView, this.f10269m);
        }
        e.m(this.f10265i, this.f10264h.img, e.f9398c);
    }

    private void e() {
        TextView textView = this.f10266j;
        if (textView == null) {
            g gVar = new g(this.f10263g, true);
            gVar.f(1);
            gVar.d(16);
            GradientTextView b = gVar.b();
            this.f10266j = b;
            RelativeLayout.LayoutParams u = this.f10270n.u(b);
            u.addRule(14);
            addView(this.f10266j, u);
        } else {
            f.d(textView, this.f10270n, true);
        }
        this.f10266j.setTypeface(FontsUtil.getTypeFace(getContext(), 4097));
        this.f10266j.setTextColor(this.f10264h.selectedTextColor);
        g.o(this.f10266j, 60);
        this.f10266j.setText(com.jingdong.app.mall.home.o.a.f.I(this.f10264h.getCouponInfo(1), 0.5f));
        this.o.F(new Rect(0, 66, 0, 0));
        TextView textView2 = this.f10267k;
        if (textView2 == null) {
            g gVar2 = new g(this.f10263g, true);
            gVar2.f(1);
            gVar2.d(16);
            GradientTextView b2 = gVar2.b();
            this.f10267k = b2;
            RelativeLayout.LayoutParams u2 = this.o.u(b2);
            u2.addRule(14);
            addView(this.f10267k, u2);
        } else {
            f.d(textView2, this.o, true);
        }
        this.f10267k.setTextColor(this.f10264h.selectedTextColor);
        g.o(this.f10267k, 22);
        TextView textView3 = this.f10267k;
        textView3.setText(com.jingdong.app.mall.home.o.a.f.l(textView3, d.d(R2.anim.pickerview_dialog_scale_in), this.f10264h.getCouponBenefit(1)));
        this.p.F(new Rect(0, 116, 0, 0));
        GradientTextView gradientTextView = this.f10268l;
        if (gradientTextView == null) {
            g gVar3 = new g(this.f10263g, true);
            gVar3.f(1);
            gVar3.d(16);
            GradientTextView b3 = gVar3.b();
            this.f10268l = b3;
            RelativeLayout.LayoutParams u3 = this.p.u(b3);
            u3.addRule(14);
            addView(this.f10268l, u3);
        } else {
            f.d(gradientTextView, this.p, true);
        }
        this.f10268l.setTextGradient(GradientTextView.GradientType.LeftToRight, this.f10264h.btnColor);
        this.f10268l.setMaxWidth(d.d(134));
        g.o(this.f10268l, 22);
        g.k(this.f10268l, true);
        GradientTextView gradientTextView2 = this.f10268l;
        gradientTextView2.setText(com.jingdong.app.mall.home.o.a.f.l(gradientTextView2, d.d(134), this.f10264h.getCardBtnText(1)));
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.newcomer.NewcomerBaseItem
    public void a(NewcomerFloorEntity.NewcomerBaseModel newcomerBaseModel) {
        if (newcomerBaseModel == null) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.n(newcomerBaseModel);
        this.f10264h = (NewcomerFloorEntity.NewcomerCardModel) newcomerBaseModel;
        d();
        e();
        setOnClickListener(new a());
    }
}
