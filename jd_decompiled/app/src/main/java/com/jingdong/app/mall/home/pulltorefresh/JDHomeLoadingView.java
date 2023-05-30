package com.jingdong.app.mall.home.pulltorefresh;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.FrameLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.guide.PullXViewGuideLayout;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh;
import com.jingdong.app.mall.home.widget.HomePullRefreshRecyclerView;
import com.jingdong.app.mall.home.widget.HomeTopBgView;

/* loaded from: classes4.dex */
public class JDHomeLoadingView extends JDHomeBaseLoadingView {
    public JDHomeLoadingView(Context context, BaseVerticalRefresh.g gVar) {
        super(context, gVar);
    }

    private void W() {
        Bitmap c2;
        HomeTopBgView homeTopBgView = this.K;
        if (homeTopBgView == null || (c2 = homeTopBgView.c()) == null) {
            return;
        }
        N(c2);
    }

    @Override // com.jingdong.app.mall.home.pulltorefresh.JDHomeBaseLoadingView
    public void B() {
        super.B();
        W();
    }

    @Override // com.jingdong.app.mall.home.pulltorefresh.JDHomeBaseLoadingView
    protected void D() {
        FrameLayout r;
        HomePullRefreshRecyclerView h2 = com.jingdong.app.mall.home.a.h();
        if (h2 == null || (r = h2.r()) == null) {
            return;
        }
        HomeTopBgView homeTopBgView = new HomeTopBgView(getContext(), true);
        this.K = homeTopBgView;
        homeTopBgView.setId(R.id.home_xview_bg);
        int v = m.v(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, v);
        layoutParams.topMargin = (-v) + h.Q();
        this.K.setLayoutParams(layoutParams);
        this.K.setAlpha(0.0f);
        m.b(r, this.K, r.getChildCount() - 1);
        PullXViewGuideLayout pullXViewGuideLayout = new PullXViewGuideLayout(getContext());
        this.L = pullXViewGuideLayout;
        m.b(r, pullXViewGuideLayout, r.getChildCount() - 1);
    }

    public PullXViewGuideLayout V() {
        return this.L;
    }

    @Override // com.jingdong.app.mall.home.pulltorefresh.JDHomeBaseLoadingView, com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView, com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void d(float f2, boolean z, boolean z2) {
        if (this.B == null) {
            return;
        }
        super.d(f2, z, z2);
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView, com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void e(int i2) {
        super.e(i2);
        i.n(i2);
        if (this.K != null) {
            this.K.setTranslationY((-i2) <= d.d(h.p) ? i2 : -r0);
        }
        PullXViewGuideLayout pullXViewGuideLayout = this.L;
        if (pullXViewGuideLayout == null || pullXViewGuideLayout.l()) {
            return;
        }
        this.L.n(i2);
    }

    @Override // com.jingdong.app.mall.home.pulltorefresh.JDHomeBaseLoadingView, com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView
    protected boolean s() {
        return this.E;
    }
}
