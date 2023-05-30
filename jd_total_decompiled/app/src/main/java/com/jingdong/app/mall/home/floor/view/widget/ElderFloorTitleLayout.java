package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;

/* loaded from: classes4.dex */
public class ElderFloorTitleLayout extends RelativeLayout {

    /* renamed from: g */
    private GradientTextView f10043g;

    /* renamed from: h */
    private SimpleDraweeView f10044h;

    /* renamed from: i */
    private ElderLabelLayout f10045i;

    /* renamed from: j */
    private int f10046j;

    /* renamed from: k */
    private f f10047k;

    /* renamed from: l */
    private f f10048l;

    /* renamed from: m */
    private f f10049m;

    /* loaded from: classes4.dex */
    public class a extends JDSimpleImageLoadingListener {
        a() {
            ElderFloorTitleLayout.this = r1;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
            ElderFloorTitleLayout.this.f10043g.setVisibility(0);
            ElderFloorTitleLayout.this.f10043g.setMaxWidth(d.f9279g);
            ElderFloorTitleLayout.this.f10043g.setMinWidth(0);
            ElderFloorTitleLayout.this.f10044h.setVisibility(8);
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            ElderFloorTitleLayout.this.f10043g.setVisibility(4);
            ElderFloorTitleLayout.this.f10043g.setMaxWidth(ElderFloorTitleLayout.this.f10047k.v());
            ElderFloorTitleLayout.this.f10043g.setMinWidth(ElderFloorTitleLayout.this.f10047k.v());
            ElderFloorTitleLayout.this.f10044h.setVisibility(0);
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            ElderFloorTitleLayout.this.f10043g.setVisibility(0);
            ElderFloorTitleLayout.this.f10043g.setMaxWidth(d.f9279g);
            ElderFloorTitleLayout.this.f10043g.setMinWidth(0);
            ElderFloorTitleLayout.this.f10044h.setVisibility(8);
        }
    }

    public ElderFloorTitleLayout(Context context, int i2) {
        super(context);
        this.f10048l = new f(-2, -2);
        this.f10049m = new f(-2, 37);
        this.f10046j = i2;
    }

    private void e(com.jingdong.app.mall.home.r.e.f fVar) {
        GradientTextView gradientTextView = this.f10043g;
        if (gradientTextView == null) {
            g gVar = new g(getContext(), true);
            gVar.e(5);
            gVar.f(1);
            gVar.l(-13421773);
            GradientTextView b = gVar.b();
            this.f10043g = b;
            g.o(b, 36);
            this.f10043g.setIncludeFontPadding(false);
            this.f10043g.getPaint().setFakeBoldText(true);
            this.f10043g.setId(R.id.homefloor_child_item1);
            this.f10048l.F(new Rect(24, 0, 0, 0));
            RelativeLayout.LayoutParams u = this.f10048l.u(this.f10043g);
            u.addRule(9);
            u.addRule(15);
            addView(this.f10043g, u);
        } else {
            f.c(gradientTextView, this.f10048l);
        }
        this.f10043g.setTextGradient(GradientTextView.GradientType.LeftToRight, m.p(fVar.C(), -13421773, true));
        this.f10043g.setText(com.jingdong.app.mall.home.o.a.f.j(5, fVar.O()));
        setContentDescription(fVar.O());
    }

    private void f(com.jingdong.app.mall.home.r.e.f fVar) {
        this.f10043g.setVisibility(0);
        this.f10043g.setMaxWidth(d.f9279g);
        this.f10043g.setMinWidth(0);
        String Q = fVar == null ? "" : fVar.Q();
        h hVar = fVar == null ? null : fVar.a;
        boolean z = hVar != null && "1".equals(hVar.getJsonString("fontShape"));
        if (fVar != null && !TextUtils.isEmpty(Q) && z) {
            int min = Math.min(Math.max(64, com.jingdong.app.mall.home.n.h.c.h(fVar.getJsonString("showNameImgWidth"), 0)), 152);
            if (this.f10044h == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.f10044h = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                f fVar2 = new f(min, 36);
                this.f10047k = fVar2;
                fVar2.F(new Rect(24, 0, 0, 0));
                RelativeLayout.LayoutParams u = this.f10047k.u(this.f10044h);
                u.addRule(15);
                addView(this.f10044h, u);
            } else {
                this.f10047k.Q(min);
                f.c(this.f10044h, this.f10047k);
            }
            this.f10044h.setVisibility(0);
            e.j(Q, this.f10044h, e.f9402h, false, new a(), null);
            return;
        }
        SimpleDraweeView simpleDraweeView = this.f10044h;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(8);
        }
    }

    private void g(com.jingdong.app.mall.home.r.e.f fVar) {
        if (fVar != null && !TextUtils.isEmpty(fVar.W())) {
            ElderLabelLayout elderLabelLayout = this.f10045i;
            if (elderLabelLayout == null) {
                ElderLabelLayout elderLabelLayout2 = new ElderLabelLayout(getContext());
                this.f10045i = elderLabelLayout2;
                elderLabelLayout2.g(this.f10046j);
                this.f10049m.F(new Rect(12, 0, 0, 0));
                RelativeLayout.LayoutParams u = this.f10049m.u(this.f10045i);
                u.addRule(1, this.f10043g.getId());
                u.addRule(15);
                addView(this.f10045i, u);
            } else {
                f.c(elderLabelLayout, this.f10049m);
            }
            this.f10045i.setVisibility(0);
            this.f10045i.b(fVar);
            return;
        }
        ElderLabelLayout elderLabelLayout3 = this.f10045i;
        if (elderLabelLayout3 != null) {
            elderLabelLayout3.setVisibility(8);
        }
    }

    public void d(com.jingdong.app.mall.home.r.e.f fVar) {
        if (fVar == null) {
            return;
        }
        e(fVar);
        f(fVar);
        g(fVar);
    }
}
