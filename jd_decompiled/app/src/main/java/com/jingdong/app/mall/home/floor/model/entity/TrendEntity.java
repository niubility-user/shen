package com.jingdong.app.mall.home.floor.model.entity;

import com.jingdong.app.mall.home.r.e.k.e;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class TrendEntity extends FloorEntity {
    private List<e> itemList = new ArrayList();

    public void addItem(e eVar) {
        this.itemList.add(eVar);
    }

    public e getItemAt(int i2) {
        if (i2 < 0 || i2 >= this.itemList.size()) {
            return null;
        }
        return this.itemList.get(i2);
    }

    public List<e> getItemList() {
        return this.itemList;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public boolean isValid() {
        return this.itemList.size() >= 4;
    }
}
