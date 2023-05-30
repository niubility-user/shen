package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.b.g.f;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes4.dex */
public class Line1To4Layout extends BaseLineLayout<f> {
    private SimpleDraweeView s;
    private SimpleDraweeView t;
    private GradientTextView u;
    private GradientTextView v;
    private f w;
    private PriceContent x;

    /* loaded from: classes4.dex */
    public static class PriceContent extends LinearLayout {
        private f bindModel;
        private f.a itemSizeInfo;
        private GradientTextView tvPrice;
        private GradientTextView tvTag;

        public PriceContent(Context context) {
            super(context);
        }

        private void bindJdPrice() {
            com.jingdong.app.mall.home.floor.common.f fVar = this.itemSizeInfo.f9783g;
            if (Line1To4Layout.F(fVar, this.tvPrice)) {
                GradientTextView gradientTextView = this.tvPrice;
                if (gradientTextView == null) {
                    GradientTextView gradientTextView2 = new GradientTextView(getContext());
                    this.tvPrice = gradientTextView2;
                    gradientTextView2.setMaxLines(1);
                    this.tvPrice.setGravity(17);
                    this.tvPrice.setEllipsize(TextUtils.TruncateAt.END);
                    GradientTextView gradientTextView3 = this.tvPrice;
                    addView(gradientTextView3, fVar.i(gradientTextView3));
                } else {
                    com.jingdong.app.mall.home.floor.common.f.d(gradientTextView, fVar, true);
                }
                String P = this.bindModel.P();
                if (!TextUtils.isEmpty(P)) {
                    this.tvPrice.setVisibility(0);
                    this.tvPrice.setTextColor(m.i(this.bindModel.Q(), -14277082));
                    this.tvPrice.setMaxWidth(this.itemSizeInfo.f9785i);
                    this.tvPrice.setTypeface(FontsUtil.getTypeFace(getContext()));
                    this.tvPrice.setText(com.jingdong.app.mall.home.o.a.f.H(P));
                    com.jingdong.app.mall.home.o.a.f.I0(this.tvPrice, 32);
                    return;
                }
                this.tvPrice.setVisibility(8);
            }
        }

        private void bindPriceTag() {
            com.jingdong.app.mall.home.floor.common.f fVar = this.itemSizeInfo.f9784h;
            if (Line1To4Layout.F(fVar, this.tvTag)) {
                GradientTextView gradientTextView = this.tvTag;
                if (gradientTextView == null) {
                    GradientTextView gradientTextView2 = new GradientTextView(getContext());
                    this.tvTag = gradientTextView2;
                    gradientTextView2.setMaxLines(1);
                    this.tvTag.setGravity(17);
                    addView(this.tvTag, fVar.i(this.tvTag));
                } else {
                    gradientTextView.setVisibility(0);
                    e.d(this.tvTag, d.d(2));
                    com.jingdong.app.mall.home.floor.common.f.d(this.tvTag, fVar, true);
                }
                String W = this.bindModel.W();
                if (!TextUtils.isEmpty(W)) {
                    this.tvTag.setText(W);
                    this.tvTag.setBackgroundColor(-2832);
                    this.tvTag.setTextColor(-381927);
                    this.tvTag.setVisibility(0);
                    com.jingdong.app.mall.home.o.a.f.I0(this.tvTag, 18);
                    return;
                }
                this.tvTag.setVisibility(8);
            }
        }

        public void bind(f fVar) {
            this.bindModel = fVar;
            this.itemSizeInfo = fVar.O();
            e.a(this, d.d(12));
            setBackgroundColor(fVar.a0() ? -1 : 0);
            bindJdPrice();
            bindPriceTag();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ f f9895g;

        a(f fVar) {
            this.f9895g = fVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f9895g.e0(((BaseLineLayout) Line1To4Layout.this).o);
        }
    }

    public Line1To4Layout(Context context, com.jingdong.app.mall.home.floor.view.b.a aVar) {
        super(context, aVar);
    }

    private void A(com.jingdong.app.mall.home.floor.common.f fVar, boolean z) {
        if (!F(fVar, this.x) || z) {
            r(true, this.x);
            return;
        }
        PriceContent priceContent = this.x;
        if (priceContent == null) {
            PriceContent priceContent2 = new PriceContent(this.o);
            this.x = priceContent2;
            priceContent2.setOrientation(0);
            this.x.setGravity(17);
            RelativeLayout.LayoutParams u = fVar.u(this.x);
            u.addRule(14);
            addView(this.x, 0, u);
        } else {
            com.jingdong.app.mall.home.floor.common.f.d(priceContent, fVar, true);
        }
        this.x.setVisibility(0);
        this.x.bind(this.w);
    }

    private void B(com.jingdong.app.mall.home.floor.common.f fVar) {
        if (F(fVar, this)) {
            setVisibility(0);
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = fVar.v();
            layoutParams.height = fVar.h();
        }
    }

