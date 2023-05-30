package com.jingdong.app.mall.bundle.dolphinlib.floor.entity;

import android.text.TextUtils;
import com.jd.lib.babel.ifloor.entity.BaseFloorModel;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinStoreFloorModel extends BaseFloorModel {
    public List<AdvertGroupData> advertGroupData;
    public DolphinStoreFloorConfig boardParams;
    public List<ProductGroupData> productGroupData;

    public List<AdvertEntity> getStoreList() {
        List<AdvertGroupData> list = this.advertGroupData;
        if (list == null || list.size() <= 0) {
            return null;
        }
        return this.advertGroupData.get(0).advertList;
    }

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public boolean handleData() {
        List<AdvertGroupData> list = this.advertGroupData;
        if (list == null || list.size() == 0) {
            return false;
        }
        List<AdvertEntity> list2 = this.advertGroupData.get(0).advertList;
        DolphinStoreFloorConfig dolphinStoreFloorConfig = this.boardParams;
        if (dolphinStoreFloorConfig != null) {
            dolphinStoreFloorConfig.addBgImages();
        }
        if (list2 == null || list2.size() < 3 || this.productGroupData == null || list2.size() != this.productGroupData.size()) {
            return false;
        }
        int i2 = 0;
        while (i2 < list2.size()) {
            list2.get(i2).productList = this.productGroupData.get(i2).productList;
            list2.get(i2).skuGroupId = this.productGroupData.get(i2).groupId;
            if (list2.get(i2).productList != null && list2.get(i2).productList.size() >= 4) {
                if (this.boardParams != null && TextUtils.isEmpty(list2.get(i2).pictureUrl)) {
                    list2.get(i2).pictureUrl = this.boardParams.getBgImage();
                }
            } else {
                list2.remove(i2);
                this.productGroupData.remove(i2);
                i2--;
            }
            i2++;
        }
        return list2.size() >= 3;
    }
}
