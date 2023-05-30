package com.jingdong.app.mall.home.floor.ctrl;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter;
import com.jingdong.app.mall.home.floor.view.widget.LinerPagerCursor;

/* loaded from: classes4.dex */
public class d implements i {
    private ICursorContentViewPresenter a;
    private LinerPagerCursor d;
    private int b = 0;

    /* renamed from: c  reason: collision with root package name */
    private int f9396c = 0;

    /* renamed from: e  reason: collision with root package name */
    private RelativeLayout.LayoutParams f9397e = new RelativeLayout.LayoutParams(-2, -2);

    @Override // com.jingdong.app.mall.home.floor.ctrl.i
    public int a() {
        return this.f9396c;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.i
    public void b(int i2, ViewGroup viewGroup, int i3) {
        if (i2 < 1) {
            viewGroup.setVisibility(8);
        } else if (this.d == null) {
        } else {
            if (viewGroup.getVisibility() == 8) {
                viewGroup.setVisibility(0);
            }
            if (i2 < 2) {
                this.d.setVisibility(8);
                return;
            }
            if (this.d.getVisibility() == 8) {
                this.d.setVisibility(0);
            }
            if (this.d.getParent() == null) {
                viewGroup.addView(this.d);
            }
            this.d.i(i2);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.i
    public boolean c(int i2) {
        return this.b == i2;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.i
    public View d() {
        return this.d;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.i
    public void e(int i2) {
        this.b = i2;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.i
    public void f(LinerPagerCursor linerPagerCursor, int i2, ICursorContentViewPresenter iCursorContentViewPresenter, int i3) {
        this.d = linerPagerCursor;
        this.f9397e.addRule(8, i2);
        this.f9397e.addRule(14);
        this.d.setPadding(0, 0, 0, 0);
        this.f9397e.setMargins(0, 0, 0, i3);
        this.d.setLayoutParams(this.f9397e);
        if (iCursorContentViewPresenter != null) {
            this.d.f(iCursorContentViewPresenter.getBannerCursorColor(), iCursorContentViewPresenter.getCursorSpaceColor(), iCursorContentViewPresenter.getCursorSelectColor());
            this.d.h(iCursorContentViewPresenter.getCursorWidthUnSelect(), iCursorContentViewPresenter.getSelectWidth(), iCursorContentViewPresenter.getCursorHeight(), iCursorContentViewPresenter.getCursorSpace());
        }
        this.a = iCursorContentViewPresenter;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.i
    public void g(int i2) {
        LinerPagerCursor linerPagerCursor = this.d;
        if (linerPagerCursor != null) {
            ViewGroup.LayoutParams layoutParams = linerPagerCursor.getLayoutParams();
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                layoutParams2.setMargins(0, 0, 0, i2);
                this.d.setLayoutParams(layoutParams2);
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.i
    public void h() {
        LinerPagerCursor linerPagerCursor = this.d;
        if (linerPagerCursor == null) {
            return;
        }
        ViewParent parent = linerPagerCursor.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.d);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.i
    public void onPageScrolled(int i2, float f2, int i3) {
        LinerPagerCursor linerPagerCursor = this.d;
        if (linerPagerCursor != null) {
            linerPagerCursor.c(i2, f2, i3);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.i
    public void onPageSelected(int i2) {
        this.f9396c = i2;
        LinerPagerCursor linerPagerCursor = this.d;
        if (linerPagerCursor != null) {
            linerPagerCursor.d(i2);
        }
    }
}