    private void C(com.jingdong.app.mall.home.floor.common.f fVar, boolean z) {
        if (!F(fVar, this.t) || z) {
            r(true, this.t);
            return;
        }
        SimpleDraweeView simpleDraweeView = this.t;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.o);
            this.t = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u = fVar.u(this.t);
            u.addRule(14);
            addView(this.t, u);
        } else {
            com.jingdong.app.mall.home.floor.common.f.d(simpleDraweeView, fVar, true);
        }
        if (this.w.Z()) {
            this.t.setBackgroundColor(-1);
        }
        e.d(this.t, d.d(12));
        String T = this.w.T();
        this.t.setVisibility(0);
        com.jingdong.app.mall.home.floor.ctrl.e.m(this.t, T, com.jingdong.app.mall.home.floor.ctrl.e.f9398c);
    }

    private void D(com.jingdong.app.mall.home.floor.common.f fVar, boolean z) {
        String R = this.w.R();
        if (!F(fVar, this.u) || z || TextUtils.isEmpty(R)) {
            r(true, this.u);
            return;
        }
        GradientTextView gradientTextView = this.u;
        if (gradientTextView == null) {
            GradientTextView gradientTextView2 = new GradientTextView(this.o);
            this.u = gradientTextView2;
            gradientTextView2.setMaxLines(1);
            this.u.setGravity(17);
            this.u.setEllipsize(TextUtils.TruncateAt.END);
            RelativeLayout.LayoutParams u = fVar.u(this.u);
            u.addRule(14);
            addView(this.u, u);
        } else {
            com.jingdong.app.mall.home.floor.common.f.c(gradientTextView, fVar);
        }
        this.u.setTextGradient(GradientTextView.GradientType.LeftBottomToRightTop, m.o(this.w.V(), -1));
        String S = this.w.S();
        int[] iArr = {-381927, -367591};
        if (!TextUtils.isEmpty(S)) {
            com.jingdong.app.mall.home.floor.view.b.h.a.h(m.p(S, iArr[0], false), iArr);
        }
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, com.jingdong.app.mall.home.floor.view.b.h.a.f(iArr, 204, 255));
        gradientDrawable.setStroke(1, -1426063361);
        gradientDrawable.setCornerRadius(fVar.h() >> 1);
        this.u.setBackgroundDrawable(gradientDrawable);
        g.o(this.u, this.w.O().f9786j);
        this.u.setText(R);
        this.u.setVisibility(0);
        this.u.setMaxWidth(d.d(this.w.O().f9787k));
    }

    private void E(com.jingdong.app.mall.home.floor.common.f fVar, boolean z) {
        if (!F(fVar, this.v) || z) {
            r(true, this.v);
            return;
        }
        GradientTextView gradientTextView = this.v;
        if (gradientTextView == null) {
            GradientTextView gradientTextView2 = new GradientTextView(this.o);
            this.v = gradientTextView2;
            gradientTextView2.setGravity(17);
            this.v.setMaxLines(1);
            this.v.setEllipsize(TextUtils.TruncateAt.END);
            addView(this.v, fVar.u(this.v));
        } else {
            gradientTextView.setVisibility(0);
            com.jingdong.app.mall.home.floor.common.f.d(this.v, fVar, true);
        }
        this.v.setTextGradient(GradientTextView.GradientType.LeftBottomToRightTop, m.o(this.w.V(), -8355712));
        this.v.setText(this.w.U());
        this.v.setTextSize(0, d.d(this.w.N()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean F(com.jingdong.app.mall.home.floor.common.f fVar, View view) {
        if (fVar == null && view != null) {
            view.setVisibility(8);
        }
        return fVar != null;
    }

    private void z(com.jingdong.app.mall.home.floor.common.f fVar, boolean z, String str) {
        if (!F(fVar, this.s) || !z) {
            r(true, this.s);
            return;
        }
        SimpleDraweeView simpleDraweeView = this.s;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.o);
            this.s = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u = fVar.u(this.s);
            u.addRule(14);
            addView(this.s, u);
        } else {
            com.jingdong.app.mall.home.floor.common.f.d(simpleDraweeView, fVar, true);
        }
        this.s.setVisibility(0);
        e.d(this.s, d.d(12));
        com.jingdong.app.mall.home.floor.ctrl.e.u(this.s, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout
    /* renamed from: G  reason: merged with bridge method [inline-methods] */
    public void v(@NonNull f fVar, int i2) {
        f.a O = fVar.O();
        this.w = fVar;
        setOnClickListener(new a(fVar));
        B(O.a);
        String e2 = this.w.e();
        boolean z = !TextUtils.isEmpty(e2);
        z(O.b, z, e2);
        C(O.f9780c, z);
        D(O.d, z);
        A(O.f9781e, z);
        E(O.f9782f, z);
    }
}
