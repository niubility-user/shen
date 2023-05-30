package com.jingdong.app.mall.home.r.f.a;

import com.jingdong.app.mall.home.floor.model.entity.BubbleBannerV936Entity;
import com.jingdong.app.mall.home.floor.presenter.engine.BubbleBannerV936Engine;
import com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBubbleBannerV936;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class h extends b<BubbleBannerV936Entity, BubbleBannerV936Engine, MallFloorBubbleBannerV936> implements ICursorContentViewPresenter {

    /* renamed from: h */
    private int f10755h;

    public boolean P() {
        return ((BubbleBannerV936Entity) this.d).getPlayAnimation() == 1 && S() > 0 && Y() > 1;
    }

    public void Q() {
        int Y = this.f10755h % Y();
        String str = "realPage: " + Y;
        int Z = (Y + 1) * Z() * 4;
        for (int Z2 = Z() * Y * 4; Z2 < Z; Z2++) {
            ((BubbleBannerV936Entity) this.d).addItemExpoJson(W(Z2));
            ((BubbleBannerV936Entity) this.d).addItemExpo(V(Z2));
        }
    }

    public int R() {
        return ((BubbleBannerV936Entity) this.d).getPlayAnimationDelay();
    }

    public int S() {
        return ((BubbleBannerV936Entity) this.d).getPlayAnimationSpeed();
    }

    public String T() {
        return ((BubbleBannerV936Entity) this.d).getBgImg();
    }

    public com.jingdong.app.mall.home.r.e.f U(int i2) {
        ArrayList<com.jingdong.app.mall.home.r.e.f> data = ((BubbleBannerV936Entity) this.d).getData();
        return (data == null || i2 >= data.size()) ? new com.jingdong.app.mall.home.r.e.f(null, 0) : data.get(i2);
    }

    public String V(int i2) {
        com.jingdong.app.mall.home.r.e.f fVar;
        int dataSize = ((BubbleBannerV936Entity) this.d).getDataSize();
        ArrayList<com.jingdong.app.mall.home.r.e.f> data = ((BubbleBannerV936Entity) this.d).getData();
        return (i2 < 0 || i2 >= dataSize || data == null || (fVar = data.get(i2)) == null) ? "" : fVar.j();
    }

    public com.jingdong.app.mall.home.r.c.b W(int i2) {
        return ((BubbleBannerV936Entity) this.d).getItemJson(i2);
    }

    public int X() {
        return 2000;
    }

    public int Y() {
        return ((BubbleBannerV936Entity) this.d).getDataSize() / (Z() * 4);
    }

    public int Z() {
        return ((BubbleBannerV936Entity) this.d).getShowRow();
    }

    public int a0() {
        if (Y() == 0 || Y() == 1) {
            return 0;
        }
        int i2 = 1000;
        if (1000 % Y() == 0) {
            return 1000;
        }
        while (i2 % Y() != 0) {
            i2--;
        }
        return i2;
    }

    public boolean b0() {
        return ((BubbleBannerV936Entity) this.d).getIsShowSubTitle();
    }

    public void c0(int i2) {
        this.f10755h = i2;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getBannerCursorColor() {
        return ((BubbleBannerV936Entity) this.d).getBannerCursorColor();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorHeight() {
        return ((BubbleBannerV936Entity) this.d).getCursorHeight();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorMarginBottom() {
        return ((BubbleBannerV936Entity) this.d).getCursorMarginBottom();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSelectColor() {
        return ((BubbleBannerV936Entity) this.d).getCursorSelectColor();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpace() {
        return ((BubbleBannerV936Entity) this.d).getCursorSpace();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpaceColor() {
        return ((BubbleBannerV936Entity) this.d).getCursorSpaceColor();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorWidthUnSelect() {
        return ((BubbleBannerV936Entity) this.d).getCursorWidthUnSelect();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getSelectWidth() {
        return ((BubbleBannerV936Entity) this.d).getCursorWidthUnSelect();
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        IMallFloorUI iMallFloorUI = (IMallFloorUI) c();
        if (iMallFloorUI == null) {
            return;
        }
        if (((BubbleBannerV936Entity) this.d).isValid()) {
            iMallFloorUI.onSetVisible(true);
            iMallFloorUI.onRefreshView();
            return;
        }
        iMallFloorUI.onSetVisible(false);
    }
}
