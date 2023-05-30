package com.jingdong.app.mall.home.deploy.view.layout.banner2x4;

import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.b.h.a;
import com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter;

/* loaded from: classes4.dex */
public class BannerCursorPresenter implements ICursorContentViewPresenter {
    private int a;
    private int b;

    public void a(DBanner2x4Model dBanner2x4Model) {
        b(dBanner2x4Model.f("indicatorColor"));
    }

    public void b(String str) {
        int[] iArr = {-1, -1907998};
        int[] n2 = m.n(str, iArr, true);
        if (n2 != null && n2.length >= 2) {
            iArr = n2;
        }
        int[] f2 = a.f(iArr, 255, 102);
        this.a = f2[0];
        this.b = f2[1];
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getBannerCursorColor() {
        return this.b;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorHeight() {
        return d.d(12);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorMarginBottom() {
        return 0;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSelectColor() {
        return this.a;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpace() {
        return d.d(8);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpaceColor() {
        return 855638016;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorWidthUnSelect() {
        return d.d(12);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getSelectWidth() {
        return d.d(24);
    }
}
