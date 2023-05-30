package com.jingdong.app.mall.home.category.floor.base;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.drawable.ScalingUtils;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.n.g.u.f;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public abstract class BaseCaSkuTitleFloor<M extends f> extends BaseCaRecycleItem<M> {
    protected DarkWhiteBgImageView q;
    protected GradientTextView r;
    protected com.jingdong.app.mall.home.n.g.w.d s;
    private int t;

    public BaseCaSkuTitleFloor(Context context) {
        super(context);
        o(context);
        com.jingdong.app.mall.home.n.g.w.d r = r();
        this.s = r;
        if (r == null || !r.b()) {
            return;
        }
        DarkWhiteBgImageView darkWhiteBgImageView = new DarkWhiteBgImageView(context);
        this.q = darkWhiteBgImageView;
        darkWhiteBgImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.q.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
        this.q.setId(R.id.mallfloor_item9);
        RelativeLayout.LayoutParams u = this.s.a.u(this.q);
        u.addRule(14);
        addView(this.q, u);
        GradientTextView gradientTextView = new GradientTextView(context);
        this.r = gradientTextView;
        gradientTextView.setGravity(16);
        this.r.setSingleLine();
        this.r.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, this.s.b.h());
        layoutParams.addRule(14);
        n(layoutParams);
        q(layoutParams);
        addView(this.r);
    }

    private void q(RelativeLayout.LayoutParams layoutParams) {
        if (this.t != com.jingdong.app.mall.home.floor.common.d.f9279g) {
            com.jingdong.app.mall.home.floor.common.f.c(this.q, this.s.a);
            com.jingdong.app.mall.home.floor.common.f.b(this.r, -2, this.s.b.h());
            this.r.setMaxWidth(this.s.b.v());
            this.s.b.P(this.r);
            this.s.b.D(layoutParams);
            this.r.setLayoutParams(layoutParams);
            this.t = com.jingdong.app.mall.home.floor.common.d.f9279g;
        }
    }

    protected void n(RelativeLayout.LayoutParams layoutParams) {
    }

    protected void o(Context context) {
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: p  reason: merged with bridge method [inline-methods] */
    public void e(@NotNull M m2) {
        int i2 = this.s.f10389c;
        if (i2 < 0) {
            this.r.setTextColor(i2);
        }
        ViewGroup.LayoutParams layoutParams = this.r.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        q((RelativeLayout.LayoutParams) layoutParams);
        this.r.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(this.s.d));
        e.m(this.q, m2.i(), this.s.a());
        s(m2);
    }

    protected abstract com.jingdong.app.mall.home.n.g.w.d r();

    protected void s(@NotNull M m2) {
        String j2 = m2.j();
        this.r.setText(j2);
        int i2 = TextUtils.isEmpty(j2) ? 8 : 0;
        if (this.r.getVisibility() != i2) {
            this.r.setVisibility(i2);
        }
    }
}
