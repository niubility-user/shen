package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.b.c;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout;
import com.jingdong.app.mall.home.floor.view.linefloor.base.b;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;

/* loaded from: classes4.dex */
public class LineTitleLayout extends RelativeLayout {

    /* renamed from: g */
    private GradientTextView f9907g;

    /* renamed from: h */
    private SimpleDraweeView f9908h;

    /* renamed from: i */
    private f f9909i;

    /* renamed from: j */
    private LabelTitleLayout f9910j;

    /* renamed from: k */
    private f f9911k;

    /* loaded from: classes4.dex */
    public class a extends JDSimpleImageLoadingListener {

        /* renamed from: g */
        final /* synthetic */ b f9912g;

        a(b bVar) {
            LineTitleLayout.this = r1;
            this.f9912g = bVar;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            if (this.f9912g.C() && LineTitleLayout.this.f9908h.getVisibility() == 0) {
                LineTitleLayout.this.f9907g.setVisibility(4);
                LineTitleLayout.this.f9907g.setMaxWidth(LineTitleLayout.this.f9909i.v());
                LineTitleLayout.this.f9907g.setMinWidth(LineTitleLayout.this.f9909i.v());
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            LineTitleLayout.this.f9907g.setVisibility(0);
            LineTitleLayout.this.f9907g.setMaxWidth(d.f9279g);
            LineTitleLayout.this.f9907g.setMinWidth(0);
            LineTitleLayout.this.f9908h.setVisibility(8);
        }
    }

    public LineTitleLayout(Context context) {
        super(context);
        g gVar = new g(context, true);
        gVar.m(30);
        gVar.e(5);
        gVar.f(1);
        gVar.l(-15066598);
        GradientTextView b = gVar.b();
        this.f9907g = b;
        b.setId(R.id.homefloor_child_item1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        addView(this.f9907g, layoutParams);
    }

    private void e(b bVar, int i2, int i3) {
        String g0 = bVar.g0(i2);
        this.f9907g.setMaxWidth(d.f9279g);
        this.f9907g.setMinWidth(0);
        this.f9907g.setVisibility(0);
        if (!TextUtils.isEmpty(g0) && bVar.C() && i3 > 0) {
            if (this.f9908h == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.f9908h = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                f fVar = new f(i3, 32);
                this.f9909i = fVar;
                RelativeLayout.LayoutParams u = fVar.u(this.f9908h);
                u.addRule(15);
                addView(this.f9908h, u);
            } else {
                this.f9909i.Q(i3);
                f.c(this.f9908h, this.f9909i);
            }
            this.f9908h.setVisibility(0);
            e.j(g0, this.f9908h, e.f9402h, false, new a(bVar), null);
            return;
        }
        SimpleDraweeView simpleDraweeView = this.f9908h;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(8);
        }
    }

    private void f(String str, b bVar, BaseLineLayout baseLineLayout, int i2) {
        if (TextUtils.isEmpty(bVar.i0())) {
            LabelTitleLayout labelTitleLayout = this.f9910j;
            if (labelTitleLayout != null) {
                labelTitleLayout.setVisibility(8);
                return;
            }
            return;
        }
        boolean z = bVar.a == c.SPECIAL;
        int i3 = z ? 30 : 32;
        if (this.f9910j == null) {
            this.f9910j = new LabelTitleLayout(getContext(), z);
            f fVar = new f(-2, i3);
            this.f9911k = fVar;
            if (z) {
                fVar.E(4, 0, 0, 0);
                this.f9911k.J(16, 0, 6, 0);
            } else {
                fVar.E(12, 0, 0, 0);
            }
            RelativeLayout.LayoutParams u = this.f9911k.u(this.f9910j);
            u.addRule(1, this.f9907g.getId());
            u.addRule(15);
            addView(this.f9910j, u);
        } else {
            this.f9911k.C(i3);
            f.c(this.f9910j, this.f9911k);
        }
        this.f9910j.d(Math.max((int) this.f9907g.getPaint().measureText(str), i2), bVar, baseLineLayout);
    }

    public void d(b bVar, BaseLineLayout baseLineLayout, int i2) {
        int[] f0 = bVar.f0(i2);
        String j2 = com.jingdong.app.mall.home.o.a.f.j(bVar.q() == 2 ? 5 : 4, bVar.n0(i2));
        this.f9907g.setText(j2);
        this.f9907g.getPaint().setFakeBoldText(bVar.x(i2));
        this.f9907g.setTextGradient(GradientTextView.GradientType.LeftToRight, f0);
        g.o(this.f9907g, bVar.s(i2));
        int min = Math.min(Math.max(64, bVar.h0(i2)), 200);
        e(bVar, i2, min);
        if (i2 == 0 && bVar.k().getSubWeight() >= 2) {
            f(j2, bVar, baseLineLayout, d.d(min));
        }
        setContentDescription(j2 + bVar.c0(i2));
    }
}
