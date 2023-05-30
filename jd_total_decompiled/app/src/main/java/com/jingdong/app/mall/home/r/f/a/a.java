package com.jingdong.app.mall.home.r.f.a;

import com.jingdong.app.mall.home.floor.model.entity.ListItemFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.ListItemFloorEngine;
import com.jingdong.app.mall.home.floor.view.adapter.BaseListItemPagerAdapter;
import com.jingdong.app.mall.home.floor.view.view.MallFloorPanic;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class a<E extends ListItemFloorEntity, G extends ListItemFloorEngine, U extends MallFloorPanic> extends b<E, G, U> implements BaseListItemPagerAdapter.b {

    /* renamed from: h */
    protected boolean f10736h;

    public a(Class cls, Class cls2) {
        super(cls, cls2);
    }

    public String P() {
        return ((ListItemFloorEntity) this.d).getAdvertImgUrl();
    }

    public int Q() {
        return ((ListItemFloorEntity) this.d).getContentHeight();
    }

    public int R() {
        return ((ListItemFloorEntity) this.d).getContentWidth();
    }

    public Object S(int i2) {
        return ((ListItemFloorEntity) this.d).getItemByPosition(i2);
    }

    public List<?> T() {
        return ((ListItemFloorEntity) this.d).getItemList();
    }

    public int U() {
        if (((ListItemFloorEntity) this.d).isItemListEmpty()) {
            return 0;
        }
        int itemListSize = ((ListItemFloorEntity) this.d).getItemListSize();
        if (itemListSize > ((ListItemFloorEntity) this.d).getListItemCountLimit()) {
            this.f10736h = true;
            return ((ListItemFloorEntity) this.d).isHaveAdvert() ? itemListSize + 2 : itemListSize + 1;
        }
        this.f10736h = false;
        return ((ListItemFloorEntity) this.d).isHaveAdvert() ? itemListSize + 1 : itemListSize;
    }

    public String V(boolean z) {
        return ((ListItemFloorEntity) this.d).getMaiDianSourceValue(z);
    }

    public boolean W() {
        return this.f10736h;
    }

    public boolean X() {
        return ((ListItemFloorEntity) this.d).isHaveAdvert();
    }

    public boolean Y() {
        return ((ListItemFloorEntity) this.d).isItemListEmpty();
    }

    public void Z() {
        ((ListItemFloorEntity) this.d).resetItemListFromTmp();
    }
}
