package com.babel.example.floor.entity;

import com.jd.lib.babel.ifloor.entity.BaseFloorModel;
import java.util.List;

/* loaded from: classes.dex */
public class ProductLayoutModel extends BaseFloorModel {
    public List<ProductGroup> productGroupData;

    public List<ProductModel> getFirstList() {
        return this.productGroupData.get(0).productList;
    }

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public int getTotalCount() {
        return this.productGroupData.get(0).productList.size();
    }

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public boolean handleData() {
        List<ProductGroup> list = this.productGroupData;
        return list != null && list.size() > 0 && this.productGroupData.get(0).hasData();
    }
}
