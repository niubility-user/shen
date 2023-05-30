package com.jingdong.app.mall.home.floor.view.widget.newcomer;

import android.content.Context;
import android.graphics.Rect;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.model.entity.NewcomerFloorEntity;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes4.dex */
public class NewcomerCardCoupon extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private Context f10256g;

    /* renamed from: h  reason: collision with root package name */
    private TextView f10257h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f10258i;

    /* renamed from: j  reason: collision with root package name */
    private GradientTextView f10259j;

    /* renamed from: k  reason: collision with root package name */
    private final f f10260k;

    /* renamed from: l  reason: collision with root package name */
    private final f f10261l;

    /* renamed from: m  reason: collision with root package name */
    private final f f10262m;

    public NewcomerCardCoupon(Context context) {
        super(context);
        this.f10260k = new f(-2, -2);
        this.f10261l = new f(-2, -2);
        this.f10262m = new f(-2, 46);
        this.f10256g = context;
    }

    public void a(NewcomerFloorEntity.NewcomerCardModel newcomerCardModel, int i2) {
        this.f10260k.F(new Rect(0, -4, 0, 0));
        TextView textView = this.f10257h;
        if (textView == null) {
            g gVar = new g(this.f10256g, true);
            gVar.f(1);
            gVar.d(16);
            GradientTextView b = gVar.b();
            this.f10257h = b;
            RelativeLayout.LayoutParams u = this.f10260k.u(b);
            u.addRule(14);
            addView(this.f10257h, u);
        } else {
            f.d(textView, this.f10260k, true);
        }
        this.f10257h.setTypeface(FontsUtil.getTypeFace(getContext(), 4097));
        this.f10257h.setTextColor(newcomerCardModel.selectedTextColor);
        g.o(this.f10257h, 56);
        this.f10257h.setText(com.jingdong.app.mall.home.o.a.f.I(newcomerCardModel.getCouponInfo(i2), 0.54f));
        this.f10261l.F(new Rect(0, 56, 0, 0));
        TextView textView2 = this.f10258i;
        if (textView2 == null) {
            g gVar2 = new g(this.f10256g, true);
            gVar2.f(1);
            gVar2.d(16);
            GradientTextView b2 = gVar2.b();
            this.f10258i = b2;
            RelativeLayout.LayoutParams u2 = this.f10261l.u(b2);
            u2.addRule(14);
            addView(this.f10258i, u2);
        } else {
            f.d(textView2, this.f10261l, true);
        }
        this.f10258i.setTextColor(newcomerCardModel.selectedTextColor);
        g.o(this.f10258i, 22);
        TextView textView3 = this.f10258i;
        textView3.setText(com.jingdong.app.mall.home.o.a.f.l(textView3, d.d(newcomerCardModel.moduleSize == 3 ? 150 : 122), newcomerCardModel.getCouponBenefit(i2)));
        this.f10262m.F(new Rect(0, 92, 0, 0));
        GradientTextView gradientTextView = this.f10259j;
        if (gradientTextView == null) {
            g gVar3 = new g(this.f10256g, true);
            gVar3.f(1);
            gVar3.d(16);
            GradientTextView b3 = gVar3.b();
            this.f10259j = b3;
            RelativeLayout.LayoutParams u3 = this.f10262m.u(b3);
            u3.addRule(14);
            addView(this.f10259j, u3);
        } else {
            f.d(gradientTextView, this.f10262m, true);
        }
        this.f10259j.setTextGradient(GradientTextView.GradientType.LeftToRight, newcomerCardModel.btnColor);
        g.o(this.f10259j, 22);
        GradientTextView gradientTextView2 = this.f10259j;
        gradientTextView2.setText(com.jingdong.app.mall.home.o.a.f.l(gradientTextView2, d.d(newcomerCardModel.moduleSize != 3 ? 122 : 150), newcomerCardModel.getCardBtnText(i2)));
    }
}
