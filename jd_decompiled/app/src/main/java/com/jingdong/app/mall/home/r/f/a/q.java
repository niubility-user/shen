package com.jingdong.app.mall.home.r.f.a;

import android.text.TextUtils;
import android.widget.ImageView;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.LinearFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.LinearFloorEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLinearLayout;
import com.jingdong.cleanmvp.common.BaseEvent;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class q extends b<LinearFloorEntity, LinearFloorEngine, MallFloorLinearLayout> {

    /* renamed from: h */
    private ArrayList<String> f10781h;

    /* renamed from: i */
    private int f10782i;

    public q(Class cls, Class cls2) {
        super(cls, cls2);
        this.f10781h = new ArrayList<>();
        this.f10782i = 0;
    }

    private void P(com.jingdong.app.mall.home.r.e.d dVar) {
        MallFloorLinearLayout mallFloorLinearLayout = (MallFloorLinearLayout) c();
        if ("08002".equals(dVar.a) && ((LinearFloorEntity) this.d).isSupportLayerBg()) {
            mallFloorLinearLayout.addLayerBg();
        }
    }

    private ArrayList<com.jingdong.app.mall.home.r.e.f> Q(com.jingdong.app.mall.home.r.e.d dVar) {
        ArrayList<ArrayList<com.jingdong.app.mall.home.r.e.f>> b = dVar.b();
        if (this.f10782i == 0) {
            return dVar.a();
        }
        if (b != null && b.size() > this.f10782i - 1) {
            return dVar.b().get(this.f10782i - 1);
        }
        return dVar.a();
    }

    private boolean a0() {
        return "06056".equals(this.f10739f.a) && 3 == this.f10739f.mParentModel.E;
    }

    private void e0() {
        ArrayList<ArrayList<com.jingdong.app.mall.home.r.e.f>> b = this.f10739f.b();
        if (b == null || b.size() < 1) {
            return;
        }
        int i2 = this.f10782i + 1;
        this.f10782i = i2;
        this.f10782i = i2 % (b.size() + 1);
        if (a0()) {
            com.jingdong.app.mall.home.r.e.d dVar = this.f10739f;
            v(dVar.mParentModel, dVar);
        }
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void M(@NotNull IMallFloorUI iMallFloorUI) {
        super.M(iMallFloorUI);
        this.f10782i = 0;
        ArrayList<String> arrayList = this.f10781h;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public List<com.jingdong.app.mall.home.r.e.f> R() {
        return ((LinearFloorEntity) this.d).getFloorItemElements();
    }

    public boolean S() {
        return ((LinearFloorEntity) this.d).isHasSkuAnimation();
    }

    public com.jingdong.app.mall.home.r.e.f T(int i2) {
        return ((LinearFloorEntity) this.d).getItemElement(i2);
    }

    public String U() {
        return ((LinearFloorEntity) this.d).getSubFloorBgImg();
    }

    public int V() {
        return ((LinearFloorEntity) this.d).getLayerRemain();
    }

    public boolean W(int i2) {
        return ((LinearFloorEntity) this.d).getItemElement(i2).c() == 1;
    }

    public ImageView.ScaleType X() {
        return ((LinearFloorEntity) this.d).getSeparationSingleBgImgScaleType();
    }

    public com.jingdong.app.mall.home.r.c.c Y() {
        return ((LinearFloorEntity) this.d).getSpecialExpo();
    }

    public void Z(int i2) {
        ((LinearFloorEntity) this.d).setItemCount(i2);
    }

    public void b0(int i2) {
        ((LinearFloorEntity) this.d).setItemPadding(i2);
    }

    public void c0(int i2) {
        ((LinearFloorEntity) this.d).setItemWeightCount(i2);
    }

    public void d0(ArrayList<Integer> arrayList) {
        ((LinearFloorEntity) this.d).setItemsWidth(arrayList);
    }

    public void f0() {
        com.jingdong.app.mall.home.floor.common.i.m.M(LinearFloorEntity.LAYERSHOWTIMES, ((LinearFloorEntity) this.d).getLayerShowTimes() + 1);
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public ArrayList<String> g() {
        if (this.f10781h.size() > 0) {
            return this.f10781h;
        }
        return super.g();
    }

    public boolean g0() {
        return ((LinearFloorEntity) this.d).useBgMarginColor();
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b, com.jingdong.app.mall.home.r.f.a.c
    public void onEventMainThread(BaseEvent baseEvent) {
        ArrayList<com.jingdong.app.mall.home.r.e.f> Q;
        MallFloorLinearLayout mallFloorLinearLayout;
        String type = baseEvent.getType();
        type.hashCode();
        char c2 = '\uffff';
        switch (type.hashCode()) {
            case -1799025179:
                if (type.equals("data_change")) {
                    c2 = 0;
                    break;
                }
                break;
            case -277321843:
                if (type.equals("home_resume")) {
                    c2 = 1;
                    break;
                }
                break;
            case 436492672:
                if (type.equals("home_splash_close")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                if (a0()) {
                    e0();
                    break;
                }
                break;
            case 1:
                if (a0()) {
                    this.f10781h.clear();
                    if (((IMallFloorUI) c()).isFloorDisplay() && (Q = Q(this.f10739f)) != null && Q.size() >= 1) {
                        for (int i2 = 0; i2 < Q.size(); i2++) {
                            this.f10781h.add(Q.get(i2).j());
                        }
                        break;
                    }
                }
                break;
            case 2:
                if (((LinearFloorEntity) this.d).isSupportLayerBg() && (mallFloorLinearLayout = (MallFloorLinearLayout) c()) != null) {
                    mallFloorLinearLayout.startLayerTask();
                    break;
                }
                break;
        }
        super.onEventMainThread(baseEvent);
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        MallFloorLinearLayout mallFloorLinearLayout;
        ArrayList<com.jingdong.app.mall.home.r.e.f> a;
        int i2;
        int i3;
        int i4;
        int i5;
        super.v(hVar, dVar);
        if (dVar == null || (mallFloorLinearLayout = (MallFloorLinearLayout) c()) == null) {
            return;
        }
        if (!j()) {
            mallFloorLinearLayout.onInitViewData();
        }
        int itemViewCount = ((LinearFloorEntity) this.d).getItemViewCount();
        ArrayList<com.jingdong.app.mall.home.r.e.f> a2 = dVar.a();
        if (a0()) {
            a = Q(dVar);
        } else {
            a = dVar.a();
        }
        ArrayList<com.jingdong.app.mall.home.r.e.f> arrayList = a;
        if (itemViewCount != 0 && itemViewCount == a2.size()) {
            mallFloorLinearLayout.onSetVisible(true);
            com.jingdong.app.mall.home.floor.common.i.t tVar = dVar.q;
            int layoutInnerWidth = ((LinearFloorEntity) this.d).getLayoutInnerWidth();
            int itemDividerWidth = ((LinearFloorEntity) this.d).getItemDividerWidth();
            int itemWeightCount = ((LinearFloorEntity) this.d).getItemWeightCount();
            int averageItemCalculateWidth = ((LinearFloorEntity) this.d).getAverageItemCalculateWidth(itemViewCount);
            w(itemViewCount);
            int i6 = 0;
            int i7 = 0;
            while (i6 < itemViewCount) {
                com.jingdong.app.mall.home.r.e.f fVar = arrayList.get(i6);
                if (a0() && mallFloorLinearLayout.isFloorDisplay()) {
                    this.f10781h.add(fVar.j());
                }
                com.jingdong.app.mall.home.floor.common.i.r modelTypeEnum = tVar.getModelTypeEnum(fVar.a0());
                boolean C = C(i6);
                boolean D = D(i6);
                if (((LinearFloorEntity) this.d).isHaveItemWidths()) {
                    int itemWidth = ((LinearFloorEntity) this.d).getItemWidth(i6);
                    i2 = itemWeightCount > 0 ? (itemWidth * averageItemCalculateWidth) + ((itemWidth - 1) * itemDividerWidth) : com.jingdong.app.mall.home.floor.common.d.d(itemWidth);
                } else {
                    i2 = averageItemCalculateWidth;
                }
                if (i6 == itemViewCount - 1) {
                    i4 = layoutInnerWidth - itemDividerWidth;
                    i3 = layoutInnerWidth;
                } else {
                    int i8 = layoutInnerWidth - i2;
                    if (i6 > 0) {
                        i8 -= itemDividerWidth;
                    }
                    ((LinearFloorEntity) this.d).addItemDividerPath(i8, true);
                    i3 = i8;
                    i4 = i2;
                }
                Object obj = null;
                if (!TextUtils.isEmpty(fVar.P())) {
                    mallFloorLinearLayout.initMixedFloorViewItem(dVar, fVar, i4, ((LinearFloorEntity) this.d).getLayoutHeight(), i6, Integer.valueOf(i7));
                } else if (!D && !C) {
                    obj = mallFloorLinearLayout.initFloorViewItem(fVar, i4, ((LinearFloorEntity) this.d).getLayoutHeight(), i6, Integer.valueOf(i7));
                } else {
                    FloorEntity floorEntity = this.d;
                    i5 = i6;
                    obj = mallFloorLinearLayout.initSeparationFloorViewItem(dVar, modelTypeEnum, fVar, floorEntity, i4, ((LinearFloorEntity) floorEntity).getLayoutHeight(), i6, Integer.valueOf(i7));
                    i7 = (obj == null && (obj instanceof Integer)) ? ((Integer) obj).intValue() : i7 + 1;
                    i6 = i5 + 1;
                    layoutInnerWidth = i3;
                }
                i5 = i6;
                if (obj == null) {
                }
                i6 = i5 + 1;
                layoutInnerWidth = i3;
            }
            P(dVar);
            mallFloorLinearLayout.postWaitMainThreadQue();
            return;
        }
        mallFloorLinearLayout.cleanUI();
        mallFloorLinearLayout.onSetVisible(false);
    }
}
