package com.jingdong.app.mall.home.floor.ctrl;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.widget.HomeRecycleView;

/* loaded from: classes4.dex */
public class a {
    public static int d = 78;

    /* renamed from: e */
    public static int f9371e;

    /* renamed from: f */
    public static int f9372f;
    private View a;
    private RelativeLayout b;

    /* renamed from: c */
    private int f9373c;

    public a(View view, RelativeLayout relativeLayout, HomeRecycleView homeRecycleView, int i2) {
        ViewGroup.MarginLayoutParams layoutParams;
        this.a = null;
        this.b = null;
        if (relativeLayout == null || view == null) {
            return;
        }
        this.f9373c = i2;
        this.b = relativeLayout;
        this.a = view;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
            layoutParams = (ViewGroup.MarginLayoutParams) layoutParams2;
        } else {
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        }
        if (this.f9373c <= 0) {
            this.f9373c = com.jingdong.app.mall.home.a.f8560i;
        }
        int i3 = this.f9373c;
        if (i3 > 0) {
            layoutParams.topMargin = i3;
        }
        this.a.setLayoutParams(layoutParams);
        this.a.setVisibility(8);
        this.b.addView(this.a);
    }

    public static boolean g(HomeRecycleView homeRecycleView, int i2) {
        if (homeRecycleView == null) {
            return false;
        }
        int d2 = i2 + com.jingdong.app.mall.home.floor.common.d.d(d) + f9372f + 5;
        View d3 = homeRecycleView.d();
        return ((!homeRecycleView.k() || d3 == null) ? com.jingdong.app.mall.home.floor.common.d.f9278f : d3.getTop()) < d2;
    }

    public static boolean h(HomeRecycleView homeRecycleView, int i2, int i3) {
        if (homeRecycleView == null || homeRecycleView.i() < h.T()) {
            return false;
        }
        int d2 = i2 + com.jingdong.app.mall.home.floor.common.d.d(d) + f9372f + 5;
        if (i3 > Math.max(Math.min(com.jingdong.app.mall.home.a.f8564m - d2, com.jingdong.app.mall.home.floor.common.d.f9278f * 1.5f), f9371e)) {
            return true;
        }
        boolean k2 = homeRecycleView.k();
        View d3 = homeRecycleView.d();
        return ((!k2 || d3 == null) ? com.jingdong.app.mall.home.floor.common.d.f9278f : d3.getTop()) < d2;
    }

    public void a() {
        View view = this.a;
        if (view == null) {
            return;
        }
        view.setVisibility(8);
        RelativeLayout relativeLayout = this.b;
        if (relativeLayout != null) {
            relativeLayout.removeView(this.a);
        }
        this.a = null;
    }

    public int b() {
        if (com.jingdong.app.mall.home.floor.common.i.g.b()) {
            return 0;
        }
        View view = this.a;
        if (view instanceof BaseMallFloor) {
            return ((BaseMallFloor) view).getLayoutHeight();
        }
        return 0;
    }

    public int c() {
        View view = this.a;
        if ((view instanceof BaseMallFloor) && view.getVisibility() == 0 && this.a.getParent() != null) {
            return ((BaseMallFloor) this.a).getLayoutHeight();
        }
        return 0;
    }

    public void d() {
        View view = this.a;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public int e(boolean z, int i2, int i3) {
        View view = this.a;
        if (view == null) {
            return 8;
        }
        int i4 = z ? 0 : 8;
        if (i4 == 0 && i3 > 0) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.topMargin = i3;
                this.a.setLayoutParams(marginLayoutParams);
            }
        }
        f(i4);
        return i4;
    }

    public void f(int i2) {
        if (this.a == null || !JDHomeFragment.O0()) {
            return;
        }
        com.jingdong.app.mall.home.a.s.h(false, i2);
        this.a.setVisibility(i2);
    }
}
